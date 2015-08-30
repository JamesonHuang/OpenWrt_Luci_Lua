// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.view.*;
import android.widget.EditText;
import com.sonos.acr.util.SonosToast;
import com.sonos.sclib.SCIMusicServiceWizard;
import com.sonos.sclib.SCIStringInput;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSPassword extends MusicServicesWizardState
{

    public StateMSPassword(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_PASSWORD, 0x7f030032);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        passwordField = (EditText)view.findViewById(0x7f0a00c4);
        return view;
    }

    public boolean onNextPressed()
    {
        getWizard().getPasswordInput().setString(passwordField.getText().toString());
        boolean flag;
        if(getWizard().areInputsValid())
        {
            flag = true;
        } else
        {
            SonosToast.popupDialog(getString(0x7f0c0068), null);
            flag = false;
        }
        return flag;
    }

    private EditText passwordField;
}
