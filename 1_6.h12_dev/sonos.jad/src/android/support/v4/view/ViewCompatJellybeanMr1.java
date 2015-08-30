// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

class ViewCompatJellybeanMr1
{

    ViewCompatJellybeanMr1()
    {
    }

    public static int getLabelFor(View view)
    {
        return view.getLabelFor();
    }

    public static int getLayoutDirection(View view)
    {
        return view.getLayoutDirection();
    }

    public static int getPaddingEnd(View view)
    {
        return view.getPaddingEnd();
    }

    public static int getPaddingStart(View view)
    {
        return view.getPaddingStart();
    }

    public static int getWindowSystemUiVisibility(View view)
    {
        return view.getWindowSystemUiVisibility();
    }

    public static void setLabelFor(View view, int i)
    {
        view.setLabelFor(i);
    }

    public static void setLayerPaint(View view, Paint paint)
    {
        view.setLayerPaint(paint);
    }

    public static void setLayoutDirection(View view, int i)
    {
        view.setLayoutDirection(i);
    }

    public static void setPaddingRelative(View view, int i, int j, int k, int l)
    {
        view.setPaddingRelative(i, j, k, l);
    }
}
