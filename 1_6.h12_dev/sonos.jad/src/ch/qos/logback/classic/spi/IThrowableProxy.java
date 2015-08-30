// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;


// Referenced classes of package ch.qos.logback.classic.spi:
//            StackTraceElementProxy

public interface IThrowableProxy
{

    public abstract IThrowableProxy getCause();

    public abstract String getClassName();

    public abstract int getCommonFrames();

    public abstract String getMessage();

    public abstract StackTraceElementProxy[] getStackTraceElementProxyArray();

    public abstract IThrowableProxy[] getSuppressed();
}
