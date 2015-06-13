#include "nebula_proto.h"
#include <time.h>
#include <stdio.h>
#include <unistd.h>
#include <uuid/uuid.h>
#include <string.h>

static const char* StrRequestType[] = {
    "REQ PING",
    "REQ AUTH",
    "REQ MSGSYNC",
    "REQ MSGPSH",
    "REQ MSG",
    "REQ MSGACK",
    "REQ MSGFIN",
    "REQ SUB",
    "REQ SMS",
    "REQ PRES",
    "REQ ACCSTATUS",
    "REQ INVITE",
    "REQ ACK",
    "REQ BYE",
    "REQ UPDATE",
    "REQ ACCUPDATE"
};

static const char* StrResponseType[] = {
    "RSP PING",
    "RSP AUTH",
    "RSP MSGSYNC",
    "RSP MSGPSH",
    "RSP MSG",
    "RSP MSGACK",
    "RSP MSGFIN",
    "RSP SUB",
    "RSP SMS",
    "RSP PRES",
    "RSP ACCSTATUS",
    "RSP INVITE",
    "RSP ACK",
    "RSP BYE",
    "RSP UPDATE",
    "RSP ACCUPDATE"
};

const char* MsgType2Str(uint8_t type)
{
    uint8_t request_flag = (type >> 7 & 0x01);
    uint8_t msg_type = type & 0x7F;
    switch (msg_type) {   //内部服务使用的协议
        case NEBULA_KIEV_ACCOFFLINE:
            return (request_flag ? "REQ ACCOFFLINE" : "RSP ACCOFFLINE");
        case NEBULA_KIEV_ACCONLINE:
            return (request_flag ? "REQ ACCONLINE" : "RSP ACCONLINE");
        case NEBULA_KIEV_MSGACK:
            return (request_flag ? "REQ KIEVACK" : "RSP KIEVACK");
        default:
            break;
    }

    if (!IS_VALID_TYPE(type)) {
        return (request_flag ? "REQ UnKnown": "RSP UnKnown");
    }

    if (request_flag) {
        return StrRequestType[msg_type - 1];
    }

    return StrResponseType[msg_type-1];
}

uint32_t CreateMachineId()
{
    uuid_t uuid = {0};
    char str[37];
    uint32_t hash = 5381;
    
    memset(str, 0, sizeof(str));
    uuid_generate(uuid);
    uuid_unparse(uuid, str);

    int i;
    for(i=0; i<36; i++) {
        hash = ((hash << 5) + hash) + str[i];
    }
    return hash;
}


void CreateMsgid(char *msgid)
{
    static uint32_t machine_id;
    static uint32_t ref_cnt = 0;
    if (ref_cnt == 0) {
        machine_id = CreateMachineId();
    }

    struct MsgId {
        uint32_t timestamp;
        uint32_t hash:24;
        uint16_t pid;
        uint32_t cnt:24;
    }__attribute__((packed));

    struct MsgId id;
    id.timestamp = time(0);
    id.hash = machine_id;
    id.pid = getpid();
    id.cnt = ref_cnt++;
    sprintf(msgid, "%08x%06x%04x%06x", id.timestamp, id.hash, id.pid, id.cnt);
    msgid[24] = '\0';
}
