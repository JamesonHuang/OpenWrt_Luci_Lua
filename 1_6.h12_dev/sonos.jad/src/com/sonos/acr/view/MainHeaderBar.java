// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.*;
import android.widget.FrameLayout;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.DisplayController;
import com.sonos.acr.util.ViewUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosAlternateTextView

public class MainHeaderBar extends FrameLayout
{
    public static interface HeaderBarActionListener
    {

        public abstract void onCloseRoomsClicked(View view);

        public abstract void onCollapseClicked();

        public abstract void onDrawerButtonClicked();

        public abstract void onRoomsSpinnerClicked(View view);

        public abstract void onSearchClicked();
    }


    public MainHeaderBar(Context context)
    {
        this(context, null);
    }

    public MainHeaderBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public MainHeaderBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset);
        isLight = false;
        lightSearchButtonVisibility = 0;
        LayoutInflater.from(context).inflate(0x7f030022, this);
        lightRoomsNameContainer = findViewById(0x7f0a0090);
        darkRoomsNameContainer = findViewById(0x7f0a0084);
        drawerButton = findViewById(0x7f0a008f);
        drawerButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(actionListener != null)
                    actionListener.onDrawerButtonClicked();
            }

            final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
        }
);
        darkHeaderBarLayout = (ViewGroup)findViewById(0x7f0a0081);
        lightHeaderBarLayout = (ViewGroup)findViewById(0x7f0a008c);
        lightRoomsTitleContainer = findViewById(0x7f0a0093);
        darkRoomsTitleContainer = findViewById(0x7f0a0088);
        lightRoomGroupingTitleContainer = findViewById(0x7f0a0095);
        darkRoomGroupingTitleContainer = findViewById(0x7f0a008a);
        lightRoomFrame = findViewById(0x7f0a0092);
        darkRoomFrame = findViewById(0x7f0a0087);
        darkHeaderBarContents = findViewById(0x7f0a0082);
        lightHeaderBarContents = findViewById(0x7f0a008e);
        lightBackground = findViewById(0x7f0a008d);
        collapseButton = findViewById(0x7f0a0083);
        collapseButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(actionListener != null)
                    actionListener.onCollapseClicked();
            }

            final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
        }
);
        if(!isInEditMode())
        {
            android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(actionListener != null)
                        actionListener.onRoomsSpinnerClicked(view);
                }

                final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
            }
;
            android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(actionListener != null)
                        actionListener.onCloseRoomsClicked(view);
                }

                final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
            }
;
            lightRoomsNameContainer.setOnClickListener(onclicklistener);
            darkRoomsNameContainer.setOnClickListener(onclicklistener);
            lightRoomsTitleContainer.setOnClickListener(onclicklistener1);
            darkRoomsTitleContainer.setOnClickListener(onclicklistener1);
            android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(actionListener != null)
                        actionListener.onSearchClicked();
                }

                final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
            }
;
            darkSearchButton = findViewById(0x7f0a0086);
            darkSearchButton.setOnClickListener(onclicklistener2);
            lightSearchButton = findViewById(0x7f0a0097);
            lightSearchButton.setOnClickListener(onclicklistener2);
        }
    }

    private void clearAllAnimations()
    {
        lightRoomGroupingTitleContainer.clearAnimation();
        lightRoomsTitleContainer.clearAnimation();
        lightHeaderBarContents.clearAnimation();
        lightRoomFrame.clearAnimation();
        darkRoomsTitleContainer.clearAnimation();
        darkRoomGroupingTitleContainer.clearAnimation();
        darkHeaderBarContents.clearAnimation();
        darkRoomFrame.clearAnimation();
    }

    public void hideRoomGrouping()
    {
        if(DisplayController.getScreenType() != 0)
        {
            lightRoomsTitleContainer.setVisibility(0);
            darkRoomsTitleContainer.setVisibility(0);
            AnimationSet animationset = new AnimationSet(true);
            Animation animation = AnimationUtils.loadAnimation(getContext(), 0x7f040009);
            Animation animation1;
            if(isLight)
                lightRoomsTitleContainer.setAnimation(animation);
            else
                darkRoomsTitleContainer.setAnimation(animation);
            animationset.addAnimation(animation);
            animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f040008);
            if(isLight)
                lightRoomGroupingTitleContainer.setAnimation(animation1);
            else
                lightRoomGroupingTitleContainer.setAnimation(animation1);
            animationset.addAnimation(animation1);
            animationset.startNow();
            lightRoomGroupingTitleContainer.setVisibility(4);
            darkRoomGroupingTitleContainer.setVisibility(4);
        }
    }

    public void hideRooms()
    {
        View view;
        View view1;
        if(DisplayController.getScreenType() == 0)
        {
            AnimationSet animationset;
            Animation animation;
            Animation animation1;
            if(isLight)
                view = lightHeaderBarContents;
            else
                view = darkHeaderBarContents;
            if(isLight)
                view1 = darkRoomFrame;
            else
                view1 = darkRoomFrame;
            if(isLight)
                lightBackground.setVisibility(0);
        } else
        {
            if(isLight)
                view = lightRoomsNameContainer;
            else
                view = darkRoomsNameContainer;
            if(isLight)
                view1 = lightRoomFrame;
            else
                view1 = darkRoomFrame;
        }
        animationset = new AnimationSet(true);
        view.setVisibility(0);
        animation = AnimationUtils.loadAnimation(getContext(), 0x7f040009);
        view.setAnimation(animation);
        animationset.addAnimation(animation);
        animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f040008);
        view1.setAnimation(animation1);
        animationset.addAnimation(animation1);
        if(DisplayController.getScreenType() == 0 && isLight)
        {
            animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f040005);
            lightBackground.setAnimation(animation1);
            animationset.addAnimation(animation1);
        }
        animation1.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation2)
            {
                clearAllAnimations();
                if(isLight)
                    darkHeaderBarContents.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation2)
            {
            }

            public void onAnimationStart(Animation animation2)
            {
                MainHeaderBar mainheaderbar = MainHeaderBar.this;
                float f;
                if(isLight)
                    f = 1.0F;
                else
                    f = 0.0F;
                mainheaderbar.updateSlideOffset(f);
            }

            final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
        }
);
        animationset.startNow();
        view1.setVisibility(4);
        if(DisplayController.getScreenType() == 0 && isLight)
            darkHeaderBarLayout.setVisibility(4);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        Household household = ((SonosActivity)getContext()).getHousehold();
        if(household != null && household.getCurrentZoneGroup() != null)
            setCurrentZoneGroup(household.getCurrentZoneGroup());
    }

    public void setActionListener(HeaderBarActionListener headerbaractionlistener)
    {
        actionListener = headerbaractionlistener;
    }

    public void setCurrentZoneGroup(ZoneGroup zonegroup)
    {
        String s = zonegroup.createZoneGroupTitle(1, true);
        String s1 = zonegroup.createZoneGroupTitle(2, true);
        ((SonosAlternateTextView)findViewById(0x7f0a0085)).setTextStrings(s1, s);
        ((SonosAlternateTextView)findViewById(0x7f0a0091)).setTextStrings(s1, s);
    }

    public void setDrawerButtonVisibility(int i)
    {
        drawerButton.clearAnimation();
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            View view = drawerButton;
            float f;
            if(i == 0)
                f = 1.0F;
            else
                f = 0.0F;
            view.setAlpha(f);
        }
        drawerButton.setVisibility(i);
        drawerButtonVisibility = i;
    }

    public void setLightSearchButtonVisible(boolean flag)
    {
        int i = 0;
        if(lightSearchButton != null)
        {
            View view = lightSearchButton;
            int j;
            if(flag)
                j = 0;
            else
                j = 4;
            view.setVisibility(j);
            if(!flag)
                i = 4;
            lightSearchButtonVisibility = i;
        }
    }

    public void setTopPadding(int i)
    {
        ViewUtils.setPaddingTop(darkHeaderBarLayout, i + darkHeaderBarLayout.getPaddingTop());
        ViewUtils.setPaddingTop(lightHeaderBarLayout, i + lightHeaderBarLayout.getPaddingTop());
    }

    public void showRoomGrouping()
    {
        if(DisplayController.getScreenType() != 0)
        {
            AnimationSet animationset = new AnimationSet(true);
            Animation animation = AnimationUtils.loadAnimation(getContext(), 0x7f04000a);
            Animation animation1;
            if(isLight)
                lightRoomsTitleContainer.setAnimation(animation);
            else
                darkRoomsTitleContainer.setAnimation(animation);
            animationset.addAnimation(animation);
            animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f040007);
            if(isLight)
            {
                lightRoomGroupingTitleContainer.setVisibility(0);
                lightRoomGroupingTitleContainer.setAnimation(animation1);
            } else
            {
                darkRoomGroupingTitleContainer.setVisibility(0);
                darkRoomGroupingTitleContainer.setAnimation(animation1);
            }
            animationset.addAnimation(animation1);
            animationset.startNow();
            lightRoomsTitleContainer.setVisibility(4);
            darkRoomsTitleContainer.setVisibility(4);
        }
    }

    public void showRooms()
    {
        clearAllAnimations();
        View view;
        View view1;
        if(DisplayController.getScreenType() == 0)
        {
            AnimationSet animationset;
            Animation animation;
            Animation animation1;
            Animation animation2;
            if(isLight)
                view = lightHeaderBarContents;
            else
                view = darkHeaderBarContents;
            if(isLight)
                view1 = darkRoomFrame;
            else
                view1 = darkRoomFrame;
            if(isLight)
            {
                darkRoomsTitleContainer.setVisibility(0);
                darkHeaderBarLayout.setVisibility(0);
                ViewUtils.setAlpha(darkHeaderBarLayout, 1.0F);
                darkHeaderBarContents.setVisibility(4);
            } else
            {
                darkRoomsTitleContainer.setVisibility(0);
            }
        } else
        {
            if(isLight)
                view = lightRoomsNameContainer;
            else
                view = darkRoomsNameContainer;
            if(isLight)
                view1 = lightRoomFrame;
            else
                view1 = darkRoomFrame;
        }
        animationset = new AnimationSet(true);
        animation = AnimationUtils.loadAnimation(getContext(), 0x7f04000a);
        view.setVisibility(0);
        view.setAnimation(animation);
        animationset.addAnimation(animation);
        animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f040007);
        animation1.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation3)
            {
                if(DisplayController.getScreenType() == 0)
                {
                    drawerButton.setVisibility(4);
                    collapseButton.setVisibility(4);
                    darkSearchButton.setVisibility(4);
                    lightSearchButton.setVisibility(4);
                    darkRoomsNameContainer.setVisibility(4);
                    lightRoomsNameContainer.setVisibility(4);
                }
            }

            public void onAnimationRepeat(Animation animation3)
            {
            }

            public void onAnimationStart(Animation animation3)
            {
            }

            final MainHeaderBar this$0;

            
            {
                this$0 = MainHeaderBar.this;
                super();
            }
        }
);
        view1.setVisibility(0);
        view1.setAnimation(animation1);
        animationset.addAnimation(animation1);
        if(DisplayController.getScreenType() == 0 && isLight)
        {
            animation2 = AnimationUtils.loadAnimation(getContext(), 0x7f040006);
            lightBackground.setAnimation(animation2);
            animationset.addAnimation(animation2);
        }
        animationset.startNow();
        view.setVisibility(4);
        if(DisplayController.getScreenType() == 0 && isLight)
            lightBackground.setVisibility(4);
    }

    public void updateSlideOffset(float f)
    {
        ViewUtils.setAlpha(lightHeaderBarLayout, f);
        ViewUtils.setAlpha(darkHeaderBarLayout, 1.0F - f);
        ViewUtils.setAlpha(darkSearchButton, 1.0F - f);
        if((double)f > 0.5D)
        {
            ViewUtils.setAlpha(drawerButton, -1F + f * 2.0F);
            ViewUtils.setAlpha(collapseButton, 0.0F);
        } else
        {
            ViewUtils.setAlpha(drawerButton, 0.0F);
            ViewUtils.setAlpha(collapseButton, 1.0F - f * 2.0F);
        }
        lightHeaderBarLayout.setVisibility(0);
        darkHeaderBarLayout.setVisibility(0);
        drawerButton.setVisibility(drawerButtonVisibility);
        collapseButton.setVisibility(0);
        darkSearchButton.setVisibility(0);
        lightSearchButton.setVisibility(lightSearchButtonVisibility);
        darkRoomsNameContainer.setVisibility(0);
        lightRoomsNameContainer.setVisibility(0);
        if(f != 0.0F) goto _L2; else goto _L1
_L1:
        lightHeaderBarLayout.setVisibility(8);
        darkHeaderBarLayout.setVisibility(0);
        drawerButton.setVisibility(4);
        collapseButton.setVisibility(0);
        darkRoomsNameContainer.setVisibility(0);
        lightRoomsNameContainer.setVisibility(4);
        drawerButton.clearAnimation();
        collapseButton.clearAnimation();
        isLight = false;
_L4:
        return;
_L2:
        if(f == 1.0F)
        {
            lightHeaderBarLayout.setVisibility(0);
            darkHeaderBarLayout.setVisibility(4);
            drawerButton.setVisibility(drawerButtonVisibility);
            darkSearchButton.setVisibility(4);
            collapseButton.setVisibility(4);
            darkRoomsNameContainer.setVisibility(4);
            lightRoomsNameContainer.setVisibility(0);
            isLight = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final String LOG_TAG = "MainHeaderBar";
    private HeaderBarActionListener actionListener;
    private View collapseButton;
    View darkHeaderBarContents;
    private ViewGroup darkHeaderBarLayout;
    View darkRoomFrame;
    View darkRoomGroupingTitleContainer;
    private View darkRoomsNameContainer;
    View darkRoomsTitleContainer;
    private View darkSearchButton;
    private View drawerButton;
    private int drawerButtonVisibility;
    boolean isLight;
    View lightBackground;
    View lightHeaderBarContents;
    private ViewGroup lightHeaderBarLayout;
    View lightRoomFrame;
    View lightRoomGroupingTitleContainer;
    private View lightRoomsNameContainer;
    View lightRoomsTitleContainer;
    private View lightSearchButton;
    private int lightSearchButtonVisibility;








}
