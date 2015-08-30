// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class ResourcesCompatApi21
{

    ResourcesCompatApi21()
    {
    }

    public static Drawable getDrawable(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        return resources.getDrawable(i, theme);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int j, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        return resources.getDrawableForDensity(i, j, theme);
    }
}
