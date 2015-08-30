// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import java.io.*;
import java.util.Map;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

// Referenced classes of package ch.qos.logback.classic.spi:
//            ILoggingEvent, ThrowableProxyVO, LoggerContextVO, IThrowableProxy

public class LoggingEventVO
    implements ILoggingEvent, Serializable
{

    public LoggingEventVO()
    {
    }

    public static LoggingEventVO build(ILoggingEvent iloggingevent)
    {
        LoggingEventVO loggingeventvo = new LoggingEventVO();
        loggingeventvo.loggerName = iloggingevent.getLoggerName();
        loggingeventvo.loggerContextVO = iloggingevent.getLoggerContextVO();
        loggingeventvo.threadName = iloggingevent.getThreadName();
        loggingeventvo.level = iloggingevent.getLevel();
        loggingeventvo.message = iloggingevent.getMessage();
        loggingeventvo.argumentArray = iloggingevent.getArgumentArray();
        loggingeventvo.marker = iloggingevent.getMarker();
        loggingeventvo.mdcPropertyMap = iloggingevent.getMDCPropertyMap();
        loggingeventvo.timeStamp = iloggingevent.getTimeStamp();
        loggingeventvo.throwableProxy = ThrowableProxyVO.build(iloggingevent.getThrowableProxy());
        if(iloggingevent.hasCallerData())
            loggingeventvo.callerDataArray = iloggingevent.getCallerData();
        return loggingeventvo;
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        level = Level.toLevel(objectinputstream.readInt());
        int i = objectinputstream.readInt();
        if(i != -1)
        {
            argumentArray = new String[i];
            for(int j = 0; j < i; j++)
            {
                Object obj = objectinputstream.readObject();
                if(!"NULL_ARGUMENT_ARRAY_ELEMENT".equals(obj))
                    argumentArray[j] = obj;
            }

        }
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        objectoutputstream.writeInt(level.levelInt);
        if(argumentArray != null)
        {
            objectoutputstream.writeInt(argumentArray.length);
            int i = 0;
            while(i < argumentArray.length) 
            {
                if(argumentArray[i] != null)
                    objectoutputstream.writeObject(argumentArray[i].toString());
                else
                    objectoutputstream.writeObject("NULL_ARGUMENT_ARRAY_ELEMENT");
                i++;
            }
        } else
        {
            objectoutputstream.writeInt(-1);
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(getClass() != obj.getClass())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        LoggingEventVO loggingeventvo = (LoggingEventVO)obj;
        if(message == null)
        {
            if(loggingeventvo.message != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!message.equals(loggingeventvo.message))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(loggerName == null)
        {
            if(loggingeventvo.loggerName != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!loggerName.equals(loggingeventvo.loggerName))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(threadName == null)
        {
            if(loggingeventvo.threadName != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!threadName.equals(loggingeventvo.threadName))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(timeStamp != loggingeventvo.timeStamp)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(marker == null)
        {
            if(loggingeventvo.marker != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!marker.equals(loggingeventvo.marker))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(mdcPropertyMap == null)
        {
            if(loggingeventvo.mdcPropertyMap != null)
                flag = false;
        } else
        if(!mdcPropertyMap.equals(loggingeventvo.mdcPropertyMap))
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Object[] getArgumentArray()
    {
        return argumentArray;
    }

    public StackTraceElement[] getCallerData()
    {
        return callerDataArray;
    }

    public long getContextBirthTime()
    {
        return loggerContextVO.getBirthTime();
    }

    public LoggerContextVO getContextLoggerRemoteView()
    {
        return loggerContextVO;
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
        return mdcPropertyMap;
    }

    public Marker getMarker()
    {
        return marker;
    }

    public Map getMdc()
    {
        return mdcPropertyMap;
    }

    public String getMessage()
    {
        return message;
    }

    public String getThreadName()
    {
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

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(message == null)
            j = 0;
        else
            j = message.hashCode();
        k = 31 * (j + 31);
        if(threadName != null)
            i = threadName.hashCode();
        return 31 * (k + i) + (int)(timeStamp ^ timeStamp >>> 32);
    }

    public void prepareForDeferredProcessing()
    {
    }

    private static final int NULL_ARGUMENT_ARRAY = -1;
    private static final String NULL_ARGUMENT_ARRAY_ELEMENT = "NULL_ARGUMENT_ARRAY_ELEMENT";
    private static final long serialVersionUID = 0x5af38006fcec0a48L;
    private transient Object argumentArray[];
    private StackTraceElement callerDataArray[];
    private transient String formattedMessage;
    private transient Level level;
    private LoggerContextVO loggerContextVO;
    private String loggerName;
    private Marker marker;
    private Map mdcPropertyMap;
    private String message;
    private String threadName;
    private ThrowableProxyVO throwableProxy;
    private long timeStamp;
}
