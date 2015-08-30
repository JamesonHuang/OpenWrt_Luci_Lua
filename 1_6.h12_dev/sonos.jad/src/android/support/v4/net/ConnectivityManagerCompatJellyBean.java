// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import android.net.ConnectivityManager;

class ConnectivityManagerCompatJellyBean
{

    ConnectivityManagerCompatJellyBean()
    {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
    {
        return connectivitymanager.isActiveNetworkMetered();
    }
}
