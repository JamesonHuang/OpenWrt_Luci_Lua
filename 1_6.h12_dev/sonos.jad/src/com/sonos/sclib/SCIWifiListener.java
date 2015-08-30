// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIWifiConnectionResult

public class SCIWifiListener extends SCIObj
{

    protected SCIWifiListener(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWifiListenerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWifiListener(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWifiListener(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWifiListener sciwifilistener)
    {
        long l;
        if(sciwifilistener == null)
            l = 0L;
        else
            l = sciwifilistener.swigCPtr;
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

    public void setSSIDResult(String s, boolean flag)
    {
        sclibJNI.SCIWifiListener_setSSIDResult(swigCPtr, this, s, flag);
    }

    public void setSSIDResultExtended(String s, SCIWifiConnectionResult sciwificonnectionresult)
    {
        sclibJNI.SCIWifiListener_setSSIDResultExtended(swigCPtr, this, s, sciwificonnectionresult.swigValue());
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWifiListener");
    private long swigCPtr;

}
