// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.View;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarNagIntro extends SoundbarWizardState
{

    public StateSetupSoundbarNagIntro(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f03009c, true, false);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() == 0x7f0a0056)
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1);
        else
        if(view.getId() == 0x7f0a0057)
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_2);
        else
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_3);
        transitionNext();
    }
}
