// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener, ViewPropertyAnimatorCompatKK, ViewPropertyAnimatorCompatJellybeanMr2, 
//            ViewPropertyAnimatorCompatJB, ViewPropertyAnimatorCompatICS, ViewCompat

public class ViewPropertyAnimatorCompat
{
    static class KitKatViewPropertyAnimatorCompatImpl extends JBMr2ViewPropertyAnimatorCompatImpl
    {

        public void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
        {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, viewpropertyanimatorupdatelistener);
        }

        KitKatViewPropertyAnimatorCompatImpl()
        {
        }
    }

    static class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl
    {

        public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(view);
        }

        JBMr2ViewPropertyAnimatorCompatImpl()
        {
        }
    }

    static class JBViewPropertyAnimatorCompatImpl extends ICSViewPropertyAnimatorCompatImpl
    {

        public void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
        {
            ViewPropertyAnimatorCompatJB.setListener(view, viewpropertyanimatorlistener);
        }

        public void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            ViewPropertyAnimatorCompatJB.withEndAction(view, runnable);
        }

        public void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            ViewPropertyAnimatorCompatJB.withLayer(view);
        }

        public void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            ViewPropertyAnimatorCompatJB.withStartAction(view, runnable);
        }

        JBViewPropertyAnimatorCompatImpl()
        {
        }
    }

    static class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl
    {
        static class MyVpaListener
            implements ViewPropertyAnimatorListener
        {

            public void onAnimationCancel(View view)
            {
                Object obj = view.getTag(0x7e000000);
                ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
                if(obj instanceof ViewPropertyAnimatorListener)
                    viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
                if(viewpropertyanimatorlistener != null)
                    viewpropertyanimatorlistener.onAnimationCancel(view);
            }

            public void onAnimationEnd(View view)
            {
                if(mVpa.mOldLayerType >= 0)
                {
                    ViewCompat.setLayerType(view, mVpa.mOldLayerType, null);
                    mVpa.mOldLayerType = -1;
                }
                if(mVpa.mEndAction != null)
                    mVpa.mEndAction.run();
                Object obj = view.getTag(0x7e000000);
                ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
                if(obj instanceof ViewPropertyAnimatorListener)
                    viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
                if(viewpropertyanimatorlistener != null)
                    viewpropertyanimatorlistener.onAnimationEnd(view);
            }

            public void onAnimationStart(View view)
            {
                if(mVpa.mOldLayerType >= 0)
                    ViewCompat.setLayerType(view, 2, null);
                if(mVpa.mStartAction != null)
                    mVpa.mStartAction.run();
                Object obj = view.getTag(0x7e000000);
                ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
                if(obj instanceof ViewPropertyAnimatorListener)
                    viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
                if(viewpropertyanimatorlistener != null)
                    viewpropertyanimatorlistener.onAnimationStart(view);
            }

            ViewPropertyAnimatorCompat mVpa;

            MyVpaListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
            {
                mVpa = viewpropertyanimatorcompat;
            }
        }


        public void alpha(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.alpha(view, f);
        }

        public void alphaBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.alphaBy(view, f);
        }

        public void cancel(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            ViewPropertyAnimatorCompatICS.cancel(view);
        }

        public long getDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return ViewPropertyAnimatorCompatICS.getDuration(view);
        }

        public long getStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return ViewPropertyAnimatorCompatICS.getStartDelay(view);
        }

        public void rotation(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotation(view, f);
        }

        public void rotationBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotationBy(view, f);
        }

        public void rotationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotationX(view, f);
        }

        public void rotationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotationXBy(view, f);
        }

        public void rotationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotationY(view, f);
        }

        public void rotationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.rotationYBy(view, f);
        }

        public void scaleX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.scaleX(view, f);
        }

        public void scaleXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.scaleXBy(view, f);
        }

        public void scaleY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.scaleY(view, f);
        }

        public void scaleYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.scaleYBy(view, f);
        }

        public void setDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l)
        {
            ViewPropertyAnimatorCompatICS.setDuration(view, l);
        }

        public void setInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Interpolator interpolator)
        {
            ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator);
        }

        public void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
        {
            view.setTag(0x7e000000, viewpropertyanimatorlistener);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
        }

        public void setStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l)
        {
            ViewPropertyAnimatorCompatICS.setStartDelay(view, l);
        }

        public void start(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            ViewPropertyAnimatorCompatICS.start(view);
        }

        public void translationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.translationX(view, f);
        }

        public void translationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.translationXBy(view, f);
        }

        public void translationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.translationY(view, f);
        }

        public void translationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.translationYBy(view, f);
        }

        public void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
            viewpropertyanimatorcompat.mEndAction = runnable;
        }

        public void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            viewpropertyanimatorcompat.mOldLayerType = ViewCompat.getLayerType(view);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
        }

        public void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewpropertyanimatorcompat));
            viewpropertyanimatorcompat.mStartAction = runnable;
        }

        public void x(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.x(view, f);
        }

        public void xBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.xBy(view, f);
        }

        public void y(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.y(view, f);
        }

        public void yBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            ViewPropertyAnimatorCompatICS.yBy(view, f);
        }

        WeakHashMap mLayerMap;

        ICSViewPropertyAnimatorCompatImpl()
        {
            mLayerMap = null;
        }
    }

    static class BaseViewPropertyAnimatorCompatImpl
        implements ViewPropertyAnimatorCompatImpl
    {
        class Starter
            implements Runnable
        {

            public void run()
            {
                View view = (View)mViewRef.get();
                if(view != null)
                    startAnimation(mVpa, view);
            }

            WeakReference mViewRef;
            ViewPropertyAnimatorCompat mVpa;
            final BaseViewPropertyAnimatorCompatImpl this$0;

            private Starter(View view)
            {
                this$0 = BaseViewPropertyAnimatorCompatImpl.this;
                super();
                mViewRef = new WeakReference(view);
                mVpa = ViewPropertyAnimatorCompat.this;
            }

        }


        private void postStartMessage(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            Object obj = null;
            if(mStarterMap != null)
                obj = (Runnable)mStarterMap.get(view);
            if(obj == null)
            {
                obj = viewpropertyanimatorcompat. new Starter(view);
                if(mStarterMap == null)
                    mStarterMap = new WeakHashMap();
                mStarterMap.put(view, obj);
            }
            view.removeCallbacks(((Runnable) (obj)));
            view.post(((Runnable) (obj)));
        }

        private void removeStartMessage(View view)
        {
            if(mStarterMap != null)
            {
                Runnable runnable = (Runnable)mStarterMap.get(view);
                if(runnable != null)
                    view.removeCallbacks(runnable);
            }
        }

        private void startAnimation(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            Object obj = view.getTag(0x7e000000);
            ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
            if(obj instanceof ViewPropertyAnimatorListener)
                viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
            Runnable runnable = viewpropertyanimatorcompat.mStartAction;
            Runnable runnable1 = viewpropertyanimatorcompat.mEndAction;
            if(runnable != null)
                runnable.run();
            if(viewpropertyanimatorlistener != null)
            {
                viewpropertyanimatorlistener.onAnimationStart(view);
                viewpropertyanimatorlistener.onAnimationEnd(view);
            }
            if(runnable1 != null)
                runnable1.run();
            if(mStarterMap != null)
                mStarterMap.remove(view);
        }

        public void alpha(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void alphaBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void cancel(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public long getDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return 0L;
        }

        public Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return null;
        }

        public long getStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            return 0L;
        }

        public void rotation(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void rotationBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void rotationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void rotationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void rotationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void rotationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void scaleX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void scaleXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void scaleY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void scaleYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void setDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l)
        {
        }

        public void setInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Interpolator interpolator)
        {
        }

        public void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener)
        {
            view.setTag(0x7e000000, viewpropertyanimatorlistener);
        }

        public void setStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l)
        {
        }

        public void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
        {
        }

        public void start(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
            removeStartMessage(view);
            startAnimation(viewpropertyanimatorcompat, view);
        }

        public void translationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void translationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void translationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void translationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            viewpropertyanimatorcompat.mEndAction = runnable;
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view)
        {
        }

        public void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable)
        {
            viewpropertyanimatorcompat.mStartAction = runnable;
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void x(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void xBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void y(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        public void yBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f)
        {
            postStartMessage(viewpropertyanimatorcompat, view);
        }

        WeakHashMap mStarterMap;


        BaseViewPropertyAnimatorCompatImpl()
        {
            mStarterMap = null;
        }
    }

    static interface ViewPropertyAnimatorCompatImpl
    {

        public abstract void alpha(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void alphaBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void cancel(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract long getDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract Interpolator getInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract long getStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract void rotation(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void rotationBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void rotationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void rotationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void rotationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void rotationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void scaleX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void scaleXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void scaleY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void scaleYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void setDuration(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l);

        public abstract void setInterpolator(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Interpolator interpolator);

        public abstract void setListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorListener viewpropertyanimatorlistener);

        public abstract void setStartDelay(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, long l);

        public abstract void setUpdateListener(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener);

        public abstract void start(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract void translationX(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void translationXBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void translationY(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void translationYBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void withEndAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable);

        public abstract void withLayer(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view);

        public abstract void withStartAction(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, Runnable runnable);

        public abstract void x(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void xBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void y(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);

        public abstract void yBy(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, View view, float f);
    }


    ViewPropertyAnimatorCompat(View view)
    {
        mStartAction = null;
        mEndAction = null;
        mOldLayerType = -1;
        mView = new WeakReference(view);
    }

    public ViewPropertyAnimatorCompat alpha(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.alpha(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.alphaBy(this, view, f);
        return this;
    }

    public void cancel()
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.cancel(this, view);
    }

    public long getDuration()
    {
        View view = (View)mView.get();
        long l;
        if(view != null)
            l = IMPL.getDuration(this, view);
        else
            l = 0L;
        return l;
    }

    public Interpolator getInterpolator()
    {
        View view = (View)mView.get();
        Interpolator interpolator;
        if(view != null)
            interpolator = IMPL.getInterpolator(this, view);
        else
            interpolator = null;
        return interpolator;
    }

    public long getStartDelay()
    {
        View view = (View)mView.get();
        long l;
        if(view != null)
            l = IMPL.getStartDelay(this, view);
        else
            l = 0L;
        return l;
    }

    public ViewPropertyAnimatorCompat rotation(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotation(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotationBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotationX(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotationXBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotationY(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.rotationYBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.scaleX(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.scaleXBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.scaleY(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.scaleYBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long l)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.setDuration(this, view, l);
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.setInterpolator(this, view, interpolator);
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.setListener(this, view, viewpropertyanimatorlistener);
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long l)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.setStartDelay(this, view, l);
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.setUpdateListener(this, view, viewpropertyanimatorupdatelistener);
        return this;
    }

    public void start()
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.start(this, view);
    }

    public ViewPropertyAnimatorCompat translationX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.translationX(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.translationXBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.translationY(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.translationYBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.withEndAction(this, view, runnable);
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer()
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.withLayer(this, view);
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.withStartAction(this, view, runnable);
        return this;
    }

    public ViewPropertyAnimatorCompat x(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.x(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.xBy(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.y(this, view, f);
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            IMPL.yBy(this, view, f);
        return this;
    }

    static final ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 0x7e000000;
    private static final String TAG = "ViewAnimatorCompat";
    private Runnable mEndAction;
    private int mOldLayerType;
    private Runnable mStartAction;
    private WeakReference mView;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 19)
            IMPL = new KitKatViewPropertyAnimatorCompatImpl();
        else
        if(i >= 18)
            IMPL = new JBMr2ViewPropertyAnimatorCompatImpl();
        else
        if(i >= 16)
            IMPL = new JBViewPropertyAnimatorCompatImpl();
        else
        if(i >= 14)
            IMPL = new ICSViewPropertyAnimatorCompatImpl();
        else
            IMPL = new BaseViewPropertyAnimatorCompatImpl();
    }



/*
    static Runnable access$002(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, Runnable runnable)
    {
        viewpropertyanimatorcompat.mEndAction = runnable;
        return runnable;
    }

*/



/*
    static Runnable access$102(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, Runnable runnable)
    {
        viewpropertyanimatorcompat.mStartAction = runnable;
        return runnable;
    }

*/



/*
    static int access$402(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, int i)
    {
        viewpropertyanimatorcompat.mOldLayerType = i;
        return i;
    }

*/
}
