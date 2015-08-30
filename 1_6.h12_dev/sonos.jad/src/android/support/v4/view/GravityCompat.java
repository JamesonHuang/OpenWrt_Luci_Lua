// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

// Referenced classes of package android.support.v4.view:
//            GravityCompatJellybeanMr1

public class GravityCompat
{
    static class GravityCompatImplJellybeanMr1
        implements GravityCompatImpl
    {

        public void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, 
                int j1)
        {
            GravityCompatJellybeanMr1.apply(i, j, k, rect, l, i1, rect1, j1);
        }

        public void apply(int i, int j, int k, Rect rect, Rect rect1, int l)
        {
            GravityCompatJellybeanMr1.apply(i, j, k, rect, rect1, l);
        }

        public void applyDisplay(int i, Rect rect, Rect rect1, int j)
        {
            GravityCompatJellybeanMr1.applyDisplay(i, rect, rect1, j);
        }

        public int getAbsoluteGravity(int i, int j)
        {
            return GravityCompatJellybeanMr1.getAbsoluteGravity(i, j);
        }

        GravityCompatImplJellybeanMr1()
        {
        }
    }

    static class GravityCompatImplBase
        implements GravityCompatImpl
    {

        public void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, 
                int j1)
        {
            Gravity.apply(i, j, k, rect, l, i1, rect1);
        }

        public void apply(int i, int j, int k, Rect rect, Rect rect1, int l)
        {
            Gravity.apply(i, j, k, rect, rect1);
        }

        public void applyDisplay(int i, Rect rect, Rect rect1, int j)
        {
            Gravity.applyDisplay(i, rect, rect1);
        }

        public int getAbsoluteGravity(int i, int j)
        {
            return 0xff7fffff & i;
        }

        GravityCompatImplBase()
        {
        }
    }

    static interface GravityCompatImpl
    {

        public abstract void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, 
                int j1);

        public abstract void apply(int i, int j, int k, Rect rect, Rect rect1, int l);

        public abstract void applyDisplay(int i, Rect rect, Rect rect1, int j);

        public abstract int getAbsoluteGravity(int i, int j);
    }


    public GravityCompat()
    {
    }

    public static void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, int j1)
    {
        IMPL.apply(i, j, k, rect, l, i1, rect1, j1);
    }

    public static void apply(int i, int j, int k, Rect rect, Rect rect1, int l)
    {
        IMPL.apply(i, j, k, rect, rect1, l);
    }

    public static void applyDisplay(int i, Rect rect, Rect rect1, int j)
    {
        IMPL.applyDisplay(i, rect, rect1, j);
    }

    public static int getAbsoluteGravity(int i, int j)
    {
        return IMPL.getAbsoluteGravity(i, j);
    }

    public static final int END = 0x800005;
    static final GravityCompatImpl IMPL;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 0x800007;
    public static final int RELATIVE_LAYOUT_DIRECTION = 0x800000;
    public static final int START = 0x800003;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            IMPL = new GravityCompatImplJellybeanMr1();
        else
            IMPL = new GravityCompatImplBase();
    }
}
