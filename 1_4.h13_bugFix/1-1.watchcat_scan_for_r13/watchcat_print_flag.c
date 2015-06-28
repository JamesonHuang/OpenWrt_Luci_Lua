/*
 *  UDP BS INFO Server
 */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>

char* pcmdurl = "wget http://127.0.0.1/cgi-bin/luci/bs/info -q -O -";

//char filebuf[2048];

int PipeReadBsInfo(char* filebuf)
{
    printf("pipe test\n");
    FILE * fstr = NULL;
    int ret;
    int haveread = 0;
    fstr = popen(pcmdurl, "rb");
    
    if(fstr == NULL){
        printf("popen fail");
        return 0;
    }
    while(1){
        ret = fread(filebuf + haveread, 1, 1024, fstr);
        if(ret <= 0)
            break;
        haveread += ret;
    }
    printf("READ:******%d chars\n", haveread);
    printf("READ:%s\n", filebuf);
    pclose(fstr);
}

int main(int argc ,char * argv[])
{
    unsigned short port = 51232;
    int sock_fd;
    struct sockaddr_in addr;

    struct sockaddr_in client_addr ;
    socklen_t addr_len;

    char buf[1024];
    int len ;
    perror("init test1\n");
    printf("init test2\n");
    if(argc >1)
    {
        port = (unsigned short)atoi(argv[1]);
    }

    sock_fd = socket(PF_INET,SOCK_DGRAM,0);
    //set broadcast 
    int on = 1;
    setsockopt(sock_fd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on));
    
    if(sock_fd == -1)
    {
        perror("socket");
        return -1;
    }
    memset(&addr,0,sizeof(addr));
    addr.sin_port = htons(port);
    addr.sin_family = AF_INET;
    //addr.sin_addr.s_addr = inet_addr("0.0.0.0");
	//INADDR_ANY = 0 = inet_addr("0.0.0.0")
    addr.sin_addr.s_addr = INADDR_ANY;

    if(bind(sock_fd,(const struct sockaddr *)&addr,sizeof(addr)) == -1)
    {
        perror("bind");
        close(sock_fd);
        return -2;
    }
    //fprintf(stdout,"UDP Echo Server listen on %d\n",port); 
    printf("init test33\n");
    printf("UDP Echo Server listen on %d\n", port);
    printf("init test34\n");
    while(1)
    {
        //code change    
        char *filebuf = (char *)malloc(2048 * sizeof(char));
        if(filebuf == NULL)
        {
            //printf("molloc fail");
            perror("molloc fail");
            return 0;
        }
    
        memset(filebuf, 0x00, sizeof(filebuf));
        memset(&client_addr,0,sizeof(client_addr));
		memset(buf, 0x00, sizeof(buf));

        client_addr.sin_family = AF_INET;
        addr_len = sizeof(client_addr);
        //len = recvfrom(sock_fd,buf,sizeof(buf),0,NULL,NULL);
        
        printf("while test1\n");
        len = recvfrom(sock_fd,buf,sizeof(buf),0,(struct sockaddr *)&client_addr,&addr_len);
        printf("recv test\n");
        
        //if(len <= 0){
        printf("while test1 %s\n", buf);
        if((len <=0) || (strncmp(buf, "sysinfo", sizeof("sysinfo")-1) != 0)){
            //sleep(1);
            printf("%d\n", len);
            printf("%d\n", (strncmp(buf, "sysinfo", sizeof("sysinfo")-1)));
            continue;
        }
        printf("recvfrom test2\n");
        buf[len] = 0;
        printf("client addr %s ,port %d,buf %s\n",inet_ntoa(client_addr.sin_addr),ntohs(client_addr.sin_port),buf);
        //ReadBsInfo();
        /*
         *if(strlen(filebuf) == 0){
         *   PipeReadBsInfo();
         *}
         */
        printf("send begin test\n");
        PipeReadBsInfo(filebuf);
        printf("%s %d : %s\n", __func__, __LINE__, filebuf);
        sendto(sock_fd,filebuf,strlen(filebuf)+1,0,(struct sockaddr *)&client_addr,addr_len);
        printf("send over test\n");
        free(filebuf);
    }
    close(sock_fd);
    printf("close test\n");
}
