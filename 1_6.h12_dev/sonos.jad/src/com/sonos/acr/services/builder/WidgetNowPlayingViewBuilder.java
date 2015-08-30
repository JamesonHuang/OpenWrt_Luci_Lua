// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.builder;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.sonos.acr.SonosLaunchActivity;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.services.widgets.RoomWidgetConfigActivity;
import com.sonos.acr.services.widgets.RoomWidgetService;
import com.sonos.acr.util.StringUtils;

// Referenced classes of package com.sonos.acr.services.builder:
//            InfoNowPlayingViewBuilder

public class WidgetNowPlayingViewBuilder extends InfoNowPlayingViewBuilder
{

    public WidgetNowPlayingViewBuilder(Context context, String s)
    {
        super(context, 0x7f0300b1, com/sonos/acr/services/widgets/RoomWidgetService);
        primaryDeviceId = s;
    }

    private Intent createConfigIntent()
    {
        Intent intent = (new Intent(context, com/sonos/acr/services/widgets/RoomWidgetConfigActivity)).addFlags(0x10000000);
        if(appWidgetId != 0)
            intent.putExtra("appWidgetId", appWidgetId);
        intent.putExtra("com.sonos.intent.extra.ZDID", primaryDeviceId);
        return intent;
    }

    private Intent createHomeScreenIntent()
    {
        return (new Intent(context, com/sonos/acr/SonosLaunchActivity)).addFlags(0x14000000);
    }

    private void setMessageVisibility(RemoteViews remoteviews)
    {
        byte byte0 = 4;
        remoteviews.setViewVisibility(0x7f0a00d1, 0);
        remoteviews.setViewVisibility(0x7f0a00d0, 0);
        remoteviews.setViewVisibility(0x7f0a00ce, byte0);
        remoteviews.setViewVisibility(0x7f0a00cf, byte0);
        if(!showThirdLineMetadata)
            byte0 = 8;
        remoteviews.setViewVisibility(0x7f0a00e8, byte0);
    }

    void onViewCreated(RemoteViews remoteviews, ZoneGroup zonegroup)
    {
        super.onViewCreated(remoteviews, zonegroup);
        if(zonegroup == null) goto _L2; else goto _L1
_L1:
        if(!zonegroup.isCompatible())
        {
            setMessageVisibility(remoteviews);
            remoteviews.setTextViewText(0x7f0a00d1, zonegroup.getZoneMenuStatusText(context));
        }
_L4:
        return;
_L2:
        if(systemInfoMessage == null)
            setSystemInfoMessage(context.getString(0x7f0c011b), true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setShowThirdLineMetadata(boolean flag)
    {
        showThirdLineMetadata = flag;
    }

    protected void updateIntents(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        super.updateIntents(remoteviews, nowplaying);
        if(nowplaying != null)
        {
            String s = nowplaying.getZoneGroup().getID();
            updateViewIntent(false, remoteviews, 0x7f0a00ca, createHomeScreenIntent(), s);
            updateViewIntent(false, remoteviews, 0x7f0a00d0, createConfigIntent(), s);
        } else
        {
            updateViewIntent(false, remoteviews, 0x7f0a00ca, createHomeScreenIntent(), null);
            if(canSelectRooms)
                updateViewIntent(false, remoteviews, 0x7f0a00d0, createConfigIntent(), null);
            else
                updateViewIntent(false, remoteviews, 0x7f0a00d0, createHomeScreenIntent(), null);
        }
    }

    protected void updateMetadataTexts(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        int i = 0;
        if(nowplaying != null)
        {
            remoteviews.setViewVisibility(0x7f0a00d0, 0);
            remoteviews.setViewVisibility(0x7f0a01fd, 8);
            if(showThirdLineMetadata)
            {
                remoteviews.setViewVisibility(0x7f0a00e8, 0);
                String as1[] = nowplaying.getTripleLineMetaData();
                String s2;
                String s3;
                String s4;
                if(as1 != null)
                    s2 = as1[0];
                else
                    s2 = "";
                remoteviews.setTextViewText(0x7f0a00ce, s2);
                if(as1 != null)
                    s3 = as1[1];
                else
                    s3 = "";
                remoteviews.setTextViewText(0x7f0a00cf, s3);
                if(as1 != null)
                    s4 = as1[2];
                else
                    s4 = "";
                remoteviews.setTextViewText(0x7f0a00e8, s4);
            } else
            {
                remoteviews.setViewVisibility(0x7f0a00e8, 8);
                String as[] = nowplaying.getDoubleLineMetaData();
                String s;
                String s1;
                if(as != null)
                    s = as[0];
                else
                    s = "";
                remoteviews.setTextViewText(0x7f0a00ce, s);
                if(as != null)
                    s1 = as[1];
                else
                    s1 = "";
                remoteviews.setTextViewText(0x7f0a00cf, s1);
            }
        } else
        {
            remoteviews.setTextViewText(0x7f0a00ce, "");
            remoteviews.setTextViewText(0x7f0a00cf, "");
            remoteviews.setTextViewText(0x7f0a00e8, "");
            remoteviews.setViewVisibility(0x7f0a00d0, 4);
            remoteviews.setViewVisibility(0x7f0a01fd, 8);
        }
        if(StringUtils.isNotEmptyOrNull(infoMessage))
        {
            remoteviews.setTextViewText(0x7f0a00d1, infoMessage);
            remoteviews.setViewVisibility(0x7f0a00d1, 0);
            byte byte1;
            if(showThirdLineMetadata)
                byte1 = 3;
            else
                byte1 = 2;
            remoteviews.setInt(0x7f0a00d1, "setMaxLines", byte1);
            remoteviews.setViewVisibility(0x7f0a00ce, 8);
            remoteviews.setViewVisibility(0x7f0a00cf, 8);
            remoteviews.setViewVisibility(0x7f0a00e8, 8);
            remoteviews.setViewVisibility(0x7f0a00d0, 0);
            remoteviews.setViewVisibility(0x7f0a01fd, 8);
        } else
        if(StringUtils.isNotEmptyOrNull(systemInfoMessage))
        {
            remoteviews.setViewVisibility(0x7f0a00d1, 4);
            int k;
            byte byte0;
            if(canSelectRooms)
                remoteviews.setTextViewText(0x7f0a00d0, systemInfoMessage);
            else
                remoteviews.setTextViewText(0x7f0a01fd, systemInfoMessage);
            remoteviews.setViewVisibility(0x7f0a00ce, 0);
            remoteviews.setTextViewText(0x7f0a00ce, "-");
            remoteviews.setViewVisibility(0x7f0a00cf, 0);
            remoteviews.setTextViewText(0x7f0a00cf, "-");
            if(showThirdLineMetadata)
                k = 0;
            else
                k = 8;
            remoteviews.setViewVisibility(0x7f0a00e8, k);
            remoteviews.setTextViewText(0x7f0a00e8, "-");
            if(canSelectRooms)
                byte0 = 8;
            else
                byte0 = 0;
            remoteviews.setViewVisibility(0x7f0a01fd, byte0);
            if(!canSelectRooms)
                i = 8;
            remoteviews.setViewVisibility(0x7f0a00d0, i);
        } else
        {
            remoteviews.setViewVisibility(0x7f0a00d1, 8);
            remoteviews.setViewVisibility(0x7f0a00ce, 0);
            remoteviews.setViewVisibility(0x7f0a00cf, 0);
            int j;
            if(showThirdLineMetadata)
                j = 0;
            else
                j = 8;
            remoteviews.setViewVisibility(0x7f0a00e8, j);
            remoteviews.setViewVisibility(0x7f0a01fd, 8);
            remoteviews.setViewVisibility(0x7f0a00d0, 0);
        }
    }

    protected void updateZoneGroupText(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(StringUtils.isEmptyOrNull(primaryDeviceId) || nowplaying == null)
        {
            super.updateZoneGroupText(remoteviews, nowplaying);
        } else
        {
            setSystemInfoMessage(null, false);
            String s = nowplaying.getZoneGroup().createZoneGroupTitle(1, true, primaryDeviceId);
            if(s != null)
                s = s.toUpperCase();
            remoteviews.setTextViewText(0x7f0a00d0, s);
        }
    }

    private String primaryDeviceId;
    private boolean showThirdLineMetadata;
}
