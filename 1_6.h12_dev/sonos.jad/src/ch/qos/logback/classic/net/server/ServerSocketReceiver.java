// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.net.ReceiverBase;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.net.server.ServerListener;
import ch.qos.logback.core.net.server.ServerRunner;
import ch.qos.logback.core.util.CloseUtil;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executor;
import javax.net.ServerSocketFactory;

// Referenced classes of package ch.qos.logback.classic.net.server:
//            RemoteAppenderServerListener, RemoteAppenderServerRunner

public class ServerSocketReceiver extends ReceiverBase
{

    public ServerSocketReceiver()
    {
        port = 4560;
        backlog = 50;
    }

    protected ServerListener createServerListener(ServerSocket serversocket)
    {
        return new RemoteAppenderServerListener(serversocket);
    }

    protected ServerRunner createServerRunner(ServerListener serverlistener, Executor executor)
    {
        return new RemoteAppenderServerRunner(serverlistener, executor);
    }

    public String getAddress()
    {
        return address;
    }

    public int getBacklog()
    {
        return backlog;
    }

    protected InetAddress getInetAddress()
        throws UnknownHostException
    {
        InetAddress inetaddress;
        if(getAddress() == null)
            inetaddress = null;
        else
            inetaddress = InetAddress.getByName(getAddress());
        return inetaddress;
    }

    public int getPort()
    {
        return port;
    }

    protected Runnable getRunnableTask()
    {
        return runner;
    }

    protected ServerSocketFactory getServerSocketFactory()
        throws Exception
    {
        return ServerSocketFactory.getDefault();
    }

    protected void onStop()
    {
        try
        {
            if(runner != null)
                runner.stop();
        }
        catch(IOException ioexception)
        {
            addError((new StringBuilder()).append("server shutdown error: ").append(ioexception).toString(), ioexception);
        }
    }

    public void setAddress(String s)
    {
        address = s;
    }

    public void setBacklog(int i)
    {
        backlog = i;
    }

    public void setPort(int i)
    {
        port = i;
    }

    protected boolean shouldStart()
    {
        runner = createServerRunner(createServerListener(getServerSocketFactory().createServerSocket(getPort(), getBacklog(), getInetAddress())), getContext().getExecutorService());
        runner.setContext(getContext());
        boolean flag = true;
_L2:
        return flag;
        Exception exception;
        exception;
        addError((new StringBuilder()).append("server startup error: ").append(exception).toString(), exception);
        CloseUtil.closeQuietly(serverSocket);
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int DEFAULT_BACKLOG = 50;
    private String address;
    private int backlog;
    private int port;
    private ServerRunner runner;
    private ServerSocket serverSocket;
}
