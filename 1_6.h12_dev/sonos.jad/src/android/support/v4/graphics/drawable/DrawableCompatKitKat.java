// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatKitKat
{

    DrawableCompatKitKat()
    {
    }

    public static boolean isAutoMirrored(Drawable drawable)
    {
        return drawable.isAutoMirrored();
    }

    public static void setAutoMirrored(Drawable drawable, boolean flag)
    {
        drawable.setAutoMirrored(flag);
    }
}
