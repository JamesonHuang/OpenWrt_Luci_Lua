// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import java.net.Socket;
import java.util.concurrent.Callable;
import javax.net.SocketFactory;

public interface SocketConnector
    extends Callable
{
    public static interface ExceptionHandler
    {

        public abstract void connectionFailed(SocketConnector socketconnector, Exception exception);
    }


    public abstract Socket call()
        throws InterruptedException;

    public abstract void setExceptionHandler(ExceptionHandler exceptionhandler);

    public abstract void setSocketFactory(SocketFactory socketfactory);
}
