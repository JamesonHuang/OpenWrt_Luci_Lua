/******************************************************************************
 * FileName: nebula_compress_uncompress_wrapper.h
 * Description: 压缩和解压缩
 * Version: 1.0
 * History: 
 *     yuxiaobo    2015-01-20    1.0    created
 * 
 *****************************************************************************/
#ifndef __NEBULA_COMPRESS_UNCOMPRESS_H__
#define __NEBULA_COMPRESS_UNCOMPRESS_H__ 
#include <stdint.h>

int NebulaCompress(uint8_t type, const char* src, uint32_t src_len,
                   char *dst, uint32_t *dst_len);

int NebulaUnCompress(uint8_t type, const char* src, uint32_t src_len,
                     char *dst, uint32_t *dst_len);

#endif

