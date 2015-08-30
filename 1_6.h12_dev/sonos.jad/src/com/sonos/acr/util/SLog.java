// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.util.Log;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import java.io.File;
import org.slf4j.LoggerFactory;

public class SLog
{

    public SLog()
    {
    }

    private static void configureLogback()
    {
        hasConfiguredLogback = true;
        LoggerContext loggercontext = (LoggerContext)LoggerFactory.getILoggerFactory();
        loggercontext.reset();
        PatternLayoutEncoder patternlayoutencoder = new PatternLayoutEncoder();
        patternlayoutencoder.setContext(loggercontext);
        patternlayoutencoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        patternlayoutencoder.start();
        File file = SCLibManager.getStorageRoot(SonosApplication.getInstance());
        RollingFileAppender rollingfileappender = new RollingFileAppender();
        rollingfileappender.setContext(loggercontext);
        rollingfileappender.setFile((new StringBuilder()).append(file.getAbsolutePath()).append("/app.log").toString());
        rollingfileappender.setEncoder(patternlayoutencoder);
        rollingfileappender.setAppend(true);
        FixedWindowRollingPolicy fixedwindowrollingpolicy = new FixedWindowRollingPolicy();
        fixedwindowrollingpolicy.setMinIndex(1);
        fixedwindowrollingpolicy.setMaxIndex(1);
        fixedwindowrollingpolicy.setParent(rollingfileappender);
        fixedwindowrollingpolicy.setContext(loggercontext);
        fixedwindowrollingpolicy.setFileNamePattern((new StringBuilder()).append(file.getAbsolutePath()).append("/app.backup.%i.log").toString());
        fixedwindowrollingpolicy.start();
        SizeBasedTriggeringPolicy sizebasedtriggeringpolicy = new SizeBasedTriggeringPolicy();
        fixedwindowrollingpolicy.setContext(loggercontext);
        sizebasedtriggeringpolicy.setMaxFileSize("250KB");
        sizebasedtriggeringpolicy.start();
        rollingfileappender.setRollingPolicy(fixedwindowrollingpolicy);
        rollingfileappender.setTriggeringPolicy(sizebasedtriggeringpolicy);
        rollingfileappender.start();
        Logger logger = (Logger)LoggerFactory.getLogger("ROOT");
        logger.setLevel(Level.TRACE);
        logger.addAppender(rollingfileappender);
    }

    public static void d(String s, String s1)
    {
        log(s, 3, s1);
    }

    public static void d(String s, String s1, Throwable throwable)
    {
        log(s, 3, s1, throwable);
    }

    public static void e(String s, String s1)
    {
        log(s, 6, s1);
    }

    public static void e(String s, String s1, Throwable throwable)
    {
        log(s, 6, s1, throwable);
    }

    public static void i(String s, String s1)
    {
        log(s, 4, s1);
    }

    public static void i(String s, String s1, Throwable throwable)
    {
        log(s, 4, s1, throwable);
    }

    public static void log(String s, int j, String s1)
    {
        if(j < defaultLogLevel) goto _L2; else goto _L1
_L1:
        org.slf4j.Logger logger;
        if(!hasConfiguredLogback)
            configureLogback();
        logger = LoggerFactory.getLogger(s);
        j;
        JVM INSTR tableswitch 2 7: default 60
    //                   2 130
    //                   3 114
    //                   4 98
    //                   5 82
    //                   6 68
    //                   7 146;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        throw new IllegalArgumentException();
_L8:
        Log.e(s, s1);
        logger.error(s1);
_L2:
        return;
_L7:
        Log.w(s, s1);
        logger.warn(s1);
        continue; /* Loop/switch isn't completed */
_L6:
        Log.i(s, s1);
        logger.info(s1);
        continue; /* Loop/switch isn't completed */
_L5:
        Log.d(s, s1);
        logger.debug(s1);
        continue; /* Loop/switch isn't completed */
_L4:
        Log.v(s, s1);
        logger.trace(s1);
        continue; /* Loop/switch isn't completed */
_L9:
        Log.println(j, s, s1);
        if(true) goto _L2; else goto _L10
_L10:
    }

    public static void log(String s, int j, String s1, Throwable throwable)
    {
        if(j < defaultLogLevel) goto _L2; else goto _L1
_L1:
        org.slf4j.Logger logger;
        if(!hasConfiguredLogback)
            configureLogback();
        logger = LoggerFactory.getLogger(s);
        j;
        JVM INSTR tableswitch 2 7: default 60
    //                   2 142
    //                   3 123
    //                   4 104
    //                   5 85
    //                   6 68
    //                   7 161;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        throw new IllegalArgumentException();
_L8:
        Log.e(s, s1, throwable);
        logger.error(s1, throwable);
_L2:
        return;
_L7:
        Log.w(s, s1, throwable);
        logger.warn(s1, throwable);
        continue; /* Loop/switch isn't completed */
_L6:
        Log.i(s, s1, throwable);
        logger.info(s1, throwable);
        continue; /* Loop/switch isn't completed */
_L5:
        Log.d(s, s1, throwable);
        logger.debug(s1, throwable);
        continue; /* Loop/switch isn't completed */
_L4:
        Log.v(s, s1, throwable);
        logger.trace(s1, throwable);
        continue; /* Loop/switch isn't completed */
_L9:
        Log.println(j, s, s1);
        if(true) goto _L2; else goto _L10
_L10:
    }

    public static void setDefaultLogLevel(int j)
    {
        defaultLogLevel = j;
    }

    public static void v(String s, String s1)
    {
        log(s, 2, s1);
    }

    public static void v(String s, String s1, Throwable throwable)
    {
        log(s, 2, s1, throwable);
    }

    public static void w(String s, String s1)
    {
        log(s, 5, s1);
    }

    public static void w(String s, String s1, Throwable throwable)
    {
        log(s, 5, s1, throwable);
    }

    static int defaultLogLevel = 10;
    private static boolean hasConfiguredLogback = false;
    static int logFileMaxLines = 500;

}
