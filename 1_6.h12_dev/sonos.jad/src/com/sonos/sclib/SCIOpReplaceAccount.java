// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpReplaceAccount extends SCIOp
{

    protected SCIOpReplaceAccount(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpReplaceAccountUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpReplaceAccount(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpReplaceAccount(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpReplaceAccount sciopreplaceaccount)
    {
        long l;
        if(sciopreplaceaccount == null)
            l = 0L;
        else
            l = sciopreplaceaccount.swigCPtr;
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
        return sclibJNI.SCIOpReplaceAccount_getAccountUDN(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpReplaceAccount");
    private long swigCPtr;

}
