// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIStringInputBase, sclibJNI

public class SCIStringInput extends SCIStringInputBase
{

    protected SCIStringInput(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIStringInputBase(sclibJNI.SWIGSCIStringInputUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringInput(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringInput(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringInput scistringinput)
    {
        long l;
        if(scistringinput == null)
            l = 0L;
        else
            l = scistringinput.swigCPtr;
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

    public boolean isValid()
    {
        return sclibJNI.SCIStringInput_isValid__SWIG_0(swigCPtr, this);
    }

    public boolean isValid(String s)
    {
        return sclibJNI.SCIStringInput_isValid__SWIG_1(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringInput");
    private long swigCPtr;

}
