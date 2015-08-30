// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCITime, SCIOp, 
//            SCIEventSink

public class SCIIndexManager extends SCIObj
{

    protected SCIIndexManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIIndexManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIIndexManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIIndexManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIIndexManager sciindexmanager)
    {
        long l;
        if(sciindexmanager == null)
            l = 0L;
        else
            l = sciindexmanager.swigCPtr;
        return l;
    }

    public SCIOp createSetDailyIndexRefreshTimeOp(SCITime scitime)
    {
        long l = sclibJNI.SCIIndexManager_createSetDailyIndexRefreshTimeOp(swigCPtr, this, SCITime.getCPtr(scitime), scitime);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createUpdateMusicIndexOp(String s)
    {
        long l = sclibJNI.SCIIndexManager_createUpdateMusicIndexOp(swigCPtr, this, s);
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

    public String getErrorMessage()
    {
        return sclibJNI.SCIIndexManager_getErrorMessage(swigCPtr, this);
    }

    public SCITime getIndexingTime()
    {
        long l = sclibJNI.SCIIndexManager_getIndexingTime(swigCPtr, this);
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return scitime;
    }

    public boolean isIndexingInProgress()
    {
        return sclibJNI.SCIIndexManager_isIndexingInProgress(swigCPtr, this);
    }

    public boolean isIndexingScheduled()
    {
        return sclibJNI.SCIIndexManager_isIndexingScheduled(swigCPtr, this);
    }

    public void setPreparingForIndexing()
    {
        sclibJNI.SCIIndexManager_setPreparingForIndexing(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIIndexManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIIndexManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIIndexManager");
    private long swigCPtr;

}
