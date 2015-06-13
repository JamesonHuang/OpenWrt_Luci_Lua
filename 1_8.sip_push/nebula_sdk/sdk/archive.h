#ifndef ARCHIVE_H
#define ARCHIVE_H

#include "dict.h"

struct _Archive
{
    int n_sub_apps;
    char **sub_apps;
    dict **seqs;
};
typedef struct _Archive Archive;
extern Archive g_archive;

void NebulaInitAutoSave();

int NebulaIsSubscribed(const char* appname);

int NebulaAddSubApp(const char* appname);

int NebulaLoadFromFile(const char* file);

int NebulaSaveToFile(const char* file);

#endif //ARCHIVE_H
