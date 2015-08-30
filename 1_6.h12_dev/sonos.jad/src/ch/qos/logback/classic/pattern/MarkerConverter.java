// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class MarkerConverter extends ClassicConverter
{

    public MarkerConverter()
    {
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        org.slf4j.Marker marker = iloggingevent.getMarker();
        String s;
        if(marker == null)
            s = EMPTY;
        else
            s = marker.toString();
        return s;
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    private static String EMPTY = "";

}
