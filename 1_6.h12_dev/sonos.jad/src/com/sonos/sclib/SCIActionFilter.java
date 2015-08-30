// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionDescriptor

public class SCIActionFilter extends SCIObj
{

    protected SCIActionFilter(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionFilterUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionFilter(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionFilter(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionFilter sciactionfilter)
    {
        long l;
        if(sciactionfilter == null)
            l = 0L;
        else
            l = sciactionfilter.swigCPtr;
        return l;
    }

    public boolean acceptsAction(SCIActionDescriptor sciactiondescriptor)
    {
        return sclibJNI.SCIActionFilter_acceptsAction(swigCPtr, this, SCIActionDescriptor.getCPtr(sciactiondescriptor), sciactiondescriptor);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionFilter");
    private long swigCPtr;

}
