// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class LevelConverter extends ClassicConverter
{

    public LevelConverter()
    {
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        return iloggingevent.getLevel().toString();
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }
}
