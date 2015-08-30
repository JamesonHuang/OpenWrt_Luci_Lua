// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db.names;


public final class ColumnName extends Enum
{

    private ColumnName(String s, int i)
    {
        super(s, i);
    }

    public static ColumnName valueOf(String s)
    {
        return (ColumnName)Enum.valueOf(ch/qos/logback/classic/db/names/ColumnName, s);
    }

    public static ColumnName[] values()
    {
        return (ColumnName[])$VALUES.clone();
    }

    private static final ColumnName $VALUES[];
    public static final ColumnName ARG0;
    public static final ColumnName ARG1;
    public static final ColumnName ARG2;
    public static final ColumnName ARG3;
    public static final ColumnName CALLER_CLASS;
    public static final ColumnName CALLER_FILENAME;
    public static final ColumnName CALLER_LINE;
    public static final ColumnName CALLER_METHOD;
    public static final ColumnName EVENT_ID;
    public static final ColumnName FORMATTED_MESSAGE;
    public static final ColumnName I;
    public static final ColumnName LEVEL_STRING;
    public static final ColumnName LOGGER_NAME;
    public static final ColumnName MAPPED_KEY;
    public static final ColumnName MAPPED_VALUE;
    public static final ColumnName REFERENCE_FLAG;
    public static final ColumnName THREAD_NAME;
    public static final ColumnName TIMESTMP;
    public static final ColumnName TRACE_LINE;

    static 
    {
        EVENT_ID = new ColumnName("EVENT_ID", 0);
        TIMESTMP = new ColumnName("TIMESTMP", 1);
        FORMATTED_MESSAGE = new ColumnName("FORMATTED_MESSAGE", 2);
        LOGGER_NAME = new ColumnName("LOGGER_NAME", 3);
        LEVEL_STRING = new ColumnName("LEVEL_STRING", 4);
        THREAD_NAME = new ColumnName("THREAD_NAME", 5);
        REFERENCE_FLAG = new ColumnName("REFERENCE_FLAG", 6);
        ARG0 = new ColumnName("ARG0", 7);
        ARG1 = new ColumnName("ARG1", 8);
        ARG2 = new ColumnName("ARG2", 9);
        ARG3 = new ColumnName("ARG3", 10);
        CALLER_FILENAME = new ColumnName("CALLER_FILENAME", 11);
        CALLER_CLASS = new ColumnName("CALLER_CLASS", 12);
        CALLER_METHOD = new ColumnName("CALLER_METHOD", 13);
        CALLER_LINE = new ColumnName("CALLER_LINE", 14);
        MAPPED_KEY = new ColumnName("MAPPED_KEY", 15);
        MAPPED_VALUE = new ColumnName("MAPPED_VALUE", 16);
        I = new ColumnName("I", 17);
        TRACE_LINE = new ColumnName("TRACE_LINE", 18);
        ColumnName acolumnname[] = new ColumnName[19];
        acolumnname[0] = EVENT_ID;
        acolumnname[1] = TIMESTMP;
        acolumnname[2] = FORMATTED_MESSAGE;
        acolumnname[3] = LOGGER_NAME;
        acolumnname[4] = LEVEL_STRING;
        acolumnname[5] = THREAD_NAME;
        acolumnname[6] = REFERENCE_FLAG;
        acolumnname[7] = ARG0;
        acolumnname[8] = ARG1;
        acolumnname[9] = ARG2;
        acolumnname[10] = ARG3;
        acolumnname[11] = CALLER_FILENAME;
        acolumnname[12] = CALLER_CLASS;
        acolumnname[13] = CALLER_METHOD;
        acolumnname[14] = CALLER_LINE;
        acolumnname[15] = MAPPED_KEY;
        acolumnname[16] = MAPPED_VALUE;
        acolumnname[17] = I;
        acolumnname[18] = TRACE_LINE;
        $VALUES = acolumnname;
    }
}
