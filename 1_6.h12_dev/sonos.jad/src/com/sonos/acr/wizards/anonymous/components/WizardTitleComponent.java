// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.*;
import com.sonos.acr.view.SonosTextView;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardTitleComponent extends WizardComponent
{

    public WizardTitleComponent()
    {
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        SonosTextView sonostextview = (SonosTextView)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300bd, viewgroup, false);
        sonostextview.setText(title);
        sonostextview.setVisibility(0);
        return sonostextview;
    }

    public String getTitle()
    {
        return title;
    }

    public String logString()
    {
        return (new StringBuilder()).append("Title Component - ").append(title).toString();
    }

    protected void setTitle(String s)
    {
        title = s;
    }

    private String title;
}
