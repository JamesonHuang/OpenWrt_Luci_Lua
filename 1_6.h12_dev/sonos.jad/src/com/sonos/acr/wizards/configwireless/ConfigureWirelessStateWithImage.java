// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.configwireless;

import android.view.*;
import android.widget.ImageView;

// Referenced classes of package com.sonos.acr.wizards.configwireless:
//            ConfigureWirelessState, ConfigureWirelessWizard

public class ConfigureWirelessStateWithImage extends ConfigureWirelessState
{

    public ConfigureWirelessStateWithImage(ConfigureWirelessWizard configurewirelesswizard, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate, int i)
    {
        super(configurewirelesswizard, configurewirelesswizardstate, i);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        ((ImageView)view.findViewById(0x7f0a005c)).setImageResource(0x7f06005e);
        return view;
    }
}
