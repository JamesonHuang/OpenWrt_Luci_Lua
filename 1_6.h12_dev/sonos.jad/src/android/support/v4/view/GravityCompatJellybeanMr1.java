// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

class GravityCompatJellybeanMr1
{

    GravityCompatJellybeanMr1()
    {
    }

    public static void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, int j1)
    {
        Gravity.apply(i, j, k, rect, l, i1, rect1, j1);
    }

    public static void apply(int i, int j, int k, Rect rect, Rect rect1, int l)
    {
        Gravity.apply(i, j, k, rect, rect1, l);
    }

    public static void applyDisplay(int i, Rect rect, Rect rect1, int j)
    {
        Gravity.applyDisplay(i, rect, rect1, j);
    }

    public static int getAbsoluteGravity(int i, int j)
    {
        return Gravity.getAbsoluteGravity(i, j);
    }
}
