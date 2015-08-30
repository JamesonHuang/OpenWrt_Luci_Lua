// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.ImageView;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.SCISetupWizard;
import java.util.Timer;
import java.util.TimerTask;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupLights extends SetupWizardState
{

    public StateSetupLights(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate, int i, boolean flag, boolean flag1)
    {
        super(setupwizard, setupwizardstate, i, flag, flag1);
        blinkTimer = new Timer();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        displayImage = (ImageView)view.findViewById(0x7f0a0193);
        if(getWizard().getState() == com.sonos.sclib.SCISetupWizard.SetupWizardState.STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT.swigValue())
        {
            displayImage.setImageResource(0x7f060052);
            blinkTimer.scheduleAtFixedRate(new TimerTask() {

                public void run()
                {
                    if(access$100 != null && ((SetupWizard)access$100).getActivity() != null)
                        ((SetupWizard)access$100).getActivity().runOnUiThread(new Runnable() {

                            public void run()
                            {
                                if(displayImage != null)
                                {
                                    ImageView imageview = displayImage;
                                    byte byte0;
                                    if(displayImage.getVisibility() == 0)
                                        byte0 = 4;
                                    else
                                        byte0 = 0;
                                    imageview.setVisibility(byte0);
                                }
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                }

                final StateSetupLights this$0;

            
            {
                this$0 = StateSetupLights.this;
                super();
            }
            }
, 750L, 750L);
        } else
        {
            displayImage.setVisibility(0);
        }
        return view;
    }

    Timer blinkTimer;
    ImageView displayImage;



}
