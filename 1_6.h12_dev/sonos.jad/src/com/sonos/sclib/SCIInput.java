// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIInput extends SCIObj
{

    protected SCIInput(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIInputUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIInput(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIInput(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIInput sciinput)
    {
        long l;
        if(sciinput == null)
            l = 0L;
        else
            l = sciinput.swigCPtr;
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

    public String getID()
    {
        return sclibJNI.SCIInput_getID(swigCPtr, this);
    }

    public String getInterfaceID()
    {
        return sclibJNI.SCIInput_getInterfaceID(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIInput");
    private long swigCPtr;

}
