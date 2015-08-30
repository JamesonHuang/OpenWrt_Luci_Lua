// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

class ActionBarDrawerToggleJellybeanMR2
{

    ActionBarDrawerToggleJellybeanMR2()
    {
    }

    public static Drawable getThemeUpIndicator(Activity activity)
    {
        ActionBar actionbar = activity.getActionBar();
        Object obj;
        TypedArray typedarray;
        Drawable drawable;
        if(actionbar != null)
            obj = actionbar.getThemedContext();
        else
            obj = activity;
        typedarray = ((Context) (obj)).obtainStyledAttributes(null, THEME_ATTRS, 0x10102ce, 0);
        drawable = typedarray.getDrawable(0);
        typedarray.recycle();
        return drawable;
    }

    public static Object setActionBarDescription(Object obj, Activity activity, int i)
    {
        ActionBar actionbar = activity.getActionBar();
        if(actionbar != null)
            actionbar.setHomeActionContentDescription(i);
        return obj;
    }

    public static Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i)
    {
        ActionBar actionbar = activity.getActionBar();
        if(actionbar != null)
        {
            actionbar.setHomeAsUpIndicator(drawable);
            actionbar.setHomeActionContentDescription(i);
        }
        return obj;
    }

    private static final String TAG = "ActionBarDrawerToggleImplJellybeanMR2";
    private static final int THEME_ATTRS[];

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x101030b;
        THEME_ATTRS = ai;
    }
}
