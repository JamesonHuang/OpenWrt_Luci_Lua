// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.ssl.ConfigurableSSLServerSocketFactory;
import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
import java.security.NoSuchAlgorithmException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;

// Referenced classes of package ch.qos.logback.classic.net:
//            SimpleSocketServer

public class SimpleSSLSocketServer extends SimpleSocketServer
{

    public SimpleSSLSocketServer(LoggerContext loggercontext, int i)
        throws NoSuchAlgorithmException
    {
        this(loggercontext, i, SSLContext.getDefault());
    }

    public SimpleSSLSocketServer(LoggerContext loggercontext, int i, SSLContext sslcontext)
    {
        super(loggercontext, i);
        if(sslcontext == null)
        {
            throw new NullPointerException("SSL context required");
        } else
        {
            SSLParametersConfiguration sslparametersconfiguration = new SSLParametersConfiguration();
            sslparametersconfiguration.setContext(loggercontext);
            socketFactory = new ConfigurableSSLServerSocketFactory(sslparametersconfiguration, sslcontext.getServerSocketFactory());
            return;
        }
    }

    public static void main(String args[])
        throws Exception
    {
        doMain(ch/qos/logback/classic/net/SimpleSSLSocketServer, args);
    }

    protected ServerSocketFactory getServerSocketFactory()
    {
        return socketFactory;
    }

    private final ServerSocketFactory socketFactory;
}
