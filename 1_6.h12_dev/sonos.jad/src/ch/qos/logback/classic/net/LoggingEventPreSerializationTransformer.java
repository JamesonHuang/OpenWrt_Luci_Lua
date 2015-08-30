// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.io.Serializable;

public class LoggingEventPreSerializationTransformer
    implements PreSerializationTransformer
{

    public LoggingEventPreSerializationTransformer()
    {
    }

    public Serializable transform(ILoggingEvent iloggingevent)
    {
        Object obj;
        if(iloggingevent == null)
            obj = null;
        else
        if(iloggingevent instanceof LoggingEvent)
            obj = LoggingEventVO.build(iloggingevent);
        else
        if(iloggingevent instanceof LoggingEventVO)
            obj = (LoggingEventVO)iloggingevent;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported type ").append(iloggingevent.getClass().getName()).toString());
        return ((Serializable) (obj));
    }

    public volatile Serializable transform(Object obj)
    {
        return transform((ILoggingEvent)obj);
    }
}
