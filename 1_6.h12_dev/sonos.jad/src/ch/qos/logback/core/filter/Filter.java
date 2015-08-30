// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.filter;

import ch.qos.logback.core.spi.*;

public abstract class Filter extends ContextAwareBase
    implements LifeCycle
{

    public Filter()
    {
        start = false;
    }

    public abstract FilterReply decide(Object obj);

    public String getName()
    {
        return name;
    }

    public boolean isStarted()
    {
        return start;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void start()
    {
        start = true;
    }

    public void stop()
    {
        start = false;
    }

    private String name;
    boolean start;
}
