// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.LoggerContext;
import java.io.Serializable;

// Referenced classes of package ch.qos.logback.classic.spi:
//            LoggerContextVO

public class LoggerRemoteView
    implements Serializable
{

    public LoggerRemoteView(String s, LoggerContext loggercontext)
    {
        name = s;
        if(!$assertionsDisabled && loggercontext.getLoggerContextRemoteView() == null)
        {
            throw new AssertionError();
        } else
        {
            loggerContextView = loggercontext.getLoggerContextRemoteView();
            return;
        }
    }

    public LoggerContextVO getLoggerContextView()
    {
        return loggerContextView;
    }

    public String getName()
    {
        return name;
    }

    static final boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 0x45c7d6c89d9f4ae0L;
    final LoggerContextVO loggerContextView;
    final String name;

    static 
    {
        boolean flag;
        if(!ch/qos/logback/classic/spi/LoggerRemoteView.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
