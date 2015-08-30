// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;

// Referenced classes of package android.support.v4.widget:
//            EdgeEffectCompatIcs

public class EdgeEffectCompat
{
    static class EdgeEffectIcsImpl
        implements EdgeEffectImpl
    {

        public boolean draw(Object obj, Canvas canvas)
        {
            return EdgeEffectCompatIcs.draw(obj, canvas);
        }

        public void finish(Object obj)
        {
            EdgeEffectCompatIcs.finish(obj);
        }

        public boolean isFinished(Object obj)
        {
            return EdgeEffectCompatIcs.isFinished(obj);
        }

        public Object newEdgeEffect(Context context)
        {
            return EdgeEffectCompatIcs.newEdgeEffect(context);
        }

        public boolean onAbsorb(Object obj, int i)
        {
            return EdgeEffectCompatIcs.onAbsorb(obj, i);
        }

        public boolean onPull(Object obj, float f)
        {
            return EdgeEffectCompatIcs.onPull(obj, f);
        }

        public boolean onRelease(Object obj)
        {
            return EdgeEffectCompatIcs.onRelease(obj);
        }

        public void setSize(Object obj, int i, int j)
        {
            EdgeEffectCompatIcs.setSize(obj, i, j);
        }

        EdgeEffectIcsImpl()
        {
        }
    }

    static class BaseEdgeEffectImpl
        implements EdgeEffectImpl
    {

        public boolean draw(Object obj, Canvas canvas)
        {
            return false;
        }

        public void finish(Object obj)
        {
        }

        public boolean isFinished(Object obj)
        {
            return true;
        }

        public Object newEdgeEffect(Context context)
        {
            return null;
        }

        public boolean onAbsorb(Object obj, int i)
        {
            return false;
        }

        public boolean onPull(Object obj, float f)
        {
            return false;
        }

        public boolean onRelease(Object obj)
        {
            return false;
        }

        public void setSize(Object obj, int i, int j)
        {
        }

        BaseEdgeEffectImpl()
        {
        }
    }

    static interface EdgeEffectImpl
    {

        public abstract boolean draw(Object obj, Canvas canvas);

        public abstract void finish(Object obj);

        public abstract boolean isFinished(Object obj);

        public abstract Object newEdgeEffect(Context context);

        public abstract boolean onAbsorb(Object obj, int i);

        public abstract boolean onPull(Object obj, float f);

        public abstract boolean onRelease(Object obj);

        public abstract void setSize(Object obj, int i, int j);
    }


    public EdgeEffectCompat(Context context)
    {
        mEdgeEffect = IMPL.newEdgeEffect(context);
    }

    public boolean draw(Canvas canvas)
    {
        return IMPL.draw(mEdgeEffect, canvas);
    }

    public void finish()
    {
        IMPL.finish(mEdgeEffect);
    }

    public boolean isFinished()
    {
        return IMPL.isFinished(mEdgeEffect);
    }

    public boolean onAbsorb(int i)
    {
        return IMPL.onAbsorb(mEdgeEffect, i);
    }

    public boolean onPull(float f)
    {
        return IMPL.onPull(mEdgeEffect, f);
    }

    public boolean onRelease()
    {
        return IMPL.onRelease(mEdgeEffect);
    }

    public void setSize(int i, int j)
    {
        IMPL.setSize(mEdgeEffect, i, j);
    }

    private static final EdgeEffectImpl IMPL;
    private Object mEdgeEffect;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new EdgeEffectIcsImpl();
        else
            IMPL = new BaseEdgeEffectImpl();
    }
}
