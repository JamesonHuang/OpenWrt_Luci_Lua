// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


final class EnumStateStatus extends Enum
{

    private EnumStateStatus(String s, int i)
    {
        super(s, i);
    }

    public static EnumStateStatus valueOf(String s)
    {
        return (EnumStateStatus)Enum.valueOf(com/splunk/mint/EnumStateStatus, s);
    }

    public static EnumStateStatus[] values()
    {
        return (EnumStateStatus[])$VALUES.clone();
    }

    private static final EnumStateStatus $VALUES[];
    public static final EnumStateStatus NA;
    public static final EnumStateStatus OFF;
    public static final EnumStateStatus ON;

    static 
    {
        ON = new EnumStateStatus("ON", 0);
        OFF = new EnumStateStatus("OFF", 1);
        NA = new EnumStateStatus("NA", 2);
        EnumStateStatus aenumstatestatus[] = new EnumStateStatus[3];
        aenumstatestatus[0] = ON;
        aenumstatestatus[1] = OFF;
        aenumstatestatus[2] = NA;
        $VALUES = aenumstatestatus;
    }
}
