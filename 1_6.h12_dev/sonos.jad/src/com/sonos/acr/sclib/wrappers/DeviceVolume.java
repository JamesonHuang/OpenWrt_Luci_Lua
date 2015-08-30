// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.sclib.wrappers:
//            GroupVolume

public class DeviceVolume extends GroupVolume
{
    public static final class VolumeMode extends Enum
    {

        public static VolumeMode valueOf(String s)
        {
            return (VolumeMode)Enum.valueOf(com/sonos/acr/sclib/wrappers/DeviceVolume$VolumeMode, s);
        }

        public static VolumeMode[] values()
        {
            return (VolumeMode[])$VALUES.clone();
        }

        private static final VolumeMode $VALUES[];
        public static final VolumeMode DEFAULT;
        public static final VolumeMode FIXED;
        public static final VolumeMode INDIVIDUAL_ONLY;
        public static final VolumeMode UNKNOWN;

        static 
        {
            DEFAULT = new VolumeMode("DEFAULT", 0);
            FIXED = new VolumeMode("FIXED", 1);
            INDIVIDUAL_ONLY = new VolumeMode("INDIVIDUAL_ONLY", 2);
            UNKNOWN = new VolumeMode("UNKNOWN", 3);
            VolumeMode avolumemode[] = new VolumeMode[4];
            avolumemode[0] = DEFAULT;
            avolumemode[1] = FIXED;
            avolumemode[2] = INDIVIDUAL_ONLY;
            avolumemode[3] = UNKNOWN;
            $VALUES = avolumemode;
        }

        private VolumeMode(String s, int i)
        {
            super(s, i);
        }
    }


    public DeviceVolume(SCIGroupVolume scigroupvolume, SCIDeviceVolume scidevicevolume)
    {
        super(scigroupvolume);
        sciDeviceVolume = scidevicevolume;
    }

    public void beginContinuousVolumeAdjustments()
    {
        sciDeviceVolume.beginContinuousVolumeAdjustments();
    }

    public void endContinuousVolumeAdjustments()
    {
        sciDeviceVolume.endContinuousVolumeAdjustments();
    }

    public String getDeviceID()
    {
        return sciDeviceVolume.getDeviceID();
    }

    public String getDeviceName()
    {
        return sciDeviceVolume.getPresentationText(com.sonos.sclib.SCIDeviceVolume.PresentationTextType.DEVICE_TITLE);
    }

    public DeviceVolume getGroupVolume()
    {
        if(!isGroupCoordinator())
            this = super.getGroupVolume();
        return this;
    }

    public VolumeMode getSliderMode()
    {
        if(!isGroupCoordinator()) goto _L2; else goto _L1
_L1:
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[];
            static final int $SwitchMap$com$sonos$sclib$SCIGroupVolumeMode[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode = new int[SCIDeviceOutputMode.values().length];
                NoSuchFieldError nosuchfielderror6;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_FIXED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_UNKNOWN.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_HEADPHONES.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_DEFAULT.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$com$sonos$sclib$SCIGroupVolumeMode = new int[SCIGroupVolumeMode.values().length];
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIGroupVolumeMode[SCIGroupVolumeMode.SCI_GROUPVOLUMEMODE_FIXED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIGroupVolumeMode[SCIGroupVolumeMode.SCI_GROUPVOLUMEMODE_INDIVIDUAL_ONLY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                $SwitchMap$com$sonos$sclib$SCIGroupVolumeMode[SCIGroupVolumeMode.SCI_GROUPVOLUMEMODE_DEFAULT.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror6;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIGroupVolumeMode[sciGroupVolume.getGroupVolumeMode().ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 50
    //                   2 57;
           goto _L3 _L4 _L5
_L3:
        VolumeMode volumemode = VolumeMode.DEFAULT;
_L6:
        return volumemode;
_L4:
        volumemode = VolumeMode.FIXED;
        continue; /* Loop/switch isn't completed */
_L5:
        volumemode = VolumeMode.INDIVIDUAL_ONLY;
        if(true) goto _L6; else goto _L2
_L2:
        switch(_cls1..SwitchMap.com.sonos.sclib.SCIDeviceOutputMode[sciDeviceVolume.outputMode().ordinal()])
        {
        default:
            volumemode = VolumeMode.DEFAULT;
            break;

        case 1: // '\001'
            volumemode = VolumeMode.FIXED;
            break;

        case 2: // '\002'
            volumemode = VolumeMode.UNKNOWN;
            break;
        }
        if(true) goto _L6; else goto _L7
_L7:
    }

    public String getUnadjustableText()
    {
        return sciDeviceVolume.getPresentationText(com.sonos.sclib.SCIDeviceVolume.PresentationTextType.UNADJUSTABLE_TEXT);
    }

    public int getVolume()
    {
        return sciDeviceVolume.getVolume();
    }

    public boolean isGroupCoordinator()
    {
        return sclibConstants.SCIGROUPVOLUME_GROUPID.equals(getDeviceID());
    }

    public boolean isMuted()
    {
        return sciDeviceVolume.isMuted();
    }

    public boolean isVolumeAdjustable()
    {
        return sciDeviceVolume.isVolumeAdjustable();
    }

    public SCIDeviceOutputMode outputMode()
    {
        return sciDeviceVolume.outputMode();
    }

    public void setAbsoluteVolume(int i)
    {
        sciDeviceVolume.setAbsoluteVolume(i);
    }

    public void setMute(boolean flag)
    {
        sciDeviceVolume.setMute(flag);
    }

    public void setRelativeVolume(int i)
    {
        sciDeviceVolume.setRelativeVolume(i);
    }

    SCIDeviceVolume sciDeviceVolume;
}
