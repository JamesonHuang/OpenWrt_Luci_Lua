// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.util.CloseUtil;
import java.io.IOException;
import java.net.*;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ServerListener, Client

public abstract class ServerSocketListener
    implements ServerListener
{

    public ServerSocketListener(ServerSocket serversocket)
    {
        serverSocket = serversocket;
    }

    private String socketAddressToString(SocketAddress socketaddress)
    {
        String s = socketaddress.toString();
        int i = s.indexOf("/");
        if(i >= 0)
            s = s.substring(i + 1);
        return s;
    }

    public Client acceptClient()
        throws IOException
    {
        Socket socket = serverSocket.accept();
        return createClient(socketAddressToString(socket.getRemoteSocketAddress()), socket);
    }

    public void close()
    {
        CloseUtil.closeQuietly(serverSocket);
    }

    protected abstract Client createClient(String s, Socket socket)
        throws IOException;

    public String toString()
    {
        return socketAddressToString(serverSocket.getLocalSocketAddress());
    }

    private final ServerSocket serverSocket;
}
