// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.FrameLayout;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.volume.views.RoomsVolumeView;
import com.sonos.acr.volume.views.VolumeSliderView;
import java.util.ArrayList;

public class GroupVolumeFragment extends SonosFragment
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public GroupVolumeFragment()
    {
        super(0x7f010054);
    }

    protected void loadContents()
    {
        groupVolumeRoot.removeView(groupVolumeLayout);
        groupVolumeLayout = LayoutInflater.from(themedContext).inflate(0x7f03001e, groupVolumeRoot, false);
        roomsVolumeView = (RoomsVolumeView)groupVolumeLayout.findViewById(0x7f0a007b);
        masterVolume = (VolumeSliderView)groupVolumeLayout.findViewById(0x7f0a007c);
        masterVolume.setDeviceId("");
        scrollView = groupVolumeLayout.findViewById(0x7f0a007a);
        groupVolumeRoot.addView(groupVolumeLayout);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        String s = "";
        View view = getActivity().getCurrentFocus();
        if(view instanceof VolumeSliderView)
            s = ((VolumeSliderView)view).getDeviceId();
        loadContents();
        if(s.equals(""))
            masterVolume.requestFocus();
        else
            roomsVolumeView.setFocusedRoom(s);
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        groupVolumeRoot = new FrameLayout(themedContext);
        loadContents();
        gvBackground = groupVolumeRoot.findViewById(0x7f0a007e);
        return groupVolumeRoot;
    }

    public void onHiddenChanged(boolean flag)
    {
        if(!flag)
        {
            ZoneGroup zonegroup = getHousehold().getCurrentZoneGroup();
            if(zonegroup == null || zonegroup.getDevices().size() <= 1)
                roomsVolumeView.setVisibility(8);
            else
                roomsVolumeView.setVisibility(0);
            SonosApplication.getInstance().getHandler().post(new Runnable() {

                public void run()
                {
                    masterVolume.requestFocus();
                }

                final GroupVolumeFragment this$0;

            
            {
                this$0 = GroupVolumeFragment.this;
                super();
            }
            }
);
        }
        super.onHiddenChanged(flag);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
        {
            ZoneGroup zonegroup = household.getCurrentZoneGroup();
            String s;
            StringBuilder stringbuilder;
            Object obj;
            if(zonegroup == null || zonegroup.getDevices().size() <= 1)
                roomsVolumeView.setVisibility(8);
            else
                roomsVolumeView.setVisibility(0);
            s = LOG_TAG;
            stringbuilder = (new StringBuilder()).append("ZoneGroupChanged: ");
            if(zonegroup != null)
                obj = Integer.valueOf(zonegroup.getDevices().size());
            else
                obj = "null zone group";
            SLog.e(s, stringbuilder.append(obj).toString());
        }
    }

    public void onSubscribeEventSinks()
    {
        super.onSubscribeEventSinks();
        getSonosActivity().getHouseholdController().getCurrentZoneGroupController().observeViewHierarchy((ViewGroup)getView());
    }

    public void onUnsubscribeEventSinks()
    {
        super.onUnsubscribeEventSinks();
        getSonosActivity().getHouseholdController().getCurrentZoneGroupController().ignoreViewHierarchy((ViewGroup)getView());
    }

    public boolean willHandleTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(ViewUtils.isMotionEventInView(motionevent, scrollView) || ViewUtils.isMotionEventInView(motionevent, masterVolume))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String LOG_TAG = com/sonos/acr/volume/fragments/GroupVolumeFragment.getSimpleName();
    private View groupVolumeLayout;
    private ViewGroup groupVolumeRoot;
    private View gvBackground;
    protected VolumeSliderView masterVolume;
    protected RoomsVolumeView roomsVolumeView;
    private View scrollView;

}
