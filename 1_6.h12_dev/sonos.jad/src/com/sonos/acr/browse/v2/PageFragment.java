// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import android.os.Bundle;
import android.view.View;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.browse.v2.actions.ActionData;
import com.sonos.acr.browse.v2.view.PageHeaderController;
import com.sonos.sclib.sclib;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class PageFragment extends SonosFragment
{
    public static interface PageFragmentListener
    {

        public abstract void onPageInvalidated(PageFragment pagefragment);

        public abstract void onPageUpdated(PageFragment pagefragment);
    }


    protected PageFragment()
    {
        pageHeaderController = new PageHeaderController(this);
        pageFragmentListeners = new ArrayList();
        backNavigation = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE;
    }

    protected PageFragment(int i)
    {
        super(i);
        pageHeaderController = new PageHeaderController(this);
        pageFragmentListeners = new ArrayList();
        backNavigation = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE;
    }

    public void addPageFragmentListener(PageFragmentListener pagefragmentlistener)
    {
        if(!pageFragmentListeners.contains(pagefragmentlistener))
            pageFragmentListeners.add(pagefragmentlistener);
    }

    public String getActionFilter(boolean flag)
    {
        return sclib.SC_ACTION_FILTERNAME_DEFAULT;
    }

    public com.sonos.acr.util.NavigationUtils.BackNavigationRouting getBackNagivation()
    {
        return backNavigation;
    }

    public ActionData getPageActions()
    {
        return null;
    }

    public String getSubTitle()
    {
        return "";
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        return "";
    }

    public ActionData getTitleActions()
    {
        return null;
    }

    public boolean hasActionsMenu()
    {
        boolean flag;
        if(getPageActions() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasFooterMenu()
    {
        return false;
    }

    public boolean hasTitleMenu()
    {
        boolean flag;
        if(getTitleActions() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void invalidatePage()
    {
        for(Iterator iterator = pageFragmentListeners.iterator(); iterator.hasNext(); ((PageFragmentListener)iterator.next()).onPageInvalidated(this));
    }

    public abstract boolean isGone();

    protected void notifyPageUpdated()
    {
        updateHeaderBar();
        for(Iterator iterator = pageFragmentListeners.iterator(); iterator.hasNext(); ((PageFragmentListener)iterator.next()).onPageUpdated(this));
    }

    public void onShowActionMenu()
    {
    }

    public void onSubscribeEventSinks()
    {
        super.onSubscribeEventSinks();
        notifyPageUpdated();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        pageHeader = view.findViewById(0x7f0a0047);
        pageHeaderController.watchView(pageHeader);
        pageHeaderController.setHasOptionsMenu(hasActionsMenu());
        pageHeaderController.setHasTitleMenu(hasTitleMenu());
    }

    public void removePageFragmentListener(PageFragmentListener pagefragmentlistener)
    {
        pageFragmentListeners.remove(pagefragmentlistener);
    }

    public void setBackNavigation(com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting)
    {
        backNavigation = backnavigationrouting;
    }

    protected void updateHeaderBar()
    {
        if(pageHeaderController != null)
        {
            pageHeaderController.setHasOptionsMenu(hasActionsMenu());
            pageHeaderController.setHasTitleMenu(hasTitleMenu());
            pageHeaderController.updateView();
        }
    }

    private com.sonos.acr.util.NavigationUtils.BackNavigationRouting backNavigation;
    protected ArrayList pageFragmentListeners;
    protected View pageHeader;
    public PageHeaderController pageHeaderController;
}
