// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.core.net.ssl.*;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;

// Referenced classes of package ch.qos.logback.classic.net.server:
//            ServerSocketReceiver

public class SSLServerSocketReceiver extends ServerSocketReceiver
    implements SSLComponent
{

    public SSLServerSocketReceiver()
    {
    }

    protected ServerSocketFactory getServerSocketFactory()
        throws Exception
    {
        if(socketFactory == null)
        {
            SSLContext sslcontext = getSsl().createContext(this);
            SSLParametersConfiguration sslparametersconfiguration = getSsl().getParameters();
            sslparametersconfiguration.setContext(getContext());
            socketFactory = new ConfigurableSSLServerSocketFactory(sslparametersconfiguration, sslcontext.getServerSocketFactory());
        }
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

    private ServerSocketFactory socketFactory;
    private SSLConfiguration ssl;
}
