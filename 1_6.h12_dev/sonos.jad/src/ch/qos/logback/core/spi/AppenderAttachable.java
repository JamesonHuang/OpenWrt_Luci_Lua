// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.Appender;
import java.util.Iterator;

public interface AppenderAttachable
{

    public abstract void addAppender(Appender appender);

    public abstract void detachAndStopAllAppenders();

    public abstract boolean detachAppender(Appender appender);

    public abstract boolean detachAppender(String s);

    public abstract Appender getAppender(String s);

    public abstract boolean isAttached(Appender appender);

    public abstract Iterator iteratorForAppenders();
}
