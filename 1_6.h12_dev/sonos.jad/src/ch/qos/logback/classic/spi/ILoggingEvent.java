// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import java.util.Map;
import org.slf4j.Marker;

// Referenced classes of package ch.qos.logback.classic.spi:
//            LoggerContextVO, IThrowableProxy

public interface ILoggingEvent
    extends DeferredProcessingAware
{

    public abstract Object[] getArgumentArray();

    public abstract StackTraceElement[] getCallerData();

    public abstract String getFormattedMessage();

    public abstract Level getLevel();

    public abstract LoggerContextVO getLoggerContextVO();

    public abstract String getLoggerName();

    public abstract Map getMDCPropertyMap();

    public abstract Marker getMarker();

    public abstract Map getMdc();

    public abstract String getMessage();

    public abstract String getThreadName();

    public abstract IThrowableProxy getThrowableProxy();

    public abstract long getTimeStamp();

    public abstract boolean hasCallerData();

    public abstract void prepareForDeferredProcessing();
}
