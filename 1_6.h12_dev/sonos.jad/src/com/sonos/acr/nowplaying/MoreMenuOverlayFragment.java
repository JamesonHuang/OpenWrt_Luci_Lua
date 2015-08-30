// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.*;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.BrowseItemActionData;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;
import com.sonos.acr.browse.v2.pages.DataSourceFixedSectionedListPageFragment;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.infoview.InfoviewHeaderDataSourceEventSink;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.nowplaying.views.NowPlayingOverlayCell;
import com.sonos.acr.nowplaying.views.SleepTimerFragment;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.uibusymanager.SimpleUiBusyManager;
import com.sonos.acr.util.SLog;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosTextView;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.nowplaying:
//            SleepDialogHandler, SonosHomeActivity

public class MoreMenuOverlayFragment extends DataSourceFixedSectionedListPageFragment
    implements com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener, com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener, android.view.View.OnClickListener, SleepDialogHandler.SleepTimerListener
{

    public MoreMenuOverlayFragment(SCIBrowseDataSource scibrowsedatasource, boolean flag, boolean flag1, boolean flag2)
    {
        super(scibrowsedatasource);
        showHeader = false;
        hideUntilReady = false;
        dismissOnMusicChanged = false;
        showHeader = flag;
        dismissOnMusicChanged = flag1;
        hideUntilReady = flag2;
    }

    private boolean hideSleepTimer()
    {
        boolean flag = false;
        if(sleepTimerFragment != null)
        {
            getChildFragmentManager().beginTransaction().remove(sleepTimerFragment).commitAllowingStateLoss();
            sleepTimerFragment = null;
            menuContents.setVisibility(0);
            flag = true;
        }
        return flag;
    }

    private boolean showSleepTimer()
    {
        boolean flag;
        if(sleepTimerFragment == null)
        {
            sleepTimerFragment = new SleepTimerFragment();
            getChildFragmentManager().beginTransaction().add(0x7f0a00e5, sleepTimerFragment).commitAllowingStateLoss();
            getChildFragmentManager().executePendingTransactions();
            menuContents.setVisibility(8);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void updateCurrentZoneGroupListener()
    {
        if(zoneGroupController != null)
        {
            zoneGroupController.removeNowPlayingListener(this);
            zoneGroupController.ignoreViewHierarchy(headerView);
            zoneGroupController.ignoreViewHierarchy(playModeView);
            zoneGroupController.getSleepTimerHandler().unregisterSleepTimerListener(this);
        }
        zoneGroupController = getSonosActivity().getHouseholdController().getCurrentZoneGroupController();
        zoneGroupController.addNowPlayingListener(this);
        zoneGroupController.getSleepTimerHandler().registerSleepTimerListener(this);
        boolean flag;
        if(zoneGroupController.getSleepTimerHandler().getTimeRemainingSeconds() > 0)
            flag = true;
        else
            flag = false;
        onSleepTimerChanged(flag);
        zoneGroupController.observeViewHierarchy(playModeView);
        zoneGroupController.observeViewHierarchy(headerView);
    }

    private void updateHeader()
    {
        View view = rootView.findViewById(0x7f0a00cc);
        if(showHeader)
        {
            view.setVisibility(0);
            headerView.setVisibility(0);
            android.view.ViewGroup.LayoutParams layoutparams1 = menuContents.getLayoutParams();
            layoutparams1.width = -1;
            menuContents.setLayoutParams(layoutparams1);
            menuContents.setBackgroundResource(0x7f080069);
        } else
        {
            view.setVisibility(8);
            headerView.setVisibility(8);
            android.view.ViewGroup.LayoutParams layoutparams = menuContents.getLayoutParams();
            layoutparams.width = -2;
            menuContents.setLayoutParams(layoutparams);
            menuContents.setBackgroundResource(0x7f020025);
        }
    }

    public BrowseItemCell createCellView(int i)
    {
        return new NowPlayingOverlayCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new BrowseSectionHeader(themedContext) {

            protected int getLayoutId()
            {
                return 0x7f030037;
            }

            final MoreMenuOverlayFragment this$0;

            
            {
                this$0 = MoreMenuOverlayFragment.this;
                super(context);
            }
        }
;
    }

    public void dismiss()
    {
        invalidatePage();
    }

    protected int getLayoutId()
    {
        return 0x7f03003e;
    }

    public void onActiveChanged(boolean flag)
    {
        super.onActiveChanged(flag);
        if(flag)
        {
            zoneGroupController.ignoreViewHierarchy(headerView);
            hideSleepTimer();
        }
    }

    public boolean onBackPressed()
    {
        boolean flag;
        if(hideSleepTimer())
            flag = true;
        else
            flag = super.onBackPressed();
        return flag;
    }

    public void onClick(View view)
    {
        if(view == sleepTimerButton)
            if(sleepTimerFragment == null)
                showSleepTimer();
            else
                hideSleepTimer();
    }

    public void onDataSourceUpdated()
    {
        int i = 0;
        super.onDataSourceUpdated();
        if(rootView != null)
        {
            if(busyManager != null && (browseDataSource == null || browseDataSource.isValid()))
            {
                busyManager.stop();
                busyManager = null;
                rootView.postDelayed(new Runnable() {

                    public void run()
                    {
                        rootView.setVisibility(0);
                    }

                    final MoreMenuOverlayFragment this$0;

            
            {
                this$0 = MoreMenuOverlayFragment.this;
                super();
            }
                }
, 100L);
            }
            boolean flag;
            View view;
            int j;
            if(browseDataSource != null && (!browseDataSource.isValid() || browseDataSource.getNumItems() > 0L))
                flag = true;
            else
                flag = false;
            view = playModeLine;
            if(flag)
                j = 0;
            else
                j = 8;
            view.setVisibility(j);
            if(adapterListView != null)
            {
                AbsListView abslistview = adapterListView;
                if(!flag)
                    i = 8;
                abslistview.setVisibility(i);
            }
        }
        if(showHeader && browseDataSource != null && (headerDataSource == null || infoviewHeaderEventSink == null))
        {
            headerDataSource = (SCIInfoViewHeaderDataSource)browseDataSource.queryInterface(sclibConstants.SCIINFOVIEWHEADERDATASOURCE_INTERFACE);
            infoviewHeaderEventSink = new InfoviewHeaderDataSourceEventSink() {

                public void onInfoviewHeaderChanged(SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource)
                {
                    if(metadataText4 != null)
                        if(sciinfoviewheaderdatasource.getNumItems() > 3L)
                        {
                            SCIInfoViewHeaderItem sciinfoviewheaderitem = sciinfoviewheaderdatasource.getItemAtIndex(3L);
                            metadataText4.setText(sciinfoviewheaderitem.getValue());
                        } else
                        {
                            metadataText4.setText("");
                        }
                }

                final MoreMenuOverlayFragment this$0;

            
            {
                this$0 = MoreMenuOverlayFragment.this;
                super();
            }
            }
;
            headerDataSource.subscribe(infoviewHeaderEventSink);
        }
    }

    public void onDestroyView()
    {
        hideSleepTimer();
        super.onDestroyView();
        zoneGroupController.ignoreViewHierarchy(headerView);
        zoneGroupController.ignoreViewHierarchy(playModeView);
        zoneGroupController.removeNowPlayingListener(this);
        zoneGroupController.getSleepTimerHandler().unregisterSleepTimerListener(this);
        if(busyManager != null)
        {
            busyManager.stop();
            busyManager = null;
        }
    }

    public void onItemClick(BrowseItemCell browseitemcell)
    {
        SonosHomeActivity sonoshomeactivity;
        SCIBrowseItem scibrowseitem;
        sonoshomeactivity = null;
        scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        if(!scibrowseitem.canPush()) goto _L4; else goto _L3
_L3:
        FragmentActivity fragmentactivity = getActivity();
        if(fragmentactivity instanceof SonosHomeActivity)
            sonoshomeactivity = (SonosHomeActivity)fragmentactivity;
        if(sonoshomeactivity != null)
        {
            sonoshomeactivity.pushURI(scibrowseitem.getSCUri(), scibrowseitem.getPrimaryTitle(), true);
            sonoshomeactivity.showBrowseMusic();
        }
_L6:
        invalidatePage();
_L2:
        return;
_L4:
        if(scibrowseitem.canActOn())
        {
            BrowseItemActionData browseitemactiondata = new BrowseItemActionData(scibrowseitem, null);
            if(browseitemactiondata.size() == 1 && scibrowseitem.getMoreMenuDataSource() == null)
            {
                ActionItem actionitem = (ActionItem)browseitemactiondata.getActions().get(0);
                SLog.e(LOG_TAG, (new StringBuilder()).append("Executing Item: ").append(actionitem).toString());
                actionitem.perform();
            } else
            if(browseitemactiondata.size() > 0)
                (new ActionDialogFragment(browseitemactiondata, false)).show(getActivity().getSupportFragmentManager(), "");
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        return false;
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
        {
            SCIBrowseDataSource scibrowsedatasource;
            if(nowplaying != null)
                scibrowsedatasource = nowplaying.sciNowPlayingSrc.getMoreMenuDataSource();
            else
                scibrowsedatasource = null;
            if((browseDataSource == null || scibrowsedatasource == null || !browseDataSource.getSCUri().equals(scibrowsedatasource.getSCUri())) && dismissOnMusicChanged)
                dismiss();
            if(nowplaying == null || !nowplaying.hasMusic())
                dismiss();
        }
    }

    public void onPause()
    {
        super.onPause();
        setOnItemClickListener(null);
    }

    public void onResume()
    {
        super.onResume();
        setOnItemClickListener(this);
    }

    public void onSleepTimerChanged(boolean flag)
    {
        String s = zoneGroupController.getSleepTimerHandler().getTimeRemainingText();
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        spannablestringbuilder.append(new SpannableString(sleepTimerButton.getResources().getString(0x7f0c0017)));
        if(s != null)
        {
            SpannableString spannablestring = new SpannableString((new StringBuilder()).append(" (").append(s).append(")").toString());
            int i = getResources().getColor(0x7f08001f);
            if(s.matches(getResources().getString(0x7f0c00c1)))
                i = getResources().getColor(0x7f080037);
            spannablestring.setSpan(new ForegroundColorSpan(i), 0, spannablestring.length(), 0);
            spannablestringbuilder.append(spannablestring);
        }
        sleepTimerButton.setText(spannablestringbuilder, android.widget.TextView.BufferType.SPANNABLE);
    }

    public void onStop()
    {
        super.onStop();
        dismiss();
    }

    public void onUnsubscribeEventSinks()
    {
        super.onUnsubscribeEventSinks();
        if(headerDataSource != null)
        {
            headerDataSource.unsubscribe(infoviewHeaderEventSink);
            infoviewHeaderEventSink = null;
            headerDataSource = null;
        }
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        rootView = (ViewGroup)view.findViewById(0x7f0a00e5);
        menuContents = (ViewGroup)rootView.findViewById(0x7f0a00e6);
        playModeLine = rootView.findViewById(0x7f0a00eb);
        playModeView = (ViewGroup)rootView.findViewById(0x7f0a00ec);
        headerView = (ViewGroup)rootView.findViewById(0x7f0a00e7);
        metadataText4 = (SonosTextView)rootView.findViewById(0x7f0a00e9);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                invalidatePage();
            }

            final MoreMenuOverlayFragment this$0;

            
            {
                this$0 = MoreMenuOverlayFragment.this;
                super();
            }
        }
;
        rootView.findViewById(0x7f0a00cc).setOnClickListener(onclicklistener);
        updateHeader();
        sleepTimerButton = (SonosButton)view.findViewById(0x7f0a00ee);
        sleepTimerButton.setOnClickListener(this);
        updateCurrentZoneGroupListener();
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            LayoutTransition layouttransition = new LayoutTransition();
            layouttransition.setDuration(300L);
            layouttransition.setAnimator(0, null);
            layouttransition.setAnimator(1, null);
            if(android.os.Build.VERSION.SDK_INT > 15)
                layouttransition.enableTransitionType(4);
            rootView.setLayoutTransition(layouttransition);
        }
        if(hideUntilReady && browseDataSource != null && !browseDataSource.isValid())
        {
            rootView.setVisibility(4);
            busyManager = new SimpleUiBusyManager(getActivity());
            busyManager.start();
        }
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
    }

    private SimpleUiBusyManager busyManager;
    private boolean dismissOnMusicChanged;
    private SCIInfoViewHeaderDataSource headerDataSource;
    ViewGroup headerView;
    private boolean hideUntilReady;
    private InfoviewHeaderDataSourceEventSink infoviewHeaderEventSink;
    ViewGroup menuContents;
    SonosTextView metadataText4;
    View playModeLine;
    ViewGroup playModeView;
    ViewGroup rootView;
    boolean showHeader;
    private SonosButton sleepTimerButton;
    private SleepTimerFragment sleepTimerFragment;
    private ZoneGroupController zoneGroupController;

}
