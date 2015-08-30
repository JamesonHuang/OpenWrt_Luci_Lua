// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network;

import java.util.*;

// Referenced classes of package com.splunk.mint.network:
//            Metric

public class MonitorRegistry
{

    public MonitorRegistry()
    {
        metrics = Collections.synchronizedSet(new HashSet());
    }

    public void add(Metric metric)
    {
        metrics.add(metric);
    }

    /**
     * @deprecated Method getMetricsForName is deprecated
     */

    public ArrayList getMetricsForName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = new ArrayList();
        Set set = metrics;
        set;
        JVM INSTR monitorenter ;
        for(Iterator iterator = metrics.iterator(); iterator.hasNext(); iterator.remove())
        {
            Metric metric = (Metric)iterator.next();
            if(s.contains(metric.getName().substring(0, metric.getName().indexOf("-"))))
                arraylist.add(metric);
        }

        break MISSING_BLOCK_LABEL_108;
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        set;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Metric metric;
        for(Iterator iterator = metrics.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder()).append(metric.getName()).append(" = ").append(metric.getValue()).append("\n").toString()))
            metric = (Metric)iterator.next();

        return stringbuffer.toString();
    }

    private Set metrics;
}
