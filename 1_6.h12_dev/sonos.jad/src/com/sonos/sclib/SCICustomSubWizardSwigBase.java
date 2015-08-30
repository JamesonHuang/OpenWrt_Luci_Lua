// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCICustomSubWizard, sclibJNI

public class SCICustomSubWizardSwigBase extends SCICustomSubWizard
{

    public SCICustomSubWizardSwigBase()
    {
        SCICustomSubWizardSwigBase(sclibJNI.new_SCICustomSubWizardSwigBase(), true);
        sclibJNI.SCICustomSubWizardSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCICustomSubWizardSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCICustomSubWizard(sclibJNI.SWIGSCICustomSubWizardSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCICustomSubWizardSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCICustomSubWizardSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        long l;
        if(scicustomsubwizardswigbase == null)
            l = 0L;
        else
            l = scicustomsubwizardswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCICustomSubWizardSwigBase)
            s = sclibJNI.SCICustomSubWizardSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCICustomSubWizardSwigBase_dumpSCIObjSwigExplicitSCICustomSubWizardSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCICustomSubWizardSwigBase");
    private long swigCPtr;

}
