// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

class ScrollerCompatGingerbread
{

    ScrollerCompatGingerbread()
    {
    }

    public static void abortAnimation(Object obj)
    {
        ((OverScroller)obj).abortAnimation();
    }

    public static boolean computeScrollOffset(Object obj)
    {
        return ((OverScroller)obj).computeScrollOffset();
    }

    public static Object createScroller(Context context, Interpolator interpolator)
    {
        OverScroller overscroller;
        if(interpolator != null)
            overscroller = new OverScroller(context, interpolator);
        else
            overscroller = new OverScroller(context);
        return overscroller;
    }

    public static void fling(Object obj, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        ((OverScroller)obj).fling(i, j, k, l, i1, j1, k1, l1);
    }

    public static void fling(Object obj, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2)
    {
        ((OverScroller)obj).fling(i, j, k, l, i1, j1, k1, l1, i2, j2);
    }

    public static int getCurrX(Object obj)
    {
        return ((OverScroller)obj).getCurrX();
    }

    public static int getCurrY(Object obj)
    {
        return ((OverScroller)obj).getCurrY();
    }

    public static int getFinalX(Object obj)
    {
        return ((OverScroller)obj).getFinalX();
    }

    public static int getFinalY(Object obj)
    {
        return ((OverScroller)obj).getFinalY();
    }

    public static boolean isFinished(Object obj)
    {
        return ((OverScroller)obj).isFinished();
    }

    public static boolean isOverScrolled(Object obj)
    {
        return ((OverScroller)obj).isOverScrolled();
    }

    public static void notifyHorizontalEdgeReached(Object obj, int i, int j, int k)
    {
        ((OverScroller)obj).notifyHorizontalEdgeReached(i, j, k);
    }

    public static void notifyVerticalEdgeReached(Object obj, int i, int j, int k)
    {
        ((OverScroller)obj).notifyVerticalEdgeReached(i, j, k);
    }

    public static void startScroll(Object obj, int i, int j, int k, int l)
    {
        ((OverScroller)obj).startScroll(i, j, k, l);
    }

    public static void startScroll(Object obj, int i, int j, int k, int l, int i1)
    {
        ((OverScroller)obj).startScroll(i, j, k, l, i1);
    }
}
