// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.support.v4.util.LruCache;

// Referenced classes of package com.sonos.acr.util:
//            WeakMap, SLog

public class LruWeakCache
{

    public LruWeakCache(int i)
    {
        lruReferences = new LruCache(i);
    }

    public void clear()
    {
        lruReferences.evictAll();
        weakReferences.clear();
    }

    public Object get(Object obj)
    {
        Object obj1 = lruReferences.get(obj);
        if(obj1 == null)
        {
            obj1 = weakReferences.get(obj);
            if(obj1 != null)
            {
                lruReferences.put(obj, obj1);
                SLog.e(LOG_TAG, (new StringBuilder()).append("Found A Weak Reference for: ").append(obj).toString());
            }
        }
        return obj1;
    }

    public void put(Object obj, Object obj1)
    {
        lruReferences.put(obj, obj1);
        weakReferences.put(obj, obj1);
    }

    private static final String LOG_TAG = com/sonos/acr/util/LruWeakCache.getSimpleName();
    private final LruCache lruReferences;
    private final WeakMap weakReferences = new WeakMap();

}
