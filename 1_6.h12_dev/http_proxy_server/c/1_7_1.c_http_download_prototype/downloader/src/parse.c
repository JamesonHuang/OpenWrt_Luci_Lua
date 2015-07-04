/*
    "parse.c"
    Copyright (C) <2012>  <"Mark Deng" 2010.tpk@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <errno.h>

#include "parse.h"
#include "dbg.h"

static void add2list (struct ip_list * const ips,char const *ipstr);

extern struct ip_list *parse_addr (char const *hostname)
{
    
    struct addrinfo *answer, hint, *curr;
    char ipstr[16] = {'\0'};
    volatile int ret;
    
    struct ip_list *ips = NULL;
    ips = (struct ip_list *) calloc (1, sizeof (struct ip_list));
    CHECK_MEM (ips);
    
    bzero (&hint, sizeof (hint));
    hint.ai_family = AF_INET;
    hint.ai_socktype = SOCK_STREAM;
    
    if ((ret = getaddrinfo (hostname, NULL, &hint, &answer)) != 0) {
        fprintf (stderr, "getaddrinfo: %s\n",gai_strerror (ret));
        exit (EXIT_FAILURE);
    }

    for (curr = answer; curr != NULL; curr = curr->ai_next) {
        
        inet_ntop (AF_INET,
                   &(((struct sockaddr_in *)(curr->ai_addr))->sin_addr),
                   ipstr, sizeof (ipstr));
        add2list (ips, ipstr);
    }

    freeaddrinfo (answer);
    return ips;
    
error:
    return NULL;
}

extern void free_ip_list (struct ip_list *ips)
{
    struct ip_list *temp = NULL;
    struct ip_list *delete_temp = NULL;
    delete_temp = ips;
    
    while (1)
    {
        temp = delete_temp->next;
        free (delete_temp);
        delete_temp = NULL;
        delete_temp = temp;
        if (delete_temp == NULL) break;
    }
    ips = NULL;
}

static void add2list (struct ip_list * const ips,char const *ipstr)
{
    struct ip_list *ip = NULL;
    struct ip_list *temp = NULL;
    
    CHECK_MEM(ips);
    
    if (ips->next == NULL && (strlen(ips->ipstr) == 0))
    {
        strncpy (ips->ipstr, ipstr, 16);
        return;
    }

    ip = (struct ip_list *) calloc (1, sizeof (struct ip_list));
    CHECK_MEM (ip);
    
    temp = ips;
    while (temp->next)
    {
        temp = temp->next;
    }
    temp->next = ip;
    strncpy (ip->ipstr, ipstr, 16);

error:
    return;
}
