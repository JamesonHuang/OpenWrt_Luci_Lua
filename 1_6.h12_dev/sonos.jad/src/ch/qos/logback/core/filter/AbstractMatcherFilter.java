// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.filter;

import ch.qos.logback.core.spi.FilterReply;

// Referenced classes of package ch.qos.logback.core.filter:
//            Filter

public abstract class AbstractMatcherFilter extends Filter
{

    public AbstractMatcherFilter()
    {
        onMatch = FilterReply.NEUTRAL;
        onMismatch = FilterReply.NEUTRAL;
    }

    public final FilterReply getOnMatch()
    {
        return onMatch;
    }

    public final FilterReply getOnMismatch()
    {
        return onMismatch;
    }

    public final void setOnMatch(FilterReply filterreply)
    {
        onMatch = filterreply;
    }

    public final void setOnMismatch(FilterReply filterreply)
    {
        onMismatch = filterreply;
    }

    protected FilterReply onMatch;
    protected FilterReply onMismatch;
}
