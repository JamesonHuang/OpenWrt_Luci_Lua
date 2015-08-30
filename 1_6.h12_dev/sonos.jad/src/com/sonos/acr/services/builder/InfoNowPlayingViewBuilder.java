// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.builder;

import android.content.Context;
import android.widget.RemoteViews;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.StringUtils;

// Referenced classes of package com.sonos.acr.services.builder:
//            FullNowPlayingRemoteViewBuilder

public class InfoNowPlayingViewBuilder extends FullNowPlayingRemoteViewBuilder
{

    public InfoNowPlayingViewBuilder(Context context, int i, Class class1)
    {
        super(context, i, class1);
    }

    public InfoNowPlayingViewBuilder setInfoMessage(String s)
    {
        infoMessage = s;
        return this;
    }

    public InfoNowPlayingViewBuilder setSystemInfoMessage(String s, boolean flag)
    {
        systemInfoMessage = s;
        canSelectRooms = flag;
        return this;
    }

    protected void updateMetadataTexts(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        super.updateMetadataTexts(remoteviews, nowplaying);
        if(StringUtils.isNotEmptyOrNull(infoMessage))
        {
            remoteviews.setTextViewText(0x7f0a00d1, infoMessage);
            remoteviews.setViewVisibility(0x7f0a00d1, 0);
            remoteviews.setViewVisibility(0x7f0a00d0, 4);
            remoteviews.setViewVisibility(0x7f0a00ce, 4);
            remoteviews.setViewVisibility(0x7f0a00cf, 4);
        } else
        if(StringUtils.isNotEmptyOrNull(systemInfoMessage))
        {
            remoteviews.setViewVisibility(0x7f0a00d1, 4);
            remoteviews.setTextViewText(0x7f0a00d0, systemInfoMessage);
            remoteviews.setViewVisibility(0x7f0a00d0, 0);
            remoteviews.setViewVisibility(0x7f0a00ce, 0);
            remoteviews.setTextViewText(0x7f0a00ce, "-");
            remoteviews.setViewVisibility(0x7f0a00cf, 0);
            remoteviews.setTextViewText(0x7f0a00cf, "-");
        } else
        {
            remoteviews.setViewVisibility(0x7f0a00d1, 4);
            remoteviews.setViewVisibility(0x7f0a00ce, 0);
            remoteviews.setViewVisibility(0x7f0a00d0, 0);
            remoteviews.setViewVisibility(0x7f0a00cf, 0);
        }
    }

    boolean canSelectRooms;
    String infoMessage;
    String systemInfoMessage;
}
