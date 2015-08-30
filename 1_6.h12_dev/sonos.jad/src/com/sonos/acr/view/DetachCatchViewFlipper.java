// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class DetachCatchViewFlipper extends ViewFlipper
{

    public DetachCatchViewFlipper(Context context)
    {
        super(context);
    }

    public DetachCatchViewFlipper(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
_L1:
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        stopFlipping();
          goto _L1
    }
}
