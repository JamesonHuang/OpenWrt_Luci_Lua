// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import ch.qos.logback.core.util.DelayStrategy;
import ch.qos.logback.core.util.FixedDelay;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;

// Referenced classes of package ch.qos.logback.core.net:
//            SocketConnector

public class DefaultSocketConnector
    implements SocketConnector
{
    private static class ConsoleExceptionHandler
        implements SocketConnector.ExceptionHandler
    {

        public void connectionFailed(SocketConnector socketconnector, Exception exception)
        {
            System.out.println(exception);
        }

        private ConsoleExceptionHandler()
        {
        }

    }


    public DefaultSocketConnector(InetAddress inetaddress, int i, long l, long l1)
    {
        this(inetaddress, i, ((DelayStrategy) (new FixedDelay(l, l1))));
    }

    public DefaultSocketConnector(InetAddress inetaddress, int i, DelayStrategy delaystrategy)
    {
        address = inetaddress;
        port = i;
        delayStrategy = delaystrategy;
    }

    private Socket createSocket()
    {
        Socket socket = null;
        Socket socket1 = socketFactory.createSocket(address, port);
        socket = socket1;
_L2:
        return socket;
        IOException ioexception;
        ioexception;
        exceptionHandler.connectionFailed(this, ioexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void useDefaultsForMissingFields()
    {
        if(exceptionHandler == null)
            exceptionHandler = new ConsoleExceptionHandler();
        if(socketFactory == null)
            socketFactory = SocketFactory.getDefault();
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

    public Socket call()
        throws InterruptedException
    {
        useDefaultsForMissingFields();
        Socket socket;
        for(socket = createSocket(); socket == null && !Thread.currentThread().isInterrupted(); socket = createSocket())
            Thread.sleep(delayStrategy.nextDelay());

        return socket;
    }

    public void setExceptionHandler(SocketConnector.ExceptionHandler exceptionhandler)
    {
        exceptionHandler = exceptionhandler;
    }

    public void setSocketFactory(SocketFactory socketfactory)
    {
        socketFactory = socketfactory;
    }

    private final InetAddress address;
    private final DelayStrategy delayStrategy;
    private SocketConnector.ExceptionHandler exceptionHandler;
    private final int port;
    private SocketFactory socketFactory;
}
