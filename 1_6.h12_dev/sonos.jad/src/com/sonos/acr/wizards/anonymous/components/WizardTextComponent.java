// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;
import android.widget.TextView;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardTextComponent extends WizardComponent
{

    public WizardTextComponent()
    {
        bold = false;
        selectable = false;
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        TextView textview = (TextView)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300bc, viewgroup, false);
        textview.setText(text);
        if(bold)
            textview.setTypeface(textview.getTypeface(), 1);
        if(selectable && android.os.Build.VERSION.SDK_INT >= 11)
            textview.setTextIsSelectable(true);
        textview.setVisibility(0);
        return textview;
    }

    public String getText()
    {
        return text;
    }

    public String logString()
    {
        return (new StringBuilder()).append("Text Component - ").append(text).toString();
    }

    public void setBold()
    {
        bold = true;
    }

    public void setSelectable()
    {
        selectable = true;
    }

    protected void setText(String s)
    {
        text = s;
    }

    private boolean bold;
    private boolean selectable;
    private String text;
}
