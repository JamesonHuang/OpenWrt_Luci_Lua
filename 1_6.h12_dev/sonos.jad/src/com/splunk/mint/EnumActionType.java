// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


final class EnumActionType extends Enum
{

    private EnumActionType(String s, int i)
    {
        super(s, i);
    }

    public static EnumActionType valueOf(String s)
    {
        return (EnumActionType)Enum.valueOf(com/splunk/mint/EnumActionType, s);
    }

    public static EnumActionType[] values()
    {
        return (EnumActionType[])$VALUES.clone();
    }

    private static final EnumActionType $VALUES[];
    public static final EnumActionType error;
    public static final EnumActionType event;
    public static final EnumActionType gnip;
    public static final EnumActionType log;
    public static final EnumActionType network;
    public static final EnumActionType performance;
    public static final EnumActionType ping;
    public static final EnumActionType trstart;
    public static final EnumActionType trstop;
    public static final EnumActionType view;

    static 
    {
        error = new EnumActionType("error", 0);
        event = new EnumActionType("event", 1);
        ping = new EnumActionType("ping", 2);
        gnip = new EnumActionType("gnip", 3);
        trstart = new EnumActionType("trstart", 4);
        trstop = new EnumActionType("trstop", 5);
        network = new EnumActionType("network", 6);
        performance = new EnumActionType("performance", 7);
        view = new EnumActionType("view", 8);
        log = new EnumActionType("log", 9);
        EnumActionType aenumactiontype[] = new EnumActionType[10];
        aenumactiontype[0] = error;
        aenumactiontype[1] = event;
        aenumactiontype[2] = ping;
        aenumactiontype[3] = gnip;
        aenumactiontype[4] = trstart;
        aenumactiontype[5] = trstop;
        aenumactiontype[6] = network;
        aenumactiontype[7] = performance;
        aenumactiontype[8] = view;
        aenumactiontype[9] = log;
        $VALUES = aenumactiontype;
    }
}
