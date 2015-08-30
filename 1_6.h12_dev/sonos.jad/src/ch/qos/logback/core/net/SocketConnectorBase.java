// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import javax.net.SocketFactory;

// Referenced classes of package ch.qos.logback.core.net:
//            SocketConnector

public class SocketConnectorBase
    implements SocketConnector
{
    private static class FixedDelay
        implements DelayStrategy
    {

        public int nextDelay()
        {
            int i = nextDelay;
            nextDelay = retryDelay;
            return i;
        }

        private int nextDelay;
        private final int retryDelay;

        public FixedDelay(int i, int j)
        {
            nextDelay = i;
            retryDelay = j;
        }
    }

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

    public static interface DelayStrategy
    {

        public abstract int nextDelay();
    }


    public SocketConnectorBase(InetAddress inetaddress, int i, int j, int k)
    {
        this(inetaddress, i, ((DelayStrategy) (new FixedDelay(j, k))));
    }

    public SocketConnectorBase(InetAddress inetaddress, int i, DelayStrategy delaystrategy)
    {
        lock = new ReentrantLock();
        connectCondition = lock.newCondition();
        address = inetaddress;
        port = i;
        delayStrategy = delaystrategy;
    }

    private void signalConnected()
    {
        lock.lock();
        connectCondition.signalAll();
        lock.unlock();
        return;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public Socket awaitConnection()
        throws InterruptedException
    {
        return awaitConnection(0x7fffffffffffffffL);
    }

    public Socket awaitConnection(long l)
        throws InterruptedException
    {
        boolean flag;
        lock.lock();
        flag = false;
_L2:
        Socket socket1;
        if(socket == null && !flag)
        {
            if(!connectCondition.await(l, TimeUnit.MILLISECONDS))
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_75;
        }
        socket1 = socket;
        lock.unlock();
        return socket1;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

    public Socket call()
        throws InterruptedException
    {
        return null;
    }

    public void run()
    {
        if(socket != null)
            throw new IllegalStateException("connector cannot be reused");
        if(exceptionHandler == null)
            exceptionHandler = new ConsoleExceptionHandler();
        if(socketFactory == null)
            socketFactory = SocketFactory.getDefault();
_L1:
        if(Thread.currentThread().isInterrupted())
            break MISSING_BLOCK_LABEL_95;
        Thread.sleep(delayStrategy.nextDelay());
        socket = socketFactory.createSocket(address, port);
        signalConnected();
_L2:
        return;
        Exception exception;
        exception;
        exceptionHandler.connectionFailed(this, exception);
          goto _L1
        InterruptedException interruptedexception;
        interruptedexception;
        exceptionHandler.connectionFailed(this, interruptedexception);
          goto _L2
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
    private final Condition connectCondition;
    private DelayStrategy delayStrategy;
    private SocketConnector.ExceptionHandler exceptionHandler;
    private final Lock lock;
    private final int port;
    private Socket socket;
    private SocketFactory socketFactory;
}
