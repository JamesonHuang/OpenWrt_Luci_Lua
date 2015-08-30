// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpQueueReplaceAllTracks extends SCIOp
{

    protected SCIOpQueueReplaceAllTracks(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpQueueReplaceAllTracksUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpQueueReplaceAllTracks(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpQueueReplaceAllTracks(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpQueueReplaceAllTracks sciopqueuereplacealltracks)
    {
        long l;
        if(sciopqueuereplacealltracks == null)
            l = 0L;
        else
            l = sciopqueuereplacealltracks.swigCPtr;
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

    public long getNewQueueLength()
    {
        return sclibJNI.SCIOpQueueReplaceAllTracks_getNewQueueLength(swigCPtr, this);
    }

    public long getNewUpdateID()
    {
        return sclibJNI.SCIOpQueueReplaceAllTracks_getNewUpdateID(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpQueueReplaceAllTracks");
    private long swigCPtr;

}
