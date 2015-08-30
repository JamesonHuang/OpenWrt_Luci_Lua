// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import com.sonos.acr.sclib.wrappers.NowPlaying;

// Referenced classes of package com.sonos.acr.nowplaying.controllers:
//            PlayIndicatorController

public class CurrentPlayIndicatorController extends PlayIndicatorController
{

    public CurrentPlayIndicatorController()
    {
    }

    public void addListener(PlayIndicatorController.PlayIndicatorListener playindicatorlistener)
    {
        realPlayIndicatorController.addListener(playindicatorlistener);
    }

    public int[] getLevels()
    {
        return realPlayIndicatorController.getLevels();
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        realPlayIndicatorController.onNowPlayingEvent(nowplaying, nowplayevent);
    }

    public void removeListener(PlayIndicatorController.PlayIndicatorListener playindicatorlistener)
    {
        realPlayIndicatorController.removeListener(playindicatorlistener);
    }

    public void setRealPlayIndicatorController(PlayIndicatorController playindicatorcontroller)
    {
        realPlayIndicatorController = playindicatorcontroller;
    }

    private PlayIndicatorController realPlayIndicatorController;
}
