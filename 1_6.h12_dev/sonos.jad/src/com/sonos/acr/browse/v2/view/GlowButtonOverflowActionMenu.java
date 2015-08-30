// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.sonos.acr.browse.v2.actions.ActionData;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;
import com.sonos.acr.view.SonosButton;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            ActionMenu, GlowButtonBarActionMenu

public class GlowButtonOverflowActionMenu extends FrameLayout
    implements ActionMenu
{

    public GlowButtonOverflowActionMenu(Context context)
    {
        this(context, null);
    }

    public GlowButtonOverflowActionMenu(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public GlowButtonOverflowActionMenu(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        inEditMode = false;
        View view = LayoutInflater.from(context).inflate(0x7f03001c, this, true);
        overflowButton = (ImageButton)view.findViewById(0x7f0a0077);
        overflowButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(actionSet instanceof ActionData)
                    (new ActionDialogFragment((ActionData)actionSet)).show(parentFragment.getChildFragmentManager(), "", overflowButton, 51);
            }

            final GlowButtonOverflowActionMenu this$0;

            
            {
                this$0 = GlowButtonOverflowActionMenu.this;
                super();
            }
        }
);
        buttonBarActionMenu = (GlowButtonBarActionMenu)view.findViewById(0x7f0a0078);
        updateView();
    }

    private boolean doButtonsOverflow(int i)
    {
        boolean flag = false;
        int j = 0;
        buttonBarActionMenu.forceLayout();
        if(buttonBarActionMenu != null)
        {
            int k = 0;
            while(k < buttonBarActionMenu.getChildCount()) 
            {
                View view = buttonBarActionMenu.getChildAt(k);
                if(view instanceof SonosButton)
                {
                    android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
                    int l;
                    if(layoutparams != null && layoutparams.height > 0)
                        l = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.width, 0x40000000);
                    else
                        l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                    view.measure(l, android.view.View.MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(0x7f09006b), 0x40000000));
                    j += view.getMeasuredWidth();
                }
                k++;
            }
        }
        if(j > i)
            flag = true;
        return flag;
    }

    private void updateView()
    {
        boolean flag = false;
        if(!inEditMode)
            flag = doButtonsOverflow(getMeasuredWidth() - getResources().getDimensionPixelOffset(0x7f09006c));
        if(actionSet == null || actionSet.size() == 0)
        {
            overflowButton.setVisibility(4);
            buttonBarActionMenu.setVisibility(4);
        } else
        if(!flag || actionSet.size() <= 1)
        {
            overflowButton.setVisibility(4);
            buttonBarActionMenu.setVisibility(0);
        } else
        {
            if(actionSet != null && actionSet.isAtLeastOneEnabled())
                overflowButton.setVisibility(0);
            else
                overflowButton.setVisibility(4);
            buttonBarActionMenu.setVisibility(4);
        }
    }

    public void clearActionItems()
    {
        actionSet = null;
        buttonBarActionMenu.clearActionItems();
        updateView();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
            updateView();
    }

    public void setActions(ActionSet actionset)
    {
        actionSet = actionset;
        buttonBarActionMenu.setActions(actionSet);
        updateView();
    }

    public void setEditState(boolean flag)
    {
        inEditMode = flag;
        if(buttonBarActionMenu == null) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        buttonBarActionMenu.getLayoutParams().width = -1;
        if(buttonBarActionMenu.getChildCount() == 1)
            buttonBarActionMenu.getChildAt(0).getLayoutParams().width = -1;
_L2:
        return;
_L4:
        buttonBarActionMenu.getLayoutParams().width = -2;
        if(buttonBarActionMenu.getChildCount() >= 1)
            buttonBarActionMenu.getChildAt(0).getLayoutParams().width = -2;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setParentFragment(Fragment fragment)
    {
        parentFragment = fragment;
    }

    private static final String LOG_TAG = com/sonos/acr/browse/v2/view/GlowButtonOverflowActionMenu.getSimpleName();
    ActionSet actionSet;
    GlowButtonBarActionMenu buttonBarActionMenu;
    boolean inEditMode;
    ImageButton overflowButton;
    Fragment parentFragment;

}
