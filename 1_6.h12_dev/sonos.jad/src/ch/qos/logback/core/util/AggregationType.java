// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


public final class AggregationType extends Enum
{

    private AggregationType(String s, int i)
    {
        super(s, i);
    }

    public static AggregationType valueOf(String s)
    {
        return (AggregationType)Enum.valueOf(ch/qos/logback/core/util/AggregationType, s);
    }

    public static AggregationType[] values()
    {
        return (AggregationType[])$VALUES.clone();
    }

    private static final AggregationType $VALUES[];
    public static final AggregationType AS_BASIC_PROPERTY;
    public static final AggregationType AS_BASIC_PROPERTY_COLLECTION;
    public static final AggregationType AS_COMPLEX_PROPERTY;
    public static final AggregationType AS_COMPLEX_PROPERTY_COLLECTION;
    public static final AggregationType NOT_FOUND;

    static 
    {
        NOT_FOUND = new AggregationType("NOT_FOUND", 0);
        AS_BASIC_PROPERTY = new AggregationType("AS_BASIC_PROPERTY", 1);
        AS_COMPLEX_PROPERTY = new AggregationType("AS_COMPLEX_PROPERTY", 2);
        AS_BASIC_PROPERTY_COLLECTION = new AggregationType("AS_BASIC_PROPERTY_COLLECTION", 3);
        AS_COMPLEX_PROPERTY_COLLECTION = new AggregationType("AS_COMPLEX_PROPERTY_COLLECTION", 4);
        AggregationType aaggregationtype[] = new AggregationType[5];
        aaggregationtype[0] = NOT_FOUND;
        aaggregationtype[1] = AS_BASIC_PROPERTY;
        aaggregationtype[2] = AS_COMPLEX_PROPERTY;
        aaggregationtype[3] = AS_BASIC_PROPERTY_COLLECTION;
        aaggregationtype[4] = AS_COMPLEX_PROPERTY_COLLECTION;
        $VALUES = aaggregationtype;
    }
}
