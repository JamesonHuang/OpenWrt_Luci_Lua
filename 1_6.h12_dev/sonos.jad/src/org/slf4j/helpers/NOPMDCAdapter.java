// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMDCAdapter
    implements MDCAdapter
{

    public NOPMDCAdapter()
    {
    }

    public void clear()
    {
    }

    public String get(String s)
    {
        return null;
    }

    public Map getCopyOfContextMap()
    {
        return null;
    }

    public void put(String s, String s1)
    {
    }

    public void remove(String s)
    {
    }

    public void setContextMap(Map map)
    {
    }
}
