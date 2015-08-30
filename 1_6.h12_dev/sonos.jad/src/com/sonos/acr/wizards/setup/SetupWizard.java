// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.content.Intent;
import android.net.wifi.WifiManager;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.*;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, StateSetupDeviceAdded, StateSetupNameDevice, StateSetupAutoplayConfigure, 
//            StateSetupSubIntro, StateSetupSubChooseRoom, StateSetupSubRemove, StateSetupSubSpeaker, 
//            StateSetupSubPhase, StateSetupSubLevel, StateSetupChooseConfigure, StateSetupLights, 
//            StateSetupWithImage, StateSetupWaitingWifi, StateSetupWaiting, StateSetupWifiConnecting

public class SetupWizard extends Wizard
{

    public SetupWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCISetupWizard scisetupwizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, scisetupwizard);
    }

    private String getDeviceId()
    {
        return actionContext.getPropertyBag().getStrProp("SOUNDBAR_DEVICE_ID");
    }

    public WizardState buildState(int i)
    {
        com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate = com.sonos.sclib.SCISetupWizard.SetupWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[];
            static final int $SwitchMap$com$sonos$sclib$SCRunWizardActionType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState = new int[com.sonos.sclib.SCISetupWizard.SetupWizardState.values().length];
                NoSuchFieldError nosuchfielderror74;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_DEVICE_ADDED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_NAME_DEVICE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_AUTOPLAY_CONFIGURE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_INTRO.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_CHOOSE_ROOM.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_REMOVE.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_SPEAKER.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_CALIBRATE_PHASE.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_CALIBRATE_LEVEL.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SURROUND_CHOOSE_ROOM.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_CHOOSE_CONFIGURE_DEVICE.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CONNECTING.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CHECK_CHECKING.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_WAITING.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_CONNECTING.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_DEVICE_BEING_CONFIGURED.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_WELCOME.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_PLEASEWAIT_RANGECHECK.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_NEXT_DEVICE.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_NO_ZONEPLAYERS.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_AUTOPLAY_ASSIGNED.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_NO_ROOMS.ordinal()] = 22;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_OUTRO.ordinal()] = 23;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_CALIBRATE_INTRO.ordinal()] = 24;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_CALIBRATE_MOVE.ordinal()] = 25;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_FULL_SETUP_DONE.ordinal()] = 26;
                }
                catch(NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CHECK_UPDATE_NEEDED.ordinal()] = 27;
                }
                catch(NoSuchFieldError nosuchfielderror26) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SUB_FAILURE.ordinal()] = 28;
                }
                catch(NoSuchFieldError nosuchfielderror27) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_START_TEMPWIRE.ordinal()] = 29;
                }
                catch(NoSuchFieldError nosuchfielderror28) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_NEED_BRIDGE.ordinal()] = 30;
                }
                catch(NoSuchFieldError nosuchfielderror29) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_NEW_WIRED_COMPONENT.ordinal()] = 31;
                }
                catch(NoSuchFieldError nosuchfielderror30) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_TEMPWIRE_INCOMPLETE.ordinal()] = 32;
                }
                catch(NoSuchFieldError nosuchfielderror31) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_BETA_RESULT.ordinal()] = 33;
                }
                catch(NoSuchFieldError nosuchfielderror32) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_PREP.ordinal()] = 34;
                }
                catch(NoSuchFieldError nosuchfielderror33) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_FINISH_MOREINFO_PLAYBAR.ordinal()] = 35;
                }
                catch(NoSuchFieldError nosuchfielderror34) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_FINISH_INCOMPLETE.ordinal()] = 36;
                }
                catch(NoSuchFieldError nosuchfielderror35) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_NO_EXISTING_HH_FOUND.ordinal()] = 37;
                }
                catch(NoSuchFieldError nosuchfielderror36) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT.ordinal()] = 38;
                }
                catch(NoSuchFieldError nosuchfielderror37) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_TEMPWIRE_CONNECT_ETH.ordinal()] = 39;
                }
                catch(NoSuchFieldError nosuchfielderror38) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_DISCONNECT_FIRSTPLAYER.ordinal()] = 40;
                }
                catch(NoSuchFieldError nosuchfielderror39) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_RESTART_FIRSTPLAYER.ordinal()] = 41;
                }
                catch(NoSuchFieldError nosuchfielderror40) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_POWER_UP_TEMPWIRE.ordinal()] = 42;
                }
                catch(NoSuchFieldError nosuchfielderror41) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CONNECTFAIL.ordinal()] = 43;
                }
                catch(NoSuchFieldError nosuchfielderror42) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_OUT_OF_RANGE.ordinal()] = 44;
                }
                catch(NoSuchFieldError nosuchfielderror43) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CONNECT_POWER.ordinal()] = 45;
                }
                catch(NoSuchFieldError nosuchfielderror44) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_ALLPLAYERS_IN_RANGE.ordinal()] = 46;
                }
                catch(NoSuchFieldError nosuchfielderror45) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_USE_WIRELESS.ordinal()] = 47;
                }
                catch(NoSuchFieldError nosuchfielderror46) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_REPEAT_BUTTONS.ordinal()] = 48;
                }
                catch(NoSuchFieldError nosuchfielderror47) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_MULTIPLE_HOUSEHOLD_FOUND.ordinal()] = 49;
                }
                catch(NoSuchFieldError nosuchfielderror48) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_ADD_BRIDGE.ordinal()] = 50;
                }
                catch(NoSuchFieldError nosuchfielderror49) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_NEW_WIRED_COMPONENT_CHECK.ordinal()] = 51;
                }
                catch(NoSuchFieldError nosuchfielderror50) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_WIRE_BRIDGE.ordinal()] = 52;
                }
                catch(NoSuchFieldError nosuchfielderror51) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_PROBLEM.ordinal()] = 53;
                }
                catch(NoSuchFieldError nosuchfielderror52) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_CHECK_LIGHTS.ordinal()] = 54;
                }
                catch(NoSuchFieldError nosuchfielderror53) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_FINISH_COMPLETE.ordinal()] = 55;
                }
                catch(NoSuchFieldError nosuchfielderror54) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_CHECK_ERROR.ordinal()] = 56;
                }
                catch(NoSuchFieldError nosuchfielderror55) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_BETA_OPT_IN.ordinal()] = 57;
                }
                catch(NoSuchFieldError nosuchfielderror56) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_IDENTIFY_COMPONENT_ERROR.ordinal()] = 58;
                }
                catch(NoSuchFieldError nosuchfielderror57) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_POWER_UP_TEMPWIRE_ERROR.ordinal()] = 59;
                }
                catch(NoSuchFieldError nosuchfielderror58) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_SURROUND_INTRO.ordinal()] = 60;
                }
                catch(NoSuchFieldError nosuchfielderror59) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_COMPLETE.ordinal()] = 61;
                }
                catch(NoSuchFieldError nosuchfielderror60) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_PRESS_TO_ADD.ordinal()] = 62;
                }
                catch(NoSuchFieldError nosuchfielderror61) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_WAITING.ordinal()] = 63;
                }
                catch(NoSuchFieldError nosuchfielderror62) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_CONNECT_TO_AP.ordinal()] = 64;
                }
                catch(NoSuchFieldError nosuchfielderror63) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_INSECURE_PLAYER.ordinal()] = 65;
                }
                catch(NoSuchFieldError nosuchfielderror64) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISetupWizard$SetupWizardState[com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_INSECURE_DOCK.ordinal()] = 66;
                }
                catch(NoSuchFieldError nosuchfielderror65) { }
                $SwitchMap$com$sonos$sclib$SCRunWizardActionType = new int[SCRunWizardActionType.values().length];
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ADDPLAYERORSUB.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror66) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ADDBOOST.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror67) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SETUP_UNCONFIGURED.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror68) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_REGISTRATION.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror69) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_SHAREUSAGEDATA.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror70) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CHANGE_SPEAKERS.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror71) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CONFIGURE_SUB.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror72) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_RECALIBRATE_SUB.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror73) { }
                $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE.ordinal()] = 9;
_L2:
                return;
                nosuchfielderror74;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCISetupWizard.SetupWizardState[setupwizardstate.ordinal()];
        JVM INSTR tableswitch 1 66: default 292
    //                   1 306
    //                   2 319
    //                   3 332
    //                   4 345
    //                   5 358
    //                   6 371
    //                   7 384
    //                   8 397
    //                   9 410
    //                   10 423
    //                   11 436
    //                   12 449
    //                   13 449
    //                   14 449
    //                   15 449
    //                   16 466
    //                   17 466
    //                   18 481
    //                   19 496
    //                   20 496
    //                   21 496
    //                   22 496
    //                   23 496
    //                   24 496
    //                   25 496
    //                   26 496
    //                   27 496
    //                   28 496
    //                   29 496
    //                   30 496
    //                   31 496
    //                   32 496
    //                   33 496
    //                   34 496
    //                   35 496
    //                   36 496
    //                   37 496
    //                   38 511
    //                   39 528
    //                   40 545
    //                   41 562
    //                   42 579
    //                   43 596
    //                   44 613
    //                   45 632
    //                   46 649
    //                   47 666
    //                   48 683
    //                   49 683
    //                   50 683
    //                   51 683
    //                   52 700
    //                   53 717
    //                   54 734
    //                   55 753
    //                   56 770
    //                   57 785
    //                   58 785
    //                   59 785
    //                   60 802
    //                   61 819
    //                   62 829
    //                   63 842
    //                   64 855
    //                   65 868
    //                   66 887;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L13 _L13 _L13 _L14 _L14 _L15 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L27 _L27 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L33 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40
_L1:
        Object obj = new SetupWizardState(this, setupwizardstate, 0x7f0300b3);
_L42:
        return ((WizardState) (obj));
_L2:
        obj = new StateSetupDeviceAdded(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new StateSetupNameDevice(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new StateSetupAutoplayConfigure(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new StateSetupSubIntro(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new StateSetupSubChooseRoom(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new StateSetupSubRemove(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new StateSetupSubSpeaker(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new StateSetupSubPhase(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new StateSetupSubLevel(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new StateSetupSubChooseRoom(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new StateSetupChooseConfigure(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f0300b4, true, true);
        continue; /* Loop/switch isn't completed */
_L14:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f0300b4);
        continue; /* Loop/switch isn't completed */
_L15:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f0300b4);
        continue; /* Loop/switch isn't completed */
_L16:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f0300b3);
        continue; /* Loop/switch isn't completed */
_L17:
        obj = new StateSetupLights(this, setupwizardstate, 0x7f03007b, false, false);
        continue; /* Loop/switch isn't completed */
_L18:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03007a, 0x7f06005e);
        continue; /* Loop/switch isn't completed */
_L19:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03007a, 0x7f06004e);
        continue; /* Loop/switch isn't completed */
_L20:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03007a, 0x7f06003d);
        continue; /* Loop/switch isn't completed */
_L21:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03007b, 0x7f060054);
        continue; /* Loop/switch isn't completed */
_L22:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f030078, true, true);
        continue; /* Loop/switch isn't completed */
_L23:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f030088, 0x7f06004f, true, false);
        continue; /* Loop/switch isn't completed */
_L24:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f030093, 0x7f06003d);
        continue; /* Loop/switch isn't completed */
_L25:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f030094, 0x7f06003b);
        continue; /* Loop/switch isn't completed */
_L26:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f03007d, true, false);
        continue; /* Loop/switch isn't completed */
_L27:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f030089, true, false);
        continue; /* Loop/switch isn't completed */
_L28:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f030091, 0x7f06003c);
        continue; /* Loop/switch isn't completed */
_L29:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f030078, true, false);
        continue; /* Loop/switch isn't completed */
_L30:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03008c, 0x7f060051, true, true);
        continue; /* Loop/switch isn't completed */
_L31:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f030089, true, true);
        continue; /* Loop/switch isn't completed */
_L32:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f030089);
        continue; /* Loop/switch isn't completed */
_L33:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f03007c, true, false);
        continue; /* Loop/switch isn't completed */
_L34:
        obj = new SetupWizardState(this, setupwizardstate, 0x7f03008a, true, false);
        continue; /* Loop/switch isn't completed */
_L35:
        throw new RuntimeException("Should not build be building complete state!");
_L36:
        obj = new StateSetupWaitingWifi(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L37:
        obj = new StateSetupWaiting(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L38:
        obj = new StateSetupWifiConnecting(this, setupwizardstate);
        continue; /* Loop/switch isn't completed */
_L39:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03008d, 0x7f06004f, true, true);
        continue; /* Loop/switch isn't completed */
_L40:
        obj = new StateSetupWithImage(this, setupwizardstate, 0x7f03008d, 0x7f060045, true, true);
        if(true) goto _L42; else goto _L41
_L41:
    }

    public boolean canRunWithoutWifi()
    {
        return true;
    }

    protected SCISetupWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        _cls1..SwitchMap.com.sonos.sclib.SCRunWizardActionType[scrunwizardactiontype.ordinal()];
        JVM INSTR tableswitch 1 9: default 60
    //                   1 69
    //                   2 79
    //                   3 89
    //                   4 114
    //                   5 124
    //                   6 134
    //                   7 148
    //                   8 162
    //                   9 176;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        SCISetupWizard scisetupwizard = LibraryUtils.getSystem().createSetupWizard();
_L12:
        return scisetupwizard;
_L2:
        scisetupwizard = LibraryUtils.getHousehold().createAddPlayerOrSubWizard();
        continue; /* Loop/switch isn't completed */
_L3:
        scisetupwizard = LibraryUtils.getHousehold().createAddBoostWizard();
        continue; /* Loop/switch isn't completed */
_L4:
        String s = actionContext.getPropertyBag().getStrProp(sclibConstants.SCRUNWIZARDITEMPROP_WIZARD_DEVICE_UDN);
        scisetupwizard = LibraryUtils.getHousehold().createConfigureSonosComponentWizard(s);
        continue; /* Loop/switch isn't completed */
_L5:
        scisetupwizard = LibraryUtils.getHousehold().createRegistrationWizard();
        continue; /* Loop/switch isn't completed */
_L6:
        scisetupwizard = LibraryUtils.getHousehold().createShareUsageDataWizard();
        continue; /* Loop/switch isn't completed */
_L7:
        scisetupwizard = LibraryUtils.getHousehold().createChangeSpeakersWizard(getDeviceId());
        continue; /* Loop/switch isn't completed */
_L8:
        scisetupwizard = LibraryUtils.getHousehold().createConfigureSubWizard(getDeviceId());
        continue; /* Loop/switch isn't completed */
_L9:
        scisetupwizard = LibraryUtils.getHousehold().createRecalibrateSubWizard(getDeviceId());
        continue; /* Loop/switch isn't completed */
_L10:
        scisetupwizard = LibraryUtils.getHousehold().createChooseConfigurePage();
        if(true) goto _L12; else goto _L11
_L11:
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCISetupWizard.SetupWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCISetupWizard.SetupWizardState.swigToEnum(i).toString();
    }

    public CharSequence getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID setupwizstringid)
    {
        return super.getRecommendedText(setupwizstringid.swigValue());
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCISetupWizard.SetupWizardState.values();
    }

    public void onComplete()
    {
        if(((SCISetupWizard)getWizard()).getMode() == com.sonos.sclib.SCISetupWizard.SetupWizardMode.MODE_FULL_SETUP)
        {
            if(((SCISetupWizard)getWizard()).getExitCode() == sclibConstants.SC_SETUPWIZ_EXITCODE_LAUNCH_MORE_MUSIC)
            {
                ApplicationStateManager.getInstance().closeAllActivities();
                getActivity().startActivity(new Intent(getActivity(), SonosActivity.getCurrentStateActivity()));
                getActivity().showMoreMusic(null);
            } else
            if(((SCISetupWizard)sciWizard).completedAndIsAssociatedToHousehold())
            {
                ApplicationStateManager.getInstance().closeAllActivities();
                getActivity().startActivity((new Intent(getActivity(), SonosActivity.getCurrentStateActivity())).addFlags(0x20000000));
            } else
            {
                super.onComplete();
            }
        } else
        {
            super.onComplete();
        }
    }

    public boolean shouldWarp()
    {
        return false;
    }

    public void start()
    {
        super.start();
        WifiManager wifimanager = (WifiManager)SonosApplication.getInstance().getSystemService("wifi");
        if(wifimanager != null)
        {
            multicastLock = wifimanager.createMulticastLock("SonosSetupWizard");
            multicastLock.acquire();
        }
    }

    public void stop()
    {
        super.stop();
        if(multicastLock != null && multicastLock.isHeld())
            multicastLock.release();
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/setup/SetupWizard.getSimpleName();
    protected android.net.wifi.WifiManager.MulticastLock multicastLock;

}
