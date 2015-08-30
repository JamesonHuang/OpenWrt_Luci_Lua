// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dslv;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.sonos.acr.util.ImageUtils;
import java.io.*;
import java.util.ArrayList;

// Referenced classes of package dslv:
//            AllNodeController, DragSortController, DragSortItemView

public class DragSortListView extends ListView
{
    private class DragSortTracker
    {

        public void appendState()
        {
            if(mTracking) goto _L2; else goto _L1
_L1:
            return;
_L2:
            mBuilder.append("<DSLVState>\n");
            int i = getChildCount();
            int j = getFirstVisiblePosition();
            mBuilder.append("    <Positions>");
            for(int k = 0; k < i; k++)
                mBuilder.append(j + k).append(",");

            mBuilder.append("</Positions>\n");
            mBuilder.append("    <Tops>");
            for(int l = 0; l < i; l++)
                mBuilder.append(getChildAt(l).getTop()).append(",");

            mBuilder.append("</Tops>\n");
            mBuilder.append("    <Bottoms>");
            for(int i1 = 0; i1 < i; i1++)
                mBuilder.append(getChildAt(i1).getBottom()).append(",");

            mBuilder.append("</Bottoms>\n");
            mBuilder.append("    <FirstExpPos>").append(mFirstExpPos).append("</FirstExpPos>\n");
            mBuilder.append("    <FirstExpBlankHeight>").append(getItemHeight(mFirstExpPos) - getChildHeight(mFirstExpPos)).append("</FirstExpBlankHeight>\n");
            mBuilder.append("    <SecondExpPos>").append(mSecondExpPos).append("</SecondExpPos>\n");
            mBuilder.append("    <SecondExpBlankHeight>").append(getItemHeight(mSecondExpPos) - getChildHeight(mSecondExpPos)).append("</SecondExpBlankHeight>\n");
            mBuilder.append("    <SrcPos>").append(mSrcPos).append("</SrcPos>\n");
            mBuilder.append("    <SrcHeight>").append(mFloatViewHeight + getDividerHeight()).append("</SrcHeight>\n");
            mBuilder.append("    <ViewHeight>").append(getHeight()).append("</ViewHeight>\n");
            mBuilder.append("    <LastY>").append(mLastY).append("</LastY>\n");
            mBuilder.append("    <FloatY>").append(mFloatViewMid).append("</FloatY>\n");
            mBuilder.append("    <ShuffleEdges>");
            for(int j1 = 0; j1 < i; j1++)
                mBuilder.append(getShuffleEdge(j + j1, getChildAt(j1).getTop())).append(",");

            mBuilder.append("</ShuffleEdges>\n");
            mBuilder.append("</DSLVState>\n");
            mNumInBuffer = 1 + mNumInBuffer;
            if(mNumInBuffer > 1000)
            {
                flush();
                mNumInBuffer = 0;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void flush()
        {
            if(mTracking)
            {
                boolean flag = true;
                try
                {
                    if(mNumFlushes == 0)
                        flag = false;
                    FileWriter filewriter = new FileWriter(mFile, flag);
                    filewriter.write(mBuilder.toString());
                    mBuilder.delete(0, mBuilder.length());
                    filewriter.flush();
                    filewriter.close();
                    mNumFlushes = 1 + mNumFlushes;
                }
                catch(IOException ioexception) { }
            }
        }

        public void startTracking()
        {
            mBuilder.append("<DSLVStates>\n");
            mNumFlushes = 0;
            mTracking = true;
        }

        public void stopTracking()
        {
            if(mTracking)
            {
                mBuilder.append("</DSLVStates>\n");
                flush();
                mTracking = false;
            }
        }

        StringBuilder mBuilder;
        File mFile;
        private int mNumFlushes;
        private int mNumInBuffer;
        private boolean mTracking;
        final DragSortListView this$0;

        public DragSortTracker()
        {
            this$0 = DragSortListView.this;
            super();
            mBuilder = new StringBuilder();
            mNumInBuffer = 0;
            mNumFlushes = 0;
            mTracking = false;
            mFile = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
            if(mFile.exists())
                break MISSING_BLOCK_LABEL_77;
            mFile.createNewFile();
            Log.d("mobeta", "file created");
_L1:
            return;
            IOException ioexception;
            ioexception;
            Log.w("mobeta", "Could not create dslv_state.txt");
            Log.d("mobeta", ioexception.getMessage());
              goto _L1
        }
    }

    private class DragScroller
        implements Runnable
    {

        public int getScrollDir()
        {
            int i;
            if(mScrolling)
                i = scrollDir;
            else
                i = -1;
            return i;
        }

        public boolean isScrolling()
        {
            return mScrolling;
        }

        public void run()
        {
            int j;
            int i1;
            int j1;
            if(mAbort)
            {
                mScrolling = false;
            } else
            {
                int i = getFirstVisiblePosition();
                j = getLastVisiblePosition();
                int k = getCount();
                int l = getPaddingTop();
                i1 = getHeight() - l - getPaddingBottom();
                j1 = Math.min(mY, mFloatViewMid + mFloatViewHeightHalf);
                int k1 = Math.max(mY, mFloatViewMid - mFloatViewHeightHalf);
                View view1;
                int i2;
                if(scrollDir == 0)
                {
                    View view2 = getChildAt(0);
                    if(view2 == null)
                    {
                        mScrolling = false;
                    } else
                    {
label0:
                        {
                            if(i != 0 || view2.getTop() != l)
                                break label0;
                            mScrolling = false;
                        }
                    }
                } else
                {
                    View view = getChildAt(j - i);
                    if(view == null)
                    {
                        mScrolling = false;
                    } else
                    {
label1:
                        {
                            if(j != k - 1 || view.getBottom() > i1 + l)
                                break label1;
                            mScrolling = false;
                        }
                    }
                }
            }
_L2:
            return;
            mScrollSpeed = mScrollProfile.getSpeed((mUpScrollStartYF - (float)k1) / mDragUpScrollHeight, mPrevTime);
_L3:
            mCurrTime = SystemClock.uptimeMillis();
            dt = mCurrTime - mPrevTime;
            dy = Math.round(mScrollSpeed * dt);
            int l1;
            if(dy >= 0)
            {
                dy = Math.min(i1, dy);
                l1 = i;
            } else
            {
                dy = Math.max(-i1, dy);
                l1 = j;
            }
            view1 = getChildAt(l1 - i);
            i2 = view1.getTop() + dy;
            if(l1 == 0 && i2 > l)
                i2 = l;
            mBlockLayoutRequests = true;
            setSelectionFromTop(l1, i2 - l);
            layoutChildren();
            invalidate();
            mBlockLayoutRequests = false;
            doDragFloatView(l1, view1, false);
            mPrevTime = mCurrTime;
            post(this);
            if(true) goto _L2; else goto _L1
_L1:
            mScrollSpeed = -mScrollProfile.getSpeed(((float)j1 - mDownScrollStartYF) / mDragDownScrollHeight, mPrevTime);
              goto _L3
        }

        public void startScrolling(int i)
        {
            if(!mScrolling)
            {
                mAbort = false;
                mScrolling = true;
                tStart = SystemClock.uptimeMillis();
                mPrevTime = tStart;
                scrollDir = i;
                post(this);
            }
        }

        public void stopScrolling(boolean flag)
        {
            if(flag)
            {
                removeCallbacks(this);
                mScrolling = false;
            } else
            {
                mAbort = true;
            }
        }

        public static final int DOWN = 1;
        public static final int STOP = -1;
        public static final int UP;
        private float dt;
        private int dy;
        private boolean mAbort;
        private long mCurrTime;
        private int mFirstFooter;
        private int mLastHeader;
        private long mPrevTime;
        private float mScrollSpeed;
        private boolean mScrolling;
        private int scrollDir;
        private long tStart;
        final DragSortListView this$0;

        public DragScroller()
        {
            this$0 = DragSortListView.this;
            super();
            mScrolling = false;
        }
    }

    public static interface DragScrollProfile
    {

        public abstract float getSpeed(float f, long l);
    }

    public static interface DragSortListener
        extends DropListener, DragListener, RemoveListener
    {
    }

    public static interface RemoveListener
    {

        public abstract void remove(int i);
    }

    public static interface DropListener
    {

        public abstract void drop(int i, int j);
    }

    public static interface DragListener
    {

        public abstract void drag(int i, int j);
    }

    public static interface FloatViewManager
    {

        public abstract View onCreateFloatView(int i);

        public abstract void onDestroyFloatView(View view);

        public abstract void onDragFloatView(View view, Point point, Point point1);
    }

    private class RemoveAnimator extends SmoothAnimator
    {

        public void onStart()
        {
            mFirstChildHeight = -1;
            mSecondChildHeight = -1;
            mFirstPos = mFirstExpPos;
            mSecondPos = mSecondExpPos;
            srcPos = mSrcPos;
            mDragState = 1;
            destroyFloatView();
        }

        public void onStop()
        {
            doRemoveItem();
        }

        public void onUpdate(float f, float f1)
        {
            float f2 = 1.0F - f1;
            int i = getFirstVisiblePosition();
            View view = getChildAt(mFirstPos - i);
            if(view != null)
            {
                if(mFirstChildHeight == -1)
                {
                    mFirstChildHeight = getChildHeight(mFirstPos, view, false);
                    mFirstStartBlank = view.getHeight() - mFirstChildHeight;
                }
                int k = Math.max((int)(f2 * mFirstStartBlank), 1);
                android.view.ViewGroup.LayoutParams layoutparams1 = view.getLayoutParams();
                layoutparams1.height = k + mFirstChildHeight;
                view.setLayoutParams(layoutparams1);
            }
            if(mSecondPos != mFirstPos)
            {
                View view1 = getChildAt(mSecondPos - i);
                if(view1 != null)
                {
                    if(mSecondChildHeight == -1)
                    {
                        mSecondChildHeight = getChildHeight(mSecondPos, view1, false);
                        mSecondStartBlank = view1.getHeight() - mSecondChildHeight;
                    }
                    int j = Math.max((int)(f2 * mSecondStartBlank), 1);
                    android.view.ViewGroup.LayoutParams layoutparams = view1.getLayoutParams();
                    layoutparams.height = j + mSecondChildHeight;
                    view1.setLayoutParams(layoutparams);
                }
            }
        }

        private int mFirstChildHeight;
        private int mFirstPos;
        private float mFirstStartBlank;
        private int mSecondChildHeight;
        private int mSecondPos;
        private float mSecondStartBlank;
        private int srcPos;
        final DragSortListView this$0;

        public RemoveAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super(f, i);
            mFirstChildHeight = -1;
            mSecondChildHeight = -1;
        }
    }

    private class DropAnimator extends SmoothAnimator
    {

        private int getTargetY()
        {
            int i = getFirstVisiblePosition();
            int j = (mItemHeightCollapsed + getDividerHeight()) / 2;
            View view = getChildAt(mDropPos - i);
            int k = -1;
            if(view != null)
            {
                if(mDropPos == srcPos)
                    k = view.getTop();
                else
                if(mDropPos < srcPos)
                    k = view.getTop() - j;
                else
                    k = (j + view.getBottom()) - mFloatViewHeight;
            } else
            {
                cancel();
            }
            return k;
        }

        public void onStart()
        {
            mDropPos = mFloatPos;
            srcPos = mSrcPos;
            mDragState = 2;
            mInitDeltaY = mFloatLoc.y - getTargetY();
            mInitDeltaX = mFloatLoc.x - getPaddingLeft();
        }

        public void onStop()
        {
            dropFloatView();
        }

        public void onUpdate(float f, float f1)
        {
            int i = getTargetY();
            float f2 = mFloatLoc.y - i;
            float f3 = 1.0F - f1;
            if(f3 < Math.abs(f2 / mInitDeltaY))
            {
                mFloatLoc.y = i + (int)(f3 * mInitDeltaY);
                mFloatLoc.x = getPaddingLeft() + (int)(f3 * mInitDeltaX);
                doDragFloatView(true);
            }
        }

        private int mDropPos;
        private float mInitDeltaX;
        private float mInitDeltaY;
        private int srcPos;
        final DragSortListView this$0;

        public DropAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super(f, i);
        }
    }

    private class LiftAnimator extends SmoothAnimator
    {

        public void onStart()
        {
            mInitDragDeltaY = mDragDeltaY;
            mFinalDragDeltaY = mFloatViewHeightHalf;
        }

        public void onUpdate(float f, float f1)
        {
            if(mDragState != 4)
            {
                cancel();
            } else
            {
                mDragDeltaY = (int)(f1 * mFinalDragDeltaY + (1.0F - f1) * mInitDragDeltaY);
                mFloatLoc.y = mY - mDragDeltaY;
                doDragFloatView(true);
            }
        }

        private float mFinalDragDeltaY;
        private float mInitDragDeltaY;
        final DragSortListView this$0;

        public LiftAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super(f, i);
        }
    }

    private class SmoothAnimator
        implements Runnable
    {

        public void cancel()
        {
            mCanceled = true;
        }

        public void onStart()
        {
        }

        public void onStop()
        {
        }

        public void onUpdate(float f, float f1)
        {
        }

        public void run()
        {
            if(!mCanceled)
            {
                float f = (float)(SystemClock.uptimeMillis() - mStartTime) / mDurationF;
                if(f >= 1.0F)
                {
                    onUpdate(1.0F, 1.0F);
                    onStop();
                } else
                {
                    onUpdate(f, transform(f));
                    post(this);
                }
            }
        }

        public void start()
        {
            mStartTime = SystemClock.uptimeMillis();
            mCanceled = false;
            onStart();
            post(this);
        }

        public float transform(float f)
        {
            float f1;
            if(f < mAlpha)
                f1 = f * (f * mA);
            else
            if(f < 1.0F - mAlpha)
                f1 = mB + f * mC;
            else
                f1 = 1.0F - mD * (f - 1.0F) * (f - 1.0F);
            return f1;
        }

        private float mA;
        private float mAlpha;
        private float mB;
        private float mC;
        private boolean mCanceled;
        private float mD;
        private float mDurationF;
        private long mStartTime;
        final DragSortListView this$0;

        public SmoothAnimator(float f, int i)
        {
            this$0 = DragSortListView.this;
            super();
            mAlpha = f;
            mDurationF = i;
            float f1 = 1.0F / (2.0F * mAlpha * (1.0F - mAlpha));
            mD = f1;
            mA = f1;
            mB = mAlpha / (2.0F * (mAlpha - 1.0F));
            mC = 1.0F / (1.0F - mAlpha);
        }
    }

    private class HeightCache
    {

        public void add(int i, int j)
        {
            int k = mMap.get(i, -1);
            if(k != j)
            {
                if(k == -1)
                {
                    if(mMap.size() == mMaxSize)
                        mMap.delete(((Integer)mOrder.remove(0)).intValue());
                } else
                {
                    mOrder.remove(Integer.valueOf(i));
                }
                mMap.put(i, j);
                mOrder.add(Integer.valueOf(i));
            }
        }

        public void clear()
        {
            mMap.clear();
            mOrder.clear();
        }

        public int get(int i)
        {
            return mMap.get(i, -1);
        }

        private SparseIntArray mMap;
        private int mMaxSize;
        private ArrayList mOrder;
        final DragSortListView this$0;

        public HeightCache(int i)
        {
            this$0 = DragSortListView.this;
            super();
            mMap = new SparseIntArray(i);
            mOrder = new ArrayList(i);
            mMaxSize = i;
        }
    }

    private class AdapterWrapper extends HeaderViewListAdapter
    {

        public ListAdapter getAdapter()
        {
            return mAdapter;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            DragSortItemView dragsortitemview;
            if(view != null)
            {
                dragsortitemview = (DragSortItemView)view;
                View view1 = dragsortitemview.getChildAt(0);
                View view2 = mAdapter.getView(i, view1, dragsortitemview);
                if(view2 != view1)
                {
                    if(view1 != null)
                        dragsortitemview.removeViewAt(0);
                    dragsortitemview.addView(view2);
                }
            } else
            {
                dragsortitemview = new DragSortItemView(getContext());
                dragsortitemview.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
                dragsortitemview.addView(mAdapter.getView(i, null, dragsortitemview));
            }
            adjustItem(i + getHeaderViewsCount(), dragsortitemview, true);
            return dragsortitemview;
        }

        private ListAdapter mAdapter;
        final DragSortListView this$0;

        public AdapterWrapper(ListAdapter listadapter)
        {
            this$0 = DragSortListView.this;
            super(null, null, listadapter);
            mAdapter = listadapter;
        }
    }


    public DragSortListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFloatLoc = new Point();
        mTouchLoc = new Point();
        mFloatViewOnMeasured = false;
        mFloatAlpha = 1.0F;
        mCurrFloatAlpha = 1.0F;
        mAnimate = false;
        mDragEnabled = true;
        mDragState = 0;
        mItemHeightCollapsed = 1;
        mWidthMeasureSpec = 0;
        mSampleViewTypes = new View[1];
        mDragUpScrollStartFrac = 0.3333333F;
        mDragDownScrollStartFrac = 0.3333333F;
        mMaxScrollSpeed = 0.5F;
        mScrollProfile = new DragScrollProfile() {

            public float getSpeed(float f, long l1)
            {
                return f * mMaxScrollSpeed;
            }

            final DragSortListView this$0;

            
            {
                this$0 = DragSortListView.this;
                super();
            }
        }
;
        mDragFlags = 0;
        mLastCallWasIntercept = false;
        mInTouchEvent = false;
        mFloatViewManager = null;
        mCancelMethod = 0;
        mSlideRegionFrac = 0.25F;
        mSlideFrac = 0.0F;
        mTrackDragSort = false;
        mBlockLayoutRequests = false;
        mIgnoreTouchEvent = false;
        mChildHeightCache = new HeightCache(3);
        mFloatViewInvalidated = false;
        int i = 150;
        int j = 150;
        if(attributeset != null)
        {
            TypedArray typedarray = getContext().obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.DragSortListView, 0, 0);
            mItemHeightCollapsed = Math.max(1, typedarray.getDimensionPixelSize(0, 1));
            mTrackDragSort = typedarray.getBoolean(5, false);
            if(mTrackDragSort)
                mDragSortTracker = new DragSortTracker();
            mFloatAlpha = typedarray.getFloat(6, mFloatAlpha);
            mCurrFloatAlpha = mFloatAlpha;
            mDragEnabled = typedarray.getBoolean(10, mDragEnabled);
            mSlideRegionFrac = Math.max(0.0F, Math.min(1.0F, 1.0F - typedarray.getFloat(7, 0.75F)));
            boolean flag;
            if(mSlideRegionFrac > 0.0F)
                flag = true;
            else
                flag = false;
            mAnimate = flag;
            setDragScrollStart(typedarray.getFloat(1, mDragUpScrollStartFrac));
            mMaxScrollSpeed = typedarray.getFloat(2, mMaxScrollSpeed);
            i = typedarray.getInt(8, i);
            j = typedarray.getInt(9, j);
            if(typedarray.getBoolean(16, true))
            {
                boolean flag1 = typedarray.getBoolean(12, false);
                int k = typedarray.getInt(4, 1);
                boolean flag2 = typedarray.getBoolean(11, true);
                int l = typedarray.getInt(13, 0);
                int i1 = typedarray.getResourceId(14, 0);
                int j1 = typedarray.getResourceId(15, 0);
                int k1 = typedarray.getResourceId(3, 0);
                AllNodeController allnodecontroller = new AllNodeController(this, i1, l, k, j1);
                allnodecontroller.setRemoveEnabled(flag1);
                allnodecontroller.setSortEnabled(flag2);
                allnodecontroller.setFloatBGDrawableRes(k1);
                mFloatViewManager = allnodecontroller;
                setOnTouchListener(allnodecontroller);
            }
            typedarray.recycle();
        }
        mDragScroller = new DragScroller();
        if(i > 0)
            mRemoveAnimator = new RemoveAnimator(0.5F, i);
        if(j > 0)
            mDropAnimator = new DropAnimator(0.5F, j);
        mCancelEvent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
        mObserver = new DataSetObserver() {

            private void cancel()
            {
                if(mDragState == 4)
                    cancelDrag();
            }

            public void onChanged()
            {
                cancel();
            }

            public void onInvalidated()
            {
                cancel();
            }

            final DragSortListView this$0;

            
            {
                this$0 = DragSortListView.this;
                super();
            }
        }
;
        paint = new Paint();
    }

    private void adjustAllItems()
    {
        int i = getFirstVisiblePosition();
        int j = getLastVisiblePosition();
        int k = Math.max(0, getHeaderViewsCount() - i);
        int l = Math.min(j - i, (-1 + getCount()) - getFooterViewsCount() - i);
        for(int i1 = k; i1 <= l; i1++)
        {
            View view = getChildAt(i1);
            if(view != null)
                adjustItem(i + i1, view, false);
        }

    }

    private void adjustItem(int i)
    {
        View view = getChildAt(i - getFirstVisiblePosition());
        if(view != null)
            adjustItem(i, view, false);
    }

    private void adjustItem(int i, View view, boolean flag)
    {
        int j = calcItemHeight(i, view, flag);
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if(j != layoutparams.height)
        {
            layoutparams.height = j;
            view.setLayoutParams(layoutparams);
        }
        if(i != mFirstExpPos && i != mSecondExpPos) goto _L2; else goto _L1
_L1:
        if(i >= mSrcPos) goto _L4; else goto _L3
_L3:
        ((DragSortItemView)view).setGravity(80);
_L2:
        int k = view.getVisibility();
        byte byte0 = 0;
        if(i == mSrcPos && mFloatView != null)
            byte0 = 4;
        if(byte0 != k)
            view.setVisibility(byte0);
        return;
_L4:
        if(i > mSrcPos)
            ((DragSortItemView)view).setGravity(48);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void adjustOnReorder()
    {
        int i = getFirstVisiblePosition();
        if(mSrcPos < i)
        {
            View view = getChildAt(0);
            int j = 0;
            if(view != null)
                j = view.getTop();
            setSelectionFromTop(i - 1, j - getPaddingTop());
        }
    }

    private int adjustScroll(int i, View view, int j, int k)
    {
        int l;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        l = 0;
        int i1 = getChildHeight(i);
        j1 = view.getHeight();
        k1 = calcItemHeight(i, i1);
        l1 = j1;
        i2 = k1;
        if(i != mSrcPos)
        {
            l1 -= i1;
            i2 -= i1;
        }
        j2 = mFloatViewHeight;
        if(mSrcPos != mFirstExpPos && mSrcPos != mSecondExpPos)
            j2 -= mItemHeightCollapsed;
        if(i > j) goto _L2; else goto _L1
_L1:
        if(i > mFirstExpPos)
            l = 0 + (j2 - i2);
_L4:
        return l;
_L2:
        if(i == k)
        {
            if(i <= mFirstExpPos)
                l = 0 + (l1 - j2);
            else
            if(i == mSecondExpPos)
                l = 0 + (j1 - k1);
            else
                l = 0 + l1;
        } else
        if(i <= mFirstExpPos)
            l = 0 - j2;
        else
        if(i == mSecondExpPos)
            l = 0 - i2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int calcItemHeight(int i, int j)
    {
        getDividerHeight();
        boolean flag;
        int k;
        int l;
        int i1;
        if(mAnimate && mFirstExpPos != mSecondExpPos)
            flag = true;
        else
            flag = false;
        k = mFloatViewHeight - mItemHeightCollapsed;
        l = (int)(mSlideFrac * (float)k);
        if(i == mSrcPos)
        {
            if(mSrcPos == mFirstExpPos)
            {
                if(flag)
                    i1 = l + mItemHeightCollapsed;
                else
                    i1 = mFloatViewHeight;
            } else
            if(mSrcPos == mSecondExpPos)
                i1 = mFloatViewHeight - l;
            else
                i1 = mItemHeightCollapsed;
        } else
        if(i == mFirstExpPos)
        {
            if(flag)
                i1 = j + l;
            else
                i1 = j + k;
        } else
        if(i == mSecondExpPos)
            i1 = (j + k) - l;
        else
            i1 = j;
        return i1;
    }

    private int calcItemHeight(int i, View view, boolean flag)
    {
        return calcItemHeight(i, getChildHeight(i, view, flag));
    }

    private void clearPositions()
    {
        mSrcPos = -1;
        mFirstExpPos = -1;
        mSecondExpPos = -1;
        mFloatPos = -1;
    }

    private void continueDrag(int i, int j)
    {
        int k;
        int l;
        int i1;
        mFloatLoc.x = i - mDragDeltaX;
        mFloatLoc.y = j - mDragDeltaY;
        doDragFloatView(true);
        k = Math.min(j, mFloatViewMid + mFloatViewHeightHalf);
        l = Math.max(j, mFloatViewMid - mFloatViewHeightHalf);
        i1 = mDragScroller.getScrollDir();
        if(k <= mLastY || k <= mDownScrollStartY || i1 == 1) goto _L2; else goto _L1
_L1:
        if(i1 != -1)
            mDragScroller.stopScrolling(true);
        mDragScroller.startScrolling(1);
_L4:
        return;
_L2:
        if(l < mLastY && l < mUpScrollStartY && i1 != 0)
        {
            if(i1 != -1)
                mDragScroller.stopScrolling(true);
            mDragScroller.startScrolling(0);
        } else
        if(l >= mUpScrollStartY && k <= mDownScrollStartY && mDragScroller.isScrolling())
            mDragScroller.stopScrolling(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void destroyFloatView()
    {
        if(mFloatView != null)
        {
            mFloatView.setVisibility(8);
            if(mFloatViewManager != null)
                mFloatViewManager.onDestroyFloatView(mFloatView);
            mFloatView = null;
            invalidate();
        }
    }

    private void doActionUpOrCancel()
    {
        mCancelMethod = 0;
        mInTouchEvent = false;
        if(mDragState == 3)
            mDragState = 0;
        mCurrFloatAlpha = mFloatAlpha;
        mChildHeightCache.clear();
    }

    private void doDragFloatView(int i, View view, boolean flag)
    {
        mBlockLayoutRequests = true;
        updateFloatView();
        int j = mFirstExpPos;
        int k = mSecondExpPos;
        boolean flag1 = updatePositions();
        if(flag1)
        {
            adjustAllItems();
            setSelectionFromTop(i, (adjustScroll(i, view, j, k) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if(flag1 || flag)
            invalidate();
        mBlockLayoutRequests = false;
    }

    private void doDragFloatView(boolean flag)
    {
        int i = getFirstVisiblePosition() + getChildCount() / 2;
        View view = getChildAt(getChildCount() / 2);
        if(view != null)
            doDragFloatView(i, view, flag);
    }

    private void doRemoveItem()
    {
        doRemoveItem(mSrcPos - getHeaderViewsCount());
    }

    private void doRemoveItem(int i)
    {
        mDragState = 1;
        if(mRemoveListener != null)
            mRemoveListener.remove(i);
        destroyFloatView();
        adjustOnReorder();
        clearPositions();
        if(mInTouchEvent)
            mDragState = 3;
        else
            mDragState = 0;
    }

    private void drawDivider(int i, Canvas canvas)
    {
        Drawable drawable = getDivider();
        int j = getDividerHeight();
        if(drawable != null && j != 0)
        {
            ViewGroup viewgroup = (ViewGroup)getChildAt(i - getFirstVisiblePosition());
            if(viewgroup != null)
            {
                int k = getPaddingLeft();
                int l = getWidth() - getPaddingRight();
                int i1 = viewgroup.getChildAt(0).getHeight();
                int j1;
                int k1;
                if(i > mSrcPos)
                {
                    k1 = i1 + viewgroup.getTop();
                    j1 = k1 + j;
                } else
                {
                    j1 = viewgroup.getBottom() - i1;
                    k1 = j1 - j;
                }
                canvas.save();
                canvas.clipRect(k, k1, l, j1);
                drawable.setBounds(k, k1, l, j1);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    private void dropFloatView()
    {
        mDragState = 2;
        if(mDropListener != null && mFloatPos >= 0 && mFloatPos < getCount())
        {
            int i = getHeaderViewsCount();
            mDropListener.drop(mSrcPos - i, mFloatPos - i);
        }
        if(frozenBitmap == null)
            finishDrop();
    }

    private void finishDrop()
    {
        destroyFloatView();
        adjustOnReorder();
        clearPositions();
        adjustAllItems();
        if(mInTouchEvent)
            mDragState = 3;
        else
            mDragState = 0;
    }

    private int getChildHeight(int i)
    {
        int j = 0;
        if(i != mSrcPos) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        View view = getChildAt(i - getFirstVisiblePosition());
        if(view != null)
        {
            j = getChildHeight(i, view, false);
        } else
        {
            j = mChildHeightCache.get(i);
            if(j == -1)
            {
                ListAdapter listadapter = getAdapter();
                int k = listadapter.getItemViewType(i);
                int l = listadapter.getViewTypeCount();
                if(l != mSampleViewTypes.length)
                    mSampleViewTypes = new View[l];
                View view1;
                if(k >= 0)
                {
                    if(mSampleViewTypes[k] == null)
                    {
                        view1 = listadapter.getView(i, null, this);
                        mSampleViewTypes[k] = view1;
                    } else
                    {
                        view1 = listadapter.getView(i, mSampleViewTypes[k], this);
                    }
                } else
                {
                    view1 = listadapter.getView(i, null, this);
                }
                j = getChildHeight(i, view1, true);
                mChildHeightCache.add(i, j);
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private int getChildHeight(int i, View view, boolean flag)
    {
        int j = 0;
        if(i != mSrcPos) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        View view1;
        android.view.ViewGroup.LayoutParams layoutparams;
        if(i < getHeaderViewsCount() || i >= getCount() - getFooterViewsCount())
            view1 = view;
        else
            view1 = ((ViewGroup)view).getChildAt(0);
        layoutparams = view1.getLayoutParams();
        if(layoutparams != null && layoutparams.height > 0)
        {
            j = layoutparams.height;
        } else
        {
            j = view1.getHeight();
            if(j == 0 || flag)
            {
                measureItem(view1);
                j = view1.getMeasuredHeight();
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private int getItemHeight(int i)
    {
        View view = getChildAt(i - getFirstVisiblePosition());
        int j;
        if(view != null)
            j = view.getHeight();
        else
            j = calcItemHeight(i, getChildHeight(i));
        return j;
    }

    private int getShuffleEdge(int i, int j)
    {
        int k;
        int l;
        k = getHeaderViewsCount();
        l = getFooterViewsCount();
        if(i > k && i < getCount() - l) goto _L2; else goto _L1
_L1:
        int i1 = j;
_L5:
        return i1;
_L2:
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        j1 = getDividerHeight();
        k1 = mFloatViewHeight - mItemHeightCollapsed;
        l1 = getChildHeight(i);
        i2 = getItemHeight(i);
        j2 = j;
        if(mSecondExpPos > mSrcPos) goto _L4; else goto _L3
_L3:
        if(i == mSecondExpPos && mFirstExpPos != mSecondExpPos)
        {
            if(i == mSrcPos)
                j2 = (j + i2) - mFloatViewHeight;
            else
                j2 = (j + (i2 - l1)) - k1;
        } else
        if(i > mSecondExpPos && i <= mSrcPos)
            j2 = j - k1;
_L6:
        if(i <= mSrcPos)
            i1 = j2 + (mFloatViewHeight - j1 - getChildHeight(i - 1)) / 2;
        else
            i1 = j2 + (l1 - j1 - mFloatViewHeight) / 2;
        if(true) goto _L5; else goto _L4
_L4:
        if(i > mSrcPos && i <= mFirstExpPos)
            j2 = j + k1;
        else
        if(i == mSecondExpPos && mFirstExpPos != mSecondExpPos)
            j2 = j + (i2 - l1);
          goto _L6
    }

    private void invalidateFloatView()
    {
        mFloatViewInvalidated = true;
    }

    private void measureFloatView()
    {
        if(mFloatView != null)
        {
            measureItem(mFloatView);
            mFloatViewHeight = mFloatView.getMeasuredHeight();
            mFloatViewHeightHalf = mFloatViewHeight / 2;
        }
    }

    private void measureItem(View view)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if(layoutparams == null)
            layoutparams = new android.view.ViewGroup.LayoutParams(-1, -2);
        int i = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, getListPaddingLeft() + getListPaddingRight(), layoutparams.width);
        int j;
        if(layoutparams.height > 0)
            j = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
        else
            j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        view.measure(i, j);
    }

    private void printPosData()
    {
        Log.d("mobeta", (new StringBuilder()).append("mSrcPos=").append(mSrcPos).append(" mFirstExpPos=").append(mFirstExpPos).append(" mSecondExpPos=").append(mSecondExpPos).toString());
    }

    private void saveTouchCoords(MotionEvent motionevent)
    {
        int i = 0xff & motionevent.getAction();
        if(i != 0)
        {
            mLastX = mX;
            mLastY = mY;
        }
        mX = (int)motionevent.getX();
        mY = (int)motionevent.getY();
        if(i == 0)
        {
            mLastX = mX;
            mLastY = mY;
        }
        mOffsetX = (int)motionevent.getRawX() - mX;
        mOffsetY = (int)motionevent.getRawY() - mY;
    }

    private void updateFloatView()
    {
        if(mFloatViewManager != null)
        {
            mTouchLoc.set(mX, mY);
            mFloatViewManager.onDragFloatView(mFloatView, mFloatLoc, mTouchLoc);
        }
        int i = mFloatLoc.x;
        int j = mFloatLoc.y;
        int k = getPaddingLeft();
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if((1 & mDragFlags) == 0 && i > k)
            mFloatLoc.x = k;
        else
        if((2 & mDragFlags) == 0 && i < k)
            mFloatLoc.x = k;
        l = getHeaderViewsCount();
        i1 = getFooterViewsCount();
        j1 = getFirstVisiblePosition();
        k1 = getLastVisiblePosition();
        l1 = getPaddingTop();
        if(j1 < l)
            l1 = getChildAt(-1 + (l - j1)).getBottom();
        if((8 & mDragFlags) == 0 && j1 <= mSrcPos)
            l1 = Math.max(getChildAt(mSrcPos - j1).getTop(), l1);
        i2 = getHeight() - getPaddingBottom();
        if(k1 >= -1 + (getCount() - i1))
            i2 = getChildAt((-1 + (getCount() - i1)) - j1).getBottom();
        if((4 & mDragFlags) == 0 && k1 >= mSrcPos)
            i2 = Math.min(getChildAt(mSrcPos - j1).getBottom(), i2);
        if(j < l1)
            mFloatLoc.y = l1;
        else
        if(j + mFloatViewHeight > i2)
            mFloatLoc.y = i2 - mFloatViewHeight;
        mFloatViewMid = mFloatLoc.y + mFloatViewHeightHalf;
    }

    private boolean updatePositions()
    {
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int i = getFirstVisiblePosition();
        int j = mFirstExpPos;
        View view = getChildAt(j - i);
        if(view == null)
        {
            j = i + getChildCount() / 2;
            view = getChildAt(j - i);
        }
        int k = view.getTop();
        l = view.getHeight();
        i1 = getShuffleEdge(j, k);
        j1 = i1;
        k1 = getDividerHeight();
        l1 = j;
        i2 = k;
        if(mFloatViewMid >= i1) goto _L2; else goto _L1
_L1:
        if(l1 < 0) goto _L4; else goto _L3
_L3:
        int i5;
        l1--;
        i5 = getItemHeight(l1);
        if(l1 != 0) goto _L6; else goto _L5
_L5:
        i1 = i2 - k1 - i5;
_L4:
        int k2 = getHeaderViewsCount();
        int l2 = getFooterViewsCount();
        boolean flag = false;
        int i3 = mFirstExpPos;
        int j3 = mSecondExpPos;
        float f = mSlideFrac;
        if(mAnimate)
        {
            int k3 = Math.abs(i1 - j1);
            int j2;
            int l3;
            int i4;
            int j4;
            float f1;
            int k4;
            int l4;
            if(mFloatViewMid < i1)
            {
                i4 = i1;
                l3 = j1;
            } else
            {
                l3 = i1;
                i4 = j1;
            }
            j4 = (int)(0.5F * mSlideRegionFrac * (float)k3);
            f1 = j4;
            k4 = l3 + j4;
            l4 = i4 - j4;
            if(mFloatViewMid < k4)
            {
                mFirstExpPos = l1 - 1;
                mSecondExpPos = l1;
                mSlideFrac = (0.5F * (float)(k4 - mFloatViewMid)) / f1;
            } else
            if(mFloatViewMid < l4)
            {
                mFirstExpPos = l1;
                mSecondExpPos = l1;
            } else
            {
                mFirstExpPos = l1;
                mSecondExpPos = l1 + 1;
                mSlideFrac = 0.5F * (1.0F + (float)(i4 - mFloatViewMid) / f1);
            }
        } else
        {
            mFirstExpPos = l1;
            mSecondExpPos = l1;
        }
        if(mFirstExpPos < k2)
        {
            l1 = k2;
            mFirstExpPos = l1;
            mSecondExpPos = l1;
        } else
        if(mSecondExpPos >= getCount() - l2)
        {
            l1 = -1 + (getCount() - l2);
            mFirstExpPos = l1;
            mSecondExpPos = l1;
        }
        if(mFirstExpPos != i3 || mSecondExpPos != j3 || mSlideFrac != f)
            flag = true;
        if(l1 != mFloatPos)
        {
            if(mDragListener != null)
                mDragListener.drag(mFloatPos - k2, l1 - k2);
            mFloatPos = l1;
            flag = true;
        }
        return flag;
_L6:
        i2 -= i5 + k1;
        i1 = getShuffleEdge(l1, i2);
        if(mFloatViewMid >= i1) goto _L4; else goto _L7
_L7:
        j1 = i1;
          goto _L1
_L2:
        j2 = getCount();
_L9:
        if(l1 < j2)
        {
label0:
            {
                if(l1 != j2 - 1)
                    break label0;
                i1 = l + (i2 + k1);
            }
        }
          goto _L4
        i2 += k1 + l;
        l = getItemHeight(l1 + 1);
        i1 = getShuffleEdge(l1 + 1, i2);
        if(mFloatViewMid < i1) goto _L4; else goto _L8
_L8:
        j1 = i1;
        l1++;
          goto _L9
    }

    private void updateScrollStarts()
    {
        int i = getPaddingTop();
        int j = getHeight() - i - getPaddingBottom();
        float f = j;
        mUpScrollStartYF = (float)i + f * mDragUpScrollStartFrac;
        mDownScrollStartYF = (float)i + f * (1.0F - mDragDownScrollStartFrac);
        mUpScrollStartY = (int)mUpScrollStartYF;
        mDownScrollStartY = (int)mDownScrollStartYF;
        mDragUpScrollHeight = mUpScrollStartYF - (float)i;
        mDragDownScrollHeight = (float)(i + j) - mDownScrollStartYF;
    }

    public void cancelDrag()
    {
        if(mDragState == 4)
        {
            mDragScroller.stopScrolling(true);
            destroyFloatView();
            clearPositions();
            adjustAllItems();
            if(mInTouchEvent)
                mDragState = 3;
            else
                mDragState = 0;
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if(frozenBitmap == null)
        {
            super.dispatchDraw(canvas);
            if(mDragState != 0)
            {
                if(mFirstExpPos != mSrcPos)
                    drawDivider(mFirstExpPos, canvas);
                if(mSecondExpPos != mFirstExpPos && mSecondExpPos != mSrcPos)
                    drawDivider(mSecondExpPos, canvas);
            }
            if(mFloatView != null)
            {
                int i = mFloatView.getWidth();
                int j = mFloatView.getHeight();
                int k = (int)(255F * mCurrFloatAlpha);
                canvas.save();
                canvas.translate(mFloatLoc.x, mFloatLoc.y);
                canvas.clipRect(0, 0, i, j);
                canvas.saveLayerAlpha(0.0F, 0.0F, i, j, k, 31);
                mFloatView.draw(canvas);
                canvas.restore();
                canvas.restore();
            }
        }
    }

    public void freezeView()
    {
        if(frozenBitmap == null)
        {
            setDrawingCacheEnabled(true);
            frozenBitmap = ImageUtils.createBitmap(getDrawingCache());
            setDrawingCacheEnabled(false);
            invalidate();
        }
    }

    public float getFloatAlpha()
    {
        return mCurrFloatAlpha;
    }

    public ListAdapter getInputAdapter()
    {
        ListAdapter listadapter;
        if(mAdapterWrapper == null)
            listadapter = null;
        else
            listadapter = mAdapterWrapper.getAdapter();
        return listadapter;
    }

    public boolean isDragEnabled()
    {
        return mDragEnabled;
    }

    protected void layoutChildren()
    {
        super.layoutChildren();
        if(mFloatView != null)
        {
            if(mFloatView.isLayoutRequested() && !mFloatViewOnMeasured)
                measureFloatView();
            mFloatView.layout(0, 0, mFloatView.getMeasuredWidth(), mFloatView.getMeasuredHeight());
            mFloatViewOnMeasured = false;
        }
    }

    public void moveItem(int i, int j)
    {
        if(mDropListener != null)
        {
            int k = getInputAdapter().getCount();
            if(i >= 0 && i < k && j >= 0 && j < k)
                mDropListener.drop(i, j);
        }
    }

    protected boolean onDragTouchEvent(MotionEvent motionevent)
    {
        0xff & motionevent.getAction();
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 1 3: default 44
    //                   1 65
    //                   2 86
    //                   3 46;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L4:
        if(mDragState == 4)
            cancelDrag();
        doActionUpOrCancel();
        continue; /* Loop/switch isn't completed */
_L2:
        if(mDragState == 4)
            stopDrag(false);
        doActionUpOrCancel();
        continue; /* Loop/switch isn't completed */
_L3:
        continueDrag((int)motionevent.getX(), (int)motionevent.getY());
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onDraw(Canvas canvas)
    {
        if(frozenBitmap == null) goto _L2; else goto _L1
_L1:
        canvas.drawBitmap(frozenBitmap, 0.0F, 0.0F, paint);
_L4:
        return;
_L2:
        super.onDraw(canvas);
        if(mTrackDragSort)
            mDragSortTracker.appendState();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(mDragEnabled) goto _L2; else goto _L1
_L1:
        boolean flag = super.onInterceptTouchEvent(motionevent);
_L8:
        return flag;
_L2:
        int i;
        saveTouchCoords(motionevent);
        mLastCallWasIntercept = true;
        i = 0xff & motionevent.getAction();
        if(i == 0)
        {
            if(mDragState != 0)
            {
                mIgnoreTouchEvent = true;
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            mInTouchEvent = true;
        }
        flag = false;
        if(mFloatView == null) goto _L4; else goto _L3
_L3:
        flag = true;
_L6:
        if(i == 1 || i == 3)
            mInTouchEvent = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(super.onInterceptTouchEvent(motionevent))
            flag = true;
        switch(i)
        {
        case 2: // '\002'
        default:
            if(flag)
                mCancelMethod = 1;
            else
                mCancelMethod = 2;
            break;

        case 1: // '\001'
        case 3: // '\003'
            doActionUpOrCancel();
            break;
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(mFloatView != null)
        {
            if(mFloatView.isLayoutRequested())
                measureFloatView();
            mFloatViewOnMeasured = true;
        }
        mWidthMeasureSpec = i;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        updateScrollStarts();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mIgnoreTouchEvent) goto _L2; else goto _L1
_L1:
        boolean flag;
        mIgnoreTouchEvent = false;
        flag = false;
_L4:
        return flag;
_L2:
        if(!mDragEnabled)
        {
            flag = super.onTouchEvent(motionevent);
        } else
        {
            flag = false;
            boolean flag1 = mLastCallWasIntercept;
            mLastCallWasIntercept = false;
            if(!flag1)
                saveTouchCoords(motionevent);
            if(mDragState == 4)
            {
                onDragTouchEvent(motionevent);
                flag = true;
            } else
            {
                if(mDragState == 0 && super.onTouchEvent(motionevent))
                    flag = true;
                switch(0xff & motionevent.getAction())
                {
                case 2: // '\002'
                default:
                    if(flag)
                        mCancelMethod = 1;
                    break;

                case 1: // '\001'
                case 3: // '\003'
                    doActionUpOrCancel();
                    break;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeItem(int i)
    {
        if(mDragState != 0 && mDragState != 4) goto _L2; else goto _L1
_L1:
        if(mDragState == 0)
        {
            mSrcPos = i + getHeaderViewsCount();
            mFirstExpPos = mSrcPos;
            mSecondExpPos = mSrcPos;
            mFloatPos = mSrcPos;
            View view = getChildAt(mSrcPos - getFirstVisiblePosition());
            if(view != null)
                view.setVisibility(4);
        }
        if(!mInTouchEvent) goto _L4; else goto _L3
_L3:
        mCancelMethod;
        JVM INSTR tableswitch 1 2: default 116
    //                   1 131
    //                   2 143;
           goto _L5 _L6 _L7
_L5:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_143;
_L4:
        if(mRemoveAnimator != null)
            mRemoveAnimator.start();
        else
            doRemoveItem(i);
_L2:
        return;
_L6:
        super.onTouchEvent(mCancelEvent);
          goto _L4
        super.onInterceptTouchEvent(mCancelEvent);
          goto _L4
    }

    public void requestLayout()
    {
        if(!mBlockLayoutRequests)
            super.requestLayout();
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        mAdapterWrapper = new AdapterWrapper(listadapter);
        if(listadapter != null)
        {
            listadapter.registerDataSetObserver(mObserver);
            if(listadapter instanceof DropListener)
                setDropListener((DropListener)listadapter);
            if(listadapter instanceof DragListener)
                setDragListener((DragListener)listadapter);
            if(listadapter instanceof RemoveListener)
                setRemoveListener((RemoveListener)listadapter);
        }
        super.setAdapter(mAdapterWrapper);
    }

    public void setDragEnabled(boolean flag)
    {
        mDragEnabled = flag;
    }

    public void setDragListener(DragListener draglistener)
    {
        mDragListener = draglistener;
    }

    public void setDragScrollProfile(DragScrollProfile dragscrollprofile)
    {
        if(dragscrollprofile != null)
            mScrollProfile = dragscrollprofile;
    }

    public void setDragScrollStart(float f)
    {
        setDragScrollStarts(f, f);
    }

    public void setDragScrollStarts(float f, float f1)
    {
        if(f1 > 0.5F)
            mDragDownScrollStartFrac = 0.5F;
        else
            mDragDownScrollStartFrac = f1;
        if(f > 0.5F)
            mDragUpScrollStartFrac = 0.5F;
        else
            mDragUpScrollStartFrac = f;
        if(getHeight() != 0)
            updateScrollStarts();
    }

    public void setDragSortListener(DragSortListener dragsortlistener)
    {
        setDropListener(dragsortlistener);
        setDragListener(dragsortlistener);
        setRemoveListener(dragsortlistener);
    }

    public void setDropListener(DropListener droplistener)
    {
        mDropListener = droplistener;
    }

    public void setFloatAlpha(float f)
    {
        mCurrFloatAlpha = f;
    }

    public void setFloatViewManager(FloatViewManager floatviewmanager)
    {
        mFloatViewManager = floatviewmanager;
    }

    public void setMaxScrollSpeed(float f)
    {
        mMaxScrollSpeed = f;
    }

    public void setRemoveListener(RemoveListener removelistener)
    {
        mRemoveListener = removelistener;
    }

    public boolean startDrag(int i, int j, int k, int l)
    {
        boolean flag = false;
        if(mInTouchEvent && mFloatViewManager != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        View view = mFloatViewManager.onCreateFloatView(i);
        if(view != null)
            flag = startDrag(i, view, j, k, l);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean startDrag(int i, View view, int j, int k, int l)
    {
        boolean flag = true;
        if(mDragState == 0 && mInTouchEvent && mFloatView == null && view != null) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(getParent() != null)
            getParent().requestDisallowInterceptTouchEvent(flag);
        int i1 = i + getHeaderViewsCount();
        mFirstExpPos = i1;
        mSecondExpPos = i1;
        mSrcPos = i1;
        mFloatPos = i1;
        mDragState = 4;
        mDragFlags = 0;
        mDragFlags = j | mDragFlags;
        mFloatView = view;
        measureFloatView();
        mDragDeltaX = k;
        mDragDeltaY = l;
        mDragStartY = mY;
        mFloatLoc.x = mX - mDragDeltaX;
        mFloatLoc.y = mY - mDragDeltaY;
        View view1 = getChildAt(mSrcPos - getFirstVisiblePosition());
        if(view1 != null)
            view1.setVisibility(4);
        if(mTrackDragSort)
            mDragSortTracker.startTracking();
        switch(mCancelMethod)
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_265;
_L5:
        requestLayout();
        if(mLiftAnimator != null)
            mLiftAnimator.start();
        if(true) goto _L4; else goto _L3
_L3:
        super.onTouchEvent(mCancelEvent);
          goto _L5
        super.onInterceptTouchEvent(mCancelEvent);
          goto _L5
    }

    public boolean stopDrag(boolean flag)
    {
        boolean flag1 = true;
        if(mFloatView != null)
        {
            mDragScroller.stopScrolling(flag1);
            if(flag)
                removeItem(mSrcPos - getHeaderViewsCount());
            else
            if(mDropAnimator != null)
                mDropAnimator.start();
            else
                dropFloatView();
            if(mTrackDragSort)
                mDragSortTracker.stopTracking();
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public void unfreezeView()
    {
        if(frozenBitmap != null)
        {
            frozenBitmap.recycle();
            frozenBitmap = null;
            finishDrop();
            invalidate();
        }
    }

    private static final int DRAGGING = 4;
    public static final int DRAG_NEG_X = 2;
    public static final int DRAG_NEG_Y = 8;
    public static final int DRAG_POS_X = 1;
    public static final int DRAG_POS_Y = 4;
    private static final int DROPPING = 2;
    private static final int IDLE = 0;
    private static final int NO_CANCEL = 0;
    private static final int ON_INTERCEPT_TOUCH_EVENT = 2;
    private static final int ON_TOUCH_EVENT = 1;
    private static final int REMOVING = 1;
    private static final int STOPPED = 3;
    private static final int sCacheSize = 3;
    private Bitmap frozenBitmap;
    private AdapterWrapper mAdapterWrapper;
    private boolean mAnimate;
    private boolean mBlockLayoutRequests;
    private MotionEvent mCancelEvent;
    private int mCancelMethod;
    private HeightCache mChildHeightCache;
    private float mCurrFloatAlpha;
    private int mDownScrollStartY;
    private float mDownScrollStartYF;
    private int mDragDeltaX;
    private int mDragDeltaY;
    private float mDragDownScrollHeight;
    private float mDragDownScrollStartFrac;
    private boolean mDragEnabled;
    private int mDragFlags;
    private DragListener mDragListener;
    private DragScroller mDragScroller;
    private DragSortTracker mDragSortTracker;
    private int mDragStartY;
    private int mDragState;
    private float mDragUpScrollHeight;
    private float mDragUpScrollStartFrac;
    private DropAnimator mDropAnimator;
    private DropListener mDropListener;
    private int mFirstExpPos;
    private float mFloatAlpha;
    private Point mFloatLoc;
    private int mFloatPos;
    private View mFloatView;
    private int mFloatViewHeight;
    private int mFloatViewHeightHalf;
    private boolean mFloatViewInvalidated;
    private FloatViewManager mFloatViewManager;
    private int mFloatViewMid;
    private boolean mFloatViewOnMeasured;
    private boolean mIgnoreTouchEvent;
    private boolean mInTouchEvent;
    private int mItemHeightCollapsed;
    private boolean mLastCallWasIntercept;
    private int mLastX;
    private int mLastY;
    private LiftAnimator mLiftAnimator;
    private float mMaxScrollSpeed;
    private DataSetObserver mObserver;
    private int mOffsetX;
    private int mOffsetY;
    private RemoveAnimator mRemoveAnimator;
    private RemoveListener mRemoveListener;
    private View mSampleViewTypes[];
    private DragScrollProfile mScrollProfile;
    private int mSecondExpPos;
    private float mSlideFrac;
    private float mSlideRegionFrac;
    private int mSrcPos;
    private Point mTouchLoc;
    private boolean mTrackDragSort;
    private int mUpScrollStartY;
    private float mUpScrollStartYF;
    private int mWidthMeasureSpec;
    private int mX;
    private int mY;
    private Paint paint;





/*
    static int access$102(DragSortListView dragsortlistview, int i)
    {
        dragsortlistview.mDragState = i;
        return i;
    }

*/
















/*
    static boolean access$2402(DragSortListView dragsortlistview, boolean flag)
    {
        dragsortlistview.mBlockLayoutRequests = flag;
        return flag;
    }

*/








/*
    static int access$302(DragSortListView dragsortlistview, int i)
    {
        dragsortlistview.mDragDeltaY = i;
        return i;
    }

*/






}
