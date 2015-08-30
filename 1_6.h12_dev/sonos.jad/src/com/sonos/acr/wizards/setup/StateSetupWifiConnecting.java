// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.os.Handler;
import android.view.*;
import android.widget.ProgressBar;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.PeriodicExecutor;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupWifiConnecting extends SetupWizardState
{

    public StateSetupWifiConnecting(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030090, true, true);
        progressStartTime = 0L;
        updateProgressBarTask = new PeriodicExecutor(1000L, 1500 * getWizard().getWifiConnectionTimeout(), SonosApplication.getInstance().getHandler()) {

            public void execute()
            {
                if(progressBar != null)
                {
                    int i = progressBar.getMax();
                    int j = Math.min(i, (int)((double)i * ((double)getTimeSinceStart() / (double)getmDuration())));
                    progressBar.setProgress(j);
                    if(j >= i)
                        stop();
                } else
                {
                    stop();
                }
            }

            public long getTimeSinceStart()
            {
                return System.currentTimeMillis() - progressStartTime;
            }

            public void onStart()
            {
                if(progressStartTime == 0L)
                    progressStartTime = System.currentTimeMillis();
            }

            final StateSetupWifiConnecting this$0;

            
            {
                this$0 = StateSetupWifiConnecting.this;
                super(l, l1, handler);
            }
        }
;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        progressBar = (ProgressBar)view.findViewById(0x7f0a01b0);
        return view;
    }

    public void onEntry(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        super.onEntry(statetransitiontype);
        updateProgressBarTask.start();
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        super.onExit(statetransitiontype);
        updateProgressBarTask.stop();
    }

    public static final int JOIN_HH_PROGRESS_UPDATE_SECS = 1;
    private ProgressBar progressBar;
    private long progressStartTime;
    PeriodicExecutor updateProgressBarTask;




/*
    static long access$102(StateSetupWifiConnecting statesetupwificonnecting, long l)
    {
        statesetupwificonnecting.progressStartTime = l;
        return l;
    }

*/
}
