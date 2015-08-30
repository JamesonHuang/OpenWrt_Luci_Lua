// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ServerSocketListener, RemoteReceiverStreamClient, Client, RemoteReceiverClient

class RemoteReceiverServerListener extends ServerSocketListener
{

    public RemoteReceiverServerListener(ServerSocket serversocket)
    {
        super(serversocket);
    }

    protected volatile Client createClient(String s, Socket socket)
        throws IOException
    {
        return createClient(s, socket);
    }

    protected RemoteReceiverClient createClient(String s, Socket socket)
        throws IOException
    {
        return new RemoteReceiverStreamClient(s, socket);
    }
}
