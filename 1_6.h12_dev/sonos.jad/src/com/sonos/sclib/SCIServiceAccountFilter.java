// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIServiceAccount

public class SCIServiceAccountFilter extends SCIObj
{

    protected SCIServiceAccountFilter(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceAccountFilterUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceAccountFilter(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceAccountFilter(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceAccountFilter sciserviceaccountfilter)
    {
        long l;
        if(sciserviceaccountfilter == null)
            l = 0L;
        else
            l = sciserviceaccountfilter.swigCPtr;
        return l;
    }

    public boolean acceptsServiceAccount(SCIServiceAccount sciserviceaccount)
    {
        return sclibJNI.SCIServiceAccountFilter_acceptsServiceAccount(swigCPtr, this, SCIServiceAccount.getCPtr(sciserviceaccount), sciserviceaccount);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceAccountFilter");
    private long swigCPtr;

}
