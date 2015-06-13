#include "net.h"
#include <unistd.h>
#include <sys/socket.h>
#include <sys/epoll.h>
#include <sys/fcntl.h>
#include <sys/uio.h>
#include <arpa/inet.h>
#include <sys/time.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "logger.h"

#define EPOLL_SIZE 32
int g_epollfd = -1;

struct _NebulaBuf
{
    int len;
    int pos;
    char buf[0];
};
typedef struct _NebulaBuf NebulaBuf;

static int __read(Conn* conn);
static int __write(Conn* conn);
static void __err(Conn* conn);

Conn* NebulaConnCreate()
{
    if (g_epollfd == -1) {
        g_epollfd = epoll_create(EPOLL_SIZE);
        if (g_epollfd == -1) return NULL;
    }

    Conn* conn = (Conn*)malloc(sizeof(Conn));
    memset(conn, 0, sizeof(Conn));
    conn->read = __read;
    conn->write = __write;
    conn->err = __err;
    conn->send_list = CreateList();
    if (-1 == pthread_mutex_init(&conn->mtx, NULL)) {
        free(conn);
        return NULL;
    }

    if (!conn->send_list) {
        free(conn);
        return NULL;
    }

    return conn;
}

void NebulaConnInit(Conn* conn, void(*on_connect)(Conn*, int),
        void(*on_read)(Conn*, NebulaHeader*), void(*on_disconnect)(Conn*))
{
    conn->on_connect = on_connect;
    conn->on_read = on_read;
    conn->on_disconnect = on_disconnect;
}

int NebulaConnConnect(Conn* conn, const char* host, uint16_t port)
{
    if (!conn || !host || !port || !*host) return -1;

    if (conn->state == eConnState_Estab || conn->state == eConnState_Connecting)
        return -1;

    if (conn->host) free(conn->host);
    conn->host = strdup(host);
    conn->port = port;

    if (conn->socketfd >= 0) {
        epoll_ctl(g_epollfd, EPOLL_CTL_DEL, conn->socketfd, NULL);
        close(conn->socketfd);
        conn->socketfd = -1;
    }
    conn->state = eConnState_Init;

    conn->socketfd = socket(AF_INET, SOCK_STREAM, 0);
    if (conn->socketfd == -1) return -1;

    int flag = fcntl(conn->socketfd, F_GETFL);
    if (fcntl(conn->socketfd, F_SETFL, flag | O_NONBLOCK) == -1)
        return -1;

    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = inet_addr(host);

    int ret;
retry_connect:
    ret = connect(conn->socketfd, (struct sockaddr*)&addr, sizeof(addr));
    if (ret == 0) {
        // connect completed immediately. 
        conn->state = eConnState_Estab;
        if (conn->on_connect)
            conn->on_connect(conn, 0);

        struct epoll_event ev = {EPOLLIN, {conn}};
        epoll_ctl(g_epollfd, EPOLL_CTL_ADD, conn->socketfd, &ev);
        return 0;
    } else {
        if (errno == EINTR)
            goto retry_connect;

        if (errno != EINPROGRESS) {
            // connect error.
            conn->state = eConnState_Error;
            close(conn->socketfd);
            conn->socketfd = -1;
            return -1;
        }

        // connect not completed immediately, wait from epoll_wait.
        struct epoll_event ev = {EPOLLOUT, {conn}};
        epoll_ctl(g_epollfd, EPOLL_CTL_ADD, conn->socketfd, &ev);
        conn->state = eConnState_Connecting;
    }

    return 0;
}

int NebulaConnSend(Conn* conn, NebulaHeader *head, ProtobufCMessage *msg)
{
    if (!conn || conn->state != eConnState_Estab) return -1;

    int len = sizeof(NebulaHeader);
    if (msg) {
        int msg_len = protobuf_c_message_get_packed_size(msg);
        head->len = htons(msg_len);
        len += msg_len;
    }
    NebulaBuf *buf = (NebulaBuf*)malloc(sizeof(NebulaBuf) + len);
    buf->len = len;
    buf->pos = 0;
    memcpy(buf->buf, head, sizeof(NebulaHeader));
    if (msg)
        protobuf_c_message_pack(msg, (uint8_t*)buf->buf + sizeof(NebulaHeader));

    log_debug("NEBULA TX %s|%d", MsgType2Str(head->type), (int)htons(head->len));

    pthread_mutex_lock(&conn->mtx);
    if (-1 == PushBack(conn->send_list, buf)) {
        free(buf);
        return -1;
    }
    pthread_mutex_unlock(&conn->mtx);

    struct epoll_event ev = {EPOLLOUT | EPOLLIN, {conn}};
    epoll_ctl(g_epollfd, EPOLL_CTL_MOD, conn->socketfd, &ev);
    return 0;
}

void NebulaConnLoop()
{
    if (g_epollfd == -1) {
        g_epollfd = epoll_create(EPOLL_SIZE);
        if (g_epollfd == -1) return;
    }

    int n, i;
    struct epoll_event ev[EPOLL_SIZE];
    n = epoll_wait(g_epollfd, ev, EPOLL_SIZE, 20);
    if (n == -1) return ;

    for (i = 0; i < n; i++) {
        Conn *c = (Conn*)ev[i].data.ptr;

        if (ev[i].events & EPOLLHUP || ev[i].events & EPOLLERR) {
            c->err(c);
            continue;
        }

        if (ev[i].events & EPOLLIN) {
            if (c->read(c) < 0) {
                c->err(c);
                continue;
            }
        }

        if (ev[i].events & EPOLLOUT) {
            if (c->write(c) < 0) {
                c->err(c);
                continue;
            }
        }
    }
}

static int __read(Conn* conn)
{
    ssize_t n;
retry_read:
    n = read(conn->socketfd, conn->recv_buf + conn->recv_pos,
            sizeof(conn->recv_buf) - conn->recv_pos);
    if (n == -1) {
        if (errno == EINTR)
            goto retry_read;

        if (errno == EAGAIN)
            return 0;

        return -1;
    }

    if (n == 0)
        return -1;

    conn->recv_pos += n;
    int pos = 0;
    for (;;)
    {
        if (conn->recv_pos - pos < sizeof(NebulaHeader))
            break;

        NebulaHeader *head = (NebulaHeader*)(conn->recv_buf + pos);
        int pack_len = sizeof(NebulaHeader) + htons(head->len);
        if (pack_len > sizeof(conn->recv_buf))
            return -1;

        if (conn->recv_pos - pos < pack_len)
            break;

        if (conn->on_read)
            conn->on_read(conn, head);
        pos += pack_len;
    }

    if (pos > 0) {
        conn->recv_pos -= pos;
        memmove(conn->recv_buf + pos, conn->recv_buf, conn->recv_pos);
    }

    return 0;
}

static int __write(Conn* conn)
{
    if (conn->state == eConnState_Connecting) {
        // check connect result.
        int err = 0;
        socklen_t sl = sizeof(int);
        int ret = getsockopt(conn->socketfd, SOL_SOCKET, SO_ERROR, &err, &sl);
        if (ret == -1 || err != 0) {
            // connect failed.
            conn->state = eConnState_Error;
            epoll_ctl(g_epollfd, EPOLL_CTL_DEL, conn->socketfd, NULL);
            close(conn->socketfd);
            conn->socketfd = -1;
            if (conn->on_connect)
                conn->on_connect(conn, (ret == -1 ? errno : err));
            return -1;
        }

        struct epoll_event ev = {EPOLLIN, {conn}};
        epoll_ctl(g_epollfd, EPOLL_CTL_MOD, conn->socketfd, &ev);
        conn->state = eConnState_Estab;
        if (conn->on_connect)
            conn->on_connect(conn, 0);
        return 0;
    }

    pthread_mutex_lock(&conn->mtx);
    struct iovec iov[UIO_MAXIOV];
    ListIterator it = ListBegin(conn->send_list);
    int iov_cnt = 0;
    for (; it && iov_cnt < UIO_MAXIOV; it = ListNext(it), iov_cnt++)
    {
        NebulaBuf *buf = *(NebulaBuf **)it;
        iov[iov_cnt].iov_len = buf->len - buf->pos;
        iov[iov_cnt].iov_base = buf->buf + buf->pos;
    }

    ssize_t n;
retry_write:
    n = writev(conn->socketfd, iov, iov_cnt);
    if (n == -1) {
        if (errno == EINTR)
            goto retry_write;

        if (errno == EAGAIN)
            return 0;

        return -1;
    }

    if (n == 0)
        return -1;

    for (;;)
    {
        NebulaBuf *buf = (NebulaBuf *)Front(conn->send_list);
        int len = buf->len - buf->pos;
        if (len < n) {
            n -= len;
            PopFront(conn->send_list);
            continue;
        } else if (len == n) {
            n -= len;
            PopFront(conn->send_list);
            break;
        } else if (len > n) {
            buf->pos += n;
            n = 0;
            break;
        }
    }
    pthread_mutex_unlock(&conn->mtx);

    if (conn->send_list->size == 0) {
        struct epoll_event ev = {EPOLLIN, {conn}};
        epoll_ctl(g_epollfd, EPOLL_CTL_MOD, conn->socketfd, &ev);
    }

    return 0;
}

static void __err(Conn* conn)
{
    conn->state = eConnState_Error;
    epoll_ctl(g_epollfd, EPOLL_CTL_DEL, conn->socketfd, NULL);
    close(conn->socketfd);
    conn->socketfd = -1;
    conn->on_disconnect(conn);
}

void NebulaConnNebulaDisconnect(Conn* conn)
{
    conn->err(conn);
}
