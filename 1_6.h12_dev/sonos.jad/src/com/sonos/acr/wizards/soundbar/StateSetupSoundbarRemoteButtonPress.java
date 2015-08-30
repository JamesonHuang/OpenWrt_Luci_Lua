// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarRemoteButtonPress extends SoundbarWizardState
{

    public StateSetupSoundbarRemoteButtonPress(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, Boolean boolean1)
    {
        boolean flag;
        if(!boolean1.booleanValue())
            flag = true;
        else
            flag = false;
        super(soundbarwizard, soundbarwizardstate, 0x7f03009d, flag, false);
        isDone = Boolean.valueOf(false);
        isDone = boolean1;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view;
        SonosImageView sonosimageview;
        SCISoundbarWizard scisoundbarwizard;
        view = super.onCreateView(layoutinflater, viewgroup);
        sonosimageview = (SonosImageView)view.findViewById(0x7f0a01d0);
        scisoundbarwizard = getWizard();
        if(!isDone.booleanValue()) goto _L2; else goto _L1
_L1:
        sonosimageview.setImageResource(0x7f06008e);
_L4:
        return view;
_L2:
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarRemoteButton[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarRemoteButton = new int[com.sonos.sclib.SCISoundbarWizard.SoundbarRemoteButton.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarRemoteButton[com.sonos.sclib.SCISoundbarWizard.SoundbarRemoteButton.LEARN_BUTTON_VOLDOWN.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarRemoteButton[com.sonos.sclib.SCISoundbarWizard.SoundbarRemoteButton.LEARN_BUTTON_MUTE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$sonos$sclib$SCISoundbarWizard$SoundbarRemoteButton[com.sonos.sclib.SCISoundbarWizard.SoundbarRemoteButton.LEARN_BUTTON_VOLUP.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.com.sonos.sclib.SCISoundbarWizard.SoundbarRemoteButton[scisoundbarwizard.getCurrentRemoteButton().ordinal()])
        {
        default:
            sonosimageview.setImageResource(0x7f060091);
            break;

        case 1: // '\001'
            sonosimageview.setImageResource(0x7f060090);
            break;

        case 2: // '\002'
            sonosimageview.setImageResource(0x7f06008f);
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Boolean isDone;
}
