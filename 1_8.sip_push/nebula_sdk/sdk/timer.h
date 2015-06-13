/*
 * =====================================================================================
 *
 *       Filename:  timer.h
 *
 *    Description:  小根堆定时器实现(非多线程安全)
 *
 *        Version:  1.0
 *        Created:  2013-08-20
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  xiaoboyu
 *   Organization:  
 *
 * =====================================================================================
 */
#ifndef __TIMER_HEAP_H__
#define __TIMER_HEAP_H__

#include <stdint.h>
#include "util_rbtree.h"
#ifdef __cplusplus
extern "C" {
#endif

typedef struct timer_heap_t timer_heap_t;

typedef struct timer_entry
{
    int32_t id;
    uint8_t in_rbtree;     /*是否在红黑树中*/
    util_rbtree_node_t rbnode;
    void *user_data;           
    void (*timeout_cb)(timer_heap_t* ht, struct timer_entry *entry);
}timer_entry;

void timer_entry_init(timer_entry* entry, int32_t id, void* user_data, \
        void (*timeout_cb)(timer_heap_t* ht, struct timer_entry *entry));
/*
 * 创建定时器
 * @param limit_size 一次超时的元素个数
 */
timer_heap_t* timer_heap_create(int32_t limit_size);

/*
 * @brief 设置定时器每次最大超时元素的个数 默认是256个
 */
void set_timer_limit(timer_heap_t *ht, int32_t limit);

/*
 * @ref 添加元素到定时器
 * @param expires 超时时间ms
 * @return 成功返回0 失败返回-1
 */
int32_t timer_add( timer_heap_t* ht, timer_entry *entry, uint32_t expires );

/*
 * @ref 从定时器中删除一个元素
 */
void timer_del( timer_heap_t* ht, timer_entry* entry );


/*
 * @brief 更新超时时间(前提是这个entry必须在定时器内, 否则可能导致不可预知的错误)
 */
int32_t timer_update(timer_heap_t* ht, timer_entry *entry, uint32_t expires);

/*
 * @brief 获取最近超时时间
 * @return -1 没有超时
 */
int32_t timer_earliest_time(timer_heap_t* ht);

/*
 * @ref 获取定时器元素的个数
 */
int32_t timer_size ( timer_heap_t* ht );

/*
 * @ref 扫描定时器，执行那些超时的定时器元素
 * @return 返回已经处理的超时定时器元素的个数
 */
int32_t timer_run(timer_heap_t* ht);

void timer_destroy( timer_heap_t* ht);

#ifdef __cplusplus
}
#endif

#endif

