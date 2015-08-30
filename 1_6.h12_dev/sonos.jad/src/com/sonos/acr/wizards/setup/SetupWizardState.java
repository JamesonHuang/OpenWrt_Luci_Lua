// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizard

public class SetupWizardState extends WizardState
{

    public SetupWizardState(SetupWizard setupwizard, int i, int j)
    {
        this(setupwizard, i, j, false, false);
    }

    public SetupWizardState(SetupWizard setupwizard, int i, int j, boolean flag, boolean flag1)
    {
        super(setupwizard, i, j, flag, flag1);
    }

    public SetupWizardState(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate, int i)
    {
        this(setupwizard, setupwizardstate.swigValue(), i, false, false);
    }

    public SetupWizardState(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate, int i, boolean flag, boolean flag1)
    {
        this(setupwizard, setupwizardstate.swigValue(), i, flag, flag1);
    }

    private void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tTitle:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_TITLE_1)).append("\n\tInstructions:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_WIZARD_INSTRUCTIONS)).append("\n\tBody:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY)).append("\n\tBody1:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_1)).append("\n\tBody2:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_2)).append("\n\tBody3:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_3)).append("\n\tBody4:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_4)).append("\n\tBody5:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_5)).append("\n\tInput1:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_1)).append("\n\tInput2:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_2)).append("\n\tInput3:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_3)).append("\n\tInput4:").append(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_4)).toString();
        SLog.v(LOG_TAG, s);
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCISetupWizard.SetupWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((SetupWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCISetupWizard getWizard()
    {
        return (SCISetupWizard)((SetupWizard)sonosWizard).getWizard();
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_2);
            transitionNext();
        } else
        if(view.getId() == 0x7f0a0058)
        {
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_3);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        if(sclibWizardState == com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_SETUP_WELCOME.swigValue() && busyView != null)
            busyView.setVisibility(8);
        populateView(view, 0x7f0a0051, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_TITLE_1.swigValue());
        populateView(view, 0x7f0a0052, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_1.swigValue());
        populateView(view, 0x7f0a0053, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_2.swigValue());
        populateView(view, 0x7f0a005d, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_3.swigValue());
        populateView(view, 0x7f0a005e, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_4.swigValue());
        populateView(view, 0x7f0a0056, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_1.swigValue());
        populateView(view, 0x7f0a0057, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_2.swigValue());
        populateView(view, 0x7f0a0058, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_3.swigValue());
        populateView(view, 0x7f0a00c3, com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_WIZARD_INSTRUCTIONS.swigValue());
        logWizardText();
        return view;
    }
}
