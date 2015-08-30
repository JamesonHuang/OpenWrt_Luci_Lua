// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.sclib.SCIEnumerable;
import com.sonos.sclib.SCIEnumerator;
import java.util.Iterator;

public class EnumeratorAdapter
    implements Iterator, Iterable
{

    public EnumeratorAdapter(SCIEnumerable scienumerable, String s)
    {
        this(scienumerable.getEnumerator(), s);
    }

    public EnumeratorAdapter(SCIEnumerator scienumerator, String s)
    {
        mTypeName = null;
        index = 0;
        count = 0;
        mEnumerator = scienumerator;
        if(mEnumerator != null)
        {
            count = mEnumerator.count();
            mEnumerator.reset();
        }
        mTypeName = s;
    }

    protected void findNextItem()
    {
        mNextItem = null;
        if(index < count) goto _L2; else goto _L1
_L1:
        return;
_L2:
        com.sonos.sclib.SCIObj sciobj;
        do
        {
            if(!mEnumerator.moveNext())
                continue; /* Loop/switch isn't completed */
            index = 1 + index;
            sciobj = mEnumerator.getCurrent(mTypeName);
        } while(sciobj == null);
        mNextItem = sciobj;
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected Object getNextItem()
    {
        if(mNextItem == null)
            findNextItem();
        return mNextItem;
    }

    public boolean hasNext()
    {
        boolean flag = false;
        if(mEnumerator != null && getNextItem() != null)
            flag = true;
        return flag;
    }

    public Iterator iterator()
    {
        return this;
    }

    public Object next()
    {
        Object obj = getNextItem();
        mNextItem = null;
        return obj;
    }

    public void remove()
    {
    }

    private int count;
    private int index;
    private SCIEnumerator mEnumerator;
    private Object mNextItem;
    private String mTypeName;
}
