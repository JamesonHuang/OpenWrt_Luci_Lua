// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            ListPopupWindowCompatKitKat

public class ListPopupWindowCompat
{
    static class KitKatListPopupWindowImpl extends BaseListPopupWindowImpl
    {

        public android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view)
        {
            return ListPopupWindowCompatKitKat.createDragToOpenListener(obj, view);
        }

        KitKatListPopupWindowImpl()
        {
        }
    }

    static class BaseListPopupWindowImpl
        implements ListPopupWindowImpl
    {

        public android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view)
        {
            return null;
        }

        BaseListPopupWindowImpl()
        {
        }
    }

    static interface ListPopupWindowImpl
    {

        public abstract android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view);
    }


    private ListPopupWindowCompat()
    {
    }

    public static android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view)
    {
        return IMPL.createDragToOpenListener(obj, view);
    }

    static final ListPopupWindowImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new KitKatListPopupWindowImpl();
        else
            IMPL = new BaseListPopupWindowImpl();
    }
}
