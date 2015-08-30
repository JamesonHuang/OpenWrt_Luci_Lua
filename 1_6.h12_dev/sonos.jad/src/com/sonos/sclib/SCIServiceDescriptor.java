// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionContext, SCIOpAddServiceAccount, 
//            SCIOpValidateServiceCredentials, SCCredentialsType

public class SCIServiceDescriptor extends SCIObj
{

    protected SCIServiceDescriptor(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceDescriptorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceDescriptor(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceDescriptor(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceDescriptor sciservicedescriptor)
    {
        long l;
        if(sciservicedescriptor == null)
            l = 0L;
        else
            l = sciservicedescriptor.swigCPtr;
        return l;
    }

    public boolean canAddAccount()
    {
        return sclibJNI.SCIServiceDescriptor_canAddAccount(swigCPtr, this);
    }

    public SCIActionContext createAddAccountAction(String s, String s1)
    {
        long l = sclibJNI.SCIServiceDescriptor_createAddAccountAction(swigCPtr, this, s, s1);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIOpAddServiceAccount createAddAccountOp(String s, String s1)
    {
        long l = sclibJNI.SCIServiceDescriptor_createAddAccountOp(swigCPtr, this, s, s1);
        SCIOpAddServiceAccount sciopaddserviceaccount;
        if(l == 0L)
            sciopaddserviceaccount = null;
        else
            sciopaddserviceaccount = new SCIOpAddServiceAccount(l, true);
        return sciopaddserviceaccount;
    }

    public SCIOpAddServiceAccount createAddLinkCodeAccountOp(String s, String s1, String s2)
    {
        long l = sclibJNI.SCIServiceDescriptor_createAddLinkCodeAccountOp(swigCPtr, this, s, s1, s2);
        SCIOpAddServiceAccount sciopaddserviceaccount;
        if(l == 0L)
            sciopaddserviceaccount = null;
        else
            sciopaddserviceaccount = new SCIOpAddServiceAccount(l, true);
        return sciopaddserviceaccount;
    }

    public SCIOpAddServiceAccount createProvisionCredentialedTrialAccountOp(String s, String s1)
    {
        long l = sclibJNI.SCIServiceDescriptor_createProvisionCredentialedTrialAccountOp(swigCPtr, this, s, s1);
        SCIOpAddServiceAccount sciopaddserviceaccount;
        if(l == 0L)
            sciopaddserviceaccount = null;
        else
            sciopaddserviceaccount = new SCIOpAddServiceAccount(l, true);
        return sciopaddserviceaccount;
    }

    public SCIOpAddServiceAccount createProvisionTrialAccountOp()
    {
        long l = sclibJNI.SCIServiceDescriptor_createProvisionTrialAccountOp(swigCPtr, this);
        SCIOpAddServiceAccount sciopaddserviceaccount;
        if(l == 0L)
            sciopaddserviceaccount = null;
        else
            sciopaddserviceaccount = new SCIOpAddServiceAccount(l, true);
        return sciopaddserviceaccount;
    }

    public SCIOpValidateServiceCredentials createValidateServiceCredentialsOp(String s, String s1)
    {
        long l = sclibJNI.SCIServiceDescriptor_createValidateServiceCredentialsOp(swigCPtr, this, s, s1);
        SCIOpValidateServiceCredentials sciopvalidateservicecredentials;
        if(l == 0L)
            sciopvalidateservicecredentials = null;
        else
            sciopvalidateservicecredentials = new SCIOpValidateServiceCredentials(l, true);
        return sciopvalidateservicecredentials;
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

    public boolean doesServiceAccountSupportInterface(String s)
    {
        return sclibJNI.SCIServiceDescriptor_doesServiceAccountSupportInterface(swigCPtr, this, s);
    }

    public SCCredentialsType getCredentialsType()
    {
        return SCCredentialsType.swigToEnum(sclibJNI.SCIServiceDescriptor_getCredentialsType(swigCPtr, this));
    }

    public String getDescription()
    {
        return sclibJNI.SCIServiceDescriptor_getDescription(swigCPtr, this);
    }

    public String getID()
    {
        return sclibJNI.SCIServiceDescriptor_getID(swigCPtr, this);
    }

    public String getLogoStr()
    {
        return sclibJNI.SCIServiceDescriptor_getLogoStr(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getLogoType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIServiceDescriptor_getLogoType(swigCPtr, this));
    }

    public String getShortTitle()
    {
        return sclibJNI.SCIServiceDescriptor_getShortTitle(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCIServiceDescriptor_getTitle(swigCPtr, this);
    }

    public boolean hasAccount()
    {
        return sclibJNI.SCIServiceDescriptor_hasAccount(swigCPtr, this);
    }

    public boolean isPreload()
    {
        return sclibJNI.SCIServiceDescriptor_isPreload(swigCPtr, this);
    }

    public boolean isSonosSoundLab()
    {
        return sclibJNI.SCIServiceDescriptor_isSonosSoundLab(swigCPtr, this);
    }

    public boolean supportsMergableTrial()
    {
        return sclibJNI.SCIServiceDescriptor_supportsMergableTrial(swigCPtr, this);
    }

    public boolean supportsTrial()
    {
        return sclibJNI.SCIServiceDescriptor_supportsTrial(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceDescriptor");
    private long swigCPtr;

}
