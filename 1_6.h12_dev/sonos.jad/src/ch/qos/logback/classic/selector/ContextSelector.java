// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.selector;

import ch.qos.logback.classic.LoggerContext;
import java.util.List;

public interface ContextSelector
{

    public abstract LoggerContext detachLoggerContext(String s);

    public abstract List getContextNames();

    public abstract LoggerContext getDefaultLoggerContext();

    public abstract LoggerContext getLoggerContext();

    public abstract LoggerContext getLoggerContext(String s);
}
