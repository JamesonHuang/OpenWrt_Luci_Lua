// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.pattern.SyslogStartConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.util.LevelToSyslogSeverity;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.net.SyslogAppenderBase;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class SyslogAppender extends SyslogAppenderBase
{

    public SyslogAppender()
    {
        stackTraceLayout = new PatternLayout();
        stackTracePattern = "\t";
        throwableExcluded = false;
    }

    private void handleThrowableFirstLine(OutputStream outputstream, IThrowableProxy ithrowableproxy, String s, boolean flag)
        throws IOException
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(s);
        if(!flag)
            stringbuilder.append("Caused by: ");
        stringbuilder.append(ithrowableproxy.getClassName()).append(": ").append(ithrowableproxy.getMessage());
        outputstream.write(stringbuilder.toString().getBytes());
        outputstream.flush();
    }

    private void setupStackTraceLayout()
    {
        stackTraceLayout.getInstanceConverterMap().put("syslogStart", ch/qos/logback/classic/pattern/SyslogStartConverter.getName());
        stackTraceLayout.setPattern((new StringBuilder()).append(getPrefixPattern()).append(stackTracePattern).toString());
        stackTraceLayout.setContext(getContext());
        stackTraceLayout.start();
    }

    public Layout buildLayout()
    {
        PatternLayout patternlayout = new PatternLayout();
        patternlayout.getInstanceConverterMap().put("syslogStart", ch/qos/logback/classic/pattern/SyslogStartConverter.getName());
        if(suffixPattern == null)
            suffixPattern = "[%thread] %logger %msg";
        patternlayout.setPattern((new StringBuilder()).append(getPrefixPattern()).append(suffixPattern).toString());
        patternlayout.setContext(getContext());
        patternlayout.start();
        return patternlayout;
    }

    String getPrefixPattern()
    {
        return (new StringBuilder()).append("%syslogStart{").append(getFacility()).append("}%nopex{}").toString();
    }

    public int getSeverityForEvent(Object obj)
    {
        return LevelToSyslogSeverity.convert((ILoggingEvent)obj);
    }

    public String getStackTracePattern()
    {
        return stackTracePattern;
    }

    public boolean isThrowableExcluded()
    {
        return throwableExcluded;
    }

    protected void postProcess(Object obj, OutputStream outputstream)
    {
        if(!throwableExcluded) goto _L2; else goto _L1
_L1:
        return;
_L2:
        IThrowableProxy ithrowableproxy;
        String s;
        boolean flag;
        ILoggingEvent iloggingevent = (ILoggingEvent)obj;
        ithrowableproxy = iloggingevent.getThrowableProxy();
        if(ithrowableproxy == null)
            continue; /* Loop/switch isn't completed */
        s = stackTraceLayout.doLayout(iloggingevent);
        flag = true;
_L5:
        ch.qos.logback.classic.spi.StackTraceElementProxy astacktraceelementproxy[];
        if(ithrowableproxy == null)
            continue; /* Loop/switch isn't completed */
        astacktraceelementproxy = ithrowableproxy.getStackTraceElementProxyArray();
        int i;
        int j;
        handleThrowableFirstLine(outputstream, ithrowableproxy, s, flag);
        i = astacktraceelementproxy.length;
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_130;
        ch.qos.logback.classic.spi.StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[j];
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s).append(stacktraceelementproxy);
        outputstream.write(stringbuilder.toString().getBytes());
        outputstream.flush();
        j++;
          goto _L3
        ithrowableproxy = ithrowableproxy.getCause();
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        IOException ioexception;
        ioexception;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void setStackTracePattern(String s)
    {
        stackTracePattern = s;
    }

    public void setThrowableExcluded(boolean flag)
    {
        throwableExcluded = flag;
    }

    boolean stackTraceHeaderLine(StringBuilder stringbuilder, boolean flag)
    {
        return false;
    }

    public void start()
    {
        super.start();
        setupStackTraceLayout();
    }

    public static final String DEFAULT_STACKTRACE_PATTERN = "\t";
    public static final String DEFAULT_SUFFIX_PATTERN = "[%thread] %logger %msg";
    PatternLayout stackTraceLayout;
    String stackTracePattern;
    boolean throwableExcluded;
}
