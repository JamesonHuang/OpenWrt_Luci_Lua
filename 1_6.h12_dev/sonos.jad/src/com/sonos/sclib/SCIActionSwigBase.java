// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIAction, sclibJNI

public class SCIActionSwigBase extends SCIAction
{

    public SCIActionSwigBase()
    {
        SCIActionSwigBase(sclibJNI.new_SCIActionSwigBase(), true);
        sclibJNI.SCIActionSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIActionSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIAction(sclibJNI.SWIGSCIActionSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionSwigBase sciactionswigbase)
    {
        long l;
        if(sciactionswigbase == null)
            l = 0L;
        else
            l = sciactionswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIActionSwigBase)
            s = sclibJNI.SCIActionSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIActionSwigBase_dumpSCIObjSwigExplicitSCIActionSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionSwigBase");
    private long swigCPtr;

}
