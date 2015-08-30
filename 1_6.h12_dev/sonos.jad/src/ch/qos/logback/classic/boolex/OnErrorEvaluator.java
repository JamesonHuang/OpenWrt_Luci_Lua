// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.boolex;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

public class OnErrorEvaluator extends EventEvaluatorBase
{

    public OnErrorEvaluator()
    {
    }

    public boolean evaluate(ILoggingEvent iloggingevent)
        throws NullPointerException, EvaluationException
    {
        boolean flag;
        if(iloggingevent.getLevel().levelInt >= 40000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public volatile boolean evaluate(Object obj)
        throws NullPointerException, EvaluationException
    {
        return evaluate((ILoggingEvent)obj);
    }
}
