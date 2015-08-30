// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.spi;

import org.slf4j.IMarkerFactory;

public interface MarkerFactoryBinder
{

    public abstract IMarkerFactory getMarkerFactory();

    public abstract String getMarkerFactoryClassStr();
}
