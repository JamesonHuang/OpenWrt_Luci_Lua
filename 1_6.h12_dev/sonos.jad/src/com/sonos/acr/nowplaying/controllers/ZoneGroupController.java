// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sonos.acr.nowplaying.SleepDialogHandler;
import com.sonos.acr.nowplaying.views.TransportView;
import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.WeakHashSet;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.nowplaying.controllers:
//            TransportViewController, SourceViewController, VolumeViewController, VolumeView

public class ZoneGroupController
    implements android.view.ViewGroup.OnHierarchyChangeListener, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener
{
    public static final class Controller extends Enum
    {

        public static Controller valueOf(String s)
        {
            return (Controller)Enum.valueOf(com/sonos/acr/nowplaying/controllers/ZoneGroupController$Controller, s);
        }

        public static Controller[] values()
        {
            return (Controller[])$VALUES.clone();
        }

        private static final Controller $VALUES[];
        public static final Controller npsrc;
        public static final Controller nptp;
        public static final Controller zgvol;

        static 
        {
            nptp = new Controller("nptp", 0);
            npsrc = new Controller("npsrc", 1);
            zgvol = new Controller("zgvol", 2);
            Controller acontroller[] = new Controller[3];
            acontroller[0] = nptp;
            acontroller[1] = npsrc;
            acontroller[2] = zgvol;
            $VALUES = acontroller;
        }

        private Controller(String s, int i)
        {
            super(s, i);
        }
    }


    public ZoneGroupController(Context context1)
    {
        context = context1;
        transportViewController = new TransportViewController(context1);
        sourceViewController = new SourceViewController(context1);
        nowPlayingListeners.add(transportViewController);
        nowPlayingListeners.add(sourceViewController);
    }

    private Controller getController(String s)
    {
        return Controller.valueOf(s);
    }

    public void addNowPlayingListener(com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener nowplayinglistener)
    {
        nowPlayingListeners.add(nowplayinglistener);
    }

    public SleepDialogHandler getSleepTimerHandler()
    {
        return sleepTimerHandler;
    }

    public SourceViewController getSourceViewController()
    {
        return sourceViewController;
    }

    public TransportViewController getTransportViewController()
    {
        return transportViewController;
    }

    public VolumeViewController getVolumeViewController()
    {
        return volumeViewController;
    }

    public void ignoreView(View view)
    {
        if(!(view instanceof TransportView)) goto _L2; else goto _L1
_L1:
        transportViewController.removeView((TransportView)view);
_L4:
        return;
_L2:
        if(!(view instanceof VolumeView))
            break; /* Loop/switch isn't completed */
        volumeViewController.removeView((VolumeView)view);
        if(true) goto _L4; else goto _L3
_L3:
        Object obj = view.getTag();
        if(obj == null) goto _L4; else goto _L5
_L5:
        String as[] = (new StringBuilder()).append("").append(obj).toString().split(":");
        if(as.length <= 0) goto _L4; else goto _L6
_L6:
        class _cls1
        {

            static final int $SwitchMap$com$sonos$acr$nowplaying$controllers$ZoneGroupController$Controller[];

            static 
            {
                $SwitchMap$com$sonos$acr$nowplaying$controllers$ZoneGroupController$Controller = new int[Controller.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$ZoneGroupController$Controller[Controller.npsrc.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$acr$nowplaying$controllers$ZoneGroupController$Controller[Controller.nptp.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.com.sonos.acr.nowplaying.controllers.ZoneGroupController.Controller[getController(as[0]).ordinal()])
        {
        case 1: // '\001'
            sourceViewController.ignoreView(view);
            break;

        case 2: // '\002'
            transportViewController.ignoreView(view);
            break;
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    public void ignoreViewHierarchy(ViewGroup viewgroup)
    {
        if(hierarchyViews.remove(viewgroup))
        {
            viewgroup.setOnHierarchyChangeListener(null);
            ignoreView(viewgroup);
            for(int i = 0; i < viewgroup.getChildCount(); i++)
                onChildViewRemoved(viewgroup, viewgroup.getChildAt(i));

        }
    }

    public void observeView(View view)
    {
        if(!(view instanceof TransportView)) goto _L2; else goto _L1
_L1:
        transportViewController.addView((TransportView)view);
_L4:
        return;
_L2:
        if(!(view instanceof VolumeView))
            break; /* Loop/switch isn't completed */
        volumeViewController.addView((VolumeView)view);
        if(true) goto _L4; else goto _L3
_L3:
        Object obj = view.getTag();
        if(obj == null) goto _L4; else goto _L5
_L5:
        String as[] = (new StringBuilder()).append("").append(obj).toString().split(":");
        if(as.length <= 0) goto _L4; else goto _L6
_L6:
        switch(_cls1..SwitchMap.com.sonos.acr.nowplaying.controllers.ZoneGroupController.Controller[getController(as[0]).ordinal()])
        {
        case 1: // '\001'
            sourceViewController.observeView(view);
            break;

        case 2: // '\002'
            transportViewController.observeView(view);
            break;
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    public void observeViewHierarchy(ViewGroup viewgroup)
    {
        if(hierarchyViews.add(viewgroup))
        {
            viewgroup.setOnHierarchyChangeListener(this);
            observeView(viewgroup);
            for(int i = 0; i < viewgroup.getChildCount(); i++)
                onChildViewAdded(viewgroup, viewgroup.getChildAt(i));

        }
    }

    public void onChildViewAdded(View view, View view1)
    {
        if(view1 instanceof ViewGroup)
            observeViewHierarchy((ViewGroup)view1);
        else
            observeView(view1);
    }

    public void onChildViewRemoved(View view, View view1)
    {
        if(view1 instanceof ViewGroup)
            ignoreViewHierarchy((ViewGroup)view1);
        else
            ignoreView(view1);
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        Iterator iterator = nowPlayingListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener nowplayinglistener = (com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener)iterator.next();
            if(nowplayinglistener != null)
                nowplayinglistener.onNowPlayingEvent(nowplaying, nowplayevent);
            if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnSleepTimerGenerationChanged)
                sleepTimerHandler.pollTime();
        } while(true);
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        volumeViewController.onVolumeEvent(groupvolume, volumeevent);
    }

    public void removeNowPlayingListener(com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener nowplayinglistener)
    {
        nowPlayingListeners.remove(nowplayinglistener);
    }

    public static final String DELIMITER = ":";
    public static final String LOG_TAG = com/sonos/acr/nowplaying/controllers/ZoneGroupController.getSimpleName();
    private Context context;
    private final WeakHashSet hierarchyViews = new WeakHashSet();
    private final WeakHashSet nowPlayingListeners = new WeakHashSet();
    private final SleepDialogHandler sleepTimerHandler = new SleepDialogHandler();
    private final SourceViewController sourceViewController;
    private final TransportViewController transportViewController;
    private final VolumeViewController volumeViewController = new VolumeViewController();

}
