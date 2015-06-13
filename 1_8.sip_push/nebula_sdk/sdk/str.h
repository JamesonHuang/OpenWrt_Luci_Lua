#ifndef __STUN_STRING_H__
#define __STUN_STRING_H__

#ifdef __cplusplus
extern "C" {
#endif
#include <sys/types.h>
#include <string.h>
#include <stdbool.h>

struct str{
    size_t size;     /*buf所占内存长度*/
    size_t len;      /*buf字符长度*/
    char* ptr;
};

void string_init(struct str *str);

void string_deinit(struct str *str);

bool string_empty(const struct str *str);

int string_duplicate(struct str *dst, const struct str *src);

int string_copy(struct str *dst, const char *src, size_t srclen);

int string_sprintf(struct str *str, const char* fmt, ...);

int string_compare(const struct str *s1, const struct str *s2);

void string_reset(struct str *str);

int string_append(struct str *str, const char *buf, size_t buflen);

#define string_length(s) ((s)->len)

#define string_append_fast(s, bufptr, bufsize) \
    do { \
        if (((s)->size - (s)->len) > bufsize) { \
            memcpy((s)->ptr + (s)->len, (bufptr), bufsize); \
            (s)->len += bufsize; \
            (s)->ptr[(s)->len] = '\0'; \
        } else { \
            string_append((s), (bufptr), bufsize); \
        } \
    }while(0)

#ifdef __cplusplus
}
#endif

#endif

