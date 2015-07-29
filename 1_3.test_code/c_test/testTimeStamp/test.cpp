/*************************************************************************
	> File Name:        test.cpp
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年07月15日 星期三 15时52分24秒
 ************************************************************************/
/* 微秒 */

#include<stdio.h>
#include <sys/time.h>
#include<time.h>
int main()
{
    struct timeval t_val;
    gettimeofday(&t_val, NULL);
    printf("start, now, sec=%d m_sec=%d \n", t_val.tv_sec, t_val.tv_usec);
    long sec = t_val.tv_sec;
    time_t t_sec = (time_t)sec;
    printf("date:%s", ctime(&t_sec));
    return 0;
}


/*纳秒
#include <stdio.h>
#include <time.h>

int main()
{
    struct timespec time1 = {0, 0};
    clock_gettime(CLOCK_REALTIME, &time1);
    printf("CLOCK_REALTIME: %ld, %ld\n", time1.tv_sec, time1.tv_nsec);
    time_t tmp = time1.tv_sec * 1000000000 + time1.tv_nsec;
    printf("CLOCK_REALTIME: %ld\n", tmp);
    
    clock_gettime(CLOCK_MONOTONIC, &time1);
    printf("CLOCK_MONOTONIC: %ld, %ld\n", time1.tv_sec, time1.tv_nsec);
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &time1);
    printf("CLOCK_PROCESS_CPUTIME_ID: %ld, %ld\n", time1.tv_sec, time1.tv_nsec);
    clock_gettime(CLOCK_THREAD_CPUTIME_ID, &time1);
    printf("CLOCK_THREAD_CPUTIME_ID: %ld, %ld\n", time1.tv_sec, time1.tv_nsec);

    printf("%ld\n", time(NULL));
}
*/
