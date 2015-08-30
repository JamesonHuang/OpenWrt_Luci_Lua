// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggerContextVO;
import java.util.Map;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public final class PropertyConverter extends ClassicConverter
{

    public PropertyConverter()
    {
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        if(key != null) goto _L2; else goto _L1
_L1:
        String s = "Property_HAS_NO_KEY";
_L4:
        return s;
_L2:
        s = (String)iloggingevent.getLoggerContextVO().getPropertyMap().get(key);
        if(s == null)
            s = System.getProperty(key);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    public void start()
    {
        String s = getFirstOption();
        if(s != null)
        {
            key = s;
            super.start();
        }
    }

    String key;
}
