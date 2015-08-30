// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

// Referenced classes of package org.slf4j.helpers:
//            SubstituteLogger

public class SubstituteLoggerFactory
    implements ILoggerFactory
{

    public SubstituteLoggerFactory()
    {
    }

    public void clear()
    {
        loggers.clear();
    }

    public Logger getLogger(String s)
    {
        SubstituteLogger substitutelogger = (SubstituteLogger)loggers.get(s);
        if(substitutelogger == null)
        {
            substitutelogger = new SubstituteLogger(s);
            SubstituteLogger substitutelogger1 = (SubstituteLogger)loggers.putIfAbsent(s, substitutelogger);
            if(substitutelogger1 != null)
                substitutelogger = substitutelogger1;
        }
        return substitutelogger;
    }

    public List getLoggerNames()
    {
        return new ArrayList(loggers.keySet());
    }

    public List getLoggers()
    {
        return new ArrayList(loggers.values());
    }

    final ConcurrentMap loggers = new ConcurrentHashMap();
}
