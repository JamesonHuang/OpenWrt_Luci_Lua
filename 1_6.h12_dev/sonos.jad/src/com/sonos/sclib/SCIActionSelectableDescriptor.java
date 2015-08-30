// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionNoArgDescriptor, sclibJNI

public class SCIActionSelectableDescriptor extends SCIActionNoArgDescriptor
{

    protected SCIActionSelectableDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionNoArgDescriptor(sclibJNI.SWIGSCIActionSelectableDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionSelectableDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionSelectableDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionSelectableDescriptor sciactionselectabledescriptor)
    {
        long l;
        if(sciactionselectabledescriptor == null)
            l = 0L;
        else
            l = sciactionselectabledescriptor.swigCPtr;
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

    public boolean isCurrentSelection()
    {
        return sclibJNI.SCIActionSelectableDescriptor_isCurrentSelection(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionSelectableDescriptor");
    private long swigCPtr;

}
