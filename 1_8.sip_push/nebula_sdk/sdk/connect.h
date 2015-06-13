#ifndef CONNECT_H
#define CONNECT_H

#include <google/protobuf-c/protobuf-c.h>

#define MAX_BUF_SIZE 4096

int NebulaConnect();

int NebulaSend(uint8_t type, ProtobufCMessage *msg);

void NebulaDisconnect();

#endif //CONNECT_H
