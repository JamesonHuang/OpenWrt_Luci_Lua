// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAudibleManager, SCIServiceDescriptorFilter, 
//            SCIEnumerator, SCIServiceAccountManager, SCIServiceDescriptor, SCIEventSink

public class SCIServiceDescriptorManager extends SCIObj
{

    protected SCIServiceDescriptorManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceDescriptorManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceDescriptorManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceDescriptorManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceDescriptorManager sciservicedescriptormanager)
    {
        long l;
        if(sciservicedescriptormanager == null)
            l = 0L;
        else
            l = sciservicedescriptormanager.swigCPtr;
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

    public SCIAudibleManager getAudibleManager()
    {
        long l = sclibJNI.SCIServiceDescriptorManager_getAudibleManager(swigCPtr, this);
        SCIAudibleManager sciaudiblemanager;
        if(l == 0L)
            sciaudiblemanager = null;
        else
            sciaudiblemanager = new SCIAudibleManager(l, true);
        return sciaudiblemanager;
    }

    public SCIEnumerator getFilteredServiceDescriptors(SCIServiceDescriptorFilter sciservicedescriptorfilter)
    {
        long l = sclibJNI.SCIServiceDescriptorManager_getFilteredServiceDescriptors(swigCPtr, this, SCIServiceDescriptorFilter.getCPtr(sciservicedescriptorfilter), sciservicedescriptorfilter);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIServiceAccountManager getServiceAccountManager()
    {
        long l = sclibJNI.SCIServiceDescriptorManager_getServiceAccountManager(swigCPtr, this);
        SCIServiceAccountManager sciserviceaccountmanager;
        if(l == 0L)
            sciserviceaccountmanager = null;
        else
            sciserviceaccountmanager = new SCIServiceAccountManager(l, true);
        return sciserviceaccountmanager;
    }

    public SCIServiceDescriptor lookupServiceDescriptor(String s)
    {
        long l = sclibJNI.SCIServiceDescriptorManager_lookupServiceDescriptor(swigCPtr, this, s);
        SCIServiceDescriptor sciservicedescriptor;
        if(l == 0L)
            sciservicedescriptor = null;
        else
            sciservicedescriptor = new SCIServiceDescriptor(l, true);
        return sciservicedescriptor;
    }

    public SCIServiceDescriptor lookupServiceDescriptorBySCUri(String s)
    {
        long l = sclibJNI.SCIServiceDescriptorManager_lookupServiceDescriptorBySCUri(swigCPtr, this, s);
        SCIServiceDescriptor sciservicedescriptor;
        if(l == 0L)
            sciservicedescriptor = null;
        else
            sciservicedescriptor = new SCIServiceDescriptor(l, true);
        return sciservicedescriptor;
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIServiceDescriptorManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIServiceDescriptorManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceDescriptorManager");
    private long swigCPtr;

}
