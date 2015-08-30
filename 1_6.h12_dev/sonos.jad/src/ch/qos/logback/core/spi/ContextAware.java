// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.status.Status;

public interface ContextAware
{

    public abstract void addError(String s);

    public abstract void addError(String s, Throwable throwable);

    public abstract void addInfo(String s);

    public abstract void addInfo(String s, Throwable throwable);

    public abstract void addStatus(Status status);

    public abstract void addWarn(String s);

    public abstract void addWarn(String s, Throwable throwable);

    public abstract Context getContext();

    public abstract void setContext(Context context);
}
