// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.*;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.localaudiolibrary.LocalMusicService;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.util:
//            PeriodicExecutor, SLog, LibraryUtils

public class UserActivityMonitor
{
    private class ScreenReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if(!intent.getAction().equals("android.intent.action.SCREEN_OFF")) goto _L2; else goto _L1
_L1:
            SLog.w(UserActivityMonitor.LOG_TAG, (new StringBuilder()).append("Screen off. Has wifilock:").append(LocalMusicService.hasWifiLock()).toString());
            if(m_activityTimer != null && m_activityTimer.isRunning())
                m_activityTimer.stop();
            UserActivityMonitor useractivitymonitor = UserActivityMonitor.this;
            boolean flag;
            SCILibrary scilibrary1;
            if(!LocalMusicService.hasWifiLock())
                flag = true;
            else
                flag = false;
            useractivitymonitor.forceRebindOnWake = flag;
            screenOffTime = System.currentTimeMillis();
            scilibrary1 = LibraryUtils.getSCLibManager().getLibrary();
            if(scilibrary1 != null)
                scilibrary1.getBrowseManager().suspendPolling();
            onSleep();
_L4:
            return;
_L2:
            if(intent.getAction().equals("android.intent.action.SCREEN_ON"))
            {
                SLog.w(UserActivityMonitor.LOG_TAG, (new StringBuilder()).append("Screen on, Force rebind: ").append(forceRebindOnWake).toString());
                if(forceRebindOnWake && SonosApplication.getInstance().getNetworkStatusMonitor().isWifiConnected() && System.currentTimeMillis() - screenOffTime > 30000L)
                    SonosApplication.getInstance().getSCLibManager().refreshNetworking();
                if(m_activityTimer != null && !m_activityTimer.isRunning())
                    m_activityTimer.start();
                SCILibrary scilibrary = LibraryUtils.getSCLibManager().getLibrary();
                if(scilibrary != null)
                    scilibrary.getBrowseManager().resumePolling();
                onWake();
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public final IntentFilter filter = new IntentFilter();
        final UserActivityMonitor this$0;

        public ScreenReceiver()
        {
            this$0 = UserActivityMonitor.this;
            super();
            filter.addAction("android.intent.action.SCREEN_ON");
            filter.addAction("android.intent.action.SCREEN_OFF");
        }
    }


    public UserActivityMonitor(SonosApplication sonosapplication)
    {
        sleepEnabled = true;
        forceRebindOnWake = true;
        screenOffTime = 0L;
        applicationContext = sonosapplication;
        m_activityTimer = new PeriodicExecutor(0x2bf20L, sonosapplication.getHandler()) {

            public void execute()
            {
                stop();
                onSleep();
            }

            final UserActivityMonitor this$0;

            
            {
                this$0 = UserActivityMonitor.this;
                super(l, handler);
            }
        }
;
    }

    private void onWake()
    {
        SCIDealerMode scidealermode = SonosApplication.getInstance().getSCLibManager().getDealerMode();
        if(scidealermode != null)
            scidealermode.controllerWokeUp();
    }

    public void onSleep()
    {
    }

    public void onUserActivity()
    {
        if(!m_activityTimer.isRunning())
            onWake();
        else
            m_activityTimer.stop();
        m_activityTimer.start();
    }

    public void onWifiLockStatusChanged(boolean flag)
    {
        boolean flag1;
        if(forceRebindOnWake || !flag)
            flag1 = true;
        else
            flag1 = false;
        forceRebindOnWake = flag1;
    }

    public void start()
    {
        if(m_screenReceiver == null)
        {
            m_screenReceiver = new ScreenReceiver();
            applicationContext.registerReceiver(m_screenReceiver, m_screenReceiver.filter);
        }
    }

    public void stop()
    {
        if(m_screenReceiver != null)
            applicationContext.unregisterReceiver(m_screenReceiver);
    }

    public static final String LOG_TAG = com/sonos/acr/util/UserActivityMonitor.getSimpleName();
    private static final long SLEEP_TIME = 0x2bf20L;
    private final SonosApplication applicationContext;
    private boolean forceRebindOnWake;
    private PeriodicExecutor m_activityTimer;
    private ScreenReceiver m_screenReceiver;
    private long screenOffTime;
    private boolean sleepEnabled;





/*
    static boolean access$102(UserActivityMonitor useractivitymonitor, boolean flag)
    {
        useractivitymonitor.forceRebindOnWake = flag;
        return flag;
    }

*/



/*
    static long access$202(UserActivityMonitor useractivitymonitor, long l)
    {
        useractivitymonitor.screenOffTime = l;
        return l;
    }

*/

}
