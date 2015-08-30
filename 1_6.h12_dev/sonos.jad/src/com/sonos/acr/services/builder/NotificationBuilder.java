// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.builder;

import android.content.Context;
import android.widget.RemoteViews;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.services.notification.NotificationService;

// Referenced classes of package com.sonos.acr.services.builder:
//            FullNowPlayingRemoteViewBuilder

public class NotificationBuilder extends FullNowPlayingRemoteViewBuilder
{

    public NotificationBuilder(Context context)
    {
        super(context, 0x7f03003a, com/sonos/acr/services/notification/NotificationService);
    }

    protected void updateMetadataTexts(RemoteViews remoteviews, NowPlaying nowplaying)
    {
        if(nowplaying != null)
            remoteviews.setTextViewText(0x7f0a00d7, nowplaying.getSingleLineMetaData());
        else
            remoteviews.setTextViewText(0x7f0a00d7, "");
    }
}
