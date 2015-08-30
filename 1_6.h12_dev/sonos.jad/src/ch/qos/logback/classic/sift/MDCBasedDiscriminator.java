// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.AbstractDiscriminator;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;

public class MDCBasedDiscriminator extends AbstractDiscriminator
{

    public MDCBasedDiscriminator()
    {
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public String getDiscriminatingValue(ILoggingEvent iloggingevent)
    {
        Map map = iloggingevent.getMDCPropertyMap();
        if(map != null) goto _L2; else goto _L1
_L1:
        String s = defaultValue;
_L4:
        return s;
_L2:
        s = (String)map.get(key);
        if(s == null)
            s = defaultValue;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public volatile String getDiscriminatingValue(Object obj)
    {
        return getDiscriminatingValue((ILoggingEvent)obj);
    }

    public String getKey()
    {
        return key;
    }

    public void setDefaultValue(String s)
    {
        defaultValue = s;
    }

    public void setKey(String s)
    {
        key = s;
    }

    public void start()
    {
        int i = 0;
        if(OptionHelper.isEmpty(key))
        {
            addError("The \"Key\" property must be set");
            i = 1;
        }
        if(OptionHelper.isEmpty(defaultValue))
        {
            i++;
            addError("The \"DefaultValue\" property must be set");
        }
        if(i == 0)
            started = true;
    }

    private String defaultValue;
    private String key;
}
