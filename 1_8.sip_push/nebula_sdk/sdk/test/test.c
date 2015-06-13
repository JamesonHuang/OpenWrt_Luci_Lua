#include "nebula_sdk/nebula_sdk.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/resource.h>
#include <limits.h>

/*
 *void OnPush(const char* data, int len)
 *{
 *    char *body = strndup(data, len);
 *    printf("karldbg %s %d\n", __func__, len);
 *    do {
 *        int cnt = 0;
 *        for(cnt=0; cnt<len; cnt++) {
 *            printf("karldbg %s pos: 0x%02x, 0x%02x, %c\n", __func__, cnt, (char)*(body+cnt), *(body+cnt));
 *        }
 *    } while(0);
 *    printf("recv push:%s\n", body);
 *    free(body);
 *}
 */

void OnPush(const char* app, const char *msg)
{
	printf("karldbg %s %d %s\n", __func__, __LINE__, app);
	printf("karldbg %s %d %s\n", __func__, __LINE__, msg);
    /*
	 *do {
	 *    int cnt = 0;
	 *    for(cnt=0; cnt<len; cnt++) {
	 *        printf("karldbg %s pos: 0x%02x, 0x%02x, %c\n", __func__, cnt, (char)*(body+cnt), *(body+cnt));
	 *    }
	 *} while(0);
     */
}

int main()
{
    //??
    struct rlimit no_limit = {RLIM_INFINITY, RLIM_INFINITY};
    setrlimit(RLIMIT_CORE, &no_limit);

    /*if (-1 == NebulaInit("1000000010000001", "https://172.16.82.71", "data")) {*/
    if (-1 == NebulaInit("R10WZOANC5400EE", "https://172.16.82.71", "data")) {
        printf("Init error.\n");
        return 1;
    }

    /*const char * apps[] = {"com.meizu.cloud"};*/
    const char * apps[] = {"com.meizu.router"};

    NebulaSubScribe(1, apps);

	NebulaMsgCallback cb = OnPush;

    /*NebulaRegister(eNebulaMsg_Push, &OnPush);*/
    NebulaRegister(eNebulaMsg_Push, cb);

    if (-1 == NebulaStart()) {
        printf("Start error.\n");
        return 1;
    }

    printf("start success.\n");

    for (;;)
        sleep(1);

    return 0;
}

