// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.io.Serializable;
import java.util.*;

public class WeakHashSet extends AbstractSet
    implements Set, Cloneable, Serializable
{

    public WeakHashSet()
    {
        weakHashMap = new WeakHashMap();
    }

    private WeakHashSet(WeakHashMap weakhashmap)
    {
        weakHashMap = weakhashmap;
    }

    public boolean add(Object obj)
    {
        boolean flag;
        if(obj != null && !weakHashMap.containsKey(obj))
        {
            weakHashMap.put(obj, null);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean contains(Object obj)
    {
        return weakHashMap.containsKey(obj);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null || getClass() != obj.getClass())
            flag = false;
        else
        if(!super.equals(obj))
        {
            flag = false;
        } else
        {
            WeakHashSet weakhashset = (WeakHashSet)obj;
            if(!weakHashMap.equals(weakhashset.weakHashMap))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int hashCode()
    {
        return 31 * super.hashCode() + weakHashMap.hashCode();
    }

    public Iterator iterator()
    {
        return (new ArrayList(weakHashMap.keySet())).iterator();
    }

    public boolean remove(Object obj)
    {
        boolean flag;
        if(weakHashMap.containsKey(obj))
        {
            weakHashMap.remove(obj);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int size()
    {
        return weakHashMap.size();
    }

    private final WeakHashMap weakHashMap;
}
