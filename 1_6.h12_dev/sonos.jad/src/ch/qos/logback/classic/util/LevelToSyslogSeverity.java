// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LevelToSyslogSeverity
{

    public LevelToSyslogSeverity()
    {
    }

    public static int convert(ILoggingEvent iloggingevent)
    {
        Level level = iloggingevent.getLevel();
        level.levelInt;
        JVM INSTR lookupswitch 5: default 60
    //                   5000: 107
    //                   10000: 107
    //                   20000: 101
    //                   30000: 96
    //                   40000: 92;
           goto _L1 _L2 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Level ").append(level).append(" is not a valid level for a printing method").toString());
_L5:
        byte byte0 = 3;
_L7:
        return byte0;
_L4:
        byte0 = 4;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 6;
        continue; /* Loop/switch isn't completed */
_L2:
        byte0 = 7;
        if(true) goto _L7; else goto _L6
_L6:
    }
}
