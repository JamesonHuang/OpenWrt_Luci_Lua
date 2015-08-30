// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.util.Linkify;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.pages.DataSourcePageFragment;
import com.sonos.acr.browse.v2.pages.PageFactory;
import com.sonos.acr.nowplaying.controllers.*;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.browse.v2.settings:
//            SettingsFlipperFragment, AdvancedSettingsBrowsePageFragment, FixedSectionedSettingsBrowsePageFragment, SettingsBrowsePageFragment

public class SettingsActivity extends SonosActivity
    implements Browseable, PageFactory, com.sonos.acr.browse.v2.PageFragment.PageFragmentListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public SettingsActivity()
    {
        lastIntent = null;
    }

    private void updateActionBar()
    {
        if(android.os.Build.VERSION.SDK_INT > 10 && getActionBar() != null)
        {
            if(android.os.Build.VERSION.SDK_INT > 13)
                getActionBar().setIcon(0x106000d);
            getActionBar().setDisplayShowHomeEnabled(true);
            if(browseFlipperFragment.canGoBack())
            {
                if(android.os.Build.VERSION.SDK_INT > 13)
                    getActionBar().setHomeButtonEnabled(true);
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } else
            {
                if(android.os.Build.VERSION.SDK_INT > 13)
                    getActionBar().setHomeButtonEnabled(false);
                getActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    public boolean canRunInLC()
    {
        return true;
    }

    public DataSourcePageFragment createBrowsePage(SCIBrowseDataSource scibrowsedatasource)
    {
        Object obj;
        if(scibrowsedatasource != null && sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Advanced).equals(scibrowsedatasource.getSCUri()))
            obj = new AdvancedSettingsBrowsePageFragment(scibrowsedatasource);
        else
        if(scibrowsedatasource != null && (sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Alarms).equals(scibrowsedatasource.getSCUri()) || sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Services_MyMusicServices).equals(scibrowsedatasource.getSCUri())))
            obj = new FixedSectionedSettingsBrowsePageFragment(scibrowsedatasource);
        else
            obj = new SettingsBrowsePageFragment(scibrowsedatasource);
        return ((DataSourcePageFragment) (obj));
    }

    public void displayBrowseStack(SCIBrowseStackManager scibrowsestackmanager)
    {
        browseFlipperFragment.displayBrowseStack(scibrowsestackmanager);
    }

    public HouseholdController getHouseholdController()
    {
        return householdController;
    }

    public void onBackPressed()
    {
        if(!browseFlipperFragment.isVisible() || !browseFlipperFragment.onBackPressed())
            super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        FrameLayout framelayout = new FrameLayout(this);
        framelayout.setId(0x7f0a0001);
        setContentView(framelayout);
        browseFlipperFragment = new SettingsFlipperFragment();
        browseFlipperFragment.setPageFactory(this);
        browseFlipperFragment.addPageFragmentListener(this);
        getSupportFragmentManager().beginTransaction().replace(0x7f0a0001, browseFlipperFragment).commit();
        lastIntent = getIntent();
        householdController = new HouseholdController(this);
        updateActionBar();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f0e0000, menu);
        return true;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        browseFlipperFragment.removePageFragmentListener(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(getHousehold() == null || getHousehold().getAssociatedDevice() == null)
            jumpToLCScreen();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(getHousehold() == null || getHousehold().getCurrentZoneGroup() == null)
            flag = super.onKeyDown(i, keyevent);
        else
        if(i == 24 || i == 25)
            flag = getHouseholdController().getCurrentZoneGroupController().getVolumeViewController().onVolumeKeyDown(i, "");
        else
            flag = super.onKeyDown(i, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(getHousehold() == null || getHousehold().getCurrentZoneGroup() == null)
            flag = super.onKeyUp(i, keyevent);
        else
        if(i == 24 || i == 25)
            flag = getHouseholdController().getCurrentZoneGroupController().getVolumeViewController().onVolumeKeyUp(i, "");
        else
            flag = super.onKeyUp(i, keyevent);
        return flag;
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        lastIntent = intent;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag = true;
        if(menuitem.getItemId() == 0x102002c && browseFlipperFragment.canGoBack())
            popPages(flag);
        else
            flag = super.onOptionsItemSelected(menuitem);
        return flag;
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        updateActionBar();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
        updateActionBar();
    }

    protected void onPause()
    {
        super.onPause();
        HouseholdEventSink.getInstance().removeListener(this);
        householdController.unsubscribe();
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        PageFragment pagefragment = browseFlipperFragment.getTopPage();
        if(pagefragment instanceof DataSourcePageFragment)
        {
            String s = ((DataSourcePageFragment)pagefragment).getSCUri();
            if(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Alarms).equals(s))
                flag = true;
        }
        MenuItem menuitem = menu.findItem(0x7f0a0208);
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        menuitem.setVisible(flag1);
        menu.findItem(0x7f0a0207).setVisible(flag);
        return super.onPrepareOptionsMenu(menu);
    }

    protected void onResume()
    {
        super.onResume();
        HouseholdEventSink.getInstance().addListener(this);
        householdController.subscribe();
    }

    protected void onStart()
    {
        super.onStart();
        processIntent();
    }

    public void popPages(int i)
    {
        browseFlipperFragment.popPages(i);
    }

    protected void processIntent()
    {
        if(lastIntent != null)
        {
            if("SHOW_FRAGMENT".equals(lastIntent.getAction()))
            {
                Bundle bundle = lastIntent.getExtras();
                String s = bundle.getString("ROOT_URI");
                int i = bundle.getInt("ROOT_TITLE");
                browseFlipperFragment.setBrowseRoot(s, getResources().getString(i));
            }
            lastIntent = null;
        }
    }

    public void pushURI(String s, String s1, boolean flag)
    {
        browseFlipperFragment.pushURI(s, s1, flag);
    }

    public void showAlarmSettings()
    {
        browseFlipperFragment.pushURI(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Alarms), getResources().getString(0x7f0c005a), true, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public void showPicker(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        browseFlipperFragment.showPicker(scibrowsedatasource, s);
    }

    public void showSettings()
    {
        browseFlipperFragment.pushURI(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings), getResources().getString(0x7f0c00ca), true, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public void showTextPane(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata, SCIActionContext sciactioncontext)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        String s2 = getResources().getString(0x104000a);
        builder.setIcon(0x108009b);
        builder.setTitle(s);
        builder.setMessage(s1);
        builder.setPositiveButton(s2, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final SettingsActivity this$0;

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
        }
);
        builder.setCancelable(true);
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
        TextView textview = (TextView)alertdialog.findViewById(0x102000b);
        Linkify.addLinks(textview, 1);
        textview.setLinkTextColor(textview.getTextColors().getDefaultColor());
    }

    public static final String ACTION_SHOW = "SHOW_FRAGMENT";
    public static final String EXTRA_BROWSE_ROOT_URI = "ROOT_URI";
    public static final String EXTRA_BROWSE_THEME = "BROWSE_THEME";
    public static final String EXTRA_TITLEID = "ROOT_TITLE";
    protected SettingsFlipperFragment browseFlipperFragment;
    protected HouseholdController householdController;
    Intent lastIntent;
}
