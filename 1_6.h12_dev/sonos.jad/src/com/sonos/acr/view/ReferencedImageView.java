// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.ViewUtils;

public class ReferencedImageView extends View
{

    public ReferencedImageView(Context context)
    {
        this(context, null);
    }

    public ReferencedImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ReferencedImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        translationPosition = new int[2];
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.ReferencedImageView);
        referenceId = typedarray.getResourceId(0, 0);
        typedarray.recycle();
        setBackgroundColor(0x7f08001d);
        if(android.os.Build.VERSION.SDK_INT >= 11)
            setLayerType(1, null);
    }

    public boolean isOpaque()
    {
        return false;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        reference = getRootView().findViewById(referenceId);
    }

    public void onDraw(Canvas canvas)
    {
        if(reference != null)
        {
            SLog.e(LOG_TAG, "On Draw Called");
            super.onDraw(canvas);
            int i = canvas.save();
            canvas.translate((float)translationPosition[0] + ViewUtils.getTranslationX(this), (float)translationPosition[1] + ViewUtils.getTranslationY(this));
            reference.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(reference != null)
            ViewUtils.getRelativeViewDistance(this, reference, translationPosition);
        invalidate();
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(j);
        int j1 = android.view.View.MeasureSpec.getSize(i);
        if(l == 0 && reference != null)
            i1 = reference.getMeasuredHeight();
        if(k == 0 && reference != null)
            j1 = reference.getMeasuredWidth();
        setMeasuredDimension(j1, i1);
    }

    public static final String LOG_TAG = com/sonos/acr/view/ReferencedImageView.getSimpleName();
    View reference;
    int referenceId;
    private final int translationPosition[];

}
