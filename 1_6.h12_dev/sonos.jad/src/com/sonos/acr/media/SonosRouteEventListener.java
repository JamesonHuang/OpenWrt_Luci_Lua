// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.sclib.wrappers.NowPlaying;

public interface SonosRouteEventListener
    extends com.sonos.acr.sclib.sinks.SCLibEventSink.EventListener
{
    public static final class SonosRouteEvent extends Enum
    {

        public static SonosRouteEvent valueOf(String s)
        {
            return (SonosRouteEvent)Enum.valueOf(com/sonos/acr/media/SonosRouteEventListener$SonosRouteEvent, s);
        }

        public static SonosRouteEvent[] values()
        {
            return (SonosRouteEvent[])$VALUES.clone();
        }

        private static final SonosRouteEvent $VALUES[];
        public static final SonosRouteEvent OnGroupVolumeChanged;
        public static final SonosRouteEvent OnMusicChanged;

        static 
        {
            OnMusicChanged = new SonosRouteEvent("OnMusicChanged", 0);
            OnGroupVolumeChanged = new SonosRouteEvent("OnGroupVolumeChanged", 1);
            SonosRouteEvent asonosrouteevent[] = new SonosRouteEvent[2];
            asonosrouteevent[0] = OnMusicChanged;
            asonosrouteevent[1] = OnGroupVolumeChanged;
            $VALUES = asonosrouteevent;
        }

        private SonosRouteEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract void onGroupVolumeChanged(GroupVolume groupvolume, SonosRouteEvent sonosrouteevent);

    public abstract void onNowPlayingEvent(NowPlaying nowplaying, SonosRouteEvent sonosrouteevent);

    public abstract void onZoneGroupsChanged();
}
