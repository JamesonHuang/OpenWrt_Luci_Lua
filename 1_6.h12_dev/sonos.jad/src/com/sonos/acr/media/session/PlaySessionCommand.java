// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class PlaySessionCommand extends SonosSessionCommand
{

    public PlaySessionCommand(SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_PLAY, sonosroutesession, sciopcbswigbase);
    }

    public void run()
    {
        ZoneGroup zonegroup = session.getZoneGroup();
        if(zonegroup != null)
        {
            if(session.getNumItemsInQueue() > 0)
            {
                final SCIOp playOp = zonegroup.nowPlaying.getTransport().createPlayOp();
                playOp._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int i)
                    {
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("play completed, res=").append(i).toString());
                        removeOp(playOp);
                        completeSessionCommand(l, i);
                    }

                    final PlaySessionCommand this$0;
                    final SCIOp val$playOp;

            
            {
                this$0 = PlaySessionCommand.this;
                playOp = sciop;
                super();
            }
                }
);
                addOp(playOp);
            } else
            {
                updateQueueModelInSession();
                completeSessionCommand(0L, 0);
            }
        } else
        {
            completeSessionCommand(0L, 7000);
        }
    }

    protected void updateQueueModelInSession()
    {
        session.setSonosStatePlaying(true);
    }
}
