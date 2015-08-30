// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            NowPlayingEventSink, HouseholdEventSink

public class CurrentNowPlayingEventSink extends NowPlayingEventSink
    implements HouseholdEventSink.HouseholdListener
{

    private CurrentNowPlayingEventSink()
    {
    }

    public static CurrentNowPlayingEventSink getInstance()
    {
        if(instance == null)
            instance = new CurrentNowPlayingEventSink();
        return instance;
    }

    private void subscribe()
    {
        if(subscribedNowPlaying == null)
        {
            ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
            if(zonegroup != null)
            {
                subscribedNowPlaying = zonegroup.nowPlaying;
                subscribedNowPlaying.subscribe(this);
            } else
            {
                SLog.e(LOG_TAG, "No current ZoneGroup to subscribe to.");
            }
        }
    }

    private void unsubscribe()
    {
        if(subscribedNowPlaying != null)
        {
            subscribedNowPlaying.unsubscribe(this);
            subscribedNowPlaying = null;
        }
    }

    public boolean addListener(NowPlayingEventSink.NowPlayingListener nowplayinglistener)
    {
        boolean flag = super.addListener(nowplayinglistener);
        if(flag && subscribedNowPlaying != null)
        {
            nowplayinglistener.onNowPlayingEvent(subscribedNowPlaying, NowPlayingEventSink.NowPlayEvent.OnMusicChanged);
            nowplayinglistener.onNowPlayingEvent(subscribedNowPlaying, NowPlayingEventSink.NowPlayEvent.OnAlarmRunningChanged);
            nowplayinglistener.onNowPlayingEvent(subscribedNowPlaying, NowPlayingEventSink.NowPlayEvent.OnSleepTimerGenerationChanged);
            nowplayinglistener.onNowPlayingEvent(subscribedNowPlaying, NowPlayingEventSink.NowPlayEvent.OnSnoozeRunningChanged);
        }
        return flag;
    }

    public volatile boolean addListener(SCLibEventSink.EventListener eventlistener)
    {
        return addListener((NowPlayingEventSink.NowPlayingListener)eventlistener);
    }

    public ZoneGroup getCurrentZoneGroup()
    {
        ZoneGroup zonegroup;
        if(subscribedNowPlaying != null)
            zonegroup = subscribedNowPlaying.getZoneGroup();
        else
            zonegroup = null;
        return zonegroup;
    }

    protected boolean hasSubscription()
    {
        boolean flag;
        if(subscribedNowPlaying != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onHouseholdEvent(Household household, HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
            SonosApplication.getInstance().getHandler().post(new Runnable() {

                public void run()
                {
                    unsubscribe();
                    subscribe();
                }

                final CurrentNowPlayingEventSink this$0;

            
            {
                this$0 = CurrentNowPlayingEventSink.this;
                super();
            }
            }
);
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

    private static CurrentNowPlayingEventSink instance;
    NowPlaying subscribedNowPlaying;


}
