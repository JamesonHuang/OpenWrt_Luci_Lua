// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp

public class SCIScrobblingService extends SCIObj
{

    protected SCIScrobblingService(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIScrobblingServiceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIScrobblingService(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIScrobblingService(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIScrobblingService sciscrobblingservice)
    {
        long l;
        if(sciscrobblingservice == null)
            l = 0L;
        else
            l = sciscrobblingservice.swigCPtr;
        return l;
    }

    public SCIOp createEnableScrobblingOp(boolean flag)
    {
        long l = sclibJNI.SCIScrobblingService_createEnableScrobblingOp(swigCPtr, this, flag);
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

    public boolean isLoggingEnabled()
    {
        return sclibJNI.SCIScrobblingService_isLoggingEnabled(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIScrobblingService");
    private long swigCPtr;

}
