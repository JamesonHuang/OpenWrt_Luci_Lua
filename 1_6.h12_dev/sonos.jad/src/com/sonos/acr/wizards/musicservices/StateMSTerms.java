// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.view.*;
import android.widget.TextView;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSTerms extends MusicServicesWizardState
{

    public StateMSTerms(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_TERMS, 0x7f03002b, true, false);
    }

    public void onClick(View view)
    {
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setAgreeToTerms(true);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setAgreeToTerms(false);
            transitionNext();
            ((MusicServicesWizard)sonosWizard).onComplete();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        ((TextView)view.findViewById(0x7f0a00be)).setText(getWizard().getTermsText());
        return view;
    }
}
