// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIEnumerator extends SCIObj
{

    protected SCIEnumerator(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIEnumeratorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIEnumerator(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIEnumerator(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIEnumerator scienumerator)
    {
        long l;
        if(scienumerator == null)
            l = 0L;
        else
            l = scienumerator.swigCPtr;
        return l;
    }

    public int count()
    {
        return sclibJNI.SCIEnumerator_count(swigCPtr, this);
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

    public SCIObj getCurrent(String s)
    {
        return sclibJNI.SCIEnumerator_getCurrent(swigCPtr, this, s);
    }

    public boolean moveNext()
    {
        return sclibJNI.SCIEnumerator_moveNext(swigCPtr, this);
    }

    public void reset()
    {
        sclibJNI.SCIEnumerator_reset(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIEnumerator");
    private long swigCPtr;

}
