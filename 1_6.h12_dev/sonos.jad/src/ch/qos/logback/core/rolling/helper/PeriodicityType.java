// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;


public final class PeriodicityType extends Enum
{

    private PeriodicityType(String s, int i)
    {
        super(s, i);
    }

    public static PeriodicityType valueOf(String s)
    {
        return (PeriodicityType)Enum.valueOf(ch/qos/logback/core/rolling/helper/PeriodicityType, s);
    }

    public static PeriodicityType[] values()
    {
        return (PeriodicityType[])$VALUES.clone();
    }

    private static final PeriodicityType $VALUES[];
    public static final PeriodicityType ERRONEOUS;
    public static final PeriodicityType HALF_DAY;
    public static final PeriodicityType TOP_OF_DAY;
    public static final PeriodicityType TOP_OF_HOUR;
    public static final PeriodicityType TOP_OF_MILLISECOND;
    public static final PeriodicityType TOP_OF_MINUTE;
    public static final PeriodicityType TOP_OF_MONTH;
    public static final PeriodicityType TOP_OF_SECOND;
    public static final PeriodicityType TOP_OF_WEEK;
    static PeriodicityType VALID_ORDERED_LIST[];

    static 
    {
        ERRONEOUS = new PeriodicityType("ERRONEOUS", 0);
        TOP_OF_MILLISECOND = new PeriodicityType("TOP_OF_MILLISECOND", 1);
        TOP_OF_SECOND = new PeriodicityType("TOP_OF_SECOND", 2);
        TOP_OF_MINUTE = new PeriodicityType("TOP_OF_MINUTE", 3);
        TOP_OF_HOUR = new PeriodicityType("TOP_OF_HOUR", 4);
        HALF_DAY = new PeriodicityType("HALF_DAY", 5);
        TOP_OF_DAY = new PeriodicityType("TOP_OF_DAY", 6);
        TOP_OF_WEEK = new PeriodicityType("TOP_OF_WEEK", 7);
        TOP_OF_MONTH = new PeriodicityType("TOP_OF_MONTH", 8);
        PeriodicityType aperiodicitytype[] = new PeriodicityType[9];
        aperiodicitytype[0] = ERRONEOUS;
        aperiodicitytype[1] = TOP_OF_MILLISECOND;
        aperiodicitytype[2] = TOP_OF_SECOND;
        aperiodicitytype[3] = TOP_OF_MINUTE;
        aperiodicitytype[4] = TOP_OF_HOUR;
        aperiodicitytype[5] = HALF_DAY;
        aperiodicitytype[6] = TOP_OF_DAY;
        aperiodicitytype[7] = TOP_OF_WEEK;
        aperiodicitytype[8] = TOP_OF_MONTH;
        $VALUES = aperiodicitytype;
        PeriodicityType aperiodicitytype1[] = new PeriodicityType[7];
        aperiodicitytype1[0] = TOP_OF_MILLISECOND;
        aperiodicitytype1[1] = TOP_OF_SECOND;
        aperiodicitytype1[2] = TOP_OF_MINUTE;
        aperiodicitytype1[3] = TOP_OF_HOUR;
        aperiodicitytype1[4] = TOP_OF_DAY;
        aperiodicitytype1[5] = TOP_OF_WEEK;
        aperiodicitytype1[6] = TOP_OF_MONTH;
        VALID_ORDERED_LIST = aperiodicitytype1;
    }
}
