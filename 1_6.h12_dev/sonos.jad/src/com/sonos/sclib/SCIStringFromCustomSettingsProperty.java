// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI, SCIEnumerator

public class SCIStringFromCustomSettingsProperty extends SCISettingsProperty
{

    protected SCIStringFromCustomSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCIStringFromCustomSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringFromCustomSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringFromCustomSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringFromCustomSettingsProperty scistringfromcustomsettingsproperty)
    {
        long l;
        if(scistringfromcustomsettingsproperty == null)
            l = 0L;
        else
            l = scistringfromcustomsettingsproperty.swigCPtr;
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

    public SCIEnumerator getPredefinedValues()
    {
        long l = sclibJNI.SCIStringFromCustomSettingsProperty_getPredefinedValues(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getResourceID()
    {
        return sclibJNI.SCIStringFromCustomSettingsProperty_getResourceID(swigCPtr, this);
    }

    public String getValue()
    {
        return sclibJNI.SCIStringFromCustomSettingsProperty_getValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringFromCustomSettingsProperty");
    private long swigCPtr;

}
