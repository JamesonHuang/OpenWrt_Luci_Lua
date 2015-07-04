/************************************************
 *
 * file  : Log.h
 * author: bobding
 * date  : 2014-09-24
 * detail:
 *
************************************************/

#ifndef _LOG_H_
#define _LOG_H_

#include <stdio.h>
#include <unistd.h>
#include <stdarg.h>
#include <memory.h>
#include <time.h>
#include <sys/time.h>

#define LogCritical Critical
#define LogError    Error
#define LogWarn     Warn
#define LogPrompt   Prompt

#define LOGPATH "./1.log"

FILE* handle;
/*
static Log* Instance()
{
    static Log instance;
    return &instance;
}
*/

const char* FormatedTime()
{
    static char strDate[128];
    memset(strDate, 0, sizeof(strDate));

    struct timeval tv;
    int ret = gettimeofday(&tv, 0);
    if (ret < 0)
    {
        return strDate;
    }

    struct tm* pt = localtime(&tv.tv_sec);
    snprintf(strDate, 127, "%d-%02d-%02d %02d:%02d:%02d.%03d", pt->tm_year + 1900, pt->tm_mon + 1, pt->tm_mday, pt->tm_hour, pt->tm_min, pt->tm_sec, (int)(tv.tv_usec / 1000));

    return strDate;
}

void initLog()
{
    handle = 0;
    if (-1 != access(LOGPATH, F_OK))
    {
        remove(LOGPATH);
    }
    handle = fopen(LOGPATH, "w");
}

void destroyLog()
{
    if (0 != handle)
    {
        fclose(handle);
    }

    handle = 0;
}

// flush every item
int Critical(const char* fmt, ...)
{
    initLog();
    static char buffer[128 + 1024];
    memset(buffer, 0, sizeof(buffer));
    snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Critical");

    va_list ap;
    va_start(ap, fmt);
    int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
    va_end(ap);

    fwrite(buffer, strlen(buffer), 1, handle);
    fflush(handle);

    printf("%s", buffer);
    destroyLog();
    return length;
}

// flush every item
int Error(const char* fmt, ...)
{
    initLog();
    if (0 == handle)
    {
        return -1;
    }

    static char buffer[128 + 1024];
    memset(buffer, 0, sizeof(buffer));
    snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Error");

    va_list ap;
    va_start(ap, fmt);
    int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
    va_end(ap);

    fwrite(buffer, strlen(buffer), 1, handle);
    fflush(handle);

    printf("%s", buffer);
    destroyLog();
    return length;
}

// flush every 100 items, careless lost
int Warn(const char* fmt, ...)
{
    initLog();
    static unsigned int maxCnt = 100;
    static unsigned int curCnt = 0;

    if (0 == handle)
    {
        return -1;
    }

    static char buffer[128 + 1024];
    memset(buffer, 0, sizeof(buffer));
    snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Warn");

    va_list ap;
    va_start(ap, fmt);
    int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
    va_end(ap);

    fwrite(buffer, strlen(buffer), 1, handle);

    if (++curCnt >= maxCnt)
    {
        fflush(handle);
        curCnt = 0;
    }

    printf("%s", buffer);
    destroyLog();
    return length;
}

// stdout
int Prompt(const char* fmt, ...)
{
    initLog();
    static char buffer[128 + 1024];
    memset(buffer, 0, sizeof(buffer));
    snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Prompt");

    va_list ap;
    va_start(ap, fmt);
    int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
    va_end(ap);

    printf("%s", buffer);
    destroyLog();
    return length;
}

/*
class Log
{
private:
    FILE* handle;

public:
    static Log* Instance()
    {
        static Log instance;
        return &instance;
    }

public:
    Log() : handle(0)
    {
        if (-1 != access(LOGPATH, F_OK))
        {
            remove(LOGPATH);
        }

        handle = fopen(LOGPATH, "w");
    }

    ~Log()
    {
        if (0 != handle)
        {
            fclose(handle);
        }

        handle = 0;
    }

    // flush every item
    int Critical(const char* fmt, ...)
    {
        static char buffer[128 + 1024];
        memset(buffer, 0, sizeof(buffer));
        snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Critical");

        va_list ap;
        va_start(ap, fmt);
        int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
        va_end(ap);

        fwrite(buffer, strlen(buffer), 1, handle);
        fflush(handle);

        printf("%s", buffer);

        return length;
    }

    // flush every item
    int Error(const char* fmt, ...)
    {
        if (0 == handle)
        {
            return -1;
        }

        static char buffer[128 + 1024];
        memset(buffer, 0, sizeof(buffer));
        snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Error");

        va_list ap;
        va_start(ap, fmt);
        int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
        va_end(ap);

        fwrite(buffer, strlen(buffer), 1, handle);
        fflush(handle);

        printf("%s", buffer);

        return length;
    }

    // flush every 100 items, careless lost
    int Warn(const char* fmt, ...)
    {
        static unsigned int maxCnt = 100;
        static unsigned int curCnt = 0;

        if (0 == handle)
        {
            return -1;
        }

        static char buffer[128 + 1024];
        memset(buffer, 0, sizeof(buffer));
        snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Warn");

        va_list ap;
        va_start(ap, fmt);
        int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
        va_end(ap);

        fwrite(buffer, strlen(buffer), 1, handle);

        if (++curCnt >= maxCnt)
        {
            fflush(handle);
            curCnt = 0;
        }

        printf("%s", buffer);

        return length;
    }

    // stdout
    int Prompt(const char* fmt, ...)
    {
        static char buffer[128 + 1024];
        memset(buffer, 0, sizeof(buffer));
        snprintf(buffer, 128, "%s [%-8s] ", FormatedTime(), "Prompt");

        va_list ap;
        va_start(ap, fmt);
        int length = vsnprintf((char*)(buffer+strlen(buffer)), 1023, fmt, ap);
        va_end(ap);

        printf("%s", buffer);

        return length;
    }

protected:
    const char* FormatedTime()
    {
        static char strDate[128];
        memset(strDate, 0, sizeof(strDate));

        struct timeval tv;
        int ret = gettimeofday(&tv, 0);
        if (ret < 0)
        {
            return strDate;
        }

        struct tm* pt = localtime(&tv.tv_sec);
        snprintf(strDate, 127, "%d-%02d-%02d %02d:%02d:%02d.%03d", pt->tm_year + 1900, pt->tm_mon + 1, pt->tm_mday, pt->tm_hour, pt->tm_min, pt->tm_sec, (int)(tv.tv_usec / 1000));

        return strDate;
    }
};
*/
#endif  // _LOG_H_
