// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.view:
//            DividerLinearLayout

public class DynamicDividedHeader extends DividerLinearLayout
{

    public DynamicDividedHeader(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.DynamicDividedHeader);
        rightBgResId = typedarray.getResourceId(0, 0);
        leftBgResId = typedarray.getResourceId(1, 0);
        centerBgResId = typedarray.getResourceId(2, 0);
        singleItemBgResId = typedarray.getResourceId(3, 0);
        typedarray.recycle();
    }

    protected ArrayList getVisibleViews()
    {
        ArrayList arraylist = new ArrayList();
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(view.getVisibility() == 0)
                arraylist.add(view);
        }

        return arraylist;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        ArrayList arraylist = getVisibleViews();
        int i1 = arraylist.size();
        int j1 = 0;
        while(j1 < i1) 
        {
            if(j1 == 0)
            {
                if(i1 == 1)
                    ((View)arraylist.get(j1)).setBackgroundResource(singleItemBgResId);
                else
                    ((View)arraylist.get(j1)).setBackgroundResource(leftBgResId);
            } else
            if(j1 == i1 - 1)
                ((View)arraylist.get(j1)).setBackgroundResource(rightBgResId);
            else
                ((View)arraylist.get(j1)).setBackgroundResource(centerBgResId);
            j1++;
        }
        super.onLayout(flag, i, j, k, l);
    }

    int centerBgResId;
    int leftBgResId;
    int rightBgResId;
    int singleItemBgResId;
}
