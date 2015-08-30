// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.impl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.StatusUtil;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder
    implements LoggerFactoryBinder
{

    private StaticLoggerBinder()
    {
        initialized = false;
        defaultLoggerContext = new LoggerContext();
        defaultLoggerContext.setName("default");
    }

    public static StaticLoggerBinder getSingleton()
    {
        return SINGLETON;
    }

    static void reset()
    {
        SINGLETON = new StaticLoggerBinder();
        SINGLETON.init();
    }

    public ILoggerFactory getLoggerFactory()
    {
        LoggerContext loggercontext;
        if(!initialized)
        {
            loggercontext = defaultLoggerContext;
        } else
        {
            if(contextSelectorBinder.getContextSelector() == null)
                throw new IllegalStateException("contextSelector cannot be null. See also http://logback.qos.ch/codes.html#null_CS");
            loggercontext = contextSelectorBinder.getContextSelector().getLoggerContext();
        }
        return loggercontext;
    }

    public String getLoggerFactoryClassStr()
    {
        return contextSelectorBinder.getClass().getName();
    }

    void init()
    {
        try
        {
            try
            {
                (new ContextInitializer(defaultLoggerContext)).autoConfig();
            }
            catch(JoranException joranexception)
            {
                Util.report("Failed to auto configure default logger context", joranexception);
            }
            if(!StatusUtil.contextHasStatusListener(defaultLoggerContext))
                StatusPrinter.printInCaseOfErrorsOrWarnings(defaultLoggerContext);
            contextSelectorBinder.init(defaultLoggerContext, KEY);
            initialized = true;
        }
        catch(Throwable throwable)
        {
            Util.report((new StringBuilder()).append("Failed to instantiate [").append(ch/qos/logback/classic/LoggerContext.getName()).append("]").toString(), throwable);
        }
    }

    private static Object KEY = new Object();
    static final String NULL_CS_URL = "http://logback.qos.ch/codes.html#null_CS";
    public static String REQUESTED_API_VERSION = "1.6";
    private static StaticLoggerBinder SINGLETON;
    private final ContextSelectorStaticBinder contextSelectorBinder = ContextSelectorStaticBinder.getSingleton();
    private LoggerContext defaultLoggerContext;
    private boolean initialized;

    static 
    {
        SINGLETON = new StaticLoggerBinder();
        SINGLETON.init();
    }
}
