// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.joran.spi.ConsoleTarget;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.WarnStatus;
import java.util.Arrays;

// Referenced classes of package ch.qos.logback.core:
//            OutputStreamAppender

public class ConsoleAppender extends OutputStreamAppender
{

    public ConsoleAppender()
    {
        target = ConsoleTarget.SystemOut;
    }

    private void targetWarn(String s)
    {
        WarnStatus warnstatus = new WarnStatus((new StringBuilder()).append("[").append(s).append("] should be one of ").append(Arrays.toString(ConsoleTarget.values())).toString(), this);
        warnstatus.add(new WarnStatus("Using previously set target, System.out by default.", this));
        addStatus(warnstatus);
    }

    public String getTarget()
    {
        return target.getName();
    }

    public void setTarget(String s)
    {
        ConsoleTarget consoletarget = ConsoleTarget.findByName(s.trim());
        if(consoletarget == null)
            targetWarn(s);
        else
            target = consoletarget;
    }

    public void start()
    {
        setOutputStream(target.getStream());
        super.start();
    }

    protected ConsoleTarget target;
}
