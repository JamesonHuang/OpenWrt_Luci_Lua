// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.Discriminator;
import ch.qos.logback.core.sift.SiftingAppenderBase;
import org.slf4j.Marker;

public class SiftingAppender extends SiftingAppenderBase
{

    public SiftingAppender()
    {
    }

    protected boolean eventMarksEndOfLife(ILoggingEvent iloggingevent)
    {
        Marker marker = iloggingevent.getMarker();
        boolean flag;
        if(marker == null)
            flag = false;
        else
            flag = marker.contains(ClassicConstants.FINALIZE_SESSION_MARKER);
        return flag;
    }

    protected volatile boolean eventMarksEndOfLife(Object obj)
    {
        return eventMarksEndOfLife((ILoggingEvent)obj);
    }

    protected long getTimestamp(ILoggingEvent iloggingevent)
    {
        return iloggingevent.getTimeStamp();
    }

    protected volatile long getTimestamp(Object obj)
    {
        return getTimestamp((ILoggingEvent)obj);
    }

    public void setDiscriminator(Discriminator discriminator)
    {
        super.setDiscriminator(discriminator);
    }
}
