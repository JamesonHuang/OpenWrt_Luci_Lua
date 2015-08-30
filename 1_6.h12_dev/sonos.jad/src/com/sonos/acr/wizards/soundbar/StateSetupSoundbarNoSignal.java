// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.View;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarNoSignal extends SoundbarWizardState
{

    public StateSetupSoundbarNoSignal(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f0300b2, true, true);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setTryTVSignalDetectionAgain(true);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setTryTVSignalDetectionAgain(false);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
