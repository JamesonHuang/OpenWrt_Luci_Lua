// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package android.support.v4.view:
//            PagerAdapter, ViewCompat, MotionEventCompat, VelocityTrackerCompat, 
//            KeyEventCompat, ViewConfigurationCompat, AccessibilityDelegateCompat

public class ViewPager extends ViewGroup
{
    static class ViewPositionComparator
        implements Comparator
    {

        public int compare(View view, View view1)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
            int i;
            if(layoutparams.isDecor != layoutparams1.isDecor)
            {
                if(layoutparams.isDecor)
                    i = 1;
                else
                    i = -1;
            } else
            {
                i = layoutparams.position - layoutparams1.position;
            }
            return i;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        ViewPositionComparator()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams()
        {
            super(-1, -1);
            widthFactor = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            widthFactor = 0.0F;
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, ViewPager.LAYOUT_ATTRS);
            gravity = typedarray.getInteger(0, 48);
            typedarray.recycle();
        }
    }

    private class PagerObserver extends DataSetObserver
    {

        public void onChanged()
        {
            dataSetChanged();
        }

        public void onInvalidated()
        {
            dataSetChanged();
        }

        final ViewPager this$0;

        private PagerObserver()
        {
            this$0 = ViewPager.this;
            super();
        }

    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private boolean canScroll()
        {
            boolean flag = true;
            if(mAdapter == null || mAdapter.getCount() <= flag)
                flag = false;
            return flag;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/view/ViewPager.getName());
            AccessibilityRecordCompat accessibilityrecordcompat = AccessibilityRecordCompat.obtain();
            accessibilityrecordcompat.setScrollable(canScroll());
            if(accessibilityevent.getEventType() == 4096 && mAdapter != null)
            {
                accessibilityrecordcompat.setItemCount(mAdapter.getCount());
                accessibilityrecordcompat.setFromIndex(mCurItem);
                accessibilityrecordcompat.setToIndex(mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            accessibilitynodeinfocompat.setClassName(android/support/v4/view/ViewPager.getName());
            accessibilitynodeinfocompat.setScrollable(canScroll());
            if(canScrollHorizontally(1))
                accessibilitynodeinfocompat.addAction(4096);
            if(canScrollHorizontally(-1))
                accessibilitynodeinfocompat.addAction(8192);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            boolean flag = true;
            if(!super.performAccessibilityAction(view, i, bundle)) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            switch(i)
            {
            default:
                flag = false;
                break;

            case 4096: 
                if(canScrollHorizontally(flag))
                    setCurrentItem(1 + mCurItem);
                else
                    flag = false;
                break;

            case 8192: 
                if(canScrollHorizontally(-1))
                    setCurrentItem(-1 + mCurItem);
                else
                    flag = false;
                break;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        final ViewPager this$0;

        MyAccessibilityDelegate()
        {
            this$0 = ViewPager.this;
            super();
        }
    }

    public static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("FragmentPager.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(position);
            parcel.writeParcelable(adapterState, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
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
);
        Parcelable adapterState;
        ClassLoader loader;
        int position;


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel);
            if(classloader == null)
                classloader = getClass().getClassLoader();
            position = parcel.readInt();
            adapterState = parcel.readParcelable(classloader);
            loader = classloader;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    static interface Decor
    {
    }

    static interface OnAdapterChangeListener
    {

        public abstract void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1);
    }

    public static interface PageTransformer
    {

        public abstract void transformPage(View view, float f);
    }

    public static class SimpleOnPageChangeListener
        implements OnPageChangeListener
    {

        public void onPageScrollStateChanged(int i)
        {
        }

        public void onPageScrolled(int i, float f, int j)
        {
        }

        public void onPageSelected(int i)
        {
        }

        public SimpleOnPageChangeListener()
        {
        }
    }

    public static interface OnPageChangeListener
    {

        public abstract void onPageScrollStateChanged(int i);

        public abstract void onPageScrolled(int i, float f, int j);

        public abstract void onPageSelected(int i);
    }

    static class ItemInfo
    {

        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo()
        {
        }
    }


    public ViewPager(Context context)
    {
        super(context);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+038F;
        mLastOffset = 3.402823E+038F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new Runnable() {

            public void run()
            {
                setScrollState(0);
                populate();
            }

            final ViewPager this$0;

            
            {
                this$0 = ViewPager.this;
                super();
            }
        }
;
        mScrollState = 0;
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+038F;
        mLastOffset = 3.402823E+038F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new _cls3();
        mScrollState = 0;
        initViewPager();
    }

    private void calculatePageOffsets(ItemInfo iteminfo, int i, ItemInfo iteminfo1)
    {
        int j = mAdapter.getCount();
        int k = getClientWidth();
        float f;
        if(k > 0)
            f = (float)mPageMargin / (float)k;
        else
            f = 0.0F;
        if(iteminfo1 != null)
        {
            int k2 = iteminfo1.position;
            if(k2 < iteminfo.position)
            {
                int j3 = 0;
                float f6 = f + (iteminfo1.offset + iteminfo1.widthFactor);
                for(int k3 = k2 + 1; k3 <= iteminfo.position && j3 < mItems.size(); k3++)
                {
                    ItemInfo iteminfo5;
                    for(iteminfo5 = (ItemInfo)mItems.get(j3); k3 > iteminfo5.position && j3 < -1 + mItems.size(); iteminfo5 = (ItemInfo)mItems.get(j3))
                        j3++;

                    for(; k3 < iteminfo5.position; k3++)
                        f6 += f + mAdapter.getPageWidth(k3);

                    iteminfo5.offset = f6;
                    f6 += f + iteminfo5.widthFactor;
                }

            } else
            if(k2 > iteminfo.position)
            {
                int l2 = -1 + mItems.size();
                float f5 = iteminfo1.offset;
                for(int i3 = k2 - 1; i3 >= iteminfo.position && l2 >= 0; i3--)
                {
                    ItemInfo iteminfo4;
                    for(iteminfo4 = (ItemInfo)mItems.get(l2); i3 < iteminfo4.position && l2 > 0; iteminfo4 = (ItemInfo)mItems.get(l2))
                        l2--;

                    for(; i3 > iteminfo4.position; i3--)
                        f5 -= f + mAdapter.getPageWidth(i3);

                    f5 -= f + iteminfo4.widthFactor;
                    iteminfo4.offset = f5;
                }

            }
        }
        int l = mItems.size();
        float f1 = iteminfo.offset;
        int i1 = -1 + iteminfo.position;
        float f2;
        float f3;
        if(iteminfo.position == 0)
            f2 = iteminfo.offset;
        else
            f2 = -3.402823E+038F;
        mFirstOffset = f2;
        if(iteminfo.position == j - 1)
            f3 = (iteminfo.offset + iteminfo.widthFactor) - 1.0F;
        else
            f3 = 3.402823E+038F;
        mLastOffset = f3;
        for(int j1 = i - 1; j1 >= 0;)
        {
            ItemInfo iteminfo3;
            int j2;
            for(iteminfo3 = (ItemInfo)mItems.get(j1); i1 > iteminfo3.position; i1 = j2)
            {
                PagerAdapter pageradapter1 = mAdapter;
                j2 = i1 - 1;
                f1 -= f + pageradapter1.getPageWidth(i1);
            }

            f1 -= f + iteminfo3.widthFactor;
            iteminfo3.offset = f1;
            if(iteminfo3.position == 0)
                mFirstOffset = f1;
            j1--;
            i1--;
        }

        float f4 = f + (iteminfo.offset + iteminfo.widthFactor);
        int k1 = 1 + iteminfo.position;
        for(int l1 = i + 1; l1 < l;)
        {
            ItemInfo iteminfo2;
            int i2;
            for(iteminfo2 = (ItemInfo)mItems.get(l1); k1 < iteminfo2.position; k1 = i2)
            {
                PagerAdapter pageradapter = mAdapter;
                i2 = k1 + 1;
                f4 += f + pageradapter.getPageWidth(k1);
            }

            if(iteminfo2.position == j - 1)
                mLastOffset = (f4 + iteminfo2.widthFactor) - 1.0F;
            iteminfo2.offset = f4;
            f4 += f + iteminfo2.widthFactor;
            l1++;
            k1++;
        }

        mNeedCalculatePageOffsets = false;
    }

    private void completeScroll(boolean flag)
    {
        boolean flag1;
        if(mScrollState == 2)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            setScrollingCacheEnabled(false);
            mScroller.abortAnimation();
            int j = getScrollX();
            int k = getScrollY();
            int l = mScroller.getCurrX();
            int i1 = mScroller.getCurrY();
            if(j != l || k != i1)
                scrollTo(l, i1);
        }
        mPopulatePending = false;
        for(int i = 0; i < mItems.size(); i++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i);
            if(iteminfo.scrolling)
            {
                flag1 = true;
                iteminfo.scrolling = false;
            }
        }

        if(flag1)
            if(flag)
                ViewCompat.postOnAnimation(this, mEndScrollRunnable);
            else
                mEndScrollRunnable.run();
    }

    private int determineTargetPage(int i, float f, int j, int k)
    {
        int l;
        if(Math.abs(k) > mFlingDistance && Math.abs(j) > mMinimumVelocity)
        {
            if(j > 0)
                l = i;
            else
                l = i + 1;
        } else
        {
            float f1;
            if(i >= mCurItem)
                f1 = 0.4F;
            else
                f1 = 0.6F;
            l = (int)(f1 + (f + (float)i));
        }
        if(mItems.size() > 0)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(0);
            ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
            l = Math.max(iteminfo.position, Math.min(l, iteminfo1.position));
        }
        return l;
    }

    private void enableLayers(boolean flag)
    {
        int i = getChildCount();
        int j = 0;
        while(j < i) 
        {
            byte byte0;
            if(flag)
                byte0 = 2;
            else
                byte0 = 0;
            ViewCompat.setLayerType(getChildAt(j), byte0, null);
            j++;
        }
    }

    private void endDrag()
    {
        mIsBeingDragged = false;
        mIsUnableToDrag = false;
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view)
    {
        if(rect == null)
            rect = new Rect();
        if(view == null)
        {
            rect.set(0, 0, 0, 0);
        } else
        {
            rect.left = view.getLeft();
            rect.right = view.getRight();
            rect.top = view.getTop();
            rect.bottom = view.getBottom();
            ViewParent viewparent = view.getParent();
            while((viewparent instanceof ViewGroup) && viewparent != this) 
            {
                ViewGroup viewgroup = (ViewGroup)viewparent;
                rect.left = rect.left + viewgroup.getLeft();
                rect.right = rect.right + viewgroup.getRight();
                rect.top = rect.top + viewgroup.getTop();
                rect.bottom = rect.bottom + viewgroup.getBottom();
                viewparent = viewgroup.getParent();
            }
        }
        return rect;
    }

    private int getClientWidth()
    {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private ItemInfo infoForCurrentScrollPosition()
    {
        float f = 0.0F;
        int i = getClientWidth();
        float f1;
        int j;
        float f2;
        float f3;
        boolean flag;
        ItemInfo iteminfo;
        int k;
        if(i > 0)
            f1 = (float)getScrollX() / (float)i;
        else
            f1 = 0.0F;
        if(i > 0)
            f = (float)mPageMargin / (float)i;
        j = -1;
        f2 = 0.0F;
        f3 = 0.0F;
        flag = true;
        iteminfo = null;
        k = 0;
        do
        {
            ItemInfo iteminfo1;
            float f4;
label0:
            {
                if(k < mItems.size())
                {
                    iteminfo1 = (ItemInfo)mItems.get(k);
                    if(!flag && iteminfo1.position != j + 1)
                    {
                        iteminfo1 = mTempItem;
                        iteminfo1.offset = f + (f2 + f3);
                        iteminfo1.position = j + 1;
                        iteminfo1.widthFactor = mAdapter.getPageWidth(iteminfo1.position);
                        k--;
                    }
                    f4 = iteminfo1.offset;
                    float f5 = f + (f4 + iteminfo1.widthFactor);
                    if(flag || f1 >= f4)
                    {
                        if(f1 >= f5 && k != -1 + mItems.size())
                            break label0;
                        iteminfo = iteminfo1;
                    }
                }
                return iteminfo;
            }
            flag = false;
            j = iteminfo1.position;
            f2 = f4;
            f3 = iteminfo1.widthFactor;
            iteminfo = iteminfo1;
            k++;
        } while(true);
    }

    private boolean isGutterDrag(float f, float f1)
    {
        boolean flag;
        if(f < (float)mGutterSize && f1 > 0.0F || f > (float)(getWidth() - mGutterSize) && f1 < 0.0F)
            flag = true;
        else
            flag = false;
        return flag;
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
            mLastMotionX = MotionEventCompat.getX(motionevent, j);
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
            if(mVelocityTracker != null)
                mVelocityTracker.clear();
        }
    }

    private boolean pageScrolled(int i)
    {
        boolean flag = false;
        if(mItems.size() == 0)
        {
            mCalledSuper = false;
            onPageScrolled(0, 0.0F, 0);
            if(!mCalledSuper)
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else
        {
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            int j = getClientWidth();
            int k = j + mPageMargin;
            float f = (float)mPageMargin / (float)j;
            int l = iteminfo.position;
            float f1 = ((float)i / (float)j - iteminfo.offset) / (f + iteminfo.widthFactor);
            int i1 = (int)(f1 * (float)k);
            mCalledSuper = false;
            onPageScrolled(l, f1, i1);
            if(!mCalledSuper)
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            flag = true;
        }
        return flag;
    }

    private boolean performDrag(float f)
    {
        boolean flag;
        float f2;
        int i;
        float f3;
        float f4;
        boolean flag1;
        boolean flag2;
        flag = false;
        float f1 = mLastMotionX - f;
        mLastMotionX = f;
        f2 = f1 + (float)getScrollX();
        i = getClientWidth();
        f3 = (float)i * mFirstOffset;
        f4 = (float)i * mLastOffset;
        flag1 = true;
        flag2 = true;
        ItemInfo iteminfo = (ItemInfo)mItems.get(0);
        ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
        if(iteminfo.position != 0)
        {
            flag1 = false;
            f3 = iteminfo.offset * (float)i;
        }
        if(iteminfo1.position != -1 + mAdapter.getCount())
        {
            flag2 = false;
            f4 = iteminfo1.offset * (float)i;
        }
        if(f2 >= f3) goto _L2; else goto _L1
_L1:
        if(flag1)
        {
            float f6 = f3 - f2;
            flag = mLeftEdge.onPull(Math.abs(f6) / (float)i);
        }
        f2 = f3;
_L4:
        mLastMotionX = mLastMotionX + (f2 - (float)(int)f2);
        scrollTo((int)f2, getScrollY());
        pageScrolled((int)f2);
        return flag;
_L2:
        if(f2 > f4)
        {
            if(flag2)
            {
                float f5 = f2 - f4;
                flag = mRightEdge.onPull(Math.abs(f5) / (float)i);
            }
            f2 = f4;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void recomputeScrollPosition(int i, int j, int k, int l)
    {
        if(j <= 0 || mItems.isEmpty()) goto _L2; else goto _L1
_L1:
        int j1 = k + (i - getPaddingLeft() - getPaddingRight());
        int k1 = l + (j - getPaddingLeft() - getPaddingRight());
        int l1 = (int)(((float)getScrollX() / (float)k1) * (float)j1);
        scrollTo(l1, getScrollY());
        if(!mScroller.isFinished())
        {
            int i2 = mScroller.getDuration() - mScroller.timePassed();
            ItemInfo iteminfo1 = infoForPosition(mCurItem);
            mScroller.startScroll(l1, 0, (int)(iteminfo1.offset * (float)i), 0, i2);
        }
_L4:
        return;
_L2:
        ItemInfo iteminfo = infoForPosition(mCurItem);
        float f;
        int i1;
        if(iteminfo != null)
            f = Math.min(iteminfo.offset, mLastOffset);
        else
            f = 0.0F;
        i1 = (int)(f * (float)(i - getPaddingLeft() - getPaddingRight()));
        if(i1 != getScrollX())
        {
            completeScroll(false);
            scrollTo(i1, getScrollY());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void removeNonDecorViews()
    {
        for(int i = 0; i < getChildCount(); i++)
            if(!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor)
            {
                removeViewAt(i);
                i--;
            }

    }

    private void requestParentDisallowInterceptTouchEvent(boolean flag)
    {
        ViewParent viewparent = getParent();
        if(viewparent != null)
            viewparent.requestDisallowInterceptTouchEvent(flag);
    }

    private void scrollToItem(int i, boolean flag, int j, boolean flag1)
    {
        ItemInfo iteminfo = infoForPosition(i);
        int k = 0;
        if(iteminfo != null)
            k = (int)((float)getClientWidth() * Math.max(mFirstOffset, Math.min(iteminfo.offset, mLastOffset)));
        if(flag)
        {
            smoothScrollTo(k, 0, j);
            if(flag1 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(i);
            if(flag1 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(i);
        } else
        {
            if(flag1 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(i);
            if(flag1 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(i);
            completeScroll(false);
            scrollTo(k, 0);
            pageScrolled(k);
        }
    }

    private void setScrollState(int i)
    {
        if(mScrollState != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mScrollState = i;
        if(mPageTransformer != null)
        {
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            enableLayers(flag);
        }
        if(mOnPageChangeListener != null)
            mOnPageChangeListener.onPageScrollStateChanged(i);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void setScrollingCacheEnabled(boolean flag)
    {
        if(mScrollingCacheEnabled != flag)
            mScrollingCacheEnabled = flag;
    }

    private void sortChildDrawingOrder()
    {
        if(mDrawingOrder != 0)
        {
            int i;
            if(mDrawingOrderedChildren == null)
                mDrawingOrderedChildren = new ArrayList();
            else
                mDrawingOrderedChildren.clear();
            i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                mDrawingOrderedChildren.add(view);
            }

            Collections.sort(mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        int k;
        int l;
        k = arraylist.size();
        l = getDescendantFocusability();
        if(l != 0x60000)
        {
            for(int i1 = 0; i1 < getChildCount(); i1++)
            {
                View view = getChildAt(i1);
                if(view.getVisibility() == 0)
                {
                    ItemInfo iteminfo = infoForChild(view);
                    if(iteminfo != null && iteminfo.position == mCurItem)
                        view.addFocusables(arraylist, i, j);
                }
            }

        }
        break MISSING_BLOCK_LABEL_87;
        if((l != 0x40000 || k == arraylist.size()) && isFocusable() && ((j & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arraylist != null)
            arraylist.add(this);
        return;
    }

    ItemInfo addNewItem(int i, int j)
    {
        ItemInfo iteminfo = new ItemInfo();
        iteminfo.position = i;
        iteminfo.object = mAdapter.instantiateItem(this, i);
        iteminfo.widthFactor = mAdapter.getPageWidth(i);
        if(j < 0 || j >= mItems.size())
            mItems.add(iteminfo);
        else
            mItems.add(j, iteminfo);
        return iteminfo;
    }

    public void addTouchables(ArrayList arraylist)
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 0)
                continue;
            ItemInfo iteminfo = infoForChild(view);
            if(iteminfo != null && iteminfo.position == mCurItem)
                view.addTouchables(arraylist);
        }

    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(!checkLayoutParams(layoutparams))
            layoutparams = generateLayoutParams(layoutparams);
        LayoutParams layoutparams1 = (LayoutParams)layoutparams;
        layoutparams1.isDecor = layoutparams1.isDecor | (view instanceof Decor);
        if(mInLayout)
        {
            if(layoutparams1 != null && layoutparams1.isDecor)
                throw new IllegalStateException("Cannot add pager decor view during layout");
            layoutparams1.needsMeasure = true;
            addViewInLayout(view, i, layoutparams);
        } else
        {
            super.addView(view, i, layoutparams);
        }
    }

    public boolean arrowScroll(int i)
    {
        View view = findFocus();
        if(view != this) goto _L2; else goto _L1
_L1:
        view = null;
_L4:
        boolean flag1 = false;
        View view1 = FocusFinder.getInstance().findNextFocus(this, view, i);
        if(view1 != null && view1 != view)
        {
            if(i == 17)
            {
                int l = getChildRectInPagerCoordinates(mTempRect, view1).left;
                int i1 = getChildRectInPagerCoordinates(mTempRect, view).left;
                boolean flag;
                ViewParent viewparent;
                StringBuilder stringbuilder;
                ViewParent viewparent1;
                if(view != null && l >= i1)
                    flag1 = pageLeft();
                else
                    flag1 = view1.requestFocus();
            } else
            if(i == 66)
            {
                int j = getChildRectInPagerCoordinates(mTempRect, view1).left;
                int k = getChildRectInPagerCoordinates(mTempRect, view).left;
                if(view != null && j <= k)
                    flag1 = pageRight();
                else
                    flag1 = view1.requestFocus();
            }
        } else
        if(i == 17 || i == 1)
            flag1 = pageLeft();
        else
        if(i == 66 || i == 2)
            flag1 = pageRight();
        if(flag1)
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        return flag1;
_L2:
        if(view == null) goto _L4; else goto _L3
_L3:
        flag = false;
        viewparent = view.getParent();
_L5:
label0:
        {
            if(viewparent instanceof ViewGroup)
            {
                if(viewparent != this)
                    break label0;
                flag = true;
            }
            if(!flag)
            {
                stringbuilder = new StringBuilder();
                stringbuilder.append(view.getClass().getSimpleName());
                for(viewparent1 = view.getParent(); viewparent1 instanceof ViewGroup; viewparent1 = viewparent1.getParent())
                    stringbuilder.append(" => ").append(viewparent1.getClass().getSimpleName());

                break MISSING_BLOCK_LABEL_217;
            }
        }
          goto _L4
        viewparent = viewparent.getParent();
          goto _L5
        Log.e("ViewPager", (new StringBuilder()).append("arrowScroll tried to find focus based on non-child current focused view ").append(stringbuilder.toString()).toString());
        view = null;
          goto _L4
    }

    public boolean beginFakeDrag()
    {
        boolean flag = false;
        if(!mIsBeingDragged)
        {
            mFakeDragging = true;
            setScrollState(1);
            mLastMotionX = 0.0F;
            mInitialMotionX = 0.0F;
            long l;
            MotionEvent motionevent;
            if(mVelocityTracker == null)
                mVelocityTracker = VelocityTracker.obtain();
            else
                mVelocityTracker.clear();
            l = SystemClock.uptimeMillis();
            motionevent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
            mVelocityTracker.addMovement(motionevent);
            motionevent.recycle();
            mFakeDragBeginTime = l;
            flag = true;
        }
        return flag;
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

    public boolean canScrollHorizontally(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        if(mAdapter != null) goto _L2; else goto _L1
_L1:
        return flag1;
_L2:
        int j = getClientWidth();
        int k = getScrollX();
        if(i < 0)
        {
            if(k <= (int)((float)j * mFirstOffset))
                flag = false;
            flag1 = flag;
        } else
        if(i > 0)
        {
            if(k >= (int)((float)j * mLastOffset))
                flag = false;
            flag1 = flag;
        }
        if(true) goto _L1; else goto _L3
_L3:
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

    public void computeScroll()
    {
        if(!mScroller.isFinished() && mScroller.computeScrollOffset())
        {
            int i = getScrollX();
            int j = getScrollY();
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if(i != k || j != l)
            {
                scrollTo(k, l);
                if(!pageScrolled(k))
                {
                    mScroller.abortAnimation();
                    scrollTo(0, l);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
        } else
        {
            completeScroll(true);
        }
    }

    void dataSetChanged()
    {
        int i = mAdapter.getCount();
        mExpectedAdapterCount = i;
        boolean flag;
        int j;
        boolean flag1;
        int k;
        if(mItems.size() < 1 + 2 * mOffscreenPageLimit && mItems.size() < i)
            flag = true;
        else
            flag = false;
        j = mCurItem;
        flag1 = false;
        k = 0;
        while(k < mItems.size()) 
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(k);
            int j1 = mAdapter.getItemPosition(iteminfo.object);
            if(j1 != -1)
                if(j1 == -2)
                {
                    mItems.remove(k);
                    k--;
                    if(!flag1)
                    {
                        mAdapter.startUpdate(this);
                        flag1 = true;
                    }
                    mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
                    flag = true;
                    if(mCurItem == iteminfo.position)
                    {
                        j = Math.max(0, Math.min(mCurItem, i - 1));
                        flag = true;
                    }
                } else
                if(iteminfo.position != j1)
                {
                    if(iteminfo.position == mCurItem)
                        j = j1;
                    iteminfo.position = j1;
                    flag = true;
                }
            k++;
        }
        if(flag1)
            mAdapter.finishUpdate(this);
        Collections.sort(mItems, COMPARATOR);
        if(flag)
        {
            int l = getChildCount();
            for(int i1 = 0; i1 < l; i1++)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(i1).getLayoutParams();
                if(!layoutparams.isDecor)
                    layoutparams.widthFactor = 0.0F;
            }

            setCurrentItemInternal(j, false, true);
            requestLayout();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        if(super.dispatchKeyEvent(keyevent) || executeKeyEvent(keyevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if(accessibilityevent.getEventType() != 4096) goto _L2; else goto _L1
_L1:
        boolean flag = super.dispatchPopulateAccessibilityEvent(accessibilityevent);
_L4:
        return flag;
_L2:
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.getVisibility() != 0)
                continue;
            ItemInfo iteminfo = infoForChild(view);
            if(iteminfo == null || iteminfo.position != mCurItem || !view.dispatchPopulateAccessibilityEvent(accessibilityevent))
                continue;
            flag = true;
            continue; /* Loop/switch isn't completed */
        }

        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    float distanceInfluenceForSnapDuration(float f)
    {
        return (float)Math.sin((float)(0.4712389167638204D * (double)(f - 0.5F)));
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        boolean flag = false;
        int i = ViewCompat.getOverScrollMode(this);
        if(i == 0 || i == 1 && mAdapter != null && mAdapter.getCount() > 1)
        {
            if(!mLeftEdge.isFinished())
            {
                int i1 = canvas.save();
                int j1 = getHeight() - getPaddingTop() - getPaddingBottom();
                int k1 = getWidth();
                canvas.rotate(270F);
                canvas.translate(-j1 + getPaddingTop(), mFirstOffset * (float)k1);
                mLeftEdge.setSize(j1, k1);
                flag = false | mLeftEdge.draw(canvas);
                canvas.restoreToCount(i1);
            }
            if(!mRightEdge.isFinished())
            {
                int j = canvas.save();
                int k = getWidth();
                int l = getHeight() - getPaddingTop() - getPaddingBottom();
                canvas.rotate(90F);
                canvas.translate(-getPaddingTop(), -(1.0F + mLastOffset) * (float)k);
                mRightEdge.setSize(l, k);
                flag |= mRightEdge.draw(canvas);
                canvas.restoreToCount(j);
            }
        } else
        {
            mLeftEdge.finish();
            mRightEdge.finish();
        }
        if(flag)
            ViewCompat.postInvalidateOnAnimation(this);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mMarginDrawable;
        if(drawable != null && drawable.isStateful())
            drawable.setState(getDrawableState());
    }

    public void endFakeDrag()
    {
        if(!mFakeDragging)
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        } else
        {
            VelocityTracker velocitytracker = mVelocityTracker;
            velocitytracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int i = (int)VelocityTrackerCompat.getXVelocity(velocitytracker, mActivePointerId);
            mPopulatePending = true;
            int j = getClientWidth();
            int k = getScrollX();
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(iteminfo.position, ((float)k / (float)j - iteminfo.offset) / iteminfo.widthFactor, i, (int)(mLastMotionX - mInitialMotionX)), true, true, i);
            endDrag();
            mFakeDragging = false;
            return;
        }
    }

    public boolean executeKeyEvent(KeyEvent keyevent)
    {
        boolean flag = false;
        if(keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 3: default 48
    //                   21: 50
    //                   22: 60
    //                   61: 70;
           goto _L2 _L3 _L4 _L5
_L2:
        return flag;
_L3:
        flag = arrowScroll(17);
        continue; /* Loop/switch isn't completed */
_L4:
        flag = arrowScroll(66);
        continue; /* Loop/switch isn't completed */
_L5:
        if(android.os.Build.VERSION.SDK_INT >= 11)
            if(KeyEventCompat.hasNoModifiers(keyevent))
                flag = arrowScroll(2);
            else
            if(KeyEventCompat.hasModifiers(keyevent, 1))
                flag = arrowScroll(1);
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void fakeDragBy(float f)
    {
        float f1;
        float f2;
        float f3;
        if(!mFakeDragging)
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        mLastMotionX = f + mLastMotionX;
        f1 = (float)getScrollX() - f;
        int i = getClientWidth();
        f2 = (float)i * mFirstOffset;
        f3 = (float)i * mLastOffset;
        ItemInfo iteminfo = (ItemInfo)mItems.get(0);
        ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
        if(iteminfo.position != 0)
            f2 = iteminfo.offset * (float)i;
        if(iteminfo1.position != -1 + mAdapter.getCount())
            f3 = iteminfo1.offset * (float)i;
        if(f1 >= f2) goto _L2; else goto _L1
_L1:
        f1 = f2;
_L4:
        mLastMotionX = mLastMotionX + (f1 - (float)(int)f1);
        scrollTo((int)f1, getScrollY());
        pageScrolled((int)f1);
        long l = SystemClock.uptimeMillis();
        MotionEvent motionevent = MotionEvent.obtain(mFakeDragBeginTime, l, 2, mLastMotionX, 0.0F, 0);
        mVelocityTracker.addMovement(motionevent);
        motionevent.recycle();
        return;
_L2:
        if(f1 > f3)
            f1 = f3;
        if(true) goto _L4; else goto _L3
_L3:
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
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter()
    {
        return mAdapter;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        int k;
        if(mDrawingOrder == 2)
            k = i - 1 - j;
        else
            k = j;
        return ((LayoutParams)((View)mDrawingOrderedChildren.get(k)).getLayoutParams()).childIndex;
    }

    public int getCurrentItem()
    {
        return mCurItem;
    }

    public int getOffscreenPageLimit()
    {
        return mOffscreenPageLimit;
    }

    public int getPageMargin()
    {
        return mPageMargin;
    }

    ItemInfo infoForAnyChild(View view)
    {
_L3:
        ViewParent viewparent;
        viewparent = view.getParent();
        if(viewparent == this)
            break MISSING_BLOCK_LABEL_33;
        if(viewparent != null && (viewparent instanceof View)) goto _L2; else goto _L1
_L1:
        ItemInfo iteminfo = null;
_L4:
        return iteminfo;
_L2:
        view = (View)viewparent;
          goto _L3
        iteminfo = infoForChild(view);
          goto _L4
    }

    ItemInfo infoForChild(View view)
    {
        int i = 0;
_L3:
        ItemInfo iteminfo;
        if(i >= mItems.size())
            break MISSING_BLOCK_LABEL_48;
        iteminfo = (ItemInfo)mItems.get(i);
        if(!mAdapter.isViewFromObject(view, iteminfo.object)) goto _L2; else goto _L1
_L1:
        return iteminfo;
_L2:
        i++;
          goto _L3
        iteminfo = null;
          goto _L1
    }

    ItemInfo infoForPosition(int i)
    {
        int j = 0;
_L3:
        ItemInfo iteminfo;
        if(j >= mItems.size())
            break MISSING_BLOCK_LABEL_41;
        iteminfo = (ItemInfo)mItems.get(j);
        if(iteminfo.position != i) goto _L2; else goto _L1
_L1:
        return iteminfo;
_L2:
        j++;
          goto _L3
        iteminfo = null;
          goto _L1
    }

    void initViewPager()
    {
        setWillNotDraw(false);
        setDescendantFocusability(0x40000);
        setFocusable(true);
        Context context = getContext();
        mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewconfiguration);
        mMinimumVelocity = (int)(400F * f);
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mLeftEdge = new EdgeEffectCompat(context);
        mRightEdge = new EdgeEffectCompat(context);
        mFlingDistance = (int)(25F * f);
        mCloseEnough = (int)(2.0F * f);
        mDefaultGutterSize = (int)(16F * f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if(ViewCompat.getImportantForAccessibility(this) == 0)
            ViewCompat.setImportantForAccessibility(this, 1);
    }

    public boolean isFakeDragging()
    {
        return mFakeDragging;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        removeCallbacks(mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mPageMargin <= 0 || mMarginDrawable == null || mItems.size() <= 0 || mAdapter == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        float f;
        int k;
        ItemInfo iteminfo;
        float f1;
        int l;
        int j1;
        int k1;
        i = getScrollX();
        j = getWidth();
        f = (float)mPageMargin / (float)j;
        k = 0;
        iteminfo = (ItemInfo)mItems.get(0);
        f1 = iteminfo.offset;
        l = mItems.size();
        int i1 = iteminfo.position;
        j1 = ((ItemInfo)mItems.get(l - 1)).position;
        k1 = i1;
_L6:
        if(k1 >= j1) goto _L2; else goto _L3
_L3:
        ArrayList arraylist;
        for(; k1 > iteminfo.position && k < l; iteminfo = (ItemInfo)arraylist.get(k))
        {
            arraylist = mItems;
            k++;
        }

        float f3;
        if(k1 == iteminfo.position)
        {
            f3 = (iteminfo.offset + iteminfo.widthFactor) * (float)j;
            f1 = f + (iteminfo.offset + iteminfo.widthFactor);
        } else
        {
            float f2 = mAdapter.getPageWidth(k1);
            f3 = (f1 + f2) * (float)j;
            f1 += f2 + f;
        }
        if(f3 + (float)mPageMargin > (float)i)
        {
            mMarginDrawable.setBounds((int)f3, mTopPageBounds, (int)(0.5F + (f3 + (float)mPageMargin)), mBottomPageBounds);
            mMarginDrawable.draw(canvas);
        }
        if(f3 <= (float)(i + j)) goto _L4; else goto _L2
_L2:
        return;
_L4:
        k1++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        i = 0xff & motionevent.getAction();
        if(i == 3 || i == 1)
        {
            mIsBeingDragged = false;
            mIsUnableToDrag = false;
            mActivePointerId = -1;
            if(mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
            flag = false;
        } else
        {
label0:
            {
                if(i == 0)
                    break label0;
                if(mIsBeingDragged)
                {
                    flag = true;
                } else
                {
                    if(!mIsUnableToDrag)
                        break label0;
                    flag = false;
                }
            }
        }
_L5:
        return flag;
        i;
        JVM INSTR lookupswitch 3: default 120
    //                   0: 406
    //                   2: 150
    //                   6: 544;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_544;
_L7:
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        flag = mIsBeingDragged;
          goto _L5
_L3:
        int j = mActivePointerId;
        if(j == -1) goto _L7; else goto _L6
_L6:
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
label1:
        {
            int k = MotionEventCompat.findPointerIndex(motionevent, j);
            f2 = MotionEventCompat.getX(motionevent, k);
            f3 = f2 - mLastMotionX;
            f4 = Math.abs(f3);
            f5 = MotionEventCompat.getY(motionevent, k);
            f6 = Math.abs(f5 - mInitialMotionY);
            if(f3 == 0.0F || isGutterDrag(mLastMotionX, f3) || !canScroll(this, false, (int)f3, (int)f2, (int)f5))
                break label1;
            mLastMotionX = f2;
            mLastMotionY = f5;
            mIsUnableToDrag = true;
            flag = false;
        }
          goto _L5
        if(f4 > (float)mTouchSlop && 0.5F * f4 > f6)
        {
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
            float f7;
            if(f3 > 0.0F)
                f7 = mInitialMotionX + (float)mTouchSlop;
            else
                f7 = mInitialMotionX - (float)mTouchSlop;
            mLastMotionX = f7;
            mLastMotionY = f5;
            setScrollingCacheEnabled(true);
        } else
        if(f6 > (float)mTouchSlop)
            mIsUnableToDrag = true;
        if(mIsBeingDragged && performDrag(f2))
            ViewCompat.postInvalidateOnAnimation(this);
          goto _L7
_L2:
        float f = motionevent.getX();
        mInitialMotionX = f;
        mLastMotionX = f;
        float f1 = motionevent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mIsUnableToDrag = false;
        mScroller.computeScrollOffset();
        if(mScrollState == 2 && Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough)
        {
            mScroller.abortAnimation();
            mPopulatePending = false;
            populate();
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
        } else
        {
            completeScroll(false);
            mIsBeingDragged = false;
        }
          goto _L7
        onSecondaryPointerUp(motionevent);
          goto _L7
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        i1 = getChildCount();
        j1 = k - i;
        k1 = l - j;
        l1 = getPaddingLeft();
        i2 = getPaddingTop();
        j2 = getPaddingRight();
        k2 = getPaddingBottom();
        l2 = getScrollX();
        i3 = 0;
        j3 = 0;
_L17:
        if(j3 >= i1) goto _L2; else goto _L1
_L1:
        View view1 = getChildAt(j3);
        if(view1.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if(!layoutparams1.isDecor) goto _L4; else goto _L5
_L5:
        int k4;
        int l4;
        k4 = 7 & layoutparams1.gravity;
        l4 = 0x70 & layoutparams1.gravity;
        k4;
        JVM INSTR tableswitch 1 5: default 152
    //                   1 254
    //                   2 152
    //                   3 237
    //                   4 152
    //                   5 274;
           goto _L6 _L7 _L6 _L8 _L6 _L9
_L6:
        int i5 = l1;
_L14:
        l4;
        JVM INSTR lookupswitch 3: default 192
    //                   16: 317
    //                   48: 300
    //                   80: 337;
           goto _L10 _L11 _L12 _L13
_L10:
        int j5 = i2;
_L15:
        int k5 = i5 + l2;
        view1.layout(k5, j5, k5 + view1.getMeasuredWidth(), j5 + view1.getMeasuredHeight());
        i3++;
_L4:
        j3++;
        continue; /* Loop/switch isn't completed */
_L8:
        i5 = l1;
        l1 += view1.getMeasuredWidth();
          goto _L14
_L7:
        i5 = Math.max((j1 - view1.getMeasuredWidth()) / 2, l1);
          goto _L14
_L9:
        i5 = j1 - j2 - view1.getMeasuredWidth();
        j2 += view1.getMeasuredWidth();
          goto _L14
_L12:
        j5 = i2;
        i2 += view1.getMeasuredHeight();
          goto _L15
_L11:
        j5 = Math.max((k1 - view1.getMeasuredHeight()) / 2, i2);
          goto _L15
_L13:
        j5 = k1 - k2 - view1.getMeasuredHeight();
        k2 += view1.getMeasuredHeight();
          goto _L15
_L2:
        int k3 = j1 - l1 - j2;
        for(int l3 = 0; l3 < i1; l3++)
        {
            View view = getChildAt(l3);
            if(view.getVisibility() == 8)
                continue;
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(layoutparams.isDecor)
                continue;
            ItemInfo iteminfo = infoForChild(view);
            if(iteminfo == null)
                continue;
            int i4 = l1 + (int)((float)k3 * iteminfo.offset);
            int j4 = i2;
            if(layoutparams.needsMeasure)
            {
                layoutparams.needsMeasure = false;
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)k3 * layoutparams.widthFactor), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k1 - i2 - k2, 0x40000000));
            }
            view.layout(i4, j4, i4 + view.getMeasuredWidth(), j4 + view.getMeasuredHeight());
        }

        mTopPageBounds = i2;
        mBottomPageBounds = k1 - k2;
        mDecorChildCount = i3;
        if(mFirstLayout)
            scrollToItem(mCurItem, false, 0, false);
        mFirstLayout = false;
        return;
        if(true) goto _L17; else goto _L16
_L16:
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, j));
        int k = getMeasuredWidth();
        mGutterSize = Math.min(k / 10, mDefaultGutterSize);
        int l = k - getPaddingLeft() - getPaddingRight();
        int i1 = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int j1 = getChildCount();
        int k1 = 0;
        do
        {
            if(k1 < j1)
            {
                View view1 = getChildAt(k1);
                if(view1.getVisibility() != 8)
                {
                    LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                    if(layoutparams1 != null && layoutparams1.isDecor)
                    {
                        int j2 = 7 & layoutparams1.gravity;
                        int k2 = 0x70 & layoutparams1.gravity;
                        int l2 = 0x80000000;
                        int i3 = 0x80000000;
                        boolean flag;
                        boolean flag1;
                        int j3;
                        int k3;
                        if(k2 == 48 || k2 == 80)
                            flag = true;
                        else
                            flag = false;
                        if(j2 == 3 || j2 == 5)
                            flag1 = true;
                        else
                            flag1 = false;
                        if(flag)
                            l2 = 0x40000000;
                        else
                        if(flag1)
                            i3 = 0x40000000;
                        j3 = l;
                        k3 = i1;
                        if(layoutparams1.width != -2)
                        {
                            l2 = 0x40000000;
                            if(layoutparams1.width != -1)
                                j3 = layoutparams1.width;
                        }
                        if(layoutparams1.height != -2)
                        {
                            i3 = 0x40000000;
                            if(layoutparams1.height != -1)
                                k3 = layoutparams1.height;
                        }
                        view1.measure(android.view.View.MeasureSpec.makeMeasureSpec(j3, l2), android.view.View.MeasureSpec.makeMeasureSpec(k3, i3));
                        if(flag)
                            i1 -= view1.getMeasuredHeight();
                        else
                        if(flag1)
                            l -= view1.getMeasuredWidth();
                    }
                }
                k1++;
                continue;
            }
            mChildWidthMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000);
            mChildHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000);
            mInLayout = true;
            populate();
            mInLayout = false;
            int l1 = getChildCount();
            for(int i2 = 0; i2 < l1; i2++)
            {
                View view = getChildAt(i2);
                if(view.getVisibility() == 8)
                    continue;
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if(layoutparams == null || !layoutparams.isDecor)
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)l * layoutparams.widthFactor), 0x40000000), mChildHeightMeasureSpec);
            }

            return;
        } while(true);
    }

    protected void onPageScrolled(int i, float f, int j)
    {
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        if(mDecorChildCount <= 0)
            break MISSING_BLOCK_LABEL_215;
        j1 = getScrollX();
        k1 = getPaddingLeft();
        l1 = getPaddingRight();
        i2 = getWidth();
        j2 = getChildCount();
        k2 = 0;
_L2:
        View view1;
        LayoutParams layoutparams;
        if(k2 >= j2)
            break MISSING_BLOCK_LABEL_215;
        view1 = getChildAt(k2);
        layoutparams = (LayoutParams)view1.getLayoutParams();
        if(layoutparams.isDecor)
            break; /* Loop/switch isn't completed */
_L7:
        k2++;
        if(true) goto _L2; else goto _L1
_L1:
        7 & layoutparams.gravity;
        JVM INSTR tableswitch 1 5: default 120
    //                   1 169
    //                   2 120
    //                   3 152
    //                   4 120
    //                   5 189;
           goto _L3 _L4 _L3 _L5 _L3 _L6
_L6:
        break MISSING_BLOCK_LABEL_189;
_L3:
        int l2 = k1;
_L8:
        int i3 = (l2 + j1) - view1.getLeft();
        if(i3 != 0)
            view1.offsetLeftAndRight(i3);
          goto _L7
_L5:
        l2 = k1;
        k1 += view1.getWidth();
          goto _L8
_L4:
        l2 = Math.max((i2 - view1.getMeasuredWidth()) / 2, k1);
          goto _L8
        l2 = i2 - l1 - view1.getMeasuredWidth();
        l1 += view1.getMeasuredWidth();
          goto _L8
        if(mOnPageChangeListener != null)
            mOnPageChangeListener.onPageScrolled(i, f, j);
        if(mInternalPageChangeListener != null)
            mInternalPageChangeListener.onPageScrolled(i, f, j);
        if(mPageTransformer != null)
        {
            int k = getScrollX();
            int l = getChildCount();
            int i1 = 0;
            while(i1 < l) 
            {
                View view = getChildAt(i1);
                if(!((LayoutParams)view.getLayoutParams()).isDecor)
                {
                    float f1 = (float)(view.getLeft() - k) / (float)getClientWidth();
                    mPageTransformer.transformPage(view, f1);
                }
                i1++;
            }
        }
        mCalledSuper = true;
        return;
          goto _L7
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        byte byte0;
        int i1;
        boolean flag;
        int j = getChildCount();
        int k;
        int l;
        View view;
        ItemInfo iteminfo;
        if((i & 2) != 0)
        {
            k = 0;
            byte0 = 1;
            l = j;
        } else
        {
            k = j - 1;
            byte0 = -1;
            l = -1;
        }
        i1 = k;
_L3:
        if(i1 == l) goto _L2; else goto _L1
_L1:
        view = getChildAt(i1);
        if(view.getVisibility() != 0)
            continue; /* Loop/switch isn't completed */
        iteminfo = infoForChild(view);
        if(iteminfo == null || iteminfo.position != mCurItem || !view.requestFocus(i, rect))
            continue; /* Loop/switch isn't completed */
        flag = true;
_L4:
        return flag;
        i1 += byte0;
          goto _L3
_L2:
        flag = false;
          goto _L4
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            SavedState savedstate = (SavedState)parcelable;
            super.onRestoreInstanceState(savedstate.getSuperState());
            if(mAdapter != null)
            {
                mAdapter.restoreState(savedstate.adapterState, savedstate.loader);
                setCurrentItemInternal(savedstate.position, false, true);
            } else
            {
                mRestoredCurItem = savedstate.position;
                mRestoredAdapterState = savedstate.adapterState;
                mRestoredClassLoader = savedstate.loader;
            }
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.position = mCurItem;
        if(mAdapter != null)
            savedstate.adapterState = mAdapter.saveState();
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(i != k)
            recomputeScrollPosition(i, k, mPageMargin, mPageMargin);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mFakeDragging) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L11:
        return flag;
_L2:
        int i;
        boolean flag1;
        if(motionevent.getAction() == 0 && motionevent.getEdgeFlags() != 0)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(mAdapter == null || mAdapter.getCount() == 0)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        i = motionevent.getAction();
        flag1 = false;
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 128
    //                   0 142
    //                   1 402
    //                   2 206
    //                   3 549
    //                   4 128
    //                   5 597
    //                   6 626;
           goto _L3 _L4 _L5 _L6 _L7 _L3 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_626;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L12:
        if(flag1)
            ViewCompat.postInvalidateOnAnimation(this);
        flag = true;
        if(true) goto _L11; else goto _L10
_L10:
        mScroller.abortAnimation();
        mPopulatePending = false;
        populate();
        float f5 = motionevent.getX();
        mInitialMotionX = f5;
        mLastMotionX = f5;
        float f6 = motionevent.getY();
        mInitialMotionY = f6;
        mLastMotionY = f6;
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
          goto _L12
_L6:
        if(!mIsBeingDragged)
        {
            int j1 = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
            float f = MotionEventCompat.getX(motionevent, j1);
            float f1 = Math.abs(f - mLastMotionX);
            float f2 = MotionEventCompat.getY(motionevent, j1);
            float f3 = Math.abs(f2 - mLastMotionY);
            if(f1 > (float)mTouchSlop && f1 > f3)
            {
                mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                float f4;
                ViewParent viewparent;
                if(f - mInitialMotionX > 0.0F)
                    f4 = mInitialMotionX + (float)mTouchSlop;
                else
                    f4 = mInitialMotionX - (float)mTouchSlop;
                mLastMotionX = f4;
                mLastMotionY = f2;
                setScrollState(1);
                setScrollingCacheEnabled(true);
                viewparent = getParent();
                if(viewparent != null)
                    viewparent.requestDisallowInterceptTouchEvent(true);
            }
        }
        if(mIsBeingDragged)
            flag1 = false | performDrag(MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId)));
          goto _L12
_L5:
        if(mIsBeingDragged)
        {
            VelocityTracker velocitytracker = mVelocityTracker;
            velocitytracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int k = (int)VelocityTrackerCompat.getXVelocity(velocitytracker, mActivePointerId);
            mPopulatePending = true;
            int l = getClientWidth();
            int i1 = getScrollX();
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(iteminfo.position, ((float)i1 / (float)l - iteminfo.offset) / iteminfo.widthFactor, k, (int)(MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId)) - mInitialMotionX)), true, true, k);
            mActivePointerId = -1;
            endDrag();
            flag1 = mLeftEdge.onRelease() | mRightEdge.onRelease();
        }
          goto _L12
_L7:
        if(mIsBeingDragged)
        {
            scrollToItem(mCurItem, true, 0, false);
            mActivePointerId = -1;
            endDrag();
            flag1 = mLeftEdge.onRelease() | mRightEdge.onRelease();
        }
          goto _L12
_L8:
        int j = MotionEventCompat.getActionIndex(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, j);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
          goto _L12
        onSecondaryPointerUp(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
          goto _L12
    }

    boolean pageLeft()
    {
        boolean flag = true;
        if(mCurItem > 0)
            setCurrentItem(-1 + mCurItem, flag);
        else
            flag = false;
        return flag;
    }

    boolean pageRight()
    {
        boolean flag = true;
        if(mAdapter != null && mCurItem < -1 + mAdapter.getCount())
            setCurrentItem(1 + mCurItem, flag);
        else
            flag = false;
        return flag;
    }

    void populate()
    {
        populate(mCurItem);
    }

    void populate(int i)
    {
        ItemInfo iteminfo;
        byte byte0;
        iteminfo = null;
        byte0 = 2;
        if(mCurItem != i)
        {
            if(mCurItem < i)
                byte0 = 66;
            else
                byte0 = 17;
            iteminfo = infoForPosition(mCurItem);
            mCurItem = i;
        }
        if(mAdapter != null) goto _L2; else goto _L1
_L1:
        sortChildDrawingOrder();
_L4:
        return;
_L2:
        if(!mPopulatePending)
            break; /* Loop/switch isn't completed */
        sortChildDrawingOrder();
        if(true) goto _L4; else goto _L3
_L3:
        if(getWindowToken() == null) goto _L4; else goto _L5
_L5:
        int k;
        int l;
        int i1;
        mAdapter.startUpdate(this);
        int j = mOffscreenPageLimit;
        k = Math.max(0, mCurItem - j);
        l = mAdapter.getCount();
        i1 = Math.min(l - 1, j + mCurItem);
        if(l == mExpectedAdapterCount) goto _L7; else goto _L6
_L6:
        String s1 = getResources().getResourceName(getId());
        String s = s1;
_L8:
        throw new IllegalStateException((new StringBuilder()).append("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ").append(mExpectedAdapterCount).append(", found: ").append(l).append(" Pager id: ").append(s).append(" Pager class: ").append(getClass()).append(" Problematic adapter: ").append(mAdapter.getClass()).toString());
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
        s = Integer.toHexString(getId());
        if(true) goto _L8; else goto _L7
_L7:
        ItemInfo iteminfo1;
        int j1;
        iteminfo1 = null;
        j1 = 0;
_L26:
        if(j1 >= mItems.size()) goto _L10; else goto _L9
_L9:
        ItemInfo iteminfo8 = (ItemInfo)mItems.get(j1);
        if(iteminfo8.position < mCurItem) goto _L12; else goto _L11
_L11:
        if(iteminfo8.position == mCurItem)
            iteminfo1 = iteminfo8;
_L10:
        if(iteminfo1 == null && l > 0)
            iteminfo1 = addNewItem(mCurItem, j1);
        if(iteminfo1 == null) goto _L14; else goto _L13
_L13:
        float f;
        int l2;
        ItemInfo iteminfo5;
        int i3;
        int j3;
        float f2;
        int k3;
        ItemInfo iteminfo6;
        int i4;
        f = 0.0F;
        l2 = j1 - 1;
        PagerAdapter pageradapter;
        int k1;
        int l1;
        int i2;
        View view2;
        LayoutParams layoutparams;
        ItemInfo iteminfo4;
        float f1;
        int l3;
        if(l2 >= 0)
            iteminfo5 = (ItemInfo)mItems.get(l2);
        else
            iteminfo5 = null;
        i3 = getClientWidth();
        if(i3 <= 0)
            f1 = 0.0F;
        else
            f1 = (2.0F - iteminfo1.widthFactor) + (float)getPaddingLeft() / (float)i3;
        j3 = -1 + mCurItem;
_L27:
        if(j3 < 0) goto _L16; else goto _L15
_L15:
        if(f < f1 || j3 >= k) goto _L18; else goto _L17
_L17:
        if(iteminfo5 != null) goto _L19; else goto _L16
_L16:
        f2 = iteminfo1.widthFactor;
        k3 = j1 + 1;
        if(f2 >= 2.0F) goto _L21; else goto _L20
_L20:
        l3 = mItems.size();
        float f3;
        int k5;
        int l5;
        int i6;
        PagerAdapter pageradapter2;
        Object obj2;
        if(k3 < l3)
            iteminfo6 = (ItemInfo)mItems.get(k3);
        else
            iteminfo6 = null;
        if(i3 <= 0)
            f3 = 0.0F;
        else
            f3 = 2.0F + (float)getPaddingRight() / (float)i3;
        i4 = 1 + mCurItem;
_L29:
        if(i4 >= l) goto _L21; else goto _L22
_L22:
        if(f2 < f3 || i4 <= i1) goto _L24; else goto _L23
_L23:
        if(iteminfo6 != null) goto _L25; else goto _L21
_L21:
        calculatePageOffsets(iteminfo1, j1, iteminfo);
_L14:
        pageradapter = mAdapter;
        k1 = mCurItem;
        Object obj;
        ItemInfo iteminfo7;
        int j4;
        int k4;
        int l4;
        int i5;
        PagerAdapter pageradapter1;
        Object obj1;
        int j5;
        if(iteminfo1 != null)
            obj = iteminfo1.object;
        else
            obj = null;
        pageradapter.setPrimaryItem(this, k1, obj);
        mAdapter.finishUpdate(this);
        l1 = getChildCount();
        for(i2 = 0; i2 < l1; i2++)
        {
            view2 = getChildAt(i2);
            layoutparams = (LayoutParams)view2.getLayoutParams();
            layoutparams.childIndex = i2;
            if(!layoutparams.isDecor && layoutparams.widthFactor == 0.0F)
            {
                iteminfo4 = infoForChild(view2);
                if(iteminfo4 != null)
                {
                    layoutparams.widthFactor = iteminfo4.widthFactor;
                    layoutparams.position = iteminfo4.position;
                }
            }
        }

        break MISSING_BLOCK_LABEL_1152;
_L12:
        j1++;
          goto _L26
_L19:
        i6 = iteminfo5.position;
        if(j3 == i6 && !iteminfo5.scrolling)
        {
            mItems.remove(l2);
            pageradapter2 = mAdapter;
            obj2 = iteminfo5.object;
            pageradapter2.destroyItem(this, j3, obj2);
            l2--;
            j1--;
            if(l2 >= 0)
                iteminfo5 = (ItemInfo)mItems.get(l2);
            else
                iteminfo5 = null;
        }
_L28:
        j3--;
          goto _L27
_L18:
label0:
        {
            if(iteminfo5 == null)
                break label0;
            l5 = iteminfo5.position;
            if(j3 != l5)
                break label0;
            f += iteminfo5.widthFactor;
            if(--l2 >= 0)
                iteminfo5 = (ItemInfo)mItems.get(l2);
            else
                iteminfo5 = null;
        }
          goto _L28
        k5 = l2 + 1;
        f += addNewItem(j3, k5).widthFactor;
        j1++;
        if(l2 >= 0)
            iteminfo5 = (ItemInfo)mItems.get(l2);
        else
            iteminfo5 = null;
          goto _L28
_L25:
        i5 = iteminfo6.position;
        if(i4 == i5 && !iteminfo6.scrolling)
        {
            mItems.remove(k3);
            pageradapter1 = mAdapter;
            obj1 = iteminfo6.object;
            pageradapter1.destroyItem(this, i4, obj1);
            j5 = mItems.size();
            if(k3 < j5)
                iteminfo6 = (ItemInfo)mItems.get(k3);
            else
                iteminfo6 = null;
        }
_L30:
        i4++;
          goto _L29
_L24:
label1:
        {
            if(iteminfo6 == null)
                break label1;
            k4 = iteminfo6.position;
            if(i4 != k4)
                break label1;
            f2 += iteminfo6.widthFactor;
            k3++;
            l4 = mItems.size();
            if(k3 < l4)
                iteminfo6 = (ItemInfo)mItems.get(k3);
            else
                iteminfo6 = null;
        }
          goto _L30
        iteminfo7 = addNewItem(i4, k3);
        k3++;
        f2 += iteminfo7.widthFactor;
        j4 = mItems.size();
        if(k3 < j4)
            iteminfo6 = (ItemInfo)mItems.get(k3);
        else
            iteminfo6 = null;
          goto _L30
        sortChildDrawingOrder();
        if(!hasFocus()) goto _L4; else goto _L31
_L31:
        View view = findFocus();
        ItemInfo iteminfo2;
        int j2;
        int k2;
        View view1;
        ItemInfo iteminfo3;
        if(view != null)
            iteminfo2 = infoForAnyChild(view);
        else
            iteminfo2 = null;
        if(iteminfo2 != null && iteminfo2.position == mCurItem) goto _L4; else goto _L32
_L32:
        j2 = 0;
_L35:
        k2 = getChildCount();
        if(j2 >= k2) goto _L4; else goto _L33
_L33:
        view1 = getChildAt(j2);
        iteminfo3 = infoForChild(view1);
        if(iteminfo3 != null && iteminfo3.position == mCurItem && view1.requestFocus(byte0)) goto _L4; else goto _L34
_L34:
        j2++;
          goto _L35
    }

    public void removeView(View view)
    {
        if(mInLayout)
            removeViewInLayout(view);
        else
            super.removeView(view);
    }

    public void setAdapter(PagerAdapter pageradapter)
    {
        if(mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter.startUpdate(this);
            for(int i = 0; i < mItems.size(); i++)
            {
                ItemInfo iteminfo = (ItemInfo)mItems.get(i);
                mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
            }

            mAdapter.finishUpdate(this);
            mItems.clear();
            removeNonDecorViews();
            mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pageradapter1 = mAdapter;
        mAdapter = pageradapter;
        mExpectedAdapterCount = 0;
        if(mAdapter != null)
        {
            if(mObserver == null)
                mObserver = new PagerObserver();
            mAdapter.registerDataSetObserver(mObserver);
            mPopulatePending = false;
            boolean flag = mFirstLayout;
            mFirstLayout = true;
            mExpectedAdapterCount = mAdapter.getCount();
            if(mRestoredCurItem >= 0)
            {
                mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
                setCurrentItemInternal(mRestoredCurItem, false, true);
                mRestoredCurItem = -1;
                mRestoredAdapterState = null;
                mRestoredClassLoader = null;
            } else
            if(!flag)
                populate();
            else
                requestLayout();
        }
        if(mAdapterChangeListener != null && pageradapter1 != pageradapter)
            mAdapterChangeListener.onAdapterChanged(pageradapter1, pageradapter);
    }

    void setChildrenDrawingOrderEnabledCompat(boolean flag)
    {
        if(android.os.Build.VERSION.SDK_INT < 7)
            break MISSING_BLOCK_LABEL_71;
        Method method;
        Object aobj[];
        if(mSetChildrenDrawingOrderEnabled == null)
            try
            {
                Class aclass[] = new Class[1];
                aclass[0] = Boolean.TYPE;
                mSetChildrenDrawingOrderEnabled = android/view/ViewGroup.getDeclaredMethod("setChildrenDrawingOrderEnabled", aclass);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", nosuchmethodexception);
            }
        method = mSetChildrenDrawingOrderEnabled;
        aobj = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        method.invoke(this, aobj);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("ViewPager", "Error changing children drawing order", exception);
          goto _L1
    }

    public void setCurrentItem(int i)
    {
        mPopulatePending = false;
        boolean flag;
        if(!mFirstLayout)
            flag = true;
        else
            flag = false;
        setCurrentItemInternal(i, flag, false);
    }

    public void setCurrentItem(int i, boolean flag)
    {
        mPopulatePending = false;
        setCurrentItemInternal(i, flag, false);
    }

    void setCurrentItemInternal(int i, boolean flag, boolean flag1)
    {
        setCurrentItemInternal(i, flag, flag1, 0);
    }

    void setCurrentItemInternal(int i, boolean flag, boolean flag1, int j)
    {
        boolean flag2 = true;
        if(mAdapter != null && mAdapter.getCount() > 0) goto _L2; else goto _L1
_L1:
        setScrollingCacheEnabled(false);
_L8:
        return;
_L2:
        if(!flag1 && mCurItem == i && mItems.size() != 0)
        {
            setScrollingCacheEnabled(false);
            continue; /* Loop/switch isn't completed */
        }
        if(i >= 0) goto _L4; else goto _L3
_L3:
        i = 0;
_L6:
        int k = mOffscreenPageLimit;
        if(i > k + mCurItem || i < mCurItem - k)
        {
            for(int l = 0; l < mItems.size(); l++)
                ((ItemInfo)mItems.get(l)).scrolling = flag2;

        }
        break; /* Loop/switch isn't completed */
_L4:
        if(i >= mAdapter.getCount())
            i = -1 + mAdapter.getCount();
        if(true) goto _L6; else goto _L5
_L5:
        if(mCurItem == i)
            flag2 = false;
        if(mFirstLayout)
        {
            mCurItem = i;
            if(flag2 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(i);
            if(flag2 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(i);
            requestLayout();
        } else
        {
            populate(i);
            scrollToItem(i, flag, j, flag2);
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        OnPageChangeListener onpagechangelistener1 = mInternalPageChangeListener;
        mInternalPageChangeListener = onpagechangelistener;
        return onpagechangelistener1;
    }

    public void setOffscreenPageLimit(int i)
    {
        if(i < 1)
        {
            Log.w("ViewPager", (new StringBuilder()).append("Requested offscreen page limit ").append(i).append(" too small; defaulting to ").append(1).toString());
            i = 1;
        }
        if(i != mOffscreenPageLimit)
        {
            mOffscreenPageLimit = i;
            populate();
        }
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener onadapterchangelistener)
    {
        mAdapterChangeListener = onadapterchangelistener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        mOnPageChangeListener = onpagechangelistener;
    }

    public void setPageMargin(int i)
    {
        int j = mPageMargin;
        mPageMargin = i;
        int k = getWidth();
        recomputeScrollPosition(k, k, i, j);
        requestLayout();
    }

    public void setPageMarginDrawable(int i)
    {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable)
    {
        mMarginDrawable = drawable;
        if(drawable != null)
            refreshDrawableState();
        boolean flag;
        if(drawable == null)
            flag = true;
        else
            flag = false;
        setWillNotDraw(flag);
        invalidate();
    }

    public void setPageTransformer(boolean flag, PageTransformer pagetransformer)
    {
        int i = 1;
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            boolean flag1;
            int j;
            int k;
            if(pagetransformer != null)
                flag1 = i;
            else
                flag1 = false;
            if(mPageTransformer != null)
                j = i;
            else
                j = 0;
            if(flag1 != j)
                k = i;
            else
                k = 0;
            mPageTransformer = pagetransformer;
            setChildrenDrawingOrderEnabledCompat(flag1);
            if(flag1)
            {
                if(flag)
                    i = 2;
                mDrawingOrder = i;
            } else
            {
                mDrawingOrder = 0;
            }
            if(k != 0)
                populate();
        }
    }

    void smoothScrollTo(int i, int j)
    {
        smoothScrollTo(i, j, 0);
    }

    void smoothScrollTo(int i, int j, int k)
    {
        if(getChildCount() == 0)
        {
            setScrollingCacheEnabled(false);
        } else
        {
            int l = getScrollX();
            int i1 = getScrollY();
            int j1 = i - l;
            int k1 = j - i1;
            if(j1 == 0 && k1 == 0)
            {
                completeScroll(false);
                populate();
                setScrollState(0);
            } else
            {
                setScrollingCacheEnabled(true);
                setScrollState(2);
                int l1 = getClientWidth();
                int i2 = l1 / 2;
                float f = Math.min(1.0F, (1.0F * (float)Math.abs(j1)) / (float)l1);
                float f1 = (float)i2 + (float)i2 * distanceInfluenceForSnapDuration(f);
                int j2 = Math.abs(k);
                int k2;
                int l2;
                if(j2 > 0)
                {
                    k2 = 4 * Math.round(1000F * Math.abs(f1 / (float)j2));
                } else
                {
                    float f2 = (float)l1 * mAdapter.getPageWidth(mCurItem);
                    k2 = (int)(100F * (1.0F + (float)Math.abs(j1) / (f2 + (float)mPageMargin)));
                }
                l2 = Math.min(k2, 600);
                mScroller.startScroll(l, i1, j1, k1, l2);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(super.verifyDrawable(drawable) || drawable == mMarginDrawable)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR = new Comparator() {

        public int compare(ItemInfo iteminfo, ItemInfo iteminfo1)
        {
            return iteminfo.position - iteminfo1.position;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ItemInfo)obj, (ItemInfo)obj1);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int LAYOUT_ATTRS[];
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE;
    private static final Interpolator sInterpolator = new Interpolator() {

        public float getInterpolation(float f)
        {
            float f1 = f - 1.0F;
            return 1.0F + f1 * (f1 * (f1 * (f1 * f1)));
        }

    }
;
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x10100b3;
        LAYOUT_ATTRS = ai;
    }




}
