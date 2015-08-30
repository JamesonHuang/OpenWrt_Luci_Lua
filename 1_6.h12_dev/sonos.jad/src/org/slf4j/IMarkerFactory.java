// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j;


// Referenced classes of package org.slf4j:
//            Marker

public interface IMarkerFactory
{

    public abstract boolean detachMarker(String s);

    public abstract boolean exists(String s);

    public abstract Marker getDetachedMarker(String s);

    public abstract Marker getMarker(String s);
}
