// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            DefaultArchiveRemover, RollingCalendar, FileNamePattern

public class TimeBasedArchiveRemover extends DefaultArchiveRemover
{

    public TimeBasedArchiveRemover(FileNamePattern filenamepattern, RollingCalendar rollingcalendar)
    {
        super(filenamepattern, rollingcalendar);
    }

    protected void cleanByPeriodOffset(Date date, int i)
    {
        Date date1 = rc.getRelativeDate(date, i);
        File file = new File(fileNamePattern.convert(date1));
        if(file.exists() && file.isFile())
        {
            file.delete();
            addInfo((new StringBuilder()).append("deleting ").append(file).toString());
            if(parentClean)
                removeFolderIfEmpty(file.getParentFile());
        }
    }

    public String toString()
    {
        return "c.q.l.core.rolling.helper.TimeBasedArchiveRemover";
    }
}
