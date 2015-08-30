// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.SeekBar;
import com.sonos.acr.util.SLog;

public class SonosProgressBar extends SeekBar
{

    public SonosProgressBar(Context context)
    {
        this(context, null);
    }

    public SonosProgressBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosProgressBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        int ai[] = new int[1];
        ai[0] = 0x7f01005d;
        progressableState = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x7f01005e;
        noProgressState = ai1;
        progressable = true;
        isNoProgress = true;
        isUserInteractable = true;
        thumbBounds = new Rect();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosProgressBar);
        setThumbOffset(typedarray.getDimensionPixelOffset(0, 0));
        typedarray.recycle();
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void checkProgress()
    {
        boolean flag;
        if(getProgress() == 0)
            flag = true;
        else
            flag = false;
        if(isNoProgress != flag)
        {
            isNoProgress = flag;
            refreshDrawableState();
        }
    }

    public int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 2);
        if(progressable && progressableState != null)
            mergeDrawableStates(ai, progressableState);
        if(isNoProgress && noProgressState != null)
            mergeDrawableStates(ai, noProgressState);
        return ai;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        flag = false;
        checkProgress();
        if(isUserInteractable) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(motionevent.getAction() != 0)
            break; /* Loop/switch isn't completed */
        Rect rect = thumb.getBounds();
        thumbBounds.set(rect.left - scaledTouchSlop, rect.top - scaledTouchSlop, rect.right + scaledTouchSlop, rect.bottom + scaledTouchSlop);
        SLog.e("ProgressBar", (new StringBuilder()).append("Thumb Bounds: ").append(thumbBounds).toString());
        if(!thumbBounds.contains((int)motionevent.getX(), (int)motionevent.getY()))
            continue; /* Loop/switch isn't completed */
        super.onTouchEvent(motionevent);
_L4:
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
        super.onTouchEvent(motionevent);
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        checkProgress();
        boolean flag;
        if(!isUserInteractable)
            flag = true;
        else
            flag = super.onTrackballEvent(motionevent);
        return flag;
    }

    public void setIsUserInteractable(boolean flag)
    {
        isUserInteractable = flag;
        setFocusable(flag);
    }

    public void setProgress(int i)
    {
        super.setProgress(i);
        checkProgress();
    }

    public void setProgressable(boolean flag)
    {
        progressable = flag;
        refreshDrawableState();
    }

    public void setThumb(Drawable drawable)
    {
        super.setThumb(drawable);
        thumb = drawable;
    }

    private boolean isNoProgress;
    private boolean isUserInteractable;
    private int noProgressState[];
    private boolean progressable;
    private int progressableState[];
    private int scaledTouchSlop;
    private Drawable thumb;
    private final Rect thumbBounds;
}
