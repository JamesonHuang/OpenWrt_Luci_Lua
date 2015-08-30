// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


final class EnumExceptionType extends Enum
{

    private EnumExceptionType(String s, int i)
    {
        super(s, i);
    }

    public static EnumExceptionType valueOf(String s)
    {
        return (EnumExceptionType)Enum.valueOf(com/splunk/mint/EnumExceptionType, s);
    }

    public static EnumExceptionType[] values()
    {
        return (EnumExceptionType[])$VALUES.clone();
    }

    private static final EnumExceptionType $VALUES[];
    public static final EnumExceptionType HANDLED;
    public static final EnumExceptionType UNHANDLED;

    static 
    {
        HANDLED = new EnumExceptionType("HANDLED", 0);
        UNHANDLED = new EnumExceptionType("UNHANDLED", 1);
        EnumExceptionType aenumexceptiontype[] = new EnumExceptionType[2];
        aenumexceptiontype[0] = HANDLED;
        aenumexceptiontype[1] = UNHANDLED;
        $VALUES = aenumexceptiontype;
    }
}
