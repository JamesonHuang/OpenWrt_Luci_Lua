// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCISettingsProperty, sclibJNI, SCIEnumerator, SCIStringInput

public class SCIStringFromListSettingsProperty extends SCISettingsProperty
{

    protected SCIStringFromListSettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCISettingsProperty(sclibJNI.SWIGSCIStringFromListSettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringFromListSettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringFromListSettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringFromListSettingsProperty scistringfromlistsettingsproperty)
    {
        long l;
        if(scistringfromlistsettingsproperty == null)
            l = 0L;
        else
            l = scistringfromlistsettingsproperty.swigCPtr;
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
        long l = sclibJNI.SCIStringFromListSettingsProperty_getPredefinedValues(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getPrompt()
    {
        return sclibJNI.SCIStringFromListSettingsProperty_getPrompt(swigCPtr, this);
    }

    public String getResourceID()
    {
        return sclibJNI.SCIStringFromListSettingsProperty_getResourceID(swigCPtr, this);
    }

    public SCIStringInput getValidator()
    {
        long l = sclibJNI.SCIStringFromListSettingsProperty_getValidator(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public String getValue()
    {
        return sclibJNI.SCIStringFromListSettingsProperty_getValue(swigCPtr, this);
    }

    public boolean predefinedValueRequired()
    {
        return sclibJNI.SCIStringFromListSettingsProperty_predefinedValueRequired(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringFromListSettingsProperty");
    private long swigCPtr;

}
