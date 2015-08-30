// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI, SCISystemTime

public class SCIDateTimeSettingsProperty extends SCISettingsProperty
{

    protected SCIDateTimeSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCIDateTimeSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDateTimeSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDateTimeSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDateTimeSettingsProperty scidatetimesettingsproperty)
    {
        long l;
        if(scidatetimesettingsproperty == null)
            l = 0L;
        else
            l = scidatetimesettingsproperty.swigCPtr;
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

    public String getFormattedValue()
    {
        return sclibJNI.SCIDateTimeSettingsProperty_getFormattedValue(swigCPtr, this);
    }

    public SCISystemTime getValue()
    {
        long l = sclibJNI.SCIDateTimeSettingsProperty_getValue(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDateTimeSettingsProperty");
    private long swigCPtr;

}
