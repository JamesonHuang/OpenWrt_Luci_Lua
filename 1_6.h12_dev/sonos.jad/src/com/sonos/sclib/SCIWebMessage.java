// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIPropertyBag

public class SCIWebMessage extends SCIObj
{

    protected SCIWebMessage(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWebMessageUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWebMessage(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWebMessage(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWebMessage sciwebmessage)
    {
        long l;
        if(sciwebmessage == null)
            l = 0L;
        else
            l = sciwebmessage.swigCPtr;
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

    public SCIPropertyBag getProperties()
    {
        long l = sclibJNI.SCIWebMessage_getProperties(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public String getSubject()
    {
        return sclibJNI.SCIWebMessage_getSubject(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWebMessage");
    private long swigCPtr;

}
