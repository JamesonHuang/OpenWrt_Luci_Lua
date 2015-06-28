/************************************************
 *
 * file  : main.cpp
 * author: bobding
 * date  : 2014-09-25
 * detail:
 *
************************************************/

#include "TcpSocket.h"
#include "DownloadTask.h"

int main()
{
    /*
    TcpSocket* socket = new TcpSocket();

    int ret = socket->Open();

    ret = socket->Connect("image.modoomarble.qq.com", 80);
    //ret = socket->Connect("14.17.32.211");

    const char* s = "GET /modoomarble/data610_spr_1.zip HTTP/1.1\r\n"
                    "Host: image.modoomarble.qq.com\r\n\r\n";

    ret = socket->Send(s, strlen(s));

    char buffer[512];
    ret = socket->Recv(buffer, 512);
    buffer[ret] = 0;
    printf("length: %d\n %s\n", ret, buffer);

    ret = socket->Close();

    ret = socket->Open();

    ret = socket->Connect("www.qq.com", 80);

    s = "GET / HTTP/1.1\r\n"
        "Host: www.qq.com\r\n\r\n";

    ret = socket->Send(s, strlen(s));

    ret = socket->Recv(buffer, 512);
    buffer[ret] = 0;
    printf("length: %d\n %s\n", ret, buffer);
    */

    char* buffer = 0;
    unsigned int length = 0;

    //DownloadTask task;
    //int ret = task.Start("image.modoomarble.qq.com/modoomarble/data610_spr_1.zip", &buffer, &length);
    int ret = taskStart("images.china.cn/attachement/jpg/site1000/20150611/002564bb43f116e36f8c4c.jpg", &buffer, &length);

    if (ret >= 0)
    {
        FILE* fp = fopen("002564bb43f116e36f8c4c.jpg", "w");
        fwrite(buffer, length, 1, fp);
        fclose(fp);
    }

    free(buffer);

    return 0;
}
