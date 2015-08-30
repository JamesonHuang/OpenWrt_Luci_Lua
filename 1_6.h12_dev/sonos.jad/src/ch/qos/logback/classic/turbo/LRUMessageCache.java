// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import java.util.LinkedHashMap;

class LRUMessageCache extends LinkedHashMap
{

    LRUMessageCache(int i)
    {
        super((int)(1.333333F * (float)i), 0.75F, true);
        if(i < 1)
        {
            throw new IllegalArgumentException("Cache size cannot be smaller than 1");
        } else
        {
            cacheSize = i;
            return;
        }
    }

    /**
     * @deprecated Method clear is deprecated
     */

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        super.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    int getMessageCountAndThenIncrement(String s)
    {
        int i = 0;
        if(s != null) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        this;
        JVM INSTR monitorenter ;
        Integer integer;
        Integer integer1;
        integer = (Integer)super.get(s);
        if(integer != null)
            break MISSING_BLOCK_LABEL_50;
        integer1 = Integer.valueOf(0);
_L3:
        super.put(s, integer1);
        this;
        JVM INSTR monitorexit ;
        i = integer1.intValue();
        continue; /* Loop/switch isn't completed */
        integer1 = Integer.valueOf(1 + integer.intValue());
          goto _L3
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected boolean removeEldestEntry(java.util.Map.Entry entry)
    {
        boolean flag;
        if(size() > cacheSize)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final long serialVersionUID = 1L;
    final int cacheSize;
}
