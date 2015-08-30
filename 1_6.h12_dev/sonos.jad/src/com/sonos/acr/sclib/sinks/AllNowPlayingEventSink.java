// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            NowPlayingEventSink, HouseholdEventSink

public class AllNowPlayingEventSink extends NowPlayingEventSink
    implements HouseholdEventSink.HouseholdListener
{
    public static interface AllNowPlayingEventListener
        extends NowPlayingEventSink.NowPlayingListener, HouseholdEventSink.HouseholdListener
    {
    }


    private AllNowPlayingEventSink()
    {
    }

    public static AllNowPlayingEventSink getInstance()
    {
        if(instance == null)
            instance = new AllNowPlayingEventSink();
        return instance;
    }

    private void subscribe()
    {
        if(nowPlayingSubscriptions == null)
        {
            Household household = LibraryUtils.getHousehold();
            if(household != null)
            {
                nowPlayingSubscriptions = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY);
                if(nowPlayingSubscriptions != null)
                {
                    for(Iterator iterator = nowPlayingSubscriptions.iterator(); iterator.hasNext(); ((ZoneGroup)iterator.next()).nowPlaying.subscribe(this));
                    SLog.i(LOG_TAG, (new StringBuilder()).append("Subscribed to ").append(nowPlayingSubscriptions.size()).append(" zonegroups").toString());
                } else
                {
                    SLog.e(LOG_TAG, "Unable to subscribe, no zonegroups");
                }
            } else
            {
                SLog.e(LOG_TAG, "Unable to subscribe, no household");
            }
        } else
        {
            SLog.e(LOG_TAG, "Unable to subscribe, already subscribed");
        }
    }

    private void unsubscribe()
    {
        if(nowPlayingSubscriptions != null)
        {
            for(Iterator iterator = nowPlayingSubscriptions.iterator(); iterator.hasNext(); ((ZoneGroup)iterator.next()).nowPlaying.unsubscribe(this));
            nowPlayingSubscriptions = null;
        }
    }

    public boolean addListener(AllNowPlayingEventListener allnowplayingeventlistener)
    {
        boolean flag;
        if(super.addListener(allnowplayingeventlistener))
        {
            HouseholdEventSink.getInstance().addListener(allnowplayingeventlistener);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public volatile boolean addListener(SCLibEventSink.EventListener eventlistener)
    {
        return addListener((AllNowPlayingEventListener)eventlistener);
    }

    protected boolean hasSubscription()
    {
        boolean flag;
        if(nowPlayingSubscriptions != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onHouseholdEvent(Household household, HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
        {
            unsubscribe();
            subscribe();
        }
    }

    public void removeListener(AllNowPlayingEventListener allnowplayingeventlistener)
    {
        HouseholdEventSink.getInstance().removeListener(allnowplayingeventlistener);
        super.removeListener(allnowplayingeventlistener);
    }

    public volatile void removeListener(SCLibEventSink.EventListener eventlistener)
    {
        removeListener((AllNowPlayingEventListener)eventlistener);
    }

    protected void startMonitoring()
    {
        HouseholdEventSink.getInstance().addListener(this);
        subscribe();
    }

    protected void stopMonitoring()
    {
        unsubscribe();
        HouseholdEventSink.getInstance().removeListener(this);
    }

    private static AllNowPlayingEventSink instance;
    ArrayList nowPlayingSubscriptions;
}
