// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIPropertyBag, SWIGTYPE_p_int

public class SCIStringTemplate extends SCIObj
{

    protected SCIStringTemplate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIStringTemplateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringTemplate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringTemplate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringTemplate scistringtemplate)
    {
        long l;
        if(scistringtemplate == null)
            l = 0L;
        else
            l = scistringtemplate.swigCPtr;
        return l;
    }

    public String buildString(SCIPropertyBag scipropertybag, SWIGTYPE_p_int swigtype_p_int)
    {
        return sclibJNI.SCIStringTemplate_buildString(swigCPtr, this, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag, SWIGTYPE_p_int.getCPtr(swigtype_p_int));
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringTemplate");
    private long swigCPtr;

}
