// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import android.widget.ImageView;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarWithImage extends SoundbarWizardState
{

    public StateSetupSoundbarWithImage(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, int i, int j)
    {
        super(soundbarwizard, soundbarwizardstate, i);
        imageResId = 0;
        imageResId = j;
    }

    public StateSetupSoundbarWithImage(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, int i, int j, boolean flag, boolean flag1)
    {
        super(soundbarwizard, soundbarwizardstate, i, flag, flag1);
        imageResId = 0;
        imageResId = j;
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
        ((ImageView)view.findViewById(0x7f0a005c)).setImageResource(imageResId);
        return view;
    }

    int imageResId;
}
