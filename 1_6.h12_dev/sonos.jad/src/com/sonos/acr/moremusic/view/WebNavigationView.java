// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.moremusic.view;


public interface WebNavigationView
{
    public static interface WebNavigationListener
    {

        public abstract void onLastPageButtonPressed();

        public abstract void onNextPageButtonPressed();

        public abstract void onRefreshPageButtonPressed();
    }


    public abstract void resetButtons();

    public abstract void setNavigationListener(WebNavigationListener webnavigationlistener);

    public abstract void updateNavigationButtons(boolean flag, boolean flag1);

    public abstract void updatePageLoaded(boolean flag);

    public abstract void updateVisibility(boolean flag);
}
