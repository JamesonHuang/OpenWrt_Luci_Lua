// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.helpers.CyclicBuffer;
import ch.qos.logback.core.spi.LogbackLock;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.WarnStatus;
import java.util.*;

public class BasicStatusManager
    implements StatusManager
{

    public BasicStatusManager()
    {
        count = 0;
        level = 0;
    }

    private void fireStatusAddEvent(Status status)
    {
        LogbackLock logbacklock = statusListenerListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        for(Iterator iterator = statusListenerList.iterator(); iterator.hasNext(); ((StatusListener)iterator.next()).addStatusEvent(status));
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        throw exception;
        logbacklock;
        JVM INSTR monitorexit ;
    }

    public void add(Status status)
    {
        fireStatusAddEvent(status);
        count = 1 + count;
        if(status.getLevel() > level)
            level = status.getLevel();
        LogbackLock logbacklock = statusListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        if(statusList.size() < 150)
            statusList.add(status);
        else
            tailBuffer.add(status);
        return;
    }

    public void add(StatusListener statuslistener)
    {
        LogbackLock logbacklock = statusListenerListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        statusListenerList.add(statuslistener);
        return;
    }

    public boolean addUniquely(StatusListener statuslistener, Object obj)
    {
        Iterator iterator = getCopyOfStatusListenerList().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        StatusListener statuslistener1 = (StatusListener)iterator.next();
        if(!statuslistener1.getClass().isInstance(statuslistener)) goto _L4; else goto _L3
_L3:
        boolean flag;
        add(new WarnStatus((new StringBuilder()).append("A previous listener of type [").append(statuslistener1.getClass()).append("] has been already registered. Skipping double registration.").toString(), obj));
        flag = false;
_L6:
        return flag;
_L2:
        add(statuslistener);
        flag = true;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void clear()
    {
        LogbackLock logbacklock = statusListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        count = 0;
        statusList.clear();
        tailBuffer.clear();
        return;
    }

    public List getCopyOfStatusList()
    {
        LogbackLock logbacklock = statusListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        ArrayList arraylist = new ArrayList(statusList);
        arraylist.addAll(tailBuffer.asList());
        return arraylist;
    }

    public List getCopyOfStatusListenerList()
    {
        LogbackLock logbacklock = statusListenerListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        ArrayList arraylist = new ArrayList(statusListenerList);
        return arraylist;
    }

    public int getCount()
    {
        return count;
    }

    public int getLevel()
    {
        return level;
    }

    public void remove(StatusListener statuslistener)
    {
        LogbackLock logbacklock = statusListenerListLock;
        logbacklock;
        JVM INSTR monitorenter ;
        statusListenerList.remove(statuslistener);
        return;
    }

    public static final int MAX_HEADER_COUNT = 150;
    public static final int TAIL_SIZE = 150;
    int count;
    int level;
    protected final List statusList = new ArrayList();
    protected final LogbackLock statusListLock = new LogbackLock();
    protected final List statusListenerList = new ArrayList();
    protected final LogbackLock statusListenerListLock = new LogbackLock();
    protected final CyclicBuffer tailBuffer = new CyclicBuffer(150);
}
