// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.content.res:
//            ResourcesCompatApi21, ResourcesCompatIcsMr1

public class ResourcesCompat
{

    public ResourcesCompat()
    {
    }

    public static Drawable getDrawable(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        Drawable drawable;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            drawable = ResourcesCompatApi21.getDrawable(resources, i, theme);
        else
            drawable = resources.getDrawable(i);
        return drawable;
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int j, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        int k = android.os.Build.VERSION.SDK_INT;
        Drawable drawable;
        if(k >= 21)
            drawable = ResourcesCompatApi21.getDrawableForDensity(resources, i, j, theme);
        else
        if(k >= 15)
            drawable = ResourcesCompatIcsMr1.getDrawableForDensity(resources, i, j);
        else
            drawable = resources.getDrawable(i);
        return drawable;
    }
}
