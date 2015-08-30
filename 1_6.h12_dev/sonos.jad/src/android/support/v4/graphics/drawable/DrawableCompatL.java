// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class DrawableCompatL
{

    DrawableCompatL()
    {
    }

    public static void setHotspot(Drawable drawable, float f, float f1)
    {
        drawable.setHotspot(f, f1);
    }

    public static void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
    {
        drawable.setHotspotBounds(i, j, k, l);
    }

    public static void setTint(Drawable drawable, int i)
    {
        drawable.setTint(i);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorstatelist)
    {
        drawable.setTintList(colorstatelist);
    }

    public static void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
    {
        drawable.setTintMode(mode);
    }
}
