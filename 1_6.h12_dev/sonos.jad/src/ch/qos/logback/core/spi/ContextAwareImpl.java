// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.status.*;
import java.io.PrintStream;

// Referenced classes of package ch.qos.logback.core.spi:
//            ContextAware

public class ContextAwareImpl
    implements ContextAware
{

    public ContextAwareImpl(Context context1, Object obj)
    {
        noContextWarning = 0;
        context = context1;
        origin = obj;
    }

    public void addError(String s)
    {
        addStatus(new ErrorStatus(s, getOrigin()));
    }

    public void addError(String s, Throwable throwable)
    {
        addStatus(new ErrorStatus(s, getOrigin(), throwable));
    }

    public void addInfo(String s)
    {
        addStatus(new InfoStatus(s, getOrigin()));
    }

    public void addInfo(String s, Throwable throwable)
    {
        addStatus(new InfoStatus(s, getOrigin(), throwable));
    }

    public void addStatus(Status status)
    {
        if(context != null) goto _L2; else goto _L1
_L1:
        int i = noContextWarning;
        noContextWarning = i + 1;
        if(i == 0)
            System.out.println((new StringBuilder()).append("LOGBACK: No context given for ").append(this).toString());
_L4:
        return;
_L2:
        StatusManager statusmanager = context.getStatusManager();
        if(statusmanager != null)
            statusmanager.add(status);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addWarn(String s)
    {
        addStatus(new WarnStatus(s, getOrigin()));
    }

    public void addWarn(String s, Throwable throwable)
    {
        addStatus(new WarnStatus(s, getOrigin(), throwable));
    }

    public Context getContext()
    {
        return context;
    }

    protected Object getOrigin()
    {
        return origin;
    }

    public StatusManager getStatusManager()
    {
        StatusManager statusmanager;
        if(context == null)
            statusmanager = null;
        else
            statusmanager = context.getStatusManager();
        return statusmanager;
    }

    public void setContext(Context context1)
    {
        if(context == null)
            context = context1;
        else
        if(context != context1)
            throw new IllegalStateException("Context has been already set");
    }

    protected Context context;
    private int noContextWarning;
    final Object origin;
}
