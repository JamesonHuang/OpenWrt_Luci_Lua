// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.app.Activity;
import android.content.Context;
import android.view.*;
import android.widget.FrameLayout;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.*;
import com.sonos.acr.volume.views.RoomsVolumeView;
import com.sonos.acr.volume.views.VolumeSliderView;

public class GroupVolumePopupWindow extends SonosPopupWindow
{
    private class SonosPopupFrame extends FrameLayout
    {

        public boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            boolean flag = true;
            if(keyevent.getKeyCode() != 4) goto _L2; else goto _L1
_L1:
            if(getKeyDispatcherState() != null) goto _L4; else goto _L3
_L3:
            flag = super.dispatchKeyEvent(keyevent);
_L6:
            return flag;
_L4:
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
                if(dispatcherstate1 != null)
                    dispatcherstate1.startTracking(keyevent, this);
                continue; /* Loop/switch isn't completed */
            }
            if(keyevent.getAction() == flag)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
                if(dispatcherstate != null && dispatcherstate.isTracking(keyevent) && !keyevent.isCanceled())
                {
                    if(!onBackPressed())
                        dismiss();
                    continue; /* Loop/switch isn't completed */
                }
            }
_L2:
            flag = super.dispatchKeyEvent(keyevent);
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected void onAttachedToWindow()
        {
            super.onAttachedToWindow();
            masterVolume.requestFocus();
        }

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            boolean flag;
            if((context instanceof SonosActivity) && ((SonosActivity)context).handleKeyDown(i, keyevent) || super.onKeyDown(i, keyevent))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean onKeyUp(int i, KeyEvent keyevent)
        {
            boolean flag;
            if((context instanceof SonosActivity) && ((SonosActivity)context).handleKeyUp(i, keyevent) || super.onKeyUp(i, keyevent))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = true;
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            if(motionevent.getAction() == 0 && (i < 0 || i >= getWidth() || j < 0 || j >= getHeight()))
                dismiss();
            else
            if(motionevent.getAction() == 0 && !ViewUtils.isMotionEventInView(motionevent, masterVolume) && !ViewUtils.isMotionEventInView(motionevent, volumeScrollView))
                dismiss();
            else
            if(motionevent.getAction() == 4)
                dismiss();
            else
                flag = super.onTouchEvent(motionevent);
            return flag;
        }

        final GroupVolumePopupWindow this$0;

        public SonosPopupFrame(Context context1)
        {
            this$0 = GroupVolumePopupWindow.this;
            super(context1);
            if(android.os.Build.VERSION.SDK_INT >= 14)
                setFitsSystemWindows(true);
        }
    }


    public GroupVolumePopupWindow(Activity activity, ZoneGroupController zonegroupcontroller)
    {
        super(activity);
        context = activity;
        popupView = LayoutInflater.from(activity).inflate(0x7f03001f, null);
        masterVolume = (VolumeSliderView)popupView.findViewById(0x7f0a007c);
        volumeScrollView = (VolumeScrollView)popupView.findViewById(0x7f0a007a);
        roomsVolumeView = (RoomsVolumeView)popupView.findViewById(0x7f0a007b);
        BlurFrame blurframe = (BlurFrame)popupView.findViewById(0x7f0a0080);
        if(blurframe != null)
            blurframe.setRootView(activity.findViewById(0x7f0a01b4));
        zonegroupcontroller.observeViewHierarchy((ViewGroup)popupView);
        SonosPopupFrame sonospopupframe = new SonosPopupFrame(activity);
        sonospopupframe.addView(popupView);
        setContentView(sonospopupframe);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(null);
        setClippingEnabled(false);
        setAnimationStyle(0x7f0d00a7);
    }

    public void matchLayoutSettingsToAnchorVolume(View view)
    {
        ViewUtils.copyLayoutSettings(view, masterVolume);
        int ai[] = volumeBarContentIds;
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            ViewUtils.copyLayoutSettings(view.findViewById(k), masterVolume.findViewById(k));
        }

        roomsVolumeView.sizeToAnchor(view);
    }

    public boolean onBackPressed()
    {
        return false;
    }

    public void showAsDropDown(View view)
    {
        matchLayoutSettingsToAnchorVolume(view);
        int ai[] = new int[2];
        view.getLocationInWindow(ai);
        setHeight(ai[1] + view.getHeight());
        setWidth(view.getWidth());
        if(roomsVolumeView.getChildCount() > 0)
        {
            View view1 = roomsVolumeView.getChildAt(0);
            view1.measure(0, 0);
            if(view1.getMeasuredHeight() > 0)
                volumeScrollView.setCellHeight(view1.getMeasuredHeight());
        }
        super.showAsDropDown(view, 0, -view.getHeight());
    }

    public static int volumeBarContentIds[];
    protected Context context;
    protected VolumeSliderView masterVolume;
    protected View popupView;
    protected RoomsVolumeView roomsVolumeView;
    protected VolumeScrollView volumeScrollView;

    static 
    {
        int ai[] = new int[4];
        ai[0] = 0x7f0a01da;
        ai[1] = 0x7f0a01db;
        ai[2] = 0x7f0a01dc;
        ai[3] = 0x7f0a01dd;
        volumeBarContentIds = ai;
    }
}
