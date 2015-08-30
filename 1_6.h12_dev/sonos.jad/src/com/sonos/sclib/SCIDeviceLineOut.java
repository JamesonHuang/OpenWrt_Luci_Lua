// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpRenderingControlGetOutputFixed, SCIOpRenderingControlGetSupportsOutputFixed, 
//            SCIOp

public class SCIDeviceLineOut extends SCIObj
{

    protected SCIDeviceLineOut(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceLineOutUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceLineOut(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceLineOut(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceLineOut scidevicelineout)
    {
        long l;
        if(scidevicelineout == null)
            l = 0L;
        else
            l = scidevicelineout.swigCPtr;
        return l;
    }

    public SCIOpRenderingControlGetOutputFixed createGetLineOutFixed()
    {
        long l = sclibJNI.SCIDeviceLineOut_createGetLineOutFixed(swigCPtr, this);
        SCIOpRenderingControlGetOutputFixed scioprenderingcontrolgetoutputfixed;
        if(l == 0L)
            scioprenderingcontrolgetoutputfixed = null;
        else
            scioprenderingcontrolgetoutputfixed = new SCIOpRenderingControlGetOutputFixed(l, true);
        return scioprenderingcontrolgetoutputfixed;
    }

    public SCIOpRenderingControlGetSupportsOutputFixed createGetSupportsLineOutFixed()
    {
        long l = sclibJNI.SCIDeviceLineOut_createGetSupportsLineOutFixed(swigCPtr, this);
        SCIOpRenderingControlGetSupportsOutputFixed scioprenderingcontrolgetsupportsoutputfixed;
        if(l == 0L)
            scioprenderingcontrolgetsupportsoutputfixed = null;
        else
            scioprenderingcontrolgetsupportsoutputfixed = new SCIOpRenderingControlGetSupportsOutputFixed(l, true);
        return scioprenderingcontrolgetsupportsoutputfixed;
    }

    public SCIOp createSetLineOutLevelOp(boolean flag)
    {
        long l = sclibJNI.SCIDeviceLineOut_createSetLineOutLevelOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceLineOut");
    private long swigCPtr;

}
