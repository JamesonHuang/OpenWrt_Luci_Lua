// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIconID

public class SCIResource extends SCIObj
{

    protected SCIResource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIResourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIResource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIResource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIResource sciresource)
    {
        long l;
        if(sciresource == null)
            l = 0L;
        else
            l = sciresource.swigCPtr;
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
        return sclibJNI.SCIResource_getID(swigCPtr, this);
    }

    public SCIconID getIconID()
    {
        return SCIconID.swigToEnum(sclibJNI.SCIResource_getIconID(swigCPtr, this));
    }

    public String getName()
    {
        return sclibJNI.SCIResource_getName(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIResource");
    private long swigCPtr;

}
