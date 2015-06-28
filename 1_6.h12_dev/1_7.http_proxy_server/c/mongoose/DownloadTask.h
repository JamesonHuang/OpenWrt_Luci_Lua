/************************************************
 *
 * file  : DownloadTask.h
 * author: bobding
 * date  : 2014-09-25
 * detail:
 *
************************************************/

#ifndef _DOWNLOADTASK_H_
#define _DOWNLOADTASK_H_

#include "TcpSocket.h"
#include "HttpUtils.h"

#define MAXBUFFERSIZE   2048

//TcpSocket* socket;
/*
void initDownloadTask()
{
    //socket = 0;
    initTcpSocket();
    //socket = new TcpSocket();
}

void destroyDownloadTask()
{
    if (0 != socket)
    {
        free(socket);
    }

    socket = 0;
}
*/

int taskStart(const char* url, char** buffer, unsigned int* length)
{
    /*if (0 == socket)
    {
        LogError("[DownloadTask::Start] invalid socket, url: %s.\n", url);
        return -1;
    }*/

    int ret = socketOpen();
    if (ret < 0)
    {
        return -1;
    }

    struct HttpUrl httpUrl = ParseUrl(url);

    ret = socketConnect(httpUrl.host, httpUrl.port);
    if (ret < 0)
    {
        return -1;
    }

    ret = socketSend(httpUrl.request, strlen(httpUrl.request));
    if (ret < 0)
    {
        return -1;
    }

    char dummy[MAXBUFFERSIZE];
    ret = socketRecv(dummy, MAXBUFFERSIZE);   // receive 1st package.
    if (ret <= 0)
    {
        LogError("[DownloadTask::Start] recv head 0 bytes, url: %s.\n", url);
        return -1;
    }

    dummy[ret] = '\0';

    struct HttpHead httpHead = ParseHead(dummy, strlen(dummy));
    if (HS_FAIL == httpHead.httpState)
    {
        LogError("[DownloadTask::Start] recv failed, url: %s.\n", url);
        return -1;
    }
    else if (HS_RETRY == httpHead.httpState)
    {
        LogWarn("[DownloadTask::Start] retry, url: %s.\n", url);
        return taskStart(url, buffer, length);
    }
    else if (HS_REDIR == httpHead.httpState)
    {
        LogWarn("[DownloadTask::Start] redir, url: %s, location: %s.\n", url, httpHead.location);
        return taskStart(httpHead.location, buffer, length);
    }

    *length = httpHead.contentLength;
    *buffer = (char *)malloc(*length * sizeof(char*));
    //*buffer = new char[*length];
    memset(*buffer, 0, *length);
    unsigned int offset = 0;

    if (ret > httpHead.headLength)
    {
        memcpy((*buffer) + offset, dummy + httpHead.headLength, ret - httpHead.headLength);
        offset += ret - httpHead.headLength;
    }

    while ((ret = socketRecv(dummy, MAXBUFFERSIZE)) >= 0)
    {
        LogPrompt("[DownloadTask::Start] received: %d, %d / %d bytes.\n", ret, offset, *length);

        if (offset >= *length)
        {
            break;
        }

        if (0 == ret)
        {
            continue;
        }

        if (offset + ret > *length)
        {
            ret = *length - offset;
        }

        memcpy((*buffer) + offset, dummy, ret);

        offset += ret;
    }
    return 0;
}


/*
class DownloadTask
{
public:
    DownloadTask()
    {
        socket = 0;
        socket = new TcpSocket();
    }

    ~DownloadTask()
    {
        if (0 != socket)
        {
            delete socket;
        }

        socket = 0;
    }

    int Start(const char* url, char** buffer, unsigned int* length)
    {
        if (0 == socket)
        {
            LogError("[DownloadTask::Start] invalid socket, url: %s.\n", url);
            return -1;
        }

        int ret = socket->Open();
        if (ret < 0)
        {
            return -1;
        }

        HttpUrl httpUrl = HttpUtils::ParseUrl(url);

        ret = socket->Connect(httpUrl.host, httpUrl.port);
        if (ret < 0)
        {
            return -1;
        }

        ret = socket->Send(httpUrl.request, strlen(httpUrl.request));
        if (ret < 0)
        {
            return -1;
        }

        char dummy[MAXBUFFERSIZE];
        ret = socket->Recv(dummy, MAXBUFFERSIZE);   // receive 1st package.
        if (ret <= 0)
        {
            LogError("[DownloadTask::Start] recv head 0 bytes, url: %s.\n", url);
            return -1;
        }

        dummy[ret] = '\0';

        HttpHead httpHead = HttpUtils::ParseHead(dummy, strlen(dummy));
        if (HS_FAIL == httpHead.httpState)
        {
            LogError("[DownloadTask::Start] recv failed, url: %s.\n", url);
            return -1;
        }
        else if (HS_RETRY == httpHead.httpState)
        {
            LogWarn("[DownloadTask::Start] retry, url: %s.\n", url);
            return Start(url, buffer, length);
        }
        else if (HS_REDIR == httpHead.httpState)
        {
            LogWarn("[DownloadTask::Start] redir, url: %s, location: %s.\n", url, httpHead.location);
            return Start(httpHead.location, buffer, length);
        }

        *length = httpHead.contentLength;
        *buffer = new char[*length];
        memset(*buffer, 0, *length);
        unsigned int offset = 0;

        if (ret > httpHead.headLength)
        {
            memcpy((*buffer) + offset, dummy + httpHead.headLength, ret - httpHead.headLength);
            offset += ret - httpHead.headLength;
        }

        while ((ret = socket->Recv(dummy, MAXBUFFERSIZE)) >= 0)
        {
            LogPrompt("[DownloadTask::Start] received: %d, %d / %d bytes.\n", ret, offset, *length);

            if (offset >= *length)
            {
                break;
            }

            if (0 == ret)
            {
                continue;
            }

            if (offset + ret > *length)
            {
                ret = *length - offset;
            }

            memcpy((*buffer) + offset, dummy, ret);

            offset += ret;
        }

        return 0;
    }

private:
    TcpSocket* socket;
};
*/
#endif // _DOWNLOADTASK_H_
