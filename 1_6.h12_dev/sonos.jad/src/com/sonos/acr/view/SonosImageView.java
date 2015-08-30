// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sonos.acr.util.ImageUtils;

public class SonosImageView extends ImageView
    implements SleepIconDrawable.SleepTimerChangedListener
{

    public SonosImageView(Context context)
    {
        this(context, null);
    }

    public SonosImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        fixedAlong = 0;
        rawResId = 0;
        rawResIdBg = 0;
        foregroundChanged = false;
        foregroundId = 0;
        hasLayoutPassOccurredSinceResourceUpdated = false;
        rawImageLocked = false;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosImageView, i, 0);
        int j;
        if(typedarray != null)
            j = typedarray.getResourceId(4, 0);
        else
            j = 0;
        setRawImageResource(j, false);
        setBackgroundResource(typedarray.getResourceId(0, 0));
        setAlpha((int)(255F * typedarray.getFloat(2, 1.0F)));
        setFixedDimention(typedarray.getInt(3, 0));
        setForegroundDrawable(typedarray.getResourceId(1, 0));
        typedarray.recycle();
    }

    private void setForegroundDrawable(int i)
    {
        if(foregroundId != i)
        {
            foregroundId = i;
            if(foregroundDrawable != null)
            {
                foregroundDrawable.setCallback(null);
                unscheduleDrawable(foregroundDrawable);
            }
            foregroundDrawable = getResources().getDrawable(i);
            if(foregroundDrawable != null)
            {
                foregroundDrawable.setCallback(this);
                if(foregroundDrawable.isStateful())
                    foregroundDrawable.setState(getDrawableState());
                Drawable drawable = foregroundDrawable;
                boolean flag;
                if(getVisibility() == 0)
                    flag = true;
                else
                    flag = false;
                drawable.setVisible(flag, true);
            }
            foregroundChanged = true;
            setWillNotDraw(false);
            invalidate();
        }
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(foregroundDrawable != null)
        {
            if(foregroundChanged)
            {
                foregroundDrawable.setBounds(0, 0, getWidth(), getHeight());
                foregroundChanged = false;
            }
            foregroundDrawable.draw(canvas);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(foregroundDrawable != null && foregroundDrawable.isStateful())
        {
            foregroundDrawable.setState(getDrawableState());
            invalidate();
        }
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(foregroundDrawable != null)
            foregroundDrawable.jumpToCurrentState();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag && rawResId != 0 && (!rawImageLocked || !hasLayoutPassOccurredSinceResourceUpdated))
        {
            setRawImageResource(rawResId, true);
            hasLayoutPassOccurredSinceResourceUpdated = true;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(j);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = Math.min(k, l);
        int j1 = Math.max(k, l);
        if(fixedAlong == 2)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000));
        else
        if(fixedAlong == 1)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000));
        else
        if(fixedAlong == 3)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000));
        else
        if(fixedAlong == 4)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000));
        else
            super.onMeasure(i, j);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(foregroundDrawable != null)
        {
            foregroundChanged = true;
            invalidate();
        }
    }

    public void onSleepTimerChanged(boolean flag)
    {
        setSelected(flag);
    }

    public void setBackgroundResource(int i)
    {
        if(!isInEditMode() && i > 0 && ImageUtils.isRawImage(getResources(), i))
        {
            if(rawResIdBg != i)
            {
                super.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtils.getSvgFromResource(getResources(), i, getWidth(), getHeight())));
                rawResIdBg = i;
            }
        } else
        {
            super.setBackgroundResource(i);
            rawResIdBg = 0;
        }
    }

    public void setFixedDimention(int i)
    {
        fixedAlong = i;
    }

    public void setImageDrawable(Drawable drawable)
    {
        updateImageDrawable(drawable);
        rawResId = 0;
    }

    public void setImageResource(int i)
    {
        if(i > 0 && !isInEditMode() && ImageUtils.isRawImage(getResources(), i))
        {
            setRawImageResource(i, false);
        } else
        {
            updateImageResource(i);
            rawResId = 0;
        }
    }

    public void setRawImageResource(int i, boolean flag)
    {
        if(!isInEditMode()) goto _L2; else goto _L1
_L1:
        setImageResource(0x7f02006e);
_L4:
        return;
_L2:
        if(rawResId != i || flag)
        {
            super.setImageBitmap(ImageUtils.getSvgFromResource(getResources(), i, getWidth(), getHeight()));
            rawResId = i;
            hasLayoutPassOccurredSinceResourceUpdated = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setRawImageResourceLockState(boolean flag)
    {
        rawImageLocked = flag;
        if(!flag && rawResId != 0)
        {
            setRawImageResource(rawResId, true);
            hasLayoutPassOccurredSinceResourceUpdated = true;
        }
    }

    protected void updateImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
    }

    protected void updateImageResource(int i)
    {
        super.setImageResource(i);
    }

    public static final int FIXED_ALONG_HEIGHT = 2;
    public static final int FIXED_ALONG_LARGEST = 4;
    public static final int FIXED_ALONG_NONE = 0;
    public static final int FIXED_ALONG_SHORTEST = 3;
    public static final int FIXED_ALONG_WIDTH = 1;
    public static final String LOG_TAG = com/sonos/acr/view/SonosImageView.getSimpleName();
    private int fixedAlong;
    private boolean foregroundChanged;
    private Drawable foregroundDrawable;
    private int foregroundId;
    boolean hasLayoutPassOccurredSinceResourceUpdated;
    boolean rawImageLocked;
    protected int rawResId;
    protected int rawResIdBg;

}
