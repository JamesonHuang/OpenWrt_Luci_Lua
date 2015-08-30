// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarWaitingSurround extends SoundbarWizardState
{

    public StateSetupSoundbarWaitingSurround(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, com.sonos.sclib.SCIDevice.DeviceModel devicemodel)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030087, true, false);
        zpModel = devicemodel;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        twoButton = (ImageView)view.findViewById(0x7f0a01a7);
        animButtonFader = AnimationUtils.loadAnimation(((SoundbarWizard)sonosWizard).getActivity(), 0x7f040011);
        animButtonFader.setRepeatCount(-1);
        twoButton.setAnimation(animButtonFader);
        return view;
    }

    private Animation animArrowSlide;
    private Animation animButtonFader;
    private ImageView twoButton;
    private com.sonos.sclib.SCIDevice.DeviceModel zpModel;
}
