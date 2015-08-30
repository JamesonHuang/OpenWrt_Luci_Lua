// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.text.InputFilter;
import android.view.*;
import android.widget.EditText;
import com.sonos.acr.util.SonosToast;
import com.sonos.sclib.SCIMusicServiceWizard;
import com.sonos.sclib.SCIStringInput;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSLoginPassword extends MusicServicesWizardState
{

    public StateMSLoginPassword(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_LOGINPASSWORD, 0x7f03002c);
    }

    private void saveData()
    {
        getWizard().getLoginInput().setString(loginid.getText().toString());
        getWizard().getPasswordInput().setString(password.getText().toString());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        loginid = (EditText)view.findViewById(0x7f0a00bf);
        password = (EditText)view.findViewById(0x7f0a00c0);
        loginid.setInputType(0x80000);
        SCIStringInput scistringinput = getWizard().getLoginInput();
        loginid.setText(scistringinput.getString());
        InputFilter ainputfilter[] = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(scistringinput.getMaxNumChars());
        loginid.setFilters(ainputfilter);
        SCIStringInput scistringinput1 = getWizard().getPasswordInput();
        password.setText(scistringinput1.getString());
        InputFilter ainputfilter1[] = new InputFilter[1];
        ainputfilter1[0] = new android.text.InputFilter.LengthFilter(scistringinput1.getMaxNumChars());
        password.setFilters(ainputfilter1);
        return view;
    }

    public boolean onNextPressed()
    {
        saveData();
        boolean flag;
        if(getWizard().areInputsValid())
        {
            flag = true;
        } else
        {
            SonosToast.popupDialog(getString(0x7f0c0067), null);
            flag = false;
        }
        return flag;
    }

    private EditText loginid;
    private EditText password;
}
