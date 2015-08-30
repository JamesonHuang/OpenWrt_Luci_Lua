// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAddServiceAccount extends SCIOp
{

    protected SCIOpAddServiceAccount(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAddServiceAccountUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAddServiceAccount(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAddServiceAccount(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAddServiceAccount sciopaddserviceaccount)
    {
        long l;
        if(sciopaddserviceaccount == null)
            l = 0L;
        else
            l = sciopaddserviceaccount.swigCPtr;
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

    public String getAccountUDN()
    {
        return sclibJNI.SCIOpAddServiceAccount_getAccountUDN(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAddServiceAccount");
    private long swigCPtr;

}
