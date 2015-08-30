// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.AbstractSocketAppender;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.net.InetAddress;

// Referenced classes of package ch.qos.logback.classic.net:
//            LoggingEventPreSerializationTransformer

public class SocketAppender extends AbstractSocketAppender
{

    public SocketAppender()
    {
        includeCallerData = false;
    }

    public SocketAppender(String s, int i)
    {
        super(s, i);
        includeCallerData = false;
    }

    public SocketAppender(InetAddress inetaddress, int i)
    {
        super(inetaddress.getHostAddress(), i);
        includeCallerData = false;
    }

    public PreSerializationTransformer getPST()
    {
        return pst;
    }

    protected void postProcessEvent(ILoggingEvent iloggingevent)
    {
        if(includeCallerData)
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
