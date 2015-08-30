// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI

public class SCISpinnerSettingsProperty extends SCISettingsProperty
{

    protected SCISpinnerSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCISpinnerSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISpinnerSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISpinnerSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISpinnerSettingsProperty scispinnersettingsproperty)
    {
        long l;
        if(scispinnersettingsproperty == null)
            l = 0L;
        else
            l = scispinnersettingsproperty.swigCPtr;
        return l;
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

    public String getValue()
    {
        return sclibJNI.SCISpinnerSettingsProperty_getValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISpinnerSettingsProperty");
    private long swigCPtr;

}
