// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.*;
import ch.qos.logback.core.status.Status;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.pattern:
//            FormattingConverter

public abstract class DynamicConverter extends FormattingConverter
    implements LifeCycle, ContextAware
{

    public DynamicConverter()
    {
        cab = new ContextAwareBase(this);
        started = false;
    }

    public void addError(String s)
    {
        cab.addError(s);
    }

    public void addError(String s, Throwable throwable)
    {
        cab.addError(s, throwable);
    }

    public void addInfo(String s)
    {
        cab.addInfo(s);
    }

    public void addInfo(String s, Throwable throwable)
    {
        cab.addInfo(s, throwable);
    }

    public void addStatus(Status status)
    {
        cab.addStatus(status);
    }

    public void addWarn(String s)
    {
        cab.addWarn(s);
    }

    public void addWarn(String s, Throwable throwable)
    {
        cab.addWarn(s, throwable);
    }

    public Context getContext()
    {
        return cab.getContext();
    }

    public String getFirstOption()
    {
        String s;
        if(optionList == null || optionList.size() == 0)
            s = null;
        else
            s = (String)optionList.get(0);
        return s;
    }

    protected List getOptionList()
    {
        return optionList;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setContext(Context context)
    {
        cab.setContext(context);
    }

    public void setOptionList(List list)
    {
        optionList = list;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    ContextAwareBase cab;
    private List optionList;
    protected boolean started;
}
