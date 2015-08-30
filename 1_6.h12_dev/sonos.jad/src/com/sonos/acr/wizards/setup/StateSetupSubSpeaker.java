// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.View;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupSubSpeaker extends SetupWizardState
{

    public StateSetupSubSpeaker(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030083, true, false);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        getWizard().setSubSpeakerSizeIndex(Integer.valueOf(view.getTag().toString()).intValue());
        transitionNext();
    }
}
