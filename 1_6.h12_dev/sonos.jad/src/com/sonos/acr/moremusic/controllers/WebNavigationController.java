// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.moremusic.controllers;

import android.webkit.WebView;
import com.sonos.acr.moremusic.view.WebNavigationView;
import java.util.ArrayList;
import java.util.Iterator;

public class WebNavigationController
    implements com.sonos.acr.moremusic.view.WebNavigationView.WebNavigationListener
{

    public WebNavigationController(WebView webview)
    {
        webNavigationViews = new ArrayList();
        webView = null;
        loading = false;
        webView = webview;
    }

    private boolean canGoBack()
    {
        return webView.canGoBack();
    }

    private boolean canGoNext()
    {
        return webView.canGoForward();
    }

    private void goBack()
    {
        if(canGoBack())
            webView.goBack();
    }

    private void goNext()
    {
        if(canGoNext())
            webView.goForward();
    }

    private void stopOrRefresh()
    {
        if(loading)
            webView.stopLoading();
        else
            webView.reload();
    }

    public void addView(WebNavigationView webnavigationview)
    {
        if(webnavigationview != null)
        {
            webnavigationview.setNavigationListener(this);
            webNavigationViews.add(webnavigationview);
        }
    }

    public boolean backToInternal()
    {
        webView.stopLoading();
        boolean flag;
        if(webView.canGoBack())
        {
            webView.goBack();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void clear()
    {
        for(Iterator iterator = webNavigationViews.iterator(); iterator.hasNext(); ((WebNavigationView)iterator.next()).setNavigationListener(null));
        webNavigationViews.clear();
    }

    public boolean isPageLoading()
    {
        return loading;
    }

    public void onLastPageButtonPressed()
    {
        goBack();
    }

    public void onNextPageButtonPressed()
    {
        goNext();
    }

    public void onRefreshPageButtonPressed()
    {
        stopOrRefresh();
    }

    public void pageLoadError()
    {
        for(Iterator iterator = webNavigationViews.iterator(); iterator.hasNext(); ((WebNavigationView)iterator.next()).updatePageLoaded(true));
    }

    public void pageLoadFinished()
    {
        for(Iterator iterator = webNavigationViews.iterator(); iterator.hasNext(); ((WebNavigationView)iterator.next()).updatePageLoaded(true));
        loading = false;
    }

    public void pageLoadInProgress()
    {
        for(Iterator iterator = webNavigationViews.iterator(); iterator.hasNext(); ((WebNavigationView)iterator.next()).updatePageLoaded(false));
        loading = true;
    }

    public void pageLoadStarting(String s)
    {
    }

    public void removeView(WebNavigationView webnavigationview)
    {
        webNavigationViews.remove(webnavigationview);
        webnavigationview.setNavigationListener(null);
    }

    private boolean loading;
    ArrayList webNavigationViews;
    private WebView webView;
}
