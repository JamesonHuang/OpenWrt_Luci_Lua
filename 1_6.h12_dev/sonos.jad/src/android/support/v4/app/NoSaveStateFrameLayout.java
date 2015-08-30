// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

class NoSaveStateFrameLayout extends FrameLayout
{

    public NoSaveStateFrameLayout(Context context)
    {
        super(context);
    }

    static ViewGroup wrap(View view)
    {
        NoSaveStateFrameLayout nosavestateframelayout = new NoSaveStateFrameLayout(view.getContext());
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if(layoutparams != null)
            nosavestateframelayout.setLayoutParams(layoutparams);
        view.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        nosavestateframelayout.addView(view);
        return nosavestateframelayout;
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        dispatchFreezeSelfOnly(sparsearray);
    }
}
