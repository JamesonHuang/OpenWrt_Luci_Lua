// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.*;
import android.widget.TextView;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSMultipleAccountsAdded extends MusicServicesWizardState
{

    public StateMSMultipleAccountsAdded(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED, 0x7f0300b3);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        TextView textview = (TextView)view.findViewById(0x7f0a0053);
        String s = textview.getText().toString();
        SpannableString spannablestring = new SpannableString(s);
        int i = s.indexOf("\u22EF");
        if(i != -1)
            spannablestring.setSpan(new ImageSpan(((MusicServicesWizard)sonosWizard).getActivity(), 0x7f020056, 1), i, i + "\u22EF".length(), 33);
        textview.setText(spannablestring);
        return view;
    }
}
