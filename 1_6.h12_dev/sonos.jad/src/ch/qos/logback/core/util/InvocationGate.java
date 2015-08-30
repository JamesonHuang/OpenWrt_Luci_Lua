// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


public class InvocationGate
{

    public InvocationGate()
    {
        mask = 15L;
        lastMaskCheck = System.currentTimeMillis();
        invocationCounter = 0L;
    }

    public boolean skipFurtherWork()
    {
        long l = invocationCounter;
        invocationCounter = 1L + l;
        boolean flag;
        if((l & mask) != mask)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void updateMaskIfNecessary(long l)
    {
        long l1;
        l1 = l - lastMaskCheck;
        lastMaskCheck = l;
        if(l1 >= 100L || mask >= 65535L) goto _L2; else goto _L1
_L1:
        mask = 1L | mask << 1;
_L4:
        return;
_L2:
        if(l1 > 800L)
            mask = mask >>> 2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int MAX_MASK = 65535;
    private static final long thresholdForMaskIncrease = 100L;
    private long invocationCounter;
    private volatile long lastMaskCheck;
    private volatile long mask;
    private final long thresholdForMaskDecrease = 800L;
}
