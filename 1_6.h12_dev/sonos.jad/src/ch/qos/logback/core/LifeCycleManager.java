// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import java.util.*;

public class LifeCycleManager
{

    public LifeCycleManager()
    {
    }

    public void register(LifeCycle lifecycle)
    {
        components.add(lifecycle);
    }

    public void reset()
    {
        Iterator iterator = components.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            LifeCycle lifecycle = (LifeCycle)iterator.next();
            if(lifecycle.isStarted())
                lifecycle.stop();
        } while(true);
        components.clear();
    }

    private final Set components = new HashSet();
}
