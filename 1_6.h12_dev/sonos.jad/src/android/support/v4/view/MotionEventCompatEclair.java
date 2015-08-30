// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompatEclair
{

    MotionEventCompatEclair()
    {
    }

    public static int findPointerIndex(MotionEvent motionevent, int i)
    {
        return motionevent.findPointerIndex(i);
    }

    public static int getPointerCount(MotionEvent motionevent)
    {
        return motionevent.getPointerCount();
    }

    public static int getPointerId(MotionEvent motionevent, int i)
    {
        return motionevent.getPointerId(i);
    }

    public static float getX(MotionEvent motionevent, int i)
    {
        return motionevent.getX(i);
    }

    public static float getY(MotionEvent motionevent, int i)
    {
        return motionevent.getY(i);
    }
}
