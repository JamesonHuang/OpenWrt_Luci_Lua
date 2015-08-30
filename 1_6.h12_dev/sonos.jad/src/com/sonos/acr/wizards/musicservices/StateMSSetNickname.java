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

public class StateMSSetNickname extends MusicServicesWizardState
{

    public StateMSSetNickname(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_SET_NICKNAME, 0x7f03002d);
    }

    private void saveData()
    {
        getWizard().getNicknameInput().setString(nicknameText.getText().toString());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        nicknameText = (EditText)view.findViewById(0x7f0a00c1);
        nicknameText.setInputType(0x80000);
        SCIStringInput scistringinput = getWizard().getNicknameInput();
        nicknameText.setText(scistringinput.getString());
        InputFilter ainputfilter[] = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(scistringinput.getMaxNumChars());
        nicknameText.setFilters(ainputfilter);
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
            SonosToast.popupDialog(getString(0x7f0c0065), null);
            flag = false;
        }
        return flag;
    }

    private EditText nicknameText;
}
