#include "nebula_encrypt_decrypt_wrapper.h"
#include "crypto_framwork.h"

int NebulaEncrypt(uint8_t type, const char *key, uint32_t key_len,
                   const char *src, uint32_t src_len,
                   char *dst, uint32_t* len)
{
    int res;
    switch (type) {
        case 1:
            res = CRYPTO_encrypt_without_key((const uint8_t*)src, src_len, (uint8_t*)dst, len);
            break;
        case 2:
            res = CRYPTO1_encrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        case 3:
            res = CRYPTO2_encrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        case 4:
            res = CRYPTO3_encrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        default:
            res = -1;
            break;
    }
    return res;
}

int NebulaDecrypt(uint8_t type, const char *key, uint32_t key_len,
                   const char *src, uint32_t src_len,
                   char *dst, uint32_t* len)
{
    int res;
    switch (type) {
        case 1:
            res = CRYPTO_decrypt_without_key((const uint8_t*)src, src_len, (uint8_t*)dst, len);
            break;
        case 2:
            res = CRYPTO1_decrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        case 3:
            res = CRYPTO2_decrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        case 4:
            res = CRYPTO3_decrypt((uint8_t*)key, key_len, (const uint8_t*)src,
                    src_len, (uint8_t*)dst, len);
            break;
        default:
            res = -1;
            break;
    }
    return res;
}
