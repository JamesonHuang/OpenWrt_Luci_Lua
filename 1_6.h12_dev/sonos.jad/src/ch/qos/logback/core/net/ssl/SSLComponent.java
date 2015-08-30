// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;


// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLConfiguration

public interface SSLComponent
{

    public abstract SSLConfiguration getSsl();

    public abstract void setSsl(SSLConfiguration sslconfiguration);
}
