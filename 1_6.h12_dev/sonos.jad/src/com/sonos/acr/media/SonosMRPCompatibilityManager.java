// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.sonos.acr.application.SonosApplication;
import java.util.ArrayList;
import java.util.Iterator;

public final class SonosMRPCompatibilityManager
{
    private class PackageReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals("android.intent.action.PACKAGE_REPLACED"))
            {
                Uri uri = Uri.parse(intent.getDataString());
                if(uri.getScheme().equals("package") && uri.getSchemeSpecificPart().equals("com.google.android.music"))
                {
                    gpmSupportsCQ = detectGPMCloudQueueEnabled();
                    for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((MRPCompatibilityListener)iterator.next()).onCompatibilityChanged());
                }
            }
        }

        public final IntentFilter filter = new IntentFilter();
        final SonosMRPCompatibilityManager this$0;

        public PackageReceiver()
        {
            this$0 = SonosMRPCompatibilityManager.this;
            super();
            filter.addAction("android.intent.action.PACKAGE_REPLACED");
            filter.addDataScheme("package");
        }
    }

    public static interface MRPCompatibilityListener
    {

        public abstract void onCompatibilityChanged();
    }


    public SonosMRPCompatibilityManager()
    {
        listeners = new ArrayList();
        gpmSupportsCQ = false;
        gpmSupportsCQ = detectGPMCloudQueueEnabled();
        packageReceiver = new PackageReceiver();
        SonosApplication.getInstance().registerReceiver(packageReceiver, packageReceiver.filter);
    }

    private boolean detectGPMCloudQueueEnabled()
    {
        return false;
    }

    private int getPackageVersionCode(String s)
    {
        int i = 0;
        try
        {
            PackageInfo packageinfo = SonosApplication.getInstance().getPackageManager().getPackageInfo(s, 0);
            if(packageinfo != null)
                i = packageinfo.versionCode;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        return i;
    }

    public boolean isCloudQueueSupported()
    {
        return gpmSupportsCQ;
    }

    public void subscribe(MRPCompatibilityListener mrpcompatibilitylistener)
    {
        if(!listeners.contains(mrpcompatibilitylistener))
            listeners.add(mrpcompatibilitylistener);
    }

    public void unsubscribe(MRPCompatibilityListener mrpcompatibilitylistener)
    {
        listeners.remove(mrpcompatibilitylistener);
    }

    public static final String GPM_PACKAGE_NAME = "com.google.android.music";
    private static final String LOG_TAG = "SonosMRPCompatibilityManager";
    private boolean gpmSupportsCQ;
    private ArrayList listeners;
    private PackageReceiver packageReceiver;


/*
    static boolean access$002(SonosMRPCompatibilityManager sonosmrpcompatibilitymanager, boolean flag)
    {
        sonosmrpcompatibilitymanager.gpmSupportsCQ = flag;
        return flag;
    }

*/


}
