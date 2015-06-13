#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include "timer.h"

#define DEFAULT_TIMER_LIMIT_SIZE 256

struct timer_heap_t
{
    int32_t limit_size;      //一次超时的元素个数
    util_rbtree_t rbtree;
};

/*{{{ timer_heap_create*/
timer_heap_t* timer_heap_create(int32_t limit_size) 
{
    timer_heap_t* ht;

    ht = (timer_heap_t*)malloc(sizeof(timer_heap_t));
    if(ht == NULL){
        return NULL;
    }

    ht->limit_size = (limit_size <=0 ? DEFAULT_TIMER_LIMIT_SIZE : limit_size);
    util_rbtree_init(&ht->rbtree);

    return ht;
}

void set_timer_limit(timer_heap_t *ht, int32_t limit)
{
    if(ht == NULL)
        return;
    if (limit > 0) {
        ht->limit_size = limit;
    }
}

/*}}}*/

/*{{{ timer_add*/
int32_t timer_add( timer_heap_t* ht, timer_entry* entry, uint32_t expires)
{
    struct timeval now;
    long msec;
    if(!ht || !entry)
    {
        return -1;  /*参数错误*/
    }

    /*获取当前时间*/
    gettimeofday(&now, NULL);
    msec = now.tv_sec*1000 + now.tv_usec/1000 + expires;
    if (entry->in_rbtree) {
        //已经在红黑树中 先删除再插入
        util_rbtree_delete(&ht->rbtree, &entry->rbnode);
        entry->in_rbtree = 0;
        rbt_clear_node(&entry->rbnode);
    }

    entry->rbnode.key = msec;
    util_rbtree_insert(&ht->rbtree, &entry->rbnode);
    entry->in_rbtree = 1;

    return 0;
}
/*}}}*/

/*{{{ timer_del*/
void timer_del( timer_heap_t* ht, timer_entry* entry)
{
    if(!ht || !entry)
        return;
    if (!entry->in_rbtree)
        return;

    util_rbtree_delete(&ht->rbtree, &entry->rbnode);
    entry->in_rbtree = 0;
    rbt_clear_node(&entry->rbnode);
}
/*}}}*/

int32_t timer_update(timer_heap_t* ht, timer_entry *entry, uint32_t expires)
{
    return timer_add(ht, entry, expires);
}

int32_t timer_earliest_time(timer_heap_t* ht)
{
    struct timeval now; 
    long msec;
    util_rbtree_node_t *rbnode;

    if(!ht)
        return -1;
    if(ht->rbtree.size == 0)
        return -1;
    gettimeofday(&now, NULL);

    rbnode = util_rbtree_min(&ht->rbtree);
    if (rbnode == NULL)
        return -1;
    msec = rbnode->key - (now.tv_sec*1000 + now.tv_usec/1000);
    if(msec < 0)
        msec = 0;
    return (int32_t)msec;
}

int32_t timer_size(timer_heap_t* ht)
{
    if(!ht)
        return 0;
    return (int32_t)ht->rbtree.size;
}

int32_t timer_run(timer_heap_t* ht)
{
    struct timeval now;
    long msec;
    int i;
    int count = 0;
    if(!ht || util_rbtree_isempty(&ht->rbtree)){
        return 0;
    }

    gettimeofday(&now, NULL);
    msec = now.tv_sec*1000 + now.tv_usec/1000;
    for (i=0; i<ht->limit_size; ++i) {
        timer_entry *entry;
        util_rbtree_node_t *node = util_rbtree_min(&ht->rbtree);
        if (node == NULL) break;
        entry = (timer_entry*)node->data;
        if (node->key <= msec) {
            ++count;
            util_rbtree_delete(&ht->rbtree, node);
            rbt_clear_node(node);
            entry->in_rbtree = 0;
            if (entry->timeout_cb) {
                entry->timeout_cb(ht, entry);
            }
        } else {
            break;
        }
    }

    return count;
}

void timer_entry_init(timer_entry* entry, int32_t id, void* user_data, \
        void (*timeout_cb)(timer_heap_t* ht, struct timer_entry *entry))
{
    entry->user_data = user_data;
    entry->timeout_cb = timeout_cb;
    entry->id = id;
    entry->in_rbtree = 0;
    rbt_clear_node(&entry->rbnode);
    entry->rbnode.data = entry;
}

void timer_destroy( timer_heap_t* ht)
{
    if(!ht)
        return;
    while(!util_rbtree_isempty(&ht->rbtree)) {
        timer_entry *entry;
        util_rbtree_node_t *node = util_rbtree_min(&ht->rbtree);
        entry = (timer_entry*)node->data;
        util_rbtree_delete(&ht->rbtree, node);
        rbt_clear_node(node);
        entry->in_rbtree = 0;
        if (entry->timeout_cb) {
            entry->timeout_cb(ht, entry);
        }
    }
    free(ht);
}
