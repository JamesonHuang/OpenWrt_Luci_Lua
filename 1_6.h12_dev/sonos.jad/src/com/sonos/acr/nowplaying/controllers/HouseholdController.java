// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import android.content.Context;
import com.sonos.acr.sclib.sinks.*;
import com.sonos.acr.sclib.wrappers.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.nowplaying.controllers:
//            CurrentPlayIndicatorController, ZoneGroupController, PlayIndicatorController, TransportViewController

public class HouseholdController
    implements com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener
{

    public HouseholdController(Context context1)
    {
        playIndicatorControllers = new HashMap();
        currentPlayIndicatorController = new CurrentPlayIndicatorController();
        context = context1;
        currentZoneGroupController = new ZoneGroupController(context1);
    }

    public CurrentPlayIndicatorController getCurrentPlayIndicatorController()
    {
        return currentPlayIndicatorController;
    }

    public ZoneGroupController getCurrentZoneGroupController()
    {
        return currentZoneGroupController;
    }

    public PlayIndicatorController getPlayIndicatorController(String s)
    {
        return (PlayIndicatorController)playIndicatorControllers.get(s);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent != com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged) goto _L2; else goto _L1
_L1:
        HashMap hashmap = new HashMap();
        ZoneGroup zonegroup;
        PlayIndicatorController playindicatorcontroller;
        for(Iterator iterator = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY).iterator(); iterator.hasNext(); hashmap.put(zonegroup.getID(), playindicatorcontroller))
        {
            zonegroup = (ZoneGroup)iterator.next();
            playindicatorcontroller = (PlayIndicatorController)playIndicatorControllers.get(zonegroup.getID());
            if(playindicatorcontroller == null)
                playindicatorcontroller = new PlayIndicatorController();
            playindicatorcontroller.onNowPlayingEvent(zonegroup.nowPlaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged);
        }

        playIndicatorControllers = hashmap;
_L4:
        return;
_L2:
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
        {
            ZoneGroup zonegroup1 = household.getCurrentZoneGroup();
            if(zonegroup1 != null)
                currentPlayIndicatorController.setRealPlayIndicatorController((PlayIndicatorController)playIndicatorControllers.get(zonegroup1.getID()));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        PlayIndicatorController playindicatorcontroller = (PlayIndicatorController)playIndicatorControllers.get(nowplaying.getZoneGroup().getID());
        if(playindicatorcontroller != null)
            playindicatorcontroller.onNowPlayingEvent(nowplaying, nowplayevent);
    }

    public void subscribe()
    {
        AllNowPlayingEventSink.getInstance().addListener(this);
        CurrentNowPlayingEventSink.getInstance().addListener(currentZoneGroupController);
        CurrentGroupVolumeEventSink.getInstance().addListener(currentZoneGroupController);
        currentZoneGroupController.getTransportViewController().subscribe();
    }

    public void unsubscribe()
    {
        AllNowPlayingEventSink.getInstance().removeListener(this);
        CurrentNowPlayingEventSink.getInstance().removeListener(currentZoneGroupController);
        CurrentGroupVolumeEventSink.getInstance().removeListener(currentZoneGroupController);
        currentZoneGroupController.getTransportViewController().unsubscribe();
    }

    Context context;
    CurrentPlayIndicatorController currentPlayIndicatorController;
    ZoneGroupController currentZoneGroupController;
    HashMap playIndicatorControllers;
}
