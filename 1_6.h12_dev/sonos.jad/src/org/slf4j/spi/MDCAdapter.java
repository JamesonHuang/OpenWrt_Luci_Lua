// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter
{

    public abstract void clear();

    public abstract String get(String s);

    public abstract Map getCopyOfContextMap();

    public abstract void put(String s, String s1);

    public abstract void remove(String s);

    public abstract void setContextMap(Map map);
}
