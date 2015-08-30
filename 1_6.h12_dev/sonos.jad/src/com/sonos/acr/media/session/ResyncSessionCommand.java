// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import android.net.Uri;
import com.sonos.acr.media.*;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class ResyncSessionCommand extends SonosSessionCommand
{
    public static final class ResyncReason extends Enum
    {

        public static ResyncReason valueOf(String s)
        {
            return (ResyncReason)Enum.valueOf(com/sonos/acr/media/session/ResyncSessionCommand$ResyncReason, s);
        }

        public static ResyncReason[] values()
        {
            return (ResyncReason[])$VALUES.clone();
        }

        private static final ResyncReason $VALUES[];
        public static final ResyncReason REASON_QUEUE_MODEL_MISMATCH;
        public static final ResyncReason REASON_TIMEOUT_ERROR;
        public static final ResyncReason REASON_UNKNOWN;

        static 
        {
            REASON_QUEUE_MODEL_MISMATCH = new ResyncReason("REASON_QUEUE_MODEL_MISMATCH", 0);
            REASON_TIMEOUT_ERROR = new ResyncReason("REASON_TIMEOUT_ERROR", 1);
            REASON_UNKNOWN = new ResyncReason("REASON_UNKNOWN", 2);
            ResyncReason aresyncreason[] = new ResyncReason[3];
            aresyncreason[0] = REASON_QUEUE_MODEL_MISMATCH;
            aresyncreason[1] = REASON_TIMEOUT_ERROR;
            aresyncreason[2] = REASON_UNKNOWN;
            $VALUES = aresyncreason;
        }

        private ResyncReason(String s, int i)
        {
            super(s, i);
        }
    }


    public ResyncSessionCommand(SonosRouteSession sonosroutesession, ResyncReason resyncreason, long l, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_RESYNC, sonosroutesession, sciopcbswigbase);
        reason = ResyncReason.REASON_UNKNOWN;
        reason = resyncreason;
        contentPositionInMillis = l;
    }

    private void attemptResync(int i)
    {
        SLog.w("SonosRouteSession", (new StringBuilder()).append("Resync failed with code ").append(i).append("! attempting to retry.").toString());
        if(i == 1001 || i == 1002)
            session.resyncInternal(ResyncReason.REASON_TIMEOUT_ERROR);
        else
            session.resyncInternal(ResyncReason.REASON_UNKNOWN);
    }

    private static int getResyncDelayInMillisecs()
    {
        return 100;
    }

    private void resyncQueueAndCompleteSessionCommand()
    {
        if(!session.isQueueValid())
        {
            SLog.e("SonosRouteSession", "Cannot resync: invalid private queue!");
            completeSessionCommand(0L, 7001);
        } else
        {
            SCIPlayQueueMgr sciplayqueuemgr = session.getPlayQueueMgr();
            if(sciplayqueuemgr == null)
            {
                SLog.e("SonosRouteSession", "Cannot resync: no private queue manager!");
                completeSessionCommand(0L, 7002);
            } else
            {
                final int numItems = session.getNumItemsInQueue();
                if(numItems == 0)
                {
                    final SCIOpGenericUpdateQueue removeAllItemsOp = sciplayqueuemgr.createRemoveAllItemsOp(0L);
                    removeAllItemsOp._start(new SCIOpCBSwigBase() {

                        public void _operationComplete(long l, int j)
                        {
                            removeOp(removeAllItemsOp);
                            SLog.d("SonosRouteSession", (new StringBuilder()).append("Remove all tracks (resync) completed, res=").append(j).toString());
                            if(j == 0)
                            {
                                session.setLastUpdateId(removeAllItemsOp.getNewUpdateID());
                                session.setExpectingStoppedToTrue();
                            }
                            completeSessionCommand(l, j);
                            if(j != 0)
                                attemptResync(j);
                        }

                        final ResyncSessionCommand this$0;
                        final SCIOpGenericUpdateQueue val$removeAllItemsOp;

            
            {
                this$0 = ResyncSessionCommand.this;
                removeAllItemsOp = sciopgenericupdatequeue;
                super();
            }
                    }
);
                    addOp(removeAllItemsOp);
                } else
                {
                    SCIStringArray scistringarray = sclib.createSCStringArray();
                    SCIStringArray scistringarray1 = sclib.createSCStringArray();
                    for(int i = 0; i < session.getNumItemsInQueue(); i++)
                    {
                        SonosMediaItem sonosmediaitem1 = session.getItemInQueueAt(i);
                        scistringarray.append(sonosmediaitem1.getUri().toString());
                        scistringarray1.append(sonosmediaitem1.getMetadata().getDIDLLite(false));
                    }

                    SonosMediaItem sonosmediaitem = session.getCurrentItem();
                    if(sonosmediaitem == null && numItems > 0)
                        sonosmediaitem = session.getItemInQueueAt(0);
                    if(sonosmediaitem != null)
                        registerAsTrackPositionSourceForItem(sonosmediaitem);
                    final SCIOpQueueReplaceAllTracks replaceAllTracksOp = sciplayqueuemgr.createReplaceAllTracksOp(scistringarray, scistringarray1, 0L, Integer.toString(1 + session.getCurrentItemIndex()), 0L);
                    replaceAllTracksOp._start(new SCIOpCBSwigBase() {

                        public void _operationComplete(long l, int j)
                        {
                            removeOp(replaceAllTracksOp);
                            boolean flag;
                            StringBuilder stringbuilder;
                            String s;
                            if(j == 0 && contentPositionInMillis > 0L)
                                flag = true;
                            else
                                flag = false;
                            stringbuilder = (new StringBuilder()).append("Replace all tracks (").append(numItems).append(" items) completed, res=").append(j);
                            if(flag)
                                s = (new StringBuilder()).append(", seeking to ").append(contentPositionInMillis).toString();
                            else
                                s = "";
                            SLog.d("SonosRouteSession", stringbuilder.append(s).toString());
                            if(j == 0)
                            {
                                session.setLastUpdateId(replaceAllTracksOp.getNewUpdateID());
                                session.setExpectingStoppedToTrue();
                                if(flag)
                                    session.transport.seekTo(contentPositionInMillis, new SCIOpCBSwigBase() {

                                        public void _operationComplete(long l1, int k)
                                        {
                                            completeSessionCommand(l1, k);
                                        }

                                        final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                                    }
, getOps());
                                SonosMediaItem sonosmediaitem2 = session.getCurrentItem();
                                if(sonosmediaitem2 != null)
                                    sonosmediaitem2.updateAndReport(sonosmediaitem2.getPlaybackState(), "resync finished, playing", 0, null, contentPositionInMillis);
                            }
                            if(!flag)
                                completeSessionCommand(l, j);
                            if(j != 0)
                                attemptResync(j);
                        }

                        final ResyncSessionCommand this$0;
                        final int val$numItems;
                        final SCIOpQueueReplaceAllTracks val$replaceAllTracksOp;

            
            {
                this$0 = ResyncSessionCommand.this;
                replaceAllTracksOp = sciopqueuereplacealltracks;
                numItems = i;
                super();
            }
                    }
);
                    addOp(replaceAllTracksOp);
                }
            }
        }
    }

    public void run()
    {
        if(!session.isQueueValid())
        {
            SLog.e("SonosRouteSession", "Cannot resync: invalid private queue!");
            completeSessionCommand(0L, 7001);
        } else
        if(reason == ResyncReason.REASON_TIMEOUT_ERROR)
        {
            final SCIOp delayOp = sclib.createSCNullAsyncOperation(getResyncDelayInMillisecs());
            delayOp._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    removeSimpleOp(delayOp);
                    resyncQueueAndCompleteSessionCommand();
                }

                final ResyncSessionCommand this$0;
                final SCIOp val$delayOp;

            
            {
                this$0 = ResyncSessionCommand.this;
                delayOp = sciop;
                super();
            }
            }
);
            addSimpleOp(delayOp);
        } else
        {
            resyncQueueAndCompleteSessionCommand();
        }
    }

    private ResyncReason reason;


}
