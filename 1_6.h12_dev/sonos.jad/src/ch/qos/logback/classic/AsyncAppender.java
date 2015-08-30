// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AsyncAppenderBase;

// Referenced classes of package ch.qos.logback.classic:
//            Level

public class AsyncAppender extends AsyncAppenderBase
{

    public AsyncAppender()
    {
        includeCallerData = false;
    }

    protected boolean isDiscardable(ILoggingEvent iloggingevent)
    {
        boolean flag;
        if(iloggingevent.getLevel().toInt() <= 20000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected volatile boolean isDiscardable(Object obj)
    {
        return isDiscardable((ILoggingEvent)obj);
    }

    public boolean isIncludeCallerData()
    {
        return includeCallerData;
    }

    protected void preprocess(ILoggingEvent iloggingevent)
    {
        iloggingevent.prepareForDeferredProcessing();
        if(includeCallerData)
            iloggingevent.getCallerData();
    }

    protected volatile void preprocess(Object obj)
    {
        preprocess((ILoggingEvent)obj);
    }

    public void setIncludeCallerData(boolean flag)
    {
        includeCallerData = flag;
    }

    boolean includeCallerData;
}
