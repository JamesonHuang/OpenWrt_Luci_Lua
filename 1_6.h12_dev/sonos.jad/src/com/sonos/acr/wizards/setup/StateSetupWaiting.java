// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupWaiting extends SetupWizardState
{

    public StateSetupWaiting(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f03008e, true, false);
    }

    private void startDNAButtonPressAnimation()
    {
        dnaButtonGlow.clearAnimation();
        animDnaButtonFader.reset();
        animDnaButtonFader.setRepeatCount(-1);
        dnaButtonGlow.startAnimation(animDnaButtonFader);
    }

    private void startOneButtonPressAnimation()
    {
        singleButtonGlow.clearAnimation();
        animOneButtonFader.reset();
        animOneButtonFader.setRepeatCount(-1);
        singleButtonGlow.startAnimation(animOneButtonFader);
    }

    private void startTwoButtonPressAnimation()
    {
        doubleButtonGlow.clearAnimation();
        animTwoButtonsFader.reset();
        animTwoButtonsFader.setRepeatCount(-1);
        doubleButtonGlow.startAnimation(animTwoButtonsFader);
    }

    public void onClick(View view)
    {
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        singleButtonGlow.clearAnimation();
        doubleButtonGlow.clearAnimation();
        dnaButtonGlow.clearAnimation();
        if(getWizard().getSelection() == com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1) goto _L4; else goto _L3
_L3:
        getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1);
        if(joinButtonType != com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN) goto _L6; else goto _L5
_L5:
        doubleContainer.setVisibility(8);
        singleContainer.setVisibility(0);
        if(animOneButtonFader == null)
            animOneButtonFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
        startOneButtonPressAnimation();
_L8:
        CharSequence charsequence = ((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_TITLE_1.swigValue());
        titleView.setText(charsequence);
        CharSequence charsequence1 = ((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_1.swigValue());
        bodyView.setText(charsequence1);
        CharSequence charsequence2 = ((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_1.swigValue());
        buttonView.setText(charsequence2);
_L2:
        return;
_L6:
        if(joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN_BOOST)
        {
            dnaContainer.setVisibility(8);
            singleContainer.setVisibility(0);
            if(animOneButtonFader == null)
                animOneButtonFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            startOneButtonPressAnimation();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_2);
        if(joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN)
        {
            singleContainer.setVisibility(8);
            doubleContainer.setVisibility(0);
            if(animTwoButtonsFader == null)
                animTwoButtonsFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            startTwoButtonPressAnimation();
        } else
        if(joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN_BOOST)
        {
            singleContainer.setVisibility(8);
            dnaContainer.setVisibility(0);
            if(animDnaButtonFader == null)
                animDnaButtonFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            startDNAButtonPressAnimation();
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        titleView = (TextView)view.findViewById(0x7f0a0051);
        bodyView = (TextView)view.findViewById(0x7f0a0052);
        buttonView = (TextView)view.findViewById(0x7f0a0056);
        doubleContainer = (FrameLayout)view.findViewById(0x7f0a01a8);
        singleContainer = (FrameLayout)view.findViewById(0x7f0a01aa);
        dnaContainer = (FrameLayout)view.findViewById(0x7f0a01ac);
        doubleButtonGlow = (ImageView)view.findViewById(0x7f0a01a9);
        singleButtonGlow = (ImageView)view.findViewById(0x7f0a01ab);
        dnaButtonGlow = (ImageView)view.findViewById(0x7f0a01ad);
        joinButtonType = getWizard().getJoinButtonType();
        if(joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN || joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_PLAYER)
        {
            animTwoButtonsFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            singleContainer.setVisibility(8);
            dnaContainer.setVisibility(8);
            startTwoButtonPressAnimation();
        } else
        if(joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_UNKNOWN_BOOST || joinButtonType == com.sonos.sclib.SCISetupWizard.JoinButtonType.JOIN_BUTTON_DNA)
        {
            animDnaButtonFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            singleContainer.setVisibility(8);
            doubleContainer.setVisibility(8);
            startDNAButtonPressAnimation();
        } else
        {
            animOneButtonFader = AnimationUtils.loadAnimation(((SetupWizard)sonosWizard).getActivity(), 0x7f040011);
            doubleContainer.setVisibility(8);
            dnaContainer.setVisibility(8);
            startOneButtonPressAnimation();
        }
        return view;
    }

    private Animation animDnaButtonFader;
    private Animation animOneButtonFader;
    private Animation animTwoButtonsFader;
    private TextView bodyView;
    private TextView buttonView;
    private ImageView dnaButtonGlow;
    private FrameLayout dnaContainer;
    private ImageView doubleButtonGlow;
    private FrameLayout doubleContainer;
    private com.sonos.sclib.SCISetupWizard.JoinButtonType joinButtonType;
    private ImageView singleButtonGlow;
    private FrameLayout singleContainer;
    private TextView titleView;
}
