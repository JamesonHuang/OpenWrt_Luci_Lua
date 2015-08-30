// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.sonos.acr.nowplaying.controllers.PlayIndicatorController;

public class PlayIndicatorView extends View
    implements com.sonos.acr.nowplaying.controllers.PlayIndicatorController.PlayIndicatorListener
{

    public PlayIndicatorView(Context context)
    {
        this(context, null);
    }

    public PlayIndicatorView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public PlayIndicatorView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        paint = new Paint();
        levelRect = new Rect();
        isAttached = false;
        int j = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.PlayIndicator).getColor(0, -1);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(j);
    }

    public void attachToController()
    {
        if(controller != null)
            controller.addListener(this);
    }

    public void detachFromController()
    {
        if(controller != null)
            controller.removeListener(this);
    }

    public int getBaseline()
    {
        android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
        int i;
        if(layoutparams != null && layoutparams.height > 0)
            i = layoutparams.height;
        else
            i = getHeight();
        return i;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        isAttached = true;
        attachToController();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        detachFromController();
        isAttached = false;
    }

    public void onDraw(Canvas canvas)
    {
        float f = getWidth() - getPaddingLeft() - getPaddingRight();
        float f1 = getHeight() - getPaddingTop() - getPaddingBottom();
        float f2 = f / 5F;
        int ai[];
        int i;
        if(controller != null)
            ai = controller.getLevels();
        else
        if(isInEditMode())
            ai = EDIT_LEVEL;
        else
            ai = ZERO_LEVEL;
        for(i = 0; i < 3; i++)
        {
            int j = getPaddingLeft() + i * (2 * (int)f2);
            int k = ai[i];
            int l = getPaddingTop() + (int)(f1 * ((float)(100 - k) / 100F));
            levelRect.set(j, l, j + (int)f2, getHeight() - getPaddingBottom());
            canvas.drawRect(levelRect, paint);
        }

    }

    public void onLevelsUpdated()
    {
        invalidate();
    }

    public void setController(PlayIndicatorController playindicatorcontroller)
    {
        if(playindicatorcontroller != controller)
        {
            if(isAttached)
            {
                detachFromController();
                controller = playindicatorcontroller;
                attachToController();
            } else
            {
                controller = playindicatorcontroller;
            }
            invalidate();
        }
    }

    private static final int EDIT_LEVEL[];
    private static final int ZERO_LEVEL[];
    private PlayIndicatorController controller;
    boolean isAttached;
    private Rect levelRect;
    private Paint paint;

    static 
    {
        int ai[] = new int[3];
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ZERO_LEVEL = ai;
        int ai1[] = new int[3];
        ai1[0] = 60;
        ai1[1] = 20;
        ai1[2] = 100;
        EDIT_LEVEL = ai1;
    }
}
