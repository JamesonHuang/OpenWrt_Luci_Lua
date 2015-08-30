// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, CustomZPSpinnerAdapter, SetupWizard

public class StateSetupNameDevice extends SetupWizardState
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public StateSetupNameDevice(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030095);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        zonePlayerAdapter = new CustomZPSpinnerAdapter(layoutinflater.getContext());
        zonePlayerAdapter.setResourceIds(0x7f0300c0, 0x1090009);
        defaultZonePlayerNames = (Spinner)view.findViewById(0x7f0a01b1);
        defaultZonePlayerNames.setAdapter(zonePlayerAdapter);
        defaultZonePlayerNames.setOnItemSelectedListener(this);
        populateRoomNames();
        return view;
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if(getWizard().requiresInput())
        {
            String s = zonePlayerAdapter.getItem(i).toString();
            getWizard().getDeviceNameInput().setString(s);
            String s1 = zonePlayerAdapter.getItemURL(i);
            getWizard().setDeviceIcon(s1);
        }
    }

    public boolean onNextPressed()
    {
        int i = defaultZonePlayerNames.getSelectedItemPosition();
        String s = zonePlayerAdapter.getItem(i).toString();
        SCISetupWizard scisetupwizard = getWizard();
        scisetupwizard.getDeviceNameInput().setString(s);
        scisetupwizard.setDeviceIcon(zonePlayerAdapter.getItemURL(i));
        return true;
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    public void populateRoomNames()
    {
        SCILibrary scilibrary = LibraryUtils.getSCLibManager().getLibrary();
        SCIEnumerator scienumerator = scilibrary.getRoomResourcesForDeviceNaming(getWizard().getConnectedDeviceID());
        SCRoomID scroomid = scilibrary.getDefaultRoomIDForDeviceNaming();
        String s = getWizard().getDeviceName();
        boolean flag = false;
        do
        {
            if(!scienumerator.moveNext())
                break;
            SCIRoomResource sciroomresource = (SCIRoomResource)scienumerator.getCurrent(sclibConstants.SCIROOMRESOURCE_INTERFACE);
            String s1 = sciroomresource.getName();
            String s2 = sciroomresource.getIconURI();
            zonePlayerAdapter.addItem(s1, s2, sciroomresource.getRoomID().name());
            if(s != null && s.equals(s1))
            {
                defaultZonePlayerNames.setSelection(-1 + zonePlayerAdapter.getCount());
                flag = true;
            }
            if(sciroomresource.getRoomID() == scroomid && !flag)
                defaultZonePlayerNames.setSelection(-1 + zonePlayerAdapter.getCount());
        } while(true);
        zonePlayerAdapter.notifyDataSetChanged();
    }

    private Spinner defaultZonePlayerNames;
    CustomZPSpinnerAdapter zonePlayerAdapter;
}
