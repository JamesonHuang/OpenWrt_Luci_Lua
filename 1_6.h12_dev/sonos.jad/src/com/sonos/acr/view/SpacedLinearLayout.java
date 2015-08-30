// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SpacedLinearLayout extends LinearLayout
{

    public SpacedLinearLayout(Context context)
    {
        super(context);
    }

    public SpacedLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public SpacedLinearLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        int i1 = getChildCount();
        int j1 = getMeasuredWidth();
        int k1 = getMeasuredHeight();
        int l1;
        int i2;
        if(i1 < 2)
            l1 = 0;
        else
            l1 = j1 / (i1 - 1);
        for(i2 = 1; i2 < i1; i2++)
        {
            View view = getChildAt(i2);
            int j2 = view.getMeasuredWidth();
            int k2 = view.getMeasuredHeight();
            int l2 = l1 * i2 - j2 / 2;
            if(i2 == i1 - 1)
                l2 = l1 * i2 - j2;
            int i3 = (k1 - k2) / 2;
            view.layout(l2, i3, l2 + j2, i3 + k2);
        }

    }
}
