/******************************************************************************
 * FileName: 
 * Description: 
 * Version: 
 * History: 
 *     yuxiaobo    2014-12-23    1.0    created
 * 
 *****************************************************************************/
#ifndef __NEBULA_PROTO_H__
#define __NEBULA_PROTO_H__ 
#include <stdint.h>

struct _NebulaHeader
{
    uint8_t magic;
    uint8_t ver;
    uint8_t flag;
    uint8_t type;
    uint16_t len;
}__attribute__((packed));

typedef struct _NebulaHeader NebulaHeader;

#define NEBULA_MSG_PING 0x01
#define NEBULA_MSG_AUTH 0x02
#define NEBULA_MSG_SYNC 0x03
#define NEBULA_MSG_PSH  0x04
#define NEBULA_MSG_MSG  0x05
#define NEBULA_MSG_ACK  0x06
#define NEBULA_MSG_FIN  0x07
#define NEBULA_MSG_SUB  0x08
#define NEBULA_MSG_SMS  0x09
#define NEBULA_MSG_PRES 0x0A
#define NEBULA_MSG_ACCSTATUS 0x0B
#define NEBULA_MSG_INVITE    0x0C
#define NEBULA_MSG_DIALOGACK 0x0D
#define NEBULA_MSG_BYE       0x0E
#define NEBULA_MSG_UPDATE    0x0F
#define NEBULA_MSG_ACCUPDATE 0x10

#define NEBULA_KIEV_ACCOFFLINE 0x7D
#define NEBULA_KIEV_ACCONLINE 0x7E
#define NEBULA_KIEV_MSGACK   0x7F

#define MSG_IS_REQUEST(head) (((head)->type >> 7) & 0x01)
#define MSG_SET_REQUEST(type) (0x80 | type)

const char* MsgType2Str(uint8_t type);

#define IS_VALID_TYPE(type) (((type) & 0x7F) > 0 && ((type) & 0x7F) <= 0x10)

#define HEAD_INIT(h,t) \
    do { \
        (h)->magic = 0xF8; \
        (h)->ver   = 0x01; \
        (h)->flag  = 0x00; \
        (h)->type  = (t); \
        (h)->len   = 0;    \
    }while(0)


#define MSG_ID_LEN 24
/*
 * 生成msgid
 * @param msgid 长度必须是MSG_ID_LEN+1
 */
void CreateMsgid(char *msgid);

#endif
