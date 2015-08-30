// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import java.util.HashMap;

public class CopyOnInheritThreadLocal extends InheritableThreadLocal
{

    public CopyOnInheritThreadLocal()
    {
    }

    protected volatile Object childValue(Object obj)
    {
        return childValue((HashMap)obj);
    }

    protected HashMap childValue(HashMap hashmap)
    {
        HashMap hashmap1;
        if(hashmap == null)
            hashmap1 = null;
        else
            hashmap1 = new HashMap(hashmap);
        return hashmap1;
    }
}
