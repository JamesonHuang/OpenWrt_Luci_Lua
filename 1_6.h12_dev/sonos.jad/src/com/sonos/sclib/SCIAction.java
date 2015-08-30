// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionContext, SCActionCompletionStatus

public class SCIAction extends SCIObj
{

    protected SCIAction(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAction(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAction(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAction sciaction)
    {
        long l;
        if(sciaction == null)
            l = 0L;
        else
            l = sciaction.swigCPtr;
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

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        return SCActionCompletionStatus.swigToEnum(sclibJNI.SCIAction_perform(swigCPtr, this, SCIActionContext.getCPtr(sciactioncontext), sciactioncontext));
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAction");
    private long swigCPtr;

}
