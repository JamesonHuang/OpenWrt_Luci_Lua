// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIGetAboutSonosStringCB extends SCIObj
{

    protected SCIGetAboutSonosStringCB(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIGetAboutSonosStringCBUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIGetAboutSonosStringCB(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIGetAboutSonosStringCB(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIGetAboutSonosStringCB scigetaboutsonosstringcb)
    {
        long l;
        if(scigetaboutsonosstringcb == null)
            l = 0L;
        else
            l = scigetaboutsonosstringcb.swigCPtr;
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

    public void updateGetAboutSonosString(String s)
    {
        sclibJNI.SCIGetAboutSonosStringCB_updateGetAboutSonosString(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIGetAboutSonosStringCB");
    private long swigCPtr;

}
