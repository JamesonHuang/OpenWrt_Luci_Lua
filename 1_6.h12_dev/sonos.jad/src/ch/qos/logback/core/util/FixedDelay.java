// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


// Referenced classes of package ch.qos.logback.core.util:
//            DelayStrategy

public class FixedDelay
    implements DelayStrategy
{

    public FixedDelay(int i)
    {
        this(i, i);
    }

    public FixedDelay(long l, long l1)
    {
        new String();
        nextDelay = l;
        subsequentDelay = l1;
    }

    public long nextDelay()
    {
        long l = nextDelay;
        nextDelay = subsequentDelay;
        return l;
    }

    private long nextDelay;
    private final long subsequentDelay;
}
