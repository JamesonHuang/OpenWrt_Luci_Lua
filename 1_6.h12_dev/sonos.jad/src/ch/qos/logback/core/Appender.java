// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.FilterAttachable;
import ch.qos.logback.core.spi.LifeCycle;

// Referenced classes of package ch.qos.logback.core:
//            LogbackException

public interface Appender
    extends LifeCycle, ContextAware, FilterAttachable
{

    public abstract void doAppend(Object obj)
        throws LogbackException;

    public abstract String getName();

    public abstract void setName(String s);
}
