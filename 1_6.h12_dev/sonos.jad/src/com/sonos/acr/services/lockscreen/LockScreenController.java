// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.lockscreen;

import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.*;
import android.os.*;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.nowplaying.controllers.*;
import com.sonos.acr.nowplaying.views.TransportView;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.services.notification.MusicIntentReceiver;
import com.sonos.acr.util.*;
import com.sonos.acr.volume.ContinuousVolumeHelper;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.services.lockscreen:
//            MusicFocusable, AudioFocusHelper

public class LockScreenController
    implements TransportView, MusicFocusable
{
    private class MyOnPlaybackPositionUpdateListener
        implements android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
    {

        public void onPlaybackPositionUpdate(final long newPositionMs)
        {
            handler.removeCallbacks(delayPositionUpdate);
            Looper.myQueue().removeIdleHandler(idleHandler);
            if(delayPositionUpdate == null)
                transportEventListener.onStartTrackingTouch(null);
            delayPositionUpdate = new Runnable() {

                public void run()
                {
                    transportEventListener.onStopTrackingTouch(null, newPositionMs);
                    lastPositionUpdateTime = System.currentTimeMillis();
                    delayPositionUpdate = null;
                }

                final MyOnPlaybackPositionUpdateListener this$1;
                final long val$newPositionMs;

                
                {
                    this$1 = MyOnPlaybackPositionUpdateListener.this;
                    newPositionMs = l;
                    super();
                }
            }
;
            handler.postDelayed(delayPositionUpdate, 1000L);
            sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.SWIPE, com.sonos.sclib.SCIAppReporting.SCViewID.LOCKSCREEN, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_SEEK);
        }

        final LockScreenController this$0;

        private MyOnPlaybackPositionUpdateListener()
        {
            this$0 = LockScreenController.this;
            super();
        }

    }

    private class MediaRemoteAlbumArtController extends AlbumArtController
    {

        protected void clearImage()
        {
            setImageBitmap(null, true);
        }

        protected void setImageBitmap(Bitmap bitmap, boolean flag)
        {
            LockScreenController lockscreencontroller = LockScreenController.this;
            Bitmap bitmap1;
            if(bitmap != null)
                bitmap1 = ImageUtils.copyBitmap(bitmap);
            else
                bitmap1 = null;
            lockscreencontroller.currentBitmap = bitmap1;
            if(!flag)
                remoteControlClient.editMetadata(false).putBitmap(100, currentBitmap).apply();
            currentResId = 0;
        }

        protected void setImageResource(int i)
        {
            Resources resources = context.getResources();
            if(currentResId != i)
            {
                if(ImageUtils.isRawImage(resources, i))
                {
                    setImageBitmap(ImageUtils.getSvgFromResource(resources, i, 0, 0), true);
                } else
                {
                    android.graphics.drawable.Drawable drawable = resources.getDrawable(i);
                    if(drawable instanceof BitmapDrawable)
                    {
                        setImageBitmap(((BitmapDrawable)drawable).getBitmap(), true);
                    } else
                    {
                        SLog.e(LOG_TAG, (new StringBuilder()).append("Unknown Drawable Type; ").append(drawable).toString());
                        clearImage();
                    }
                }
                currentResId = i;
            }
        }

        int currentResId;
        final LockScreenController this$0;

        public MediaRemoteAlbumArtController()
        {
            this$0 = LockScreenController.this;
            super(AlbumArtSize.SIZE_NOW_PLAYING, 0x7f060019, 0x7f060019, 0x7f060019);
            currentResId = 0;
        }
    }


    public LockScreenController(Context context1, ZoneGroupController zonegroupcontroller)
    {
        hasAudioFocus = false;
        lastPositionUpdateTime = 0L;
        wasPlaying = false;
        oldNowPlayingStates = null;
        handler = SonosApplication.getInstance().getHandler();
        context = context1;
        zoneGroupController = zonegroupcontroller;
        audioManager = (AudioManager)context1.getSystemService("audio");
        audioFocusHelper = new AudioFocusHelper(context1, this);
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        mediaButtonReceiver = new ComponentName(context1, com/sonos/acr/services/notification/MusicIntentReceiver);
        intent.setComponent(mediaButtonReceiver);
        remoteControlClient = new RemoteControlClient(PendingIntent.getBroadcast(context1, 0, intent, 0));
        if(android.os.Build.VERSION.SDK_INT >= 16)
            mediaRouter = (MediaRouter)context1.getSystemService("media_router");
        else
            mediaRouter = null;
        continuousVolumeHelper = new ContinuousVolumeHelper("Sonos");
        continuousVolumeHelper.setContinousVolumeCallback(new com.sonos.acr.volume.ContinuousVolumeHelper.ContinousVolumeCallback() {

            public void onVolumeChanged(int i, boolean flag)
            {
                if(userRoute != null)
                    userRoute.setVolume(i);
            }

            final LockScreenController this$0;

            
            {
                this$0 = LockScreenController.this;
                super();
            }
        }
);
        watchdog = new SonosWifiLockWatchdog(context1, "SonosLockScreenWifiLock", 30000L);
    }

    private String createAlbumName()
    {
        String s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_ALBUM_NAME)).getText();
        if(s == null || StringUtils.isEmptyOrNull(s.trim()))
            s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_SIMPLE_STRING_3)).getText();
        if(s == null || StringUtils.isEmptyOrNull(s.trim()))
            s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_RADIO_STATION_NAME)).getText();
        return s;
    }

    private String createArtistName()
    {
        String s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_ARTIST_NAME)).getText();
        if(s == null || StringUtils.isEmptyOrNull(s.trim()))
            s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_SIMPLE_STRING_2)).getText();
        return s;
    }

    private int getLockscreenCannedArt(NowPlaying nowplaying)
    {
        return 0x7f020098;
    }

    private boolean hasTransportStateChanged(NowPlaying nowplaying)
    {
        boolean flag = false;
        if(oldNowPlayingStates == null || oldNowPlayingStates[0] != nowplaying.getTransport().isPlayPauseEnabled() || oldNowPlayingStates[1] != nowplaying.getTransport().isPreviousTrackEnabled() || oldNowPlayingStates[2] != nowplaying.getTransport().isNextTrackEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag1 = oldNowPlayingStates[3];
        boolean flag2;
        if(nowplaying.getTransport().getPlayPauseDisplayState() == com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_STOP)
            flag2 = true;
        else
            flag2 = false;
        if(flag1 != flag2) goto _L2; else goto _L3
_L3:
        return flag;
_L2:
        oldNowPlayingStates = new boolean[4];
        oldNowPlayingStates[flag] = nowplaying.getTransport().isPlayPauseEnabled();
        oldNowPlayingStates[1] = nowplaying.getTransport().isPreviousTrackEnabled();
        oldNowPlayingStates[2] = nowplaying.getTransport().isNextTrackEnabled();
        boolean aflag[] = oldNowPlayingStates;
        if(nowplaying.getTransport().getPlayPauseDisplayState() == com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_STOP)
            flag = true;
        aflag[3] = flag;
        flag = true;
        if(true) goto _L3; else goto _L4
_L4:
    }

    private boolean isHouseholdPlayingP2S()
    {
        Household household = LibraryUtils.getHousehold();
        if(household == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY).iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        ZoneGroup zonegroup = (ZoneGroup)iterator.next();
        if(!zonegroup.nowPlaying.getTransport().hasLocalMuseSession() || zonegroup.nowPlaying.getTransport().getPlaybackState() != SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING) goto _L5; else goto _L4
_L4:
        boolean flag = true;
_L7:
        return flag;
_L2:
        flag = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private boolean keyGuardVisible()
    {
        KeyguardManager keyguardmanager = (KeyguardManager)context.getSystemService("keyguard");
        boolean flag;
        if(screenIsOn() && keyguardmanager != null && keyguardmanager.inKeyguardRestrictedInputMode())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void onAudioFocusGained()
    {
        SLog.i(LOG_TAG, "On AudioFocusGained");
        audioManager.registerMediaButtonEventReceiver(mediaButtonReceiver);
        updateView(LibraryUtils.getCurrentZoneGroup());
        boolean flag;
        if(!screenIsOn() || keyGuardVisible())
            flag = true;
        else
            flag = false;
        setVolumeTakeoverEnabled(flag);
    }

    private void onAudioFocusLost()
    {
        SLog.i(LOG_TAG, "On Audio Focus Lost");
        lastZoneGroup = null;
        oldNowPlayingStates = null;
        lastZoneGroupName = null;
    }

    private boolean postDelayedMetadataUpdate()
    {
        long l = System.currentTimeMillis() - lastPositionUpdateTime;
        boolean flag;
        if(l < 2000L)
        {
            final Handler handler = SonosApplication.getInstance().getHandler();
            if(delayMusicChangedHandler == null && delayPositionUpdate == null)
                delayMusicChangedHandler = new Runnable() {

                    public void run()
                    {
                        handler.removeCallbacks(delayMusicChangedHandler);
                        delayMusicChangedHandler = null;
                        lastPositionUpdateTime = 0L;
                        if(lastZoneGroup != null)
                            updateView(lastZoneGroup);
                    }

                    final LockScreenController this$0;
                    final Handler val$handler;

            
            {
                this$0 = LockScreenController.this;
                handler = handler1;
                super();
            }
                }
;
            handler.removeCallbacks(delayMusicChangedHandler);
            handler.postDelayed(delayMusicChangedHandler, 2000L - l);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean screenIsOn()
    {
        PowerManager powermanager = (PowerManager)context.getSystemService("power");
        boolean flag;
        if(powermanager != null && powermanager.isScreenOn())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private android.media.MediaRouter.UserRouteInfo setUpUserRoute()
    {
        final android.media.MediaRouter.UserRouteInfo userRoute;
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            userRoute = mediaRouter.createUserRoute(mediaRouter.createRouteCategory("SonosCurrent", false));
            userRoute.setVolumeHandling(1);
            userRoute.setPlaybackType(0);
            userRoute.setPlaybackStream(3);
            userRoute.setVolumeMax(100);
            userRoute.setVolumeCallback(new android.media.MediaRouter.VolumeCallback() {

                public void onVolumeSetRequest(android.media.MediaRouter.RouteInfo routeinfo, int i)
                {
                    continuousVolumeHelper.onSetVolume(i, LibraryUtils.getCurrentZoneGroup());
                }

                public void onVolumeUpdateRequest(android.media.MediaRouter.RouteInfo routeinfo, int i)
                {
                    watchdog.requestWifiLock(false, true);
                    zoneGroupController.getVolumeViewController().onRelativeVolumeSeekChange("", i);
                    GroupVolume groupvolume = zoneGroupController.getVolumeViewController().getSubscribedGroupVolume();
                    if(groupvolume != null && groupvolume.getGroupVolume() != null)
                    {
                        if(groupvolume.getGroupVolume().isMuted())
                            zoneGroupController.getVolumeViewController().toggleMute("");
                        userRoute.setVolume(groupvolume.getGroupVolume().getVolume());
                    }
                }

                final LockScreenController this$0;
                final android.media.MediaRouter.UserRouteInfo val$userRoute;

            
            {
                this$0 = LockScreenController.this;
                userRoute = userrouteinfo;
                super();
            }
            }
);
        } else
        {
            userRoute = null;
        }
        return userRoute;
    }

    private void setVolumeTakeoverEnabled(boolean flag)
    {
        if(userRoute != null)
            if(flag)
                userRoute.setPlaybackType(1);
            else
                userRoute.setPlaybackType(0);
        if(!flag)
            watchdog.releaseWifiLock();
    }

    private void updateMetaData(NowPlaying nowplaying)
    {
        HashMap hashmap = nowplaying.getPositionalMetaData();
        if(lastNowPlayingMetaData == null || !lastNowPlayingMetaData.equals(hashmap) && !postDelayedMetadataUpdate())
        {
            lastNowPlayingMetaData = hashmap;
            String s = nowplaying.getZoneGroup().createZoneGroupTitle(1, true).toUpperCase();
            String s1 = createTrackTitle();
            String s2 = createArtistName();
            StringBuilder stringbuilder = (new StringBuilder()).append(s);
            String s3;
            String s4;
            String s5;
            android.media.RemoteControlClient.MetadataEditor metadataeditor;
            if(StringUtils.isNotEmptyOrNull(s2))
                s3 = (new StringBuilder()).append(" - ").append(s2).toString();
            else
                s3 = "";
            s4 = stringbuilder.append(s3).toString();
            s5 = createAlbumName();
            metadataeditor = remoteControlClient.editMetadata(true).putString(2, s4).putString(13, s4).putString(7, s1).putString(4, ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_CREATOR)).getText()).putString(6, ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_GENRE)).getText()).putString(1, s5).putBitmap(100, currentBitmap);
            if(nowplaying.getTransport().isSeekEnabled())
                metadataeditor = metadataeditor.putLong(9, ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_TRACK_DURATION)).getTextAsInt());
            metadataeditor.apply();
        }
    }

    public String createTrackTitle()
    {
        String s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_SIMPLE_STRING_1)).getText();
        if(s == null || StringUtils.isEmptyOrNull(s.trim()))
            s = ((com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData)lastNowPlayingMetaData.get(SCNPMetadataType.SC_NP_META_TRACK_NAME)).getText();
        return s;
    }

    public void ensureAudioFocus(boolean flag)
    {
        NowPlaying nowplaying = null;
        if(LibraryUtils.getCurrentZoneGroup() != null)
            nowplaying = LibraryUtils.getCurrentZoneGroup().nowPlaying;
        if(!hasAudioFocus && !audioManager.isMusicActive() && !isHouseholdPlayingP2S() && nowplaying != null && nowplaying.isPlaying() && (!wasPlaying || flag))
        {
            hasAudioFocus = audioFocusHelper.requestFocus();
            if(hasAudioFocus)
                onAudioFocusGained();
        }
        boolean flag1;
        if(nowplaying != null)
            flag1 = nowplaying.isPlaying();
        else
            flag1 = false;
        wasPlaying = flag1;
    }

    public void onGainedAudioFocus()
    {
        hasAudioFocus = true;
        onAudioFocusGained();
    }

    public void onLostAudioFocus(boolean flag)
    {
        if(!flag)
        {
            hasAudioFocus = false;
            onAudioFocusLost();
        }
    }

    public void onProgressChange(final SCINowPlayingTransport nowPlaying, final long progress, boolean flag)
    {
        if(!flag && delayPositionUpdate == null)
        {
            Looper.myQueue().removeIdleHandler(idleHandler);
            idleHandler = new android.os.MessageQueue.IdleHandler() {

                public boolean queueIdle()
                {
                    updatePlaybackState(nowPlaying, progress);
                    return false;
                }

                final LockScreenController this$0;
                final SCINowPlayingTransport val$nowPlaying;
                final long val$progress;

            
            {
                this$0 = LockScreenController.this;
                nowPlaying = scinowplayingtransport;
                progress = l;
                super();
            }
            }
;
            Looper.myQueue().addIdleHandler(idleHandler);
        }
    }

    public void setTransportViewController(com.sonos.acr.nowplaying.views.TransportView.TransportEventListener transporteventlistener)
    {
        SLog.e(LOG_TAG, "TransportEventListener Set");
        transportEventListener = transporteventlistener;
    }

    public void start()
    {
        SLog.i(LOG_TAG, "Starting Lock screen Controller");
        zoneGroupController.getTransportViewController().addView(this);
        IntentFilter intentfilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentfilter.addAction("android.intent.action.USER_PRESENT");
        intentfilter.addAction("android.intent.action.SCREEN_ON");
        context.registerReceiver(userInteractionReceiver, intentfilter);
        ensureAudioFocus(false);
    }

    public void stop()
    {
        SLog.i(LOG_TAG, "Stopping Lock screen Controller");
        remoteControlClient.editMetadata(true).apply();
        remoteControlClient.setPlaybackState(0);
        if(audioFocusHelper.abandonFocus())
        {
            hasAudioFocus = false;
            onAudioFocusLost();
        } else
        {
            SLog.e(LOG_TAG, "Failed to abandon focus");
        }
        context.unregisterReceiver(userInteractionReceiver);
        continuousVolumeHelper.cleanup();
        zoneGroupController.getTransportViewController().removeView(this);
        audioManager.unregisterRemoteControlClient(remoteControlClient);
        audioManager.unregisterMediaButtonEventReceiver(mediaButtonReceiver);
    }

    public void updatePlaybackState(SCINowPlayingTransport scinowplayingtransport, long l)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("On updating playback state: ").append(l).toString());
        if(!hasAudioFocus) goto _L2; else goto _L1
_L1:
        if(android.os.Build.VERSION.SDK_INT < 18) goto _L4; else goto _L3
_L3:
        class _cls6
        {

            static final int $SwitchMap$com$sonos$sclib$SCNPPlaybackState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState = new int[SCNPPlaybackState.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls6..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scinowplayingtransport.getPlaybackState().ordinal()];
        JVM INSTR tableswitch 1 3: default 80
    //                   1 81
    //                   2 94
    //                   3 107;
           goto _L2 _L5 _L6 _L7
_L2:
        return;
_L5:
        remoteControlClient.setPlaybackState(3, l, 1.0F);
        continue; /* Loop/switch isn't completed */
_L6:
        remoteControlClient.setPlaybackState(2, l, 1.0F);
        continue; /* Loop/switch isn't completed */
_L7:
        remoteControlClient.setPlaybackState(1, l, 1.0F);
        if(true) goto _L2; else goto _L4
_L4:
        switch(_cls6..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scinowplayingtransport.getPlaybackState().ordinal()])
        {
        case 1: // '\001'
            remoteControlClient.setPlaybackState(3);
            break;

        case 2: // '\002'
            remoteControlClient.setPlaybackState(2);
            break;

        case 3: // '\003'
            remoteControlClient.setPlaybackState(1);
            break;
        }
        if(true) goto _L2; else goto _L8
_L8:
    }

    public void updateView(NowPlaying nowplaying)
    {
        updateView(nowplaying.getZoneGroup());
    }

    public void updateView(ZoneGroup zonegroup)
    {
        updateView(zonegroup, false);
    }

    public void updateView(ZoneGroup zonegroup, boolean flag)
    {
        boolean flag1 = true;
        ensureAudioFocus(flag);
        if(hasAudioFocus && !postDelayedMetadataUpdate())
        {
            if(lastZoneGroupName != null && zonegroup != null)
            {
                String s2 = zonegroup.createZoneGroupTitle(flag1, flag1).toUpperCase();
                if(!lastZoneGroupName.equals(s2))
                    lastNowPlayingMetaData = null;
            }
            lastZoneGroup = zonegroup;
            if(zonegroup != null)
            {
                String s = zonegroup.createZoneGroupTitle(flag1, flag1);
                String s1;
                NowPlaying nowplaying;
                if(StringUtils.isEmptyOrNull(s))
                    s1 = "";
                else
                    s1 = s.toUpperCase();
                lastZoneGroupName = s1;
                nowplaying = zonegroup.nowPlaying;
                if(hasTransportStateChanged(nowplaying))
                {
                    RemoteControlClient remotecontrolclient = remoteControlClient;
                    byte byte0;
                    int i;
                    char c;
                    int j;
                    byte byte1;
                    if(nowplaying.getTransport().isPlayPauseEnabled())
                        byte0 = 8;
                    else
                        byte0 = 0;
                    if(!nowplaying.getTransport().isPreviousTrackEnabled())
                        flag1 = false;
                    i = flag1 | byte0;
                    if(nowplaying.getTransport().isNextTrackEnabled())
                        c = '\200';
                    else
                        c = '\0';
                    j = i | c;
                    if(nowplaying.getTransport().getPlayPauseDisplayState() == com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_STOP)
                        byte1 = 32;
                    else
                        byte1 = 0;
                    remotecontrolclient.setTransportControlFlags(byte1 | j);
                    if(android.os.Build.VERSION.SDK_INT >= 18)
                    {
                        RemoteControlClient remotecontrolclient1 = remoteControlClient;
                        MyOnPlaybackPositionUpdateListener myonplaybackpositionupdatelistener;
                        if(nowplaying.getTransport().isSeekEnabled())
                            myonplaybackpositionupdatelistener = new MyOnPlaybackPositionUpdateListener();
                        else
                            myonplaybackpositionupdatelistener = null;
                        remotecontrolclient1.setPlaybackPositionUpdateListener(myonplaybackpositionupdatelistener);
                    }
                    audioManager.registerRemoteControlClient(remoteControlClient);
                    if(userRoute != null)
                        userRoute.setRemoteControlClient(remoteControlClient);
                }
                onProgressChange(nowplaying.getTransport(), zoneGroupController.getTransportViewController().getElapsedTime(), false);
                albumArtController.setDefaultResourceId(getLockscreenCannedArt(nowplaying));
                albumArtController.setImageURI(nowplaying.getAlbumArtURI(albumArtController.getAlbumArtSize()));
                albumArtController.setNextImageURI(nowplaying.getNextTrackAlbumArtURI(albumArtController.getAlbumArtSize()));
                updateMetaData(nowplaying);
            } else
            {
                lastZoneGroupName = "";
            }
        }
    }

    public void updateVolume(int i)
    {
        continuousVolumeHelper.setLastEventedVolume(i);
        if(!continuousVolumeHelper.isVolumeTimerActive() && userRoute != null)
            userRoute.setVolume(i);
    }

    public static final String LOCKSCREEN_ENABLED = "LOCKSCREEN_ENABLED";
    private static final String LOG_TAG = com/sonos/acr/services/lockscreen/LockScreenController.getSimpleName();
    private static final long ON_MUSIC_CHANGED_DELAY = 2000L;
    public static final int STREAM_TYPE = 3;
    private final AlbumArtController albumArtController = new MediaRemoteAlbumArtController();
    private final AudioFocusHelper audioFocusHelper;
    private final AudioManager audioManager;
    private final Context context;
    private ContinuousVolumeHelper continuousVolumeHelper;
    private Bitmap currentBitmap;
    private Runnable delayMusicChangedHandler;
    Runnable delayPositionUpdate;
    Handler handler;
    private boolean hasAudioFocus;
    android.os.MessageQueue.IdleHandler idleHandler;
    private HashMap lastNowPlayingMetaData;
    private long lastPositionUpdateTime;
    private ZoneGroup lastZoneGroup;
    private String lastZoneGroupName;
    private final ComponentName mediaButtonReceiver;
    private final MediaRouter mediaRouter;
    boolean oldNowPlayingStates[];
    private final RemoteControlClient remoteControlClient;
    private com.sonos.acr.nowplaying.views.TransportView.TransportEventListener transportEventListener;
    private final BroadcastReceiver userInteractionReceiver = new BroadcastReceiver() {

        public void onReceive(Context context2, Intent intent1)
        {
            SLog.e(LockScreenController.LOG_TAG, (new StringBuilder()).append("action: ").append(intent1.getAction()).append(" hasAudioFocus: ").append(hasAudioFocus).append(" keyGaurdVisible: ").append(keyGuardVisible()).toString());
            LockScreenController lockscreencontroller = LockScreenController.this;
            boolean flag;
            if(("android.intent.action.SCREEN_OFF".equals(intent1.getAction()) || keyGuardVisible()) && hasAudioFocus)
                flag = true;
            else
                flag = false;
            lockscreencontroller.setVolumeTakeoverEnabled(flag);
        }

        final LockScreenController this$0;

            
            {
                this$0 = LockScreenController.this;
                super();
            }
    }
;
    private final android.media.MediaRouter.UserRouteInfo userRoute = setUpUserRoute();
    private boolean wasPlaying;
    private SonosWifiLockWatchdog watchdog;
    private final ZoneGroupController zoneGroupController;





/*
    static long access$1002(LockScreenController lockscreencontroller, long l)
    {
        lockscreencontroller.lastPositionUpdateTime = l;
        return l;
    }

*/



/*
    static Runnable access$1102(LockScreenController lockscreencontroller, Runnable runnable)
    {
        lockscreencontroller.delayMusicChangedHandler = runnable;
        return runnable;
    }

*/











/*
    static Bitmap access$702(LockScreenController lockscreencontroller, Bitmap bitmap)
    {
        lockscreencontroller.currentBitmap = bitmap;
        return bitmap;
    }

*/


}
