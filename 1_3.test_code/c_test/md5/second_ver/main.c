/*************************************************************************
	> File Name:        main.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月02日 星期四 18时16分37秒
 ************************************************************************/

#include <stdio.h>  
#include <string.h>
#include <stdlib.h>
//#include "md5.h"
#include "md5.c"
  

/*
void get_encry_upg_key(char *serviceCode, char *sipToken, char *version)
{
    //char *key = "2635881a7ab0593849fe89e685fc56cd";
    unsigned char *res = (unsigned char *)malloc(17 * sizeof(unsigned char *));
    char *buffer = (char *)malloc(120 * sizeof(char *));
    sprintf(buffer, "%s%s%s%s", serviceCode, sipToken, version, key);
    
    MD5_CTX md5;  
    MD5Init(&md5);
    MD5Update(&md5, (unsigned char*)buffer, strlen((char *)buffer));   //对欲加密的字符进行加密  
    MD5Final(res, &md5);                               //获得最终结果  
  
    printf("加密前:%s\n加密后:",buffer);  
    for(int i = 0; i < 16; i++)  
    {  
        printf("%02x ",res[i]);  
    }  
  
    printf("\n\n\n加密结束!\n");
}
*/



int main(int argc, char* argv[])  
{ 
    //get_encry_upg_key("123", "456", "789");

    int i;  
    MD5_CTX md5;  
    MD5Init(&md5);                      //初始化用于md5加密的结构  
  
    unsigned char encrypt[1024];        //存放于加密的信息  
    unsigned char decrypt[17];          //存放加密后的结果  
    scanf("%s",encrypt);                //输入加密的字符   
    MD5Update(&md5,encrypt,strlen((char *)encrypt));   //对欲加密的字符进行加密  
    MD5Final(decrypt,&md5);                            //获得最终结果  
  
    printf("加密前:%s\n加密后:",encrypt);  
    for(i=0; i<16; i++)  
    {  
        printf("%02x ",decrypt[i]);  
    }  
  
    printf("\n\n\n加密结束!\n");  
  
    return 0;  
}  
