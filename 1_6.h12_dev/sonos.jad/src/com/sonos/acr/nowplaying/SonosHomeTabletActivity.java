// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.animation.*;
import android.widget.RelativeLayout;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.browse.v2.*;
import com.sonos.acr.browse.v2.pages.*;
import com.sonos.acr.browse.v2.queue.QueueFragment;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.acr.view.RemoteImageView;
import com.sonos.acr.view.SonosTextView;
import com.sonos.acr.volume.views.VolumeSliderView;
import com.sonos.acr.zonemenu.RoomGroupingFragment;
import com.sonos.acr.zonemenu.RoomsMenuFragment;
import com.sonos.sclib.*;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.nowplaying:
//            SonosHomeActivity, GroupVolumeController, GroupVolumePopupWindow, MoreMenuOverlayFragment

public abstract class SonosHomeTabletActivity extends SonosHomeActivity
    implements GroupVolumeController.GroupVolumeListener, com.sonos.acr.browse.v2.PageFragment.PageFragmentListener, com.sonos.acr.browse.v2.queue.QueueFragment.QueueInUseListener
{
    private class SlidingLayoutAnimation extends Animation
    {

        protected void applyTransformation(float f, Transformation transformation)
        {
            if(slidingPanel != null)
                slidingPanel.setPanelHeight((int)((float)panelHeight * (1.0F - f)));
        }

        public boolean willChangeBounds()
        {
            return true;
        }

        private final int panelHeight;
        final SonosHomeTabletActivity this$0;

        public SlidingLayoutAnimation()
        {
            this$0 = SonosHomeTabletActivity.this;
            super();
            panelHeight = slidingPanel.getPanelHeight();
        }
    }

    private class WeightAnimation extends Animation
    {

        protected void applyTransformation(float f, Transformation transformation)
        {
            if(queueContainer != null)
            {
                android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)queueContainer.getLayoutParams();
                layoutparams1.weight = mStartWeight + f * mDeltaWeight;
                queueContainer.setLayoutParams(layoutparams1);
            }
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)nowPlayingOuterContainer.getLayoutParams();
            layoutparams.gravity = 53;
            nowPlayingOuterContainer.setLayoutParams(layoutparams);
            forceNowPlayingFrameLayout();
            ViewGroup viewgroup = nowPlayingOuterContainer;
            ViewGroup viewgroup1 = nowPlayingOuterContainer;
            float f1;
            ViewGroup viewgroup2;
            if(mDeltaWeight < 0.0F)
                f1 = f;
            else
                f1 = 1.0F - f;
            ViewUtils.setTranslationX(viewgroup, -SonosHomeTabletActivity.calculateCenteringMargin(viewgroup1, f1));
            viewgroup2 = queueFrame;
            if(mDeltaWeight <= 0.0F)
                f = 1.0F - f;
            ViewUtils.setAlpha(viewgroup2, f);
        }

        public boolean willChangeBounds()
        {
            return true;
        }

        private final float mDeltaWeight;
        private final float mStartWeight;
        final SonosHomeTabletActivity this$0;

        public WeightAnimation(float f, float f1)
        {
            this$0 = SonosHomeTabletActivity.this;
            super();
            mStartWeight = f;
            mDeltaWeight = f1 - f;
        }
    }


    public SonosHomeTabletActivity()
    {
    }

    private static int calculateCenteringMargin(View view, float f)
    {
        View view1 = (View)view.getParent();
        return (int)((f * (float)(view1.getWidth() - view1.getPaddingLeft() - view1.getPaddingRight() - view.getWidth())) / 2.0F);
    }

    private int calculateQueueWidth()
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)queueFragment.getView().getLayoutParams();
        int i = layoutparams.width;
        layoutparams.width = -1;
        queueFragment.getView().setLayoutParams(layoutparams);
        android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)queueContainer.getLayoutParams();
        float f = layoutparams1.weight;
        layoutparams1.weight = 1.0F;
        queueContainer.setLayoutParams(layoutparams1);
        int j = queueFrame.getVisibility();
        queueFrame.setVisibility(0);
        nowPlayingFrame.measure(android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFrame.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFrame.getHeight(), 0x40000000));
        nowPlayingFrame.layout(nowPlayingFrame.getLeft(), nowPlayingFrame.getTop(), nowPlayingFrame.getRight(), nowPlayingFrame.getBottom());
        int k = queueFragment.getView().getWidth();
        SLog.e(LOG_TAG, (new StringBuilder()).append("Queue Width: ").append(k).toString());
        layoutparams.width = i;
        layoutparams1.weight = f;
        queueFrame.setVisibility(j);
        nowPlayingFrame.requestLayout();
        return k;
    }

    private boolean queueLockedClosed()
    {
        return LibraryUtils.getHouseholdPreferences().getBoolean("queueLockedClosed", false);
    }

    protected void concludeSizeChange()
    {
        albumArt.setRawImageResourceLockState(false);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(shouldEatTouchEvent(motionevent) || super.dispatchTouchEvent(motionevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean doBackLogic()
    {
        boolean flag;
        if(roomGroupingFragment != null && roomGroupingFragment.isVisible() && !roomGroupingFragment.onBackPressed())
            hideRoomGrouping();
        else
        if(roomsMenuFragment != null && roomsMenuFragment.isVisible() && !roomsMenuFragment.onBackPressed())
            hideRooms();
        else
        if(queueFrame.getVisibility() != 0 || !queueFragment.onBackPressed())
            if(groupVolumeController.isShown())
                groupVolumeController.performGVDismiss();
            else
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

    protected void doNowPlayingSizing(final boolean showQueue)
    {
        final android.widget.LinearLayout.LayoutParams lp;
        final android.widget.FrameLayout.LayoutParams nowPlayingParams;
        float f1;
        lp = (android.widget.LinearLayout.LayoutParams)queueContainer.getLayoutParams();
        nowPlayingParams = (android.widget.FrameLayout.LayoutParams)nowPlayingOuterContainer.getLayoutParams();
        float f = lp.weight;
        final int queueWidth;
        final View queueFragmentView;
        if(showQueue)
            f1 = 1.0F;
        else
            f1 = 0.0F;
        queueFrame.clearAnimation();
        groupVolumeController.performGVDismiss();
        if(!nowPlayingOuterContainer.isShown() || nowPlayingFrame.getWidth() <= 0) goto _L2; else goto _L1
_L1:
        queueWidth = calculateQueueWidth();
        weightAnimation = new WeightAnimation(f, f1);
        weightAnimation.setInterpolator(new DecelerateInterpolator(1.0F));
        weightAnimation.setDuration(400L);
        weightAnimation.setFillEnabled(false);
        queueFragmentView = queueFragment.getView();
        weightAnimation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                concludeSizeChange();
                if(!showQueue)
                {
                    lp.weight = 0.0F;
                    queueContainer.setLayoutParams(lp);
                    nowPlayingParams.gravity = 49;
                    queueFrame.setVisibility(4);
                }
                ViewUtils.setTranslationX(nowPlayingOuterContainer, 0.0F);
                nowPlayingOuterContainer.setLayoutParams(nowPlayingParams);
                if(showQueue)
                {
                    android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)queueFragmentView.getLayoutParams();
                    layoutparams1.width = -1;
                    queueFragmentView.setLayoutParams(layoutparams1);
                    ViewUtils.setAlpha(queueFrame, 1.0F);
                }
                queueContainer.clearAnimation();
                if(marqueeController != null)
                    marqueeController.start();
                toggleQueueButton.setEnabled(true);
                nowPlayingFrame.clearAnimation();
                nowPlayingOuterContainer.clearAnimation();
                queueFrame.clearAnimation();
                forceNowPlayingFrameLayout();
                weightAnimation = null;
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                float f2 = 0.0F;
                prepareForSizeChange();
                queueFrame.setVisibility(0);
                toggleQueueButton.setEnabled(false);
                android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)nowPlayingOuterContainer.getLayoutParams();
                layoutparams1.gravity = 53;
                nowPlayingOuterContainer.setLayoutParams(layoutparams1);
                forceNowPlayingFrameLayout();
                android.widget.FrameLayout.LayoutParams layoutparams2 = (android.widget.FrameLayout.LayoutParams)queueFragmentView.getLayoutParams();
                layoutparams2.width = queueWidth;
                queueFragmentView.setLayoutParams(layoutparams2);
                queueFragmentView.invalidate();
                if(showQueue)
                {
                    lp.weight = 0.0F;
                    queueFragment.updateScrollPosition(true);
                }
                ViewGroup viewgroup1 = nowPlayingOuterContainer;
                ViewGroup viewgroup2 = nowPlayingOuterContainer;
                if(showQueue)
                    f2 = 1.0F;
                ViewUtils.setTranslationX(viewgroup1, -SonosHomeTabletActivity.calculateCenteringMargin(viewgroup2, f2));
                marqueeController.stop();
            }

            final SonosHomeTabletActivity this$0;
            final android.widget.LinearLayout.LayoutParams val$lp;
            final android.widget.FrameLayout.LayoutParams val$nowPlayingParams;
            final View val$queueFragmentView;
            final int val$queueWidth;
            final boolean val$showQueue;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                queueFragmentView = view;
                queueWidth = i;
                showQueue = flag;
                lp = layoutparams;
                nowPlayingParams = layoutparams1;
                super();
            }
        }
);
        nowPlayingFrame.startAnimation(weightAnimation);
_L4:
        return;
_L2:
        lp.weight = f1;
        byte byte0;
        ViewGroup viewgroup;
        int i;
        if(showQueue)
            byte0 = 5;
        else
            byte0 = 1;
        nowPlayingParams.gravity = byte0 | 0x30;
        queueContainer.clearAnimation();
        viewgroup = queueFrame;
        if(showQueue)
            i = 0;
        else
            i = 4;
        viewgroup.setVisibility(i);
        queueContainer.setLayoutParams(lp);
        queueFragment.updateScrollPosition(true);
        if(showQueue)
        {
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)queueFragment.getView().getLayoutParams();
            layoutparams.width = -1;
            queueFragment.getView().setLayoutParams(layoutparams);
            ViewUtils.setAlpha(queueFrame, 1.0F);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void forceNowPlayingFrameLayout()
    {
        nowPlayingFrame.measure(android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFrame.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(nowPlayingFrame.getHeight(), 0x40000000));
        nowPlayingFrame.layout(nowPlayingFrame.getLeft(), nowPlayingFrame.getTop(), nowPlayingFrame.getRight(), nowPlayingFrame.getBottom());
    }

    protected com.sonos.acr.util.NavigationUtils.BackNavigationRouting getBackRoutingState(SCIActionContext sciactioncontext)
    {
        com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE;
        if(slidingPanel.isExpanded())
            backnavigationrouting = com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_NOWPLAYING;
        return backnavigationrouting;
    }

    protected int getMinVolumeCount()
    {
        return 1;
    }

    protected abstract VolumeSliderView getVisibleMasterVolume();

    public void hideQueue()
    {
        updateQueueButton(false);
        doNowPlayingSizing(false);
    }

    public void hideRoomGrouping()
    {
        if(roomGroupingFragment != null)
        {
            super.hideRoomGrouping();
            roomsMenuFragment.showRoomsHolder();
        }
    }

    protected boolean isInLandscapeMode()
    {
        boolean flag;
        if(getResources().getConfiguration().orientation == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onAnimationEnd(SonosFragment sonosfragment)
    {
        super.onAnimationEnd(sonosfragment);
        if(sonosfragment == roomsMenuFragment && !sonosfragment.isVisible() && slidingAnimation != null)
            slidingPanel.getChildAt(1).startAnimation(slidingAnimation);
    }

    public void onCollapseClicked()
    {
        if(!groupVolumePopupWindow.isShowing())
            showBrowseMusic();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        resolveOrientation();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        albumArt = (RemoteImageView)nowPlayingFrame.findViewById(0x7f0a002c);
        if(toggleQueueButton != null)
            toggleQueueButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    toggleQueueClicked();
                }

                final SonosHomeTabletActivity this$0;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                super();
            }
            }
);
        groupVolumePopupWindow = new GroupVolumePopupWindow(this, currentZoneGroupController);
        queueFragment.setQueueInUseListener(this);
        if(queueLockedClosed())
            hideQueue();
        else
        if(queueFragment.isInUse())
            showQueue();
        else
            hideQueue();
    }

    protected void onGroupVolumeDismissed(View view)
    {
        groupVolumeController.cancelGVDismiss();
        if(view != null)
            view.setVisibility(0);
    }

    protected void onGroupingAttached(boolean flag)
    {
        super.onGroupingAttached(flag);
        if(flag)
            roomsMenuFragment.hideRoomsHolder();
    }

    public void onHideGroupVolume()
    {
        if(groupVolumePopupWindow.isShowing())
            groupVolumePopupWindow.dismiss();
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        if(pickerDialog != null && pagefragment == pickerDialog.getChildFragment())
            pickerDialog.dismiss();
        super.onPageInvalidated(pagefragment);
    }

    public void onPanelCollapsed(View view)
    {
        super.onPanelCollapsed(view);
        if(!queueFragment.isInUse() && queueFrame.getVisibility() == 0)
            hideQueue();
        forceNowPlayingFrameLayout();
    }

    public void onQueueInUseChanged(boolean flag)
    {
        if(queueLockedClosed()) goto _L2; else goto _L1
_L1:
        boolean flag1;
        if(queueFrame.getVisibility() == 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag || flag1) goto _L4; else goto _L3
_L3:
        showQueue();
_L2:
        return;
_L4:
        if(!flag && flag1)
            hideQueue();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onShowGroupVolume()
    {
        boolean flag;
        int i;
        flag = false;
        i = 0;
        break MISSING_BLOCK_LABEL_4;
        while(true) 
        {
            do
                return flag;
            while(infoPopupFragment != null && infoPopupFragment.isVisible() || slidingPanel.isSliding() || weightAnimation != null || slidingPanel.isPartiallyExpanded());
            ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
            if(zonegroup != null)
                i = zonegroup.getDevices().size();
            ((RelativeLayout)findViewById(0x7f0a01b4)).requestFocus();
            if(i > getMinVolumeCount() && (roomGroupingFragment == null || !roomGroupingFragment.isVisible()))
            {
                if(roomsMenuFragment.isVisible())
                    hideRooms();
                flag = showGroupVolumePopupWindow(i, true);
            }
        }
    }

    protected void onStart()
    {
        super.onStart();
        resolveOrientation();
    }

    protected void prepareForSizeChange()
    {
        albumArt.setRawImageResourceLockState(true);
    }

    protected void resolveOrientation()
    {
        if(isInLandscapeMode())
            showLandscape();
        else
            showPortrait();
        groupVolumeController.performGVDismiss();
    }

    public void setContentView(int i)
    {
        super.setContentView(i);
        toggleQueueButton = findViewById(0x7f0a01c5);
        toggleQueueText = (SonosTextView)findViewById(0x7f0a01c7);
        toggleQueueHide = findViewById(0x7f0a01c8);
        toggleQueueShow = findViewById(0x7f0a01c6);
        queueContainer = (ViewGroup)findViewById(0x7f0a01c4);
    }

    protected void setCurrentZoneGroup(String s)
    {
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        if(StringUtils.isNotEmptyOrNull(s) && (zonegroup == null || !s.equals(zonegroup.getID())))
        {
            if(!slidingPanel.isExpanded())
            {
                slidingAnimation = new SlidingLayoutAnimation();
                slidingAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                slidingAnimation.setDuration(350L);
                slidingAnimation.setRepeatCount(1);
                slidingAnimation.setRepeatMode(2);
                getHouseholdController().getCurrentZoneGroupController().ignoreViewHierarchy(nowPlayingFooter);
                slidingAnimation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                    public void onAnimationEnd(Animation animation)
                    {
                        slidingPanel.getChildAt(1).clearAnimation();
                        slidingAnimation = null;
                    }

                    public void onAnimationRepeat(Animation animation)
                    {
                        getHouseholdController().getCurrentZoneGroupController().observeViewHierarchy(nowPlayingFooter);
                    }

                    public void onAnimationStart(Animation animation)
                    {
                    }

                    final SonosHomeTabletActivity this$0;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                super();
            }
                }
);
            }
            getHousehold().setCurrentZoneGroup(s);
        }
    }

    protected boolean shouldEatTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(roomsMenuFragment.isVisible() && !ViewUtils.isMotionEventInView(motionevent, roomsMenuFragment.getView()))
        {
            hideRooms();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void showBrowseMusic()
    {
        super.showBrowseMusic();
        if(roomsMenuFragment != null)
            hideRooms();
    }

    protected boolean showGroupVolumePopupWindow(int i, boolean flag)
    {
        boolean flag1;
        if(!groupVolumePopupWindow.isShowing())
        {
            final VolumeSliderView volumeView = getVisibleMasterVolume();
            groupVolumePopupWindow.setOnDismissListener(new com.sonos.acr.view.SonosPopupWindow.OnDismissListener() {

                public void onDismiss()
                {
                    onGroupVolumeDismissed(volumeView);
                }

                final SonosHomeTabletActivity this$0;
                final View val$volumeView;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                volumeView = view;
                super();
            }
            }
);
            groupVolumePopupWindow.showAsDropDown(volumeView);
            if(flag)
            {
                volumeView.startAnimation(AnimationUtils.loadAnimation(this, 0x7f040000));
                volumeView.setVisibility(4);
            }
            SLog.e(LOG_TAG, "Showing GroupVolume");
            flag1 = true;
        } else
        {
            SLog.e(LOG_TAG, "Not Showing GroupVolume: Already Shown");
            flag1 = false;
        }
        return flag1;
    }

    public void showInfo(View view)
    {
        NowPlaying nowplaying = getHousehold().getCurrentZoneGroup().nowPlaying;
        if(nowplaying.hasMusic())
        {
            final MoreMenuOverlayFragment final_fragment = new MoreMenuOverlayFragment(nowplaying.sciNowPlayingSrc.getMoreMenuDataSource(), false, true, true);
            com.sonos.acr.browse.v2.PageFragment.PageFragmentListener pagefragmentlistener = new com.sonos.acr.browse.v2.PageFragment.PageFragmentListener() {

                public void onPageInvalidated(PageFragment pagefragment)
                {
                    if(infoPopupFragment != null)
                        infoPopupFragment.dismissAllowingStateLoss();
                }

                public void onPageUpdated(PageFragment pagefragment)
                {
                }

                final SonosHomeTabletActivity this$0;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                super();
            }
            }
;
            final_fragment.addPageFragmentListener(pagefragmentlistener);
            infoPopupFragment = new ActionPopupFragment(pagefragmentlistener) {

                public void onPopupDismissed()
                {
                    super.onPopupDismissed();
                    moreMenuFragment.removePageFragmentListener(pageFragmentListener);
                    infoPopupFragment = null;
                }

                final SonosHomeTabletActivity this$0;
                final MoreMenuOverlayFragment val$moreMenuFragment;
                final com.sonos.acr.browse.v2.PageFragment.PageFragmentListener val$pageFragmentListener;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                moreMenuFragment = moremenuoverlayfragment;
                pageFragmentListener = pagefragmentlistener;
                super(final_fragment);
            }
            }
;
            infoPopupFragment.show(getSupportFragmentManager(), "", view);
        }
    }

    protected void showLandscape()
    {
    }

    public void showNowPlaying()
    {
        super.showNowPlaying();
        if(roomsMenuFragment != null)
            hideRooms();
    }

    public void showPicker(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        if(s.equals(sclibConstants.SC_ACTIONID_ADD_TO_PLAYLIST))
        {
            PickerFragment pickerfragment = new PickerFragment(scibrowsedatasource) {

                public void onViewCreated(View view, Bundle bundle)
                {
                    super.onViewCreated(view, bundle);
                    pushPage(createBrowsePage(rootDataSource), false, false);
                }

                final SonosHomeTabletActivity this$0;
                final SCIBrowseDataSource val$rootDataSource;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                rootDataSource = scibrowsedatasource;
                super(final_i);
            }
            }
;
            pickerfragment.setPageFactory(new PageFactory() {

                public DataSourcePageFragment createBrowsePage(SCIBrowseDataSource scibrowsedatasource1)
                {
                    return new PlaylistPickerPageFragment(scibrowsedatasource1);
                }

                final SonosHomeTabletActivity this$0;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                super();
            }
            }
);
            pickerfragment.addPageFragmentListener(this);
            pickerDialog = new FragmentHolderDialog(pickerfragment, false) {

                public void onDismiss(DialogInterface dialoginterface)
                {
                    super.onDismiss(dialoginterface);
                    pickerDialog = null;
                }

                final SonosHomeTabletActivity this$0;

            
            {
                this$0 = SonosHomeTabletActivity.this;
                super(sonosfragment, flag);
            }
            }
;
            pickerDialog.show(getSupportFragmentManager(), "");
        } else
        {
            super.showPicker(scibrowsedatasource, s);
        }
    }

    protected void showPortrait()
    {
    }

    public void showQueue()
    {
        updateQueueButton(true);
        doNowPlayingSizing(true);
        queueFragment.setInEditMode(false);
    }

    protected void toggleQueueClicked()
    {
        if(!groupVolumePopupWindow.isShowing())
        {
            boolean flag;
            if(queueFrame.getVisibility() == 0)
                flag = true;
            else
                flag = false;
            LibraryUtils.getHouseholdPreferences().edit().putBoolean("queueLockedClosed", flag).commit();
            if(!flag)
                showQueue();
            else
                hideQueue();
        }
    }

    protected void updateQueueButton(boolean flag)
    {
        if(flag)
        {
            toggleQueueText.setText(getResources().getString(0x7f0c0052));
            toggleQueueHide.setVisibility(0);
            toggleQueueShow.setVisibility(8);
        } else
        {
            toggleQueueText.setText(getResources().getString(0x7f0c00e7));
            toggleQueueHide.setVisibility(8);
            toggleQueueShow.setVisibility(0);
        }
    }

    public static final String QUEUE_LOCKED_CLOSED = "queueLockedClosed";
    private RemoteImageView albumArt;
    protected GroupVolumePopupWindow groupVolumePopupWindow;
    ActionPopupFragment infoPopupFragment;
    private FragmentHolderDialog pickerDialog;
    protected ViewGroup queueContainer;
    private Animation slidingAnimation;
    protected View toggleQueueButton;
    protected View toggleQueueHide;
    protected View toggleQueueShow;
    protected SonosTextView toggleQueueText;
    private WeightAnimation weightAnimation;



/*
    static WeightAnimation access$102(SonosHomeTabletActivity sonoshometabletactivity, WeightAnimation weightanimation)
    {
        sonoshometabletactivity.weightAnimation = weightanimation;
        return weightanimation;
    }

*/


/*
    static Animation access$202(SonosHomeTabletActivity sonoshometabletactivity, Animation animation)
    {
        sonoshometabletactivity.slidingAnimation = animation;
        return animation;
    }

*/


/*
    static FragmentHolderDialog access$302(SonosHomeTabletActivity sonoshometabletactivity, FragmentHolderDialog fragmentholderdialog)
    {
        sonoshometabletactivity.pickerDialog = fragmentholderdialog;
        return fragmentholderdialog;
    }

*/
}
