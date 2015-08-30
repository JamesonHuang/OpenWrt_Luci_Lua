// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIBrowseManager extends SCIObj
{

    protected SCIBrowseManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseManager scibrowsemanager)
    {
        long l;
        if(scibrowsemanager == null)
            l = 0L;
        else
            l = scibrowsemanager.swigCPtr;
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

    public void resumePolling()
    {
        sclibJNI.SCIBrowseManager_resumePolling(swigCPtr, this);
    }

    public void suspendPolling()
    {
        sclibJNI.SCIBrowseManager_suspendPolling(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseManager");
    private long swigCPtr;

}
