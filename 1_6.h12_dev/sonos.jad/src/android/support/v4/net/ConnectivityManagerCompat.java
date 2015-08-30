// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// Referenced classes of package android.support.v4.net:
//            ConnectivityManagerCompatJellyBean, ConnectivityManagerCompatHoneycombMR2, ConnectivityManagerCompatGingerbread

public class ConnectivityManagerCompat
{
    static class JellyBeanConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivitymanager);
        }

        JellyBeanConnectivityManagerCompatImpl()
        {
        }
    }

    static class HoneycombMR2ConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivitymanager);
        }

        HoneycombMR2ConnectivityManagerCompatImpl()
        {
        }
    }

    static class GingerbreadConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return ConnectivityManagerCompatGingerbread.isActiveNetworkMetered(connectivitymanager);
        }

        GingerbreadConnectivityManagerCompatImpl()
        {
        }
    }

    static class BaseConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            boolean flag;
            NetworkInfo networkinfo;
            flag = true;
            networkinfo = connectivitymanager.getActiveNetworkInfo();
            if(networkinfo != null) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            switch(networkinfo.getType())
            {
            case 1: // '\001'
                flag = false;
                break;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        BaseConnectivityManagerCompatImpl()
        {
        }
    }

    static interface ConnectivityManagerCompatImpl
    {

        public abstract boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager);
    }


    public ConnectivityManagerCompat()
    {
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivitymanager, Intent intent)
    {
        NetworkInfo networkinfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
        NetworkInfo networkinfo1;
        if(networkinfo != null)
            networkinfo1 = connectivitymanager.getNetworkInfo(networkinfo.getType());
        else
            networkinfo1 = null;
        return networkinfo1;
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
    {
        return IMPL.isActiveNetworkMetered(connectivitymanager);
    }

    private static final ConnectivityManagerCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new JellyBeanConnectivityManagerCompatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 13)
            IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 8)
            IMPL = new GingerbreadConnectivityManagerCompatImpl();
        else
            IMPL = new BaseConnectivityManagerCompatImpl();
    }
}
