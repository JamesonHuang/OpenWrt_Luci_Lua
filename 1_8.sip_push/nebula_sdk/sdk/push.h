#ifndef PUSH_H
#define PUSH_H

#include "nebula_proto.h"

// push
// Send sync request to server when received MSGPUSH.
// Remember push sequences in memory. 

int NebulaInitPush();

void NebulaOnPush(NebulaHeader *head, const char* data, int len);

void NebulaOnMsg(NebulaHeader *head, const char* data, int len);

#endif //PUSH_H
