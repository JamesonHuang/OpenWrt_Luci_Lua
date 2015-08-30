// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import dslv.DragSortListView;

public class FadingEdgeDragListView extends DragSortListView
{

    public FadingEdgeDragListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected float getTopFadingEdgeStrength()
    {
        return 0.0F;
    }
}
