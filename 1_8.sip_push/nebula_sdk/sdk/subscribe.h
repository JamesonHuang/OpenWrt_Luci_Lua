#ifndef SUBSCRIBE_H
#define SUBSCRIBE_H

#include "nebula_proto.h"

int subscribe_loop();

void NebulaOnSub(NebulaHeader *head, const char* data, int len);

#endif //SUBSCRIBE_H
