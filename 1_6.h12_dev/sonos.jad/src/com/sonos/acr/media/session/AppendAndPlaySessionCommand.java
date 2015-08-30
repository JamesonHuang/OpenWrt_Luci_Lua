// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import android.net.Uri;
import com.sonos.acr.media.*;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class AppendAndPlaySessionCommand extends SonosSessionCommand
{

    public AppendAndPlaySessionCommand(SonosRouteSession sonosroutesession, SonosMediaItem sonosmediaitem, boolean flag, long l, SCIOpCBSwigBase sciopcbswigbase, boolean flag1, 
            boolean flag2)
    {
        boolean flag3 = true;
        super(SonosSessionCommand.SessionCommandType.SCMD_ENQUEUESEEKANDPLAY, sonosroutesession, sciopcbswigbase);
        items = new ArrayList();
        callbacks = new HashMap();
        queueModelUpdated = false;
        doSeek = false;
        masterItemId = sonosmediaitem.getId();
        items.add(sonosmediaitem);
        callbacks.put(masterItemId, sciopcbswigbase);
        playNow = flag;
        contentPositionInMillis = l;
        attemptResyncOnFail = flag1;
        firstAppendInQueue = flag2;
        if(!playNow && sonosroutesession.getNumItemsInAppQueue() != flag3 || l / 1000L <= 0L)
            flag3 = false;
        doSeek = flag3;
        if(doSeek)
            registerAsTrackPositionSourceForItem(sonosmediaitem);
    }

    private void appendItemsToQueueSeekAndPlay(final List items, final SCIOpCBSwigBase callback)
    {
        if(session.getPlayQueue() != null && session.getPlayQueue().isValid()) goto _L2; else goto _L1
_L1:
        SLog.e("SonosRouteSession", "Cannot append to queue: no private queue!");
        if(callback != null)
            callback._operationComplete(0L, 7001);
_L4:
        return;
_L2:
        SCIPlayQueueMgr sciplayqueuemgr = session.getPlayQueueMgr();
        if(sciplayqueuemgr != null)
        {
            SCIStringArray scistringarray = sclib.createSCStringArray();
            SCIStringArray scistringarray1 = sclib.createSCStringArray();
            SonosMediaItem sonosmediaitem;
            for(Iterator iterator = items.iterator(); iterator.hasNext(); scistringarray1.append(sonosmediaitem.getMetadata().getDIDLLite(false)))
            {
                sonosmediaitem = (SonosMediaItem)iterator.next();
                scistringarray.append(sonosmediaitem.getUri().toString());
            }

            final SCIOpCBSwigBase seekCompleteCallback = new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i != 0)
                        SLog.w("SonosRouteSession", (new StringBuilder()).append("seek failed, res=").append(i).toString());
                    if(!playNow) goto _L2; else goto _L1
_L1:
                    runPlayOp(callback);
_L4:
                    return;
_L2:
                    if(callback != null)
                        callback._operationComplete(l, i);
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final AppendAndPlaySessionCommand this$0;
                final SCIOpCBSwigBase val$callback;

            
            {
                this$0 = AppendAndPlaySessionCommand.this;
                callback = sciopcbswigbase;
                super();
            }
            }
;
            final SCIOpAddTracksToQueue appendTracksOp = sciplayqueuemgr.createAppendItemsOp(scistringarray, scistringarray1, false, false, session.getLastUpdateId());
            appendTracksOp._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    removeOp(appendTracksOp);
                    long l1 = appendTracksOp.getNumTracksAdded();
                    SLog.d("SonosRouteSession", (new StringBuilder()).append("Append tracks to private queue completed, res=").append(i).append(", numTracksAdded=").append(l1).toString());
                    if(i != 0) goto _L2; else goto _L1
_L1:
                    session.setLastUpdateId(appendTracksOp.getNewUpdateID());
                    if(!doSeek) goto _L4; else goto _L3
_L3:
                    session.transport.seekTo(contentPositionInMillis, seekCompleteCallback, getOps());
_L6:
                    return;
_L4:
                    if(playNow)
                        runPlayOp(callback);
                    else
                    if(callback != null)
                        callback._operationComplete(l, i);
                    continue; /* Loop/switch isn't completed */
_L2:
                    if(attemptResyncOnFail)
                    {
                        if(i == 1028)
                        {
                            SLog.w("SonosRouteSession", "received UPNP_RESULT_STALE_OBJECT, resyncing queue");
                            session.resync(ResyncSessionCommand.ResyncReason.REASON_QUEUE_MODEL_MISMATCH);
                        } else
                        if(i == 1001 || i == 1002)
                        {
                            SLog.w("SonosRouteSession", (new StringBuilder()).append("received network error ").append(i).append(", resyncing queue").toString());
                            session.resync(ResyncSessionCommand.ResyncReason.REASON_TIMEOUT_ERROR);
                        } else
                        {
                            Iterator iterator2 = items.iterator();
                            while(iterator2.hasNext()) 
                                ((SonosMediaItem)iterator2.next()).updateAndReport(7, (new StringBuilder()).append("Error ").append(i).append(" adding track(s) to queue").toString());
                        }
                    } else
                    {
                        Iterator iterator1 = items.iterator();
                        while(iterator1.hasNext()) 
                            ((SonosMediaItem)iterator1.next()).updateAndReport(7, (new StringBuilder()).append("Error ").append(i).append(" adding track(s) to queue").toString());
                    }
                    if(callback != null)
                        callback._operationComplete(l, i);
                    if(true) goto _L6; else goto _L5
_L5:
                }

                final AppendAndPlaySessionCommand this$0;
                final SCIOpAddTracksToQueue val$appendTracksOp;
                final SCIOpCBSwigBase val$callback;
                final List val$items;
                final SCIOpCBSwigBase val$seekCompleteCallback;

            
            {
                this$0 = AppendAndPlaySessionCommand.this;
                appendTracksOp = sciopaddtrackstoqueue;
                seekCompleteCallback = sciopcbswigbase;
                callback = sciopcbswigbase1;
                items = list;
                super();
            }
            }
);
            addOp(appendTracksOp);
        } else
        {
            SLog.e("SonosRouteSession", "Cannot append to queue: no private queue manager!");
            if(callback != null)
                callback._operationComplete(0L, 7002);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void onMerged()
    {
        items.clear();
        callbacks.clear();
    }

    private void runPlayOp(final SCIOpCBSwigBase callback)
    {
        ZoneGroup zonegroup = session.getZoneGroup();
        if(zonegroup != null) goto _L2; else goto _L1
_L1:
        SLog.e("SonosRouteSession", "AppendAndPlaySessionCommand: group is null!");
        if(callback == null) goto _L2; else goto _L3
_L3:
        callback._operationComplete(0L, 7000);
_L5:
        return;
_L2:
        final SCIOp playOp = zonegroup.nowPlaying.getTransport().createPlayOp();
        playOp._start(new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                removeSimpleOp(playOp);
                if(i != 0)
                    SLog.w("SonosRouteSession", (new StringBuilder()).append("play failed, res=").append(i).toString());
                if(callback != null)
                    callback._operationComplete(l, i);
            }

            final AppendAndPlaySessionCommand this$0;
            final SCIOpCBSwigBase val$callback;
            final SCIOp val$playOp;

            
            {
                this$0 = AppendAndPlaySessionCommand.this;
                playOp = sciop;
                callback = sciopcbswigbase;
                super();
            }
        }
);
        addSimpleOp(playOp);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public long getTrackPositionInMillisecs()
    {
        long l;
        if(doSeek)
            l = contentPositionInMillis;
        else
            l = -1L;
        return l;
    }

    protected boolean isFirstAppendInQueue()
    {
        return firstAppendInQueue;
    }

    protected void mergeAppendCommand(AppendAndPlaySessionCommand appendandplaysessioncommand)
    {
        if(!$assertionsDisabled && firstAppendInQueue)
        {
            throw new AssertionError();
        } else
        {
            items.addAll(appendandplaysessioncommand.items);
            callbacks.putAll(appendandplaysessioncommand.callbacks);
            appendandplaysessioncommand.onMerged();
            return;
        }
    }

    public void run()
    {
        appendItemsToQueueSeekAndPlay(items, new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                Iterator iterator;
                if(i == 0)
                    SLog.d("SonosRouteSession", (new StringBuilder()).append("appendItemToQueue succeeded, res=").append(i).toString());
                else
                    SLog.e("SonosRouteSession", (new StringBuilder()).append("appendItemToQueue failed, res=").append(i).toString());
                callbacks.remove(masterItemId);
                completeSessionCommand(l, i);
                iterator = items.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    String s = ((SonosMediaItem)iterator.next()).getId();
                    SCIOpCBSwigBase sciopcbswigbase = (SCIOpCBSwigBase)callbacks.get(s);
                    if(sciopcbswigbase != null)
                        sciopcbswigbase._operationComplete(l, i);
                } while(true);
            }

            final AppendAndPlaySessionCommand this$0;

            
            {
                this$0 = AppendAndPlaySessionCommand.this;
                super();
            }
        }
);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AppendAndPlaySessionCommand [items=").append(items).append(", playNow=").append(playNow).append(", contentPositionMillis=").append(contentPositionInMillis).append(", queueModelUpdated=").append(queueModelUpdated).append(", attemptRetryOnFail=").append(attemptResyncOnFail).append("]").toString();
    }

    protected void updateQueueModelInSession()
    {
        if(playNow)
            session.setSonosStatePlaying(true);
        if(!queueModelUpdated)
        {
            SonosMediaItem sonosmediaitem;
            for(Iterator iterator = items.iterator(); iterator.hasNext(); session.addItemToQueueModel(sonosmediaitem))
                sonosmediaitem = (SonosMediaItem)iterator.next();

            queueModelUpdated = true;
        }
    }

    static final boolean $assertionsDisabled;
    private final boolean attemptResyncOnFail;
    private Map callbacks;
    private boolean doSeek;
    private final boolean firstAppendInQueue;
    private List items;
    String masterItemId;
    private final boolean playNow;
    private boolean queueModelUpdated;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/media/session/AppendAndPlaySessionCommand.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }






}
