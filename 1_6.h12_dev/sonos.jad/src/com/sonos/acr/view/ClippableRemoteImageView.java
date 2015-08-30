// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.WindowManager;
import com.sonos.acr.util.ViewUtils;

// Referenced classes of package com.sonos.acr.view:
//            RemoteImageView

public class ClippableRemoteImageView extends RemoteImageView
{

    public ClippableRemoteImageView(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        context = context1;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(context != null && context.getSystemService("window") != null)
        {
            Point point = ViewUtils.getDisplaySize((WindowManager)getContext().getSystemService("window"));
            Rect rect = new Rect();
            getHitRect(rect);
            int k = point.y - rect.bottom;
            android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)getLayoutParams();
            if(layoutparams != null)
            {
                layoutparams.setMargins(layoutparams.leftMargin, k * -1, layoutparams.rightMargin, layoutparams.bottomMargin);
                setLayoutParams(layoutparams);
            }
        }
    }

    Context context;
}
