// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import android.os.Handler;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class ClearAllSessionCommand extends SonosSessionCommand
{

    public ClearAllSessionCommand(SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_CLEARALL, sonosroutesession, sciopcbswigbase);
        queueModelUpdated = false;
        waitingForStopped = false;
        waitingForUpnpComplete = false;
        cachedSerialNum = 0L;
        onWaitForStoppedTimeout = new Runnable() {

            public void run()
            {
                SLog.w("SonosRouteSession", "ClearAllSessionCommand: 10 seconds is up, giving up waiting for STOPPED");
                waitingForStopped = false;
                completeSessionCommandIfReady(0);
            }

            final ClearAllSessionCommand this$0;

            
            {
                this$0 = ClearAllSessionCommand.this;
                super();
            }
        }
;
    }

    private void beginWaitingForStoppedEvent()
    {
        waitingForStopped = true;
        session.getHandler().postDelayed(onWaitForStoppedTimeout, 10000L);
    }

    private void completeSessionCommandIfReady(int i)
    {
        if(!waitingForUpnpComplete && !waitingForStopped)
            completeSessionCommand(cachedSerialNum, i);
    }

    private void stopWaitingForStoppedEvent()
    {
        waitingForStopped = false;
        session.getHandler().removeCallbacks(onWaitForStoppedTimeout);
    }

    protected boolean notifyNowPlayingEvent(NowPlaying nowplaying)
    {
        boolean flag = false;
        if(waitingForStopped && nowplaying.getTransport().getPlaybackState() == SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED)
        {
            stopWaitingForStoppedEvent();
            completeSessionCommandIfReady(0);
            flag = true;
        }
        return flag;
    }

    public void run()
    {
        SLog.d("SonosRouteSession", "starting ClearAllSessionCommand");
        if(session.getPlayQueue() == null || !session.getPlayQueue().isValid())
        {
            SLog.e("SonosRouteSession", "Cannot clear the queue: invalid private queue!");
            completeSessionCommand(0L, 7001);
        } else
        {
            SCIPlayQueueMgr sciplayqueuemgr = session.getPlayQueueMgr();
            if(sciplayqueuemgr != null)
            {
                beginWaitingForStoppedEvent();
                waitingForUpnpComplete = true;
                final SCIOpGenericUpdateQueue removeAllItemsOp = sciplayqueuemgr.createRemoveAllItemsOp(session.getLastUpdateId());
                removeAllItemsOp._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int i)
                    {
                        waitingForUpnpComplete = false;
                        cachedSerialNum = l;
                        removeOp(removeAllItemsOp);
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("Remove tracks in queue completed, res=").append(i).toString());
                        if(i != 0) goto _L2; else goto _L1
_L1:
                        session.setLastUpdateId(removeAllItemsOp.getNewUpdateID());
                        session.setExpectingStoppedToTrue();
_L4:
                        completeSessionCommandIfReady(i);
                        return;
_L2:
                        stopWaitingForStoppedEvent();
                        if(i == 1028)
                        {
                            SLog.w("SonosRouteSession", "received UPNP_RESULT_STALE_OBJECT, resyncing queue");
                            session.resync(ResyncSessionCommand.ResyncReason.REASON_QUEUE_MODEL_MISMATCH);
                        } else
                        if(i == 1001 || i == 1002)
                        {
                            SLog.w("SonosRouteSession", "received UPNP_RESULT_CANT_CONNECT or UPNP_RESULT_TIMEOUT, resyncing queue");
                            session.resync(ResyncSessionCommand.ResyncReason.REASON_TIMEOUT_ERROR);
                        } else
                        if(i == 800)
                            session.resync(ResyncSessionCommand.ResyncReason.REASON_UNKNOWN);
                        if(true) goto _L4; else goto _L3
_L3:
                    }

                    final ClearAllSessionCommand this$0;
                    final SCIOpGenericUpdateQueue val$removeAllItemsOp;

            
            {
                this$0 = ClearAllSessionCommand.this;
                removeAllItemsOp = sciopgenericupdatequeue;
                super();
            }
                }
);
                addOp(removeAllItemsOp);
            } else
            {
                waitingForUpnpComplete = false;
                SLog.e("SonosRouteSession", "Cannot clear the queue: no private queue manager!");
                completeSessionCommandIfReady(7002);
            }
        }
    }

    protected void updateQueueModelInSession()
    {
        if(!queueModelUpdated)
        {
            session.cancelAndRemoveAllItemsInQueueModel();
            queueModelUpdated = true;
        }
    }

    private static final long WAIT_FOR_STOPPED_TIMEOUT_MILLIS = 10000L;
    private long cachedSerialNum;
    private Runnable onWaitForStoppedTimeout;
    private boolean queueModelUpdated;
    private boolean waitingForStopped;
    private boolean waitingForUpnpComplete;


/*
    static boolean access$002(ClearAllSessionCommand clearallsessioncommand, boolean flag)
    {
        clearallsessioncommand.waitingForStopped = flag;
        return flag;
    }

*/



/*
    static boolean access$202(ClearAllSessionCommand clearallsessioncommand, boolean flag)
    {
        clearallsessioncommand.waitingForUpnpComplete = flag;
        return flag;
    }

*/


/*
    static long access$302(ClearAllSessionCommand clearallsessioncommand, long l)
    {
        clearallsessioncommand.cachedSerialNum = l;
        return l;
    }

*/

}
