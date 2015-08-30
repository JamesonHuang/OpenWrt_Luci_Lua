// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.content.Intent;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.AppDataStore;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.anonymous.AnonymousWizard;
import com.sonos.acr.wizards.configwireless.ConfigureWirelessWizard;
import com.sonos.acr.wizards.musicservices.MusicServicesWizard;
import com.sonos.acr.wizards.onlineupdate.OnlineUpdateWizard;
import com.sonos.acr.wizards.setup.SetupWizard;
import com.sonos.acr.wizards.sonosnet.SonosnetWizard;
import com.sonos.acr.wizards.soundbar.SoundbarWizard;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class RunWizardAction extends UIAction
{

    public RunWizardAction(SonosActivity sonosactivity, SCIWizard sciwizard)
    {
        super(sonosactivity);
        sciWizard = sciwizard;
        wizardActionType = SCRunWizardActionType.SCACTN_RUNWIZ_INVALID;
    }

    public RunWizardAction(SonosActivity sonosactivity, SCRunWizardActionType scrunwizardactiontype, boolean flag)
    {
        super(sonosactivity);
        wizardActionType = scrunwizardactiontype;
        debug = flag;
    }

    private Wizard createWizard(SCIActionContext sciactioncontext)
    {
        String s;
label0:
        {
            s = getDeviceId(sciactioncontext);
            if(!debug)
                break label0;
            Iterator iterator = LibraryUtils.getHousehold().getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_ANY).iterator();
            ZoneDevice zonedevice;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                zonedevice = (ZoneDevice)iterator.next();
            } while(zonedevice.getDeviceModel() != com.sonos.sclib.SCIDevice.DeviceModel.DM_SOUNDBAR);
            s = zonedevice.getId();
        }
        sciactioncontext.getPropertyBag().setStrProp("SOUNDBAR_DEVICE_ID", s);
        if(sciWizard == null) goto _L2; else goto _L1
_L1:
        Object obj;
        debug = sciactioncontext.getPropertyBag().getBoolProp("DEBUG_WIZARD");
        SCISetupWizard scisetupwizard = (SCISetupWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCISetupWizard);
        if(scisetupwizard != null)
        {
            obj = new SetupWizard(this, sciactioncontext, wizardActionType, scisetupwizard);
        } else
        {
            SCIMusicServiceWizard scimusicservicewizard = (SCIMusicServiceWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCIMusicServiceWizard);
            if(scimusicservicewizard != null)
            {
                obj = new MusicServicesWizard(this, sciactioncontext, wizardActionType, scimusicservicewizard);
            } else
            {
                SCIOnlineUpdateWizard scionlineupdatewizard = (SCIOnlineUpdateWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCIOnlineUpdateWizard);
                if(scionlineupdatewizard != null)
                {
                    obj = new OnlineUpdateWizard(this, sciactioncontext, wizardActionType, scionlineupdatewizard);
                } else
                {
                    SCISonosNetSetupWizard scisonosnetsetupwizard = (SCISonosNetSetupWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCISonosNetSetupWizard);
                    if(scisonosnetsetupwizard != null)
                    {
                        obj = new SonosnetWizard(this, sciactioncontext, wizardActionType, scisonosnetsetupwizard);
                    } else
                    {
                        SCISoundbarWizard scisoundbarwizard = (SCISoundbarWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCISoundbarWizard);
                        if(scisoundbarwizard != null)
                        {
                            obj = new SoundbarWizard(this, sciactioncontext, wizardActionType, scisoundbarwizard);
                        } else
                        {
                            SCIConfigureWirelessWizard sciconfigurewirelesswizard = (SCIConfigureWirelessWizard)LibraryUtils.cast(sciWizard, com/sonos/sclib/SCIConfigureWirelessWizard);
                            if(sciconfigurewirelesswizard != null)
                            {
                                obj = new ConfigureWirelessWizard(this, sciactioncontext, wizardActionType, sciconfigurewirelesswizard);
                            } else
                            {
                                SLog.i("RunWizardAction", "createWizard called with anonymous sciWizard!");
                                obj = new AnonymousWizard(this, sciactioncontext, sciWizard);
                            }
                        }
                    }
                }
            }
        }
_L4:
        return ((Wizard) (obj));
_L2:
        class _cls1
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
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SONOSNET_SETUP.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SOUNDBAR.ordinal()] = 22;
_L2:
                return;
                nosuchfielderror21;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.com.sonos.sclib.SCRunWizardActionType[wizardActionType.ordinal()])
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Unknown SCRunWizardActionType: ").append(wizardActionType).toString());

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
            obj = new SetupWizard(this, sciactioncontext, wizardActionType, null);
            break;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
            obj = new MusicServicesWizard(this, sciactioncontext, wizardActionType, null);
            break;

        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
            obj = new OnlineUpdateWizard(this, sciactioncontext, wizardActionType, null);
            break;

        case 21: // '\025'
            if(debug)
                obj = new ConfigureWirelessWizard(this, sciactioncontext, SCRunWizardActionType.SCACTN_RUNWIZ_INVALID, LibraryUtils.getHousehold().createConfigureWirelessWizard());
            else
                obj = new SonosnetWizard(this, sciactioncontext, wizardActionType, null);
            break;

        case 22: // '\026'
            obj = new SoundbarWizard(this, sciactioncontext, wizardActionType, null);
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String getDeviceId(SCIActionContext sciactioncontext)
    {
        return sclib.SCLibGetDeviceIDFromDeviceSettingsSCUri(sciactioncontext.getPropertyBag().getStrProp(sclibConstants.SCRUNWIZARDITEMPROP_WIZARD_URI));
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        Intent intent = (new Intent(currentContext, com/sonos/acr/wizards/SonosWizardActivity)).addFlags(0x4000000);
        Wizard wizard = createWizard(sciactioncontext);
        intent.putExtra("DEBUG_WIZARD", debug);
        wizard.setDebugMode(debug);
        if(debug)
            intent.addFlags(0x10000000);
        intent.putExtra("WIZARD_OBJECT", SonosApplication.getAppDataStore().put(wizard));
        currentContext.startActivity(intent);
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/RunWizardAction.getSimpleName();
    boolean debug;
    SCIWizard sciWizard;
    SCRunWizardActionType wizardActionType;

}
