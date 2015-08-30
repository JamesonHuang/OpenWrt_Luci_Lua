// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.jul;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

// Referenced classes of package ch.qos.logback.classic.jul:
//            JULHelper

public class LevelChangePropagator extends ContextAwareBase
    implements LoggerContextListener, LifeCycle
{

    public LevelChangePropagator()
    {
        julLoggerSet = new HashSet();
        isStarted = false;
        resetJUL = false;
    }

    private void propagate(ch.qos.logback.classic.Logger logger, Level level)
    {
        addInfo((new StringBuilder()).append("Propagating ").append(level).append(" level on ").append(logger).append(" onto the JUL framework").toString());
        Logger logger1 = JULHelper.asJULLogger(logger);
        julLoggerSet.add(logger1);
        logger1.setLevel(JULHelper.asJULLevel(level));
    }

    private void propagateExistingLoggerLevels()
    {
        Iterator iterator = ((LoggerContext)context).getLoggerList().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)iterator.next();
            if(logger.getLevel() != null)
                propagate(logger, logger.getLevel());
        } while(true);
    }

    public boolean isResetResistant()
    {
        return false;
    }

    public boolean isStarted()
    {
        return isStarted;
    }

    public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level)
    {
        propagate(logger, level);
    }

    public void onReset(LoggerContext loggercontext)
    {
    }

    public void onStart(LoggerContext loggercontext)
    {
    }

    public void onStop(LoggerContext loggercontext)
    {
    }

    public void resetJULLevels()
    {
        LogManager logmanager = LogManager.getLogManager();
        Enumeration enumeration = logmanager.getLoggerNames();
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s = (String)enumeration.nextElement();
            Logger logger = logmanager.getLogger(s);
            if(JULHelper.isRegularNonRootLogger(logger) && logger.getLevel() != null)
            {
                addInfo((new StringBuilder()).append("Setting level of jul logger [").append(s).append("] to null").toString());
                logger.setLevel(null);
            }
        } while(true);
    }

    public void setResetJUL(boolean flag)
    {
        resetJUL = flag;
    }

    public void start()
    {
        if(resetJUL)
            resetJULLevels();
        propagateExistingLoggerLevels();
        isStarted = true;
    }

    public void stop()
    {
        isStarted = false;
    }

    boolean isStarted;
    private Set julLoggerSet;
    boolean resetJUL;
}
