// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCINetstartListener extends SCIObj
{

    protected SCINetstartListener(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINetstartListenerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetstartListener(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetstartListener(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetstartListener scinetstartlistener)
    {
        long l;
        if(scinetstartlistener == null)
            l = 0L;
        else
            l = scinetstartlistener.swigCPtr;
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

    public void onDeviceDiscoveryWaiting()
    {
        sclibJNI.SCINetstartListener_onDeviceDiscoveryWaiting(swigCPtr, this);
    }

    public void onJoinComplete()
    {
        sclibJNI.SCINetstartListener_onJoinComplete(swigCPtr, this);
    }

    public void onJoinFail()
    {
        sclibJNI.SCINetstartListener_onJoinFail(swigCPtr, this);
    }

    public void onNetParamsAcquired(String s, String s1, int i)
    {
        sclibJNI.SCINetstartListener_onNetParamsAcquired(swigCPtr, this, s, s1, i);
    }

    public void onSonosnetOptionChange(boolean flag)
    {
        sclibJNI.SCINetstartListener_onSonosnetOptionChange(swigCPtr, this, flag);
    }

    public void onSonosnetPasswordReset()
    {
        sclibJNI.SCINetstartListener_onSonosnetPasswordReset(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetstartListener");
    private long swigCPtr;

}
