// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.sonos.acr.application.AppDataStore;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.NetworkStateMonitor;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.SonosToast;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosEditText;
import com.sonos.acr.view.SonosImageView;
import com.sonos.acr.view.SonosTextView;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.acr.wizards.setup.SetupWizard;
import com.sonos.sclib.*;
import java.util.Locale;

// Referenced classes of package com.sonos.acr:
//            SonosFragment, SonosActivity

public class WelcomeFragment extends SonosFragment
    implements com.sonos.acr.network.SonosNetworkManager.ConnectionListener
{

    public WelcomeFragment()
    {
        onSignUpPage = false;
        dialog = null;
        focusSecond = false;
    }

    private void showWelcomeView()
    {
        focusTextField(false);
        emailText.setText("");
        postalCodeText.setText("");
        fadeIn = AnimationUtils.loadAnimation(getActivity(), 0x7f040001);
        fadeOut = AnimationUtils.loadAnimation(getActivity(), 0x7f040002);
        ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getView().findViewById(0x7f0a01ef).getWindowToken(), 0);
        swap(getView().findViewById(0x7f0a01ef), getView().findViewById(0x7f0a01e1));
        onSignUpPage = false;
        View view1 = getView().findViewById(0x7f0a00ad);
        View view2 = getView().findViewById(0x7f0a01ec);
        if(view1 != null && view2 != null)
            swap(view2, view1);
    }

    private void swap(View view1, View view2)
    {
        view1.setAnimation(fadeOut);
        view2.setAnimation(fadeIn);
        view1.setVisibility(8);
        view2.setVisibility(0);
        if(getView().findViewById(0x7f0a01ef).getVisibility() == 0)
        {
            focusTextField(focusSecond);
            if(focusSecond)
                ((InputMethodManager)getActivity().getSystemService("input_method")).showSoftInput(postalCodeText, 0);
            else
                ((InputMethodManager)getActivity().getSystemService("input_method")).showSoftInput(emailText, 0);
        }
    }

    private void updateWifiState()
    {
        if(networkStateMonitor.isInternetAvailable())
        {
            demoButton.setVisibility(0);
            informedButton.setVisibility(0);
            welcomeText.setVisibility(0);
            if(websiteButton != null)
                websiteButton.setVisibility(0);
        } else
        {
            demoButton.setVisibility(4);
            informedButton.setVisibility(4);
            welcomeText.setVisibility(8);
            if(websiteButton != null)
                websiteButton.setVisibility(4);
        }
        if(networkStateMonitor.isWifiDisabled() || !networkStateMonitor.isWifiConnected())
        {
            welcomeBody.setVisibility(8);
            setupButton.setVisibility(8);
            orText.setVisibility(8);
            noWifi.setVisibility(0);
            wifiImage.setVisibility(0);
            if(networkStateMonitor.isWifiDisabled())
            {
                enableWifiButton.setVisibility(0);
                enableWifiButton.setText(getResources().getString(0x7f0c011c));
            } else
            {
                enableWifiButton.setVisibility(8);
            }
        } else
        {
            welcomeBody.setVisibility(0);
            setupButton.setVisibility(0);
            orText.setVisibility(0);
            noWifi.setVisibility(8);
            wifiImage.setVisibility(8);
            enableWifiButton.setVisibility(8);
            demoButton.setVisibility(0);
            informedButton.setVisibility(0);
            welcomeText.setVisibility(0);
            if(websiteButton != null)
                websiteButton.setVisibility(0);
        }
    }

    public void focusTextField(boolean flag)
    {
        if(flag)
        {
            if(postalCodeText != null)
                postalCodeText.requestFocus();
            focusSecond = true;
        } else
        {
            if(emailText != null)
                emailText.requestFocus();
            focusSecond = false;
        }
    }

    public boolean isFlipped()
    {
        return onSignUpPage;
    }

    public boolean isSecondTextFieldSelected()
    {
        return postalCodeText.isFocused();
    }

    public boolean onBackPressed()
    {
        if(getView().findViewById(0x7f0a01e1).getVisibility() == 0)
            getActivity().finish();
        else
            showWelcomeView();
        return true;
    }

    public void onCancelButtonClicked(View view1)
    {
        showWelcomeView();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(focusSecond && postalCodeText != null)
            postalCodeText.requestFocus();
    }

    public void onConnectionStatusChange()
    {
        SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

            public void run()
            {
                updateWifiState();
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
, 50L);
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

            public void run()
            {
                updateWifiState();
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
, 50L);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        view = layoutinflater.inflate(0x7f0300ac, null);
        networkStateMonitor = new NetworkStateMonitor(getSonosActivity());
        emailText = (SonosEditText)view.findViewById(0x7f0a01f0);
        postalCodeText = (SonosEditText)view.findViewById(0x7f0a01f1);
        headerSubtext = (TextView)view.findViewById(0x7f0a01ee);
        postalCodeText.setInputType(0x80000);
        welcomeBody = (TextView)view.findViewById(0x7f0a01e3);
        noWifi = (TextView)view.findViewById(0x7f0a01e4);
        setupButton = (SonosButton)view.findViewById(0x7f0a01e5);
        setupButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onSetupButtonClicked(view1);
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        demoButton = (SonosButton)view.findViewById(0x7f0a01e8);
        demoButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onViewDemoButtonClicked(view1);
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        informedButton = (SonosButton)view.findViewById(0x7f0a01e9);
        informedButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onSignUpButtonClicked(view1);
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        signUpOk = (SonosButton)view.findViewById(0x7f0a01f3);
        signUpOk.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onSignUpSubmitButtonClicked(view1);
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        signUpCancel = (SonosButton)view.findViewById(0x7f0a01f4);
        signUpCancel.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onCancelButtonClicked(view1);
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        orText = (TextView)view.findViewById(0x7f0a01e6);
        wifiImage = (SonosImageView)view.findViewById(0x7f0a00b1);
        welcomeText = (TextView)view.findViewById(0x7f0a01e7);
        websiteButton = (SonosTextView)view.findViewById(0x7f0a00aa);
        if(websiteButton != null)
            websiteButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    onSonosWebsiteButtonClicked(null);
                }

                final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
            }
);
        enableWifiButton = (SonosButton)view.findViewById(0x7f0a01ea);
        enableWifiButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                ((WifiManager)getSonosActivity().getSystemService("wifi")).setWifiEnabled(true);
                updateWifiState();
_L2:
                return;
                SecurityException securityexception;
                securityexception;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
        }
);
        updateWifiState();
        return view;
    }

    public void onPause()
    {
        super.onPause();
        getSonosActivity().unregisterReceiver(networkConnectivityReceiver);
        getSonosActivity().sonosNetworkManager.unsubscribe(this);
    }

    public void onResume()
    {
        super.onResume();
        getSonosActivity().sonosNetworkManager.subscribe(this);
        getSonosActivity().registerReceiver(networkConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        updateWifiState();
    }

    public void onSetupButtonClicked(View view1)
    {
        startActivity((new Intent(getActivity(), com/sonos/acr/wizards/SonosWizardActivity)).putExtra("WIZARD_OBJECT", SonosApplication.getAppDataStore().put(new SetupWizard(null, null, SCRunWizardActionType.SCACTN_RUNWIZ_SETUP, null))));
    }

    public void onSignUpButtonClicked(View view1)
    {
        onSignUpButtonClicked(view1, true);
    }

    public void onSignUpButtonClicked(View view1, boolean flag)
    {
        View view2;
        View view3;
        if(flag)
        {
            fadeIn = AnimationUtils.loadAnimation(getActivity(), 0x7f040001);
            fadeOut = AnimationUtils.loadAnimation(getActivity(), 0x7f040002);
        } else
        {
            fadeIn = null;
            fadeOut = null;
        }
        swap(getView().findViewById(0x7f0a01e1), getView().findViewById(0x7f0a01ef));
        onSignUpPage = true;
        view2 = getView().findViewById(0x7f0a00ad);
        view3 = getView().findViewById(0x7f0a01ec);
        if(view2 != null && view3 != null)
            swap(view2, view3);
        getView().findViewById(0x7f0a01f3).setEnabled(true);
    }

    public void onSignUpSubmitButtonClicked(View view1)
    {
        String s = emailText.getText().toString();
        String s1 = postalCodeText.getText().toString();
        if(s.length() == 0)
            SonosToast.popupDialog(getResources().getString(0x7f0c00da), null);
        else
        if(s1.length() == 0)
        {
            SonosToast.popupDialog(getResources().getString(0x7f0c00db), null);
        } else
        {
            Locale locale = Locale.getDefault();
            String s2 = SCLibManager.getLanguageID(locale.getLanguage());
            if(s2 == null)
                s2 = (new StringBuilder()).append("").append(locale.getLanguage()).append("-").append(locale.getCountry()).toString();
            SCIOpRegEmailOptIn sciopregemailoptin = getSonosActivity().getSCOpFactory().createRegisterLeadOp(s, s2, s1, 10055);
            if(sciopregemailoptin != null)
            {
                if(sciopregemailoptin.getOpResult() == com.sonos.sclib.SCIOpRegEmailOptIn.RegEmailOptInResult.REOIOP_RESULT_BAD_EMAIL.swigValue())
                {
                    SonosToast.popupDialog(getResources().getString(0x7f0c00d9), null);
                } else
                {
                    submitDialog = new ProgressDialog(getActivity());
                    SCIOpCBSwigBase sciopcbswigbase = new SCIOpCBSwigBase() {

                        public void _operationComplete(long l, int i)
                        {
                            submitDialog.dismiss();
                            if(i == 0)
                            {
                                SLog.e(LOG_TAG, "Sign Up async IO operation succeeded ");
                                SonosToast.popupDialog(getResources().getString(0x7f0c00dd), null);
                            } else
                            {
                                SLog.e(LOG_TAG, "Sign Up async IO operation failed ");
                                SonosToast.popupDialog(getResources().getString(0x7f0c00d7), null);
                            }
                            showWelcomeView();
                        }

                        final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
                    }
;
                    submitDialog.show();
                    submitDialog.setContentView(0x7f03005c);
                    sciopregemailoptin._start(sciopcbswigbase);
                    view1.setEnabled(false);
                }
            } else
            {
                SLog.e(LOG_TAG, "SCLIB returned an null op for registration.");
                SonosToast.popupDialog(getResources().getString(0x7f0c00d7), null);
            }
        }
    }

    public void onSonosWebsiteButtonClicked(View view1)
    {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(0x7f0c0112))));
    }

    public void onViewDemoButtonClicked(View view1)
    {
        if(getSonosActivity().sonosNetworkManager.hasNoInternetConnection(false))
            showNoInternetMessage();
        else
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getLibrary().getRecommendedURL(SCILibrary.SC_URL_SONOS_DEMO))));
    }

    public void setBottomPadding(int i)
    {
        View view1 = view.findViewById(0x7f0a01e2);
        if(view1 != null)
            ViewUtils.setPaddingBottom(view1, i);
    }

    public void setWifiButtonText(String s)
    {
        enableWifiButton.setText(s);
    }

    protected void showNoInternetMessage()
    {
        if(dialog == null)
            dialog = (new android.app.AlertDialog.Builder(getActivity())).setMessage(getResources().getString(0x7f0c010b)).setCancelable(true).setPositiveButton(0x7f0c003c, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
            }
).create();
        if(!dialog.isShowing())
            dialog.show();
    }

    private final String LOG_TAG = (new StringBuilder()).append(com/sonos/acr/WelcomeFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).append(":").append(hashCode()).toString();
    private SonosButton demoButton;
    private AlertDialog dialog;
    private SonosEditText emailText;
    private SonosButton enableWifiButton;
    Animation fadeIn;
    Animation fadeOut;
    public boolean focusSecond;
    private TextView headerSubtext;
    private SonosButton informedButton;
    private final BroadcastReceiver networkConnectivityReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            SLog.d(LOG_TAG, (new StringBuilder()).append("Received Intent: ").append(intent.getAction()).toString());
            if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
                updateWifiState();
        }

        final WelcomeFragment this$0;

            
            {
                this$0 = WelcomeFragment.this;
                super();
            }
    }
;
    private NetworkStateMonitor networkStateMonitor;
    private TextView noWifi;
    private boolean onSignUpPage;
    private TextView orText;
    private SonosEditText postalCodeText;
    private SonosButton setupButton;
    private SonosButton signUpCancel;
    private SonosButton signUpOk;
    private ProgressDialog submitDialog;
    private View view;
    private SonosTextView websiteButton;
    private TextView welcomeBody;
    private TextView welcomeText;
    private SonosImageView wifiImage;




}
