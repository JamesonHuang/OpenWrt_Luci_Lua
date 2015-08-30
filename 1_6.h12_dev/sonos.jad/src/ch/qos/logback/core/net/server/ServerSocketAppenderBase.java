// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.net.ServerSocketFactory;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ServerRunner, RemoteReceiverServerListener, RemoteReceiverServerRunner, ServerListener, 
//            ClientVisitor, RemoteReceiverClient, Client

public abstract class ServerSocketAppenderBase extends AppenderBase
{

    public ServerSocketAppenderBase()
    {
        port = 4560;
        backlog = 50;
        clientQueueSize = 100;
    }

    protected void append(Object obj)
    {
        if(obj != null)
        {
            postProcessEvent(obj);
            final Serializable serEvent = getPST().transform(obj);
            runner.accept(new ClientVisitor() {

                public volatile void visit(Client client)
                {
                    visit((RemoteReceiverClient)client);
                }

                public void visit(RemoteReceiverClient remotereceiverclient)
                {
                    remotereceiverclient.offer(serEvent);
                }

                final ServerSocketAppenderBase this$0;
                final Serializable val$serEvent;

            
            {
                this$0 = ServerSocketAppenderBase.this;
                serEvent = serializable;
                super();
            }
            }
);
        }
    }

    protected ServerListener createServerListener(ServerSocket serversocket)
    {
        return new RemoteReceiverServerListener(serversocket);
    }

    protected ServerRunner createServerRunner(ServerListener serverlistener, Executor executor)
    {
        return new RemoteReceiverServerRunner(serverlistener, executor, getClientQueueSize());
    }

    public String getAddress()
    {
        return address;
    }

    public Integer getBacklog()
    {
        return Integer.valueOf(backlog);
    }

    public int getClientQueueSize()
    {
        return clientQueueSize;
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

    protected abstract PreSerializationTransformer getPST();

    public int getPort()
    {
        return port;
    }

    protected ServerSocketFactory getServerSocketFactory()
        throws Exception
    {
        return ServerSocketFactory.getDefault();
    }

    protected abstract void postProcessEvent(Object obj);

    public void setAddress(String s)
    {
        address = s;
    }

    public void setBacklog(Integer integer)
    {
        backlog = integer.intValue();
    }

    public void setClientQueueSize(int i)
    {
        clientQueueSize = i;
    }

    public void setPort(int i)
    {
        port = i;
    }

    public void start()
    {
        if(!isStarted())
            try
            {
                runner = createServerRunner(createServerListener(getServerSocketFactory().createServerSocket(getPort(), getBacklog().intValue(), getInetAddress())), getContext().getExecutorService());
                runner.setContext(getContext());
                getContext().getExecutorService().execute(runner);
                super.start();
            }
            catch(Exception exception)
            {
                addError((new StringBuilder()).append("server startup error: ").append(exception).toString(), exception);
            }
    }

    public void stop()
    {
        if(isStarted())
            try
            {
                runner.stop();
                super.stop();
            }
            catch(IOException ioexception)
            {
                addError((new StringBuilder()).append("server shutdown error: ").append(ioexception).toString(), ioexception);
            }
    }

    public static final int DEFAULT_BACKLOG = 50;
    public static final int DEFAULT_CLIENT_QUEUE_SIZE = 100;
    private String address;
    private int backlog;
    private int clientQueueSize;
    private int port;
    private ServerRunner runner;
}
