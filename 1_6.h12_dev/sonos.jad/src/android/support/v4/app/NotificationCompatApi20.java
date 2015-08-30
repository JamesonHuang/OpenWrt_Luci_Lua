// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            RemoteInputCompatApi20, NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions

class NotificationCompatApi20
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        public void addAction(NotificationCompatBase.Action action)
        {
            NotificationCompatApi20.addAction(b, action);
        }

        public Notification build()
        {
            b.setExtras(mExtras);
            return b.build();
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private Bundle mExtras;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, ArrayList arraylist, Bundle bundle, String s, 
                boolean flag4, String s1)
        {
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
            b = builder3.setFullScreenIntent(pendingintent1, flag8).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag).setLocalOnly(flag3).setGroup(s).setGroupSummary(flag4).setSortKey(s1);
            mExtras = new Bundle();
            if(bundle != null)
                mExtras.putAll(bundle);
            if(arraylist != null && !arraylist.isEmpty())
                mExtras.putStringArray("android.people", (String[])arraylist.toArray(new String[arraylist.size()]));
        }
    }


    NotificationCompatApi20()
    {
    }

    public static void addAction(android.app.Notification.Builder builder, NotificationCompatBase.Action action)
    {
        android.app.Notification.Action.Builder builder1 = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
        if(action.getRemoteInputs() != null)
        {
            android.app.RemoteInput aremoteinput[] = RemoteInputCompatApi20.fromCompat(action.getRemoteInputs());
            int i = aremoteinput.length;
            for(int j = 0; j < i; j++)
                builder1.addRemoteInput(aremoteinput[j]);

        }
        if(action.getExtras() != null)
            builder1.addExtras(action.getExtras());
        builder.addAction(builder1.build());
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        return getActionCompatFromAction(notification.actions[i], factory, factory1);
    }

    private static NotificationCompatBase.Action getActionCompatFromAction(android.app.Notification.Action action, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput[] = RemoteInputCompatApi20.toCompat(action.getRemoteInputs(), factory1);
        return factory.build(action.icon, action.title, action.actionIntent, action.getExtras(), aremoteinput);
    }

    private static android.app.Notification.Action getActionFromActionCompat(NotificationCompatBase.Action action)
    {
        android.app.Notification.Action.Builder builder = (new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent())).addExtras(action.getExtras());
        RemoteInputCompatBase.RemoteInput aremoteinput[] = action.getRemoteInputs();
        if(aremoteinput != null)
        {
            android.app.RemoteInput aremoteinput1[] = RemoteInputCompatApi20.fromCompat(aremoteinput);
            int i = aremoteinput1.length;
            for(int j = 0; j < i; j++)
                builder.addRemoteInput(aremoteinput1[j]);

        }
        return builder.build();
    }

    public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList arraylist, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        NotificationCompatBase.Action aaction[];
        if(arraylist == null)
        {
            aaction = null;
        } else
        {
            aaction = factory.newArray(arraylist.size());
            int i = 0;
            while(i < aaction.length) 
            {
                aaction[i] = getActionCompatFromAction((android.app.Notification.Action)arraylist.get(i), factory, factory1);
                i++;
            }
        }
        return aaction;
    }

    public static String getGroup(Notification notification)
    {
        return notification.getGroup();
    }

    public static boolean getLocalOnly(Notification notification)
    {
        boolean flag;
        if((0x100 & notification.flags) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static ArrayList getParcelableArrayListForActions(NotificationCompatBase.Action aaction[])
    {
        ArrayList arraylist;
        if(aaction == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList(aaction.length);
            int i = aaction.length;
            int j = 0;
            while(j < i) 
            {
                arraylist.add(getActionFromActionCompat(aaction[j]));
                j++;
            }
        }
        return arraylist;
    }

    public static String getSortKey(Notification notification)
    {
        return notification.getSortKey();
    }

    public static boolean isGroupSummary(Notification notification)
    {
        boolean flag;
        if((0x200 & notification.flags) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }
}
