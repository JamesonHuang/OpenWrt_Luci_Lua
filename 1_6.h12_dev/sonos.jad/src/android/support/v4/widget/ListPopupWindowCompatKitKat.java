// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListPopupWindow;

class ListPopupWindowCompatKitKat
{

    ListPopupWindowCompatKitKat()
    {
    }

    public static android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view)
    {
        return ((ListPopupWindow)obj).createDragToOpenListener(view);
    }
}
