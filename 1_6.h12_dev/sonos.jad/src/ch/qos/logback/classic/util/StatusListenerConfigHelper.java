// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.OptionHelper;

public class StatusListenerConfigHelper
{

    public StatusListenerConfigHelper()
    {
    }

    private static void addStatusListener(LoggerContext loggercontext, String s)
    {
        initListener(loggercontext, createListenerPerClassName(loggercontext, s));
    }

    private static StatusListener createListenerPerClassName(LoggerContext loggercontext, String s)
    {
        StatusListener statuslistener;
        try
        {
            statuslistener = (StatusListener)OptionHelper.instantiateByClassName(s, ch/qos/logback/core/status/StatusListener, loggercontext);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            statuslistener = null;
        }
        return statuslistener;
    }

    private static void initListener(LoggerContext loggercontext, StatusListener statuslistener)
    {
        if(statuslistener != null)
        {
            if(statuslistener instanceof ContextAware)
                ((ContextAware)statuslistener).setContext(loggercontext);
            if(statuslistener instanceof LifeCycle)
                ((LifeCycle)statuslistener).start();
            loggercontext.getStatusManager().add(statuslistener);
        }
    }

    static void installIfAsked(LoggerContext loggercontext)
    {
        String s = OptionHelper.getSystemProperty("logback.statusListenerClass");
        if(!OptionHelper.isEmpty(s))
            addStatusListener(loggercontext, s);
    }
}
