// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import android.app.*;
import android.content.*;
import android.database.ContentObserver;
import android.graphics.BitmapFactory;
import android.os.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.services.notification.NotificationService;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCILibrary;

// Referenced classes of package com.sonos.acr.localaudiolibrary:
//            LocalMediaUtils, LocalMusicServerDelegate

public class LocalMusicService extends Service
    implements com.sonos.acr.util.SonosWifiLockWatchdog.WifiLockReleaseListener
{

    public LocalMusicService()
    {
    }

    public static boolean hasWifiLock()
    {
        boolean flag;
        if(watchdog != null && watchdog.hasWifiLock())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void init(SonosApplication sonosapplication)
    {
        if(contentObserver == null)
        {
            contentObserver = new ContentObserver(sonosapplication) {

                public void onChange(boolean flag)
                {
                    SLog.d(LocalMusicService.LOG_TAG, "Local media Dataset Changed!!  Initializing.");
                    if(!LocalMusicService.startedService)
                        LocalMusicService.start(context);
                }

                final SonosApplication val$context;

            
            {
                context = sonosapplication;
                super(final_handler);
            }
            }
;
            sonosapplication.getContentResolver().registerContentObserver(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, false, contentObserver);
        }
        start(sonosapplication);
    }

    public static void onBeginStreaming(Context context)
    {
        if(LocalMediaUtils.hasAudioTracks(context.getContentResolver()))
            context.startService((new Intent(context, com/sonos/acr/localaudiolibrary/LocalMusicService)).setAction("AUDIO_STREAM_BEGIN_ACTION"));
    }

    public static void onEndStreaming(Context context)
    {
        if(LocalMediaUtils.hasAudioTracks(context.getContentResolver()))
            context.startService((new Intent(context, com/sonos/acr/localaudiolibrary/LocalMusicService)).setAction("AUDIO_STREAM_END_ACTION"));
    }

    public static void onLocalSongPlayRequest(Context context)
    {
        if(LocalMediaUtils.hasAudioTracks(context.getContentResolver()))
            context.startService((new Intent(context, com/sonos/acr/localaudiolibrary/LocalMusicService)).setAction("AUDIO_STREAM_PLAY_REQUEST_ACTION"));
    }

    public static void registerDeviceWithHH()
    {
        SCILibrary scilibrary = LibraryUtils.getSCLibManager().getLibrary();
        String s = (new StringBuilder()).append("ACR-").append(scilibrary.getHostDeviceID()).toString();
        String s1 = (new StringBuilder()).append(Build.MANUFACTURER.substring(0, 1).toUpperCase()).append(Build.MANUFACTURER.substring(1)).toString();
        String s2 = (new StringBuilder()).append(Build.MODEL.substring(0, 1).toUpperCase()).append(Build.MODEL.substring(1)).toString();
        scilibrary.registerMobileDevice((new StringBuilder()).append(s1).append(" ").append(s2).toString(), s);
    }

    public static void start(SonosApplication sonosapplication)
    {
        if(LocalMediaUtils.hasAudioTracks(sonosapplication.getContentResolver()))
        {
            SLog.d(LOG_TAG, "User Has Tracks! Starting local library service");
            sonosapplication.startService((new Intent(sonosapplication, com/sonos/acr/localaudiolibrary/LocalMusicService)).setAction("startService"));
            startedService = true;
        } else
        {
            SLog.d(LOG_TAG, "No Tracks! ");
        }
    }

    private void startForegroundWithNotification()
    {
        if(NotificationService.isForegroundNotificationRequired())
        {
            Notification notification = null;
            if(notificationService != null)
                notification = notificationService.getForegroundNotification();
            if(notification != null)
            {
                startForeground(10, notification);
            } else
            {
                android.app.Notification.Builder builder = (new android.app.Notification.Builder(this)).setContentIntent(PendingIntent.getActivity(this, 0, (new Intent(this, SonosActivity.getCurrentStateActivity())).addFlags(0x10000000), 0)).setContentTitle(getText(0x7f0c0092)).setSmallIcon(0x7f020060).setStyle((new android.app.Notification.BigTextStyle()).bigText(getText(0x7f0c0090))).setLargeIcon(BitmapFactory.decodeResource(getResources(), 0x7f020050)).addAction(0x7f0200a3, getText(0x7f0c0091), PendingIntent.getService(this, 0, (new Intent(getApplicationContext(), com/sonos/acr/localaudiolibrary/LocalMusicService)).setAction("killService"), 0));
                if(android.os.Build.VERSION.SDK_INT >= 21)
                    builder.setVisibility(1);
                startForeground(10, builder.build());
            }
        } else
        {
            startForeground(NOTIFICATION_ID, new Notification());
        }
    }

    private void stopForeground()
    {
        stopForeground(true);
        if(notificationService != null && notificationService.getForegroundNotification() != null)
            notificationService.updateNotification();
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        SonosApplication sonosapplication = SonosApplication.getInstance();
        if(!sonosapplication.getSCLibManager().isInitialized())
            sonosapplication.startServices();
        registerDeviceWithHH();
        watchdog = new SonosWifiLockWatchdog(SonosApplication.getInstance().getApplicationContext(), "SonosMusicLock", 0x1b7740L);
        watchdog.setWifiReleaseListener(this);
        if(NotificationService.isForegroundNotificationRequired())
        {
            notificationServiceConnection = new ServiceConnection() {

                public void onServiceConnected(ComponentName componentname, IBinder ibinder)
                {
                    notificationService = ((com.sonos.acr.services.notification.NotificationService.LocalBinder)ibinder).getService();
                }

                public void onServiceDisconnected(ComponentName componentname)
                {
                    notificationService = null;
                }

                final LocalMusicService this$0;

            
            {
                this$0 = LocalMusicService.this;
                super();
            }
            }
;
            bindService(new Intent(this, com/sonos/acr/services/notification/NotificationService), notificationServiceConnection, 1);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(watchdog != null)
            watchdog.releaseWifiLock();
        if(notificationServiceConnection != null)
        {
            unbindService(notificationServiceConnection);
            notificationServiceConnection = null;
            notificationService = null;
        }
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        boolean flag = true;
        super.onStartCommand(intent, i, j);
        if(intent != null)
        {
            String s = intent.getAction();
            SLog.i(LOG_TAG, (new StringBuilder()).append("Local Music Service Got action: ").append(s).toString());
            if(StringUtils.isNotEmptyOrNull(s))
                if("startService".equals(s) && localMSDelegate == null)
                {
                    localMSDelegate = new LocalMusicServerDelegate(getApplicationContext());
                    LibraryUtils.getSCLibManager().getLibrary().createMusicServer(localMSDelegate);
                } else
                if("killService".equals(s) && localMSDelegate != null)
                {
                    localMSDelegate = null;
                    stopForeground();
                    if(watchdog != null)
                        watchdog.releaseWifiLock();
                } else
                if(s.startsWith("AUDIO_STREAM_") && localMSDelegate != null)
                {
                    startForegroundWithNotification();
                    if("AUDIO_STREAM_BEGIN_ACTION".equals(s))
                        watchdog.requestWifiLock(flag, false);
                    else
                    if("AUDIO_STREAM_END_ACTION".equals(s))
                        watchdog.requestWifiLock(false, false);
                    else
                    if("AUDIO_STREAM_PLAY_REQUEST_ACTION".equals(s))
                        watchdog.requestWifiLock(false, flag);
                }
        }
        if(localMSDelegate == null)
            flag = 2;
        return ((flag) ? 1 : 0);
    }

    public void onWifiReleased()
    {
        stopForeground();
    }

    public static final String AUDIO_STREAM = "AUDIO_STREAM_";
    public static final String AUDIO_STREAM_BEGIN_ACTION = "AUDIO_STREAM_BEGIN_ACTION";
    public static final String AUDIO_STREAM_END_ACTION = "AUDIO_STREAM_END_ACTION";
    public static final String AUDIO_STREAM_PLAY_REQUEST_ACTION = "AUDIO_STREAM_PLAY_REQUEST_ACTION";
    private static final String LOG_TAG = com/sonos/acr/localaudiolibrary/LocalMusicService.getSimpleName();
    private static int NOTIFICATION_ID = 0;
    public static final String START_SERVICE_ACTION = "startService";
    public static final String STOP_SERVICE_ACTION = "killService";
    public static final int WIFI_RELEASE_TIMEOUT = 0x1b7740;
    private static ContentObserver contentObserver;
    private static boolean startedService = false;
    private static SonosWifiLockWatchdog watchdog;
    private LocalMusicServerDelegate localMSDelegate;
    private NotificationService notificationService;
    private ServiceConnection notificationServiceConnection;

    static 
    {
        NOTIFICATION_ID = 20;
    }




/*
    static NotificationService access$202(LocalMusicService localmusicservice, NotificationService notificationservice)
    {
        localmusicservice.notificationService = notificationservice;
        return notificationservice;
    }

*/
}
