// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.rolling.helper.TimeBasedArchiveRemover;
import java.io.File;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TimeBasedFileNamingAndTriggeringPolicyBase, TimeBasedRollingPolicy

public class DefaultTimeBasedFileNamingAndTriggeringPolicy extends TimeBasedFileNamingAndTriggeringPolicyBase
{

    public DefaultTimeBasedFileNamingAndTriggeringPolicy()
    {
    }

    public boolean isTriggeringEvent(File file, Object obj)
    {
        long l = getCurrentTime();
        boolean flag;
        if(l >= nextCheck)
        {
            java.util.Date date = dateInCurrentPeriod;
            addInfo((new StringBuilder()).append("Elapsed period: ").append(date).toString());
            elapsedPeriodsFileName = tbrp.fileNamePatternWCS.convert(date);
            setDateInCurrentPeriod(l);
            computeNextCheck();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void start()
    {
        super.start();
        archiveRemover = new TimeBasedArchiveRemover(tbrp.fileNamePattern, rc);
        archiveRemover.setContext(context);
        started = true;
    }

    public String toString()
    {
        return "c.q.l.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy";
    }
}
