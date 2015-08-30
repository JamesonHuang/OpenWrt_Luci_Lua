// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.boolex;

import ch.qos.logback.classic.spi.ILoggingEvent;

public interface IEvaluator
{

    public abstract boolean doEvaluate(ILoggingEvent iloggingevent);
}
