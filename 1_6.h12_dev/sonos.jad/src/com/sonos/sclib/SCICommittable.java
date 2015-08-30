// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCICommittable extends SCIObj
{

    protected SCICommittable(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCICommittableUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCICommittable(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCICommittable(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCICommittable scicommittable)
    {
        long l;
        if(scicommittable == null)
            l = 0L;
        else
            l = scicommittable.swigCPtr;
        return l;
    }

    public void commit()
    {
        sclibJNI.SCICommittable_commit(swigCPtr, this);
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

    public boolean isCommitted()
    {
        return sclibJNI.SCICommittable_isCommitted(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCICommittable");
    private long swigCPtr;

}
