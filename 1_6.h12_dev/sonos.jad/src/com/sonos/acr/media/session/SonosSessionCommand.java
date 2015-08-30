// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.media.*;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.sclib.SCIOp;
import com.sonos.sclib.SCIOpCBSwigBase;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosRouteSession

public abstract class SonosSessionCommand
    implements SonosMediaItemTrackPositionSource
{
    public static final class SessionCommandType extends Enum
    {

        public static SessionCommandType valueOf(String s)
        {
            return (SessionCommandType)Enum.valueOf(com/sonos/acr/media/session/SonosSessionCommand$SessionCommandType, s);
        }

        public static SessionCommandType[] values()
        {
            return (SessionCommandType[])$VALUES.clone();
        }

        public boolean insertAtFrontOfQueue()
        {
            boolean flag;
            if(this == SCMD_ATTACHQUEUE)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean shouldRemoveOnClearQueue()
        {
            boolean flag;
            if(this != SCMD_ATTACHQUEUE)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private static final SessionCommandType $VALUES[];
        public static final SessionCommandType SCMD_ATTACHQUEUE;
        public static final SessionCommandType SCMD_CLEARALL;
        public static final SessionCommandType SCMD_CREATESESSION;
        public static final SessionCommandType SCMD_ENQUEUESEEKANDPLAY;
        public static final SessionCommandType SCMD_PAUSE;
        public static final SessionCommandType SCMD_PLAY;
        public static final SessionCommandType SCMD_REMOVE;
        public static final SessionCommandType SCMD_RESYNC;
        public static final SessionCommandType SCMD_SEEK;

        static 
        {
            SCMD_CREATESESSION = new SessionCommandType("SCMD_CREATESESSION", 0);
            SCMD_ATTACHQUEUE = new SessionCommandType("SCMD_ATTACHQUEUE", 1);
            SCMD_ENQUEUESEEKANDPLAY = new SessionCommandType("SCMD_ENQUEUESEEKANDPLAY", 2);
            SCMD_CLEARALL = new SessionCommandType("SCMD_CLEARALL", 3);
            SCMD_REMOVE = new SessionCommandType("SCMD_REMOVE", 4);
            SCMD_PLAY = new SessionCommandType("SCMD_PLAY", 5);
            SCMD_PAUSE = new SessionCommandType("SCMD_PAUSE", 6);
            SCMD_SEEK = new SessionCommandType("SCMD_SEEK", 7);
            SCMD_RESYNC = new SessionCommandType("SCMD_RESYNC", 8);
            SessionCommandType asessioncommandtype[] = new SessionCommandType[9];
            asessioncommandtype[0] = SCMD_CREATESESSION;
            asessioncommandtype[1] = SCMD_ATTACHQUEUE;
            asessioncommandtype[2] = SCMD_ENQUEUESEEKANDPLAY;
            asessioncommandtype[3] = SCMD_CLEARALL;
            asessioncommandtype[4] = SCMD_REMOVE;
            asessioncommandtype[5] = SCMD_PLAY;
            asessioncommandtype[6] = SCMD_PAUSE;
            asessioncommandtype[7] = SCMD_SEEK;
            asessioncommandtype[8] = SCMD_RESYNC;
            $VALUES = asessioncommandtype;
        }

        private SessionCommandType(String s, int i)
        {
            super(s, i);
        }
    }


    public SonosSessionCommand(SessionCommandType sessioncommandtype, SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        sessionOps = new ArrayList();
        isUpdatingQueue = false;
        associatedItem = null;
        contentPositionInMillis = -1L;
        type = sessioncommandtype;
        callback = sciopcbswigbase;
        session = sonosroutesession;
    }

    public void addOp(SCIOp sciop)
    {
        isUpdatingQueue = true;
        addSimpleOp(sciop);
        updateQueueModelInSession();
    }

    public void addSimpleOp(SCIOp sciop)
    {
        sessionOps.add(sciop);
    }

    public boolean areTransportTrackPositionUpdatesEnabled()
    {
        return false;
    }

    public void cancel()
    {
        for(Iterator iterator = sessionOps.iterator(); iterator.hasNext(); ((SCIOp)iterator.next())._cancel());
        if(associatedItem != null)
        {
            associatedItem.removeTrackPositionSource(this);
            associatedItem = null;
        }
    }

    protected void completeSessionCommand(long l, int i)
    {
        MRPLog.i("SonosRouteSession", (new StringBuilder()).append("session command ").append(type).append(" completed, res=").append(i).toString());
        if(callback != null)
            callback._operationComplete(l, i);
        if(associatedItem != null)
        {
            associatedItem.removeTrackPositionSource(this);
            associatedItem = null;
        }
        session.clearSessionCommand(this);
        session.processSessionCommands();
    }

    public ArrayList getOps()
    {
        return sessionOps;
    }

    public long getTrackPositionInMillisecs()
    {
        return contentPositionInMillis;
    }

    public SessionCommandType getType()
    {
        return type;
    }

    protected boolean notifyNowPlayingEvent(NowPlaying nowplaying)
    {
        return false;
    }

    public boolean overrideItemTrackPositionCaching()
    {
        return true;
    }

    protected void registerAsTrackPositionSourceForItem(SonosMediaItem sonosmediaitem)
    {
        associatedItem = sonosmediaitem;
        sonosmediaitem.addTrackPositionSource(this);
    }

    public void removeOp(SCIOp sciop)
    {
        isUpdatingQueue = false;
        removeSimpleOp(sciop);
    }

    public void removeSimpleOp(SCIOp sciop)
    {
        sessionOps.remove(sciop);
    }

    public abstract void run();

    protected void updateQueueModelInSession()
    {
    }

    public boolean updatingQueue()
    {
        return isUpdatingQueue;
    }

    public static final int SCMD_ERR_EMPTY_QUEUE = 7003;
    public static final int SCMD_ERR_INVALID_GROUP = 7000;
    public static final int SCMD_ERR_INVALID_QUEUE = 7001;
    public static final int SCMD_ERR_INVALID_QUEUEMGR = 7002;
    public static final int SCMD_ERR_ITEM_NOT_FOUND = 7004;
    private SonosMediaItem associatedItem;
    private SCIOpCBSwigBase callback;
    protected long contentPositionInMillis;
    private boolean isUpdatingQueue;
    protected SonosRouteSession session;
    private ArrayList sessionOps;
    private SessionCommandType type;
}
