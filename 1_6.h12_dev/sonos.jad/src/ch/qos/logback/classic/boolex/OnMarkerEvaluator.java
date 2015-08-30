// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.boolex;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;
import java.util.*;
import org.slf4j.Marker;

public class OnMarkerEvaluator extends EventEvaluatorBase
{

    public OnMarkerEvaluator()
    {
        markerList = new ArrayList();
    }

    public void addMarker(String s)
    {
        markerList.add(s);
    }

    public boolean evaluate(ILoggingEvent iloggingevent)
        throws NullPointerException, EvaluationException
    {
        Marker marker = iloggingevent.getMarker();
        if(marker != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        for(Iterator iterator = markerList.iterator(); iterator.hasNext();)
            if(marker.contains((String)iterator.next()))
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }

        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public volatile boolean evaluate(Object obj)
        throws NullPointerException, EvaluationException
    {
        return evaluate((ILoggingEvent)obj);
    }

    List markerList;
}
