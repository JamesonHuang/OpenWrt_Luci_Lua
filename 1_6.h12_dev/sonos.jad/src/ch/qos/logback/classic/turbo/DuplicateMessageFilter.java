// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            TurboFilter, LRUMessageCache

public class DuplicateMessageFilter extends TurboFilter
{

    public DuplicateMessageFilter()
    {
        allowedRepetitions = 5;
        cacheSize = 100;
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply;
        if(msgCache.getMessageCountAndThenIncrement(s) <= allowedRepetitions)
            filterreply = FilterReply.NEUTRAL;
        else
            filterreply = FilterReply.DENY;
        return filterreply;
    }

    public int getAllowedRepetitions()
    {
        return allowedRepetitions;
    }

    public int getCacheSize()
    {
        return cacheSize;
    }

    public void setAllowedRepetitions(int i)
    {
        allowedRepetitions = i;
    }

    public void setCacheSize(int i)
    {
        cacheSize = i;
    }

    public void start()
    {
        msgCache = new LRUMessageCache(cacheSize);
        super.start();
    }

    public void stop()
    {
        msgCache.clear();
        msgCache = null;
        super.stop();
    }

    public static final int DEFAULT_ALLOWED_REPETITIONS = 5;
    public static final int DEFAULT_CACHE_SIZE = 100;
    public int allowedRepetitions;
    public int cacheSize;
    private LRUMessageCache msgCache;
}
