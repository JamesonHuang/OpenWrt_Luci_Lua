// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network;

import java.io.Serializable;

// Referenced classes of package com.splunk.mint.network:
//            Metric

public class Timer extends Metric
{

    public Timer(String s)
    {
        super((new StringBuilder()).append(s).append("-timer").toString());
        start = null;
        end = Long.valueOf(0L);
    }

    public void done()
    {
        end = Long.valueOf(System.currentTimeMillis());
    }

    public long getStartValue()
    {
        long l;
        if(start == null)
            l = 0L;
        else
            l = start.longValue();
        return l;
    }

    public long getStopValue()
    {
        return end.longValue();
    }

    public volatile Serializable getValue()
    {
        return getValue();
    }

    public Long getValue()
    {
        Long long1;
        if(start == null)
            long1 = null;
        else
        if(end != null)
            long1 = Long.valueOf(end.longValue() - start.longValue());
        else
            long1 = Long.valueOf(System.currentTimeMillis() - start.longValue());
        return long1;
    }

    public void start()
    {
        if(start == null)
            start = Long.valueOf(System.currentTimeMillis());
    }

    private Long end;
    private Long start;
}
