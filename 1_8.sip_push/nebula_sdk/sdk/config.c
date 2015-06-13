#include "config.h"

eStatus g_status = eStatus_Init;

const char* Nebularand_string()
{
    static char buf[32] = {};
    int len = rand() % (sizeof(buf) - 1 - 16) + 16;
    int i;
    for (i = 0; i < len; ++i)
    {
        int v = rand() % 36;
        if (v < 10)
            buf[i] = '0' + v;
        else
            buf[i] = 'a' + v - 10;
    }
    buf[i] = '\0';
    return buf;
}

const char* Nebulamsgtype_name(int type)
{
    switch (type) {
        case eNebulaMsg_Push:        /// 推送 (路由器只需关心这一类消息)
            return "eNebulaMsg_Push";
            break;
        case eNebulaMsg_Presence:    /// Presence
            return "eNebulaMsg_Presence";
            break;
        case eNebulaMsg_Sms:         /// 网络短信
            return "eNebulaMsg_Sms";
            break;
        case eNebulaMsg_Mms:         /// 网络彩信
            return "eNebulaMsg_Mms";
            break;
    }

    return "";
}

const char* Nebulastatus_name(eStatus ss)
{
    static const char* names[] = {
        "eStatus_Init",
        "eStatus_Redirect",
        "eStatus_LoopTest",
        "eStatus_Connect",
        "eStatus_Connecting",
        "eStatus_Auth1",
        "eStatus_Auth1_Waiting",
        "eStatus_Auth2",
        "eStatus_Sub",
        "eStatus_Estab",
    };

    return names[ss];
}

eStatus Nebulaget_status()
{
    return g_status;
}
