// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.spi.LogbackLock;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.ExecutorServiceUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

// Referenced classes of package ch.qos.logback.core:
//            Context, BasicStatusManager, LifeCycleManager

public class ContextBase
    implements Context, LifeCycle
{

    public ContextBase()
    {
        birthTime = System.currentTimeMillis();
        sm = new BasicStatusManager();
        propertyMap = new HashMap();
        objectMap = new HashMap();
        configurationLock = new LogbackLock();
    }

    /**
     * @deprecated Method stopExecutorService is deprecated
     */

    private void stopExecutorService()
    {
        this;
        JVM INSTR monitorenter ;
        if(executorService != null)
        {
            ExecutorServiceUtil.shutdown(executorService);
            executorService = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public long getBirthTime()
    {
        return birthTime;
    }

    public Object getConfigurationLock()
    {
        return configurationLock;
    }

    public Map getCopyOfPropertyMap()
    {
        return new HashMap(propertyMap);
    }

    public ExecutorService getExecutorService()
    {
        if(executorService != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if(executorService == null)
            executorService = ExecutorServiceUtil.newExecutorService();
        this;
        JVM INSTR monitorexit ;
_L2:
        return executorService;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    /**
     * @deprecated Method getLifeCycleManager is deprecated
     */

    LifeCycleManager getLifeCycleManager()
    {
        this;
        JVM INSTR monitorenter ;
        LifeCycleManager lifecyclemanager;
        if(lifeCycleManager == null)
            lifeCycleManager = new LifeCycleManager();
        lifecyclemanager = lifeCycleManager;
        this;
        JVM INSTR monitorexit ;
        return lifecyclemanager;
        Exception exception;
        exception;
        throw exception;
    }

    public String getName()
    {
        return name;
    }

    public Object getObject(String s)
    {
        return objectMap.get(s);
    }

    public String getProperty(String s)
    {
        String s1;
        if("CONTEXT_NAME".equals(s))
            s1 = getName();
        else
            s1 = (String)propertyMap.get(s);
        return s1;
    }

    public StatusManager getStatusManager()
    {
        return sm;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void putObject(String s, Object obj)
    {
        objectMap.put(s, obj);
    }

    public void putProperty(String s, String s1)
    {
        propertyMap.put(s, s1);
    }

    public void register(LifeCycle lifecycle)
    {
        getLifeCycleManager().register(lifecycle);
    }

    public void reset()
    {
        getLifeCycleManager().reset();
        propertyMap.clear();
        objectMap.clear();
    }

    public void setName(String s)
        throws IllegalStateException
    {
        if(s == null || !s.equals(name))
            if(name == null || "default".equals(name))
                name = s;
            else
                throw new IllegalStateException("Context has been already given a name");
    }

    public void setStatusManager(StatusManager statusmanager)
    {
        if(statusmanager == null)
        {
            throw new IllegalArgumentException("null StatusManager not allowed");
        } else
        {
            sm = statusmanager;
            return;
        }
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        stopExecutorService();
        started = false;
    }

    public String toString()
    {
        return name;
    }

    private long birthTime;
    LogbackLock configurationLock;
    private volatile ExecutorService executorService;
    private LifeCycleManager lifeCycleManager;
    private String name;
    Map objectMap;
    Map propertyMap;
    private StatusManager sm;
    private boolean started;
}
