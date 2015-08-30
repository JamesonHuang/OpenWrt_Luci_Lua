// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarConfigureSurround extends SoundbarWizardState
{

    public StateSetupSoundbarConfigureSurround(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f03009a, true, false);
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
        if(sclibWizardState == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_STILLADD.swigValue())
        {
            if(getWizard().getSoundbarSurroundSetup() == com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1)
                ((SonosImageView)view.findViewById(0x7f0a01cd)).setRawImageResource(0x7f060047, false);
            else
                ((SonosImageView)view.findViewById(0x7f0a01cd)).setRawImageResource(0x7f060049, false);
            view.findViewById(0x7f0a01ce).setVisibility(8);
        } else
        {
            ((SonosImageView)view.findViewById(0x7f0a01cd)).setRawImageResource(0x7f06009b, false);
        }
        return view;
    }
}
