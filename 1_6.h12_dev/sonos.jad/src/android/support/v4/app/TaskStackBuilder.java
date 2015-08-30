// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.*;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.v4.app:
//            NavUtils, TaskStackBuilderJellybean, TaskStackBuilderHoneycomb

public class TaskStackBuilder
    implements Iterable
{
    static class TaskStackBuilderImplJellybean
        implements TaskStackBuilderImpl
    {

        public PendingIntent getPendingIntent(Context context, Intent aintent[], int i, int j, Bundle bundle)
        {
            aintent[0] = (new Intent(aintent[0])).addFlags(0x1000c000);
            return TaskStackBuilderJellybean.getActivitiesPendingIntent(context, i, aintent, j, bundle);
        }

        TaskStackBuilderImplJellybean()
        {
        }
    }

    static class TaskStackBuilderImplHoneycomb
        implements TaskStackBuilderImpl
    {

        public PendingIntent getPendingIntent(Context context, Intent aintent[], int i, int j, Bundle bundle)
        {
            aintent[0] = (new Intent(aintent[0])).addFlags(0x1000c000);
            return TaskStackBuilderHoneycomb.getActivitiesPendingIntent(context, i, aintent, j);
        }

        TaskStackBuilderImplHoneycomb()
        {
        }
    }

    static class TaskStackBuilderImplBase
        implements TaskStackBuilderImpl
    {

        public PendingIntent getPendingIntent(Context context, Intent aintent[], int i, int j, Bundle bundle)
        {
            Intent intent = new Intent(aintent[-1 + aintent.length]);
            intent.addFlags(0x10000000);
            return PendingIntent.getActivity(context, i, intent, j);
        }

        TaskStackBuilderImplBase()
        {
        }
    }

    static interface TaskStackBuilderImpl
    {

        public abstract PendingIntent getPendingIntent(Context context, Intent aintent[], int i, int j, Bundle bundle);
    }

    public static interface SupportParentable
    {

        public abstract Intent getSupportParentActivityIntent();
    }


    private TaskStackBuilder(Context context)
    {
        mSourceContext = context;
    }

    public static TaskStackBuilder create(Context context)
    {
        return new TaskStackBuilder(context);
    }

    public static TaskStackBuilder from(Context context)
    {
        return create(context);
    }

    public TaskStackBuilder addNextIntent(Intent intent)
    {
        mIntents.add(intent);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent intent)
    {
        ComponentName componentname = intent.getComponent();
        if(componentname == null)
            componentname = intent.resolveActivity(mSourceContext.getPackageManager());
        if(componentname != null)
            addParentStack(componentname);
        addNextIntent(intent);
        return this;
    }

    public TaskStackBuilder addParentStack(Activity activity)
    {
        Intent intent = null;
        if(activity instanceof SupportParentable)
            intent = ((SupportParentable)activity).getSupportParentActivityIntent();
        if(intent == null)
            intent = NavUtils.getParentActivityIntent(activity);
        if(intent != null)
        {
            ComponentName componentname = intent.getComponent();
            if(componentname == null)
                componentname = intent.resolveActivity(mSourceContext.getPackageManager());
            addParentStack(componentname);
            addNextIntent(intent);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName componentname)
    {
        int i = mIntents.size();
        Intent intent = NavUtils.getParentActivityIntent(mSourceContext, componentname);
_L1:
        Intent intent1;
        if(intent == null)
            break MISSING_BLOCK_LABEL_72;
        mIntents.add(i, intent);
        intent1 = NavUtils.getParentActivityIntent(mSourceContext, intent.getComponent());
        intent = intent1;
          goto _L1
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
        throw new IllegalArgumentException(namenotfoundexception);
        return this;
    }

    public TaskStackBuilder addParentStack(Class class1)
    {
        return addParentStack(new ComponentName(mSourceContext, class1));
    }

    public Intent editIntentAt(int i)
    {
        return (Intent)mIntents.get(i);
    }

    public Intent getIntent(int i)
    {
        return editIntentAt(i);
    }

    public int getIntentCount()
    {
        return mIntents.size();
    }

    public Intent[] getIntents()
    {
        Intent aintent[] = new Intent[mIntents.size()];
        if(aintent.length != 0)
        {
            aintent[0] = (new Intent((Intent)mIntents.get(0))).addFlags(0x1000c000);
            int i = 1;
            while(i < aintent.length) 
            {
                aintent[i] = new Intent((Intent)mIntents.get(i));
                i++;
            }
        }
        return aintent;
    }

    public PendingIntent getPendingIntent(int i, int j)
    {
        return getPendingIntent(i, j, null);
    }

    public PendingIntent getPendingIntent(int i, int j, Bundle bundle)
    {
        if(mIntents.isEmpty())
        {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        } else
        {
            Intent aintent[] = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
            aintent[0] = (new Intent(aintent[0])).addFlags(0x1000c000);
            return IMPL.getPendingIntent(mSourceContext, aintent, i, j, bundle);
        }
    }

    public Iterator iterator()
    {
        return mIntents.iterator();
    }

    public void startActivities()
    {
        startActivities(null);
    }

    public void startActivities(Bundle bundle)
    {
        if(mIntents.isEmpty())
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        Intent aintent[] = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
        aintent[0] = (new Intent(aintent[0])).addFlags(0x1000c000);
        if(!ContextCompat.startActivities(mSourceContext, aintent, bundle))
        {
            Intent intent = new Intent(aintent[-1 + aintent.length]);
            intent.addFlags(0x10000000);
            mSourceContext.startActivity(intent);
        }
    }

    private static final TaskStackBuilderImpl IMPL;
    private static final String TAG = "TaskStackBuilder";
    private final ArrayList mIntents = new ArrayList();
    private final Context mSourceContext;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new TaskStackBuilderImplHoneycomb();
        else
            IMPL = new TaskStackBuilderImplBase();
    }
}
