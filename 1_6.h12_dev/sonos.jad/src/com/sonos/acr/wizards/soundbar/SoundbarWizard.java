// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.acr.wizards.setup.StateSetupSubWaiting;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, StateSetupSoundbarConfigureSurround, StateSoundbarSubConnect, StateSetupSoundbarSpeakerCheck, 
//            StateSetupSoundbarTwoInput, StateSetupSoundbarConfigureSub, StateSetupSoundbarSubPhase, StateSetupSoundbarSubLevel, 
//            StateSetupSoundbarSurroundCalibration, StateSetupSoundbarSurroundConnect, StateSetupSoundbarWaitingSurround, StateSetupSoundbarRemoteButtonPress, 
//            StateSetupSoundbarConnection, StateSetupSoundbarNagIntro, StateSetupSoundbarWithImage, StateSoundbarAudioCalibration

public class SoundbarWizard extends Wizard
{

    public SoundbarWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCISoundbarWizard scisoundbarwizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, scisoundbarwizard);
    }

    private com.sonos.sclib.SCIDevice.DeviceModel getSurroundDeviceId()
    {
        return ((SCISoundbarWizard)sciWizard).getSoundbarSurroundSetup();
    }

    public WizardState buildState(int i)
    {
        com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate = com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[];
            static final int $SwitchMap$com$sonos$sclib$SCRunWizardActionType[] = new int[SCRunWizardActionType.values().length];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState = new int[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.values().length];
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CONFIGURE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_STILLADD.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_WAITING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_CONNECTOK.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SPEAKER_CHECK.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_PARTIAL_SURROUND.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_REPLACE_REMOTE.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_MATCH.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_NOTASUB_EXISTING.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_STILLADD.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CONFIGURE_SUB.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_NOTASUB_NEW.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_PHASE.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_LEVEL.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CALIBRATION.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_PLACE.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CONNECTOK.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_WAITING.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_ONE_LEARN.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_LEARN_NEW_BUTTON.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_KNOWN_REMOTE.ordinal()] = 22;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_INTRO.ordinal()] = 23;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SPDIF_SIGNAL.ordinal()] = 24;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_NAG_INTRO.ordinal()] = 25;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_TIMEOUT.ordinal()] = 26;
                }
                catch(NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_POWER.ordinal()] = 27;
                }
                catch(NoSuchFieldError nosuchfielderror26) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_INSECURE_PLAYER.ordinal()] = 28;
                }
                catch(NoSuchFieldError nosuchfielderror27) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CALIBRATION_PREP.ordinal()] = 29;
                }
                catch(NoSuchFieldError nosuchfielderror28) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CALIBRATION_PREP2.ordinal()] = 30;
                }
                catch(NoSuchFieldError nosuchfielderror29) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CALIBRATION_DONE.ordinal()] = 31;
                }
                catch(NoSuchFieldError nosuchfielderror30) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_REMOVE_SURROUND.ordinal()] = 32;
                }
                catch(NoSuchFieldError nosuchfielderror31) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_CONNECTING.ordinal()] = 33;
                }
                catch(NoSuchFieldError nosuchfielderror32) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CONNECTING.ordinal()] = 34;
                }
                catch(NoSuchFieldError nosuchfielderror33) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_DETECT.ordinal()] = 35;
                }
                catch(NoSuchFieldError nosuchfielderror34) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TURN_AUTOPLAY_ON.ordinal()] = 36;
                }
                catch(NoSuchFieldError nosuchfielderror35) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_DELAYED_BONDING.ordinal()] = 37;
                }
                catch(NoSuchFieldError nosuchfielderror36) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_DELAYED_BONDING_ERROR.ordinal()] = 38;
                }
                catch(NoSuchFieldError nosuchfielderror37) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_OFF.ordinal()] = 39;
                }
                catch(NoSuchFieldError nosuchfielderror38) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_OTHER_COMPONENTS.ordinal()] = 40;
                }
                catch(NoSuchFieldError nosuchfielderror39) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_ERROR.ordinal()] = 41;
                }
                catch(NoSuchFieldError nosuchfielderror40) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_NOT_COMPATIBLE.ordinal()] = 42;
                }
                catch(NoSuchFieldError nosuchfielderror41) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_EXISTING.ordinal()] = 43;
                }
                catch(NoSuchFieldError nosuchfielderror42) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_NOSURROUND.ordinal()] = 44;
                }
                catch(NoSuchFieldError nosuchfielderror43) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_ERROR.ordinal()] = 45;
                }
                catch(NoSuchFieldError nosuchfielderror44) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_PREP.ordinal()] = 46;
                }
                catch(NoSuchFieldError nosuchfielderror45) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_SPEAKERS.ordinal()] = 47;
                }
                catch(NoSuchFieldError nosuchfielderror46) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_ZEROVOL.ordinal()] = 48;
                }
                catch(NoSuchFieldError nosuchfielderror47) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE.ordinal()] = 49;
                }
                catch(NoSuchFieldError nosuchfielderror48) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_ALT.ordinal()] = 50;
                }
                catch(NoSuchFieldError nosuchfielderror49) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_NOTVSIGNAL_ERR.ordinal()] = 51;
                }
                catch(NoSuchFieldError nosuchfielderror50) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_NOTVSIGNAL_CONTINUE.ordinal()] = 52;
                }
                catch(NoSuchFieldError nosuchfielderror51) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SPEAKER_PLAY.ordinal()] = 53;
                }
                catch(NoSuchFieldError nosuchfielderror52) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_BUTTON_HEARD.ordinal()] = 54;
                }
                catch(NoSuchFieldError nosuchfielderror53) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_NEW_REMOTE_DONE.ordinal()] = 55;
                }
                catch(NoSuchFieldError nosuchfielderror54) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_REMOTE_HEARD.ordinal()] = 56;
                }
                catch(NoSuchFieldError nosuchfielderror55) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SUB_NOSUB.ordinal()] = 57;
                }
                catch(NoSuchFieldError nosuchfielderror56) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_REMOVE_SURROUND_INTRO.ordinal()] = 58;
                }
                catch(NoSuchFieldError nosuchfielderror57) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_REMOVE_SURROUND_RESULT.ordinal()] = 59;
                }
                catch(NoSuchFieldError nosuchfielderror58) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_ADD_SURROUND_PREP.ordinal()] = 60;
                }
                catch(NoSuchFieldError nosuchfielderror59) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_NAG_MESSAGE.ordinal()] = 61;
                }
                catch(NoSuchFieldError nosuchfielderror60) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_NAG_VOLUME.ordinal()] = 62;
                }
                catch(NoSuchFieldError nosuchfielderror61) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarWizardState[com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_UPDATE_ERROR.ordinal()] = 63;
                }
                catch(NoSuchFieldError nosuchfielderror62) { }
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState[soundbarwizardstate.ordinal()];
        JVM INSTR tableswitch 1 37: default 176
    //                   1 190
    //                   2 203
    //                   3 216
    //                   4 229
    //                   5 242
    //                   6 255
    //                   7 255
    //                   8 255
    //                   9 268
    //                   10 268
    //                   11 268
    //                   12 281
    //                   13 296
    //                   14 309
    //                   15 322
    //                   16 339
    //                   17 339
    //                   18 352
    //                   19 366
    //                   20 383
    //                   21 383
    //                   22 400
    //                   23 417
    //                   24 417
    //                   25 430
    //                   26 443
    //                   27 458
    //                   28 475
    //                   29 494
    //                   30 494
    //                   31 494
    //                   32 507
    //                   33 507
    //                   34 507
    //                   35 507
    //                   36 507
    //                   37 507;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L7 _L7 _L8 _L8 _L8 _L9 _L10 _L11 _L12 _L13 _L13 _L14 _L15 _L16 _L16 _L17 _L18 _L18 _L19 _L20 _L21 _L22 _L23 _L23 _L23 _L24 _L24 _L24 _L24 _L24 _L24
_L1:
        Object obj = new SoundbarWizardState(this, soundbarwizardstate, 0x7f0300b3);
_L26:
        return ((WizardState) (obj));
_L2:
        obj = new StateSetupSoundbarConfigureSurround(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new StateSetupSoundbarConfigureSurround(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new StateSetupSubWaiting(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new StateSoundbarSubConnect(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new StateSetupSoundbarSpeakerCheck(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new StateSetupSoundbarTwoInput(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new StateSetupSoundbarConfigureSub(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new SoundbarWizardState(this, soundbarwizardstate, 0x7f03007e);
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new StateSetupSoundbarSubPhase(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new StateSetupSoundbarSubLevel(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new StateSetupSoundbarSurroundCalibration(this, soundbarwizardstate, getSurroundDeviceId());
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new StateSetupSoundbarSurroundConnect(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L14:
        obj = new StateSetupSoundbarSurroundConnect(this, soundbarwizardstate, false);
        continue; /* Loop/switch isn't completed */
_L15:
        obj = new StateSetupSoundbarWaitingSurround(this, soundbarwizardstate, getSurroundDeviceId());
        continue; /* Loop/switch isn't completed */
_L16:
        obj = new StateSetupSoundbarRemoteButtonPress(this, soundbarwizardstate, Boolean.valueOf(false));
        continue; /* Loop/switch isn't completed */
_L17:
        obj = new StateSetupSoundbarRemoteButtonPress(this, soundbarwizardstate, Boolean.valueOf(true));
        continue; /* Loop/switch isn't completed */
_L18:
        obj = new StateSetupSoundbarConnection(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L19:
        obj = new StateSetupSoundbarNagIntro(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L20:
        obj = new SoundbarWizardState(this, soundbarwizardstate, 0x7f03009e);
        continue; /* Loop/switch isn't completed */
_L21:
        obj = new StateSetupSoundbarWithImage(this, soundbarwizardstate, 0x7f030091, 0x7f06003e);
        continue; /* Loop/switch isn't completed */
_L22:
        obj = new StateSetupSoundbarWithImage(this, soundbarwizardstate, 0x7f03008d, 0x7f06004f, true, true);
        continue; /* Loop/switch isn't completed */
_L23:
        obj = new StateSoundbarAudioCalibration(this, soundbarwizardstate);
        continue; /* Loop/switch isn't completed */
_L24:
        obj = new SoundbarWizardState(this, soundbarwizardstate, 0x7f0300b4);
        if(true) goto _L26; else goto _L25
_L25:
    }

    protected SCISoundbarWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        String s = actionContext.getPropertyBag().getStrProp("SOUNDBAR_DEVICE_ID");
        int _tmp = _cls1..SwitchMap.com.sonos.sclib.SCRunWizardActionType[scrunwizardactiontype.ordinal()];
        return LibraryUtils.getHousehold().createSoundbarWizard(s);
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.swigToEnum(i).toString();
    }

    public CharSequence getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID soundbarwizstringid)
    {
        return super.getRecommendedText(soundbarwizstringid.swigValue());
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.values();
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/soundbar/SoundbarWizard.getSimpleName();

}
