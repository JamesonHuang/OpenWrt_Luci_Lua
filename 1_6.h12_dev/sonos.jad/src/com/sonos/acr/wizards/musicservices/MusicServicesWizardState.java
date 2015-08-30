// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizard

public class MusicServicesWizardState extends WizardState
{

    public MusicServicesWizardState(MusicServicesWizard musicserviceswizard, int i, int j)
    {
        super(musicserviceswizard, i, j);
    }

    public MusicServicesWizardState(MusicServicesWizard musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState musicservicewizardstate, int i)
    {
        super(musicserviceswizard, musicservicewizardstate.swigValue(), i);
    }

    public MusicServicesWizardState(MusicServicesWizard musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState musicservicewizardstate, int i, boolean flag, boolean flag1)
    {
        super(musicserviceswizard, musicservicewizardstate.swigValue(), i, flag, flag1);
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((MusicServicesWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCIMusicServiceWizard getWizard()
    {
        return (SCIMusicServiceWizard)((MusicServicesWizard)sonosWizard).getWizard();
    }

    protected void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tTitle:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_TITLE_1)).append("\n\tInstructions:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INSTRUCTIONS)).append("\n\tBody:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY)).append("\n\tBody1:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_1)).append("\n\tBody2:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_2)).append("\n\tBody3:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_3)).append("\n\tBody4:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_4)).append("\n\tInput1:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INPUT_1)).append("\n\tInput2:").append(((MusicServicesWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INPUT_2)).toString();
        SLog.v(LOG_TAG, s);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        populateView(view, 0x7f0a0051, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_TITLE_1.swigValue());
        populateView(view, 0x7f0a0052, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_1.swigValue());
        populateView(view, 0x7f0a0053, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_2.swigValue());
        populateView(view, 0x7f0a005d, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_3.swigValue());
        populateView(view, 0x7f0a005e, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_BODY_4.swigValue());
        populateView(view, 0x7f0a0056, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INPUT_1.swigValue());
        populateView(view, 0x7f0a0057, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INPUT_2.swigValue());
        populateView(view, 0x7f0a00c3, com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID.MSWIZ_STRID_INSTRUCTIONS.swigValue());
        logWizardText();
        return view;
    }

    protected static final int POPUP_DIALOG_DISPLAY_TIME_MSECS = 5000;
}
