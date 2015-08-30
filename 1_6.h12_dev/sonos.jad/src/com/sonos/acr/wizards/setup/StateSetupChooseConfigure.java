// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.*;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupChooseConfigure extends SetupWizardState
{

    public StateSetupChooseConfigure(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030076);
    }

    private void populateList()
    {
        devices = new ArrayList();
        devicesIDs = new ArrayList();
        EnumeratorAdapter enumeratoradapter = new EnumeratorAdapter(getWizard().getUnconfiguredDevices(), sclib.SCIDEVICE_INTERFACE);
        if(enumeratoradapter != null)
        {
            Iterator iterator = enumeratoradapter.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                SCIDevice scidevice = (SCIDevice)iterator.next();
                if(!scidevice.isConfigured() && scidevice.isCompatible())
                {
                    devices.add(scidevice.getTitle());
                    devicesIDs.add(scidevice.getID());
                }
            } while(true);
        }
        ArrayAdapter arrayadapter = new ArrayAdapter(((SetupWizard)sonosWizard).getActivity(), 0x7f0300c0, devices);
        arrayadapter.setDropDownViewResource(0x1090009);
        deviceSpinner.setAdapter(arrayadapter);
        if(devices.toArray().length > 0)
            deviceSpinner.setSelection(0);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        rootView = super.onCreateView(layoutinflater, viewgroup);
        deviceSpinner = (Spinner)rootView.findViewById(0x7f0a018e);
        deviceSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                getWizard().setDeviceToConfigure((String)devicesIDs.get(deviceSpinner.getSelectedItemPosition()));
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final StateSetupChooseConfigure this$0;

            
            {
                this$0 = StateSetupChooseConfigure.this;
                super();
            }
        }
);
        populateList();
        return rootView;
    }

    Spinner deviceSpinner;
    ArrayList devices;
    ArrayList devicesIDs;
}
