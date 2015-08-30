// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.view.SonosImageView;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarConnection extends SoundbarWizardState
{

    public StateSetupSoundbarConnection(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        boolean flag = false;
        super(soundbarwizard, soundbarwizardstate, 0x7f03009b);
        bIntro = Boolean.valueOf(false);
        if(soundbarwizardstate == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_TV_INTRO)
            flag = true;
        bIntro = Boolean.valueOf(flag);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SonosImageView sonosimageview = (SonosImageView)view.findViewById(0x7f0a01cf);
        getWizard();
        if(bIntro.booleanValue())
            sonosimageview.setImageResource(0x7f060094);
        else
            sonosimageview.setImageResource(0x7f060095);
        return view;
    }

    private Boolean bIntro;
}
