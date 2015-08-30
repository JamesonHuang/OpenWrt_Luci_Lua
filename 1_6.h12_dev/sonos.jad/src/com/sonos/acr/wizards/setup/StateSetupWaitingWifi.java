// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupWaitingWifi extends SetupWizardState
{

    public StateSetupWaitingWifi(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f03008f, false, false);
    }

    private void startTwoButtonPressAnimation()
    {
        doubleButtonGlow.clearAnimation();
        animTwoButtonsFader.reset();
        animTwoButtonsFader.setRepeatCount(-1);
        doubleButtonGlow.startAnimation(animTwoButtonsFader);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        doubleButtonGlow = (ImageView)view.findViewById(0x7f0a01a9);
        animTwoButtonsFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
        startTwoButtonPressAnimation();
        return view;
    }

    private Animation animTwoButtonsFader;
    private ImageView doubleButtonGlow;
}
