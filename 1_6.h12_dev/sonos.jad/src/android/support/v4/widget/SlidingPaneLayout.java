// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.*;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.widget:
//            ViewDragHelper

public class SlidingPaneLayout extends ViewGroup
{
    private class DisableLayerRunnable
        implements Runnable
    {

        public void run()
        {
            if(mChildView.getParent() == SlidingPaneLayout.this)
            {
                ViewCompat.setLayerType(mChildView, 0, null);
                invalidateChildRegion(mChildView);
            }
            mPostedRunnables.remove(this);
        }

        final View mChildView;
        final SlidingPaneLayout this$0;

        DisableLayerRunnable(View view)
        {
            this$0 = SlidingPaneLayout.this;
            super();
            mChildView = view;
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, AccessibilityNodeInfoCompat accessibilitynodeinfocompat1)
        {
            Rect rect = mTmpRect;
            accessibilitynodeinfocompat1.getBoundsInParent(rect);
            accessibilitynodeinfocompat.setBoundsInParent(rect);
            accessibilitynodeinfocompat1.getBoundsInScreen(rect);
            accessibilitynodeinfocompat.setBoundsInScreen(rect);
            accessibilitynodeinfocompat.setVisibleToUser(accessibilitynodeinfocompat1.isVisibleToUser());
            accessibilitynodeinfocompat.setPackageName(accessibilitynodeinfocompat1.getPackageName());
            accessibilitynodeinfocompat.setClassName(accessibilitynodeinfocompat1.getClassName());
            accessibilitynodeinfocompat.setContentDescription(accessibilitynodeinfocompat1.getContentDescription());
            accessibilitynodeinfocompat.setEnabled(accessibilitynodeinfocompat1.isEnabled());
            accessibilitynodeinfocompat.setClickable(accessibilitynodeinfocompat1.isClickable());
            accessibilitynodeinfocompat.setFocusable(accessibilitynodeinfocompat1.isFocusable());
            accessibilitynodeinfocompat.setFocused(accessibilitynodeinfocompat1.isFocused());
            accessibilitynodeinfocompat.setAccessibilityFocused(accessibilitynodeinfocompat1.isAccessibilityFocused());
            accessibilitynodeinfocompat.setSelected(accessibilitynodeinfocompat1.isSelected());
            accessibilitynodeinfocompat.setLongClickable(accessibilitynodeinfocompat1.isLongClickable());
            accessibilitynodeinfocompat.addAction(accessibilitynodeinfocompat1.getActions());
            accessibilitynodeinfocompat.setMovementGranularities(accessibilitynodeinfocompat1.getMovementGranularities());
        }

        public boolean filter(View view)
        {
            return isDimmed(view);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/widget/SlidingPaneLayout.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = AccessibilityNodeInfoCompat.obtain(accessibilitynodeinfocompat);
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
            copyNodeInfoNoChildren(accessibilitynodeinfocompat, accessibilitynodeinfocompat1);
            accessibilitynodeinfocompat1.recycle();
            accessibilitynodeinfocompat.setClassName(android/support/v4/widget/SlidingPaneLayout.getName());
            accessibilitynodeinfocompat.setSource(view);
            android.view.ViewParent viewparent = ViewCompat.getParentForAccessibility(view);
            if(viewparent instanceof View)
                accessibilitynodeinfocompat.setParent((View)viewparent);
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view1 = getChildAt(j);
                if(!filter(view1) && view1.getVisibility() == 0)
                {
                    ViewCompat.setImportantForAccessibility(view1, 1);
                    accessibilitynodeinfocompat.addChild(view1);
                }
            }

        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            boolean flag;
            if(!filter(view))
                flag = super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            else
                flag = false;
            return flag;
        }

        private final Rect mTmpRect = new Rect();
        final SlidingPaneLayout this$0;

        AccessibilityDelegate()
        {
            this$0 = SlidingPaneLayout.this;
            super();
        }
    }

    static class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase
    {

        public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
        {
            ViewCompat.setLayerPaint(view, ((LayoutParams)view.getLayoutParams()).dimPaint);
        }

        SlidingPanelLayoutImplJBMR1()
        {
        }
    }

    static class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase
    {

        public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
        {
            if(mGetDisplayList != null && mRecreateDisplayList != null)
            {
                try
                {
                    mRecreateDisplayList.setBoolean(view, true);
                    mGetDisplayList.invoke(view, (Object[])null);
                }
                catch(Exception exception)
                {
                    Log.e("SlidingPaneLayout", "Error refreshing display list state", exception);
                }
                super.invalidateChildRegion(slidingpanelayout, view);
            } else
            {
                view.invalidate();
            }
        }

        private Method mGetDisplayList;
        private Field mRecreateDisplayList;

        SlidingPanelLayoutImplJB()
        {
            try
            {
                mGetDisplayList = android/view/View.getDeclaredMethod("getDisplayList", (Class[])null);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", nosuchmethodexception);
            }
            mRecreateDisplayList = android/view/View.getDeclaredField("mRecreateDisplayList");
            mRecreateDisplayList.setAccessible(true);
_L1:
            return;
            NoSuchFieldException nosuchfieldexception;
            nosuchfieldexception;
            Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", nosuchfieldexception);
              goto _L1
        }
    }

    static class SlidingPanelLayoutImplBase
        implements SlidingPanelLayoutImpl
    {

        public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
        {
            ViewCompat.postInvalidateOnAnimation(slidingpanelayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }

        SlidingPanelLayoutImplBase()
        {
        }
    }

    static interface SlidingPanelLayoutImpl
    {

        public abstract void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            int j;
            if(isOpen)
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
        boolean isOpen;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            isOpen = flag;
        }


        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        private static final int ATTRS[];
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;

        static 
        {
            int ai[] = new int[1];
            ai[0] = 0x1010181;
            ATTRS = ai;
        }

        public LayoutParams()
        {
            super(-1, -1);
            weight = 0.0F;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            weight = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            weight = 0.0F;
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, ATTRS);
            weight = typedarray.getFloat(0, 0.0F);
            typedarray.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            weight = 0.0F;
            weight = layoutparams.weight;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            weight = 0.0F;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            weight = 0.0F;
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback
    {

        public int clampViewPositionHorizontal(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
            int i1;
            if(isLayoutRtlSupport())
            {
                int j1 = getWidth() - (getPaddingRight() + layoutparams.rightMargin + mSlideableView.getWidth());
                int k1 = j1 - mSlideRange;
                i1 = Math.max(Math.min(i, j1), k1);
            } else
            {
                int k = getPaddingLeft() + layoutparams.leftMargin;
                int l = k + mSlideRange;
                i1 = Math.min(Math.max(i, k), l);
            }
            return i1;
        }

        public int clampViewPositionVertical(View view, int i, int j)
        {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view)
        {
            return mSlideRange;
        }

        public void onEdgeDragStarted(int i, int j)
        {
            mDragHelper.captureChildView(mSlideableView, j);
        }

        public void onViewCaptured(View view, int i)
        {
            setAllChildrenVisible();
        }

        public void onViewDragStateChanged(int i)
        {
            if(mDragHelper.getViewDragState() == 0)
                if(mSlideOffset == 0.0F)
                {
                    updateObscuredViewsVisibility(mSlideableView);
                    dispatchOnPanelClosed(mSlideableView);
                    mPreservedOpenState = false;
                } else
                {
                    dispatchOnPanelOpened(mSlideableView);
                    mPreservedOpenState = true;
                }
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            onPanelDragged(i);
            invalidate();
        }

        public void onViewReleased(View view, float f, float f1)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(!isLayoutRtlSupport()) goto _L2; else goto _L1
_L1:
            int i;
            int j = getPaddingRight() + layoutparams.rightMargin;
            if(f < 0.0F || f == 0.0F && mSlideOffset > 0.5F)
                j += mSlideRange;
            int k = mSlideableView.getWidth();
            i = getWidth() - j - k;
_L4:
            mDragHelper.settleCapturedViewAt(i, view.getTop());
            invalidate();
            return;
_L2:
            i = getPaddingLeft() + layoutparams.leftMargin;
            if(f > 0.0F || f == 0.0F && mSlideOffset > 0.5F)
                i += mSlideRange;
            if(true) goto _L4; else goto _L3
_L3:
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

        final SlidingPaneLayout this$0;

        private DragHelperCallback()
        {
            this$0 = SlidingPaneLayout.this;
            super();
        }

    }

    public static class SimplePanelSlideListener
        implements PanelSlideListener
    {

        public void onPanelClosed(View view)
        {
        }

        public void onPanelOpened(View view)
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

        public abstract void onPanelClosed(View view);

        public abstract void onPanelOpened(View view);

        public abstract void onPanelSlide(View view, float f);
    }


    public SlidingPaneLayout(Context context)
    {
        this(context, null);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mSliderFadeColor = 0xcccccccc;
        mFirstLayout = true;
        mTmpRect = new Rect();
        mPostedRunnables = new ArrayList();
        float f = context.getResources().getDisplayMetrics().density;
        mOverhangSize = (int)(0.5F + 32F * f);
        ViewConfiguration.get(context);
        setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewCompat.setImportantForAccessibility(this, 1);
        mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback());
        mDragHelper.setMinVelocity(400F * f);
    }

    private boolean closePane(View view, int i)
    {
        boolean flag = false;
        if(mFirstLayout || smoothSlideTo(0.0F, i))
        {
            mPreservedOpenState = false;
            flag = true;
        }
        return flag;
    }

    private void dimChildView(View view, float f, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(f <= 0.0F || i == 0) goto _L2; else goto _L1
_L1:
        int j = (int)(f * (float)((0xff000000 & i) >>> 24)) << 24 | 0xffffff & i;
        if(layoutparams.dimPaint == null)
            layoutparams.dimPaint = new Paint();
        layoutparams.dimPaint.setColorFilter(new PorterDuffColorFilter(j, android.graphics.PorterDuff.Mode.SRC_OVER));
        if(ViewCompat.getLayerType(view) != 2)
            ViewCompat.setLayerType(view, 2, layoutparams.dimPaint);
        invalidateChildRegion(view);
_L4:
        return;
_L2:
        if(ViewCompat.getLayerType(view) != 0)
        {
            if(layoutparams.dimPaint != null)
                layoutparams.dimPaint.setColorFilter(null);
            DisableLayerRunnable disablelayerrunnable = new DisableLayerRunnable(view);
            mPostedRunnables.add(disablelayerrunnable);
            ViewCompat.postOnAnimation(this, disablelayerrunnable);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void invalidateChildRegion(View view)
    {
        IMPL.invalidateChildRegion(this, view);
    }

    private boolean isLayoutRtlSupport()
    {
        boolean flag = true;
        if(ViewCompat.getLayoutDirection(this) != flag)
            flag = false;
        return flag;
    }

    private void onPanelDragged(int i)
    {
        if(mSlideableView == null)
        {
            mSlideOffset = 0.0F;
        } else
        {
            boolean flag = isLayoutRtlSupport();
            LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
            int j = mSlideableView.getWidth();
            int k;
            int l;
            int i1;
            if(flag)
                k = getWidth() - i - j;
            else
                k = i;
            if(flag)
                l = getPaddingRight();
            else
                l = getPaddingLeft();
            if(flag)
                i1 = layoutparams.rightMargin;
            else
                i1 = layoutparams.leftMargin;
            mSlideOffset = (float)(k - (l + i1)) / (float)mSlideRange;
            if(mParallaxBy != 0)
                parallaxOtherViews(mSlideOffset);
            if(layoutparams.dimWhenOffset)
                dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
            dispatchOnPanelSlide(mSlideableView);
        }
    }

    private boolean openPane(View view, int i)
    {
        boolean flag = true;
        if(mFirstLayout || smoothSlideTo(1.0F, i))
            mPreservedOpenState = flag;
        else
            flag = false;
        return flag;
    }

    private void parallaxOtherViews(float f)
    {
        boolean flag;
        boolean flag1;
        flag = isLayoutRtlSupport();
        LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
        if(layoutparams.dimWhenOffset)
        {
label0:
            {
                int i;
                int j;
                int i1;
                if(flag)
                    i1 = layoutparams.rightMargin;
                else
                    i1 = layoutparams.leftMargin;
                if(i1 <= 0)
                {
                    flag1 = true;
                    break label0;
                }
            }
        }
        flag1 = false;
        continue;
        while(true) 
        {
            i = getChildCount();
            j = 0;
            while(j < i) 
            {
                View view = getChildAt(j);
                if(view != mSlideableView)
                {
                    int k = (int)((1.0F - mParallaxOffset) * (float)mParallaxBy);
                    mParallaxOffset = f;
                    int l = k - (int)((1.0F - f) * (float)mParallaxBy);
                    if(flag)
                        l = -l;
                    view.offsetLeftAndRight(l);
                    if(flag1)
                    {
                        float f1;
                        if(flag)
                            f1 = mParallaxOffset - 1.0F;
                        else
                            f1 = 1.0F - mParallaxOffset;
                        dimChildView(view, f1, mCoveredFadeColor);
                    }
                }
                j++;
            }
            return;
        }
    }

    private static boolean viewIsOpaque(View view)
    {
        boolean flag = true;
        if(!ViewCompat.isOpaque(view)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(android.os.Build.VERSION.SDK_INT >= 18)
        {
            flag = false;
        } else
        {
            Drawable drawable = view.getBackground();
            if(drawable != null)
            {
                if(drawable.getOpacity() != -1)
                    flag = false;
            } else
            {
                flag = false;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        if(!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        ViewGroup viewgroup;
        int l;
        int i1;
        int j1;
        viewgroup = (ViewGroup)view;
        l = view.getScrollX();
        i1 = view.getScrollY();
        j1 = -1 + viewgroup.getChildCount();
_L4:
        if(j1 < 0) goto _L2; else goto _L3
_L3:
        boolean flag1;
        View view1 = viewgroup.getChildAt(j1);
        if(j + l < view1.getLeft() || j + l >= view1.getRight() || k + i1 < view1.getTop() || k + i1 >= view1.getBottom())
            continue; /* Loop/switch isn't completed */
        int k1 = (j + l) - view1.getLeft();
        int l1 = (k + i1) - view1.getTop();
        if(!canScroll(view1, true, i, k1, l1))
            continue; /* Loop/switch isn't completed */
        flag1 = true;
_L5:
        return flag1;
        j1--;
          goto _L4
_L2:
label0:
        {
            if(!flag)
                break label0;
            if(!isLayoutRtlSupport())
                i = -i;
            if(!ViewCompat.canScrollHorizontally(view, i))
                break label0;
            flag1 = true;
        }
          goto _L5
        flag1 = false;
          goto _L5
    }

    public boolean canSlide()
    {
        return mCanSlide;
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

    public boolean closePane()
    {
        return closePane(mSlideableView, 0);
    }

    public void computeScroll()
    {
        if(mDragHelper.continueSettling(true))
            if(!mCanSlide)
                mDragHelper.abort();
            else
                ViewCompat.postInvalidateOnAnimation(this);
    }

    void dispatchOnPanelClosed(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelClosed(view);
        sendAccessibilityEvent(32);
    }

    void dispatchOnPanelOpened(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelOpened(view);
        sendAccessibilityEvent(32);
    }

    void dispatchOnPanelSlide(View view)
    {
        if(mPanelSlideListener != null)
            mPanelSlideListener.onPanelSlide(view, mSlideOffset);
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        Drawable drawable;
        View view;
        if(isLayoutRtlSupport())
            drawable = mShadowDrawableRight;
        else
            drawable = mShadowDrawableLeft;
        if(getChildCount() > 1)
            view = getChildAt(1);
        else
            view = null;
        if(view != null && drawable != null)
        {
            int i = view.getTop();
            int j = view.getBottom();
            int k = drawable.getIntrinsicWidth();
            int l;
            int i1;
            if(isLayoutRtlSupport())
            {
                i1 = view.getRight();
                l = i1 + k;
            } else
            {
                l = view.getLeft();
                i1 = l - k;
            }
            drawable.setBounds(i1, i, l, j);
            drawable.draw(canvas);
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int i = canvas.save(2);
        boolean flag;
        if(mCanSlide && !layoutparams.slideable && mSlideableView != null)
        {
            canvas.getClipBounds(mTmpRect);
            if(isLayoutRtlSupport())
                mTmpRect.left = Math.max(mTmpRect.left, mSlideableView.getRight());
            else
                mTmpRect.right = Math.min(mTmpRect.right, mSlideableView.getLeft());
            canvas.clipRect(mTmpRect);
        }
        if(android.os.Build.VERSION.SDK_INT >= 11)
            flag = super.drawChild(canvas, view, l);
        else
        if(layoutparams.dimWhenOffset && mSlideOffset > 0.0F)
        {
            if(!view.isDrawingCacheEnabled())
                view.setDrawingCacheEnabled(true);
            android.graphics.Bitmap bitmap = view.getDrawingCache();
            if(bitmap != null)
            {
                canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), layoutparams.dimPaint);
                flag = false;
            } else
            {
                Log.e("SlidingPaneLayout", (new StringBuilder()).append("drawChild: child view ").append(view).append(" returned null drawing cache").toString());
                flag = super.drawChild(canvas, view, l);
            }
        } else
        {
            if(view.isDrawingCacheEnabled())
                view.setDrawingCacheEnabled(false);
            flag = super.drawChild(canvas, view, l);
        }
        canvas.restoreToCount(i);
        return flag;
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

    public int getParallaxDistance()
    {
        return mParallaxBy;
    }

    public int getSliderFadeColor()
    {
        return mSliderFadeColor;
    }

    boolean isDimmed(View view)
    {
        boolean flag = false;
        if(view != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(mCanSlide && layoutparams.dimWhenOffset && mSlideOffset > 0.0F)
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isOpen()
    {
        boolean flag;
        if(!mCanSlide || mSlideOffset == 1.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSlideable()
    {
        return mCanSlide;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mFirstLayout = true;
        int i = 0;
        for(int j = mPostedRunnables.size(); i < j; i++)
            ((DisableLayerRunnable)mPostedRunnables.get(i)).run();

        mPostedRunnables.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        i = MotionEventCompat.getActionMasked(motionevent);
        if(!mCanSlide && i == 0 && getChildCount() > 1)
        {
            View view = getChildAt(1);
            if(view != null)
            {
                boolean flag2;
                if(!mDragHelper.isViewUnder(view, (int)motionevent.getX(), (int)motionevent.getY()))
                    flag2 = true;
                else
                    flag2 = false;
                mPreservedOpenState = flag2;
            }
        }
        if(mCanSlide && (!mIsUnableToDrag || i == 0)) goto _L2; else goto _L1
_L1:
        mDragHelper.cancel();
        flag = super.onInterceptTouchEvent(motionevent);
_L8:
        return flag;
_L2:
        boolean flag1;
        if(i == 3 || i == 1)
        {
            mDragHelper.cancel();
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        i;
        JVM INSTR tableswitch 0 2: default 160
    //                   0 181
    //                   1 160
    //                   2 247;
           goto _L3 _L4 _L3 _L5
_L3:
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        if(mDragHelper.shouldInterceptTouchEvent(motionevent) || flag1)
            flag = true;
        else
            flag = false;
        continue; /* Loop/switch isn't completed */
_L4:
        mIsUnableToDrag = false;
        f4 = motionevent.getX();
        f5 = motionevent.getY();
        mInitialMotionX = f4;
        mInitialMotionY = f5;
        if(mDragHelper.isViewUnder(mSlideableView, (int)f4, (int)f5) && isDimmed(mSlideableView))
            flag1 = true;
          goto _L3
_L5:
        f = motionevent.getX();
        f1 = motionevent.getY();
        f2 = Math.abs(f - mInitialMotionX);
        f3 = Math.abs(f1 - mInitialMotionY);
        if(f2 <= (float)mDragHelper.getTouchSlop() || f3 <= f2) goto _L3; else goto _L6
_L6:
        mDragHelper.cancel();
        mIsUnableToDrag = true;
        flag = false;
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        boolean flag1 = isLayoutRtlSupport();
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        if(flag1)
            mDragHelper.setEdgeTrackingEnabled(2);
        else
            mDragHelper.setEdgeTrackingEnabled(1);
        i1 = k - i;
        if(flag1)
            j1 = getPaddingRight();
        else
            j1 = getPaddingLeft();
        if(flag1)
            k1 = getPaddingLeft();
        else
            k1 = getPaddingRight();
        l1 = getPaddingTop();
        i2 = getChildCount();
        j2 = j1;
        k2 = j2;
        if(mFirstLayout)
        {
            int l2;
            float f;
            if(mCanSlide && mPreservedOpenState)
                f = 1.0F;
            else
                f = 0.0F;
            mSlideOffset = f;
        }
        l2 = 0;
        while(l2 < i2) 
        {
            View view = getChildAt(l2);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int j3 = view.getMeasuredWidth();
                int k3 = 0;
                int l3;
                int i4;
                if(layoutparams.slideable)
                {
                    int j4 = layoutparams.leftMargin + layoutparams.rightMargin;
                    int k4 = i1 - k1 - mOverhangSize;
                    int l4 = Math.min(k2, k4) - j2 - j4;
                    mSlideRange = l4;
                    int i5;
                    boolean flag2;
                    int j5;
                    if(flag1)
                        i5 = layoutparams.rightMargin;
                    else
                        i5 = layoutparams.leftMargin;
                    if(l4 + (j2 + i5) + j3 / 2 > i1 - k1)
                        flag2 = true;
                    else
                        flag2 = false;
                    layoutparams.dimWhenOffset = flag2;
                    j5 = (int)((float)l4 * mSlideOffset);
                    j2 += j5 + i5;
                    mSlideOffset = (float)j5 / (float)mSlideRange;
                } else
                if(mCanSlide && mParallaxBy != 0)
                {
                    k3 = (int)((1.0F - mSlideOffset) * (float)mParallaxBy);
                    j2 = k2;
                } else
                {
                    j2 = k2;
                }
                if(flag1)
                {
                    i4 = k3 + (i1 - j2);
                    l3 = i4 - j3;
                } else
                {
                    l3 = j2 - k3;
                    i4 = l3 + j3;
                }
                view.layout(l3, l1, i4, l1 + view.getMeasuredHeight());
                k2 += view.getWidth();
            }
            l2++;
        }
        if(mFirstLayout)
        {
            if(mCanSlide)
            {
                if(mParallaxBy != 0)
                    parallaxOtherViews(mSlideOffset);
                if(((LayoutParams)mSlideableView.getLayoutParams()).dimWhenOffset)
                    dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
            } else
            {
                int i3 = 0;
                while(i3 < i2) 
                {
                    dimChildView(getChildAt(i3), 0.0F, mSliderFadeColor);
                    i3++;
                }
            }
            updateObscuredViewsVisibility(mSlideableView);
        }
        mFirstLayout = false;
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getSize(i);
        i1 = android.view.View.MeasureSpec.getMode(j);
        j1 = android.view.View.MeasureSpec.getSize(j);
        if(k == 0x40000000) goto _L2; else goto _L1
_L1:
        if(!isInEditMode()) goto _L4; else goto _L3
_L13:
        int k1;
        int l1;
        k1 = 0;
        l1 = -1;
        i1;
        JVM INSTR lookupswitch 2: default 80
    //                   -2147483648: 265
    //                   1073741824: 244;
           goto _L5 _L6 _L7
_L5:
        float f;
        boolean flag;
        int i2;
        int j2;
        int k2;
        int l2;
        f = 0.0F;
        flag = false;
        i2 = l - getPaddingLeft() - getPaddingRight();
        j2 = i2;
        k2 = getChildCount();
        if(k2 > 2)
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        mSlideableView = null;
        l2 = 0;
_L12:
        if(l2 >= k2) goto _L9; else goto _L8
_L8:
        View view1;
        LayoutParams layoutparams1;
        view1 = getChildAt(l2);
        layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if(view1.getVisibility() != 8) goto _L11; else goto _L10
_L10:
        layoutparams1.dimWhenOffset = false;
_L16:
        l2++;
          goto _L12
_L3:
        if(k != 0x80000000 && k == 0)
            l = 300;
          goto _L13
_L4:
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
_L2:
        if(i1 == 0)
            if(isInEditMode())
            {
                if(i1 == 0)
                {
                    i1 = 0x80000000;
                    j1 = 300;
                }
            } else
            {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
        continue; /* Loop/switch isn't completed */
_L7:
        l1 = j1 - getPaddingTop() - getPaddingBottom();
        k1 = l1;
          goto _L5
_L6:
        l1 = j1 - getPaddingTop() - getPaddingBottom();
          goto _L5
_L11:
        if(layoutparams1.weight <= 0.0F) goto _L15; else goto _L14
_L14:
        f += layoutparams1.weight;
        if(layoutparams1.width == 0) goto _L16; else goto _L15
_L15:
        int i5 = layoutparams1.leftMargin + layoutparams1.rightMargin;
        int j5;
        int k5;
        int l5;
        int i6;
        boolean flag2;
        if(layoutparams1.width == -2)
            j5 = android.view.View.MeasureSpec.makeMeasureSpec(i2 - i5, 0x80000000);
        else
        if(layoutparams1.width == -1)
            j5 = android.view.View.MeasureSpec.makeMeasureSpec(i2 - i5, 0x40000000);
        else
            j5 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams1.width, 0x40000000);
        if(layoutparams1.height == -2)
            k5 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x80000000);
        else
        if(layoutparams1.height == -1)
            k5 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000);
        else
            k5 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams1.height, 0x40000000);
        view1.measure(j5, k5);
        l5 = view1.getMeasuredWidth();
        i6 = view1.getMeasuredHeight();
        if(i1 == 0x80000000 && i6 > k1)
            k1 = Math.min(i6, l1);
        j2 -= l5;
        if(j2 < 0)
            flag2 = true;
        else
            flag2 = false;
        layoutparams1.slideable = flag2;
        flag |= flag2;
        if(layoutparams1.slideable)
            mSlideableView = view1;
          goto _L16
_L9:
        if(flag || f > 0.0F)
        {
            int i3 = i2 - mOverhangSize;
            int j3 = 0;
            while(j3 < k2) 
            {
                View view = getChildAt(j3);
                if(view.getVisibility() != 8)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    if(view.getVisibility() != 8)
                    {
                        boolean flag1;
                        int k3;
                        if(layoutparams.width == 0 && layoutparams.weight > 0.0F)
                            flag1 = true;
                        else
                            flag1 = false;
                        if(flag1)
                            k3 = 0;
                        else
                            k3 = view.getMeasuredWidth();
                        if(flag && view != mSlideableView)
                        {
                            if(layoutparams.width < 0 && (k3 > i3 || layoutparams.weight > 0.0F))
                            {
                                int l4;
                                if(flag1)
                                {
                                    if(layoutparams.height == -2)
                                        l4 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x80000000);
                                    else
                                    if(layoutparams.height == -1)
                                        l4 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000);
                                    else
                                        l4 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
                                } else
                                {
                                    l4 = android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000);
                                }
                                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i3, 0x40000000), l4);
                            }
                        } else
                        if(layoutparams.weight > 0.0F)
                        {
                            int l3;
                            if(layoutparams.width == 0)
                            {
                                if(layoutparams.height == -2)
                                    l3 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x80000000);
                                else
                                if(layoutparams.height == -1)
                                    l3 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000);
                                else
                                    l3 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
                            } else
                            {
                                l3 = android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000);
                            }
                            if(flag)
                            {
                                int j4 = i2 - (layoutparams.leftMargin + layoutparams.rightMargin);
                                int k4 = android.view.View.MeasureSpec.makeMeasureSpec(j4, 0x40000000);
                                if(k3 != j4)
                                    view.measure(k4, l3);
                            } else
                            {
                                int i4 = Math.max(0, j2);
                                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k3 + (int)((layoutparams.weight * (float)i4) / f), 0x40000000), l3);
                            }
                        }
                    }
                }
                j3++;
            }
        }
        setMeasuredDimension(l, k1 + getPaddingTop() + getPaddingBottom());
        mCanSlide = flag;
        if(mDragHelper.getViewDragState() != 0 && !flag)
            mDragHelper.abort();
        return;
        if(true) goto _L13; else goto _L17
_L17:
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(savedstate.isOpen)
            openPane();
        else
            closePane();
        mPreservedOpenState = savedstate.isOpen;
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        boolean flag;
        if(isSlideable())
            flag = isOpen();
        else
            flag = mPreservedOpenState;
        savedstate.isOpen = flag;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(i != k)
            mFirstLayout = true;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mCanSlide) goto _L2; else goto _L1
_L1:
        boolean flag = super.onTouchEvent(motionevent);
_L4:
        return flag;
_L2:
        mDragHelper.processTouchEvent(motionevent);
        int i = motionevent.getAction();
        flag = true;
        switch(i & 0xff)
        {
        case 0: // '\0'
            float f4 = motionevent.getX();
            float f5 = motionevent.getY();
            mInitialMotionX = f4;
            mInitialMotionY = f5;
            break;

        case 1: // '\001'
            if(isDimmed(mSlideableView))
            {
                float f = motionevent.getX();
                float f1 = motionevent.getY();
                float f2 = f - mInitialMotionX;
                float f3 = f1 - mInitialMotionY;
                int j = mDragHelper.getTouchSlop();
                if(f2 * f2 + f3 * f3 < (float)(j * j) && mDragHelper.isViewUnder(mSlideableView, (int)f, (int)f1))
                    closePane(mSlideableView, 0);
            }
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean openPane()
    {
        return openPane(mSlideableView, 0);
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
            mPreservedOpenState = flag;
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

    public void setCoveredFadeColor(int i)
    {
        mCoveredFadeColor = i;
    }

    public void setPanelSlideListener(PanelSlideListener panelslidelistener)
    {
        mPanelSlideListener = panelslidelistener;
    }

    public void setParallaxDistance(int i)
    {
        mParallaxBy = i;
        requestLayout();
    }

    public void setShadowDrawable(Drawable drawable)
    {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable)
    {
        mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable)
    {
        mShadowDrawableRight = drawable;
    }

    public void setShadowResource(int i)
    {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i)
    {
        setShadowDrawableLeft(getResources().getDrawable(i));
    }

    public void setShadowResourceRight(int i)
    {
        setShadowDrawableRight(getResources().getDrawable(i));
    }

    public void setSliderFadeColor(int i)
    {
        mSliderFadeColor = i;
    }

    public void smoothSlideClosed()
    {
        closePane();
    }

    public void smoothSlideOpen()
    {
        openPane();
    }

    boolean smoothSlideTo(float f, int i)
    {
        boolean flag = false;
        if(mCanSlide) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        boolean flag1 = isLayoutRtlSupport();
        LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
        int j;
        if(flag1)
        {
            int k = getPaddingRight() + layoutparams.rightMargin;
            int l = mSlideableView.getWidth();
            j = (int)((float)getWidth() - ((float)k + f * (float)mSlideRange + (float)l));
        } else
        {
            j = (int)((float)(getPaddingLeft() + layoutparams.leftMargin) + f * (float)mSlideRange);
        }
        if(mDragHelper.smoothSlideViewTo(mSlideableView, j, mSlideableView.getTop()))
        {
            setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            flag = true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    void updateObscuredViewsVisibility(View view)
    {
        boolean flag = isLayoutRtlSupport();
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(flag)
            i = getWidth() - getPaddingRight();
        else
            i = getPaddingLeft();
        if(flag)
            j = getPaddingLeft();
        else
            j = getWidth() - getPaddingRight();
        k = getPaddingTop();
        l = getHeight() - getPaddingBottom();
        if(view != null && viewIsOpaque(view))
        {
            l1 = view.getLeft();
            k1 = view.getRight();
            j1 = view.getTop();
            i1 = view.getBottom();
        } else
        {
            i1 = 0;
            j1 = 0;
            k1 = 0;
            l1 = 0;
        }
        i2 = 0;
        j2 = getChildCount();
        do
        {
            View view1;
label0:
            {
                if(i2 < j2)
                {
                    view1 = getChildAt(i2);
                    if(view1 != view)
                        break label0;
                }
                return;
            }
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;
            byte byte0;
            if(flag)
                k2 = j;
            else
                k2 = i;
            l2 = Math.max(k2, view1.getLeft());
            i3 = Math.max(k, view1.getTop());
            if(flag)
                j3 = i;
            else
                j3 = j;
            k3 = Math.min(j3, view1.getRight());
            l3 = Math.min(l, view1.getBottom());
            if(l2 >= l1 && i3 >= j1 && k3 <= k1 && l3 <= i1)
                byte0 = 4;
            else
                byte0 = 0;
            view1.setVisibility(byte0);
            i2++;
        } while(true);
    }

    private static final int DEFAULT_FADE_COLOR = 0xcccccccc;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    static final SlidingPanelLayoutImpl IMPL;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    private final ArrayList mPostedRunnables;
    private boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    private float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 17)
            IMPL = new SlidingPanelLayoutImplJBMR1();
        else
        if(i >= 16)
            IMPL = new SlidingPanelLayoutImplJB();
        else
            IMPL = new SlidingPanelLayoutImplBase();
    }








/*
    static boolean access$502(SlidingPaneLayout slidingpanelayout, boolean flag)
    {
        slidingpanelayout.mPreservedOpenState = flag;
        return flag;
    }

*/



}
