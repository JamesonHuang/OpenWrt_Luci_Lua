#include "archive.h"
#include "config.h"
#include <sys/file.h>
#include <ctype.h>
#include <sys/types.h>
#include <errno.h>
#include <stdio.h>
#include <string.h>
#include "timer.h"

extern dict* g_seq_dict;
extern char* g_storage_file;
Archive g_archive = {0, NULL, &g_seq_dict};

int NebulaIsSubscribed(const char* appname)
{
    int i;
    for (i = 0; i < g_archive.n_sub_apps; ++i) {
        if (strcmp(appname, g_archive.sub_apps[i]) == 0)
            return 1;
    }

    return 0;
}

int NebulaAddSubApp(const char* appname)
{
    if (!g_archive.sub_apps) {
        g_archive.sub_apps = (char**)malloc(sizeof(char*));
        if (!g_archive.sub_apps) {
            log_error("malloc error.");
            return -1;
        }

        g_archive.sub_apps[0] = strdup(appname);
        if (!g_archive.sub_apps[0]) {
            log_error("malloc error.");
            return -1;
        }
        g_archive.n_sub_apps = 1;
        return 0;
    }

    g_archive.sub_apps =
        (char**)realloc(g_archive.sub_apps, sizeof(char*) * (g_archive.n_sub_apps + 1));
    if (!g_archive.sub_apps) {
        log_error("malloc error.");
        return -1;
    }
    g_archive.sub_apps[g_archive.n_sub_apps] = strdup(appname);
    if (!g_archive.sub_apps[g_archive.n_sub_apps]) {
        log_error("malloc error.");
        return -1;
    }
    g_archive.n_sub_apps++;
    return 0;
}

void ReleaseArchive()
{
    int i;
    for (i = 0; i < g_archive.n_sub_apps; ++i) {
        free(g_archive.sub_apps[i]);
    }
    free(g_archive.sub_apps);
    g_archive.n_sub_apps = 0;
    g_archive.sub_apps = NULL;
}

int NebulaLoadFromFile(const char* file)
{
    char *key = NULL;
    FILE *f = fopen(file, "r");
    if (!f) {
        log_warn("not found archive file:%s, please confirm SDK is first startup.", file);
        return 0;
    }

    ReleaseArchive();

    char *line = NULL;
    size_t len = 0;
    ssize_t n = getline(&line, &len, f);
    if (n <= 0) {
        fclose(f);
        return -1;
    }
    key = strndup(line, strlen(line) - 1);
    g_archive.n_sub_apps = atoi(key);
    free(key);
    int i;
    for (i = 0; i < g_archive.n_sub_apps; ++i) {
        n = getline(&line, &len, f);
        if (n <= 0) {
            ReleaseArchive();
            fclose(f);
            return -1;
        }
        key = strndup(line, strlen(line) - 1);
        NebulaAddSubApp(key);
        free(key);
    }

    n = getline(&line, &len, f);
    if (n <= 0) {
        ReleaseArchive();
        fclose(f);
        return -1;
    }
    key = strndup(line, strlen(line) - 1);
    int dict_size = atoi(key);
    free(key);
    for (i = 0; i < dict_size; ++i) {
        n = getline(&line, &len, f);
        if (n <= 0) {
            ReleaseArchive();
            fclose(f);
            return -1;
        }

        key = strndup(line, strlen(line) - 1);
        char* pSpace = strchr(key, ' ');
        if (!pSpace) {
            ReleaseArchive();
            fclose(f);
            free(key);
            return -1;
        }

        *pSpace = '\0';
        char* value = pSpace + 1;
        int v = atoi(value);
        dict *seqs = *g_archive.seqs;
        dictEntry *entry = dictFind(seqs, key);
        if (entry) {
            entry->v.u64 = v;
        } else {
            dictAdd(seqs, key, (void*)(uint64_t)v);
        }
        free(key);
    }

    if (line)
        free(line);
    fclose(f);
    return 0;
}

int NebulaSaveToFile(const char* file)
{
    FILE *f = fopen(file, "w");
    if (!f) {
        return -1;
    }

    char buf[32];
    snprintf(buf, sizeof(buf), "%d", g_archive.n_sub_apps);
    fwrite(buf, 1, strlen(buf), f);
    fwrite("\n", 1, 1, f);
    int i;
    for (i = 0; i < g_archive.n_sub_apps; ++i) {
        fwrite(g_archive.sub_apps[i], 1, strlen(g_archive.sub_apps[i]), f);
        fwrite("\n", 1, 1, f);
    }

    dict *seqs = *g_archive.seqs;
    snprintf(buf, sizeof(buf), "%d", (int)dictSize(seqs));
    fwrite(buf, 1, strlen(buf), f);
    fwrite("\n", 1, 1, f);
    dictIterator *it = dictGetIterator(seqs);
    dictEntry *entry = dictNext(it);
    while (entry) {
        fwrite((const char*)entry->key, 1, strlen((const char*)entry->key), f);
        fwrite(" ", 1, 1, f);
        snprintf(buf, sizeof(buf), "%lu", entry->v.u64);
        fwrite(buf, 1, strlen(buf), f);
        fwrite("\n", 1, 1, f);
        entry = dictNext(it);
    }
    int ret = fflush(f);
    fclose(f);
    return ret;
}

struct timer_entry g_save_entry;
void save_timer(timer_heap_t* ht, timer_entry* entry)
{
    timer_update(ht, entry, 10000);
    NebulaSaveToFile(g_storage_file);
}

void NebulaInitAutoSave()
{
    timer_entry_init(&g_save_entry, 0, NULL, &save_timer);
    timer_add(g_timer, &g_save_entry, 1000);
}


