// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

class NotificationCompatIceCreamSandwich
{

    NotificationCompatIceCreamSandwich()
    {
    }

    static Notification add(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, PendingIntent pendingintent, 
            PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag)
    {
        android.app.Notification.Builder builder = (new android.app.Notification.Builder(context)).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        boolean flag1;
        android.app.Notification.Builder builder1;
        boolean flag2;
        android.app.Notification.Builder builder2;
        boolean flag3;
        android.app.Notification.Builder builder3;
        boolean flag4;
        if((2 & notification.flags) != 0)
            flag1 = true;
        else
            flag1 = false;
        builder1 = builder.setOngoing(flag1);
        if((8 & notification.flags) != 0)
            flag2 = true;
        else
            flag2 = false;
        builder2 = builder1.setOnlyAlertOnce(flag2);
        if((0x10 & notification.flags) != 0)
            flag3 = true;
        else
            flag3 = false;
        builder3 = builder2.setAutoCancel(flag3).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
        if((0x80 & notification.flags) != 0)
            flag4 = true;
        else
            flag4 = false;
        return builder3.setFullScreenIntent(pendingintent1, flag4).setLargeIcon(bitmap).setNumber(i).setProgress(j, k, flag).getNotification();
    }
}
