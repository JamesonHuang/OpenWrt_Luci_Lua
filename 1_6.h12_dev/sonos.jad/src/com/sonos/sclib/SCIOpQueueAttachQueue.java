// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpQueueAttachQueue extends SCIOp
{

    protected SCIOpQueueAttachQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpQueueAttachQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpQueueAttachQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpQueueAttachQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpQueueAttachQueue sciopqueueattachqueue)
    {
        long l;
        if(sciopqueueattachqueue == null)
            l = 0L;
        else
            l = sciopqueueattachqueue.swigCPtr;
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

    public long getQueueID()
    {
        return sclibJNI.SCIOpQueueAttachQueue_getQueueID(swigCPtr, this);
    }

    public String getQueueOwnerContext()
    {
        return sclibJNI.SCIOpQueueAttachQueue_getQueueOwnerContext(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpQueueAttachQueue");
    private long swigCPtr;

}
