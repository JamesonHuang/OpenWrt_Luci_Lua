// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.graphics.*;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.widget:
//            BakedBezierInterpolator

final class SwipeProgressBar
{

    public SwipeProgressBar(View view)
    {
        mBounds = new Rect();
        mParent = view;
        mColor1 = 0xb3000000;
        mColor2 = 0x80000000;
        mColor3 = 0x4d000000;
        mColor4 = 0x1a000000;
    }

    private void drawCircle(Canvas canvas, float f, float f1, int i, float f2)
    {
        mPaint.setColor(i);
        canvas.save();
        canvas.translate(f, f1);
        float f3 = INTERPOLATOR.getInterpolation(f2);
        canvas.scale(f3, f3);
        canvas.drawCircle(0.0F, 0.0F, f, mPaint);
        canvas.restore();
    }

    private void drawTrigger(Canvas canvas, int i, int j)
    {
        mPaint.setColor(mColor1);
        canvas.drawCircle(i, j, (float)i * mTriggerPercentage, mPaint);
    }

    void draw(Canvas canvas)
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        int i1;
        i = mBounds.width();
        j = mBounds.height();
        k = i / 2;
        l = j / 2;
        flag = false;
        i1 = canvas.save();
        canvas.clipRect(mBounds);
        if(!mRunning && mFinishTime <= 0L) goto _L2; else goto _L1
_L1:
        long l1;
        long l3;
        float f;
        l1 = AnimationUtils.currentAnimationTimeMillis();
        long l2 = (l1 - mStartTime) % 2000L;
        l3 = (l1 - mStartTime) / 2000L;
        f = (float)l2 / 20F;
        if(mRunning) goto _L4; else goto _L3
_L3:
        if(l1 - mFinishTime < 1000L) goto _L6; else goto _L5
_L5:
        mFinishTime = 0L;
_L7:
        return;
_L6:
        float f6 = (float)((l1 - mFinishTime) % 1000L) / 10F / 100F;
        float f7 = (float)(i / 2) * INTERPOLATOR.getInterpolation(f6);
        mClipRect.set((float)k - f7, 0.0F, f7 + (float)k, j);
        canvas.saveLayerAlpha(mClipRect, 0, 0);
        flag = true;
_L4:
        if(l3 == 0L)
            canvas.drawColor(mColor1);
        else
        if(f >= 0.0F && f < 25F)
            canvas.drawColor(mColor4);
        else
        if(f >= 25F && f < 50F)
            canvas.drawColor(mColor1);
        else
        if(f >= 50F && f < 75F)
            canvas.drawColor(mColor2);
        else
            canvas.drawColor(mColor3);
        if(f >= 0.0F && f <= 25F)
        {
            float f5 = (2.0F * (25F + f)) / 100F;
            drawCircle(canvas, k, l, mColor1, f5);
        }
        if(f >= 0.0F && f <= 50F)
        {
            float f4 = (2.0F * f) / 100F;
            drawCircle(canvas, k, l, mColor2, f4);
        }
        if(f >= 25F && f <= 75F)
        {
            float f3 = (2.0F * (f - 25F)) / 100F;
            drawCircle(canvas, k, l, mColor3, f3);
        }
        if(f >= 50F && f <= 100F)
        {
            float f2 = (2.0F * (f - 50F)) / 100F;
            drawCircle(canvas, k, l, mColor4, f2);
        }
        if(f >= 75F && f <= 100F)
        {
            float f1 = (2.0F * (f - 75F)) / 100F;
            drawCircle(canvas, k, l, mColor1, f1);
        }
        if(mTriggerPercentage > 0.0F && flag)
        {
            canvas.restoreToCount(i1);
            i1 = canvas.save();
            canvas.clipRect(mBounds);
            drawTrigger(canvas, k, l);
        }
        ViewCompat.postInvalidateOnAnimation(mParent, mBounds.left, mBounds.top, mBounds.right, mBounds.bottom);
_L8:
        canvas.restoreToCount(i1);
        if(true) goto _L7; else goto _L2
_L2:
        if(mTriggerPercentage > 0.0F && (double)mTriggerPercentage <= 1.0D)
            drawTrigger(canvas, k, l);
          goto _L8
    }

    boolean isRunning()
    {
        boolean flag;
        if(mRunning || mFinishTime > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void setBounds(int i, int j, int k, int l)
    {
        mBounds.left = i;
        mBounds.top = j;
        mBounds.right = k;
        mBounds.bottom = l;
    }

    void setColorScheme(int i, int j, int k, int l)
    {
        mColor1 = i;
        mColor2 = j;
        mColor3 = k;
        mColor4 = l;
    }

    void setTriggerPercentage(float f)
    {
        mTriggerPercentage = f;
        mStartTime = 0L;
        ViewCompat.postInvalidateOnAnimation(mParent, mBounds.left, mBounds.top, mBounds.right, mBounds.bottom);
    }

    void start()
    {
        if(!mRunning)
        {
            mTriggerPercentage = 0.0F;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = true;
            mParent.postInvalidate();
        }
    }

    void stop()
    {
        if(mRunning)
        {
            mTriggerPercentage = 0.0F;
            mFinishTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = false;
            mParent.postInvalidate();
        }
    }

    private static final int ANIMATION_DURATION_MS = 2000;
    private static final int COLOR1 = 0xb3000000;
    private static final int COLOR2 = 0x80000000;
    private static final int COLOR3 = 0x4d000000;
    private static final int COLOR4 = 0x1a000000;
    private static final int FINISH_ANIMATION_DURATION_MS = 1000;
    private static final Interpolator INTERPOLATOR = BakedBezierInterpolator.getInstance();
    private Rect mBounds;
    private final RectF mClipRect = new RectF();
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private long mFinishTime;
    private final Paint mPaint = new Paint();
    private View mParent;
    private boolean mRunning;
    private long mStartTime;
    private float mTriggerPercentage;

}
