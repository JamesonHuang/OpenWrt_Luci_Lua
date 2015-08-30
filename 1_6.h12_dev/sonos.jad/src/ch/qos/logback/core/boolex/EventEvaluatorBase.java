// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.boolex;

import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.core.boolex:
//            EventEvaluator

public abstract class EventEvaluatorBase extends ContextAwareBase
    implements EventEvaluator
{

    public EventEvaluatorBase()
    {
    }

    public String getName()
    {
        return name;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setName(String s)
    {
        if(name != null)
        {
            throw new IllegalStateException("name has been already set");
        } else
        {
            name = s;
            return;
        }
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    String name;
    boolean started;
}
