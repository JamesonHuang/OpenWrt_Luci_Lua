// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            StateSetupSoundbarWithImage, SoundbarWizard

public class StateSoundbarAudioCalibration extends StateSetupSoundbarWithImage
{

    public StateSoundbarAudioCalibration(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030092, getImageId((SCISoundbarWizard)soundbarwizard.getWizard(), soundbarwizardstate));
    }

    private static int getImageId(SCISoundbarWizard scisoundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        boolean flag;
        flag = scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_SUB);
        if(soundbarwizardstate != com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CALIBRATION_PREP)
            break MISSING_BLOCK_LABEL_89;
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel = new int[com.sonos.sclib.SCIDevice.DeviceModel.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_UNKNOWN.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS3.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIDevice.DeviceModel[scisoundbarwizard.getSoundbarSurroundSetup().ordinal()];
        JVM INSTR tableswitch 1 2: default 48
    //                   1 57
    //                   2 73;
           goto _L1 _L2 _L3
_L1:
        int i;
        if(flag)
            i = 0x7f06009a;
        else
            i = 0x7f060099;
_L4:
        return i;
_L2:
        if(flag)
            i = 0x7f060098;
        else
            i = 0x7f060097;
          goto _L4
_L3:
        if(!flag) goto _L1; else goto _L5
_L5:
        i = 0x7f060096;
          goto _L4
        if(soundbarwizardstate == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_CALIBRATION_PREP2)
        {
            if(flag)
                i = 0x7f060062;
            else
                i = 0x7f060061;
        } else
        if(flag)
            i = 0x7f060060;
        else
            i = 0x7f06005f;
          goto _L4
    }
}
