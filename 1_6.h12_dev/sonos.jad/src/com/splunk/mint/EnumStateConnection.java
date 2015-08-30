// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


final class EnumStateConnection extends Enum
{

    private EnumStateConnection(String s, int i)
    {
        super(s, i);
    }

    public static EnumStateConnection valueOf(String s)
    {
        return (EnumStateConnection)Enum.valueOf(com/splunk/mint/EnumStateConnection, s);
    }

    public static EnumStateConnection[] values()
    {
        return (EnumStateConnection[])$VALUES.clone();
    }

    private static final EnumStateConnection $VALUES[];
    public static final EnumStateConnection NA;
    public static final EnumStateConnection NONE;
    public static final EnumStateConnection WiFi;
    public static final EnumStateConnection net_2G;
    public static final EnumStateConnection net_3G;

    static 
    {
        WiFi = new EnumStateConnection("WiFi", 0);
        net_3G = new EnumStateConnection("net_3G", 1);
        net_2G = new EnumStateConnection("net_2G", 2);
        NONE = new EnumStateConnection("NONE", 3);
        NA = new EnumStateConnection("NA", 4);
        EnumStateConnection aenumstateconnection[] = new EnumStateConnection[5];
        aenumstateconnection[0] = WiFi;
        aenumstateconnection[1] = net_3G;
        aenumstateconnection[2] = net_2G;
        aenumstateconnection[3] = NONE;
        aenumstateconnection[4] = NA;
        $VALUES = aenumstateconnection;
    }
}
