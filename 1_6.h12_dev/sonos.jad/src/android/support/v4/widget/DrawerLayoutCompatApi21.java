// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayoutImpl

class DrawerLayoutCompatApi21
{
    static class InsetsListener
        implements android.view.View.OnApplyWindowInsetsListener
    {

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
        {
            DrawerLayoutImpl drawerlayoutimpl = (DrawerLayoutImpl)view;
            boolean flag;
            if(windowinsets.getSystemWindowInsetTop() > 0)
                flag = true;
            else
                flag = false;
            drawerlayoutimpl.setChildInsets(windowinsets, flag);
            return windowinsets.consumeSystemWindowInsets();
        }

        InsetsListener()
        {
        }
    }


    DrawerLayoutCompatApi21()
    {
    }

    public static void applyMarginInsets(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, Object obj, int i)
    {
        WindowInsets windowinsets = (WindowInsets)obj;
        if(i != 3) goto _L2; else goto _L1
_L1:
        windowinsets = windowinsets.replaceSystemWindowInsets(windowinsets.getSystemWindowInsetLeft(), windowinsets.getSystemWindowInsetTop(), 0, windowinsets.getSystemWindowInsetBottom());
_L4:
        marginlayoutparams.leftMargin = windowinsets.getSystemWindowInsetLeft();
        marginlayoutparams.topMargin = windowinsets.getSystemWindowInsetTop();
        marginlayoutparams.rightMargin = windowinsets.getSystemWindowInsetRight();
        marginlayoutparams.bottomMargin = windowinsets.getSystemWindowInsetBottom();
        return;
_L2:
        if(i == 5)
            windowinsets = windowinsets.replaceSystemWindowInsets(0, windowinsets.getSystemWindowInsetTop(), windowinsets.getSystemWindowInsetRight(), windowinsets.getSystemWindowInsetBottom());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void configureApplyInsets(View view)
    {
        if(view instanceof DrawerLayoutImpl)
        {
            view.setOnApplyWindowInsetsListener(new InsetsListener());
            view.setSystemUiVisibility(1280);
        }
    }

    public static void dispatchChildInsets(View view, Object obj, int i)
    {
        WindowInsets windowinsets = (WindowInsets)obj;
        if(i != 3) goto _L2; else goto _L1
_L1:
        windowinsets = windowinsets.replaceSystemWindowInsets(windowinsets.getSystemWindowInsetLeft(), windowinsets.getSystemWindowInsetTop(), 0, windowinsets.getSystemWindowInsetBottom());
_L4:
        view.dispatchApplyWindowInsets(windowinsets);
        return;
_L2:
        if(i == 5)
            windowinsets = windowinsets.replaceSystemWindowInsets(0, windowinsets.getSystemWindowInsetTop(), windowinsets.getSystemWindowInsetRight(), windowinsets.getSystemWindowInsetBottom());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Drawable getDefaultStatusBarBackground(Context context)
    {
        TypedArray typedarray = context.obtainStyledAttributes(THEME_ATTRS);
        Drawable drawable = typedarray.getDrawable(0);
        typedarray.recycle();
        return drawable;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }

    public static int getTopInset(Object obj)
    {
        int i;
        if(obj != null)
            i = ((WindowInsets)obj).getSystemWindowInsetTop();
        else
            i = 0;
        return i;
    }

    private static final int THEME_ATTRS[];

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x1010434;
        THEME_ATTRS = ai;
    }
}
