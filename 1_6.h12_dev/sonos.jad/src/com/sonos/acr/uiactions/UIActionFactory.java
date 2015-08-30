// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            ShowBrowsePickerAction, ToggleBooleanPreferenceAction, CrashAction, CrashNativeAction, 
//            DebugWizardAction, DebugSvgAction, ShowBrowseStackAction, DisplayCustomControlAction, 
//            DisplayDatePickerAction, PushSCUriAction, DisplaySliderProgressAction, DisplayMenuAction, 
//            DisplayMenuAndTextInputAction, DisplayMenuPopupAction, DisplayMessagePopupAction, DisplayTextInputAction, 
//            DisplayTextPaneAction, DisplayTimePickerAction, RunWizardAction, NavigationAction, 
//            PopBrowseStackAction, RunAsyncIOOperationAction

public class UIActionFactory extends SCIActionFactorySwigBase
{

    public UIActionFactory(SonosActivity sonosactivity)
    {
        currentContext = sonosactivity;
    }

    public SCIAction createBrowsePickerAction(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        return new ShowBrowsePickerAction(currentContext, scibrowsedatasource, s);
    }

    public SCIAction createCustomUIAction(String s, String s1)
    {
        Object obj;
        if(s.equals(com/sonos/acr/uiactions/ToggleBooleanPreferenceAction.getSimpleName()))
            obj = new ToggleBooleanPreferenceAction(currentContext, s1);
        else
        if(s.equals(com/sonos/acr/uiactions/CrashAction.getSimpleName()))
            obj = new CrashAction(currentContext, s1);
        else
        if(s.equals(com/sonos/acr/uiactions/CrashNativeAction.getSimpleName()))
            obj = new CrashNativeAction(currentContext);
        else
        if(s.equals(com/sonos/acr/uiactions/DebugWizardAction.getSimpleName()))
            obj = new DebugWizardAction(currentContext);
        else
        if(s.equals(com/sonos/acr/uiactions/DebugSvgAction.getSimpleName()))
            obj = new DebugSvgAction(currentContext);
        else
            obj = null;
        return ((SCIAction) (obj));
    }

    public SCIAction createDisplayBrowseStackAction(SCIBrowseStackManager scibrowsestackmanager)
    {
        return new ShowBrowseStackAction(currentContext, scibrowsestackmanager);
    }

    public SCIAction createDisplayCustomControlAction(SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        return DisplayCustomControlAction.createAction(currentContext, scdisplaycustomcontrolactiontype);
    }

    public SCIAction createDisplayDatePickerAction(String s, String s1, SCISystemTime scisystemtime)
    {
        return new DisplayDatePickerAction(currentContext, s, scisystemtime);
    }

    public SCIAction createDisplayDualTextInputAction(String s, String s1, String s2, String s3, SCIStringInput scistringinput, String s4, String s5, 
            SCIStringInput scistringinput1)
    {
        SLog.w("UIActionFactory", "DisplayDualTextInputAction should not be called on the ACR");
        return null;
    }

    public SCIAction createDisplayInfoViewAction(String s, String s1)
    {
        return new PushSCUriAction(currentContext, s1, s, false);
    }

    public SCIAction createDisplayIntegerInputAction(String s, String s1, String s2, int i, int j, int k)
    {
        return new DisplaySliderProgressAction(currentContext, s, s1, s2, i, j, k);
    }

    public SCIAction createDisplayMenuAction(String s, String s1, String s2, SCIStringArray scistringarray, int i)
    {
        SLog.d("UIActionFactory", (new StringBuilder()).append("DisplayMenuAction initial selection: ").append(i).toString());
        return new DisplayMenuAction(currentContext, s, s1, s2, scistringarray, i);
    }

    public SCIAction createDisplayMenuAndTextInputAction(String s, String s1, String s2, boolean flag, SCIEnumerator scienumerator, int i, SCIResource sciresource, 
            int j, String s3)
    {
        return new DisplayMenuAndTextInputAction(currentContext, s, s1, s2, flag, scienumerator, i, sciresource, j, s3);
    }

    public SCIAction createDisplayMenuPopupAction(String s, SCIStringArray scistringarray, int i, String s1, boolean flag, boolean flag1)
    {
        return new DisplayMenuPopupAction(currentContext, s, scistringarray, i, s1, flag);
    }

    public SCIAction createDisplayMessagePopupAction(String s, int i)
    {
        return new DisplayMessagePopupAction(currentContext, s, i);
    }

    public SCIAction createDisplayTextInputAction(String s, String s1, String s2, SCIStringInput scistringinput)
    {
        return new DisplayTextInputAction(currentContext, s, s1, s2, scistringinput);
    }

    public SCIAction createDisplayTextPaneAction(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        return new DisplayTextPaneAction(currentContext, s, s1, sciinfoviewtextpanemetadata);
    }

    public SCIAction createDisplayTimePickerAction(String s, String s1, SCITime scitime)
    {
        return new DisplayTimePickerAction(currentContext, s, scitime);
    }

    public SCIAction createDisplayWizardAction(SCIWizard sciwizard)
    {
        return new RunWizardAction(currentContext, sciwizard);
    }

    public SCIAction createNavigationAction(SCNavigationActionType scnavigationactiontype, SCIPropertyBag scipropertybag)
    {
        return new NavigationAction(currentContext, scnavigationactiontype, scipropertybag);
    }

    public SCIAction createPopBrowseAction(int i)
    {
        return new PopBrowseStackAction(currentContext, i);
    }

    public SCIAction createPushSCUriAction(String s, String s1, boolean flag)
    {
        return new PushSCUriAction(currentContext, s1, s, flag);
    }

    public SCIAction createRunAsyncIOOperationAction(SCIOp sciop)
    {
        return new RunAsyncIOOperationAction(currentContext, sciop);
    }

    public SCIAction createRunWizardAction(SCRunWizardActionType scrunwizardactiontype)
    {
        return new RunWizardAction(currentContext, scrunwizardactiontype, false);
    }

    public static final String PROPERTY_ICON_ID = "iconId";
    final SonosActivity currentContext;
}
