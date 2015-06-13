#include "subscribe.h"
#include "config.h"
#include "timer.h"
#include "message.pb-c.h"
#include "nebula_proto.h"
#include "connect.h"
#include <time.h>
#include "archive.h"

typedef enum {
    eSubStatus_Init,
    eSubStatus_WaitResponse,
    eSubStatus_Done,
} eSubStatus;

static eSubStatus g_sub_status = eSubStatus_Init;
static time_t g_sub_tick = 0;

int subscribe()
{
    int count = 0, i;
    char** sub_apps = (char**)malloc(g_sub_app_count * sizeof(char*));
    if (!sub_apps) return -1;

    for (i = 0; i < g_sub_app_count; ++i)
    {
        if (NebulaIsSubscribed(g_sub_apps[i])) continue;
        sub_apps[count++] = g_sub_apps[i];
    }

    if (!count) return 1;

    Nebula__SubscribeRequest request;
    nebula__subscribe_request__init(&request);
    request.n_app = count;
    request.app = sub_apps;
    int ret = NebulaSend(MSG_SET_REQUEST(NEBULA_MSG_SUB), (ProtobufCMessage*)&request);
    free(sub_apps);
    return ret;
}

void NebulaOnSubSuccess(const char* appname)
{
    // TODO: save subscribe app and sub time.
    log_info("sub %s success.", appname);
    NebulaAddSubApp(appname);
}

void NebulaOnSub(NebulaHeader *head, const char* data, int len)
{
    Nebula__SubscribeResponse *response = nebula__subscribe_response__unpack(NULL, len, (const uint8_t*)data);
    if (!response) {
        log_error("subcribe response parse error.");
        return ;
    }

    if (response->status != 200) {
        log_error("subcribe failed. status=%d", response->status);
        nebula__subscribe_response__free_unpacked(response, NULL);
        return ;
    }

    size_t i;
    for (i = 0; i < response->n_content; ++i)
    {
        Nebula__SubscribeResponse__Content *ctn = response->content[i];
        if (ctn->status == 200) {
            NebulaOnSubSuccess(ctn->app);
        }
    }

    g_sub_status = eSubStatus_Done;
    nebula__subscribe_response__free_unpacked(response, NULL);
}

int subscribe_loop()
{
    if (!g_sub_app_count) {
        return 0;
    }

    int ret;
    switch (g_sub_status) {
        case eStatus_Init:
            ret = subscribe();
            if (ret == 0) {
                g_sub_status = eSubStatus_WaitResponse;
                g_sub_tick = clock();
            } else if (ret == 1) {
                return 0;
            } else {
                return -1;
            }
            break;

        case eSubStatus_WaitResponse:
            if (clock() - g_sub_tick > 10000) {
                // timeout
                g_sub_status = eSubStatus_Init;
            }
            break;

        case eSubStatus_Done:
            return 0;
    }

    return -1;
}
