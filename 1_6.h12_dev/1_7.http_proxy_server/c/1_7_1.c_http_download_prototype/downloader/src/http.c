/*
    "http.c"
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
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#include "http.h"
#include "dbg.h"

extern int download (char const *ipstr, int port, char const *path, char const *file_name)
{
    int socketfd;
    char http_protol[BUFFER];
    int ret;
    float http_ver = 0.0;
    int status;
    int write_length;
    int file_len;
    char recvbuf[BUFFER]; /* recieves http server http protol HEAD */
    char buffer[BUFFER];
    FILE *fp = NULL;
    void *start = NULL;
    struct sockaddr_in download_addr;

    if (port == 0) port = PORT;
    
    if ((socketfd = socket (AF_INET, SOCK_STREAM, 0)) == -1)
    {
        printf ("socket error:%d\n", errno);
        return -1;
    }
    
    bzero (&download_addr, sizeof (download_addr));
    
    download_addr.sin_family = AF_INET;
    download_addr.sin_port = htons (port);
    /* download_addr.sin_addr.s_addr = inet_addr("10.0.0.21"); */
    inet_pton (AF_INET, ipstr, &download_addr.sin_addr.s_addr);

    if (connect (socketfd, (struct sockaddr *)&download_addr, (socklen_t)sizeof (download_addr)) == -1)
    {
        printf ("connect error:%d\n", errno);
        exit (1);
    }
    
    bzero (http_protol, sizeof (http_protol));
    bzero (recvbuf, sizeof (recvbuf));
    
    sprintf (http_protol, "GET %s%s HTTP/1.1\n" \
             "From: 2010.tpk@gmail.com\n" \
             "Host: mark@\n" \
             "User-agent: downloadApp(beta-0.1, i386-pc-linux)\n" \
             "Conection: Keep-Alive\n\n",
             path, file_name);


    ret = write (socketfd, http_protol, strlen (http_protol));
    if (ret == -1)
    {
        printf ("write failed:%d\n", errno);
        exit (1);
    }
    
    ret = read (socketfd, recvbuf, sizeof (recvbuf));
    if (ret == 0)
    {
        printf ("server closed:%d\n", errno);
        exit (1);
    }
    else if (ret == -1)
    {
        printf ("read failed:%d\n", errno);
        exit (1);
    }
    
    printf ("%s", recvbuf);
    sscanf (strstr (recvbuf, "HTTP/"), "HTTP/%f %d", &http_ver, &status);
    sscanf (strstr (recvbuf, "Content-Length"), "Content-Length: %d", &file_len);

    if (status != 200 || file_len == 0)
    {
        printf ("http connect failed!\n");
        exit (1);
    }
    
                              
    fp = fopen (file_name, "wb");
    if (fp == NULL)
    {
        printf ("File:%s Can not open:%d\n", file_name, errno);
        exit (1);
    }
    
    bzero (buffer, sizeof (buffer));

    /* download file's address start here whithout http protol HEAD */
    start = (void *) strstr(recvbuf, "\r\n\r\n") + sizeof ("\r\n\r\n")-1;
    fwrite (start, sizeof (char), ret - ((void *)start - (void *)&recvbuf), fp);
    
    while (1)
    {
        ret = read (socketfd, buffer, sizeof (buffer));
        
        if (ret == 0) break; /* download finish */
                
        if (ret < 0)
        {
            printf ("Recieve data from server [%s] failed!\n", ipstr);
            break;
        }
        
        write_length = fwrite (buffer, sizeof (char), ret, fp);
        if (write_length < ret)
        {
            printf ("File: %s write failed.\n", file_name);
            break;
        }
        bzero (buffer, sizeof (buffer));
    }

    printf ("\ndownload %s file finish.\n", file_name);

    close (socketfd);
    fclose (fp);
    
    return 0;
}
