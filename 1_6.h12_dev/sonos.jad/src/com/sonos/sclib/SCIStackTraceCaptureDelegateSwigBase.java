// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIStackTraceCaptureDelegate, sclibJNI

public class SCIStackTraceCaptureDelegateSwigBase extends SCIStackTraceCaptureDelegate
{

    public SCIStackTraceCaptureDelegateSwigBase()
    {
        SCIStackTraceCaptureDelegateSwigBase(sclibJNI.new_SCIStackTraceCaptureDelegateSwigBase(), true);
        sclibJNI.SCIStackTraceCaptureDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIStackTraceCaptureDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIStackTraceCaptureDelegate(sclibJNI.SWIGSCIStackTraceCaptureDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStackTraceCaptureDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStackTraceCaptureDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase)
    {
        long l;
        if(scistacktracecapturedelegateswigbase == null)
            l = 0L;
        else
            l = scistacktracecapturedelegateswigbase.swigCPtr;
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

    public String dumpSCIObj()
    {
        String s;
        if(getClass() == com/sonos/sclib/SCIStackTraceCaptureDelegateSwigBase)
            s = sclibJNI.SCIStackTraceCaptureDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIStackTraceCaptureDelegateSwigBase_dumpSCIObjSwigExplicitSCIStackTraceCaptureDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStackTraceCaptureDelegateSwigBase");
    private long swigCPtr;

}
