// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import java.util.List;

// Referenced classes of package ch.qos.logback.core.status:
//            Status, StatusListener

public interface StatusManager
{

    public abstract void add(Status status);

    public abstract void add(StatusListener statuslistener);

    public abstract boolean addUniquely(StatusListener statuslistener, Object obj);

    public abstract void clear();

    public abstract List getCopyOfStatusList();

    public abstract List getCopyOfStatusListenerList();

    public abstract int getCount();

    public abstract void remove(StatusListener statuslistener);
}
