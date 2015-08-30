// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.ViewConfiguration;

// Referenced classes of package android.support.v4.view:
//            ViewConfigurationCompatICS, ViewConfigurationCompatFroyo

public class ViewConfigurationCompat
{
    static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl
    {

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewconfiguration);
        }

        IcsViewConfigurationVersionImpl()
        {
        }
    }

    static class HoneycombViewConfigurationVersionImpl extends FroyoViewConfigurationVersionImpl
    {

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return false;
        }

        HoneycombViewConfigurationVersionImpl()
        {
        }
    }

    static class FroyoViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl
    {

        public int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
        {
            return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewconfiguration);
        }

        FroyoViewConfigurationVersionImpl()
        {
        }
    }

    static class BaseViewConfigurationVersionImpl
        implements ViewConfigurationVersionImpl
    {

        public int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
        {
            return viewconfiguration.getScaledTouchSlop();
        }

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return true;
        }

        BaseViewConfigurationVersionImpl()
        {
        }
    }

    static interface ViewConfigurationVersionImpl
    {

        public abstract int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration);

        public abstract boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration);
    }


    public ViewConfigurationCompat()
    {
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
    {
        return IMPL.getScaledPagingTouchSlop(viewconfiguration);
    }

    public static boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
    {
        return IMPL.hasPermanentMenuKey(viewconfiguration);
    }

    static final ViewConfigurationVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new IcsViewConfigurationVersionImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new HoneycombViewConfigurationVersionImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 8)
            IMPL = new FroyoViewConfigurationVersionImpl();
        else
            IMPL = new BaseViewConfigurationVersionImpl();
    }
}
