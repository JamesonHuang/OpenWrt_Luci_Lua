// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

public class FixedBackgroundScrollView extends RelativeLayout
{

    public FixedBackgroundScrollView(Context context)
    {
        super(context);
    }

    public FixedBackgroundScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public FixedBackgroundScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        if(getChildCount() != 1) goto _L2; else goto _L1
_L1:
        scrollView = (ScrollView)getChildAt(0);
_L4:
        return;
_L2:
        if(getChildCount() == 2)
        {
            background = (ImageSwitcher)getChildAt(0);
            scrollView = (ScrollView)getChildAt(1);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(scrollView.getChildCount() > 0)
        {
            int k = scrollView.getChildAt(0).getPaddingTop() - background.getCurrentView().getMeasuredHeight() - scrollView.getPaddingTop();
            scrollView.getChildAt(0).setPadding(scrollView.getPaddingLeft(), background.getCurrentView().getMeasuredHeight() - scrollView.getPaddingTop(), scrollView.getPaddingRight(), scrollView.getPaddingBottom());
            if(k > 0)
                scrollView.scrollTo(0, scrollView.getScrollY() - k);
        }
    }

    private ImageSwitcher background;
    private ScrollView scrollView;
}
