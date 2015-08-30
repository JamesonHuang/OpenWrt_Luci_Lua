// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIStringArray extends SCIObj
{

    protected SCIStringArray(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIStringArrayUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringArray(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringArray(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringArray scistringarray)
    {
        long l;
        if(scistringarray == null)
            l = 0L;
        else
            l = scistringarray.swigCPtr;
        return l;
    }

    public void append(String s)
    {
        sclibJNI.SCIStringArray_append(swigCPtr, this, s);
    }

    public void clear()
    {
        sclibJNI.SCIStringArray_clear(swigCPtr, this);
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

    public String getAt(long l)
    {
        return sclibJNI.SCIStringArray_getAt(swigCPtr, this, l);
    }

    public boolean isEmpty()
    {
        return sclibJNI.SCIStringArray_isEmpty(swigCPtr, this);
    }

    public void remove(long l)
    {
        sclibJNI.SCIStringArray_remove(swigCPtr, this, l);
    }

    public long size()
    {
        return sclibJNI.SCIStringArray_size(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringArray");
    private long swigCPtr;

}
