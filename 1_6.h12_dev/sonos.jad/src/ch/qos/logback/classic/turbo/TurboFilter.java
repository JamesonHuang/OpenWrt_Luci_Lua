// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.*;
import org.slf4j.Marker;

public abstract class TurboFilter extends ContextAwareBase
    implements LifeCycle
{

    public TurboFilter()
    {
        start = false;
    }

    public abstract FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable);

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
