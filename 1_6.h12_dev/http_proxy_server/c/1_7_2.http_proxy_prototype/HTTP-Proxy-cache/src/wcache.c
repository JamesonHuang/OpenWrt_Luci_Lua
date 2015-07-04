/*
 * File Name : wcache.c 
 * Author : Gaurav Tungatkar
 * Creation Date : 07-03-2011
 * Description :
 * Web cache
 * This includes generic linked list implementation as well as wrapper function
 * for managing cache.
 * Cache design : 
 * Each cache object is linked in a global double linked list "cache"
 * It is also part of the list pointed to by a HashTable entry.
 * Each cache object contains multiple "fragments" - nothing but data buffers,
 * linked together.
 * Hash table stores pointers to cache based on hash value of http GET reqest
 * line.
 *
 */

#include <stdio.h>
#include <stddef.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/time.h>
#include <string.h>
#include <pthread.h>
#include "wcache.h"
#include "httpconf.h"
struct wcache_list * wcache_hashtable[WCACHE_HASHTABLE_SIZE];
struct wcache_list cache_list;
extern struct http_server_config cfg; 
extern pthread_mutex_t list_mutex;
 /* very rudimentary hash function */
unsigned int hash(char * buf)
{
        unsigned int hashv = 1298; //seed
        const char *s = buf;
        while (*s)
        {
                hashv = hashv * 101  +  *s++;
        }
        return hashv;
}

struct wcache_entry * wcache_entry_alloc()
{
        struct wcache_entry *e = (struct wcache_entry *)malloc(sizeof(struct wcache_entry));
        memset(e, 0, sizeof(struct wcache_entry));
        pthread_mutex_init(&(e->entry_lock), NULL);
        return e;
}
int lock_entry(struct wcache_entry *w)
{
        return pthread_mutex_lock(&(w->entry_lock));

}
int unlock_entry(struct wcache_entry *w)
{
        return pthread_mutex_unlock(&(w->entry_lock));

}
void wcache_table_init()
{
        int i;
        for(i = 0; i < WCACHE_HASHTABLE_SIZE; i++)
        {
                wcache_hashtable[i] = NULL;
        }
}
struct wcache_list * wcache_list_alloc()
{
        struct wcache_list * l = (struct wcache_list*)malloc(sizeof(struct wcache_list));
        memset(l, 0, sizeof(struct wcache_list));
        return l;
}
void wcache_list_init(struct wcache_list *l)
{
        assert(l != NULL);
        l->head.next = l->head.prev = &(l->head);
}


/*add element to a list*/
int wcache_list_add(struct wcache_list *l, struct wcache_list_element *e)
{
        if(e == NULL) goto ERR;
        if(l == NULL) goto ERR;
        struct wcache_list_element * last;
        if(l->head.prev == NULL)
        {
                l->head.prev = l->head.next = e;
                e->prev = &(l->head);
                e->next = &(l->head);
                return OK;
        }
        last = l->head.prev;
        last->next = e;
        e->prev = last;
        e->next = &(l->head);
        l->head.prev = e;
        return OK;
ERR:
        //LOG
        return ERROR;

}

int wcache_list_del(struct wcache_list_element *e)
{
        if(e == NULL) goto ERR;
        e->prev->next = e->next;
        e->next->prev = e->prev;
        e->prev = NULL;
        e->next = NULL;
        return OK;    
ERR:
        return ERROR;

}
/*add a fragment to this entry*/
int wcache_fragment_add(struct wcache_entry *entry, char *buffer, int size)
{
        struct fragment_buffer *fb = 
                (struct fragment_buffer *)malloc(sizeof(struct fragment_buffer));
        int ret;
        assert(fb);
    //    memset(fb, 0, sizeof(struct fragment_buffer));
        memcpy(fb->buffer, buffer, FRAGMENT_SIZE);
        fb->size = size;
        entry->size += size + sizeof(struct fragment_buffer);
        pthread_mutex_lock(&list_mutex);
        ret =  wcache_list_add(&(entry->fragments), &(fb->elem));
        pthread_mutex_unlock(&list_mutex);
        return ret;
}


int wcache_entry_replace(struct wcache *w, struct wcache_entry *entry)
{
        //cache entry replacement
        //for now, modified FIFO policy followed- replace the oldest entry
        //whose deletion leaves __enough space__ in the cache for the current entry.
        //This means, that the "oldest" entry may not be the one that is always
        //replaced.
        struct wcache_list_element *e = &(w->l.head);

        struct wcache_entry * we;
        if(e == NULL)
                return OK;

        for(; e->next != &(w->l.head); e = e->next)
        {
                we = container_of(e->next, struct wcache_entry, cache_elem);
                if((w->curr_size + entry->size - we->size) <= w->max_size)
                {
                        w->curr_size =- we->size;
                        printf("Removing %s\n", we->http_signature);
                        wcache_remove_entry(we);
                        return OK;
                }
                
        }
        return ERROR;

}
/*add an entry to cache. Handle cache full and replacement*/
int wcache_add(struct wcache *w, struct wcache_entry *entry)
{
        struct wcache_list * l = &(w->l);
        struct wcache_list *hte;
        if(pthread_mutex_lock(&list_mutex) != 0)
                return ERROR;
        if(w->curr_size + entry->size >= w->max_size)
        {
                //cache full. remove one entry and make space.
                wcache_entry_replace(w, entry);

        }
        if(w->curr_size + entry->size >= w->max_size)
        {
                //LOG
                goto ERR;
        
        }
                
        if(wcache_list_add(l, &(entry->cache_elem)) == ERROR)
                goto ERR;

        w->curr_size += entry->size;
        hte = wcache_hashtable[entry->signature_hash % WCACHE_HASHTABLE_SIZE];
        if(hte == NULL)
        {
        hte = wcache_hashtable[entry->signature_hash % WCACHE_HASHTABLE_SIZE] =
                wcache_list_alloc();

        }
        if(wcache_list_add(hte, &(entry->hash_elem)) == ERROR)
                goto ERR;

        pthread_mutex_unlock(&list_mutex);
        return OK;
ERR:
        pthread_mutex_unlock(&list_mutex);
        //LOG
        return ERROR;
}

/*find the cache entry corresponding to this http signature*/
struct wcache_entry * wcache_find(char http_signature[HTTP_SIGNATURE_SIZE],
                time_t ts)
{
        /*
         * ts - either the if modified since time or the current time
         * */
        /* TODO */
        unsigned int hashv = hash(http_signature);
        struct wcache_list *l = NULL;
        struct wcache_list_element *e;
        struct wcache_entry * we;
        
        if(pthread_mutex_lock(&list_mutex) != 0)
                return NULL;
        l = wcache_hashtable[hashv % WCACHE_HASHTABLE_SIZE];
        if(l) 
                e = &(l->head);
        else 
                goto RET;
        for(;e->next != &(l->head); e = e->next)
        {
                //w = l;
                we = container_of((e->next), struct wcache_entry, hash_elem);
                if(!we->valid) 
                        goto RET;
                if(strcmp(http_signature, we->http_signature) == 0)
                {
                        goto RETWE;
                }

        }
RET:
        pthread_mutex_unlock(&list_mutex);
        return NULL;
RETWE:
        pthread_mutex_unlock(&list_mutex);
        return we;

}

/*free all fragments of this entry*/
int wcache_remove_fragments(struct wcache_entry *w)
{
        struct fragment_buffer *b;
        if(w->fragments.head.next)
        {
                while(w->fragments.head.next != w->fragments.head.prev)
                { 
                        b = container_of(w->fragments.head.next, 
                                        struct fragment_buffer, elem);
                        wcache_list_del(w->fragments.head.next);
                        w->size -= b->size;
                        if(b) free(b);
                }
        }
        return OK;

}
int wcache_remove_entry(struct wcache_entry *w)
{
      
        if(wcache_list_del(&(w->cache_elem)) == ERROR)
        { 
                                        printf(":2 check\n");
                return ERROR;
        }
        if(wcache_list_del(&(w->hash_elem)) == ERROR)
        { 
                                        printf(":3 check\n");
                return ERROR;
        }
                                        printf(":4 check\n");
        //free every fragment
        wcache_remove_fragments(w);
        pthread_mutex_destroy(&(w->entry_lock));
        free(w);
        w = NULL;

        return OK;
}
#if 0
//void *  alloc_wcache_hashtable();
int main11()
{
        struct wcache_entry *entry = 
                (struct wcache_entry *)malloc(sizeof(struct wcache_entry));
//        cache_list.head = (struct wcache_list_element*)malloc(
  //                      sizeof (struct wcache_list_element));
        /*  wcache_list_add(&cache_list, &(entry->cache_elem));
        entry = (struct wcache_entry *)malloc(sizeof(struct wcache_entry));
        entry->signature_hash = 30;
        wcache_list_add(&cache_list, &(entry->cache_elem));
        entry = NULL;
        entry = get_struct(cache_list.head.next, struct wcache_entry,
                        cache_elem);
        printf("TC1: %d\n", entry->signature_hash);
        entry = get_struct(cache_list.head.next->next, struct wcache_entry,
                        cache_elem);
        printf("TC1: %d\n", entry->signature_hash);
         */
        cache.curr_size= 0;
        cache.max_size = 5;
      //  cache.l.head.next = cache.l.head.prev = &(cache.l.head);
        wcache_list_init(&(cache.l));
        entry->size = 15;
        strcpy(entry->http_signature, "HTTP/1.10");
        entry->signature_hash = hash(entry->http_signature);
        entry->valid = 1;
        entry->ts = 10;
        wcache_add(&cache, entry);
        entry = (struct wcache_entry *)malloc(sizeof(struct wcache_entry));
        entry->size = 10;
        strcpy(entry->http_signature, "HTTP/2.22");
        entry->signature_hash = hash(entry->http_signature);
        entry->valid = 1;
        entry->ts = 10;
        wcache_add(&cache, entry);
        entry = NULL;        
        entry  = wcache_find("HTTP/1.10", 12);
        if(entry != NULL)
        printf("TC1: %s\n", entry->http_signature);
         
        return 0;
}
#endif
