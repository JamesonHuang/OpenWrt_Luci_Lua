// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.soundbar;

import android.view.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCISoundbarWizard;

// Referenced classes of package com.sonos.acr.wizards.soundbar:
//            SoundbarWizardState, SoundbarWizard

public class StateSetupSoundbarSurroundConnect extends SoundbarWizardState
{
    static final class SurroundSide extends Enum
    {

        public static SurroundSide valueOf(String s)
        {
            return (SurroundSide)Enum.valueOf(com/sonos/acr/wizards/soundbar/StateSetupSoundbarSurroundConnect$SurroundSide, s);
        }

        public static SurroundSide[] values()
        {
            return (SurroundSide[])$VALUES.clone();
        }

        private static final SurroundSide $VALUES[];
        public static final SurroundSide LEFT;
        public static final SurroundSide RIGHT;
        public static final SurroundSide UNKNOWN;

        static 
        {
            UNKNOWN = new SurroundSide("UNKNOWN", 0);
            LEFT = new SurroundSide("LEFT", 1);
            RIGHT = new SurroundSide("RIGHT", 2);
            SurroundSide asurroundside[] = new SurroundSide[3];
            asurroundside[0] = UNKNOWN;
            asurroundside[1] = LEFT;
            asurroundside[2] = RIGHT;
            $VALUES = asurroundside;
        }

        private SurroundSide(String s, int i)
        {
            super(s, i);
        }
    }

    static final class ConnectState extends Enum
    {

        public static ConnectState valueOf(String s)
        {
            return (ConnectState)Enum.valueOf(com/sonos/acr/wizards/soundbar/StateSetupSoundbarSurroundConnect$ConnectState, s);
        }

        public static ConnectState[] values()
        {
            return (ConnectState[])$VALUES.clone();
        }

        private static final ConnectState $VALUES[];
        public static final ConnectState POST_CONNECT;
        public static final ConnectState PRE_CONNECT;

        static 
        {
            PRE_CONNECT = new ConnectState("PRE_CONNECT", 0);
            POST_CONNECT = new ConnectState("POST_CONNECT", 1);
            ConnectState aconnectstate[] = new ConnectState[2];
            aconnectstate[0] = PRE_CONNECT;
            aconnectstate[1] = POST_CONNECT;
            $VALUES = aconnectstate;
        }

        private ConnectState(String s, int i)
        {
            super(s, i);
        }
    }


    public StateSetupSoundbarSurroundConnect(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030086);
        ConnectState connectstate;
        if(soundbarwizardstate == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CONNECTOK)
            connectstate = ConnectState.POST_CONNECT;
        else
            connectstate = ConnectState.PRE_CONNECT;
        connectState = connectstate;
        showNext = true;
    }

    public StateSetupSoundbarSurroundConnect(SoundbarWizard soundbarwizard, com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState soundbarwizardstate, boolean flag)
    {
        super(soundbarwizard, soundbarwizardstate, 0x7f030086);
        ConnectState connectstate;
        if(soundbarwizardstate == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CONNECTOK)
            connectstate = ConnectState.POST_CONNECT;
        else
            connectstate = ConnectState.PRE_CONNECT;
        connectState = connectstate;
        showNext = flag;
    }

    public boolean hasAltNextButton()
    {
        boolean flag;
        if(!showNext)
            flag = true;
        else
            flag = super.hasAltNextButton();
        return flag;
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a01a4) goto _L2; else goto _L1
_L1:
        getWizard().setComponentToAttach(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a01a5)
        {
            getWizard().setComponentToAttach(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_RIGHT_SURROUND);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        SCISoundbarWizard scisoundbarwizard = getWizard();
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        SonosImageView sonosimageview;
        SurroundSide surroundside;
        if(scisoundbarwizard.getSoundbarSurroundSetup() == com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS1)
            flag = true;
        else
            flag = false;
        flag1 = scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_SUB);
        flag2 = scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND);
        flag3 = scisoundbarwizard.isComponentAttached(com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_RIGHT_SURROUND);
        sonosimageview = (SonosImageView)view.findViewById(0x7f0a01a3);
        if(!scisoundbarwizard.requiresInput())
        {
            SonosButton sonosbutton2 = (SonosButton)view.findViewById(0x7f0a01a4);
            SonosButton sonosbutton3 = (SonosButton)view.findViewById(0x7f0a01a5);
            sonosbutton2.setVisibility(8);
            sonosbutton3.setVisibility(8);
        }
        surroundside = SurroundSide.UNKNOWN;
        if(scisoundbarwizard.currentlyAttaching() == com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_LEFT_SURROUND)
            surroundside = SurroundSide.LEFT;
        else
        if(scisoundbarwizard.currentlyAttaching() == com.sonos.sclib.SCISoundbarWizard.SoundbarComponent.SOUNDBAR_COMP_RIGHT_SURROUND)
            surroundside = SurroundSide.RIGHT;
        if(scisoundbarwizard.getState() == com.sonos.sclib.SCISoundbarWizard.SoundbarWizardState.STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE.swigValue())
        {
            int i;
            SonosButton sonosbutton;
            SonosButton sonosbutton1;
            if(flag1)
                i = 0x7f06006b;
            else
                i = 0x7f06006c;
            sonosimageview.setImageResource(i);
            sonosbutton = (SonosButton)view.findViewById(0x7f0a01a4);
            sonosbutton1 = (SonosButton)view.findViewById(0x7f0a01a5);
            sonosbutton.setVisibility(0);
            sonosbutton1.setVisibility(0);
            sonosbutton.setOnClickListener(this);
            sonosbutton1.setOnClickListener(this);
        } else
        if(surroundside == SurroundSide.LEFT)
        {
            if(connectState == ConnectState.PRE_CONNECT)
            {
                if(!flag1)
                {
                    if(flag3)
                    {
                        if(flag)
                            sonosimageview.setImageResource(0x7f060075);
                        else
                            sonosimageview.setImageResource(0x7f060083);
                    } else
                    {
                        sonosimageview.setImageResource(0x7f060071);
                    }
                } else
                if(flag3)
                {
                    if(flag)
                        sonosimageview.setImageResource(0x7f060072);
                    else
                        sonosimageview.setImageResource(0x7f060080);
                } else
                {
                    sonosimageview.setImageResource(0x7f060070);
                }
            } else
            if(!flag1)
            {
                if(flag3)
                {
                    if(flag)
                        sonosimageview.setImageResource(0x7f060074);
                    else
                        sonosimageview.setImageResource(0x7f060082);
                } else
                if(flag)
                    sonosimageview.setImageResource(0x7f060078);
                else
                    sonosimageview.setImageResource(0x7f060086);
            } else
            if(flag3)
            {
                if(flag)
                    sonosimageview.setImageResource(0x7f060073);
                else
                    sonosimageview.setImageResource(0x7f060081);
            } else
            if(flag)
                sonosimageview.setImageResource(0x7f060077);
            else
                sonosimageview.setImageResource(0x7f060085);
        } else
        if(surroundside == SurroundSide.RIGHT)
        {
            if(connectState == ConnectState.PRE_CONNECT)
            {
                if(!flag1)
                {
                    if(flag2)
                    {
                        if(flag)
                            sonosimageview.setImageResource(0x7f06007f);
                        else
                            sonosimageview.setImageResource(0x7f06008d);
                    } else
                    {
                        sonosimageview.setImageResource(0x7f060093);
                    }
                } else
                if(flag2)
                {
                    if(flag)
                        sonosimageview.setImageResource(0x7f06007e);
                    else
                        sonosimageview.setImageResource(0x7f06008c);
                } else
                {
                    sonosimageview.setImageResource(0x7f060092);
                }
            } else
            if(!flag1)
            {
                if(flag2)
                {
                    if(flag)
                        sonosimageview.setImageResource(0x7f06007b);
                    else
                        sonosimageview.setImageResource(0x7f060089);
                } else
                if(flag)
                    sonosimageview.setImageResource(0x7f06007d);
                else
                    sonosimageview.setImageResource(0x7f06008b);
            } else
            if(flag2)
            {
                if(flag)
                    sonosimageview.setImageResource(0x7f06007a);
                else
                    sonosimageview.setImageResource(0x7f060088);
            } else
            if(flag)
                sonosimageview.setImageResource(0x7f06007c);
            else
                sonosimageview.setImageResource(0x7f06008a);
        } else
        {
            SLog.e(LOG_TAG, "Unexpected state: surround state is unknown");
        }
        return view;
    }

    ConnectState connectState;
    boolean showNext;
}
