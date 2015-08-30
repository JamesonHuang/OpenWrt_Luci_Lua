// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import ch.qos.logback.core.util.CloseUtil;
import ch.qos.logback.core.util.Duration;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.*;
import javax.net.SocketFactory;

// Referenced classes of package ch.qos.logback.core.net:
//            SocketConnector, DefaultSocketConnector

public abstract class AbstractSocketAppender extends AppenderBase
    implements Runnable, SocketConnector.ExceptionHandler
{

    protected AbstractSocketAppender()
    {
        port = 4560;
        reconnectionDelay = new Duration(30000L);
        queueSize = 128;
        acceptConnectionTimeout = 5000;
        eventDelayLimit = new Duration(100L);
    }

    protected AbstractSocketAppender(String s, int i)
    {
        port = 4560;
        reconnectionDelay = new Duration(30000L);
        queueSize = 128;
        acceptConnectionTimeout = 5000;
        eventDelayLimit = new Duration(100L);
        remoteHost = s;
        port = i;
    }

    private Future activateConnector(SocketConnector socketconnector)
    {
        Future future1 = getContext().getExecutorService().submit(socketconnector);
        Future future = future1;
_L2:
        return future;
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        future = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private SocketConnector createConnector(InetAddress inetaddress, int i, int j, long l)
    {
        SocketConnector socketconnector = newConnector(inetaddress, i, j, l);
        socketconnector.setExceptionHandler(this);
        socketconnector.setSocketFactory(getSocketFactory());
        return socketconnector;
    }

    private void dispatchEvents()
        throws InterruptedException
    {
        ObjectOutputStream objectoutputstream;
        int i;
        socket.setSoTimeout(acceptConnectionTimeout);
        objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
        socket.setSoTimeout(0);
        addInfo((new StringBuilder()).append(peerId).append("connection established").toString());
        i = 0;
_L1:
        do
        {
            Object obj = queue.take();
            postProcessEvent(obj);
            objectoutputstream.writeObject(getPST().transform(obj));
            objectoutputstream.flush();
        } while(++i < 70);
        objectoutputstream.reset();
        i = 0;
          goto _L1
        IOException ioexception;
        ioexception;
        addInfo((new StringBuilder()).append(peerId).append("connection failed: ").append(ioexception).toString());
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(peerId).append("connection closed").toString());
        return;
        Exception exception;
        exception;
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(peerId).append("connection closed").toString());
        throw exception;
    }

    protected static InetAddress getAddressByName(String s)
    {
        InetAddress inetaddress1 = InetAddress.getByName(s);
        InetAddress inetaddress = inetaddress1;
_L2:
        return inetaddress;
        Exception exception;
        exception;
        inetaddress = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Socket waitForConnectorToReturnASocket()
        throws InterruptedException
    {
        Socket socket1;
        try
        {
            socket1 = (Socket)connectorTask.get();
            connectorTask = null;
        }
        catch(ExecutionException executionexception)
        {
            socket1 = null;
        }
        return socket1;
    }

    protected void append(Object obj)
    {
        if(obj != null && isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            if(!queue.offer(obj, eventDelayLimit.getMilliseconds(), TimeUnit.MILLISECONDS))
                addInfo((new StringBuilder()).append("Dropping event due to timeout limit of [").append(eventDelayLimit).append("] milliseconds being exceeded").toString());
        }
        catch(InterruptedException interruptedexception)
        {
            addError("Interrupted while appending event to SocketAppender", interruptedexception);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void connectionFailed(SocketConnector socketconnector, Exception exception)
    {
        if(exception instanceof InterruptedException)
            addInfo("connector interrupted");
        else
        if(exception instanceof ConnectException)
            addInfo((new StringBuilder()).append(peerId).append("connection refused").toString());
        else
            addInfo((new StringBuilder()).append(peerId).append(exception).toString());
    }

    public Duration getEventDelayLimit()
    {
        return eventDelayLimit;
    }

    protected abstract PreSerializationTransformer getPST();

    public int getPort()
    {
        return port;
    }

    public int getQueueSize()
    {
        return queueSize;
    }

    public Duration getReconnectionDelay()
    {
        return reconnectionDelay;
    }

    public String getRemoteHost()
    {
        return remoteHost;
    }

    protected SocketFactory getSocketFactory()
    {
        return SocketFactory.getDefault();
    }

    BlockingQueue newBlockingQueue(int i)
    {
        Object obj;
        if(i <= 0)
            obj = new SynchronousQueue();
        else
            obj = new ArrayBlockingQueue(i);
        return (BlockingQueue)obj;
    }

    protected SocketConnector newConnector(InetAddress inetaddress, int i, long l, long l1)
    {
        return new DefaultSocketConnector(inetaddress, i, l, l1);
    }

    protected abstract void postProcessEvent(Object obj);

    public final void run()
    {
        signalEntryInRunMethod();
_L5:
        if(Thread.currentThread().isInterrupted()) goto _L2; else goto _L1
_L1:
        Future future;
        connectorTask = activateConnector(createConnector(address, port, 0, reconnectionDelay.getMilliseconds()));
        future = connectorTask;
        if(future != null) goto _L3; else goto _L2
_L2:
        addInfo("shutting down");
        return;
_L3:
        socket = waitForConnectorToReturnASocket();
        if(socket == null) goto _L2; else goto _L4
_L4:
        dispatchEvents();
          goto _L5
        InterruptedException interruptedexception;
        interruptedexception;
          goto _L2
    }

    void setAcceptConnectionTimeout(int i)
    {
        acceptConnectionTimeout = i;
    }

    public void setEventDelayLimit(Duration duration)
    {
        eventDelayLimit = duration;
    }

    public void setPort(int i)
    {
        port = i;
    }

    public void setQueueSize(int i)
    {
        queueSize = i;
    }

    public void setReconnectionDelay(Duration duration)
    {
        reconnectionDelay = duration;
    }

    public void setRemoteHost(String s)
    {
        remoteHost = s;
    }

    protected void signalEntryInRunMethod()
    {
    }

    public void start()
    {
        if(!isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
        if(port <= 0)
        {
            i = 1;
            addError((new StringBuilder()).append("No port was configured for appender").append(name).append(" For more information, please visit http://logback.qos.ch/codes.html#socket_no_port").toString());
        }
        if(remoteHost == null)
        {
            i++;
            addError((new StringBuilder()).append("No remote host was configured for appender").append(name).append(" For more information, please visit http://logback.qos.ch/codes.html#socket_no_host").toString());
        }
        if(queueSize < 0)
        {
            i++;
            addError("Queue size must be non-negative");
        }
        if(i == 0)
            try
            {
                address = InetAddress.getByName(remoteHost);
            }
            catch(UnknownHostException unknownhostexception)
            {
                addError((new StringBuilder()).append("unknown host: ").append(remoteHost).toString());
                i++;
            }
        if(i == 0)
        {
            queue = newBlockingQueue(queueSize);
            peerId = (new StringBuilder()).append("remote peer ").append(remoteHost).append(":").append(port).append(": ").toString();
            task = getContext().getExecutorService().submit(this);
            super.start();
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void stop()
    {
        if(isStarted())
        {
            CloseUtil.closeQuietly(socket);
            task.cancel(true);
            if(connectorTask != null)
                connectorTask.cancel(true);
            super.stop();
        }
    }

    private static final int DEFAULT_ACCEPT_CONNECTION_DELAY = 5000;
    private static final int DEFAULT_EVENT_DELAY_TIMEOUT = 100;
    public static final int DEFAULT_PORT = 4560;
    public static final int DEFAULT_QUEUE_SIZE = 128;
    public static final int DEFAULT_RECONNECTION_DELAY = 30000;
    private int acceptConnectionTimeout;
    private InetAddress address;
    private Future connectorTask;
    private Duration eventDelayLimit;
    private String peerId;
    private int port;
    private BlockingQueue queue;
    private int queueSize;
    private Duration reconnectionDelay;
    private String remoteHost;
    private volatile Socket socket;
    private Future task;
}
