// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.builder;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.services.notification.NotificationService;
import com.sonos.acr.services.widgets.RoomWidget;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.volume.GroupVolumeDialogActivity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.services.builder:
//            RemoteImageViewController

public class FullNowPlayingRemoteViewBuilder
{

    public FullNowPlayingRemoteViewBuilder(Context context1, int i, Class class1)
    {
        enabledAlpha = 255;
        disabledAlpha = 75;
        AlbumArtSize albumartsize;
        char c;
        if(USE_SMALL_IMAGE)
            albumartsize = AlbumArtSize.SIZE_BROWSE;
        else
            albumartsize = AlbumArtSize.SIZE_LARGE_BROWSE;
        if(USE_SMALL_IMAGE)
            c = '<';
        else
            c = '\240';
        remoteImageViewController = new RemoteImageViewController(albumartsize, 0x7f0a00cb, null, true, c);
        voting0ImageViewController = new RemoteImageViewController(AlbumArtSize.SIZE_RATINGS, 0x7f0a00d3, null, false);
        voting1ImageViewController = new RemoteImageViewController(AlbumArtSize.SIZE_RATINGS, 0x7f0a00d6, null, false);
        context = context1;
        viewLayoutId = i;
        serviceClass = class1;
    }

    public static PendingIntent getPendingIntent(Context context1, boolean flag, Intent intent, String s, int i)
    {
        intent.putExtra("com.sonos.intent.extra.ZGID", s);
        intent.setData(Uri.parse(intent.toUri(1)));
        PendingIntent pendingintent;
        if(context1 instanceof RoomWidget)
            intent.putExtra("com.sonos.intent.extra.VIEW_ID", com.sonos.sclib.SCIAppReporting.SCViewID.WIDGET);
        else
        if(context1 instanceof NotificationService)
            intent.putExtra("com.sonos.intent.extra.VIEW_ID", com.sonos.sclib.SCIAppReporting.SCViewID.NOTIFICATIONS);
        if(flag)
            pendingintent = PendingIntent.getService(context1, i, intent, 0x8000000);
        else
            pendingintent = PendingIntent.getActivity(context1, i, intent, 0x8000000);
        return pendingintent;
    }

    private void updateAlbumArt(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
        {
            remoteImageViewController.updateImage(remoteviews, nowplaying.getDefaultAlbumArtResourceId(), nowplaying.getAlbumArtURI(remoteImageViewController.getAlbumArtSize()), com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL);
            remoteImageViewController.updateNextAlbumArtUri(nowplaying.getNextTrackAlbumArtURI(remoteImageViewController.getAlbumArtSize()));
        } else
        {
            remoteImageViewController.updateImage(remoteviews, 0x7f060022, null, null);
        }
    }

    public void cancel()
    {
        remoteImageViewController.cancel();
        voting0ImageViewController.cancel();
        voting1ImageViewController.cancel();
    }

    public final RemoteViews createView(ZoneGroup zonegroup, int i)
    {
        appWidgetId = i;
        RemoteViews remoteviews = new RemoteViews(context.getPackageName(), viewLayoutId);
        onViewCreated(remoteviews, zonegroup);
        return remoteviews;
    }

    void onViewCreated(RemoteViews remoteviews, ZoneGroup zonegroup)
    {
        NowPlaying nowplaying;
        DeviceVolume devicevolume;
        if(zonegroup != null)
            nowplaying = zonegroup.nowPlaying;
        else
            nowplaying = null;
        updateIntents(remoteviews, nowplaying);
        updateMetadataTexts(remoteviews, nowplaying);
        updateZoneGroupText(remoteviews, nowplaying);
        updateAlbumArt(remoteviews, nowplaying);
        updateButtons(remoteviews, nowplaying);
        updateVotingButtons(remoteviews, nowplaying);
        devicevolume = null;
        if(zonegroup != null)
        {
            GroupVolume groupvolume = zonegroup.getGroupVolume();
            if(groupvolume != null)
                devicevolume = groupvolume.getGroupVolume();
        }
        updateGroupVolume(remoteviews, devicevolume);
    }

    public FullNowPlayingRemoteViewBuilder setDisabledAlpha(int i)
    {
        disabledAlpha = i;
        return this;
    }

    public FullNowPlayingRemoteViewBuilder setEnabledAlpha(int i)
    {
        enabledAlpha = i;
        return this;
    }

    public FullNowPlayingRemoteViewBuilder setImageViewListener(RemoteImageViewController.RemoteImageViewListener remoteimageviewlistener)
    {
        imageViewListener = remoteimageviewlistener;
        remoteImageViewController.setListener(remoteimageviewlistener);
        voting0ImageViewController.setListener(remoteimageviewlistener);
        voting1ImageViewController.setListener(remoteimageviewlistener);
        return this;
    }

    protected void setViewEnabled(RemoteViews remoteviews, int i, boolean flag)
    {
        int j;
        if(flag)
            j = enabledAlpha;
        else
            j = disabledAlpha;
        remoteviews.setInt(i, "setAlpha", j);
        remoteviews.setBoolean(i, "setEnabled", flag);
    }

    protected void updateButtons(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying == null)
            break MISSING_BLOCK_LABEL_125;
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState = new int[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_PLAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_PAUSE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_STOP.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState[nowplaying.getPlayPauseDisplayState().ordinal()];
        JVM INSTR tableswitch 1 3: default 40
    //                   1 86
    //                   2 99
    //                   3 112;
           goto _L1 _L2 _L3 _L4
_L1:
        setViewEnabled(remoteviews, 0x7f0a00d5, nowplaying.getTransport().isPlayPauseEnabled());
        setViewEnabled(remoteviews, 0x7f0a0063, nowplaying.getTransport().isNextTrackEnabled());
        setViewEnabled(remoteviews, 0x7f0a00d4, nowplaying.getTransport().isPreviousTrackEnabled());
_L5:
        return;
_L2:
        remoteviews.setImageViewResource(0x7f0a00d5, 0x7f020082);
          goto _L1
_L3:
        remoteviews.setImageViewResource(0x7f0a00d5, 0x7f020080);
          goto _L1
_L4:
        remoteviews.setImageViewResource(0x7f0a00d5, 0x7f020087);
          goto _L1
        remoteviews.setImageViewResource(0x7f0a00d5, 0x7f020082);
        setViewEnabled(remoteviews, 0x7f0a00d5, false);
        setViewEnabled(remoteviews, 0x7f0a0063, false);
        setViewEnabled(remoteviews, 0x7f0a00d4, false);
          goto _L5
    }

    protected void updateGroupVolume(RemoteViews remoteviews, DeviceVolume devicevolume)
    {
        if(devicevolume != null)
        {
            if(devicevolume.isMuted())
                remoteviews.setImageViewResource(0x7f0a00d2, 0x7f02007f);
            else
                remoteviews.setImageViewResource(0x7f0a00d2, 0x7f020089);
            setViewEnabled(remoteviews, 0x7f0a00d2, true);
        } else
        {
            setViewEnabled(remoteviews, 0x7f0a00d2, false);
        }
    }

    protected void updateIntents(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
        {
            String s = nowplaying.getZoneGroup().getID();
            updateViewIntent(true, remoteviews, 0x7f0a0063, (new Intent(context, serviceClass)).setAction("com.sonos.intent.action.TRANSPORT_NEXT"), s);
            updateViewIntent(true, remoteviews, 0x7f0a00d5, (new Intent(context, serviceClass)).setAction("com.sonos.intent.action.TRANSPORT_PLAYPAUSE"), s);
            updateViewIntent(true, remoteviews, 0x7f0a00d4, (new Intent(context, serviceClass)).setAction("com.sonos.intent.action.TRANSPORT_PREV"), s);
            updateViewIntent(true, remoteviews, voting0ImageViewController.getImageViewId(), (new Intent(context, serviceClass)).setAction("com.sonos.intent.action.VOTE").putExtra("com.sonos.intent.extra.VOTE_ID", 0), s);
            updateViewIntent(true, remoteviews, voting1ImageViewController.getImageViewId(), (new Intent(context, serviceClass)).setAction("com.sonos.intent.action.VOTE").putExtra("com.sonos.intent.extra.VOTE_ID", 1), s);
            updateViewIntent(true, remoteviews, 0x7f0a00cc, (new Intent(context, serviceClass)).setAction("ACTION_END_NOTIFICATIONS"), s);
            updateViewIntent(false, remoteviews, 0x7f0a00d2, (new Intent(context, com/sonos/acr/volume/GroupVolumeDialogActivity)).addFlags(0x10000000).setAction(String.valueOf(System.identityHashCode(this))), s);
        }
    }

    protected void updateMetadataTexts(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
        {
            String as[] = nowplaying.getTripleLineMetaData();
            if(as != null)
            {
                remoteviews.setTextViewText(0x7f0a00ce, as[0]);
                String s;
                if(StringUtils.isEmptyOrNull(as[1]))
                    s = as[2];
                else
                if(StringUtils.isEmptyOrNull(as[2]))
                {
                    s = as[1];
                } else
                {
                    Object aobj[] = new Object[2];
                    aobj[0] = as[1];
                    aobj[1] = as[2];
                    s = String.format("%s \u2013 %s", aobj);
                }
                remoteviews.setTextViewText(0x7f0a00cf, s);
            } else
            {
                remoteviews.setTextViewText(0x7f0a00ce, "");
                remoteviews.setTextViewText(0x7f0a00cf, "");
            }
        } else
        {
            remoteviews.setTextViewText(0x7f0a00ce, "");
            remoteviews.setTextViewText(0x7f0a00cf, "");
        }
    }

    protected void updateRatingView(RemoteViews remoteviews, SCINowPlayingRatings scinowplayingratings, int i, RemoteImageViewController remoteimageviewcontroller)
    {
        int j = remoteimageviewcontroller.getImageViewId();
        if(i >= scinowplayingratings.numberOfRatings() || !scinowplayingratings.isRatingVisible(i))
        {
            remoteviews.setViewVisibility(j, 4);
        } else
        {
            remoteviews.setViewVisibility(j, 0);
            com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = scinowplayingratings.getRatingImageType(i);
            remoteimageviewcontroller.updateImage(remoteviews, 0, scinowplayingratings.getRatingImageURL(i), scalbumarttype);
        }
    }

    protected void updateTVEqButtons(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        int i = voting0ImageViewController.getImageViewId();
        int j = voting1ImageViewController.getImageViewId();
        remoteviews.setViewVisibility(i, 0);
        if(nowplaying.getNightMode())
            remoteviews.setImageViewResource(i, 0x7f02005f);
        else
            remoteviews.setImageViewResource(i, 0x7f02005e);
        remoteviews.setViewVisibility(j, 0);
        if(nowplaying.getTVDialogEnhancement())
            remoteviews.setImageViewResource(j, 0x7f020076);
        else
            remoteviews.setImageViewResource(j, 0x7f020075);
    }

    protected void updateViewIntent(boolean flag, RemoteViews remoteviews, int i, Intent intent, String s)
    {
        PendingIntent pendingintent = getPendingIntent(context, flag, intent, s, appWidgetId);
        if(pendingintent != null)
            remoteviews.setOnClickPendingIntent(i, pendingintent);
    }

    protected void updateVotingButtons(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
        {
            SCINowPlayingRatings scinowplayingratings = nowplaying.getRatings();
            if(scinowplayingratings != null)
            {
                if(scinowplayingratings.numberOfRatings() == 1)
                {
                    remoteviews.setViewVisibility(voting0ImageViewController.getImageViewId(), 4);
                    updateRatingView(remoteviews, scinowplayingratings, 0, voting1ImageViewController);
                } else
                {
                    updateRatingView(remoteviews, scinowplayingratings, 0, voting0ImageViewController);
                    updateRatingView(remoteviews, scinowplayingratings, 1, voting1ImageViewController);
                }
                if(nowplaying.getSourceType() == SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
                    updateTVEqButtons(remoteviews, nowplaying);
            }
        } else
        {
            remoteviews.setViewVisibility(voting0ImageViewController.getImageViewId(), 4);
            remoteviews.setViewVisibility(voting1ImageViewController.getImageViewId(), 4);
        }
    }

    protected void updateZoneGroupText(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
        {
            String s = nowplaying.getZoneGroup().createZoneGroupTitle(1, true);
            if(s != null)
                s = s.toUpperCase();
            remoteviews.setTextViewText(0x7f0a00d0, s);
        }
    }

    public static final boolean USE_SMALL_IMAGE;
    public final String LOG_TAG = getClass().getSimpleName();
    protected int appWidgetId;
    protected Context context;
    int disabledAlpha;
    int enabledAlpha;
    RemoteImageViewController.RemoteImageViewListener imageViewListener;
    private final RemoteImageViewController remoteImageViewController;
    Class serviceClass;
    int viewLayoutId;
    private final RemoteImageViewController voting0ImageViewController;
    private final RemoteImageViewController voting1ImageViewController;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT < 11)
            flag = true;
        else
            flag = false;
        USE_SMALL_IMAGE = flag;
    }
}
