// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI, SCIPlayQueue

public class SCIOpAttachPrivateQueue extends SCIOp
{

    protected SCIOpAttachPrivateQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAttachPrivateQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAttachPrivateQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAttachPrivateQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAttachPrivateQueue sciopattachprivatequeue)
    {
        long l;
        if(sciopattachprivatequeue == null)
            l = 0L;
        else
            l = sciopattachprivatequeue.swigCPtr;
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

    public SCIPlayQueue getAttachedPrivateQueue()
    {
        long l = sclibJNI.SCIOpAttachPrivateQueue_getAttachedPrivateQueue(swigCPtr, this);
        SCIPlayQueue sciplayqueue;
        if(l == 0L)
            sciplayqueue = null;
        else
            sciplayqueue = new SCIPlayQueue(l, true);
        return sciplayqueue;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAttachPrivateQueue");
    private long swigCPtr;

}
