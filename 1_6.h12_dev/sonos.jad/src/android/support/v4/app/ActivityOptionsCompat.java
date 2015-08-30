// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            ActivityOptionsCompatJB, ActivityOptionsCompat21

public class ActivityOptionsCompat
{
    private static class ActivityOptionsImpl21 extends ActivityOptionsCompat
    {

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImpl21)
            {
                ActivityOptionsImpl21 activityoptionsimpl21 = (ActivityOptionsImpl21)activityoptionscompat;
                mImpl.update(activityoptionsimpl21.mImpl);
            }
        }

        private final ActivityOptionsCompat21 mImpl;

        ActivityOptionsImpl21(ActivityOptionsCompat21 activityoptionscompat21)
        {
            mImpl = activityoptionscompat21;
        }
    }

    private static class ActivityOptionsImplJB extends ActivityOptionsCompat
    {

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImplJB)
            {
                ActivityOptionsImplJB activityoptionsimpljb = (ActivityOptionsImplJB)activityoptionscompat;
                mImpl.update(activityoptionsimpljb.mImpl);
            }
        }

        private final ActivityOptionsCompatJB mImpl;

        ActivityOptionsImplJB(ActivityOptionsCompatJB activityoptionscompatjb)
        {
            mImpl = activityoptionscompatjb;
        }
    }


    protected ActivityOptionsCompat()
    {
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int i, int j)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            obj = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, i, j));
        else
            obj = new ActivityOptionsCompat();
        return ((ActivityOptionsCompat) (obj));
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            obj = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, i, j, k, l));
        else
            obj = new ActivityOptionsCompat();
        return ((ActivityOptionsCompat) (obj));
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View view, String s)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            obj = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, s));
        else
            obj = new ActivityOptionsCompat();
        return ((ActivityOptionsCompat) (obj));
    }

    public static transient ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair apair[])
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            View aview[] = null;
            String as[] = null;
            if(apair != null)
            {
                aview = new View[apair.length];
                as = new String[apair.length];
                for(int i = 0; i < apair.length; i++)
                {
                    aview[i] = (View)apair[i].first;
                    as[i] = (String)apair[i].second;
                }

            }
            obj = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, aview, as));
        } else
        {
            obj = new ActivityOptionsCompat();
        }
        return ((ActivityOptionsCompat) (obj));
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            obj = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        else
            obj = new ActivityOptionsCompat();
        return ((ActivityOptionsCompat) (obj));
    }

    public Bundle toBundle()
    {
        return null;
    }

    public void update(ActivityOptionsCompat activityoptionscompat)
    {
    }
}
