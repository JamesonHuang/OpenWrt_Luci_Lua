// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            AbsActionMenu

public class GlowButtonBarActionMenu extends AbsActionMenu
{

    public GlowButtonBarActionMenu(Context context)
    {
        this(context, null);
    }

    public GlowButtonBarActionMenu(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public GlowButtonBarActionMenu(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.GlowButtonBarActionMenu);
        buttonStyleLayout = typedarray.getResourceId(0, getLayout());
        typedarray.recycle();
    }

    public View addActionItem(AbsActionMenu.ClickActionHandler clickactionhandler)
    {
        TextView textview = (TextView)LayoutInflater.from(getContext()).inflate(buttonStyleLayout, this, false);
        addView(textview);
        textview.setText(clickactionhandler.getLabel());
        textview.setOnClickListener(clickactionhandler);
        textview.setEnabled(clickactionhandler.isEnabled());
        return textview;
    }

    public void clearActionItems()
    {
        removeAllViews();
    }

    protected int getLayout()
    {
        return 0x7f03001a;
    }

    public void setOrientation(int i)
    {
        super.setOrientation(i);
    }

    private static final String LOG_TAG = com/sonos/acr/browse/v2/view/GlowButtonBarActionMenu.getSimpleName();
    int buttonStyleLayout;

}
