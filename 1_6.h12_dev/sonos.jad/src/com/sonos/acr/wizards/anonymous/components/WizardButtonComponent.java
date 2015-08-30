// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.SCIWizard;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardButtonComponent extends WizardComponent
{

    public WizardButtonComponent()
    {
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        SonosButton sonosbutton = (SonosButton)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300b5, viewgroup, false);
        sonosbutton.setText(text);
        sonosbutton.setVisibility(0);
        sonosbutton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                wizard.getWizard().selectInput(inputIndex, 0);
            }

            final WizardButtonComponent this$0;

            
            {
                this$0 = WizardButtonComponent.this;
                super();
            }
        }
);
        return sonosbutton;
    }

    public String getText()
    {
        return text;
    }

    public String logString()
    {
        return (new StringBuilder()).append("Text Component - ").append(text).toString();
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

    private com.sonos.sclib.SCIWizard.WizInputSelection inputIndex;
    private String text;
    private Wizard wizard;


}
