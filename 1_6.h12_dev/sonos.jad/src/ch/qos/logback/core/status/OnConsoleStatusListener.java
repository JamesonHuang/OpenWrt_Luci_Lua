// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import ch.qos.logback.core.Context;
import java.io.PrintStream;

// Referenced classes of package ch.qos.logback.core.status:
//            OnPrintStreamStatusListenerBase, StatusManager, Status

public class OnConsoleStatusListener extends OnPrintStreamStatusListenerBase
{

    public OnConsoleStatusListener()
    {
    }

    public static void addNewInstanceToContext(Context context)
    {
        OnConsoleStatusListener onconsolestatuslistener = new OnConsoleStatusListener();
        onconsolestatuslistener.setContext(context);
        if(context.getStatusManager().addUniquely(onconsolestatuslistener, context))
            onconsolestatuslistener.start();
    }

    public volatile void addStatusEvent(Status status)
    {
        super.addStatusEvent(status);
    }

    protected PrintStream getPrintStream()
    {
        return System.out;
    }

    public volatile long getRetrospective()
    {
        return super.getRetrospective();
    }

    public volatile boolean isStarted()
    {
        return super.isStarted();
    }

    public volatile void setRetrospective(long l)
    {
        super.setRetrospective(l);
    }

    public volatile void start()
    {
        super.start();
    }

    public volatile void stop()
    {
        super.stop();
    }
}
