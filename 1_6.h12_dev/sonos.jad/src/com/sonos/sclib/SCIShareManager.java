// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp, SCIShare, 
//            SCIBrowseDataSource, SCIEnumerator, SCIEventSink

public class SCIShareManager extends SCIObj
{

    protected SCIShareManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIShareManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIShareManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIShareManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIShareManager scisharemanager)
    {
        long l;
        if(scisharemanager == null)
            l = 0L;
        else
            l = scisharemanager.swigCPtr;
        return l;
    }

    public SCIOp createAddShareOp(String s, String s1, String s2)
    {
        long l = sclibJNI.SCIShareManager_createAddShareOp(swigCPtr, this, s, s1, s2);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createRemoveShareOp(SCIShare scishare)
    {
        long l = sclibJNI.SCIShareManager_createRemoveShareOp(swigCPtr, this, SCIShare.getCPtr(scishare), scishare);
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

    public SCIBrowseDataSource getShareDataSource()
    {
        long l = sclibJNI.SCIShareManager_getShareDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public SCIEnumerator getShares()
    {
        long l = sclibJNI.SCIShareManager_getShares(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean hasShares()
    {
        return sclibJNI.SCIShareManager_hasShares(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIShareManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIShareManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIShareManager");
    private long swigCPtr;

}
