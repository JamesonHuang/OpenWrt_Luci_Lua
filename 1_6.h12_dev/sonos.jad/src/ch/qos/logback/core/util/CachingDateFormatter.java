// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CachingDateFormatter
{

    public CachingDateFormatter(String s)
    {
        lastTimestamp = -1L;
        cachedStr = null;
        sdf = new SimpleDateFormat(s);
    }

    public final String format(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(l != lastTimestamp)
        {
            lastTimestamp = l;
            cachedStr = sdf.format(new Date(l));
        }
        String s = cachedStr;
        return s;
    }

    public void setTimeZone(TimeZone timezone)
    {
        sdf.setTimeZone(timezone);
    }

    String cachedStr;
    long lastTimestamp;
    final SimpleDateFormat sdf;
}
