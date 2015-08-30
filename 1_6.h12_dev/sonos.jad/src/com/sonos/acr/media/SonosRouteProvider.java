// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.*;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.media.MediaRouteDescriptor;
import android.support.v7.media.MediaRouteProvider;
import android.util.Pair;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIVersion;
import java.util.*;

// Referenced classes of package com.sonos.acr.media:
//            SonosMRPCompatibilityManager, SonosRouteWifiLock, SonosRoutePlayerInfo, SonosRouteController

public final class SonosRouteProvider extends MediaRouteProvider
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.network.SonosNetworkManager.ConnectionListener, SonosRoutePlayerInfo.SonosRoutePlayerInfoCB
{
    private class MRPCompatibilityReceiver
        implements SonosMRPCompatibilityManager.MRPCompatibilityListener
    {

        public void onCompatibilityChanged()
        {
            SLog.d("SonosRouteProvider", "MRP Compatibility Changed, re-publishing routes.");
            publishSonosRoutes();
        }

        final SonosRouteProvider this$0;

        private MRPCompatibilityReceiver()
        {
            this$0 = SonosRouteProvider.this;
            super();
        }

    }

    private class ScreenReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context1, Intent intent)
        {
            if(!intent.getAction().equals("android.intent.action.SCREEN_OFF")) goto _L2; else goto _L1
_L1:
            suspendDeviceExpiration();
_L4:
            return;
_L2:
            if(intent.getAction().equals("android.intent.action.SCREEN_ON"))
                resumeDeviceExpiration();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public final IntentFilter filter = new IntentFilter();
        final SonosRouteProvider this$0;

        public ScreenReceiver()
        {
            this$0 = SonosRouteProvider.this;
            super();
            filter.addAction("android.intent.action.SCREEN_ON");
            filter.addAction("android.intent.action.SCREEN_OFF");
        }
    }

    public static interface SonosRouteProviderListener
    {

        public abstract void onSonosRouteProviderEvent(SonosRouteProvider sonosrouteprovider, RouteProviderEvent routeproviderevent);
    }

    public static final class RouteProviderEvent extends Enum
    {

        public static RouteProviderEvent valueOf(String s)
        {
            return (RouteProviderEvent)Enum.valueOf(com/sonos/acr/media/SonosRouteProvider$RouteProviderEvent, s);
        }

        public static RouteProviderEvent[] values()
        {
            return (RouteProviderEvent[])$VALUES.clone();
        }

        private static final RouteProviderEvent $VALUES[];
        public static final RouteProviderEvent OnActiveRoutesChanged;
        public static final RouteProviderEvent OnRouteProviderInitialized;

        static 
        {
            OnActiveRoutesChanged = new RouteProviderEvent("OnActiveRoutesChanged", 0);
            OnRouteProviderInitialized = new RouteProviderEvent("OnRouteProviderInitialized", 1);
            RouteProviderEvent arouteproviderevent[] = new RouteProviderEvent[2];
            arouteproviderevent[0] = OnActiveRoutesChanged;
            arouteproviderevent[1] = OnRouteProviderInitialized;
            $VALUES = arouteproviderevent;
        }

        private RouteProviderEvent(String s, int i)
        {
            super(s, i);
        }
    }


    private SonosRouteProvider(Context context1)
    {
        super(context1);
        sonosRouteVolumes = new HashMap();
        sonosRoutePlayerInfo = new HashMap();
        activeRouteControllers = new HashMap();
        haveSeenRoutes = false;
        lastGroupsString = "";
        context = context1;
        SonosApplication sonosapplication = SonosApplication.getInstance();
        SCLibManager sclibmanager = sonosapplication.getSCLibManager();
        if(!sclibmanager.isInitialized())
            sonosapplication.startServices();
        if(!$assertionsDisabled && !sclibmanager.isInitialized())
        {
            throw new AssertionError();
        } else
        {
            mrpCompatibilityManager = new SonosMRPCompatibilityManager();
            mrpCompatibilityManager.subscribe(new MRPCompatibilityReceiver());
            sonosNetworkManager = sonosapplication.getNetworkStatusMonitor();
            sonosNetworkManager.subscribe(this);
            HouseholdEventSink.getInstance().addListener(this);
            screenReceiver = new ScreenReceiver();
            sonosapplication.registerReceiver(screenReceiver, screenReceiver.filter);
            wifiLock = new SonosRouteWifiLock(SonosApplication.getInstance().getApplicationContext(), "SonosMRPLock");
            return;
        }
    }

    private static void addDataTypeUnchecked(IntentFilter intentfilter, String s)
    {
        try
        {
            intentfilter.addDataType(s);
            return;
        }
        catch(android.content.IntentFilter.MalformedMimeTypeException malformedmimetypeexception)
        {
            throw new RuntimeException(malformedmimetypeexception);
        }
    }

    public static void addListener(SonosRouteProviderListener sonosrouteproviderlistener)
    {
        routeProviderListeners.add(sonosrouteproviderlistener);
    }

    private MediaRouteDescriptor createMediaRouteDescriptorForGroup(Household household, ZoneGroup zonegroup)
    {
        MediaRouteDescriptor mediaroutedescriptor = null;
        ZoneDevice zonedevice = getMasterDeviceIfSonosGroupUsesMRP(zonegroup);
        if(zonedevice != null)
        {
            String s = getRouteID(household.getID(), zonegroup.getID());
            ArrayList arraylist = getControlFiltersForPlayer(zonedevice.getId());
            if(arraylist != null)
            {
                DeviceVolume devicevolume = zonegroup.getGroupVolume().getGroupVolume();
                int i;
                android.support.v7.media.MediaRouteDescriptor.Builder builder;
                String s1;
                Integer integer;
                int j;
                if(devicevolume.isVolumeAdjustable())
                    i = 1;
                else
                    i = 0;
                builder = new android.support.v7.media.MediaRouteDescriptor.Builder(s, zonegroup.createSimpleZoneGroupTitle());
                if(zonegroup.getDevices().size() > 1)
                    s1 = context.getResources().getString(0x7f0c0074);
                else
                    s1 = context.getResources().getString(0x7f0c0075);
                builder.setDescription(s1).setPlaybackType(1).setVolumeHandling(i).setVolumeMax(100);
                builder.addControlFilters(arraylist);
                integer = (Integer)sonosRouteVolumes.get(s);
                if(integer != null)
                    j = integer.intValue();
                else
                    j = devicevolume.getVolume();
                builder.setVolume(j);
                mediaroutedescriptor = builder.build();
            }
        }
        return mediaroutedescriptor;
    }

    private ArrayList createRouteControlFilters(ArrayList arraylist)
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        intentfilter.addAction("android.media.intent.action.PLAY");
        intentfilter.addDataScheme("http");
        intentfilter.addDataScheme("https");
        IntentFilter intentfilter1 = new IntentFilter();
        intentfilter1.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        intentfilter1.addAction("android.media.intent.action.ENQUEUE");
        intentfilter1.addDataScheme("http");
        intentfilter1.addDataScheme("https");
        String s;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); addDataTypeUnchecked(intentfilter1, s))
        {
            s = (String)iterator.next();
            addDataTypeUnchecked(intentfilter, s);
        }

        IntentFilter intentfilter2 = new IntentFilter();
        intentfilter2.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        intentfilter2.addAction("android.media.intent.action.START_SESSION");
        intentfilter2.addAction("android.media.intent.action.END_SESSION");
        intentfilter2.addAction("android.media.intent.action.PAUSE");
        intentfilter2.addAction("android.media.intent.action.SEEK");
        intentfilter2.addAction("android.media.intent.action.STOP");
        intentfilter2.addAction("android.media.intent.action.GET_STATUS");
        intentfilter2.addAction("android.media.intent.action.GET_SESSION_STATUS");
        intentfilter2.addAction("android.media.intent.action.RESUME");
        intentfilter2.addAction("android.media.intent.action.STOP");
        intentfilter2.addAction("android.media.intent.action.REMOVE");
        ArrayList arraylist1 = new ArrayList();
        arraylist1.add(intentfilter);
        arraylist1.add(intentfilter1);
        arraylist1.add(intentfilter2);
        return arraylist1;
    }

    private ArrayList getControlFiltersForPlayer(String s)
    {
        ArrayList arraylist;
        SonosRoutePlayerInfo sonosrouteplayerinfo;
        arraylist = null;
        sonosrouteplayerinfo = (SonosRoutePlayerInfo)sonosRoutePlayerInfo.get(s);
        if(sonosrouteplayerinfo != null) goto _L2; else goto _L1
_L1:
        return arraylist;
_L2:
        ArrayList arraylist1 = sonosrouteplayerinfo.queryRoutePlayerInfo();
        if(arraylist1 != null)
            arraylist = createRouteControlFilters(arraylist1);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private android.support.v7.media.MediaRouteProvider.RouteController getControllerForRouteId(String s)
    {
        if(!activeRouteControllers.containsKey(s))
        {
            activeRouteControllers.put(s, new Pair(new SonosRouteController(this, context, s), Integer.valueOf(1)));
        } else
        {
            SonosRouteController sonosroutecontroller = (SonosRouteController)((Pair)activeRouteControllers.get(s)).first;
            int i = ((Integer)((Pair)activeRouteControllers.get(s)).second).intValue();
            activeRouteControllers.put(s, new Pair(sonosroutecontroller, Integer.valueOf(i + 1)));
        }
        wifiLock.acquire();
        notifyRoutesChanged();
        return (android.support.v7.media.MediaRouteProvider.RouteController)((Pair)activeRouteControllers.get(s)).first;
    }

    public static String getGroupIDFromRouteID(String s)
    {
        String as[] = s.split(SONOS_ROUTEID_DELIM);
        String s1;
        if(as.length > 1)
            s1 = as[1];
        else
            s1 = null;
        return s1;
    }

    public static SonosRouteProvider getInstance(Context context1)
    {
        if(instance == null)
        {
            instance = new SonosRouteProvider(context1);
            instance.notifyRouteProviderInitialized();
        }
        return instance;
    }

    private ZoneDevice getMasterDeviceIfSonosGroupUsesMRP(ZoneGroup zonegroup)
    {
        if(zonegroup.isCompatibleAndVisible()) goto _L2; else goto _L1
_L1:
        ZoneDevice zonedevice = null;
_L4:
        return zonedevice;
_L2:
        zonedevice = zonegroup.getMasterDevice();
        if(zonedevice != null)
        {
            SCIVersion sciversion = zonedevice.getSoftwareVersion();
            boolean flag;
            boolean flag1;
            if(sciversion.getMajor() >= 29 && mrpCompatibilityManager.isCloudQueueSupported())
                flag = true;
            else
                flag = false;
            if(sciversion.getMajor() == 22 && sciversion.getBuild() >= 0x11170 || sciversion.getMajor() >= 24 && sciversion.getBuild() >= 0x10aae)
                flag1 = true;
            else
                flag1 = false;
            if(flag || !flag1)
            {
                StringBuilder stringbuilder = (new StringBuilder()).append(zonegroup.getSortName()).append(" (").append(sciversion.toString()).append(") ");
                String s;
                if(flag)
                    s = "supports Cloud Queue.";
                else
                    s = "does not support android-MRP.";
                SLog.d("SonosRouteProvider", stringbuilder.append(s).toString());
                zonedevice = null;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String getRouteID(String s, String s1)
    {
        return (new StringBuilder()).append(s).append(SONOS_ROUTEID_DELIM).append(s1).toString();
    }

    public static boolean isRouteActive(String s)
    {
        boolean flag;
        if(instance != null)
            flag = instance.activeRouteControllers.containsKey(s);
        else
            flag = false;
        return flag;
    }

    private void markAllActiveRoutesAsDetached()
    {
        SonosRouteController sonosroutecontroller;
        for(Iterator iterator = activeRouteControllers.keySet().iterator(); iterator.hasNext(); sonosroutecontroller.enterSimulatedPausedState())
        {
            String s = (String)iterator.next();
            sonosroutecontroller = (SonosRouteController)((Pair)activeRouteControllers.get(s)).first;
            sonosroutecontroller.setAttachedToSonosGroup(false);
        }

    }

    private void notifyRouteProviderInitialized()
    {
        for(Iterator iterator = routeProviderListeners.iterator(); iterator.hasNext(); ((SonosRouteProviderListener)iterator.next()).onSonosRouteProviderEvent(this, RouteProviderEvent.OnRouteProviderInitialized));
    }

    private void notifyRoutesChanged()
    {
        for(Iterator iterator = routeProviderListeners.iterator(); iterator.hasNext(); ((SonosRouteProviderListener)iterator.next()).onSonosRouteProviderEvent(this, RouteProviderEvent.OnActiveRoutesChanged));
    }

    private void reattachToDetachedRoutesIfPossible(Household household)
    {
        Iterator iterator = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_COMPATIBLE).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ZoneGroup zonegroup = (ZoneGroup)iterator.next();
            if(getMasterDeviceIfSonosGroupUsesMRP(zonegroup) != null)
            {
                String s = getRouteID(household.getID(), zonegroup.getID());
                if(activeRouteControllers.containsKey(s))
                    ((SonosRouteController)((Pair)activeRouteControllers.get(s)).first).setAttachedToSonosGroup(true);
            }
        } while(true);
    }

    public static void removeListener(SonosRouteProviderListener sonosrouteproviderlistener)
    {
        routeProviderListeners.remove(sonosrouteproviderlistener);
    }

    private void resumeDeviceExpiration()
    {
        SonosApplication.getInstance().getSCLibManager().resumeDeviceExpiration();
    }

    private void suspendDeviceExpiration()
    {
        if(!activeRouteControllers.isEmpty())
            SonosApplication.getInstance().getSCLibManager().suspendDeviceExpiration();
    }

    public SonosRoutePlayerInfo getRoutePlayerInfo(String s)
    {
        return (SonosRoutePlayerInfo)sonosRoutePlayerInfo.get(s);
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("onConnectionStatusChange: wifi ");
        String s;
        StringBuilder stringbuilder1;
        String s1;
        if(sonosnetworkmanager.isWifiAvailable())
            s = "";
        else
            s = "un";
        stringbuilder1 = stringbuilder.append(s).append("available, ");
        if(sonosnetworkmanager.isWifiConnected())
            s1 = "";
        else
            s1 = "dis";
        SLog.d("SonosRouteProvider", stringbuilder1.append(s1).append("connected.").toString());
        if(!sonosnetworkmanager.isWifiConnected())
            markAllActiveRoutesAsDetached();
        publishSonosRoutes();
    }

    public android.support.v7.media.MediaRouteProvider.RouteController onCreateRouteController(String s)
    {
        SLog.d("SonosRouteProvider", (new StringBuilder()).append("onCreateRouteController called for route '").append(s).append("'").toString());
        return getControllerForRouteId(s);
    }

    public void onGetRoutePlayerInfoError(SonosRoutePlayerInfo sonosrouteplayerinfo, String s)
    {
        SLog.e("SonosRouteProvider", (new StringBuilder()).append("[").append(sonosrouteplayerinfo.getPlayerId()).append("] error retrieving supported MIME types: ").append(s).toString());
        final String playerId = sonosrouteplayerinfo.getPlayerId();
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                getControlFiltersForPlayer(playerId);
            }

            final SonosRouteProvider this$0;
            final String val$playerId;

            
            {
                this$0 = SonosRouteProvider.this;
                playerId = s;
                super();
            }
        }
, 2000L);
    }

    public void onGetRoutePlayerInfoFatalError(SonosRoutePlayerInfo sonosrouteplayerinfo, String s)
    {
        String s1 = sonosrouteplayerinfo.getPlayerId();
        SLog.e("SonosRouteProvider", (new StringBuilder()).append("[").append(s1).append("] error: unable to retrieve MIME types: ").append(s).toString());
        if(sonosRoutePlayerInfo.containsKey(s1))
            sonosRoutePlayerInfo.remove(s1);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent.equals(com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged))
            if(LibraryUtils.householdHasTransientOrphanedGroups(household))
            {
                SLog.d("SonosRouteProvider", (new StringBuilder()).append("[").append(toString()).append("] onHouseholdEvent: ").append(householdevent.name()).append(": event ignored, orphaned group found! ").toString());
            } else
            {
                SLog.d("SonosRouteProvider", (new StringBuilder()).append("[").append(toString()).append("] onHouseholdEvent: ").append(householdevent.name()).toString());
                Iterator iterator = sonosRoutePlayerInfo.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    if(household.lookupDevice((String)iterator.next()) == null)
                        iterator.remove();
                } while(true);
                Iterator iterator1 = household.getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_COMPATIBLE_AND_VISIBLE).iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break;
                    String s = ((ZoneDevice)iterator1.next()).getId();
                    if(sonosRoutePlayerInfo.get(s) == null)
                    {
                        SonosRoutePlayerInfo sonosrouteplayerinfo = new SonosRoutePlayerInfo(s, this);
                        sonosRoutePlayerInfo.put(s, sonosrouteplayerinfo);
                        sonosrouteplayerinfo.queryRoutePlayerInfo();
                    }
                } while(true);
                reattachToDetachedRoutesIfPossible(household);
                publishSonosRoutes();
            }
    }

    public void onRoutePlayerInfoAvailable(SonosRoutePlayerInfo sonosrouteplayerinfo)
    {
        publishSonosRoutes();
    }

    protected void onSonosRouteVolumeChanged(String s, int i, boolean flag)
    {
        Integer integer = Integer.valueOf(i);
        if(flag)
            integer = Integer.valueOf(0);
        sonosRouteVolumes.put(s, integer);
        publishSonosRoutes();
    }

    protected void publishSonosRoutes()
    {
        ArrayList arraylist = new ArrayList();
        HashSet hashset = new HashSet();
        SCLibManager sclibmanager = SonosApplication.getInstance().getSCLibManager();
        String s = "";
        if(sclibmanager.isAssociatedToHousehold() && sonosNetworkManager.isWifiConnected())
        {
            Household household = sclibmanager.getHousehold();
            Iterator iterator1 = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_COMPATIBLE).iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                MediaRouteDescriptor mediaroutedescriptor1 = createMediaRouteDescriptorForGroup(household, (ZoneGroup)iterator1.next());
                if(mediaroutedescriptor1 != null)
                {
                    arraylist.add(mediaroutedescriptor1);
                    if(!haveSeenRoutes)
                        haveSeenRoutes = true;
                    String s2 = mediaroutedescriptor1.getId();
                    if(!StringUtils.isEmptyOrNull(s))
                        s = (new StringBuilder()).append(s).append(", ").toString();
                    s = (new StringBuilder()).append(s).append(mediaroutedescriptor1.getName()).toString();
                    hashset.add(s2);
                    if(activeRouteControllers.containsKey(s2))
                        ((SonosRouteController)((Pair)activeRouteControllers.get(s2)).first).setCachedRouteDescriptor(mediaroutedescriptor1);
                }
            } while(true);
        }
        Iterator iterator = activeRouteControllers.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s1 = (String)iterator.next();
            if(!hashset.contains(s1))
            {
                SonosRouteController sonosroutecontroller = (SonosRouteController)((Pair)activeRouteControllers.get(s1)).first;
                if(sonosroutecontroller.getPublishThisRouteWhenDetached())
                {
                    MediaRouteDescriptor mediaroutedescriptor = sonosroutecontroller.getCachedRouteDescriptor();
                    if(mediaroutedescriptor != null)
                    {
                        arraylist.add(mediaroutedescriptor);
                        if(!StringUtils.isEmptyOrNull(s))
                            s = (new StringBuilder()).append(s).append(", ").toString();
                        s = (new StringBuilder()).append(s).append(mediaroutedescriptor.getName()).toString();
                        hashset.add(mediaroutedescriptor.getId());
                    }
                }
            }
        } while(true);
        if(!s.equals(lastGroupsString))
        {
            lastGroupsString = s;
            android.support.v7.media.MediaRouteProviderDescriptor.Builder builder;
            if(StringUtils.isEmptyOrNull(lastGroupsString))
                SLog.d("SonosRouteProvider", "publishSonosRoutes: no Sonos groups found");
            else
                SLog.d("SonosRouteProvider", (new StringBuilder()).append("publishSonosRoutes: adding groups ").append(lastGroupsString).toString());
        }
        if(haveSeenRoutes)
        {
            builder = new android.support.v7.media.MediaRouteProviderDescriptor.Builder();
            builder.addRoutes(arraylist);
            setDescriptor(builder.build());
        }
    }

    protected boolean routeReleased(String s)
    {
        boolean flag = false;
        if(activeRouteControllers.containsKey(s))
        {
            int i = ((Integer)((Pair)activeRouteControllers.get(s)).second).intValue();
            if(i > 1)
            {
                SonosRouteController sonosroutecontroller = (SonosRouteController)((Pair)activeRouteControllers.get(s)).first;
                activeRouteControllers.put(s, new Pair(sonosroutecontroller, Integer.valueOf(i - 1)));
            } else
            {
                activeRouteControllers.remove(s);
                publishSonosRoutes();
                flag = true;
            }
            notifyRoutesChanged();
        }
        if(activeRouteControllers.isEmpty())
            wifiLock.release();
        return flag;
    }

    static final boolean $assertionsDisabled = false;
    private static final String LOG_TAG = "SonosRouteProvider";
    private static String SONOS_ROUTEID_DELIM = "::";
    private static final int VOLUME_MAX = 100;
    private static SonosRouteProvider instance = null;
    private static List routeProviderListeners = new ArrayList();
    private static SonosRouteWifiLock wifiLock;
    private HashMap activeRouteControllers;
    private Context context;
    private boolean haveSeenRoutes;
    private String lastGroupsString;
    private SonosMRPCompatibilityManager mrpCompatibilityManager;
    private ScreenReceiver screenReceiver;
    private SonosNetworkManager sonosNetworkManager;
    private HashMap sonosRoutePlayerInfo;
    private HashMap sonosRouteVolumes;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/media/SonosRouteProvider.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }



}
