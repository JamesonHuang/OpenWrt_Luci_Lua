// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.splunk.mint.network:
//            Metric

public class Counter extends Metric
{

    public Counter(String s)
    {
        super(s);
        count = new AtomicLong();
    }

    public void dec()
    {
        count.decrementAndGet();
    }

    public void dec(long l)
    {
        count.getAndAdd(-l);
    }

    public volatile Serializable getValue()
    {
        return getValue();
    }

    public Long getValue()
    {
        return Long.valueOf(count.get());
    }

    public void inc()
    {
        count.incrementAndGet();
    }

    public void inc(long l)
    {
        count.addAndGet(l);
    }

    private AtomicLong count;
}
