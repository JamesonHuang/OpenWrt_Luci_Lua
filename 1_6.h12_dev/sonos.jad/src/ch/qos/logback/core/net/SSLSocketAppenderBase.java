// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import ch.qos.logback.core.net.ssl.ConfigurableSSLSocketFactory;
import ch.qos.logback.core.net.ssl.SSLComponent;
import ch.qos.logback.core.net.ssl.SSLConfiguration;
import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

// Referenced classes of package ch.qos.logback.core.net:
//            AbstractSocketAppender

public abstract class SSLSocketAppenderBase extends AbstractSocketAppender
    implements SSLComponent
{

    public SSLSocketAppenderBase()
    {
    }

    protected SocketFactory getSocketFactory()
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
        socketFactory = new ConfigurableSSLSocketFactory(sslparametersconfiguration, sslcontext.getSocketFactory());
        super.start();
_L1:
        return;
        Exception exception;
        exception;
        addError(exception.getMessage(), exception);
          goto _L1
    }

    private SocketFactory socketFactory;
    private SSLConfiguration ssl;
}
