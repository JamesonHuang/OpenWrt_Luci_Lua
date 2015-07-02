/*************************************************************************
	> File Name:        openssl_md5.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月02日 星期四 17时52分19秒
 ************************************************************************/

#include <stdio.h>
#include <string.h>
#include <openssl/hmac.h>
#include <openssl/md5.h>

int main()
{
    // use unsigned char
    unsigned char    digest[16];
    char *input = "hek2mgl";
    int length = strlen(input);
    int i=0;

    // don't miss the underscore after MD5
    MD5_CTX md5;    
    MD5_Init(&md5);

    while (length > 0) {
        if (length > 512) {
            MD5_Update(&md5, input, 512);
        } else {
            MD5_Update(&md5, input, length);
        }
        length -= 512;
        input += 512;
    }

    MD5_Final(digest, &md5);
    printf("digest is: ");
    for(i = 0; i < 16; i++) {
        printf("%02x", digest[i]);
    }
    printf("\n");
    return 0;
}
