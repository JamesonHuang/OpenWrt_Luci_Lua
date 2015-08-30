// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI, SCIPlayQueue

public class SCIOpNewPrivateQueue extends SCIOp
{

    protected SCIOpNewPrivateQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpNewPrivateQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpNewPrivateQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpNewPrivateQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpNewPrivateQueue sciopnewprivatequeue)
    {
        long l;
        if(sciopnewprivatequeue == null)
            l = 0L;
        else
            l = sciopnewprivatequeue.swigCPtr;
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

    public SCIPlayQueue getCreatedPrivateQueue()
    {
        long l = sclibJNI.SCIOpNewPrivateQueue_getCreatedPrivateQueue(swigCPtr, this);
        SCIPlayQueue sciplayqueue;
        if(l == 0L)
            sciplayqueue = null;
        else
            sciplayqueue = new SCIPlayQueue(l, true);
        return sciplayqueue;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpNewPrivateQueue");
    private long swigCPtr;

}
