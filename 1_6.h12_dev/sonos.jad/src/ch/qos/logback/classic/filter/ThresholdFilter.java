// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class ThresholdFilter extends Filter
{

    public ThresholdFilter()
    {
    }

    public FilterReply decide(ILoggingEvent iloggingevent)
    {
        FilterReply filterreply;
        if(!isStarted())
            filterreply = FilterReply.NEUTRAL;
        else
        if(iloggingevent.getLevel().isGreaterOrEqual(level))
            filterreply = FilterReply.NEUTRAL;
        else
            filterreply = FilterReply.DENY;
        return filterreply;
    }

    public volatile FilterReply decide(Object obj)
    {
        return decide((ILoggingEvent)obj);
    }

    public void setLevel(String s)
    {
        level = Level.toLevel(s);
    }

    public void start()
    {
        if(level != null)
            super.start();
    }

    Level level;
}
