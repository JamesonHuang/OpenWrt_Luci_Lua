// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SWIGTYPE_p_SCPtrT_SCITime_t

public class SCITime extends SCIObj
{

    protected SCITime(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCITimeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCITime(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCITime(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCITime scitime)
    {
        long l;
        if(scitime == null)
            l = 0L;
        else
            l = scitime.swigCPtr;
        return l;
    }

    public SCITime clone()
    {
        long l = sclibJNI.SCITime_clone(swigCPtr, this);
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return scitime;
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

    public boolean equalsTo(SWIGTYPE_p_SCPtrT_SCITime_t swigtype_p_scptrt_scitime_t)
    {
        return sclibJNI.SCITime_equalsTo(swigCPtr, this, SWIGTYPE_p_SCPtrT_SCITime_t.getCPtr(swigtype_p_scptrt_scitime_t));
    }

    public int get12Hour()
    {
        return sclibJNI.SCITime_get12Hour(swigCPtr, this);
    }

    public int getHour()
    {
        return sclibJNI.SCITime_getHour(swigCPtr, this);
    }

    public int getMinute()
    {
        return sclibJNI.SCITime_getMinute(swigCPtr, this);
    }

    public int getSecond()
    {
        return sclibJNI.SCITime_getSecond(swigCPtr, this);
    }

    public boolean isLessThan(SWIGTYPE_p_SCPtrT_SCITime_t swigtype_p_scptrt_scitime_t)
    {
        return sclibJNI.SCITime_isLessThan(swigCPtr, this, SWIGTYPE_p_SCPtrT_SCITime_t.getCPtr(swigtype_p_scptrt_scitime_t));
    }

    public boolean isPm()
    {
        return sclibJNI.SCITime_isPm(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCITime_isValid(swigCPtr, this);
    }

    public void setHour(int i)
    {
        sclibJNI.SCITime_setHour(swigCPtr, this, i);
    }

    public void setMinute(int i)
    {
        sclibJNI.SCITime_setMinute(swigCPtr, this, i);
    }

    public void setPm(boolean flag)
    {
        sclibJNI.SCITime_setPm(swigCPtr, this, flag);
    }

    public void setSecond(int i)
    {
        sclibJNI.SCITime_setSecond(swigCPtr, this, i);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCITime");
    private long swigCPtr;

}
