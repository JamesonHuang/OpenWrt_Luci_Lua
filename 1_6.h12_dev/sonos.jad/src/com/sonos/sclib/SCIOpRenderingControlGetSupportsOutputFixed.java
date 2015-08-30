// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpRenderingControlGetSupportsOutputFixed extends SCIOp
{

    protected SCIOpRenderingControlGetSupportsOutputFixed(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpRenderingControlGetSupportsOutputFixedUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpRenderingControlGetSupportsOutputFixed(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpRenderingControlGetSupportsOutputFixed(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpRenderingControlGetSupportsOutputFixed scioprenderingcontrolgetsupportsoutputfixed)
    {
        long l;
        if(scioprenderingcontrolgetsupportsoutputfixed == null)
            l = 0L;
        else
            l = scioprenderingcontrolgetsupportsoutputfixed.swigCPtr;
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

    public boolean getCurrentSupportsFixed()
    {
        return sclibJNI.SCIOpRenderingControlGetSupportsOutputFixed_getCurrentSupportsFixed(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpRenderingControlGetSupportsOutputFixed");
    private long swigCPtr;

}
