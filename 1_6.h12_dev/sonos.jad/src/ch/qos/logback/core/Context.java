// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.status.StatusManager;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public interface Context
    extends PropertyContainer
{

    public abstract long getBirthTime();

    public abstract Object getConfigurationLock();

    public abstract Map getCopyOfPropertyMap();

    public abstract ExecutorService getExecutorService();

    public abstract String getName();

    public abstract Object getObject(String s);

    public abstract String getProperty(String s);

    public abstract StatusManager getStatusManager();

    public abstract void putObject(String s, Object obj);

    public abstract void putProperty(String s, String s1);

    public abstract void register(LifeCycle lifecycle);

    public abstract void setName(String s);
}
