// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout
{

    public SquareRelativeLayout(Context context)
    {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public SquareRelativeLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(k, k);
    }
}
