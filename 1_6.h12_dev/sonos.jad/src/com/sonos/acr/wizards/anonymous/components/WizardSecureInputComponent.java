// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.*;
import android.widget.CheckBox;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.SonosEditText;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.SCIStringInput;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardSecureInputComponent extends WizardComponent
{

    public WizardSecureInputComponent()
    {
        showPasswordCheckBoxVisibility = 0;
    }

    private void saveData()
    {
        sciStringInput.setString(passkeyInput.getText().toString());
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300ba, viewgroup, false);
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

            final WizardSecureInputComponent this$0;

            
            {
                this$0 = WizardSecureInputComponent.this;
                super();
            }
        }
);
        showPasswordCheckBox = (CheckBox)view.findViewById(0x7f0a0055);
        showPasswordCheckBox.setVisibility(showPasswordCheckBoxVisibility);
        showPasswordCheckBox.setText(checkboxText);
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

            final WizardSecureInputComponent this$0;

            
            {
                this$0 = WizardSecureInputComponent.this;
                super();
            }
        }
);
        return view;
    }

    public String logString()
    {
        return "Secure Textfield Component";
    }

    protected void setCheckboxText(String s)
    {
        checkboxText = s;
    }

    protected void setShowPasswordCheckBoxVisible(boolean flag)
    {
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        showPasswordCheckBoxVisibility = i;
    }

    protected void setStringInput(SCIStringInput scistringinput)
    {
        sciStringInput = scistringinput;
    }

    private String checkboxText;
    private SonosEditText passkeyInput;
    private SCIStringInput sciStringInput;
    private CheckBox showPasswordCheckBox;
    private int showPasswordCheckBoxVisibility;
    private Wizard wizard;



}
