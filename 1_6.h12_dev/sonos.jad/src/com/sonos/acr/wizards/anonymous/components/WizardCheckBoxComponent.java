// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;
import android.widget.CheckBox;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.SCIWizard;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardCheckBoxComponent extends WizardComponent
{

    public WizardCheckBoxComponent()
    {
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        checkbox = (CheckBox)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300b6, viewgroup, false);
        checkbox.setText(text);
        checkbox.setVisibility(0);
        CheckBox checkbox1 = checkbox;
        boolean flag;
        if(wizard.getWizard().getInputIndex(inputIndex) != 0)
            flag = true;
        else
            flag = false;
        checkbox1.setChecked(flag);
        checkbox.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SCIWizard sciwizard = wizard.getWizard();
                com.sonos.sclib.SCIWizard.WizInputSelection wizinputselection = inputIndex;
                int i;
                if(checkbox.isChecked())
                    i = 1;
                else
                    i = 0;
                sciwizard.selectInput(wizinputselection, i);
            }

            final WizardCheckBoxComponent this$0;

            
            {
                this$0 = WizardCheckBoxComponent.this;
                super();
            }
        }
);
        return checkbox;
    }

    public String getText()
    {
        return text;
    }

    public String logString()
    {
        return (new StringBuilder()).append("Checkbox Component - ").append(text).toString();
    }

    protected void setInput(int i)
    {
        inputIndex = com.sonos.sclib.SCIWizard.WizInputSelection.swigToEnum(i);
    }

    protected void setText(String s)
    {
        text = s;
    }

    protected void setWizard(Wizard wizard1)
    {
        wizard = wizard1;
    }

    private CheckBox checkbox;
    private com.sonos.sclib.SCIWizard.WizInputSelection inputIndex;
    private String text;
    private Wizard wizard;



}
