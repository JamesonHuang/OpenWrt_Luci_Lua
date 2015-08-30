// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIServiceDescriptor

public class SCIServiceDescriptorFilter extends SCIObj
{

    protected SCIServiceDescriptorFilter(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceDescriptorFilterUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceDescriptorFilter(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceDescriptorFilter(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceDescriptorFilter sciservicedescriptorfilter)
    {
        long l;
        if(sciservicedescriptorfilter == null)
            l = 0L;
        else
            l = sciservicedescriptorfilter.swigCPtr;
        return l;
    }

    public boolean acceptsServiceDescriptor(SCIServiceDescriptor sciservicedescriptor)
    {
        return sclibJNI.SCIServiceDescriptorFilter_acceptsServiceDescriptor(swigCPtr, this, SCIServiceDescriptor.getCPtr(sciservicedescriptor), sciservicedescriptor);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceDescriptorFilter");
    private long swigCPtr;

}
