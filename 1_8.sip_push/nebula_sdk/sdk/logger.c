#include <stdint.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdarg.h>
#include <sys/time.h>
#include <time.h>
#include <string.h>
#include <assert.h>
#include "logger.h"

#define MIN(a, b)           ((a) < (b) ? (a) : (b))
#define MAX(a, b)           ((a) > (b) ? (a) : (b))

#ifndef LOG_MAX_LEN
#define LOG_MAX_LEN         8192
#endif

typedef struct logger_t {
    int fd;
    int level;
    char* name;
}logger_t;

logger_t logger = {STDERR_FILENO, LOG_LEVEL_VERB, NULL};

static const char* strlevel[] = {
    "ERROR",
    "WARN",
    "NOTICE",
    "INFO",
    "DEBUG",
    "VERB"
};

void log_init(logger_t *log, const char* name, int level)
{
    log->level = MAX(LOG_LEVEL_ERR, MIN(level, LOG_LEVEL_VERB));
    if(name == NULL || *name == '\0'){
        log->fd = STDERR_FILENO;
    }else{
        log->name = strdup(name);
        if(log->name == NULL) {
            log->fd = STDERR_FILENO;
            return;
        }
        log->fd = open(name, O_WRONLY | O_APPEND | O_CREAT, 0644);
        if(log->fd < 0 ) {
            log->fd = STDERR_FILENO;
        }
    }
}

void log_deinit(logger_t *log)
{
    if(log->fd > 0 && log->fd != STDERR_FILENO)
        close(log->fd);
    if(log->name)
        free(log->name);
}

void log_write(logger_t *log, int level, const char *file, int line, const char* func, const char *fmt, ...)
{
    struct tm local;
    struct timeval tv;
    va_list args;
    int len;
    char buffer[LOG_MAX_LEN];

    gettimeofday(&tv, NULL);
    localtime_r(&tv.tv_sec, &local);

    level = MAX(LOG_LEVEL_ERR, MIN(level, LOG_LEVEL_VERB));

    len = snprintf(buffer, sizeof(buffer), "[%04d-%02d-%02d %02d:%02d:%02d.%06lu]|"
            "%s|%s:%d:(%s):", local.tm_year+1900, local.tm_mon+1, local.tm_mday, 
            local.tm_hour, local.tm_min, local.tm_sec, tv.tv_usec,  
            strlevel[level], file, line, func);

    if(len < LOG_MAX_LEN - 1){
        va_start(args, fmt);
        len += vsnprintf(buffer + len, LOG_MAX_LEN-len, fmt, args);
        va_end(args);
        len = (len >= LOG_MAX_LEN ? LOG_MAX_LEN - 1 : len);
    }else{
        len = LOG_MAX_LEN - 1;
    }
    buffer[len++] = '\n';
    len = write(log->fd, buffer, len);
    (void)len;
}

int log_loggable(logger_t *log, int level)
{
    if(level > log->level || level < 0)
        return 0;
    return 1;
}

void log_level_set(logger_t *log, int level)
{
    log->level = MAX(LOG_LEVEL_ERR, MIN(level, LOG_LEVEL_VERB));
}

void log_reopen(logger_t *log)
{
    if(log->fd > 0 && log->fd != STDERR_FILENO){
        close(log->fd);
        log->fd = open(log->name, O_WRONLY | O_APPEND | O_CREAT, 0644);
        if(log->fd < 0) {
            log->fd = STDERR_FILENO;
        }
    }

    return;
}

void log_level_up(logger_t *log)
{
    if(log->level < LOG_LEVEL_VERB)
        log->level++;
}

void log_level_down(logger_t *log)
{
    if(log->level > LOG_LEVEL_ERR)
        log->level--;
}

