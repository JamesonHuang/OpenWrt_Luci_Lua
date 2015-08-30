// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.notification;

import android.app.Notification;
import android.app.PendingIntent;
import com.sonos.acr.sclib.wrappers.ZoneGroup;

// Referenced classes of package com.sonos.acr.services.notification:
//            NotificationService

public abstract class SonosNotification extends Notification
{

    protected SonosNotification(NotificationService notificationservice)
    {
        remoteViewService = notificationservice;
        icon = 0x7f020060;
        contentIntent = getContentIntent(notificationservice);
        if(android.os.Build.VERSION.SDK_INT >= 21)
            visibility = 1;
    }

    public abstract void cancel();

    public abstract PendingIntent getContentIntent(NotificationService notificationservice);

    public abstract void showMessage(String s, long l);

    public abstract void updateView(ZoneGroup zonegroup);

    protected final NotificationService remoteViewService;
}
