// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIWizard, SCIPropertyBag

public class SCICustomSubWizard extends SCIObj
{

    protected SCICustomSubWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCICustomSubWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCICustomSubWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCICustomSubWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCICustomSubWizard scicustomsubwizard)
    {
        long l;
        if(scicustomsubwizard == null)
            l = 0L;
        else
            l = scicustomsubwizard.swigCPtr;
        return l;
    }

    public boolean canClientCancelWizard()
    {
        return sclibJNI.SCICustomSubWizard_canClientCancelWizard(swigCPtr, this);
    }

    public boolean canClientTransitionToNextState()
    {
        return sclibJNI.SCICustomSubWizard_canClientTransitionToNextState(swigCPtr, this);
    }

    public boolean canClientTransitionToPreviousState()
    {
        return sclibJNI.SCICustomSubWizard_canClientTransitionToPreviousState(swigCPtr, this);
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

    public int enter(SCIWizard sciwizard)
    {
        return sclibJNI.SCICustomSubWizard_enter(swigCPtr, this, SCIWizard.getCPtr(sciwizard), sciwizard);
    }

    public void exit()
    {
        sclibJNI.SCICustomSubWizard_exit(swigCPtr, this);
    }

    public int getNextStateID()
    {
        return sclibJNI.SCICustomSubWizard_getNextStateID(swigCPtr, this);
    }

    public SCIPropertyBag getPropertyBag()
    {
        long l = sclibJNI.SCICustomSubWizard_getPropertyBag(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public String getRecommendedLabelForNextState()
    {
        return sclibJNI.SCICustomSubWizard_getRecommendedLabelForNextState(swigCPtr, this);
    }

    public void onSubWizardStateTransition(int i)
    {
        sclibJNI.SCICustomSubWizard_onSubWizardStateTransition(swigCPtr, this, i);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCICustomSubWizard");
    private long swigCPtr;

}
