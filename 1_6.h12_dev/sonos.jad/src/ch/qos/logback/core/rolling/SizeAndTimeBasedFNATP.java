// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.rolling.helper.SizeAndTimeBasedArchiveRemover;
import ch.qos.logback.core.util.FileSize;
import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TimeBasedFileNamingAndTriggeringPolicyBase, TimeBasedRollingPolicy

public class SizeAndTimeBasedFNATP extends TimeBasedFileNamingAndTriggeringPolicyBase
{

    public SizeAndTimeBasedFNATP()
    {
        currentPeriodsCounter = 0;
        invocationMask = 1;
    }

    private String getFileNameIncludingCompressionSuffix(Date date, int i)
    {
        FileNamePattern filenamepattern = tbrp.fileNamePattern;
        Object aobj[] = new Object[2];
        aobj[0] = dateInCurrentPeriod;
        aobj[1] = Integer.valueOf(i);
        return filenamepattern.convertMultipleArguments(aobj);
    }

    void computeCurrentPeriodsHighestCounterValue(String s)
    {
        File afile[] = FileFilterUtil.filesInFolderMatchingStemRegex((new File(getCurrentPeriodsFileNameWithoutCompressionSuffix())).getParentFile(), s);
        if(afile != null && afile.length != 0) goto _L2; else goto _L1
_L1:
        currentPeriodsCounter = 0;
_L4:
        return;
_L2:
        currentPeriodsCounter = FileFilterUtil.findHighestCounter(afile, s);
        if(tbrp.getParentsRawFileProperty() != null || tbrp.compressionMode != CompressionMode.NONE)
            currentPeriodsCounter = 1 + currentPeriodsCounter;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected ArchiveRemover createArchiveRemover()
    {
        return new SizeAndTimeBasedArchiveRemover(tbrp.fileNamePattern, rc);
    }

    public String getCurrentPeriodsFileNameWithoutCompressionSuffix()
    {
        FileNamePattern filenamepattern = tbrp.fileNamePatternWCS;
        Object aobj[] = new Object[2];
        aobj[0] = dateInCurrentPeriod;
        aobj[1] = Integer.valueOf(currentPeriodsCounter);
        return filenamepattern.convertMultipleArguments(aobj);
    }

    public String getMaxFileSize()
    {
        return maxFileSizeAsString;
    }

    public boolean isTriggeringEvent(File file, Object obj)
    {
        boolean flag = true;
        long l = getCurrentTime();
        if(l >= nextCheck)
        {
            Date date = dateInCurrentPeriod;
            FileNamePattern filenamepattern1 = tbrp.fileNamePatternWCS;
            Object aobj1[] = new Object[2];
            aobj1[0] = date;
            aobj1[flag] = Integer.valueOf(currentPeriodsCounter);
            elapsedPeriodsFileName = filenamepattern1.convertMultipleArguments(aobj1);
            currentPeriodsCounter = 0;
            setDateInCurrentPeriod(l);
            computeNextCheck();
        } else
        {
            int i = 1 + invocationCounter;
            invocationCounter = i;
            if((i & invocationMask) != invocationMask)
            {
                flag = false;
            } else
            {
                if(invocationMask < 15)
                    invocationMask = 1 + (invocationMask << 1);
                if(file.length() >= maxFileSize.getSize())
                {
                    FileNamePattern filenamepattern = tbrp.fileNamePatternWCS;
                    Object aobj[] = new Object[2];
                    aobj[0] = dateInCurrentPeriod;
                    aobj[flag] = Integer.valueOf(currentPeriodsCounter);
                    elapsedPeriodsFileName = filenamepattern.convertMultipleArguments(aobj);
                    currentPeriodsCounter = 1 + currentPeriodsCounter;
                } else
                {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public void setMaxFileSize(String s)
    {
        maxFileSizeAsString = s;
        maxFileSize = FileSize.valueOf(s);
    }

    public void start()
    {
        super.start();
        archiveRemover = createArchiveRemover();
        archiveRemover.setContext(context);
        computeCurrentPeriodsHighestCounterValue(FileFilterUtil.afterLastSlash(tbrp.fileNamePattern.toRegexForFixedDate(dateInCurrentPeriod)));
        started = true;
    }

    int currentPeriodsCounter;
    private int invocationCounter;
    private int invocationMask;
    FileSize maxFileSize;
    String maxFileSizeAsString;
}
