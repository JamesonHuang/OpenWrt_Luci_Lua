// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.CachingDateFormatter;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class DateConverter extends ClassicConverter
{

    public DateConverter()
    {
        lastTimestamp = -1L;
        timestampStrCache = null;
        cachingDateFormatter = null;
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        long l = iloggingevent.getTimeStamp();
        return cachingDateFormatter.format(l);
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    public void start()
    {
        String s = getFirstOption();
        if(s == null)
            s = "yyyy-MM-dd HH:mm:ss,SSS";
        String s1;
        List list;
        if(s.equals("ISO8601"))
            s1 = "yyyy-MM-dd HH:mm:ss,SSS";
        else
            s1 = s;
        try
        {
            cachingDateFormatter = new CachingDateFormatter(s1);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            addWarn((new StringBuilder()).append("Could not instantiate SimpleDateFormat with pattern ").append(s1).toString(), illegalargumentexception);
            cachingDateFormatter = new CachingDateFormatter("yyyy-MM-dd HH:mm:ss,SSS");
        }
        list = getOptionList();
        if(list != null && list.size() > 1)
        {
            TimeZone timezone = TimeZone.getTimeZone((String)list.get(1));
            cachingDateFormatter.setTimeZone(timezone);
        }
    }

    CachingDateFormatter cachingDateFormatter;
    long lastTimestamp;
    String timestampStrCache;
}
