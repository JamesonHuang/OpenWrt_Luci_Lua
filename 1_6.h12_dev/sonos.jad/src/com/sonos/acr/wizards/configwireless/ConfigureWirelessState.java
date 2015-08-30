// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.configwireless;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.SCIConfigureWirelessWizard;

// Referenced classes of package com.sonos.acr.wizards.configwireless:
//            ConfigureWirelessWizard

public class ConfigureWirelessState extends WizardState
{

    public ConfigureWirelessState(ConfigureWirelessWizard configurewirelesswizard, int i, int j)
    {
        super(configurewirelesswizard, i, j);
    }

    public ConfigureWirelessState(ConfigureWirelessWizard configurewirelesswizard, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate, int i)
    {
        super(configurewirelesswizard, configurewirelesswizardstate.swigValue(), i);
    }

    public ConfigureWirelessState(ConfigureWirelessWizard configurewirelesswizard, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate, int i, boolean flag)
    {
        super(configurewirelesswizard, configurewirelesswizardstate.swigValue(), i, flag, false);
    }

    public String getStateName()
    {
        return com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.swigToEnum(sclibWizardState).toString();
    }

    protected String getString(int i)
    {
        return ((ConfigureWirelessWizard)sonosWizard).getActivity().getString(i);
    }

    protected SCIConfigureWirelessWizard getWizard()
    {
        return (SCIConfigureWirelessWizard)((ConfigureWirelessWizard)sonosWizard).getWizard();
    }

    protected void logWizardText()
    {
        String s = (new StringBuilder()).append("\nWIZARD TEXTS:\n\tTitle:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_TITLE_1)).append("\n\tBody:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY)).append("\n\tBody1:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_1)).append("\n\tBody2:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_2)).append("\n\tBody3:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_3)).append("\n\tBody4:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_4)).append("\n\tInput1:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_1)).append("\n\tInput2:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_2)).append("\n\tInput3:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_3)).append("\n\tInput4:").append(((ConfigureWirelessWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_4)).toString();
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
        populateView(view, 0x7f0a0051, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_TITLE_1.swigValue());
        populateView(view, 0x7f0a0052, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_1.swigValue());
        populateView(view, 0x7f0a0053, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_2.swigValue());
        populateView(view, 0x7f0a005d, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_3.swigValue());
        populateView(view, 0x7f0a005e, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_BODY_4.swigValue());
        populateView(view, 0x7f0a0056, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_1.swigValue());
        populateView(view, 0x7f0a0057, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_2.swigValue());
        populateView(view, 0x7f0a0058, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_INPUT_3.swigValue());
        logWizardText();
        return view;
    }

    protected static final int POPUP_DIALOG_DISPLAY_TIME_MSECS = 5000;
}
