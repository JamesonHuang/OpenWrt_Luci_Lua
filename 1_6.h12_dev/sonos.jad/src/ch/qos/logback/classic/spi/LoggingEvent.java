// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.*;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.MDCAdapter;

// Referenced classes of package ch.qos.logback.classic.spi:
//            ILoggingEvent, ThrowableProxy, EventArgUtil, CallerData, 
//            LoggerContextVO, IThrowableProxy

public class LoggingEvent
    implements ILoggingEvent
{

    public LoggingEvent()
    {
    }

    public LoggingEvent(String s, Logger logger, Level level1, String s1, Throwable throwable, Object aobj[])
    {
        fqnOfLoggerClass = s;
        loggerName = logger.getName();
        loggerContext = logger.getLoggerContext();
        loggerContextVO = loggerContext.getLoggerContextRemoteView();
        level = level1;
        message = s1;
        argumentArray = aobj;
        if(throwable == null)
            throwable = extractThrowableAnRearrangeArguments(aobj);
        if(throwable != null)
        {
            throwableProxy = new ThrowableProxy(throwable);
            if(logger.getLoggerContext().isPackagingDataEnabled())
                throwableProxy.calculatePackagingData();
        }
        timeStamp = System.currentTimeMillis();
    }

    private Throwable extractThrowableAnRearrangeArguments(Object aobj[])
    {
        Throwable throwable = EventArgUtil.extractThrowable(aobj);
        if(EventArgUtil.successfulExtraction(throwable))
            argumentArray = EventArgUtil.trimmedCopy(aobj);
        return throwable;
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        throw new UnsupportedOperationException((new StringBuilder()).append(getClass()).append(" does not support serialization. ").append("Use LoggerEventVO instance instead. See also LoggerEventVO.build method.").toString());
    }

    public Object[] getArgumentArray()
    {
        return argumentArray;
    }

    public StackTraceElement[] getCallerData()
    {
        if(callerDataArray == null)
            callerDataArray = CallerData.extract(new Throwable(), fqnOfLoggerClass, loggerContext.getMaxCallerDataDepth(), loggerContext.getFrameworkPackages());
        return callerDataArray;
    }

    public long getContextBirthTime()
    {
        return loggerContextVO.getBirthTime();
    }

    public String getFormattedMessage()
    {
        String s;
        if(formattedMessage != null)
        {
            s = formattedMessage;
        } else
        {
            if(argumentArray != null)
                formattedMessage = MessageFormatter.arrayFormat(message, argumentArray).getMessage();
            else
                formattedMessage = message;
            s = formattedMessage;
        }
        return s;
    }

    public Level getLevel()
    {
        return level;
    }

    public LoggerContextVO getLoggerContextVO()
    {
        return loggerContextVO;
    }

    public String getLoggerName()
    {
        return loggerName;
    }

    public Map getMDCPropertyMap()
    {
        if(mdcPropertyMap == null)
        {
            MDCAdapter mdcadapter = MDC.getMDCAdapter();
            if(mdcadapter instanceof LogbackMDCAdapter)
                mdcPropertyMap = ((LogbackMDCAdapter)mdcadapter).getPropertyMap();
            else
                mdcPropertyMap = mdcadapter.getCopyOfContextMap();
        }
        if(mdcPropertyMap == null)
            mdcPropertyMap = CACHED_NULL_MAP;
        return mdcPropertyMap;
    }

    public Marker getMarker()
    {
        return marker;
    }

    public Map getMdc()
    {
        return getMDCPropertyMap();
    }

    public String getMessage()
    {
        return message;
    }

    public String getThreadName()
    {
        if(threadName == null)
            threadName = Thread.currentThread().getName();
        return threadName;
    }

    public IThrowableProxy getThrowableProxy()
    {
        return throwableProxy;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public boolean hasCallerData()
    {
        boolean flag;
        if(callerDataArray != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void prepareForDeferredProcessing()
    {
        getFormattedMessage();
        getThreadName();
        getMDCPropertyMap();
    }

    public void setArgumentArray(Object aobj[])
    {
        if(argumentArray != null)
        {
            throw new IllegalStateException("argArray has been already set");
        } else
        {
            argumentArray = aobj;
            return;
        }
    }

    public void setCallerData(StackTraceElement astacktraceelement[])
    {
        callerDataArray = astacktraceelement;
    }

    public void setLevel(Level level1)
    {
        if(level != null)
        {
            throw new IllegalStateException("The level has been already set for this event.");
        } else
        {
            level = level1;
            return;
        }
    }

    public void setLoggerContextRemoteView(LoggerContextVO loggercontextvo)
    {
        loggerContextVO = loggercontextvo;
    }

    public void setLoggerName(String s)
    {
        loggerName = s;
    }

    public void setMDCPropertyMap(Map map)
    {
        if(mdcPropertyMap != null)
        {
            throw new IllegalStateException("The MDCPropertyMap has been already set for this event.");
        } else
        {
            mdcPropertyMap = map;
            return;
        }
    }

    public void setMarker(Marker marker1)
    {
        if(marker != null)
        {
            throw new IllegalStateException("The marker has been already set for this event.");
        } else
        {
            marker = marker1;
            return;
        }
    }

    public void setMessage(String s)
    {
        if(message != null)
        {
            throw new IllegalStateException("The message for this event has been set already.");
        } else
        {
            message = s;
            return;
        }
    }

    public void setThreadName(String s)
        throws IllegalStateException
    {
        if(threadName != null)
        {
            throw new IllegalStateException("threadName has been already set");
        } else
        {
            threadName = s;
            return;
        }
    }

    public void setThrowableProxy(ThrowableProxy throwableproxy)
    {
        if(throwableProxy != null)
        {
            throw new IllegalStateException("ThrowableProxy has been already set.");
        } else
        {
            throwableProxy = throwableproxy;
            return;
        }
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        stringbuilder.append(level).append("] ");
        stringbuilder.append(getFormattedMessage());
        return stringbuilder.toString();
    }

    private static final Map CACHED_NULL_MAP = new HashMap();
    private transient Object argumentArray[];
    private StackTraceElement callerDataArray[];
    transient String formattedMessage;
    transient String fqnOfLoggerClass;
    private transient Level level;
    private LoggerContext loggerContext;
    private LoggerContextVO loggerContextVO;
    private String loggerName;
    private Marker marker;
    private Map mdcPropertyMap;
    private String message;
    private String threadName;
    private ThrowableProxy throwableProxy;
    private long timeStamp;

}
