// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDescriptor, sclibJNI, SCIActionContext

public class SCIActionWithStringDescriptor extends SCIActionDescriptor
{

    protected SCIActionWithStringDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDescriptor(sclibJNI.SWIGSCIActionWithStringDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionWithStringDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionWithStringDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionWithStringDescriptor sciactionwithstringdescriptor)
    {
        long l;
        if(sciactionwithstringdescriptor == null)
            l = 0L;
        else
            l = sciactionwithstringdescriptor.swigCPtr;
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

    public SCIActionContext getAction(String s)
    {
        long l = sclibJNI.SCIActionWithStringDescriptor_getAction(swigCPtr, this, s);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionWithStringDescriptor");
    private long swigCPtr;

}
