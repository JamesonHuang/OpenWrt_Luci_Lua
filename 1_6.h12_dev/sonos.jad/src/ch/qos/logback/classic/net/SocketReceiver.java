// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.net.DefaultSocketConnector;
import ch.qos.logback.core.net.SocketConnector;
import ch.qos.logback.core.util.CloseUtil;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import javax.net.SocketFactory;

// Referenced classes of package ch.qos.logback.classic.net:
//            ReceiverBase

public class SocketReceiver extends ReceiverBase
    implements Runnable, ch.qos.logback.core.net.SocketConnector.ExceptionHandler
{

    public SocketReceiver()
    {
        acceptConnectionTimeout = 5000;
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

    private SocketConnector createConnector(InetAddress inetaddress, int i, int j, int k)
    {
        SocketConnector socketconnector = newConnector(inetaddress, i, j, k);
        socketconnector.setExceptionHandler(this);
        socketconnector.setSocketFactory(getSocketFactory());
        return socketconnector;
    }

    private void dispatchEvents(LoggerContext loggercontext)
    {
        socket.setSoTimeout(acceptConnectionTimeout);
        ObjectInputStream objectinputstream = new ObjectInputStream(socket.getInputStream());
        socket.setSoTimeout(0);
        addInfo((new StringBuilder()).append(receiverId).append("connection established").toString());
        do
        {
            ILoggingEvent iloggingevent;
            Logger logger;
            do
            {
                iloggingevent = (ILoggingEvent)objectinputstream.readObject();
                logger = loggercontext.getLogger(iloggingevent.getLoggerName());
            } while(!logger.isEnabledFor(iloggingevent.getLevel()));
            logger.callAppenders(iloggingevent);
        } while(true);
        EOFException eofexception;
        eofexception;
        addInfo((new StringBuilder()).append(receiverId).append("end-of-stream detected").toString());
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(receiverId).append("connection closed").toString());
_L1:
        return;
        IOException ioexception;
        ioexception;
        addInfo((new StringBuilder()).append(receiverId).append("connection failed: ").append(ioexception).toString());
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(receiverId).append("connection closed").toString());
          goto _L1
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        addInfo((new StringBuilder()).append(receiverId).append("unknown event class: ").append(classnotfoundexception).toString());
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(receiverId).append("connection closed").toString());
          goto _L1
        Exception exception;
        exception;
        CloseUtil.closeQuietly(socket);
        socket = null;
        addInfo((new StringBuilder()).append(receiverId).append("connection closed").toString());
        throw exception;
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

    public void connectionFailed(SocketConnector socketconnector, Exception exception)
    {
        if(exception instanceof InterruptedException)
            addInfo("connector interrupted");
        else
        if(exception instanceof ConnectException)
            addInfo((new StringBuilder()).append(receiverId).append("connection refused").toString());
        else
            addInfo((new StringBuilder()).append(receiverId).append(exception).toString());
    }

    protected Runnable getRunnableTask()
    {
        return this;
    }

    protected SocketFactory getSocketFactory()
    {
        return SocketFactory.getDefault();
    }

    protected SocketConnector newConnector(InetAddress inetaddress, int i, int j, int k)
    {
        return new DefaultSocketConnector(inetaddress, i, j, k);
    }

    protected void onStop()
    {
        if(socket != null)
            CloseUtil.closeQuietly(socket);
    }

    public void run()
    {
        LoggerContext loggercontext = (LoggerContext)getContext();
_L5:
        if(Thread.currentThread().isInterrupted()) goto _L2; else goto _L1
_L1:
        Future future;
        connectorTask = activateConnector(createConnector(address, port, 0, reconnectionDelay));
        future = connectorTask;
        if(future != null) goto _L3; else goto _L2
_L2:
        addInfo("shutting down");
        return;
_L3:
        socket = waitForConnectorToReturnASocket();
        if(socket == null) goto _L2; else goto _L4
_L4:
        dispatchEvents(loggercontext);
          goto _L5
        InterruptedException interruptedexception;
        interruptedexception;
          goto _L2
    }

    public void setAcceptConnectionTimeout(int i)
    {
        acceptConnectionTimeout = i;
    }

    public void setPort(int i)
    {
        port = i;
    }

    public void setReconnectionDelay(int i)
    {
        reconnectionDelay = i;
    }

    public void setRemoteHost(String s)
    {
        remoteHost = s;
    }

    protected boolean shouldStart()
    {
        boolean flag = true;
        int i;
        if(port == 0)
        {
            addError("No port was configured for receiver. For more information, please visit http://logback.qos.ch/codes.html#receiver_no_port");
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if(remoteHost == null)
        {
            i++;
            addError("No host name or address was configured for receiver. For more information, please visit http://logback.qos.ch/codes.html#receiver_no_host");
        }
        if(reconnectionDelay == 0)
            reconnectionDelay = 30000;
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
            receiverId = (new StringBuilder()).append("receiver ").append(remoteHost).append(":").append(port).append(": ").toString();
        if(i != 0)
            flag = false;
        return flag;
    }

    private static final int DEFAULT_ACCEPT_CONNECTION_DELAY = 5000;
    private int acceptConnectionTimeout;
    private InetAddress address;
    private Future connectorTask;
    private int port;
    private String receiverId;
    private int reconnectionDelay;
    private String remoteHost;
    private volatile Socket socket;
}
