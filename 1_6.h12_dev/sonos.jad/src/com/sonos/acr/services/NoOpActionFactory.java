// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

public class NoOpActionFactory extends SCIActionFactorySwigBase
{
    class NoopAction extends SCIActionSwigBase
    {

        public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
        {
            sciactioncontext.getPropertyBag().setBoolProp(sclibConstants.SCACTN_BOOLPROP_ACTION_NOT_PERFORMED, true);
            SLog.e(NoOpActionFactory.LOG_TAG, (new StringBuilder()).append("Perform called on NOOp Action! created by: ").append(callingMethod).toString());
            return SCActionCompletionStatus.DONE_WITH_ACTION;
        }

        String callingMethod;
        final NoOpActionFactory this$0;

        NoopAction(String s)
        {
            this$0 = NoOpActionFactory.this;
            super();
            callingMethod = s;
        }
    }


    public NoOpActionFactory()
    {
    }

    public SCIAction createBrowsePickerAction(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        return new NoopAction("createBrowsePicker");
    }

    public SCIAction createCustomUIAction(String s, String s1)
    {
        return new NoopAction("createCustomUIAction");
    }

    public SCIAction createDisplayBrowseStackAction(SCIBrowseStackManager scibrowsestackmanager)
    {
        return new NoopAction("createDisplayBrowseStackAction");
    }

    public SCIAction createDisplayCustomControlAction(SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        return new NoopAction("createDisplayCustomControlAction");
    }

    public SCIAction createDisplayDatePickerAction(String s, String s1, SCISystemTime scisystemtime)
    {
        return new NoopAction("createDisplayDatePickerAction");
    }

    public SCIAction createDisplayDualTextInputAction(String s, String s1, String s2, String s3, SCIStringInput scistringinput, String s4, String s5, 
            SCIStringInput scistringinput1)
    {
        return new NoopAction("createDisplayDualTextInputAction");
    }

    public SCIAction createDisplayIntegerInputAction(String s, String s1, String s2, int i, int j, int k)
    {
        return new NoopAction("createDisplayIntegerInputAction");
    }

    public SCIAction createDisplayMenuAction(String s, String s1, String s2, SCIStringArray scistringarray, int i)
    {
        return new NoopAction("createDisplayMenuAction");
    }

    public SCIAction createDisplayMenuAndTextInputAction(String s, String s1, String s2, boolean flag, SCIEnumerator scienumerator, int i, SCIResource sciresource, 
            int j, String s3)
    {
        return new NoopAction("createDisplayMenuAndTextInputAction");
    }

    public SCIAction createDisplayMenuPopupAction(String s, SCIStringArray scistringarray, int i, String s1, boolean flag, boolean flag1)
    {
        return new NoopAction("createDisplayMenuPopupAction");
    }

    public SCIAction createDisplayMessagePopupAction(String s, int i)
    {
        return new NoopAction("createDisplayMessagePopupAction");
    }

    public SCIAction createDisplayTextInputAction(String s, String s1, String s2, SCIStringInput scistringinput)
    {
        return new NoopAction("createDisplayTextInputAction");
    }

    public SCIAction createDisplayTextPaneAction(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        return new NoopAction("createDisplayTextPaneAction");
    }

    public SCIAction createDisplayTimePickerAction(String s, String s1, SCITime scitime)
    {
        return new NoopAction("createDisplayTimePickerAction");
    }

    public SCIAction createDisplayWizardAction(SCIWizard sciwizard)
    {
        return new NoopAction("createDisplayWizardAction");
    }

    public SCIAction createNavigationAction(SCNavigationActionType scnavigationactiontype, SCIPropertyBag scipropertybag)
    {
        return new NoopAction("createNavigationAction");
    }

    public SCIAction createPopBrowseAction(int i)
    {
        return new NoopAction("createPopBrowseAction");
    }

    public SCIAction createPushSCUriAction(String s, String s1, boolean flag)
    {
        return new NoopAction("createPushSCUriAction");
    }

    public SCIAction createRunAsyncIOOperationAction(SCIOp sciop)
    {
        return new NoopAction("createRunAsyncIOOperationAction");
    }

    public SCIAction createRunWizardAction(SCRunWizardActionType scrunwizardactiontype)
    {
        return new NoopAction("createRunWizardAction");
    }

    private static final String LOG_TAG = com/sonos/acr/services/NoOpActionFactory.getSimpleName();


}
