// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public abstract class RoundedBitmapDrawable extends Drawable
{

    RoundedBitmapDrawable(Resources resources, Bitmap bitmap)
    {
        mTargetDensity = 160;
        mGravity = 119;
        mPaint = new Paint(6);
        mApplyGravity = true;
        if(resources != null)
            mTargetDensity = resources.getDisplayMetrics().densityDpi;
        mBitmap = bitmap;
        if(mBitmap != null)
        {
            computeBitmapSize();
            mBitmapShader = new BitmapShader(mBitmap, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
        } else
        {
            mBitmapHeight = -1;
            mBitmapWidth = -1;
        }
    }

    private void computeBitmapSize()
    {
        mBitmapWidth = mBitmap.getScaledWidth(mTargetDensity);
        mBitmapHeight = mBitmap.getScaledHeight(mTargetDensity);
    }

    private static boolean isGreaterThanZero(float f)
    {
        boolean flag;
        if(Float.compare(f, 0.0F) > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void draw(Canvas canvas)
    {
        Bitmap bitmap = mBitmap;
        if(bitmap != null)
        {
            updateDstRect();
            Paint paint = mPaint;
            if(paint.getShader() == null)
                canvas.drawBitmap(bitmap, null, mDstRect, paint);
            else
                canvas.drawRoundRect(mDstRectF, mCornerRadius, mCornerRadius, paint);
        }
    }

    public int getAlpha()
    {
        return mPaint.getAlpha();
    }

    public final Bitmap getBitmap()
    {
        return mBitmap;
    }

    public ColorFilter getColorFilter()
    {
        return mPaint.getColorFilter();
    }

    public float getCornerRadius()
    {
        return mCornerRadius;
    }

    public int getGravity()
    {
        return mGravity;
    }

    public int getIntrinsicHeight()
    {
        return mBitmapHeight;
    }

    public int getIntrinsicWidth()
    {
        return mBitmapWidth;
    }

    public int getOpacity()
    {
        byte byte0 = -3;
        if(mGravity == 119) goto _L2; else goto _L1
_L1:
        return byte0;
_L2:
        Bitmap bitmap = mBitmap;
        if(bitmap != null && !bitmap.hasAlpha() && mPaint.getAlpha() >= 255 && !isGreaterThanZero(mCornerRadius))
            byte0 = -1;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public final Paint getPaint()
    {
        return mPaint;
    }

    void gravityCompatApply(int i, int j, int k, Rect rect, Rect rect1)
    {
        throw new UnsupportedOperationException();
    }

    public boolean hasAntiAlias()
    {
        return mPaint.isAntiAlias();
    }

    public boolean hasMipMap()
    {
        throw new UnsupportedOperationException();
    }

    public void setAlpha(int i)
    {
        if(i != mPaint.getAlpha())
        {
            mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setAntiAlias(boolean flag)
    {
        mPaint.setAntiAlias(flag);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public void setCornerRadius(float f)
    {
        if(isGreaterThanZero(f))
            mPaint.setShader(mBitmapShader);
        else
            mPaint.setShader(null);
        mCornerRadius = f;
    }

    public void setDither(boolean flag)
    {
        mPaint.setDither(flag);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean flag)
    {
        mPaint.setFilterBitmap(flag);
        invalidateSelf();
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            mGravity = i;
            mApplyGravity = true;
            invalidateSelf();
        }
    }

    public void setMipMap(boolean flag)
    {
        throw new UnsupportedOperationException();
    }

    public void setTargetDensity(int i)
    {
        if(mTargetDensity != i)
        {
            if(i == 0)
                i = 160;
            mTargetDensity = i;
            if(mBitmap != null)
                computeBitmapSize();
            invalidateSelf();
        }
    }

    public void setTargetDensity(Canvas canvas)
    {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displaymetrics)
    {
        setTargetDensity(displaymetrics.densityDpi);
    }

    void updateDstRect()
    {
        if(mApplyGravity)
        {
            gravityCompatApply(mGravity, mBitmapWidth, mBitmapHeight, getBounds(), mDstRect);
            mDstRectF.set(mDstRect);
            mApplyGravity = false;
        }
    }

    private static final int DEFAULT_PAINT_FLAGS = 6;
    private boolean mApplyGravity;
    Bitmap mBitmap;
    private int mBitmapHeight;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect = new Rect();
    final RectF mDstRectF = new RectF();
    private int mGravity;
    private Paint mPaint;
    private int mTargetDensity;
}
