#include "connect.h"
#include <arpa/inet.h>
#include <netinet/in.h>
#include <string.h>
#include "config.h"
#include "net.h"
#include "auth.h"
#include "subscribe.h"
#include "nebula.pb-c.h"
#include "nebula_proto.h"
#include "nebula_compress_uncompress_wrapper.h"
#include "nebula_encrypt_decrypt_wrapper.h"
#include "push.h"
#include "timer.h"

static Conn* g_conn = NULL;
timer_entry g_ping_entry;

void Ping(timer_heap_t* ht, timer_entry *entry);

static void __on_connect(Conn* conn, int err)
{
    if (err) {
        // connect failed.
        log_error("connect server_addr:%s:%d error:%s", conn->host, conn->port, strerror(err));
        PopFront(g_server_list);
        jump_status(eStatus_Connect);
        return ;
    }

    log_info("connect success, server_addr:%s:%d", conn->host, conn->port);
    timer_entry_init(&g_ping_entry, 0, NULL, &Ping);
    timer_add(g_timer, &g_ping_entry, 180);
    next_status();
}

static void __handle_message(NebulaHeader* head)
{
    static char *buf1 = NULL;
    if (!buf1)
        buf1 = (char*)malloc(MAX_BUF_SIZE);
    static char *buf2 = NULL;
    if (!buf2)
        buf2 = (char*)malloc(MAX_BUF_SIZE);

    log_debug("NEBULA RX %s|%d", MsgType2Str(head->type), (int)htons(head->len));

    if ((head->type & 0x7f) == NEBULA_MSG_PING) {
        log_debug("pong.");
        return ;
    }

    Nebula__NebulaMsg *msg = nebula__nebula_msg__unpack(NULL, htons(head->len), (uint8_t*)(head + 1));
    if (!msg) {
        log_debug("NebulaMsg parser error.");
        return ;
    }

    char *data = (char*)msg->body.data;
    uint32_t len = msg->body.len;

    int compress_type = head->flag >> 4;
    int encrypt_type = head->flag & 0x0f;
    if (compress_type) {
        // uncompress
        uint32_t dst_len = MAX_BUF_SIZE;
        if (-1 == NebulaUnCompress(compress_type, data, len, buf1, &dst_len)) {
            log_debug("uncompress error. head->type=%d", head->type);
            return ;
        }

        data = buf1;
        len = dst_len;
    }

    if (encrypt_type) {
        // unencrypt
        uint32_t dst_len = MAX_BUF_SIZE;
        if (-1 == NebulaDecrypt(encrypt_type, g_token,
                    strlen(g_token), data, len, buf2, &dst_len)) {
            log_debug("unencrypt error. head->type=%d", head->type);
            return ;
        }

        data = buf2;
        len = dst_len;
    }

    // dispatch
    if (MSG_IS_REQUEST(head)) {
        // request
        switch (head->type & 0x7f) {
            case NEBULA_MSG_PSH:
                NebulaOnPush(head, data, len);
                break;

            case NEBULA_MSG_MSG:
                NebulaOnMsg(head, data, len);
                break;
        }
    } else {
        // response
        switch (head->type & 0x7f) {
            case NEBULA_MSG_AUTH:
                NebulaOnAuth(head, data, len);
                break;

            case NEBULA_MSG_SUB:
                NebulaOnSub(head, data, len);
                break;
        }
    }
}

static void __on_read(Conn* conn, NebulaHeader* head)
{
    (void)conn;
    __handle_message(head);
}

static void __on_disconnect(Conn* conn)
{
    (void)conn;
    log_warn("tcp net disconnected, retry connect...");
    timer_del(g_timer, &g_ping_entry);
    if (Nebulaget_status() > eStatus_Connect)
        jump_status(eStatus_Connect);
}

int NebulaConnect()
{
    if (!g_conn) {
        g_conn = NebulaConnCreate();
        NebulaConnInit(g_conn, __on_connect, __on_read, __on_disconnect);
        if (!g_conn) return -1;
    }

    if (g_conn->state == eConnState_Estab) {
        NebulaDisconnect();
    }

    if (!g_server_list->size) {
        // 没有ip列表, 重新redirect.
        jump_status(eStatus_Redirect);
        return -1;
    }

    struct sockaddr_in *addr = (struct sockaddr_in *)Front(g_server_list);
    uint16_t port = htons(addr->sin_port);
    char ip[32] = {};
    inet_ntop(AF_INET, &addr->sin_addr.s_addr, ip, sizeof(ip));

    if (-1 == NebulaConnConnect(g_conn, ip, port)) {
        log_error("connect error, server_addr:%s:%d", ip, port);
        PopFront(g_server_list);
        return -1;
    }

    log_info("connect to server:%s:%d", ip, port);
    return 0;
}

int NebulaSend(uint8_t type, ProtobufCMessage *msg)
{
    NebulaHeader head;
    HEAD_INIT(&head, type);

    Nebula__NebulaMsg nebula_msg;
    nebula__nebula_msg__init(&nebula_msg);
    char msgid_buf[24];
    CreateMsgid(msgid_buf);
    nebula_msg.msgid = msgid_buf;
    if (msg) {
        nebula_msg.has_body = 1;
        nebula_msg.body.len = protobuf_c_message_get_packed_size(msg);
        nebula_msg.body.data = (uint8_t*)malloc(nebula_msg.body.len);
        if (!nebula_msg.body.data) {
            log_error("malloc error.");
            return -1;
        }

        protobuf_c_message_pack(msg, nebula_msg.body.data);
    }

    int ret = NebulaConnSend(g_conn, &head, (ProtobufCMessage *)&nebula_msg);
    if (nebula_msg.has_body) {
        free(nebula_msg.body.data);
    }
    return ret;
}

void Ping(timer_heap_t* ht, timer_entry *entry)
{
    NebulaHeader head;
    HEAD_INIT(&head, MSG_SET_REQUEST(NEBULA_MSG_PING));
    head.flag = 180;
    //TODO: dynamic ping's time interval.
    timer_update(ht, entry, head.flag * 1000);
    if (-1 == NebulaConnSend(g_conn, &head, NULL)) {
        log_error("Ping send error.");
    }
}

void NebulaDisconnect()
{
    NebulaConnNebulaDisconnect(g_conn);
}

