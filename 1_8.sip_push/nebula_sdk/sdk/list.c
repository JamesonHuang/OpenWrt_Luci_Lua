#include "list.h"
#include <assert.h>
#include <stdlib.h>

List* CreateList()
{
    List *list = (List*)malloc(sizeof(List));
    if (NULL == list) return NULL;

    list->head = list->tail = NULL;
    list->size = 0;
    return list;
}

void DestroyList(List* list)
{
    ClearList(list);
    free(list);
}

void ClearList(List* list)
{
    Node *pos = list->head;
    while (NULL != pos)
    {
        Node *old = pos;
        pos = pos->next;
        free(old->data);
        free(old);
    }
    list->head = list->tail = NULL;
    list->size = 0;
}

int PushBack(List* list, void* data)
{
    if (NULL == list->tail) {
        list->head = list->tail = (Node*)malloc(sizeof(Node));
        if (!list->tail)
            return -1;

        list->tail->next = list->tail->prev = NULL;
        list->tail->data = data;
        ++list->size;
        return 0;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    if (!newNode)
        return -1;

    newNode->data = data;
    newNode->next = NULL;
    newNode->prev = list->tail;
    list->tail->next = newNode;
    list->tail = newNode;
    ++list->size;
    return 0;
}

int PushFront(List* list, void* data)
{
    if (NULL == list->head) {
        list->head = list->tail = (Node*)malloc(sizeof(Node));
        if (!list->tail)
            return -1;

        list->tail->next = list->tail->prev = NULL;
        list->tail->data = data;
        ++list->size;
        return 0;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    if (!newNode)
        return -1;

    newNode->data = data;
    newNode->next = list->head;
    newNode->prev = NULL;
    list->head->prev = newNode;
    list->head = newNode;
    ++list->size;
    return 0;
}

void PopBack(List* list)
{
    if (0 == list->size) return ;
    --list->size;

    if (list->head == list->tail) {
        ClearList(list);
        return ;
    }

    free(list->tail->data);
    list->tail = list->tail->prev;
    free(list->tail->next);
    list->tail->next = NULL;
}

void PopFront(List* list)
{
    if (0 == list->size) return ;
    --list->size;

    if (list->head == list->tail) {
        ClearList(list);
        return ;
    }

    free(list->head->data);
    list->head = list->head->next;
    free(list->head->prev);
    list->head->prev = NULL;
}


void* Back(List* list)
{
    if (0 == list->size) return NULL;
    return list->tail->data;
}

void* Front(List* list)
{
    if (0 == list->size) return NULL;
    return list->head->data;
}

ListIterator ListBegin(List* list)
{
    if (!list->head) return NULL;
    return &list->head->data;
}

ListIterator ListNext(ListIterator itr)
{
    if (!itr) return NULL;
    Node *node = (Node*)itr;
    if (!node->next) return NULL;
    return &node->next->data;
}

int Accumulate(List* list, int(*pred)(void* data))
{
    int result = 0;
    Node *pos = list->head;
    while (pos) {
        result += pred(pos->data);
        pos = pos->next;
    }
    return result;
}

