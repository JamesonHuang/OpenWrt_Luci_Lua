// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            WindowInsetsCompat

class WindowInsetsCompatApi21 extends WindowInsetsCompat
{

    WindowInsetsCompatApi21(WindowInsets windowinsets)
    {
        mSource = windowinsets;
    }

    public WindowInsetsCompat consumeStableInsets()
    {
        return new WindowInsetsCompatApi21(mSource.consumeStableInsets());
    }

    public WindowInsetsCompat consumeSystemWindowInsets()
    {
        return new WindowInsetsCompatApi21(mSource.consumeSystemWindowInsets());
    }

    public int getStableInsetBottom()
    {
        return mSource.getStableInsetBottom();
    }

    public int getStableInsetLeft()
    {
        return mSource.getStableInsetLeft();
    }

    public int getStableInsetRight()
    {
        return mSource.getStableInsetRight();
    }

    public int getStableInsetTop()
    {
        return mSource.getStableInsetTop();
    }

    public int getSystemWindowInsetBottom()
    {
        return mSource.getSystemWindowInsetBottom();
    }

    public int getSystemWindowInsetLeft()
    {
        return mSource.getSystemWindowInsetLeft();
    }

    public int getSystemWindowInsetRight()
    {
        return mSource.getSystemWindowInsetRight();
    }

    public int getSystemWindowInsetTop()
    {
        return mSource.getSystemWindowInsetTop();
    }

    public boolean hasInsets()
    {
        return mSource.hasInsets();
    }

    public boolean hasStableInsets()
    {
        return mSource.hasStableInsets();
    }

    public boolean hasSystemWindowInsets()
    {
        return mSource.hasSystemWindowInsets();
    }

    public boolean isConsumed()
    {
        return mSource.isConsumed();
    }

    public boolean isRound()
    {
        return mSource.isRound();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int j, int k, int l)
    {
        return new WindowInsetsCompatApi21(mSource.replaceSystemWindowInsets(i, j, k, l));
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect)
    {
        return new WindowInsetsCompatApi21(mSource.replaceSystemWindowInsets(rect));
    }

    WindowInsets unwrap()
    {
        return mSource;
    }

    private final WindowInsets mSource;
}
