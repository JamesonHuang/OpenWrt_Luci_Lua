// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAction, SCIPropertyBag, 
//            SCActionCompletionStatus, SCIActionDelegate

public class SCIActionContext extends SCIObj
{

    protected SCIActionContext(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionContextUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionContext(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionContext(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionContext sciactioncontext)
    {
        long l;
        if(sciactioncontext == null)
            l = 0L;
        else
            l = sciactioncontext.swigCPtr;
        return l;
    }

    public void actionHasCompleted(SCIAction sciaction)
    {
        sclibJNI.SCIActionContext_actionHasCompleted(swigCPtr, this, SCIAction.getCPtr(sciaction), sciaction);
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

    public SCIPropertyBag getPropertyBag()
    {
        long l = sclibJNI.SCIActionContext_getPropertyBag(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public SCActionCompletionStatus perform()
    {
        return SCActionCompletionStatus.swigToEnum(sclibJNI.SCIActionContext_perform(swigCPtr, this));
    }

    public void setDelegate(SCIActionDelegate sciactiondelegate)
    {
        sclibJNI.SCIActionContext_setDelegate(swigCPtr, this, SCIActionDelegate.getCPtr(sciactiondelegate), sciactiondelegate);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionContext");
    private long swigCPtr;

}
