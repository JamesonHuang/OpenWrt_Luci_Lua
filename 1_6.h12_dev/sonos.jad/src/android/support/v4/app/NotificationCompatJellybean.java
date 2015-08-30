// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, BundleUtil, RemoteInputCompatJellybean, NotificationBuilderWithActions

class NotificationCompatJellybean
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
            Notification notification = b.build();
            Bundle bundle = NotificationCompatJellybean.getExtras(notification);
            Bundle bundle1 = new Bundle(mExtras);
            Iterator iterator = mExtras.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = (String)iterator.next();
                if(bundle.containsKey(s))
                    bundle1.remove(s);
            } while(true);
            bundle.putAll(bundle1);
            SparseArray sparsearray = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
            if(sparsearray != null)
                NotificationCompatJellybean.getExtras(notification).putSparseParcelableArray("android.support.actionExtras", sparsearray);
            return notification;
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private List mActionExtrasList;
        private final Bundle mExtras = new Bundle();

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                int l, CharSequence charsequence3, boolean flag2, Bundle bundle, String s, boolean flag3, String s1)
        {
            mActionExtrasList = new ArrayList();
            android.app.Notification.Builder builder = (new android.app.Notification.Builder(context)).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean flag4;
            android.app.Notification.Builder builder1;
            boolean flag5;
            android.app.Notification.Builder builder2;
            boolean flag6;
            android.app.Notification.Builder builder3;
            boolean flag7;
            if((2 & notification.flags) != 0)
                flag4 = true;
            else
                flag4 = false;
            builder1 = builder.setOngoing(flag4);
            if((8 & notification.flags) != 0)
                flag5 = true;
            else
                flag5 = false;
            builder2 = builder1.setOnlyAlertOnce(flag5);
            if((0x10 & notification.flags) != 0)
                flag6 = true;
            else
                flag6 = false;
            builder3 = builder2.setAutoCancel(flag6).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setSubText(charsequence3).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((0x80 & notification.flags) != 0)
                flag7 = true;
            else
                flag7 = false;
            b = builder3.setFullScreenIntent(pendingintent1, flag7).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag1).setPriority(l).setProgress(j, k, flag);
            if(bundle != null)
                mExtras.putAll(bundle);
            if(flag2)
                mExtras.putBoolean("android.support.localOnly", true);
            if(s != null)
            {
                mExtras.putString("android.support.groupKey", s);
                if(flag3)
                    mExtras.putBoolean("android.support.isGroupSummary", true);
                else
                    mExtras.putBoolean("android.support.useSideChannel", true);
            }
            if(s1 != null)
                mExtras.putString("android.support.sortKey", s1);
        }
    }


    NotificationCompatJellybean()
    {
    }

    public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, Bitmap bitmap, Bitmap bitmap1, boolean flag1)
    {
        android.app.Notification.BigPictureStyle bigpicturestyle = (new android.app.Notification.BigPictureStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence).bigPicture(bitmap);
        if(flag1)
            bigpicturestyle.bigLargeIcon(bitmap1);
        if(flag)
            bigpicturestyle.setSummaryText(charsequence1);
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, CharSequence charsequence2)
    {
        android.app.Notification.BigTextStyle bigtextstyle = (new android.app.Notification.BigTextStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence).bigText(charsequence2);
        if(flag)
            bigtextstyle.setSummaryText(charsequence1);
    }

    public static void addInboxStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, ArrayList arraylist)
    {
        android.app.Notification.InboxStyle inboxstyle = (new android.app.Notification.InboxStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence);
        if(flag)
            inboxstyle.setSummaryText(charsequence1);
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); inboxstyle.addLine((CharSequence)iterator.next()));
    }

    public static SparseArray buildActionExtrasMap(List list)
    {
        SparseArray sparsearray = null;
        int i = 0;
        for(int j = list.size(); i < j; i++)
        {
            Bundle bundle = (Bundle)list.get(i);
            if(bundle == null)
                continue;
            if(sparsearray == null)
                sparsearray = new SparseArray();
            sparsearray.put(i, bundle);
        }

        return sparsearray;
    }

    private static boolean ensureActionReflectionReadyLocked()
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = true;
        if(!sActionsAccessFailed) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        try
        {
            if(sActionsField == null)
            {
                sActionClass = Class.forName("android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField("icon");
                sActionTitleField = sActionClass.getDeclaredField("title");
                sActionIntentField = sActionClass.getDeclaredField("actionIntent");
                sActionsField = android/app/Notification.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            Log.e("NotificationCompat", "Unable to access notification actions", classnotfoundexception);
            sActionsAccessFailed = flag1;
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            Log.e("NotificationCompat", "Unable to access notification actions", nosuchfieldexception);
            sActionsAccessFailed = flag1;
        }
        if(sActionsAccessFailed)
            flag1 = false;
        flag = flag1;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        Object obj = sActionsLock;
        obj;
        JVM INSTR monitorenter ;
        NotificationCompatBase.Action action;
        NotificationCompatBase.Action action1;
        try
        {
            Object obj1 = getActionObjectsLocked(notification)[i];
            Bundle bundle = null;
            Bundle bundle1 = getExtras(notification);
            if(bundle1 != null)
            {
                SparseArray sparsearray = bundle1.getSparseParcelableArray("android.support.actionExtras");
                if(sparsearray != null)
                    bundle = (Bundle)sparsearray.get(i);
            }
            action1 = readAction(factory, factory1, sActionIconField.getInt(obj1), (CharSequence)sActionTitleField.get(obj1), (PendingIntent)sActionIntentField.get(obj1), bundle);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Log.e("NotificationCompat", "Unable to access notification actions", illegalaccessexception);
            sActionsAccessFailed = true;
            action = null;
            break MISSING_BLOCK_LABEL_137;
        }
        action = action1;
        obj;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_137;
        Exception exception;
        exception;
        throw exception;
        return action;
    }

    public static int getActionCount(Notification notification)
    {
        Object obj = sActionsLock;
        obj;
        JVM INSTR monitorenter ;
        Object aobj[] = getActionObjectsLocked(notification);
        int i;
        if(aobj != null)
            i = aobj.length;
        else
            i = 0;
        return i;
    }

    private static NotificationCompatBase.Action getActionFromBundle(Bundle bundle, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        return factory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), factory1));
    }

    private static Object[] getActionObjectsLocked(Notification notification)
    {
        Object obj = sActionsLock;
        obj;
        JVM INSTR monitorenter ;
        if(ensureActionReflectionReadyLocked()) goto _L2; else goto _L1
_L1:
        Object aobj[] = null;
_L4:
        return aobj;
_L2:
        aobj = (Object[])(Object[])sActionsField.get(notification);
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.e("NotificationCompat", "Unable to access notification actions", illegalaccessexception);
        sActionsAccessFailed = true;
        obj;
        JVM INSTR monitorexit ;
        aobj = null;
        if(true) goto _L4; else goto _L3
_L3:
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
                aaction[i] = getActionFromBundle((Bundle)arraylist.get(i), factory, factory1);
                i++;
            }
        }
        return aaction;
    }

    private static Bundle getBundleForAction(NotificationCompatBase.Action action)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("icon", action.getIcon());
        bundle.putCharSequence("title", action.getTitle());
        bundle.putParcelable("actionIntent", action.getActionIntent());
        bundle.putBundle("extras", action.getExtras());
        bundle.putParcelableArray("remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle;
    }

    public static Bundle getExtras(Notification notification)
    {
        Object obj = sExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(!sExtrasFieldAccessFailed) goto _L2; else goto _L1
_L1:
        Bundle bundle = null;
_L3:
        return bundle;
_L2:
        Field field;
        if(sExtrasField != null)
            break MISSING_BLOCK_LABEL_80;
        field = android/app/Notification.getDeclaredField("extras");
        if(android/os/Bundle.isAssignableFrom(field.getType()))
            break MISSING_BLOCK_LABEL_69;
        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
        sExtrasFieldAccessFailed = true;
        obj;
        JVM INSTR monitorexit ;
        bundle = null;
          goto _L3
        field.setAccessible(true);
        sExtrasField = field;
        bundle = (Bundle)sExtrasField.get(notification);
        if(bundle == null)
        {
            bundle = new Bundle();
            sExtrasField.set(notification, bundle);
        }
        obj;
        JVM INSTR monitorexit ;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.e("NotificationCompat", "Unable to access notification extras", illegalaccessexception);
_L4:
        sExtrasFieldAccessFailed = true;
        obj;
        JVM INSTR monitorexit ;
        bundle = null;
          goto _L3
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        Log.e("NotificationCompat", "Unable to access notification extras", nosuchfieldexception);
          goto _L4
    }

    public static String getGroup(Notification notification)
    {
        return getExtras(notification).getString("android.support.groupKey");
    }

    public static boolean getLocalOnly(Notification notification)
    {
        return getExtras(notification).getBoolean("android.support.localOnly");
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
                arraylist.add(getBundleForAction(aaction[j]));
                j++;
            }
        }
        return arraylist;
    }

    public static String getSortKey(Notification notification)
    {
        return getExtras(notification).getString("android.support.sortKey");
    }

    public static boolean isGroupSummary(Notification notification)
    {
        return getExtras(notification).getBoolean("android.support.isGroupSummary");
    }

    public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1, int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput[] = null;
        if(bundle != null)
            aremoteinput = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), factory1);
        return factory.build(i, charsequence, pendingintent, bundle, aremoteinput);
    }

    public static Bundle writeActionAndGetExtras(android.app.Notification.Builder builder, NotificationCompatBase.Action action)
    {
        builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        Bundle bundle = new Bundle(action.getExtras());
        if(action.getRemoteInputs() != null)
            bundle.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle;
    }

    static final String EXTRA_ACTION_EXTRAS = "android.support.actionExtras";
    static final String EXTRA_GROUP_KEY = "android.support.groupKey";
    static final String EXTRA_GROUP_SUMMARY = "android.support.isGroupSummary";
    static final String EXTRA_LOCAL_ONLY = "android.support.localOnly";
    static final String EXTRA_REMOTE_INPUTS = "android.support.remoteInputs";
    static final String EXTRA_SORT_KEY = "android.support.sortKey";
    static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock = new Object();
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock = new Object();

}
