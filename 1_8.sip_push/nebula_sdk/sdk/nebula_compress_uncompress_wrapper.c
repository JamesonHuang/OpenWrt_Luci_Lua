#include "nebula_compress_uncompress_wrapper.h"
#include <snappy-c.h>
#include <zlib.h>

int NebulaCompress(uint8_t type, const char* src, uint32_t src_len,
                   char *dst, uint32_t *dst_len)
{
    int res;
    size_t _src_len = (size_t)src_len;
    size_t _dst_len = (size_t)*dst_len;

    switch(type) {
        case 1:
            if (Z_OK != compress((uint8_t*)dst, &_dst_len, (uint8_t*)src, _src_len)) {
                res = -1;
            }else{
                res = 0;
                *dst_len = (uint32_t)_dst_len;
            }
            break;
        case 2:
            if (0 != snappy_compress(src, _src_len, dst, &_dst_len)) {
                res = -1;
            }else{
                res = 0;
                *dst_len = (uint32_t)_dst_len;
            }
            break;
        default:
            res = -1;
            break;
    }

    return res;
}

int NebulaUnCompress(uint8_t type, const char* src, uint32_t src_len,
                     char *dst, uint32_t *dst_len)
{
    int res;
    size_t _src_len = (size_t)src_len;
    size_t _dst_len = (size_t)*dst_len;

    switch(type) {
        case 1:
            if (Z_OK != uncompress((uint8_t*)dst, &_dst_len, (uint8_t*)src, _src_len)) {
                res = -1;
            }else{
                res = 0;
                *dst_len = (uint32_t)_dst_len;
            }
            break;
        case 2:
            if (0 != snappy_uncompress(src, _src_len, dst, &_dst_len)) {
                res = -1;
            }else{
                res = 0;
                *dst_len = (uint32_t)_dst_len;
            }
            break;
        default:
            res = -1;
            break;
    }
    return res;
}

