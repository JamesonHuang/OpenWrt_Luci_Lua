// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import java.util.List;

// Referenced classes of package ch.qos.logback.core:
//            Appender

public abstract class AppenderBase extends ContextAwareBase
    implements Appender
{

    public AppenderBase()
    {
        started = false;
        guard = false;
        fai = new FilterAttachableImpl();
        statusRepeatCount = 0;
        exceptionCount = 0;
    }

    public void addFilter(Filter filter)
    {
        fai.addFilter(filter);
    }

    protected abstract void append(Object obj);

    public void clearAllFilters()
    {
        fai.clearAllFilters();
    }

    /**
     * @deprecated Method doAppend is deprecated
     */

    public void doAppend(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = guard;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        guard = true;
        if(started)
            break MISSING_BLOCK_LABEL_98;
        int j = statusRepeatCount;
        statusRepeatCount = j + 1;
        if(j < 5)
            addStatus(new WarnStatus((new StringBuilder()).append("Attempted to append to non started appender [").append(name).append("].").toString(), this));
        guard = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
        FilterReply filterreply;
        FilterReply filterreply1;
        filterreply = getFilterChainDecision(obj);
        filterreply1 = FilterReply.DENY;
        if(filterreply != filterreply1)
            break MISSING_BLOCK_LABEL_125;
        guard = false;
          goto _L1
        append(obj);
        guard = false;
          goto _L1
        Exception exception2;
        exception2;
        int i = exceptionCount;
        exceptionCount = i + 1;
        if(i < 5)
            addError((new StringBuilder()).append("Appender [").append(name).append("] failed to append.").toString(), exception2);
        guard = false;
          goto _L1
        Exception exception1;
        exception1;
        guard = false;
        throw exception1;
    }

    public List getCopyOfAttachedFiltersList()
    {
        return fai.getCopyOfAttachedFiltersList();
    }

    public FilterReply getFilterChainDecision(Object obj)
    {
        return fai.getFilterChainDecision(obj);
    }

    public String getName()
    {
        return name;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("[").append(name).append("]").toString();
    }

    static final int ALLOWED_REPEATS = 5;
    private int exceptionCount;
    private FilterAttachableImpl fai;
    private boolean guard;
    protected String name;
    protected boolean started;
    private int statusRepeatCount;
}
