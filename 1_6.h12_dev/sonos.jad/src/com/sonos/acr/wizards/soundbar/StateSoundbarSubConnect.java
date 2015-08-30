// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSoundbarSubConnect extends SoundbarWizardState
{

    public StateSoundbarSubConnect(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030086);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SCISoundbarWizard scisoundbarwizard = getWizard();
        boolean flag;
        boolean flag1;
        SonosImageView sonosimageview;
        if(scisoundbarwizard.getSoundbarSurroundSetup() == com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1)
            flag = true;
        else
            flag = false;
        flag1 = scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_SUB);
        sonosimageview = (SonosImageView)view.findViewById(0x7f0a01a3);
        if(!scisoundbarwizard.requiresInput())
        {
            SonosButton sonosbutton = (SonosButton)view.findViewById(0x7f0a01a4);
            SonosButton sonosbutton1 = (SonosButton)view.findViewById(0x7f0a01a5);
            sonosbutton.setVisibility(8);
            sonosbutton1.setVisibility(8);
        }
        if(flag1)
            if(scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_RIGHT_SURROUND) && scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND))
            {
                if(flag)
                    sonosimageview.setImageResource(0x7f060065);
                else
                    sonosimageview.setImageResource(0x7f060068);
            } else
            if(scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND))
            {
                if(flag)
                    sonosimageview.setImageResource(0x7f060066);
                else
                    sonosimageview.setImageResource(0x7f060069);
            } else
            if(scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_RIGHT_SURROUND))
            {
                if(flag)
                    sonosimageview.setImageResource(0x7f060067);
                else
                    sonosimageview.setImageResource(0x7f06006a);
            } else
            {
                sonosimageview.setImageResource(0x7f060064);
            }
        return view;
    }
}
