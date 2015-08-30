// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import android.view.*;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sonos.sclib.SCIOnlineUpdateWizard;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizardState, OnlineUpdateWizard

public class StateOUUpgradeProgress extends OnlineUpdateWizardState
{

    public StateOUUpgradeProgress(OnlineUpdateWizard onlineupdatewizard)
    {
        super(onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS, 0x7f03004a);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        progressBar = (ProgressBar)view.findViewById(0x7f0a0113);
        progressBar.setProgress(5 + getWizard().getUpdateCompletedPercentage());
        return view;
    }

    public void onDeviceStatusChanged()
    {
        super.onDeviceStatusChanged();
        progressBar.setProgress(5 + getWizard().getUpdateCompletedPercentage());
        updateWizardText(rootView);
        logWizardText();
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        if(statetransitiontype == com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_FORWARD)
            progressBar.setProgress(105);
    }

    private ProgressBar progressBar;
    private TextView statusText;
}
