// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIInputValidationCB extends SCIObj
{

    protected SCIInputValidationCB(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIInputValidationCBUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIInputValidationCB(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIInputValidationCB(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIInputValidationCB sciinputvalidationcb)
    {
        long l;
        if(sciinputvalidationcb == null)
            l = 0L;
        else
            l = sciinputvalidationcb.swigCPtr;
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

    public void handleInputValidationCB(String s, boolean flag)
    {
        sclibJNI.SCIInputValidationCB_handleInputValidationCB(swigCPtr, this, s, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIInputValidationCB");
    private long swigCPtr;

}
