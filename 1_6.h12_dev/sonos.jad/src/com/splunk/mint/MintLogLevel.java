// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


public final class MintLogLevel extends Enum
{

    private MintLogLevel(String s, int i)
    {
        super(s, i);
    }

    public static MintLogLevel valueOf(String s)
    {
        return (MintLogLevel)Enum.valueOf(com/splunk/mint/MintLogLevel, s);
    }

    public static MintLogLevel[] values()
    {
        return (MintLogLevel[])$VALUES.clone();
    }

    private static final MintLogLevel $VALUES[];
    public static final MintLogLevel Debug;
    public static final MintLogLevel Error;
    public static final MintLogLevel Info;
    public static final MintLogLevel Verbose;
    public static final MintLogLevel Warning;

    static 
    {
        Verbose = new MintLogLevel("Verbose", 0);
        Debug = new MintLogLevel("Debug", 1);
        Info = new MintLogLevel("Info", 2);
        Warning = new MintLogLevel("Warning", 3);
        Error = new MintLogLevel("Error", 4);
        MintLogLevel amintloglevel[] = new MintLogLevel[5];
        amintloglevel[0] = Verbose;
        amintloglevel[1] = Debug;
        amintloglevel[2] = Info;
        amintloglevel[3] = Warning;
        amintloglevel[4] = Error;
        $VALUES = amintloglevel;
    }
}
