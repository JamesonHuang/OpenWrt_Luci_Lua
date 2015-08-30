// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableCompatL, DrawableCompatKitKat, DrawableCompatHoneycomb

public class DrawableCompat
{
    static class LDrawableImpl extends KitKatDrawableImpl
    {

        public void setHotspot(Drawable drawable, float f, float f1)
        {
            DrawableCompatL.setHotspot(drawable, f, f1);
        }

        public void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
        {
            DrawableCompatL.setHotspotBounds(drawable, i, j, k, l);
        }

        public void setTint(Drawable drawable, int i)
        {
            DrawableCompatL.setTint(drawable, i);
        }

        public void setTintList(Drawable drawable, ColorStateList colorstatelist)
        {
            DrawableCompatL.setTintList(drawable, colorstatelist);
        }

        public void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
        {
            DrawableCompatL.setTintMode(drawable, mode);
        }

        LDrawableImpl()
        {
        }
    }

    static class KitKatDrawableImpl extends HoneycombDrawableImpl
    {

        public boolean isAutoMirrored(Drawable drawable)
        {
            return DrawableCompatKitKat.isAutoMirrored(drawable);
        }

        public void setAutoMirrored(Drawable drawable, boolean flag)
        {
            DrawableCompatKitKat.setAutoMirrored(drawable, flag);
        }

        KitKatDrawableImpl()
        {
        }
    }

    static class HoneycombDrawableImpl extends BaseDrawableImpl
    {

        public void jumpToCurrentState(Drawable drawable)
        {
            DrawableCompatHoneycomb.jumpToCurrentState(drawable);
        }

        HoneycombDrawableImpl()
        {
        }
    }

    static class BaseDrawableImpl
        implements DrawableImpl
    {

        public boolean isAutoMirrored(Drawable drawable)
        {
            return false;
        }

        public void jumpToCurrentState(Drawable drawable)
        {
        }

        public void setAutoMirrored(Drawable drawable, boolean flag)
        {
        }

        public void setHotspot(Drawable drawable, float f, float f1)
        {
        }

        public void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
        {
        }

        public void setTint(Drawable drawable, int i)
        {
        }

        public void setTintList(Drawable drawable, ColorStateList colorstatelist)
        {
        }

        public void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
        {
        }

        BaseDrawableImpl()
        {
        }
    }

    static interface DrawableImpl
    {

        public abstract boolean isAutoMirrored(Drawable drawable);

        public abstract void jumpToCurrentState(Drawable drawable);

        public abstract void setAutoMirrored(Drawable drawable, boolean flag);

        public abstract void setHotspot(Drawable drawable, float f, float f1);

        public abstract void setHotspotBounds(Drawable drawable, int i, int j, int k, int l);

        public abstract void setTint(Drawable drawable, int i);

        public abstract void setTintList(Drawable drawable, ColorStateList colorstatelist);

        public abstract void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode);
    }


    public DrawableCompat()
    {
    }

    public static boolean isAutoMirrored(Drawable drawable)
    {
        return IMPL.isAutoMirrored(drawable);
    }

    public static void jumpToCurrentState(Drawable drawable)
    {
        IMPL.jumpToCurrentState(drawable);
    }

    public static void setAutoMirrored(Drawable drawable, boolean flag)
    {
        IMPL.setAutoMirrored(drawable, flag);
    }

    public static void setHotspot(Drawable drawable, float f, float f1)
    {
        IMPL.setHotspot(drawable, f, f1);
    }

    public static void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
    {
        IMPL.setHotspotBounds(drawable, i, j, k, l);
    }

    public static void setTint(Drawable drawable, int i)
    {
        IMPL.setTint(drawable, i);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorstatelist)
    {
        IMPL.setTintList(drawable, colorstatelist);
    }

    public static void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setTintMode(drawable, mode);
    }

    static final DrawableImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 21)
            IMPL = new LDrawableImpl();
        else
        if(i >= 19)
            IMPL = new KitKatDrawableImpl();
        else
        if(i >= 11)
            IMPL = new HoneycombDrawableImpl();
        else
            IMPL = new BaseDrawableImpl();
    }
}
