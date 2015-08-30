// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;

import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.core.sift:
//            Discriminator

public abstract class AbstractDiscriminator extends ContextAwareBase
    implements Discriminator
{

    public AbstractDiscriminator()
    {
    }

    public boolean isStarted()
    {
        return started;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    protected boolean started;
}
