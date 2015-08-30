// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.DbgProp;
import com.sonos.acr.util.ImageUtils;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.InsetRelativeLayout;
import com.sonos.acr.view.SonosImageView;
import com.sonos.acr.view.SonosTextView;
import com.sonos.acr.wizards.SonosWizardActivity;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr:
//            SonosActivity, WelcomeFragment

public class SonosLaunchActivity extends SonosActivity
    implements com.sonos.acr.network.SonosNetworkManager.ConnectionListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.view.InsetRelativeLayout.InsetListener
{

    public SonosLaunchActivity()
    {
        handler = new Handler();
        startInFlippedState = false;
        isSecondTextFieldSelected = false;
        hasAttachedFragment = false;
    }

    private void doWebButtonVisibility()
    {
        if((0xf & getResources().getConfiguration().screenLayout) < 3) goto _L2; else goto _L1
_L1:
        if(hasAttachedFragment) goto _L4; else goto _L3
_L3:
        if(urlButton != null)
            urlButton.setVisibility(4);
_L2:
        return;
_L4:
        if(!sonosNetworkManager.isRunning() || !sonosNetworkManager.isWifiConnected())
        {
            if(sonosNetworkManager.isRunning() && !sonosNetworkManager.hasNoInternetConnection(false))
            {
                if(urlButton != null)
                    urlButton.setVisibility(0);
            } else
            if(urlButton != null)
                urlButton.setVisibility(4);
        } else
        if(urlButton != null)
            urlButton.setVisibility(0);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean householdContainsZoneGroup(String s)
    {
        boolean flag = false;
        Iterator iterator = getHousehold().getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(s.equals(((ZoneGroup)iterator.next()).getID()))
                flag = true;
        } while(true);
        return flag;
    }

    private void postDelayedStart(int i)
    {
        removeCallbacks();
        delayAction = new Runnable() {

            public void run()
            {
                removeCallbacks();
                startNextActivity();
            }

            final SonosLaunchActivity this$0;

            
            {
                this$0 = SonosLaunchActivity.this;
                super();
            }
        }
;
        handler.postDelayed(delayAction, i);
    }

    private void postDelayedStartCurrentNPActivity(int i)
    {
        removeCallbacks();
        delayAction = new Runnable() {

            public void run()
            {
                removeCallbacks();
                startCurrentNPActivity();
            }

            final SonosLaunchActivity this$0;

            
            {
                this$0 = SonosLaunchActivity.this;
                super();
            }
        }
;
        handler.postDelayed(delayAction, i);
    }

    private void postInitialize()
    {
        removeCallbacks();
        delayAction = new Runnable() {

            public void run()
            {
                removeCallbacks();
                getApplicationContext().startServices();
                postDelayedStart(2 * SonosLaunchActivity.SPLASH_TIMEOUT);
_L2:
                return;
                NoClassDefFoundError noclassdeffounderror1;
                noclassdeffounderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final SonosLaunchActivity this$0;

            
            {
                this$0 = SonosLaunchActivity.this;
                super();
            }
        }
;
        handler.postDelayed(delayAction, SPLASH_TIMEOUT);
_L1:
        return;
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        LibraryUtils.showLinkErrors();
        ApplicationStateManager.getInstance().closeAllActivities();
          goto _L1
    }

    private void removeCallbacks()
    {
        handler.removeCallbacks(delayAction);
        delayAction = null;
    }

    private void setSplashImage()
    {
        File file1;
        File file = SCLibManager.getStorageRoot(this);
        file1 = null;
        if(file != null)
            file1 = new File((new StringBuilder()).append(file.getAbsolutePath()).append("/splash_rendered.png").toString());
        if(file1 == null || !file1.exists() || !file1.canRead()) goto _L2; else goto _L1
_L1:
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
        options.inScaled = false;
        bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
_L4:
        splash.setImageBitmap(bitmap);
        return;
_L2:
        Point point = ViewUtils.getDisplaySize(this);
        int i = Math.max(point.x, point.y);
        if(file1 != null)
            try
            {
                bitmap = ImageUtils.getBitmap(SVG.getFromResource(getResources(), 0x7f060006), new RectF(0.0F, 0.0F, i, i));
                FileOutputStream fileoutputstream = new FileOutputStream(file1);
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fileoutputstream);
            }
            catch(FileNotFoundException filenotfoundexception)
            {
                SLog.e(LOG_TAG, "Splash Image not found: ", filenotfoundexception);
                filenotfoundexception.printStackTrace();
            }
            catch(SVGParseException svgparseexception)
            {
                SLog.e(LOG_TAG, "Error Parsing the splash image");
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setupUI()
    {
        setContentView(0x7f030027);
        splash = (SonosImageView)findViewById(0x7f0a00a5);
        buildTypeText = (TextView)findViewById(0x7f0a00a6);
        urlButton = (SonosTextView)findViewById(0x7f0a00aa);
        urlButton.setVisibility(8);
        urlButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onSonosWebsiteButtonClicked(view);
            }

            final SonosLaunchActivity this$0;

            
            {
                this$0 = SonosLaunchActivity.this;
                super();
            }
        }
);
        splashLogo = (ImageView)findViewById(0x7f0a00a9);
        ((InsetRelativeLayout)findViewById(0x7f0a00a4)).setListener(this);
    }

    private void startCurrentNPActivity()
    {
        HouseholdEventSink.getInstance().removeListener(this);
        String s = getIntent().getStringExtra("com.sonos.intent.extra.ZGID");
        if(StringUtils.isNotEmptyOrNull(s) && householdContainsZoneGroup(s))
        {
            getHousehold().setCurrentZoneGroup(s);
            showNowPlaying(true);
        } else
        if(LibraryUtils.getSharedPreferences().getBoolean("StickyNowPlaying", false))
            showNowPlaying(true);
        else
            showBrowseMusic(true);
        finish();
    }

    public boolean canRunInLC()
    {
        return true;
    }

    protected boolean canRunWithoutWifi()
    {
        return true;
    }

    public void onBackPressed()
    {
        if(welcomeFragment != null)
            welcomeFragment.onBackPressed();
        else
            super.onBackPressed();
    }

    public void onCancelButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onCancelButtonClicked(view);
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        if(LibraryUtils.getSCLibManager().isInitialized() && sonosnetworkmanager.isWifiConnected())
            postDelayedStart(2 * SPLASH_TIMEOUT);
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onConnectionStatusChange();
        doWebButtonVisibility();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(bundle != null)
        {
            hasAttachedFragment = bundle.getBoolean("hasAttachedFragment", false);
            startInFlippedState = bundle.getBoolean("isFragmentFlipped", false);
            isSecondTextFieldSelected = bundle.getBoolean("isSecondTextFieldSelected", false);
        }
        setupUI();
        if(!LibraryUtils.getSCLibManager().isInitialized() || !"".equals(getHousehold().getID()) && LibraryUtils.getCurrentZoneGroup() == null)
        {
            setSplashImage();
        } else
        {
            splash.setVisibility(8);
            buildTypeText.setVisibility(8);
        }
        if(welcomeFragment == null)
        {
            welcomeFragment = (WelcomeFragment)getSupportFragmentManager().findFragmentById(0x7f0a00a7);
            getSupportFragmentManager().beginTransaction().hide(welcomeFragment).commit();
            if(hasAttachedFragment)
            {
                getSupportFragmentManager().beginTransaction().show(welcomeFragment).commit();
                if(splashLogo != null)
                    splashLogo.setVisibility(0);
            }
            if(startInFlippedState)
                welcomeFragment.onSignUpButtonClicked(null, false);
            welcomeFragment.focusTextField(isSecondTextFieldSelected);
            welcomeFragment.onConnectionStatusChange();
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(bitmap != null)
            bitmap.recycle();
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        startNextActivity();
    }

    public void onInsetUpdate(Rect rect)
    {
        ViewUtils.setPaddingBottom(findViewById(0x7f0a00aa), rect.bottom);
        if(welcomeFragment != null)
            welcomeFragment.setBottomPadding(rect.bottom);
    }

    protected void onPause()
    {
        super.onPause();
        removeCallbacks();
        HouseholdEventSink.getInstance().removeListener(this);
_L2:
        return;
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void onResume()
    {
        super.onResume();
        if(LibraryUtils.getSCLibManager().isInitialized()) goto _L2; else goto _L1
_L1:
        postInitialize();
_L4:
        return;
_L2:
        Iterator iterator = ApplicationStateManager.getInstance().sonosActivities.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Context context = (Context)iterator.next();
            if((context instanceof SonosWizardActivity) && !((SonosWizardActivity)context).isFinishing())
                finish();
        } while(true);
        if(!isFinishing())
            startNextActivity();
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBoolean("hasAttachedFragment", hasAttachedFragment);
        bundle.putBoolean("isFragmentFlipped", welcomeFragment.isFlipped());
        bundle.putBoolean("isSecondTextFieldSelected", welcomeFragment.isSecondTextFieldSelected());
        super.onSaveInstanceState(bundle);
    }

    public void onSetupButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onSetupButtonClicked(view);
    }

    public void onSignUpButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onSignUpButtonClicked(view);
    }

    public void onSignUpSubmitButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onSignUpSubmitButtonClicked(view);
    }

    public void onSonosWebsiteButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onSonosWebsiteButtonClicked(view);
    }

    public void onViewDemoButtonClicked(View view)
    {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
        if(fragment != null)
            ((WelcomeFragment)fragment).onViewDemoButtonClicked(view);
    }

    public void startNextActivity()
    {
        if(LibraryUtils.getSCLibManager().isInitialized() && delayAction == null)
            if("".equals(getHousehold().getID()))
            {
                if(!hasAttachedFragment)
                {
                    hasAttachedFragment = true;
                    android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0a00a7);
                    FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(0x7f040003, 0x7f040004);
                    fragmenttransaction.show(fragment).commitAllowingStateLoss();
                    if(splashLogo != null)
                        splashLogo.setVisibility(0);
                    if((0xf & getResources().getConfiguration().screenLayout) >= 3)
                    {
                        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
                        alphaanimation.setDuration(720L);
                        alphaanimation.setInterpolator(new DecelerateInterpolator());
                        alphaanimation.setFillAfter(true);
                        AlphaAnimation alphaanimation1 = new AlphaAnimation(0.0F, 1.0F);
                        alphaanimation1.setDuration(720L);
                        alphaanimation1.setInterpolator(new DecelerateInterpolator());
                        alphaanimation1.setFillAfter(false);
                        doWebButtonVisibility();
                        if(urlButton.getVisibility() == 0)
                            urlButton.startAnimation(alphaanimation1);
                        if(splash.getVisibility() == 0)
                        {
                            buildTypeText.startAnimation(alphaanimation);
                            splash.startAnimation(alphaanimation);
                        }
                    }
                }
            } else
            if(LibraryUtils.getCurrentZoneGroup() == null)
            {
                HouseholdEventSink.getInstance().addListener(this);
                postDelayedStartCurrentNPActivity(SPLASH_TIMEOUT);
            } else
            {
                startCurrentNPActivity();
            }
    }

    private static final int SPLASH_TIMEOUT = DbgProp.get("splashTimeout", 500);
    private Bitmap bitmap;
    private TextView buildTypeText;
    private Runnable delayAction;
    protected Handler handler;
    private boolean hasAttachedFragment;
    boolean isSecondTextFieldSelected;
    private SonosImageView splash;
    private ImageView splashLogo;
    boolean startInFlippedState;
    private SonosTextView urlButton;
    WelcomeFragment welcomeFragment;





}
