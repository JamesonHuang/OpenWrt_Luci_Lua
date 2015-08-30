// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            MatchingFilter

public class MarkerFilter extends MatchingFilter
{

    public MarkerFilter()
    {
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply;
        if(!isStarted())
            filterreply = FilterReply.NEUTRAL;
        else
        if(marker == null)
            filterreply = onMismatch;
        else
        if(marker.contains(markerToMatch))
            filterreply = onMatch;
        else
            filterreply = onMismatch;
        return filterreply;
    }

    public void setMarker(String s)
    {
        if(s != null)
            markerToMatch = MarkerFactory.getMarker(s);
    }

    public void start()
    {
        if(markerToMatch != null)
            super.start();
        else
            addError((new StringBuilder()).append("The marker property must be set for [").append(getName()).append("]").toString());
    }

    Marker markerToMatch;
}
