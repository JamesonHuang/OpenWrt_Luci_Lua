// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

class ActivityOptionsCompatJB
{

    private ActivityOptionsCompatJB(ActivityOptions activityoptions)
    {
        mActivityOptions = activityoptions;
    }

    public static ActivityOptionsCompatJB makeCustomAnimation(Context context, int i, int j)
    {
        return new ActivityOptionsCompatJB(ActivityOptions.makeCustomAnimation(context, i, j));
    }

    public static ActivityOptionsCompatJB makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        return new ActivityOptionsCompatJB(ActivityOptions.makeScaleUpAnimation(view, i, j, k, l));
    }

    public static ActivityOptionsCompatJB makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        return new ActivityOptionsCompatJB(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
    }

    public Bundle toBundle()
    {
        return mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompatJB activityoptionscompatjb)
    {
        mActivityOptions.update(activityoptionscompatjb.mActivityOptions);
    }

    private final ActivityOptions mActivityOptions;
}
