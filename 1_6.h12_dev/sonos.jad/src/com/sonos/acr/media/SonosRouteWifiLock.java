// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.sonos.acr.util.SLog;

public class SonosRouteWifiLock
{

    public SonosRouteWifiLock(Context context1, String s)
    {
        locked = false;
        context = context1;
        tag = s;
        LOG_TAG = (new StringBuilder()).append(getClass().getSimpleName()).append(":").append(s).toString();
    }

    private void createLock(Context context1, String s)
    {
        WifiManager wifimanager = (WifiManager)context1.getSystemService("wifi");
        if(wifimanager != null)
            wifiLock = wifimanager.createWifiLock(1, s);
        else
            SLog.e(LOG_TAG, "Unable to grab a Wifi Lock");
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
            locked = false;
        }
    }

    private final String LOG_TAG;
    Context context;
    boolean locked;
    String tag;
    android.net.wifi.WifiManager.WifiLock wifiLock;
}
