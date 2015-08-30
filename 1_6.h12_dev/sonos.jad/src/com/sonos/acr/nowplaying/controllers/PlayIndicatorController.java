// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.PeriodicExecutor;
import com.sonos.sclib.SCINowPlayingTransport;
import com.sonos.sclib.SCNPPlaybackState;
import java.util.*;

public class PlayIndicatorController
    implements com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener
{
    public static interface PlayIndicatorListener
    {

        public abstract void onLevelsUpdated();
    }


    public PlayIndicatorController()
    {
        currentLevel = new int[3];
        desiredLevel = new int[3];
        levelIncreasing = new boolean[3];
        random = new Random();
        periodicExecutor = new PeriodicExecutor(90L) {

            public void execute()
            {
                updateLevels();
            }

            final PlayIndicatorController this$0;

            
            {
                this$0 = PlayIndicatorController.this;
                super(l);
            }
        }
;
        listeners = new ArrayList();
    }

    private int getRandomValueForLevel(int i)
    {
        int k;
        if(levelIncreasing[i])
        {
            int l = Math.max(1, 100 - currentLevel[i]);
            k = currentLevel[i] + random.nextInt(l);
        } else
        {
            int j = Math.max(1, -20 + currentLevel[i]);
            k = currentLevel[i] - random.nextInt(j);
        }
        return k;
    }

    private void setPlaying(boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        startAnimating();
_L4:
        isPlaying = flag;
        return;
_L2:
        if(isPlaying)
        {
            int i = 0;
            while(i < 3) 
            {
                levelIncreasing[i] = false;
                desiredLevel[i] = 0;
                i++;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void startAnimating()
    {
        periodicExecutor.start();
    }

    private void stopAnimating()
    {
        periodicExecutor.stop();
        for(int i = 0; i < 3; i++)
            currentLevel[i] = 0;

    }

    private void updateLevels()
    {
        int i = 0;
        while(i < 3) 
        {
            if(levelIncreasing[i])
            {
                int ai1[] = currentLevel;
                ai1[i] = ai1[i] + LEVEL_DELTA[i];
                currentLevel[i] = Math.min(currentLevel[i], desiredLevel[i]);
            } else
            {
                int ai[] = currentLevel;
                ai[i] = ai[i] - LEVEL_DELTA[i];
                currentLevel[i] = Math.max(currentLevel[i], desiredLevel[i]);
            }
            i++;
        }
        if(!isPlaying) goto _L2; else goto _L1
_L1:
        int k = 0;
        while(k < 3) 
        {
            if(levelIncreasing[k] && currentLevel[k] == desiredLevel[k])
            {
                levelIncreasing[k] = false;
                desiredLevel[k] = getRandomValueForLevel(k);
            } else
            if(!levelIncreasing[k] && currentLevel[k] == desiredLevel[k])
            {
                levelIncreasing[k] = true;
                desiredLevel[k] = getRandomValueForLevel(k);
            }
            k++;
        }
          goto _L3
_L2:
        boolean flag;
        int j;
        flag = true;
        j = 0;
_L9:
        if(j >= 3) goto _L5; else goto _L4
_L4:
        if(currentLevel[j] == desiredLevel[j]) goto _L7; else goto _L6
_L6:
        flag = false;
_L5:
        if(flag)
            stopAnimating();
_L3:
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((PlayIndicatorListener)iterator.next()).onLevelsUpdated());
        break; /* Loop/switch isn't completed */
_L7:
        j++;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void addListener(PlayIndicatorListener playindicatorlistener)
    {
        listeners.add(playindicatorlistener);
        if(isPlaying && listeners.size() == 1)
            startAnimating();
        else
            playindicatorlistener.onLevelsUpdated();
    }

    public int[] getLevels()
    {
        return currentLevel;
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
        {
            boolean flag;
            if(nowplaying.getTransport().getPlaybackState() == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
                flag = true;
            else
                flag = false;
            setPlaying(flag);
        }
    }

    public void removeListener(PlayIndicatorListener playindicatorlistener)
    {
        listeners.remove(playindicatorlistener);
        if(listeners.size() == 0)
            stopAnimating();
    }

    public static final int LEVEL_COUNT = 3;
    private static final int LEVEL_DELTA[];
    public static final int MAX_LEVEL = 100;
    public static final int MIN_LEVEL = 20;
    private int currentLevel[];
    private int desiredLevel[];
    private boolean isPlaying;
    private boolean levelIncreasing[];
    private ArrayList listeners;
    private PeriodicExecutor periodicExecutor;
    private Random random;

    static 
    {
        int ai[] = new int[3];
        ai[0] = 11;
        ai[1] = 9;
        ai[2] = 13;
        LEVEL_DELTA = ai;
    }

}
