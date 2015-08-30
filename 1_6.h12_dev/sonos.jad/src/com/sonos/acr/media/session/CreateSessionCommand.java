// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.media.SonosRouteTransport;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.SonosQueuePlaybackPolicy;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class CreateSessionCommand extends SonosSessionCommand
{

    public CreateSessionCommand(SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_CREATESESSION, sonosroutesession, sciopcbswigbase);
    }

    private void createQueueAndSetAVT(ZoneGroup zonegroup, final SCIOpCBSwigBase callback)
    {
        SonosQueuePlaybackPolicy sonosqueueplaybackpolicy = new SonosQueuePlaybackPolicy(false, false, com.sonos.acr.util.SonosQueuePlaybackPolicy.CacheOnPausePolicyMode.Enabled_FullTrackOnly, true, true);
        final SCIOpNewPrivateQueue newQueueOp = zonegroup.createNewPrivateQueueOp(session.queueOwnerId, session.clientId, sonosqueueplaybackpolicy);
        SCIOpCBSwigBase sciopcbswigbase = new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                removeOp(newQueueOp);
                SLog.d("SonosRouteSession", (new StringBuilder()).append("Create new private queue: res=").append(i).toString());
                boolean flag;
                SCIPlayQueue sciplayqueue;
                StringBuilder stringbuilder;
                StringBuilder stringbuilder1;
                if(i == 0)
                    flag = session.validateAndSetPlayQueue(newQueueOp.getCreatedPrivateQueue());
                else
                    flag = false;
                if(!flag) goto _L2; else goto _L1
_L1:
                sciplayqueue = session.getPlayQueue();
                stringbuilder = (new StringBuilder()).append("Success creating queue, queueId=");
                Object obj;
                String s;
                if(sciplayqueue != null)
                    obj = Long.valueOf(sciplayqueue.getQueueID());
                else
                    obj = "N/A";
                stringbuilder1 = stringbuilder.append(obj).append(", queueOwnerId=");
                if(sciplayqueue != null)
                    s = sciplayqueue.getQueueOwnerID();
                else
                    s = "N/A";
                SLog.d("SonosRouteSession", stringbuilder1.append(s).toString());
                if(sciplayqueue == null || sciplayqueue.getQueueID() <= 0L)
                {
                    SLog.e("SonosRouteSession", "Unexpected private queue result: invalid SCIPlayQueue object!");
                    if(callback != null)
                        callback._operationComplete(l, 7001);
                } else
                {
                    session.subscribeToQueue();
                    session.transport.setTransportURI(session.getQueueUri(), "", setAVTransportURICompleteCallback, getOps());
                }
_L4:
                return;
_L2:
                if(i == 0)
                    i = 7001;
                SLog.d("SonosRouteSession", (new StringBuilder()).append("Error creating private queue: res=").append(i).toString());
                if(callback != null)
                    callback._operationComplete(l, i);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final CreateSessionCommand this$0;
            final SCIOpCBSwigBase val$callback;
            final SCIOpNewPrivateQueue val$newQueueOp;
            final SCIOpCBSwigBase val$setAVTransportURICompleteCallback;

            
            {
                this$0 = CreateSessionCommand.this;
                newQueueOp = sciopnewprivatequeue;
                callback = sciopcbswigbase;
                setAVTransportURICompleteCallback = sciopcbswigbase1;
                super();
            }
        }
;
        session.transport.addExpectedUri("");
        newQueueOp._start(sciopcbswigbase);
        addOp(newQueueOp);
    }

    public void run()
    {
        ZoneGroup zonegroup = session.getZoneGroup();
        if(zonegroup == null)
            completeSessionCommand(0L, 7000);
        else
        if(session.getPlayQueue() != null)
        {
            SLog.d("SonosRouteSession", "CreateSessionCommand: session already exists");
            if(!session.getPlayQueue().isValid())
            {
                SLog.e("SonosRouteSession", "CreateSessionCommand: existing session not valid!");
                completeSessionCommand(0L, 7001);
            } else
            {
                completeSessionCommand(0L, 0);
            }
        } else
        {
            createQueueAndSetAVT(zonegroup, new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i == 0)
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("createQueueAndSetAVT succeeded, res=").append(i).toString());
                    else
                        SLog.e("SonosRouteSession", (new StringBuilder()).append("createQueueAndSetAVT failed, res=").append(i).toString());
                    completeSessionCommand(l, i);
                }

                final CreateSessionCommand this$0;

            
            {
                this$0 = CreateSessionCommand.this;
                super();
            }
            }
);
        }
    }

    // Unreferenced inner class com/sonos/acr/media/session/CreateSessionCommand$1

/* anonymous class */
    class _cls1 extends SCIOpCBSwigBase
    {

        public void _operationComplete(long l, int i)
        {
            SLog.d("SonosRouteSession", (new StringBuilder()).append("Set URI to queue completed, res=").append(i).toString());
            if(i != 0)
                session.clearPlayQueue();
            if(callback != null)
                callback._operationComplete(l, i);
        }

        final CreateSessionCommand this$0;
        final SCIOpCBSwigBase val$callback;

            
            {
                this$0 = CreateSessionCommand.this;
                callback = sciopcbswigbase;
                super();
            }
    }

}
