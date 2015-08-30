// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIDebug extends SCIObj
{

    protected SCIDebug(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDebugUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDebug(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDebug(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDebug scidebug)
    {
        long l;
        if(scidebug == null)
            l = 0L;
        else
            l = scidebug.swigCPtr;
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

    public void forceCrash()
    {
        sclibJNI.SCIDebug_forceCrash(swigCPtr, this);
    }

    public int getSCLibObjectCount()
    {
        return sclibJNI.SCIDebug_getSCLibObjectCount(swigCPtr, this);
    }

    public void setDiagnosticLevel(String s, int i)
    {
        sclibJNI.SCIDebug_setDiagnosticLevel(swigCPtr, this, s, i);
    }

    public void setGlobalOpsDelay(int i)
    {
        sclibJNI.SCIDebug_setGlobalOpsDelay(swigCPtr, this, i);
    }

    public void setMaxListenerCountThreshold(int i)
    {
        sclibJNI.SCIDebug_setMaxListenerCountThreshold(swigCPtr, this, i);
    }

    public void setNetworkIOFailureCondition(int i, int j, String s)
    {
        sclibJNI.SCIDebug_setNetworkIOFailureCondition(swigCPtr, this, i, j, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDebug");
    private long swigCPtr;

}
