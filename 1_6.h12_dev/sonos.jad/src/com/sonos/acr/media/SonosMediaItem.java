// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.media.MediaItemStatus;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import java.util.*;

// Referenced classes of package com.sonos.acr.media:
//            SonosMetadata, SonosMediaItemTrackPositionSource, MRPLog, SonosMediaItemListener

public class SonosMediaItem
{

    SonosMediaItem(Uri uri1, Bundle bundle, String s, Bundle bundle1, PendingIntent pendingintent, Context context1, String s1, 
            long l, SonosMediaItemTrackPositionSource sonosmediaitemtrackpositionsource)
    {
        playbackState = 0;
        listeners = new ArrayList();
        lastHttpStatusCode = 0;
        trackPositionSources = new LinkedList();
        useCachedTrackPosition = true;
        hasReceivedTransportTrackPositionUpdates = false;
        id = generateId();
        sessionId = s1;
        metadata = new SonosMetadata(bundle, s, bundle1);
        uri = uri1;
        statusUpdatePI = pendingintent;
        if(l < 0L)
        {
            SLog.e("SonosMediaItem", "Invalid initial track position!");
            cachedTrackPositionInMillisecs = 0L;
        } else
        {
            cachedTrackPositionInMillisecs = l;
        }
        if(sonosmediaitemtrackpositionsource == null)
            SLog.e("SonosMediaItem", "Must specify transport track position source!");
        else
            trackPositionSources.add(sonosmediaitemtrackpositionsource);
        context = context1;
    }

    private String generateId()
    {
        return UUID.randomUUID().toString();
    }

    private SonosMediaItemTrackPositionSource getTrackPositionSource()
    {
        return (SonosMediaItemTrackPositionSource)trackPositionSources.peekFirst();
    }

    protected static boolean isTerminalPlaybackState(int i)
    {
        boolean flag;
        if(i == 5 || i == 7 || i == 6 || i == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String stringFromPlaybackState(int i)
    {
        i;
        JVM INSTR tableswitch 0 7: default 48
    //                   0 88
    //                   1 94
    //                   2 82
    //                   3 52
    //                   4 70
    //                   5 58
    //                   6 76
    //                   7 64;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        String s = null;
_L11:
        return s;
_L5:
        s = "BUFFERING";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "CANCELED";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "ERROR";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "FINISHED";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "INVALIDATED";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "PAUSED";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "PENDING";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "PLAYING";
        if(true) goto _L11; else goto _L10
_L10:
    }

    public void addListener(SonosMediaItemListener sonosmediaitemlistener)
    {
        if(!listeners.contains(sonosmediaitemlistener))
            listeners.add(sonosmediaitemlistener);
    }

    public void addTrackPositionSource(SonosMediaItemTrackPositionSource sonosmediaitemtrackpositionsource)
    {
        trackPositionSources.addFirst(sonosmediaitemtrackpositionsource);
    }

    public void cacheTrackPosition(long l)
    {
        useCachedTrackPosition = true;
        cachedTrackPositionInMillisecs = l;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null || !(obj instanceof SonosMediaItem))
            flag = false;
        else
            flag = ((SonosMediaItem)obj).getId().equals(id);
        return flag;
    }

    public long getCachedTrackPositionInMillisecs()
    {
        return cachedTrackPositionInMillisecs;
    }

    public boolean getHasReceivedTransportTrackPositionUpdates()
    {
        return hasReceivedTransportTrackPositionUpdates;
    }

    public String getId()
    {
        return id;
    }

    public int getLastHttpStatusCode()
    {
        return lastHttpStatusCode;
    }

    public MediaItemStatus getLastStatusUpdate()
    {
        return lastStatusUpdate;
    }

    public SonosMetadata getMetadata()
    {
        return metadata;
    }

    public int getPlaybackState()
    {
        return playbackState;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public long getTrackPositionInMillisecs()
    {
        SonosMediaItemTrackPositionSource sonosmediaitemtrackpositionsource = getTrackPositionSource();
        long l;
        if(sonosmediaitemtrackpositionsource == null || useCachedTrackPosition && !sonosmediaitemtrackpositionsource.overrideItemTrackPositionCaching())
            l = cachedTrackPositionInMillisecs;
        else
            l = sonosmediaitemtrackpositionsource.getTrackPositionInMillisecs();
        return l;
    }

    public Uri getUri()
    {
        return uri;
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public boolean isInTerminalPlaybackState()
    {
        return isTerminalPlaybackState(playbackState);
    }

    protected void onTransportTrackPositionUpdated(long l, long l1, int i)
    {
        if(useCachedTrackPosition)
            useCachedTrackPosition = false;
        hasReceivedTransportTrackPositionUpdates = true;
        SonosMediaItemTrackPositionSource sonosmediaitemtrackpositionsource;
        if(l1 == 0L && l > 0L)
            cachedTrackPositionInMillisecs = l;
        else
            cachedTrackPositionInMillisecs = l1;
        sonosmediaitemtrackpositionsource = getTrackPositionSource();
        if(sonosmediaitemtrackpositionsource == null || sonosmediaitemtrackpositionsource.areTransportTrackPositionUpdatesEnabled())
        {
            int j = (int)(l1 / 1000L);
            int k = getMetadata().getDurationInSecs();
            updateAndReport(i, (new StringBuilder()).append("Reporting track position at ").append(j).append(" / ").append(k).append(" secs").toString(), 0, null, l1);
        }
    }

    public void removeListener(SonosMediaItemListener sonosmediaitemlistener)
    {
        if(listeners.contains(sonosmediaitemlistener))
            listeners.remove(sonosmediaitemlistener);
    }

    public void removeTrackPositionSource(SonosMediaItemTrackPositionSource sonosmediaitemtrackpositionsource)
    {
        trackPositionSources.remove(sonosmediaitemtrackpositionsource);
    }

    public void setSessionId(String s)
    {
        sessionId = s;
    }

    public void updateAndReport(int i, String s)
    {
        updateAndReport(i, s, 0, null, -1L);
    }

    public void updateAndReport(int i, String s, int j, Bundle bundle, long l)
    {
        boolean flag;
        flag = false;
        lastHttpStatusCode = j;
        if(!isTerminalPlaybackState(playbackState)) goto _L2; else goto _L1
_L1:
        SLog.d("SonosMediaItem", (new StringBuilder()).append("Already in terminal state, status update dropped for item ").append(getId()).append(": '").append(s).append("'").toString());
_L4:
        return;
_L2:
        if(playbackState != i)
        {
            flag = true;
            playbackState = i;
        }
        if(statusUpdatePI != null)
        {
            Intent intent = new Intent();
            android.support.v7.media.MediaItemStatus.Builder builder = new android.support.v7.media.MediaItemStatus.Builder(i);
            if(l >= 0L)
                builder.setContentPosition(l);
            if(metadata.getDurationInSecs() >= 0)
                builder.setContentDuration(1000 * metadata.getDurationInSecs());
            if(j >= 400)
            {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("android.media.status.extra.HTTP_STATUS_CODE", j);
                bundle1.putBundle("android.media.status.extra.HTTP_RESPONSE_HEADERS", bundle);
                builder.setExtras(bundle1);
            }
            lastStatusUpdate = builder.build();
            MRPLog.i("SonosMediaItem", (new StringBuilder()).append("updateAndReport: status=").append(lastStatusUpdate).append(", itemID=").append(getId()).append(", sessionID=").append(getSessionId()).toString());
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.media.intent.extra.SESSION_ID", getSessionId());
            bundle2.putString("android.media.intent.extra.ITEM_ID", getId());
            bundle2.putBundle("android.media.intent.extra.ITEM_STATUS", lastStatusUpdate.asBundle());
            intent.putExtras(bundle2);
            try
            {
                statusUpdatePI.send(context, 0, intent);
            }
            catch(android.app.PendingIntent.CanceledException canceledexception)
            {
                canceledexception.printStackTrace();
            }
            if(isTerminalPlaybackState(i))
                statusUpdatePI = null;
            if(!StringUtils.isEmptyOrNull(s))
            {
                String s1 = (new StringBuilder()).append("updateAndReport: ").append(s).append(", itemId=").append(getId()).toString();
                Iterator iterator;
                if(i == 7)
                    SLog.e("SonosMediaItem", s1);
                else
                    SLog.d("SonosMediaItem", s1);
            }
        }
        if(flag)
        {
            iterator = listeners.iterator();
            while(iterator.hasNext()) 
                ((SonosMediaItemListener)iterator.next()).onPlaybackStateChanged(this);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String LOG_TAG = "SonosMediaItem";
    private long cachedTrackPositionInMillisecs;
    private Context context;
    private boolean hasReceivedTransportTrackPositionUpdates;
    private String id;
    private int lastHttpStatusCode;
    private MediaItemStatus lastStatusUpdate;
    private ArrayList listeners;
    private SonosMetadata metadata;
    private int playbackState;
    private String sessionId;
    private PendingIntent statusUpdatePI;
    private LinkedList trackPositionSources;
    private Uri uri;
    private boolean useCachedTrackPosition;
}
