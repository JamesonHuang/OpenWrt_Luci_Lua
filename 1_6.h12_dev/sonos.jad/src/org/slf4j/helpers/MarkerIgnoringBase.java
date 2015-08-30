// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import org.slf4j.Logger;
import org.slf4j.Marker;

// Referenced classes of package org.slf4j.helpers:
//            NamedLoggerBase

public abstract class MarkerIgnoringBase extends NamedLoggerBase
    implements Logger
{

    public MarkerIgnoringBase()
    {
    }

    public void debug(Marker marker, String s)
    {
        debug(s);
    }

    public void debug(Marker marker, String s, Object obj)
    {
        debug(s, obj);
    }

    public void debug(Marker marker, String s, Object obj, Object obj1)
    {
        debug(s, obj, obj1);
    }

    public void debug(Marker marker, String s, Throwable throwable)
    {
        debug(s, throwable);
    }

    public transient void debug(Marker marker, String s, Object aobj[])
    {
        debug(s, aobj);
    }

    public void error(Marker marker, String s)
    {
        error(s);
    }

    public void error(Marker marker, String s, Object obj)
    {
        error(s, obj);
    }

    public void error(Marker marker, String s, Object obj, Object obj1)
    {
        error(s, obj, obj1);
    }

    public void error(Marker marker, String s, Throwable throwable)
    {
        error(s, throwable);
    }

    public transient void error(Marker marker, String s, Object aobj[])
    {
        error(s, aobj);
    }

    public volatile String getName()
    {
        return super.getName();
    }

    public void info(Marker marker, String s)
    {
        info(s);
    }

    public void info(Marker marker, String s, Object obj)
    {
        info(s, obj);
    }

    public void info(Marker marker, String s, Object obj, Object obj1)
    {
        info(s, obj, obj1);
    }

    public void info(Marker marker, String s, Throwable throwable)
    {
        info(s, throwable);
    }

    public transient void info(Marker marker, String s, Object aobj[])
    {
        info(s, aobj);
    }

    public boolean isDebugEnabled(Marker marker)
    {
        return isDebugEnabled();
    }

    public boolean isErrorEnabled(Marker marker)
    {
        return isErrorEnabled();
    }

    public boolean isInfoEnabled(Marker marker)
    {
        return isInfoEnabled();
    }

    public boolean isTraceEnabled(Marker marker)
    {
        return isTraceEnabled();
    }

    public boolean isWarnEnabled(Marker marker)
    {
        return isWarnEnabled();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("(").append(getName()).append(")").toString();
    }

    public void trace(Marker marker, String s)
    {
        trace(s);
    }

    public void trace(Marker marker, String s, Object obj)
    {
        trace(s, obj);
    }

    public void trace(Marker marker, String s, Object obj, Object obj1)
    {
        trace(s, obj, obj1);
    }

    public void trace(Marker marker, String s, Throwable throwable)
    {
        trace(s, throwable);
    }

    public transient void trace(Marker marker, String s, Object aobj[])
    {
        trace(s, aobj);
    }

    public void warn(Marker marker, String s)
    {
        warn(s);
    }

    public void warn(Marker marker, String s, Object obj)
    {
        warn(s, obj);
    }

    public void warn(Marker marker, String s, Object obj, Object obj1)
    {
        warn(s, obj, obj1);
    }

    public void warn(Marker marker, String s, Throwable throwable)
    {
        warn(s, throwable);
    }

    public transient void warn(Marker marker, String s, Object aobj[])
    {
        warn(s, aobj);
    }

    private static final long serialVersionUID = 0x7d83b1554e5d279bL;
}
