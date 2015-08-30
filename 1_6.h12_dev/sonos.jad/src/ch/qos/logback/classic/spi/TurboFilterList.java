// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Marker;

public final class TurboFilterList extends CopyOnWriteArrayList
{

    public TurboFilterList()
    {
    }

    public FilterReply getTurboFilterChainDecision(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        if(size() != 1) goto _L2; else goto _L1
_L1:
        FilterReply filterreply;
        FilterReply filterreply1;
        try
        {
            filterreply1 = ((TurboFilter)get(0)).decide(marker, logger, level, s, aobj, throwable);
        }
        catch(IndexOutOfBoundsException indexoutofboundsexception)
        {
            filterreply = FilterReply.NEUTRAL;
            continue; /* Loop/switch isn't completed */
        }
        filterreply = filterreply1;
_L4:
        return filterreply;
_L2:
        Object aobj1[] = toArray();
        int i = aobj1.length;
        for(int j = 0; j < i; j++)
        {
            filterreply = ((TurboFilter)aobj1[j]).decide(marker, logger, level, s, aobj, throwable);
            if(filterreply == FilterReply.DENY || filterreply == FilterReply.ACCEPT)
                continue; /* Loop/switch isn't completed */
        }

        filterreply = FilterReply.NEUTRAL;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final long serialVersionUID = 1L;
}
