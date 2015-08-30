// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggerContextVO;
import ch.qos.logback.core.sift.AbstractDiscriminator;

public class ContextBasedDiscriminator extends AbstractDiscriminator
{

    public ContextBasedDiscriminator()
    {
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public String getDiscriminatingValue(ILoggingEvent iloggingevent)
    {
        String s = iloggingevent.getLoggerContextVO().getName();
        if(s == null)
            s = defaultValue;
        return s;
    }

    public volatile String getDiscriminatingValue(Object obj)
    {
        return getDiscriminatingValue((ILoggingEvent)obj);
    }

    public String getKey()
    {
        return "contextName";
    }

    public void setDefaultValue(String s)
    {
        defaultValue = s;
    }

    public void setKey(String s)
    {
        throw new UnsupportedOperationException("Key cannot be set. Using fixed key contextName");
    }

    private static final String KEY = "contextName";
    private String defaultValue;
}
