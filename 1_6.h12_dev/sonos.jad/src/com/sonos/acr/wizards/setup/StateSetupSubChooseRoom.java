// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.sonos.acr.wizards.WizardView;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, CustomZPSpinnerAdapter, SetupWizard

public class StateSetupSubChooseRoom extends SetupWizardState
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public StateSetupSubChooseRoom(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f03007f);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        zonePlayerAdapter = new CustomZPSpinnerAdapter(((SetupWizard)sonosWizard).getActivity());
        zonePlayerAdapter.setResourceIds(0x1090008, 0x1090009);
        Spinner spinner = (Spinner)view.findViewById(0x7f0a0194);
        spinner.setPrompt(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_1));
        spinner.setAdapter(zonePlayerAdapter);
        spinner.setOnItemSelectedListener(this);
        SCIDevice scidevice;
        for(SCIEnumerator scienumerator = getWizard().getCandidateDevices(); scienumerator.moveNext(); zonePlayerAdapter.addItem(scidevice.getTitle(), scidevice.getIconURI(), scidevice.getID()))
            scidevice = (SCIDevice)scienumerator.getCurrent(sclibConstants.SCIDEVICE_INTERFACE);

        zonePlayerAdapter.notifyDataSetChanged();
        return view;
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        String s = zonePlayerAdapter.getZonePlayerId(i);
        String s1 = zonePlayerAdapter.getItem(i).toString();
        getWizard().setRequestedSubMaster(s, s1);
        ((SetupWizard)sonosWizard).getWizardView().updateButtons(this);
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    private CustomZPSpinnerAdapter zonePlayerAdapter;
}
