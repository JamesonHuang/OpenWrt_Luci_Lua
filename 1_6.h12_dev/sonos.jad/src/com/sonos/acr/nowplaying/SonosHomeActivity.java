// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import com.sonos.acr.*;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.Browseable;
import com.sonos.acr.browse.v2.*;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.BrowseItemActionData;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;
import com.sonos.acr.browse.v2.queue.QueueFragment;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.nowplaying.controllers.TransportViewController;
import com.sonos.acr.nowplaying.controllers.VolumeViewController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.*;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.util.*;
import com.sonos.acr.view.MainHeaderBar;
import com.sonos.acr.view.MarqueeView;
import com.sonos.acr.zonemenu.RoomGroupingFragment;
import com.sonos.acr.zonemenu.RoomsMenuFragment;
import com.sonos.sclib.*;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.nowplaying:
//            GroupVolumeController

public abstract class SonosHomeActivity extends SonosActivity
    implements com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, Browseable, com.sonos.acr.sclib.SonosListener.StateListener, com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener, com.sonos.acr.view.MainHeaderBar.HeaderBarActionListener, com.sonos.acr.browse.v2.PageFragment.PageFragmentListener, GroupVolumeController.GroupVolumeListener, com.sonos.acr.zonemenu.RoomsMenuFragment.RoomsListener
{

    public SonosHomeActivity()
    {
        groupVolumeController = new GroupVolumeController(GROUPVOLUME_POPUP_DELAY);
        handler = new Handler();
        saveCallBack = new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                if(i != 802) goto _L2; else goto _L1
_L1:
                SonosToast.okDialog(0x7f0c00b8, SonosHomeActivity.this);
_L4:
                return;
_L2:
                if(i == 803)
                    SonosToast.popupDialog(0x7f0c00b7);
                else
                if(i == 804)
                    SonosToast.popupDialog(0x7f0c00b9);
                else
                if(i != 0)
                {
                    SonosToast.popupDialog(0x7f0c00bb);
                    SLog.e(LOG_TAG, (new StringBuilder()).append("Error ").append(i).append(" when saving queue!").toString());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
        }
;
        handler = SonosApplication.getInstance().getHandler();
    }

    private void showLastService()
    {
        String s;
        String s1;
        SharedPreferences sharedpreferences = LibraryUtils.getHouseholdPreferences();
        s = sharedpreferences.getString("LAST_SELECTED_SERVICE_URI", favoritesURI);
        s1 = sharedpreferences.getString("LAST_SELECTED_SERVICE_NAME", "");
        if(!browseMusicFragment.setBrowseRoot(s, s1)) goto _L2; else goto _L1
_L1:
        rootFragment.setItemSelected(s);
_L4:
        return;
_L2:
        if(!favoritesURI.equals(s) && browseMusicFragment.setBrowseRoot(favoritesURI, ""))
            rootFragment.setItemSelected(favoritesURI);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateFooterHeight()
    {
        View view = findViewById(0x7f0a00d8);
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(SCREEN.widthPx(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(SCREEN.heightPx(), 0x80000000));
        int i = view.getMeasuredHeight();
        slidingPanel.setPanelHeight(i);
    }

    private void validateBrowseRoot()
    {
        if((browseMusicFragment.isAtBrowseRoot() || browseMusicFragment.isBrowseEmpty()) && !LibraryUtils.getHouseholdPreferences().getString("LAST_SELECTED_SERVICE_URI", favoritesURI).equals(browseMusicFragment.getTopSCUri()))
            showLastService();
    }

    public void closeSearch()
    {
        browseMusicFragment.closeSearch();
    }

    public void dismiss()
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        if(fragmentmanager.getBackStackEntryCount() > 0)
            fragmentmanager.popBackStack();
    }

    public void displayBrowseStack(SCIBrowseStackManager scibrowsestackmanager)
    {
        browseMusicFragment.displayBrowseStack(scibrowsestackmanager);
        showBrowseMusic();
    }

    protected abstract boolean doBackLogic();

    protected com.sonos.acr.util.NavigationUtils.BackNavigationRouting getBackRoutingState(SCIActionContext sciactioncontext)
    {
        com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting;
        String s;
        backnavigationrouting = null;
        if(sciactioncontext == null)
            break MISSING_BLOCK_LABEL_47;
        SCIPropertyBag scipropertybag = sciactioncontext.getPropertyBag();
        if(scipropertybag == null)
            break MISSING_BLOCK_LABEL_47;
        s = scipropertybag.getStrProp("com.sonos.acr.util.backNavigationRoutingKey");
        if(s == null || s.length() <= 0)
            break MISSING_BLOCK_LABEL_47;
        com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting1 = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.valueOf(s);
        backnavigationrouting = backnavigationrouting1;
_L1:
        if(backnavigationrouting == null)
        {
            backnavigationrouting = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE;
            IllegalArgumentException illegalargumentexception;
            if(queueFragment.isVisible())
                backnavigationrouting = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE;
            else
            if(slidingPanel.isExpanded())
                backnavigationrouting = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_NOWPLAYING;
        }
        return backnavigationrouting;
        illegalargumentexception;
        SLog.e(LOG_TAG, (new StringBuilder()).append("Error ").append(s).append(" invalid navigation key").toString());
          goto _L1
    }

    public HouseholdController getHouseholdController()
    {
        return householdController;
    }

    public SCIOpCBSwigBase getSaveCallBack()
    {
        return saveCallBack;
    }

    public SearchController getSearchController()
    {
        return searchViewController;
    }

    public boolean handleKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        if(getHousehold() != null && getHousehold().getCurrentZoneGroup() != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(slidingPanel.isSliding() && shouldSuppressWhenSliding())
            flag = true;
        else
        if(i == 24 || i == 25)
            flag = currentZoneGroupController.getVolumeViewController().onVolumeKeyDown(i, "");
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean handleKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        if(getHousehold() != null && getHousehold().getCurrentZoneGroup() != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(slidingPanel.isSliding() && shouldSuppressWhenSliding())
            flag = true;
        else
        if(i == 24 || i == 25)
            flag = currentZoneGroupController.getVolumeViewController().onVolumeKeyUp(i, "");
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void hideMusicMenu()
    {
    }

    public void hideRoomGrouping()
    {
        if(roomGroupingFragment != null)
        {
            FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
            fragmenttransaction.setCustomAnimations(0, 0x7f040002);
            fragmenttransaction.remove(roomGroupingFragment);
            fragmenttransaction.commitAllowingStateLoss();
            roomGroupingFragment = null;
            mainHeaderBar.hideRoomGrouping();
        }
    }

    public boolean hideRooms()
    {
        boolean flag = true;
        if(roomsMenuFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0, 0x7f040010).hide(roomsMenuFragment).commitAllowingStateLoss();
            if(browseMusicFragment.isSearching() && !slidingPanel.isExpanded())
                browseMusicFragment.showSearch(flag);
            mainHeaderBar.hideRooms();
            hideRoomGrouping();
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isQueueVisible()
    {
        return queueFragment.isVisible();
    }

    public void onActiveChanged(SonosFragment sonosfragment, boolean flag)
    {
    }

    public void onAnimationEnd(SonosFragment sonosfragment)
    {
    }

    public void onAnimationStart(SonosFragment sonosfragment)
    {
    }

    public void onBackPressed()
    {
        if(!doBackLogic() && !requestExit())
            super.onBackPressed();
    }

    public void onCloseRoomsClicked(View view)
    {
        hideRooms();
    }

    public void onCollapseClicked()
    {
        showBrowseMusic();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        ApplicationStateManager.getInstance().closeAllActivitiesExcept(this);
        favoritesURI = sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Favorites);
        marqueeController = new MarqueeController();
        searchViewController = new SearchController();
        householdController = new HouseholdController(this);
        currentZoneGroupController = householdController.getCurrentZoneGroupController();
        setContentView(0x7f030098);
        FragmentManager fragmentmanager = getSupportFragmentManager();
        onCreateFragments(fragmentmanager, fragmentmanager.beginTransaction()).commit();
        currentZoneGroupController.getVolumeViewController().addVolumeViewListener(groupVolumeController);
        currentZoneGroupController.observeView(findViewById(0x7f0a007f));
        currentZoneGroupController.observeViewHierarchy((ViewGroup)findViewById(0x7f0a00f3));
        currentZoneGroupController.observeViewHierarchy(nowPlayingFooter);
        groupVolumeController.setGroupVolumeListener(this);
        browseMusicFragment.addPageFragmentListener(this);
        slidingPanel.setPanelSlideListener(this);
        findViewById(0x7f0a00d8).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                showNowPlaying();
            }

            final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
        }
);
        mainHeaderBar.setActionListener(this);
        rootFragment.setOnItemClickListener(new com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener() {

            public void onItemClick(BrowseItemCell browseitemcell)
            {
                rootItemClicked(browseitemcell);
            }

            public boolean onItemLongClick(BrowseItemCell browseitemcell)
            {
                return false;
            }

            final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
        }
);
        updateFooterHeight();
        setUpMarquees();
        roomsMenuFragment.addListener(this);
        rootFragment.addPageFragmentListener(this);
        if(SonosApplication.getInstance().getSCLibManager().isFirstRunAfterUpdate())
        {
            SonosApplication.getInstance().getSCLibManager().resetFirstRun();
            showBrowseMusic();
            onResumeRunnable = new Runnable() {

                public void run()
                {
                    showMusicMenu();
                    onResumeRunnable = null;
                }

                final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
            }
;
        } else
        {
            newIntent = getIntent();
        }
    }

    protected FragmentTransaction onCreateFragments(FragmentManager fragmentmanager, FragmentTransaction fragmenttransaction)
    {
        roomsMenuFragment = (RoomsMenuFragment)fragmentmanager.findFragmentById(0x7f0a01c0);
        browseMusicFragment = (SearchBrowseFragment)fragmentmanager.findFragmentById(0x7f0a01b9);
        queueFragment = (QueueFragment)fragmentmanager.findFragmentById(0x7f0a01c2);
        fragmenttransaction.hide(roomsMenuFragment);
        rootFragment = (MusicMenuFragment)fragmentmanager.findFragmentById(0x7f0a01be);
        return fragmenttransaction;
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f0e0000, menu);
        return true;
    }

    protected void onDestroy()
    {
        currentZoneGroupController.getVolumeViewController().removeVolumeViewListener(groupVolumeController);
        groupVolumeController.setGroupVolumeListener(null);
        roomsMenuFragment.removeListener(this);
        browseMusicFragment.removePageFragmentListener(this);
        super.onDestroy();
    }

    public void onDrawChild(Canvas canvas, View view, long l)
    {
    }

    public void onDrawerButtonClicked()
    {
    }

    protected void onGroupingAttached(boolean flag)
    {
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        ZoneGroup zonegroup = household.getCurrentZoneGroup();
        if((householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged || householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged) && zonegroup != null)
            mainHeaderBar.setCurrentZoneGroup(zonegroup);
    }

    public void onHouseholdStateChange(com.sonos.acr.sclib.SonosListener.HouseholdState householdstate, com.sonos.acr.sclib.SonosListener.HouseholdSubState householdsubstate)
    {
        if(isLimitedConnectivity())
            startActivity((new Intent(this, com/sonos/acr/LimitedConnectivityActivity)).addFlags(0x20020000));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        SLog.e(LOG_TAG, "OnKeyDown");
        boolean flag;
        if(handleKeyDown(i, keyevent) || super.onKeyDown(i, keyevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        SLog.e(LOG_TAG, "OnKeyUp");
        boolean flag;
        if(handleKeyUp(i, keyevent) || super.onKeyUp(i, keyevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onLayoutChanged(View view)
    {
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        newIntent = intent;
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
        {
            String s = nowplaying.getAsynchronousErrorString();
            if(s != null && !"".equals(s))
                SonosToast.popupDialog(s, null);
        }
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        if(pagefragment == browseMusicFragment && browseMusicFragment.isBrowseEmpty())
            validateBrowseRoot();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
        if(pagefragment == browseMusicFragment)
        {
            MainHeaderBar mainheaderbar = mainHeaderBar;
            boolean flag;
            if(!browseMusicFragment.isSearching())
                flag = true;
            else
                flag = false;
            mainheaderbar.setLightSearchButtonVisible(flag);
        }
        if(pagefragment == rootFragment)
            validateBrowseRoot();
    }

    public void onPanelCollapsed(View view)
    {
        SLog.d(LOG_TAG, "onPanelCollapsed: showing footer and browseMusic, Hiding nowplaying");
        nowPlayingAlphaFrame.setVisibility(8);
        nowPlayingFooter.setVisibility(0);
        browseMusicFrame.setVisibility(0);
        nowPlayingFooter.clearAnimation();
        nowPlayingAlphaFrame.clearAnimation();
        slidingPanel.setDragView(footerDragArea);
        slidingPanel.setShouldAllowTap(true);
        mainHeaderBar.updateSlideOffset(1.0F);
        ViewUtils.setAlpha(nowPlayingFooter, 1.0F);
        marqueeController.stop();
        LibraryUtils.getSharedPreferences().edit().putBoolean("StickyNowPlaying", false).commit();
    }

    public void onPanelExpanded(View view)
    {
        SLog.d(LOG_TAG, "onPanelExpanded: hiding footer and browseMusic, showing nowplaying");
        nowPlayingAlphaFrame.setVisibility(0);
        nowPlayingFooter.setVisibility(8);
        browseMusicFrame.setVisibility(8);
        nowPlayingFooter.clearAnimation();
        nowPlayingAlphaFrame.clearAnimation();
        slidingPanel.setDragView(null);
        mainHeaderBar.updateSlideOffset(0.0F);
        ViewUtils.setAlpha(nowPlayingAlphaFrame, 1.0F);
        marqueeController.start();
        LibraryUtils.getSharedPreferences().edit().putBoolean("StickyNowPlaying", true).commit();
    }

    public void onPanelSlide(View view, float f)
    {
        SLog.d(LOG_TAG, "onPanelSlide: making all views visible");
        nowPlayingAlphaFrame.setVisibility(0);
        nowPlayingFooter.setVisibility(0);
        browseMusicFrame.setVisibility(0);
        mainHeaderBar.updateSlideOffset(f);
        ViewUtils.setAlpha(nowPlayingFooter, f);
        ViewUtils.setAlpha(nowPlayingAlphaFrame, 1.0F - f);
    }

    protected void onPause()
    {
        super.onPause();
        CurrentNowPlayingEventSink.getInstance().removeListener(this);
        CurrentGroupVolumeEventSink.getInstance().removeListener(this);
        householdController.unsubscribe();
        HouseholdEventSink.getInstance().removeListener(searchViewController);
        SonosApplication.getInstance().getListener().unsubscribe(this);
        handler.removeCallbacks(intentProcessor);
        intentProcessor = null;
        marqueeController.stop();
    }

    protected void onResume()
    {
        super.onResume();
        SonosApplication.getInstance().getListener().subscribe(this);
        currentZoneGroupController.getTransportViewController().subscribe();
        HouseholdEventSink.getInstance().addListener(searchViewController);
        CurrentNowPlayingEventSink.getInstance().addListener(this);
        CurrentGroupVolumeEventSink.getInstance().addListener(this);
        if(newIntent != null)
        {
            processIntent(newIntent);
            newIntent = null;
        }
        householdController.subscribe();
        marqueeController.start();
        SonosApplication.getInstance().getHandler().postDelayed(onResumeRunnable, 3000L);
    }

    public void onRoomsSpinnerClicked(View view)
    {
        showRooms();
    }

    public void onSearchClicked()
    {
        showSearch();
    }

    public boolean onSearchRequested()
    {
        showSearch();
        return true;
    }

    protected void onStart()
    {
        super.onStart();
        validateBrowseRoot();
        HouseholdEventSink.getInstance().addListener(this);
    }

    protected void onStop()
    {
        super.onStop();
        HouseholdEventSink.getInstance().removeListener(this);
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
    }

    public void onZoneGroupSelected(String s, String s1)
    {
        ZoneGroup zonegroup = getHousehold().lookupZoneGroup(s);
        SCIActionContext sciactioncontext = LibraryUtils.getZoneGroupMgr().getActionForZoneGroup(s);
        if(sciactioncontext != null)
        {
            boolean flag;
            if(zonegroup == null || zonegroup.isCompatible() && !zonegroup.isUnconfigured() && !zonegroup.isUnbondedSUB())
                flag = true;
            else
                flag = false;
            if(flag)
                setCurrentZoneGroup(s);
            sciactioncontext.getPropertyBag().setBoolProp("fromRoomsMenu", true);
            sciactioncontext.perform();
            if(flag || DisplayController.getScreenType() != 0)
                SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

                    public void run()
                    {
                        hideRooms();
                    }

                    final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
                }
, 500L);
        }
    }

    public void popPages(int i)
    {
        browseMusicFragment.popPages(i);
        showBrowseMusic();
    }

    protected void processIntent(Intent intent)
    {
        intentProcessor = new Runnable() {

            public void run()
            {
                if("SHOW_METADATA".equals(action))
                    showNowPlaying();
                else
                if("SHOW_MUSICBROWSE".equals(action))
                {
                    showBrowseMusic();
                } else
                {
                    ZoneGroup zonegroup = CurrentNowPlayingEventSink.getInstance().getCurrentZoneGroup();
                    if(zonegroup == null || zonegroup.nowPlaying.hasMusic())
                        showNowPlaying();
                    else
                        showBrowseMusic();
                }
            }

            final SonosHomeActivity this$0;
            final String val$action;

            
            {
                this$0 = SonosHomeActivity.this;
                action = s;
                super();
            }
        }
;
        handler.postDelayed(intentProcessor, 750L);
    }

    public void pushURI(String s, String s1, boolean flag)
    {
        browseMusicFragment.pushURI(s, s1, flag, getBackRoutingState(null));
        showBrowseMusic();
    }

    public boolean requestExit()
    {
        SLog.i(LOG_TAG, "SonosActivity.backRequestsExit");
        if(!SonosApplication.isUserAMonkey())
            doExit();
        return true;
    }

    protected void rootItemClicked(BrowseItemCell browseitemcell)
    {
        SCIBrowseItem scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        if(!scibrowseitem.canPush()) goto _L4; else goto _L3
_L3:
        setBrowseRoot(scibrowseitem.getSCUri(), scibrowseitem.getPrimaryTitle());
        hideMusicMenu();
_L2:
        return;
_L4:
        BrowseItemActionData browseitemactiondata = new BrowseItemActionData(scibrowseitem, sclib.createDefaultSCIActionFilter(sclib.SC_ACTION_FILTERNAME_DEFAULT));
        if(browseitemactiondata.size() == 1 && !BrowseUtils.isPlayAction((ActionItem)browseitemactiondata.getActions().get(0)))
        {
            ActionItem actionitem = (ActionItem)browseitemactiondata.getActions().get(0);
            SLog.e(LOG_TAG, (new StringBuilder()).append("Executing Item: ").append(actionitem).toString());
            hideMusicMenu();
            actionitem.perform();
        } else
        if(browseitemactiondata.size() > 0)
            (new ActionDialogFragment(browseitemactiondata, false)).show(getSupportFragmentManager(), "");
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setBrowseRoot(String s, String s1)
    {
        LibraryUtils.getHouseholdPreferences().edit().putString("LAST_SELECTED_SERVICE_URI", s).putString("LAST_SELECTED_SERVICE_NAME", s1).commit();
        if(browseMusicFragment.setBrowseRoot(s, s1))
            rootFragment.setItemSelected(s);
    }

    public void setContentView(int i)
    {
        super.setContentView(i);
        mainHeaderBar = (MainHeaderBar)findViewById(0x7f0a01b6);
        slidingPanel = (SlidingUpPanelLayout)findViewById(0x7f0a01b7);
        nowPlayingFrame = (ViewGroup)findViewById(0x7f0a01bc);
        nowPlayingAlphaFrame = (ViewGroup)findViewById(0x7f0a01bb);
        nowPlayingOuterContainer = (ViewGroup)findViewById(0x7f0a00f7);
        nowPlayingFooter = (ViewGroup)findViewById(0x7f0a01ba);
        footerDragArea = findViewById(0x7f0a00d9);
        browseMusicFrame = (ViewGroup)findViewById(0x7f0a01b8);
        queueFrame = (ViewGroup)findViewById(0x7f0a01c2);
    }

    protected void setCurrentZoneGroup(String s)
    {
        Household household = getHousehold();
        if(household != null)
            household.setCurrentZoneGroup(s);
    }

    protected void setUpMarquees()
    {
        View view = findViewById(0x7f0a00fc);
        if(view instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view);
        View view1 = findViewById(0x7f0a00fd);
        if(view1 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view1);
        View view2 = findViewById(0x7f0a00fe);
        if(view2 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view2);
        View view3 = findViewById(0x7f0a00ff);
        if(view3 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view3);
    }

    protected boolean shouldSuppressWhenSliding()
    {
        return true;
    }

    public void showBrowseMusic()
    {
        SLog.d(LOG_TAG, "showBrowseMusic called");
        if(slidingPanel.isExpanded() || slidingPanel.isSliding())
            slidingPanel.collapsePane();
    }

    protected void showMusicMenu()
    {
        showBrowseMusic();
    }

    public void showNowPlaying()
    {
        if(!slidingPanel.isExpanded())
            slidingPanel.expandPane();
    }

    public void showPicker(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        browseMusicFragment.showPicker(scibrowsedatasource, s);
        showBrowseMusic();
    }

    public void showRoomGrouping(String s)
    {
        if(roomGroupingFragment == null)
        {
            roomGroupingFragment = new RoomGroupingFragment() {

                public void onAttach(Activity activity)
                {
                    super.onAttach(activity);
                    onGroupingAttached(true);
                }

                public void onDetach()
                {
                    super.onDetach();
                    onGroupingAttached(false);
                }

                final SonosHomeActivity this$0;

            
            {
                this$0 = SonosHomeActivity.this;
                super();
            }
            }
;
            roomGroupingFragment.setGroupingId(s);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040001, 0).add(0x7f0a015c, roomGroupingFragment).commitAllowingStateLoss();
            mainHeaderBar.showRoomGrouping();
        }
    }

    public void showRooms()
    {
        if(!roomsMenuFragment.isVisible() && !slidingPanel.isSliding())
        {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f04000f, 0).show(roomsMenuFragment).commitAllowingStateLoss();
            mainHeaderBar.showRooms();
        }
    }

    public void showSearch()
    {
        browseMusicFragment.showSearch(true, getBackRoutingState(null));
        showBrowseMusic();
    }

    public void showSearch(SCIPropertyBag scipropertybag)
    {
        super.showSearch(scipropertybag);
        getSearchController().setAggregateSearchData(scipropertybag);
        showSearch();
    }

    public void showTextPane(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata, SCIActionContext sciactioncontext)
    {
        browseMusicFragment.pushTextPane(s, s1, sciinfoviewtextpanemetadata, getBackRoutingState(sciactioncontext));
        showBrowseMusic();
    }

    public static final String ACTION_SHOW_METADATA = "SHOW_METADATA";
    public static final String ACTION_SHOW_MUSICBROWSE = "SHOW_MUSICBROWSE";
    public static final String FAST_VOLUME_ACCESS_PREF = "FastVolumeAccess";
    public static final int GROUPVOLUME_POPUP_DELAY = DbgProp.get("groupVolumeTimeout", 5000);
    public static final String LAST_SELECTED_SERVICE_NAME_PREF = "LAST_SELECTED_SERVICE_NAME";
    public static final String LAST_SELECTED_SERVICE_URI_PREF = "LAST_SELECTED_SERVICE_URI";
    protected static final Screen SCREEN = new Screen(SonosApplication.getInstance());
    public static final String STICKY_NOW_PLAYING = "StickyNowPlaying";
    protected SearchBrowseFragment browseMusicFragment;
    protected ViewGroup browseMusicFrame;
    protected ZoneGroupController currentZoneGroupController;
    private String favoritesURI;
    protected View footerDragArea;
    protected GroupVolumeController groupVolumeController;
    protected Handler handler;
    protected HouseholdController householdController;
    private Runnable intentProcessor;
    protected MainHeaderBar mainHeaderBar;
    protected MarqueeController marqueeController;
    protected Intent newIntent;
    protected ViewGroup nowPlayingAlphaFrame;
    protected ViewGroup nowPlayingFooter;
    protected ViewGroup nowPlayingFrame;
    protected ViewGroup nowPlayingOuterContainer;
    private Runnable onResumeRunnable;
    protected QueueFragment queueFragment;
    protected ViewGroup queueFrame;
    protected RoomGroupingFragment roomGroupingFragment;
    protected RoomsMenuFragment roomsMenuFragment;
    protected MusicMenuFragment rootFragment;
    private SCIOpCBSwigBase saveCallBack;
    protected SearchController searchViewController;
    protected SlidingUpPanelLayout slidingPanel;



/*
    static Runnable access$002(SonosHomeActivity sonoshomeactivity, Runnable runnable)
    {
        sonoshomeactivity.onResumeRunnable = runnable;
        return runnable;
    }

*/
}
