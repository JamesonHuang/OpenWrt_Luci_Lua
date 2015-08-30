// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWebBridgeDelegate, sclibJNI

public class SCIWebBridgeDelegateSwigBase extends SCIWebBridgeDelegate
{

    public SCIWebBridgeDelegateSwigBase()
    {
        SCIWebBridgeDelegateSwigBase(sclibJNI.new_SCIWebBridgeDelegateSwigBase(), true);
        sclibJNI.SCIWebBridgeDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIWebBridgeDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWebBridgeDelegate(sclibJNI.SWIGSCIWebBridgeDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWebBridgeDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWebBridgeDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase)
    {
        long l;
        if(sciwebbridgedelegateswigbase == null)
            l = 0L;
        else
            l = sciwebbridgedelegateswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIWebBridgeDelegateSwigBase)
            s = sclibJNI.SCIWebBridgeDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIWebBridgeDelegateSwigBase_dumpSCIObjSwigExplicitSCIWebBridgeDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWebBridgeDelegateSwigBase");
    private long swigCPtr;

}
