// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.status:
//            StatusListener, StatusManager, Status

abstract class OnPrintStreamStatusListenerBase extends ContextAwareBase
    implements StatusListener, LifeCycle
{

    OnPrintStreamStatusListenerBase()
    {
        isStarted = false;
        retrospective = 300L;
    }

    private void print(Status status)
    {
        StringBuilder stringbuilder = new StringBuilder();
        StatusPrinter.buildStr(stringbuilder, "", status);
        getPrintStream().print(stringbuilder);
    }

    private void retrospectivePrint()
    {
        if(context != null)
        {
            long l = System.currentTimeMillis();
            Iterator iterator = context.getStatusManager().getCopyOfStatusList().iterator();
            while(iterator.hasNext()) 
            {
                Status status = (Status)iterator.next();
                if(l - status.getDate().longValue() < retrospective)
                    print(status);
            }
        }
    }

    public void addStatusEvent(Status status)
    {
        if(isStarted)
            print(status);
    }

    protected abstract PrintStream getPrintStream();

    public long getRetrospective()
    {
        return retrospective;
    }

    public boolean isStarted()
    {
        return isStarted;
    }

    public void setRetrospective(long l)
    {
        retrospective = l;
    }

    public void start()
    {
        isStarted = true;
        if(retrospective > 0L)
            retrospectivePrint();
    }

    public void stop()
    {
        isStarted = false;
    }

    static final long DEFAULT_RETROSPECTIVE = 300L;
    boolean isStarted;
    long retrospective;
}
