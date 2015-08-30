// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.*;
import android.widget.*;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.SearchBrowseFragment;
import com.sonos.acr.browse.v2.queue.QueueFragment;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.FragmentHolderDialog;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.view.MainHeaderBar;
import com.sonos.acr.volume.fragments.GroupVolumeFragment;
import com.sonos.acr.zonemenu.RoomGroupingFragment;
import com.sonos.acr.zonemenu.RoomsMenuFragment;
import com.sonos.sclib.SCINowPlayingSource;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.nowplaying:
//            SonosHomeActivity, GroupVolumeController, MoreMenuOverlayFragment

public class SonosHomePhoneActivity extends SonosHomeActivity
{

    public SonosHomePhoneActivity()
    {
        isSliding = false;
    }

    private void hideDrawer()
    {
        Animation animation = AnimationUtils.loadAnimation(this, 0x7f040002);
        drawerLayout.setAnimation(animation);
        mainHeaderBar.setAnimation(animation);
        animation.start();
        drawerLayout.setVisibility(8);
        mainHeaderBar.setVisibility(8);
    }

    private void showDrawer()
    {
        Animation animation = AnimationUtils.loadAnimation(this, 0x7f040001);
        drawerLayout.setVisibility(0);
        mainHeaderBar.setVisibility(0);
        drawerLayout.setAnimation(animation);
        mainHeaderBar.setAnimation(animation);
        animation.start();
    }

    private boolean willDispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(groupVolumeFragment == null || !groupVolumeFragment.isVisible()) goto _L2; else goto _L1
_L1:
        int i = motionevent.getAction();
        if(i != flag && i != 0) goto _L2; else goto _L3
_L3:
        if(!groupVolumeFragment.willHandleTouchEvent(motionevent)) goto _L5; else goto _L4
_L4:
        if(i == flag)
            groupVolumeController.startGVDismiss();
        else
            groupVolumeController.pauseGVDismiss();
_L2:
        flag = false;
_L7:
        return flag;
_L5:
        if(i != 0)
            continue; /* Loop/switch isn't completed */
        groupVolumeController.performGVDismiss();
        if(true) goto _L7; else goto _L6
_L6:
        if(true) goto _L2; else goto _L8
_L8:
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(willDispatchTouchEvent(motionevent) || super.dispatchTouchEvent(motionevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean doBackLogic()
    {
        boolean flag;
        if(roomGroupingFragment != null && roomGroupingFragment.isVisible())
        {
            if(!roomGroupingFragment.onBackPressed())
                hideRoomGrouping();
        } else
        if(roomsMenuFragment != null && roomsMenuFragment.isVisible())
        {
            if(!roomsMenuFragment.onBackPressed())
                hideRooms();
        } else
        if(queueFragment != null && queueFragment.isVisible())
        {
            if(!queueFragment.onBackPressed())
                hideQueue();
        } else
        if(drawerLayout.isDrawerVisible(drawerView))
            drawerLayout.closeDrawers();
        else
        if(groupVolumeFragment != null && groupVolumeFragment.isVisible())
        {
            if(!groupVolumeFragment.onBackPressed())
                groupVolumeController.performGVDismiss();
        } else
        if(slidingPanel.isExpanded() && !browseMusicFragment.isAtBrowseRoot())
            showBrowseMusic();
        else
        if(!browseMusicFragment.onBackPressed())
        {
label0:
            {
                if(slidingPanel.isExpanded())
                    break label0;
                slidingPanel.expandPane();
            }
        }
        flag = true;
_L2:
        return flag;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void hideMusicMenu()
    {
        drawerLayout.closeDrawers();
    }

    public void hideQueue()
    {
        if(queueFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0, 0x7f040002).hide(queueFragment).commitAllowingStateLoss();
            super.hideQueue();
            showDrawer();
            queueFragment.setInEditMode(false);
        }
    }

    public boolean hideRooms()
    {
        boolean flag = false;
        if(roomsMenuFragment.isVisible())
        {
            AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
            alphaanimation.setDuration(getResources().getInteger(0x7f0b0008));
            drawerLayout.setVisibility(0);
            drawerLayout.startAnimation(alphaanimation);
            flag = super.hideRooms();
        }
        return flag;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        metadataText.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                View view1 = view.findViewById(0x7f0a00fb);
                showInfo(view1);
            }

            final SonosHomePhoneActivity this$0;

            
            {
                this$0 = SonosHomePhoneActivity.this;
                super();
            }
        }
);
        queueButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                showQueue();
            }

            final SonosHomePhoneActivity this$0;

            
            {
                this$0 = SonosHomePhoneActivity.this;
                super();
            }
        }
);
        mainHeaderBar.setDrawerButtonVisibility(0);
        drawerLayout.setDrawerLockMode(1);
        drawerLayout.setScrimColor(getResources().getColor(0x7f080029));
    }

    protected FragmentTransaction onCreateFragments(FragmentManager fragmentmanager, FragmentTransaction fragmenttransaction)
    {
        FragmentTransaction fragmenttransaction1 = super.onCreateFragments(fragmentmanager, fragmenttransaction);
        groupVolumeFragment = (GroupVolumeFragment)fragmentmanager.findFragmentById(0x7f0a01c1);
        browseMusicFragment.setForceAnimation(true);
        fragmenttransaction1.hide(groupVolumeFragment);
        fragmenttransaction1.hide(queueFragment);
        return fragmenttransaction1;
    }

    public void onDrawerButtonClicked()
    {
        super.onDrawerButtonClicked();
        if(!drawerLayout.isDrawerVisible(drawerView) && !isSliding && !slidingPanel.isExpanded())
            drawerLayout.openDrawer(drawerView);
        else
            drawerLayout.closeDrawers();
    }

    public void onHideGroupVolume()
    {
        if(groupVolumeFragment != null && groupVolumeFragment.isVisible())
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040001, 0x7f040002).hide(groupVolumeFragment).commitAllowingStateLoss();
    }

    public void onPanelCollapsed(View view)
    {
        super.onPanelCollapsed(view);
        drawerLayout.setDrawerLockMode(0);
        isSliding = false;
        mainHeaderBar.setDrawerButtonVisibility(0);
    }

    public void onPanelExpanded(View view)
    {
        super.onPanelExpanded(view);
        if(drawerLayout.isDrawerVisible(drawerView))
            hideMusicMenu();
        drawerLayout.setDrawerLockMode(1);
        mainHeaderBar.setDrawerButtonVisibility(8);
        isSliding = false;
    }

    public void onPanelSlide(View view, float f)
    {
        super.onPanelSlide(view, f);
        if(!isSliding)
        {
            hideMusicMenu();
            mainHeaderBar.setDrawerButtonVisibility(0);
            isSliding = true;
        }
    }

    public boolean onShowGroupVolume()
    {
        boolean flag;
        int i;
        flag = true;
        i = 0;
        if((roomsMenuFragment == null || !roomsMenuFragment.isVisible()) && (infoDialog == null || !infoDialog.isVisible())) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        ((RelativeLayout)findViewById(0x7f0a01b4)).requestFocus();
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        if(zonegroup != null)
            i = zonegroup.getDevices().size();
        if((i > flag || !slidingPanel.isExpanded()) && groupVolumeFragment != null && !groupVolumeFragment.isVisible() && queueFragment != null && !queueFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040001, 0x7f040002).show(groupVolumeFragment).commitAllowingStateLoss();
            if(drawerLayout.isDrawerVisible(drawerView))
                hideMusicMenu();
        } else
        {
            flag = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setContentView(int i)
    {
        super.setContentView(i);
        metadataText = (RelativeLayout)findViewById(0x7f0a00fa);
        queueButton = (ImageButton)findViewById(0x7f0a01e0);
        drawerView = findViewById(0x7f0a01bd);
        fragmentHolder = (FrameLayout)findViewById(0x7f0a0066);
        drawerLayout = (DrawerLayout)findViewById(0x7f0a01b5);
    }

    protected boolean shouldSuppressWhenSliding()
    {
        boolean flag;
        if(queueFragment == null || !queueFragment.isVisible())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void showInfo(View view)
    {
        NowPlaying nowplaying;
        nowplaying = getHousehold().getCurrentZoneGroup().nowPlaying;
        break MISSING_BLOCK_LABEL_11;
        if(nowplaying.hasMusic() && infoDialog == null)
        {
            final MoreMenuOverlayFragment final_sonosfragment = new MoreMenuOverlayFragment(nowplaying.sciNowPlayingSrc.getMoreMenuDataSource(), true, false, false);
            com.sonos.acr.browse.v2.PageFragment.PageFragmentListener pagefragmentlistener = new com.sonos.acr.browse.v2.PageFragment.PageFragmentListener() {

                public void onPageInvalidated(PageFragment pagefragment)
                {
                    if(infoDialog != null)
                        infoDialog.dismissAllowingStateLoss();
                }

                public void onPageUpdated(PageFragment pagefragment)
                {
                }

                final SonosHomePhoneActivity this$0;

            
            {
                this$0 = SonosHomePhoneActivity.this;
                super();
            }
            }
;
            final_sonosfragment.addPageFragmentListener(pagefragmentlistener);
            infoDialog = new FragmentHolderDialog(final_sonosfragment, pagefragmentlistener) {

                public void onDismiss(DialogInterface dialoginterface)
                {
                    super.onDismiss(dialoginterface);
                    moreMenuFragment.removePageFragmentListener(pageFragmentListener);
                    infoDialog = null;
                }

                final SonosHomePhoneActivity this$0;
                final MoreMenuOverlayFragment val$moreMenuFragment;
                final com.sonos.acr.browse.v2.PageFragment.PageFragmentListener val$pageFragmentListener;

            
            {
                this$0 = SonosHomePhoneActivity.this;
                moreMenuFragment = moremenuoverlayfragment;
                pageFragmentListener = pagefragmentlistener;
                super(final_sonosfragment, final_flag);
            }
            }
;
            infoDialog.show(getSupportFragmentManager(), "");
        }
        return;
    }

    protected void showMusicMenu()
    {
        if(!slidingPanel.isExpanded() && !slidingPanel.isPartiallyExpanded())
            drawerLayout.openDrawer(drawerView);
    }

    public void showNowPlaying()
    {
        super.showNowPlaying();
    }

    public void showQueue()
    {
        if(!queueFragment.isVisible() && !roomsMenuFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040001, 0).show(queueFragment).commitAllowingStateLoss();
            hideDrawer();
            getSupportFragmentManager().executePendingTransactions();
            onHideGroupVolume();
            groupVolumeController.cancelGVDismiss();
        }
    }

    public void showRooms()
    {
        if(!roomsMenuFragment.isVisible() && !slidingPanel.isSliding() && !queueFragment.isVisible())
        {
            AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, -0.5F);
            alphaanimation.setDuration(getResources().getInteger(0x7f0b0008));
            drawerLayout.startAnimation(alphaanimation);
            drawerLayout.setVisibility(4);
            super.showRooms();
        }
    }

    public void showSearch()
    {
        super.showSearch();
        if(drawerLayout.isDrawerOpen(drawerView))
            drawerLayout.closeDrawers();
    }

    DrawerLayout drawerLayout;
    View drawerView;
    FrameLayout fragmentHolder;
    GroupVolumeFragment groupVolumeFragment;
    FragmentHolderDialog infoDialog;
    boolean isSliding;
    RelativeLayout metadataText;
    ImageButton queueButton;
}
