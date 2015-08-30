// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIStackTraceCaptureDelegate extends SCIObj
{

    protected SCIStackTraceCaptureDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIStackTraceCaptureDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStackTraceCaptureDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStackTraceCaptureDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStackTraceCaptureDelegate scistacktracecapturedelegate)
    {
        long l;
        if(scistacktracecapturedelegate == null)
            l = 0L;
        else
            l = scistacktracecapturedelegate.swigCPtr;
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

    public void stackTraceCaptured(String s)
    {
        sclibJNI.SCIStackTraceCaptureDelegate_stackTraceCaptured(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStackTraceCaptureDelegate");
    private long swigCPtr;

}
