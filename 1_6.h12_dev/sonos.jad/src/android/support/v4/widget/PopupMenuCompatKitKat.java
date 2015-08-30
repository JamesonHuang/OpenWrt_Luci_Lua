// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.widget.PopupMenu;

class PopupMenuCompatKitKat
{

    PopupMenuCompatKitKat()
    {
    }

    public static android.view.View.OnTouchListener getDragToOpenListener(Object obj)
    {
        return ((PopupMenu)obj).getDragToOpenListener();
    }
}
