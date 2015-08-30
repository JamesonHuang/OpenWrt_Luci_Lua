// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import com.sonos.acr.sclib.sinks.SCLibEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.media:
//            SonosRouteEventListener

public class SonosRouteEventSink extends SCLibEventSink
{

    public SonosRouteEventSink(ZoneGroup zonegroup)
    {
        subscribed = false;
        group = zonegroup;
    }

    private void subscribe()
    {
        if(!subscribed)
        {
            SLog.d("SonosRouteEventSink", "Subscribing...");
            if(group != null && group.nowPlaying != null && group.getGroupVolume() != null)
            {
                group.nowPlaying.subscribe(this);
                group.getGroupVolume().subscribe(this);
                subscribed = true;
            }
        }
    }

    private void unsubscribe()
    {
        if(subscribed)
        {
            SLog.d("SonosRouteEventSink", "Unsubscribing...");
            if(group != null)
            {
                if(group.getGroupVolume() != null)
                    group.getGroupVolume().unsubscribe(this);
                if(group.nowPlaying != null)
                    group.nowPlaying.unsubscribe(this);
            }
            subscribed = false;
        }
    }

    protected boolean hasSubscription()
    {
        return subscribed;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        ArrayList arraylist = new ArrayList(listeners);
        if(s.equals(sclibConstants.SCINOWPLAYING_ONMUSICCHANGED_EVENT))
        {
            NowPlaying nowplaying = (new ZoneGroup((SCINowPlaying)sciobj)).nowPlaying;
            for(Iterator iterator2 = arraylist.iterator(); iterator2.hasNext(); ((SonosRouteEventListener)iterator2.next()).onNowPlayingEvent(nowplaying, SonosRouteEventListener.SonosRouteEvent.OnMusicChanged));
        } else
        if(s.equals(sclibConstants.SCIGROUPVOLUME_ONVOLUMECHANGED_EVENT) || s.equals(sclibConstants.SCIGROUPVOLUME_ONMUTECHANGED_EVENT) || s.equals(sclibConstants.SCIGROUPVOLUME_ONOUTPUTMODECHANGED_EVENT))
        {
            GroupVolume groupvolume = (new ZoneGroup((SCIZoneGroup)LibraryUtils.cast((SCIGroupVolume)sciobj, com/sonos/sclib/SCIZoneGroup))).getGroupVolume();
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((SonosRouteEventListener)iterator.next()).onGroupVolumeChanged(groupvolume, SonosRouteEventListener.SonosRouteEvent.OnGroupVolumeChanged));
        } else
        if(s.equals(sclibConstants.SCIGROUPVOLUME_ONZONEGROUPCHANGED_EVENT))
        {
            for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); ((SonosRouteEventListener)iterator1.next()).onZoneGroupsChanged());
        }
    }

    protected void startMonitoring()
    {
        subscribe();
    }

    protected void stopMonitoring()
    {
        unsubscribe();
    }

    private static final String LOG_TAG = "SonosRouteEventSink";
    private ZoneGroup group;
    private boolean subscribed;
}
