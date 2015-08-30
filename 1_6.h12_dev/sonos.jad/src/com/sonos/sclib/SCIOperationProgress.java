// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIOperationProgress extends SCIObj
{

    protected SCIOperationProgress(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIOperationProgressUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOperationProgress(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOperationProgress(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOperationProgress scioperationprogress)
    {
        long l;
        if(scioperationprogress == null)
            l = 0L;
        else
            l = scioperationprogress.swigCPtr;
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

    public int getPercentageCompleted()
    {
        return sclibJNI.SCIOperationProgress_getPercentageCompleted(swigCPtr, this);
    }

    public String getProgressText()
    {
        return sclibJNI.SCIOperationProgress_getProgressText(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOperationProgress");
    private long swigCPtr;

}
