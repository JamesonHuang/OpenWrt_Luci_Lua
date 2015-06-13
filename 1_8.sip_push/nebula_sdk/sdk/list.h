#ifndef __ASYNC_FCGI_LIST__
#define __ASYNC_FCGI_LIST__

typedef struct Node {
    void *data;
    struct Node *prev;
    struct Node *next;
} Node;

typedef struct List {
    Node *head;
    Node *tail;
    unsigned long size;
} List;

List* CreateList();
void DestroyList(List* list);
void ClearList(List* list);
int PushBack(List* list, void* data);
int PushFront(List* list, void* data);
void PopBack(List* list);
void PopFront(List* list);
void* Back(List* list);
void* Front(List* list);

// iterator
typedef void** ListIterator;
ListIterator ListBegin(List* list);
ListIterator ListNext(ListIterator itr);

// algorithm
int Accumulate(List* list, int(*pred)(void* data));

#endif //__ASYNC_FCGI_LIST__
