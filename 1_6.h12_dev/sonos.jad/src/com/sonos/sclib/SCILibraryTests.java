// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIStringArray

public class SCILibraryTests extends SCIObj
{

    protected SCILibraryTests(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILibraryTestsUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILibraryTests(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILibraryTests(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILibraryTests scilibrarytests)
    {
        long l;
        if(scilibrarytests == null)
            l = 0L;
        else
            l = scilibrarytests.swigCPtr;
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

    public SCIStringArray getWizardImageResourceIds()
    {
        long l = sclibJNI.SCILibraryTests_getWizardImageResourceIds(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public void testDisplayMenuPopupAction()
    {
        sclibJNI.SCILibraryTests_testDisplayMenuPopupAction(swigCPtr, this);
    }

    public void testDisplayMessagePopupAction()
    {
        sclibJNI.SCILibraryTests_testDisplayMessagePopupAction(swigCPtr, this);
    }

    public void testTextInputAction()
    {
        sclibJNI.SCILibraryTests_testTextInputAction(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILibraryTests");
    private long swigCPtr;

}
