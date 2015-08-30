// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.util.Duration;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package ch.qos.logback.core.sift:
//            Discriminator, AppenderTracker, AppenderFactory

public abstract class SiftingAppenderBase extends AppenderBase
{

    public SiftingAppenderBase()
    {
        timeout = new Duration(0x1b7740L);
        maxAppenderCount = 0x7fffffff;
    }

    protected void append(Object obj)
    {
        if(isStarted())
        {
            String s = discriminator.getDiscriminatingValue(obj);
            long l = getTimestamp(obj);
            Appender appender = (Appender)appenderTracker.getOrCreate(s, l);
            if(eventMarksEndOfLife(obj))
                appenderTracker.endOfLife(s);
            appenderTracker.removeStaleComponents(l);
            appender.doAppend(obj);
        }
    }

    protected abstract boolean eventMarksEndOfLife(Object obj);

    public AppenderTracker getAppenderTracker()
    {
        return appenderTracker;
    }

    public Discriminator getDiscriminator()
    {
        return discriminator;
    }

    public String getDiscriminatorKey()
    {
        String s;
        if(discriminator != null)
            s = discriminator.getKey();
        else
            s = null;
        return s;
    }

    public int getMaxAppenderCount()
    {
        return maxAppenderCount;
    }

    public Duration getTimeout()
    {
        return timeout;
    }

    protected abstract long getTimestamp(Object obj);

    public void setAppenderFactory(AppenderFactory appenderfactory)
    {
        appenderFactory = appenderfactory;
    }

    public void setDiscriminator(Discriminator discriminator1)
    {
        discriminator = discriminator1;
    }

    public void setMaxAppenderCount(int i)
    {
        maxAppenderCount = i;
    }

    public void setTimeout(Duration duration)
    {
        timeout = duration;
    }

    public void start()
    {
        int i = 0;
        if(discriminator == null)
        {
            addError("Missing discriminator. Aborting");
            i = 1;
        }
        if(!discriminator.isStarted())
        {
            addError("Discriminator has not started successfully. Aborting");
            i++;
        }
        if(appenderFactory == null)
        {
            addError("AppenderFactory has not been set. Aborting");
            i++;
        } else
        {
            appenderTracker = new AppenderTracker(context, appenderFactory);
            appenderTracker.setMaxComponents(maxAppenderCount);
            appenderTracker.setTimeout(timeout.getMilliseconds());
        }
        if(i == 0)
            super.start();
    }

    public void stop()
    {
        for(Iterator iterator = appenderTracker.allComponents().iterator(); iterator.hasNext(); ((Appender)iterator.next()).stop());
    }

    AppenderFactory appenderFactory;
    protected AppenderTracker appenderTracker;
    Discriminator discriminator;
    int maxAppenderCount;
    Duration timeout;
}
