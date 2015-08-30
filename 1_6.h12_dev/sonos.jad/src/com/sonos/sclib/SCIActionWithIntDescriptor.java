// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDescriptor, sclibJNI, SCIActionContext

public class SCIActionWithIntDescriptor extends SCIActionDescriptor
{

    protected SCIActionWithIntDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDescriptor(sclibJNI.SWIGSCIActionWithIntDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionWithIntDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionWithIntDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionWithIntDescriptor sciactionwithintdescriptor)
    {
        long l;
        if(sciactionwithintdescriptor == null)
            l = 0L;
        else
            l = sciactionwithintdescriptor.swigCPtr;
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

    public SCIActionContext getAction(int i)
    {
        long l = sclibJNI.SCIActionWithIntDescriptor_getAction(swigCPtr, this, i);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionWithIntDescriptor");
    private long swigCPtr;

}
