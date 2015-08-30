// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.util.*;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class TimingProfiler
{

    protected TimingProfiler()
    {
        m_timer = new Date();
        resetList();
    }

    public static TimingProfiler getInstance()
    {
        if(m_Instance == null)
            m_Instance = new TimingProfiler();
        return m_Instance;
    }

    private long getMicrosecondTime()
    {
        return System.nanoTime() / 1000L;
    }

    public void addNewTimeInterval(String s)
    {
        m_AccumulatedTimes.add(Long.valueOf(getMicrosecondTime() - m_IntervalTime));
        m_Tags.add(s);
    }

    public void dumpIntervals()
    {
        Iterator iterator = m_AccumulatedTimes.iterator();
        Iterator iterator1 = m_Tags.iterator();
        for(; iterator.hasNext(); SLog.d("PERF", (new StringBuilder()).append((String)iterator1.next()).append(iterator.next()).toString()));
    }

    public List getAccumulatedTimes()
    {
        return m_AccumulatedTimes;
    }

    public long getDuration()
    {
        return m_Duration / 1000L;
    }

    public boolean isProfiling()
    {
        boolean flag;
        if(m_StartTime != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void resetList()
    {
        m_AccumulatedTimes = new ArrayList(32);
        m_Tags = new ArrayList(32);
    }

    public void startAccumulatedInterval()
    {
        m_IntervalTime = getMicrosecondTime();
    }

    public void startProfiling()
    {
        if(!$assertionsDisabled && m_StartTime != 0L)
        {
            throw new AssertionError();
        } else
        {
            m_StartTime = getMicrosecondTime();
            return;
        }
    }

    public void stopProfiling()
    {
        if(!$assertionsDisabled && m_StartTime == 0L)
        {
            throw new AssertionError();
        } else
        {
            m_Duration = getMicrosecondTime() - m_StartTime;
            m_StartTime = 0L;
            return;
        }
    }

    static final boolean $assertionsDisabled = false;
    private static final String LOG_TAG = "PERF";
    private static TimingProfiler m_Instance = null;
    ArrayList m_AccumulatedTimes;
    long m_Duration;
    long m_IntervalTime;
    long m_StartTime;
    ArrayList m_Tags;
    int m_count;
    private Date m_timer;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/util/TimingProfiler.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
