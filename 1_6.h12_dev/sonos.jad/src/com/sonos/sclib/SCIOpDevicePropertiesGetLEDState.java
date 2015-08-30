// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpDevicePropertiesGetLEDState extends SCIOp
{

    protected SCIOpDevicePropertiesGetLEDState(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpDevicePropertiesGetLEDStateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpDevicePropertiesGetLEDState(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpDevicePropertiesGetLEDState(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpDevicePropertiesGetLEDState sciopdevicepropertiesgetledstate)
    {
        long l;
        if(sciopdevicepropertiesgetledstate == null)
            l = 0L;
        else
            l = sciopdevicepropertiesgetledstate.swigCPtr;
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

    public String getCurrentLEDState()
    {
        return sclibJNI.SCIOpDevicePropertiesGetLEDState_getCurrentLEDState(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpDevicePropertiesGetLEDState");
    private long swigCPtr;

}
