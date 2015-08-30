// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.sonos.acr.imaging.BlurProcessor;
import com.sonos.acr.imaging.JavaBlur;
import com.sonos.acr.util.ImageUtils;
import com.sonos.acr.util.SLog;

public class BlurFrame extends FrameLayout
    implements android.view.ViewTreeObserver.OnPreDrawListener
{

    public BlurFrame(Context context)
    {
        this(context, null);
    }

    public BlurFrame(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public BlurFrame(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset);
        scaleFactor = 1.0F;
        adjustedWidthScaleFactor = 1.0F;
        adjustedHeightScaleFactor = 1.0F;
        location = new int[2];
        paint = new Paint();
        attachedToWindow = false;
        observedViewInvalidated = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.BlurFrame);
        Object obj;
        if(isInEditMode())
            obj = new JavaBlur();
        else
            obj = ImageUtils.createBlurProcessor(context);
        blurProcessor = ((BlurProcessor) (obj));
        setBlurRadius(typedarray.getDimension(0, 0.0F));
        setWillNotDraw(false);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
    }

    private void invalidateBlur()
    {
        observedViewInvalidated = true;
        invalidate();
    }

    public static int roundMult4(int i)
    {
        return -4 & i + 2;
    }

    public boolean isOpaque()
    {
        return true;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        invalidateBlur();
        attachedToWindow = true;
        if(rootView != null)
            rootView.getViewTreeObserver().addOnPreDrawListener(this);
    }

    protected void onDetachedFromWindow()
    {
        attachedToWindow = false;
        if(rootView != null)
            rootView.getViewTreeObserver().removeOnPreDrawListener(this);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        if(observedViewInvalidated && rootView != null)
        {
            getLocationOnScreen(location);
            int j = blurCanvas.save();
            blurCanvas.translate(-location[0], -location[1]);
            rootView.draw(blurCanvas);
            blurCanvas.restoreToCount(j);
            if(android.os.Build.VERSION.SDK_INT >= 11 && android.os.Build.VERSION.SDK_INT <= 13)
                (new Canvas(outBitmap)).drawPoint(0.0F, 0.0F, paint);
            blurProcessor.performBlur(sourceBitmap, outBitmap, blurRadius);
            observedViewInvalidated = false;
        }
        int i = canvas.save();
        canvas.scale(1.0F / adjustedWidthScaleFactor, 1.0F / adjustedHeightScaleFactor);
        canvas.drawBitmap(outBitmap, 0.0F, 0.0F, paint);
        canvas.restoreToCount(i);
        super.onDraw(canvas);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
            invalidateBlur();
    }

    public boolean onPreDraw()
    {
        invalidateBlur();
        return true;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        SLog.e(LOG_TAG, (new StringBuilder()).append("OnSizeChanged! ").append(j).append(" ").append(i).toString());
        if(sourceBitmap != null)
        {
            sourceBitmap.recycle();
            sourceBitmap = null;
        }
        if(outBitmap != null)
        {
            outBitmap.recycle();
            outBitmap = null;
        }
        if(i > 0 && j > 0)
        {
            int i1 = roundMult4((int)Math.ceil((float)i * scaleFactor));
            int j1 = roundMult4((int)Math.ceil((float)j * scaleFactor));
            sourceBitmap = ImageUtils.createBitmap(i1, j1, android.graphics.Bitmap.Config.ARGB_8888);
            blurCanvas = new Canvas(sourceBitmap);
            blurCanvas.drawColor(0xff000000);
            outBitmap = ImageUtils.createBitmap(sourceBitmap);
            adjustedWidthScaleFactor = (float)i1 / (float)i;
            adjustedHeightScaleFactor = (float)j1 / (float)j;
            blurCanvas.scale(adjustedWidthScaleFactor, adjustedHeightScaleFactor);
            invalidateBlur();
        }
    }

    public void setBlurRadius(float f)
    {
        blurRadius = f;
        scaleFactor = 1.0F;
        float f1 = getContext().getResources().getDisplayMetrics().density;
        if(blurRadius > f1)
        {
            blurRadius = blurRadius / f1;
            scaleFactor = scaleFactor / f1;
        }
        if(blurRadius > ImageUtils.MAX_BLUR_RADIUS)
        {
            scaleFactor = scaleFactor * (ImageUtils.MAX_BLUR_RADIUS / blurRadius);
            blurRadius = ImageUtils.MAX_BLUR_RADIUS;
        }
        invalidateBlur();
    }

    public void setRootView(View view)
    {
        if(attachedToWindow && rootView != null)
            rootView.getViewTreeObserver().addOnPreDrawListener(this);
        rootView = view.getRootView();
        if(attachedToWindow && rootView != null)
            rootView.getViewTreeObserver().removeOnPreDrawListener(this);
    }

    private static final String LOG_TAG = com/sonos/acr/view/BlurFrame.getSimpleName();
    float adjustedHeightScaleFactor;
    float adjustedWidthScaleFactor;
    boolean attachedToWindow;
    private Canvas blurCanvas;
    protected final BlurProcessor blurProcessor;
    private float blurRadius;
    int location[];
    boolean observedViewInvalidated;
    private Bitmap outBitmap;
    private Paint paint;
    private View rootView;
    float scaleFactor;
    private Bitmap sourceBitmap;

}
