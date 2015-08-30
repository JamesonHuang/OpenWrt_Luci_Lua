// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCISettingsProperty extends SCIObj
{

    protected SCISettingsProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISettingsPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISettingsProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISettingsProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISettingsProperty scisettingsproperty)
    {
        long l;
        if(scisettingsproperty == null)
            l = 0L;
        else
            l = scisettingsproperty.swigCPtr;
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

    public long getID()
    {
        return sclibJNI.SCISettingsProperty_getID(swigCPtr, this);
    }

    public String getInterfaceID()
    {
        return sclibJNI.SCISettingsProperty_getInterfaceID(swigCPtr, this);
    }

    public String getName()
    {
        return sclibJNI.SCISettingsProperty_getName(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISettingsProperty");
    private long swigCPtr;

}
