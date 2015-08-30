// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarSurroundCalibration extends SoundbarWizardState
{

    public StateSetupSoundbarSurroundCalibration(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, com.sonos.sclib.SCIDevice.DeviceModel devicemodel)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030085, true, false);
        zpModel = devicemodel;
    }

    public void onClick(View view)
    {
        SCISoundbarWizard scisoundbarwizard;
        super.onClick(view);
        scisoundbarwizard = getWizard();
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setComponentDistance(scisoundbarwizard.currentlyAttaching(), com.sonos.sclib.SCISoundbarWizard.SoundbarDistance.SOUNDBAR_DISTANCE_ADJACENT);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setComponentDistance(scisoundbarwizard.currentlyAttaching(), com.sonos.sclib.SCISoundbarWizard.SoundbarDistance.SOUNDBAR_DISTANCE_CLOSE);
            transitionNext();
        } else
        if(view.getId() == 0x7f0a0058)
        {
            getWizard().setComponentDistance(scisoundbarwizard.currentlyAttaching(), com.sonos.sclib.SCISoundbarWizard.SoundbarDistance.SOUNDBAR_DISTANCE_DEFAULT);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SCISoundbarWizard scisoundbarwizard = getWizard();
        boolean flag;
        if(zpModel == com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1)
            flag = true;
        else
            flag = false;
        if(scisoundbarwizard.currentlyAttaching() == com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND)
        {
            if(flag)
                view.findViewById(0x7f0a01a1).setVisibility(0);
            else
                view.findViewById(0x7f0a019f).setVisibility(0);
        } else
        if(flag)
            view.findViewById(0x7f0a01a2).setVisibility(0);
        else
            view.findViewById(0x7f0a01a0).setVisibility(0);
        return view;
    }

    private com.sonos.sclib.SCIDevice.DeviceModel zpModel;
}
