// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;
import com.sonos.acr.util.SLog;
import java.lang.reflect.Field;
import java.util.ArrayList;

// Referenced classes of package com.emilsjolander.components.stickylistheaders:
//            WrapperView, AdapterWrapper, SectionIndexerAdapterWrapper, StickyListHeadersAdapter

public class StickyListHeadersListView extends ListView
{
    public static interface OnHeaderClickListener
    {

        public abstract void onHeaderClick(StickyListHeadersListView stickylistheaderslistview, View view, int i, long l, boolean flag);
    }


    public StickyListHeadersListView(Context context)
    {
        this(context, null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010074);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAreHeadersSticky = true;
        mBindToPadding = true;
        mClippingRect = new Rect();
        mCurrentHeaderId = null;
        mHeaderDownY = -1F;
        mHeaderBeingPressed = false;
        mDrawingListUnderStickyHeader = true;
        mSelectorRect = new Rect();
        mAdapterHeaderClickListener = new AdapterWrapper.OnHeaderClickListener() {

            public void onHeaderClick(View view, int j, long l)
            {
                if(mOnHeaderClickListener != null)
                    mOnHeaderClickListener.onHeaderClick(StickyListHeadersListView.this, view, j, l, false);
            }

            final StickyListHeadersListView this$0;

            
            {
                this$0 = StickyListHeadersListView.this;
                super();
            }
        }
;
        mDataSetChangedObserver = new DataSetObserver() {

            public void onChanged()
            {
                reset();
            }

            public void onInvalidated()
            {
                reset();
            }

            final StickyListHeadersListView this$0;

            
            {
                this$0 = StickyListHeadersListView.this;
                super();
            }
        }
;
        mOnScrollListener = new android.widget.AbsListView.OnScrollListener() {

            public void onScroll(AbsListView abslistview, int j, int k, int l)
            {
                if(mOnScrollListenerDelegate != null)
                    mOnScrollListenerDelegate.onScroll(abslistview, j, k, l);
                scrollChanged(j);
            }

            public void onScrollStateChanged(AbsListView abslistview, int j)
            {
                if(mOnScrollListenerDelegate != null)
                    mOnScrollListenerDelegate.onScrollStateChanged(abslistview, j);
            }

            final StickyListHeadersListView this$0;

            
            {
                this$0 = StickyListHeadersListView.this;
                super();
            }
        }
;
        super.setOnScrollListener(mOnScrollListener);
        super.setDivider(null);
        super.setDividerHeight(0);
        mViewConfig = ViewConfiguration.get(context);
        Field field = android/widget/AbsListView.getDeclaredField("mSelectorRect");
        field.setAccessible(true);
        mSelectorRect = (Rect)field.get(this);
        mSelectorPositionField = android/widget/AbsListView.getDeclaredField("mSelectorPosition");
        mSelectorPositionField.setAccessible(true);
_L2:
        return;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        continue; /* Loop/switch isn't completed */
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        continue; /* Loop/switch isn't completed */
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void drawStickyHeader(Canvas canvas)
    {
        int i = getHeaderHeight();
        int j = mHeaderBottomPosition - i;
        mClippingRect.left = getPaddingLeft();
        mClippingRect.right = getWidth() - getPaddingRight();
        mClippingRect.bottom = j + i;
        mClippingRect.top = getOffsetPadding();
        if(mHeaderBottomPosition > mClippingRect.top)
        {
            canvas.save();
            if(!mDrawingListUnderStickyHeader)
                canvas.clipRect(mClippingRect);
            canvas.translate(getPaddingLeft(), j);
            mHeader.draw(canvas);
            canvas.restore();
        }
    }

    private int fixedFirstVisibleItem(int i)
    {
        int j = getOffsetPadding();
        if(android.os.Build.VERSION.SDK_INT < 11 || mBindToPadding && j >= 0) goto _L2; else goto _L1
_L1:
        int l = i;
_L4:
        return l;
_L2:
        int k = 0;
        do
        {
label0:
            {
                if(k < getChildCount())
                {
                    if(getChildAt(k).getBottom() < j)
                        break label0;
                    i += k;
                }
                if(mBindToPadding && getPaddingTop() > 0 && super.getChildAt(0) != null && super.getChildAt(0).getTop() > 0 && i > 0)
                    i--;
                l = i;
            }
            if(true)
                continue;
            k++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getHeaderHeight()
    {
        int i;
        if(mHeader == null)
            i = 0;
        else
            i = mHeader.getMeasuredHeight();
        return i;
    }

    private int getOffsetPadding()
    {
        int i;
        if(mBindToPadding)
            i = getPaddingTop();
        else
            i = 0;
        return i;
    }

    private int getSelectorPosition()
    {
        if(mSelectorPositionField != null) goto _L2; else goto _L1
_L1:
        int k = 0;
_L5:
        if(k >= getChildCount())
            break MISSING_BLOCK_LABEL_79;
        if(getChildAt(k).getBottom() != mSelectorRect.bottom) goto _L4; else goto _L3
_L3:
        int i = k + fixedFirstVisibleItem(getFirstVisiblePosition());
_L6:
        return i;
_L4:
        k++;
          goto _L5
_L2:
        int j = mSelectorPositionField.getInt(this);
        i = j;
          goto _L6
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
_L7:
        i = -1;
          goto _L6
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        illegalaccessexception.printStackTrace();
          goto _L7
    }

    private boolean isScrollBarOverlay()
    {
        int i = getScrollBarStyle();
        boolean flag;
        if(i == 0 || i == 0x2000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void measureHeader()
    {
        int i = getWidth() - getPaddingLeft() - getPaddingRight();
        int j;
        int k;
        android.view.ViewGroup.LayoutParams layoutparams;
        int l;
        if(isScrollBarOverlay())
            j = 0;
        else
            j = getVerticalScrollbarWidth();
        k = android.view.View.MeasureSpec.makeMeasureSpec(i - j, 0x40000000);
        layoutparams = mHeader.getLayoutParams();
        if(layoutparams != null && layoutparams.height > 0)
            l = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
        else
            l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        mHeader.measure(k, l);
        mHeader.layout(getPaddingLeft(), 0, getWidth() - getPaddingRight(), mHeader.getMeasuredHeight());
    }

    private void positionSelectorRect()
    {
        if(!mSelectorRect.isEmpty())
        {
            int i = getSelectorPosition();
            if(i >= 0)
            {
                View view = getChildAt(i - fixedFirstVisibleItem(getFirstVisiblePosition()));
                if(view instanceof WrapperView)
                {
                    WrapperView wrapperview = (WrapperView)view;
                    mSelectorRect.top = wrapperview.getTop() + wrapperview.mItemTop;
                }
            }
        }
    }

    private void reset()
    {
        mHeader = null;
        mCurrentHeaderId = null;
        mHeaderPosition = null;
        mHeaderBottomPosition = -1;
    }

    private void scrollChanged(int i)
    {
        int j;
        if(mAdapter == null)
            j = 0;
        else
            j = mAdapter.getCount();
        if(j != 0 && mAreHeadersSticky) goto _L2; else goto _L1
_L1:
        return;
_L2:
label0:
        {
            int k = getHeaderViewsCount();
            int l = fixedFirstVisibleItem(i) - k;
            if(l < 0 || l > j - 1)
            {
                reset();
                updateHeaderVisibilities();
                invalidate();
                continue; /* Loop/switch isn't completed */
            }
            if(mHeaderPosition == null || mHeaderPosition.intValue() != l)
            {
                mHeaderPosition = Integer.valueOf(l);
                mCurrentHeaderId = Long.valueOf(mAdapter.getHeaderId(l));
                mHeader = mAdapter.getHeaderView(mHeaderPosition.intValue(), mHeader, this);
                measureHeader();
            }
            int i1 = getChildCount();
            if(i1 == 0)
                break label0;
            View view = null;
            int j1 = 0x7fffffff;
            boolean flag = false;
            int k1 = 0;
            do
            {
                if(k1 >= i1)
                    break;
                View view1 = super.getChildAt(k1);
                boolean flag1;
                int k2;
                if(mFooterViews != null && mFooterViews.contains(view1))
                    flag1 = true;
                else
                    flag1 = false;
                k2 = view1.getTop() - getOffsetPadding();
                if(k2 >= 0 && (view == null || !flag && !((WrapperView)view).hasHeader() || (flag1 || ((WrapperView)view1).hasHeader()) && k2 < j1))
                {
                    view = view1;
                    flag = flag1;
                    j1 = k2;
                }
                k1++;
            } while(true);
            int l1 = getHeaderHeight();
            if(view != null && (flag || ((WrapperView)view).hasHeader()))
            {
                if(l == k && super.getChildAt(0).getTop() > 0 && !mBindToPadding)
                {
                    mHeaderBottomPosition = 0;
                } else
                {
                    int i2 = getOffsetPadding();
                    mHeaderBottomPosition = Math.min(view.getTop(), l1 + i2);
                    int j2;
                    if(mHeaderBottomPosition < i2)
                        j2 = l1 + i2;
                    else
                        j2 = mHeaderBottomPosition;
                    mHeaderBottomPosition = j2;
                }
            } else
            {
                mHeaderBottomPosition = l1 + getOffsetPadding();
            }
        }
        updateHeaderVisibilities();
        invalidate();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void updateHeaderVisibilities()
    {
        int i = getOffsetPadding();
        int j = getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = super.getChildAt(k);
            if(!(view instanceof WrapperView))
                continue;
            WrapperView wrapperview = (WrapperView)view;
            if(wrapperview.hasHeader())
            {
                View view1 = wrapperview.mHeader;
                if(wrapperview.getTop() < i)
                    view1.setVisibility(4);
                else
                    view1.setVisibility(0);
            }
            k++;
        }
    }

    private AdapterWrapper wrapAdapter(ListAdapter listadapter)
    {
        Object obj;
        if(listadapter instanceof SectionIndexer)
            obj = new SectionIndexerAdapterWrapper(getContext(), (StickyListHeadersAdapter)listadapter);
        else
            obj = new AdapterWrapper(getContext(), (StickyListHeadersAdapter)listadapter);
        ((AdapterWrapper) (obj)).setDivider(mDivider);
        ((AdapterWrapper) (obj)).setDividerHeight(mDividerHeight);
        ((AdapterWrapper) (obj)).registerDataSetObserver(mDataSetChangedObserver);
        ((AdapterWrapper) (obj)).setOnHeaderClickListener(mAdapterHeaderClickListener);
        return ((AdapterWrapper) (obj));
    }

    public void addFooterView(View view)
    {
        super.addFooterView(view);
        if(mFooterViews == null)
            mFooterViews = new ArrayList();
        mFooterViews.add(view);
    }

    protected void dispatchDraw(Canvas canvas)
    {
        positionSelectorRect();
        if(!mAreHeadersSticky || mHeader == null)
        {
            super.dispatchDraw(canvas);
        } else
        {
            if(!mDrawingListUnderStickyHeader)
            {
                mClippingRect.set(0, mHeaderBottomPosition, getWidth(), getHeight());
                canvas.save();
                canvas.clipRect(mClippingRect);
            }
            super.dispatchDraw(canvas);
            if(!mDrawingListUnderStickyHeader)
                canvas.restore();
            drawStickyHeader(canvas);
        }
    }

    public boolean getAreHeadersSticky()
    {
        return mAreHeadersSticky;
    }

    public StickyListHeadersAdapter getWrappedAdapter()
    {
        StickyListHeadersAdapter stickylistheadersadapter;
        if(mAdapter == null)
            stickylistheadersadapter = null;
        else
            stickylistheadersadapter = mAdapter.mDelegate;
        return stickylistheadersadapter;
    }

    public View getWrappedView(int i)
    {
        View view = getChildAt(i);
        if(view instanceof WrapperView)
            view = ((WrapperView)view).mItem;
        return view;
    }

    public boolean isDrawingListUnderStickyHeader()
    {
        return mDrawingListUnderStickyHeader;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(mAreHeadersSticky && motionevent.getAction() == 0 && motionevent.getY() <= (float)mHeaderBottomPosition)
        {
            mHeaderDownY = motionevent.getY();
            mHeaderBeingPressed = flag;
            mHeader.setPressed(flag);
            mHeader.invalidate();
            invalidate(0, 0, getWidth(), mHeaderBottomPosition);
        } else
        {
            flag = super.onInterceptTouchEvent(motionevent);
        }
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
        {
            reset();
            scrollChanged(getFirstVisiblePosition());
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if(i != 0 || motionevent.getY() > (float)mHeaderBottomPosition) goto _L2; else goto _L1
_L1:
        boolean flag;
        mHeaderDownY = motionevent.getY();
        mHeaderBeingPressed = true;
        mHeader.setPressed(true);
        mHeader.invalidate();
        invalidate(0, 0, getWidth(), mHeaderBottomPosition);
        flag = true;
          goto _L3
_L2:
        if(!mHeaderBeingPressed) goto _L5; else goto _L4
_L4:
        if(Math.abs(motionevent.getY() - mHeaderDownY) >= (float)mViewConfig.getScaledTouchSlop()) goto _L7; else goto _L6
_L6:
        if(i == 1 || i == 3)
        {
            mHeaderDownY = -1F;
            mHeaderBeingPressed = false;
            mHeader.setPressed(false);
            mHeader.invalidate();
            invalidate(0, 0, getWidth(), mHeaderBottomPosition);
            if(mOnHeaderClickListener != null)
                mOnHeaderClickListener.onHeaderClick(this, mHeader, mHeaderPosition.intValue(), mCurrentHeaderId.longValue(), true);
        }
          goto _L8
_L7:
        mHeaderDownY = -1F;
        mHeaderBeingPressed = false;
        mHeader.setPressed(false);
        mHeader.invalidate();
        invalidate(0, 0, getWidth(), mHeaderBottomPosition);
_L5:
        boolean flag1 = super.onTouchEvent(motionevent);
        flag = flag1;
          goto _L3
        ArrayIndexOutOfBoundsException arrayindexoutofboundsexception;
        arrayindexoutofboundsexception;
        SLog.w("View", "Caught ArrayIndexOutOfBoundsException");
        flag = true;
_L3:
        return flag;
_L8:
        flag = true;
        if(true) goto _L3; else goto _L9
_L9:
    }

    public boolean performItemClick(View view, int i, long l)
    {
        if(view instanceof WrapperView)
            view = ((WrapperView)view).mItem;
        return super.performItemClick(view, i, l);
    }

    public boolean removeFooterView(View view)
    {
        boolean flag;
        if(super.removeFooterView(view))
        {
            mFooterViews.remove(view);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(isInEditMode())
            super.setAdapter(listadapter);
        else
        if(listadapter == null)
        {
            mAdapter = null;
            reset();
            super.setAdapter(null);
        } else
        {
            if(!(listadapter instanceof StickyListHeadersAdapter))
                throw new IllegalArgumentException("Adapter must implement StickyListHeadersAdapter");
            mAdapter = wrapAdapter(listadapter);
            reset();
            super.setAdapter(mAdapter);
        }
    }

    public void setAreHeadersSticky(boolean flag)
    {
        if(mAreHeadersSticky != flag)
        {
            mAreHeadersSticky = flag;
            requestLayout();
        }
    }

    public void setBindToPadding(boolean flag)
    {
        mBindToPadding = flag;
    }

    public void setDivider(Drawable drawable)
    {
        mDivider = drawable;
        if(drawable != null)
        {
            int i = drawable.getIntrinsicHeight();
            if(i >= 0)
                setDividerHeight(i);
        }
        if(mAdapter != null)
        {
            mAdapter.setDivider(drawable);
            requestLayout();
            invalidate();
        }
    }

    public void setDividerHeight(int i)
    {
        mDividerHeight = i;
        if(mAdapter != null)
        {
            mAdapter.setDividerHeight(i);
            requestLayout();
            invalidate();
        }
    }

    public void setDrawingListUnderStickyHeader(boolean flag)
    {
        mDrawingListUnderStickyHeader = flag;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onheaderclicklistener)
    {
        mOnHeaderClickListener = onheaderclicklistener;
    }

    public void setOnScrollListener(android.widget.AbsListView.OnScrollListener onscrolllistener)
    {
        mOnScrollListenerDelegate = onscrolllistener;
    }

    private AdapterWrapper mAdapter;
    private AdapterWrapper.OnHeaderClickListener mAdapterHeaderClickListener;
    private boolean mAreHeadersSticky;
    private boolean mBindToPadding;
    private final Rect mClippingRect;
    private Long mCurrentHeaderId;
    private DataSetObserver mDataSetChangedObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    private boolean mDrawingListUnderStickyHeader;
    private ArrayList mFooterViews;
    private View mHeader;
    private boolean mHeaderBeingPressed;
    private int mHeaderBottomPosition;
    private float mHeaderDownY;
    private Integer mHeaderPosition;
    private OnHeaderClickListener mOnHeaderClickListener;
    private android.widget.AbsListView.OnScrollListener mOnScrollListener;
    private android.widget.AbsListView.OnScrollListener mOnScrollListenerDelegate;
    private Field mSelectorPositionField;
    private Rect mSelectorRect;
    private ViewConfiguration mViewConfig;




}
