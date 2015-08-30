// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import com.sonos.acr.wizards.SubLevelController;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupSubLevel extends SetupWizardState
{

    public StateSetupSubLevel(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030080);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        controller = new SubLevelController(view, getWizard().getSubCalibrator());
        return view;
    }

    public void onEntry(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        controller.start();
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        controller.stop();
    }

    SubLevelController controller;
}
