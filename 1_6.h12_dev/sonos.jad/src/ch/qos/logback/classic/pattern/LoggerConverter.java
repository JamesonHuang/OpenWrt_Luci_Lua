// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            NamedConverter

public class LoggerConverter extends NamedConverter
{

    public LoggerConverter()
    {
    }

    protected String getFullyQualifiedName(ILoggingEvent iloggingevent)
    {
        return iloggingevent.getLoggerName();
    }
}
