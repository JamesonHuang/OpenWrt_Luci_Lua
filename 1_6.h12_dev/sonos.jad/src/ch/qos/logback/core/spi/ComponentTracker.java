// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import java.util.Collection;
import java.util.Set;

public interface ComponentTracker
{

    public abstract Collection allComponents();

    public abstract Set allKeys();

    public abstract void endOfLife(String s);

    public abstract Object find(String s);

    public abstract int getComponentCount();

    public abstract Object getOrCreate(String s, long l);

    public abstract void removeStaleComponents(long l);

    public static final int DEFAULT_MAX_COMPONENTS = 0x7fffffff;
    public static final int DEFAULT_TIMEOUT = 0x1b7740;
}
