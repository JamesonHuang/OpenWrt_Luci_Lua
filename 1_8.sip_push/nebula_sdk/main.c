#include <stdio.h>
#include "nebula_sdk.h"
    
void msg_cb(const char* data, int len)
{
	printf("karldbg %s %s\n", __func__, data);
}

int main(int argc, const char *argv[])
{

	const char* app_name[] = {"com.meizu.router",};
	NebulaMsgCallback cb = msg_cb;

    NebulaInit("R10WZOANC5400EE", "https://p.meizu.com", "/tmp");

    NebulaSubScribe(1, app_name);
    
	NebulaRegister(eNebulaMsg_Push, cb);

    NebulaStart();

	while(1) {
		sleep(1);
	}
	
	return 0;
}
