// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.*;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ThrowableProxyConverter

public class ExtendedThrowableProxyConverter extends ThrowableProxyConverter
{

    public ExtendedThrowableProxyConverter()
    {
    }

    protected void extraData(StringBuilder stringbuilder, StackTraceElementProxy stacktraceelementproxy)
    {
        ThrowableProxyUtil.subjoinPackagingData(stringbuilder, stacktraceelementproxy);
    }

    protected void prepareLoggingEvent(ILoggingEvent iloggingevent)
    {
    }
}
