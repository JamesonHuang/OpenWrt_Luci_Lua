// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardListComponent extends WizardComponent
{

    public WizardListComponent()
    {
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        Spinner spinner = (Spinner)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300b9, viewgroup, false);
        spinner.setVisibility(0);
        ArrayAdapter arrayadapter = new ArrayAdapter(viewgroup.getContext(), 0x7f0300c0, items);
        spinner.setAdapter(arrayadapter);
        arrayadapter.setDropDownViewResource(0x1090009);
        spinner.setTag(input);
        spinner.setSelection(initialSelection);
        return spinner;
    }

    public com.sonos.sclib.SCIWizard.WizInputSelection getInput()
    {
        return input;
    }

    public ArrayList getItems()
    {
        return items;
    }

    public String logString()
    {
        String s = (new StringBuilder()).append("List Component:\n\tInput = ").append(input.name()).append("\n").append("\tItems:\n").toString();
        for(Iterator iterator = items.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            s = (new StringBuilder()).append(s).append("\t\t").append(s1).append("\n").toString();
        }

        return s;
    }

    protected void setInitialSelection(int i)
    {
        initialSelection = i;
    }

    protected void setInput(com.sonos.sclib.SCIWizard.WizInputSelection wizinputselection)
    {
        input = wizinputselection;
    }

    protected void setItems(ArrayList arraylist)
    {
        items = arraylist;
    }

    private int initialSelection;
    private com.sonos.sclib.SCIWizard.WizInputSelection input;
    private ArrayList items;
}
