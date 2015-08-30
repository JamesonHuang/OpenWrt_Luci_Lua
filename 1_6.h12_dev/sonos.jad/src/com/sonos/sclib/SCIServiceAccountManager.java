// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIServiceAccountFilter, SCIEnumerator, 
//            SCIServiceDescriptorManager, SCIServiceAccount, SCIEventSink

public class SCIServiceAccountManager extends SCIObj
{

    protected SCIServiceAccountManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceAccountManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceAccountManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceAccountManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceAccountManager sciserviceaccountmanager)
    {
        long l;
        if(sciserviceaccountmanager == null)
            l = 0L;
        else
            l = sciserviceaccountmanager.swigCPtr;
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

    public boolean getDefaultAccount(String s, Object aobj[])
    {
        return sclibJNI.SCIServiceAccountManager_getDefaultAccount(swigCPtr, this, s, aobj);
    }

    public SCIEnumerator getFilteredServiceAccounts(SCIServiceAccountFilter sciserviceaccountfilter)
    {
        long l = sclibJNI.SCIServiceAccountManager_getFilteredServiceAccounts(swigCPtr, this, SCIServiceAccountFilter.getCPtr(sciserviceaccountfilter), sciserviceaccountfilter);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIServiceDescriptorManager getServiceDescriptorManager()
    {
        long l = sclibJNI.SCIServiceAccountManager_getServiceDescriptorManager(swigCPtr, this);
        SCIServiceDescriptorManager sciservicedescriptormanager;
        if(l == 0L)
            sciservicedescriptormanager = null;
        else
            sciservicedescriptormanager = new SCIServiceDescriptorManager(l, true);
        return sciservicedescriptormanager;
    }

    public boolean isAccountToDisplay(String s)
    {
        return sclibJNI.SCIServiceAccountManager_isAccountToDisplay(swigCPtr, this, s);
    }

    public boolean isDefaultAccount(String s)
    {
        return sclibJNI.SCIServiceAccountManager_isDefaultAccount(swigCPtr, this, s);
    }

    public SCIServiceAccount lookupServiceAccount(String s)
    {
        long l = sclibJNI.SCIServiceAccountManager_lookupServiceAccount(swigCPtr, this, s);
        SCIServiceAccount sciserviceaccount;
        if(l == 0L)
            sciserviceaccount = null;
        else
            sciserviceaccount = new SCIServiceAccount(l, true);
        return sciserviceaccount;
    }

    public boolean setDefaultAccount(String s)
    {
        return sclibJNI.SCIServiceAccountManager_setDefaultAccount(swigCPtr, this, s);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIServiceAccountManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIServiceAccountManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public boolean validateDefaultAccount(String s)
    {
        return sclibJNI.SCIServiceAccountManager_validateDefaultAccount(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceAccountManager");
    private long swigCPtr;

}
