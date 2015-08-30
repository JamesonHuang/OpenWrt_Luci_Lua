// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import javax.net.ssl.SSLServerSocket;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLConfigurable

public class SSLConfigurableServerSocket
    implements SSLConfigurable
{

    public SSLConfigurableServerSocket(SSLServerSocket sslserversocket)
    {
        _flddelegate = sslserversocket;
    }

    public String[] getDefaultCipherSuites()
    {
        return _flddelegate.getEnabledCipherSuites();
    }

    public String[] getDefaultProtocols()
    {
        return _flddelegate.getEnabledProtocols();
    }

    public String[] getSupportedCipherSuites()
    {
        return _flddelegate.getSupportedCipherSuites();
    }

    public String[] getSupportedProtocols()
    {
        return _flddelegate.getSupportedProtocols();
    }

    public void setEnabledCipherSuites(String as[])
    {
        _flddelegate.setEnabledCipherSuites(as);
    }

    public void setEnabledProtocols(String as[])
    {
        _flddelegate.setEnabledProtocols(as);
    }

    public void setNeedClientAuth(boolean flag)
    {
        _flddelegate.setNeedClientAuth(flag);
    }

    public void setWantClientAuth(boolean flag)
    {
        _flddelegate.setWantClientAuth(flag);
    }

    private final SSLServerSocket _flddelegate;
}
