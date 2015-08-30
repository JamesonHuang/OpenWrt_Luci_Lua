// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizard

public class SoundbarWizardState extends WizardState
{

    public SoundbarWizardState(SoundbarWizard soundbarwizard, int i, int j)
    {
        this(soundbarwizard, i, j, false, false);
    }

    public SoundbarWizardState(SoundbarWizard soundbarwizard, int i, int j, boolean flag, boolean flag1)
    {
        super(soundbarwizard, i, j, flag, flag1);
    }

    public SoundbarWizardState(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, int i)
    {
        this(soundbarwizard, soundbarwizardstate.swigValue(), i, false, false);
    }

    public SoundbarWizardState(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, int i, boolean flag, boolean flag1)
    {
        this(soundbarwizard, soundbarwizardstate.swigValue(), i, flag, flag1);
    }

    private void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tTitle:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_TITLE_1)).append("\n\tInstructions:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_WIZARD_INSTRUCTIONS)).append("\n\tBody:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY)).append("\n\tBody1:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_1)).append("\n\tBody2:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_2)).append("\n\tBody3:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_3)).append("\n\tBody4:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_4)).append("\n\tBody5:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_5)).append("\n\tInput1:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_1)).append("\n\tInput2:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_2)).append("\n\tInput3:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_3)).append("\n\tInput4:").append(((SoundbarWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_4)).toString();
        SLog.v(LOG_TAG, s);
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((SoundbarWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCISoundbarWizard getWizard()
    {
        return (SCISoundbarWizard)((SoundbarWizard)sonosWizard).getWizard();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        populateView(view, 0x7f0a0051, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_TITLE_1.swigValue());
        populateView(view, 0x7f0a0052, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_1.swigValue());
        populateView(view, 0x7f0a0053, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_2.swigValue());
        populateView(view, 0x7f0a005d, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_3.swigValue());
        populateView(view, 0x7f0a005e, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_BODY_4.swigValue());
        populateView(view, 0x7f0a0056, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_1.swigValue());
        populateView(view, 0x7f0a0057, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_2.swigValue());
        populateView(view, 0x7f0a0058, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_INPUT_3.swigValue());
        populateView(view, 0x7f0a00c3, com.sonos.sclib.SCISoundbarWizard.SoundbarWizStringID.SOUNDBAR_STRID_WIZARD_INSTRUCTIONS.swigValue());
        logWizardText();
        return view;
    }
}
