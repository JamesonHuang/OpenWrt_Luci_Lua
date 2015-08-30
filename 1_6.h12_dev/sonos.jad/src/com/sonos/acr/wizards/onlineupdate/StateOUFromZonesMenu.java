// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import android.view.*;
import android.widget.TextView;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizardState, OnlineUpdateWizard

public class StateOUFromZonesMenu extends OnlineUpdateWizardState
{

    public StateOUFromZonesMenu(OnlineUpdateWizard onlineupdatewizard)
    {
        super(onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_FROM_ZONES_MENU_START, 0x7f0300b3);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = layoutinflater.inflate(0x7f0300b3, viewgroup, false);
        TextView textview = (TextView)view.findViewById(0x7f0a0051);
        textview.setVisibility(0);
        textview.setText(0x7f0c009f);
        TextView textview1 = (TextView)view.findViewById(0x7f0a0052);
        textview1.setText(0x7f0c009e);
        textview1.setVisibility(0);
        return view;
    }
}
