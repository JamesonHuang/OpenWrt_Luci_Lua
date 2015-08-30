// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

class MaterialProgressDrawable extends Drawable
    implements Animatable
{
    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator
    {

        public float getInterpolation(float f)
        {
            return super.getInterpolation(Math.min(1.0F, 2.0F * f));
        }

        private StartCurveInterpolator()
        {
        }

    }

    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator
    {

        public float getInterpolation(float f)
        {
            return super.getInterpolation(Math.max(0.0F, 2.0F * (f - 0.5F)));
        }

        private EndCurveInterpolator()
        {
        }

    }

    private static class Ring
    {

        private void drawTriangle(Canvas canvas, float f, float f1, Rect rect)
        {
            if(mShowArrow)
            {
                float f2;
                float f3;
                float f4;
                if(mArrow == null)
                {
                    mArrow = new Path();
                    mArrow.setFillType(android.graphics.Path.FillType.EVEN_ODD);
                } else
                {
                    mArrow.reset();
                }
                f2 = (float)((int)mStrokeInset / 2) * mArrowScale;
                f3 = (float)(mRingCenterRadius * Math.cos(0.0D) + (double)rect.exactCenterX());
                f4 = (float)(mRingCenterRadius * Math.sin(0.0D) + (double)rect.exactCenterY());
                mArrow.moveTo(0.0F, 0.0F);
                mArrow.lineTo((float)mArrowWidth * mArrowScale, 0.0F);
                mArrow.lineTo(((float)mArrowWidth * mArrowScale) / 2.0F, (float)mArrowHeight * mArrowScale);
                mArrow.offset(f3 - f2, f4);
                mArrow.close();
                mArrowPaint.setColor(mColors[mColorIndex]);
                canvas.rotate((f + f1) - 5F, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(mArrow, mArrowPaint);
            }
        }

        private void invalidateSelf()
        {
            mCallback.invalidateDrawable(null);
        }

        public void draw(Canvas canvas, Rect rect)
        {
            RectF rectf = mTempBounds;
            rectf.set(rect);
            rectf.inset(mStrokeInset, mStrokeInset);
            float f = 360F * (mStartTrim + mRotation);
            float f1 = 360F * (mEndTrim + mRotation) - f;
            mPaint.setColor(mColors[mColorIndex]);
            canvas.drawArc(rectf, f, f1, false, mPaint);
            drawTriangle(canvas, f, f1, rect);
            if(mAlpha < 255)
            {
                mCirclePaint.setColor(mBackgroundColor);
                mCirclePaint.setAlpha(255 - mAlpha);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), rect.width() / 2, mCirclePaint);
            }
        }

        public int getAlpha()
        {
            return mAlpha;
        }

        public double getCenterRadius()
        {
            return mRingCenterRadius;
        }

        public float getEndTrim()
        {
            return mEndTrim;
        }

        public float getInsets()
        {
            return mStrokeInset;
        }

        public float getRotation()
        {
            return mRotation;
        }

        public float getStartTrim()
        {
            return mStartTrim;
        }

        public float getStartingEndTrim()
        {
            return mStartingEndTrim;
        }

        public float getStartingRotation()
        {
            return mStartingRotation;
        }

        public float getStartingStartTrim()
        {
            return mStartingStartTrim;
        }

        public float getStrokeWidth()
        {
            return mStrokeWidth;
        }

        public void goToNextColor()
        {
            mColorIndex = (1 + mColorIndex) % mColors.length;
        }

        public void resetOriginals()
        {
            mStartingStartTrim = 0.0F;
            mStartingEndTrim = 0.0F;
            mStartingRotation = 0.0F;
            setStartTrim(0.0F);
            setEndTrim(0.0F);
            setRotation(0.0F);
        }

        public void setAlpha(int i)
        {
            mAlpha = i;
        }

        public void setArrowDimensions(float f, float f1)
        {
            mArrowWidth = (int)f;
            mArrowHeight = (int)f1;
        }

        public void setArrowScale(float f)
        {
            if(f != mArrowScale)
            {
                mArrowScale = f;
                invalidateSelf();
            }
        }

        public void setBackgroundColor(int i)
        {
            mBackgroundColor = i;
        }

        public void setCenterRadius(double d)
        {
            mRingCenterRadius = d;
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
            mPaint.setColorFilter(colorfilter);
            invalidateSelf();
        }

        public void setColorIndex(int i)
        {
            mColorIndex = i;
        }

        public void setColors(int ai[])
        {
            mColors = ai;
            setColorIndex(0);
        }

        public void setEndTrim(float f)
        {
            mEndTrim = f;
            invalidateSelf();
        }

        public void setInsets(int i, int j)
        {
            float f = Math.min(i, j);
            float f1;
            if(mRingCenterRadius <= 0.0D || f < 0.0F)
                f1 = (float)Math.ceil(mStrokeWidth / 2.0F);
            else
                f1 = (float)((double)(f / 2.0F) - mRingCenterRadius);
            mStrokeInset = f1;
        }

        public void setRotation(float f)
        {
            mRotation = f;
            invalidateSelf();
        }

        public void setShowArrow(boolean flag)
        {
            if(mShowArrow != flag)
            {
                mShowArrow = flag;
                invalidateSelf();
            }
        }

        public void setStartTrim(float f)
        {
            mStartTrim = f;
            invalidateSelf();
        }

        public void setStrokeWidth(float f)
        {
            mStrokeWidth = f;
            mPaint.setStrokeWidth(f);
            invalidateSelf();
        }

        public void storeOriginals()
        {
            mStartingStartTrim = mStartTrim;
            mStartingEndTrim = mEndTrim;
            mStartingRotation = mRotation;
        }

        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint = new Paint();
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final android.graphics.drawable.Drawable.Callback mCallback;
        private final Paint mCirclePaint = new Paint();
        private int mColorIndex;
        private int mColors[];
        private float mEndTrim;
        private final Paint mPaint = new Paint();
        private double mRingCenterRadius;
        private float mRotation;
        private boolean mShowArrow;
        private float mStartTrim;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset;
        private float mStrokeWidth;
        private final RectF mTempBounds = new RectF();

        public Ring(android.graphics.drawable.Drawable.Callback callback)
        {
            mStartTrim = 0.0F;
            mEndTrim = 0.0F;
            mRotation = 0.0F;
            mStrokeWidth = 5F;
            mStrokeInset = 2.5F;
            mCallback = callback;
            mPaint.setStrokeCap(android.graphics.Paint.Cap.SQUARE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mArrowPaint.setStyle(android.graphics.Paint.Style.FILL);
            mArrowPaint.setAntiAlias(true);
        }
    }

    public static interface ProgressDrawableSize
        extends Annotation
    {
    }


    public MaterialProgressDrawable(Context context, View view)
    {
        int ai[] = new int[1];
        ai[0] = 0xff000000;
        COLORS = ai;
        mParent = view;
        mResources = context.getResources();
        mRing = new Ring(mCallback);
        mRing.setColors(COLORS);
        updateSizes(1);
        setupAnimators();
    }

    private void applyFinishTranslation(float f, Ring ring)
    {
        float f1 = (float)(1.0D + Math.floor(ring.getStartingRotation() / 0.8F));
        ring.setStartTrim(ring.getStartingStartTrim() + f * (ring.getStartingEndTrim() - ring.getStartingStartTrim()));
        ring.setRotation(ring.getStartingRotation() + f * (f1 - ring.getStartingRotation()));
    }

    private float getRotation()
    {
        return mRotation;
    }

    private void setSizeParameters(double d, double d1, double d2, double d3, float f, float f1)
    {
        Ring ring = mRing;
        float f2 = mResources.getDisplayMetrics().density;
        mWidth = d * (double)f2;
        mHeight = d1 * (double)f2;
        ring.setStrokeWidth(f2 * (float)d3);
        ring.setCenterRadius(d2 * (double)f2);
        ring.setColorIndex(0);
        ring.setArrowDimensions(f * f2, f1 * f2);
        ring.setInsets((int)mWidth, (int)mHeight);
    }

    private void setupAnimators()
    {
        final Ring ring = mRing;
        Animation animation = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                if(mFinishing)
                {
                    applyFinishTranslation(f, ring);
                } else
                {
                    float f1 = (float)Math.toRadians((double)ring.getStrokeWidth() / (6.2831853071795862D * ring.getCenterRadius()));
                    float f2 = ring.getStartingEndTrim();
                    float f3 = ring.getStartingStartTrim();
                    float f4 = ring.getStartingRotation();
                    float f5 = f2 + (0.8F - f1) * MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(f);
                    ring.setEndTrim(f5);
                    float f6 = f3 + 0.8F * MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(f);
                    ring.setStartTrim(f6);
                    float f7 = f4 + 0.25F * f;
                    ring.setRotation(f7);
                    float f8 = 144F * f + 720F * (mRotationCount / 5F);
                    setRotation(f8);
                }
            }

            final MaterialProgressDrawable this$0;
            final Ring val$ring;

            
            {
                this$0 = MaterialProgressDrawable.this;
                ring = ring1;
                super();
            }
        }
;
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(LINEAR_INTERPOLATOR);
        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation1)
            {
            }

            public void onAnimationRepeat(Animation animation1)
            {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.setStartTrim(ring.getEndTrim());
                if(mFinishing)
                {
                    mFinishing = false;
                    animation1.setDuration(1333L);
                    ring.setShowArrow(false);
                } else
                {
                    mRotationCount = (1.0F + mRotationCount) % 5F;
                }
            }

            public void onAnimationStart(Animation animation1)
            {
                mRotationCount = 0.0F;
            }

            final MaterialProgressDrawable this$0;
            final Ring val$ring;

            
            {
                this$0 = MaterialProgressDrawable.this;
                ring = ring1;
                super();
            }
        }
);
        mAnimation = animation;
    }

    public void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        int i = canvas.save();
        canvas.rotate(mRotation, rect.exactCenterX(), rect.exactCenterY());
        mRing.draw(canvas, rect);
        canvas.restoreToCount(i);
    }

    public int getAlpha()
    {
        return mRing.getAlpha();
    }

    public int getIntrinsicHeight()
    {
        return (int)mHeight;
    }

    public int getIntrinsicWidth()
    {
        return (int)mWidth;
    }

    public int getOpacity()
    {
        return -3;
    }

    public boolean isRunning()
    {
        ArrayList arraylist;
        int i;
        int j;
        arraylist = mAnimators;
        i = arraylist.size();
        j = 0;
_L3:
        Animation animation;
        if(j >= i)
            break MISSING_BLOCK_LABEL_55;
        animation = (Animation)arraylist.get(j);
        if(!animation.hasStarted() || animation.hasEnded()) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public void setAlpha(int i)
    {
        mRing.setAlpha(i);
    }

    public void setArrowScale(float f)
    {
        mRing.setArrowScale(f);
    }

    public void setBackgroundColor(int i)
    {
        mRing.setBackgroundColor(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mRing.setColorFilter(colorfilter);
    }

    public transient void setColorSchemeColors(int ai[])
    {
        mRing.setColors(ai);
        mRing.setColorIndex(0);
    }

    public void setProgressRotation(float f)
    {
        mRing.setRotation(f);
    }

    void setRotation(float f)
    {
        mRotation = f;
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f1)
    {
        mRing.setStartTrim(f);
        mRing.setEndTrim(f1);
    }

    public void showArrow(boolean flag)
    {
        mRing.setShowArrow(flag);
    }

    public void start()
    {
        mAnimation.reset();
        mRing.storeOriginals();
        if(mRing.getEndTrim() != mRing.getStartTrim())
        {
            mFinishing = true;
            mAnimation.setDuration(666L);
            mParent.startAnimation(mAnimation);
        } else
        {
            mRing.setColorIndex(0);
            mRing.resetOriginals();
            mAnimation.setDuration(1333L);
            mParent.startAnimation(mAnimation);
        }
    }

    public void stop()
    {
        mParent.clearAnimation();
        setRotation(0.0F);
        mRing.setShowArrow(false);
        mRing.setColorIndex(0);
        mRing.resetOriginals();
    }

    public void updateSizes(int i)
    {
        if(i == 0)
            setSizeParameters(56D, 56D, 12.5D, 3D, 12F, 6F);
        else
            setSizeParameters(40D, 40D, 8.75D, 2.5D, 10F, 5F);
    }

    private static final int ANIMATION_DURATION = 1333;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 5F;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75F;
    private static final float CENTER_RADIUS_LARGE = 12.5F;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    static final int DEFAULT = 1;
    private static final Interpolator EASE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final Interpolator END_CURVE_INTERPOLATOR = new EndCurveInterpolator();
    static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8F;
    private static final float NUM_POINTS = 5F;
    private static final Interpolator START_CURVE_INTERPOLATOR = new StartCurveInterpolator();
    private static final float STROKE_WIDTH = 2.5F;
    private static final float STROKE_WIDTH_LARGE = 3F;
    private final int COLORS[];
    private Animation mAnimation;
    private final ArrayList mAnimators = new ArrayList();
    private final android.graphics.drawable.Drawable.Callback mCallback = new android.graphics.drawable.Drawable.Callback() {

        public void invalidateDrawable(Drawable drawable)
        {
            invalidateSelf();
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
        {
            scheduleSelf(runnable, l);
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable)
        {
            unscheduleSelf(runnable);
        }

        final MaterialProgressDrawable this$0;

            
            {
                this$0 = MaterialProgressDrawable.this;
                super();
            }
    }
;
    boolean mFinishing;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;







/*
    static float access$502(MaterialProgressDrawable materialprogressdrawable, float f)
    {
        materialprogressdrawable.mRotationCount = f;
        return f;
    }

*/
}
