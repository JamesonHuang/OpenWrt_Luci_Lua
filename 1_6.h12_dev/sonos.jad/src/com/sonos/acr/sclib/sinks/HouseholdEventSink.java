// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink

public class HouseholdEventSink extends SCLibEventSink
    implements com.sonos.acr.network.SonosNetworkManager.ConnectionListener, com.sonos.acr.sclib.wrappers.Household.HouseholdChangedListener
{
    public static interface HouseholdListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onHouseholdEvent(Household household, HouseholdEvent householdevent);
    }

    public static final class HouseholdEvent extends Enum
    {

        public static HouseholdEvent valueOf(String s)
        {
            return (HouseholdEvent)Enum.valueOf(com/sonos/acr/sclib/sinks/HouseholdEventSink$HouseholdEvent, s);
        }

        public static HouseholdEvent[] values()
        {
            return (HouseholdEvent[])$VALUES.clone();
        }

        private static final HouseholdEvent $VALUES[];
        public static final HouseholdEvent OnCurrentZoneGroupChanged;
        public static final HouseholdEvent OnSearchablesListChanged;
        public static final HouseholdEvent OnZoneGroupChanged;

        static 
        {
            OnCurrentZoneGroupChanged = new HouseholdEvent("OnCurrentZoneGroupChanged", 0);
            OnZoneGroupChanged = new HouseholdEvent("OnZoneGroupChanged", 1);
            OnSearchablesListChanged = new HouseholdEvent("OnSearchablesListChanged", 2);
            HouseholdEvent ahouseholdevent[] = new HouseholdEvent[3];
            ahouseholdevent[0] = OnCurrentZoneGroupChanged;
            ahouseholdevent[1] = OnZoneGroupChanged;
            ahouseholdevent[2] = OnSearchablesListChanged;
            $VALUES = ahouseholdevent;
        }

        private HouseholdEvent(String s, int i)
        {
            super(s, i);
        }
    }


    private HouseholdEventSink()
    {
        subscribedHousehold = null;
        networkManager = SonosApplication.getInstance().getNetworkStatusMonitor();
    }

    public static HouseholdEventSink getInstance()
    {
        if(instance == null)
            instance = new HouseholdEventSink();
        return instance;
    }

    private void subscribe()
    {
        if(subscribedHousehold == null)
        {
            subscribedHousehold = LibraryUtils.getHousehold();
            if(subscribedHousehold != null)
                subscribedHousehold.subscribe(this);
            else
                SLog.e(LOG_TAG, "Error Starting monitor: can't get household");
        } else
        {
            SLog.e(LOG_TAG, "Error starting monitor: household already started");
        }
    }

    private void unsubscribe()
    {
        if(subscribedHousehold != null)
        {
            subscribedHousehold.unsubscribe(this);
            subscribedHousehold = null;
        } else
        {
            SLog.e(LOG_TAG, "Household was null, unable to unsubscribe");
        }
    }

    public boolean addListener(HouseholdListener householdlistener)
    {
        boolean flag = super.addListener(householdlistener);
        if(flag && householdlistener != null && subscribedHousehold != null)
        {
            householdlistener.onHouseholdEvent(subscribedHousehold, HouseholdEvent.OnZoneGroupChanged);
            householdlistener.onHouseholdEvent(subscribedHousehold, HouseholdEvent.OnCurrentZoneGroupChanged);
            householdlistener.onHouseholdEvent(subscribedHousehold, HouseholdEvent.OnSearchablesListChanged);
        }
        return flag;
    }

    public volatile boolean addListener(SCLibEventSink.EventListener eventlistener)
    {
        return addListener((HouseholdListener)eventlistener);
    }

    public Household getSubscribedHousehold()
    {
        return subscribedHousehold;
    }

    protected boolean hasSubscription()
    {
        boolean flag;
        if(subscribedHousehold != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        if(sonosnetworkmanager.isWifiConnected())
            subscribe();
        else
            unsubscribe();
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(sciobj instanceof SCIHousehold)
        {
            ArrayList arraylist = new ArrayList(listeners);
            Household household = LibraryUtils.getHousehold();
            if(s.equals(sclibConstants.SCIHOUSEHOLD_ONCURRENTZONEGROUPCHANGED_EVENT))
            {
                for(Iterator iterator2 = arraylist.iterator(); iterator2.hasNext(); ((HouseholdListener)iterator2.next()).onHouseholdEvent(household, HouseholdEvent.OnCurrentZoneGroupChanged));
            } else
            if(s.equals(sclibConstants.SCIHOUSEHOLD_ONZONEGROUPSCHANGED_EVENT))
            {
                for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); ((HouseholdListener)iterator1.next()).onHouseholdEvent(household, HouseholdEvent.OnZoneGroupChanged));
            } else
            if(s.equals(sclibConstants.SCIHOUSEHOLD_ONSEARCHABLESLISTCHANGED_EVENT))
            {
                household.updateSearchables();
                for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((HouseholdListener)iterator.next()).onHouseholdEvent(household, HouseholdEvent.OnSearchablesListChanged));
            }
        }
    }

    public void onHouseholdChanged()
    {
        if(hasSubscription())
        {
            unsubscribe();
            subscribe();
        }
    }

    public void removeListener(HouseholdListener householdlistener)
    {
        super.removeListener(householdlistener);
    }

    public volatile void removeListener(SCLibEventSink.EventListener eventlistener)
    {
        removeListener((HouseholdListener)eventlistener);
    }

    protected void startMonitoring()
    {
        networkManager.subscribe(this);
        subscribe();
    }

    protected void stopMonitoring()
    {
        networkManager.unsubscribe(this);
        unsubscribe();
    }

    protected void terminate()
    {
        unsubscribe();
        networkManager.unsubscribe(this);
    }

    private static HouseholdEventSink instance;
    SonosNetworkManager networkManager;
    Household subscribedHousehold;
}
