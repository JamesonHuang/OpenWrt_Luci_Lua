// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

class NavUtilsJB
{

    NavUtilsJB()
    {
    }

    public static Intent getParentActivityIntent(Activity activity)
    {
        return activity.getParentActivityIntent();
    }

    public static String getParentActivityName(ActivityInfo activityinfo)
    {
        return activityinfo.parentActivityName;
    }

    public static void navigateUpTo(Activity activity, Intent intent)
    {
        activity.navigateUpTo(intent);
    }

    public static boolean shouldUpRecreateTask(Activity activity, Intent intent)
    {
        return activity.shouldUpRecreateTask(intent);
    }
}
