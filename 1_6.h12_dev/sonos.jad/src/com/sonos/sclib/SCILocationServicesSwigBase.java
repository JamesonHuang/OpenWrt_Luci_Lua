// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCILocationServices, sclibJNI

public class SCILocationServicesSwigBase extends SCILocationServices
{

    public SCILocationServicesSwigBase()
    {
        SCILocationServicesSwigBase(sclibJNI.new_SCILocationServicesSwigBase(), true);
        sclibJNI.SCILocationServicesSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCILocationServicesSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCILocationServices(sclibJNI.SWIGSCILocationServicesSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocationServicesSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocationServicesSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        long l;
        if(scilocationservicesswigbase == null)
            l = 0L;
        else
            l = scilocationservicesswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCILocationServicesSwigBase)
            s = sclibJNI.SCILocationServicesSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCILocationServicesSwigBase_dumpSCIObjSwigExplicitSCILocationServicesSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocationServicesSwigBase");
    private long swigCPtr;

}
