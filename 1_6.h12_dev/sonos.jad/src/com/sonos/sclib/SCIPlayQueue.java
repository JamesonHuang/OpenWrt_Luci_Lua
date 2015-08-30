// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIBrowseDataSource, SCIEventSink

public class SCIPlayQueue extends SCIObj
{

    protected SCIPlayQueue(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPlayQueueUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPlayQueue(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPlayQueue(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPlayQueue sciplayqueue)
    {
        long l;
        if(sciplayqueue == null)
            l = 0L;
        else
            l = sciplayqueue.swigCPtr;
        return l;
    }

    public SCIBrowseDataSource createDataSource()
    {
        long l = sclibJNI.SCIPlayQueue_createDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
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
        return sclibJNI.SCIPlayQueue_getQueueID(swigCPtr, this);
    }

    public String getQueueOwnerID()
    {
        return sclibJNI.SCIPlayQueue_getQueueOwnerID(swigCPtr, this);
    }

    public long getUpdateID()
    {
        return sclibJNI.SCIPlayQueue_getUpdateID(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIPlayQueue_isValid(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIPlayQueue_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIPlayQueue_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPlayQueue");
    private long swigCPtr;

}
