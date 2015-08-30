// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package ch.qos.logback.core.spi:
//            FilterAttachable, FilterReply

public final class FilterAttachableImpl
    implements FilterAttachable
{

    public FilterAttachableImpl()
    {
        filterList = new CopyOnWriteArrayList();
    }

    public void addFilter(Filter filter)
    {
        filterList.add(filter);
    }

    public void clearAllFilters()
    {
        filterList.clear();
    }

    public List getCopyOfAttachedFiltersList()
    {
        return new ArrayList(filterList);
    }

    public FilterReply getFilterChainDecision(Object obj)
    {
        Iterator iterator = filterList.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        FilterReply filterreply = ((Filter)iterator.next()).decide(obj);
        if(filterreply != FilterReply.DENY && filterreply != FilterReply.ACCEPT) goto _L4; else goto _L3
_L3:
        return filterreply;
_L2:
        filterreply = FilterReply.NEUTRAL;
        if(true) goto _L3; else goto _L5
_L5:
    }

    CopyOnWriteArrayList filterList;
}
