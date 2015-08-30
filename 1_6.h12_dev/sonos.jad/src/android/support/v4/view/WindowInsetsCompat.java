// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;

public class WindowInsetsCompat
{

    WindowInsetsCompat()
    {
    }

    public WindowInsetsCompat consumeStableInsets()
    {
        return this;
    }

    public WindowInsetsCompat consumeSystemWindowInsets()
    {
        return this;
    }

    public int getStableInsetBottom()
    {
        return 0;
    }

    public int getStableInsetLeft()
    {
        return 0;
    }

    public int getStableInsetRight()
    {
        return 0;
    }

    public int getStableInsetTop()
    {
        return 0;
    }

    public int getSystemWindowInsetBottom()
    {
        return 0;
    }

    public int getSystemWindowInsetLeft()
    {
        return 0;
    }

    public int getSystemWindowInsetRight()
    {
        return 0;
    }

    public int getSystemWindowInsetTop()
    {
        return 0;
    }

    public boolean hasInsets()
    {
        return false;
    }

    public boolean hasStableInsets()
    {
        return false;
    }

    public boolean hasSystemWindowInsets()
    {
        return false;
    }

    public boolean isConsumed()
    {
        return false;
    }

    public boolean isRound()
    {
        return false;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int j, int k, int l)
    {
        return this;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect)
    {
        return this;
    }
}
