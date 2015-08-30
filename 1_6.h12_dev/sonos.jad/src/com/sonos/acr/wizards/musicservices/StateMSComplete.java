// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.view.*;
import android.widget.TextView;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSComplete extends MusicServicesWizardState
{

    public StateMSComplete(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_COMPLETE, 0x7f030035);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        serviceSetup = (TextView)view.findViewById(0x7f0a00c7);
        String s = serviceSetup.getText().toString();
        String s1 = getWizard().getServiceName();
        String s2 = getString(0x7f0c006b);
        Object aobj[] = new Object[2];
        aobj[0] = s1;
        aobj[1] = s;
        String s3 = String.format(s2, aobj);
        serviceSetup.setText(s3);
        return view;
    }

    private TextView serviceSetup;
}
