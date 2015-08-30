// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIStringInputBase, sclibJNI, SCIInputValidationCB

public class SCIStringInputWithAsyncValidation extends SCIStringInputBase
{

    protected SCIStringInputWithAsyncValidation(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIStringInputBase(sclibJNI.SWIGSCIStringInputWithAsyncValidationUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringInputWithAsyncValidation(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringInputWithAsyncValidation(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringInputWithAsyncValidation scistringinputwithasyncvalidation)
    {
        long l;
        if(scistringinputwithasyncvalidation == null)
            l = 0L;
        else
            l = scistringinputwithasyncvalidation.swigCPtr;
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

    public boolean isValid(SCIInputValidationCB sciinputvalidationcb)
    {
        return sclibJNI.SCIStringInputWithAsyncValidation_isValid__SWIG_0(swigCPtr, this, SCIInputValidationCB.getCPtr(sciinputvalidationcb), sciinputvalidationcb);
    }

    public boolean isValid(String s, SCIInputValidationCB sciinputvalidationcb)
    {
        return sclibJNI.SCIStringInputWithAsyncValidation_isValid__SWIG_1(swigCPtr, this, s, SCIInputValidationCB.getCPtr(sciinputvalidationcb), sciinputvalidationcb);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringInputWithAsyncValidation");
    private long swigCPtr;

}
