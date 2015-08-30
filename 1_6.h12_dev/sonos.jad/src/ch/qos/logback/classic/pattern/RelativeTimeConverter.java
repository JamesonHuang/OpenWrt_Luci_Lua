// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggerContextVO;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class RelativeTimeConverter extends ClassicConverter
{

    public RelativeTimeConverter()
    {
        lastTimestamp = -1L;
        timesmapCache = null;
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        long l = iloggingevent.getTimeStamp();
        this;
        JVM INSTR monitorenter ;
        if(l != lastTimestamp)
        {
            lastTimestamp = l;
            timesmapCache = Long.toString(l - iloggingevent.getLoggerContextVO().getBirthTime());
        }
        String s = timesmapCache;
        return s;
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    long lastTimestamp;
    String timesmapCache;
}
