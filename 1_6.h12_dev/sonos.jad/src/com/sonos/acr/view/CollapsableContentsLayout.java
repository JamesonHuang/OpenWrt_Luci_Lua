// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package com.sonos.acr.view:
//            SonosLinearLayout

public class CollapsableContentsLayout extends SonosLinearLayout
{

    public CollapsableContentsLayout(Context context)
    {
        super(context);
    }

    public CollapsableContentsLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = 0;
        super.onLayout(flag, i, j, k, l);
        if(getChildCount() == 1)
        {
            View view = getChildAt(0);
            if(view.getMeasuredHeight() < getMeasuredHeight())
                i1 = 8;
            view.setVisibility(i1);
        }
    }
}
