// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.moremusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

// Referenced classes of package com.sonos.acr.moremusic.view:
//            WebNavigationView

public class MainWebNavigationView extends LinearLayout
    implements android.view.View.OnClickListener, WebNavigationView
{

    public MainWebNavigationView(Context context)
    {
        this(context, null);
    }

    public MainWebNavigationView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        currentDrawableState = STATE_IDLE_SET;
        init(context);
    }

    public void init(Context context)
    {
        LayoutInflater.from(context).inflate(0x7f03002a, this, true);
        lastButton = (ImageButton)findViewById(0x7f0a00bb);
        nextButton = (ImageButton)findViewById(0x7f0a00bc);
        refreshButton = (ImageButton)findViewById(0x7f0a00bd);
        lastButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        refreshButton.setOnClickListener(this);
        lastButton.setEnabled(false);
        nextButton.setEnabled(false);
        refreshButton.setEnabled(true);
        setVisibility(8);
    }

    public void onClick(View view)
    {
        if(navigationListener == null) goto _L2; else goto _L1
_L1:
        if(view.getId() != lastButton.getId()) goto _L4; else goto _L3
_L3:
        navigationListener.onLastPageButtonPressed();
_L2:
        return;
_L4:
        if(view.getId() == nextButton.getId())
            navigationListener.onNextPageButtonPressed();
        else
        if(view.getId() == refreshButton.getId())
            navigationListener.onRefreshPageButtonPressed();
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected int[] onCreateDrawableState(int i)
    {
        return mergeDrawableStates(super.onCreateDrawableState(i + 1), currentDrawableState);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        int i1 = getChildCount();
        int j1 = getMeasuredWidth();
        int k1 = getMeasuredHeight();
        int l1 = j1 / (i1 + 1);
        for(int i2 = 0; i2 < i1; i2++)
        {
            View view = getChildAt(i2);
            int j2 = view.getMeasuredWidth();
            int k2 = view.getMeasuredHeight();
            int l2 = l1 * (i2 + 1) - j2 / 2;
            int i3 = (k1 - k2) / 2;
            view.layout(l2, i3, l2 + j2, i3 + k2);
        }

    }

    public void resetButtons()
    {
        updateNavigationButtons(false, false);
        updatePageLoaded(true);
    }

    public void setNavigationListener(WebNavigationView.WebNavigationListener webnavigationlistener)
    {
        navigationListener = webnavigationlistener;
    }

    public void updateNavigationButtons(boolean flag, boolean flag1)
    {
        lastButton.setEnabled(flag);
        nextButton.setEnabled(flag1);
    }

    public void updatePageLoaded(boolean flag)
    {
        int ai[];
        if(flag)
            ai = STATE_IDLE_SET;
        else
            ai = STATE_LOADING_SET;
        currentDrawableState = ai;
        refreshButton.setImageState(currentDrawableState, true);
    }

    public void updateVisibility(boolean flag)
    {
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        setVisibility(i);
    }

    protected static int STATE_IDLE_SET[];
    protected static int STATE_LOADING_SET[];
    protected int currentDrawableState[];
    protected ImageButton lastButton;
    protected WebNavigationView.WebNavigationListener navigationListener;
    protected ImageButton nextButton;
    protected ImageButton refreshButton;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x7f010027;
        STATE_LOADING_SET = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x7f010028;
        STATE_IDLE_SET = ai1;
    }
}
