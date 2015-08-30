// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDelegate, sclibJNI

public class SCIActionDelegateSwigBase extends SCIActionDelegate
{

    public SCIActionDelegateSwigBase()
    {
        SCIActionDelegateSwigBase(sclibJNI.new_SCIActionDelegateSwigBase(), true);
        sclibJNI.SCIActionDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIActionDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDelegate(sclibJNI.SWIGSCIActionDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionDelegateSwigBase sciactiondelegateswigbase)
    {
        long l;
        if(sciactiondelegateswigbase == null)
            l = 0L;
        else
            l = sciactiondelegateswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIActionDelegateSwigBase)
            s = sclibJNI.SCIActionDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIActionDelegateSwigBase_dumpSCIObjSwigExplicitSCIActionDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionDelegateSwigBase");
    private long swigCPtr;

}
