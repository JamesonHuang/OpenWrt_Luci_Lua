// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume;

import android.os.Handler;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.SLog;
import java.util.Timer;
import java.util.TimerTask;

public class ContinuousVolumeHelper
{
    public static interface ContinousVolumeCallback
    {

        public abstract void onVolumeChanged(int i, boolean flag);
    }


    public ContinuousVolumeHelper(String s)
    {
        lastEventedVolume = -1;
        lastEventedGroupMutedState = false;
        mHandler = new Handler();
        routeId = s;
    }

    public void cleanup()
    {
        if(setVolumeTimer != null)
        {
            setVolumeTimer.cancel();
            setVolumeTimer = null;
        }
    }

    public boolean getLastEventedGroupMutedState()
    {
        return lastEventedGroupMutedState;
    }

    public int getLastEventedVolume()
    {
        return lastEventedVolume;
    }

    public boolean isVolumeTimerActive()
    {
        boolean flag;
        if(setVolumeTimer != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onSetVolume(int i, ZoneGroup zonegroup)
    {
        if(zonegroup == null || zonegroup.getGroupVolume() == null) goto _L2; else goto _L1
_L1:
        final DeviceVolume groupVolume = zonegroup.getGroupVolume().getGroupVolume();
        if(groupVolume != null) goto _L4; else goto _L3
_L3:
        SLog.d("ContinuousVolumeHelper", "Volume Adjustment attempted for 'null' groupVolume");
_L2:
        return;
_L4:
        if(groupVolume.isVolumeAdjustable())
        {
            boolean flag;
            if(setVolumeTimer == null)
            {
                SLog.d("ContinuousVolumeHelper", "Begin Volume Adjustments");
                groupVolume.beginContinuousVolumeAdjustments();
            } else
            {
                setVolumeTimer.cancel();
            }
            setVolumeTimer = new Timer();
            setVolumeTimer.schedule(new TimerTask() {

                public void run()
                {
                    SLog.d("ContinuousVolumeHelper", "End Volume Adjustments");
                    groupVolume.endContinuousVolumeAdjustments();
                    setVolumeTimer = null;
                    mHandler.post(new Runnable() {

                        public void run()
                        {
                            SLog.d("ContinuousVolumeHelper", (new StringBuilder()).append(routeId).append("Delayed onGroupVolumeChanged: ").append(lastEventedVolume).toString());
                            if(callback != null)
                                callback.onVolumeChanged(lastEventedVolume, lastEventedGroupMutedState);
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
);
                }

                final ContinuousVolumeHelper this$0;
                final DeviceVolume val$groupVolume;

            
            {
                this$0 = ContinuousVolumeHelper.this;
                groupVolume = devicevolume;
                super();
            }
            }
, 2000L);
            groupVolume.setAbsoluteVolume(i);
            flag = groupVolume.isMuted();
            if(flag && i > 0)
            {
                groupVolume.setMute(false);
                flag = false;
            }
            if(callback != null)
                callback.onVolumeChanged(i, flag);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setContinousVolumeCallback(ContinousVolumeCallback continousvolumecallback)
    {
        callback = continousvolumecallback;
    }

    public void setLastEventedGroupMutedState(boolean flag)
    {
        lastEventedGroupMutedState = flag;
    }

    public void setLastEventedVolume(int i)
    {
        lastEventedVolume = i;
    }

    private static final String LOG_TAG = "ContinuousVolumeHelper";
    private static final long SONOS_SETVOLUME_TIMER_DELAY_IN_MILLISECS = 2000L;
    private ContinousVolumeCallback callback;
    private boolean lastEventedGroupMutedState;
    private int lastEventedVolume;
    private Handler mHandler;
    private String routeId;
    private Timer setVolumeTimer;


/*
    static Timer access$002(ContinuousVolumeHelper continuousvolumehelper, Timer timer)
    {
        continuousvolumehelper.setVolumeTimer = timer;
        return timer;
    }

*/





}
