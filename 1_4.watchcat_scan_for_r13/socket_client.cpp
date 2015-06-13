/*************************************************************************
	> File Name:        socket_client.cpp
	> Description:      
	> Conclusion:          
	> Author:           rh_Jameson
	> Created Time:     2015年05月21日 星期四 16时19分58秒
 ************************************************************************/
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<netdb.h>
#include <sys/ioctl.h>
#include <net/if.h>
/**
客户端实现广播
**/
#define IP_FOUND "sysinfo"
#define IP_FOUND_ACK "IP_FOUND_ACK"
#define IFNAME "wlan2"
#define MCAST_PORT 51232
int main(int argc,char*argv[]){
    int ret=-1;
    int sock=-1;
    int so_broadcast=1;
    struct ifreq ifr;
    struct sockaddr_in broadcast_addr;//广播地址
    struct sockaddr_in from_addr;//服务端地址
    socklen_t from_len=sizeof(from_addr);
    int count=-1;
    fd_set readfd;//读文件描述符集合
    char buffer[1024];
    struct timeval timeout;
    timeout.tv_sec=2;//超时时间为2秒
    timeout.tv_usec=0;
    sock=socket(AF_INET,SOCK_DGRAM,0);//建立数据报套接字
    
    if(sock<0){
      printf("HandleIPFound:sock init error\n");
      return 0;
    }
    //将使用的网络接口名字复制到ifr.ifr_name中，由于不同的网卡接口的广播地址是不一样的，因此指定网卡接口
    strncpy(ifr.ifr_name,IFNAME,strlen(IFNAME));
    //发送命令，获得网络接口的广播地址
    if(ioctl(sock,SIOCGIFBRDADDR,&ifr)==-1){
        perror("ioctl error");
        return 0;
    }
    //将获得的广播地址复制到broadcast_addr
    memcpy(&broadcast_addr,&ifr.ifr_broadaddr,sizeof(struct sockaddr_in));
    //设置广播端口号
    //inet_aton("255.255.255.255", &broadcast_addr.sin_addr);
    printf("broadcast IP is:%s\n",inet_ntoa(broadcast_addr.sin_addr));
    broadcast_addr.sin_family=AF_INET;
    broadcast_addr.sin_port=htons(MCAST_PORT);
    //默认的套接字描述符sock是不支持广播，必须设置套接字描述符以支持广播
    ret=setsockopt(sock,SOL_SOCKET,SO_BROADCAST,&so_broadcast,sizeof(so_broadcast));
    //发送多次广播，看网络上是否有服务器存在
    int times=10;
    int i=0;
    for(i = 0; i < times; i++){//一共发送10次广播，每次等待2秒是否有回应
      //广播发送服务器地址请求
        timeout.tv_sec = 2;//超时时间为2秒
        timeout.tv_usec = 0;
        ret=sendto(sock,IP_FOUND, strlen(IP_FOUND), 0, (struct sockaddr*)&broadcast_addr, sizeof(broadcast_addr));
        if(ret == -1){
            continue;
        }

        //文件描述符清0
        FD_ZERO(&readfd);
        //将套接字文件描述符加入到文件描述符集合中
        FD_SET(sock,&readfd);
        //select侦听是否有数据到来
        ret=select(sock+1,&readfd,NULL,NULL,&timeout);
        switch(ret){
         case -1:
            break;
         case 0:
            printf("timeout\n");
            break;
         default:
            //接收到数据
            if(FD_ISSET(sock,&readfd)){
                count = recvfrom(sock, buffer, 1024, 0, (struct sockaddr*)&from_addr,&from_len);//from_addr为服务器端地址
                printf("recvmsg is %s\n", buffer);
                if(strstr(buffer, IP_FOUND_ACK)){
                    printf("found server IP is:%s\n",inet_ntoa(from_addr.sin_addr));
                    //服务器端的发送端口号
                    printf("Server Port:%d\n",htons(from_addr.sin_port));
                }
              return 0;  
            }
            break;
        }
    }
    return 0;
} 
