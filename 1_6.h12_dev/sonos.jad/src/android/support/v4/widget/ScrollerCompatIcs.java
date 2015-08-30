// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.widget.OverScroller;

class ScrollerCompatIcs
{

    ScrollerCompatIcs()
    {
    }

    public static float getCurrVelocity(Object obj)
    {
        return ((OverScroller)obj).getCurrVelocity();
    }
}
