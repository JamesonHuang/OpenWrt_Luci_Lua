// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.view.animation.*;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.v2.queue.QueueFragment;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.util.MarqueeController;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.*;
import com.sonos.acr.volume.views.VolumeSliderView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

// Referenced classes of package com.sonos.acr.nowplaying:
//            SonosHomeTabletActivity, GroupVolumeController, GroupVolumePopupWindow

public class SonosHomeLargeTabletActivity extends SonosHomeTabletActivity
{

    public SonosHomeLargeTabletActivity()
    {
        isSliding = false;
    }

    protected void concludeSizeChange()
    {
        super.concludeSizeChange();
        portAlbumArt.setRawImageResourceLockState(false);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(motionevent.getAction() == 0 && !isInLandscapeMode() && footerMasterVolume.isShown() && !ViewUtils.isMotionEventInView(motionevent, footerMasterVolume))
        {
            groupVolumeController.performGVDismiss();
            flag = true;
        } else
        {
            flag = super.dispatchTouchEvent(motionevent);
        }
        return flag;
    }

    protected boolean doBackLogic()
    {
        boolean flag;
        if(drawerLayout.isDrawerVisible(drawerView))
        {
            drawerLayout.closeDrawers();
            flag = true;
        } else
        {
            flag = super.doBackLogic();
        }
        return flag;
    }

    protected int getMinVolumeCount()
    {
        int i;
        if(isInLandscapeMode() || slidingPanel.isExpanded())
            i = 1;
        else
            i = 0;
        return i;
    }

    protected VolumeSliderView getVisibleMasterVolume()
    {
        VolumeSliderView volumesliderview;
        if(isInLandscapeMode())
        {
            if(!slidingPanel.isExpanded())
                volumesliderview = footerMasterVolume;
            else
            if(nowPlayingOuterContainerLandRight.getVisibility() == 0)
                volumesliderview = nowPlayingMasterVolumeLandRight;
            else
                volumesliderview = nowPlayingMasterVolumeLandLeft;
        } else
        if(!slidingPanel.isExpanded())
            volumesliderview = footerMasterVolume;
        else
            volumesliderview = nowPlayingMasterVolumePort;
        return volumesliderview;
    }

    protected void hideMusicMenu()
    {
        drawerLayout.closeDrawers();
    }

    public void hideQueue()
    {
        if(isInLandscapeMode())
        {
            DecelerateInterpolator decelerateinterpolator = new DecelerateInterpolator();
            if(queueFrame.isShown())
                queueFrame.startAnimation(AnimationUtils.loadAnimation(this, 0x7f04000e));
            queueFrame.setVisibility(8);
            nowPlayingOuterContainerLandRight.setVisibility(0);
            noQueueProgressBar.setVisibility(0);
            Animation animation = AnimationUtils.loadAnimation(this, 0x7f040001);
            animation.setDuration(ANIM_DURATION);
            animation.setStartOffset(ANIM_DURATION);
            animation.setInterpolator(decelerateinterpolator);
            nowPlayingOuterContainerLandRight.setAnimation(animation);
            noQueueProgressBar.setAnimation(animation);
            animation.startNow();
            Animation animation1 = AnimationUtils.loadAnimation(this, 0x7f040002);
            animation1.setDuration(ANIM_DURATION);
            animation1.setInterpolator(decelerateinterpolator);
            npMetadataOverlay.startAnimation(animation1);
            npMetadataOverlay.setVisibility(8);
            updateQueueButton(false);
            if(marqueeController != null)
                marqueeController.reset();
            queueFragment.updateScrollPosition(true);
        } else
        {
            super.hideQueue();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        currentZoneGroupController.observeViewHierarchy(nowPlayingOuterContainerLandRight);
    }

    public void onDrawerButtonClicked()
    {
        super.onDrawerButtonClicked();
        if(!drawerLayout.isDrawerVisible(drawerView) && !isSliding && !slidingPanel.isExpanded())
            drawerLayout.openDrawer(drawerView);
        else
            drawerLayout.closeDrawers();
    }

    protected void onGroupVolumeDismissed(View view)
    {
        super.onGroupVolumeDismissed(view);
        if(!isInLandscapeMode())
            footerMasterVolume.setVisibility(8);
        footerNowPlayingControls.setVisibility(0);
    }

    public void onHideGroupVolume()
    {
        if(!groupVolumePopupWindow.isShowing() && footerMasterVolume.getVisibility() == 0)
            onGroupVolumeDismissed(footerMasterVolume);
        super.onHideGroupVolume();
    }

    public void onPanelCollapsed(View view)
    {
        super.onPanelCollapsed(view);
        if(!isInLandscapeMode())
            drawerLayout.setDrawerLockMode(0);
        isSliding = false;
    }

    public void onPanelExpanded(View view)
    {
        super.onPanelExpanded(view);
        hideMusicMenu();
        drawerLayout.setDrawerLockMode(1);
        isSliding = false;
    }

    public void onPanelSlide(View view, float f)
    {
        super.onPanelSlide(view, f);
        if(!isSliding)
        {
            hideMusicMenu();
            isSliding = true;
        }
    }

    public boolean onShowGroupVolume()
    {
        boolean flag = super.onShowGroupVolume();
        if(flag)
            hideMusicMenu();
        return flag;
    }

    protected void prepareForSizeChange()
    {
        super.prepareForSizeChange();
        portAlbumArt.setRawImageResourceLockState(true);
    }

    public void setContentView(int i)
    {
        super.setContentView(i);
        browseMusicFragmentFrame = findViewById(0x7f0a01b9);
        rootListFragmentFrame = findViewById(0x7f0a01be);
        portRootListFragmentFrame = (ViewGroup)findViewById(0x7f0a01c9);
        landRootListFragmentFrame = (ViewGroup)findViewById(0x7f0a01c3);
        nowPlayingOuterContainerLandRight = (ViewGroup)findViewById(0x7f0a010d);
        nowPlayingOuterContainerLand = (ViewGroup)findViewById(0x7f0a0105);
        nowPlayingOuterContainerPort = (ViewGroup)findViewById(0x7f0a0112);
        noQueueProgressBar = findViewById(0x7f0a010c);
        npMetadataOverlay = findViewById(0x7f0a0106);
        footerNowPlayingControls = findViewById(0x7f0a00dc);
        nowPlayingMasterVolumeLandLeft = (VolumeSliderView)nowPlayingOuterContainerLand.findViewById(0x7f0a00f5);
        nowPlayingMasterVolumeLandRight = (VolumeSliderView)nowPlayingOuterContainerLandRight.findViewById(0x7f0a00f5);
        nowPlayingMasterVolumePort = (VolumeSliderView)nowPlayingOuterContainerPort.findViewById(0x7f0a00f5);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                View view1 = view.findViewById(0x7f0a00fb);
                showInfo(view1);
            }

            final SonosHomeLargeTabletActivity this$0;

            
            {
                this$0 = SonosHomeLargeTabletActivity.this;
                super();
            }
        }
;
        nowPlayingOuterContainerLand.findViewById(0x7f0a0107).setOnClickListener(onclicklistener);
        nowPlayingOuterContainerLandRight.findViewById(0x7f0a0107).setOnClickListener(onclicklistener);
        nowPlayingOuterContainerPort.findViewById(0x7f0a0107).setOnClickListener(onclicklistener);
        if(isInLandscapeMode())
            nowPlayingOuterContainer = nowPlayingOuterContainerLand;
        else
            nowPlayingOuterContainer = nowPlayingOuterContainerPort;
        footerMasterVolume = (VolumeSliderView)findViewById(0x7f0a00e2);
        drawerLayout = (DrawerLayout)findViewById(0x7f0a01b5);
        drawerLayout.setDrawerLockMode(1);
        drawerLayout.setScrimColor(getResources().getColor(0x7f080029));
        drawerView = findViewById(0x7f0a01bd);
        mainHeaderBar = (MainHeaderBar)findViewById(0x7f0a01b6);
        mainHeaderBar.setDrawerButtonVisibility(0);
        portAlbumArt = (RemoteImageView)nowPlayingOuterContainerPort.findViewById(0x7f0a002c);
    }

    protected void setUpMarquees()
    {
        super.setUpMarquees();
        View view = findViewById(0x7f0a0108);
        if(view instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view);
        View view1 = findViewById(0x7f0a0109);
        if(view1 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view1);
        View view2 = findViewById(0x7f0a010a);
        if(view2 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view2);
        View view3 = findViewById(0x7f0a010b);
        if(view3 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view3);
        View view4 = findViewById(0x7f0a010e);
        if(view4 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view4);
        View view5 = findViewById(0x7f0a010f);
        if(view5 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view5);
        View view6 = findViewById(0x7f0a0110);
        if(view6 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view6);
        View view7 = findViewById(0x7f0a0111);
        if(view7 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view7);
    }

    protected boolean showGroupVolumePopupWindow(int i, boolean flag)
    {
        boolean flag1 = true;
        if(!nowPlayingFooter.isShown() || isInLandscapeMode()) goto _L2; else goto _L1
_L1:
        footerNowPlayingControls.setVisibility(8);
        if(i != flag1) goto _L4; else goto _L3
_L3:
        footerMasterVolume.setVisibility(0);
_L6:
        return flag1;
_L4:
        footerMasterVolume.setVisibility(4);
        flag = false;
        nowPlayingFooter.measure(android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFooter.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFooter.getHeight(), 0x40000000));
        nowPlayingFooter.layout(nowPlayingFooter.getLeft(), nowPlayingFooter.getTop(), nowPlayingFooter.getRight(), nowPlayingFooter.getBottom());
_L2:
        flag1 = super.showGroupVolumePopupWindow(i, flag);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void showLandscape()
    {
        super.showLandscape();
        footerMasterVolume.setVisibility(0);
        footerNowPlayingControls.setVisibility(0);
        nowPlayingOuterContainerLand.clearAnimation();
        nowPlayingOuterContainerPort.clearAnimation();
        nowPlayingOuterContainerLand.setVisibility(0);
        nowPlayingOuterContainerPort.setVisibility(8);
        nowPlayingOuterContainer = nowPlayingOuterContainerLand;
        landRootListFragmentFrame.setVisibility(0);
        nowPlayingMasterVolumeLandLeft.setVisibility(0);
        nowPlayingMasterVolumeLandRight.setVisibility(0);
        ((ViewGroup)rootListFragmentFrame.getParent()).removeView(rootListFragmentFrame);
        landRootListFragmentFrame.addView(rootListFragmentFrame);
        hideMusicMenu();
        drawerLayout.setDrawerLockMode(1);
        ((android.widget.LinearLayout.LayoutParams)queueContainer.getLayoutParams()).weight = 1.0F;
        mainHeaderBar.setDrawerButtonVisibility(4);
        if(queueFrame.getVisibility() == 0)
        {
            nowPlayingOuterContainerLandRight.setVisibility(8);
            noQueueProgressBar.setVisibility(8);
            npMetadataOverlay.setVisibility(0);
        } else
        {
            nowPlayingOuterContainerLandRight.setVisibility(0);
            noQueueProgressBar.setVisibility(0);
            npMetadataOverlay.setVisibility(8);
        }
    }

    protected void showMusicMenu()
    {
        if(!isInLandscapeMode() && !slidingPanel.isExpanded() && !slidingPanel.isPartiallyExpanded())
            drawerLayout.openDrawer(drawerView);
    }

    protected void showPortrait()
    {
        super.showPortrait();
        footerMasterVolume.setVisibility(8);
        footerNowPlayingControls.setVisibility(0);
        nowPlayingMasterVolumePort.setVisibility(0);
        nowPlayingOuterContainerLand.setVisibility(8);
        nowPlayingOuterContainerPort.setVisibility(0);
        nowPlayingOuterContainer = nowPlayingOuterContainerPort;
        queueContainer.clearAnimation();
        nowPlayingOuterContainerLandRight.setVisibility(8);
        findViewById(0x7f0a010c).setVisibility(8);
        findViewById(0x7f0a0106).setVisibility(0);
        ((ViewGroup)rootListFragmentFrame.getParent()).removeView(rootListFragmentFrame);
        portRootListFragmentFrame.addView(rootListFragmentFrame);
        landRootListFragmentFrame.setVisibility(8);
        if(!slidingPanel.isExpanded())
            drawerLayout.setDrawerLockMode(0);
        mainHeaderBar.setDrawerButtonVisibility(0);
        if(queueFrame.getVisibility() != 0)
            ((android.widget.LinearLayout.LayoutParams)queueContainer.getLayoutParams()).weight = 0.0F;
        nowPlayingOuterContainerLandRight.setVisibility(8);
    }

    public void showQueue()
    {
        if(isInLandscapeMode())
        {
            AccelerateDecelerateInterpolator acceleratedecelerateinterpolator = new AccelerateDecelerateInterpolator();
            queueFrame.setVisibility(0);
            groupVolumeController.performGVDismiss();
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)queueFragment.getView().getLayoutParams();
            layoutparams.width = -1;
            queueFragment.getView().setLayoutParams(layoutparams);
            if(queueFrame.isShown())
            {
                ViewUtils.setAlpha(queueFrame, 1.0F);
                queueFrame.startAnimation(AnimationUtils.loadAnimation(this, 0x7f04000d));
            }
            AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
            alphaanimation.setDuration(ANIM_DURATION);
            nowPlayingOuterContainerLandRight.setAnimation(alphaanimation);
            noQueueProgressBar.setAnimation(alphaanimation);
            alphaanimation.setInterpolator(acceleratedecelerateinterpolator);
            alphaanimation.startNow();
            nowPlayingOuterContainerLandRight.setVisibility(8);
            noQueueProgressBar.setVisibility(8);
            npMetadataOverlay.setVisibility(0);
            AlphaAnimation alphaanimation1 = new AlphaAnimation(0.0F, 1.0F);
            alphaanimation1.setDuration(ANIM_DURATION);
            alphaanimation1.setStartOffset((long)(1.5D * (double)ANIM_DURATION));
            alphaanimation1.setInterpolator(acceleratedecelerateinterpolator);
            npMetadataOverlay.startAnimation(alphaanimation1);
            updateQueueButton(true);
            queueFragment.setInEditMode(false);
            if(marqueeController != null)
                marqueeController.reset();
        } else
        {
            super.showQueue();
        }
    }

    public void showRooms()
    {
        if(!slidingPanel.isSliding())
        {
            super.showRooms();
            hideMusicMenu();
        }
    }

    public void showSearch()
    {
        super.showSearch();
        if(drawerLayout.isDrawerOpen(drawerView))
            drawerLayout.closeDrawers();
    }

    protected void toggleQueueClicked()
    {
        super.toggleQueueClicked();
        marqueeController.reset();
    }

    public static final int ANIM_DURATION = SonosApplication.getInstance().getResources().getInteger(0x7f0b0007);
    protected View browseMusicFragmentFrame;
    protected DrawerLayout drawerLayout;
    protected View drawerView;
    protected VolumeSliderView footerMasterVolume;
    protected View footerNowPlayingControls;
    boolean isSliding;
    protected ViewGroup landRootListFragmentFrame;
    protected View noQueueProgressBar;
    protected VolumeSliderView nowPlayingMasterVolumeLandLeft;
    protected VolumeSliderView nowPlayingMasterVolumeLandRight;
    protected VolumeSliderView nowPlayingMasterVolumePort;
    protected ViewGroup nowPlayingOuterContainerLand;
    protected ViewGroup nowPlayingOuterContainerLandRight;
    protected ViewGroup nowPlayingOuterContainerPort;
    protected View npMetadataOverlay;
    protected RemoteImageView portAlbumArt;
    protected ViewGroup portRootListFragmentFrame;
    protected View rootListFragmentFrame;

}
