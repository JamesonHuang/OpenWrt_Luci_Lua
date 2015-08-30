// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.sonos.acr.wizards.soundbar.SoundbarWizard;
import com.sonos.acr.wizards.soundbar.SoundbarWizardState;

public class StateSetupSubWaiting extends SoundbarWizardState
{

    public StateSetupSubWaiting(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030084);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        downButton = (ImageView)view.findViewById(0x7f0a019d);
        anim_buttons_fader = AnimationUtils.loadAnimation(((SoundbarWizard)sonosWizard).getActivity(), 0x7f040011);
        anim_buttons_fader.setRepeatCount(-1);
        downButton.setAnimation(anim_buttons_fader);
        return view;
    }

    private Animation anim_buttons_fader;
    private ImageView downArrow;
    private ImageView downButton;
}
