// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink, HouseholdEventSink

public abstract class GroupVolumeEventSink extends SCLibEventSink
    implements HouseholdEventSink.HouseholdListener
{
    public static interface GroupVolumeListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onVolumeEvent(GroupVolume groupvolume, VolumeEvent volumeevent);
    }

    public static final class VolumeEvent extends Enum
    {

        public static VolumeEvent valueOf(String s)
        {
            return (VolumeEvent)Enum.valueOf(com/sonos/acr/sclib/sinks/GroupVolumeEventSink$VolumeEvent, s);
        }

        public static VolumeEvent[] values()
        {
            return (VolumeEvent[])$VALUES.clone();
        }

        private static final VolumeEvent $VALUES[];
        public static final VolumeEvent OnMuteChanged;
        public static final VolumeEvent OnOuputmodeChanged;
        public static final VolumeEvent OnVolumeChanged;
        public static final VolumeEvent OnZoneGroupChanged;

        static 
        {
            OnMuteChanged = new VolumeEvent("OnMuteChanged", 0);
            OnOuputmodeChanged = new VolumeEvent("OnOuputmodeChanged", 1);
            OnVolumeChanged = new VolumeEvent("OnVolumeChanged", 2);
            OnZoneGroupChanged = new VolumeEvent("OnZoneGroupChanged", 3);
            VolumeEvent avolumeevent[] = new VolumeEvent[4];
            avolumeevent[0] = OnMuteChanged;
            avolumeevent[1] = OnOuputmodeChanged;
            avolumeevent[2] = OnVolumeChanged;
            avolumeevent[3] = OnZoneGroupChanged;
            $VALUES = avolumeevent;
        }

        private VolumeEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public GroupVolumeEventSink()
    {
    }

    public boolean addListener(GroupVolumeListener groupvolumelistener)
    {
        boolean flag;
        if(super.addListener(groupvolumelistener) && subscribedGroupVolume != null)
        {
            groupvolumelistener.onVolumeEvent(subscribedGroupVolume, VolumeEvent.OnMuteChanged);
            groupvolumelistener.onVolumeEvent(subscribedGroupVolume, VolumeEvent.OnOuputmodeChanged);
            groupvolumelistener.onVolumeEvent(subscribedGroupVolume, VolumeEvent.OnVolumeChanged);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public volatile boolean addListener(SCLibEventSink.EventListener eventlistener)
    {
        return addListener((GroupVolumeListener)eventlistener);
    }

    protected abstract GroupVolume createGroupVolume();

    protected boolean hasSubscription()
    {
        boolean flag;
        if(subscribedGroupVolume != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(sciobj instanceof SCIGroupVolume)
        {
            GroupVolume groupvolume = new GroupVolume((SCIGroupVolume)sciobj);
            if(s.equals(sclibConstants.SCIGROUPVOLUME_ONMUTECHANGED_EVENT))
            {
                for(Iterator iterator3 = listeners.iterator(); iterator3.hasNext(); ((GroupVolumeListener)iterator3.next()).onVolumeEvent(groupvolume, VolumeEvent.OnMuteChanged));
            } else
            if(s.equals(sclibConstants.SCIGROUPVOLUME_ONOUTPUTMODECHANGED_EVENT))
            {
                for(Iterator iterator2 = listeners.iterator(); iterator2.hasNext(); ((GroupVolumeListener)iterator2.next()).onVolumeEvent(groupvolume, VolumeEvent.OnOuputmodeChanged));
            } else
            if(s.equals(sclibConstants.SCIGROUPVOLUME_ONVOLUMECHANGED_EVENT))
            {
                for(Iterator iterator1 = listeners.iterator(); iterator1.hasNext(); ((GroupVolumeListener)iterator1.next()).onVolumeEvent(groupvolume, VolumeEvent.OnVolumeChanged));
            } else
            if(s.equals(sclibConstants.SCIGROUPVOLUME_ONZONEGROUPCHANGED_EVENT))
            {
                for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((GroupVolumeListener)iterator.next()).onVolumeEvent(groupvolume, VolumeEvent.OnZoneGroupChanged));
            }
        }
    }

    public void onHouseholdEvent(Household household, HouseholdEventSink.HouseholdEvent householdevent)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$acr$sclib$sinks$HouseholdEventSink$HouseholdEvent[];

            static 
            {
                $SwitchMap$com$sonos$acr$sclib$sinks$HouseholdEventSink$HouseholdEvent = new int[HouseholdEventSink.HouseholdEvent.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$acr$sclib$sinks$HouseholdEventSink$HouseholdEvent[HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$acr$sclib$sinks$HouseholdEventSink$HouseholdEvent[HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent[householdevent.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 33
    //                   2 33;
           goto _L1 _L2 _L2
_L1:
        return;
_L2:
        unsubscribe();
        subscribe();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void startMonitoring()
    {
        subscribe();
        HouseholdEventSink.getInstance().addListener(this);
    }

    protected void stopMonitoring()
    {
        HouseholdEventSink.getInstance().removeListener(this);
        unsubscribe();
    }

    protected void subscribe()
    {
        if(subscribedGroupVolume == null)
        {
            subscribedGroupVolume = createGroupVolume();
            if(subscribedGroupVolume != null)
                subscribedGroupVolume.subscribe(this);
            else
                SLog.e(LOG_TAG, "Error Subscribing: subscription was null");
        } else
        {
            SLog.e(LOG_TAG, "Error Subscribing: already Subscribed.");
        }
    }

    protected void unsubscribe()
    {
        if(subscribedGroupVolume != null)
        {
            subscribedGroupVolume.unsubscribe(this);
            subscribedGroupVolume = null;
        }
    }

    protected GroupVolume subscribedGroupVolume;
}
