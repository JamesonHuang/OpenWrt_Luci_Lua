// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCILibrary, SCISetupWizard, 
//            SCIArtworkCacheManager, SCIEnumerator, SCINetstartListener, SCIEventSink

public class SCISystem extends SCIObj
{

    protected SCISystem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISystemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISystem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISystem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISystem scisystem)
    {
        long l;
        if(scisystem == null)
            l = 0L;
        else
            l = scisystem.swigCPtr;
        return l;
    }

    public static SCISystem getInterface(SCILibrary scilibrary)
    {
        long l = sclibJNI.SCISystem_getInterface(SCILibrary.getCPtr(scilibrary), scilibrary);
        SCISystem scisystem;
        if(l == 0L)
            scisystem = null;
        else
            scisystem = new SCISystem(l, true);
        return scisystem;
    }

    public String blockingFetchSonosNetInfo()
    {
        return sclibJNI.SCISystem_blockingFetchSonosNetInfo(swigCPtr, this);
    }

    public void cleanupOnlineUpdateFiles()
    {
        sclibJNI.SCISystem_cleanupOnlineUpdateFiles(swigCPtr, this);
    }

    public SCISetupWizard createSetupWizard()
    {
        long l = sclibJNI.SCISystem_createSetupWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
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

    public boolean factoryResetConfigFiles()
    {
        return sclibJNI.SCISystem_factoryResetConfigFiles(swigCPtr, this);
    }

    public SCIArtworkCacheManager getArtworkCacheManager()
    {
        long l = sclibJNI.SCISystem_getArtworkCacheManager(swigCPtr, this);
        SCIArtworkCacheManager sciartworkcachemanager;
        if(l == 0L)
            sciartworkcachemanager = null;
        else
            sciartworkcachemanager = new SCIArtworkCacheManager(l, true);
        return sciartworkcachemanager;
    }

    public String getCopyright()
    {
        return sclibJNI.SCISystem_getCopyright(swigCPtr, this);
    }

    public SCIEnumerator getDebugWizardActions()
    {
        long l = sclibJNI.SCISystem_getDebugWizardActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCINetstartListener getNetstartListener()
    {
        long l = sclibJNI.SCISystem_getNetstartListener(swigCPtr, this);
        SCINetstartListener scinetstartlistener;
        if(l == 0L)
            scinetstartlistener = null;
        else
            scinetstartlistener = new SCINetstartListener(l, true);
        return scinetstartlistener;
    }

    public boolean isFactoryReset()
    {
        return sclibJNI.SCISystem_isFactoryReset(swigCPtr, this);
    }

    public boolean isRunningBackgroundOperations()
    {
        return sclibJNI.SCISystem_isRunningBackgroundOperations(swigCPtr, this);
    }

    public boolean isSonosNetAllowed()
    {
        return sclibJNI.SCISystem_isSonosNetAllowed(swigCPtr, this);
    }

    public boolean needToResumeOnlineUpdate()
    {
        return sclibJNI.SCISystem_needToResumeOnlineUpdate(swigCPtr, this);
    }

    public void resetSonosNetPassword()
    {
        sclibJNI.SCISystem_resetSonosNetPassword(swigCPtr, this);
    }

    public void setNetstartListener(SCINetstartListener scinetstartlistener)
    {
        sclibJNI.SCISystem_setNetstartListener(swigCPtr, this, SCINetstartListener.getCPtr(scinetstartlistener), scinetstartlistener);
    }

    public void setSonosNetAllowed(boolean flag)
    {
        sclibJNI.SCISystem_setSonosNetAllowed(swigCPtr, this, flag);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCISystem_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCISystem_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISystem");
    private long swigCPtr;

}
