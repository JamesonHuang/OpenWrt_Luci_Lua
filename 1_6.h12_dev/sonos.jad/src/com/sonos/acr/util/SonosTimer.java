// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Handler;

public class SonosTimer
    implements Runnable
{

    public SonosTimer()
    {
        running = false;
    }

    public final void cancel()
    {
        if(running)
        {
            handler.removeCallbacks(this);
            running = false;
            onCancelled();
        }
    }

    public boolean isRunning()
    {
        return running;
    }

    public void onCancelled()
    {
    }

    public void onStart()
    {
    }

    public void onTimerFired()
    {
    }

    public final void run()
    {
        running = false;
        onTimerFired();
    }

    public final void start(long l)
    {
        if(!running)
            onStart();
        handler.removeCallbacks(this);
        handler.postDelayed(this, l);
        running = true;
    }

    final Handler handler = new Handler();
    private boolean running;
}
