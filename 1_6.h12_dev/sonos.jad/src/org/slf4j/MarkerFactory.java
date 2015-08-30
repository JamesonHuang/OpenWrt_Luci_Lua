// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

// Referenced classes of package org.slf4j:
//            IMarkerFactory, Marker

public class MarkerFactory
{

    private MarkerFactory()
    {
    }

    public static Marker getDetachedMarker(String s)
    {
        return markerFactory.getDetachedMarker(s);
    }

    public static IMarkerFactory getIMarkerFactory()
    {
        return markerFactory;
    }

    public static Marker getMarker(String s)
    {
        return markerFactory.getMarker(s);
    }

    static IMarkerFactory markerFactory = new BasicMarkerFactory();

    static 
    {
        markerFactory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
_L1:
        return;
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
          goto _L1
        Exception exception;
        exception;
        Util.report("Unexpected failure while binding MarkerFactory", exception);
          goto _L1
    }
}
