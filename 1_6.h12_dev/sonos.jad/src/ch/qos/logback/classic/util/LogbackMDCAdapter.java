// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import java.util.*;
import org.slf4j.spi.MDCAdapter;

public final class LogbackMDCAdapter
    implements MDCAdapter
{

    public LogbackMDCAdapter()
    {
    }

    private Map duplicateAndInsertNewMap(Map map)
    {
        Map map1 = Collections.synchronizedMap(new HashMap());
        if(map == null) goto _L2; else goto _L1
_L1:
        map;
        JVM INSTR monitorenter ;
        map1.putAll(map);
        map;
        JVM INSTR monitorexit ;
_L2:
        copyOnInheritThreadLocal.set(map1);
        return map1;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private Integer getAndSetLastOperation(int i)
    {
        Integer integer = (Integer)lastOperation.get();
        lastOperation.set(Integer.valueOf(i));
        return integer;
    }

    private boolean wasLastOpReadOrNull(Integer integer)
    {
        boolean flag;
        if(integer == null || integer.intValue() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void clear()
    {
        lastOperation.set(Integer.valueOf(1));
        copyOnInheritThreadLocal.remove();
    }

    public String get(String s)
    {
        Map map = getPropertyMap();
        String s1;
        if(map != null && s != null)
            s1 = (String)map.get(s);
        else
            s1 = null;
        return s1;
    }

    public Map getCopyOfContextMap()
    {
        lastOperation.set(Integer.valueOf(2));
        Map map = (Map)copyOnInheritThreadLocal.get();
        Object obj;
        if(map == null)
            obj = null;
        else
            obj = new HashMap(map);
        return ((Map) (obj));
    }

    public Set getKeys()
    {
        Map map = getPropertyMap();
        Set set;
        if(map != null)
            set = map.keySet();
        else
            set = null;
        return set;
    }

    public Map getPropertyMap()
    {
        lastOperation.set(Integer.valueOf(2));
        return (Map)copyOnInheritThreadLocal.get();
    }

    public void put(String s, String s1)
        throws IllegalArgumentException
    {
        if(s == null)
            throw new IllegalArgumentException("key cannot be null");
        Map map = (Map)copyOnInheritThreadLocal.get();
        if(wasLastOpReadOrNull(getAndSetLastOperation(1)) || map == null)
            duplicateAndInsertNewMap(map).put(s, s1);
        else
            map.put(s, s1);
    }

    public void remove(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Map map = (Map)copyOnInheritThreadLocal.get();
        if(map != null)
            if(wasLastOpReadOrNull(getAndSetLastOperation(1)))
                duplicateAndInsertNewMap(map).remove(s);
            else
                map.remove(s);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setContextMap(Map map)
    {
        lastOperation.set(Integer.valueOf(1));
        Map map1 = Collections.synchronizedMap(new HashMap());
        map1.putAll(map);
        copyOnInheritThreadLocal.set(map1);
    }

    private static final int READ_OPERATION = 2;
    private static final int WRITE_OPERATION = 1;
    final InheritableThreadLocal copyOnInheritThreadLocal = new InheritableThreadLocal();
    final ThreadLocal lastOperation = new ThreadLocal();
}
