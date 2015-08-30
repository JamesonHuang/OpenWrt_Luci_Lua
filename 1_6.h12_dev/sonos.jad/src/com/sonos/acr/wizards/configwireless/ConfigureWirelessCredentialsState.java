// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.configwireless;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.*;
import android.widget.CheckBox;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.SonosEditText;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.configwireless:
//            ConfigureWirelessState, ConfigureWirelessWizard

public class ConfigureWirelessCredentialsState extends ConfigureWirelessState
{

    public ConfigureWirelessCredentialsState(ConfigureWirelessWizard configurewirelesswizard, com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate)
    {
        super(configurewirelesswizard, configurewirelesswizardstate, 0x7f030013, true);
        sciStringInput = ((SCIConfigureWirelessWizard)configurewirelesswizard.getWizard()).getPasswordInput();
        SCIPropertyBag scipropertybag = getWizard().getOriginalWifiInfo();
        if(scipropertybag != null)
        {
            originalSSID = scipropertybag.getStrProp(sclib.SCI_WIFI_SSID);
            isOriginalNetworkOpen = scipropertybag.getBoolProp(sclib.SCI_WIFI_OPEN);
        }
    }

    private void saveData()
    {
        sciStringInput.setString(passkeyInput.getText().toString());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        passkeyInput = (SonosEditText)view.findViewById(0x7f0a0054);
        passkeyInput.setText(sciStringInput.getString());
        passkeyInput.setSingleLine();
        passkeyInput.setTypeface(ViewUtils.SONOS_REGULAR);
        passkeyInput.setTransformationMethod(new PasswordTransformationMethod());
        passkeyInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                saveData();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final ConfigureWirelessCredentialsState this$0;

            
            {
                this$0 = ConfigureWirelessCredentialsState.this;
                super();
            }
        }
);
        showPasswordCheckBox = (CheckBox)view.findViewById(0x7f0a0055);
        showPasswordCheckBox.setText(getWizard().getRecommendedPresentationText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID.CONFIGUREWIRELESS_STRID_LABEL_3.swigValue()));
        showPasswordCheckBox.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                int i = passkeyInput.getInputType();
                int j;
                if(showPasswordCheckBox.isChecked())
                    j = 0x90 | i & 0xffffff7f;
                else
                    j = 0x80 | i & 0xffffff6f;
                passkeyInput.setInputType(j);
                passkeyInput.setSingleLine();
                passkeyInput.setTypeface(ViewUtils.SONOS_REGULAR);
                if(showPasswordCheckBox.isChecked())
                    passkeyInput.setTransformationMethod(new SingleLineTransformationMethod());
                else
                    passkeyInput.setTransformationMethod(new PasswordTransformationMethod());
                passkeyInput.setSelection(passkeyInput.getText().length());
            }

            final ConfigureWirelessCredentialsState this$0;

            
            {
                this$0 = ConfigureWirelessCredentialsState.this;
                super();
            }
        }
);
        if(getWizard().isOpenNetwork(getWizard().getSSID()) || getWizard().getSSID().equalsIgnoreCase(originalSSID) && isOriginalNetworkOpen)
        {
            view.findViewById(0x7f0a0053).setVisibility(4);
            passkeyInput.setVisibility(4);
            showPasswordCheckBox.setVisibility(4);
            if(getWizard().isNested())
                view.findViewById(0x7f0a0058).setVisibility(4);
        }
        return view;
    }

    private boolean isOriginalNetworkOpen;
    private String originalSSID;
    private SonosEditText passkeyInput;
    private SCIStringInput sciStringInput;
    private CheckBox showPasswordCheckBox;



}
