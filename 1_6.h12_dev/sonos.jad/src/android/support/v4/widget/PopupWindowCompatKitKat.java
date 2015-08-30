// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;

class PopupWindowCompatKitKat
{

    PopupWindowCompatKitKat()
    {
    }

    public static void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
    {
        popupwindow.showAsDropDown(view, i, j, k);
    }
}
