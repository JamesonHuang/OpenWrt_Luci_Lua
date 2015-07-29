/*************************************************************************
	> File Name:        atoi.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月15日 星期三 22时26分47秒
 ************************************************************************/
#include<stdlib.h>
#include<stdio.h>
#include<time.h>


char buf[50];

char * timestamp()
{
    time_t time_stp;
    time ( &time_stp );
    printf("%ld\n", time_stp);
    sprintf(buf, "%d", time_stp);
    return buf;
}


int main(int argc, char *argv[])
{
    //printf("timestamp: %s\n", timestamp());
    char *str = timestamp();
    int test = atoi(str);
    time_t tt = atoi(str);
    
    printf("%d\n", test);
    printf("%d\n", tt);
}
