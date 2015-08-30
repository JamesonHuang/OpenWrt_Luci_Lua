// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class RemoteBitmapDrawable extends Drawable
{

    public RemoteBitmapDrawable(Bitmap bitmap, Rect rect)
    {
        outBitmap = bitmap;
        outRect = rect;
        blurPaint = new Paint();
        blurPaint.setAntiAlias(true);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outBitmap, outRect, getBounds(), blurPaint);
    }

    public int getIntrinsicHeight()
    {
        return outRect.height();
    }

    public int getIntrinsicWidth()
    {
        return outRect.width();
    }

    public int getOpacity()
    {
        return -1;
    }

    public void setAlpha(int i)
    {
        blurPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        blurPaint.setColorFilter(colorfilter);
    }

    public void setDither(boolean flag)
    {
        blurPaint.setDither(true);
    }

    private static final String LOG_TAG = com/sonos/acr/RemoteBitmapDrawable.getSimpleName();
    Paint blurPaint;
    private Bitmap outBitmap;
    private Rect outRect;

}
