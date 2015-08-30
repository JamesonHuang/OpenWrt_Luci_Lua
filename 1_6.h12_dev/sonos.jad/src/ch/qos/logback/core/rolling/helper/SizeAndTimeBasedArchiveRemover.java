// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            DefaultArchiveRemover, RollingCalendar, FileNamePattern, FileFilterUtil

public class SizeAndTimeBasedArchiveRemover extends DefaultArchiveRemover
{

    public SizeAndTimeBasedArchiveRemover(FileNamePattern filenamepattern, RollingCalendar rollingcalendar)
    {
        super(filenamepattern, rollingcalendar);
    }

    public void cleanByPeriodOffset(Date date, int i)
    {
        int j = 0;
        Date date1 = rc.getRelativeDate(date, i);
        String s = FileFilterUtil.afterLastSlash(fileNamePattern.toRegexForFixedDate(date1));
        FileNamePattern filenamepattern = fileNamePattern;
        Object aobj[] = new Object[2];
        aobj[j] = date1;
        aobj[1] = Integer.valueOf(0);
        File file = (new File(filenamepattern.convertMultipleArguments(aobj))).getAbsoluteFile().getAbsoluteFile().getParentFile();
        File afile[] = FileFilterUtil.filesInFolderMatchingStemRegex(file, s);
        for(int k = afile.length; j < k; j++)
            afile[j].delete();

        if(parentClean)
            removeFolderIfEmpty(file);
    }
}
