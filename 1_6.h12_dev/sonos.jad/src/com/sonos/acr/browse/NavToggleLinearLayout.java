// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import com.sonos.acr.view.DividerLinearLayout;

public class NavToggleLinearLayout extends DividerLinearLayout
{

    public NavToggleLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setBackgroundResource(0x7f020010);
    }

    public void setPressed(boolean flag)
    {
        super.setPressed(flag);
        Drawable drawable = getBackground().getCurrent();
        if(drawable != null && (drawable instanceof TransitionDrawable))
            if(flag)
                ((TransitionDrawable)drawable).startTransition(ViewConfiguration.getLongPressTimeout());
            else
                ((TransitionDrawable)drawable).resetTransition();
    }
}
