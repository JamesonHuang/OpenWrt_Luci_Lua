// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import java.io.Serializable;

public final class Level
    implements Serializable
{

    private Level(int i, String s)
    {
        levelInt = i;
        levelStr = s;
    }

    public static Level fromLocationAwareLoggerInteger(int i)
    {
        i;
        JVM INSTR lookupswitch 5: default 52
    //                   0: 79
    //                   10: 85
    //                   20: 92
    //                   30: 99
    //                   40: 106;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append(i).append(" not a valid level value").toString());
_L2:
        Level level = TRACE;
_L8:
        return level;
_L3:
        level = DEBUG;
        continue; /* Loop/switch isn't completed */
_L4:
        level = INFO;
        continue; /* Loop/switch isn't completed */
_L5:
        level = WARN;
        continue; /* Loop/switch isn't completed */
_L6:
        level = ERROR;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private Object readResolve()
    {
        return toLevel(levelInt);
    }

    public static Level toLevel(int i)
    {
        return toLevel(i, DEBUG);
    }

    public static Level toLevel(int i, Level level)
    {
        i;
        JVM INSTR lookupswitch 7: default 68
    //                   -2147483648: 70
    //                   5000: 77
    //                   10000: 84
    //                   20000: 91
    //                   30000: 98
    //                   40000: 105
    //                   2147483647: 112;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return level;
_L2:
        level = ALL;
        continue; /* Loop/switch isn't completed */
_L3:
        level = TRACE;
        continue; /* Loop/switch isn't completed */
_L4:
        level = DEBUG;
        continue; /* Loop/switch isn't completed */
_L5:
        level = INFO;
        continue; /* Loop/switch isn't completed */
_L6:
        level = WARN;
        continue; /* Loop/switch isn't completed */
_L7:
        level = ERROR;
        continue; /* Loop/switch isn't completed */
_L8:
        level = OFF;
        if(true) goto _L1; else goto _L9
_L9:
    }

    public static Level toLevel(String s)
    {
        return toLevel(s, DEBUG);
    }

    public static Level toLevel(String s, Level level)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        return level;
_L2:
        if(s.equalsIgnoreCase("ALL"))
            level = ALL;
        else
        if(s.equalsIgnoreCase("TRACE"))
            level = TRACE;
        else
        if(s.equalsIgnoreCase("DEBUG"))
            level = DEBUG;
        else
        if(s.equalsIgnoreCase("INFO"))
            level = INFO;
        else
        if(s.equalsIgnoreCase("WARN"))
            level = WARN;
        else
        if(s.equalsIgnoreCase("ERROR"))
            level = ERROR;
        else
        if(s.equalsIgnoreCase("OFF"))
            level = OFF;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static int toLocationAwareLoggerInteger(Level level)
    {
        if(level == null)
            throw new IllegalArgumentException("null level parameter is not admitted");
        level.toInt();
        JVM INSTR lookupswitch 5: default 68
    //                   5000: 95
    //                   10000: 99
    //                   20000: 105
    //                   30000: 111
    //                   40000: 117;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append(level).append(" not a valid level value").toString());
_L2:
        int i = 0;
_L8:
        return i;
_L3:
        i = 10;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 20;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 30;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 40;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static Level valueOf(String s)
    {
        return toLevel(s, DEBUG);
    }

    public boolean isGreaterOrEqual(Level level)
    {
        boolean flag;
        if(levelInt >= level.levelInt)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int toInt()
    {
        return levelInt;
    }

    public Integer toInteger()
    {
        levelInt;
        JVM INSTR lookupswitch 7: default 72
    //                   -2147483648: 119
    //                   5000: 125
    //                   10000: 132
    //                   20000: 139
    //                   30000: 146
    //                   40000: 153
    //                   2147483647: 160;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Level ").append(levelStr).append(", ").append(levelInt).append(" is unknown.").toString());
_L2:
        Integer integer = ALL_INTEGER;
_L10:
        return integer;
_L3:
        integer = TRACE_INTEGER;
        continue; /* Loop/switch isn't completed */
_L4:
        integer = DEBUG_INTEGER;
        continue; /* Loop/switch isn't completed */
_L5:
        integer = INFO_INTEGER;
        continue; /* Loop/switch isn't completed */
_L6:
        integer = WARN_INTEGER;
        continue; /* Loop/switch isn't completed */
_L7:
        integer = ERROR_INTEGER;
        continue; /* Loop/switch isn't completed */
_L8:
        integer = OFF_INTEGER;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public String toString()
    {
        return levelStr;
    }

    public static final Level ALL = new Level(0x80000000, "ALL");
    public static final int ALL_INT = 0x80000000;
    public static final Integer ALL_INTEGER = Integer.valueOf(0x80000000);
    public static final Level DEBUG = new Level(10000, "DEBUG");
    public static final int DEBUG_INT = 10000;
    public static final Integer DEBUG_INTEGER = Integer.valueOf(10000);
    public static final Level ERROR = new Level(40000, "ERROR");
    public static final int ERROR_INT = 40000;
    public static final Integer ERROR_INTEGER = Integer.valueOf(40000);
    public static final Level INFO = new Level(20000, "INFO");
    public static final int INFO_INT = 20000;
    public static final Integer INFO_INTEGER = Integer.valueOf(20000);
    public static final Level OFF = new Level(0x7fffffff, "OFF");
    public static final int OFF_INT = 0x7fffffff;
    public static final Integer OFF_INTEGER = Integer.valueOf(0x7fffffff);
    public static final Level TRACE = new Level(5000, "TRACE");
    public static final int TRACE_INT = 5000;
    public static final Integer TRACE_INTEGER = Integer.valueOf(5000);
    public static final Level WARN = new Level(30000, "WARN");
    public static final int WARN_INT = 30000;
    public static final Integer WARN_INTEGER = Integer.valueOf(30000);
    private static final long serialVersionUID = 0xf4b3c2f0fcd34c67L;
    public final int levelInt;
    public final String levelStr;

}
