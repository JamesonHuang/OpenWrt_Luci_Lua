// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.rolling.helper.RollingCalendar;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TimeBasedFileNamingAndTriggeringPolicy, TimeBasedRollingPolicy

public abstract class TimeBasedFileNamingAndTriggeringPolicyBase extends ContextAwareBase
    implements TimeBasedFileNamingAndTriggeringPolicy
{

    public TimeBasedFileNamingAndTriggeringPolicyBase()
    {
        archiveRemover = null;
        artificialCurrentTime = -1L;
        dateInCurrentPeriod = null;
        started = false;
    }

    protected void computeNextCheck()
    {
        nextCheck = rc.getNextTriggeringMillis(dateInCurrentPeriod);
    }

    public ArchiveRemover getArchiveRemover()
    {
        return archiveRemover;
    }

    public String getCurrentPeriodsFileNameWithoutCompressionSuffix()
    {
        return tbrp.fileNamePatternWCS.convert(dateInCurrentPeriod);
    }

    public long getCurrentTime()
    {
        long l;
        if(artificialCurrentTime >= 0L)
            l = artificialCurrentTime;
        else
            l = System.currentTimeMillis();
        return l;
    }

    public String getElapsedPeriodsFileName()
    {
        return elapsedPeriodsFileName;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setCurrentTime(long l)
    {
        artificialCurrentTime = l;
    }

    protected void setDateInCurrentPeriod(long l)
    {
        dateInCurrentPeriod.setTime(l);
    }

    public void setDateInCurrentPeriod(Date date)
    {
        dateInCurrentPeriod = date;
    }

    public void setTimeBasedRollingPolicy(TimeBasedRollingPolicy timebasedrollingpolicy)
    {
        tbrp = timebasedrollingpolicy;
    }

    public void start()
    {
        DateTokenConverter datetokenconverter = tbrp.fileNamePattern.getPrimaryDateTokenConverter();
        if(datetokenconverter == null)
            throw new IllegalStateException((new StringBuilder()).append("FileNamePattern [").append(tbrp.fileNamePattern.getPattern()).append("] does not contain a valid DateToken").toString());
        rc = new RollingCalendar();
        rc.init(datetokenconverter.getDatePattern());
        addInfo((new StringBuilder()).append("The date pattern is '").append(datetokenconverter.getDatePattern()).append("' from file name pattern '").append(tbrp.fileNamePattern.getPattern()).append("'.").toString());
        rc.printPeriodicity(this);
        setDateInCurrentPeriod(new Date(getCurrentTime()));
        if(tbrp.getParentsRawFileProperty() != null)
        {
            File file = new File(tbrp.getParentsRawFileProperty());
            if(file.exists() && file.canRead())
                setDateInCurrentPeriod(new Date(file.lastModified()));
        }
        addInfo((new StringBuilder()).append("Setting initial period to ").append(dateInCurrentPeriod).toString());
        computeNextCheck();
    }

    public void stop()
    {
        started = false;
    }

    protected ArchiveRemover archiveRemover;
    protected long artificialCurrentTime;
    protected Date dateInCurrentPeriod;
    protected String elapsedPeriodsFileName;
    protected long nextCheck;
    protected RollingCalendar rc;
    protected boolean started;
    protected TimeBasedRollingPolicy tbrp;
}
