// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.sonosnet;

import android.view.*;
import android.widget.CheckBox;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCISonosNetSetupWizard;
import com.sonos.sclib.SCISystem;

// Referenced classes of package com.sonos.acr.wizards.sonosnet:
//            SonosnetWizardState, SonosnetWizard

public class StateSonosnetActivate extends SonosnetWizardState
{

    public StateSonosnetActivate(SonosnetWizard sonosnetwizard)
    {
        super(sonosnetwizard, com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.STATE_SONOSNETSETUP_CHOOSEOPTION, 0x7f030099);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        allowConnectionCheckBox = (CheckBox)view.findViewById(0x7f0a01cc);
        populateView(view, 0x7f0a01cc, SCISonosNetSetupWizard.STRID_PRESENTATION_BODY_2);
        SCISystem scisystem = LibraryUtils.getSystem();
        if(scisystem != null)
            allowConnectionCheckBox.setChecked(scisystem.isSonosNetAllowed());
        return view;
    }

    public boolean onNextPressed()
    {
        if(allowConnectionCheckBox != null)
        {
            SCISystem scisystem = LibraryUtils.getSystem();
            if(scisystem != null)
                scisystem.setSonosNetAllowed(allowConnectionCheckBox.isChecked());
        }
        ((SonosnetWizard)sonosWizard).onComplete();
        return true;
    }

    private CheckBox allowConnectionCheckBox;
}
