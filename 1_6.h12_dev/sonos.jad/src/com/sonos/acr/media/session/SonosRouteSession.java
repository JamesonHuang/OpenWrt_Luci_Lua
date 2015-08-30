// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.media.MediaSessionStatus;
import com.sonos.acr.media.*;
import com.sonos.acr.sclib.sinks.PrivateQueueEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionQueueModel, SonosSessionCommand, RemoveItemSessionCommand, AppendAndPlaySessionCommand, 
//            AttachQueueCommand, ClearAllSessionCommand, CreateSessionCommand, PauseSessionCommand, 
//            PlaySessionCommand, ResyncSessionCommand, SeekSessionCommand

public class SonosRouteSession
    implements com.sonos.acr.sclib.sinks.PrivateQueueEventSink.QueueListener, SonosMediaItemListener
{

    public SonosRouteSession(Context context1, String s, SonosRouteTransport sonosroutetransport, String s1, PendingIntent pendingintent)
    {
        sonosQueueModel = new SonosSessionQueueModel(false);
        appQueueModel = new SonosSessionQueueModel(true);
        isDiscarded = false;
        playQueue = null;
        playQueueMgr = null;
        privateQueueEventSink = null;
        queueUri = "x-rincon-queue:INVALID";
        sessionCommands = new LinkedList();
        currentSessionCommand = null;
        newSonosSession = true;
        queueHasBeenCleared = true;
        sessionState = 0;
        sessionId = generateId();
        queueOwnerId = (new StringBuilder()).append("SonosMRP:").append(s1).toString();
        sonosGroupId = s;
        transport = sonosroutetransport;
        clientId = s1;
        statusReceiver = pendingintent;
        handler = new Handler(context1.getMainLooper());
        context = context1;
        SLog.d("SonosRouteSession", (new StringBuilder()).append("Creating new session with Id=").append(sessionId).toString());
    }

    private void cancelAllCommands()
    {
        if(currentSessionCommand != null)
            currentSessionCommand.cancel();
        if(pendingRemoveItemSessionCommand != null)
            pendingRemoveItemSessionCommand.cancel();
        for(Iterator iterator = sessionCommands.iterator(); iterator.hasNext(); ((SonosSessionCommand)iterator.next()).cancel());
        sessionCommands.clear();
        currentSessionCommand = null;
        pendingRemoveItemSessionCommand = null;
    }

    private void clearCommandQueue()
    {
        if(!sessionCommands.isEmpty())
        {
            for(ListIterator listiterator = sessionCommands.listIterator(); listiterator.hasNext();)
            {
                SonosSessionCommand sonossessioncommand = (SonosSessionCommand)listiterator.next();
                if(sonossessioncommand.getType().shouldRemoveOnClearQueue())
                    listiterator.remove();
                else
                    SLog.d("SonosRouteSession", (new StringBuilder()).append("Not clearing ").append(sonossessioncommand.getType()).append(" from queue.").toString());
            }

        }
        queueHasBeenCleared = true;
    }

    private String generateId()
    {
        return UUID.randomUUID().toString();
    }

    public static long getQueueIDFromUri(String s)
    {
        if(!s.startsWith("x-rincon-queue:") || !s.contains("#")) goto _L2; else goto _L1
_L1:
        String as[] = s.split("#");
        if(as.length != 2) goto _L2; else goto _L3
_L3:
        long l;
        long l1;
        try
        {
            l1 = Long.parseLong(as[1]);
        }
        catch(NumberFormatException numberformatexception)
        {
            l = -1L;
            continue; /* Loop/switch isn't completed */
        }
        l = l1;
_L5:
        return l;
_L2:
        l = -1L;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void invalidateSession()
    {
        for(Iterator iterator = sonosQueueModel.getItems().iterator(); iterator.hasNext(); ((SonosMediaItem)iterator.next()).updateAndReport(6, "Queue invalidated"));
        setAppStatePlaying(false);
        updateAndReport(2, "Queue invalidated");
    }

    private void scheduleSessionCommand(SonosSessionCommand sonossessioncommand)
    {
        boolean flag;
        SonosSessionCommand.SessionCommandType sessioncommandtype;
        boolean flag1;
        flag = true;
        sessioncommandtype = sonossessioncommand.getType();
        flag1 = true;
        class _cls5
        {

            static final int $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[];

            static 
            {
                $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType = new int[SonosSessionCommand.SessionCommandType.values().length];
                NoSuchFieldError nosuchfielderror8;
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_ENQUEUESEEKANDPLAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_CLEARALL.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_CREATESESSION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_ATTACHQUEUE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_PAUSE.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_PLAY.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_REMOVE.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_RESYNC.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                $SwitchMap$com$sonos$acr$media$session$SonosSessionCommand$SessionCommandType[SonosSessionCommand.SessionCommandType.SCMD_SEEK.ordinal()] = 9;
_L2:
                return;
                nosuchfielderror8;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls5..SwitchMap.com.sonos.acr.media.session.SonosSessionCommand.SessionCommandType[sessioncommandtype.ordinal()];
        JVM INSTR tableswitch 1 2: default 40
    //                   1 65
    //                   2 152;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_152;
_L4:
        boolean flag2;
        AppendAndPlaySessionCommand appendandplaysessioncommand;
        if(flag1)
            if(!sessioncommandtype.insertAtFrontOfQueue())
                sessionCommands.addLast(sonossessioncommand);
            else
                sessionCommands.addFirst(sonossessioncommand);
        processSessionCommands();
        return;
_L2:
        if(!sessionCommands.isEmpty() && ((SonosSessionCommand)sessionCommands.getLast()).getType() == SonosSessionCommand.SessionCommandType.SCMD_ENQUEUESEEKANDPLAY)
            flag2 = flag;
        else
            flag2 = false;
        if(flag2)
        {
            appendandplaysessioncommand = (AppendAndPlaySessionCommand)sessionCommands.getLast();
            if(appendandplaysessioncommand.isFirstAppendInQueue())
                flag = false;
            if(flag)
            {
                appendandplaysessioncommand.mergeAppendCommand((AppendAndPlaySessionCommand)sonossessioncommand);
                flag1 = false;
            }
        }
          goto _L4
        clearCommandQueue();
          goto _L4
    }

    private void unsubscribeFromQueue()
    {
        if(privateQueueEventSink != null)
        {
            privateQueueEventSink.removeListener(this);
            privateQueueEventSink = null;
        }
    }

    protected void addItemToQueueModel(SonosMediaItem sonosmediaitem)
    {
        sonosmediaitem.addListener(this);
        sonosQueueModel.appendItem(sonosmediaitem);
    }

    public void appendItemToQueueAndPlay(SonosMediaItem sonosmediaitem, boolean flag, long l, final SCIOpCBSwigBase callback)
    {
        final boolean attemptResyncOnFail;
        boolean flag1;
        boolean flag2;
        if(!newSonosSession)
            attemptResyncOnFail = true;
        else
            attemptResyncOnFail = false;
        appQueueModel.appendItem(sonosmediaitem);
        if(appQueueModel.getSize() == 1)
            flag1 = true;
        else
            flag1 = false;
        if(flag || flag1 && isAppStatePlaying())
            flag2 = true;
        else
            flag2 = false;
        if(flag2)
            setAppStatePlaying(true);
        scheduleSessionCommand(new AppendAndPlaySessionCommand(this, sonosmediaitem, flag2, l, new SCIOpCBSwigBase() {

            public void _operationComplete(long l1, int i)
            {
                if(i != 0 && !attemptResyncOnFail)
                    cancelAllCommands();
                if(callback != null)
                    callback._operationComplete(l1, i);
            }

            final SonosRouteSession this$0;
            final boolean val$attemptResyncOnFail;
            final SCIOpCBSwigBase val$callback;

            
            {
                this$0 = SonosRouteSession.this;
                attemptResyncOnFail = flag;
                callback = sciopcbswigbase;
                super();
            }
        }
, attemptResyncOnFail, queueHasBeenCleared));
        if(pendingRemoveItemSessionCommand != null)
        {
            scheduleSessionCommand(pendingRemoveItemSessionCommand);
            pendingRemoveItemSessionCommand = null;
        }
        queueHasBeenCleared = false;
        newSonosSession = false;
    }

    public void attachQueue(SCIOpCBSwigBase sciopcbswigbase)
    {
        scheduleSessionCommand(new AttachQueueCommand(this, sciopcbswigbase));
    }

    protected void cancelAllItems()
    {
        for(Iterator iterator = sonosQueueModel.getItems().iterator(); iterator.hasNext(); ((SonosMediaItem)iterator.next()).updateAndReport(5, "Queue cleared"));
    }

    protected void cancelAndRemoveAllItemsInQueueModel()
    {
        cancelAllItems();
        sonosQueueModel.clear();
    }

    protected void clearPlayQueue()
    {
        playQueue = null;
        playQueueMgr = null;
        queueUri = "";
        SLog.d("SonosRouteSession", "Queue discarded");
    }

    public void clearQueueIfNecessary()
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(getNumItemsInQueue() > 0)
            flag = true;
        else
            flag = false;
        if(sessionCommands.size() > 0)
            flag1 = true;
        else
            flag1 = false;
        if(currentSessionCommand != null && currentSessionCommand.getType() == SonosSessionCommand.SessionCommandType.SCMD_ENQUEUESEEKANDPLAY)
            flag2 = true;
        else
            flag2 = false;
        if(flag || flag1 || flag2)
        {
            scheduleSessionCommand(new ClearAllSessionCommand(this, null));
            appQueueModel.clear();
        }
        setAppStatePlaying(true);
    }

    protected void clearSessionCommand(SonosSessionCommand sonossessioncommand)
    {
        if(sonossessioncommand == currentSessionCommand)
            currentSessionCommand = null;
        else
            SLog.e("SonosRouteSession", "Attempting to clear invalid command!");
    }

    public void createSonosSessionIfNecessary(final SCIOpCBSwigBase callback)
    {
        boolean flag = true;
        if(isQueueValid())
        {
            SLog.d("SonosRouteSession", "Sonos session already exists, not attempting to create another");
            flag = false;
        } else
        if(currentSessionCommand != null && currentSessionCommand.getType() == SonosSessionCommand.SessionCommandType.SCMD_CREATESESSION)
        {
            SLog.d("SonosRouteSession", "Already executing CreateSessionCommand, not scheduling another");
            flag = false;
        } else
        if(!sessionCommands.isEmpty() && ((SonosSessionCommand)sessionCommands.getFirst()).getType() == SonosSessionCommand.SessionCommandType.SCMD_CREATESESSION)
        {
            SLog.d("SonosRouteSession", "CreateSessionCommand already scheduled, not scheduling another");
            flag = false;
        }
        if(flag)
        {
            SLog.d("SonosRouteSession", "Scheduling new CreateSessionCommand");
            scheduleSessionCommand(new CreateSessionCommand(this, new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i != 0)
                    {
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("session creation failed with res=").append(i).append(", canceling pending session commands").toString());
                        cancelAllCommands();
                    }
                    if(callback != null)
                        callback._operationComplete(l, i);
                }

                final SonosRouteSession this$0;
                final SCIOpCBSwigBase val$callback;

            
            {
                this$0 = SonosRouteSession.this;
                callback = sciopcbswigbase;
                super();
            }
            }
));
        } else
        {
            callback._operationComplete(0L, 0);
        }
    }

    public void discard()
    {
        SLog.d("SonosRouteSession", (new StringBuilder()).append("Discarding session with Id=").append(sessionId).toString());
        cancelAllCommands();
        transport.cancelAllOps();
        invalidateSession();
        for(Iterator iterator = sonosQueueModel.getItems().iterator(); iterator.hasNext(); ((SonosMediaItem)iterator.next()).removeListener(this));
        isDiscarded = true;
        unsubscribeFromQueue();
        clearPlayQueue();
        playQueueMgr = null;
        sessionId = null;
    }

    public String getClientId()
    {
        return clientId;
    }

    public SonosMediaItem getCurrentItem()
    {
        return sonosQueueModel.getCurrentItem();
    }

    protected int getCurrentItemIndex()
    {
        return sonosQueueModel.getCurrentIndex();
    }

    public List getFinishedItemsOnCurrentIndexChange(int i)
    {
        if(i < sonosQueueModel.getSize()) goto _L2; else goto _L1
_L1:
        Object obj;
        SLog.w("SonosRouteSession", "notifyCurrentItemIndexChanged: queue size mismatch, resyncing");
        resync(ResyncSessionCommand.ResyncReason.REASON_QUEUE_MODEL_MISMATCH);
        obj = null;
_L4:
        return ((List) (obj));
_L2:
        obj = new ArrayList();
        int j = appQueueModel.getCurrentIndex();
        if(i >= 0 && i > j)
        {
            for(int k = Math.max(j, 0); k < i; k++)
            {
                SonosMediaItem sonosmediaitem1 = sonosQueueModel.getItemAt(k);
                if(sonosmediaitem1 == null)
                    continue;
                if(k == i - 1)
                    sonosmediaitem1.cacheTrackPosition(sonosmediaitem1.getTrackPositionInMillisecs());
                ((List) (obj)).add(sonosmediaitem1);
            }

            SonosMediaItem sonosmediaitem = sonosQueueModel.getItemAt(i);
            String s = (new StringBuilder()).append("Current item changed from index=").append(j).append(" to ").append(i).append(", Id=").append(sonosmediaitem.getId()).toString();
            sonosQueueModel.setCurrentIndex(i);
            SLog.d("SonosRouteSession", s);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Handler getHandler()
    {
        return handler;
    }

    public int getIndexOfItemInQueue(String s)
    {
        int i;
        Iterator iterator;
        i = 0;
        iterator = sonosQueueModel.getItems().iterator();
_L3:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_51;
        if(!((SonosMediaItem)iterator.next()).getId().equals(s)) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    protected SonosMediaItem getItemInQueueAt(int i)
    {
        return sonosQueueModel.getItemAt(i);
    }

    public SonosMediaItem getItemWithId(String s)
    {
        Iterator iterator = sonosQueueModel.getItems().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SonosMediaItem sonosmediaitem = (SonosMediaItem)iterator.next();
        if(!sonosmediaitem.getId().equals(s)) goto _L4; else goto _L3
_L3:
        return sonosmediaitem;
_L2:
        sonosmediaitem = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public SonosMediaItem getItemWithUri(String s)
    {
        Iterator iterator = sonosQueueModel.getItems().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SonosMediaItem sonosmediaitem = (SonosMediaItem)iterator.next();
        if(!sonosmediaitem.getUri().toString().equals(s)) goto _L4; else goto _L3
_L3:
        return sonosmediaitem;
_L2:
        sonosmediaitem = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    protected long getLastUpdateId()
    {
        return lastUpdateId;
    }

    protected int getNumItemsInAppQueue()
    {
        return appQueueModel.getSize();
    }

    public int getNumItemsInQueue()
    {
        return sonosQueueModel.getSize();
    }

    protected SCIPlayQueue getPlayQueue()
    {
        return playQueue;
    }

    protected SCIPlayQueueMgr getPlayQueueMgr()
    {
        return playQueueMgr;
    }

    public long getQueueId()
    {
        long l;
        if(getPlayQueue() != null)
            l = getPlayQueue().getQueueID();
        else
            l = -1L;
        return l;
    }

    public String getQueueUri()
    {
        return queueUri;
    }

    public long getRemainingPlayTimeInMillis()
    {
        SonosMediaItem sonosmediaitem = getCurrentItem();
        long l = 0L;
        if(sonosmediaitem != null)
        {
            long l2 = sonosmediaitem.getTrackPositionInMillisecs();
            l = 1000L * (long)sonosmediaitem.getMetadata().getDurationInSecs() - l2;
        }
        long l1 = 0L;
        for(int i = 1 + sonosQueueModel.getCurrentIndex(); i < sonosQueueModel.getSize(); i++)
            l1 += 1000 * sonosQueueModel.getItemAt(i).getMetadata().getDurationInSecs();

        return l + l1;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public int getSessionState()
    {
        return sessionState;
    }

    protected ZoneGroup getZoneGroup()
    {
        return SonosRouteTransport.getZoneGroupFromRouteId(sonosGroupId);
    }

    public boolean isAppStatePlaying()
    {
        return appQueueModel.isPlaying();
    }

    public boolean isDiscarded()
    {
        return isDiscarded;
    }

    public boolean isQueueValid()
    {
        boolean flag;
        if(playQueue != null && playQueue.isValid())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isResyncPending()
    {
        boolean flag;
        if(currentSessionCommand != null && currentSessionCommand.getType() == SonosSessionCommand.SessionCommandType.SCMD_RESYNC)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSonosStatePlaying()
    {
        return sonosQueueModel.isPlaying();
    }

    public void markQueueAsFinished()
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        Iterator iterator = sonosQueueModel.getItems().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            SonosMediaItem sonosmediaitem1 = (SonosMediaItem)iterator.next();
            int i = sonosQueueModel.getIndexOfItem(sonosmediaitem1);
            if(sonosQueueModel.getCurrentIndex() == -1 || i < sonosQueueModel.getCurrentIndex())
                arraylist.add(sonosmediaitem1.getId());
            else
            if(i >= sonosQueueModel.getCurrentIndex())
                arraylist1.add(sonosmediaitem1.getId());
        } while(true);
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); getItemWithId((String)iterator1.next()).updateAndReport(5, "Item canceled"));
        SonosMediaItem sonosmediaitem;
        for(Iterator iterator2 = arraylist1.iterator(); iterator2.hasNext(); sonosmediaitem.updateAndReport(4, "Item finished", 0, null, 1000 * sonosmediaitem.getMetadata().getDurationInSecs()))
            sonosmediaitem = getItemWithId((String)iterator2.next());

        setAppStatePlaying(true);
    }

    public boolean notifyCurrentCommandOfNowPlayingEvent(NowPlaying nowplaying)
    {
        boolean flag = true;
        SCNPPlaybackState scnpplaybackstate = nowplaying.getTransport().getPlaybackState();
        if(scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
            setSonosStatePlaying(flag);
        else
        if(scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED)
            setSonosStatePlaying(false);
        if(currentSessionCommand == null || !currentSessionCommand.notifyNowPlayingEvent(nowplaying))
            flag = false;
        return flag;
    }

    public void onPlaybackStateChanged(SonosMediaItem sonosmediaitem)
    {
        int i = sonosmediaitem.getPlaybackState();
        boolean flag;
        if(i == 4 || i == 7)
            flag = true;
        else
            flag = false;
        if(sonosQueueModel.containsItem(sonosmediaitem) && flag)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("Auto-removing item ").append(sonosmediaitem.getId()).append(" with playbackState=");
            String s;
            boolean flag1;
            if(i == 4)
                s = "FINISHED";
            else
                s = "ERROR";
            SLog.d("SonosRouteSession", stringbuilder.append(s).toString());
            if(i == 7 && sonosmediaitem.getLastHttpStatusCode() >= 400)
                flag1 = true;
            else
                flag1 = false;
            removeItem(sonosmediaitem.getId(), flag1);
        }
    }

    public void onPrivateQueueChanged(SCIPlayQueue sciplayqueue)
    {
        long l = sciplayqueue.getUpdateID();
        String s = sciplayqueue.getQueueOwnerID();
        if(!$assertionsDisabled && l == 0L)
            throw new AssertionError();
        SLog.d("SonosRouteSession", (new StringBuilder()).append("onPrivateQueueChanged, update ID=").append(l).append(", queueOwnerId=").append(s).append(", expected=").append(queueOwnerId).toString());
        if((!StringUtils.isEmptyOrNull(s) || !StringUtils.isEmptyOrNull(queueOwnerId)) && (s == null || !s.equals(queueOwnerId)) && (queueOwnerId == null || !queueOwnerId.equals(s)))
            SLog.v("SonosRouteSession", (new StringBuilder()).append("Evented Owner ID ").append(s).append(" doesn't match expected owner ID ").append(queueOwnerId).toString());
    }

    public void onQueueInvalidationEvent(SCIPlayQueue sciplayqueue)
    {
        SLog.d("SonosRouteSession", "onQueueInvalidationEvent");
    }

    public void pause()
    {
        setAppStatePlaying(false);
        scheduleSessionCommand(new PauseSessionCommand(this, null));
    }

    public void play()
    {
        setAppStatePlaying(true);
        scheduleSessionCommand(new PlaySessionCommand(this, null));
    }

    protected void processSessionCommands()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("processSessionCommands: cmds=").append(sessionCommands.size()).append(", inProgress=");
        boolean flag;
        if(currentSessionCommand != null)
            flag = true;
        else
            flag = false;
        SLog.d("SonosRouteSession", stringbuilder.append(flag).toString());
        if(sessionCommands.size() > 0 && currentSessionCommand == null)
        {
            currentSessionCommand = (SonosSessionCommand)sessionCommands.removeFirst();
            if(currentSessionCommand != null)
            {
                SLog.d("SonosRouteSession", (new StringBuilder()).append("running session command ").append(currentSessionCommand.getType()).toString());
                currentSessionCommand.run();
            }
        }
    }

    public void removeItem(String s)
    {
        removeItem(s, false);
    }

    public void removeItem(String s, boolean flag)
    {
        RemoveItemSessionCommand removeitemsessioncommand = new RemoveItemSessionCommand(this, s, null);
        SonosMediaItem sonosmediaitem = appQueueModel.getItemById(s);
        boolean flag1;
        if(sonosQueueModel.getIndexOfItem(sonosmediaitem) == -1 + sonosQueueModel.getSize() && sonosQueueModel.getSize() > 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag && flag1)
        {
            pendingRemoveItemSessionCommand = removeitemsessioncommand;
            getHandler().postDelayed(new Runnable() {

                public void run()
                {
                    if(pendingRemoveItemSessionCommand != null)
                    {
                        scheduleSessionCommand(pendingRemoveItemSessionCommand);
                        pendingRemoveItemSessionCommand = null;
                    }
                }

                final SonosRouteSession this$0;

            
            {
                this$0 = SonosRouteSession.this;
                super();
            }
            }
, 5000L);
        } else
        {
            scheduleSessionCommand(removeitemsessioncommand);
        }
        if(sonosmediaitem != null)
            appQueueModel.removeItem(sonosmediaitem);
    }

    protected void removeItemInQueueModel(String s)
    {
        SonosMediaItem sonosmediaitem = getItemWithId(s);
        if(sonosmediaitem != null)
            sonosmediaitem.removeListener(this);
        sonosQueueModel.removeItem(sonosmediaitem);
    }

    protected void resync(ResyncSessionCommand.ResyncReason resyncreason)
    {
        firstResyncTimestamp = 0L;
        SLog.d("SonosRouteSession", (new StringBuilder()).append("resync queue: ").append(resyncreason.toString()).toString());
        resyncInternal(resyncreason);
    }

    protected void resyncInternal(ResyncSessionCommand.ResyncReason resyncreason)
    {
        if(currentSessionCommand == null || currentSessionCommand.getType() != SonosSessionCommand.SessionCommandType.SCMD_RESYNC) goto _L2; else goto _L1
_L1:
        SLog.w("SonosRouteSession", "resync already scheduled, not scheduling another.");
_L4:
        return;
_L2:
        long l = (new Date()).getTime();
        SonosMediaItem sonosmediaitem = getCurrentItem();
        long l1;
        if(firstResyncTimestamp == 0L)
            firstResyncTimestamp = l;
        else
        if(l - firstResyncTimestamp >= 6000L)
        {
            if(sonosmediaitem != null)
                sonosmediaitem.updateAndReport(7, "resync attempts timed out!");
            SLog.e("SonosRouteSession", "Resync attempts timed out!");
            continue; /* Loop/switch isn't completed */
        }
        if(sonosmediaitem != null)
            l1 = sonosmediaitem.getTrackPositionInMillisecs();
        else
            l1 = transport.getTransportTrackPositionInMillisecs();
        if(currentSessionCommand != null)
        {
            currentSessionCommand.updateQueueModelInSession();
            long l3 = currentSessionCommand.getTrackPositionInMillisecs();
            if(l3 >= 0L)
                l1 = l3;
            currentSessionCommand.cancel();
            currentSessionCommand = null;
        }
        SonosSessionCommand sonossessioncommand;
        for(Iterator iterator = sessionCommands.iterator(); iterator.hasNext(); sonossessioncommand.cancel())
        {
            sonossessioncommand = (SonosSessionCommand)iterator.next();
            sonossessioncommand.updateQueueModelInSession();
            long l2 = sonossessioncommand.getTrackPositionInMillisecs();
            if(l2 >= 0L)
                l1 = l2;
        }

        sessionCommands.clear();
        scheduleSessionCommand(new ResyncSessionCommand(this, resyncreason, l1, new SCIOpCBSwigBase() {

            public void _operationComplete(long l4, int i)
            {
                if(i != 0) goto _L2; else goto _L1
_L1:
                SLog.d("SonosRouteSession", "resync completed successfully");
_L4:
                return;
_L2:
                if(i == 7001 || i == 7002)
                    SLog.d("SonosRouteSession", "resync failed: invalid queue or queue manager");
                if(true) goto _L4; else goto _L3
_L3:
            }

            final SonosRouteSession this$0;

            
            {
                this$0 = SonosRouteSession.this;
                super();
            }
        }
));
        if(sonosQueueModel.getSize() > 0 && isAppStatePlaying())
            scheduleSessionCommand(new PlaySessionCommand(this, null));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void seekTo(long l)
    {
        scheduleSessionCommand(new SeekSessionCommand(l, getCurrentItem(), this, null));
    }

    public void setAppStatePlaying(boolean flag)
    {
        appQueueModel.setPlaying(flag);
    }

    protected void setExpectingStoppedToTrue()
    {
        transport.setExpectingStopped(true);
    }

    protected void setLastUpdateId(long l)
    {
        lastUpdateId = l;
    }

    protected void setSonosStatePlaying(boolean flag)
    {
        sonosQueueModel.setPlaying(flag);
        setAppStatePlaying(flag);
        int i = sessionState;
        StringBuilder stringbuilder = (new StringBuilder()).append("Queue play state changed: ");
        String s;
        if(flag)
            s = "Playing";
        else
            s = "Paused";
        updateAndReport(i, stringbuilder.append(s).toString());
    }

    protected void subscribeToQueue()
    {
        if(playQueue != null)
        {
            if(privateQueueEventSink != null)
                unsubscribeFromQueue();
            privateQueueEventSink = new PrivateQueueEventSink(playQueue);
            privateQueueEventSink.addListener(this);
        }
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("SessionID=").append(getSessionId()).toString();
        String s1;
        if(getPlayQueue() == null)
            s1 = (new StringBuilder()).append(s).append(", no queue").toString();
        else
            s1 = (new StringBuilder()).append(s).append(", queueID=").append(getQueueId()).append(", queueURI=").append(getQueueUri()).append(", numItems=").append(sonosQueueModel.getSize()).append(", curItem=").append(sonosQueueModel.getCurrentIndex()).toString();
        return s1;
    }

    public void updateAndReport(int i, String s)
    {
        sessionState = i;
        i;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 205
    //                   1 212
    //                   2 219;
           goto _L1 _L2 _L3 _L4
_L1:
        String s1 = "UNKNOWN";
_L5:
        if(statusReceiver == null)
            break MISSING_BLOCK_LABEL_204;
        StringBuilder stringbuilder = (new StringBuilder()).append("updateAndReport: reporting status ").append(s1).append(" and queue playback state ");
        String s2;
        Intent intent;
        android.support.v7.media.MediaSessionStatus.Builder builder;
        boolean flag;
        Bundle bundle;
        if(isAppStatePlaying())
            s2 = "playing";
        else
            s2 = "paused";
        MRPLog.i("SonosRouteSession", stringbuilder.append(s2).append(" for session ID ").append(sessionId).toString());
        intent = new Intent();
        builder = new android.support.v7.media.MediaSessionStatus.Builder(i);
        if(!isAppStatePlaying())
            flag = true;
        else
            flag = false;
        builder.setQueuePaused(flag);
        bundle = new Bundle();
        bundle.putString("android.media.intent.extra.SESSION_ID", sessionId);
        bundle.putBundle("android.media.intent.extra.SESSION_STATUS", builder.build().asBundle());
        intent.putExtras(bundle);
        statusReceiver.send(context, 0, intent);
_L6:
        return;
_L2:
        s1 = "ACTIVE";
          goto _L5
_L3:
        s1 = "ENDED";
          goto _L5
_L4:
        s1 = "INVALIDATED";
          goto _L5
        android.app.PendingIntent.CanceledException canceledexception;
        canceledexception;
        canceledexception.printStackTrace();
          goto _L6
    }

    protected boolean validateAndSetPlayQueue(SCIPlayQueue sciplayqueue)
    {
        com.sonos.sclib.SCIBrowseDataSource scibrowsedatasource;
        ZoneDevice zonedevice;
        boolean flag;
        if(sciplayqueue != null)
            scibrowsedatasource = sciplayqueue.createDataSource();
        else
            scibrowsedatasource = null;
        zonedevice = getZoneGroup().getMasterDevice();
        if(sciplayqueue == null || scibrowsedatasource == null || zonedevice == null)
        {
            clearPlayQueue();
            flag = false;
        } else
        {
            playQueue = sciplayqueue;
            playQueueMgr = (SCIPlayQueueMgr)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPlayQueueMgr);
            queueUri = (new StringBuilder()).append("x-rincon-queue:").append(zonedevice.getId()).append("#").append(getQueueId()).toString();
            SLog.d("SonosRouteSession", (new StringBuilder()).append("Queue with URI=").append(queueUri).append(" created").toString());
            flag = true;
        }
        return flag;
    }

    static final boolean $assertionsDisabled = false;
    protected static final String LOG_TAG = "SonosRouteSession";
    private static final int PENDING_REMOVE_ITEM_DELAY_MILLIS = 5000;
    private static final int RESYNC_TIMEOUT_MS = 6000;
    public static final String SONOS_QUEUE_URI_PREFIX = "x-rincon-queue:";
    private static long firstResyncTimestamp = 0L;
    private SonosSessionQueueModel appQueueModel;
    protected String clientId;
    private Context context;
    private SonosSessionCommand currentSessionCommand;
    private Handler handler;
    private boolean isDiscarded;
    private long lastUpdateId;
    private boolean newSonosSession;
    private RemoveItemSessionCommand pendingRemoveItemSessionCommand;
    private SCIPlayQueue playQueue;
    private SCIPlayQueueMgr playQueueMgr;
    private PrivateQueueEventSink privateQueueEventSink;
    private boolean queueHasBeenCleared;
    protected String queueOwnerId;
    private String queueUri;
    private LinkedList sessionCommands;
    private String sessionId;
    private int sessionState;
    private String sonosGroupId;
    private SonosSessionQueueModel sonosQueueModel;
    private PendingIntent statusReceiver;
    protected SonosRouteTransport transport;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/media/session/SonosRouteSession.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }



/*
    static RemoveItemSessionCommand access$002(SonosRouteSession sonosroutesession, RemoveItemSessionCommand removeitemsessioncommand)
    {
        sonosroutesession.pendingRemoveItemSessionCommand = removeitemsessioncommand;
        return removeitemsessioncommand;
    }

*/


}
