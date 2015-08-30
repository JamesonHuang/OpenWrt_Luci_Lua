// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.helpers;

import java.util.ArrayList;
import java.util.List;

public class CyclicBuffer
{

    public CyclicBuffer(int i)
        throws IllegalArgumentException
    {
        if(i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The maxSize argument (").append(i).append(") is not a positive integer.").toString());
        } else
        {
            init(i);
            return;
        }
    }

    public CyclicBuffer(CyclicBuffer cyclicbuffer)
    {
        maxSize = cyclicbuffer.maxSize;
        ea = (Object[])new Object[maxSize];
        System.arraycopy(((Object) (cyclicbuffer.ea)), 0, ((Object) (ea)), 0, maxSize);
        last = cyclicbuffer.last;
        first = cyclicbuffer.first;
        numElems = cyclicbuffer.numElems;
    }

    private void init(int i)
    {
        maxSize = i;
        ea = (Object[])new Object[i];
        first = 0;
        last = 0;
        numElems = 0;
    }

    public void add(Object obj)
    {
        ea[last] = obj;
        int i = 1 + last;
        last = i;
        if(i == maxSize)
            last = 0;
        if(numElems >= maxSize) goto _L2; else goto _L1
_L1:
        numElems = 1 + numElems;
_L4:
        return;
_L2:
        int j = 1 + first;
        first = j;
        if(j == maxSize)
            first = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List asList()
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < length(); i++)
            arraylist.add(get(i));

        return arraylist;
    }

    public void clear()
    {
        init(maxSize);
    }

    public Object get()
    {
        Object obj;
        if(numElems > 0)
        {
            numElems = -1 + numElems;
            obj = ea[first];
            ea[first] = null;
            int i = 1 + first;
            first = i;
            if(i == maxSize)
                first = 0;
        } else
        {
            obj = null;
        }
        return obj;
    }

    public Object get(int i)
    {
        Object obj;
        if(i < 0 || i >= numElems)
            obj = null;
        else
            obj = ea[(i + first) % maxSize];
        return obj;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public int length()
    {
        return numElems;
    }

    public void resize(int i)
    {
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Negative array size [").append(i).append("] not allowed.").toString());
        if(i != numElems)
        {
            Object aobj[] = (Object[])new Object[i];
            int j;
            int k;
            if(i < numElems)
                j = i;
            else
                j = numElems;
            for(k = 0; k < j; k++)
            {
                aobj[k] = ea[first];
                ea[first] = null;
                int l = 1 + first;
                first = l;
                if(l == numElems)
                    first = 0;
            }

            ea = aobj;
            first = 0;
            numElems = j;
            maxSize = i;
            if(j == i)
                last = 0;
            else
                last = j;
        }
    }

    Object ea[];
    int first;
    int last;
    int maxSize;
    int numElems;
}
