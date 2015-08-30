// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            OnApplyWindowInsetsListener, WindowInsetsCompatApi21

class ViewCompatApi21
{

    ViewCompatApi21()
    {
    }

    public static float getElevation(View view)
    {
        return view.getElevation();
    }

    public static String getTransitionName(View view)
    {
        return view.getTransitionName();
    }

    public static float getTranslationZ(View view)
    {
        return view.getTranslationZ();
    }

    public static boolean isImportantForAccessibility(View view)
    {
        return view.isImportantForAccessibility();
    }

    public static void requestApplyInsets(View view)
    {
        view.requestApplyInsets();
    }

    public static void setElevation(View view, float f)
    {
        view.setElevation(f);
    }

    public static void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener listener)
    {
        view.setOnApplyWindowInsetsListener(new android.view.View.OnApplyWindowInsetsListener() {

            public WindowInsets onApplyWindowInsets(View view1, WindowInsets windowinsets)
            {
                WindowInsetsCompatApi21 windowinsetscompatapi21 = new WindowInsetsCompatApi21(windowinsets);
                return ((WindowInsetsCompatApi21)listener.onApplyWindowInsets(view1, windowinsetscompatapi21)).unwrap();
            }

            final OnApplyWindowInsetsListener val$listener;

            
            {
                listener = onapplywindowinsetslistener;
                super();
            }
        }
);
    }

    public static void setTransitionName(View view, String s)
    {
        view.setTransitionName(s);
    }

    public static void setTranslationZ(View view, float f)
    {
        view.setTranslationZ(f);
    }
}
