// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIEventSink, sclibJNI

public class SCIEventSinkSwigBase extends SCIEventSink
{

    public SCIEventSinkSwigBase()
    {
        SCIEventSinkSwigBase(sclibJNI.new_SCIEventSinkSwigBase(), true);
        sclibJNI.SCIEventSinkSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIEventSinkSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIEventSink(sclibJNI.SWIGSCIEventSinkSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIEventSinkSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIEventSinkSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIEventSinkSwigBase scieventsinkswigbase)
    {
        long l;
        if(scieventsinkswigbase == null)
            l = 0L;
        else
            l = scieventsinkswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIEventSinkSwigBase)
            s = sclibJNI.SCIEventSinkSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIEventSinkSwigBase_dumpSCIObjSwigExplicitSCIEventSinkSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIEventSinkSwigBase");
    private long swigCPtr;

}
