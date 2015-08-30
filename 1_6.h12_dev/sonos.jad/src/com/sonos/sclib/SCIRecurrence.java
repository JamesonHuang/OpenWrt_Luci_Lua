// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCDayOfWeek, SCRecurrenceType

public class SCIRecurrence extends SCIObj
{

    protected SCIRecurrence(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIRecurrenceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIRecurrence(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIRecurrence(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIRecurrence scirecurrence)
    {
        long l;
        if(scirecurrence == null)
            l = 0L;
        else
            l = scirecurrence.swigCPtr;
        return l;
    }

    public boolean allDaysDisabled()
    {
        return sclibJNI.SCIRecurrence_allDaysDisabled(swigCPtr, this);
    }

    public boolean allDaysEnabled()
    {
        return sclibJNI.SCIRecurrence_allDaysEnabled(swigCPtr, this);
    }

    public SCIRecurrence clone()
    {
        long l = sclibJNI.SCIRecurrence_clone(swigCPtr, this);
        SCIRecurrence scirecurrence;
        if(l == 0L)
            scirecurrence = null;
        else
            scirecurrence = new SCIRecurrence(l, true);
        return scirecurrence;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void disableAllDays()
    {
        sclibJNI.SCIRecurrence_disableAllDays(swigCPtr, this);
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

    public void enableAllDays()
    {
        sclibJNI.SCIRecurrence_enableAllDays(swigCPtr, this);
    }

    public void enableForDayOfWeek(SCDayOfWeek scdayofweek, boolean flag)
    {
        sclibJNI.SCIRecurrence_enableForDayOfWeek(swigCPtr, this, scdayofweek.swigValue(), flag);
    }

    public SCRecurrenceType getRecurrenceType()
    {
        return SCRecurrenceType.swigToEnum(sclibJNI.SCIRecurrence_getRecurrenceType(swigCPtr, this));
    }

    public boolean isEnabledForDayOfWeek(SCDayOfWeek scdayofweek)
    {
        return sclibJNI.SCIRecurrence_isEnabledForDayOfWeek(swigCPtr, this, scdayofweek.swigValue());
    }

    public void setRecurrenceType(SCRecurrenceType screcurrencetype)
    {
        sclibJNI.SCIRecurrence_setRecurrenceType(swigCPtr, this, screcurrencetype.swigValue());
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIRecurrence");
    private long swigCPtr;

}
