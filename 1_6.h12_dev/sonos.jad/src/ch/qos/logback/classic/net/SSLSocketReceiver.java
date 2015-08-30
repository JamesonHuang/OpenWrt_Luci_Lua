// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.core.net.ssl.*;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

// Referenced classes of package ch.qos.logback.classic.net:
//            SocketReceiver

public class SSLSocketReceiver extends SocketReceiver
    implements SSLComponent
{

    public SSLSocketReceiver()
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

    protected boolean shouldStart()
    {
        boolean flag1;
        SSLContext sslcontext = getSsl().createContext(this);
        SSLParametersConfiguration sslparametersconfiguration = getSsl().getParameters();
        sslparametersconfiguration.setContext(getContext());
        socketFactory = new ConfigurableSSLSocketFactory(sslparametersconfiguration, sslcontext.getSocketFactory());
        flag1 = super.shouldStart();
        boolean flag = flag1;
_L2:
        return flag;
        Exception exception;
        exception;
        addError(exception.getMessage(), exception);
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private SocketFactory socketFactory;
    private SSLConfiguration ssl;
}
