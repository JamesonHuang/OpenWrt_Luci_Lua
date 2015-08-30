// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.wizards.SubPhaseController;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarSubPhase extends SoundbarWizardState
    implements com.sonos.acr.wizards.SubPhaseController.SubControllerImpl
{

    public StateSetupSoundbarSubPhase(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030081, true, false);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        subPhaseController.onClick(view);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        subPhaseController = new SubPhaseController(view, getWizard().getSubCalibrator(), this);
        return view;
    }

    public void onEntry(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        subPhaseController.start();
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        subPhaseController.stop();
    }

    public void setSubPhaseBIsLouder(boolean flag)
    {
        getWizard().setSubPhaseBIsLouder(flag);
        transitionNext();
    }

    SubPhaseController subPhaseController;
}
