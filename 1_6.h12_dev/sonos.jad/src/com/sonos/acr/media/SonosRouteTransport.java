// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.media.session.SonosRouteSession;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.media:
//            SonosMediaItemTrackPositionSource, SonosRouteController, SonosMediaItem, SonosMetadata, 
//            SonosRouteProvider

public class SonosRouteTransport
    implements SonosMediaItemTrackPositionSource
{

    public SonosRouteTransport(Context context, String s, SonosRouteController sonosroutecontroller)
    {
        playbackState = SCNPPlaybackState.SC_NP_PLAYBACK_UNKNOWN;
        transportOps = new ArrayList();
        trackPositionInMillisecs = 0L;
        trackPositionTimestamp = -1L;
        reportSeekPosition = false;
        seekPositionInMillisecs = -1L;
        seekOp = null;
        expectedUris = new LinkedList();
        expectingStopped = false;
        flushExpectedUris = new Runnable() {

            public void run()
            {
                for(; expectedUris.size() > 1; expectedUris.remove());
            }

            final SonosRouteTransport this$0;

            
            {
                this$0 = SonosRouteTransport.this;
                super();
            }
        }
;
        sonosGroupId = s;
        routeController = sonosroutecontroller;
        handler = new Handler(context.getMainLooper());
    }

    private void addTransportOp(SCIOp sciop, ArrayList arraylist)
    {
        if(arraylist != null)
            arraylist.add(sciop);
        else
            transportOps.add(sciop);
    }

    private void cancelExpectingStoppedTimer()
    {
        if(expectingStoppedTimer != null)
        {
            expectingStoppedTimer.cancel();
            expectingStoppedTimer = null;
        }
    }

    private static String formatSeekTimeFromMillisecs(long l)
    {
        long l1 = l / 1000L;
        long l2 = l1 % 60L;
        long l3 = (l1 / 60L) % 60L;
        long l4 = l1 / 3600L;
        Object aobj[] = new Object[3];
        aobj[0] = Long.valueOf(l4);
        aobj[1] = Long.valueOf(l3);
        aobj[2] = Long.valueOf(l2);
        return String.format("%02d:%02d:%02d", aobj);
    }

    private long getAdjustedTransportTrackPositionInMillisecs()
    {
        long l = 0L;
        if(trackPositionTimestamp >= 0L && playbackState == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
            l = SystemClock.elapsedRealtime() - trackPositionTimestamp;
        SonosMediaItem sonosmediaitem = routeController.getCurrentItem();
        int i;
        if(sonosmediaitem == null)
            i = -1;
        else
            i = sonosmediaitem.getMetadata().getDurationInSecs();
        return Math.min(l + trackPositionInMillisecs, Math.max(0L, 1000L * (long)i));
    }

    public static ZoneGroup getZoneGroupFromRouteId(String s)
    {
        Household household = SonosApplication.getInstance().getSCLibManager().getHousehold();
        String s1 = SonosRouteProvider.getGroupIDFromRouteID(s);
        ZoneGroup zonegroup;
        if(household != null && s1 != null)
            zonegroup = household.lookupZoneGroup(s1);
        else
            zonegroup = null;
        return zonegroup;
    }

    private void removeTransportOp(SCIOp sciop, ArrayList arraylist)
    {
        if(arraylist != null)
            arraylist.remove(sciop);
        else
            transportOps.remove(sciop);
    }

    private void scheduleExpectedUriFlush()
    {
        getHandler().removeCallbacks(flushExpectedUris);
        getHandler().postDelayed(flushExpectedUris, 10000L);
    }

    private void updateTransportTrackPosition(final SCIOpCBSwigBase callback)
    {
        ZoneGroup zonegroup = getZoneGroup();
        if(zonegroup != null)
        {
            final SCIOpGetTrackPositionInfo getPosOp = zonegroup.nowPlaying.getTransport().createGetTrackPositionInfoOp();
            getPosOp._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    SLog.d("SonosRouteTransport", (new StringBuilder()).append("getPositionInfo, res=").append(i).append(", pos=").append(getPosOp.getTrackPositionInSecs()).toString());
                    removeTransportOp(getPosOp, null);
                    if(reportSeekPosition && seekOp == null)
                    {
                        reportSeekPosition = false;
                        seekPositionInMillisecs = -1L;
                    }
                    if(i == 0)
                    {
                        long l1 = getAdjustedTransportTrackPositionInMillisecs();
                        trackPositionInMillisecs = 1000L * getPosOp.getTrackPositionInSecs();
                        trackPositionTimestamp = SystemClock.elapsedRealtime();
                        long l2 = getPosOp.getTrackDurationInSecs();
                        SonosMediaItem sonosmediaitem = routeController.getCurrentItem();
                        int j;
                        if(sonosmediaitem != null)
                            j = sonosmediaitem.getMetadata().getDurationInSecs();
                        else
                            j = -1;
                        if(sonosmediaitem != null && j < 0 && l2 > 0L)
                        {
                            SLog.d("SonosRouteTransport", (new StringBuilder()).append("Updating duration=").append(l2 / 1000L).toString());
                            sonosmediaitem.getMetadata().setDurationInSecs((int)(l2 / 1000L));
                        }
                        routeController.onTransportTrackPositionUpdated(l1, trackPositionInMillisecs, getPosOp.getTrackIndex(), getPosOp.getTrackURI());
                    }
                    if(callback != null)
                        callback._operationComplete(l, i);
                }

                final SonosRouteTransport this$0;
                final SCIOpCBSwigBase val$callback;
                final SCIOpGetTrackPositionInfo val$getPosOp;

            
            {
                this$0 = SonosRouteTransport.this;
                getPosOp = sciopgettrackpositioninfo;
                callback = sciopcbswigbase;
                super();
            }
            }
);
            addTransportOp(getPosOp, null);
        }
    }

    public void addExpectedUri(String s)
    {
        SLog.d("SonosRouteTransport", (new StringBuilder()).append("Adding expected URI: '").append(s).append("'").toString());
        expectedUris.add(s);
    }

    public boolean areTransportTrackPositionUpdatesEnabled()
    {
        return true;
    }

    public void cancelAllOps()
    {
        if(transportOps.size() > 0)
            SLog.d("SonosRouteTransport", (new StringBuilder()).append("Cancelling ").append(transportOps.size()).append(" op(s)").toString());
        for(Iterator iterator = transportOps.iterator(); iterator.hasNext(); ((SCIOp)iterator.next())._cancel());
        transportOps.clear();
        seekOp = null;
    }

    public void clearTransport()
    {
        setTransportURI("", "", null, null);
    }

    public void flushAllExpectedUris()
    {
        expectedUris.clear();
    }

    public Handler getHandler()
    {
        return handler;
    }

    public SCNPPlaybackState getPlaybackState()
    {
        return playbackState;
    }

    public long getTrackPositionInMillisecs()
    {
        return getTransportTrackPositionInMillisecs();
    }

    public long getTransportTrackPositionInMillisecs()
    {
        long l;
        if(reportSeekPosition && seekPositionInMillisecs >= 0L)
        {
            SLog.d("SonosRouteTransport", (new StringBuilder()).append("Reporting last seeked position=").append(seekPositionInMillisecs).toString());
            l = seekPositionInMillisecs;
        } else
        {
            l = getAdjustedTransportTrackPositionInMillisecs();
        }
        return l;
    }

    public ZoneGroup getZoneGroup()
    {
        return getZoneGroupFromRouteId(sonosGroupId);
    }

    public boolean isExpectingStopped()
    {
        return expectingStopped;
    }

    public boolean overrideItemTrackPositionCaching()
    {
        return false;
    }

    public void seekTo(long l, final SCIOpCBSwigBase callback, final ArrayList cancelableOps)
    {
        ZoneGroup zonegroup = getZoneGroup();
        if(zonegroup == null) goto _L2; else goto _L1
_L1:
        SCINowPlayingTransport scinowplayingtransport = zonegroup.nowPlaying.getTransport();
        final String seekTimeStr = formatSeekTimeFromMillisecs(l);
        seekOp = scinowplayingtransport.createSeekOp("REL_TIME", seekTimeStr);
        reportSeekPosition = true;
        seekPositionInMillisecs = l;
        seekOp._start(new SCIOpCBSwigBase() {

            public void _operationComplete(long l1, int i)
            {
                SLog.d("SonosRouteTransport", (new StringBuilder()).append("seek to position '").append(seekTimeStr).append("' completed, res=").append(i).toString());
                if(i != 0)
                    reportSeekPosition = false;
                removeTransportOp(seekOp, cancelableOps);
                seekOp = null;
                if(callback != null)
                    callback._operationComplete(l1, i);
            }

            final SonosRouteTransport this$0;
            final SCIOpCBSwigBase val$callback;
            final ArrayList val$cancelableOps;
            final String val$seekTimeStr;

            
            {
                this$0 = SonosRouteTransport.this;
                seekTimeStr = s;
                cancelableOps = arraylist;
                callback = sciopcbswigbase;
                super();
            }
        }
);
        addTransportOp(seekOp, cancelableOps);
_L4:
        return;
_L2:
        if(callback != null)
        {
            SLog.e("SonosRouteTransport", "Cannot seek: invalid group!");
            callback._operationComplete(0L, 7000);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setExpectingStopped(boolean flag)
    {
        expectingStopped = flag;
        if(flag)
        {
            cancelExpectingStoppedTimer();
            expectingStoppedTimer = new Timer();
            expectingStoppedTimer.schedule(new TimerTask() {

                public void run()
                {
                    setExpectingStopped(false);
                }

                final SonosRouteTransport this$0;

            
            {
                this$0 = SonosRouteTransport.this;
                super();
            }
            }
, 2000L);
        } else
        {
            cancelExpectingStoppedTimer();
        }
    }

    public void setPlaybackState(SCNPPlaybackState scnpplaybackstate)
    {
        class _cls6
        {

            static final int $SwitchMap$com$sonos$sclib$SCNPPlaybackState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState = new int[SCNPPlaybackState.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_UNKNOWN.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        boolean flag;
        if(playbackState == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
            flag = true;
        else
            flag = false;
        _cls6..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scnpplaybackstate.ordinal()];
        JVM INSTR tableswitch 1 3: default 48
    //                   1 92
    //                   2 124
    //                   3 129;
           goto _L1 _L2 _L3 _L4
_L1:
        if(playbackState != scnpplaybackstate)
            SLog.d("SonosRouteTransport", (new StringBuilder()).append("state=").append(scnpplaybackstate).toString());
        playbackState = scnpplaybackstate;
        return;
_L2:
        if(trackPositionInMillisecs >= 0L && trackPositionTimestamp >= 0L && !flag)
            trackPositionTimestamp = SystemClock.elapsedRealtime();
        continue; /* Loop/switch isn't completed */
_L3:
        setExpectingStopped(false);
_L4:
        if(trackPositionInMillisecs >= 0L && trackPositionTimestamp >= 0L && flag)
        {
            trackPositionInMillisecs = trackPositionInMillisecs + (SystemClock.elapsedRealtime() - trackPositionTimestamp);
            trackPositionTimestamp = SystemClock.elapsedRealtime();
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setPlaybackStateAndUpdateTransportTrackPosition(SCNPPlaybackState scnpplaybackstate)
    {
        setPlaybackState(scnpplaybackstate);
        updateTransportTrackPosition(null);
    }

    public void setTransportURI(final String uri, String s, final SCIOpCBSwigBase callback, final ArrayList cancelableOps)
    {
        ZoneGroup zonegroup = getZoneGroup();
        if(zonegroup == null) goto _L2; else goto _L1
_L1:
        addExpectedUri(uri);
        scheduleExpectedUriFlush();
        final SCIOp setAVTUriOp = zonegroup.nowPlaying.getTransport().createSetTransportURIOp(uri, s);
        setAVTUriOp._start(new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                SLog.d("SonosRouteTransport", (new StringBuilder()).append("set transport URI to '").append(uri).append("' completed, res=").append(i).toString());
                removeTransportOp(setAVTUriOp, cancelableOps);
                if(callback != null)
                    callback._operationComplete(l, i);
            }

            final SonosRouteTransport this$0;
            final SCIOpCBSwigBase val$callback;
            final ArrayList val$cancelableOps;
            final SCIOp val$setAVTUriOp;
            final String val$uri;

            
            {
                this$0 = SonosRouteTransport.this;
                uri = s;
                setAVTUriOp = sciop;
                cancelableOps = arraylist;
                callback = sciopcbswigbase;
                super();
            }
        }
);
        addTransportOp(setAVTUriOp, cancelableOps);
        if(uri.equals(""))
            setExpectingStopped(true);
        trackPositionInMillisecs = 0L;
        trackPositionTimestamp = -1L;
_L4:
        return;
_L2:
        if(callback != null)
            callback._operationComplete(0L, 9999);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean wasUriExpectedAndUpdateExpectedURIs(String s)
    {
        if(s != null && !expectedUris.isEmpty()) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        String s1;
        if(routeController != null)
            s1 = routeController.getCurrentQueueUri();
        else
            s1 = null;
        if(expectedUris.contains(s) || !StringUtils.isEmptyOrNull(s1) && s.equals(s1))
            flag = true;
        else
            flag = false;
        if(flag)
        {
            if(expectedUris.contains(s))
                for(; !expectedUris.isEmpty() && !((String)expectedUris.peek()).equals(s); expectedUris.remove());
        } else
        if(routeController != null && routeController.getCurrentQueueId() > 0L)
        {
            long l = routeController.getCurrentQueueId();
            if(l > 0L && l == SonosRouteSession.getQueueIDFromUri(s))
            {
                flag = true;
                SLog.d("SonosRouteTransport", "queueId for new AVT URI matches current queueId.");
            }
        }
        if(!flag)
        {
            Object aobj[] = new Object[3];
            aobj[0] = s;
            aobj[1] = s1;
            aobj[2] = Integer.valueOf(expectedUris.size());
            SLog.w("SonosRouteTransport", String.format("unexpected AVT URI: uri=%s, queueUri=%s, expectedUris.size=%d", aobj));
            StringBuilder stringbuilder = new StringBuilder();
            String s2;
            for(Iterator iterator = expectedUris.iterator(); iterator.hasNext(); stringbuilder.append(s2))
            {
                s2 = (String)iterator.next();
                if(stringbuilder.length() != 0)
                    stringbuilder.append(", ");
            }

            Object aobj1[] = new Object[1];
            aobj1[0] = stringbuilder.toString();
            SLog.w("SonosRouteTransport", String.format("Expected URIs: [%s]", aobj1));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int EXPECTED_URI_QUEUE_FLUSH_TIMEOUT_IN_MILLIS = 10000;
    private static final String LOG_TAG = "SonosRouteTransport";
    private final int EXPECTING_STOPPED_TIMER_IN_MILLIS = 2000;
    private Queue expectedUris;
    private boolean expectingStopped;
    private Timer expectingStoppedTimer;
    private Runnable flushExpectedUris;
    private Handler handler;
    private SCNPPlaybackState playbackState;
    private boolean reportSeekPosition;
    SonosRouteController routeController;
    private SCIOp seekOp;
    private long seekPositionInMillisecs;
    private String sonosGroupId;
    private long trackPositionInMillisecs;
    private long trackPositionTimestamp;
    private ArrayList transportOps;





/*
    static boolean access$202(SonosRouteTransport sonosroutetransport, boolean flag)
    {
        sonosroutetransport.reportSeekPosition = flag;
        return flag;
    }

*/



/*
    static SCIOp access$302(SonosRouteTransport sonosroutetransport, SCIOp sciop)
    {
        sonosroutetransport.seekOp = sciop;
        return sciop;
    }

*/


/*
    static long access$402(SonosRouteTransport sonosroutetransport, long l)
    {
        sonosroutetransport.seekPositionInMillisecs = l;
        return l;
    }

*/




/*
    static long access$602(SonosRouteTransport sonosroutetransport, long l)
    {
        sonosroutetransport.trackPositionInMillisecs = l;
        return l;
    }

*/


/*
    static long access$702(SonosRouteTransport sonosroutetransport, long l)
    {
        sonosroutetransport.trackPositionTimestamp = l;
        return l;
    }

*/
}
