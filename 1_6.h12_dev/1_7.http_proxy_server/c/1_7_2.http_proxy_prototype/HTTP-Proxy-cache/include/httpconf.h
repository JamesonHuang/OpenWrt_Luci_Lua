#ifndef __HTTPCONF__
#define __HTTPCONF__

#define MAX_BUF 1024
#define PENDING_CONN 64

#include "log.h"

/* various config element types parsed from config file*/
enum cfg_elements_t {
        CACHE_SIZE,                      
        CACHE_TIME,  
        IPADDR,
        LISTEN_PORT,
        DEFAULT         
};
struct http_server_config {
        int listen_port;                /*server listens on this port*/
        char ipaddr[16];        
        int cachesize;
        int cachetime;
};

/*Not used currently. Convenience data structure in case threads need to be used*/
struct http_server_data {
        struct http_server_config *cfg;
        int sockfd;
};

/*Function declarations */

int cfg_reader(void *c, char *line);
void http_server(void * sockfd);

int valid_method_string(char **request, char *request_method);
int valid_version(char **request, struct http_server_config *cfg, 
                char *version);

int valid_uri(char **request, struct http_server_config *cfg, 
                char *uri);
int connection_handler(struct http_server_config *cfg);
#endif
