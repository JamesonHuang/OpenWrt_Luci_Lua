// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink

public abstract class NowPlayingEventSink extends SCLibEventSink
{
    public static interface NowPlayingListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onNowPlayingEvent(NowPlaying nowplaying, NowPlayEvent nowplayevent);
    }

    public static final class NowPlayEvent extends Enum
    {

        public static NowPlayEvent valueOf(String s)
        {
            return (NowPlayEvent)Enum.valueOf(com/sonos/acr/sclib/sinks/NowPlayingEventSink$NowPlayEvent, s);
        }

        public static NowPlayEvent[] values()
        {
            return (NowPlayEvent[])$VALUES.clone();
        }

        private static final NowPlayEvent $VALUES[];
        public static final NowPlayEvent OnAlarmRunningChanged;
        public static final NowPlayEvent OnMusicChanged;
        public static final NowPlayEvent OnSleepTimerGenerationChanged;
        public static final NowPlayEvent OnSnoozeRunningChanged;
        public static final NowPlayEvent OnTVEqualizationChanged;

        static 
        {
            OnMusicChanged = new NowPlayEvent("OnMusicChanged", 0);
            OnAlarmRunningChanged = new NowPlayEvent("OnAlarmRunningChanged", 1);
            OnSleepTimerGenerationChanged = new NowPlayEvent("OnSleepTimerGenerationChanged", 2);
            OnSnoozeRunningChanged = new NowPlayEvent("OnSnoozeRunningChanged", 3);
            OnTVEqualizationChanged = new NowPlayEvent("OnTVEqualizationChanged", 4);
            NowPlayEvent anowplayevent[] = new NowPlayEvent[5];
            anowplayevent[0] = OnMusicChanged;
            anowplayevent[1] = OnAlarmRunningChanged;
            anowplayevent[2] = OnSleepTimerGenerationChanged;
            anowplayevent[3] = OnSnoozeRunningChanged;
            anowplayevent[4] = OnTVEqualizationChanged;
            $VALUES = anowplayevent;
        }

        private NowPlayEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public NowPlayingEventSink()
    {
    }

    private void dispatchEventType(ArrayList arraylist, NowPlaying nowplaying, NowPlayEvent nowplayevent)
    {
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((NowPlayingListener)iterator.next()).onNowPlayingEvent(nowplaying, nowplayevent));
    }

    public ZoneGroup getZoneGroup()
    {
        return zoneGroup;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(!(sciobj instanceof SCINowPlaying)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        NowPlaying nowplaying;
        arraylist = new ArrayList(listeners);
        zoneGroup = new ZoneGroup((SCINowPlaying)sciobj);
        nowplaying = zoneGroup.nowPlaying;
        if(!s.equals(sclibConstants.SCINOWPLAYING_ONMUSICCHANGED_EVENT)) goto _L4; else goto _L3
_L3:
        dispatchEventType(arraylist, nowplaying, NowPlayEvent.OnMusicChanged);
_L2:
        return;
_L4:
        if(s.equals(sclibConstants.SCINOWPLAYING_ONALARMRUNNINGCHANGED_EVENT))
            dispatchEventType(arraylist, nowplaying, NowPlayEvent.OnAlarmRunningChanged);
        else
        if(s.equals(sclibConstants.SCINOWPLAYING_ONSLEEPTIMERGENERATIONCHANGED_EVENT))
            dispatchEventType(arraylist, nowplaying, NowPlayEvent.OnSleepTimerGenerationChanged);
        else
        if(s.equals(sclibConstants.SCINOWPLAYING_ONSNOOZERUNNINGCHANGED_EVENT))
            dispatchEventType(arraylist, nowplaying, NowPlayEvent.OnSnoozeRunningChanged);
        else
        if(s.equals(sclibConstants.SCINOWPLAYING_ONTVEQUALIZATIONCHANGED_EVENT))
            dispatchEventType(arraylist, nowplaying, NowPlayEvent.OnTVEqualizationChanged);
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void stopMonitoring()
    {
        zoneGroup = null;
    }

    private ZoneGroup zoneGroup;
}
