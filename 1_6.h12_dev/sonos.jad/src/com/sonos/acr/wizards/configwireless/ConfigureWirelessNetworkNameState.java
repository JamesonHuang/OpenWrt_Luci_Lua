// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.configwireless;

import android.view.*;
import android.widget.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.setup.CustomZPSpinnerAdapter;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.configwireless:
//            ConfigureWirelessState, ConfigureWirelessWizard

public class ConfigureWirelessNetworkNameState extends ConfigureWirelessState
{

    public ConfigureWirelessNetworkNameState(ConfigureWirelessWizard configurewirelesswizard, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate)
    {
        super(configurewirelesswizard, configurewirelesswizardstate, 0x7f030014);
        initialSSID = "";
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        ssidText = (EditText)view.findViewById(0x7f0a005a);
        ssidSpinner = (Spinner)view.findViewById(0x7f0a005b);
        spinnerAdapter = new CustomZPSpinnerAdapter(((ConfigureWirelessWizard)sonosWizard).getActivity());
        spinnerAdapter.setResourceIds(0x7f0300c0, 0x1090009);
        ssidSpinner.setAdapter(spinnerAdapter);
        ssidSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view1, int i, long l)
            {
                String s1 = spinnerAdapter.getZonePlayerId(i);
                ssidText.setText(s1);
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final ConfigureWirelessNetworkNameState this$0;

            
            {
                this$0 = ConfigureWirelessNetworkNameState.this;
                super();
            }
        }
);
        initialSSID = ((SCIConfigureWirelessWizard)((ConfigureWirelessWizard)sonosWizard).getWizard()).getSSID();
        if(!initialSSID.equals(""))
        {
            ssidText.setText(initialSSID);
            spinnerAdapter.addItem(initialSSID, "", initialSSID);
            spinnerAdapter.notifyDataSetChanged();
        }
        populateView(view, 0x7f0a0059, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_LABEL_1.swigValue());
        SCIEnumerator scienumerator = ((SCIConfigureWirelessWizard)((ConfigureWirelessWizard)sonosWizard).getWizard()).getScanListEntries();
        scienumerator.reset();
        do
        {
            if(!scienumerator.moveNext())
                break;
            String s = ((SCINetstartScanListEntry)scienumerator.getCurrent(sclibConstants.SCINETSTARTSCANLISTENTRY_INTERFACE)).getSSID();
            if(!s.equals(initialSSID))
                spinnerAdapter.addItem(s, "", s);
        } while(true);
        spinnerAdapter.notifyDataSetChanged();
        return view;
    }

    public boolean onNextPressed()
    {
        SCIConfigureWirelessWizard sciconfigurewirelesswizard = getWizard();
        sciconfigurewirelesswizard.setSSID(ssidText.getText().toString());
        boolean flag;
        if(!sciconfigurewirelesswizard.areInputsValid())
        {
            SLog.d(LOG_TAG, "WiFi credentials not set - not all inputs are valid");
            flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    private String initialSSID;
    private CustomZPSpinnerAdapter spinnerAdapter;
    private Spinner ssidSpinner;
    private EditText ssidText;


}
