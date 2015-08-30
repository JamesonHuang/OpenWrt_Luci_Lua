// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.res.Resources;
import android.os.Handler;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.*;

public class SleepDialogHandler extends SCIOpCBSwigBase
{
    public static final class SleepTime extends Enum
    {

        public static String[] labels(Resources resources)
        {
            SleepTime asleeptime[] = values();
            String as[] = new String[asleeptime.length];
            for(int i = 0; i < asleeptime.length; i++)
                as[i] = resources.getString(asleeptime[i].nameResourceId);

            return as;
        }

        public static SleepTime valueOf(String s)
        {
            return (SleepTime)Enum.valueOf(com/sonos/acr/nowplaying/SleepDialogHandler$SleepTime, s);
        }

        public static SleepTime[] values()
        {
            return (SleepTime[])$VALUES.clone();
        }

        private static final SleepTime $VALUES[];
        public static final SleepTime SLEEP_120;
        public static final SleepTime SLEEP_15;
        public static final SleepTime SLEEP_30;
        public static final SleepTime SLEEP_45;
        public static final SleepTime SLEEP_60;
        public static final SleepTime SLEEP_OFF;
        public int nameResourceId;
        public String sleepTime;

        static 
        {
            SLEEP_OFF = new SleepTime("SLEEP_OFF", 0, 0x7f0c00c1, "");
            SLEEP_15 = new SleepTime("SLEEP_15", 1, 0x7f0c00bd, "00:15:00");
            SLEEP_30 = new SleepTime("SLEEP_30", 2, 0x7f0c00be, "00:30:00");
            SLEEP_45 = new SleepTime("SLEEP_45", 3, 0x7f0c00bf, "00:45:00");
            SLEEP_60 = new SleepTime("SLEEP_60", 4, 0x7f0c00c0, "01:00:00");
            SLEEP_120 = new SleepTime("SLEEP_120", 5, 0x7f0c00bc, "02:00:00");
            SleepTime asleeptime[] = new SleepTime[6];
            asleeptime[0] = SLEEP_OFF;
            asleeptime[1] = SLEEP_15;
            asleeptime[2] = SLEEP_30;
            asleeptime[3] = SLEEP_45;
            asleeptime[4] = SLEEP_60;
            asleeptime[5] = SLEEP_120;
            $VALUES = asleeptime;
        }

        private SleepTime(String s, int i, int j, String s1)
        {
            super(s, i);
            nameResourceId = j;
            sleepTime = s1;
        }
    }

    public static interface SleepTimerListener
    {

        public abstract void onSleepTimerChanged(boolean flag);
    }


    public SleepDialogHandler()
    {
        listeners = new HashSet();
        updater = new Runnable() {

            public void run()
            {
                updateTime();
            }

            final SleepDialogHandler this$0;

            
            {
                this$0 = SleepDialogHandler.this;
                super();
            }
        }
;
        timeRemainingSeconds = -1;
        timeRemainingMins = -1;
    }

    private void onSleepTimerChanged(boolean flag)
    {
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((SleepTimerListener)iterator.next()).onSleepTimerChanged(flag));
    }

    private int secondsFromSleepTimeString(String s)
    {
        int i = 0;
        if(s != null && s.length() == 8)
        {
            String as[] = s.split("\\:");
            int j = Integer.valueOf(as[0]).intValue();
            int k = Integer.valueOf(as[1]).intValue();
            i = Integer.valueOf(as[2]).intValue() + 60 * (k + j * 60);
        }
        return i;
    }

    private void updateRemainingTime(int i)
    {
        boolean flag;
        flag = true;
        Handler handler = SonosApplication.getInstance().getHandler();
        handler.removeCallbacks(updater);
        if(i > 0)
        {
            int k = 1 + (i - 1) % 60;
            handler.postDelayed(updater, k * 1000);
        }
        if(timeRemainingSeconds != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        timeRemainingSeconds = i;
        Resources resources = SonosApplication.getInstance().getResources();
        int j = (i + 59) / 60;
        if(timeRemainingMins != j)
        {
            if(j > 0)
            {
                String s = resources.getString(0x7f0c00c2);
                Object aobj[] = new Object[flag];
                aobj[0] = Integer.valueOf(j);
                timeRemainingText = String.format(s, aobj);
            } else
            {
                timeRemainingText = resources.getString(0x7f0c00c1);
            }
            timeRemainingMins = j;
            if(j <= 0)
                flag = false;
            onSleepTimerChanged(flag);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void _operationComplete(long l, int i)
    {
        if(remainingSleepTimeOp == null || remainingSleepTimeOp.serialNumber() != l) goto _L2; else goto _L1
_L1:
        if(i != 0) goto _L4; else goto _L3
_L3:
        String s = remainingSleepTimeOp.getRemainingSleepTimerDuration();
        lastUpdated = new Date();
        newSleepTime = s;
        int k = secondsFromSleepTimeString(s);
        lastUpdatedSecs = k;
        updateRemainingTime(k);
        remainingSleepTimeOp = null;
_L6:
        return;
_L4:
        remainingSleepTimeOp = null;
        if(numRetries < 2)
        {
            numRetries = 1 + numRetries;
            SCINowPlayingSleepTimer scinowplayingsleeptimer = LibraryUtils.getNowPlayingSleepTimer();
            if(scinowplayingsleeptimer != null)
            {
                remainingSleepTimeOp = scinowplayingsleeptimer.createGetRemainingSleepTimerDurationOp();
                if(remainingSleepTimeOp != null)
                    remainingSleepTimeOp._start(this);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(saveOp != null && saveOp.serialNumber() == l)
        {
            if(i == 0)
            {
                lastUpdated = new Date();
                int j = secondsFromSleepTimeString(newSleepTime);
                lastUpdatedSecs = j;
                updateRemainingTime(j);
            }
            saveOp = null;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int getTimeRemainingSeconds()
    {
        return timeRemainingSeconds;
    }

    public String getTimeRemainingText()
    {
        return timeRemainingText;
    }

    public void pollTime()
    {
        SLog.d(LOG_TAG, "Polling sleep timer...");
        SCINowPlayingSleepTimer scinowplayingsleeptimer = LibraryUtils.getNowPlayingSleepTimer();
        if(scinowplayingsleeptimer != null)
        {
            if(remainingSleepTimeOp != null)
                remainingSleepTimeOp._cancel();
            remainingSleepTimeOp = scinowplayingsleeptimer.createGetRemainingSleepTimerDurationOp();
            numRetries = 0;
            if(remainingSleepTimeOp != null)
                remainingSleepTimeOp._start(this);
        }
    }

    public void registerSleepTimerListener(SleepTimerListener sleeptimerlistener)
    {
        listeners.add(sleeptimerlistener);
    }

    public void setNewSleepTime(String s, SonosActivity sonosactivity)
    {
        SCINowPlayingSleepTimer scinowplayingsleeptimer = LibraryUtils.getNowPlayingSleepTimer();
        if(scinowplayingsleeptimer != null)
        {
            saveOp = scinowplayingsleeptimer.createConfigureSleepTimerOp(s);
            newSleepTime = s;
            (new UiBusyManager(sonosactivity, saveOp, this)).start();
        }
    }

    public void unregisterSleepTimerListener(SleepTimerListener sleeptimerlistener)
    {
        listeners.remove(sleeptimerlistener);
    }

    public void updateTime()
    {
        if(lastUpdated != null)
        {
            long l = ((new Date()).getTime() - lastUpdated.getTime()) / 1000L;
            long l1 = lastUpdatedSecs - l;
            if(l1 < 0L)
                l1 = 0L;
            updateRemainingTime((int)l1);
        } else
        {
            timeRemainingText = null;
        }
    }

    public static final String LOG_TAG = com/sonos/acr/nowplaying/SleepDialogHandler.getSimpleName();
    private static final int MAX_NUM_RETRIES = 2;
    private Date lastUpdated;
    private long lastUpdatedSecs;
    private Set listeners;
    private String newSleepTime;
    private int numRetries;
    private SCIOpAVTransportGetRemainingSleepTimerDuration remainingSleepTimeOp;
    private SCIOp saveOp;
    private int timeRemainingMins;
    private int timeRemainingSeconds;
    private String timeRemainingText;
    private Runnable updater;

}
