// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import android.os.SystemClock;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.media:
//            SonosRouteWakeLock

public class SonosRouteTrackBoundaryAlarm extends BroadcastReceiver
{

    public SonosRouteTrackBoundaryAlarm(Context context1)
    {
        alarmIntent = null;
        nextAlarmTimeMillis = -1L;
        isReceiverRegistered = false;
        context = context1;
        wakeLock = new SonosRouteWakeLock(SonosApplication.getInstance().getApplicationContext(), "PlaybackTransitionLock");
    }

    private void cancelAlarm(boolean flag)
    {
        if(alarmIntent != null)
        {
            if(flag)
                SLog.d("SonosRouteTrackBoundaryAlarm", "Cancelling alarm.");
            ((AlarmManager)context.getSystemService("alarm")).cancel(alarmIntent);
            alarmIntent = null;
        }
        nextAlarmTimeMillis = -1L;
    }

    private void setAlarm(long l, int i)
    {
        cancelAlarm(false);
        SLog.d("SonosRouteTrackBoundaryAlarm", (new StringBuilder()).append("Setting alarm in ").append(i).append(" secs from now.").toString());
        if(!isReceiverRegistered)
        {
            context.registerReceiver(this, new IntentFilter("ACTION_SONOS_ROUTE_TRANSITION"));
            isReceiverRegistered = true;
        }
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        Intent intent = new Intent("ACTION_SONOS_ROUTE_TRANSITION");
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmmanager.set(2, l, alarmIntent);
        nextAlarmTimeMillis = l;
    }

    public void cancelAlarm()
    {
        cancelAlarm(true);
    }

    public void disable()
    {
        cancelAlarm();
        if(isReceiverRegistered)
        {
            try
            {
                context.unregisterReceiver(this);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                if(illegalargumentexception.getMessage().contains("Receiver not registered"))
                    SLog.d("SonosRouteTrackBoundaryAlarm", illegalargumentexception.getMessage());
                else
                    throw illegalargumentexception;
            }
            isReceiverRegistered = false;
        }
    }

    public void onReceive(Context context1, Intent intent)
    {
        if(intent.getAction().equals("ACTION_SONOS_ROUTE_TRANSITION"))
            wakeLock.acquire(1000 * TRACK_BOUNDARY_WAKE_DURATION_IN_SECS);
    }

    public void setNextTrackBoundary(int i)
    {
        int j = i - SECS_TO_WAKE_BEFORE_TRACK_BOUNDARY;
        if(j < 0)
            j = 0;
        long l = SystemClock.elapsedRealtime() + (long)(j * 1000);
        if(l / 1000L != nextAlarmTimeMillis / 1000L)
            setAlarm(l, j);
    }

    private static final String ACTION_NAME = "ACTION_SONOS_ROUTE_TRANSITION";
    private static final String LOG_TAG = "SonosRouteTrackBoundaryAlarm";
    private static int SECS_TO_WAKE_BEFORE_TRACK_BOUNDARY = 5;
    private static int TRACK_BOUNDARY_WAKE_DURATION_IN_SECS = 10;
    private PendingIntent alarmIntent;
    private Context context;
    private boolean isReceiverRegistered;
    private long nextAlarmTimeMillis;
    private SonosRouteWakeLock wakeLock;

}
