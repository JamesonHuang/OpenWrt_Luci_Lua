// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.view.ShrinkableImageViewFrameLayout;
import com.sonos.sclib.SCIPropertyBag;
import com.sonos.sclib.SCISetupWizard;

// Referenced classes of package com.sonos.acr.wizards.setup:
//            SetupWizardState, SetupWizard

public class StateSetupDeviceAdded extends SetupWizardState
{

    public StateSetupDeviceAdded(SetupWizard setupwizard, com.sonos.sclib.SCISetupWizard.SetupWizardState setupwizardstate)
    {
        super(setupwizard, setupwizardstate, 0x7f030079, false, true);
    }

    private static int getIdForModel(com.sonos.sclib.SCIDevice.DeviceModel devicemodel)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel = new int[com.sonos.sclib.SCIDevice.DeviceModel.values().length];
                NoSuchFieldError nosuchfielderror10;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_BOOST.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZONEBRIDGE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP80.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP100.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP120.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS5.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS3.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_SOUNDBAR.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_SUB.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_DOCK.ordinal()] = 11;
_L2:
                return;
                nosuchfielderror10;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIDevice.DeviceModel[devicemodel.ordinal()];
        JVM INSTR tableswitch 1 11: default 68
    //                   1 73
    //                   2 79
    //                   3 85
    //                   4 91
    //                   5 91
    //                   6 97
    //                   7 103
    //                   8 109
    //                   9 115
    //                   10 121
    //                   11 127;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
        int i = 0x7f06003b;
_L13:
        return i;
_L2:
        i = 0x7f060040;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 0x7f060041;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f060042;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 0x7f060043;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 0x7f06004a;
        continue; /* Loop/switch isn't completed */
_L7:
        i = 0x7f060048;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 0x7f060046;
        continue; /* Loop/switch isn't completed */
_L9:
        i = 0x7f06004b;
        continue; /* Loop/switch isn't completed */
_L10:
        i = 0x7f06004c;
        continue; /* Loop/switch isn't completed */
_L11:
        i = 0x7f060044;
        if(true) goto _L13; else goto _L12
_L12:
    }

    public boolean hasAltNextButton()
    {
        boolean flag;
        if(inputButton1.getVisibility() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onClick(View view)
    {
        int i = view.getId();
        SCISetupWizard scisetupwizard = getWizard();
        if(i == 0x7f0a0057 || i == 0x7f0a0058)
        {
            boolean flag;
            if(i == 0x7f0a0057)
                flag = true;
            else
                flag = false;
            scisetupwizard.setAddAnotherDevice(flag);
            if(scisetupwizard.areInputsValid())
            {
                SLog.d(LOG_TAG, "Successful -- Device added.");
                transitionNext();
            } else
            {
                SLog.d(LOG_TAG, "Device not added - not all inputs are valid");
            }
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SCISetupWizard scisetupwizard = getWizard();
        ImageView imageview = (ImageView)view.findViewById(0x7f0a018f);
        ShrinkableImageViewFrameLayout shrinkableimageviewframelayout = (ShrinkableImageViewFrameLayout)view.findViewById(0x7f0a0191);
        if(scisetupwizard.getDeviceSetupFailed() || scisetupwizard.configuringWiredComponent() || scisetupwizard.getDeviceModelFound() != com.sonos.sclib.SCIDevice.DeviceModel.DM_SUB && StringUtils.isNotEmptyOrNull(scisetupwizard.getReturnValues().getStrProp("ConnectDetails")))
        {
            shrinkableimageviewframelayout.setVisibility(8);
        } else
        {
            imageview.setImageResource(getIdForModel(scisetupwizard.getDeviceModelFound()));
            String s = scisetupwizard.getDeviceName();
            SLog.d(LOG_TAG, (new StringBuilder()).append("Device Name: ").append(s).toString());
        }
        inputButton1 = view.findViewById(0x7f0a0057);
        if(!scisetupwizard.requiresInput())
        {
            TextView textview = (TextView)view.findViewById(0x7f0a0056);
            textview.setText(((SetupWizard)sonosWizard).getRecommendedText(com.sonos.sclib.SCISetupWizard.SetupWizStringID.SETUP_STRID_BODY_2.swigValue()));
            textview.setVisibility(0);
        }
        return view;
    }

    View inputButton1;
}
