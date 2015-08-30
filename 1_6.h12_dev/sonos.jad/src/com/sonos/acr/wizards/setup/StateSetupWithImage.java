// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.ImageView;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupWithImage extends SetupWizardState
{

    public StateSetupWithImage(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate, int i, int j)
    {
        super(setupwizard, setupwizardstate, i);
        imageResId = 0;
        imageResId = j;
    }

    public StateSetupWithImage(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate, int i, int j, boolean flag, boolean flag1)
    {
        super(setupwizard, setupwizardstate, i, flag, flag1);
        imageResId = 0;
        imageResId = j;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        ((ImageView)view.findViewById(0x7f0a005c)).setImageResource(imageResId);
        return view;
    }

    int imageResId;
}
