// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI

public class SCIIntegerSettingsProperty extends SCISettingsProperty
{

    protected SCIIntegerSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCIIntegerSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIIntegerSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIIntegerSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIIntegerSettingsProperty sciintegersettingsproperty)
    {
        long l;
        if(sciintegersettingsproperty == null)
            l = 0L;
        else
            l = sciintegersettingsproperty.swigCPtr;
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

    public int getMaxValue()
    {
        return sclibJNI.SCIIntegerSettingsProperty_getMaxValue(swigCPtr, this);
    }

    public int getMinValue()
    {
        return sclibJNI.SCIIntegerSettingsProperty_getMinValue(swigCPtr, this);
    }

    public int getValue()
    {
        return sclibJNI.SCIIntegerSettingsProperty_getValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIIntegerSettingsProperty");
    private long swigCPtr;

}
