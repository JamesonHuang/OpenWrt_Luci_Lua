// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.view:
//            RemoteImageView, SonosImageView

public class PinnedRemoteImageView extends RemoteImageView
{

    public PinnedRemoteImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void doResizeLogic(int i, int j)
    {
        double d = (double)j / (double)i;
        Display display = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(display.getWidth(), (int)(d * (double)display.getWidth()));
        for(int k = 0; k < getChildCount(); k++)
            getChildAt(k).setLayoutParams(layoutparams);

    }

    public View makeView()
    {
        SLog.i(LOG_TAG, "Make View Called");
        SonosImageView sonosimageview = new SonosImageView(getContext());
        sonosimageview.setFixedDimention(getFixedAlong());
        sonosimageview.setLayoutParams(new android.widget.FrameLayout.LayoutParams(((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getWidth(), -2));
        sonosimageview.setAdjustViewBounds(true);
        sonosimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_START);
        return sonosimageview;
    }

    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        if(drawable instanceof BitmapDrawable)
            doResizeLogic(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public void setImageResource(int i)
    {
        super.setImageResource(i);
        Drawable drawable = ((SonosImageView)getCurrentView()).getDrawable();
        if(drawable instanceof BitmapDrawable)
            doResizeLogic(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
