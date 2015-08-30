// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.notification;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.util.Log;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.media.SonosRouteProvider;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.nowplaying.controllers.TransportViewController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.*;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.services.SonosService;
import com.sonos.acr.services.lockscreen.LockScreenController;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.lang.ref.WeakReference;

// Referenced classes of package com.sonos.acr.services.notification:
//            SonosNotification, SimpleNotification, ComplexSonosNotification

public class NotificationService extends SonosService
    implements com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.sclib.SonosListener.StateListener, com.sonos.acr.network.SonosNetworkManager.ConnectionListener, com.sonos.acr.media.SonosRouteProvider.SonosRouteProviderListener, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener
{
    public static class LocalBinder extends Binder
    {

        public NotificationService getService()
        {
            return (NotificationService)serviceRef.get();
        }

        private WeakReference serviceRef;

        public LocalBinder(NotificationService notificationservice)
        {
            serviceRef = new WeakReference(notificationservice);
        }
    }


    public NotificationService()
    {
        postUpdates = new Runnable() {

            public void run()
            {
                updateNotification();
            }

            final NotificationService this$0;

            
            {
                this$0 = NotificationService.this;
                super();
            }
        }
;
    }

    private void hideLockscreen()
    {
        if(lockScreenController != null)
        {
            lockScreenController.stop();
            lockScreenController = null;
        }
    }

    private void hideNotification()
    {
        if(notification != null)
        {
            stopForeground(true);
            notification.cancel();
            notification = null;
        }
        hideLockscreen();
    }

    public static void init(final SonosApplication context)
    {
        if(listener == null)
        {
            if(!sharedPreferences.contains("NOTIFICATIONS_ENABLED"))
                sharedPreferences.edit().putBoolean("NOTIFICATIONS_ENABLED", true).commit();
            if(!sharedPreferences.contains("LOCKSCREEN_ENABLED"))
                sharedPreferences.edit().putBoolean("LOCKSCREEN_ENABLED", true).commit();
            listener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {

                public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
                {
                    if("NOTIFICATIONS_ENABLED".equals(s))
                        if(sharedpreferences.getBoolean("NOTIFICATIONS_ENABLED", true))
                        {
                            context.startService((new Intent(context, com/sonos/acr/services/notification/NotificationService)).setAction("ACTION_START_NOTIFICATIONS"));
                            sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.ACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.SETTINGS, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NOTIFICATIONS);
                        } else
                        {
                            context.startService((new Intent(context, com/sonos/acr/services/notification/NotificationService)).setAction("ACTION_END_NOTIFICATIONS"));
                            sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.DEACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.SETTINGS, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NOTIFICATIONS);
                        }
                }

                final SonosApplication val$context;

            
            {
                context = sonosapplication;
                super();
            }
            }
;
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        }
        if(sharedPreferences.getBoolean("NOTIFICATIONS_ENABLED", true))
            context.startService((new Intent(context, com/sonos/acr/services/notification/NotificationService)).setAction("ACTION_START_NOTIFICATIONS"));
    }

    public static boolean isForegroundNotificationRequired()
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 18)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void showLockscreen()
    {
        if(android.os.Build.VERSION.SDK_INT >= 14 && sharedPreferences.getBoolean("LOCKSCREEN_ENABLED", true) && lockScreenController == null)
        {
            lockScreenController = new LockScreenController(getApplicationContext(), zoneGroupController);
            lockScreenController.start();
        }
    }

    private void showNotification()
    {
        if(notification == null)
        {
            if(android.os.Build.VERSION.SDK_INT < 11)
                notification = new SimpleNotification(this);
            else
                notification = new ComplexSonosNotification(this);
            notification.updateView(null);
        }
        showLockscreen();
    }

    private void subscribeEventSinks()
    {
        Log.d(LOG_TAG, "Subscribing Event Sinks");
        CurrentNowPlayingEventSink.getInstance().addListener(this);
        CurrentNowPlayingEventSink.getInstance().addListener(zoneGroupController);
        CurrentGroupVolumeEventSink.getInstance().addListener(this);
        CurrentGroupVolumeEventSink.getInstance().addListener(zoneGroupController);
        HouseholdEventSink.getInstance().addListener(this);
        SonosApplication.getInstance().getNetworkStatusMonitor().subscribe(this);
        SonosApplication.getInstance().getListener().subscribe(this);
        SonosRouteProvider.addListener(this);
        zoneGroupController.getTransportViewController().subscribe();
        sharedPreferences.registerOnSharedPreferenceChangeListener(lockscreenChangeListener);
    }

    private void unsubscribeEventSinks()
    {
        Log.d(LOG_TAG, "Unsubscribing Event Sinks");
        zoneGroupController.getTransportViewController().unsubscribe();
        CurrentNowPlayingEventSink.getInstance().removeListener(this);
        CurrentNowPlayingEventSink.getInstance().removeListener(zoneGroupController);
        CurrentGroupVolumeEventSink.getInstance().removeListener(this);
        CurrentGroupVolumeEventSink.getInstance().removeListener(zoneGroupController);
        HouseholdEventSink.getInstance().removeListener(this);
        SonosApplication.getInstance().getNetworkStatusMonitor().unsubscribe(this);
        SonosApplication.getInstance().getListener().unsubscribe(this);
        SonosRouteProvider.removeListener(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(lockscreenChangeListener);
    }

    public Notification getForegroundNotification()
    {
        SonosNotification sonosnotification;
        if(notificationStarted)
        {
            if(notification == null)
                updateNotification();
            sonosnotification = notification;
        } else
        {
            sonosnotification = null;
        }
        return sonosnotification;
    }

    public IBinder onBind(Intent intent)
    {
        return new LocalBinder(this);
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        updateNotification();
    }

    public void onCreate()
    {
        super.onCreate();
        zoneGroupController = new ZoneGroupController(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged || householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
            updateNotification();
    }

    public void onHouseholdStateChange(com.sonos.acr.sclib.SonosListener.HouseholdState householdstate, com.sonos.acr.sclib.SonosListener.HouseholdSubState householdsubstate)
    {
        updateNotification();
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent != com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged) goto _L2; else goto _L1
_L1:
        String s = nowplaying.getAsynchronousErrorString();
        if(StringUtils.isNotEmptyOrNull(s))
            onZoneGroupMessage(nowplaying.getZoneGroup(), s, s, 5000L);
        else
            postUpdates();
_L4:
        return;
_L2:
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnTVEqualizationChanged)
            postUpdates();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onSonosRouteProviderEvent(SonosRouteProvider sonosrouteprovider, com.sonos.acr.media.SonosRouteProvider.RouteProviderEvent routeproviderevent)
    {
        updateNotification();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        int k;
        k = 1;
        super.onStartCommand(intent, i, j);
        int l;
        if(android.os.Build.VERSION.SDK_INT < 14 || !sharedPreferences.getBoolean("LOCKSCREEN_ENABLED", true))
            break MISSING_BLOCK_LABEL_139;
        l = k;
_L2:
        if(intent == null || "ACTION_START_NOTIFICATIONS".equals(intent.getAction()) && (notification == null || l != 0))
        {
            updateNotification(true);
            subscribeEventSinks();
            notificationStarted = true;
        } else
        if("ACTION_END_NOTIFICATIONS".equals(intent.getAction()))
        {
            hideNotification();
            stopSelf();
            unsubscribeEventSinks();
            notificationStarted = false;
        }
          goto _L1
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        k = 0;
          goto _L1
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        LibraryUtils.showLinkErrors();
        ApplicationStateManager.getInstance().closeAllActivities();
        k = 0;
_L1:
        return k;
        l = 0;
          goto _L2
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        if(volumeevent != com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent.OnMuteChanged) goto _L2; else goto _L1
_L1:
        postUpdates();
_L4:
        return;
_L2:
        if(volumeevent == com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent.OnVolumeChanged && lockScreenController != null)
            lockScreenController.updateVolume(groupvolume.getGroupVolume().getVolume());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onZoneGroupMessage(ZoneGroup zonegroup, String s, String s1, long l)
    {
        if(notification != null && zonegroup != null && zonegroup.isCurrent())
        {
            SonosNotification sonosnotification = notification;
            if(!StringUtils.isNotEmptyOrNull(s1))
                s1 = s;
            sonosnotification.showMessage(s1, l);
            updateNotification();
        }
    }

    public void postUpdates()
    {
        handler.removeCallbacks(postUpdates);
        handler.post(postUpdates);
    }

    public void updateNotification()
    {
        updateNotification(false);
    }

    public void updateNotification(boolean flag)
    {
        SonosApplication sonosapplication = SonosApplication.getInstance();
        if(!sonosapplication.getNetworkStatusMonitor().isWifiConnected() || !sonosapplication.getListener().isConnectedAndPlayable()) goto _L2; else goto _L1
_L1:
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        if(zonegroup == null) goto _L2; else goto _L3
_L3:
        boolean flag1;
        boolean flag2;
        flag1 = SonosRouteProvider.isRouteActive(SonosRouteProvider.getRouteID(LibraryUtils.getHousehold().getID(), zonegroup.getID()));
        flag2 = zonegroup.nowPlaying.getTransport().hasLocalMuseSession();
        if(flag1 || flag2 || !zonegroup.nowPlaying.hasMusic() || android.os.Build.VERSION.SDK_INT < 11 && !zonegroup.nowPlaying.isPlaying()) goto _L2; else goto _L4
_L4:
        showNotification();
        notification.updateView(zonegroup);
        if(lockScreenController != null)
            lockScreenController.updateView(zonegroup, flag);
        SLog.d(LOG_TAG, "Refreshing Notification");
        startForeground(10, notification);
_L6:
        return;
_L2:
        hideNotification();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static final String ACTION_END_NOTIFICATIONS = "ACTION_END_NOTIFICATIONS";
    public static final String ACTION_START_NOTIFICATIONS = "ACTION_START_NOTIFICATIONS";
    public static final String NOTIFICATIONS_ENABLED = "NOTIFICATIONS_ENABLED";
    public static final int NOTIFICATION_UID = 10;
    private static android.content.SharedPreferences.OnSharedPreferenceChangeListener listener;
    private static final SharedPreferences sharedPreferences = LibraryUtils.getSharedPreferences();
    private LockScreenController lockScreenController;
    private final android.content.SharedPreferences.OnSharedPreferenceChangeListener lockscreenChangeListener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {

        public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
        {
            if("LOCKSCREEN_ENABLED".equals(s))
                if(sharedpreferences.getBoolean("LOCKSCREEN_ENABLED", true))
                    updateNotification();
                else
                    hideLockscreen();
        }

        final NotificationService this$0;

            
            {
                this$0 = NotificationService.this;
                super();
            }
    }
;
    protected SonosNotification notification;
    private boolean notificationStarted;
    private Runnable postUpdates;
    private ZoneGroupController zoneGroupController;


}
