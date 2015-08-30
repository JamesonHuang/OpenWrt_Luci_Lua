// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import java.util.concurrent.ExecutorService;

public abstract class ReceiverBase extends ContextAwareBase
    implements LifeCycle
{

    public ReceiverBase()
    {
    }

    protected abstract Runnable getRunnableTask();

    public final boolean isStarted()
    {
        return started;
    }

    protected abstract void onStop();

    protected abstract boolean shouldStart();

    public final void start()
    {
        if(!isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(getContext() == null)
            throw new IllegalStateException("context not set");
        if(shouldStart())
        {
            getContext().getExecutorService().execute(getRunnableTask());
            started = true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public final void stop()
    {
        if(isStarted())
        {
            try
            {
                onStop();
            }
            catch(RuntimeException runtimeexception)
            {
                addError((new StringBuilder()).append("on stop: ").append(runtimeexception).toString(), runtimeexception);
            }
            started = false;
        }
    }

    private boolean started;
}
