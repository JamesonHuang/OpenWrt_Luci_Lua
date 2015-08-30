// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCISetupWizard;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, CustomZPSpinnerAdapter, SetupWizard

public class StateSetupAutoplayConfigure extends SetupWizardState
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public StateSetupAutoplayConfigure(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030075);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        zonePlayerAdapter = new CustomZPSpinnerAdapter(((SetupWizard)sonosWizard).getActivity());
        zonePlayerAdapter.setResourceIds(0x7f0300c0, 0x1090009);
        zonePlayerSpinner = (Spinner)view.findViewById(0x7f0a018d);
        zonePlayerSpinner.setPrompt(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_INPUT_1));
        zonePlayerSpinner.setAdapter(zonePlayerAdapter);
        zonePlayerSpinner.setOnItemSelectedListener(this);
        zonePlayerAdapter.addItem(getString(0x7f0c00cc), getString(0x7f0c00cd), getString(0x7f0c00cb));
        ZoneDevice zonedevice;
        for(Iterator iterator = LibraryUtils.getHousehold().getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_COMPATIBLE_AND_VISIBLE).iterator(); iterator.hasNext(); zonePlayerAdapter.addItem(zonedevice.getTitle(), zonedevice.getIconUri(), zonedevice.getId()))
            zonedevice = (ZoneDevice)iterator.next();

        zonePlayerAdapter.notifyDataSetChanged();
        return view;
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if(i == 0)
        {
            getWizard().setAutoplayDevice("");
        } else
        {
            String s = zonePlayerAdapter.getZonePlayerId(i);
            getWizard().setAutoplayDevice(s);
            transitionNext();
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    private CustomZPSpinnerAdapter zonePlayerAdapter;
    private Spinner zonePlayerSpinner;
}
