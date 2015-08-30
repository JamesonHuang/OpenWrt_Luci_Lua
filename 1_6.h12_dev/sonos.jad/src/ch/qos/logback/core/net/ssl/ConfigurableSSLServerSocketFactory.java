// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLConfigurableServerSocket, SSLParametersConfiguration

public class ConfigurableSSLServerSocketFactory extends ServerSocketFactory
{

    public ConfigurableSSLServerSocketFactory(SSLParametersConfiguration sslparametersconfiguration, SSLServerSocketFactory sslserversocketfactory)
    {
        parameters = sslparametersconfiguration;
        _flddelegate = sslserversocketfactory;
    }

    public ServerSocket createServerSocket(int i)
        throws IOException
    {
        SSLServerSocket sslserversocket = (SSLServerSocket)_flddelegate.createServerSocket(i);
        parameters.configure(new SSLConfigurableServerSocket(sslserversocket));
        return sslserversocket;
    }

    public ServerSocket createServerSocket(int i, int j)
        throws IOException
    {
        SSLServerSocket sslserversocket = (SSLServerSocket)_flddelegate.createServerSocket(i, j);
        parameters.configure(new SSLConfigurableServerSocket(sslserversocket));
        return sslserversocket;
    }

    public ServerSocket createServerSocket(int i, int j, InetAddress inetaddress)
        throws IOException
    {
        SSLServerSocket sslserversocket = (SSLServerSocket)_flddelegate.createServerSocket(i, j, inetaddress);
        parameters.configure(new SSLConfigurableServerSocket(sslserversocket));
        return sslserversocket;
    }

    private final SSLServerSocketFactory _flddelegate;
    private final SSLParametersConfiguration parameters;
}
