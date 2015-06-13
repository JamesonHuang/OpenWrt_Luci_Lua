/******************************************************************************
 * FileName: 
 * Description: 
 * Version: 
 * History: 
 *     yuxiaobo    2014-12-25    1.0    created
 * 
 *****************************************************************************/
#ifndef __NEBULA_ENCRYPT_DECRYPT_WRAPPER_H__
#define __NEBULA_ENCRYPT_DECRYPT_WRAPPER_H__ 
#include <stdint.h>

/*
 * 消息加密接口
 * @param type 加密类型
 * @param key 加密需要的key
 * @param src 加密的原始数据
 * @param dst 加密的结果数据
 * @param len 加密后的数据长度
 * @return 0 成功 其他 失败
 */
int NebulaEncrypt(uint8_t type, const char *key, uint32_t key_len,
                   const char *src, uint32_t src_len,
                   char *dst, uint32_t* len);

int NebulaDecrypt(uint8_t type, const char *key, uint32_t key_len,
                   const char *src, uint32_t src_len,
                   char *dst, uint32_t* len);

#endif
