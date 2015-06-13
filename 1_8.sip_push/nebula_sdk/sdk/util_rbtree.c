
/* Copyright (C) 2010. sparkling.liang@hotmail.com. All rights reserved. */

#include "util_rbtree.h"

/* the NULL node of tree */
#define _NULL(rbtree) (&((rbtree)->null))

/* structues uesed to check a rb tree */
struct rbtree_check_s
{
    short rbh; /* rb height of the tree */
    short maxd; /* max depth of the tree */
    int fini; /* check failed ? */
    const util_rbtree_node_t *null; /* sentinel of the tree */
};

typedef struct rbtree_check_s rbtree_check_t;

static void rbtree_left_rotate(util_rbtree_node_t **root,
        util_rbtree_node_t* sentinel,
        util_rbtree_node_t *node);
static void rbtree_right_rotate(util_rbtree_node_t **root,
        util_rbtree_node_t* sentinel,
        util_rbtree_node_t *node);

void util_rbtree_init(util_rbtree_t *rbtree)
{
    if(rbtree != NULL)
    {
        util_rbt_black(_NULL(rbtree)); /* null MUST be black */
        rbtree->root = _NULL(rbtree);
        rbtree->size = 0;
    }
}

util_rbtree_node_t* util_rbsubtree_min(util_rbtree_node_t *node, util_rbtree_node_t *sentinel)
{
    if(node == sentinel) return NULL;
    while(node->left != sentinel) node = node->left;
    return node;
}

util_rbtree_node_t* util_rbsubtree_max(util_rbtree_node_t *node, util_rbtree_node_t *sentinel)
{
    if(node == sentinel) return NULL;
    while(node->right != sentinel) node = node->right;
    return node;
}

void util_rbtree_insert(util_rbtree_t *rbtree, util_rbtree_node_t *node)
{
    util_rbtree_node_t **root = &rbtree->root;
    util_rbtree_node_t *sentinel = _NULL(rbtree);
    util_rbtree_node_t *temp, **p;

    /* empty tree */

    if (*root == sentinel) {
        node->parent = NULL;
        node->left = sentinel;
        node->right = sentinel;
        util_rbt_black(node);
        *root = node;
        ++rbtree->size;
        return;
    }
    /* a binary tree insert */
    temp = *root;
    for (;;) {
        p = (node->key < temp->key) ? &temp->left : &temp->right;
        if (*p == sentinel) {
            break;
        }
        temp = *p;
    }

    *p = node;
    node->parent = temp;
    node->left = sentinel;
    node->right = sentinel;
    util_rbt_red(node);

    /* re-balance tree */
    while (node != *root && util_rbt_isred(node->parent)) {

        if (node->parent == node->parent->parent->left) {
            temp = node->parent->parent->right;

            if (util_rbt_isred(temp)) {
                util_rbt_black(node->parent);
                util_rbt_black(temp);
                util_rbt_red(node->parent->parent);
                node = node->parent->parent;
            } else {
                if (node == node->parent->right) {
                    node = node->parent;
                    rbtree_left_rotate(root, sentinel, node);
                }

                util_rbt_black(node->parent);
                util_rbt_red(node->parent->parent);
                rbtree_right_rotate(root, sentinel, node->parent->parent);
            }
        } else {
            temp = node->parent->parent->left;

            if (util_rbt_isred(temp)) {
                util_rbt_black(node->parent);
                util_rbt_black(temp);
                util_rbt_red(node->parent->parent);
                node = node->parent->parent;
            } else {
                if (node == node->parent->left) {
                    node = node->parent;
                    rbtree_right_rotate(root, sentinel, node);
                }

                util_rbt_black(node->parent);
                util_rbt_red(node->parent->parent);
                rbtree_left_rotate(root, sentinel, node->parent->parent);
            }
        }
    }

    util_rbt_black(*root);
    ++rbtree->size;
}

void util_rbtree_delete(util_rbtree_t *rbtree, util_rbtree_node_t *node)
{
    util_rbtree_node_t **root = &rbtree->root;
    util_rbtree_node_t *sentinel = _NULL(rbtree);
    util_rbtree_node_t *subst, *temp, *w;
    uint8_t red;

    /* a binary tree delete */
    --rbtree->size;
    if (node->left == sentinel) {
        temp = node->right;
        subst = node;
    } else if (node->right == sentinel) {
        temp = node->left;
        subst = node;
    } else {
        subst = util_rbsubtree_min(node->right, sentinel);
        if (subst->left != sentinel) {
            temp = subst->left;
        } else {
            temp = subst->right;
        }
    }

    if (subst == *root) {
        *root = temp;
        util_rbt_black(temp);

        rbt_clear_node(node);
        return;
    }

    red = util_rbt_isred(subst);

    if (subst == subst->parent->left) {
        subst->parent->left = temp;
    } else {
        subst->parent->right = temp;
    }

    if (subst == node) {
        temp->parent = subst->parent;
    } else {

        if (subst->parent == node) {
            temp->parent = subst;
        } else {
            temp->parent = subst->parent;
        }

        subst->left = node->left;
        subst->right = node->right;
        subst->parent = node->parent;
        subst->color = node->color;

        if (node == *root) {
            *root = subst;
        } else {
            if (node == node->parent->left) {
                node->parent->left = subst;
            } else {
                node->parent->right = subst;
            }
        }

        if (subst->left != sentinel) {
            subst->left->parent = subst;
        }

        if (subst->right != sentinel) {
            subst->right->parent = subst;
        }
    }

    rbt_clear_node(node);

    if (red) {
        return;
    }

    /* a delete fixup */
    while (temp != *root && util_rbt_isblack(temp)) {

        if (temp == temp->parent->left) {
            w = temp->parent->right;

            if (util_rbt_isred(w)) {
                util_rbt_black(w);
                util_rbt_red(temp->parent);
                rbtree_left_rotate(root, sentinel, temp->parent);
                w = temp->parent->right;
            }

            if (util_rbt_isblack(w->left) && util_rbt_isblack(w->right)) {
                util_rbt_red(w);
                temp = temp->parent;
            } else {
                if (util_rbt_isblack(w->right)) {
                    util_rbt_black(w->left);
                    util_rbt_red(w);
                    rbtree_right_rotate(root, sentinel, w);
                    w = temp->parent->right;
                }

                w->color = temp->parent->color;
                util_rbt_black(temp->parent);
                util_rbt_black(w->right);
                rbtree_left_rotate(root, sentinel, temp->parent);
                temp = *root;
            }

        } else {
            w = temp->parent->left;

            if (util_rbt_isred(w)) {
                util_rbt_black(w);
                util_rbt_red(temp->parent);
                rbtree_right_rotate(root, sentinel, temp->parent);
                w = temp->parent->left;
            }

            if (util_rbt_isblack(w->left) && util_rbt_isblack(w->right)) {
                util_rbt_red(w);
                temp = temp->parent;
            } else {
                if (util_rbt_isblack(w->left)) {
                    util_rbt_black(w->right);
                    util_rbt_red(w);
                    rbtree_left_rotate(root, sentinel, w);
                    w = temp->parent->left;
                }

                w->color = temp->parent->color;
                util_rbt_black(temp->parent);
                util_rbt_black(w->left);
                rbtree_right_rotate(root, sentinel, temp->parent);
                temp = *root;
            }
        }
    }

    util_rbt_black(temp);
}

static void rbtree_left_rotate(util_rbtree_node_t **root,
        util_rbtree_node_t* sentinel,
        util_rbtree_node_t *node)
{
    util_rbtree_node_t *temp;

    temp = node->right;
    node->right = temp->left;

    if (temp->left != sentinel) {
        temp->left->parent = node;
    }

    temp->parent = node->parent;

    if (node == *root) {
        *root = temp;
    } else if (node == node->parent->left) {
        node->parent->left = temp;
    } else {
        node->parent->right = temp;
    }

    temp->left = node;
    node->parent = temp;
}
static void rbtree_right_rotate(util_rbtree_node_t **root,
        util_rbtree_node_t* sentinel,
        util_rbtree_node_t *node)
{
    util_rbtree_node_t *temp;

    temp = node->left;
    node->left = temp->right;

    if (temp->right != sentinel) {
        temp->right->parent = node;
    }

    temp->parent = node->parent;

    if (node == *root) {
        *root = temp;
    } else if (node == node->parent->right) {
        node->parent->right = temp;
    } else {
        node->parent->left = temp;
    }

    temp->right = node;
    node->parent = temp;
}


util_rbtree_node_t* util_rbtree_search(util_rbtree_t *rbtree, long key)
{
    if(rbtree != NULL)
    {
        util_rbtree_node_t *node = rbtree->root;
        util_rbtree_node_t *null = _NULL(rbtree);
        while(node != null)
        {
            if(key < node->key) node = node->left;
            else if(key > node->key) node = node->right;
            else if(node->key == key) return node;
        }
    }
    return NULL;
}

util_rbtree_node_t* util_rbtree_lookup(util_rbtree_t *rbtree, long key)
{
    if((rbtree != NULL) && !util_rbtree_isempty(rbtree))
    {
        util_rbtree_node_t *node = NULL;
        util_rbtree_node_t *temp = rbtree->root;
        util_rbtree_node_t *null = _NULL(rbtree);
        while(temp != null)
        {
            if(key <= temp->key)
            {
                node = temp; /* update node */
                temp = temp->left;
            }
            else if(key > temp->key)
            {
                temp = temp->right;
            }
        }
        /* if node==NULL return the minimum node */
        return ((node != NULL) ? node : util_rbtree_min(rbtree));
    }
    return NULL;
}

static void rbtree_check_subtree(const util_rbtree_node_t *node, rbtree_check_t *check, 
        int level, int curheight)
{
    if(check->fini) /* already failed */
    {
        return;
    }
    /* check node color */
    if(util_rbt_isblack(node))
    {
        curheight++;
    }
    else if(!util_rbt_isred(node))
    {
        check->fini = 2;
        return;
    }
    /* check left */
    if(node->left != check->null)
    {
        if(util_rbt_isred(node) && util_rbt_isred(node->left))
        {
            check->fini = 4;
            return;
        }
        if(node->key < node->left->key)
        {
            check->fini = 5;
            return;
        }
        rbtree_check_subtree(node->left, check, level+1, curheight);
    }
    else
    {
        goto __check_rb_height;
    }
    /* check right */
    if(node->right != check->null)
    {
        if(util_rbt_isred(node) && util_rbt_isred(node->right))
        {
            check->fini = 4;
            return;
        }
        if(node->key > node->right->key)
        {
            check->fini = 5;
            return;
        }
        rbtree_check_subtree(node->right, check, level+1, curheight);
    }
    else
    {
        goto __check_rb_height;
    }
    return;
__check_rb_height:
    if(check->rbh == 0)
    {
        check->rbh = curheight;
    }
    if(check->maxd < level)
    {
        check->maxd = level;
    }
    if(check->rbh != curheight)
    {
        check->fini = 3;
    }
}

int util_rbtree_check(const util_rbtree_t *rbtree, int *blackheight, int *maxdepth)
{
    rbtree_check_t check;
    if(rbtree->root == _NULL(rbtree))
    {
        return 0;
    }
    if(!util_rbt_isblack(rbtree->root))
    {
        return 1;
    }
    check.fini = check.maxd = check.rbh = 0;
    check.null = _NULL(rbtree);
    rbtree_check_subtree(rbtree->root, &check, 1, 0);
    if(blackheight)
    {
        *blackheight = check.rbh;
    }
    if(maxdepth)
    {
        *maxdepth = check.maxd;
    }
    return check.fini;
}

static void rbtree_mid_travel(util_rbtree_node_t *node, util_rbtree_node_t *sentinel, 
        void(*opera)(util_rbtree_node_t *, void *), void *data)
{
    if(node->left != sentinel)
    {
        rbtree_mid_travel(node->left, sentinel, opera, data);
    }
    opera(node, data);
    if(node->right != sentinel)
    {
        rbtree_mid_travel(node->right, sentinel, opera, data);
    }
}

void util_rbtree_mid_travel(util_rbtree_t *rbtree, 
        void(*opera)(util_rbtree_node_t *, void *), void *data)
{
    if((rbtree!=NULL) && !util_rbtree_isempty(rbtree))
    {
        rbtree_mid_travel(rbtree->root, _NULL(rbtree), opera, data);
    }
}
