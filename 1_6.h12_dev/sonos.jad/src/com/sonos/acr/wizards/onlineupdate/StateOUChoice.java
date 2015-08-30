// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import android.view.*;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.SCIOnlineUpdateWizard;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizardState, OnlineUpdateWizard

public class StateOUChoice extends OnlineUpdateWizardState
{

    public StateOUChoice(OnlineUpdateWizard onlineupdatewizard)
    {
        super(onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CHOICE, 0x7f030048, true, true);
    }

    public void onClick(View view)
    {
        int i;
        SCIOnlineUpdateWizard scionlineupdatewizard;
        i = view.getId();
        scionlineupdatewizard = getWizard();
        if(i != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        transitionNext();
_L4:
        return;
_L2:
        if(i == 0x7f0a0057 && scionlineupdatewizard != null)
            scionlineupdatewizard.abort();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SonosButton sonosbutton = (SonosButton)view.findViewById(0x7f0a0056);
        SonosButton sonosbutton1 = (SonosButton)view.findViewById(0x7f0a0057);
        SCIOnlineUpdateWizard scionlineupdatewizard = getWizard();
        if(scionlineupdatewizard != null)
        {
            sonosbutton.setText(scionlineupdatewizard.getChoiceContinueLabel());
            sonosbutton1.setText(scionlineupdatewizard.getChoiceCancelLabel());
            sonosbutton.setVisibility(0);
            sonosbutton1.setVisibility(0);
            sonosbutton.setOnClickListener(this);
            sonosbutton1.setOnClickListener(this);
        }
        return view;
    }
}
