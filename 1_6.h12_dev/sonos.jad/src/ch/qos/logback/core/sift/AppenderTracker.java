// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.helpers.NOPAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import ch.qos.logback.core.spi.ContextAwareImpl;

// Referenced classes of package ch.qos.logback.core.sift:
//            AppenderFactory

public class AppenderTracker extends AbstractComponentTracker
{

    public AppenderTracker(Context context1, AppenderFactory appenderfactory)
    {
        nopaWarningCount = 0;
        context = context1;
        appenderFactory = appenderfactory;
        contextAware = new ContextAwareImpl(context1, this);
    }

    private NOPAppender buildNOPAppender(String s)
    {
        if(nopaWarningCount < 4)
        {
            nopaWarningCount = 1 + nopaWarningCount;
            contextAware.addError((new StringBuilder()).append("Building NOPAppender for discriminating value [").append(s).append("]").toString());
        }
        NOPAppender nopappender = new NOPAppender();
        nopappender.setContext(context);
        nopappender.start();
        return nopappender;
    }

    protected Appender buildComponent(String s)
    {
        Object obj = null;
        Appender appender = appenderFactory.buildAppender(context, s);
        obj = appender;
_L2:
        if(obj == null)
            obj = buildNOPAppender(s);
        return ((Appender) (obj));
        JoranException joranexception;
        joranexception;
        contextAware.addError((new StringBuilder()).append("Error while building appender with discriminating value [").append(s).append("]").toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected volatile Object buildComponent(String s)
    {
        return buildComponent(s);
    }

    protected boolean isComponentStale(Appender appender)
    {
        boolean flag;
        if(!appender.isStarted())
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected volatile boolean isComponentStale(Object obj)
    {
        return isComponentStale((Appender)obj);
    }

    protected void processPriorToRemoval(Appender appender)
    {
        appender.stop();
    }

    protected volatile void processPriorToRemoval(Object obj)
    {
        processPriorToRemoval((Appender)obj);
    }

    final AppenderFactory appenderFactory;
    final Context context;
    final ContextAwareImpl contextAware;
    int nopaWarningCount;
}
