// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAction, SCIActionContext

public class SCIActionDelegate extends SCIObj
{

    protected SCIActionDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionDelegate sciactiondelegate)
    {
        long l;
        if(sciactiondelegate == null)
            l = 0L;
        else
            l = sciactiondelegate.swigCPtr;
        return l;
    }

    public void asyncActionHasCompleted(SCIAction sciaction, SCIActionContext sciactioncontext)
    {
        sclibJNI.SCIActionDelegate_asyncActionHasCompleted(swigCPtr, this, SCIAction.getCPtr(sciaction), sciaction, SCIActionContext.getCPtr(sciactioncontext), sciactioncontext);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionDelegate");
    private long swigCPtr;

}
