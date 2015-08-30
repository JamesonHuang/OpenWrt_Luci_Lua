// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.recovery;


public class RecoveryCoordinator
{

    public RecoveryCoordinator()
    {
        backOffCoefficient = 20L;
        currentTime = UNSET;
        next = System.currentTimeMillis() + getBackoffCoefficient();
    }

    private long getBackoffCoefficient()
    {
        long l = backOffCoefficient;
        if(backOffCoefficient < BACKOFF_COEFFICIENT_MAX)
            backOffCoefficient = 4L * backOffCoefficient;
        return l;
    }

    private long getCurrentTime()
    {
        long l;
        if(currentTime != UNSET)
            l = currentTime;
        else
            l = System.currentTimeMillis();
        return l;
    }

    public boolean isTooSoon()
    {
        long l = getCurrentTime();
        boolean flag;
        if(l > next)
        {
            next = l + getBackoffCoefficient();
            flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    void setCurrentTime(long l)
    {
        currentTime = l;
    }

    static long BACKOFF_COEFFICIENT_MAX = 0L;
    public static final long BACKOFF_COEFFICIENT_MIN = 20L;
    private static long UNSET = -1L;
    private long backOffCoefficient;
    private long currentTime;
    long next;

    static 
    {
        BACKOFF_COEFFICIENT_MAX = 0x50000L;
    }
}
