// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.spi:
//            FilterReply

public interface FilterAttachable
{

    public abstract void addFilter(Filter filter);

    public abstract void clearAllFilters();

    public abstract List getCopyOfAttachedFiltersList();

    public abstract FilterReply getFilterChainDecision(Object obj);
}
