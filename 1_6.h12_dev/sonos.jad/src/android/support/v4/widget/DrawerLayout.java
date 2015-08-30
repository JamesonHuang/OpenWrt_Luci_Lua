// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.*;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import java.lang.annotation.Annotation;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayoutImpl, ViewDragHelper, DrawerLayoutCompatApi21

public class DrawerLayout extends ViewGroup
    implements DrawerLayoutImpl
{
    final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if(!DrawerLayout.includeChildForAccessibility(view))
                accessibilitynodeinfocompat.setParent(null);
        }

        final DrawerLayout this$0;

        ChildAccessibilityDelegate()
        {
            this$0 = DrawerLayout.this;
            super();
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, ViewGroup viewgroup)
        {
            int i = viewgroup.getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = viewgroup.getChildAt(j);
                if(DrawerLayout.includeChildForAccessibility(view))
                    accessibilitynodeinfocompat.addChild(view);
            }

        }

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
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            boolean flag;
            if(accessibilityevent.getEventType() == 32)
            {
                List list = accessibilityevent.getText();
                View view1 = findVisibleDrawer();
                if(view1 != null)
                {
                    int i = getDrawerViewAbsoluteGravity(view1);
                    CharSequence charsequence = getDrawerTitle(i);
                    if(charsequence != null)
                        list.add(charsequence);
                }
                flag = true;
            } else
            {
                flag = super.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }
            return flag;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/widget/DrawerLayout.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if(DrawerLayout.CAN_HIDE_DESCENDANTS)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            } else
            {
                AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = AccessibilityNodeInfoCompat.obtain(accessibilitynodeinfocompat);
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
                accessibilitynodeinfocompat.setSource(view);
                android.view.ViewParent viewparent = ViewCompat.getParentForAccessibility(view);
                if(viewparent instanceof View)
                    accessibilitynodeinfocompat.setParent((View)viewparent);
                copyNodeInfoNoChildren(accessibilitynodeinfocompat, accessibilitynodeinfocompat1);
                accessibilitynodeinfocompat1.recycle();
                addChildrenForAccessibility(accessibilitynodeinfocompat, (ViewGroup)view);
            }
            accessibilitynodeinfocompat.setClassName(android/support/v4/widget/DrawerLayout.getName());
            accessibilitynodeinfocompat.setFocusable(false);
            accessibilitynodeinfocompat.setFocused(false);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            boolean flag;
            if(DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view))
                flag = super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            else
                flag = false;
            return flag;
        }

        private final Rect mTmpRect = new Rect();
        final DrawerLayout this$0;

        AccessibilityDelegate()
        {
            this$0 = DrawerLayout.this;
            super();
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int gravity;
        boolean isPeeking;
        boolean knownOpen;
        float onScreen;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            gravity = 0;
        }

        public LayoutParams(int i, int j, int k)
        {
            this(i, j);
            gravity = k;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = 0;
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, DrawerLayout.LAYOUT_ATTRS);
            gravity = typedarray.getInt(0, 0);
            typedarray.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
            gravity = layoutparams.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            gravity = 0;
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback
    {

        private void closeOtherDrawer()
        {
            byte byte0 = 3;
            if(mAbsGravity == byte0)
                byte0 = 5;
            View view = findDrawerWithGravity(byte0);
            if(view != null)
                closeDrawer(view);
        }

        private void peekDrawer()
        {
            int i = 0;
            int j = mDragger.getEdgeSize();
            boolean flag;
            View view;
            int k;
            if(mAbsGravity == 3)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                view = findDrawerWithGravity(3);
                if(view != null)
                    i = -view.getWidth();
                k = i + j;
            } else
            {
                view = findDrawerWithGravity(5);
                k = getWidth() - j;
            }
            if(view != null && (flag && view.getLeft() < k || !flag && view.getLeft() > k) && getDrawerLockMode(view) == 0)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                mDragger.smoothSlideViewTo(view, k, view.getTop());
                layoutparams.isPeeking = true;
                invalidate();
                closeOtherDrawer();
                cancelChildViewTouch();
            }
        }

        public int clampViewPositionHorizontal(View view, int i, int j)
        {
            int l;
            if(checkDrawerViewAbsoluteGravity(view, 3))
            {
                l = Math.max(-view.getWidth(), Math.min(i, 0));
            } else
            {
                int k = getWidth();
                l = Math.max(k - view.getWidth(), Math.min(i, k));
            }
            return l;
        }

        public int clampViewPositionVertical(View view, int i, int j)
        {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view)
        {
            int i;
            if(isDrawerView(view))
                i = view.getWidth();
            else
                i = 0;
            return i;
        }

        public void onEdgeDragStarted(int i, int j)
        {
            View view;
            if((i & 1) == 1)
                view = findDrawerWithGravity(3);
            else
                view = findDrawerWithGravity(5);
            if(view != null && getDrawerLockMode(view) == 0)
                mDragger.captureChildView(view, j);
        }

        public boolean onEdgeLock(int i)
        {
            return false;
        }

        public void onEdgeTouched(int i, int j)
        {
            postDelayed(mPeekRunnable, 160L);
        }

        public void onViewCaptured(View view, int i)
        {
            ((LayoutParams)view.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        public void onViewDragStateChanged(int i)
        {
            updateDrawerState(mAbsGravity, i, mDragger.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            int i1 = view.getWidth();
            float f;
            byte byte0;
            if(checkDrawerViewAbsoluteGravity(view, 3))
                f = (float)(i1 + i) / (float)i1;
            else
                f = (float)(getWidth() - i) / (float)i1;
            setDrawerViewOffset(view, f);
            if(f == 0.0F)
                byte0 = 4;
            else
                byte0 = 0;
            view.setVisibility(byte0);
            invalidate();
        }

        public void onViewReleased(View view, float f, float f1)
        {
            float f2 = getDrawerViewOffset(view);
            int i = view.getWidth();
            int k;
            if(checkDrawerViewAbsoluteGravity(view, 3))
            {
                if(f > 0.0F || f == 0.0F && f2 > 0.5F)
                    k = 0;
                else
                    k = -i;
            } else
            {
                int j = getWidth();
                if(f < 0.0F || f == 0.0F && f2 > 0.5F)
                    k = j - i;
                else
                    k = j;
            }
            mDragger.settleCapturedViewAt(k, view.getTop());
            invalidate();
        }

        public void removeCallbacks()
        {
            DrawerLayout.this.removeCallbacks(mPeekRunnable);
        }

        public void setDragger(ViewDragHelper viewdraghelper)
        {
            mDragger = viewdraghelper;
        }

        public boolean tryCaptureView(View view, int i)
        {
            boolean flag;
            if(isDrawerView(view) && checkDrawerViewAbsoluteGravity(view, mAbsGravity) && getDrawerLockMode(view) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable = new Runnable() {

            public void run()
            {
                peekDrawer();
            }

            final ViewDragCallback this$1;

                
                {
                    this$1 = ViewDragCallback.this;
                    super();
                }
        }
;
        final DrawerLayout this$0;


        public ViewDragCallback(int i)
        {
            this$0 = DrawerLayout.this;
            super();
            mAbsGravity = i;
        }
    }

    protected static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(openDrawerGravity);
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
        int lockModeLeft;
        int lockModeRight;
        int openDrawerGravity;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            openDrawerGravity = 0;
            lockModeLeft = 0;
            lockModeRight = 0;
            openDrawerGravity = parcel.readInt();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
            openDrawerGravity = 0;
            lockModeLeft = 0;
            lockModeRight = 0;
        }
    }

    static class DrawerLayoutCompatImplApi21
        implements DrawerLayoutCompatImpl
    {

        public void applyMarginInsets(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, Object obj, int i)
        {
            DrawerLayoutCompatApi21.applyMarginInsets(marginlayoutparams, obj, i);
        }

        public void configureApplyInsets(View view)
        {
            DrawerLayoutCompatApi21.configureApplyInsets(view);
        }

        public void dispatchChildInsets(View view, Object obj, int i)
        {
            DrawerLayoutCompatApi21.dispatchChildInsets(view, obj, i);
        }

        public Drawable getDefaultStatusBarBackground(Context context)
        {
            return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
        }

        public int getTopInset(Object obj)
        {
            return DrawerLayoutCompatApi21.getTopInset(obj);
        }

        DrawerLayoutCompatImplApi21()
        {
        }
    }

    static class DrawerLayoutCompatImplBase
        implements DrawerLayoutCompatImpl
    {

        public void applyMarginInsets(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, Object obj, int i)
        {
        }

        public void configureApplyInsets(View view)
        {
        }

        public void dispatchChildInsets(View view, Object obj, int i)
        {
        }

        public Drawable getDefaultStatusBarBackground(Context context)
        {
            return null;
        }

        public int getTopInset(Object obj)
        {
            return 0;
        }

        DrawerLayoutCompatImplBase()
        {
        }
    }

    static interface DrawerLayoutCompatImpl
    {

        public abstract void applyMarginInsets(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, Object obj, int i);

        public abstract void configureApplyInsets(View view);

        public abstract void dispatchChildInsets(View view, Object obj, int i);

        public abstract Drawable getDefaultStatusBarBackground(Context context);

        public abstract int getTopInset(Object obj);
    }

    public static abstract class SimpleDrawerListener
        implements DrawerListener
    {

        public void onDrawerClosed(View view)
        {
        }

        public void onDrawerOpened(View view)
        {
        }

        public void onDrawerSlide(View view, float f)
        {
        }

        public void onDrawerStateChanged(int i)
        {
        }

        public SimpleDrawerListener()
        {
        }
    }

    public static interface DrawerListener
    {

        public abstract void onDrawerClosed(View view);

        public abstract void onDrawerOpened(View view);

        public abstract void onDrawerSlide(View view, float f);

        public abstract void onDrawerStateChanged(int i);
    }

    private static interface EdgeGravity
        extends Annotation
    {
    }

    private static interface LockMode
        extends Annotation
    {
    }

    private static interface State
        extends Annotation
    {
    }


    public DrawerLayout(Context context)
    {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        mScrimColor = 0x99000000;
        mScrimPaint = new Paint();
        mFirstLayout = true;
        setDescendantFocusability(0x40000);
        float f = getResources().getDisplayMetrics().density;
        mMinDrawerMargin = (int)(0.5F + 64F * f);
        float f1 = 400F * f;
        mLeftCallback = new ViewDragCallback(3);
        mRightCallback = new ViewDragCallback(5);
        mLeftDragger = ViewDragHelper.create(this, 1.0F, mLeftCallback);
        mLeftDragger.setEdgeTrackingEnabled(1);
        mLeftDragger.setMinVelocity(f1);
        mLeftCallback.setDragger(mLeftDragger);
        mRightDragger = ViewDragHelper.create(this, 1.0F, mRightCallback);
        mRightDragger.setEdgeTrackingEnabled(2);
        mRightDragger.setMinVelocity(f1);
        mRightCallback.setDragger(mRightDragger);
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if(ViewCompat.getFitsSystemWindows(this))
        {
            IMPL.configureApplyInsets(this);
            mStatusBarBackground = IMPL.getDefaultStatusBarBackground(context);
        }
    }

    private View findVisibleDrawer()
    {
        int i;
        int j;
        i = getChildCount();
        j = 0;
_L3:
        View view;
        if(j >= i)
            break MISSING_BLOCK_LABEL_42;
        view = getChildAt(j);
        if(!isDrawerView(view) || !isDrawerVisible(view)) goto _L2; else goto _L1
_L1:
        return view;
_L2:
        j++;
          goto _L3
        view = null;
          goto _L1
    }

    static String gravityToString(int i)
    {
        String s;
        if((i & 3) == 3)
            s = "LEFT";
        else
        if((i & 5) == 5)
            s = "RIGHT";
        else
            s = Integer.toHexString(i);
        return s;
    }

    private static boolean hasOpaqueBackground(View view)
    {
        boolean flag = false;
        Drawable drawable = view.getBackground();
        if(drawable != null && drawable.getOpacity() == -1)
            flag = true;
        return flag;
    }

    private boolean hasPeekingDrawer()
    {
        int i;
        int j;
        i = getChildCount();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_39;
        if(!((LayoutParams)getChildAt(j).getLayoutParams()).isPeeking) goto _L2; else goto _L1
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

    private boolean hasVisibleDrawer()
    {
        boolean flag;
        if(findVisibleDrawer() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean includeChildForAccessibility(View view)
    {
        boolean flag;
        if(ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void updateChildrenImportantForAccessibility(View view, boolean flag)
    {
        int i = getChildCount();
        int j = 0;
        while(j < i) 
        {
            View view1 = getChildAt(j);
            if(!flag && !isDrawerView(view1) || flag && view1 == view)
                ViewCompat.setImportantForAccessibility(view1, 1);
            else
                ViewCompat.setImportantForAccessibility(view1, 4);
            j++;
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if(findOpenDrawer() != null || isDrawerView(view))
            ViewCompat.setImportantForAccessibility(view, 4);
        else
            ViewCompat.setImportantForAccessibility(view, 1);
        if(!CAN_HIDE_DESCENDANTS)
            ViewCompat.setAccessibilityDelegate(view, mChildAccessibilityDelegate);
    }

    void cancelChildViewTouch()
    {
        if(!mChildrenCanceledTouch)
        {
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                getChildAt(j).dispatchTouchEvent(motionevent);

            motionevent.recycle();
            mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View view, int i)
    {
        boolean flag;
        if((i & getDrawerViewAbsoluteGravity(view)) == i)
            flag = true;
        else
            flag = false;
        return flag;
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

    public void closeDrawer(int i)
    {
        View view = findDrawerWithGravity(i);
        if(view == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(gravityToString(i)).toString());
        } else
        {
            closeDrawer(view);
            return;
        }
    }

    public void closeDrawer(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a sliding drawer").toString());
        if(mFirstLayout)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            layoutparams.onScreen = 0.0F;
            layoutparams.knownOpen = false;
        } else
        if(checkDrawerViewAbsoluteGravity(view, 3))
            mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
        else
            mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
        invalidate();
    }

    public void closeDrawers()
    {
        closeDrawers(false);
    }

    void closeDrawers(boolean flag)
    {
        boolean flag1 = false;
        int i = getChildCount();
        int j = 0;
        while(j < i) 
        {
            View view = getChildAt(j);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(isDrawerView(view) && (!flag || layoutparams.isPeeking))
            {
                int k = view.getWidth();
                if(checkDrawerViewAbsoluteGravity(view, 3))
                    flag1 |= mLeftDragger.smoothSlideViewTo(view, -k, view.getTop());
                else
                    flag1 |= mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
                layoutparams.isPeeking = false;
            }
            j++;
        }
        mLeftCallback.removeCallbacks();
        mRightCallback.removeCallbacks();
        if(flag1)
            invalidate();
    }

    public void computeScroll()
    {
        int i = getChildCount();
        float f = 0.0F;
        for(int j = 0; j < i; j++)
            f = Math.max(f, ((LayoutParams)getChildAt(j).getLayoutParams()).onScreen);

        mScrimOpacity = f;
        if(mLeftDragger.continueSettling(true) | mRightDragger.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    void dispatchOnDrawerClosed(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(layoutparams.knownOpen)
        {
            layoutparams.knownOpen = false;
            if(mListener != null)
                mListener.onDrawerClosed(view);
            updateChildrenImportantForAccessibility(view, false);
            if(hasWindowFocus())
            {
                View view1 = getRootView();
                if(view1 != null)
                    view1.sendAccessibilityEvent(32);
            }
        }
    }

    void dispatchOnDrawerOpened(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(!layoutparams.knownOpen)
        {
            layoutparams.knownOpen = true;
            if(mListener != null)
                mListener.onDrawerOpened(view);
            updateChildrenImportantForAccessibility(view, true);
            if(hasWindowFocus())
                sendAccessibilityEvent(32);
            view.requestFocus();
        }
    }

    void dispatchOnDrawerSlide(View view, float f)
    {
        if(mListener != null)
            mListener.onDrawerSlide(view, f);
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        boolean flag;
        int j;
        int k;
        boolean flag1;
        int i = getHeight();
        flag = isContentView(view);
        j = 0;
        k = getWidth();
        int i1 = canvas.save();
        if(flag)
        {
            int j3 = getChildCount();
            int k3 = 0;
            while(k3 < j3) 
            {
                View view1 = getChildAt(k3);
                if(view1 != view && view1.getVisibility() == 0 && hasOpaqueBackground(view1) && isDrawerView(view1) && view1.getHeight() >= i)
                    if(checkDrawerViewAbsoluteGravity(view1, 3))
                    {
                        int i4 = view1.getRight();
                        if(i4 > j)
                            j = i4;
                    } else
                    {
                        int l3 = view1.getLeft();
                        if(l3 < k)
                            k = l3;
                    }
                k3++;
            }
            canvas.clipRect(j, 0, k, getHeight());
        }
        flag1 = super.drawChild(canvas, view, l);
        canvas.restoreToCount(i1);
        if(mScrimOpacity <= 0.0F || !flag) goto _L2; else goto _L1
_L1:
        int i3 = (int)((float)((0xff000000 & mScrimColor) >>> 24) * mScrimOpacity) << 24 | 0xffffff & mScrimColor;
        mScrimPaint.setColor(i3);
        canvas.drawRect(j, 0.0F, k, getHeight(), mScrimPaint);
_L4:
        return flag1;
_L2:
        if(mShadowLeft != null && checkDrawerViewAbsoluteGravity(view, 3))
        {
            int j2 = mShadowLeft.getIntrinsicWidth();
            int k2 = view.getRight();
            int l2 = mLeftDragger.getEdgeSize();
            float f1 = Math.max(0.0F, Math.min((float)k2 / (float)l2, 1.0F));
            mShadowLeft.setBounds(k2, view.getTop(), k2 + j2, view.getBottom());
            mShadowLeft.setAlpha((int)(255F * f1));
            mShadowLeft.draw(canvas);
        } else
        if(mShadowRight != null && checkDrawerViewAbsoluteGravity(view, 5))
        {
            int j1 = mShadowRight.getIntrinsicWidth();
            int k1 = view.getLeft();
            int l1 = getWidth() - k1;
            int i2 = mRightDragger.getEdgeSize();
            float f = Math.max(0.0F, Math.min((float)l1 / (float)i2, 1.0F));
            mShadowRight.setBounds(k1 - j1, view.getTop(), k1, view.getBottom());
            mShadowRight.setAlpha((int)(255F * f));
            mShadowRight.draw(canvas);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    View findDrawerWithGravity(int i)
    {
        int j;
        int k;
        int l;
        j = 7 & GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        k = getChildCount();
        l = 0;
_L3:
        View view;
        if(l >= k)
            break MISSING_BLOCK_LABEL_56;
        view = getChildAt(l);
        if((7 & getDrawerViewAbsoluteGravity(view)) != j) goto _L2; else goto _L1
_L1:
        return view;
_L2:
        l++;
          goto _L3
        view = null;
          goto _L1
    }

    View findOpenDrawer()
    {
        int i;
        int j;
        i = getChildCount();
        j = 0;
_L3:
        View view;
        if(j >= i)
            break MISSING_BLOCK_LABEL_39;
        view = getChildAt(j);
        if(!((LayoutParams)view.getLayoutParams()).knownOpen) goto _L2; else goto _L1
_L1:
        return view;
_L2:
        j++;
          goto _L3
        view = null;
          goto _L1
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        LayoutParams layoutparams1;
        if(layoutparams instanceof LayoutParams)
            layoutparams1 = new LayoutParams((LayoutParams)layoutparams);
        else
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            layoutparams1 = new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        else
            layoutparams1 = new LayoutParams(layoutparams);
        return layoutparams1;
    }

    public int getDrawerLockMode(int i)
    {
        int j = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        int k;
        if(j == 3)
            k = mLockModeLeft;
        else
        if(j == 5)
            k = mLockModeRight;
        else
            k = 0;
        return k;
    }

    public int getDrawerLockMode(View view)
    {
        int i = getDrawerViewAbsoluteGravity(view);
        int j;
        if(i == 3)
            j = mLockModeLeft;
        else
        if(i == 5)
            j = mLockModeRight;
        else
            j = 0;
        return j;
    }

    public CharSequence getDrawerTitle(int i)
    {
        int j = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        CharSequence charsequence;
        if(j == 3)
            charsequence = mTitleLeft;
        else
        if(j == 5)
            charsequence = mTitleRight;
        else
            charsequence = null;
        return charsequence;
    }

    int getDrawerViewAbsoluteGravity(View view)
    {
        return GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    float getDrawerViewOffset(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).onScreen;
    }

    public Drawable getStatusBarBackgroundDrawable()
    {
        return mStatusBarBackground;
    }

    boolean isContentView(View view)
    {
        boolean flag;
        if(((LayoutParams)view.getLayoutParams()).gravity == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDrawerOpen(int i)
    {
        View view = findDrawerWithGravity(i);
        boolean flag;
        if(view != null)
            flag = isDrawerOpen(view);
        else
            flag = false;
        return flag;
    }

    public boolean isDrawerOpen(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a drawer").toString());
        else
            return ((LayoutParams)view.getLayoutParams()).knownOpen;
    }

    boolean isDrawerView(View view)
    {
        boolean flag;
        if((7 & GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view))) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDrawerVisible(int i)
    {
        View view = findDrawerWithGravity(i);
        boolean flag;
        if(view != null)
            flag = isDrawerVisible(view);
        else
            flag = false;
        return flag;
    }

    public boolean isDrawerVisible(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a drawer").toString());
        boolean flag;
        if(((LayoutParams)view.getLayoutParams()).onScreen > 0.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void moveDrawerToOffset(View view, float f)
    {
        float f1 = getDrawerViewOffset(view);
        int i = view.getWidth();
        int j = (int)(f1 * (float)i);
        int k = (int)(f * (float)i) - j;
        if(!checkDrawerViewAbsoluteGravity(view, 3))
            k = -k;
        view.offsetLeftAndRight(k);
        setDrawerViewOffset(view, f);
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
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mDrawStatusBarBackground && mStatusBarBackground != null)
        {
            int i = IMPL.getTopInset(mLastInsets);
            if(i > 0)
            {
                mStatusBarBackground.setBounds(0, 0, getWidth(), i);
                mStatusBarBackground.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        boolean flag1;
        boolean flag2;
        flag = false;
        i = MotionEventCompat.getActionMasked(motionevent);
        flag1 = mLeftDragger.shouldInterceptTouchEvent(motionevent) | mRightDragger.shouldInterceptTouchEvent(motionevent);
        flag2 = false;
        i;
        JVM INSTR tableswitch 0 3: default 60
    //                   0 88
    //                   1 194
    //                   2 166
    //                   3 194;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if(flag1 || flag2 || hasPeekingDrawer() || mChildrenCanceledTouch)
            flag = true;
        return flag;
_L2:
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        mInitialMotionX = f;
        mInitialMotionY = f1;
        if(mScrimOpacity > 0.0F)
        {
            View view = mLeftDragger.findTopChildUnder((int)f, (int)f1);
            if(view != null && isContentView(view))
                flag2 = true;
        }
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(mLeftDragger.checkTouchSlop(3))
        {
            mLeftCallback.removeCallbacks();
            mRightCallback.removeCallbacks();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        closeDrawers(true);
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && hasVisibleDrawer())
        {
            KeyEventCompat.startTracking(keyevent);
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            View view = findVisibleDrawer();
            if(view != null && getDrawerLockMode(view) == 0)
                closeDrawers();
            if(view != null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = super.onKeyUp(i, keyevent);
        }
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        mInLayout = true;
        i1 = k - i;
        j1 = getChildCount();
        k1 = 0;
_L2:
        View view;
        if(k1 >= j1)
            break MISSING_BLOCK_LABEL_454;
        view = getChildAt(k1);
        if(view.getVisibility() != 8)
            break; /* Loop/switch isn't completed */
_L3:
        k1++;
        if(true) goto _L2; else goto _L1
_L1:
        LayoutParams layoutparams;
label0:
        {
            layoutparams = (LayoutParams)view.getLayoutParams();
            if(!isContentView(view))
                break label0;
            view.layout(layoutparams.leftMargin, layoutparams.topMargin, layoutparams.leftMargin + view.getMeasuredWidth(), layoutparams.topMargin + view.getMeasuredHeight());
        }
          goto _L3
        int l1;
        int i2;
        int j2;
        l1 = view.getMeasuredWidth();
        i2 = view.getMeasuredHeight();
        float f;
        boolean flag1;
        if(checkDrawerViewAbsoluteGravity(view, 3))
        {
            j2 = -l1 + (int)((float)l1 * layoutparams.onScreen);
            f = (float)(l1 + j2) / (float)l1;
        } else
        {
            j2 = i1 - (int)((float)l1 * layoutparams.onScreen);
            f = (float)(i1 - j2) / (float)l1;
        }
        if(f != layoutparams.onScreen)
            flag1 = true;
        else
            flag1 = false;
        0x70 & layoutparams.gravity;
        JVM INSTR lookupswitch 2: default 212
    //                   16: 362
    //                   80: 319;
           goto _L4 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_362;
_L4:
        view.layout(j2, layoutparams.topMargin, j2 + l1, i2 + layoutparams.topMargin);
_L7:
        if(flag1)
            setDrawerViewOffset(view, f);
        int k2;
        int l2;
        int i3;
        int j3;
        if(layoutparams.onScreen > 0.0F)
            i3 = 0;
        else
            i3 = 4;
        if(view.getVisibility() != i3)
            view.setVisibility(i3);
          goto _L3
_L6:
        j3 = l - j;
        view.layout(j2, j3 - layoutparams.bottomMargin - view.getMeasuredHeight(), j2 + l1, j3 - layoutparams.bottomMargin);
          goto _L7
        k2 = l - j;
        l2 = (k2 - i2) / 2;
        if(l2 < layoutparams.topMargin)
            l2 = layoutparams.topMargin;
        else
        if(l2 + i2 > k2 - layoutparams.bottomMargin)
            l2 = k2 - layoutparams.bottomMargin - i2;
        view.layout(j2, l2, j2 + l1, l2 + i2);
          goto _L7
        mInLayout = false;
        mFirstLayout = false;
        return;
          goto _L3
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getMode(j);
        i1 = android.view.View.MeasureSpec.getSize(i);
        j1 = android.view.View.MeasureSpec.getSize(j);
        if(k != 0x40000000 || l != 0x40000000)
        {
            if(!isInEditMode())
                break MISSING_BLOCK_LABEL_156;
            break MISSING_BLOCK_LABEL_45;
        }
_L1:
        setMeasuredDimension(i1, j1);
        boolean flag;
        int k1;
        int l1;
        int i2;
        if(mLastInsets != null && ViewCompat.getFitsSystemWindows(this))
            flag = true;
        else
            flag = false;
        k1 = ViewCompat.getLayoutDirection(this);
        l1 = getChildCount();
        i2 = 0;
        while(i2 < l1) 
        {
            View view = getChildAt(i2);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if(flag)
                {
                    int k2 = GravityCompat.getAbsoluteGravity(layoutparams.gravity, k1);
                    if(ViewCompat.getFitsSystemWindows(view))
                        IMPL.dispatchChildInsets(view, mLastInsets, k2);
                    else
                        IMPL.applyMarginInsets(layoutparams, mLastInsets, k2);
                }
                if(isContentView(view))
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i1 - layoutparams.leftMargin - layoutparams.rightMargin, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j1 - layoutparams.topMargin - layoutparams.bottomMargin, 0x40000000));
                else
                if(isDrawerView(view))
                {
                    int j2 = 7 & getDrawerViewAbsoluteGravity(view);
                    if((0 & j2) != 0)
                        throw new IllegalStateException((new StringBuilder()).append("Child drawer has absolute gravity ").append(gravityToString(j2)).append(" but this ").append("DrawerLayout").append(" already has a ").append("drawer view along that edge").toString());
                    view.measure(getChildMeasureSpec(i, mMinDrawerMargin + layoutparams.leftMargin + layoutparams.rightMargin, layoutparams.width), getChildMeasureSpec(j, layoutparams.topMargin + layoutparams.bottomMargin, layoutparams.height));
                } else
                {
                    throw new IllegalStateException((new StringBuilder()).append("Child ").append(view).append(" at index ").append(i2).append(" does not have a valid layout_gravity - must be Gravity.LEFT, ").append("Gravity.RIGHT or Gravity.NO_GRAVITY").toString());
                }
            }
            i2++;
        }
        break MISSING_BLOCK_LABEL_487;
        if(k != 0x80000000 && k == 0)
            i1 = 300;
        if(l != 0x80000000 && l == 0)
            j1 = 300;
          goto _L1
        throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(savedstate.openDrawerGravity != 0)
        {
            View view = findDrawerWithGravity(savedstate.openDrawerGravity);
            if(view != null)
                openDrawer(view);
        }
        setDrawerLockMode(savedstate.lockModeLeft, 3);
        setDrawerLockMode(savedstate.lockModeRight, 5);
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        View view = findOpenDrawer();
        if(view != null)
            savedstate.openDrawerGravity = ((LayoutParams)view.getLayoutParams()).gravity;
        savedstate.lockModeLeft = mLockModeLeft;
        savedstate.lockModeRight = mLockModeRight;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mLeftDragger.processTouchEvent(motionevent);
        mRightDragger.processTouchEvent(motionevent);
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 58
    //                   1 95
    //                   2 56
    //                   3 225;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return true;
_L2:
        float f4 = motionevent.getX();
        float f5 = motionevent.getY();
        mInitialMotionX = f4;
        mInitialMotionY = f5;
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        continue; /* Loop/switch isn't completed */
_L3:
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        boolean flag = true;
        View view = mLeftDragger.findTopChildUnder((int)f, (int)f1);
        if(view != null && isContentView(view))
        {
            float f2 = f - mInitialMotionX;
            float f3 = f1 - mInitialMotionY;
            int i = mLeftDragger.getTouchSlop();
            if(f2 * f2 + f3 * f3 < (float)(i * i))
            {
                View view1 = findOpenDrawer();
                if(view1 != null)
                    if(getDrawerLockMode(view1) == 2)
                        flag = true;
                    else
                        flag = false;
            }
        }
        closeDrawers(flag);
        mDisallowInterceptRequested = false;
        continue; /* Loop/switch isn't completed */
_L4:
        closeDrawers(true);
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void openDrawer(int i)
    {
        View view = findDrawerWithGravity(i);
        if(view == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(gravityToString(i)).toString());
        } else
        {
            openDrawer(view);
            return;
        }
    }

    public void openDrawer(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a sliding drawer").toString());
        if(mFirstLayout)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            layoutparams.onScreen = 1.0F;
            layoutparams.knownOpen = true;
            updateChildrenImportantForAccessibility(view, true);
        } else
        if(checkDrawerViewAbsoluteGravity(view, 3))
            mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
        else
            mRightDragger.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
        invalidate();
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        super.requestDisallowInterceptTouchEvent(flag);
        mDisallowInterceptRequested = flag;
        if(flag)
            closeDrawers(true);
    }

    public void requestLayout()
    {
        if(!mInLayout)
            super.requestLayout();
    }

    public void setChildInsets(Object obj, boolean flag)
    {
        mLastInsets = obj;
        mDrawStatusBarBackground = flag;
        boolean flag1;
        if(!flag && getBackground() == null)
            flag1 = true;
        else
            flag1 = false;
        setWillNotDraw(flag1);
        requestLayout();
    }

    public void setDrawerListener(DrawerListener drawerlistener)
    {
        mListener = drawerlistener;
    }

    public void setDrawerLockMode(int i)
    {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int j)
    {
        int k;
        k = GravityCompat.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this));
        if(k == 3)
            mLockModeLeft = i;
        else
        if(k == 5)
            mLockModeRight = i;
        if(i != 0)
        {
            ViewDragHelper viewdraghelper;
            if(k == 3)
                viewdraghelper = mLeftDragger;
            else
                viewdraghelper = mRightDragger;
            viewdraghelper.cancel();
        }
        i;
        JVM INSTR tableswitch 1 2: default 64
    //                   1 108
    //                   2 87;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        View view1 = findDrawerWithGravity(k);
        if(view1 != null)
            openDrawer(view1);
        continue; /* Loop/switch isn't completed */
_L2:
        View view = findDrawerWithGravity(k);
        if(view != null)
            closeDrawer(view);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setDrawerLockMode(int i, View view)
    {
        if(!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a ").append("drawer with appropriate layout_gravity").toString());
        } else
        {
            setDrawerLockMode(i, ((LayoutParams)view.getLayoutParams()).gravity);
            return;
        }
    }

    public void setDrawerShadow(int i, int j)
    {
        setDrawerShadow(getResources().getDrawable(i), j);
    }

    public void setDrawerShadow(Drawable drawable, int i)
    {
        int j = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if((j & 3) == 3)
        {
            mShadowLeft = drawable;
            invalidate();
        }
        if((j & 5) == 5)
        {
            mShadowRight = drawable;
            invalidate();
        }
    }

    public void setDrawerTitle(int i, CharSequence charsequence)
    {
        int j = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if(j != 3) goto _L2; else goto _L1
_L1:
        mTitleLeft = charsequence;
_L4:
        return;
_L2:
        if(j == 5)
            mTitleRight = charsequence;
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setDrawerViewOffset(View view, float f)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(f != layoutparams.onScreen)
        {
            layoutparams.onScreen = f;
            dispatchOnDrawerSlide(view, f);
        }
    }

    public void setScrimColor(int i)
    {
        mScrimColor = i;
        invalidate();
    }

    public void setStatusBarBackground(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = ContextCompat.getDrawable(getContext(), i);
        else
            drawable = null;
        mStatusBarBackground = drawable;
    }

    public void setStatusBarBackground(Drawable drawable)
    {
        mStatusBarBackground = drawable;
    }

    public void setStatusBarBackgroundColor(int i)
    {
        mStatusBarBackground = new ColorDrawable(i);
    }

    void updateDrawerState(int i, int j, View view)
    {
        LayoutParams layoutparams;
        int k = mLeftDragger.getViewDragState();
        int l = mRightDragger.getViewDragState();
        int i1;
        if(k == 1 || l == 1)
            i1 = 1;
        else
        if(k == 2 || l == 2)
            i1 = 2;
        else
            i1 = 0;
        if(view == null || j != 0) goto _L2; else goto _L1
_L1:
        layoutparams = (LayoutParams)view.getLayoutParams();
        if(layoutparams.onScreen != 0.0F) goto _L4; else goto _L3
_L3:
        dispatchOnDrawerClosed(view);
_L2:
        if(i1 != mDrawerState)
        {
            mDrawerState = i1;
            if(mListener != null)
                mListener.onDrawerStateChanged(i1);
        }
        return;
_L4:
        if(layoutparams.onScreen == 1.0F)
            dispatchOnDrawerOpened(view);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static final boolean ALLOW_EDGE_LOCK = false;
    private static final boolean CAN_HIDE_DESCENDANTS = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    static final DrawerLayoutCompatImpl IMPL;
    private static final int LAYOUT_ATTRS[];
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1F;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    private DrawerListener mListener;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mMinDrawerMargin;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowLeft;
    private Drawable mShadowRight;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    static 
    {
        boolean flag = true;
        int ai[] = new int[flag];
        ai[0] = 0x10100b3;
        LAYOUT_ATTRS = ai;
        if(android.os.Build.VERSION.SDK_INT < 19)
            flag = false;
        CAN_HIDE_DESCENDANTS = flag;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new DrawerLayoutCompatImplApi21();
        else
            IMPL = new DrawerLayoutCompatImplBase();
    }




}
