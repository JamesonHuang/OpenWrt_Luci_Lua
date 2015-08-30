// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.boolex;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;

// Referenced classes of package ch.qos.logback.core.boolex:
//            EvaluationException

public interface EventEvaluator
    extends ContextAware, LifeCycle
{

    public abstract boolean evaluate(Object obj)
        throws NullPointerException, EvaluationException;

    public abstract String getName();

    public abstract void setName(String s);
}
