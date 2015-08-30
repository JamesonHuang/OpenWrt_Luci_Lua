// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.PowerManager;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class AdvancedWifiLock
{

    public AdvancedWifiLock(Context context1, String s, boolean flag)
    {
        isHighPerf = false;
        locked = false;
        context = context1;
        tag = s;
        isHighPerf = flag;
        LOG_TAG = (new StringBuilder()).append(getClass().getSimpleName()).append(":").append(s).toString();
    }

    private void createLock(Context context1, String s)
    {
        PowerManager powermanager = (PowerManager)context1.getSystemService("power");
        WifiManager wifimanager;
        if(powermanager != null)
            wakeLock = powermanager.newWakeLock(1, s);
        else
            SLog.e(LOG_TAG, "Unable to create a Partial Wake Lock");
        wifimanager = (WifiManager)context1.getSystemService("wifi");
        if(wifimanager != null)
        {
            if(isHighPerf && android.os.Build.VERSION.SDK_INT >= 12)
                wifiLock = wifimanager.createWifiLock(3, s);
            else
                wifiLock = wifimanager.createWifiLock(1, s);
        } else
        {
            SLog.e(LOG_TAG, "Unable to grab a Wifi Lock");
        }
    }

    public void acquire()
    {
        if(!locked)
        {
            SLog.i(LOG_TAG, "Acquiring WifiLock");
            locked = true;
            createLock(context, tag);
            if(wifiLock != null)
                wifiLock.acquire();
            if(wakeLock != null)
                wakeLock.acquire();
        }
    }

    public boolean isHeld()
    {
        return locked;
    }

    public void release()
    {
        if(locked)
        {
            SLog.i(LOG_TAG, "Releasing Wifi Lock");
            if(wifiLock != null)
            {
                wifiLock.release();
                wifiLock = null;
            }
            if(wakeLock != null)
            {
                wakeLock.release();
                wakeLock = null;
            }
            locked = false;
        }
    }

    private final String LOG_TAG;
    Context context;
    boolean isHighPerf;
    boolean locked;
    String tag;
    android.os.PowerManager.WakeLock wakeLock;
    android.net.wifi.WifiManager.WifiLock wifiLock;
}
