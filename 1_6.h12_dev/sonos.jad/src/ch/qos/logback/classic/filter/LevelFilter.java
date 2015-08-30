// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class LevelFilter extends AbstractMatcherFilter
{

    public LevelFilter()
    {
    }

    public FilterReply decide(ILoggingEvent iloggingevent)
    {
        FilterReply filterreply;
        if(!isStarted())
            filterreply = FilterReply.NEUTRAL;
        else
        if(iloggingevent.getLevel().equals(level))
            filterreply = onMatch;
        else
            filterreply = onMismatch;
        return filterreply;
    }

    public volatile FilterReply decide(Object obj)
    {
        return decide((ILoggingEvent)obj);
    }

    public void setLevel(Level level1)
    {
        level = level1;
    }

    public void start()
    {
        if(level != null)
            super.start();
    }

    Level level;
}
