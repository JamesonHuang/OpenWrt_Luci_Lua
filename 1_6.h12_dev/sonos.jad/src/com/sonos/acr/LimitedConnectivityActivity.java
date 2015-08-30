// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.app.AlertDialog;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.SimpleActionData;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;
import com.sonos.acr.browse.v2.pages.LimitedConnectivityActionDialogFragment;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.NetworkStateMonitor;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosTextView;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr:
//            SonosActivity, SonosLaunchActivity

public class LimitedConnectivityActivity extends SonosActivity
    implements android.view.View.OnClickListener, com.sonos.acr.sclib.SonosListener.StateListener
{

    public LimitedConnectivityActivity()
    {
        searchingTimerHandler = new Handler();
        inWifiState = false;
        dialog = null;
    }

    private void doFirstLineAlignment()
    {
        boolean flag;
        android.widget.TableRow.LayoutParams layoutparams;
        if(textLine1.getVisibility() == 0 && StringUtils.isNotEmptyOrNull(textLine1.getText()) && (textLine2.getVisibility() == 8 || StringUtils.isEmptyOrNull(textLine2.getText())) && (textLine3.getVisibility() == 8 || StringUtils.isEmptyOrNull(textLine3.getText())))
            flag = true;
        else
            flag = false;
        layoutparams = new android.widget.TableRow.LayoutParams(textLine1.getLayoutParams().width, textLine1.getLayoutParams().height);
        if(flag)
        {
            textLine1.setGravity(17);
            layoutparams.gravity = 17;
            textLine1.setLayoutParams(layoutparams);
        } else
        {
            textLine1.setGravity(3);
            layoutparams.gravity = 3;
            textLine1.setLayoutParams(layoutparams);
        }
    }

    private void setupUI()
    {
        setContentView(0x7f030028);
        textLine1 = (TextView)findViewById(0x7f0a00ae);
        textLine2 = (TextView)findViewById(0x7f0a00af);
        textLine3 = (TextView)findViewById(0x7f0a00b0);
        sonosLinksContainer = (LinearLayout)findViewById(0x7f0a00b7);
        wifiImage = (ViewGroup)findViewById(0x7f0a00b1);
        buttons = findViewById(0x7f0a00b2);
        leftButton = (SonosButton)findViewById(0x7f0a00b3);
        leftButton.setOnClickListener(this);
        rightButton = (SonosButton)findViewById(0x7f0a00b4);
        rightButton.setOnClickListener(this);
        demoButton = (SonosButton)findViewById(0x7f0a00b6);
        demoButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onViewDemoButtonClicked(view);
            }

            final LimitedConnectivityActivity this$0;

            
            {
                this$0 = LimitedConnectivityActivity.this;
                super();
            }
        }
);
        sonosWebsiteLink = (SonosTextView)findViewById(0x7f0a00aa);
        sonosWebsiteLink.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onSonosWebsiteButtonClicked(view);
            }

            final LimitedConnectivityActivity this$0;

            
            {
                this$0 = LimitedConnectivityActivity.this;
                super();
            }
        }
);
        demoText = (TextView)findViewById(0x7f0a00b5);
        networkStateMonitor = new NetworkStateMonitor(this);
    }

    private void startSearchingTimer()
    {
        searching = true;
        searchingTimerHandler.postDelayed(new Runnable() {

            public void run()
            {
                stopSearchingTimer();
                timedOut = true;
                updateView();
            }

            final LimitedConnectivityActivity this$0;

            
            {
                this$0 = LimitedConnectivityActivity.this;
                super();
            }
        }
, 10000L);
    }

    private void stopSearchingTimer()
    {
        searching = false;
        searchingTimerHandler.removeCallbacksAndMessages(null);
    }

    private void updateView()
    {
        SCIPropertyBag scipropertybag;
        boolean flag = networkStateMonitor.isInternetAvailable();
        byte byte0 = 4;
        if((0xf & getResources().getConfiguration().screenLayout) >= 3)
            byte0 = 8;
        if(state == com.sonos.acr.sclib.SonosListener.HouseholdState.ConnectedNoZones && flag)
            byte0 = 0;
        sonosLinksContainer.setVisibility(byte0);
        demoButton.setVisibility(byte0);
        demoText.setVisibility(byte0);
        class _cls6
        {

            static final int $SwitchMap$com$sonos$acr$sclib$SonosListener$HouseholdState[];

            static 
            {
                $SwitchMap$com$sonos$acr$sclib$SonosListener$HouseholdState = new int[com.sonos.acr.sclib.SonosListener.HouseholdState.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$acr$sclib$SonosListener$HouseholdState[com.sonos.acr.sclib.SonosListener.HouseholdState.ConnectedPlayableZones.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$acr$sclib$SonosListener$HouseholdState[com.sonos.acr.sclib.SonosListener.HouseholdState.NoEstablishedHousehold.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        if(!networkStateMonitor.isWifiEnabled())
        {
            wifiImage.setVisibility(0);
            String s6 = getResources().getString(0x7f0c011c);
            leftButton.setText(s6);
            leftButton.setVisibility(0);
            inWifiState = true;
        } else
        {
            wifiImage.setVisibility(8);
            leftButton.setVisibility(8);
            inWifiState = false;
        }
        _cls6..SwitchMap.com.sonos.acr.sclib.SonosListener.HouseholdState[state.ordinal()];
        JVM INSTR tableswitch 1 2: default 156
    //                   1 320
    //                   2 348;
           goto _L1 _L2 _L3
_L1:
        scipropertybag = LibraryUtils.getHousehold().getZoneGroupManager().createLimitedConnectivityPropertyBag();
        if(!searching && (timedOut || !scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_PENDING))) goto _L5; else goto _L4
_L4:
        if(!searching)
            startSearchingTimer();
        s = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_PENDING);
        textLine1.setVisibility(0);
        textLine1.setText(s);
        textLine2.setVisibility(8);
        textLine3.setVisibility(8);
        if(!inWifiState)
            leftButton.setVisibility(8);
        rightButton.setVisibility(8);
        if(actionDialog != null)
        {
            actionDialog.dismiss();
            actionDialog = null;
        }
        doFirstLineAlignment();
_L7:
        return;
_L2:
        startActivity((new Intent(this, getCurrentStateActivity())).addFlags(0x34000000));
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        startActivity((new Intent(this, com/sonos/acr/SonosLaunchActivity)).addFlags(0x24000000));
        finish();
        continue; /* Loop/switch isn't completed */
_L5:
        String s1;
        if(scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_STRING_1))
        {
            String s5 = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_STRING_1);
            textLine1.setVisibility(0);
            textLine1.setText(s5);
        } else
        {
            textLine1.setVisibility(8);
        }
        if(scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_STRING_2))
        {
            String s4 = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_STRING_2);
            textLine2.setVisibility(0);
            textLine2.setText(s4);
        } else
        {
            textLine2.setVisibility(8);
        }
        if(scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_STRING_3))
        {
            String s3 = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_STRING_3);
            textLine3.setVisibility(0);
            textLine3.setText(s3);
        } else
        {
            textLine3.setVisibility(8);
        }
        if(scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_LABEL_1))
        {
            String s2 = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_LABEL_1);
            if(!inWifiState)
                leftButton.setVisibility(0);
            leftButton.setText(s2);
        } else
        if(!inWifiState)
            leftButton.setVisibility(8);
        if(!scipropertybag.doesPropExist(sclibConstants.SCIZONEGROUPMGR_LC_LABEL_2))
            break; /* Loop/switch isn't completed */
        s1 = scipropertybag.getStrProp(sclibConstants.SCIZONEGROUPMGR_LC_LABEL_2);
        rightButton.setVisibility(0);
        rightButton.setText(s1);
_L8:
        doFirstLineAlignment();
        if(true) goto _L7; else goto _L6
_L6:
        rightButton.setVisibility(8);
        if(actionDialog != null)
        {
            actionDialog.dismiss();
            actionDialog = null;
        }
          goto _L8
        if(true) goto _L7; else goto _L9
_L9:
    }

    public boolean canRunInLC()
    {
        return true;
    }

    protected boolean canRunWithoutWifi()
    {
        return true;
    }

    public void onClick(View view)
    {
        SimpleActionData simpleactiondata = new SimpleActionData(LibraryUtils.getHousehold().getZoneGroupManager().getLimitedConnectivityActions(), rightButton.getText().toString());
        if(view != leftButton) goto _L2; else goto _L1
_L1:
        if(!inWifiState) goto _L4; else goto _L3
_L3:
        WifiManager wifimanager = (WifiManager)getSystemService("wifi");
        wifimanager.setWifiEnabled(true);
        String s = getResources().getString(0x7f0c011d);
        leftButton.setText(s);
_L6:
        return;
_L4:
        if(simpleactiondata.size() > 0)
            simpleactiondata.getActionAt(0).perform();
        continue; /* Loop/switch isn't completed */
_L2:
        if(view == rightButton)
            if(simpleactiondata.size() == 2)
                simpleactiondata.getActionAt(1).perform();
            else
            if(simpleactiondata.size() > 0)
            {
                simpleactiondata.remoteItemAt(0);
                actionDialog = new LimitedConnectivityActionDialogFragment(simpleactiondata);
                actionDialog.show(getSupportFragmentManager(), "");
            }
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        setContentView(0x7f030028);
        setupUI();
        updateView();
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        super.onConnectionStatusChange(sonosnetworkmanager);
        updateView();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setupUI();
    }

    public void onHouseholdStateChange(com.sonos.acr.sclib.SonosListener.HouseholdState householdstate, com.sonos.acr.sclib.SonosListener.HouseholdSubState householdsubstate)
    {
        if(state != householdstate || subState != householdsubstate)
        {
            state = householdstate;
            subState = householdsubstate;
            updateView();
        }
    }

    protected void onPause()
    {
        super.onPause();
        SonosApplication.getInstance().getListener().unsubscribe(this);
        stopSearchingTimer();
        unregisterReceiver(networkConnectivityReceiver);
    }

    protected void onResume()
    {
        super.onResume();
        SonosListener sonoslistener = SonosApplication.getInstance().getListener();
        sonoslistener.subscribe(this);
        state = sonoslistener.getState();
        subState = sonoslistener.getSubState();
        searching = false;
        ApplicationStateManager.getInstance().closeAllActivitiesExcept(this);
        updateView();
        registerReceiver(networkConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void onSonosWebsiteButtonClicked(View view)
    {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(0x7f0c0112))));
    }

    public void onViewDemoButtonClicked(View view)
    {
        if(sonosNetworkManager.hasNoInternetConnection(false))
            showNoInternetMessage();
        else
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getLibrary().getRecommendedURL(SCILibrary.SC_URL_SONOS_DEMO))));
    }

    protected void showNoInternetMessage()
    {
        if(dialog == null)
            dialog = (new android.app.AlertDialog.Builder(this)).setMessage(getResources().getString(0x7f0c010b)).setCancelable(true).setPositiveButton(0x7f0c003c, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final LimitedConnectivityActivity this$0;

            
            {
                this$0 = LimitedConnectivityActivity.this;
                super();
            }
            }
).create();
        if(!dialog.isShowing())
            dialog.show();
    }

    ActionDialogFragment actionDialog;
    private View buttons;
    private SonosButton demoButton;
    private TextView demoText;
    private AlertDialog dialog;
    private boolean inWifiState;
    private SonosButton leftButton;
    private final BroadcastReceiver networkConnectivityReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            SLog.d(LOG_TAG, (new StringBuilder()).append("Received Intent: ").append(intent.getAction()).toString());
            if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
                updateView();
        }

        final LimitedConnectivityActivity this$0;

            
            {
                this$0 = LimitedConnectivityActivity.this;
                super();
            }
    }
;
    private NetworkStateMonitor networkStateMonitor;
    private SonosButton rightButton;
    private boolean searching;
    private Handler searchingTimerHandler;
    private LinearLayout sonosLinksContainer;
    private SonosTextView sonosWebsiteLink;
    private com.sonos.acr.sclib.SonosListener.HouseholdState state;
    private com.sonos.acr.sclib.SonosListener.HouseholdSubState subState;
    private TextView textLine1;
    private TextView textLine2;
    private TextView textLine3;
    private boolean timedOut;
    private ViewGroup wifiImage;



/*
    static boolean access$102(LimitedConnectivityActivity limitedconnectivityactivity, boolean flag)
    {
        limitedconnectivityactivity.timedOut = flag;
        return flag;
    }

*/

}
