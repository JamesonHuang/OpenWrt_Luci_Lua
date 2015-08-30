// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCILocalMediaCollectionListener extends SCIObj
{

    protected SCILocalMediaCollectionListener(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILocalMediaCollectionListenerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMediaCollectionListener(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMediaCollectionListener(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMediaCollectionListener scilocalmediacollectionlistener)
    {
        long l;
        if(scilocalmediacollectionlistener == null)
            l = 0L;
        else
            l = scilocalmediacollectionlistener.swigCPtr;
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

    public void onMediaCollectionChanged()
    {
        sclibJNI.SCILocalMediaCollectionListener_onMediaCollectionChanged(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMediaCollectionListener");
    private long swigCPtr;

}
