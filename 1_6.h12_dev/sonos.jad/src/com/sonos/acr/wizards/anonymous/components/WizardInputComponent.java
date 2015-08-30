// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.text.*;
import android.view.*;
import android.widget.TextView;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.SonosEditText;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.SCIStringInput;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardInputComponent extends WizardComponent
{

    public WizardInputComponent()
    {
    }

    private void saveData()
    {
        sciStringInput.setString(textInput.getText().toString());
        TextView textview = invalidMark;
        byte byte0;
        if(sciStringInput.isValid())
            byte0 = 4;
        else
            byte0 = 0;
        textview.setVisibility(byte0);
        wizard.updateButtons();
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300b8, viewgroup, false);
        textInput = (SonosEditText)view.findViewById(0x7f0a0170);
        textInput.setText(sciStringInput.getString());
        textInput.setHint(hint);
        textInput.setSingleLine();
        textInput.setTypeface(ViewUtils.SONOS_REGULAR);
        invalidMark = (TextView)view.findViewById(0x7f0a01ff);
        TextView textview = invalidMark;
        byte byte0;
        InputFilter ainputfilter[];
        if(sciStringInput.isValid())
            byte0 = 4;
        else
            byte0 = 0;
        textview.setVisibility(byte0);
        ainputfilter = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(sciStringInput.getMaxNumChars());
        textInput.setFilters(ainputfilter);
        textInput.addTextChangedListener(new TextWatcher() {

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

            final WizardInputComponent this$0;

            
            {
                this$0 = WizardInputComponent.this;
                super();
            }
        }
);
        return view;
    }

    public String logString()
    {
        return "Textfield Component";
    }

    protected void setHint(String s)
    {
        hint = s;
    }

    protected void setStringInput(SCIStringInput scistringinput)
    {
        sciStringInput = scistringinput;
    }

    protected void setWizard(Wizard wizard1)
    {
        wizard = wizard1;
    }

    private String hint;
    private TextView invalidMark;
    private SCIStringInput sciStringInput;
    private SonosEditText textInput;
    private Wizard wizard;

}
