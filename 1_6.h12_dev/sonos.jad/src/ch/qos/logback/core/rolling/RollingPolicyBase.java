// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.core.rolling:
//            RollingPolicy

public abstract class RollingPolicyBase extends ContextAwareBase
    implements RollingPolicy
{

    public RollingPolicyBase()
    {
        compressionMode = CompressionMode.NONE;
    }

    protected void determineCompressionMode()
    {
        if(fileNamePatternStr.endsWith(".gz"))
        {
            addInfo("Will use gz compression");
            compressionMode = CompressionMode.GZ;
        } else
        if(fileNamePatternStr.endsWith(".zip"))
        {
            addInfo("Will use zip compression");
            compressionMode = CompressionMode.ZIP;
        } else
        {
            addInfo("No compression will be used");
            compressionMode = CompressionMode.NONE;
        }
    }

    public CompressionMode getCompressionMode()
    {
        return compressionMode;
    }

    public String getFileNamePattern()
    {
        return fileNamePatternStr;
    }

    public String getParentsRawFileProperty()
    {
        return parent.rawFileProperty();
    }

    public boolean isParentPrudent()
    {
        return parent.isPrudent();
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setFileNamePattern(String s)
    {
        fileNamePatternStr = s;
    }

    public void setParent(FileAppender fileappender)
    {
        parent = fileappender;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    protected CompressionMode compressionMode;
    FileNamePattern fileNamePattern;
    protected String fileNamePatternStr;
    private FileAppender parent;
    private boolean started;
    FileNamePattern zipEntryFileNamePattern;
}
