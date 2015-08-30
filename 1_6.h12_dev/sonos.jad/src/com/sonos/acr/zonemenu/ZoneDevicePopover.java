// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.zonemenu;

import android.content.Context;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.view.MusicEqDialog;
import com.sonos.acr.view.PopOverWindow;
import java.util.ArrayList;
import java.util.Iterator;

public class ZoneDevicePopover extends PopOverWindow
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, android.view.View.OnClickListener
{

    public ZoneDevicePopover(Context context)
    {
        super(context);
    }

    public ZoneDevicePopover(Context context, ZoneGroup zonegroup, long l)
    {
        super(context);
        rootView = LayoutInflater.from(context).inflate(0x7f0300c1, null);
        zoneGroupId = zonegroup.getID();
        zoneDeviceList = (LinearLayout)rootView.findViewById(0x7f0a01f6);
        setContentView(rootView);
        setTimeout(l);
    }

    public void addDevices(ArrayList arraylist)
    {
        zoneDeviceList.removeAllViews();
        TextView textview;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); zoneDeviceList.addView(textview))
        {
            ZoneDevice zonedevice = (ZoneDevice)iterator.next();
            textview = (TextView)LayoutInflater.from(getContext()).inflate(0x7f030017, zoneDeviceList, false);
            textview.setText(zonedevice.getTitle());
            textview.setTag(zonedevice.getId());
            textview.setOnClickListener(this);
        }

    }

    protected android.view.WindowManager.LayoutParams generateLayoutParams()
    {
        android.view.WindowManager.LayoutParams layoutparams = super.generateLayoutParams();
        layoutparams.x = 24 + layoutparams.x;
        return layoutparams;
    }

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        ZoneDevice zonedevice = LibraryUtils.getHousehold().lookupDevice(s);
        if(zonedevice != null)
        {
            (new MusicEqDialog(getContext())).showEqView(zonedevice);
            dismiss();
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        HouseholdEventSink.getInstance().removeListener(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged || householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
        {
            ZoneGroup zonegroup = household.lookupZoneGroup(zoneGroupId);
            if(zonegroup != null)
                addDevices(zonegroup.getDevices());
            else
                dismiss();
        }
    }

    public boolean onTouchOutside(MotionEvent motionevent)
    {
        boolean flag;
        if(motionevent.getAction() == 0)
        {
            dismiss();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void show(View view)
    {
        HouseholdEventSink.getInstance().addListener(this);
        super.show(view, com.sonos.acr.view.PopOverWindow.Alignment.BELOW_RIGHT);
    }

    View rootView;
    LinearLayout zoneDeviceList;
    String zoneGroupId;
}
