// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.media.SonosMediaItem;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class RemoveItemSessionCommand extends SonosSessionCommand
{

    public RemoveItemSessionCommand(SonosRouteSession sonosroutesession, String s, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_REMOVE, sonosroutesession, sciopcbswigbase);
        queueModelUpdated = false;
        itemId = s;
    }

    public void run()
    {
        final int itemIdx;
        final SonosMediaItem item;
        itemIdx = session.getIndexOfItemInQueue(itemId);
        item = session.getItemWithId(itemId);
        if(session.getPlayQueue() != null && session.getPlayQueue().isValid()) goto _L2; else goto _L1
_L1:
        char c;
        SLog.e("SonosRouteSession", "Cannot remove item from the queue: invalid private queue!");
        c = '\u1B59';
_L6:
        completeSessionCommand(0L, c);
_L4:
        return;
_L2:
        if(session.getNumItemsInQueue() < 1)
        {
            SLog.e("SonosRouteSession", "Cannot remove item from the queue: nothing in the queue!");
            c = '\u1B5B';
            continue; /* Loop/switch isn't completed */
        }
        if(itemIdx < 0)
        {
            SLog.e("SonosRouteSession", "Cannot remove item from the queue: item not found!");
            c = '\u1B5C';
            continue; /* Loop/switch isn't completed */
        }
        SCIPlayQueueMgr sciplayqueuemgr = session.getPlayQueueMgr();
        if(sciplayqueuemgr == null)
            break; /* Loop/switch isn't completed */
        SLog.d("SonosRouteSession", (new StringBuilder()).append("Removing item ").append(itemIdx).append(" from queue... ").toString());
        final SCIOpGenericUpdateQueue removeItemOp = sciplayqueuemgr.createRemoveItemsOp(itemIdx, 1L, session.getLastUpdateId());
        removeItemOp._start(new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                removeOp(removeItemOp);
                SLog.d("SonosRouteSession", (new StringBuilder()).append("Remove item ").append(itemIdx).append(" in queue completed, res=").append(i).toString());
                if(i != 0) goto _L2; else goto _L1
_L1:
                item.updateAndReport(5, "Item removed");
                session.setLastUpdateId(removeItemOp.getNewUpdateID());
_L4:
                completeSessionCommand(l, i);
                return;
_L2:
                if(i == 1001 || i == 1002)
                {
                    SLog.w("SonosRouteSession", "received UPNP_RESULT_CANT_CONNECT or UPNP_REUSLT_TIMEOUT, resyncing queue");
                    session.resync(ResyncSessionCommand.ResyncReason.REASON_TIMEOUT_ERROR);
                } else
                if(i == 1028)
                {
                    SLog.w("SonosRouteSession", "received UPNP_RESULT_STALE_OBJECT, resyncing queue");
                    session.resync(ResyncSessionCommand.ResyncReason.REASON_QUEUE_MODEL_MISMATCH);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final RemoveItemSessionCommand this$0;
            final SonosMediaItem val$item;
            final int val$itemIdx;
            final SCIOpGenericUpdateQueue val$removeItemOp;

            
            {
                this$0 = RemoveItemSessionCommand.this;
                removeItemOp = sciopgenericupdatequeue;
                itemIdx = i;
                item = sonosmediaitem;
                super();
            }
        }
);
        addOp(removeItemOp);
        if(true) goto _L4; else goto _L3
_L3:
        SLog.e("SonosRouteSession", "Cannot remove track from queue: no private queue manager!");
        c = '\u1B5A';
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void updateQueueModelInSession()
    {
        if(!queueModelUpdated)
        {
            session.removeItemInQueueModel(itemId);
            queueModelUpdated = true;
        }
    }

    private String itemId;
    private boolean queueModelUpdated;
}
