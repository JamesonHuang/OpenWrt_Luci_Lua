// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.util.LoggerNameUtil;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.spi.AppenderAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

// Referenced classes of package ch.qos.logback.classic:
//            LoggerContext, Level

public final class Logger
    implements org.slf4j.Logger, LocationAwareLogger, AppenderAttachable, Serializable
{

    Logger(String s, Logger logger, LoggerContext loggercontext)
    {
        additive = true;
        name = s;
        parent = logger;
        loggerContext = loggercontext;
    }

    private int appendLoopOnAppenders(ILoggingEvent iloggingevent)
    {
        int i;
        if(aai != null)
            i = aai.appendLoopOnAppenders(iloggingevent);
        else
            i = 0;
        return i;
    }

    private void buildLoggingEventAndAppend(String s, Marker marker, Level level1, String s1, Object aobj[], Throwable throwable)
    {
        LoggingEvent loggingevent = new LoggingEvent(s, this, level1, s1, throwable, aobj);
        loggingevent.setMarker(marker);
        callAppenders(loggingevent);
    }

    private FilterReply callTurboFilters(Marker marker, Level level1)
    {
        return loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level1, null, null, null);
    }

    private void filterAndLog_0_Or3Plus(String s, Marker marker, Level level1, String s1, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply = loggerContext.getTurboFilterChainDecision_0_3OrMore(marker, this, level1, s1, aobj, throwable);
        if(filterreply != FilterReply.NEUTRAL ? filterreply != FilterReply.DENY : effectiveLevelInt <= level1.levelInt)
            buildLoggingEventAndAppend(s, marker, level1, s1, aobj, throwable);
    }

    private void filterAndLog_1(String s, Marker marker, Level level1, String s1, Object obj, Throwable throwable)
    {
        FilterReply filterreply = loggerContext.getTurboFilterChainDecision_1(marker, this, level1, s1, obj, throwable);
        if(filterreply != FilterReply.NEUTRAL ? filterreply != FilterReply.DENY : effectiveLevelInt <= level1.levelInt)
        {
            Object aobj[] = new Object[1];
            aobj[0] = obj;
            buildLoggingEventAndAppend(s, marker, level1, s1, aobj, throwable);
        }
    }

    private void filterAndLog_2(String s, Marker marker, Level level1, String s1, Object obj, Object obj1, Throwable throwable)
    {
        FilterReply filterreply = loggerContext.getTurboFilterChainDecision_2(marker, this, level1, s1, obj, obj1, throwable);
        if(filterreply != FilterReply.NEUTRAL ? filterreply != FilterReply.DENY : effectiveLevelInt <= level1.levelInt)
        {
            Object aobj[] = new Object[2];
            aobj[0] = obj;
            aobj[1] = obj1;
            buildLoggingEventAndAppend(s, marker, level1, s1, aobj, throwable);
        }
    }

    /**
     * @deprecated Method handleParentLevelChange is deprecated
     */

    private void handleParentLevelChange(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j;
        int k;
        if(level != null)
            break MISSING_BLOCK_LABEL_64;
        effectiveLevelInt = i;
        if(childrenList == null)
            break MISSING_BLOCK_LABEL_64;
        j = childrenList.size();
        k = 0;
_L1:
        if(k >= j)
            break MISSING_BLOCK_LABEL_64;
        ((Logger)childrenList.get(k)).handleParentLevelChange(i);
        k++;
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isRootLogger()
    {
        boolean flag;
        if(parent == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void localLevelReset()
    {
        effectiveLevelInt = 10000;
        if(isRootLogger())
            level = Level.DEBUG;
        else
            level = null;
    }

    /**
     * @deprecated Method addAppender is deprecated
     */

    public void addAppender(Appender appender)
    {
        this;
        JVM INSTR monitorenter ;
        if(aai == null)
            aai = new AppenderAttachableImpl();
        aai.addAppender(appender);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void callAppenders(ILoggingEvent iloggingevent)
    {
        int i = 0;
        Logger logger = this;
        do
        {
label0:
            {
                if(logger != null)
                {
                    i += logger.appendLoopOnAppenders(iloggingevent);
                    if(logger.additive)
                        break label0;
                }
                if(i == 0)
                    loggerContext.noAppenderDefinedWarning(this);
                return;
            }
            logger = logger.parent;
        } while(true);
    }

    Logger createChildByLastNamePart(String s)
    {
        if(LoggerNameUtil.getFirstSeparatorIndexOf(s) != -1)
            throw new IllegalArgumentException((new StringBuilder()).append("Child name [").append(s).append(" passed as parameter, may not include [").append('.').append("]").toString());
        if(childrenList == null)
            childrenList = new ArrayList();
        Logger logger;
        if(isRootLogger())
            logger = new Logger(s, this, loggerContext);
        else
            logger = new Logger((new StringBuilder()).append(name).append('.').append(s).toString(), this, loggerContext);
        childrenList.add(logger);
        logger.effectiveLevelInt = effectiveLevelInt;
        return logger;
    }

    Logger createChildByName(String s)
    {
        if(LoggerNameUtil.getSeparatorIndexOf(s, 1 + name.length()) != -1)
            throw new IllegalArgumentException((new StringBuilder()).append("For logger [").append(name).append("] child name [").append(s).append(" passed as parameter, may not include '.' after index").append(1 + name.length()).toString());
        if(childrenList == null)
            childrenList = new ArrayList(5);
        Logger logger = new Logger(s, this, loggerContext);
        childrenList.add(logger);
        logger.effectiveLevelInt = effectiveLevelInt;
        return logger;
    }

    public void debug(String s)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, s, null, null);
    }

    public void debug(String s, Object obj)
    {
        filterAndLog_1(FQCN, null, Level.DEBUG, s, obj, null);
    }

    public void debug(String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, null, Level.DEBUG, s, obj, obj1, null);
    }

    public void debug(String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, s, null, throwable);
    }

    public void debug(String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.DEBUG, s, aobj, null);
    }

    public void debug(Marker marker, String s)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, s, null, null);
    }

    public void debug(Marker marker, String s, Object obj)
    {
        filterAndLog_1(FQCN, marker, Level.DEBUG, s, obj, null);
    }

    public void debug(Marker marker, String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, marker, Level.DEBUG, s, obj, obj1, null);
    }

    public void debug(Marker marker, String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, s, null, throwable);
    }

    public void debug(Marker marker, String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.DEBUG, s, aobj, null);
    }

    public void detachAndStopAllAppenders()
    {
        if(aai != null)
            aai.detachAndStopAllAppenders();
    }

    public boolean detachAppender(Appender appender)
    {
        boolean flag;
        if(aai == null)
            flag = false;
        else
            flag = aai.detachAppender(appender);
        return flag;
    }

    public boolean detachAppender(String s)
    {
        boolean flag;
        if(aai == null)
            flag = false;
        else
            flag = aai.detachAppender(s);
        return flag;
    }

    public void error(String s)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, s, null, null);
    }

    public void error(String s, Object obj)
    {
        filterAndLog_1(FQCN, null, Level.ERROR, s, obj, null);
    }

    public void error(String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, null, Level.ERROR, s, obj, obj1, null);
    }

    public void error(String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, s, null, throwable);
    }

    public void error(String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.ERROR, s, aobj, null);
    }

    public void error(Marker marker, String s)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, s, null, null);
    }

    public void error(Marker marker, String s, Object obj)
    {
        filterAndLog_1(FQCN, marker, Level.ERROR, s, obj, null);
    }

    public void error(Marker marker, String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, marker, Level.ERROR, s, obj, obj1, null);
    }

    public void error(Marker marker, String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, s, null, throwable);
    }

    public void error(Marker marker, String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.ERROR, s, aobj, null);
    }

    public Appender getAppender(String s)
    {
        Appender appender;
        if(aai == null)
            appender = null;
        else
            appender = aai.getAppender(s);
        return appender;
    }

    Logger getChildByName(String s)
    {
        if(childrenList != null) goto _L2; else goto _L1
_L1:
        Logger logger = null;
_L4:
        return logger;
_L2:
        int i = childrenList.size();
        for(int j = 0; j < i; j++)
        {
            logger = (Logger)childrenList.get(j);
            if(s.equals(logger.getName()))
                continue; /* Loop/switch isn't completed */
        }

        logger = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Level getEffectiveLevel()
    {
        return Level.toLevel(effectiveLevelInt);
    }

    int getEffectiveLevelInt()
    {
        return effectiveLevelInt;
    }

    public Level getLevel()
    {
        return level;
    }

    public LoggerContext getLoggerContext()
    {
        return loggerContext;
    }

    public String getName()
    {
        return name;
    }

    public void info(String s)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, s, null, null);
    }

    public void info(String s, Object obj)
    {
        filterAndLog_1(FQCN, null, Level.INFO, s, obj, null);
    }

    public void info(String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, null, Level.INFO, s, obj, obj1, null);
    }

    public void info(String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, s, null, throwable);
    }

    public void info(String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.INFO, s, aobj, null);
    }

    public void info(Marker marker, String s)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, s, null, null);
    }

    public void info(Marker marker, String s, Object obj)
    {
        filterAndLog_1(FQCN, marker, Level.INFO, s, obj, null);
    }

    public void info(Marker marker, String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, marker, Level.INFO, s, obj, obj1, null);
    }

    public void info(Marker marker, String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, s, null, throwable);
    }

    public void info(Marker marker, String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.INFO, s, aobj, null);
    }

    public boolean isAdditive()
    {
        return additive;
    }

    public boolean isAttached(Appender appender)
    {
        boolean flag;
        if(aai == null)
            flag = false;
        else
            flag = aai.isAttached(appender);
        return flag;
    }

    public boolean isDebugEnabled()
    {
        return isDebugEnabled(null);
    }

    public boolean isDebugEnabled(Marker marker)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, Level.DEBUG);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > 10000)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public boolean isEnabledFor(Level level1)
    {
        return isEnabledFor(null, level1);
    }

    public boolean isEnabledFor(Marker marker, Level level1)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, level1);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > level1.levelInt)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public boolean isErrorEnabled()
    {
        return isErrorEnabled(null);
    }

    public boolean isErrorEnabled(Marker marker)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, Level.ERROR);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > 40000)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public boolean isInfoEnabled()
    {
        return isInfoEnabled(null);
    }

    public boolean isInfoEnabled(Marker marker)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, Level.INFO);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > 20000)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public boolean isTraceEnabled()
    {
        return isTraceEnabled(null);
    }

    public boolean isTraceEnabled(Marker marker)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, Level.TRACE);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > 5000)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public boolean isWarnEnabled()
    {
        return isWarnEnabled(null);
    }

    public boolean isWarnEnabled(Marker marker)
    {
        boolean flag;
        FilterReply filterreply;
        flag = true;
        filterreply = callTurboFilters(marker, Level.WARN);
        if(filterreply != FilterReply.NEUTRAL) goto _L2; else goto _L1
_L1:
        if(effectiveLevelInt > 30000)
            flag = false;
_L4:
        return flag;
_L2:
        if(filterreply != FilterReply.DENY)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(filterreply == FilterReply.ACCEPT) goto _L4; else goto _L5
_L5:
        throw new IllegalStateException((new StringBuilder()).append("Unknown FilterReply value: ").append(filterreply).toString());
    }

    public Iterator iteratorForAppenders()
    {
        Iterator iterator;
        if(aai == null)
            iterator = Collections.EMPTY_LIST.iterator();
        else
            iterator = aai.iteratorForAppenders();
        return iterator;
    }

    public void log(Marker marker, String s, int i, String s1, Object aobj[], Throwable throwable)
    {
        filterAndLog_0_Or3Plus(s, marker, Level.fromLocationAwareLoggerInteger(i), s1, aobj, throwable);
    }

    protected Object readResolve()
        throws ObjectStreamException
    {
        return LoggerFactory.getLogger(getName());
    }

    void recursiveReset()
    {
        detachAndStopAllAppenders();
        localLevelReset();
        additive = true;
        if(childrenList != null)
        {
            Iterator iterator = childrenList.iterator();
            while(iterator.hasNext()) 
                ((Logger)iterator.next()).recursiveReset();
        }
    }

    public void setAdditive(boolean flag)
    {
        additive = flag;
    }

    /**
     * @deprecated Method setLevel is deprecated
     */

    public void setLevel(Level level1)
    {
        this;
        JVM INSTR monitorenter ;
        Level level2 = level;
        if(level2 != level1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(level1 != null)
            break MISSING_BLOCK_LABEL_42;
        if(isRootLogger())
            throw new IllegalArgumentException("The level of the root logger cannot be set to null");
        break MISSING_BLOCK_LABEL_42;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        level = level1;
        if(level1 == null)
        {
            effectiveLevelInt = parent.effectiveLevelInt;
            level1 = parent.getEffectiveLevel();
        } else
        {
            effectiveLevelInt = level1.levelInt;
        }
        if(childrenList != null)
        {
            int i = childrenList.size();
            for(int j = 0; j < i; j++)
                ((Logger)childrenList.get(j)).handleParentLevelChange(effectiveLevelInt);

        }
        loggerContext.fireOnLevelChange(this, level1);
          goto _L1
    }

    public String toString()
    {
        return (new StringBuilder()).append("Logger[").append(name).append("]").toString();
    }

    public void trace(String s)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, s, null, null);
    }

    public void trace(String s, Object obj)
    {
        filterAndLog_1(FQCN, null, Level.TRACE, s, obj, null);
    }

    public void trace(String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, null, Level.TRACE, s, obj, obj1, null);
    }

    public void trace(String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, s, null, throwable);
    }

    public void trace(String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.TRACE, s, aobj, null);
    }

    public void trace(Marker marker, String s)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, s, null, null);
    }

    public void trace(Marker marker, String s, Object obj)
    {
        filterAndLog_1(FQCN, marker, Level.TRACE, s, obj, null);
    }

    public void trace(Marker marker, String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, marker, Level.TRACE, s, obj, obj1, null);
    }

    public void trace(Marker marker, String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, s, null, throwable);
    }

    public void trace(Marker marker, String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.TRACE, s, aobj, null);
    }

    public void warn(String s)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, s, null, null);
    }

    public void warn(String s, Object obj)
    {
        filterAndLog_1(FQCN, null, Level.WARN, s, obj, null);
    }

    public void warn(String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, null, Level.WARN, s, obj, obj1, null);
    }

    public void warn(String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, s, null, throwable);
    }

    public void warn(String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, null, Level.WARN, s, aobj, null);
    }

    public void warn(Marker marker, String s)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, s, null, null);
    }

    public void warn(Marker marker, String s, Object obj)
    {
        filterAndLog_1(FQCN, marker, Level.WARN, s, obj, null);
    }

    public void warn(Marker marker, String s, Object obj, Object obj1)
    {
        filterAndLog_2(FQCN, marker, Level.WARN, s, obj, obj1, null);
    }

    public void warn(Marker marker, String s, Throwable throwable)
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, s, null, throwable);
    }

    public void warn(Marker marker, String s, Object aobj[])
    {
        filterAndLog_0_Or3Plus(FQCN, marker, Level.WARN, s, aobj, null);
    }

    private static final int DEFAULT_CHILD_ARRAY_SIZE = 5;
    public static final String FQCN = ch/qos/logback/classic/Logger.getName();
    private static final long serialVersionUID = 0x4bb1f08f92d542c2L;
    private transient AppenderAttachableImpl aai;
    private transient boolean additive;
    private transient List childrenList;
    private transient int effectiveLevelInt;
    private transient Level level;
    final transient LoggerContext loggerContext;
    private String name;
    private transient Logger parent;

}
