// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Logger;
import java.io.Serializable;
import java.util.Comparator;

public class LoggerComparator
    implements Comparator, Serializable
{

    public LoggerComparator()
    {
    }

    public int compare(Logger logger, Logger logger1)
    {
        int i;
        if(logger.getName().equals(logger1.getName()))
            i = 0;
        else
        if(logger.getName().equals("ROOT"))
            i = -1;
        else
        if(logger1.getName().equals("ROOT"))
            i = 1;
        else
            i = logger.getName().compareTo(logger1.getName());
        return i;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((Logger)obj, (Logger)obj1);
    }

    private static final long serialVersionUID = 1L;
}
