// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class AttachQueueCommand extends SonosSessionCommand
{

    public AttachQueueCommand(SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_ATTACHQUEUE, sonosroutesession, sciopcbswigbase);
    }

    public void run()
    {
        ZoneGroup zonegroup = session.getZoneGroup();
        if(zonegroup != null)
        {
            final SCIOpAttachPrivateQueue attachPrivateQueueOp = zonegroup.createAttachPrivateQueueOp(session.queueOwnerId);
            attachPrivateQueueOp._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    SLog.d("SonosRouteSession", (new StringBuilder()).append("attach queue completed, res=").append(i).toString());
                    removeOp(attachPrivateQueueOp);
                    boolean flag;
                    if(i == 0)
                        flag = session.validateAndSetPlayQueue(attachPrivateQueueOp.getAttachedPrivateQueue());
                    else
                        flag = false;
                    if(flag)
                    {
                        SCIPlayQueue sciplayqueue = session.getPlayQueue();
                        StringBuilder stringbuilder = (new StringBuilder()).append("Success attaching to queue, queueId=");
                        Object obj;
                        StringBuilder stringbuilder1;
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
                            SLog.e("SonosRouteSession", "Unexpected attach private queue result: invalid SCIPlayQueue object!");
                            i = 7001;
                        } else
                        {
                            session.subscribeToQueue();
                        }
                    } else
                    {
                        if(i == 0)
                            i = 7001;
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("Error attaching to private queue: res=").append(i).toString());
                    }
                    completeSessionCommand(l, i);
                }

                final AttachQueueCommand this$0;
                final SCIOpAttachPrivateQueue val$attachPrivateQueueOp;

            
            {
                this$0 = AttachQueueCommand.this;
                attachPrivateQueueOp = sciopattachprivatequeue;
                super();
            }
            }
);
            addOp(attachPrivateQueueOp);
        } else
        {
            completeSessionCommand(0L, 7000);
        }
    }
}
