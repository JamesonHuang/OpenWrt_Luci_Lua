// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import ch.qos.logback.core.rolling.helper.AsynchronousCompressor;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import java.io.File;
import java.util.Date;
import java.util.concurrent.*;

// Referenced classes of package ch.qos.logback.core.rolling:
//            RollingPolicyBase, TriggeringPolicy, RolloverFailure, TimeBasedFileNamingAndTriggeringPolicy, 
//            DefaultTimeBasedFileNamingAndTriggeringPolicy

public class TimeBasedRollingPolicy extends RollingPolicyBase
    implements TriggeringPolicy
{

    public TimeBasedRollingPolicy()
    {
        renameUtil = new RenameUtil();
        maxHistory = 0;
        cleanHistoryOnStart = false;
    }

    private String transformFileNamePattern2ZipEntry(String s)
    {
        return FileFilterUtil.afterLastSlash(FileFilterUtil.slashify(s));
    }

    private void waitForAsynchronousJobToStop()
    {
        if(future == null)
            break MISSING_BLOCK_LABEL_23;
        future.get(30L, TimeUnit.SECONDS);
_L1:
        return;
        TimeoutException timeoutexception;
        timeoutexception;
        addError("Timeout while waiting for compression job to finish", timeoutexception);
          goto _L1
        Exception exception;
        exception;
        addError("Unexpected exception while waiting for compression job to finish", exception);
          goto _L1
    }

    Future asyncCompress(String s, String s1, String s2)
        throws RolloverFailure
    {
        return (new AsynchronousCompressor(compressor)).compressAsynchronously(s, s1, s2);
    }

    public String getActiveFileName()
    {
        String s = getParentsRawFileProperty();
        if(s == null)
            s = timeBasedFileNamingAndTriggeringPolicy.getCurrentPeriodsFileNameWithoutCompressionSuffix();
        return s;
    }

    public int getMaxHistory()
    {
        return maxHistory;
    }

    public TimeBasedFileNamingAndTriggeringPolicy getTimeBasedFileNamingAndTriggeringPolicy()
    {
        return timeBasedFileNamingAndTriggeringPolicy;
    }

    public boolean isCleanHistoryOnStart()
    {
        return cleanHistoryOnStart;
    }

    public boolean isTriggeringEvent(File file, Object obj)
    {
        return timeBasedFileNamingAndTriggeringPolicy.isTriggeringEvent(file, obj);
    }

    Future renamedRawAndAsyncCompress(String s, String s1)
        throws RolloverFailure
    {
        String s2 = getParentsRawFileProperty();
        String s3 = (new StringBuilder()).append(s2).append(System.nanoTime()).append(".tmp").toString();
        renameUtil.rename(s2, s3);
        return asyncCompress(s3, s, s1);
    }

    public void rollover()
        throws RolloverFailure
    {
        String s = timeBasedFileNamingAndTriggeringPolicy.getElapsedPeriodsFileName();
        String s1 = FileFilterUtil.afterLastSlash(s);
        if(compressionMode == CompressionMode.NONE)
        {
            if(getParentsRawFileProperty() != null)
                renameUtil.rename(getParentsRawFileProperty(), s);
        } else
        if(getParentsRawFileProperty() == null)
            future = asyncCompress(s, s, s1);
        else
            future = renamedRawAndAsyncCompress(s, s1);
        if(archiveRemover != null)
            archiveRemover.clean(new Date(timeBasedFileNamingAndTriggeringPolicy.getCurrentTime()));
    }

    public void setCleanHistoryOnStart(boolean flag)
    {
        cleanHistoryOnStart = flag;
    }

    public void setMaxHistory(int i)
    {
        maxHistory = i;
    }

    public void setTimeBasedFileNamingAndTriggeringPolicy(TimeBasedFileNamingAndTriggeringPolicy timebasedfilenamingandtriggeringpolicy)
    {
        timeBasedFileNamingAndTriggeringPolicy = timebasedfilenamingandtriggeringpolicy;
    }

    public void start()
    {
        renameUtil.setContext(context);
        if(fileNamePatternStr != null)
        {
            fileNamePattern = new FileNamePattern(fileNamePatternStr, context);
            determineCompressionMode();
            compressor = new Compressor(compressionMode);
            compressor.setContext(context);
            fileNamePatternWCS = new FileNamePattern(Compressor.computeFileNameStr_WCS(fileNamePatternStr, compressionMode), context);
            addInfo((new StringBuilder()).append("Will use the pattern ").append(fileNamePatternWCS).append(" for the active file").toString());
            if(compressionMode == CompressionMode.ZIP)
                zipEntryFileNamePattern = new FileNamePattern(transformFileNamePattern2ZipEntry(fileNamePatternStr), context);
            if(timeBasedFileNamingAndTriggeringPolicy == null)
                timeBasedFileNamingAndTriggeringPolicy = new DefaultTimeBasedFileNamingAndTriggeringPolicy();
            timeBasedFileNamingAndTriggeringPolicy.setContext(context);
            timeBasedFileNamingAndTriggeringPolicy.setTimeBasedRollingPolicy(this);
            timeBasedFileNamingAndTriggeringPolicy.start();
            if(maxHistory != 0)
            {
                archiveRemover = timeBasedFileNamingAndTriggeringPolicy.getArchiveRemover();
                archiveRemover.setMaxHistory(maxHistory);
                if(cleanHistoryOnStart)
                {
                    addInfo("Cleaning on start up");
                    archiveRemover.clean(new Date(timeBasedFileNamingAndTriggeringPolicy.getCurrentTime()));
                }
            }
            super.start();
            return;
        } else
        {
            addWarn("The FileNamePattern option must be set before using TimeBasedRollingPolicy. ");
            addWarn("See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
            throw new IllegalStateException("The FileNamePattern option must be set before using TimeBasedRollingPolicy. See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
        }
    }

    public void stop()
    {
        if(isStarted())
        {
            waitForAsynchronousJobToStop();
            super.stop();
        }
    }

    public String toString()
    {
        return "c.q.l.core.rolling.TimeBasedRollingPolicy";
    }

    static final String FNP_NOT_SET = "The FileNamePattern option must be set before using TimeBasedRollingPolicy. ";
    static final int INFINITE_HISTORY;
    private ArchiveRemover archiveRemover;
    boolean cleanHistoryOnStart;
    private Compressor compressor;
    FileNamePattern fileNamePatternWCS;
    Future future;
    private int maxHistory;
    private RenameUtil renameUtil;
    TimeBasedFileNamingAndTriggeringPolicy timeBasedFileNamingAndTriggeringPolicy;
}
