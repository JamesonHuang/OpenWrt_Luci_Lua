// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

public interface LocationAwareLogger
    extends Logger
{

    public abstract void log(Marker marker, String s, int i, String s1, Object aobj[], Throwable throwable);

    public static final int DEBUG_INT = 10;
    public static final int ERROR_INT = 40;
    public static final int INFO_INT = 20;
    public static final int TRACE_INT = 0;
    public static final int WARN_INT = 30;
}
