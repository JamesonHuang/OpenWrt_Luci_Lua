// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

// Referenced classes of package android.support.v4.widget:
//            ScrollerCompatIcs, ScrollerCompatGingerbread

public class ScrollerCompat
{
    static class ScrollerCompatImplIcs extends ScrollerCompatImplGingerbread
    {

        public float getCurrVelocity(Object obj)
        {
            return ScrollerCompatIcs.getCurrVelocity(obj);
        }

        ScrollerCompatImplIcs()
        {
        }
    }

    static class ScrollerCompatImplGingerbread
        implements ScrollerCompatImpl
    {

        public void abortAnimation(Object obj)
        {
            ScrollerCompatGingerbread.abortAnimation(obj);
        }

        public boolean computeScrollOffset(Object obj)
        {
            return ScrollerCompatGingerbread.computeScrollOffset(obj);
        }

        public Object createScroller(Context context, Interpolator interpolator)
        {
            return ScrollerCompatGingerbread.createScroller(context, interpolator);
        }

        public void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            ScrollerCompatGingerbread.fling(obj, i, j, k, l, i1, j1, k1, l1);
        }

        public void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1, int i2, int j2)
        {
            ScrollerCompatGingerbread.fling(obj, i, j, k, l, i1, j1, k1, l1, i2, j2);
        }

        public float getCurrVelocity(Object obj)
        {
            return 0.0F;
        }

        public int getCurrX(Object obj)
        {
            return ScrollerCompatGingerbread.getCurrX(obj);
        }

        public int getCurrY(Object obj)
        {
            return ScrollerCompatGingerbread.getCurrY(obj);
        }

        public int getFinalX(Object obj)
        {
            return ScrollerCompatGingerbread.getFinalX(obj);
        }

        public int getFinalY(Object obj)
        {
            return ScrollerCompatGingerbread.getFinalY(obj);
        }

        public boolean isFinished(Object obj)
        {
            return ScrollerCompatGingerbread.isFinished(obj);
        }

        public boolean isOverScrolled(Object obj)
        {
            return ScrollerCompatGingerbread.isOverScrolled(obj);
        }

        public void notifyHorizontalEdgeReached(Object obj, int i, int j, int k)
        {
            ScrollerCompatGingerbread.notifyHorizontalEdgeReached(obj, i, j, k);
        }

        public void notifyVerticalEdgeReached(Object obj, int i, int j, int k)
        {
            ScrollerCompatGingerbread.notifyVerticalEdgeReached(obj, i, j, k);
        }

        public void startScroll(Object obj, int i, int j, int k, int l)
        {
            ScrollerCompatGingerbread.startScroll(obj, i, j, k, l);
        }

        public void startScroll(Object obj, int i, int j, int k, int l, int i1)
        {
            ScrollerCompatGingerbread.startScroll(obj, i, j, k, l, i1);
        }

        ScrollerCompatImplGingerbread()
        {
        }
    }

    static class ScrollerCompatImplBase
        implements ScrollerCompatImpl
    {

        public void abortAnimation(Object obj)
        {
            ((Scroller)obj).abortAnimation();
        }

        public boolean computeScrollOffset(Object obj)
        {
            return ((Scroller)obj).computeScrollOffset();
        }

        public Object createScroller(Context context, Interpolator interpolator)
        {
            Scroller scroller;
            if(interpolator != null)
                scroller = new Scroller(context, interpolator);
            else
                scroller = new Scroller(context);
            return scroller;
        }

        public void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            ((Scroller)obj).fling(i, j, k, l, i1, j1, k1, l1);
        }

        public void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1, int i2, int j2)
        {
            ((Scroller)obj).fling(i, j, k, l, i1, j1, k1, l1);
        }

        public float getCurrVelocity(Object obj)
        {
            return 0.0F;
        }

        public int getCurrX(Object obj)
        {
            return ((Scroller)obj).getCurrX();
        }

        public int getCurrY(Object obj)
        {
            return ((Scroller)obj).getCurrY();
        }

        public int getFinalX(Object obj)
        {
            return ((Scroller)obj).getFinalX();
        }

        public int getFinalY(Object obj)
        {
            return ((Scroller)obj).getFinalY();
        }

        public boolean isFinished(Object obj)
        {
            return ((Scroller)obj).isFinished();
        }

        public boolean isOverScrolled(Object obj)
        {
            return false;
        }

        public void notifyHorizontalEdgeReached(Object obj, int i, int j, int k)
        {
        }

        public void notifyVerticalEdgeReached(Object obj, int i, int j, int k)
        {
        }

        public void startScroll(Object obj, int i, int j, int k, int l)
        {
            ((Scroller)obj).startScroll(i, j, k, l);
        }

        public void startScroll(Object obj, int i, int j, int k, int l, int i1)
        {
            ((Scroller)obj).startScroll(i, j, k, l, i1);
        }

        ScrollerCompatImplBase()
        {
        }
    }

    static interface ScrollerCompatImpl
    {

        public abstract void abortAnimation(Object obj);

        public abstract boolean computeScrollOffset(Object obj);

        public abstract Object createScroller(Context context, Interpolator interpolator);

        public abstract void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1);

        public abstract void fling(Object obj, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1, int i2, int j2);

        public abstract float getCurrVelocity(Object obj);

        public abstract int getCurrX(Object obj);

        public abstract int getCurrY(Object obj);

        public abstract int getFinalX(Object obj);

        public abstract int getFinalY(Object obj);

        public abstract boolean isFinished(Object obj);

        public abstract boolean isOverScrolled(Object obj);

        public abstract void notifyHorizontalEdgeReached(Object obj, int i, int j, int k);

        public abstract void notifyVerticalEdgeReached(Object obj, int i, int j, int k);

        public abstract void startScroll(Object obj, int i, int j, int k, int l);

        public abstract void startScroll(Object obj, int i, int j, int k, int l, int i1);
    }


    private ScrollerCompat(int i, Context context, Interpolator interpolator)
    {
        if(i >= 14)
            mImpl = new ScrollerCompatImplIcs();
        else
        if(i >= 9)
            mImpl = new ScrollerCompatImplGingerbread();
        else
            mImpl = new ScrollerCompatImplBase();
        mScroller = mImpl.createScroller(context, interpolator);
    }

    ScrollerCompat(Context context, Interpolator interpolator)
    {
        this(android.os.Build.VERSION.SDK_INT, context, interpolator);
    }

    public static ScrollerCompat create(Context context)
    {
        return create(context, null);
    }

    public static ScrollerCompat create(Context context, Interpolator interpolator)
    {
        return new ScrollerCompat(context, interpolator);
    }

    public void abortAnimation()
    {
        mImpl.abortAnimation(mScroller);
    }

    public boolean computeScrollOffset()
    {
        return mImpl.computeScrollOffset(mScroller);
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        mImpl.fling(mScroller, i, j, k, l, i1, j1, k1, l1);
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2)
    {
        mImpl.fling(mScroller, i, j, k, l, i1, j1, k1, l1, i2, j2);
    }

    public float getCurrVelocity()
    {
        return mImpl.getCurrVelocity(mScroller);
    }

    public int getCurrX()
    {
        return mImpl.getCurrX(mScroller);
    }

    public int getCurrY()
    {
        return mImpl.getCurrY(mScroller);
    }

    public int getFinalX()
    {
        return mImpl.getFinalX(mScroller);
    }

    public int getFinalY()
    {
        return mImpl.getFinalY(mScroller);
    }

    public boolean isFinished()
    {
        return mImpl.isFinished(mScroller);
    }

    public boolean isOverScrolled()
    {
        return mImpl.isOverScrolled(mScroller);
    }

    public void notifyHorizontalEdgeReached(int i, int j, int k)
    {
        mImpl.notifyHorizontalEdgeReached(mScroller, i, j, k);
    }

    public void notifyVerticalEdgeReached(int i, int j, int k)
    {
        mImpl.notifyVerticalEdgeReached(mScroller, i, j, k);
    }

    public void startScroll(int i, int j, int k, int l)
    {
        mImpl.startScroll(mScroller, i, j, k, l);
    }

    public void startScroll(int i, int j, int k, int l, int i1)
    {
        mImpl.startScroll(mScroller, i, j, k, l, i1);
    }

    static final int CHASE_FRAME_TIME = 16;
    private static final String TAG = "ScrollerCompat";
    ScrollerCompatImpl mImpl;
    Object mScroller;
}
