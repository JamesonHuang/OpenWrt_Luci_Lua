#include "push.h"
#include "config.h"
#include "connect.h"
#include "dict.h"
#include "message.pb-c.h"
#include "nebula_proto.h"
#include <string.h>

dict *g_seq_dict = NULL;

static unsigned int __hashFunction(const void *key)
{
    return dictGenHashFunction((const char*)key, strlen((const char*)key));
}

void* __keyDup(void *privdata, const void *key)
{
    (void)privdata;
    return strdup((const char*)key);
}

int __keyCompare(void *privdata, const void *key1, const void *key2)
{
    (void)privdata;
    return strcmp((const char*)key1, (const char*)key2) == 0;
}

void __keyDestructor(void *privdata, void *key)
{
    (void)privdata;
    free(key);
}

int NebulaInitPush()
{
    if (g_seq_dict) return 0;

    static dictType type = {
        __hashFunction,
        __keyDup,
        NULL,
        __keyCompare,
        __keyDestructor,
        NULL
    };
    g_seq_dict = dictCreate(&type, NULL);
    if (!g_seq_dict)
        return -1;

    return 0;
}

int SendSync(int type)
{
    Nebula__MessageSeq request;
    nebula__message_seq__init(&request);
    size_t seq_size = dictSize(g_seq_dict);
    request.n_content = seq_size;
    request.content = (Nebula__MessageSeq__Content**)malloc(sizeof(void*) * seq_size);

    dictIterator *it = dictGetIterator(g_seq_dict);
    dictEntry *entry = dictNext(it);
    size_t i = 0;
    while (entry) {
        request.content[i] = (Nebula__MessageSeq__Content*)malloc(sizeof(Nebula__MessageSeq__Content));
        nebula__message_seq__content__init(request.content[i]);
        request.content[i]->account = (char*)entry->key;
        request.content[i]->seq = entry->v.u64;
        i++;
        entry = dictNext(it);
    }
    dictReleaseIterator(it);
        
    // send
    int ret = NebulaSend(type, (ProtobufCMessage*)&request);
    
    for (i = 0; i < seq_size; ++i)
    {
        free(request.content[i]);
    }
    free(request.content);
    return ret;
}

void NebulaOnPush(NebulaHeader *head, const char* data, int len)
{
    (void)head, (void)data, (void)len;
    if (-1 == SendSync(MSG_SET_REQUEST(NEBULA_MSG_SYNC))) {
        log_error("SendSync failed.");
    }
}

void NebulaOnMsg(NebulaHeader *head, const char* data, int len)
{
    (void)head;
    Nebula__Message *message = nebula__message__unpack(NULL, len, (const uint8_t*)data);
    if (!message) {
        log_error("parse NebulaMessage failed.");
        return ;
    }

    size_t i;
    for (i = 0; i < message->n_content; ++i)
    {
        Nebula__Message__Content *ctn = message->content[i];
        if ((int)ctn->type >= eNebulaMsg_Max) {
            log_warn("discard unkown type message. type=%d", ctn->type);
            continue;
        }

        dictEntry * entry = dictFind(g_seq_dict, ctn->account);
        if (!entry || ctn->seq > entry->v.u64) {
            log_debug("recv message. type=%s, account=%s, seq=%u, hasbody=%d, bodylength=%lu",
                    Nebulamsgtype_name(ctn->type), ctn->account, ctn->seq, ctn->has_body, ctn->body.len);
            if (!ctn->has_body) {
                log_warn("message has not body, discard it!");
            } else if (g_nebula_cb[ctn->type])
                g_nebula_cb[ctn->type]((const char*)ctn->body.data, ctn->body.len);

            // refresh sequence
            if (entry) {
                entry->v.u64 = ctn->seq;
            } else {
                if (DICT_OK != dictAdd(g_seq_dict, ctn->account, (void*)(uint64_t)ctn->seq)) {
                    log_error("refresh sequence number error, maybe memory was deplete.");
                }
            }
        } else {
            log_warn("discard message. type=%s, account=%s, seq=%u, hasbody=%d, bodylength=%lu",
                    Nebulamsgtype_name(ctn->type), ctn->account, ctn->seq, ctn->has_body, ctn->body.len);
        }
    }

    if (message->has_flag) {
        if (message->flag == NEBULA__MESSAGE__FLAG__MESSAGE_ACK)
            SendSync(MSG_SET_REQUEST(NEBULA_MSG_ACK));
        else if (message->flag == NEBULA__MESSAGE__FLAG__MESSAGE_END)
            SendSync(MSG_SET_REQUEST(NEBULA_MSG_FIN));
    }

    nebula__message__free_unpacked(message, NULL);
}

