// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.view.*;
import android.widget.TextView;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSAccountedNeeded extends MusicServicesWizardState
{

    public StateMSAccountedNeeded(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_ACCOUNTNEEDED, 0x7f03002f);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        TextView textview = (TextView)view.findViewById(0x7f0a0052);
        textview.setText(getWizard().getAccountNeededText());
        textview.setVisibility(0);
        return view;
    }
}
