#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <stdio.h>
#include "str.h"

#define DEFAULT_STRING_SIZE 32
#define SMAX(a, b) ((a) > (b) ? (a) : (b))

void string_init(struct str *str)
{
    str->size = 0;
    str->len  = 0;
    str->ptr  = NULL;
}

void string_deinit(struct str *str)
{
    if(str->ptr){
        free(str->ptr);
        string_init(str);
    }
}

bool string_empty(const struct str *str)
{
    return str->len == 0 ? true : false;
}

int string_duplicate(struct str *dst, const struct str *src)
{
    string_reset(dst);
    return string_append(dst, src->ptr, src->len);
}

int string_copy(struct str *dst, const char *src, size_t srclen)
{
    string_reset(dst);
    return string_append(dst, src, srclen);
}

#ifndef _GNU_SOURCE 
static int vasprintf(char **buf, const char *fmt, va_list ap)
{
    static char _T_emptybuffer = '\0';
    int chars;
    char *b;

    if(!buf) { return -1; }

    chars = vsnprintf(&_T_emptybuffer, 0, fmt, ap)+1;
    if(chars < 0) { 
        chars *= -1; 
    }

    b = (char*)malloc(sizeof(char)*chars);
    if(!b) { return -1; }
    if((chars = vsprintf(b, fmt, ap)) < 0)
    {
        free(b);
    } else {
        *buf = b;
    }

    return chars;
}
#endif

int string_sprintf(struct str *str, const char* fmt, ...)
{
    va_list ap;
    char *t = NULL;
    int size;
    char buf[128];

//    string_reset(str);

    va_start(ap, fmt);
    size = vsnprintf(buf, 128, fmt, ap);
    va_end(ap);

    if(size == -1 || size > 127) {
        va_start(ap, fmt);
        if((size = vasprintf(&t, fmt, ap)) == -1) { 
            va_end(ap); 
            return -1; 
        }
        va_end(ap);

        size = string_append(str, t, size);
        free(t);
        return size;
    }
    
    return string_append(str, buf, size);
}

int string_compare(const struct str *s1, const struct str *s2)
{
    if (s1->len != s2->len) {
        return s1->len - s2->len > 0 ? 1 : -1;
    }
    return strncmp(s1->ptr, s2->ptr, s1->len);
}

void string_reset(struct str *str)
{
    if(str->ptr) {
        str->len = 0;
        str->ptr[0] = '\0';
    }
}

int string_append(struct str *str, const char *buf, size_t buflen)
{
    if( str->size - str->len <= buflen ) {
        size_t new_size = SMAX(DEFAULT_STRING_SIZE, 
                SMAX(str->size * 2, str->len + buflen + 8));
        char *t = (char*)realloc(str->ptr, new_size);
        if( t == NULL )
            return -1;

        str->size = new_size;
        str->ptr = t;
    }

    memcpy(str->ptr + str->len, buf, buflen);
    str->len += buflen;
    str->ptr[str->len] = '\0';
    return buflen;
}
