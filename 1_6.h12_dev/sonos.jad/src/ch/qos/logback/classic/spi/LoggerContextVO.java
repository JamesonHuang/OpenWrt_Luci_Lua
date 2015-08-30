// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.LoggerContext;
import java.io.Serializable;
import java.util.Map;

public class LoggerContextVO
    implements Serializable
{

    public LoggerContextVO(LoggerContext loggercontext)
    {
        name = loggercontext.getName();
        propertyMap = loggercontext.getCopyOfPropertyMap();
        birthTime = loggercontext.getBirthTime();
    }

    public LoggerContextVO(String s, Map map, long l)
    {
        name = s;
        propertyMap = map;
        birthTime = l;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(!(obj instanceof LoggerContextVO))
        {
            flag = false;
        } else
        {
            LoggerContextVO loggercontextvo = (LoggerContextVO)obj;
            if(birthTime != loggercontextvo.birthTime)
                flag = false;
            else
            if(name == null ? loggercontextvo.name != null : !name.equals(loggercontextvo.name))
                flag = false;
            else
            if(propertyMap == null ? loggercontextvo.propertyMap != null : !propertyMap.equals(loggercontextvo.propertyMap))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public long getBirthTime()
    {
        return birthTime;
    }

    public String getName()
    {
        return name;
    }

    public Map getPropertyMap()
    {
        return propertyMap;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(name != null)
            j = name.hashCode();
        else
            j = 0;
        k = j * 31;
        if(propertyMap != null)
            i = propertyMap.hashCode();
        return 31 * (k + i) + (int)(birthTime ^ birthTime >>> 32);
    }

    public String toString()
    {
        return (new StringBuilder()).append("LoggerContextVO{name='").append(name).append('\'').append(", propertyMap=").append(propertyMap).append(", birthTime=").append(birthTime).append('}').toString();
    }

    private static final long serialVersionUID = 0x4c296032dbfdf2c3L;
    final long birthTime;
    final String name;
    final Map propertyMap;
}
