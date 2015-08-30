// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.net.ssl.*;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;

// Referenced classes of package ch.qos.logback.core.net.server:
//            AbstractServerSocketAppender

public abstract class SSLServerSocketAppenderBase extends AbstractServerSocketAppender
    implements SSLComponent
{

    public SSLServerSocketAppenderBase()
    {
    }

    protected ServerSocketFactory getServerSocketFactory()
    {
        return socketFactory;
    }

    public SSLConfiguration getSsl()
    {
        if(ssl == null)
            ssl = new SSLConfiguration();
        return ssl;
    }

    public void setSsl(SSLConfiguration sslconfiguration)
    {
        ssl = sslconfiguration;
    }

    public void start()
    {
        SSLContext sslcontext = getSsl().createContext(this);
        SSLParametersConfiguration sslparametersconfiguration = getSsl().getParameters();
        sslparametersconfiguration.setContext(getContext());
        socketFactory = new ConfigurableSSLServerSocketFactory(sslparametersconfiguration, sslcontext.getServerSocketFactory());
        super.start();
_L1:
        return;
        Exception exception;
        exception;
        addError(exception.getMessage(), exception);
          goto _L1
    }

    private ServerSocketFactory socketFactory;
    private SSLConfiguration ssl;
}
