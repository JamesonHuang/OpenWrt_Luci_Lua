// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.net.wifi.WifiManager;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.network.SonosNetworkManager;

public class NetworkStateMonitor
{

    public NetworkStateMonitor(SonosActivity sonosactivity)
    {
        activity = sonosactivity;
    }

    public boolean isInternetAvailable()
    {
        boolean flag = true;
        if(!activity.getSonosNetworkManager().isRunning() || activity.getSonosNetworkManager().hasNoInternetConnection(flag))
            flag = false;
        return flag;
    }

    public boolean isWifiConnected()
    {
        boolean flag;
        if(activity.getSonosNetworkManager().isRunning() && activity.getSonosNetworkManager().hasWifiConnection())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWifiDisabled()
    {
        WifiManager wifimanager = (WifiManager)activity.getSystemService("wifi");
        boolean flag;
        if(!wifimanager.isWifiEnabled() || wifimanager.getWifiState() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWifiEnabled()
    {
        return ((WifiManager)activity.getSystemService("wifi")).isWifiEnabled();
    }

    SonosActivity activity;
}
