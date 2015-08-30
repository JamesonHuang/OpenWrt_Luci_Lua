// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.widget.RemoteViews;
import com.sonos.acr.sclib.sinks.AllNowPlayingEventSink;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.services.builder.FullNowPlayingRemoteViewBuilder;
import com.sonos.acr.services.notification.NotificationService;
import com.sonos.acr.util.SLog;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.services.widgets:
//            SonosRemoteViewsService

public class StackViewsFactory
    implements android.widget.RemoteViewsService.RemoteViewsFactory, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener
{

    public StackViewsFactory(SonosRemoteViewsService sonosremoteviewsservice)
    {
        zoneGroups = new ArrayList();
        remoteViewService = sonosremoteviewsservice;
        nowPlayingViewBuilder = (new FullNowPlayingRemoteViewBuilder(sonosremoteviewsservice, 0x7f0300b1, com/sonos/acr/services/notification/NotificationService)).setDisabledAlpha(75).setEnabledAlpha(255);
    }

    /**
     * @deprecated Method getCount is deprecated
     */

    public int getCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        SLog.d(LOG_TAG, (new StringBuilder()).append("getCount:").append(zoneGroups.size()).toString());
        i = zoneGroups.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public long getItemId(int i)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("getItemId: ").append(i).toString());
        return (long)i;
    }

    public RemoteViews getLoadingView()
    {
        SLog.d(LOG_TAG, "getLoadingView");
        return nowPlayingViewBuilder.createView(null, 0);
    }

    /**
     * @deprecated Method getViewAt is deprecated
     */

    public RemoteViews getViewAt(int i)
    {
        this;
        JVM INSTR monitorenter ;
        RemoteViews remoteviews;
        SLog.d(LOG_TAG, (new StringBuilder()).append("getViewAt: ").append(i).toString());
        zoneGroups.get(i);
        remoteviews = nowPlayingViewBuilder.createView((ZoneGroup)zoneGroups.get(i), 0);
        this;
        JVM INSTR monitorexit ;
        return remoteviews;
        Exception exception;
        exception;
        throw exception;
    }

    public int getViewTypeCount()
    {
        return 1;
    }

    public boolean hasStableIds()
    {
        return false;
    }

    public void onCreate()
    {
        SLog.e(LOG_TAG, "On Create Called");
        HouseholdEventSink.getInstance().addListener(this);
        AllNowPlayingEventSink.getInstance().addListener(this);
    }

    /**
     * @deprecated Method onDataSetChanged is deprecated
     */

    public void onDataSetChanged()
    {
        this;
        JVM INSTR monitorenter ;
        SLog.d(LOG_TAG, "onDataSetChanged");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onDestroy()
    {
        SLog.e(LOG_TAG, "On Destroy Called");
        HouseholdEventSink.getInstance().removeListener(this);
        AllNowPlayingEventSink.getInstance().removeListener(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
        {
            zoneGroups = household.getCompatibleAndVisibleZoneGroups();
            remoteViewService.onWidgetUpdated();
            SLog.d(LOG_TAG, "onHouseholdEvent caused onWidgetUpdated");
        }
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
        {
            remoteViewService.onWidgetUpdated();
            SLog.d(LOG_TAG, "onNowPlayingEvent caused onWidgetUpdated");
        }
    }

    public static final String LOG_TAG = com/sonos/acr/services/widgets/StackViewsFactory.getSimpleName();
    FullNowPlayingRemoteViewBuilder nowPlayingViewBuilder;
    SonosRemoteViewsService remoteViewService;
    ArrayList zoneGroups;

}
