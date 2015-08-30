// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.LiteralConverter;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            ArchiveRemover, FileFilterUtil, RollingCalendar, FileNamePattern, 
//            DateTokenConverter

public abstract class DefaultArchiveRemover extends ContextAwareBase
    implements ArchiveRemover
{

    public DefaultArchiveRemover(FileNamePattern filenamepattern, RollingCalendar rollingcalendar)
    {
        lastHeartBeat = -1L;
        fileNamePattern = filenamepattern;
        rc = rollingcalendar;
        parentClean = computeParentCleaningFlag(filenamepattern);
    }

    private void removeFolderIfEmpty(File file, int i)
    {
        if(i < 3 && file.isDirectory() && FileFilterUtil.isEmptyDirectory(file))
        {
            addInfo((new StringBuilder()).append("deleting folder [").append(file).append("]").toString());
            file.delete();
            removeFolderIfEmpty(file.getParentFile(), i + 1);
        }
    }

    public void clean(Date date)
    {
        long l = date.getTime();
        int i = computeElapsedPeriodsSinceLastClean(l);
        lastHeartBeat = l;
        if(i > 1)
            addInfo((new StringBuilder()).append("periodsElapsed = ").append(i).toString());
        for(int j = 0; j < i; j++)
            cleanByPeriodOffset(date, periodOffsetForDeletionTarget - j);

    }

    abstract void cleanByPeriodOffset(Date date, int i);

    int computeElapsedPeriodsSinceLastClean(long l)
    {
label0:
        {
            {
                long l1 = 336L;
                if(lastHeartBeat != -1L)
                    break label0;
                addInfo("first clean up after appender initialization");
                long l2 = rc.periodsElapsed(l, 0x149970000L + l);
                if(l2 <= l1)
                    l1 = l2;
            }
            return (int)l1;
        }
        l1 = rc.periodsElapsed(lastHeartBeat, l);
        if(l1 < 1L)
        {
            addWarn((new StringBuilder()).append("Unexpected periodsElapsed value ").append(l1).toString());
            l1 = 1L;
        }
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_43;
    }

    boolean computeParentCleaningFlag(FileNamePattern filenamepattern)
    {
        boolean flag = true;
        if(filenamepattern.getPrimaryDateTokenConverter().getDatePattern().indexOf('/') == -1) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        Converter converter = filenamepattern.headTokenConverter;
        do
        {
            if(converter == null || (converter instanceof DateTokenConverter))
            {
                for(; converter != null; converter = converter.getNext())
                    if((converter instanceof LiteralConverter) && converter.convert(null).indexOf('/') != -1)
                        continue; /* Loop/switch isn't completed */

                break;
            }
            converter = converter.getNext();
        } while(true);
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    void removeFolderIfEmpty(File file)
    {
        removeFolderIfEmpty(file, 0);
    }

    public void setMaxHistory(int i)
    {
        periodOffsetForDeletionTarget = -1 + -i;
    }

    protected static final long INACTIVITY_TOLERANCE_IN_MILLIS = 0x149970000L;
    static final int MAX_VALUE_FOR_INACTIVITY_PERIODS = 336;
    protected static final long UNINITIALIZED = -1L;
    final FileNamePattern fileNamePattern;
    long lastHeartBeat;
    final boolean parentClean;
    int periodOffsetForDeletionTarget;
    final RollingCalendar rc;
}
