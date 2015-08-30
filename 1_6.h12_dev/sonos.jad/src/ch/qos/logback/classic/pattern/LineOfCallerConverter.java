// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class LineOfCallerConverter extends ClassicConverter
{

    public LineOfCallerConverter()
    {
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        StackTraceElement astacktraceelement[] = iloggingevent.getCallerData();
        String s;
        if(astacktraceelement != null && astacktraceelement.length > 0)
            s = Integer.toString(astacktraceelement[0].getLineNumber());
        else
            s = "?";
        return s;
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }
}
