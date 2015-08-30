// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.android;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import org.slf4j.LoggerFactory;

// Referenced classes of package ch.qos.logback.classic.android:
//            LogcatAppender

public class BasicLogcatConfigurator
{

    private BasicLogcatConfigurator()
    {
    }

    public static void configure(LoggerContext loggercontext)
    {
        StatusManager statusmanager = loggercontext.getStatusManager();
        if(statusmanager != null)
            statusmanager.add(new InfoStatus("Setting up default configuration.", loggercontext));
        LogcatAppender logcatappender = new LogcatAppender();
        logcatappender.setContext(loggercontext);
        logcatappender.setName("logcat");
        PatternLayoutEncoder patternlayoutencoder = new PatternLayoutEncoder();
        patternlayoutencoder.setContext(loggercontext);
        patternlayoutencoder.setPattern("%msg");
        patternlayoutencoder.start();
        logcatappender.setEncoder(patternlayoutencoder);
        logcatappender.start();
        loggercontext.getLogger("ROOT").addAppender(logcatappender);
    }

    public static void configureDefaultContext()
    {
        configure((LoggerContext)LoggerFactory.getILoggerFactory());
    }
}
