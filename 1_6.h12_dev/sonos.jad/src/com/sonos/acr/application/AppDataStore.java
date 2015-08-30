// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.application;

import java.util.HashMap;
import java.util.Map;

public class AppDataStore
{

    public AppDataStore()
    {
        m_serialNum = 0L;
        m_data = new HashMap();
    }

    private long getNextSerialNum()
    {
        long l = m_serialNum;
        m_serialNum = 1L + l;
        return l;
    }

    public boolean clear(long l)
    {
        boolean flag;
        if(m_data.containsKey(Long.valueOf(l)))
        {
            m_data.remove(Long.valueOf(l));
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public Object get(long l)
    {
        return m_data.get(Long.valueOf(l));
    }

    public long put(Object obj)
    {
        long l = getNextSerialNum();
        m_data.put(Long.valueOf(l), obj);
        return l;
    }

    private Map m_data;
    private long m_serialNum;
}
