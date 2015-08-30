// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.spi.ContextAware;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TriggeringPolicy, TimeBasedRollingPolicy

public interface TimeBasedFileNamingAndTriggeringPolicy
    extends TriggeringPolicy, ContextAware
{

    public abstract ArchiveRemover getArchiveRemover();

    public abstract String getCurrentPeriodsFileNameWithoutCompressionSuffix();

    public abstract long getCurrentTime();

    public abstract String getElapsedPeriodsFileName();

    public abstract void setCurrentTime(long l);

    public abstract void setTimeBasedRollingPolicy(TimeBasedRollingPolicy timebasedrollingpolicy);
}
