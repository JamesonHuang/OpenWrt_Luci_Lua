// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.Context;
import android.os.PowerManager;
import com.sonos.acr.util.SLog;

public class SonosRouteWakeLock
{

    public SonosRouteWakeLock(Context context1, String s)
    {
        context = context1;
        tag = s;
        LOG_TAG = (new StringBuilder()).append(getClass().getSimpleName()).append(":").append(s).toString();
    }

    private void createLock(Context context1, String s)
    {
        PowerManager powermanager = (PowerManager)context1.getSystemService("power");
        if(powermanager != null)
            wakeLock = powermanager.newWakeLock(1, s);
        else
            SLog.e(LOG_TAG, "Unable to create a Partial Wake Lock");
    }

    public void acquire(long l)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Acquiring Partial Wake Lock for ").append(l).append(" msecs").toString());
        createLock(context, tag);
        if(wakeLock != null)
            wakeLock.acquire(l);
    }

    private final String LOG_TAG;
    Context context;
    String tag;
    android.os.PowerManager.WakeLock wakeLock;
}
