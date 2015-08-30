// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.sonos.acr.util.SLog;

public class InsetRelativeLayout extends RelativeLayout
{
    public static interface InsetListener
    {

        public abstract void onInsetUpdate(Rect rect);
    }


    public InsetRelativeLayout(Context context)
    {
        super(context);
        insets = null;
    }

    public InsetRelativeLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        insets = null;
    }

    public InsetRelativeLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        insets = null;
    }

    protected boolean fitSystemWindows(Rect rect)
    {
        SLog.e("SonosRelativeLayout", (new StringBuilder()).append("Insets: ").append(rect).toString());
        if(listener != null)
            listener.onInsetUpdate(rect);
        return super.fitSystemWindows(rect);
    }

    public Rect getInsets()
    {
        return insets;
    }

    public void setListener(InsetListener insetlistener)
    {
        listener = insetlistener;
    }

    Rect insets;
    InsetListener listener;
}
