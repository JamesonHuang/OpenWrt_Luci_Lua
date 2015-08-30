// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import com.sonos.acr.SonosLaunchActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.services.builder.*;

// Referenced classes of package com.sonos.acr.services.notification:
//            SonosNotification, NotificationService

public class ComplexSonosNotification extends SonosNotification
    implements com.sonos.acr.services.builder.RemoteImageViewController.RemoteImageViewListener
{

    public ComplexSonosNotification(NotificationService notificationservice)
    {
        super(notificationservice);
        resetInfoMessage = new Runnable() {

            public void run()
            {
                bigNotificationBuilder.setInfoMessage(null);
                forceUpdate();
            }

            final ComplexSonosNotification this$0;

            
            {
                this$0 = ComplexSonosNotification.this;
                super();
            }
        }
;
        handler = SonosApplication.getInstance().getHandler();
        notificationContentBuilder = new NotificationBuilder(remoteViewService);
        notificationContentBuilder.setImageViewListener(this).setEnabledAlpha(205).setDisabledAlpha(75);
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            bigNotificationBuilder = new InfoNowPlayingViewBuilder(remoteViewService, 0x7f030039, com/sonos/acr/services/notification/NotificationService);
            bigNotificationBuilder.setImageViewListener(this).setEnabledAlpha(255).setDisabledAlpha(75);
        }
    }

    private void forceUpdate()
    {
        remoteViewService.updateNotification();
    }

    public void cancel()
    {
        notificationContentBuilder.cancel();
        if(bigNotificationBuilder != null)
            bigNotificationBuilder.cancel();
    }

    public PendingIntent getContentIntent(NotificationService notificationservice)
    {
        Intent intent = (new Intent(notificationservice, com/sonos/acr/SonosLaunchActivity)).addFlags(0x10000000);
        NotificationBuilder _tmp = notificationContentBuilder;
        return NotificationBuilder.getPendingIntent(notificationservice, false, intent, null, 0);
    }

    public void onImageUpdated()
    {
        forceUpdate();
    }

    public void showMessage(String s, long l)
    {
        if(bigNotificationBuilder != null)
        {
            bigNotificationBuilder.setInfoMessage(s);
            handler.removeCallbacks(resetInfoMessage);
            handler.postDelayed(resetInfoMessage, l);
        }
    }

    public void updateView(ZoneGroup zonegroup)
    {
        contentView = notificationContentBuilder.createView(zonegroup, 0);
        if(bigNotificationBuilder != null)
            bigContentView = bigNotificationBuilder.createView(zonegroup, 0);
    }

    private InfoNowPlayingViewBuilder bigNotificationBuilder;
    Handler handler;
    private NotificationBuilder notificationContentBuilder;
    private Runnable resetInfoMessage;


}
