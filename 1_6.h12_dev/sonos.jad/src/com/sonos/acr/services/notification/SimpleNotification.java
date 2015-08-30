// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.sonos.acr.SonosLaunchActivity;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.services.builder.RemoteImageViewController;
import com.sonos.acr.util.AlbumArtSize;

// Referenced classes of package com.sonos.acr.services.notification:
//            SonosNotification, NotificationService

public class SimpleNotification extends SonosNotification
    implements com.sonos.acr.services.builder.RemoteImageViewController.RemoteImageViewListener
{

    public SimpleNotification(NotificationService notificationservice)
    {
        super(notificationservice);
        remoteImageViewController = new RemoteImageViewController(AlbumArtSize.SIZE_LARGE_BROWSE, 0x1020006, this, false);
    }

    public void cancel()
    {
        remoteImageViewController.cancel();
    }

    public PendingIntent getContentIntent(NotificationService notificationservice)
    {
        return PendingIntent.getActivity(notificationservice, 0, (new Intent(remoteViewService, com/sonos/acr/SonosLaunchActivity)).addFlags(0x14000000), 0);
    }

    public void onImageUpdated()
    {
        remoteViewService.updateNotification();
    }

    public void setLatestEventInfo(Context context, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent)
    {
        int i = icon;
        icon = 0;
        super.setLatestEventInfo(context, charsequence, charsequence1, pendingintent);
        icon = i;
    }

    public void showMessage(String s, long l)
    {
    }

    public void updateView(ZoneGroup zonegroup)
    {
        when = 0L;
        if(zonegroup != null)
        {
            String s = zonegroup.createZoneGroupTitle(1, true);
            setLatestEventInfo(remoteViewService, zonegroup.nowPlaying.getSingleLineMetaData(), s, contentIntent);
            remoteImageViewController.updateImage(contentView, zonegroup.nowPlaying.getDefaultAlbumArtResourceId(0x7f060022), zonegroup.nowPlaying.getAlbumArtURI(AlbumArtSize.SIZE_BROWSE), com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL);
        }
    }

    RemoteImageViewController remoteImageViewController;
}
