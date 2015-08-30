// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

class EdgeEffectCompatIcs
{

    EdgeEffectCompatIcs()
    {
    }

    public static boolean draw(Object obj, Canvas canvas)
    {
        return ((EdgeEffect)obj).draw(canvas);
    }

    public static void finish(Object obj)
    {
        ((EdgeEffect)obj).finish();
    }

    public static boolean isFinished(Object obj)
    {
        return ((EdgeEffect)obj).isFinished();
    }

    public static Object newEdgeEffect(Context context)
    {
        return new EdgeEffect(context);
    }

    public static boolean onAbsorb(Object obj, int i)
    {
        ((EdgeEffect)obj).onAbsorb(i);
        return true;
    }

    public static boolean onPull(Object obj, float f)
    {
        ((EdgeEffect)obj).onPull(f);
        return true;
    }

    public static boolean onRelease(Object obj)
    {
        EdgeEffect edgeeffect = (EdgeEffect)obj;
        edgeeffect.onRelease();
        return edgeeffect.isFinished();
    }

    public static void setSize(Object obj, int i, int j)
    {
        ((EdgeEffect)obj).setSize(i, j);
    }
}
