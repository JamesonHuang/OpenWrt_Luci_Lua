// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionDescriptor, sclibJNI, SCIActionContext

public class SCIAddToQueueAtNumberDescriptor extends SCIActionDescriptor
{

    protected SCIAddToQueueAtNumberDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionDescriptor(sclibJNI.SWIGSCIAddToQueueAtNumberDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAddToQueueAtNumberDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAddToQueueAtNumberDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAddToQueueAtNumberDescriptor sciaddtoqueueatnumberdescriptor)
    {
        long l;
        if(sciaddtoqueueatnumberdescriptor == null)
            l = 0L;
        else
            l = sciaddtoqueueatnumberdescriptor.swigCPtr;
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

    public SCIActionContext getAction(String s, long l)
    {
        long l1 = sclibJNI.SCIAddToQueueAtNumberDescriptor_getAction(swigCPtr, this, s, l);
        SCIActionContext sciactioncontext;
        if(l1 == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l1, true);
        return sciactioncontext;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAddToQueueAtNumberDescriptor");
    private long swigCPtr;

}
