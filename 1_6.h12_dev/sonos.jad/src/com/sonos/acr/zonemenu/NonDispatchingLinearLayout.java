// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.zonemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

class NonDispatchingLinearLayout extends LinearLayout
{

    public NonDispatchingLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void dispatchSetPressed(boolean flag)
    {
    }
}
