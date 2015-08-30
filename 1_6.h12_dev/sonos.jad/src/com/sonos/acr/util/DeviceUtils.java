// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;


public class DeviceUtils
{

    public DeviceUtils()
    {
    }

    public static String getModelString(com.sonos.sclib.SCIDevice.DeviceModel devicemodel)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel = new int[com.sonos.sclib.SCIDevice.DeviceModel.values().length];
                NoSuchFieldError nosuchfielderror7;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP80.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP100.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZP120.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZONEBRIDGE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS5.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_ZPS3.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_DOCK.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                $SwitchMap$com$sonos$sclib$SCIDevice$DeviceModel[com.sonos.sclib.SCIDevice.DeviceModel.DM_UNKNOWN.ordinal()] = 8;
_L2:
                return;
                nosuchfielderror7;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIDevice.DeviceModel[devicemodel.ordinal()];
        JVM INSTR tableswitch 1 7: default 52
    //                   1 57
    //                   2 63
    //                   3 69
    //                   4 75
    //                   5 81
    //                   6 87
    //                   7 93;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        String s = "Unknown";
_L10:
        return s;
_L2:
        s = "ZP80";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "ZP100";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "ZP120";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "ZB100";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "ZPS5";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "ZPS3";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "WD100";
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final String DOCK_STR = "WD100";
    private static final String UNKNOWN_STR = "Unknown";
    private static final String ZONEBRIDGE_STR = "ZB100";
    private static final String ZP100_STR = "ZP100";
    private static final String ZP120_STR = "ZP120";
    private static final String ZP80_STR = "ZP80";
    private static final String ZPS3_STR = "ZPS3";
    private static final String ZPS5_STR = "ZPS5";
}
