// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil
{

    public CloseUtil()
    {
    }

    public static void closeQuietly(Closeable closeable)
    {
        if(closeable != null)
            try
            {
                closeable.close();
            }
            catch(IOException ioexception) { }
    }

    public static void closeQuietly(ServerSocket serversocket)
    {
        if(serversocket != null)
            try
            {
                serversocket.close();
            }
            catch(IOException ioexception) { }
    }

    public static void closeQuietly(Socket socket)
    {
        if(socket != null)
            try
            {
                socket.close();
            }
            catch(IOException ioexception) { }
    }
}
