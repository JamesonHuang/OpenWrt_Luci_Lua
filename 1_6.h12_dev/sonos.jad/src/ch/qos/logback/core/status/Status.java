// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import java.util.Iterator;

public interface Status
{

    public abstract void add(Status status);

    public abstract Long getDate();

    public abstract int getEffectiveLevel();

    public abstract int getLevel();

    public abstract String getMessage();

    public abstract Object getOrigin();

    public abstract Throwable getThrowable();

    public abstract boolean hasChildren();

    public abstract Iterator iterator();

    public abstract boolean remove(Status status);

    public static final int ERROR = 2;
    public static final int INFO = 0;
    public static final int WARN = 1;
}
