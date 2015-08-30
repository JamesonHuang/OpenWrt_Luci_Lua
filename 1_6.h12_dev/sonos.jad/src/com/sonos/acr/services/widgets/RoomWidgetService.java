// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.*;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.services.SonosService;
import com.sonos.acr.util.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.services.widgets:
//            RoomWidgetProvider, RoomWidget

public class RoomWidgetService extends SonosService
    implements com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener, com.sonos.acr.sclib.SonosListener.StateListener, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener, com.sonos.acr.network.SonosNetworkManager.ConnectionListener
{

    public RoomWidgetService()
    {
        roomWidgets = new ArrayList();
    }

    public static void init(Context context)
    {
        context.startService((new Intent(context, com/sonos/acr/services/widgets/RoomWidgetService)).setAction("UPDATE_WIDGETS"));
    }

    private boolean isConnected()
    {
        boolean flag;
        if(sonosNetworkManager.isWifiConnected() && !"".equals(LibraryUtils.getHousehold().getID()) && LibraryUtils.getHousehold().getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_ANY).size() > 0 && SonosApplication.getInstance().getListener().isConnectedAndPlayable())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void clearUnusedPreferences()
    {
        SharedPreferences sharedpreferences = LibraryUtils.getHouseholdPreferences();
        Map map = sharedpreferences.getAll();
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        ArrayList arraylist = new ArrayList();
        int ai[] = appWidgetManager.getAppWidgetIds(new ComponentName(this, com/sonos/acr/services/widgets/RoomWidgetProvider));
        int i = ai.length;
        for(int j = 0; j < i; j++)
            arraylist.add(Integer.valueOf(ai[j]));

        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            if(s.startsWith("NOWPLAYING_WIDGET_DEVICE_ID") && !arraylist.contains(Integer.valueOf(s.substring("NOWPLAYING_WIDGET_DEVICE_ID".length()))))
                editor.remove(s);
        } while(true);
        editor.commit();
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        Household household = LibraryUtils.getHousehold();
        if(household != null)
            updateWidgets(household);
    }

    public void onCreate()
    {
        super.onCreate();
        appWidgetManager = AppWidgetManager.getInstance(this);
        sonosNetworkManager = SonosApplication.getInstance().getNetworkStatusMonitor();
        HouseholdEventSink.getInstance().addListener(this);
        SonosApplication.getInstance().getListener().subscribe(this);
        sonosNetworkManager.subscribe(this);
        AllNowPlayingEventSink.getInstance().addListener(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        updateWidgets(household);
        resubscribeVolume();
    }

    public void onHouseholdStateChange(com.sonos.acr.sclib.SonosListener.HouseholdState householdstate, com.sonos.acr.sclib.SonosListener.HouseholdSubState householdsubstate)
    {
        rebuildWidgets(this);
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent != com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged) goto _L2; else goto _L1
_L1:
        String s = nowplaying.getAsynchronousErrorString();
        if(StringUtils.isNotEmptyOrNull(s))
        {
            onZoneGroupMessage(nowplaying.getZoneGroup(), s, s, 5000L);
        } else
        {
            handler.removeCallbacks(postUpdates);
            handler.post(postUpdates);
        }
_L4:
        return;
_L2:
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnTVEqualizationChanged)
        {
            handler.removeCallbacks(postUpdates);
            handler.post(postUpdates);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        super.onStartCommand(intent, i, j);
        if(intent == null) goto _L2; else goto _L1
_L1:
        if(!"UPDATE_WIDGETS".equals(intent.getAction())) goto _L4; else goto _L3
_L3:
        clearUnusedPreferences();
_L2:
        int k;
        SLog.w(LOG_TAG, "Starting Widget Service...");
        rebuildWidgets(this);
        k = 1;
_L6:
        return k;
_L4:
        if(!"STOP_SERVICE".equals(intent.getAction()))
            continue; /* Loop/switch isn't completed */
        clearUnusedPreferences();
        RoomWidget roomwidget;
        for(Iterator iterator = roomWidgets.iterator(); iterator.hasNext(); roomwidget.updateWidget(null))
        {
            roomwidget = (RoomWidget)iterator.next();
            roomwidget.setConnected(false);
        }

        HouseholdEventSink.getInstance().removeListener(this);
        AllNowPlayingEventSink.getInstance().removeListener(this);
        SonosApplication.getInstance().getListener().unsubscribe(this);
        sonosNetworkManager.unsubscribe(this);
        SLog.w(LOG_TAG, "Stopping Widget Service...");
        stopSelf();
        k = 2;
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        if(volumeevent == com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent.OnMuteChanged)
        {
            Household household = LibraryUtils.getHousehold();
            if(household != null)
                updateWidgets(household);
        }
    }

    public void onZoneGroupMessage(ZoneGroup zonegroup, String s, String s1, long l)
    {
        if(zonegroup != null)
        {
            if(!StringUtils.isNotEmptyOrNull(s1))
                s1 = s;
            updateWidgets(zonegroup, s1, l);
        }
    }

    public void rebuildWidgets(Context context)
    {
        for(Iterator iterator = roomWidgets.iterator(); iterator.hasNext(); ((RoomWidget)iterator.next()).reset());
        roomWidgets.clear();
        int ai[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, com/sonos/acr/services/widgets/RoomWidgetProvider));
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            String s = LibraryUtils.getHouseholdPreferences().getString((new StringBuilder()).append("NOWPLAYING_WIDGET_DEVICE_ID").append(k).toString(), null);
            SLog.e(LOG_TAG, (new StringBuilder()).append("Building Widget: ").append(k).toString());
            roomWidgets.add(new RoomWidget(context, k, s));
        }

        Household household = LibraryUtils.getHousehold();
        if(household != null)
            updateWidgets(household);
        resubscribeVolume();
    }

    public void resubscribeVolume()
    {
        DeviceGroupVolumeEventSink.unsubscribeAll(this);
        Iterator iterator = roomWidgets.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            RoomWidget roomwidget = (RoomWidget)iterator.next();
            ZoneGroup zonegroup = LibraryUtils.getHousehold().lookupZoneGroupByDevice(roomwidget.getDeviceId());
            if(zonegroup != null)
                DeviceGroupVolumeEventSink.getInstance(zonegroup.getID()).addListener(this);
        } while(true);
    }

    public void updateWidgets(Bundle bundle)
    {
        for(Iterator iterator = roomWidgets.iterator(); iterator.hasNext(); ((RoomWidget)iterator.next()).updateWidget());
    }

    public void updateWidgets(Household household)
    {
        boolean flag = isConnected();
        RoomWidget roomwidget;
        ZoneGroup zonegroup;
        for(Iterator iterator = roomWidgets.iterator(); iterator.hasNext(); roomwidget.updateWidget(zonegroup))
        {
            roomwidget = (RoomWidget)iterator.next();
            zonegroup = household.lookupZoneGroupByDevice(roomwidget.getDeviceId());
            roomwidget.setConnected(flag);
        }

    }

    public void updateWidgets(ZoneGroup zonegroup, String s, long l)
    {
        Iterator iterator = roomWidgets.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            RoomWidget roomwidget = (RoomWidget)iterator.next();
            if(zonegroup.getDevice(roomwidget.getDeviceId()) != null)
                roomwidget.showMessage(zonegroup, s, l);
        } while(true);
    }

    public static final String STOP_SERVICE = "STOP_SERVICE";
    public static final String UPDATE_WIDGETS = "UPDATE_WIDGETS";
    AppWidgetManager appWidgetManager;
    private final Runnable postUpdates = new Runnable() {

        public void run()
        {
            updateWidgets(LibraryUtils.getHousehold());
        }

        final RoomWidgetService this$0;

            
            {
                this$0 = RoomWidgetService.this;
                super();
            }
    }
;
    private ArrayList roomWidgets;
    SonosNetworkManager sonosNetworkManager;
}
