// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import java.io.PrintStream;

// Referenced classes of package ch.qos.logback.core.status:
//            OnPrintStreamStatusListenerBase, Status

public class OnErrorConsoleStatusListener extends OnPrintStreamStatusListenerBase
{

    public OnErrorConsoleStatusListener()
    {
    }

    public volatile void addStatusEvent(Status status)
    {
        super.addStatusEvent(status);
    }

    protected PrintStream getPrintStream()
    {
        return System.err;
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
