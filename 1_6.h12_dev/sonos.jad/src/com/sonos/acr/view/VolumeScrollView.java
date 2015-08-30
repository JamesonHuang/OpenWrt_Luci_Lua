// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class VolumeScrollView extends ScrollView
{

    public VolumeScrollView(Context context)
    {
        super(context);
        init();
    }

    public VolumeScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    private void init()
    {
        cellHeight = getResources().getDimensionPixelSize(0x7f09007e);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(android.view.View.MeasureSpec.getMode(j) != 0)
        {
            double d = (0.80000000000000004D * (double)android.view.View.MeasureSpec.getSize(j)) / cellHeight;
            int k = (int)(Math.floor(d) * cellHeight);
            int l;
            if(Math.ceil(d) - d < 0.5D)
                l = (int)((double)k + cellHeight / 2D);
            else
                l = (int)((double)k - cellHeight / 2D);
            if(l < getMeasuredHeight())
                setMeasuredDimension(getMeasuredWidth(), l);
        }
    }

    public void setCellHeight(int i)
    {
        cellHeight = i;
    }

    double cellHeight;
}
