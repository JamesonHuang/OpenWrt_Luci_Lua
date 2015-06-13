#ifndef CONFIG_H
#define CONFIG_H

#include "nebula_sdk.h"
#include "timer.h"
#include "logger.h"
#include "list.h"
#include "message.pb-c.h"
#include "nebula.pb-c.h"

enum _eStatus {
    eStatus_Init,
    eStatus_Redirect,
    eStatus_LoopTest,
    eStatus_Connect,
    eStatus_Connecting,
    eStatus_Auth1,
    eStatus_Auth1_Waiting,
    eStatus_Auth2,
    eStatus_Sub,
    eStatus_Estab,
};

typedef enum _eStatus eStatus;

extern char* g_device_tag;
extern char* g_nebula_host;
extern NebulaMsgCallback g_nebula_cb[eNebulaMsg_Max];
extern int g_storage_fd;
extern timer_heap_t *g_timer;
extern List *g_server_list;
extern char* g_salt;
extern char* g_rule;
extern char* g_token;
extern int g_sub_app_count;
extern char** g_sub_apps;
extern eStatus g_status;

const char* Nebularand_string();
const char* Nebulamsgtype_name(int type);

const char* Nebulastatus_name(eStatus ss);
eStatus Nebulaget_status();

#define next_status() \
    do \
    {\
        if (g_status < eStatus_Estab) {\
            g_status ++;\
            log_info("set status to %s", Nebulastatus_name(g_status));\
        } else {\
            g_status = eStatus_Init;\
            log_info("set status to %s", Nebulastatus_name(g_status));\
        }\
    } while(0)


#define jump_status(ss) \
    do \
    {\
        g_status = ss;\
        log_info("set status to %s", Nebulastatus_name(g_status));\
    } while(0)

#endif //CONFIG_H
