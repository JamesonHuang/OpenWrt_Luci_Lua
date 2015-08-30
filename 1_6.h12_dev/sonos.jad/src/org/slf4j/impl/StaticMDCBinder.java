// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.impl;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder
{

    private StaticMDCBinder()
    {
    }

    public MDCAdapter getMDCA()
    {
        return new LogbackMDCAdapter();
    }

    public String getMDCAdapterClassStr()
    {
        return ch/qos/logback/classic/util/LogbackMDCAdapter.getName();
    }

    public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

}
