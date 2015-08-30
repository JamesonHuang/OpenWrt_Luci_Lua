// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.ScrollView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.nowplaying.controllers.VolumeViewController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.sclib.sinks.*;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.volume.views.RoomsVolumeView;
import com.sonos.acr.volume.views.VolumeSliderView;
import com.sonos.sclib.SCIAppReporting;
import com.sonos.sclib.sclib;
import java.util.ArrayList;

public class GroupVolumeDialogActivity extends SonosActivity
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public GroupVolumeDialogActivity()
    {
    }

    private void updateDialogHeight()
    {
        int i = roomsVolumeView.getHeight() + masterVolume.getHeight();
        int j = (int)(0.75F * (float)getResources().getDisplayMetrics().heightPixels);
        if(i > j)
        {
            int k = roomsVolumeView.getChildAt(0).getHeight();
            int l = (j - masterVolume.getHeight()) / k;
            int i1 = (int)((float)k * (0.5F + (float)l));
            groupScrollView.getLayoutParams().height = i1;
        } else
        {
            groupScrollView.getLayoutParams().height = -2;
        }
        groupScrollView.requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0)
        {
            Rect rect = new Rect();
            Window window = getWindow();
            if(window != null && window.getDecorView() != null)
            {
                window.getDecorView().getHitRect(rect);
                if(!rect.contains((int)motionevent.getX(), (int)motionevent.getY()))
                    finish();
            }
        }
        return super.dispatchTouchEvent(motionevent);
    }

    protected void onCreate(Bundle bundle)
    {
        requestWindowFeature(1);
        super.onCreate(bundle);
        zoneGroupId = getIntent().getStringExtra("com.sonos.intent.extra.ZGID");
        deviceGroupVolumeEventSink = DeviceGroupVolumeEventSink.getInstance(zoneGroupId);
        setContentView(0x7f03001d);
        groupScrollView = (ScrollView)findViewById(0x7f0a007a);
        roomsVolumeView = (RoomsVolumeView)findViewById(0x7f0a007b);
        masterVolume = (VolumeSliderView)findViewById(0x7f0a007c);
        masterVolume.setTag("");
        zoneGroupController = new ZoneGroupController(this);
        zoneGroupController.observeViewHierarchy((ViewGroup)findViewById(0x7f0a0079));
        volumeViewListener = new VolumeViewListener() {

            public void onUserVolumeEvent(GroupVolume groupvolume, String s, com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType usereventtype)
            {
                com.sonos.sclib.SCIAppReporting.SCViewID scviewid = (com.sonos.sclib.SCIAppReporting.SCViewID)getIntent().getSerializableExtra("com.sonos.intent.extra.VIEW_ID");
                if(scviewid == null) goto _L2; else goto _L1
_L1:
                if(!com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.EndSeek.equals(usereventtype)) goto _L4; else goto _L3
_L3:
                sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_VOLUME);
_L2:
                return;
_L4:
                if(com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.MuteChange.equals(usereventtype))
                    sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_MUTE);
                if(true) goto _L2; else goto _L5
_L5:
            }

            final GroupVolumeDialogActivity this$0;

            
            {
                this$0 = GroupVolumeDialogActivity.this;
                super();
            }
        }
;
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
        {
            ZoneGroup zonegroup = household.lookupZoneGroup(zoneGroupId);
            if(zonegroup != null)
            {
                updateDialogHeight();
                RoomsVolumeView roomsvolumeview = roomsVolumeView;
                int i;
                if(zonegroup.getDevices().size() > 1)
                    i = 0;
                else
                    i = 8;
                roomsvolumeview.setVisibility(i);
            } else
            {
                finish();
            }
        }
    }

    protected void onPause()
    {
        super.onPause();
        zoneGroupController.getVolumeViewController().removeVolumeViewListener(volumeViewListener);
        deviceGroupVolumeEventSink.removeListener(zoneGroupController);
        HouseholdEventSink.getInstance().removeListener(this);
    }

    protected void onResume()
    {
        super.onResume();
        zoneGroupController.getVolumeViewController().addVolumeViewListener(volumeViewListener);
        HouseholdEventSink.getInstance().addListener(this);
        deviceGroupVolumeEventSink.addListener(zoneGroupController);
        masterVolume.requestFocus();
    }

    private static final float DIALOG_MAX_HEIGHT_PCT = 0.75F;
    private DeviceGroupVolumeEventSink deviceGroupVolumeEventSink;
    protected ScrollView groupScrollView;
    protected VolumeSliderView masterVolume;
    protected RoomsVolumeView roomsVolumeView;
    private VolumeViewListener volumeViewListener;
    private ZoneGroupController zoneGroupController;
    private String zoneGroupId;
}
