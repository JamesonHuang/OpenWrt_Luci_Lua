// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import com.sonos.acr.imaging.BlurProcessor;
import com.sonos.acr.imaging.JavaBlur;
import com.sonos.acr.util.ImageUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosImageView

public class BlurImageView extends SonosImageView
{

    public BlurImageView(Context context)
    {
        super(context);
        scaleFactor = 1.0F;
        paint = new Paint();
        Object obj;
        if(isInEditMode())
            obj = new JavaBlur();
        else
            obj = ImageUtils.createBlurProcessor(context);
        blurProcessor = ((BlurProcessor) (obj));
    }

    private void refresh()
    {
        if(imageBitmap != null && sourceBitmap != null && outBitmap != null)
        {
            ImageUtils.scaleDrawable(imageBitmap, sourceBitmap, getScaleType());
            blurProcessor.performBlur(sourceBitmap, outBitmap, blurRadius);
            if(android.os.Build.VERSION.SDK_INT >= 11 && android.os.Build.VERSION.SDK_INT <= 13)
                (new Canvas(outBitmap)).drawPoint(0.0F, 0.0F, paint);
            BitmapDrawable bitmapdrawable = new BitmapDrawable(outBitmap);
            bitmapdrawable.setDither(true);
            super.updateImageDrawable(bitmapdrawable);
            invalidate();
        }
    }

    public static int roundMult4(int i)
    {
        return -4 & i + 2;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
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
            sourceBitmap = ImageUtils.createBitmap(roundMult4((int)((float)i * scaleFactor)), roundMult4((int)((float)j * scaleFactor)), android.graphics.Bitmap.Config.ARGB_8888);
            outBitmap = ImageUtils.createBitmap(sourceBitmap);
            refresh();
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
        refresh();
    }

    protected void updateImageDrawable(Drawable drawable)
    {
        imageBitmap = drawable;
        refresh();
    }

    private static final String LOG_TAG = com/sonos/acr/view/BlurImageView.getSimpleName();
    protected BlurProcessor blurProcessor;
    private float blurRadius;
    private Drawable imageBitmap;
    private Bitmap outBitmap;
    private Paint paint;
    float scaleFactor;
    private Bitmap sourceBitmap;

}
