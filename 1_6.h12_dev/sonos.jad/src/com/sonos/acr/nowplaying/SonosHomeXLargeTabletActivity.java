// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.sonos.acr.util.MarqueeController;
import com.sonos.acr.view.MainHeaderBar;
import com.sonos.acr.view.MarqueeView;
import com.sonos.acr.volume.views.VolumeSliderView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

// Referenced classes of package com.sonos.acr.nowplaying:
//            SonosHomeTabletActivity

public class SonosHomeXLargeTabletActivity extends SonosHomeTabletActivity
{

    public SonosHomeXLargeTabletActivity()
    {
    }

    protected VolumeSliderView getVisibleMasterVolume()
    {
        VolumeSliderView volumesliderview;
        if(!slidingPanel.isExpanded())
        {
            if(isInLandscapeMode())
                volumesliderview = footerMasterVolumeLand;
            else
                volumesliderview = footerMasterVolumePort;
        } else
        {
            volumesliderview = nowPlayingMasterVolume;
        }
        return volumesliderview;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        expandedMetadataContainer = findViewById(0x7f0a0102);
        compressedMetadataContainer = findViewById(0x7f0a0101);
        nowPlayingFooterLand = (ViewGroup)findViewById(0x7f0a00e3);
        nowPlayingFooterPort = (ViewGroup)findViewById(0x7f0a00e4);
        nowPlayingMasterVolume = (VolumeSliderView)findViewById(0x7f0a00f5);
        footerMasterVolumeLand = (VolumeSliderView)nowPlayingFooterLand.findViewById(0x7f0a00e2);
        footerMasterVolumePort = (VolumeSliderView)nowPlayingFooterPort.findViewById(0x7f0a00e2);
        transportButtons = findViewById(0x7f0a00f6);
        nowPlayingMainContainer = findViewById(0x7f0a01cb);
        rootListFrame = findViewById(0x7f0a01ca);
        mainHeaderBar.setDrawerButtonVisibility(4);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                View view1 = view.findViewById(0x7f0a00fb);
                showInfo(view1);
            }

            final SonosHomeXLargeTabletActivity this$0;

            
            {
                this$0 = SonosHomeXLargeTabletActivity.this;
                super();
            }
        }
;
        findViewById(0x7f0a0100).setOnClickListener(onclicklistener);
    }

    protected void setUpMarquees()
    {
        View view = findViewById(0x7f0a00fc);
        if(view instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view);
        View view1 = findViewById(0x7f0a00fd);
        if(view1 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view1);
        View view2 = findViewById(0x7f0a0103);
        if(view2 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view2);
        View view3 = findViewById(0x7f0a0104);
        if(view3 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view3);
        View view4 = findViewById(0x7f0a00fe);
        if(view4 instanceof MarqueeView)
            marqueeController.addMarquee((MarqueeView)view4);
    }

    protected void showLandscape()
    {
        super.showLandscape();
        nowPlayingFooterLand.setVisibility(0);
        nowPlayingFooterPort.setVisibility(8);
        rootListFrame.getLayoutParams().width = getResources().getDimensionPixelSize(0x7f090090);
        expandedMetadataContainer.setVisibility(8);
        compressedMetadataContainer.setVisibility(0);
        transportButtons.setPadding(nowPlayingMainContainer.getPaddingLeft(), getResources().getDimensionPixelSize(0x7f09008f), getResources().getDimensionPixelSize(0x7f090095), nowPlayingMainContainer.getPaddingBottom());
        nowPlayingMainContainer.setPadding(transportButtons.getPaddingLeft(), 0, transportButtons.getPaddingRight(), transportButtons.getPaddingBottom());
        queueContainer.setPadding(queueContainer.getPaddingLeft(), 0, getResources().getDimensionPixelSize(0x7f090094), queueContainer.getPaddingBottom());
        toggleQueueButton.setPadding(toggleQueueButton.getPaddingLeft(), toggleQueueButton.getPaddingTop(), getResources().getDimensionPixelSize(0x7f090092), toggleQueueButton.getPaddingBottom());
    }

    protected void showPortrait()
    {
        super.showPortrait();
        nowPlayingFooterLand.setVisibility(8);
        nowPlayingFooterPort.setVisibility(0);
        rootListFrame.getLayoutParams().width = getResources().getDimensionPixelSize(0x7f090091);
        expandedMetadataContainer.setVisibility(0);
        compressedMetadataContainer.setVisibility(8);
        transportButtons.setPadding(transportButtons.getPaddingLeft(), getResources().getDimensionPixelSize(0x7f09008f), transportButtons.getPaddingRight(), transportButtons.getPaddingBottom());
        nowPlayingMainContainer.setPadding(nowPlayingMainContainer.getPaddingLeft(), getResources().getDimensionPixelSize(0x7f09006a), getResources().getDimensionPixelSize(0x7f090093), nowPlayingMainContainer.getPaddingBottom());
        queueContainer.setPadding(queueContainer.getPaddingLeft(), getResources().getDimensionPixelSize(0x7f09006a), getResources().getDimensionPixelSize(0x7f090093), queueContainer.getPaddingBottom());
        toggleQueueButton.setPadding(toggleQueueButton.getPaddingLeft(), toggleQueueButton.getPaddingTop(), getResources().getDimensionPixelSize(0x7f090065), toggleQueueButton.getPaddingBottom());
    }

    protected View compressedMetadataContainer;
    protected View expandedMetadataContainer;
    protected VolumeSliderView footerMasterVolumeLand;
    protected VolumeSliderView footerMasterVolumePort;
    protected ViewGroup nowPlayingFooterLand;
    protected ViewGroup nowPlayingFooterPort;
    protected View nowPlayingMainContainer;
    protected VolumeSliderView nowPlayingMasterVolume;
    protected View rootListFrame;
    protected View transportButtons;
}
