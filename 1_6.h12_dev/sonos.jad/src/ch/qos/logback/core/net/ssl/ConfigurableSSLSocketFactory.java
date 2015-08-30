// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import java.io.IOException;
import java.net.*;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLConfigurableSocket, SSLParametersConfiguration

public class ConfigurableSSLSocketFactory extends SocketFactory
{

    public ConfigurableSSLSocketFactory(SSLParametersConfiguration sslparametersconfiguration, SSLSocketFactory sslsocketfactory)
    {
        parameters = sslparametersconfiguration;
        _flddelegate = sslsocketfactory;
    }

    public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        SSLSocket sslsocket = (SSLSocket)_flddelegate.createSocket(s, i);
        parameters.configure(new SSLConfigurableSocket(sslsocket));
        return sslsocket;
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        SSLSocket sslsocket = (SSLSocket)_flddelegate.createSocket(s, i, inetaddress, j);
        parameters.configure(new SSLConfigurableSocket(sslsocket));
        return sslsocket;
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        SSLSocket sslsocket = (SSLSocket)_flddelegate.createSocket(inetaddress, i);
        parameters.configure(new SSLConfigurableSocket(sslsocket));
        return sslsocket;
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        SSLSocket sslsocket = (SSLSocket)_flddelegate.createSocket(inetaddress, i, inetaddress1, j);
        parameters.configure(new SSLConfigurableSocket(sslsocket));
        return sslsocket;
    }

    private final SSLSocketFactory _flddelegate;
    private final SSLParametersConfiguration parameters;
}
