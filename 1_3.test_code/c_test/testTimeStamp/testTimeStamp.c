/*************************************************************************
	> File Name:        testTimeStamp.c
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月14日 星期二 13时05分04秒
 ************************************************************************/
/*
#include<stdio.h>
#include<time.h>
int main()
{
    time_t rawtime;
    time ( &rawtime );
    printf("%ld\n", &rawtime);
    printf ( "The current local time is: %s", ctime (&rawtime) );
}
*/
/* ctime example 
#include <stdio.h>
#include <time.h>


int main ()
{
  time_t time_stp;
  time ( &time_stp );
  printf("%ld\n", time_stp);
  printf ( "The current local time is: %s", ctime (&time_stp) );
  
  return 0;
}
*/

/* 用time()取得时间（秒数），利用localtime()

转换成struct tm 再利用mktine（）将struct tm转换成原来的秒数*/

#include <stdio.h>

#include <time.h>
/*
int main()

{

    time_t timep;

    struct tm *p;

    time(&timep);

    //printf("time() : %d \n",timep);

    p=localtime(&timep);

    timep = mktime(p);

    printf("time()->localtime()->mktime():%d\n",timep);

}
*/

#include<stdio.h>
#include<sys/time.h>

#include<unistd.h>

 

int main()
{
    /*
    struct  timeval    tv;
    struct  timezone   tz;
    gettimeofday(&tv,&tz);

    printf("tv_sec:%ld\n",tv.tv_sec);
    printf("tv_usec:%ld\n",tv.tv_usec);
    printf("tz_minuteswest:%d\n",tz.tz_minuteswest);
    printf("tz_dsttime:%d\n",tz.tz_dsttime);
    */
    struct timeval tpstart,tpend; 
    long   iTimeInterval; 
    gettimeofday(&tpstart,NULL); 
    /* to do what you want*/ 
    gettimeofday(&tpend,NULL); 
    iTimeInterval=1000000 * (tpend.tv_sec - tpstart.tv_sec); 
    iTimeInterval += tpend.tv_usec - tpstart.tv_usec; 
    /*iTimeInterval 就是微妙级的时间跨度*/ 
    printf("tpstart:%ld\n", tpstart.tv_usec);
    printf("tpend:%ld\n", tpend.tv_usec);
    printf("iTimeInterval:%ld\n", tpend);

}
