// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.lang.ref.WeakReference;
import java.util.*;

public class WeakMap
    implements Map
{
    protected static class WeakRefValue extends WeakReference
    {

        Object key;

        private WeakRefValue(Object obj, Object obj1)
        {
            super(obj1);
            key = obj;
        }

    }


    public WeakMap()
    {
    }

    public void clear()
    {
        baseMap.clear();
    }

    public boolean containsKey(Object obj)
    {
        return baseMap.containsKey(obj);
    }

    public boolean containsValue(Object obj)
    {
        return baseMap.containsValue(obj);
    }

    public Set entrySet()
    {
        throw new RuntimeException("WeakMap.entrySet() Not yet implemented!");
    }

    public Object get(Object obj)
    {
        WeakReference weakreference = (WeakReference)baseMap.get(obj);
        Object obj1;
        if(weakreference != null)
            obj1 = weakreference.get();
        else
            obj1 = null;
        return obj1;
    }

    public boolean isEmpty()
    {
        return baseMap.isEmpty();
    }

    public Set keySet()
    {
        return baseMap.keySet();
    }

    public Object put(Object obj, Object obj1)
    {
        Object obj2 = null;
        WeakRefValue weakrefvalue = (WeakRefValue)baseMap.put(obj, new WeakRefValue(obj, obj1));
        if(weakrefvalue != null)
            obj2 = weakrefvalue.get();
        return obj2;
    }

    public void putAll(Map map)
    {
        throw new RuntimeException("WeakMap.putAll() Not yet implemented!");
    }

    public Object remove(Object obj)
    {
        WeakReference weakreference = (WeakReference)baseMap.remove(obj);
        Object obj1;
        if(weakreference != null && weakreference.get() != null)
            obj1 = weakreference.get();
        else
            obj1 = null;
        return obj1;
    }

    public int size()
    {
        return baseMap.size();
    }

    public Collection values()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = baseMap.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WeakRefValue weakrefvalue = (WeakRefValue)iterator.next();
            if(weakrefvalue != null && weakrefvalue.get() != null)
                arraylist.add(weakrefvalue.get());
        } while(true);
        return arraylist;
    }

    private final WeakHashMap baseMap = new WeakHashMap();
}
