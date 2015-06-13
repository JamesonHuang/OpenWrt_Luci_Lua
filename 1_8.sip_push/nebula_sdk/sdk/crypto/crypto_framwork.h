#ifndef CRYPTO_FRAMWROK_H
#define CRYPTO_FRAMWROK_H

#include <inttypes.h>

#ifdef __cplusplus
extern "C" {
#endif

#define CRYPTO_OK					0
#define CRYPTO_UNDEF_MEMORY			-1
#define CRYPTO_TOOSHORT_DST_LEN		-2
#define CRYPTO_TOOSHORT_KEY_LEN		-3
#define CRYPTO_SET_KEY_ERROR		-4
#define CRYPTO_SRC_LEN_ZERO			-5

#define CRYPTO1_BLOCK_SIZE			16
#define CRYPTO2_BLOCK_SIZE			16
#define CRYPTO3_BLOCK_SIZE			8
/*
 * CRYPTO_UNDEF_MEMORY means key,src,dst is NULL
 * CRYPTO_TOOSHORT_DST_LEN means dst_len too short
 *	if(*len < src_len)
		return CRYPTO_TOOSHORT_DST_LEN;
	if(*len < src_len + BLOCK_SIZE - src_len % BLOCK_SIZE)
		if(src_len % BLOCK_SIZE != 0)
		return CRYPTO_TOOSHORT_DST_LEN;
	typically src_len = *len = BLOCK_SIZE * n (n = 1,2,3....) is recommend
 * CRYPTO_TOOSHORT_KEY_LEN means key_len < 16
 * CRYPTO_SET_KEY_ERROR means set key error(hardly occurs)
 */

/*
 * fast   : CRYPTO3 > CRYPTO2 > CRYPTO1
 * secure : CRYPTO2 > CRYPTO1 > CRYPTO3
 * 128 bits key is less secure than 256 bits,but run faster
 */
/* CRYPTO1 is fast */
int CRYPTO1_encrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

int CRYPTO1_decrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

/* CRYPTO2 is slightly slower but safer*/
int CRYPTO2_encrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

int CRYPTO2_decrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

/* CRYPTO3 is less secure but faster*/
int CRYPTO3_encrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

int CRYPTO3_decrypt(const unsigned char *key,const uint32_t key_len,const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);

/* implement of CRYPTO2 */
int CRYPTO_encrypt_without_key(const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);
int CRYPTO_decrypt_without_key(const unsigned char *src,const uint32_t src_len,unsigned char *dst,uint32_t *len);
#ifdef __cplusplus
}
#endif

#endif
