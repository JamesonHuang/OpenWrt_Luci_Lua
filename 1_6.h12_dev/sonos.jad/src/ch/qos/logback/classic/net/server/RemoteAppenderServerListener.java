// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.core.net.server.Client;
import ch.qos.logback.core.net.server.ServerSocketListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Referenced classes of package ch.qos.logback.classic.net.server:
//            RemoteAppenderStreamClient, RemoteAppenderClient

class RemoteAppenderServerListener extends ServerSocketListener
{

    public RemoteAppenderServerListener(ServerSocket serversocket)
    {
        super(serversocket);
    }

    protected RemoteAppenderClient createClient(String s, Socket socket)
        throws IOException
    {
        return new RemoteAppenderStreamClient(s, socket);
    }

    protected volatile Client createClient(String s, Socket socket)
        throws IOException
    {
        return createClient(s, socket);
    }
}
