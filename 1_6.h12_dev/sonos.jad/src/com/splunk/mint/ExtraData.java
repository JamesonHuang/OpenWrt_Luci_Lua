// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.*;

public class ExtraData extends HashMap
{

    public ExtraData()
    {
    }

    public boolean addExtraData(ExtraData extradata)
    {
        boolean flag;
        if(extradata == null)
        {
            flag = false;
        } else
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = extradata.entrySet().iterator(); iterator.hasNext(); put(entry.getKey(), entry.getValue()))
                entry = (java.util.Map.Entry)iterator.next();

            flag = true;
        }
        return flag;
    }

    public boolean addExtraData(String s, Object obj)
    {
        boolean flag;
        if(s == null || obj == null)
        {
            flag = false;
        } else
        {
            put(s, obj);
            flag = true;
        }
        return flag;
    }

    public boolean addExtraDataMap(HashMap hashmap)
    {
        boolean flag;
        if(hashmap == null)
        {
            flag = false;
        } else
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext(); put(entry.getKey(), entry.getValue()))
                entry = (java.util.Map.Entry)iterator.next();

            flag = true;
        }
        return flag;
    }

    public void clearData()
    {
        clear();
    }

    public HashMap getExtraData()
    {
        return this;
    }

    public boolean removeKey(String s)
    {
        boolean flag;
        if(s != null && containsKey(s))
        {
            remove(s);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private static final long serialVersionUID = 0xcf3443817e97b27fL;
}
