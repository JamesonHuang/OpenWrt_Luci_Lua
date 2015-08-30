// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.MDC;
import org.slf4j.Marker;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            TurboFilter, MDCValueLevelPair

public class DynamicThresholdFilter extends TurboFilter
{

    public DynamicThresholdFilter()
    {
        valueLevelMap = new HashMap();
        defaultThreshold = Level.ERROR;
        onHigherOrEqual = FilterReply.NEUTRAL;
        onLower = FilterReply.DENY;
    }

    public void addMDCValueLevelPair(MDCValueLevelPair mdcvaluelevelpair)
    {
        if(valueLevelMap.containsKey(mdcvaluelevelpair.getValue()))
            addError((new StringBuilder()).append(mdcvaluelevelpair.getValue()).append(" has been already set").toString());
        else
            valueLevelMap.put(mdcvaluelevelpair.getValue(), mdcvaluelevelpair.getLevel());
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        String s1 = MDC.get(key);
        FilterReply filterreply;
        if(!isStarted())
        {
            filterreply = FilterReply.NEUTRAL;
        } else
        {
            Level level1 = null;
            if(s1 != null)
                level1 = (Level)valueLevelMap.get(s1);
            if(level1 == null)
                level1 = defaultThreshold;
            if(level.isGreaterOrEqual(level1))
                filterreply = onHigherOrEqual;
            else
                filterreply = onLower;
        }
        return filterreply;
    }

    public Level getDefaultThreshold()
    {
        return defaultThreshold;
    }

    public String getKey()
    {
        return key;
    }

    public FilterReply getOnHigherOrEqual()
    {
        return onHigherOrEqual;
    }

    public FilterReply getOnLower()
    {
        return onLower;
    }

    public void setDefaultThreshold(Level level)
    {
        defaultThreshold = level;
    }

    public void setKey(String s)
    {
        key = s;
    }

    public void setOnHigherOrEqual(FilterReply filterreply)
    {
        onHigherOrEqual = filterreply;
    }

    public void setOnLower(FilterReply filterreply)
    {
        onLower = filterreply;
    }

    public void start()
    {
        if(key == null)
            addError("No key name was specified");
        super.start();
    }

    private Level defaultThreshold;
    private String key;
    private FilterReply onHigherOrEqual;
    private FilterReply onLower;
    private Map valueLevelMap;
}
