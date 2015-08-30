// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;

class ViewCompatGingerbread
{

    ViewCompatGingerbread()
    {
    }

    public static int getOverScrollMode(View view)
    {
        return view.getOverScrollMode();
    }

    public static void setOverScrollMode(View view, int i)
    {
        view.setOverScrollMode(i);
    }
}
