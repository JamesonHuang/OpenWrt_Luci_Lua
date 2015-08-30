// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCINetworkManagementDelegate, sclibJNI

public class SCINetworkManagementDelegateSwigBase extends SCINetworkManagementDelegate
{

    public SCINetworkManagementDelegateSwigBase()
    {
        SCINetworkManagementDelegateSwigBase(sclibJNI.new_SCINetworkManagementDelegateSwigBase(), true);
        sclibJNI.SCINetworkManagementDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCINetworkManagementDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCINetworkManagementDelegate(sclibJNI.SWIGSCINetworkManagementDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetworkManagementDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetworkManagementDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase)
    {
        long l;
        if(scinetworkmanagementdelegateswigbase == null)
            l = 0L;
        else
            l = scinetworkmanagementdelegateswigbase.swigCPtr;
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

    public String dumpSCIObj()
    {
        String s;
        if(getClass() == com/sonos/sclib/SCINetworkManagementDelegateSwigBase)
            s = sclibJNI.SCINetworkManagementDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCINetworkManagementDelegateSwigBase_dumpSCIObjSwigExplicitSCINetworkManagementDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetworkManagementDelegateSwigBase");
    private long swigCPtr;

}
