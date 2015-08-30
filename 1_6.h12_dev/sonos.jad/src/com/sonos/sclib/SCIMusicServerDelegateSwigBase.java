// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIMusicServerDelegate, sclibJNI

public class SCIMusicServerDelegateSwigBase extends SCIMusicServerDelegate
{

    public SCIMusicServerDelegateSwigBase()
    {
        SCIMusicServerDelegateSwigBase(sclibJNI.new_SCIMusicServerDelegateSwigBase(), true);
        sclibJNI.SCIMusicServerDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIMusicServerDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIMusicServerDelegate(sclibJNI.SWIGSCIMusicServerDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServerDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServerDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase)
    {
        long l;
        if(scimusicserverdelegateswigbase == null)
            l = 0L;
        else
            l = scimusicserverdelegateswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIMusicServerDelegateSwigBase)
            s = sclibJNI.SCIMusicServerDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIMusicServerDelegateSwigBase_dumpSCIObjSwigExplicitSCIMusicServerDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServerDelegateSwigBase");
    private long swigCPtr;

}
