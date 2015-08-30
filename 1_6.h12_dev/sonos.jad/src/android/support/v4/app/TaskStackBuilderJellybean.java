// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class TaskStackBuilderJellybean
{

    TaskStackBuilderJellybean()
    {
    }

    public static PendingIntent getActivitiesPendingIntent(Context context, int i, Intent aintent[], int j, Bundle bundle)
    {
        return PendingIntent.getActivities(context, i, aintent, j, bundle);
    }
}
