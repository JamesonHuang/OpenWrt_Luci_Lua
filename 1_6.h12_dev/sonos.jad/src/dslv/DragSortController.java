// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dslv;

import android.graphics.Point;
import android.view.*;

// Referenced classes of package dslv:
//            SimpleFloatViewManager, DragSortListView

public class DragSortController extends SimpleFloatViewManager
    implements android.view.View.OnTouchListener, android.view.GestureDetector.OnGestureListener
{

    public DragSortController(DragSortListView dragsortlistview)
    {
        this(dragsortlistview, 0, 0, 1);
    }

    public DragSortController(DragSortListView dragsortlistview, int i, int j, int k)
    {
        this(dragsortlistview, i, j, k, 0);
    }

    public DragSortController(DragSortListView dragsortlistview, int i, int j, int k, int l)
    {
        super(dragsortlistview);
        mDragInitMode = 0;
        mSortEnabled = true;
        mRemoveEnabled = false;
        mHitPos = -1;
        mClickRemoveHitPos = -1;
        mTempLoc = new int[2];
        mDragging = false;
        mFlingSpeed = 500F;
        mOrigFloatAlpha = 1.0F;
        mDslv = dragsortlistview;
        mDetector = new GestureDetector(dragsortlistview.getContext(), this);
        mFlingRemoveDetector = new GestureDetector(dragsortlistview.getContext(), mFlingRemoveListener);
        mFlingRemoveDetector.setIsLongpressEnabled(false);
        mTouchSlop = ViewConfiguration.get(dragsortlistview.getContext()).getScaledTouchSlop();
        mDragHandleId = i;
        mClickRemoveId = l;
        setRemoveMode(k);
        setDragInitMode(j);
        mOrigFloatAlpha = dragsortlistview.getFloatAlpha();
    }

    public int dragHandleHitPosition(MotionEvent motionevent)
    {
        return viewIdHitPosition(motionevent, mDragHandleId);
    }

    public int getDragInitMode()
    {
        return mDragInitMode;
    }

    public int getRemoveMode()
    {
        return mRemoveMode;
    }

    public boolean isRemoveEnabled()
    {
        return mRemoveEnabled;
    }

    public boolean isSortEnabled()
    {
        return mSortEnabled;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        if(mRemoveEnabled && mRemoveMode == 0)
            mClickRemoveHitPos = viewIdHitPosition(motionevent, mClickRemoveId);
        mHitPos = startDragPosition(motionevent);
        if(mHitPos != -1 && mDragInitMode == 0)
            startDrag(mHitPos, (int)motionevent.getX() - mItemX, (int)motionevent.getY() - mItemY);
        return true;
    }

    public void onDragFloatView(View view, Point point, Point point1)
    {
        if(!mRemoveEnabled) goto _L2; else goto _L1
_L1:
        int i;
        i = point1.x;
        point1.y;
        if(mRemoveMode != 3) goto _L4; else goto _L3
_L3:
        int l = mDslv.getWidth();
        int i1 = l / 3;
        float f1;
        if(i < i1)
            f1 = 1.0F;
        else
        if(i < l - i1)
            f1 = (float)(l - i1 - i) / (float)i1;
        else
            f1 = 0.0F;
        mDslv.setFloatAlpha(f1 * mOrigFloatAlpha);
_L2:
        return;
_L4:
        int j;
        int k;
        float f;
        if(mRemoveMode != 4)
            continue; /* Loop/switch isn't completed */
        j = mDslv.getWidth();
        k = j / 3;
        if(i >= k)
            break; /* Loop/switch isn't completed */
        f = 0.0F;
_L6:
        mDslv.setFloatAlpha(f * mOrigFloatAlpha);
        if(true) goto _L2; else goto _L5
_L5:
        if(i < j - k)
            f = (float)(i - k) / (float)k;
        else
            f = 1.0F;
          goto _L6
        if(true) goto _L2; else goto _L7
_L7:
    }

    public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        if(mHitPos != -1 && mDragInitMode == 2)
        {
            mDslv.performHapticFeedback(0);
            startDrag(mHitPos, mCurrX - mItemX, mCurrY - mItemY);
        }
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(mHitPos == -1 || mDragInitMode != 1 || mDragging) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = (int)motionevent1.getX();
        l = (int)motionevent1.getY();
        flag = false;
        if(!mRemoveEnabled || !mSortEnabled) goto _L4; else goto _L3
_L3:
        flag = true;
_L6:
        if(flag)
            startDrag(mHitPos, k - mItemX, l - mItemY);
_L2:
        return false;
_L4:
        if(mRemoveEnabled)
        {
            if(Math.abs(k - i) > mTouchSlop)
                flag = true;
            else
                flag = false;
        } else
        if(mSortEnabled)
            if(Math.abs(l - j) > mTouchSlop)
                flag = true;
            else
                flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if(mRemoveEnabled && mRemoveMode == 0 && mClickRemoveHitPos != -1)
            mDslv.removeItem(mClickRemoveHitPos - mDslv.getHeaderViewsCount());
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        mDetector.onTouchEvent(motionevent);
        if(mRemoveEnabled && mDragging && (mRemoveMode == 1 || mRemoveMode == 2))
            mFlingRemoveDetector.onTouchEvent(motionevent);
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 88
    //                   0 90
    //                   1 111
    //                   2 88
    //                   3 187;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return false;
_L2:
        mCurrX = (int)motionevent.getX();
        mCurrY = (int)motionevent.getY();
        continue; /* Loop/switch isn't completed */
_L3:
        if(mRemoveEnabled)
        {
            int i = (int)motionevent.getX();
            int j = mDslv.getWidth() / 3;
            int k = mDslv.getWidth() - j;
            if(mRemoveMode == 3 && i > k || mRemoveMode == 4 && i < j)
                mDslv.stopDrag(true);
        }
_L4:
        mDragging = false;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setClickRemoveId(int i)
    {
        mClickRemoveId = i;
    }

    public void setDragHandleId(int i)
    {
        mDragHandleId = i;
    }

    public void setDragInitMode(int i)
    {
        mDragInitMode = i;
    }

    public void setRemoveEnabled(boolean flag)
    {
        mRemoveEnabled = flag;
    }

    public void setRemoveMode(int i)
    {
        mRemoveMode = i;
    }

    public void setSortEnabled(boolean flag)
    {
        mSortEnabled = flag;
    }

    public boolean startDrag(int i, int j, int k)
    {
        int l;
        l = 0;
        if(mSortEnabled)
            l = 0 | 0xc;
        if(!mRemoveEnabled) goto _L2; else goto _L1
_L1:
        if(mRemoveMode != 1) goto _L4; else goto _L3
_L3:
        l |= 1;
_L2:
        mDragging = mDslv.startDrag(i - mDslv.getHeaderViewsCount(), l, j, k);
        return mDragging;
_L4:
        if(mRemoveMode == 2)
            l |= 2;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public int startDragPosition(MotionEvent motionevent)
    {
        return dragHandleHitPosition(motionevent);
    }

    public int viewIdHitPosition(MotionEvent motionevent, int i)
    {
        int l;
        int i1;
        int j1;
        int k1;
        int j = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        l = mDslv.pointToPosition(j, k);
        i1 = mDslv.getHeaderViewsCount();
        j1 = mDslv.getFooterViewsCount();
        k1 = mDslv.getCount();
        if(l == -1 || l < i1 || l >= k1 - j1) goto _L2; else goto _L1
_L1:
        View view;
        int l1;
        int i2;
        View view1;
        view = mDslv.getChildAt(l - mDslv.getFirstVisiblePosition());
        l1 = (int)motionevent.getRawX();
        i2 = (int)motionevent.getRawY();
        view1 = view.findViewById(i);
        if(view1 == null) goto _L2; else goto _L3
_L3:
        view1.getLocationOnScreen(mTempLoc);
        if(l1 <= mTempLoc[0] || i2 <= mTempLoc[1] || l1 >= mTempLoc[0] + view1.getWidth() || i2 >= mTempLoc[1] + view1.getHeight()) goto _L2; else goto _L4
_L4:
        mItemX = view.getLeft();
        mItemY = view.getTop();
_L6:
        return l;
_L2:
        l = -1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static final int CLICK_REMOVE = 0;
    public static final int FLING_LEFT_REMOVE = 2;
    public static final int FLING_RIGHT_REMOVE = 1;
    public static final int MISS = -1;
    public static final int ON_DOWN = 0;
    public static final int ON_DRAG = 1;
    public static final int ON_LONG_PRESS = 2;
    public static final int SLIDE_LEFT_REMOVE = 4;
    public static final int SLIDE_RIGHT_REMOVE = 3;
    private int mClickRemoveHitPos;
    private int mClickRemoveId;
    private int mCurrX;
    private int mCurrY;
    private GestureDetector mDetector;
    private int mDragHandleId;
    private int mDragInitMode;
    private boolean mDragging;
    private DragSortListView mDslv;
    private GestureDetector mFlingRemoveDetector;
    private android.view.GestureDetector.OnGestureListener mFlingRemoveListener = new android.view.GestureDetector.SimpleOnGestureListener() {

        public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if(!mRemoveEnabled) goto _L2; else goto _L1
_L1:
            mRemoveMode;
            JVM INSTR tableswitch 1 2: default 40
        //                       1 42
        //                       2 69;
               goto _L2 _L3 _L4
_L2:
            return false;
_L3:
            if(f > mFlingSpeed)
                mDslv.stopDrag(true);
            continue; /* Loop/switch isn't completed */
_L4:
            if(f < -mFlingSpeed)
                mDslv.stopDrag(true);
            if(true) goto _L2; else goto _L5
_L5:
        }

        final DragSortController this$0;

            
            {
                this$0 = DragSortController.this;
                super();
            }
    }
;
    private float mFlingSpeed;
    private int mHitPos;
    private int mItemX;
    private int mItemY;
    private float mOrigFloatAlpha;
    private boolean mRemoveEnabled;
    private int mRemoveMode;
    private boolean mSortEnabled;
    private int mTempLoc[];
    private int mTouchSlop;




}
