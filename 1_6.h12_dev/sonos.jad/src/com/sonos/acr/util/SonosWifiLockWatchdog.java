// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;

// Referenced classes of package com.sonos.acr.util:
//            SLog, UserActivityMonitor, AdvancedWifiLock

public class SonosWifiLockWatchdog
{
    public static interface WifiLockReleaseListener
    {

        public abstract void onWifiReleased();
    }

    private class WifiLockReleaser
        implements Runnable
    {

        public void run()
        {
            if(wifiLockRelease != null)
            {
                SLog.i(SonosWifiLockWatchdog.LOG_TAG, "Releasing Wifi Lock");
                wifiLock.release();
                SonosApplication.getInstance().getUserActivityMonitor().onWifiLockStatusChanged(false);
                handler.removeCallbacks(wifiLockRelease);
                wifiLockRelease = null;
                if(listener != null)
                    listener.onWifiReleased();
            }
        }

        final SonosWifiLockWatchdog this$0;

        private WifiLockReleaser()
        {
            this$0 = SonosWifiLockWatchdog.this;
            super();
        }

    }

    private static class CompositeWifiLock
    {

        public void acquireHighPerf()
        {
            if(highPerfLock != null)
            {
                standardLock.release();
                highPerfLock.acquire();
            } else
            {
                standardLock.acquire();
            }
        }

        public void acquireStandard()
        {
            if(highPerfLock != null)
                highPerfLock.release();
            standardLock.acquire();
        }

        public boolean isHeld()
        {
            boolean flag;
            if(standardLock.isHeld() || highPerfLock != null && highPerfLock.isHeld())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void release()
        {
            standardLock.release();
            if(highPerfLock != null)
                highPerfLock.release();
        }

        private AdvancedWifiLock highPerfLock;
        private AdvancedWifiLock standardLock;

        CompositeWifiLock(Context context, String s)
        {
            standardLock = new AdvancedWifiLock(context, s, false);
            if(android.os.Build.VERSION.SDK_INT >= 12)
                highPerfLock = new AdvancedWifiLock(context, (new StringBuilder()).append(s).append("_high_perf").toString(), true);
        }
    }


    public SonosWifiLockWatchdog(Context context, String s, long l)
    {
        watchdogTimerPeriod = l;
        handler = SonosApplication.getInstance().getHandler();
        wifiLock = new CompositeWifiLock(context, s);
    }

    private void cancelWatchDog()
    {
        if(wifiLockRelease != null)
        {
            SLog.i(LOG_TAG, "Cancelling watchdog");
            handler.removeCallbacks(wifiLockRelease);
            wifiLockRelease = null;
        }
    }

    private void petWatchDog()
    {
        if(wifiLockRelease != null)
        {
            SLog.i(LOG_TAG, "Petting watchdog");
            handler.removeCallbacks(wifiLockRelease);
            handler.postDelayed(wifiLockRelease, watchdogTimerPeriod);
        }
    }

    private void startWatchDog()
    {
        cancelWatchDog();
        SLog.i(LOG_TAG, "Starting watchdog");
        wifiLockRelease = new WifiLockReleaser();
        handler.postDelayed(wifiLockRelease, watchdogTimerPeriod);
    }

    public boolean hasWifiLock()
    {
        return wifiLock.isHeld();
    }

    public void releaseWifiLock()
    {
        (new WifiLockReleaser()).run();
    }

    public void requestWifiLock(boolean flag, boolean flag1)
    {
        if(flag)
        {
            wifiLock.acquireHighPerf();
            cancelWatchDog();
        } else
        if(wifiLock.isHeld() && flag1)
        {
            petWatchDog();
        } else
        {
            wifiLock.acquireStandard();
            startWatchDog();
        }
    }

    public void setWifiReleaseListener(WifiLockReleaseListener wifilockreleaselistener)
    {
        listener = wifilockreleaselistener;
    }

    private static final String LOG_TAG = com/sonos/acr/util/SonosWifiLockWatchdog.getSimpleName();
    private Handler handler;
    private WifiLockReleaseListener listener;
    private long watchdogTimerPeriod;
    private CompositeWifiLock wifiLock;
    private WifiLockReleaser wifiLockRelease;




/*
    static WifiLockReleaser access$102(SonosWifiLockWatchdog sonoswifilockwatchdog, WifiLockReleaser wifilockreleaser)
    {
        sonoswifilockwatchdog.wifiLockRelease = wifilockreleaser;
        return wifilockreleaser;
    }

*/




}
