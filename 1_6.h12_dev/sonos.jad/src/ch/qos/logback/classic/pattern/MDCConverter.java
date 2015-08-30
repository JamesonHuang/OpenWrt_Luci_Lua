// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.OptionHelper;
import java.util.*;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class MDCConverter extends ClassicConverter
{

    public MDCConverter()
    {
        defaultValue = "";
    }

    private String outputMDCForAllKeys(Map map)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = map.entrySet().iterator();
        boolean flag = true;
        while(iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            boolean flag1;
            if(flag)
            {
                flag1 = false;
            } else
            {
                stringbuilder.append(", ");
                flag1 = flag;
            }
            stringbuilder.append((String)entry.getKey()).append('=').append((String)entry.getValue());
            flag = flag1;
        }
        return stringbuilder.toString();
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        Map map = iloggingevent.getMDCPropertyMap();
        if(map != null) goto _L2; else goto _L1
_L1:
        String s = defaultValue;
_L4:
        return s;
_L2:
        if(key == null)
        {
            s = outputMDCForAllKeys(map);
        } else
        {
            s = (String)iloggingevent.getMDCPropertyMap().get(key);
            if(s == null)
                s = defaultValue;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    public void start()
    {
        String as[] = OptionHelper.extractDefaultReplacement(getFirstOption());
        key = as[0];
        if(as[1] != null)
            defaultValue = as[1];
        super.start();
    }

    public void stop()
    {
        key = null;
        super.stop();
    }

    private String defaultValue;
    private String key;
}
