// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.selector;

import ch.qos.logback.classic.LoggerContext;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package ch.qos.logback.classic.selector:
//            ContextSelector

public class DefaultContextSelector
    implements ContextSelector
{

    public DefaultContextSelector(LoggerContext loggercontext)
    {
        defaultLoggerContext = loggercontext;
    }

    public LoggerContext detachLoggerContext(String s)
    {
        return defaultLoggerContext;
    }

    public List getContextNames()
    {
        String as[] = new String[1];
        as[0] = defaultLoggerContext.getName();
        return Arrays.asList(as);
    }

    public LoggerContext getDefaultLoggerContext()
    {
        return defaultLoggerContext;
    }

    public LoggerContext getLoggerContext()
    {
        return getDefaultLoggerContext();
    }

    public LoggerContext getLoggerContext(String s)
    {
        LoggerContext loggercontext;
        if(defaultLoggerContext.getName().equals(s))
            loggercontext = defaultLoggerContext;
        else
            loggercontext = null;
        return loggercontext;
    }

    private LoggerContext defaultLoggerContext;
}
