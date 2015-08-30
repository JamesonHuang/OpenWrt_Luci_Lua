// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duration
{

    public Duration(long l)
    {
        millis = l;
    }

    public static Duration buildByDays(double d)
    {
        return new Duration((long)(86400000D * d));
    }

    public static Duration buildByHours(double d)
    {
        return new Duration((long)(3600000D * d));
    }

    public static Duration buildByMilliseconds(double d)
    {
        return new Duration((long)d);
    }

    public static Duration buildByMinutes(double d)
    {
        return new Duration((long)(60000D * d));
    }

    public static Duration buildBySeconds(double d)
    {
        return new Duration((long)(1000D * d));
    }

    public static Duration buildUnbounded()
    {
        return new Duration(0x7fffffffffffffffL);
    }

    public static Duration valueOf(String s)
    {
        Matcher matcher = DURATION_PATTERN.matcher(s);
        if(matcher.matches())
        {
            String s1 = matcher.group(1);
            String s2 = matcher.group(3);
            double d = Double.valueOf(s1).doubleValue();
            Duration duration;
            if(s2.equalsIgnoreCase("milli") || s2.equalsIgnoreCase("millisecond") || s2.length() == 0)
                duration = buildByMilliseconds(d);
            else
            if(s2.equalsIgnoreCase("second") || s2.equalsIgnoreCase("seconde"))
                duration = buildBySeconds(d);
            else
            if(s2.equalsIgnoreCase("minute"))
                duration = buildByMinutes(d);
            else
            if(s2.equalsIgnoreCase("hour"))
                duration = buildByHours(d);
            else
            if(s2.equalsIgnoreCase("day"))
                duration = buildByDays(d);
            else
                throw new IllegalStateException((new StringBuilder()).append("Unexpected ").append(s2).toString());
            return duration;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("String value [").append(s).append("] is not in the expected format.").toString());
        }
    }

    public long getMilliseconds()
    {
        return millis;
    }

    public String toString()
    {
        String s;
        if(millis < 1000L)
            s = (new StringBuilder()).append(millis).append(" milliseconds").toString();
        else
        if(millis < 60000L)
            s = (new StringBuilder()).append(millis / 1000L).append(" seconds").toString();
        else
        if(millis < 0x36ee80L)
            s = (new StringBuilder()).append(millis / 60000L).append(" minutes").toString();
        else
            s = (new StringBuilder()).append(millis / 0x36ee80L).append(" hours").toString();
        return s;
    }

    static final long DAYS_COEFFICIENT = 0x5265c00L;
    private static final int DOUBLE_GROUP = 1;
    private static final String DOUBLE_PART = "([0-9]*(.[0-9]+)?)";
    private static final Pattern DURATION_PATTERN = Pattern.compile("([0-9]*(.[0-9]+)?)\\s*(|milli(second)?|second(e)?|minute|hour|day)s?", 2);
    static final long HOURS_COEFFICIENT = 0x36ee80L;
    static final long MINUTES_COEFFICIENT = 60000L;
    static final long SECONDS_COEFFICIENT = 1000L;
    private static final int UNIT_GROUP = 3;
    private static final String UNIT_PART = "(|milli(second)?|second(e)?|minute|hour|day)s?";
    final long millis;

}
