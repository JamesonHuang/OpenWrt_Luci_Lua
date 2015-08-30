// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDescriptor, sclibJNI, SCIActionContext

public class SCIActionWithBoolDescriptor extends SCIActionDescriptor
{

    protected SCIActionWithBoolDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDescriptor(sclibJNI.SWIGSCIActionWithBoolDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionWithBoolDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionWithBoolDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionWithBoolDescriptor sciactionwithbooldescriptor)
    {
        long l;
        if(sciactionwithbooldescriptor == null)
            l = 0L;
        else
            l = sciactionwithbooldescriptor.swigCPtr;
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

    public SCIActionContext getAction(boolean flag)
    {
        long l = sclibJNI.SCIActionWithBoolDescriptor_getAction(swigCPtr, this, flag);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionWithBoolDescriptor");
    private long swigCPtr;

}
