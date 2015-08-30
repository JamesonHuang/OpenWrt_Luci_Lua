// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.html;

import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.helpers.Transform;
import ch.qos.logback.core.html.IThrowableRenderer;

public class DefaultThrowableRenderer
    implements IThrowableRenderer
{

    public DefaultThrowableRenderer()
    {
    }

    public void printFirstLine(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy.getCommonFrames() > 0)
            stringbuilder.append("<br />").append("Caused by: ");
        stringbuilder.append(ithrowableproxy.getClassName()).append(": ").append(Transform.escapeTags(ithrowableproxy.getMessage()));
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    public void render(StringBuilder stringbuilder, ILoggingEvent iloggingevent)
    {
        IThrowableProxy ithrowableproxy = iloggingevent.getThrowableProxy();
        stringbuilder.append("<tr><td class=\"Exception\" colspan=\"6\">");
        for(; ithrowableproxy != null; ithrowableproxy = ithrowableproxy.getCause())
            render(stringbuilder, ithrowableproxy);

        stringbuilder.append("</td></tr>");
    }

    void render(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        printFirstLine(stringbuilder, ithrowableproxy);
        int i = ithrowableproxy.getCommonFrames();
        StackTraceElementProxy astacktraceelementproxy[] = ithrowableproxy.getStackTraceElementProxyArray();
        for(int j = 0; j < astacktraceelementproxy.length - i; j++)
        {
            StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[j];
            stringbuilder.append("<br />&nbsp;&nbsp;&nbsp;&nbsp;");
            stringbuilder.append(Transform.escapeTags(stacktraceelementproxy.toString()));
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        }

        if(i > 0)
        {
            stringbuilder.append("<br />&nbsp;&nbsp;&nbsp;&nbsp;");
            stringbuilder.append("\t... ").append(i).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }

    public volatile void render(StringBuilder stringbuilder, Object obj)
    {
        render(stringbuilder, (ILoggingEvent)obj);
    }

    static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";
}
