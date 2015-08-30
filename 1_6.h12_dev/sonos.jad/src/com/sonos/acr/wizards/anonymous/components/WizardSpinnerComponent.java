// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardSpinnerComponent extends WizardComponent
{

    public WizardSpinnerComponent()
    {
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300bb, viewgroup, false);
        view.setVisibility(0);
        return view;
    }

    public String logString()
    {
        return "Spinner Component";
    }
}
