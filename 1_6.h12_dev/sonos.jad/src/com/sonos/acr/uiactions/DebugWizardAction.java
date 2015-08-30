// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction, RunWizardAction

public class DebugWizardAction extends UIAction
{
    private class DebugWizardItem
    {

        public SCIActionDescriptor getWizard()
        {
            return mWizard;
        }

        public SCRunWizardActionType getmWizardType()
        {
            return mWizardType;
        }

        public String toString()
        {
            if(mWizard == null) goto _L2; else goto _L1
_L1:
            String s = mWizard.getLabel();
_L4:
            return s;
_L2:
            class _cls2
            {

                static final int $SwitchMap$com$sonos$sclib$SCRunWizardActionType[];

                static 
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType = new int[SCRunWizardActionType.values().length];
                    NoSuchFieldError nosuchfielderror21;
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CONFIGURE_SUB.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_RECALIBRATE_SUB.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_REGISTRATION.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SHAREUSAGEDATA.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CHANGE_SPEAKERS.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SETUP.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ADDPLAYERORSUB.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ADDBOOST.ordinal()] = 8;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SETUP_UNCONFIGURED.ordinal()] = 9;
                    }
                    catch(NoSuchFieldError nosuchfielderror8) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE.ordinal()] = 10;
                    }
                    catch(NoSuchFieldError nosuchfielderror9) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_JOINANOTHERHOUSEHOLD.ordinal()] = 11;
                    }
                    catch(NoSuchFieldError nosuchfielderror10) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_ADD.ordinal()] = 12;
                    }
                    catch(NoSuchFieldError nosuchfielderror11) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD.ordinal()] = 13;
                    }
                    catch(NoSuchFieldError nosuchfielderror12) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_CHANGENICKNAME.ordinal()] = 14;
                    }
                    catch(NoSuchFieldError nosuchfielderror13) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_REPLACE.ordinal()] = 15;
                    }
                    catch(NoSuchFieldError nosuchfielderror14) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_REAUTHORIZE.ordinal()] = 16;
                    }
                    catch(NoSuchFieldError nosuchfielderror15) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_SUBSCRIBE.ordinal()] = 17;
                    }
                    catch(NoSuchFieldError nosuchfielderror16) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE.ordinal()] = 18;
                    }
                    catch(NoSuchFieldError nosuchfielderror17) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU.ordinal()] = 19;
                    }
                    catch(NoSuchFieldError nosuchfielderror18) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY.ordinal()] = 20;
                    }
                    catch(NoSuchFieldError nosuchfielderror19) { }
                    try
                    {
                        $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SOUNDBAR.ordinal()] = 21;
                    }
                    catch(NoSuchFieldError nosuchfielderror20) { }
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SONOSNET_SETUP.ordinal()] = 22;
_L2:
                    return;
                    nosuchfielderror21;
                    if(true) goto _L2; else goto _L1
_L1:
                }
            }

            switch(_cls2..SwitchMap.com.sonos.sclib.SCRunWizardActionType[mWizardType.ordinal()])
            {
            default:
                s = "";
                break;

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
                s = "Setup Wizard";
                break;

            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
            case 16: // '\020'
            case 17: // '\021'
                s = "Music Service Wizard";
                break;

            case 18: // '\022'
            case 19: // '\023'
            case 20: // '\024'
                s = "Online Update Wizard";
                break;

            case 21: // '\025'
                s = "Soundbar Wizard";
                break;

            case 22: // '\026'
                s = "Configure Wireless Wizard";
                break;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private SCIActionDescriptor mWizard;
        private SCRunWizardActionType mWizardType;
        final DebugWizardAction this$0;

        public DebugWizardItem(SCIActionDescriptor sciactiondescriptor)
        {
            this$0 = DebugWizardAction.this;
            super();
            mWizard = sciactiondescriptor;
        }

        public DebugWizardItem(SCRunWizardActionType scrunwizardactiontype)
        {
            this$0 = DebugWizardAction.this;
            super();
            mWizardType = scrunwizardactiontype;
        }
    }


    public DebugWizardAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
    }

    public SCActionCompletionStatus perform(final SCIActionContext sciActionContext)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(currentContext);
        builder.setTitle(0x7f0c0043);
        ArrayList arraylist = new ArrayList();
        arraylist.add(new DebugWizardItem(SCRunWizardActionType.SCACTN_RUNWIZ_SETUP));
        arraylist.add(new DebugWizardItem(SCRunWizardActionType.SCACTN_RUNWIZ_MUSICSERVICE_ADD));
        arraylist.add(new DebugWizardItem(SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE));
        arraylist.add(new DebugWizardItem(SCRunWizardActionType.SCACTN_RUNWIZ_SOUNDBAR));
        arraylist.add(new DebugWizardItem(SCRunWizardActionType.SCACTN_RUNWIZ_SONOSNET_SETUP));
        SCIEnumerator scienumerator = LibraryUtils.getSystem().getDebugWizardActions();
        scienumerator.reset();
        do
        {
            if(!scienumerator.moveNext())
                break;
            SCIActionDescriptor sciactiondescriptor = (SCIActionDescriptor)scienumerator.getCurrent(sclibConstants.SCIACTION_DESCRIPTOR_INTERFACE);
            if(sciactiondescriptor != null)
                arraylist.add(new DebugWizardItem(sciactiondescriptor));
        } while(true);
        final ArrayAdapter adapter = new ArrayAdapter(currentContext, 0x7f030096, arraylist);
        builder.setSingleChoiceItems(adapter, -1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                wizardDialog.dismiss();
                DebugWizardItem debugwizarditem = (DebugWizardItem)adapter.getItem(i);
                if(debugwizarditem.getWizard() != null)
                {
                    SCIActionNoArgDescriptor sciactionnoargdescriptor = (SCIActionNoArgDescriptor)LibraryUtils.cast(debugwizarditem.getWizard(), com/sonos/sclib/SCIActionNoArgDescriptor);
                    if(sciactionnoargdescriptor != null)
                    {
                        SCIActionContext sciactioncontext = sciactionnoargdescriptor.getAction();
                        if(sciactioncontext != null)
                        {
                            sciactioncontext.getPropertyBag().setBoolProp("DEBUG_WIZARD", true);
                            sciactioncontext.perform();
                        }
                    }
                } else
                {
                    SCRunWizardActionType scrunwizardactiontype = debugwizarditem.getmWizardType();
                    (new RunWizardAction(currentContext, scrunwizardactiontype, true)).perform(sciActionContext);
                }
            }

            final DebugWizardAction this$0;
            final ArrayAdapter val$adapter;
            final SCIActionContext val$sciActionContext;

            
            {
                this$0 = DebugWizardAction.this;
                adapter = arrayadapter;
                sciActionContext = sciactioncontext;
                super();
            }
        }
);
        wizardDialog = builder.create();
        wizardDialog.show();
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    AlertDialog wizardDialog;
}
