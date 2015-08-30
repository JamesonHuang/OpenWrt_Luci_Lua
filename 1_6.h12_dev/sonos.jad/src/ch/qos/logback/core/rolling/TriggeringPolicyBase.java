// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TriggeringPolicy

public abstract class TriggeringPolicyBase extends ContextAwareBase
    implements TriggeringPolicy
{

    public TriggeringPolicyBase()
    {
    }

    public boolean isStarted()
    {
        return start;
    }

    public void start()
    {
        start = true;
    }

    public void stop()
    {
        start = false;
    }

    private boolean start;
}
