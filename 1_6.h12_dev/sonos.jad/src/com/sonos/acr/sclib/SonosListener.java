// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCIZoneGroupMgr;
import java.util.ArrayList;
import java.util.Iterator;

public class SonosListener
    implements com.sonos.acr.network.SonosNetworkManager.ConnectionListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{
    public static final class HouseholdSubState extends Enum
    {

        public static HouseholdSubState valueOf(String s)
        {
            return (HouseholdSubState)Enum.valueOf(com/sonos/acr/sclib/SonosListener$HouseholdSubState, s);
        }

        public static HouseholdSubState[] values()
        {
            return (HouseholdSubState[])$VALUES.clone();
        }

        private static final HouseholdSubState $VALUES[];
        public static final HouseholdSubState AllBridgeDevices;
        public static final HouseholdSubState AllHiddenDevices;
        public static final HouseholdSubState AllIncompatible;
        public static final HouseholdSubState NoDevices;
        public static final HouseholdSubState NoNetwork;
        public static final HouseholdSubState None;

        static 
        {
            None = new HouseholdSubState("None", 0);
            NoNetwork = new HouseholdSubState("NoNetwork", 1);
            NoDevices = new HouseholdSubState("NoDevices", 2);
            AllHiddenDevices = new HouseholdSubState("AllHiddenDevices", 3);
            AllBridgeDevices = new HouseholdSubState("AllBridgeDevices", 4);
            AllIncompatible = new HouseholdSubState("AllIncompatible", 5);
            HouseholdSubState ahouseholdsubstate[] = new HouseholdSubState[6];
            ahouseholdsubstate[0] = None;
            ahouseholdsubstate[1] = NoNetwork;
            ahouseholdsubstate[2] = NoDevices;
            ahouseholdsubstate[3] = AllHiddenDevices;
            ahouseholdsubstate[4] = AllBridgeDevices;
            ahouseholdsubstate[5] = AllIncompatible;
            $VALUES = ahouseholdsubstate;
        }

        private HouseholdSubState(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class HouseholdState extends Enum
    {

        public static HouseholdState valueOf(String s)
        {
            return (HouseholdState)Enum.valueOf(com/sonos/acr/sclib/SonosListener$HouseholdState, s);
        }

        public static HouseholdState[] values()
        {
            return (HouseholdState[])$VALUES.clone();
        }

        private static final HouseholdState $VALUES[];
        public static final HouseholdState ConnectedAllUnconfigured;
        public static final HouseholdState ConnectedIncompatibleHousehold;
        public static final HouseholdState ConnectedNoZones;
        public static final HouseholdState ConnectedNonPlayableZones;
        public static final HouseholdState ConnectedPlayableZones;
        public static final HouseholdState NoEstablishedHousehold;

        static 
        {
            NoEstablishedHousehold = new HouseholdState("NoEstablishedHousehold", 0);
            ConnectedNoZones = new HouseholdState("ConnectedNoZones", 1);
            ConnectedIncompatibleHousehold = new HouseholdState("ConnectedIncompatibleHousehold", 2);
            ConnectedNonPlayableZones = new HouseholdState("ConnectedNonPlayableZones", 3);
            ConnectedPlayableZones = new HouseholdState("ConnectedPlayableZones", 4);
            ConnectedAllUnconfigured = new HouseholdState("ConnectedAllUnconfigured", 5);
            HouseholdState ahouseholdstate[] = new HouseholdState[6];
            ahouseholdstate[0] = NoEstablishedHousehold;
            ahouseholdstate[1] = ConnectedNoZones;
            ahouseholdstate[2] = ConnectedIncompatibleHousehold;
            ahouseholdstate[3] = ConnectedNonPlayableZones;
            ahouseholdstate[4] = ConnectedPlayableZones;
            ahouseholdstate[5] = ConnectedAllUnconfigured;
            $VALUES = ahouseholdstate;
        }

        private HouseholdState(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface StateListener
    {

        public abstract void onHouseholdStateChange(HouseholdState householdstate, HouseholdSubState householdsubstate);
    }


    public SonosListener()
    {
        LOG_TAG = getClass().getSimpleName();
        listeners = new ArrayList();
        householdState = HouseholdState.NoEstablishedHousehold;
        householdSubState = HouseholdSubState.None;
        householdID = "";
    }

    private void notifyStateChange()
    {
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((StateListener)iterator.next()).onHouseholdStateChange(householdState, householdSubState));
        String s = LOG_TAG;
        Object aobj[] = new Object[2];
        aobj[0] = householdState.toString();
        aobj[1] = householdSubState.toString();
        SLog.i(s, String.format("household state: %1$s, %2$s", aobj));
    }

    private void updateConnectivityState()
    {
        householdState;
        householdSubState;
        if(networkManager != null && networkManager.isRunning() && networkManager.isWifiAvailable() && networkManager.isWifiConnected()) goto _L2; else goto _L1
_L1:
        HouseholdState householdstate;
        HouseholdSubState householdsubstate;
        householdstate = HouseholdState.ConnectedNoZones;
        householdsubstate = HouseholdSubState.NoNetwork;
_L10:
        if(householdState != householdstate || householdSubState != householdsubstate)
        {
            householdState = householdstate;
            householdSubState = householdsubstate;
            notifyStateChange();
        }
        return;
_L2:
        SCIZoneGroupMgr scizonegroupmgr;
        com.sonos.sclib.SCIZoneGroupMgr.ZMState zmstate;
        if(household == null || "".equals(household.getID()))
        {
            householdstate = HouseholdState.NoEstablishedHousehold;
            householdsubstate = HouseholdSubState.None;
            continue; /* Loop/switch isn't completed */
        }
        scizonegroupmgr = household.getZoneGroupManager();
        zmstate = scizonegroupmgr.getState();
        if(zmstate == com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_NO_ZONES_FOUND)
        {
            householdstate = HouseholdState.ConnectedNoZones;
            householdsubstate = HouseholdSubState.NoDevices;
            continue; /* Loop/switch isn't completed */
        }
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState = new int[com.sonos.sclib.SCIZoneGroupMgr.ZMState.values().length];
                NoSuchFieldError nosuchfielderror5;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_NO_PLAYERS.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_ORPHANED_PLAYERS.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_ALL_ZONES_HIDDEN.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_ALL_UNCONFIGURED.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_NORMAL.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$com$sonos$sclib$SCIZoneGroupMgr$ZMState[com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_INCOMPATIBLE.ordinal()] = 6;
_L2:
                return;
                nosuchfielderror5;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIZoneGroupMgr.ZMState[zmstate.ordinal()];
        JVM INSTR tableswitch 1 6: default 208
    //                   1 236
    //                   2 236
    //                   3 261
    //                   4 273
    //                   5 285
    //                   6 297;
           goto _L3 _L4 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_297;
_L5:
        break; /* Loop/switch isn't completed */
_L3:
        throw new RuntimeException((new StringBuilder()).append("Unexpected device state when checking for zones:").append(zmstate).toString());
_L4:
        householdstate = HouseholdState.ConnectedNonPlayableZones;
        householdsubstate = HouseholdSubState.AllBridgeDevices;
_L11:
        if(scizonegroupmgr.getAllIncompatible())
            householdsubstate = HouseholdSubState.AllIncompatible;
        if(true) goto _L10; else goto _L9
_L9:
        householdstate = HouseholdState.ConnectedNonPlayableZones;
        householdsubstate = HouseholdSubState.AllHiddenDevices;
          goto _L11
_L6:
        householdstate = HouseholdState.ConnectedAllUnconfigured;
        householdsubstate = HouseholdSubState.None;
          goto _L11
_L7:
        householdstate = HouseholdState.ConnectedPlayableZones;
        householdsubstate = HouseholdSubState.None;
          goto _L11
        householdstate = HouseholdState.ConnectedIncompatibleHousehold;
        householdsubstate = HouseholdSubState.None;
          goto _L11
    }

    public String getHouseholdID()
    {
        return householdID;
    }

    public HouseholdState getState()
    {
        return householdState;
    }

    public HouseholdSubState getSubState()
    {
        return householdSubState;
    }

    public boolean isConnectedAndPlayable()
    {
        boolean flag;
        if(householdState == HouseholdState.ConnectedPlayableZones && householdSubState == HouseholdSubState.None)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        networkManager = sonosnetworkmanager;
        updateConnectivityState();
    }

    public void onHouseholdEvent(Household household1, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(household1 != null)
        {
            String s = household1.getID();
            if(StringUtils.isNotEmptyOrNull(s))
                householdID = s;
        }
        household = household1;
        updateConnectivityState();
    }

    public void start()
    {
        HouseholdEventSink.getInstance().addListener(this);
        networkManager = SonosApplication.getInstance().getNetworkStatusMonitor();
        networkManager.subscribe(this);
        updateConnectivityState();
    }

    public void stop()
    {
        HouseholdEventSink.getInstance().removeListener(this);
        SonosApplication.getInstance().getNetworkStatusMonitor().unsubscribe(this);
        household = null;
        networkManager = null;
        updateConnectivityState();
    }

    public void subscribe(StateListener statelistener)
    {
        if(!listeners.contains(statelistener))
        {
            statelistener.onHouseholdStateChange(householdState, householdSubState);
            listeners.add(statelistener);
        }
    }

    public void unsubscribe(StateListener statelistener)
    {
        listeners.remove(statelistener);
    }

    private static final int twentyFourHours = 0x5265c00;
    public String LOG_TAG;
    private Household household;
    private String householdID;
    private HouseholdState householdState;
    private HouseholdSubState householdSubState;
    private ArrayList listeners;
    private SonosNetworkManager networkManager;
}
