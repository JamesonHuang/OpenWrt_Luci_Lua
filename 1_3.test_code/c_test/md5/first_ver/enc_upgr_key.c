/*************************************************************************
	> File Name:        enc_upgr_key.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月01日 星期三 20时41分53秒
 ************************************************************************/
#include <stdio.h>  
#include <stdlib.h>  
#include <string.h>  
#include "md5.h" 
#include "md5.c"
void get_encry_upg_key(char *serviceCode, char *sipToken, char *version)
{
    char key[] = "2635881a7ab0593849fe89e685fc56cd";
    unsigned char res[16];
    unsigned char buffer[1024] = "";
    
    //unsigned char buffer[] ="com.meizu.routerR13PWGPFQRRV03A51000329.0.150702162635881a7ab0593849fe89e685fc56cd"; 
    sprintf((char *)buffer, "%s%s%s%s", serviceCode, sipToken, version, key);
    //strcat((char *)buffer, key); 
    MD5_CTX md5;  
    MD5Init(&md5);
    MD5Update(&md5, buffer, strlen((char *)buffer));   //对欲加密的字符进行加密  
    MD5Final(&md5, res);                               //获得最终结果  
  
    printf("加密前:%s\n加密后:",buffer);  
    for(int i = 0; i < 16; i++)  
    {  
        printf("%02x ",res[i]);  
    }  
    printf("\n\n\n加密结束!\n");
    
}  
int main(int argc, char *argv[])  
{ 
    get_encry_upg_key("com.meizu.router", "R13PWGPFQRRV03A5100032", "9.0.15070216");
    return 0;  
} 

 /*
    MD5_CTX md5;  
    MD5Init(&md5);           
    int i;  
    unsigned char encrypt[] ="com.meizu.routerR13PWGPFQRRV03A51000329.0.150702162635881a7ab0593849fe89e685fc56cd";//21232f297a57a5a743894a0e4a801fc3  
    unsigned char decrypt[16];      
    MD5Update(&md5,encrypt,strlen((char *)encrypt));  
    MD5Final(&md5,decrypt);          
    printf("加密前:%s\n加密后:",encrypt);  
    for(i=0;i<16;i++)  
    {  
        printf("%02x",decrypt[i]);  
    }  
*/
