// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.web;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.*;
import com.sonos.acr.Loc;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.io.File;

public class SCLibWebViewBridge extends WebViewClient
{
    private class BridgeCallback
        implements ValueCallback
    {

        public volatile void onReceiveValue(Object obj)
        {
            onReceiveValue((String)obj);
        }

        public void onReceiveValue(String s)
        {
            final boolean bridgeAvailable = s.equals("1");
            runOnUIThread(new Runnable() {

                public void run()
                {
                    if(webView != null)
                    {
                        SLog.d(LOG_TAG, (new StringBuilder()).append("BridgeCallback::onReceiveValue - bridgeAvailable? ").append(bridgeAvailable).toString());
                        _flddelegate.bridgeStarted(webView.getUrl(), bridgeAvailable);
                        if(bridgeAvailable)
                            webView.evaluateJavascript("S5.Bridge.fireReadyEvent();", null);
                    } else
                    {
                        SLog.w(LOG_TAG, "BridgeCallback::onReceiveValue - webView is null.");
                    }
                }

                final BridgeCallback this$1;
                final boolean val$bridgeAvailable;

                
                {
                    this$1 = BridgeCallback.this;
                    bridgeAvailable = flag;
                    super();
                }
            }
);
        }

        final SCLibWebViewBridge this$0;

        private BridgeCallback()
        {
            this$0 = SCLibWebViewBridge.this;
            super();
        }

    }


    public SCLibWebViewBridge(SCIWebBridgeDelegate sciwebbridgedelegate)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/web/SCLibWebViewBridge.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        _flddelegate = null;
        webBridge = null;
        webView = null;
        activityWebViewClient = null;
        bridgeCallback = null;
        bootstrapSpec = null;
        _flddelegate = sciwebbridgedelegate;
    }

    private void doPageLoad(WebView webview, String s)
    {
        com.sonos.sclib.SCIWebMessage sciwebmessage = sclib.createSCIWebMessage("s5.sclib.unsubscribe.*", "");
        webBridge.routeMessage(sciwebmessage, com.sonos.sclib.SCIWebBridge.SCRouteWebMessageType.UNSUBSCRIBEALL);
        String s1 = webBridge.getJavaScriptAppContextEvalExpression();
        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = s1;
            String s3 = String.format("(function(){ return %s ? 1 : 0 })();", aobj1);
            SLog.d(LOG_TAG, (new StringBuilder()).append("Evaluating: ").append(s3).toString());
            webView.evaluateJavascript(s3, bridgeCallback);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = s1.replace("\"", "\\\"");
            String s2 = String.format("(function(){var f = new Function(\"return %s ? 1 : 0\");SonosACRWebBridge.bridgeCallback(f());})();", aobj);
            SLog.d(LOG_TAG, (new StringBuilder()).append("Executing: ").append(s2).toString());
            executeJavascript(webView, s2);
        }
        activityWebViewClient.onPageFinished(webview, s);
    }

    private void runOnUIThread(Runnable runnable)
    {
        (new Handler(SonosApplication.getInstance().getMainLooper())).post(runnable);
    }

    public void bridgeCallback(String s)
    {
        runOnUIThread(new Runnable() {

            public void run()
            {
                if(webView != null)
                {
                    SLog.d(LOG_TAG, (new StringBuilder()).append("bridgeCallback - bridgeAvailable? ").append(bridgeAvailable).toString());
                    _flddelegate.bridgeStarted(webView.getUrl(), bridgeAvailable);
                    if(bridgeAvailable)
                        executeJavascript(webView, "S5.Bridge.fireReadyEvent();");
                } else
                {
                    SLog.w(LOG_TAG, "bridgeCallback - webView is null.");
                }
            }

            final SCLibWebViewBridge this$0;
            final boolean val$bridgeAvailable;

            
            {
                this$0 = SCLibWebViewBridge.this;
                bridgeAvailable = flag;
                super();
            }
        }
);
    }

    public void command(String s, String s1)
    {
        com.sonos.sclib.SCIWebMessage sciwebmessage = sclib.createSCIWebMessage(s, s1);
        webBridge.routeMessage(sciwebmessage, com.sonos.sclib.SCIWebBridge.SCRouteWebMessageType.COMMAND);
    }

    public void doUpdateVisitedHistory(WebView webview, String s, boolean flag)
    {
        activityWebViewClient.doUpdateVisitedHistory(webview, s, flag);
    }

    public void executeJavascript(WebView webview, String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        webview.loadUrl(String.format("javascript:%s;", aobj));
    }

    public SCIWebRequestSpec getConnectionSpec()
    {
        return bootstrapSpec;
    }

    public String getDataForObject(String s, String s1)
    {
        return webBridge.getDataForObject(s, s1);
    }

    public String getFailedToConnectErrorMessage(String s)
    {
        return webBridge.getFailedToConnectErrorMessage(s);
    }

    public String getRequestFailedErrorMessage()
    {
        return webBridge.getRequestFailedErrorMessage();
    }

    public String getTimeoutErrorMessage()
    {
        return webBridge.getTimeoutErrorMessage();
    }

    public void init(String s, SCIPropertyBag scipropertybag, WebView webview, WebViewClient webviewclient)
    {
        WebSettings websettings;
        com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy sccachepolicy;
        int i;
        webView = webview;
        activityWebViewClient = webviewclient;
        com.sonos.sclib.SCIHousehold scihousehold = LibraryUtils.getSCLibManager().getLibrary().getHousehold();
        webBridge = sclib.createSCIWebBridge(_flddelegate, scihousehold);
        webBridge.init();
        bridgeCallback = new BridgeCallback();
        webview.addJavascriptInterface(this, "SonosACRWebBridge");
        webview.addJavascriptInterface(bridgeCallback, "BridgeCallback");
        webview.setWebViewClient(this);
        websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setSaveFormData(false);
        websettings.setSavePassword(false);
        websettings.setDomStorageEnabled(true);
        bootstrapSpec = webBridge.getHttpRequestSpec(s, scipropertybag);
        sccachepolicy = bootstrapSpec.getCachePolicy();
        i = 1;
        class _cls2
        {

            static final int $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy = new int[com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy[com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy.LOCAL_CACHE_ELSE_LOAD.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy[com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy.FORCE_LOAD.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy[com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy.CLEAR_CACHE_FORCE_LOAD.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$sclib$SCIWebRequestSpec$SCCachePolicy[com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy.CACHE_DEFAULT.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls2..SwitchMap.com.sonos.sclib.SCIWebRequestSpec.SCCachePolicy[sccachepolicy.ordinal()];
        JVM INSTR tableswitch 1 4: default 172
    //                   1 215
    //                   2 221
    //                   3 227
    //                   4 264;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_264;
_L6:
        websettings.setCacheMode(i);
        File file = SonosApplication.getInstance().getCacheDir();
        if(file != null && i != 2)
        {
            websettings.setAppCacheEnabled(true);
            websettings.setAppCachePath(file.getPath());
        } else
        if(i == 2)
        {
            SLog.d(LOG_TAG, Loc.NOLOC("Disabling app cache"));
            websettings.setAppCacheEnabled(false);
        }
        return;
_L2:
        i = 1;
          goto _L6
_L3:
        i = 2;
          goto _L6
_L4:
        CookieSyncManager.createInstance(SonosApplication.getInstance());
        CookieManager.getInstance().removeAllCookie();
        webview.clearCache(true);
        i = 2;
        SLog.d(LOG_TAG, Loc.NOLOC("cache content cleared"));
          goto _L6
        i = -1;
          goto _L6
    }

    public void onFormResubmission(WebView webview, Message message, Message message1)
    {
        activityWebViewClient.onFormResubmission(webview, message, message1);
    }

    public void onLoadResource(WebView webview, String s)
    {
        activityWebViewClient.onLoadResource(webview, s);
    }

    public void onPageFinished(WebView webview, String s)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("Using onPageFinished to detect page has loaded for url: ").append(s).toString());
        doPageLoad(webview, s);
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        activityWebViewClient.onPageStarted(webview, s, bitmap);
    }

    public void onReceivedError(WebView webview, int i, String s, String s1)
    {
        activityWebViewClient.onReceivedError(webview, i, s, s1);
    }

    public void onReceivedHttpAuthRequest(WebView webview, HttpAuthHandler httpauthhandler, String s, String s1)
    {
        activityWebViewClient.onReceivedHttpAuthRequest(webview, httpauthhandler, s, s1);
    }

    public void onScaleChanged(WebView webview, float f, float f1)
    {
        activityWebViewClient.onScaleChanged(webview, f, f1);
    }

    public void onUnhandledKeyEvent(WebView webview, KeyEvent keyevent)
    {
        activityWebViewClient.onUnhandledKeyEvent(webview, keyevent);
    }

    public void publish(String s, String s1)
    {
        com.sonos.sclib.SCIWebMessage sciwebmessage = sclib.createSCIWebMessage(s, s1);
        webBridge.routeMessage(sciwebmessage, com.sonos.sclib.SCIWebBridge.SCRouteWebMessageType.PUBLISH);
    }

    public boolean shouldOverrideKeyEvent(WebView webview, KeyEvent keyevent)
    {
        return activityWebViewClient.shouldOverrideKeyEvent(webview, keyevent);
    }

    public boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        return activityWebViewClient.shouldOverrideUrlLoading(webview, s);
    }

    public void subscribe(String s, String s1)
    {
        com.sonos.sclib.SCIWebMessage sciwebmessage = sclib.createSCIWebMessage(s, s1);
        webBridge.routeMessage(sciwebmessage, com.sonos.sclib.SCIWebBridge.SCRouteWebMessageType.SUBSCRIBE);
    }

    public void term()
    {
        SLog.d(LOG_TAG, Loc.NOLOC("SCLibWebViewBridge Terminating"));
        webView.setWebViewClient(null);
        webView.setWebChromeClient(null);
        webView = null;
        activityWebViewClient = null;
        webBridge.term();
    }

    public String LOG_TAG;
    private WebViewClient activityWebViewClient;
    private SCIWebRequestSpec bootstrapSpec;
    private BridgeCallback bridgeCallback;
    private SCIWebBridgeDelegate _flddelegate;
    final String jsStartTemplate = "(function(){var f = new Function(\"return %s ? 1 : 0\");SonosACRWebBridge.bridgeCallback(f());})();";
    final String jsStartTemplateKitKat = "(function(){ return %s ? 1 : 0 })();";
    final String sclibReady = "S5.Bridge.fireReadyEvent();";
    private SCIWebBridge webBridge;
    private WebView webView;



}
