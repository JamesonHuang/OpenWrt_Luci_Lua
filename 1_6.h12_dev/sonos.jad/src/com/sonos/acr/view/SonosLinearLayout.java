// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SonosLinearLayout extends LinearLayout
{
    public static interface OnSizeChangeListener
    {

        public abstract void onSizeChanged(int i, int j, int k, int l);
    }


    public SonosLinearLayout(Context context)
    {
        super(context);
    }

    public SonosLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosLinearLayout);
        maxWidth = typedarray.getDimensionPixelSize(0, -1);
        maxHeight = typedarray.getDimensionPixelSize(1, -1);
        typedarray.recycle();
    }

    protected void onMeasure(int i, int j)
    {
        int k = i;
        int l = j;
        if(maxWidth != -1)
        {
            int j1 = android.view.View.MeasureSpec.getMode(i);
            k = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(android.view.View.MeasureSpec.getSize(i), maxWidth), j1);
        }
        if(maxHeight != -1)
        {
            int i1 = android.view.View.MeasureSpec.getMode(j);
            l = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(android.view.View.MeasureSpec.getSize(j), maxHeight), i1);
        }
        super.onMeasure(k, l);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(onSizeChangeListener != null)
            onSizeChangeListener.onSizeChanged(i, j, k, l);
    }

    public void setMaxHeight(int i)
    {
        maxHeight = i;
    }

    public void setMaxWidth(int i)
    {
        maxWidth = i;
    }

    public void setOnSizeChangeListener(OnSizeChangeListener onsizechangelistener)
    {
        onSizeChangeListener = onsizechangelistener;
    }

    public static final int UNDEFINED_MAX_WIDTH = -1;
    private int maxHeight;
    private int maxWidth;
    OnSizeChangeListener onSizeChangeListener;
}
