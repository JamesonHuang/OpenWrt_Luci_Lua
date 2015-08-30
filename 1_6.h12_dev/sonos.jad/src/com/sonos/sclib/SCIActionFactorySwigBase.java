// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionFactory, sclibJNI

public class SCIActionFactorySwigBase extends SCIActionFactory
{

    public SCIActionFactorySwigBase()
    {
        SCIActionFactorySwigBase(sclibJNI.new_SCIActionFactorySwigBase(), true);
        sclibJNI.SCIActionFactorySwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIActionFactorySwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionFactory(sclibJNI.SWIGSCIActionFactorySwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionFactorySwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionFactorySwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionFactorySwigBase sciactionfactoryswigbase)
    {
        long l;
        if(sciactionfactoryswigbase == null)
            l = 0L;
        else
            l = sciactionfactoryswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIActionFactorySwigBase)
            s = sclibJNI.SCIActionFactorySwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIActionFactorySwigBase_dumpSCIObjSwigExplicitSCIActionFactorySwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionFactorySwigBase");
    private long swigCPtr;

}
