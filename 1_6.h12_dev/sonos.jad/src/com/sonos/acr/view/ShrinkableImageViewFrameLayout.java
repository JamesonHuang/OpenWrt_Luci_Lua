// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ShrinkableImageViewFrameLayout extends FrameLayout
{

    public ShrinkableImageViewFrameLayout(Context context)
    {
        super(context);
    }

    public ShrinkableImageViewFrameLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public ShrinkableImageViewFrameLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        int k = j;
        int l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(j);
        if((getChildAt(0) instanceof ImageView) && ((ImageView)getChildAt(0)).getDrawable() != null)
            k = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(i1, ((ImageView)getChildAt(0)).getDrawable().getIntrinsicHeight()), l);
        super.onMeasure(i, k);
    }
}
