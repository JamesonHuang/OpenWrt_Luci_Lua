// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.log4j;

import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.helpers.Transform;
import java.util.*;

public class XMLLayout extends LayoutBase
{

    public XMLLayout()
    {
        buf = new StringBuilder(256);
        locationInfo = false;
        properties = false;
    }

    public String doLayout(ILoggingEvent iloggingevent)
    {
        IThrowableProxy ithrowableproxy;
        if(buf.capacity() > 2048)
            buf = new StringBuilder(256);
        else
            buf.setLength(0);
        buf.append("<log4j:event logger=\"");
        buf.append(iloggingevent.getLoggerName());
        buf.append("\"\r\n");
        buf.append("             timestamp=\"");
        buf.append(iloggingevent.getTimeStamp());
        buf.append("\" level=\"");
        buf.append(iloggingevent.getLevel());
        buf.append("\" thread=\"");
        buf.append(iloggingevent.getThreadName());
        buf.append("\">\r\n");
        buf.append("  <log4j:message><![CDATA[");
        Transform.appendEscapingCDATA(buf, iloggingevent.getFormattedMessage());
        buf.append("]]></log4j:message>\r\n");
        ithrowableproxy = iloggingevent.getThrowableProxy();
        if(ithrowableproxy != null)
        {
            StackTraceElementProxy astacktraceelementproxy[] = ithrowableproxy.getStackTraceElementProxyArray();
            buf.append("  <log4j:throwable><![CDATA[");
            int i = astacktraceelementproxy.length;
            for(int j = 0; j < i; j++)
            {
                StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[j];
                buf.append('\t');
                buf.append(stacktraceelementproxy.toString());
                buf.append("\r\n");
            }

            buf.append("]]></log4j:throwable>\r\n");
        }
        if(locationInfo)
        {
            StackTraceElement astacktraceelement[] = iloggingevent.getCallerData();
            if(astacktraceelement != null && astacktraceelement.length > 0)
            {
                StackTraceElement stacktraceelement = astacktraceelement[0];
                buf.append("  <log4j:locationInfo class=\"");
                buf.append(stacktraceelement.getClassName());
                buf.append("\"\r\n");
                buf.append("                      method=\"");
                buf.append(Transform.escapeTags(stacktraceelement.getMethodName()));
                buf.append("\" file=\"");
                buf.append(stacktraceelement.getFileName());
                buf.append("\" line=\"");
                buf.append(stacktraceelement.getLineNumber());
                buf.append("\"/>\r\n");
            }
        }
        if(getProperties())
        {
            Map map = iloggingevent.getMDCPropertyMap();
            if(map != null && map.size() != 0)
            {
                Set set = map.entrySet();
                buf.append("  <log4j:properties>");
                for(Iterator iterator = set.iterator(); iterator.hasNext(); buf.append(" />"))
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    buf.append("\r\n    <log4j:data");
                    buf.append((new StringBuilder()).append(" name='").append(Transform.escapeTags((String)entry.getKey())).append("'").toString());
                    buf.append((new StringBuilder()).append(" value='").append(Transform.escapeTags((String)entry.getValue())).append("'").toString());
                }

                buf.append("\r\n  </log4j:properties>");
            }
        }
        buf.append("\r\n</log4j:event>\r\n\r\n");
        return buf.toString();
    }

    public volatile String doLayout(Object obj)
    {
        return doLayout((ILoggingEvent)obj);
    }

    public String getContentType()
    {
        return "text/xml";
    }

    public boolean getLocationInfo()
    {
        return locationInfo;
    }

    public boolean getProperties()
    {
        return properties;
    }

    public void setLocationInfo(boolean flag)
    {
        locationInfo = flag;
    }

    public void setProperties(boolean flag)
    {
        properties = flag;
    }

    public void start()
    {
        super.start();
    }

    private static final int DEFAULT_SIZE = 256;
    private static final int UPPER_LIMIT = 2048;
    private StringBuilder buf;
    private boolean locationInfo;
    private boolean properties;
}
