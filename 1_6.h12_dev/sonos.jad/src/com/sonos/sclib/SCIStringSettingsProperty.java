// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI, SCIStringInput

public class SCIStringSettingsProperty extends SCISettingsProperty
{

    protected SCIStringSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCIStringSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringSettingsProperty scistringsettingsproperty)
    {
        long l;
        if(scistringsettingsproperty == null)
            l = 0L;
        else
            l = scistringsettingsproperty.swigCPtr;
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

    public SCIStringInput getValidator()
    {
        long l = sclibJNI.SCIStringSettingsProperty_getValidator(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public String getValue()
    {
        return sclibJNI.SCIStringSettingsProperty_getValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringSettingsProperty");
    private long swigCPtr;

}
