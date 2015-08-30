// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.server.SSLServerSocketAppenderBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class SSLServerSocketAppender extends SSLServerSocketAppenderBase
{

    public SSLServerSocketAppender()
    {
    }

    protected PreSerializationTransformer getPST()
    {
        return pst;
    }

    public boolean isIncludeCallerData()
    {
        return includeCallerData;
    }

    protected void postProcessEvent(ILoggingEvent iloggingevent)
    {
        if(isIncludeCallerData())
            iloggingevent.getCallerData();
    }

    protected volatile void postProcessEvent(Object obj)
    {
        postProcessEvent((ILoggingEvent)obj);
    }

    public void setIncludeCallerData(boolean flag)
    {
        includeCallerData = flag;
    }

    private static final PreSerializationTransformer pst = new LoggingEventPreSerializationTransformer();
    private boolean includeCallerData;

}
