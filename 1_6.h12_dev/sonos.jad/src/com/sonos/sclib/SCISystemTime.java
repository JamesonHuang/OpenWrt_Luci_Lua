// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCISystemTime extends SCIObj
{

    protected SCISystemTime(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISystemTimeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISystemTime(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISystemTime(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISystemTime scisystemtime)
    {
        long l;
        if(scisystemtime == null)
            l = 0L;
        else
            l = scisystemtime.swigCPtr;
        return l;
    }

    public SCISystemTime clone()
    {
        long l = sclibJNI.SCISystemTime_clone(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        swigCPtr = 0L;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDay()
    {
        return sclibJNI.SCISystemTime_getDay(swigCPtr, this);
    }

    public int getDayOfWeek()
    {
        return sclibJNI.SCISystemTime_getDayOfWeek(swigCPtr, this);
    }

    public int getDaysInMonth(int i, int j)
    {
        return sclibJNI.SCISystemTime_getDaysInMonth(swigCPtr, this, i, j);
    }

    public int getHour()
    {
        return sclibJNI.SCISystemTime_getHour(swigCPtr, this);
    }

    public int getMillisecond()
    {
        return sclibJNI.SCISystemTime_getMillisecond(swigCPtr, this);
    }

    public int getMinute()
    {
        return sclibJNI.SCISystemTime_getMinute(swigCPtr, this);
    }

    public int getMonth()
    {
        return sclibJNI.SCISystemTime_getMonth(swigCPtr, this);
    }

    public int getSecond()
    {
        return sclibJNI.SCISystemTime_getSecond(swigCPtr, this);
    }

    public int getYear()
    {
        return sclibJNI.SCISystemTime_getYear(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCISystemTime_isValid(swigCPtr, this);
    }

    public void setDay(int i)
    {
        sclibJNI.SCISystemTime_setDay(swigCPtr, this, i);
    }

    public void setDayOfWeek(int i)
    {
        sclibJNI.SCISystemTime_setDayOfWeek(swigCPtr, this, i);
    }

    public void setHour(int i)
    {
        sclibJNI.SCISystemTime_setHour(swigCPtr, this, i);
    }

    public void setMillisecond(int i)
    {
        sclibJNI.SCISystemTime_setMillisecond(swigCPtr, this, i);
    }

    public void setMinute(int i)
    {
        sclibJNI.SCISystemTime_setMinute(swigCPtr, this, i);
    }

    public void setMonth(int i)
    {
        sclibJNI.SCISystemTime_setMonth(swigCPtr, this, i);
    }

    public void setSecond(int i)
    {
        sclibJNI.SCISystemTime_setSecond(swigCPtr, this, i);
    }

    public void setYear(int i)
    {
        sclibJNI.SCISystemTime_setYear(swigCPtr, this, i);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISystemTime");
    private long swigCPtr;

}
