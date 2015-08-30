// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.os.Handler;
import com.sonos.acr.sclib.sinks.VolumeViewListener;
import com.sonos.acr.sclib.wrappers.GroupVolume;

public class GroupVolumeController
    implements VolumeViewListener
{
    public class GroupVolumeDismiss
        implements Runnable
    {

        public void run()
        {
            if(groupVolumeListener != null)
                groupVolumeListener.onHideGroupVolume();
            cancelGVDismiss();
        }

        final GroupVolumeController this$0;

        public GroupVolumeDismiss()
        {
            this$0 = GroupVolumeController.this;
            super();
        }
    }

    public static interface GroupVolumeListener
    {

        public abstract void onHideGroupVolume();

        public abstract boolean onShowGroupVolume();
    }


    public GroupVolumeController(int i)
    {
        groupVolumeDismissTime = i;
    }

    public void cancelGVDismiss()
    {
        handler.removeCallbacks(gvDissmiss);
        gvDissmiss = null;
        shown = false;
    }

    public boolean isShown()
    {
        return shown;
    }

    public void onUserVolumeEvent(GroupVolume groupvolume, String s, com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType usereventtype)
    {
        if(shown || !"".equals(s) || usereventtype != com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.StartSeek && usereventtype != com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.VolumeChange && usereventtype != com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.UserInteraction) goto _L2; else goto _L1
_L1:
        shown = groupVolumeListener.onShowGroupVolume();
        if(shown)
            startGVDismiss();
_L4:
        return;
_L2:
        if(shown)
            if(usereventtype == com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.StartSeek)
                pauseGVDismiss();
            else
            if(usereventtype == com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType.EndSeek)
                startGVDismiss();
            else
                resetGVDismiss();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void pauseGVDismiss()
    {
        handler.removeCallbacks(gvDissmiss);
    }

    public void performGVDismiss()
    {
        handler.removeCallbacks(gvDissmiss);
        gvDissmiss = new GroupVolumeDismiss();
        handler.post(gvDissmiss);
    }

    protected void resetGVDismiss()
    {
        if(gvDissmiss != null)
        {
            handler.removeCallbacks(gvDissmiss);
            handler.postDelayed(gvDissmiss, groupVolumeDismissTime);
        }
    }

    public void setGroupVolumeDismissTime(int i)
    {
        groupVolumeDismissTime = i;
    }

    public void setGroupVolumeListener(GroupVolumeListener groupvolumelistener)
    {
        groupVolumeListener = groupvolumelistener;
    }

    public void startGVDismiss()
    {
        handler.removeCallbacks(gvDissmiss);
        gvDissmiss = new GroupVolumeDismiss();
        handler.postDelayed(gvDissmiss, groupVolumeDismissTime);
    }

    protected int groupVolumeDismissTime;
    GroupVolumeListener groupVolumeListener;
    protected GroupVolumeDismiss gvDissmiss;
    protected final Handler handler = new Handler();
    private boolean shown;
}
