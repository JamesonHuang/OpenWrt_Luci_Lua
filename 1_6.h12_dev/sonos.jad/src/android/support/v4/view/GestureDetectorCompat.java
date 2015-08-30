// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.*;

// Referenced classes of package android.support.v4.view:
//            MotionEventCompat, VelocityTrackerCompat

public class GestureDetectorCompat
{
    static class GestureDetectorCompatImplJellybeanMr2
        implements GestureDetectorCompatImpl
    {

        public boolean isLongpressEnabled()
        {
            return mDetector.isLongpressEnabled();
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            return mDetector.onTouchEvent(motionevent);
        }

        public void setIsLongpressEnabled(boolean flag)
        {
            mDetector.setIsLongpressEnabled(flag);
        }

        public void setOnDoubleTapListener(android.view.GestureDetector.OnDoubleTapListener ondoubletaplistener)
        {
            mDetector.setOnDoubleTapListener(ondoubletaplistener);
        }

        private final GestureDetector mDetector;

        public GestureDetectorCompatImplJellybeanMr2(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener, Handler handler)
        {
            mDetector = new GestureDetector(context, ongesturelistener, handler);
        }
    }

    static class GestureDetectorCompatImplBase
        implements GestureDetectorCompatImpl
    {
        private class GestureHandler extends Handler
        {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 3: default 32
            //                           1 59
            //                           2 79
            //                           3 89;
                   goto _L1 _L2 _L3 _L4
_L1:
                throw new RuntimeException((new StringBuilder()).append("Unknown message ").append(message).toString());
_L2:
                mListener.onShowPress(mCurrentDownEvent);
_L6:
                return;
_L3:
                dispatchLongPress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(mDoubleTapListener != null)
                    if(!mStillDown)
                        mDoubleTapListener.onSingleTapConfirmed(mCurrentDownEvent);
                    else
                        mDeferConfirmSingleTap = true;
                if(true) goto _L6; else goto _L5
_L5:
            }

            final GestureDetectorCompatImplBase this$0;

            GestureHandler()
            {
                this$0 = GestureDetectorCompatImplBase.this;
                super();
            }

            GestureHandler(Handler handler)
            {
                this$0 = GestureDetectorCompatImplBase.this;
                super(handler.getLooper());
            }
        }


        private void cancel()
        {
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mVelocityTracker.recycle();
            mVelocityTracker = null;
            mIsDoubleTapping = false;
            mStillDown = false;
            mAlwaysInTapRegion = false;
            mAlwaysInBiggerTapRegion = false;
            mDeferConfirmSingleTap = false;
            if(mInLongPress)
                mInLongPress = false;
        }

        private void cancelTaps()
        {
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mIsDoubleTapping = false;
            mAlwaysInTapRegion = false;
            mAlwaysInBiggerTapRegion = false;
            mDeferConfirmSingleTap = false;
            if(mInLongPress)
                mInLongPress = false;
        }

        private void dispatchLongPress()
        {
            mHandler.removeMessages(3);
            mDeferConfirmSingleTap = false;
            mInLongPress = true;
            mListener.onLongPress(mCurrentDownEvent);
        }

        private void init(Context context)
        {
            if(context == null)
                throw new IllegalArgumentException("Context must not be null");
            if(mListener == null)
            {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            } else
            {
                mIsLongpressEnabled = true;
                ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
                int i = viewconfiguration.getScaledTouchSlop();
                int j = viewconfiguration.getScaledDoubleTapSlop();
                mMinimumFlingVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
                mMaximumFlingVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
                mTouchSlopSquare = i * i;
                mDoubleTapSlopSquare = j * j;
                return;
            }
        }

        private boolean isConsideredDoubleTap(MotionEvent motionevent, MotionEvent motionevent1, MotionEvent motionevent2)
        {
            boolean flag;
            flag = false;
            break MISSING_BLOCK_LABEL_3;
            while(true) 
            {
                do
                    return flag;
                while(!mAlwaysInBiggerTapRegion || motionevent2.getEventTime() - motionevent1.getEventTime() > (long)DOUBLE_TAP_TIMEOUT);
                int i = (int)motionevent.getX() - (int)motionevent2.getX();
                int j = (int)motionevent.getY() - (int)motionevent2.getY();
                if(i * i + j * j < mDoubleTapSlopSquare)
                    flag = true;
            }
        }

        public boolean isLongpressEnabled()
        {
            return mIsLongpressEnabled;
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            int k;
            float f2;
            float f3;
            boolean flag1;
            int i = motionevent.getAction();
            if(mVelocityTracker == null)
                mVelocityTracker = VelocityTracker.obtain();
            mVelocityTracker.addMovement(motionevent);
            boolean flag;
            int j;
            float f;
            float f1;
            int l;
            if((i & 0xff) == 6)
                flag = true;
            else
                flag = false;
            if(flag)
                j = MotionEventCompat.getActionIndex(motionevent);
            else
                j = -1;
            f = 0.0F;
            f1 = 0.0F;
            k = MotionEventCompat.getPointerCount(motionevent);
            l = 0;
            while(l < k) 
            {
                if(j != l)
                {
                    f += MotionEventCompat.getX(motionevent, l);
                    f1 += MotionEventCompat.getY(motionevent, l);
                }
                l++;
            }
            int i1;
            if(flag)
                i1 = k - 1;
            else
                i1 = k;
            f2 = f / (float)i1;
            f3 = f1 / (float)i1;
            flag1 = false;
            i & 0xff;
            JVM INSTR tableswitch 0 6: default 196
        //                       0 383
        //                       1 879
        //                       2 648
        //                       3 1144
        //                       4 196
        //                       5 206
        //                       6 237;
               goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
            return flag1;
_L6:
            mLastFocusX = f2;
            mDownFocusX = f2;
            mLastFocusY = f3;
            mDownFocusY = f3;
            cancelTaps();
            continue; /* Loop/switch isn't completed */
_L7:
            int j2;
            float f8;
            float f9;
            int l2;
            mLastFocusX = f2;
            mDownFocusX = f2;
            mLastFocusY = f3;
            mDownFocusY = f3;
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
            j2 = MotionEventCompat.getActionIndex(motionevent);
            int k2 = MotionEventCompat.getPointerId(motionevent, j2);
            f8 = VelocityTrackerCompat.getXVelocity(mVelocityTracker, k2);
            f9 = VelocityTrackerCompat.getYVelocity(mVelocityTracker, k2);
            l2 = 0;
_L9:
            if(l2 >= k)
                continue; /* Loop/switch isn't completed */
            if(l2 != j2)
                break; /* Loop/switch isn't completed */
_L11:
            l2++;
            int i3;
            if(true) goto _L9; else goto _L8
_L8:
            if(f8 * VelocityTrackerCompat.getXVelocity(mVelocityTracker, i3 = MotionEventCompat.getPointerId(motionevent, l2)) + f9 * VelocityTrackerCompat.getYVelocity(mVelocityTracker, i3) >= 0.0F) goto _L11; else goto _L10
_L10:
            mVelocityTracker.clear();
            continue; /* Loop/switch isn't completed */
_L2:
            if(mDoubleTapListener != null)
            {
                boolean flag2 = mHandler.hasMessages(3);
                if(flag2)
                    mHandler.removeMessages(3);
                if(mCurrentDownEvent != null && mPreviousUpEvent != null && flag2 && isConsideredDoubleTap(mCurrentDownEvent, mPreviousUpEvent, motionevent))
                {
                    mIsDoubleTapping = true;
                    flag1 = false | mDoubleTapListener.onDoubleTap(mCurrentDownEvent) | mDoubleTapListener.onDoubleTapEvent(motionevent);
                } else
                {
                    mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
                }
            }
            mLastFocusX = f2;
            mDownFocusX = f2;
            mLastFocusY = f3;
            mDownFocusY = f3;
            if(mCurrentDownEvent != null)
                mCurrentDownEvent.recycle();
            mCurrentDownEvent = MotionEvent.obtain(motionevent);
            mAlwaysInTapRegion = true;
            mAlwaysInBiggerTapRegion = true;
            mStillDown = true;
            mInLongPress = false;
            mDeferConfirmSingleTap = false;
            if(mIsLongpressEnabled)
            {
                mHandler.removeMessages(2);
                mHandler.sendEmptyMessageAtTime(2, mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT + (long)LONGPRESS_TIMEOUT);
            }
            mHandler.sendEmptyMessageAtTime(1, mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT);
            flag1 |= mListener.onDown(motionevent);
            continue; /* Loop/switch isn't completed */
_L4:
            if(!mInLongPress)
            {
                float f6 = mLastFocusX - f2;
                float f7 = mLastFocusY - f3;
                if(mIsDoubleTapping)
                    flag1 = false | mDoubleTapListener.onDoubleTapEvent(motionevent);
                else
                if(mAlwaysInTapRegion)
                {
                    int k1 = (int)(f2 - mDownFocusX);
                    int l1 = (int)(f3 - mDownFocusY);
                    int i2 = k1 * k1 + l1 * l1;
                    if(i2 > mTouchSlopSquare)
                    {
                        flag1 = mListener.onScroll(mCurrentDownEvent, motionevent, f6, f7);
                        mLastFocusX = f2;
                        mLastFocusY = f3;
                        mAlwaysInTapRegion = false;
                        mHandler.removeMessages(3);
                        mHandler.removeMessages(1);
                        mHandler.removeMessages(2);
                    }
                    if(i2 > mTouchSlopSquare)
                        mAlwaysInBiggerTapRegion = false;
                } else
                if(Math.abs(f6) >= 1.0F || Math.abs(f7) >= 1.0F)
                {
                    flag1 = mListener.onScroll(mCurrentDownEvent, motionevent, f6, f7);
                    mLastFocusX = f2;
                    mLastFocusY = f3;
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            MotionEvent motionevent1;
            mStillDown = false;
            motionevent1 = MotionEvent.obtain(motionevent);
            if(!mIsDoubleTapping) goto _L13; else goto _L12
_L12:
            flag1 = false | mDoubleTapListener.onDoubleTapEvent(motionevent);
_L14:
            if(mPreviousUpEvent != null)
                mPreviousUpEvent.recycle();
            mPreviousUpEvent = motionevent1;
            if(mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
            mIsDoubleTapping = false;
            mDeferConfirmSingleTap = false;
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            continue; /* Loop/switch isn't completed */
_L13:
            if(mInLongPress)
            {
                mHandler.removeMessages(3);
                mInLongPress = false;
            } else
            if(mAlwaysInTapRegion)
            {
                flag1 = mListener.onSingleTapUp(motionevent);
                if(mDeferConfirmSingleTap && mDoubleTapListener != null)
                    mDoubleTapListener.onSingleTapConfirmed(motionevent);
            } else
            {
                VelocityTracker velocitytracker = mVelocityTracker;
                int j1 = MotionEventCompat.getPointerId(motionevent, 0);
                velocitytracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                float f4 = VelocityTrackerCompat.getYVelocity(velocitytracker, j1);
                float f5 = VelocityTrackerCompat.getXVelocity(velocitytracker, j1);
                if(Math.abs(f4) > (float)mMinimumFlingVelocity || Math.abs(f5) > (float)mMinimumFlingVelocity)
                    flag1 = mListener.onFling(mCurrentDownEvent, motionevent, f5, f4);
            }
            if(true) goto _L14; else goto _L5
_L5:
            cancel();
            if(true) goto _L1; else goto _L15
_L15:
        }

        public void setIsLongpressEnabled(boolean flag)
        {
            mIsLongpressEnabled = flag;
        }

        public void setOnDoubleTapListener(android.view.GestureDetector.OnDoubleTapListener ondoubletaplistener)
        {
            mDoubleTapListener = ondoubletaplistener;
        }

        private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
        private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        private static final int LONG_PRESS = 2;
        private static final int SHOW_PRESS = 1;
        private static final int TAP = 3;
        private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        private MotionEvent mCurrentDownEvent;
        private boolean mDeferConfirmSingleTap;
        private android.view.GestureDetector.OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        private final android.view.GestureDetector.OnGestureListener mListener;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        private boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;








/*
        static boolean access$502(GestureDetectorCompatImplBase gesturedetectorcompatimplbase, boolean flag)
        {
            gesturedetectorcompatimplbase.mDeferConfirmSingleTap = flag;
            return flag;
        }

*/

        public GestureDetectorCompatImplBase(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener, Handler handler)
        {
            if(handler != null)
                mHandler = new GestureHandler(handler);
            else
                mHandler = new GestureHandler();
            mListener = ongesturelistener;
            if(ongesturelistener instanceof android.view.GestureDetector.OnDoubleTapListener)
                setOnDoubleTapListener((android.view.GestureDetector.OnDoubleTapListener)ongesturelistener);
            init(context);
        }
    }

    static interface GestureDetectorCompatImpl
    {

        public abstract boolean isLongpressEnabled();

        public abstract boolean onTouchEvent(MotionEvent motionevent);

        public abstract void setIsLongpressEnabled(boolean flag);

        public abstract void setOnDoubleTapListener(android.view.GestureDetector.OnDoubleTapListener ondoubletaplistener);
    }


    public GestureDetectorCompat(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener)
    {
        this(context, ongesturelistener, null);
    }

    public GestureDetectorCompat(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener, Handler handler)
    {
        if(android.os.Build.VERSION.SDK_INT > 17)
            mImpl = new GestureDetectorCompatImplJellybeanMr2(context, ongesturelistener, handler);
        else
            mImpl = new GestureDetectorCompatImplBase(context, ongesturelistener, handler);
    }

    public boolean isLongpressEnabled()
    {
        return mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return mImpl.onTouchEvent(motionevent);
    }

    public void setIsLongpressEnabled(boolean flag)
    {
        mImpl.setIsLongpressEnabled(flag);
    }

    public void setOnDoubleTapListener(android.view.GestureDetector.OnDoubleTapListener ondoubletaplistener)
    {
        mImpl.setOnDoubleTapListener(ondoubletaplistener);
    }

    private final GestureDetectorCompatImpl mImpl;
}
