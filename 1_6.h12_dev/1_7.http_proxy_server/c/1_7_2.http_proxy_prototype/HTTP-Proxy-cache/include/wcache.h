#ifndef __WCACHE_HDR__
#define __WCACHE_HDR__
#include <stddef.h>
#include <pthread.h>
#define WCACHE_HASHTABLE_SIZE 1024
#define HTTP_SIGNATURE_SIZE 512
#define HTTP_SIGNATURE_HASH_SIZE 32
#define CACHETIME 6
#define FRAGMENT_SIZE 4096
#define TIMESTAMP_SIZE 50
#define OK 1
#define ERROR 0

struct wcache_list_element {
        struct wcache_list_element *next;
        struct wcache_list_element *prev;
};


/* New linked list*/
struct wcache_list {
        struct wcache_list_element head;
};

/*A cache storing a list of cache objects*/
struct wcache {
        int curr_size;
        int max_size;
        struct wcache_list l;
};

/*A single entry in cache*/
struct wcache_entry {
        char http_signature[HTTP_SIGNATURE_SIZE];
        unsigned int signature_hash;
        time_t ts;
        char timestamp[TIMESTAMP_SIZE];
        int last_modified; /*did this entry have a Last-modified parameter*/
        int valid;      /*Is this entry valid*/
        int size;       /*size of the entry*/
        struct wcache_list fragments;           /*list storing fragment buffers*/
        struct wcache_list_element cache_elem;  /*part of single global cache
                                                  list*/
        struct wcache_list_element hash_elem;  /*part of hash table*/
        pthread_mutex_t entry_lock;
};
struct fragment_buffer {
        char buffer[FRAGMENT_SIZE];
        int size;
        struct wcache_list_element elem;
};


#define container_of(ptr, type, member) ({ \
        const typeof( ((type *)0)->member ) *__mptr = (ptr); \
         (type *)( (char *)__mptr - offsetof(type,member) );})


#define list_for_each(pos, list) \
          for (pos = ((list)->head.next); pos != &((list)->head)&&(pos!= NULL); pos = pos->next)
        
int wcache_list_add(struct wcache_list *l, struct wcache_list_element *e);
struct wcache_entry * wcache_entry_alloc();
void wcache_list_init(struct wcache_list *l);
int wcache_list_del(struct wcache_list_element *e);

int wcache_fragment_add(struct wcache_entry *entry, char *buffer, int size);
int wcache_entry_replace(struct wcache *w, struct wcache_entry *entry);

int wcache_add(struct wcache *w, struct wcache_entry *entry);
struct wcache_entry * wcache_find(char http_signature[HTTP_SIGNATURE_SIZE],
                time_t ts);

void wcache_table_init();
int wcache_remove_entry(struct wcache_entry *w);
struct wcache_entry * wcache_find(char http_signature[HTTP_SIGNATURE_SIZE],
                time_t ts);
int wcache_remove_fragments(struct wcache_entry *w);
unsigned int hash(char * buf);
int lock_entry(struct wcache_entry *w);
int unlock_entry(struct wcache_entry *w);
#endif
