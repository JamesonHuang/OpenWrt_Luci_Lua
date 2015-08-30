// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.sonos.acr.nowplaying.GroupVolumePopupWindow;
import com.sonos.acr.nowplaying.controllers.VolumeView;
import com.sonos.acr.nowplaying.controllers.VolumeViewController;
import com.sonos.acr.sclib.wrappers.DeviceVolume;
import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.DividerLinearLayout;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.volume.views:
//            VolumeSliderView

public class RoomsVolumeView extends DividerLinearLayout
    implements VolumeView
{

    public RoomsVolumeView(Context context)
    {
        this(context, null);
    }

    public RoomsVolumeView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.RoomsVolumeView);
        setCellLayoutId(typedarray.getResourceId(0, 0x7f0300a9));
        typedarray.recycle();
        setOrientation(1);
    }

    private void setCellLayoutId(int i)
    {
        cellLayoutId = i;
    }

    public void onUserVolumeEvent(GroupVolume groupvolume, String s, com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType usereventtype)
    {
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        updateZoneVolumeList(groupvolume);
    }

    public void setFocusedRoom(String s)
    {
        int i = 0;
        do
        {
label0:
            {
                if(i < getChildCount())
                {
                    VolumeSliderView volumesliderview = (VolumeSliderView)getChildAt(i);
                    if(!volumesliderview.getDeviceId().equals(s))
                        break label0;
                    volumesliderview.requestFocus();
                }
                return;
            }
            i++;
        } while(true);
    }

    public void setVolumeViewController(VolumeViewController volumeviewcontroller)
    {
        volumeViewController = volumeviewcontroller;
    }

    public void sizeToAnchor()
    {
        if(savedAnchor != null)
            sizeToAnchor(savedAnchor);
    }

    public void sizeToAnchor(View view)
    {
        if(savedAnchor != view)
            savedAnchor = view;
        for(int i = 0; i < getChildCount(); i++)
        {
            View view1 = getChildAt(i);
            ViewUtils.copyLayoutSettings(view, view1);
            int ai[] = GroupVolumePopupWindow.volumeBarContentIds;
            int j = ai.length;
            for(int k = 0; k < j; k++)
            {
                int l = ai[k];
                ViewUtils.copyLayoutSettings(view.findViewById(l), view1.findViewById(l));
            }

        }

    }

    public void updateZoneVolumeList(GroupVolume groupvolume)
    {
        if(groupvolume != null) goto _L2; else goto _L1
_L1:
        removeAllViews();
_L4:
        return;
_L2:
        ArrayList arraylist;
        arraylist = groupvolume.getDeviceVolumes();
        if(getChildCount() == arraylist.size())
            break; /* Loop/switch isn't completed */
        removeAllViews();
        LayoutInflater layoutinflater = LayoutInflater.from(getContext());
        Iterator iterator = arraylist.iterator();
        while(iterator.hasNext()) 
        {
            DeviceVolume devicevolume1 = (DeviceVolume)iterator.next();
            VolumeSliderView volumesliderview = (VolumeSliderView)layoutinflater.inflate(cellLayoutId, this, false);
            volumesliderview.setDeviceId(devicevolume1.getDeviceID());
            volumesliderview.setContentDescription(devicevolume1.getDeviceName());
            addView(volumesliderview, -1, -2);
            sizeToAnchor();
        }
        if(true) goto _L4; else goto _L3
_L3:
        int i = 0;
        while(i < arraylist.size()) 
        {
            DeviceVolume devicevolume = (DeviceVolume)arraylist.get(i);
            ((VolumeSliderView)getChildAt(i)).setDeviceId(devicevolume.getDeviceID());
            i++;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    private int cellLayoutId;
    View savedAnchor;
    VolumeViewController volumeViewController;
}
