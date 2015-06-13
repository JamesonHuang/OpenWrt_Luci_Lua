/*
 * "
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

static char* pcmdurl = "wget http://127.0.0.1/cgi-bin/luci/bs/info -q -O -";
int read_sysinfo(char* filebuf, int filebuf_len)
{
	FILE * fstr = NULL;
	int ret;
	int haveread = 0;
	fstr = popen(pcmdurl, "rb");
	if (fstr == NULL) {
		printf("popen fail");
		return 0;
	}
	while (filebuf_len > 0) {
		ret = fread(filebuf + haveread, 1, filebuf_len, fstr);
		if(ret <= 0)
			break;
		haveread += ret;
		filebuf_len -= ret;
	}
	printf("READ:******\n%s", filebuf);
	pclose(fstr);
	return 0;
}

int main(int argc ,char * argv[])
{
    int sock_fd;
    socklen_t addr_len;
    struct sockaddr_in addr;
    struct sockaddr_in client_addr ;
    unsigned short port = 51232;
    char buf[1024];
    int len ;
    if(argc > 1) {
        port = (unsigned short)atoi(argv[1]);
    }
    sock_fd = socket(PF_INET,SOCK_DGRAM,0);
    int on = 1;
    setsockopt(sock_fd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on));
    if (sock_fd == -1) {
        perror("socket");
        return -1;
    }
    memset(&addr,0,sizeof(addr));
    addr.sin_port = htons(port);
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = INADDR_ANY;
    if (bind(sock_fd,(const struct sockaddr *)&addr,sizeof(addr)) == -1) {
        perror("bind");
        close(sock_fd);
        return -2;
    }
    printf("UDP Echo Server listen on %d\n", port);
    while(1) {
        int filebuf_len = 2048;
        char *filebuf = (char *)calloc(filebuf_len, sizeof(char));
        if (filebuf == NULL) {
            perror("molloc fail");
			continue;
        }
        memset(&client_addr,0,sizeof(client_addr));
		memset(buf, 0x00, sizeof(buf));
        client_addr.sin_family = AF_INET;
        addr_len = sizeof(client_addr);
        len = recvfrom(sock_fd, buf, sizeof(buf), 0, (struct sockaddr *)&client_addr, &addr_len);
        if ((len <=0) || (strncmp(buf, "sysinfo", sizeof("sysinfo")-1) != 0)) {
            sleep(1);
            continue;
        }
        buf[len] = 0;
        printf("client addr %s ,port %d,buf %s\n",inet_ntoa(client_addr.sin_addr),ntohs(client_addr.sin_port),buf);
        read_sysinfo(filebuf, filebuf_len);
        sendto(sock_fd,filebuf,strlen(filebuf)+1,0,(struct sockaddr *)&client_addr,addr_len);
        free(filebuf);
    }
    close(sock_fd);

	return 0;
}
