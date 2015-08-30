// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network;

import java.io.Serializable;

public abstract class Metric
{

    public Metric(String s)
    {
        name = s;
    }

    public String getName()
    {
        return name;
    }

    public abstract Serializable getValue();

    private final String name;
}
