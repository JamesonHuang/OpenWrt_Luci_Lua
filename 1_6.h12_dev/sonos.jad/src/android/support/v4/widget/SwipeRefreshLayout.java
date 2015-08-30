// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.AbsListView;

// Referenced classes of package android.support.v4.widget:
//            CircleImageView, MaterialProgressDrawable

public class SwipeRefreshLayout extends ViewGroup
{
    public static interface OnRefreshListener
    {

        public abstract void onRefresh();
    }


    public SwipeRefreshLayout(Context context)
    {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mRefreshing = false;
        mTotalDragDistance = -1F;
        mOriginalOffsetCalculated = false;
        mActivePointerId = -1;
        mCircleViewIndex = -1;
        mAnimateToCorrectPosition = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                int i;
                int j;
                if(!mUsingCustomStart)
                    i = (int)(mSpinnerFinalOffset - (float)Math.abs(mOriginalOffsetTop));
                else
                    i = (int)mSpinnerFinalOffset;
                j = (mFrom + (int)(f * (float)(i - mFrom))) - mCircleView.getTop();
                setTargetOffsetTopAndBottom(j, false);
                mProgress.setArrowScale(1.0F - f);
            }

            final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
        }
;
        mAnimateToStartPosition = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                moveToStart(f);
            }

            final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
        }
;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMediumAnimationDuration = getResources().getInteger(0x10e0001);
        setWillNotDraw(false);
        mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, LAYOUT_ATTRS);
        setEnabled(typedarray.getBoolean(0, true));
        typedarray.recycle();
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        mCircleWidth = (int)(40F * displaymetrics.density);
        mCircleHeight = (int)(40F * displaymetrics.density);
        createProgressView();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        mSpinnerFinalOffset = 64F * displaymetrics.density;
        mTotalDragDistance = mSpinnerFinalOffset;
    }

    private void animateOffsetToCorrectPosition(int i, android.view.animation.Animation.AnimationListener animationlistener)
    {
        mFrom = i;
        mAnimateToCorrectPosition.reset();
        mAnimateToCorrectPosition.setDuration(200L);
        mAnimateToCorrectPosition.setInterpolator(mDecelerateInterpolator);
        if(animationlistener != null)
            mCircleView.setAnimationListener(animationlistener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, android.view.animation.Animation.AnimationListener animationlistener)
    {
        if(mScale)
        {
            startScaleDownReturnToStartAnimation(i, animationlistener);
        } else
        {
            mFrom = i;
            mAnimateToStartPosition.reset();
            mAnimateToStartPosition.setDuration(200L);
            mAnimateToStartPosition.setInterpolator(mDecelerateInterpolator);
            if(animationlistener != null)
                mCircleView.setAnimationListener(animationlistener);
            mCircleView.clearAnimation();
            mCircleView.startAnimation(mAnimateToStartPosition);
        }
    }

    private void createProgressView()
    {
        mCircleView = new CircleImageView(getContext(), 0xfffafafa, 20F);
        mProgress = new MaterialProgressDrawable(getContext(), this);
        mProgress.setBackgroundColor(0xfffafafa);
        mCircleView.setImageDrawable(mProgress);
        mCircleView.setVisibility(8);
        addView(mCircleView);
    }

    private void ensureTarget()
    {
        if(mTarget != null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        if(i >= getChildCount()) goto _L2; else goto _L3
_L3:
        View view = getChildAt(i);
        if(view.equals(mCircleView)) goto _L5; else goto _L4
_L4:
        mTarget = view;
_L2:
        return;
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private float getMotionEventY(MotionEvent motionevent, int i)
    {
        int j = MotionEventCompat.findPointerIndex(motionevent, i);
        float f;
        if(j < 0)
            f = -1F;
        else
            f = MotionEventCompat.getY(motionevent, j);
        return f;
    }

    private boolean isAlphaUsedForScale()
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT < 11)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isAnimationRunning(Animation animation)
    {
        boolean flag;
        if(animation != null && animation.hasStarted() && !animation.hasEnded())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void moveToStart(float f)
    {
        setTargetOffsetTopAndBottom((mFrom + (int)(f * (float)(mOriginalOffsetTop - mFrom))) - mCircleView.getTop(), false);
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = MotionEventCompat.getActionIndex(motionevent);
        if(MotionEventCompat.getPointerId(motionevent, i) == mActivePointerId)
        {
            int j;
            if(i == 0)
                j = 1;
            else
                j = 0;
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
        }
    }

    private void setAnimationProgress(float f)
    {
        if(isAlphaUsedForScale())
        {
            setColorViewAlpha((int)(255F * f));
        } else
        {
            ViewCompat.setScaleX(mCircleView, f);
            ViewCompat.setScaleY(mCircleView, f);
        }
    }

    private void setColorViewAlpha(int i)
    {
        mCircleView.getBackground().setAlpha(i);
        mProgress.setAlpha(i);
    }

    private void setRefreshing(boolean flag, boolean flag1)
    {
        if(mRefreshing != flag)
        {
            mNotify = flag1;
            ensureTarget();
            mRefreshing = flag;
            if(mRefreshing)
                animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, mRefreshListener);
            else
                startScaleDownAnimation(mRefreshListener);
        }
    }

    private void setTargetOffsetTopAndBottom(int i, boolean flag)
    {
        mCircleView.bringToFront();
        mCircleView.offsetTopAndBottom(i);
        mCurrentTargetOffsetTop = mCircleView.getTop();
        if(flag && android.os.Build.VERSION.SDK_INT < 11)
            invalidate();
    }

    private Animation startAlphaAnimation(final int startingAlpha, final int endingAlpha)
    {
        Object obj;
        if(mScale && isAlphaUsedForScale())
        {
            obj = null;
        } else
        {
            obj = new Animation() {

                public void applyTransformation(float f, Transformation transformation)
                {
                    mProgress.setAlpha((int)((float)startingAlpha + f * (float)(endingAlpha - startingAlpha)));
                }

                final SwipeRefreshLayout this$0;
                final int val$endingAlpha;
                final int val$startingAlpha;

            
            {
                this$0 = SwipeRefreshLayout.this;
                startingAlpha = i;
                endingAlpha = j;
                super();
            }
            }
;
            ((Animation) (obj)).setDuration(300L);
            mCircleView.setAnimationListener(null);
            mCircleView.clearAnimation();
            mCircleView.startAnimation(((Animation) (obj)));
        }
        return ((Animation) (obj));
    }

    private void startProgressAlphaMaxAnimation()
    {
        mAlphaMaxAnimation = startAlphaAnimation(mProgress.getAlpha(), 255);
    }

    private void startProgressAlphaStartAnimation()
    {
        mAlphaStartAnimation = startAlphaAnimation(mProgress.getAlpha(), 76);
    }

    private void startScaleDownAnimation(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mScaleDownAnimation = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                setAnimationProgress(1.0F - f);
            }

            final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
        }
;
        mScaleDownAnimation.setDuration(150L);
        mCircleView.setAnimationListener(animationlistener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleDownAnimation);
    }

    private void startScaleDownReturnToStartAnimation(int i, android.view.animation.Animation.AnimationListener animationlistener)
    {
        mFrom = i;
        if(isAlphaUsedForScale())
            mStartingScale = mProgress.getAlpha();
        else
            mStartingScale = ViewCompat.getScaleX(mCircleView);
        mScaleDownToStartAnimation = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                float f1 = mStartingScale + f * -mStartingScale;
                setAnimationProgress(f1);
                moveToStart(f);
            }

            final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
        }
;
        mScaleDownToStartAnimation.setDuration(150L);
        if(animationlistener != null)
            mCircleView.setAnimationListener(animationlistener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mCircleView.setVisibility(0);
        if(android.os.Build.VERSION.SDK_INT >= 11)
            mProgress.setAlpha(255);
        mScaleAnimation = new Animation() {

            public void applyTransformation(float f, Transformation transformation)
            {
                setAnimationProgress(f);
            }

            final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
        }
;
        mScaleAnimation.setDuration(mMediumAnimationDuration);
        if(animationlistener != null)
            mCircleView.setAnimationListener(animationlistener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleAnimation);
    }

    public boolean canChildScrollUp()
    {
        boolean flag = true;
        if(android.os.Build.VERSION.SDK_INT >= 14) goto _L2; else goto _L1
_L1:
        if(!(mTarget instanceof AbsListView)) goto _L4; else goto _L3
_L3:
        AbsListView abslistview = (AbsListView)mTarget;
        if(abslistview.getChildCount() <= 0 || abslistview.getFirstVisiblePosition() <= 0 && abslistview.getChildAt(0).getTop() >= abslistview.getPaddingTop())
            flag = false;
_L6:
        return flag;
_L4:
        if(mTarget.getScrollY() <= 0)
            flag = false;
        continue; /* Loop/switch isn't completed */
_L2:
        flag = ViewCompat.canScrollVertically(mTarget, -1);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if(mCircleViewIndex >= 0) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        if(j == i - 1)
            j = mCircleViewIndex;
        else
        if(j >= mCircleViewIndex)
            j++;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int getProgressCircleDiameter()
    {
        int i;
        if(mCircleView != null)
            i = mCircleView.getMeasuredHeight();
        else
            i = 0;
        return i;
    }

    public boolean isRefreshing()
    {
        return mRefreshing;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        flag = false;
        ensureTarget();
        i = MotionEventCompat.getActionMasked(motionevent);
        if(mReturningToStart && i == 0)
            mReturningToStart = false;
        if(isEnabled() && !mReturningToStart && !canChildScrollUp() && !mRefreshing) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        i;
        JVM INSTR tableswitch 0 6: default 100
    //                   0 108
    //                   1 270
    //                   2 167
    //                   3 270
    //                   4 100
    //                   5 100
    //                   6 262;
           goto _L3 _L4 _L5 _L6 _L5 _L3 _L3 _L7
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_270;
_L9:
        flag = mIsBeingDragged;
          goto _L1
_L4:
        float f1;
        setTargetOffsetTopAndBottom(mOriginalOffsetTop - mCircleView.getTop(), true);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mIsBeingDragged = false;
        f1 = getMotionEventY(motionevent, mActivePointerId);
        if(f1 == -1F) goto _L1; else goto _L8
_L8:
        mInitialDownY = f1;
          goto _L9
_L6:
label0:
        {
            if(mActivePointerId != -1)
                break label0;
            Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
        }
          goto _L1
        float f = getMotionEventY(motionevent, mActivePointerId);
        if(f == -1F) goto _L1; else goto _L10
_L10:
        if(f - mInitialDownY > (float)mTouchSlop && !mIsBeingDragged)
        {
            mInitialMotionY = mInitialDownY + (float)mTouchSlop;
            mIsBeingDragged = true;
            mProgress.setAlpha(76);
        }
          goto _L9
_L7:
        onSecondaryPointerUp(motionevent);
          goto _L9
        mIsBeingDragged = false;
        mActivePointerId = -1;
          goto _L9
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        i1 = getMeasuredWidth();
        j1 = getMeasuredHeight();
        if(getChildCount() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(mTarget == null)
            ensureTarget();
        if(mTarget != null)
        {
            View view = mTarget;
            int k1 = getPaddingLeft();
            int l1 = getPaddingTop();
            int i2 = i1 - getPaddingLeft() - getPaddingRight();
            int j2 = j1 - getPaddingTop() - getPaddingBottom();
            view.layout(k1, l1, k1 + i2, l1 + j2);
            int k2 = mCircleView.getMeasuredWidth();
            int l2 = mCircleView.getMeasuredHeight();
            mCircleView.layout(i1 / 2 - k2 / 2, mCurrentTargetOffsetTop, i1 / 2 + k2 / 2, l2 + mCurrentTargetOffsetTop);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(mTarget == null)
            ensureTarget();
        if(mTarget != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mTarget.measure(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 0x40000000));
        mCircleView.measure(android.view.View.MeasureSpec.makeMeasureSpec(mCircleWidth, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(mCircleHeight, 0x40000000));
        if(!mUsingCustomStart && !mOriginalOffsetCalculated)
        {
            mOriginalOffsetCalculated = true;
            int l = -mCircleView.getMeasuredHeight();
            mOriginalOffsetTop = l;
            mCurrentTargetOffsetTop = l;
        }
        mCircleViewIndex = -1;
        int k = 0;
        do
        {
            if(k < getChildCount())
            {
label0:
                {
                    if(getChildAt(k) != mCircleView)
                        break label0;
                    mCircleViewIndex = k;
                }
            }
            if(true)
                continue;
            k++;
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = MotionEventCompat.getActionMasked(motionevent);
        if(mReturningToStart && i == 0)
            mReturningToStart = false;
        if(isEnabled() && !mReturningToStart && !canChildScrollUp()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L13:
        return flag;
_L2:
        i;
        JVM INSTR tableswitch 0 6: default 88
    //                   0 93
    //                   1 563
    //                   2 110
    //                   3 563
    //                   4 88
    //                   5 540
    //                   6 555;
           goto _L3 _L4 _L5 _L6 _L5 _L3 _L7 _L8
_L3:
        flag = true;
        continue; /* Loop/switch isn't completed */
_L4:
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mIsBeingDragged = false;
        continue; /* Loop/switch isn't completed */
_L6:
        int j = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
        if(j < 0)
        {
            Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        float f1 = 0.5F * (MotionEventCompat.getY(motionevent, j) - mInitialMotionY);
        if(!mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        mProgress.showArrow(true);
        float f2 = f1 / mTotalDragDistance;
        if(f2 < 0.0F)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        float f3 = Math.min(1.0F, Math.abs(f2));
        float f4 = (5F * (float)Math.max((double)f3 - 0.40000000000000002D, 0.0D)) / 3F;
        float f5 = Math.abs(f1) - mTotalDragDistance;
        float f6;
        float f7;
        float f8;
        float f9;
        int k;
        float f10;
        float f11;
        if(mUsingCustomStart)
            f6 = mSpinnerFinalOffset - (float)mOriginalOffsetTop;
        else
            f6 = mSpinnerFinalOffset;
        f7 = Math.max(0.0F, Math.min(f5, 2.0F * f6) / f6);
        f8 = 2.0F * (float)((double)(f7 / 4F) - Math.pow(f7 / 4F, 2D));
        f9 = 2.0F * (f6 * f8);
        k = mOriginalOffsetTop + (int)(f9 + f6 * f3);
        if(mCircleView.getVisibility() != 0)
            mCircleView.setVisibility(0);
        if(!mScale)
        {
            ViewCompat.setScaleX(mCircleView, 1.0F);
            ViewCompat.setScaleY(mCircleView, 1.0F);
        }
        if(f1 >= mTotalDragDistance) goto _L10; else goto _L9
_L9:
        if(mScale)
            setAnimationProgress(f1 / mTotalDragDistance);
        if(mProgress.getAlpha() > 76 && !isAnimationRunning(mAlphaStartAnimation))
            startProgressAlphaStartAnimation();
        f11 = f4 * 0.8F;
        mProgress.setStartEndTrim(0.0F, Math.min(0.8F, f11));
        mProgress.setArrowScale(Math.min(1.0F, f4));
_L11:
        f10 = 0.5F * (-0.25F + 0.4F * f4 + 2.0F * f8);
        mProgress.setProgressRotation(f10);
        setTargetOffsetTopAndBottom(k - mCurrentTargetOffsetTop, true);
        continue; /* Loop/switch isn't completed */
_L10:
        if(mProgress.getAlpha() < 255 && !isAnimationRunning(mAlphaMaxAnimation))
            startProgressAlphaMaxAnimation();
        if(true) goto _L11; else goto _L7
_L7:
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, MotionEventCompat.getActionIndex(motionevent));
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        if(true) goto _L3; else goto _L5
_L5:
        if(mActivePointerId == -1)
        {
            if(i == 1)
                Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        float f = 0.5F * (MotionEventCompat.getY(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId)) - mInitialMotionY);
        mIsBeingDragged = false;
        if(f <= mTotalDragDistance)
            break; /* Loop/switch isn't completed */
        setRefreshing(true, true);
_L14:
        mActivePointerId = -1;
        flag = false;
        if(true) goto _L13; else goto _L12
_L12:
        mRefreshing = false;
        mProgress.setStartEndTrim(0.0F, 0.0F);
        android.view.animation.Animation.AnimationListener animationlistener = null;
        if(!mScale)
            animationlistener = new android.view.animation.Animation.AnimationListener() {

                public void onAnimationEnd(Animation animation)
                {
                    if(!mScale)
                        startScaleDownAnimation(null);
                }

                public void onAnimationRepeat(Animation animation)
                {
                }

                public void onAnimationStart(Animation animation)
                {
                }

                final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
            }
;
        animateOffsetToStartPosition(mCurrentTargetOffsetTop, animationlistener);
        mProgress.showArrow(false);
          goto _L14
        if(true) goto _L13; else goto _L15
_L15:
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
    }

    public transient void setColorScheme(int ai[])
    {
        setColorSchemeResources(ai);
    }

    public transient void setColorSchemeColors(int ai[])
    {
        ensureTarget();
        mProgress.setColorSchemeColors(ai);
    }

    public transient void setColorSchemeResources(int ai[])
    {
        Resources resources = getResources();
        int ai1[] = new int[ai.length];
        for(int i = 0; i < ai.length; i++)
            ai1[i] = resources.getColor(ai[i]);

        setColorSchemeColors(ai1);
    }

    public void setDistanceToTriggerSync(int i)
    {
        mTotalDragDistance = i;
    }

    public void setOnRefreshListener(OnRefreshListener onrefreshlistener)
    {
        mListener = onrefreshlistener;
    }

    public void setProgressBackgroundColor(int i)
    {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i)
    {
        mCircleView.setBackgroundColor(i);
        mProgress.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i)
    {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setProgressViewEndTarget(boolean flag, int i)
    {
        mSpinnerFinalOffset = i;
        mScale = flag;
        mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean flag, int i, int j)
    {
        mScale = flag;
        mCircleView.setVisibility(8);
        mCurrentTargetOffsetTop = i;
        mOriginalOffsetTop = i;
        mSpinnerFinalOffset = j;
        mUsingCustomStart = true;
        mCircleView.invalidate();
    }

    public void setRefreshing(boolean flag)
    {
        if(flag && mRefreshing != flag)
        {
            mRefreshing = flag;
            int i;
            if(!mUsingCustomStart)
                i = (int)(mSpinnerFinalOffset + (float)mOriginalOffsetTop);
            else
                i = (int)mSpinnerFinalOffset;
            setTargetOffsetTopAndBottom(i - mCurrentTargetOffsetTop, true);
            mNotify = false;
            startScaleUpAnimation(mRefreshListener);
        } else
        {
            setRefreshing(flag, false);
        }
    }

    public void setSize(int i)
    {
        if(i == 0 || i == 1)
        {
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            if(i == 0)
            {
                int k = (int)(56F * displaymetrics.density);
                mCircleWidth = k;
                mCircleHeight = k;
            } else
            {
                int j = (int)(40F * displaymetrics.density);
                mCircleWidth = j;
                mCircleHeight = j;
            }
            mCircleView.setImageDrawable(null);
            mProgress.updateSizes(i);
            mCircleView.setImageDrawable(mProgress);
        }
    }

    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = 0xfffafafa;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2F;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5F;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int LAYOUT_ATTRS[];
    private static final String LOG_TAG = android/support/v4/widget/SwipeRefreshLayout.getSimpleName();
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8F;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleHeight;
    private CircleImageView mCircleView;
    private int mCircleViewIndex;
    private int mCircleWidth;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNotify;
    private boolean mOriginalOffsetCalculated;
    protected int mOriginalOffsetTop;
    private MaterialProgressDrawable mProgress;
    private android.view.animation.Animation.AnimationListener mRefreshListener = new android.view.animation.Animation.AnimationListener() {

        public void onAnimationEnd(Animation animation)
        {
            if(mRefreshing)
            {
                mProgress.setAlpha(255);
                mProgress.start();
                if(mNotify && mListener != null)
                    mListener.onRefresh();
            } else
            {
                mProgress.stop();
                mCircleView.setVisibility(8);
                setColorViewAlpha(255);
                if(mScale)
                    setAnimationProgress(0.0F);
                else
                    setTargetOffsetTopAndBottom(mOriginalOffsetTop - mCurrentTargetOffsetTop, true);
            }
            mCurrentTargetOffsetTop = mCircleView.getTop();
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        final SwipeRefreshLayout this$0;

            
            {
                this$0 = SwipeRefreshLayout.this;
                super();
            }
    }
;
    private boolean mRefreshing;
    private boolean mReturningToStart;
    private boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private float mSpinnerFinalOffset;
    private float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private int mTouchSlop;
    private boolean mUsingCustomStart;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x101000e;
        LAYOUT_ATTRS = ai;
    }
















/*
    static int access$802(SwipeRefreshLayout swiperefreshlayout, int i)
    {
        swiperefreshlayout.mCurrentTargetOffsetTop = i;
        return i;
    }

*/

}
