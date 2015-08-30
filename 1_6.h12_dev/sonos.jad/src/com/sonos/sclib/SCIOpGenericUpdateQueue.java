// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpGenericUpdateQueue extends SCIOp
{

    protected SCIOpGenericUpdateQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpGenericUpdateQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpGenericUpdateQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpGenericUpdateQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpGenericUpdateQueue sciopgenericupdatequeue)
    {
        long l;
        if(sciopgenericupdatequeue == null)
            l = 0L;
        else
            l = sciopgenericupdatequeue.swigCPtr;
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

    public long getNewUpdateID()
    {
        return sclibJNI.SCIOpGenericUpdateQueue_getNewUpdateID(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpGenericUpdateQueue");
    private long swigCPtr;

}
