// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.sonosnet;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCISonosNetSetupWizard;
import com.sonos.sclib.SCIWizard;

// Referenced classes of package com.sonos.acr.wizards.sonosnet:
//            SonosnetWizard

public class SonosnetWizardState extends WizardState
{

    public SonosnetWizardState(SonosnetWizard sonosnetwizard, int i, int j)
    {
        super(sonosnetwizard, i, j);
    }

    public SonosnetWizardState(SonosnetWizard sonosnetwizard, com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState sonosnetsetupwizardstate, int i)
    {
        super(sonosnetwizard, sonosnetsetupwizardstate.swigValue(), i);
    }

    public String getBackButtonText()
    {
        String s;
        if(sclibWizardState == com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.STATE_SONOSNETSETUP_DETAILS.swigValue())
            s = getString(0x7f0c0037);
        else
            s = super.getBackButtonText();
        return s;
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((SonosnetWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCISonosNetSetupWizard getWizard()
    {
        return (SCISonosNetSetupWizard)((SonosnetWizard)sonosWizard).getWizard();
    }

    public boolean isBackEnabled()
    {
        boolean flag;
        if(sclibWizardState == com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.STATE_SONOSNETSETUP_DETAILS.swigValue() || super.isBackEnabled())
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tBody1:").append(((SonosnetWizard)sonosWizard).getRecommendedText(SCISonosNetSetupWizard.STRID_PRESENTATION_BODY_1)).append("\n\tBody2:").append(((SonosnetWizard)sonosWizard).getRecommendedText(SCISonosNetSetupWizard.STRID_PRESENTATION_BODY_2)).toString();
        SLog.v(LOG_TAG, s);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        populateView(view, 0x7f0a0051, SCIWizard.STRID_PRESENTATION_TITLE);
        populateView(view, 0x7f0a0052, SCISonosNetSetupWizard.STRID_PRESENTATION_BODY_1);
        populateView(view, 0x7f0a0053, SCISonosNetSetupWizard.STRID_PRESENTATION_BODY_2);
        logWizardText();
        return view;
    }

    protected static final int POPUP_DIALOG_DISPLAY_TIME_MSECS = 5000;
}
