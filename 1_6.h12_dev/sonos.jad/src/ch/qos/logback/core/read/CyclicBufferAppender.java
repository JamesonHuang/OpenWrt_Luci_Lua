// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.read;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.helpers.CyclicBuffer;

public class CyclicBufferAppender extends AppenderBase
{

    public CyclicBufferAppender()
    {
        maxSize = 512;
    }

    protected void append(Object obj)
    {
        if(isStarted())
            cb.add(obj);
    }

    public Object get(int i)
    {
        Object obj;
        if(isStarted())
            obj = cb.get(i);
        else
            obj = null;
        return obj;
    }

    public int getLength()
    {
        int i;
        if(isStarted())
            i = cb.length();
        else
            i = 0;
        return i;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public void reset()
    {
        cb.clear();
    }

    public void setMaxSize(int i)
    {
        maxSize = i;
    }

    public void start()
    {
        cb = new CyclicBuffer(maxSize);
        super.start();
    }

    public void stop()
    {
        cb = null;
        super.stop();
    }

    CyclicBuffer cb;
    int maxSize;
}
