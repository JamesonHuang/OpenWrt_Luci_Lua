// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import com.sonos.acr.sclib.sinks.VolumeViewListener;
import com.sonos.acr.sclib.wrappers.DeviceVolume;
import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.util.*;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.nowplaying.controllers:
//            VolumeView

public class VolumeViewController
    implements com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener
{
    public static final class UserEventType extends Enum
    {

        public static UserEventType valueOf(String s)
        {
            return (UserEventType)Enum.valueOf(com/sonos/acr/nowplaying/controllers/VolumeViewController$UserEventType, s);
        }

        public static UserEventType[] values()
        {
            return (UserEventType[])$VALUES.clone();
        }

        private static final UserEventType $VALUES[];
        public static final UserEventType ActiveDeviceChanged;
        public static final UserEventType EndSeek;
        public static final UserEventType MuteChange;
        public static final UserEventType StartSeek;
        public static final UserEventType Unspecified;
        public static final UserEventType UserInteraction;
        public static final UserEventType VolumeChange;

        static 
        {
            StartSeek = new UserEventType("StartSeek", 0);
            EndSeek = new UserEventType("EndSeek", 1);
            VolumeChange = new UserEventType("VolumeChange", 2);
            MuteChange = new UserEventType("MuteChange", 3);
            Unspecified = new UserEventType("Unspecified", 4);
            UserInteraction = new UserEventType("UserInteraction", 5);
            ActiveDeviceChanged = new UserEventType("ActiveDeviceChanged", 6);
            UserEventType ausereventtype[] = new UserEventType[7];
            ausereventtype[0] = StartSeek;
            ausereventtype[1] = EndSeek;
            ausereventtype[2] = VolumeChange;
            ausereventtype[3] = MuteChange;
            ausereventtype[4] = Unspecified;
            ausereventtype[5] = UserInteraction;
            ausereventtype[6] = ActiveDeviceChanged;
            $VALUES = ausereventtype;
        }

        private UserEventType(String s, int i)
        {
            super(s, i);
        }
    }


    public VolumeViewController()
    {
        keyHeld = -1;
    }

    private void stopVolumeExecutor()
    {
        if(volumeExecutor != null)
        {
            volumeExecutor.stop();
            volumeExecutor = null;
        }
        keyHeld = -1;
    }

    public void addView(VolumeView volumeview)
    {
        if(volumeViews.add(volumeview))
        {
            volumeview.setVolumeViewController(this);
            if(subscribedGroupVolume != null)
                volumeview.onVolumeEvent(subscribedGroupVolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent.OnZoneGroupChanged);
        }
        addVolumeViewListener(volumeview);
    }

    public void addVolumeViewListener(VolumeViewListener volumeviewlistener)
    {
        if(volumeListeners.add(volumeviewlistener) && subscribedGroupVolume != null)
            volumeviewlistener.onUserVolumeEvent(subscribedGroupVolume, "", UserEventType.Unspecified);
    }

    protected void dispatchUserEvent(String s, UserEventType usereventtype)
    {
        for(Iterator iterator = volumeListeners.iterator(); iterator.hasNext(); ((VolumeViewListener)iterator.next()).onUserVolumeEvent(subscribedGroupVolume, s, usereventtype));
    }

    public GroupVolume getSubscribedGroupVolume()
    {
        return subscribedGroupVolume;
    }

    public void onAbsoluteVolumeSeekChange(String s, int i)
    {
        if(subscribedGroupVolume != null)
        {
            DeviceVolume devicevolume = subscribedGroupVolume.getDeviceVolume(s);
            if(devicevolume != null && devicevolume.isVolumeAdjustable())
            {
                devicevolume.setAbsoluteVolume(i);
                dispatchUserEvent(s, UserEventType.VolumeChange);
            }
        }
    }

    public void onRelativeVolumeSeekChange(String s, int i)
    {
        if(subscribedGroupVolume != null)
        {
            DeviceVolume devicevolume = subscribedGroupVolume.getDeviceVolume(s);
            if(devicevolume != null && devicevolume.isVolumeAdjustable())
            {
                devicevolume.setRelativeVolume(i);
                dispatchUserEvent(s, UserEventType.VolumeChange);
            }
        }
    }

    public void onStartTrackingTouch(String s)
    {
        if(subscribedGroupVolume != null)
        {
            DeviceVolume devicevolume = subscribedGroupVolume.getDeviceVolume(s);
            if(devicevolume != null)
            {
                devicevolume.beginContinuousVolumeAdjustments();
                dispatchUserEvent(s, UserEventType.StartSeek);
                stopVolumeExecutor();
            }
        }
    }

    public void onStopTrackingTouch(String s)
    {
        if(subscribedGroupVolume != null)
        {
            DeviceVolume devicevolume = subscribedGroupVolume.getDeviceVolume(s);
            if(devicevolume != null)
            {
                devicevolume.endContinuousVolumeAdjustments();
                dispatchUserEvent(s, UserEventType.EndSeek);
                stopVolumeExecutor();
            }
        }
    }

    public void onUserInteraction(String s)
    {
        if(subscribedGroupVolume != null)
            dispatchUserEvent(s, UserEventType.UserInteraction);
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        subscribedGroupVolume = groupvolume;
        Iterator iterator = volumeViews.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            VolumeView volumeview = (VolumeView)iterator.next();
            if(volumeview != null)
                volumeview.onVolumeEvent(groupvolume, volumeevent);
        } while(true);
    }

    public boolean onVolumeKeyDown(int i, String s)
    {
        SLog.e(LOG_TAG, (new StringBuilder()).append("On Volume Key Down ").append(i).toString());
        if(volumeExecutor == null)
        {
            keyHeld = i;
            volumeExecutor = new PeriodicExecutor(320L, s, i) {

                public void execute()
                {
                    VolumeViewController volumeviewcontroller = VolumeViewController.this;
                    String s1 = deviceId;
                    byte byte0;
                    if(keyCode == 24)
                        byte0 = 4;
                    else
                        byte0 = -4;
                    volumeviewcontroller.onRelativeVolumeSeekChange(s1, byte0);
                }

                public void onStart()
                {
                    super.onStart();
                    onStartTrackingTouch(deviceId);
                    VolumeViewController volumeviewcontroller = VolumeViewController.this;
                    String s1 = deviceId;
                    byte byte0;
                    if(keyCode == 24)
                        byte0 = 4;
                    else
                        byte0 = -4;
                    volumeviewcontroller.onRelativeVolumeSeekChange(s1, byte0);
                }

                public void onStop()
                {
                    super.onStop();
                    onStopTrackingTouch(deviceId);
                }

                final VolumeViewController this$0;
                final String val$deviceId;
                final int val$keyCode;

            
            {
                this$0 = VolumeViewController.this;
                deviceId = s;
                keyCode = i;
                super(final_l, final_l1, l2);
            }
            }
;
            volumeExecutor.start();
        }
        return true;
    }

    public boolean onVolumeKeyUp(int i, String s)
    {
        SLog.e(LOG_TAG, (new StringBuilder()).append("On Volume Key Up: ").append(i).toString());
        boolean flag;
        if(i == 24 || i == 25)
        {
            if(i == keyHeld)
                stopVolumeExecutor();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void removeView(VolumeView volumeview)
    {
        volumeViews.remove(volumeview);
        volumeview.setVolumeViewController(null);
    }

    public void removeVolumeViewListener(VolumeViewListener volumeviewlistener)
    {
        volumeListeners.remove(volumeviewlistener);
    }

    public void toggleMute(String s)
    {
        if(subscribedGroupVolume != null)
        {
            DeviceVolume devicevolume = subscribedGroupVolume.getDeviceVolume(s);
            if(devicevolume != null)
            {
                boolean flag;
                if(!devicevolume.isMuted())
                    flag = true;
                else
                    flag = false;
                devicevolume.setMute(flag);
                dispatchUserEvent(s, UserEventType.MuteChange);
            }
        }
    }

    private static final String LOG_TAG = com/sonos/acr/nowplaying/controllers/VolumeViewController.getSimpleName();
    public static final int VOLUME_INCREMENT = 4;
    public static final int VOLUME_TAP_DELTA = 1;
    private int keyHeld;
    private GroupVolume subscribedGroupVolume;
    private PeriodicExecutor volumeExecutor;
    private final WeakHashSet volumeListeners = new WeakHashSet();
    private final WeakHashSet volumeViews = new WeakHashSet();

}
