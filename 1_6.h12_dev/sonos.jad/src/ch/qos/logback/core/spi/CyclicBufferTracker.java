// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.helpers.CyclicBuffer;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.spi:
//            AbstractComponentTracker

public class CyclicBufferTracker extends AbstractComponentTracker
{

    public CyclicBufferTracker()
    {
        bufferSize = 256;
        setMaxComponents(64);
    }

    protected CyclicBuffer buildComponent(String s)
    {
        return new CyclicBuffer(bufferSize);
    }

    protected volatile Object buildComponent(String s)
    {
        return buildComponent(s);
    }

    public int getBufferSize()
    {
        return bufferSize;
    }

    protected boolean isComponentStale(CyclicBuffer cyclicbuffer)
    {
        return false;
    }

    protected volatile boolean isComponentStale(Object obj)
    {
        return isComponentStale((CyclicBuffer)obj);
    }

    List lingererKeysAsOrderedList()
    {
        return new ArrayList(lingerersMap.keySet());
    }

    List liveKeysAsOrderedList()
    {
        return new ArrayList(liveMap.keySet());
    }

    protected void processPriorToRemoval(CyclicBuffer cyclicbuffer)
    {
        cyclicbuffer.clear();
    }

    protected volatile void processPriorToRemoval(Object obj)
    {
        processPriorToRemoval((CyclicBuffer)obj);
    }

    public void setBufferSize(int i)
    {
        bufferSize = i;
    }

    static final int DEFAULT_BUFFER_SIZE = 256;
    static final int DEFAULT_NUMBER_OF_BUFFERS = 64;
    int bufferSize;
}
