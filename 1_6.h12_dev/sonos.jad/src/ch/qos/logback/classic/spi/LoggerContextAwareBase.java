// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.classic.spi:
//            LoggerContextAware

public class LoggerContextAwareBase extends ContextAwareBase
    implements LoggerContextAware
{

    public LoggerContextAwareBase()
    {
    }

    public LoggerContext getLoggerContext()
    {
        return (LoggerContext)context;
    }

    public void setContext(Context context)
    {
        if((context instanceof LoggerContext) || context == null)
        {
            super.setContext(context);
            return;
        } else
        {
            throw new IllegalArgumentException("LoggerContextAwareBase only accepts contexts of type c.l.classic.LoggerContext");
        }
    }

    public void setLoggerContext(LoggerContext loggercontext)
    {
        super.setContext(loggercontext);
    }
}
