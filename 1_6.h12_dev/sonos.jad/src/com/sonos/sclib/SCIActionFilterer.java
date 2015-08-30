// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEnumerator, SCIActionFilter

public class SCIActionFilterer extends SCIObj
{

    protected SCIActionFilterer(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionFiltererUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionFilterer(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionFilterer(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionFilterer sciactionfilterer)
    {
        long l;
        if(sciactionfilterer == null)
            l = 0L;
        else
            l = sciactionfilterer.swigCPtr;
        return l;
    }

    public SCIEnumerator createFilteredEnumerator(SCIEnumerator scienumerator, SCIActionFilter sciactionfilter)
    {
        long l = sclibJNI.SCIActionFilterer_createFilteredEnumerator(swigCPtr, this, SCIEnumerator.getCPtr(scienumerator), scienumerator, SCIActionFilter.getCPtr(sciactionfilter), sciactionfilter);
        SCIEnumerator scienumerator1;
        if(l == 0L)
            scienumerator1 = null;
        else
            scienumerator1 = new SCIEnumerator(l, true);
        return scienumerator1;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionFilterer");
    private long swigCPtr;

}
