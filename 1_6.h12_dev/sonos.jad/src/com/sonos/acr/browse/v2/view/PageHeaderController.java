// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.ActionData;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;

public class PageHeaderController
    implements android.view.View.OnClickListener
{

    public PageHeaderController(PageFragment pagefragment)
    {
        hasOptionsMenu = false;
        hasTitleMenu = false;
        fragment = pagefragment;
    }

    private boolean hasActionMenu()
    {
        boolean flag;
        if(actionMenuButton != null && hasOptionsMenu)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean hasBackButton()
    {
        boolean flag;
        if(backButton != null && backOnClickListener != null && backButton.getDrawable() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onClick(View view)
    {
        if(view != actionMenuButton) goto _L2; else goto _L1
_L1:
        ActionData actiondata1 = fragment.getPageActions();
        if(actiondata1 != null)
            (new ActionDialogFragment(actiondata1) {

                protected int getLayoutId()
                {
                    return 0x7f030001;
                }

                final PageHeaderController this$0;

            
            {
                this$0 = PageHeaderController.this;
                super(actiondata);
            }
            }
).show(fragment.getChildFragmentManager(), "", actionMenuButton, 51);
_L4:
        return;
_L2:
        if(view == pageTitleSpinner)
        {
            ActionData actiondata = fragment.getTitleActions();
            if(actiondata != null)
                (new ActionDialogFragment(actiondata) {

                    protected int getLayoutId()
                    {
                        return 0x7f030001;
                    }

                    final PageHeaderController this$0;

            
            {
                this$0 = PageHeaderController.this;
                super(actiondata);
            }
                }
).show(fragment.getChildFragmentManager(), "", pageTitleSpinner, 51);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setBackOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        backOnClickListener = onclicklistener;
        if(backHolder != null)
            backHolder.setOnClickListener(onclicklistener);
    }

    public void setFragment(PageFragment pagefragment)
    {
        fragment = pagefragment;
    }

    public void setHasOptionsMenu(boolean flag)
    {
        hasOptionsMenu = flag;
        updateView();
    }

    public void setHasTitleMenu(boolean flag)
    {
        hasTitleMenu = flag;
        updateView();
    }

    public void updateView()
    {
        int i = 0;
        boolean flag = hasBackButton();
        boolean flag1;
        if(hasActionMenu() || flag)
            flag1 = true;
        else
            flag1 = false;
        if(actionMenuButton != null)
        {
            ImageView imageview1 = actionMenuButton;
            ViewGroup viewgroup;
            TextView textview;
            ImageView imageview;
            byte byte2;
            if(!flag1)
                byte2 = 8;
            else
            if(hasOptionsMenu)
                byte2 = 0;
            else
                byte2 = 8;
            imageview1.setVisibility(byte2);
        }
        if(backButton != null)
        {
            imageview = backButton;
            byte byte1;
            if(!flag1)
                byte1 = 8;
            else
            if(backOnClickListener != null)
                byte1 = 0;
            else
                byte1 = 8;
            imageview.setVisibility(byte1);
        }
        if(backHolder != null)
            backHolder.setClickable(flag);
        if(pageTitle != null)
        {
            pageTitle.setText(fragment.getTitle());
            textview = pageTitle;
            byte byte0;
            if(hasTitleMenu)
                byte0 = 8;
            else
                byte0 = 0;
            textview.setVisibility(byte0);
        }
        if(pageTitleSpinner != null)
        {
            viewgroup = pageTitleSpinner;
            if(!hasTitleMenu)
                i = 8;
            viewgroup.setVisibility(i);
        }
        if(hasTitleMenu && spinnerTitle != null)
            spinnerTitle.setText(fragment.getTitle());
        if(hasTitleMenu && spinnerSubTitle != null)
            spinnerSubTitle.setText(fragment.getSubTitle());
    }

    public void watchView(View view)
    {
        if(view != null)
        {
            pageTitle = (TextView)view.findViewById(0x7f0a004a);
            spinnerTitle = (TextView)view.findViewById(0x7f0a004c);
            spinnerSubTitle = (TextView)view.findViewById(0x7f0a004d);
            pageTitleSpinner = (ViewGroup)view.findViewById(0x7f0a004b);
            backButton = (ImageView)view.findViewById(0x7f0a0049);
            actionMenuButton = (ImageView)view.findViewById(0x7f0a004e);
            backHolder = (ViewGroup)view.findViewById(0x7f0a0048);
            pageHeader = view.findViewById(0x7f0a0047);
            if(backHolder != null)
                backHolder.setOnClickListener(backOnClickListener);
            if(actionMenuButton != null)
                actionMenuButton.setOnClickListener(this);
            if(pageTitleSpinner != null)
                pageTitleSpinner.setOnClickListener(this);
            if(pageHeader != null)
                pageHeader.setOnTouchListener(new android.view.View.OnTouchListener() {

                    public boolean onTouch(View view1, MotionEvent motionevent)
                    {
                        return true;
                    }

                    final PageHeaderController this$0;

            
            {
                this$0 = PageHeaderController.this;
                super();
            }
                }
);
        }
    }

    ImageView actionMenuButton;
    ImageView backButton;
    ViewGroup backHolder;
    android.view.View.OnClickListener backOnClickListener;
    PageFragment fragment;
    boolean hasOptionsMenu;
    boolean hasTitleMenu;
    View pageHeader;
    TextView pageTitle;
    ViewGroup pageTitleSpinner;
    TextView spinnerSubTitle;
    TextView spinnerTitle;
}
