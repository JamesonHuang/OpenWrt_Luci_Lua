// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAddTracksToQueue extends SCIOp
{

    protected SCIOpAddTracksToQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAddTracksToQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAddTracksToQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAddTracksToQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAddTracksToQueue sciopaddtrackstoqueue)
    {
        long l;
        if(sciopaddtrackstoqueue == null)
            l = 0L;
        else
            l = sciopaddtrackstoqueue.swigCPtr;
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

    public long getFirstTrackNumberAdded()
    {
        return sclibJNI.SCIOpAddTracksToQueue_getFirstTrackNumberAdded(swigCPtr, this);
    }

    public long getNewUpdateID()
    {
        return sclibJNI.SCIOpAddTracksToQueue_getNewUpdateID(swigCPtr, this);
    }

    public long getNumTracksAdded()
    {
        return sclibJNI.SCIOpAddTracksToQueue_getNumTracksAdded(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAddTracksToQueue");
    private long swigCPtr;

}
