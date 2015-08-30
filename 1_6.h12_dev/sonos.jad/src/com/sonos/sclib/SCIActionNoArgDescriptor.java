// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDescriptor, sclibJNI, SCIActionContext

public class SCIActionNoArgDescriptor extends SCIActionDescriptor
{

    protected SCIActionNoArgDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDescriptor(sclibJNI.SWIGSCIActionNoArgDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionNoArgDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionNoArgDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionNoArgDescriptor sciactionnoargdescriptor)
    {
        long l;
        if(sciactionnoargdescriptor == null)
            l = 0L;
        else
            l = sciactionnoargdescriptor.swigCPtr;
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

    public SCIActionContext getAction()
    {
        long l = sclibJNI.SCIActionNoArgDescriptor_getAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionNoArgDescriptor");
    private long swigCPtr;

}
