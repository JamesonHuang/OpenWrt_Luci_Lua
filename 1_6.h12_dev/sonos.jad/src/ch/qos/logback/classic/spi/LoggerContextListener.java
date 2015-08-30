// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.*;

public interface LoggerContextListener
{

    public abstract boolean isResetResistant();

    public abstract void onLevelChange(Logger logger, Level level);

    public abstract void onReset(LoggerContext loggercontext);

    public abstract void onStart(LoggerContext loggercontext);

    public abstract void onStop(LoggerContext loggercontext);
}
