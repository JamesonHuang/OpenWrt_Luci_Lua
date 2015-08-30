// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.util.*;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter
    implements MDCAdapter
{

    public BasicMDCAdapter()
    {
        inheritableThreadLocal = new InheritableThreadLocal();
    }

    static boolean isJDK14()
    {
        boolean flag1 = System.getProperty("java.version").startsWith("1.4");
        boolean flag = flag1;
_L2:
        return flag;
        SecurityException securityexception;
        securityexception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void clear()
    {
        Map map = (Map)inheritableThreadLocal.get();
        if(map != null)
        {
            map.clear();
            if(isJDK14())
                inheritableThreadLocal.set(null);
            else
                inheritableThreadLocal.remove();
        }
    }

    public String get(String s)
    {
        Map map = (Map)inheritableThreadLocal.get();
        String s1;
        if(map != null && s != null)
            s1 = (String)map.get(s);
        else
            s1 = null;
        return s1;
    }

    public Map getCopyOfContextMap()
    {
        Map map;
        map = (Map)inheritableThreadLocal.get();
        if(map == null)
            break MISSING_BLOCK_LABEL_45;
        Map map1 = Collections.synchronizedMap(new HashMap());
        map;
        JVM INSTR monitorenter ;
        map1.putAll(map);
        break MISSING_BLOCK_LABEL_47;
        map1 = null;
        return map1;
    }

    public Set getKeys()
    {
        Map map = (Map)inheritableThreadLocal.get();
        Set set;
        if(map != null)
            set = map.keySet();
        else
            set = null;
        return set;
    }

    public void put(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("key cannot be null");
        Map map = (Map)inheritableThreadLocal.get();
        if(map == null)
        {
            map = Collections.synchronizedMap(new HashMap());
            inheritableThreadLocal.set(map);
        }
        map.put(s, s1);
    }

    public void remove(String s)
    {
        Map map = (Map)inheritableThreadLocal.get();
        if(map != null)
            map.remove(s);
    }

    public void setContextMap(Map map)
    {
        Map map1 = Collections.synchronizedMap(new HashMap(map));
        inheritableThreadLocal.set(map1);
    }

    static boolean IS_JDK14 = isJDK14();
    private InheritableThreadLocal inheritableThreadLocal;

}
