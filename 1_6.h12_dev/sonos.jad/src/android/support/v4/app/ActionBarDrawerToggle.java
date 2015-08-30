// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.*;

// Referenced classes of package android.support.v4.app:
//            ActionBarDrawerToggleJellybeanMR2, ActionBarDrawerToggleHoneycomb

public class ActionBarDrawerToggle
    implements android.support.v4.widget.DrawerLayout.DrawerListener
{
    private class SlideDrawable extends InsetDrawable
        implements android.graphics.drawable.Drawable.Callback
    {

        public void draw(Canvas canvas)
        {
            int i = 1;
            copyBounds(mTmpRect);
            canvas.save();
            int j;
            int k;
            if(ViewCompat.getLayoutDirection(mActivity.getWindow().getDecorView()) == i)
                j = i;
            else
                j = 0;
            if(j != 0)
                i = -1;
            k = mTmpRect.width();
            canvas.translate(-mOffset * (float)k * mPosition * (float)i, 0.0F);
            if(j != 0 && !mHasMirroring)
            {
                canvas.translate(k, 0.0F);
                canvas.scale(-1F, 1.0F);
            }
            super.draw(canvas);
            canvas.restore();
        }

        public float getPosition()
        {
            return mPosition;
        }

        public void setOffset(float f)
        {
            mOffset = f;
            invalidateSelf();
        }

        public void setPosition(float f)
        {
            mPosition = f;
            invalidateSelf();
        }

        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;
        final ActionBarDrawerToggle this$0;

        private SlideDrawable(Drawable drawable)
        {
            boolean flag = false;
            this$0 = ActionBarDrawerToggle.this;
            super(drawable, 0);
            if(android.os.Build.VERSION.SDK_INT > 18)
                flag = true;
            mHasMirroring = flag;
            mTmpRect = new Rect();
        }

    }

    private static class ActionBarDrawerToggleImplJellybeanMR2
        implements ActionBarDrawerToggleImpl
    {

        public Drawable getThemeUpIndicator(Activity activity)
        {
            return ActionBarDrawerToggleJellybeanMR2.getThemeUpIndicator(activity);
        }

        public Object setActionBarDescription(Object obj, Activity activity, int i)
        {
            return ActionBarDrawerToggleJellybeanMR2.setActionBarDescription(obj, activity, i);
        }

        public Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i)
        {
            return ActionBarDrawerToggleJellybeanMR2.setActionBarUpIndicator(obj, activity, drawable, i);
        }

        private ActionBarDrawerToggleImplJellybeanMR2()
        {
        }

    }

    private static class ActionBarDrawerToggleImplHC
        implements ActionBarDrawerToggleImpl
    {

        public Drawable getThemeUpIndicator(Activity activity)
        {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(activity);
        }

        public Object setActionBarDescription(Object obj, Activity activity, int i)
        {
            return ActionBarDrawerToggleHoneycomb.setActionBarDescription(obj, activity, i);
        }

        public Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i)
        {
            return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(obj, activity, drawable, i);
        }

        private ActionBarDrawerToggleImplHC()
        {
        }

    }

    private static class ActionBarDrawerToggleImplBase
        implements ActionBarDrawerToggleImpl
    {

        public Drawable getThemeUpIndicator(Activity activity)
        {
            return null;
        }

        public Object setActionBarDescription(Object obj, Activity activity, int i)
        {
            return obj;
        }

        public Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i)
        {
            return obj;
        }

        private ActionBarDrawerToggleImplBase()
        {
        }

    }

    private static interface ActionBarDrawerToggleImpl
    {

        public abstract Drawable getThemeUpIndicator(Activity activity);

        public abstract Object setActionBarDescription(Object obj, Activity activity, int i);

        public abstract Object setActionBarUpIndicator(Object obj, Activity activity, Drawable drawable, int i);
    }

    public static interface Delegate
    {

        public abstract Drawable getThemeUpIndicator();

        public abstract void setActionBarDescription(int i);

        public abstract void setActionBarUpIndicator(Drawable drawable, int i);
    }

    public static interface DelegateProvider
    {

        public abstract Delegate getDrawerToggleDelegate();
    }


    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, int i, int j, int k)
    {
        boolean flag;
        if(!assumeMaterial(activity))
            flag = true;
        else
            flag = false;
        this(activity, drawerlayout, flag, i, j, k);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, boolean flag, int i, int j, int k)
    {
        mDrawerIndicatorEnabled = true;
        mActivity = activity;
        SlideDrawable slidedrawable;
        float f;
        if(activity instanceof DelegateProvider)
            mActivityImpl = ((DelegateProvider)activity).getDrawerToggleDelegate();
        else
            mActivityImpl = null;
        mDrawerLayout = drawerlayout;
        mDrawerImageResource = i;
        mOpenDrawerContentDescRes = j;
        mCloseDrawerContentDescRes = k;
        mHomeAsUpIndicator = getThemeUpIndicator();
        mDrawerImage = ContextCompat.getDrawable(activity, i);
        mSlider = new SlideDrawable(mDrawerImage);
        slidedrawable = mSlider;
        if(flag)
            f = 0.3333333F;
        else
            f = 0.0F;
        slidedrawable.setOffset(f);
    }

    private static boolean assumeMaterial(Context context)
    {
        boolean flag;
        if(context.getApplicationInfo().targetSdkVersion >= 21 && android.os.Build.VERSION.SDK_INT >= 21)
            flag = true;
        else
            flag = false;
        return flag;
    }

    Drawable getThemeUpIndicator()
    {
        Drawable drawable;
        if(mActivityImpl != null)
            drawable = mActivityImpl.getThemeUpIndicator();
        else
            drawable = IMPL.getThemeUpIndicator(mActivity);
        return drawable;
    }

    public boolean isDrawerIndicatorEnabled()
    {
        return mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(!mHasCustomUpIndicator)
            mHomeAsUpIndicator = getThemeUpIndicator();
        mDrawerImage = ContextCompat.getDrawable(mActivity, mDrawerImageResource);
        syncState();
    }

    public void onDrawerClosed(View view)
    {
        mSlider.setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mOpenDrawerContentDescRes);
    }

    public void onDrawerOpened(View view)
    {
        mSlider.setPosition(1.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mCloseDrawerContentDescRes);
    }

    public void onDrawerSlide(View view, float f)
    {
        float f1 = mSlider.getPosition();
        float f2;
        if(f > 0.5F)
            f2 = Math.max(f1, 2.0F * Math.max(0.0F, f - 0.5F));
        else
            f2 = Math.min(f1, f * 2.0F);
        mSlider.setPosition(f2);
    }

    public void onDrawerStateChanged(int i)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag;
        if(menuitem != null && menuitem.getItemId() == 0x102002c && mDrawerIndicatorEnabled)
        {
            if(mDrawerLayout.isDrawerVisible(0x800003))
                mDrawerLayout.closeDrawer(0x800003);
            else
                mDrawerLayout.openDrawer(0x800003);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    void setActionBarDescription(int i)
    {
        if(mActivityImpl != null)
            mActivityImpl.setActionBarDescription(i);
        else
            mSetIndicatorInfo = IMPL.setActionBarDescription(mSetIndicatorInfo, mActivity, i);
    }

    void setActionBarUpIndicator(Drawable drawable, int i)
    {
        if(mActivityImpl != null)
            mActivityImpl.setActionBarUpIndicator(drawable, i);
        else
            mSetIndicatorInfo = IMPL.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, drawable, i);
    }

    public void setDrawerIndicatorEnabled(boolean flag)
    {
        if(flag != mDrawerIndicatorEnabled)
        {
            if(flag)
            {
                SlideDrawable slidedrawable = mSlider;
                int i;
                if(mDrawerLayout.isDrawerOpen(0x800003))
                    i = mCloseDrawerContentDescRes;
                else
                    i = mOpenDrawerContentDescRes;
                setActionBarUpIndicator(slidedrawable, i);
            } else
            {
                setActionBarUpIndicator(mHomeAsUpIndicator, 0);
            }
            mDrawerIndicatorEnabled = flag;
        }
    }

    public void setHomeAsUpIndicator(int i)
    {
        Drawable drawable = null;
        if(i != 0)
            drawable = ContextCompat.getDrawable(mActivity, i);
        setHomeAsUpIndicator(drawable);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        if(drawable == null)
        {
            mHomeAsUpIndicator = getThemeUpIndicator();
            mHasCustomUpIndicator = false;
        } else
        {
            mHomeAsUpIndicator = drawable;
            mHasCustomUpIndicator = true;
        }
        if(!mDrawerIndicatorEnabled)
            setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }

    public void syncState()
    {
        if(mDrawerLayout.isDrawerOpen(0x800003))
            mSlider.setPosition(1.0F);
        else
            mSlider.setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
        {
            SlideDrawable slidedrawable = mSlider;
            int i;
            if(mDrawerLayout.isDrawerOpen(0x800003))
                i = mCloseDrawerContentDescRes;
            else
                i = mOpenDrawerContentDescRes;
            setActionBarUpIndicator(slidedrawable, i);
        }
    }

    private static final int ID_HOME = 0x102002c;
    private static final ActionBarDrawerToggleImpl IMPL;
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.3333333F;
    private final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private Object mSetIndicatorInfo;
    private SlideDrawable mSlider;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 18)
            IMPL = new ActionBarDrawerToggleImplJellybeanMR2();
        else
        if(i >= 11)
            IMPL = new ActionBarDrawerToggleImplHC();
        else
            IMPL = new ActionBarDrawerToggleImplBase();
    }

}
