// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIOpCB extends SCIObj
{

    protected SCIOpCB(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIOpCBUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpCB(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpCB(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpCB sciopcb)
    {
        long l;
        if(sciopcb == null)
            l = 0L;
        else
            l = sciopcb.swigCPtr;
        return l;
    }

    public void _operationComplete(long l, int i)
    {
        sclibJNI.SCIOpCB__operationComplete(swigCPtr, this, l, i);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpCB");
    private long swigCPtr;

}
