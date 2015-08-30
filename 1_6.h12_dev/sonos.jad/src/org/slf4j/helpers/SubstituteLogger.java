// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import org.slf4j.Logger;
import org.slf4j.Marker;

// Referenced classes of package org.slf4j.helpers:
//            NOPLogger

public class SubstituteLogger
    implements Logger
{

    public SubstituteLogger(String s)
    {
        name = s;
    }

    public void debug(String s)
    {
        _mthdelegate().debug(s);
    }

    public void debug(String s, Object obj)
    {
        _mthdelegate().debug(s, obj);
    }

    public void debug(String s, Object obj, Object obj1)
    {
        _mthdelegate().debug(s, obj, obj1);
    }

    public void debug(String s, Throwable throwable)
    {
        _mthdelegate().debug(s, throwable);
    }

    public transient void debug(String s, Object aobj[])
    {
        _mthdelegate().debug(s, aobj);
    }

    public void debug(Marker marker, String s)
    {
        _mthdelegate().debug(marker, s);
    }

    public void debug(Marker marker, String s, Object obj)
    {
        _mthdelegate().debug(marker, s, obj);
    }

    public void debug(Marker marker, String s, Object obj, Object obj1)
    {
        _mthdelegate().debug(marker, s, obj, obj1);
    }

    public void debug(Marker marker, String s, Throwable throwable)
    {
        _mthdelegate().debug(marker, s, throwable);
    }

    public transient void debug(Marker marker, String s, Object aobj[])
    {
        _mthdelegate().debug(marker, s, aobj);
    }

    Logger _mthdelegate()
    {
        Object obj;
        if(_delegate != null)
            obj = _delegate;
        else
            obj = NOPLogger.NOP_LOGGER;
        return ((Logger) (obj));
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null || getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            SubstituteLogger substitutelogger = (SubstituteLogger)obj;
            if(!name.equals(substitutelogger.name))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void error(String s)
    {
        _mthdelegate().error(s);
    }

    public void error(String s, Object obj)
    {
        _mthdelegate().error(s, obj);
    }

    public void error(String s, Object obj, Object obj1)
    {
        _mthdelegate().error(s, obj, obj1);
    }

    public void error(String s, Throwable throwable)
    {
        _mthdelegate().error(s, throwable);
    }

    public transient void error(String s, Object aobj[])
    {
        _mthdelegate().error(s, aobj);
    }

    public void error(Marker marker, String s)
    {
        _mthdelegate().error(marker, s);
    }

    public void error(Marker marker, String s, Object obj)
    {
        _mthdelegate().error(marker, s, obj);
    }

    public void error(Marker marker, String s, Object obj, Object obj1)
    {
        _mthdelegate().error(marker, s, obj, obj1);
    }

    public void error(Marker marker, String s, Throwable throwable)
    {
        _mthdelegate().error(marker, s, throwable);
    }

    public transient void error(Marker marker, String s, Object aobj[])
    {
        _mthdelegate().error(marker, s, aobj);
    }

    public String getName()
    {
        return name;
    }

    public int hashCode()
    {
        return name.hashCode();
    }

    public void info(String s)
    {
        _mthdelegate().info(s);
    }

    public void info(String s, Object obj)
    {
        _mthdelegate().info(s, obj);
    }

    public void info(String s, Object obj, Object obj1)
    {
        _mthdelegate().info(s, obj, obj1);
    }

    public void info(String s, Throwable throwable)
    {
        _mthdelegate().info(s, throwable);
    }

    public transient void info(String s, Object aobj[])
    {
        _mthdelegate().info(s, aobj);
    }

    public void info(Marker marker, String s)
    {
        _mthdelegate().info(marker, s);
    }

    public void info(Marker marker, String s, Object obj)
    {
        _mthdelegate().info(marker, s, obj);
    }

    public void info(Marker marker, String s, Object obj, Object obj1)
    {
        _mthdelegate().info(marker, s, obj, obj1);
    }

    public void info(Marker marker, String s, Throwable throwable)
    {
        _mthdelegate().info(marker, s, throwable);
    }

    public transient void info(Marker marker, String s, Object aobj[])
    {
        _mthdelegate().info(marker, s, aobj);
    }

    public boolean isDebugEnabled()
    {
        return _mthdelegate().isDebugEnabled();
    }

    public boolean isDebugEnabled(Marker marker)
    {
        return _mthdelegate().isDebugEnabled(marker);
    }

    public boolean isErrorEnabled()
    {
        return _mthdelegate().isErrorEnabled();
    }

    public boolean isErrorEnabled(Marker marker)
    {
        return _mthdelegate().isErrorEnabled(marker);
    }

    public boolean isInfoEnabled()
    {
        return _mthdelegate().isInfoEnabled();
    }

    public boolean isInfoEnabled(Marker marker)
    {
        return _mthdelegate().isInfoEnabled(marker);
    }

    public boolean isTraceEnabled()
    {
        return _mthdelegate().isTraceEnabled();
    }

    public boolean isTraceEnabled(Marker marker)
    {
        return _mthdelegate().isTraceEnabled(marker);
    }

    public boolean isWarnEnabled()
    {
        return _mthdelegate().isWarnEnabled();
    }

    public boolean isWarnEnabled(Marker marker)
    {
        return _mthdelegate().isWarnEnabled(marker);
    }

    public void setDelegate(Logger logger)
    {
        _delegate = logger;
    }

    public void trace(String s)
    {
        _mthdelegate().trace(s);
    }

    public void trace(String s, Object obj)
    {
        _mthdelegate().trace(s, obj);
    }

    public void trace(String s, Object obj, Object obj1)
    {
        _mthdelegate().trace(s, obj, obj1);
    }

    public void trace(String s, Throwable throwable)
    {
        _mthdelegate().trace(s, throwable);
    }

    public transient void trace(String s, Object aobj[])
    {
        _mthdelegate().trace(s, aobj);
    }

    public void trace(Marker marker, String s)
    {
        _mthdelegate().trace(marker, s);
    }

    public void trace(Marker marker, String s, Object obj)
    {
        _mthdelegate().trace(marker, s, obj);
    }

    public void trace(Marker marker, String s, Object obj, Object obj1)
    {
        _mthdelegate().trace(marker, s, obj, obj1);
    }

    public void trace(Marker marker, String s, Throwable throwable)
    {
        _mthdelegate().trace(marker, s, throwable);
    }

    public transient void trace(Marker marker, String s, Object aobj[])
    {
        _mthdelegate().trace(marker, s, aobj);
    }

    public void warn(String s)
    {
        _mthdelegate().warn(s);
    }

    public void warn(String s, Object obj)
    {
        _mthdelegate().warn(s, obj);
    }

    public void warn(String s, Object obj, Object obj1)
    {
        _mthdelegate().warn(s, obj, obj1);
    }

    public void warn(String s, Throwable throwable)
    {
        _mthdelegate().warn(s, throwable);
    }

    public transient void warn(String s, Object aobj[])
    {
        _mthdelegate().warn(s, aobj);
    }

    public void warn(Marker marker, String s)
    {
        _mthdelegate().warn(marker, s);
    }

    public void warn(Marker marker, String s, Object obj)
    {
        _mthdelegate().warn(marker, s, obj);
    }

    public void warn(Marker marker, String s, Object obj, Object obj1)
    {
        _mthdelegate().warn(marker, s, obj, obj1);
    }

    public void warn(Marker marker, String s, Throwable throwable)
    {
        _mthdelegate().warn(marker, s, throwable);
    }

    public transient void warn(Marker marker, String s, Object aobj[])
    {
        _mthdelegate().warn(marker, s, aobj);
    }

    private volatile Logger _delegate;
    private final String name;
}
