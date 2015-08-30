// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.jul;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class JULHelper
{

    public JULHelper()
    {
    }

    public static java.util.logging.Level asJULLevel(Level level)
    {
        if(level == null)
            throw new IllegalArgumentException("Unexpected level [null]");
        level.levelInt;
        JVM INSTR lookupswitch 7: default 84
    //                   -2147483648: 116
    //                   5000: 122
    //                   10000: 129
    //                   20000: 136
    //                   30000: 143
    //                   40000: 150
    //                   2147483647: 157;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unexpected level [").append(level).append("]").toString());
_L2:
        java.util.logging.Level level1 = java.util.logging.Level.ALL;
_L10:
        return level1;
_L3:
        level1 = java.util.logging.Level.FINEST;
        continue; /* Loop/switch isn't completed */
_L4:
        level1 = java.util.logging.Level.FINE;
        continue; /* Loop/switch isn't completed */
_L5:
        level1 = java.util.logging.Level.INFO;
        continue; /* Loop/switch isn't completed */
_L6:
        level1 = java.util.logging.Level.WARNING;
        continue; /* Loop/switch isn't completed */
_L7:
        level1 = java.util.logging.Level.SEVERE;
        continue; /* Loop/switch isn't completed */
_L8:
        level1 = java.util.logging.Level.OFF;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static java.util.logging.Logger asJULLogger(Logger logger)
    {
        return asJULLogger(logger.getName());
    }

    public static java.util.logging.Logger asJULLogger(String s)
    {
        return java.util.logging.Logger.getLogger(asJULLoggerName(s));
    }

    public static String asJULLoggerName(String s)
    {
        if("ROOT".equals(s))
            s = "";
        return s;
    }

    public static final boolean isRegularNonRootLogger(java.util.logging.Logger logger)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(logger != null && !logger.getName().equals(""))
            flag = true;
        return flag;
    }

    public static final boolean isRoot(java.util.logging.Logger logger)
    {
        boolean flag;
        if(logger == null)
            flag = false;
        else
            flag = logger.getName().equals("");
        return flag;
    }
}
