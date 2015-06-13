#include "nebula_sdk.h"
#include <sys/file.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include "string.h"
#include <pthread.h>
#include <curl/curl.h>
#include <json-c/json.h>
#include <openssl/md5.h>
#include "timer.h"
#include "str.h"
#include "logger.h"
#include "list.h"
#include "config.h"
#include "redirect.h"
#include "auth.h"
#include "subscribe.h"
#include "push.h"
#include "archive.h"
#include <unistd.h>
#include "connect.h"
#include "net.h"

char* g_device_tag = NULL;
char* g_nebula_host = NULL;
NebulaMsgCallback g_nebula_cb[eNebulaMsg_Max] = {};
char* g_storage_file = NULL;
int g_started = 0;
timer_heap_t *g_timer;
List *g_server_list;
int g_sub_app_count = 0;
char** g_sub_apps = NULL;

int NebulaInit(const char* device_tag, const char* host, const char* file)
{
    if (!g_timer)
        g_timer = timer_heap_create(1024);

    if (!g_server_list)
        g_server_list = CreateList();

    if (!device_tag || !host || !file) return -1;
    if (!*device_tag || !*host || !*file) return -1;
    g_device_tag = strdup(device_tag);
    g_nebula_host = strdup(host);
    g_storage_file = strdup(file);

    log_init(&logger, "nebula.log", 4);

    if (-1 == NebulaInitPush()) {
        return -1;
    }

    if (-1 == NebulaLoadFromFile(g_storage_file)) {
        log_warn("load archive file failed...");
        return 0;
    }

    return 0;
}

void NebulaSubScribe(int count, const char** app_name)
{
    int i;
    if (g_sub_app_count) {
        for (i = 0; i < g_sub_app_count; ++i) {
            free(g_sub_apps[i]);
        }
        free(g_sub_apps);
    }

    g_sub_app_count = count;
    g_sub_apps = (char**)malloc(sizeof(char*) * g_sub_app_count);
    for (i = 0; i < g_sub_app_count; ++i) {
        g_sub_apps[i] = strdup(app_name[i]);
    }
}

NebulaMsgCallback NebulaRegister(NebulaMsgType type, NebulaMsgCallback cb)
{
    NebulaMsgCallback old = g_nebula_cb[type];
    g_nebula_cb[type] = cb;
    return old;
}

void* __NebulaStart(void* ptr)
{
    (void)ptr;

    NebulaInitAutoSave();

    for (;;) {
        switch (Nebulaget_status()) {
            case eStatus_Init:
                next_status();
                break;

            case eStatus_Redirect:
                if (0 == NebulaRedirect()) {
                    next_status();
                } else {
                    log_info("sleep 10 seconds, delay retry redirect.");
                    sleep(10);
                }
                break;

            case eStatus_LoopTest:
                next_status();
                break;

            case eStatus_Connect:
                if (0 == NebulaConnect()) {
                    next_status();
                }
                break;

            case eStatus_Connecting:
                // nothing to do.
                break;

            case eStatus_Auth1:
                if (-1 == NebulaAuth1()) {
                    jump_status(eStatus_Redirect);
                } else {
                    next_status();
                }
                break;

            case eStatus_Auth1_Waiting:
                // nothing to do.
                break;

            case eStatus_Auth2:
                // nothing to do.
                break;

            case eStatus_Sub:
                if (0 == subscribe_loop()) {
                    next_status();
                }
                break;

            case eStatus_Estab:
                // nothing to do. Looping...
                break;
        }

        timer_run(g_timer);
        NebulaConnLoop();   
    }

    return NULL;
}

int NebulaStart()
{
    if (g_started) return -1;

    pthread_t pt;
    int ret = pthread_create(&pt, NULL, __NebulaStart, NULL);
    if (ret == 0) {
        g_started = 1;
        return 0;
    }

    return ret;
}

