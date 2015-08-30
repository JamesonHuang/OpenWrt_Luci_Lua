// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Handler;

public abstract class PeriodicExecutor
{
    private class PeriodicRunnable
        implements Runnable
    {

        public void run()
        {
            if(isRunning())
            {
                mState = PeriodicState.EXECUTING;
                execute();
                if(isRunning() && myRunnable == this)
                {
                    long l = getWaitTime();
                    if(l == PeriodicExecutor.DONE)
                    {
                        stop();
                    } else
                    {
                        mState = PeriodicState.WAITING;
                        mHandler.postDelayed(myRunnable, l);
                    }
                }
            }
        }

        final PeriodicExecutor this$0;

        private PeriodicRunnable()
        {
            this$0 = PeriodicExecutor.this;
            super();
        }

    }

    static final class PeriodicState extends Enum
    {

        public static PeriodicState valueOf(String s)
        {
            return (PeriodicState)Enum.valueOf(com/sonos/acr/util/PeriodicExecutor$PeriodicState, s);
        }

        public static PeriodicState[] values()
        {
            return (PeriodicState[])$VALUES.clone();
        }

        private static final PeriodicState $VALUES[];
        public static final PeriodicState EXECUTING;
        public static final PeriodicState STARTING;
        public static final PeriodicState STOPPED;
        public static final PeriodicState WAITING;

        static 
        {
            STOPPED = new PeriodicState("STOPPED", 0);
            STARTING = new PeriodicState("STARTING", 1);
            WAITING = new PeriodicState("WAITING", 2);
            EXECUTING = new PeriodicState("EXECUTING", 3);
            PeriodicState aperiodicstate[] = new PeriodicState[4];
            aperiodicstate[0] = STOPPED;
            aperiodicstate[1] = STARTING;
            aperiodicstate[2] = WAITING;
            aperiodicstate[3] = EXECUTING;
            $VALUES = aperiodicstate;
        }

        private PeriodicState(String s, int i)
        {
            super(s, i);
        }
    }


    public PeriodicExecutor(long l)
    {
        this(l, -1L, ((Handler) (null)));
    }

    public PeriodicExecutor(long l, long l1)
    {
        this(l, l1, l, null);
    }

    public PeriodicExecutor(long l, long l1, long l2)
    {
        this(l, l1, l2, null);
    }

    public PeriodicExecutor(long l, long l1, long l2, Handler handler)
    {
        mState = PeriodicState.STOPPED;
        name = "Unknown";
        mPeriod = l;
        mStartDelay = l2;
        mDuration = l1;
        if(handler != null)
            mHandler = handler;
        else
            mHandler = new Handler();
    }

    public PeriodicExecutor(long l, long l1, Handler handler)
    {
        this(l, l1, l, handler);
    }

    public PeriodicExecutor(long l, Handler handler)
    {
        this(l, -1L, handler);
    }

    public PeriodicExecutor(String s, long l)
    {
        this(l, -1L, ((Handler) (null)));
        name = s;
    }

    private long getWaitTime()
    {
        long l = System.currentTimeMillis();
        if(startTime == -1L)
            throw new RuntimeException("Periodic Executor Not Started!");
        long l1;
        if(mDuration != -1L)
        {
            if(startTime + mDuration <= l)
                l1 = DONE;
            else
                l1 = mPeriod;
        } else
        {
            l1 = Math.min((l - startTime) + mPeriod, mPeriod);
        }
        return l1;
    }

    public abstract void execute();

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getTimeRemaining()
    {
        if(!isRunning())
            throw new RuntimeException("Periodic Executor Not Started!");
        if(mDuration == -1L)
            throw new RuntimeException("No duration set.");
        else
            return (startTime + mDuration) - System.currentTimeMillis();
    }

    public long getTimeSinceStart()
    {
        if(!isRunning())
            throw new RuntimeException("Periodic Executor Not Started!");
        else
            return System.currentTimeMillis() - startTime;
    }

    public long getmDuration()
    {
        return mDuration;
    }

    public boolean isRunning()
    {
        boolean flag;
        if(mState != PeriodicState.STOPPED)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStarting()
    {
        boolean flag;
        if(mState == PeriodicState.STARTING)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onStart()
    {
    }

    public void onStop()
    {
    }

    public void setStartDelay(long l)
    {
        mStartDelay = l;
    }

    public final void start()
    {
        if(!isRunning())
        {
            startTime = System.currentTimeMillis();
            mState = PeriodicState.STARTING;
            onStart();
            myRunnable = new PeriodicRunnable();
            mHandler.postDelayed(myRunnable, getStartDelay());
        }
    }

    public final void stop()
    {
        if(isRunning())
        {
            mHandler.removeCallbacks(myRunnable);
            myRunnable = null;
            mState = PeriodicState.STOPPED;
            onStop();
        }
    }

    private static long DONE = 0L;
    public static final long UNDEFINED = -1L;
    private final long mDuration;
    private final Handler mHandler;
    private long mPeriod;
    private long mStartDelay;
    private PeriodicState mState;
    private PeriodicRunnable myRunnable;
    private String name;
    private long startTime;

    static 
    {
        DONE = 0L;
    }


/*
    static PeriodicState access$002(PeriodicExecutor periodicexecutor, PeriodicState periodicstate)
    {
        periodicexecutor.mState = periodicstate;
        return periodicstate;
    }

*/




}
