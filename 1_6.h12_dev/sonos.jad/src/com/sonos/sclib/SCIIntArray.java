// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIIntArray extends SCIObj
{

    protected SCIIntArray(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIIntArrayUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIIntArray(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIIntArray(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIIntArray sciintarray)
    {
        long l;
        if(sciintarray == null)
            l = 0L;
        else
            l = sciintarray.swigCPtr;
        return l;
    }

    public void append(int i)
    {
        sclibJNI.SCIIntArray_append(swigCPtr, this, i);
    }

    public void clear()
    {
        sclibJNI.SCIIntArray_clear(swigCPtr, this);
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

    public int getAt(long l)
    {
        return sclibJNI.SCIIntArray_getAt(swigCPtr, this, l);
    }

    public void remove(long l)
    {
        sclibJNI.SCIIntArray_remove(swigCPtr, this, l);
    }

    public long size()
    {
        return sclibJNI.SCIIntArray_size(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIIntArray");
    private long swigCPtr;

}
