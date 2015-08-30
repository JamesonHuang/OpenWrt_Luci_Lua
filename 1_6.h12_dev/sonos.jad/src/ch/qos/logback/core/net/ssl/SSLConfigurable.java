// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;


public interface SSLConfigurable
{

    public abstract String[] getDefaultCipherSuites();

    public abstract String[] getDefaultProtocols();

    public abstract String[] getSupportedCipherSuites();

    public abstract String[] getSupportedProtocols();

    public abstract void setEnabledCipherSuites(String as[]);

    public abstract void setEnabledProtocols(String as[]);

    public abstract void setNeedClientAuth(boolean flag);

    public abstract void setWantClientAuth(boolean flag);
}
