// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompatJellybean, NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions

class NotificationCompatKitKat
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        public void addAction(NotificationCompatBase.Action action)
        {
            mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(b, action));
        }

        public Notification build()
        {
            SparseArray sparsearray = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
            if(sparsearray != null)
                mExtras.putSparseParcelableArray("android.support.actionExtras", sparsearray);
            b.setExtras(mExtras);
            return b.build();
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private List mActionExtrasList;
        private Bundle mExtras;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, ArrayList arraylist, Bundle bundle, String s, 
                boolean flag4, String s1)
        {
            mActionExtrasList = new ArrayList();
            android.app.Notification.Builder builder = (new android.app.Notification.Builder(context)).setWhen(notification.when).setShowWhen(flag1).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean flag5;
            android.app.Notification.Builder builder1;
            boolean flag6;
            android.app.Notification.Builder builder2;
            boolean flag7;
            android.app.Notification.Builder builder3;
            boolean flag8;
            if((2 & notification.flags) != 0)
                flag5 = true;
            else
                flag5 = false;
            builder1 = builder.setOngoing(flag5);
            if((8 & notification.flags) != 0)
                flag6 = true;
            else
                flag6 = false;
            builder2 = builder1.setOnlyAlertOnce(flag6);
            if((0x10 & notification.flags) != 0)
                flag7 = true;
            else
                flag7 = false;
            builder3 = builder2.setAutoCancel(flag7).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setSubText(charsequence3).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((0x80 & notification.flags) != 0)
                flag8 = true;
            else
                flag8 = false;
            b = builder3.setFullScreenIntent(pendingintent1, flag8).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag);
            mExtras = new Bundle();
            if(bundle != null)
                mExtras.putAll(bundle);
            if(arraylist != null && !arraylist.isEmpty())
                mExtras.putStringArray("android.people", (String[])arraylist.toArray(new String[arraylist.size()]));
            if(flag3)
                mExtras.putBoolean("android.support.localOnly", true);
            if(s != null)
            {
                mExtras.putString("android.support.groupKey", s);
                if(flag4)
                    mExtras.putBoolean("android.support.isGroupSummary", true);
                else
                    mExtras.putBoolean("android.support.useSideChannel", true);
            }
            if(s1 != null)
                mExtras.putString("android.support.sortKey", s1);
        }
    }


    NotificationCompatKitKat()
    {
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        android.app.Notification.Action action = notification.actions[i];
        Bundle bundle = null;
        SparseArray sparsearray = notification.extras.getSparseParcelableArray("android.support.actionExtras");
        if(sparsearray != null)
            bundle = (Bundle)sparsearray.get(i);
        return NotificationCompatJellybean.readAction(factory, factory1, action.icon, action.title, action.actionIntent, bundle);
    }

    public static int getActionCount(Notification notification)
    {
        int i;
        if(notification.actions != null)
            i = notification.actions.length;
        else
            i = 0;
        return i;
    }

    public static Bundle getExtras(Notification notification)
    {
        return notification.extras;
    }

    public static String getGroup(Notification notification)
    {
        return notification.extras.getString("android.support.groupKey");
    }

    public static boolean getLocalOnly(Notification notification)
    {
        return notification.extras.getBoolean("android.support.localOnly");
    }

    public static String getSortKey(Notification notification)
    {
        return notification.extras.getString("android.support.sortKey");
    }

    public static boolean isGroupSummary(Notification notification)
    {
        return notification.extras.getBoolean("android.support.isGroupSummary");
    }
}
