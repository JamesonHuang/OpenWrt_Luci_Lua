// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import org.slf4j.LoggerFactory;

// Referenced classes of package ch.qos.logback.classic:
//            LoggerContext, Logger

public class BasicConfigurator
{

    private BasicConfigurator()
    {
    }

    public static void configure(LoggerContext loggercontext)
    {
        StatusManager statusmanager = loggercontext.getStatusManager();
        if(statusmanager != null)
            statusmanager.add(new InfoStatus("Setting up default configuration.", loggercontext));
        ConsoleAppender consoleappender = new ConsoleAppender();
        consoleappender.setContext(loggercontext);
        consoleappender.setName("console");
        PatternLayoutEncoder patternlayoutencoder = new PatternLayoutEncoder();
        patternlayoutencoder.setContext(loggercontext);
        patternlayoutencoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        patternlayoutencoder.start();
        consoleappender.setEncoder(patternlayoutencoder);
        consoleappender.start();
        loggercontext.getLogger("ROOT").addAppender(consoleappender);
    }

    public static void configureDefaultContext()
    {
        configure((LoggerContext)LoggerFactory.getILoggerFactory());
    }

    static final BasicConfigurator hiddenSingleton = new BasicConfigurator();

}
