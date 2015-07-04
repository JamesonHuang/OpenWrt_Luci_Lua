/************************************************
 *
 * file  : HttpUtils.h
 * author: bobding
 * date  : 2014-09-25
 * detail:
 *
************************************************/

#ifndef _HTTPUTILS_H_
#define _HTTPUTILS_H_

#define MAXURLLENGTH 1024

struct HttpUrl
{
    char host[MAXURLLENGTH];    // host
    char file[MAXURLLENGTH];    // file
    unsigned short port;        // port
    char request[MAXURLLENGTH]; // request
};

enum HttpState
{
    HS_FAIL,
    HS_SUCC,
    HS_RETRY,
    HS_REDIR,
};

struct HttpHead
{
    unsigned int httpCode;      // http code
    enum HttpState httpState;        // state
    unsigned int contentLength; // content length
    unsigned int headLength;    // head length
    char location[MAXURLLENGTH];// redirection
};

static struct HttpUrl ParseUrl(const char* url)
{
        struct HttpUrl httpUrl;
        memset(&httpUrl, 0, sizeof(struct HttpUrl));

        unsigned int length = strlen(url);
        if (length > MAXURLLENGTH)
        {
            LogError("[HttpUtils::ParseUrl] url is too long, url: %s.\n", url);
            return httpUrl;
        }

        memcpy(httpUrl.host, url, strlen(url));

        const char* s = strchr(httpUrl.host, '/');
        if (0 != s)
        {
            httpUrl.host[s - httpUrl.host] = '\0';
            s++;
            memcpy(httpUrl.file, s, strlen(s));
        }

        s = strchr(httpUrl.host, ':');
        if (0 != s)
        {
            httpUrl.host[s - httpUrl.host] = '\0';
            s++;
            httpUrl.port = (unsigned short)atoi(s);
        }
        else
        {
            httpUrl.port = 80;
        }

        snprintf(httpUrl.request, MAXURLLENGTH - 1, 
            "GET /%s HTTP/1.1\r\n"
            "Host: %s\r\n\r\n",
            httpUrl.file, httpUrl.host);

        LogPrompt("[HttpUtils::ParseUrl] host: %s, file: %s, port: %d.\n", httpUrl.host, httpUrl.file, httpUrl.port);
        return httpUrl;
}

static struct HttpHead ParseHead(const char* buffer, unsigned int length)
{
    struct HttpHead httpHead;
    memset(&httpHead, 0, sizeof(struct HttpHead));

    const char* s = strstr(buffer, "HTTP/1.1 ");
    if (0 != s)
    {
        httpHead.httpCode = (unsigned int)atoi(s + strlen("HTTP/1.1 "));
    }

    if (200 == httpHead.httpCode || 206 == httpHead.httpCode)
    {
        httpHead.httpState = HS_SUCC;
    }
    else if (201 == httpHead.httpCode)
    {
        httpHead.httpState = HS_REDIR;
    }
    else if (202 == httpHead.httpCode)
    {
        httpHead.httpState = HS_RETRY;
    }
    else if (httpHead.httpCode >= 300 && httpHead.httpCode < 400)
    {
        httpHead.httpState = HS_REDIR;
    }
    else
    {
        httpHead.httpState = HS_FAIL;
    }

    if (HS_REDIR == httpHead.httpState && 0 != (s = strstr(buffer, "Location: ")))
    {
        const char* e = strstr(s, "\r\n");
        s += strlen("Location: ");
        memcpy(httpHead.location, s, (0 == e) ? strlen(s) : (e - s));
    }

    s = strstr(buffer, "Content-Length: ");
    if (0 != s)
    {
        httpHead.contentLength = (unsigned int)atoi(s + strlen("Content-Length: "));
    }

    s = strstr(buffer, "\r\n\r\n");
    if (0 != s)
    {
        httpHead.headLength = s + strlen("\r\n\r\n") - buffer;
    }

    LogPrompt("[HttpUtils::ParseHead] httpCode: %d, contentLength: %d, headLength: %d.\n", 
        httpHead.httpCode, httpHead.contentLength, httpHead.headLength);
    
    return httpHead;
}



/*
class HttpUtils
{
public:
    static HttpUrl ParseUrl(const char* url)
    {
        HttpUrl httpUrl;
        memset(&httpUrl, 0, sizeof(HttpUrl));

        unsigned int length = strlen(url);
        if (length > MAXURLLENGTH)
        {
            LogError("[HttpUtils::ParseUrl] url is too long, url: %s.\n", url);
            return httpUrl;
        }

        memcpy(httpUrl.host, url, strlen(url));

        const char* s = strchr(httpUrl.host, '/');
        if (0 != s)
        {
            httpUrl.host[s - httpUrl.host] = '\0';
            s++;
            memcpy(httpUrl.file, s, strlen(s));
        }

        s = strchr(httpUrl.host, ':');
        if (0 != s)
        {
            httpUrl.host[s - httpUrl.host] = '\0';
            s++;
            httpUrl.port = (unsigned short)atoi(s);
        }
        else
        {
            httpUrl.port = 80;
        }

        snprintf(httpUrl.request, MAXURLLENGTH - 1, 
            "GET /%s HTTP/1.1\r\n"
            "Host: %s\r\n\r\n",
            httpUrl.file, httpUrl.host);

        LogPrompt("[HttpUtils::ParseUrl] host: %s, file: %s, port: %d.\n", httpUrl.host, httpUrl.file, httpUrl.port);

        return httpUrl;
    }

    static HttpHead ParseHead(const char* buffer, unsigned int length)
    {
        HttpHead httpHead;
        memset(&httpHead, 0, sizeof(HttpHead));

        const char* s = strstr(buffer, "HTTP/1.1 ");
        if (0 != s)
        {
            httpHead.httpCode = (unsigned int)atoi(s + strlen("HTTP/1.1 "));
        }

        if (200 == httpHead.httpCode || 206 == httpHead.httpCode)
        {
            httpHead.httpState = HS_SUCC;
        }
        else if (201 == httpHead.httpCode)
        {
            httpHead.httpState = HS_REDIR;
        }
        else if (202 == httpHead.httpCode)
        {
            httpHead.httpState = HS_RETRY;
        }
        else if (httpHead.httpCode >= 300 && httpHead.httpCode < 400)
        {
            httpHead.httpState = HS_REDIR;
        }
        else
        {
            httpHead.httpState = HS_FAIL;
        }

        if (HS_REDIR == httpHead.httpState && 0 != (s = strstr(buffer, "Location: ")))
        {
            const char* e = strstr(s, "\r\n");
            s += strlen("Location: ");
            memcpy(httpHead.location, s, (0 == e) ? strlen(s) : (e - s));
        }

        s = strstr(buffer, "Content-Length: ");
        if (0 != s)
        {
            httpHead.contentLength = (unsigned int)atoi(s + strlen("Content-Length: "));
        }

        s = strstr(buffer, "\r\n\r\n");
        if (0 != s)
        {
            httpHead.headLength = s + strlen("\r\n\r\n") - buffer;
        }

        LogPrompt("[HttpUtils::ParseHead] httpCode: %d, contentLength: %d, headLength: %d.\n", 
            httpHead.httpCode, httpHead.contentLength, httpHead.headLength);

        return httpHead;
    }
};
*/

#endif // _HTTPUTILS_H_
