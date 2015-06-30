/*************************************************************************
	> File Name:        testSprintf.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年06月29日 星期一 13时34分48秒
 ************************************************************************/

#include<stdio.h>
int main(){
    char buffer[50];
    int val = 10;
    sprintf(buffer, "helloworld %d", val);
    printf("%s", buffer);
    printf("%d, %d", __LINE__, __func__);
}
