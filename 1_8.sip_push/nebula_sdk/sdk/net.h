#ifndef NEBULA_NET_H
#define NEBULA_NET_H

// Async TCP connection.
#include <google/protobuf-c/protobuf-c.h>
#include "list.h"
#include <pthread.h>
#include "nebula_proto.h"

enum _ConnState {
    eConnState_Init,
    eConnState_Connecting,
    eConnState_Estab,
    eConnState_Error,
};
typedef enum _ConnState ConnState;

typedef struct _Conn Conn;
struct _Conn
{
    int socketfd;
    char* host;
    uint16_t port;
    void (*on_connect)(Conn*, int);
    void (*on_read)(Conn*, NebulaHeader*);
    void (*on_disconnect)(Conn*);
    int (*read)(Conn*);
    int (*write)(Conn*);
    void (*err)(Conn*);
    char recv_buf[8192];
    int recv_pos;
    List* send_list;
    pthread_mutex_t mtx;
    ConnState state;
};

Conn* NebulaConnCreate();

void NebulaConnInit(Conn* conn, void(*on_connect)(Conn*, int),
        void(*on_read)(Conn*, NebulaHeader*),
        void(*on_disconnect)(Conn*));

int NebulaConnConnect(Conn* conn, const char* host, uint16_t port);

int NebulaConnSend(Conn* conn, NebulaHeader *head, ProtobufCMessage *msg);

void NebulaConnNebulaDisconnect(Conn* conn);

void NebulaConnLoop();

#endif //NEBULA_NET_H

