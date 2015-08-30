// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

final class MediaRouterThemeHelper
{

    private MediaRouterThemeHelper()
    {
    }

    public static Context createThemedContext(Context context, boolean flag)
    {
        boolean flag1 = isLightTheme(context);
        if(flag1 && flag)
        {
            ContextThemeWrapper contextthemewrapper = new ContextThemeWrapper(context, android.support.v7.mediarouter.R.style.Theme_AppCompat);
            flag1 = false;
            context = contextthemewrapper;
        }
        int i;
        if(flag1)
            i = android.support.v7.mediarouter.R.style.Theme_MediaRouter_Light;
        else
            i = android.support.v7.mediarouter.R.style.Theme_MediaRouter;
        return new ContextThemeWrapper(context, i);
    }

    public static Drawable getThemeDrawable(Context context, int i)
    {
        int j = getThemeResource(context, i);
        Drawable drawable;
        if(j != 0)
            drawable = context.getResources().getDrawable(j);
        else
            drawable = null;
        return drawable;
    }

    public static int getThemeResource(Context context, int i)
    {
        TypedValue typedvalue = new TypedValue();
        int j;
        if(context.getTheme().resolveAttribute(i, typedvalue, true))
            j = typedvalue.resourceId;
        else
            j = 0;
        return j;
    }

    private static boolean isLightTheme(Context context)
    {
        boolean flag = true;
        TypedValue typedvalue = new TypedValue();
        if(!context.getTheme().resolveAttribute(android.support.v7.mediarouter.R.attr.isLightTheme, typedvalue, flag) || typedvalue.data == 0)
            flag = false;
        return flag;
    }
}
