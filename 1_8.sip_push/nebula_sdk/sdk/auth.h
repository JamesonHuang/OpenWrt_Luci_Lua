#ifndef AUTH_H
#define AUTH_H

#include "nebula_proto.h"

// auth 分为2步.

int NebulaAuth1();

void NebulaOnAuth(NebulaHeader *head, const char* data, int len);

#endif //AUTH_H
