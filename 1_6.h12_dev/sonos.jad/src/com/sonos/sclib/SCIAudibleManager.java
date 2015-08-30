// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionContext

public class SCIAudibleManager extends SCIObj
{

    protected SCIAudibleManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAudibleManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAudibleManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAudibleManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAudibleManager sciaudiblemanager)
    {
        long l;
        if(sciaudiblemanager == null)
            l = 0L;
        else
            l = sciaudiblemanager.swigCPtr;
        return l;
    }

    public SCIActionContext createAddAccountAction()
    {
        long l = sclibJNI.SCIAudibleManager_createAddAccountAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
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

    public String getServiceDescriptorID()
    {
        return sclibJNI.SCIAudibleManager_getServiceDescriptorID(swigCPtr, this);
    }

    public boolean isActivatedDeviceInHousehold()
    {
        return sclibJNI.SCIAudibleManager_isActivatedDeviceInHousehold(swigCPtr, this);
    }

    public void update()
    {
        sclibJNI.SCIAudibleManager_update(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAudibleManager");
    private long swigCPtr;

}
