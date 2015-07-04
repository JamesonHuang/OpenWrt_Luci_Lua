/*
 * File Name : http_server.c 
 * Author : Gaurav Tungatkar
 * Creation Date : 17-01-2011
 * Description :
 *
 */
//needed for strptime
#define _GNU_SOURCE

#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>          
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <signal.h>
#include <netdb.h>
#include <pthread.h>
#include <signal.h>
#include <sys/time.h>
#include <time.h>
#include "httpconf.h"
#include "wcache.h"
#include "tokenize.h"
#include "fileparser.h"


#define BUFFSIZE (4*1024)
#define MAX_FILENAME 512
#define HTTP_HDR_SIZE 512
#define HTTP_URI_SIZE 2048
#define HTTP_STATUS_SIZE 1024
#define HTTP_GET 11
#define HTTP_PORT 80
#define SP 0x20 
#define CRLF "\r\n"

struct http_server_config cfg ;
struct wcache cache;
pthread_mutex_t list_mutex;

/*Check if the request contains the Last-modified field.
 *return time in seconds since epoch if yes.
 *return 0 if it doesnt exist*/
time_t get_last_modified(char * request, char *timestamp)
{
        char *str;
        char dummy[20];
        struct tm t;
        time_t tsec = 0;
        int i = 0;
        assert(request);

        timestamp[0] = '\0';
        str = strstr(request, "Last-Modified:");
        if(str == NULL) goto NOTIME;
        if(tokenize(&str, dummy) <= 0)
                goto NOTIME;
        while(*str == ' ')
                str++;
        //if(tokenize(&str, timestr) <= 0)
          //      goto NOTIME;
        while(str[i] != '\n')
        {
                timestamp[i] = str[i];
                i++;
        }
        timestamp[i] = '\n';
        timestamp[i+1] = '\0';
        if (strptime(timestamp, "%a,%n%e%n%b%n%Y%n%H:%M:%S", &t) != 0 )
                  tsec = mktime(&t);
        else
                goto NOTIME;
        return tsec;

NOTIME:
        return 0;
}

int valid_method_string(char **request, char *request_method)
{
        /*only GET method supported for caching */
        if((tokenize(request, request_method) != 3)
                        ||(strcmp(request_method, "GET") != 0))
        {
               // LOG(stdout, "Invalid method\n");
               // return -1;
               return 0;
        }
        else
        {
                return HTTP_GET;
        }

}
int valid_version(char **request, struct http_server_config *cfg, 
                char *version)
{
        /* HTTP versions 1.0 and 1.1 messages are accepted
         */
        if((tokenize(request, version) <= 0)
                        ||((strcmp(version, "HTTP/1.1") != 0) && (strcmp(version,
                                                "HTTP/1.0") != 0)))
        {
                LOG(stdout, "Version not supported\n");
                return -1;
        }
        else
        {
                return 0;
        }


}
int valid_uri(char **request, struct http_server_config *cfg, 
                char *uri)
{
        /*if it sees 2 or more leading spaces(SP) - thats invalid URI*/
        if(*(*(request)+1) == SP)
        {
                LOG(stdout, "Invalid URI\n");
                return -1;
        }
        
        if((tokenize(request, uri) <= 0))
        {
                LOG(stdout, "Invalid URI\n");
                return -1;
        }
        else
        {
                //cannot refer to the parent directory
                if(uri[0] == '.' && uri[1] == '.')
                {
                        LOG(stdout, "Invalid URI\n");
                        return -1;
                }
        }
        return 0;

}

/*Given a host name and a port, open a socket to that host.
 *Returns that socket*/
int connect_server(char *host, int port)
{
        /* refer to getaddrinfo() man page */

        struct addrinfo hints;
        struct addrinfo *res, *rp;
        int fd, s;
        int flag = 0;

        memset(&hints, 0, sizeof(struct addrinfo));
        hints.ai_family = AF_INET;    
        hints.ai_socktype = SOCK_STREAM; 
        hints.ai_flags = 0;
        hints.ai_protocol = 0;          
        hints.ai_canonname = NULL;
        hints.ai_addr = NULL;
        hints.ai_next = NULL;

        s = getaddrinfo(host, NULL,  &hints, &res);
        if (s != 0) {
                fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(s));
                return ERROR;
        }
        for (rp = res; rp != NULL; rp = rp->ai_next) {
                if(rp->ai_socktype != SOCK_STREAM)
                        continue;
                fd = socket(rp->ai_family, rp->ai_socktype,
                                rp->ai_protocol);
                if (fd == -1)
                        continue;
                if(port >= 0)
                        ((struct sockaddr_in *)(rp->ai_addr))->sin_port =
                                htons(port);
                else
                        ((struct sockaddr_in *)(rp->ai_addr))->sin_port =
                                htons(HTTP_PORT);
                if (connect(fd, rp->ai_addr, rp->ai_addrlen) == 0)
                {
                        flag = 1;
                        break;                  /* Success */
                }
                close(fd);
        }
        freeaddrinfo(res);

        if (!flag) {               /* No address succeeded */
                fprintf(stderr, "Could not bind\n");
                return ERROR;
        }
        return fd;

}

/*Get the host name from the HTTP request buffer*/
int get_host(char *request, char *host)
{
        char token[HTTP_URI_SIZE];
        while(tokenize(&request, token) > 0)
        {
                if(!strcasecmp(token, "Host:"))
                        break;
        }
        if(tokenize(&request, host) <= 0)
                return ERROR;
        return OK;
        
}

int get_port(char *request)
{
        int r;
        while((*request)!= '\n' &&
                        (*request)!='\0' &&
                        (*request != ':'))
                request++;
        if(*request == ':')
                request++;
        r = strtol(request, NULL, 10);
        if(r > 0 && r < 65535)
                return r;
        return -1;
}
/* Connect to the original server and get the response.
 * Cache the object.*/
int get_fresh_response_and_cache(char *request, char *uri, int cfd)
{
        struct wcache_entry *entry = wcache_entry_alloc();
        char host[1024]; //original server
        char buff[FRAGMENT_SIZE];
        char timestamp[100];
        int fd;
        time_t m;
        int rlen = 0, sent = 0;
        int nbytes;
        struct timeval t;
        gettimeofday(&t, NULL);

        //TODO dynamically allocate the signature buffer
        memcpy(entry->http_signature, uri, sizeof(entry->http_signature));
        

        //strcpy(entry->http_request, request);

        entry->valid = 1;
        entry->signature_hash = hash(uri);
        entry->size =  sizeof(struct wcache_entry);
        entry->ts = t.tv_sec;    
        wcache_list_init(&entry->fragments);
        LOG(stdout,"\nNew cache entry created for uri :");
        LOG(stdout, uri);
        
        get_host(request, host);  //TODO
        fd = connect_server(host, HTTP_PORT);
        // FIXME
         rlen = strlen(request);
         while(sent < rlen)
         {
                 nbytes = write(fd, request+sent, rlen - sent);
                 if(nbytes == -1){
                         perror("Socket write failed");
                         exit(1);
                 }
                 sent += nbytes;
         }
        
        /*sent the request to the actual server.
         *Now receive the response, fill up the cache entry
         */
         int f = 0;
        while((rlen = read(fd, buff, FRAGMENT_SIZE)) > 0)
        {
                if(f == 0)
                {
                        f = 1;
                        m = get_last_modified(buff, timestamp);
                        if(m != 0)
                        {
                                memcpy(entry->timestamp, timestamp, sizeof(entry->timestamp));
                                entry->last_modified = 1;
                                entry->ts = m ;
                        }
                        else
                                entry->last_modified = 0;
                }
                wcache_fragment_add(entry, buff, rlen);
                write(cfd, buff, rlen);
        }
        wcache_add(&cache, entry);
        return OK;

}
/* Check response. If it contains 304: Not modified, return 1; else return 0*/
int not_modified(char *buffer)
{
        if(strstr(buffer, "304") != NULL)
                return 1;
        return 0;
}

/*Ignore SIGPIPE */
void sigpipe_ign()

{
         struct sigaction act;
         act.sa_handler = SIG_IGN;
         sigemptyset (&act.sa_mask);
         act.sa_flags = 0;
         sigaction (SIGPIPE, &act, NULL);


}
void  http_server(void * data)
{

        char request[BUFFSIZE+1];
        int numbytes;
        int errflag = 0;
        char request_method[5];
        char version[10];
        int method;
        int sockfd;
        char uri[HTTP_URI_SIZE];
        char status[HTTP_STATUS_SIZE];
        sockfd = *(int *)data;
     //   free(data);

        if((numbytes = read(sockfd, (void *)request, BUFFSIZE)) <= 0)
        {
                LOG(stdout, "read from socket failed");
                close(sockfd);
                return;
        }
        char *requestptr = request;
        if((method = valid_method_string(&requestptr, request_method)) == -1)
        {
                //ERROR in Request
                snprintf(status, 
                        HTTP_STATUS_SIZE,
                        "HTTP/1.0 400 Bad Request: Invalid Method: %s\r\n", 
                        request_method);
                LOG(stdout, status);
                errflag = 1;
        }
        if(method != HTTP_GET)
        {
                int sfd, rlen, n;
                char host[1024]; //original server
                char *r = request;
                LOG(stdout, "Acting as relay. Caching only for GET\n");
                get_host(r, host);  //TODO
                //port = get_port(r);
                sfd = connect_server(host, HTTP_PORT);
                n = write(sfd, request, BUFFSIZE);
                while((rlen = read(sfd, request, BUFFSIZE)) > 0)
                {
                        write(sockfd, request, rlen);
                }
                return;
        
        }

        if(!errflag && (method == HTTP_GET))
        {
                //tokenize URI
                //check that the method name and URI are separated by exactly 1
                //SP character
                //requestptr should now be pointing at a SP character. If the
                //next character is SP as well, invalid request
                if(valid_uri(&requestptr, &cfg, uri) == -1)
                {
                        snprintf(status, 
                                HTTP_STATUS_SIZE,
                                "HTTP/1.0 400 Bad Request: Invalid URI: %s\r\n", 
                                uri);
                        LOG(stdout, status);
                        //ERROR in request
                        errflag = 1;
                
                }
                
        
        }
        if(!errflag)
        {
                if(valid_version(&requestptr, &cfg, version) == -1)
                {
                        //ERROR
                        //HTTP/1.0 400 Bad Request: Invalid HTTP-Version:
                        //<requested HTTP version>
                        snprintf(status, 
                                HTTP_STATUS_SIZE,
                                "HTTP/1.0 400 Bad Request: Invalid HTTP-Version: %s\r\n",
                                version);
                        LOG(stdout, status);
                        errflag = 1;
                }

        }
        //seems like request came up fine! Now lets see if we can read the file
        if(!errflag)
        {
                struct wcache_entry *entry;
                struct timeval tv;
                struct wcache_list_element *e;
                struct fragment_buffer *fb;
                time_t ctim;
                char cond_get[BUFFSIZE] = {0};
                int rlen;
                char host[1024]; //original server
                // get if-modified-since ts here TODO
                gettimeofday(&tv, NULL);
                entry = wcache_find(uri, tv.tv_sec);
                if(entry)
                {
                        lock_entry(entry);
                        if(entry->last_modified)
                        {
                                //construct conditional GET request, send it to
                                //server. If response says not modified, return
                                //the cache value. 
                                //Else discard the old fragments and add the
                                //newly returned ones.
                                //conditional GET
                                int srvfd;
                                snprintf(cond_get,BUFFSIZE, 
                                   "GET %s HTTP/1.0\r\nIf-modified-since: %s\n", 
                                   uri, entry->timestamp);
                                char *rr = request;
                                get_host(rr, host);  //TODO
                                srvfd = connect_server(host, HTTP_PORT);
                                if(write(srvfd, cond_get, BUFFSIZE) <= 0)
                                {
                                        LOG(stdout, "socket write error\n");
                                }
                                cond_get[0] = '\0';
                                LOG(stdout, "Sending conditional GET:");
                                LOG(stdout, host);
                                //now just get the initial part of the request
                                //check if it is 304 Not-Modified
                                rlen = read(srvfd, cond_get, FRAGMENT_SIZE);
                                if(not_modified(cond_get))
                                {
                                        LOG(stdout, "Not modified. Return cached result\n");
                                        list_for_each(e, &(entry->fragments))
                                        {
                                                fb = container_of(e, struct fragment_buffer, elem);
                                                write(sockfd, fb->buffer, fb->size);
                                        }
                                        close(sockfd);
                                        unlock_entry(entry);
                                        return;
                                }
                                else
                                {
                                        //remove the old fragments and cache new
                                        //ones
                                        
                                        wcache_remove_fragments(entry);
                                        ctim = get_last_modified(cond_get,
                                                        entry->timestamp);
                                        if(ctim != 0)
                                        {
                                                entry->last_modified = 1;
                                                entry->ts = ctim ;
                                        }
                                        else
                                        {
                                                entry->last_modified = 0;
                                                entry->ts = tv.tv_sec;
                                        }
                                        wcache_fragment_add(entry, cond_get, rlen);
                                        write(sockfd, cond_get, rlen);
                                        while((rlen = read(srvfd, cond_get, FRAGMENT_SIZE)) > 0)
                                        {
                                                wcache_fragment_add(entry,
                                                                cond_get, rlen);
                                                write(sockfd, cond_get, rlen);
                                        }
                                }
                               
                        
                        }
                        else
                        {
                        //FOUND IN CACHE!
                                if((tv.tv_sec - entry->ts) <= cfg.cachetime)
                                {
                                        list_for_each(e, &(entry->fragments))
                                        {
                                                fb = container_of(e, struct fragment_buffer, elem);
                                                write(sockfd, fb->buffer, fb->size);
                                        }
                                }
                                else
                                {
                                        //cache entry has expired. This is lazy
                                        //checking of expired entries.
                                        entry->valid = 0;
                                        unlock_entry(entry);
                                        wcache_remove_entry(entry);
                                        get_fresh_response_and_cache(request, uri, sockfd);
                                        goto NOUNLOCK;
                                }
                        }

                }
                else
                {
                        //NOT IN CACHE TODO
                        get_fresh_response_and_cache(request, uri, sockfd);
                
                }
                if(entry != NULL)
                        unlock_entry(entry);
        }




NOUNLOCK:
        close(sockfd);
        return;
}
int main(int argc, char *argv[])
{
        memset(&cfg, 0, sizeof(cfg));
        char filename[MAX_FILENAME];
        sigpipe_ign();
        if(argc == 2)
        {
                if(strlen(argv[1]) < MAX_FILENAME)
                        strcpy(filename, argv[1]);
        }
        else
                strcpy(filename, "config.txt");
        pthread_mutex_init(&list_mutex, NULL);
        wcache_list_init(&(cache.l));
        if(file_parser(filename, cfg_reader, &cfg)== -1)
        {
                LOG(stdout, "Configuration Error.Exiting...\n");
                exit(1);
        
        }
        cache.max_size = (cfg.cachesize)*(1024);
        LOG(stdout, 
           "Starting primitive HTTP web server implemented for CSC573\n");
        wcache_table_init();
        if(connection_handler(&cfg) == -1)
        {
                LOG(stdout, "Error!Exiting..\n");
                exit(1);
        }
        pthread_mutex_destroy(&list_mutex);
        return 0;
}
