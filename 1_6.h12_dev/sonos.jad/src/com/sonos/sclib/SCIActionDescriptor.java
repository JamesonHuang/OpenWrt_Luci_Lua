// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIActionDescriptor extends SCIObj
{

    protected SCIActionDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionDescriptor sciactiondescriptor)
    {
        long l;
        if(sciactiondescriptor == null)
            l = 0L;
        else
            l = sciactiondescriptor.swigCPtr;
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

    public String getActionID()
    {
        return sclibJNI.SCIActionDescriptor_getActionID(swigCPtr, this);
    }

    public String getCategory()
    {
        return sclibJNI.SCIActionDescriptor_getCategory(swigCPtr, this);
    }

    public String getInterfaceID()
    {
        return sclibJNI.SCIActionDescriptor_getInterfaceID(swigCPtr, this);
    }

    public String getLabel()
    {
        return sclibJNI.SCIActionDescriptor_getLabel(swigCPtr, this);
    }

    public boolean isEnabled()
    {
        return sclibJNI.SCIActionDescriptor_isEnabled(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionDescriptor");
    private long swigCPtr;

}
