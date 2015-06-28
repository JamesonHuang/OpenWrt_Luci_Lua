/************************************************
 *
 * file  : TcpSocket.h
 * author: bobding
 * date  : 2014-09-25
 * detail:
 *
************************************************/

#ifndef _TCPSOCKET_H_
#define _TCPSOCKET_H_

#include <stdlib.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <errno.h>
#include "Log.h"

int sockfd;

void initTcpSocket(){
    sockfd = -1;
}

// return >=0 means success, otherwise failed.
int socketOpen(){
    sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (sockfd < 0)
    {
        LogError("[Socket::Open] create socket failed: %s.\n", strerror(errno));
    }
    return sockfd;
}

// host: 127.0.0.1 or www.qq.com
// return 0 means success, otherwise failed.
int socketConnect(const char* host, unsigned short port)
{
    if (sockfd < 0)
    {
        LogError("[Socket::Connect] invalid sockfd.\n");
        return -1;
    }

    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));

    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);

    struct hostent* h = gethostbyname(host);
    if (0 == h)
    {
        LogError("[Socket::Connect] gethostbyname failed: %s.\n", strerror(errno));
        return -1;
    }

    addr.sin_addr = *(struct in_addr*)h->h_addr_list[0];

    int ret = connect(sockfd, (const struct sockaddr*)&addr, sizeof(addr));
    if (ret < 0)
    {
        LogError("[Socket::Connect] connect failed: %s.\n", strerror(errno));
        return -1;
    }

    return 0;
}

// return send bytes, -1 means failed.
int socketSend(const char* buffer, unsigned int length)
{
    if (sockfd < 0)
    {
        LogError("[Socket::Send] invalid sockfd.\n");
        return -1;
    }

    int ret = send(sockfd, buffer, length, 0);
    if (ret < 0)
    {
        LogError("[Socket::Send] send failed: %s.\n", strerror(errno));
        return -1;
    }

    return ret;
}

// return recv bytes, -1 means failed.
int socketRecv(char* buffer, unsigned int length)
{
    if (sockfd < 0)
    {
        LogError("[Socket::Recv] invalid sockfd.\n");
        return -1;
    }

    int ret = recv(sockfd, buffer, length, 0);
    if (ret < 0)
    {
        LogError("[Socket::Recv] recv failed: %s.\n", strerror(errno));
        return -1;
    }

    return ret;
}

// return 0 success, otherwise failed.
int Close()
{
    int ret = close(sockfd);
    if (ret < 0)
    {
        LogError("[Socket::Close] close socket failed: %s.\n", strerror(errno));
    }

    sockfd = -1;

    return ret;
}

int GetSocket()
{
    return sockfd;
}

/*
class TcpSocket
{
public:
    TcpSocket() 
    {
        sockfd = -1;
    }

    // return >=0 means success, otherwise failed.
    int Open()
    {
        sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
        if (sockfd < 0)
        {
            LogError("[Socket::Open] create socket failed: %s.\n", strerror(errno));
        }

        return sockfd;
    }

    // host: 127.0.0.1 or www.qq.com
    // return 0 means success, otherwise failed.
    int Connect(const char* host, unsigned short port)
    {
        if (sockfd < 0)
        {
            LogError("[Socket::Connect] invalid sockfd.\n");
            return -1;
        }

        struct sockaddr_in addr;
        memset(&addr, 0, sizeof(addr));

        addr.sin_family = AF_INET;
        addr.sin_port = htons(port);

        struct hostent* h = gethostbyname(host);
        if (0 == h)
        {
            LogError("[Socket::Connect] gethostbyname failed: %s.\n", strerror(errno));
            return -1;
        }

        addr.sin_addr = *(struct in_addr*)h->h_addr_list[0];

        int ret = connect(sockfd, (const struct sockaddr*)&addr, sizeof(addr));
        if (ret < 0)
        {
            LogError("[Socket::Connect] connect failed: %s.\n", strerror(errno));
            return -1;
        }

        return 0;
    }

    // return send bytes, -1 means failed.
    int Send(const char* buffer, unsigned int length)
    {
        if (sockfd < 0)
        {
            LogError("[Socket::Send] invalid sockfd.\n");
            return -1;
        }

        int ret = send(sockfd, buffer, length, 0);
        if (ret < 0)
        {
            LogError("[Socket::Send] send failed: %s.\n", strerror(errno));
            return -1;
        }

        return ret;
    }

    // return recv bytes, -1 means failed.
    int Recv(char* buffer, unsigned int length)
    {
        if (sockfd < 0)
        {
            LogError("[Socket::Recv] invalid sockfd.\n");
            return -1;
        }

        int ret = recv(sockfd, buffer, length, 0);
        if (ret < 0)
        {
            LogError("[Socket::Recv] recv failed: %s.\n", strerror(errno));
            return -1;
        }

        return ret;
    }

    // return 0 success, otherwise failed.
    int Close()
    {
        int ret = close(sockfd);
        if (ret < 0)
        {
            LogError("[Socket::Close] close socket failed: %s.\n", strerror(errno));
        }

        sockfd = -1;

        return ret;
    }

    int GetSocket()
    {
        return sockfd;
    }

private:
    int sockfd;
};
*/

#endif // _TCPSOCKET_H_
