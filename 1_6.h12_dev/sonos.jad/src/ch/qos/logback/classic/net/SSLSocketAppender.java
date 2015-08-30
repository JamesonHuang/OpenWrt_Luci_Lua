// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.AbstractSSLSocketAppender;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.net.InetAddress;

// Referenced classes of package ch.qos.logback.classic.net:
//            LoggingEventPreSerializationTransformer

public class SSLSocketAppender extends AbstractSSLSocketAppender
{

    public SSLSocketAppender()
    {
        pst = new LoggingEventPreSerializationTransformer();
    }

    public SSLSocketAppender(String s, int i)
    {
        super(s, i);
        pst = new LoggingEventPreSerializationTransformer();
    }

    public SSLSocketAppender(InetAddress inetaddress, int i)
    {
        super(inetaddress.getHostAddress(), i);
        pst = new LoggingEventPreSerializationTransformer();
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

    private boolean includeCallerData;
    private final PreSerializationTransformer pst;
}
