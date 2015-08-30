// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpValidateServiceCredentials extends SCIOp
{

    protected SCIOpValidateServiceCredentials(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpValidateServiceCredentialsUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpValidateServiceCredentials(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpValidateServiceCredentials(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpValidateServiceCredentials sciopvalidateservicecredentials)
    {
        long l;
        if(sciopvalidateservicecredentials == null)
            l = 0L;
        else
            l = sciopvalidateservicecredentials.swigCPtr;
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

    public String getErrorString()
    {
        return sclibJNI.SCIOpValidateServiceCredentials_getErrorString(swigCPtr, this);
    }

    public String getLogonString()
    {
        return sclibJNI.SCIOpValidateServiceCredentials_getLogonString(swigCPtr, this);
    }

    public boolean isExpired()
    {
        return sclibJNI.SCIOpValidateServiceCredentials_isExpired(swigCPtr, this);
    }

    public boolean isFullAccount()
    {
        return sclibJNI.SCIOpValidateServiceCredentials_isFullAccount(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpValidateServiceCredentials");
    private long swigCPtr;

}
