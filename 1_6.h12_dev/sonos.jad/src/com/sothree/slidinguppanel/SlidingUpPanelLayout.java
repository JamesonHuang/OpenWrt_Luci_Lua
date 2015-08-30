// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sothree.slidinguppanel;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.*;
import android.view.*;
import com.sonos.acr.util.SLog;

public class SlidingUpPanelLayout extends ViewGroup
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            writeToParcel(parcel, i);
            int j;
            if(isExpanded)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        boolean isExpanded;


        private SavedState(Parcel parcel)
        {
            BaseSavedState(parcel);
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            isExpanded = flag;
        }


        SavedState(Parcelable parcelable)
        {
            BaseSavedState(parcelable);
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        private static final int ATTRS[];
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;

        static 
        {
            int ai[] = new int[1];
            ai[0] = 0x1010181;
            ATTRS = ai;
        }

        public LayoutParams()
        {
            MarginLayoutParams(-1, -1);
        }

        public LayoutParams(int i, int j)
        {
            MarginLayoutParams(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            MarginLayoutParams(context, attributeset);
            context.obtainStyledAttributes(attributeset, ATTRS).recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            MarginLayoutParams(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            MarginLayoutParams(marginlayoutparams);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            MarginLayoutParams(layoutparams);
        }
    }

    private class DragHelperCallback extends android.support.v4.widget.ViewDragHelper.Callback
    {

        public int clampViewPositionVertical(View view, int i, int j)
        {
            int k = getTopBound();
            int l = k + mSlideRange;
            return Math.min(Math.max(i, k), l);
        }

        public int getViewVerticalDragRange(View view)
        {
            return mSlideRange;
        }

        public void onViewCaptured(View view, int i)
        {
            setAllChildrenVisible();
        }

        public void onViewDragStateChanged(int i)
        {
            if(mDragHelper.getViewDragState() == 0)
                if((double)mSlideOffset > 0.0D && (double)mSlideOffset < 1.0D)
                    smoothSlideTo(Math.round(mSlideOffset), 0);
                else
                if(mSlideOffset == 0.0F)
                {
                    updateObscuredViewVisibility();
                    dispatchOnPanelExpanded(mSlideableView);
                    mPreservedExpandedState = true;
                } else
                {
                    dispatchOnPanelCollapsed(mSlideableView);
                    mPreservedExpandedState = false;
                }
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            onPanelDragged(j);
            invalidate();
        }

        public void onViewReleased(View view, float f, float f1)
        {
            int i = getTopBound();
            if(f1 > 0.0F || f1 == 0.0F && mSlideOffset > 0.5F)
                i += mSlideRange;
            mDragHelper.settleCapturedViewAt(view.getLeft(), i);
            invalidate();
        }

        public boolean tryCaptureView(View view, int i)
        {
            boolean flag;
            if(mIsUnableToDrag)
                flag = false;
            else
                flag = ((LayoutParams)view.getLayoutParams()).slideable;
            return flag;
        }

        final SlidingUpPanelLayout this$0;

        private DragHelperCallback()
        {
            this$0 = SlidingUpPanelLayout.this;
            Callback();
        }

    }

    public static class SimplePanelSlideListener
        implements PanelSlideListener
    {

        public void onDrawChild(Canvas canvas, View view, long l)
        {
        }

        public void onLayoutChanged(View view)
        {
        }

        public void onPanelCollapsed(View view)
        {
        }

        public void onPanelExpanded(View view)
        {
        }

        public void onPanelSlide(View view, float f)
        {
        }

        public SimplePanelSlideListener()
        {
        }
    }

    public static interface PanelSlideListener
    {

        public abstract void onDrawChild(Canvas canvas, View view, long l);

        public abstract void onLayoutChanged(View view);

        public abstract void onPanelCollapsed(View view);

        public abstract void onPanelExpanded(View view);

        public abstract void onPanelSlide(View view, float f);
    }


    public SlidingUpPanelLayout(Context context)
    {
        this(context, null);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCoveredFadeColor = 0x99000000;
        mCoveredFadePaint = new Paint();
        mPreservedExpandedState = true;
        mFirstLayout = true;
        mTmpRect = new Rect();
        shouldAllowTap = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SlidingUpPanelLayout, i, 0);
        float f = context.getResources().getDisplayMetrics().density;
        mPanelHeight = typedarray.getDimensionPixelSize(1, (int)(0.5F + 68F * f));
        mShadowHeight = typedarray.getDimensionPixelSize(2, (int)(0.5F + 4F * f));
        clipObscured = typedarray.getBoolean(3, true);
        setCoveredFadeColor(typedarray.getColor(0, 0x99000000));
        typedarray.recycle();
        setWillNotDraw(false);
        mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback());
        mDragHelper.setMinVelocity(400F * f);
        mCanSlide = true;
    }

    private boolean collapsePane(View view, int i)
    {
        boolean flag = false;
        if(mFirstLayout || smoothSlideTo(1.0F, i))
        {
            mPreservedExpandedState = false;
            if(mFirstLayout)
                dispatchOnPanelCollapsed(view);
            flag = true;
        }
        return flag;
    }

    private boolean expandPane(View view, int i)
    {
        boolean flag = true;
        if(mFirstLayout || smoothSlideTo(0.0F, i))
        {
            mPreservedExpandedState = flag;
            if(mFirstLayout)
                dispatchOnPanelExpanded(view);
        } else
        {
            flag = false;
        }
        return flag;
    }

    private int getTopBound()
    {
        View view = getChildAt(1);
        int i = getPaddingTop();
        int j;
        if(view != null)
            j = ((LayoutParams)view.getLayoutParams()).topMargin;
        else
            j = 0;
        return j + i;
    }

    private static boolean hasOpaqueBackground(View view)
    {
        boolean flag = false;
        Drawable drawable = view.getBackground();
        if(drawable != null && drawable.getOpacity() == -1)
            flag = true;
        return flag;
    }

    private boolean isDragViewHit(int i, int j)
    {
        boolean flag;
        View view;
        flag = true;
        view = mDragView;
        if(view != null && view.isEnabled()) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        int ai1[] = new int[2];
        getLocationOnScreen(ai1);
        int k = i + ai1[0];
        int l = j + ai1[flag];
        if(k < ai[0] || k >= ai[0] + view.getWidth() || l < ai[flag] || l >= ai[flag] + view.getHeight())
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void onPanelDragged(int i)
    {
        mSlideOffset = (float)(i - getTopBound()) / (float)mSlideRange;
        dispatchOnPanelSlide(mSlideableView);
    }

    protected boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        ViewGroup viewgroup;
        int l;
        int i1;
        int j1;
        if(!(view instanceof ViewGroup))
            break MISSING_BLOCK_LABEL_146;
        viewgroup = (ViewGroup)view;
        l = view.getScrollX();
        i1 = view.getScrollY();
        j1 = -1 + viewgroup.getChildCount();
_L3:
        View view1;
        if(j1 < 0)
            break MISSING_BLOCK_LABEL_146;
        view1 = viewgroup.getChildAt(j1);
        if(j + l < view1.getLeft() || j + l >= view1.getRight() || k + i1 < view1.getTop() || k + i1 >= view1.getBottom() || !canScroll(view1, true, i, (j + l) - view1.getLeft(), (k + i1) - view1.getTop())) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        j1--;
          goto _L3
        if(flag && ViewCompat.canScrollHorizontally(view, -i))
            flag1 = true;
        else
            flag1 = false;
          goto _L4
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag;
        if((layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean collapsePane()
    {
        return collapsePane(mSlideableView, 0);
    }

    public void computeScroll()
    {
        if(mDragHelper.getCapturedView() != null && mDragHelper.continueSettling(true))
            if(!mCanSlide)
                mDragHelper.abort();
            else
                ViewCompat.postInvalidateOnAnimation(this);
    }

    protected void dispatchOnPanelCollapsed(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelCollapsed(view);
        sendAccessibilityEvent(32);
    }

    protected void dispatchOnPanelExpanded(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelExpanded(view);
        sendAccessibilityEvent(32);
    }

    protected void dispatchOnPanelSlide(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelSlide(view, mSlideOffset);
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mSlideableView != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = mSlideableView.getRight();
        int j = mSlideableView.getTop() - mShadowHeight;
        int k = mSlideableView.getTop();
        int l = mSlideableView.getLeft();
        if(mShadowDrawable != null)
        {
            mShadowDrawable.setBounds(l, j, i, k);
            mShadowDrawable.draw(canvas);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int i = canvas.save(2);
        boolean flag = false;
        if(mCanSlide && !layoutparams.slideable && mSlideableView != null)
        {
            if(clipObscured)
            {
                canvas.getClipBounds(mTmpRect);
                mTmpRect.bottom = Math.min(mTmpRect.bottom, mSlideableView.getTop());
                int k = ((android.view.ViewGroup.MarginLayoutParams)mSlideableView.getLayoutParams()).topMargin;
                if(k < 0)
                {
                    Rect rect = mTmpRect;
                    rect.bottom = rect.bottom - k;
                }
                canvas.clipRect(mTmpRect);
            }
            if(mSlideOffset < 1.0F)
                flag = true;
        }
        if(mPanelSlideListener != null)
            mPanelSlideListener.onDrawChild(canvas, view, l);
        boolean flag1 = super.drawChild(canvas, view, l);
        canvas.restoreToCount(i);
        if(flag)
        {
            int j = (int)((float)((0xff000000 & mCoveredFadeColor) >>> 24) * (1.0F - mSlideOffset)) << 24 | 0xffffff & mCoveredFadeColor;
            mCoveredFadePaint.setColor(j);
            canvas.drawRect(mTmpRect, mCoveredFadePaint);
        }
        return flag1;
    }

    public boolean expandPane()
    {
        if(!isPaneVisible())
            showPane();
        return expandPane(mSlideableView, 0);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        LayoutParams layoutparams1;
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            layoutparams1 = new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        else
            layoutparams1 = new LayoutParams(layoutparams);
        return layoutparams1;
    }

    public int getCoveredFadeColor()
    {
        return mCoveredFadeColor;
    }

    public int getPanelHeight()
    {
        return mPanelHeight;
    }

    public void hidePane()
    {
        if(mSlideableView != null)
        {
            mSlideableView.setVisibility(8);
            requestLayout();
        }
    }

    public boolean isExpanded()
    {
        boolean flag;
        if(mCanSlide && mSlideOffset == 0.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPaneVisible()
    {
        int i = 1;
        int j = 0;
        if(getChildCount() >= 2)
        {
            if(getChildAt(i).getVisibility() != 0)
                i = 0;
            j = i;
        }
        return j;
    }

    public boolean isPartiallyExpanded()
    {
        boolean flag;
        if(mDragHelper.getViewDragState() != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSlideable()
    {
        return mCanSlide;
    }

    public boolean isSliding()
    {
        boolean flag;
        if(mCanSlide && mSlideOffset > 0.0F && mSlideOffset < 1.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
        if(mPreservedExpandedState)
        {
            expandPane();
        } else
        {
            setAllChildrenVisible();
            collapsePane();
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mFirstLayout = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        flag = false;
        i = MotionEventCompat.getActionMasked(motionevent);
        if(mSlideOffset == 0.0F || mSlideOffset == 1.0F) goto _L2; else goto _L1
_L1:
        flag = true;
_L8:
        return flag;
_L2:
        float f;
        float f1;
        boolean flag1;
        if(!mCanSlide || mIsUnableToDrag && i != 0)
        {
            mDragHelper.cancel();
            flag = super.onInterceptTouchEvent(motionevent);
            continue; /* Loop/switch isn't completed */
        }
        if(i == 3 || i == 1)
        {
            mDragHelper.cancel();
            if(mDragView != null)
                mDragView.setPressed(false);
            continue; /* Loop/switch isn't completed */
        }
        f = motionevent.getX();
        f1 = motionevent.getY();
        flag1 = false;
        i;
        JVM INSTR tableswitch 0 2: default 140
    //                   0 176
    //                   1 140
    //                   2 253;
           goto _L3 _L4 _L3 _L5
_L3:
        float f2;
        float f3;
        boolean flag2;
        if(mDragViewHit && mDragHelper.shouldInterceptTouchEvent(motionevent))
            flag2 = true;
        else
            flag2 = false;
        if(flag2 || flag1)
            flag = true;
        continue; /* Loop/switch isn't completed */
_L4:
        mIsUnableToDrag = false;
        mInitialMotionX = f;
        mInitialMotionY = f1;
        mDragViewHit = isDragViewHit((int)f, (int)f1);
        if(mDragViewHit)
        {
            flag1 = true;
            if(mDragView != null)
                mDragView.setPressed(true);
        } else
        if(mDragView != null)
            mDragView.setPressed(false);
          goto _L3
_L5:
        f2 = Math.abs(f - mInitialMotionX);
        f3 = Math.abs(f1 - mInitialMotionY);
        if(f3 <= (float)mDragHelper.getTouchSlop() || f2 <= f3) goto _L3; else goto _L6
_L6:
        mDragHelper.cancel();
        mIsUnableToDrag = true;
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = getPaddingLeft();
        int j1 = getPaddingTop();
        int k1 = getChildCount();
        int l1 = j1;
        int i2 = l1;
        if(mFirstLayout)
        {
            int j2;
            float f;
            if(mCanSlide && mPreservedExpandedState)
                f = 0.0F;
            else
                f = 1.0F;
            mSlideOffset = f;
        }
        j2 = 0;
        while(j2 < k1) 
        {
            View view = getChildAt(j2);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int k2 = view.getMeasuredHeight();
                int l2;
                int i3;
                if(layoutparams.slideable)
                {
                    mSlideRange = k2 - mPanelHeight;
                    l1 += (int)((float)mSlideRange * mSlideOffset);
                } else
                {
                    l1 = i2;
                }
                l2 = l1 + layoutparams.topMargin;
                i3 = l2 + k2;
                view.layout(i1, l2, i1 + view.getMeasuredWidth(), i3);
                i2 += view.getHeight();
            }
            j2++;
        }
        if(mFirstLayout)
            updateObscuredViewVisibility();
        mFirstLayout = false;
        if(mPanelSlideListener != null)
            mPanelSlideListener.onLayoutChanged(this);
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        if(i != 0 && j != 0)
        {
            if(k != 0x40000000)
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            if(i1 != 0x40000000)
                throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }
        int k1 = j1 - getPaddingTop() - getPaddingBottom();
        int _tmp = mPanelHeight;
        int l1 = getChildCount();
        int i2;
        if(l1 > 2)
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        else
        if(getChildAt(1).getVisibility() != 8);
        mSlideableView = null;
        mCanSlide = false;
        i2 = 0;
        do
        {
            if(i2 < l1)
            {
                View view = getChildAt(i2);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int j2 = k1 - layoutparams.topMargin - layoutparams.bottomMargin;
                if(view.getVisibility() == 8)
                {
                    layoutparams.dimWhenOffset = false;
                } else
                {
                    if(i2 == 1)
                    {
                        layoutparams.slideable = true;
                        layoutparams.dimWhenOffset = true;
                        mSlideableView = view;
                        mCanSlide = true;
                    }
                    int k2;
                    int l2;
                    if(layoutparams.width == -2)
                        k2 = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
                    else
                    if(layoutparams.width == -1)
                        k2 = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000);
                    else
                        k2 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.width, 0x40000000);
                    if(layoutparams.height == -2)
                        l2 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x80000000);
                    else
                    if(layoutparams.height == -1)
                        l2 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x40000000);
                    else
                        l2 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
                    view.measure(k2, l2);
                }
            } else
            {
                setMeasuredDimension(l, j1);
                return;
            }
            i2++;
        } while(true);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(savedstate.isExpanded)
            expandPane();
        else
            collapsePane();
        mPreservedExpandedState = savedstate.isExpanded;
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        boolean flag;
        if(isSlideable())
            flag = isExpanded();
        else
            flag = mPreservedExpandedState;
        savedstate.isExpanded = flag;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(j != l)
            mFirstLayout = true;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(!mCanSlide)
            flag = super.onTouchEvent(motionevent);
        else
        if(mDragHelper.getViewDragState() == 2)
        {
            flag = true;
        } else
        {
            SLog.d("SlidingPaneLayout", (new StringBuilder()).append("Processing Touch Event! ").append(motionevent).toString());
            mDragHelper.processTouchEvent(motionevent);
            int i = motionevent.getAction();
            flag = true;
            switch(i & 0xff)
            {
            default:
                break;

            case 0: // '\0'
                float f4 = motionevent.getX();
                float f5 = motionevent.getY();
                mInitialMotionX = f4;
                mInitialMotionY = f5;
                break;

            case 1: // '\001'
                float f = motionevent.getX();
                float f1 = motionevent.getY();
                float f2 = f - mInitialMotionX;
                float f3 = f1 - mInitialMotionY;
                int j = mDragHelper.getTouchSlop();
                if(mDragView != null)
                    mDragView.setPressed(false);
                if(f2 * f2 + f3 * f3 < (float)(j * j) && isDragViewHit((int)f, (int)f1) && shouldAllowTap)
                {
                    View view;
                    if(mDragView != null)
                        view = mDragView;
                    else
                        view = mSlideableView;
                    view.playSoundEffect(0);
                    if(!isExpanded())
                        expandPane(mSlideableView, 0);
                    else
                        collapsePane();
                }
                break;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void requestChildFocus(View view, View view1)
    {
        super.requestChildFocus(view, view1);
        if(!isInTouchMode() && !mCanSlide)
        {
            boolean flag;
            if(view == mSlideableView)
                flag = true;
            else
                flag = false;
            mPreservedExpandedState = flag;
        }
    }

    void setAllChildrenVisible()
    {
        int i = 0;
        for(int j = getChildCount(); i < j; i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() == 4)
                view.setVisibility(0);
        }

    }

    public void setClipObscured(boolean flag)
    {
        clipObscured = flag;
    }

    public void setCoveredFadeColor(int i)
    {
        mCoveredFadeColor = i;
        invalidate();
    }

    public void setDragView(View view)
    {
        mDragView = view;
    }

    public void setPanelHeight(int i)
    {
        if(mPanelHeight != i)
        {
            mPanelHeight = i;
            mFirstLayout = true;
            requestLayout();
            invalidate();
        }
    }

    public void setPanelSlideListener(PanelSlideListener panelslidelistener)
    {
        mPanelSlideListener = panelslidelistener;
    }

    public void setShadowDrawable(Drawable drawable)
    {
        mShadowDrawable = drawable;
    }

    public void setShouldAllowTap(boolean flag)
    {
        shouldAllowTap = flag;
    }

    public void setStartExpanded(boolean flag)
    {
        mPreservedExpandedState = flag;
    }

    public void setVisibility(int i)
    {
        int j = getVisibility();
        if(j == 0 && i == 8 || j == 8 && i == 0)
            mFirstLayout = true;
        super.setVisibility(i);
    }

    public void showPane()
    {
        if(getChildCount() >= 2)
        {
            getChildAt(1).setVisibility(0);
            requestLayout();
        }
    }

    public boolean smoothSlideTo(float f, int i)
    {
        boolean flag = false;
        if(mCanSlide) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        int j = (int)((float)getTopBound() + f * (float)mSlideRange);
        if(mDragHelper.smoothSlideViewTo(mSlideableView, mSlideableView.getLeft(), j))
        {
            setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            flag = true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    void updateObscuredViewVisibility()
    {
        if(getChildCount() != 0)
        {
            int i = getPaddingLeft();
            int j = getWidth() - getPaddingRight();
            int k = getTopBound();
            int l = getHeight() - getPaddingBottom();
            int i1;
            int j1;
            int k1;
            int l1;
            View view;
            int i2;
            int j2;
            int k2;
            int l2;
            if(mSlideableView != null)
            {
                l1 = mSlideableView.getLeft();
                k1 = mSlideableView.getRight();
                j1 = mSlideableView.getTop();
                i1 = mSlideableView.getBottom();
            } else
            {
                i1 = 0;
                j1 = 0;
                k1 = 0;
                l1 = 0;
            }
            view = getChildAt(0);
            i2 = Math.max(i, view.getLeft());
            j2 = Math.max(k, view.getTop());
            k2 = Math.min(j, view.getRight());
            l2 = Math.min(l, view.getBottom());
            if(i2 < l1 || j2 < j1 || k2 > k1 || l2 > i1);
        }
    }

    private static final int DEFAULT_FADE_COLOR = 0x99000000;
    private static final int DEFAULT_PANEL_HEIGHT = 68;
    private static final int DEFAULT_SHADOW_HEIGHT = 4;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean clipObscured;
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final Paint mCoveredFadePaint;
    private final ViewDragHelper mDragHelper;
    private View mDragView;
    private boolean mDragViewHit;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private int mPanelHeight;
    private PanelSlideListener mPanelSlideListener;
    private boolean mPreservedExpandedState;
    private Drawable mShadowDrawable;
    private final int mShadowHeight;
    protected float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private final Rect mTmpRect;
    private boolean shouldAllowTap;





/*
    static boolean access$402(SlidingUpPanelLayout slidinguppanellayout, boolean flag)
    {
        slidinguppanellayout.mPreservedExpandedState = flag;
        return flag;
    }

*/



}
