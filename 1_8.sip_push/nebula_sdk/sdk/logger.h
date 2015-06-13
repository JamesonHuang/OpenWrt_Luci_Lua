/*
 * =====================================================================================
 *
 *       Filename:  logger.h
 *
 *    Description:  日志接口
 *
 *        Version:  1.0
 *        Created:  2013年08月20日 20时09分00秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  xiaoboyu, 
 *   Organization:  
 *
 * =====================================================================================
 */
#ifndef __LOGGER_H__
#define __LOGGER_H__

#ifdef __cplusplus
extern "C" {
#endif

enum LogLevelTypes //日志分级
{
    LOG_LEVEL_ERR,   /* error conditions */
    LOG_LEVEL_WARN,  /* warning conditions */
    LOG_LEVEL_NOTICE,
    LOG_LEVEL_INFO,
    LOG_LEVEL_DEBUG,
    LOG_LEVEL_VERB
};

typedef struct logger_t logger_t;

extern logger_t logger;

#define log_error(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_ERR)){ \
            log_write(&logger, LOG_LEVEL_ERR, __FILE__, __LINE__, __FUNCTION__, \
                 fmt, ## __VA_ARGS__); \
        } \
    } while(0)
#define log_warn(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_WARN)){ \
            log_write(&logger, LOG_LEVEL_WARN, __FILE__, __LINE__, __FUNCTION__, \
                 fmt, ## __VA_ARGS__); \
        } \
    } while(0)

#define log_notice(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_NOTICE)){ \
            log_write(&logger, LOG_LEVEL_NOTICE, __FILE__, __LINE__, __FUNCTION__, \
                fmt, ## __VA_ARGS__); \
        } \
    } while(0)

#define log_info(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_INFO)){ \
            log_write(&logger, LOG_LEVEL_INFO, __FILE__, __LINE__, __FUNCTION__, \
                fmt, ## __VA_ARGS__); \
        } \
    } while(0)

#define log_debug(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_DEBUG)){ \
            log_write(&logger, LOG_LEVEL_DEBUG, __FILE__, __LINE__, __FUNCTION__, \
                 fmt, ## __VA_ARGS__); \
        } \
    } while(0)

#define log_verb(fmt, ...) \
    do{ \
        if(log_loggable(&logger, LOG_LEVEL_VERB)){ \
            log_write(&logger, LOG_LEVEL_VERB, __FILE__, __LINE__, __FUNCTION__, \
                 fmt, ## __VA_ARGS__); \
        } \
    } while(0)

void log_init(logger_t *log, const char *name, int level); 

void log_deinit(logger_t *log);
/*
 * @brief 设置log的级别
 */
void log_level_set(logger_t *log, int level);

void log_reopen(logger_t *log);

void log_level_up(logger_t *log);

void log_level_down(logger_t *log);

int  log_loggable(logger_t *log, int level);

void log_write(logger_t *log, int level,
        const char *file, int line,
        const char* func, const char *fmt, ...)
    __attribute__((format(printf, 6, 7)));

#ifdef __cplusplus
}
#endif
#endif // ~LOGGER_H_201106241335

