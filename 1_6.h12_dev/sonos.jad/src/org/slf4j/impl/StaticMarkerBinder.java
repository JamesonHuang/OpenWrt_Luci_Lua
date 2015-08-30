// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.impl;

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

public class StaticMarkerBinder
    implements MarkerFactoryBinder
{

    private StaticMarkerBinder()
    {
    }

    public IMarkerFactory getMarkerFactory()
    {
        return markerFactory;
    }

    public String getMarkerFactoryClassStr()
    {
        return org/slf4j/helpers/BasicMarkerFactory.getName();
    }

    public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();
    final IMarkerFactory markerFactory = new BasicMarkerFactory();

}
