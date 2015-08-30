// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.moremusic;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sonos.acr.Loc;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.moremusic.controllers.WebNavigationController;
import com.sonos.acr.moremusic.view.ProgressWebNavigationView;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.SonosToast;
import com.sonos.acr.view.HeaderBarView;
import com.sonos.acr.web.SCLibWebViewBridge;
import com.sonos.sclib.*;
import java.util.*;

public class MoreMusicActivity extends SonosActivity
{
    private class LoadErrorTask extends TimerTask
    {

        public void run()
        {
            runOnUiThread(new Runnable() {

                public void run()
                {
                    SLog.d(LOG_TAG, Loc.NOLOC("timed out waiting for view.isReady!"));
                    String s;
                    if(webNavigationController.isPageLoading())
                        s = sclib2web.getTimeoutErrorMessage();
                    else
                        s = sclib2web.getRequestFailedErrorMessage();
                    SonosToast.popupDialog(s, null);
                    networkActivityStopped(false);
                }

                final LoadErrorTask this$1;

                
                {
                    this$1 = LoadErrorTask.this;
                    super();
                }
            }
);
        }

        final MoreMusicActivity this$0;

        private LoadErrorTask()
        {
            this$0 = MoreMusicActivity.this;
            super();
        }

    }

    private class _MMSWebViewClient extends WebViewClient
    {

        public void onPageFinished(WebView webview, String s)
        {
            super.onPageFinished(webview, s);
        }

        public void onPageStarted(WebView webview, String s, Bitmap bitmap)
        {
            m_viewIsReadyT = System.currentTimeMillis();
            int i = 1000 * sclib2web.getConnectionSpec().getConnectionTimeOut();
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("starting timer for load timeout, ").append(i / 1000).append(" seconds").toString()));
            if(viewReadyTimer != null)
                destroyTimer();
            viewReadyTimer = new Timer();
            viewReadyTimer.schedule(new LoadErrorTask(), i + 100);
            updateTitle(0x7f0c006d);
            webNavigationController.pageLoadInProgress();
            super.onPageStarted(webview, s, bitmap);
        }

        public void onReceivedError(WebView webview, int i, String s, String s1)
        {
            SLog.d(LOG_TAG, Loc.NOLOC("webview error"));
            SonosToast.popupDialog(sclib2web.getRequestFailedErrorMessage(), null);
            networkActivityStopped(false);
            super.onReceivedError(webview, i, s, s1);
        }

        public boolean shouldOverrideUrlLoading(WebView webview, String s)
        {
            webNavigationController.pageLoadStarting(s);
            return false;
        }

        final MoreMusicActivity this$0;

        private _MMSWebViewClient()
        {
            this$0 = MoreMusicActivity.this;
            super();
        }

    }

    private class _MMSWebBridgeDelegate extends SCIWebBridgeDelegateSwigBase
    {

        public void bridgeStarted(String s, boolean flag)
        {
            if(!flag)
            {
                networkActivityStopped(false);
                String s1;
                if(webNavigationController.isPageLoading())
                    s1 = sclib2web.getTimeoutErrorMessage();
                else
                    s1 = sclib2web.getRequestFailedErrorMessage();
                SonosToast.popupDialog(s1, null);
            }
        }

        public void doPostRouteMessage(SCIWebMessage sciwebmessage, SCRouteResultType scrouteresulttype)
        {
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPostRouteMessage:").append(sciwebmessage).toString()));
            if(sciwebmessage != null)
                SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPostRouteMessage: message subject: ").append(sciwebmessage.getSubject()).toString()));
        }

        public com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult doPreRouteMessage(SCIWebMessage sciwebmessage)
        {
            com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult eprerouteresult;
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPreRouteMessage:").append(sciwebmessage).toString()));
            eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.CONTINUE_ROUTING;
            if(sciwebmessage == null) goto _L2; else goto _L1
_L1:
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPreRouteMessage: message subject: ").append(sciwebmessage.getSubject()).toString()));
            if(!sciwebmessage.getSubject().equals(sclibConstants.SCI_WEB_BRIDGE_EVENT_VIEW_ISREADY)) goto _L4; else goto _L3
_L3:
            networkActivityStopped(true);
            updateTitle();
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("s5.sys.view.isReady time:").append(System.currentTimeMillis() - m_viewIsReadyT).append("ms").toString()));
            eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.STOP_ROUTING;
_L2:
            SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPreRouteMessage finished: ").append(eprerouteresult).toString()));
            return eprerouteresult;
_L4:
            if(sciwebmessage.getSubject().equals(sclibConstants.SCI_WEB_BRIDGE_EVENT_VIEW_FAIL))
            {
                networkActivityStopped(false);
                SonosToast.popupDialog(sciwebmessage.getProperties().getStrProp("errorMessage"), null);
                eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.STOP_ROUTING;
            } else
            if(sciwebmessage.getSubject().equals(sclibConstants.SCI_WEB_BRIDGE_ACTION_OPEN_EXTERNAL_URI))
            {
                String s2 = sciwebmessage.getProperties().getStrProp("uri");
                SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("doPreRouteMessage: open external URI, uri=\"").append(s2).append("\"").toString()));
                try
                {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s2)));
                }
                catch(ActivityNotFoundException activitynotfoundexception)
                {
                    SonosToast.popupDialog(sclib2web.getFailedToConnectErrorMessage(s2), null);
                }
                eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.STOP_ROUTING;
            } else
            if(sciwebmessage.getSubject().equals(sclibConstants.SCI_WEB_BRIDGE_EVENT_SHOW_DETAIL_PAGE))
            {
                SCIPropertyBag scipropertybag1 = sciwebmessage.getProperties();
                if(scipropertybag1 != null)
                {
                    String s1;
                    if("1".equals(scipropertybag1.getStrProp("networkActivity")))
                        webNavigationController.pageLoadInProgress();
                    else
                        webNavigationController.pageLoadFinished();
                    s1 = scipropertybag1.getStrProp("load");
                    sclib2web.executeJavascript(webView, s1);
                    lastMessage = sciwebmessage;
                } else
                {
                    webNavigationController.pageLoadFinished();
                }
                eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.STOP_ROUTING;
            } else
            if(sciwebmessage.getSubject().equals(sclibConstants.SCI_WEB_BRIDGE_EVENT_UPDATE_TITLE))
            {
                SCIPropertyBag scipropertybag = sciwebmessage.getProperties();
                if(scipropertybag != null)
                {
                    String s = scipropertybag.getStrProp("title");
                    updateTitle(s);
                }
                eprerouteresult = com.sonos.sclib.SCIWebBridgeDelegate.ePreRouteResult.STOP_ROUTING;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public void publishNativeEvent(SCIWebMessage sciwebmessage, int i)
        {
            if(sciwebmessage != null)
            {
                String s = sciwebmessage.getSubject();
                String s1 = "";
                SCIPropertyBag scipropertybag = sciwebmessage.getProperties();
                if(scipropertybag != null)
                    s1 = sclib.SCLibConvertPropertyBagToJson(scipropertybag, true);
                String s2 = (new StringBuilder()).append("S5.MsgBus.publish('").append(s).append("','").append(s1).append("')").toString();
                SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("JS Eval:").append(s2).toString()));
                sclib2web.executeJavascript(webView, s2);
            }
        }

        final MoreMusicActivity this$0;

        private _MMSWebBridgeDelegate()
        {
            this$0 = MoreMusicActivity.this;
            super();
        }

    }


    public MoreMusicActivity()
    {
        boolean flag = true;
        super();
        headerBar = null;
        webView = null;
        webNavigationController = null;
        sclib2web = null;
        viewReadyTimer = null;
        m_viewIsReadyT = 0L;
        lastMessage = null;
        boolean flag1;
        if(android.os.Build.VERSION.SDK_INT >= 11)
            flag1 = flag;
        else
            flag1 = false;
        isGtHoneycomb = flag1;
        if(android.os.Build.VERSION.SDK_INT < 19)
            flag = false;
        isGtKitKat = flag;
    }

    private void destroyTimer()
    {
        if(viewReadyTimer != null)
        {
            SLog.d(LOG_TAG, Loc.NOLOC("Canceling timeout and destroying timer"));
            viewReadyTimer.cancel();
            viewReadyTimer.purge();
            viewReadyTimer = null;
        }
    }

    private void networkActivityStopped(boolean flag)
    {
        SLog.d(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("network activity stopped, success = ").append(flag).toString()));
        destroyTimer();
        if(!flag)
        {
            SLog.d(LOG_TAG, Loc.NOLOC("failed to load page, going back"));
            webNavigationController.pageLoadError();
            onBackPressed();
        } else
        {
            webNavigationController.pageLoadFinished();
        }
    }

    private void updateTitle()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                headerBar.setText(webView.getTitle());
            }

            final MoreMusicActivity this$0;

            
            {
                this$0 = MoreMusicActivity.this;
                super();
            }
        }
);
    }

    public void onBackPressed()
    {
        if(lastMessage != null)
        {
            String s = lastMessage.getProperties().getStrProp("unload");
            sclib2web.executeJavascript(webView, s);
            lastMessage = null;
        } else
        {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030029);
        onNewIntent(getIntent());
        headerBar = (HeaderBarView)findViewById(0x7f0a00b8);
        webView = (WebView)findViewById(0x7f0a00b9);
        String s = sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Services_MusicServiceCatalog);
        SCIPropertyBag scipropertybag = null;
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null && bundle1.containsKey(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR))
        {
            scipropertybag = sclib.createPropertyBag();
            Bundle bundle2 = bundle1.getBundle(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR);
            if(bundle2.keySet().size() > 0)
            {
                SCIPropertyBag scipropertybag1 = sclib.createPropertyBag();
                String s3;
                for(Iterator iterator = bundle2.keySet().iterator(); iterator.hasNext(); scipropertybag1.setStrProp(s3, bundle2.getString(s3)))
                    s3 = (String)iterator.next();

                scipropertybag.setPropBagProp(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR, scipropertybag1);
            }
        }
        _MMSWebBridgeDelegate _lmmswebbridgedelegate = new _MMSWebBridgeDelegate();
        sclib2web = new SCLibWebViewBridge(_lmmswebbridgedelegate);
        SCLibWebViewBridge sclibwebviewbridge = sclib2web;
        WebView webview = webView;
        _MMSWebViewClient _lmmswebviewclient = new _MMSWebViewClient();
        sclibwebviewbridge.init(s, scipropertybag, webview, _lmmswebviewclient);
        String s1 = sclib2web.getConnectionSpec().getUrl();
        webNavigationController = new WebNavigationController(webView);
        ProgressWebNavigationView progresswebnavigationview = (ProgressWebNavigationView)findViewById(0x7f0a0035);
        webNavigationController.addView(progresswebnavigationview);
        if(isGtHoneycomb)
            webView.setLayerType(1, null);
        if(isGtKitKat)
        {
            String s2 = getResources().getString(0x7f0c0036);
            if(SonosApplication.isDebuggable() || s2.equals("ALPHA") || s2.equals("BETA"))
                WebView.setWebContentsDebuggingEnabled(true);
        }
        updateTitle(0x7f0c006d);
        webView.loadUrl(s1);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f0e0000, menu);
        return true;
    }

    public void onDestroy()
    {
        SLog.d(LOG_TAG, Loc.NOLOC("onDestroy"));
        destroyTimer();
        webNavigationController.clear();
        webNavigationController = null;
        sclib2web.term();
        webView.loadUrl("about:blank");
        webView.clearFormData();
        webView.clearHistory();
        webView.clearMatches();
        webView.removeAllViews();
        ViewGroup viewgroup = (ViewGroup)webView.getParent();
        if(viewgroup != null)
        {
            viewgroup.removeView(webView);
            webView.destroy();
        }
        webView = null;
        super.onDestroy();
    }

    protected void onPause()
    {
        super.onPause();
        if(isGtHoneycomb)
            webView.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        if(isGtHoneycomb)
            webView.onResume();
    }

    public void updateTitle(final int stringId)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                headerBar.setText(stringId);
            }

            final MoreMusicActivity this$0;
            final int val$stringId;

            
            {
                this$0 = MoreMusicActivity.this;
                stringId = i;
                super();
            }
        }
);
    }

    public void updateTitle(final String value)
    {
        if(value.length() > 0)
            runOnUiThread(new Runnable() {

                public void run()
                {
                    headerBar.setText(value);
                }

                final MoreMusicActivity this$0;
                final String val$value;

            
            {
                this$0 = MoreMusicActivity.this;
                value = s;
                super();
            }
            }
);
    }

    private HeaderBarView headerBar;
    private final boolean isGtHoneycomb;
    private final boolean isGtKitKat;
    private SCIWebMessage lastMessage;
    private long m_viewIsReadyT;
    private SCLibWebViewBridge sclib2web;
    private Timer viewReadyTimer;
    private WebNavigationController webNavigationController;
    private WebView webView;



/*
    static Timer access$1002(MoreMusicActivity moremusicactivity, Timer timer)
    {
        moremusicactivity.viewReadyTimer = timer;
        return timer;
    }

*/








/*
    static long access$602(MoreMusicActivity moremusicactivity, long l)
    {
        moremusicactivity.m_viewIsReadyT = l;
        return l;
    }

*/




/*
    static SCIWebMessage access$902(MoreMusicActivity moremusicactivity, SCIWebMessage sciwebmessage)
    {
        moremusicactivity.lastMessage = sciwebmessage;
        return sciwebmessage;
    }

*/
}
