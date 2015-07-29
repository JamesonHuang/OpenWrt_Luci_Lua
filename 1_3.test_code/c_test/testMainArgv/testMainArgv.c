/*************************************************************************
	> File Name:        testMainArgv.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月14日 星期二 12时59分50秒
 ************************************************************************/

#include <stdio.h>  
#include <stdlib.h>  
int main(int argc,char *argv[])  
{  
    printf("参数个数= %d\n", argc);  
    printf("参数1= %s\n", argv[1]);
    printf("参数2= %s\n", argv[2]);
    printf("参数3= %s\n", argv[3]);
    return 0;  
}  
  
