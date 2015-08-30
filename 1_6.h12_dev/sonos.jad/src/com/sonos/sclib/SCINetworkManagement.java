// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCNetworkState

public class SCINetworkManagement extends SCIObj
{

    protected SCINetworkManagement(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINetworkManagementUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetworkManagement(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetworkManagement(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetworkManagement scinetworkmanagement)
    {
        long l;
        if(scinetworkmanagement == null)
            l = 0L;
        else
            l = scinetworkmanagement.swigCPtr;
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

    public SCNetworkState getNetworkState()
    {
        return SCNetworkState.swigToEnum(sclibJNI.SCINetworkManagement_getNetworkState(swigCPtr, this));
    }

    public void networkChanged()
    {
        sclibJNI.SCINetworkManagement_networkChanged(swigCPtr, this);
    }

    public void rebindNetworkSockets()
    {
        sclibJNI.SCINetworkManagement_rebindNetworkSockets(swigCPtr, this);
    }

    public void refreshNetworking()
    {
        sclibJNI.SCINetworkManagement_refreshNetworking(swigCPtr, this);
    }

    public void resumeDeviceExpiration()
    {
        sclibJNI.SCINetworkManagement_resumeDeviceExpiration(swigCPtr, this);
    }

    public void resumeNetworking()
    {
        sclibJNI.SCINetworkManagement_resumeNetworking(swigCPtr, this);
    }

    public void suspendDeviceExpiration()
    {
        sclibJNI.SCINetworkManagement_suspendDeviceExpiration(swigCPtr, this);
    }

    public void suspendNetworking()
    {
        sclibJNI.SCINetworkManagement_suspendNetworking(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetworkManagement");
    private long swigCPtr;

}
