// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIBrowseDataSource, SCIAction, 
//            SCIBrowseStackManager, SCDisplayCustomControlActionType, SCISystemTime, SCIStringInput, 
//            SCIStringArray, SCIEnumerator, SCIResource, SCIInfoViewTextPaneMetadata, 
//            SCITime, SCIWizard, SCNavigationActionType, SCIPropertyBag, 
//            SCIOp, SCRunWizardActionType

public class SCIActionFactory extends SCIObj
{

    protected SCIActionFactory(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIActionFactoryUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionFactory(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionFactory(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionFactory sciactionfactory)
    {
        long l;
        if(sciactionfactory == null)
            l = 0L;
        else
            l = sciactionfactory.swigCPtr;
        return l;
    }

    public SCIAction createBrowsePickerAction(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        long l = sclibJNI.SCIActionFactory_createBrowsePickerAction(swigCPtr, this, SCIBrowseDataSource.getCPtr(scibrowsedatasource), scibrowsedatasource, s);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createCustomUIAction(String s, String s1)
    {
        long l = sclibJNI.SCIActionFactory_createCustomUIAction(swigCPtr, this, s, s1);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayBrowseStackAction(SCIBrowseStackManager scibrowsestackmanager)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayBrowseStackAction(swigCPtr, this, SCIBrowseStackManager.getCPtr(scibrowsestackmanager), scibrowsestackmanager);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayCustomControlAction(SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayCustomControlAction(swigCPtr, this, scdisplaycustomcontrolactiontype.swigValue());
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayDatePickerAction(String s, String s1, SCISystemTime scisystemtime)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayDatePickerAction(swigCPtr, this, s, s1, SCISystemTime.getCPtr(scisystemtime), scisystemtime);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayDualTextInputAction(String s, String s1, String s2, String s3, SCIStringInput scistringinput, String s4, String s5, 
            SCIStringInput scistringinput1)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayDualTextInputAction(swigCPtr, this, s, s1, s2, s3, SCIStringInput.getCPtr(scistringinput), scistringinput, s4, s5, SCIStringInput.getCPtr(scistringinput1), scistringinput1);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayInfoViewAction(String s, String s1)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayInfoViewAction(swigCPtr, this, s, s1);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayIntegerInputAction(String s, String s1, String s2, int i, int j, int k)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayIntegerInputAction(swigCPtr, this, s, s1, s2, i, j, k);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayMenuAction(String s, String s1, String s2, SCIStringArray scistringarray, int i)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayMenuAction(swigCPtr, this, s, s1, s2, SCIStringArray.getCPtr(scistringarray), scistringarray, i);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayMenuAndTextInputAction(String s, String s1, String s2, boolean flag, SCIEnumerator scienumerator, int i, SCIResource sciresource, 
            int j, String s3)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayMenuAndTextInputAction(swigCPtr, this, s, s1, s2, flag, SCIEnumerator.getCPtr(scienumerator), scienumerator, i, SCIResource.getCPtr(sciresource), sciresource, j, s3);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayMenuPopupAction(String s, SCIStringArray scistringarray, int i, String s1, boolean flag, boolean flag1)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayMenuPopupAction(swigCPtr, this, s, SCIStringArray.getCPtr(scistringarray), scistringarray, i, s1, flag, flag1);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayMessagePopupAction(String s, int i)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayMessagePopupAction(swigCPtr, this, s, i);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayTextInputAction(String s, String s1, String s2, SCIStringInput scistringinput)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayTextInputAction(swigCPtr, this, s, s1, s2, SCIStringInput.getCPtr(scistringinput), scistringinput);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayTextPaneAction(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayTextPaneAction(swigCPtr, this, s, s1, SCIInfoViewTextPaneMetadata.getCPtr(sciinfoviewtextpanemetadata), sciinfoviewtextpanemetadata);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayTimePickerAction(String s, String s1, SCITime scitime)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayTimePickerAction(swigCPtr, this, s, s1, SCITime.getCPtr(scitime), scitime);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createDisplayWizardAction(SCIWizard sciwizard)
    {
        long l = sclibJNI.SCIActionFactory_createDisplayWizardAction(swigCPtr, this, SCIWizard.getCPtr(sciwizard), sciwizard);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createNavigationAction(SCNavigationActionType scnavigationactiontype, SCIPropertyBag scipropertybag)
    {
        long l = sclibJNI.SCIActionFactory_createNavigationAction(swigCPtr, this, scnavigationactiontype.swigValue(), SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createPopBrowseAction(int i)
    {
        long l = sclibJNI.SCIActionFactory_createPopBrowseAction(swigCPtr, this, i);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createPushSCUriAction(String s, String s1, boolean flag)
    {
        long l = sclibJNI.SCIActionFactory_createPushSCUriAction(swigCPtr, this, s, s1, flag);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createRunAsyncIOOperationAction(SCIOp sciop)
    {
        long l = sclibJNI.SCIActionFactory_createRunAsyncIOOperationAction(swigCPtr, this, SCIOp.getCPtr(sciop), sciop);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createRunWizardAction(SCRunWizardActionType scrunwizardactiontype)
    {
        long l = sclibJNI.SCIActionFactory_createRunWizardAction(swigCPtr, this, scrunwizardactiontype.swigValue());
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionFactory");
    private long swigCPtr;

}
