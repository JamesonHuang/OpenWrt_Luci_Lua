// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCIOnlineUpdateWizard;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizard

public class OnlineUpdateWizardState extends WizardState
{

    public OnlineUpdateWizardState(OnlineUpdateWizard onlineupdatewizard, int i, int j)
    {
        super(onlineupdatewizard, i, j);
    }

    public OnlineUpdateWizardState(OnlineUpdateWizard onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState onlineupdatewizardstate, int i)
    {
        super(onlineupdatewizard, onlineupdatewizardstate.swigValue(), i);
    }

    public OnlineUpdateWizardState(OnlineUpdateWizard onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState onlineupdatewizardstate, int i, boolean flag, boolean flag1)
    {
        super(onlineupdatewizard, onlineupdatewizardstate.swigValue(), i, flag, flag1);
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((OnlineUpdateWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCIOnlineUpdateWizard getWizard()
    {
        return (SCIOnlineUpdateWizard)((OnlineUpdateWizard)sonosWizard).getWizard();
    }

    protected void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tTitle:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_TITLE)).append("\n\tInstructions:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_WIZARD_INSTRUCTIONS)).append("\n\tBody:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY)).append("\n\tBody1:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_1)).append("\n\tBody2:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_2)).append("\n\tBody3:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_3)).append("\n\tInput1:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_1)).append("\n\tInput2:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_2)).append("\n\tInput3:").append(((OnlineUpdateWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_3)).toString();
        SLog.v(LOG_TAG, s);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_2);
            transitionNext();
        } else
        if(view.getId() == 0x7f0a0058)
        {
            getWizard().setSelection(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_3);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        updateWizardText(view);
        logWizardText();
        return view;
    }

    protected void updateWizardText(View view)
    {
        populateView(view, 0x7f0a0051, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_TITLE.swigValue());
        populateView(view, 0x7f0a0052, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_1.swigValue());
        populateView(view, 0x7f0a0053, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_2.swigValue());
        populateView(view, 0x7f0a005d, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_BODY_3.swigValue());
        populateView(view, 0x7f0a0056, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_1.swigValue());
        populateView(view, 0x7f0a0057, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_2.swigValue());
        populateView(view, 0x7f0a0058, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_INPUT_3.swigValue());
        populateView(view, 0x7f0a00c3, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID.OUWIZ_STRID_WIZARD_INSTRUCTIONS.swigValue());
    }

    protected static final int POPUP_DIALOG_DISPLAY_TIME_MSECS = 5000;
}
