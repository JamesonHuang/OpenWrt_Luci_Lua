// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp, SCIActionContext, 
//            SCIOpReplaceAccount, SCIEnumerator

public class SCIServiceAccount extends SCIObj
{

    protected SCIServiceAccount(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIServiceAccountUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIServiceAccount(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIServiceAccount(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIServiceAccount sciserviceaccount)
    {
        long l;
        if(sciserviceaccount == null)
            l = 0L;
        else
            l = sciserviceaccount.swigCPtr;
        return l;
    }

    public boolean canChangePassword()
    {
        return sclibJNI.SCIServiceAccount_canChangePassword(swigCPtr, this);
    }

    public boolean canReauthorizeAccount()
    {
        return sclibJNI.SCIServiceAccount_canReauthorizeAccount(swigCPtr, this);
    }

    public boolean canReplaceAccount()
    {
        return sclibJNI.SCIServiceAccount_canReplaceAccount(swigCPtr, this);
    }

    public SCIOp createMigrateTrialAccountOp(String s, String s1)
    {
        long l = sclibJNI.SCIServiceAccount_createMigrateTrialAccountOp(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIActionContext createRemoveAccountAction()
    {
        long l = sclibJNI.SCIServiceAccount_createRemoveAccountAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIOp createRemoveAccountOp()
    {
        long l = sclibJNI.SCIServiceAccount_createRemoveAccountOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOpReplaceAccount createReplaceAccountOp(String s, String s1)
    {
        long l = sclibJNI.SCIServiceAccount_createReplaceAccountOp(swigCPtr, this, s, s1);
        SCIOpReplaceAccount sciopreplaceaccount;
        if(l == 0L)
            sciopreplaceaccount = null;
        else
            sciopreplaceaccount = new SCIOpReplaceAccount(l, true);
        return sciopreplaceaccount;
    }

    public SCIOp createReplaceOAuthAccountOp(String s, String s1, String s2)
    {
        long l = sclibJNI.SCIServiceAccount_createReplaceOAuthAccountOp(swigCPtr, this, s, s1, s2);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetAccountNicknameOp(String s)
    {
        long l = sclibJNI.SCIServiceAccount_createSetAccountNicknameOp(swigCPtr, this, s);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createUpdateAccountPasswordOp(String s)
    {
        long l = sclibJNI.SCIServiceAccount_createUpdateAccountPasswordOp(swigCPtr, this, s);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    public SCIEnumerator getAllActions()
    {
        long l = sclibJNI.SCIServiceAccount_getAllActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getID()
    {
        return sclibJNI.SCIServiceAccount_getID(swigCPtr, this);
    }

    public String getLearnMoreText()
    {
        return sclibJNI.SCIServiceAccount_getLearnMoreText(swigCPtr, this);
    }

    public String getLogoStr()
    {
        return sclibJNI.SCIServiceAccount_getLogoStr(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getLogoType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIServiceAccount_getLogoType(swigCPtr, this));
    }

    public String getLogonString()
    {
        return sclibJNI.SCIServiceAccount_getLogonString(swigCPtr, this);
    }

    public String getNickname()
    {
        return sclibJNI.SCIServiceAccount_getNickname(swigCPtr, this);
    }

    public String getServiceDescriptorID()
    {
        return sclibJNI.SCIServiceAccount_getServiceDescriptorID(swigCPtr, this);
    }

    public String getShortTitle()
    {
        return sclibJNI.SCIServiceAccount_getShortTitle(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCIServiceAccount_getTitle(swigCPtr, this);
    }

    public boolean isAnonymous()
    {
        return sclibJNI.SCIServiceAccount_isAnonymous(swigCPtr, this);
    }

    public boolean isTrialAccount()
    {
        return sclibJNI.SCIServiceAccount_isTrialAccount(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIServiceAccount_isValid(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIServiceAccount");
    private long swigCPtr;

}
