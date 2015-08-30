// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern.color;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class HighlightingCompositeConverter extends ForegroundCompositeConverterBase
{

    public HighlightingCompositeConverter()
    {
    }

    protected String getForegroundColorCode(ILoggingEvent iloggingevent)
    {
        iloggingevent.getLevel().toInt();
        JVM INSTR lookupswitch 3: default 44
    //                   20000: 61
    //                   30000: 55
    //                   40000: 49;
           goto _L1 _L2 _L3 _L4
_L1:
        String s = "39";
_L6:
        return s;
_L4:
        s = "1;31";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "31";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "34";
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected volatile String getForegroundColorCode(Object obj)
    {
        return getForegroundColorCode((ILoggingEvent)obj);
    }
}
