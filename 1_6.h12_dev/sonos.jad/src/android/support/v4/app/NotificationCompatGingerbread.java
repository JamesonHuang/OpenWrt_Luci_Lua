// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;

class NotificationCompatGingerbread
{

    NotificationCompatGingerbread()
    {
    }

    public static Notification add(Notification notification, Context context, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        notification.setLatestEventInfo(context, charsequence, charsequence1, pendingintent);
        notification.fullScreenIntent = pendingintent1;
        return notification;
    }
}
