// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpCB

public class SCIOp extends SCIObj
{

    protected SCIOp(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIOpUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOp(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOp(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOp sciop)
    {
        long l;
        if(sciop == null)
            l = 0L;
        else
            l = sciop.swigCPtr;
        return l;
    }

    public void _cancel()
    {
        sclibJNI.SCIOp__cancel(swigCPtr, this);
    }

    public long _start(SCIOpCB sciopcb)
    {
        return sclibJNI.SCIOp__start(swigCPtr, this, SCIOpCB.getCPtr(sciopcb), sciopcb);
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

    public int getOpResult()
    {
        return sclibJNI.SCIOp_getOpResult(swigCPtr, this);
    }

    public String getResultString()
    {
        return sclibJNI.SCIOp_getResultString(swigCPtr, this);
    }

    public boolean isRunning()
    {
        return sclibJNI.SCIOp_isRunning(swigCPtr, this);
    }

    public long serialNumber()
    {
        return sclibJNI.SCIOp_serialNumber(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOp");
    private long swigCPtr;

}
