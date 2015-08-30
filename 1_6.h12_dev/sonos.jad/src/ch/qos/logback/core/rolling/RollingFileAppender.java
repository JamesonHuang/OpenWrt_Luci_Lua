// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import java.io.File;
import java.io.IOException;

// Referenced classes of package ch.qos.logback.core.rolling:
//            RollingPolicyBase, RollingPolicy, RolloverFailure, TriggeringPolicy

public class RollingFileAppender extends FileAppender
{

    public RollingFileAppender()
    {
    }

    private boolean fileAndPatternCollide()
    {
        if(!(triggeringPolicy instanceof RollingPolicyBase)) goto _L2; else goto _L1
_L1:
        FileNamePattern filenamepattern = ((RollingPolicyBase)triggeringPolicy).fileNamePattern;
        if(filenamepattern == null || fileName == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        String s = filenamepattern.toRegex();
        flag = fileName.matches(s);
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getFile()
    {
        return rollingPolicy.getActiveFileName();
    }

    public RollingPolicy getRollingPolicy()
    {
        return rollingPolicy;
    }

    public TriggeringPolicy getTriggeringPolicy()
    {
        return triggeringPolicy;
    }

    public void rollover()
    {
        ch.qos.logback.core.spi.LogbackLock logbacklock = lock;
        logbacklock;
        JVM INSTR monitorenter ;
        closeOutputStream();
        String s;
        try
        {
            rollingPolicy.rollover();
        }
        catch(RolloverFailure rolloverfailure)
        {
            addWarn("RolloverFailure occurred. Deferring rollover");
            append = true;
        }
        s = rollingPolicy.getActiveFileName();
        currentlyActiveFile = new File(s);
        openFile(s);
_L1:
        logbacklock;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        addError((new StringBuilder()).append("openFile(").append(s).append(") failed").toString(), ioexception);
          goto _L1
    }

    public void setFile(String s)
    {
        if(s != null && (triggeringPolicy != null || rollingPolicy != null))
        {
            addError("File property must be set before any triggeringPolicy or rollingPolicy properties");
            addError("Visit http://logback.qos.ch/codes.html#rfa_file_after for more information");
        }
        super.setFile(s);
    }

    public void setRollingPolicy(RollingPolicy rollingpolicy)
    {
        rollingPolicy = rollingpolicy;
        if(rollingPolicy instanceof TriggeringPolicy)
            triggeringPolicy = (TriggeringPolicy)rollingpolicy;
    }

    public void setTriggeringPolicy(TriggeringPolicy triggeringpolicy)
    {
        triggeringPolicy = triggeringpolicy;
        if(triggeringpolicy instanceof RollingPolicy)
            rollingPolicy = (RollingPolicy)triggeringpolicy;
    }

    public void start()
    {
        if(triggeringPolicy != null) goto _L2; else goto _L1
_L1:
        addWarn((new StringBuilder()).append("No TriggeringPolicy was set for the RollingFileAppender named ").append(getName()).toString());
        addWarn((new StringBuilder()).append("For more information, please visit ").append(RFA_NO_TP_URL).toString());
_L4:
        return;
_L2:
        if(!append)
        {
            addWarn("Append mode is mandatory for RollingFileAppender");
            append = true;
        }
        if(rollingPolicy == null)
        {
            addError((new StringBuilder()).append("No RollingPolicy was set for the RollingFileAppender named ").append(getName()).toString());
            addError((new StringBuilder()).append("For more information, please visit ").append(RFA_NO_RP_URL).toString());
            continue; /* Loop/switch isn't completed */
        }
        if(fileAndPatternCollide())
        {
            addError("File property collides with fileNamePattern. Aborting.");
            addError((new StringBuilder()).append("For more information, please visit ").append(COLLISION_URL).toString());
            continue; /* Loop/switch isn't completed */
        }
        if(isPrudent())
        {
            if(rawFileProperty() != null)
            {
                addWarn("Setting \"File\" property to null on account of prudent mode");
                setFile(null);
            }
            if(rollingPolicy.getCompressionMode() != CompressionMode.NONE)
            {
                addError("Compression is not supported in prudent mode. Aborting");
                continue; /* Loop/switch isn't completed */
            }
        }
        currentlyActiveFile = new File(getFile());
        addInfo((new StringBuilder()).append("Active log file name: ").append(getFile()).toString());
        super.start();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void stop()
    {
        if(rollingPolicy != null)
            rollingPolicy.stop();
        if(triggeringPolicy != null)
            triggeringPolicy.stop();
        super.stop();
    }

    protected void subAppend(Object obj)
    {
        synchronized(triggeringPolicy)
        {
            if(triggeringPolicy.isTriggeringEvent(currentlyActiveFile, obj))
                rollover();
        }
        super.subAppend(obj);
        return;
        exception;
        triggeringpolicy;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static String COLLISION_URL = "http://logback.qos.ch/codes.html#rfa_collision";
    private static String RFA_NO_RP_URL = "http://logback.qos.ch/codes.html#rfa_no_rp";
    private static String RFA_NO_TP_URL = "http://logback.qos.ch/codes.html#rfa_no_tp";
    File currentlyActiveFile;
    RollingPolicy rollingPolicy;
    TriggeringPolicy triggeringPolicy;

}
