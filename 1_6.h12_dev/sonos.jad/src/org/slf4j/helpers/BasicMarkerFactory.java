// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

// Referenced classes of package org.slf4j.helpers:
//            BasicMarker

public class BasicMarkerFactory
    implements IMarkerFactory
{

    public BasicMarkerFactory()
    {
    }

    public boolean detachMarker(String s)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(s != null && markerMap.remove(s) != null)
            flag = true;
        return flag;
    }

    public boolean exists(String s)
    {
        boolean flag;
        if(s == null)
            flag = false;
        else
            flag = markerMap.containsKey(s);
        return flag;
    }

    public Marker getDetachedMarker(String s)
    {
        return new BasicMarker(s);
    }

    public Marker getMarker(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Marker name cannot be null");
        Object obj = (Marker)markerMap.get(s);
        if(obj == null)
        {
            obj = new BasicMarker(s);
            Marker marker = (Marker)markerMap.putIfAbsent(s, obj);
            if(marker != null)
                obj = marker;
        }
        return ((Marker) (obj));
    }

    private final ConcurrentMap markerMap = new ConcurrentHashMap();
}
