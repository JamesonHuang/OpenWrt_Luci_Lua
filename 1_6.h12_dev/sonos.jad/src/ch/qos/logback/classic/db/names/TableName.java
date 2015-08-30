// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db.names;


public final class TableName extends Enum
{

    private TableName(String s, int i)
    {
        super(s, i);
    }

    public static TableName valueOf(String s)
    {
        return (TableName)Enum.valueOf(ch/qos/logback/classic/db/names/TableName, s);
    }

    public static TableName[] values()
    {
        return (TableName[])$VALUES.clone();
    }

    private static final TableName $VALUES[];
    public static final TableName LOGGING_EVENT;
    public static final TableName LOGGING_EVENT_EXCEPTION;
    public static final TableName LOGGING_EVENT_PROPERTY;

    static 
    {
        LOGGING_EVENT = new TableName("LOGGING_EVENT", 0);
        LOGGING_EVENT_PROPERTY = new TableName("LOGGING_EVENT_PROPERTY", 1);
        LOGGING_EVENT_EXCEPTION = new TableName("LOGGING_EVENT_EXCEPTION", 2);
        TableName atablename[] = new TableName[3];
        atablename[0] = LOGGING_EVENT;
        atablename[1] = LOGGING_EVENT_PROPERTY;
        atablename[2] = LOGGING_EVENT_EXCEPTION;
        $VALUES = atablename;
    }
}
