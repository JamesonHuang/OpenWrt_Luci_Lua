// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOpCB, sclibJNI

public class SCIOpCBSwigBase extends SCIOpCB
{

    public SCIOpCBSwigBase()
    {
        SCIOpCBSwigBase(sclibJNI.new_SCIOpCBSwigBase(), true);
        sclibJNI.SCIOpCBSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIOpCBSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOpCB(sclibJNI.SWIGSCIOpCBSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpCBSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpCBSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpCBSwigBase sciopcbswigbase)
    {
        long l;
        if(sciopcbswigbase == null)
            l = 0L;
        else
            l = sciopcbswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIOpCBSwigBase)
            s = sclibJNI.SCIOpCBSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIOpCBSwigBase_dumpSCIObjSwigExplicitSCIOpCBSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpCBSwigBase");
    private long swigCPtr;

}
