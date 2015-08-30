// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;

class NotificationManagerCompatEclair
{

    NotificationManagerCompatEclair()
    {
    }

    static void cancelNotification(NotificationManager notificationmanager, String s, int i)
    {
        notificationmanager.cancel(s, i);
    }

    public static void postNotification(NotificationManager notificationmanager, String s, int i, Notification notification)
    {
        notificationmanager.notify(s, i, notification);
    }
}
