// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIEventSink extends SCIObj
{

    protected SCIEventSink(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIEventSinkUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIEventSink(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIEventSink(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIEventSink scieventsink)
    {
        long l;
        if(scieventsink == null)
            l = 0L;
        else
            l = scieventsink.swigCPtr;
        return l;
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        sclibJNI.SCIEventSink_dispatchEvent(swigCPtr, this, sciobj, s);
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

    public boolean listenToEventType(String s)
    {
        return sclibJNI.SCIEventSink_listenToEventType(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIEventSink");
    private long swigCPtr;

}
