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

public class PauseSessionCommand extends SonosSessionCommand
{

    public PauseSessionCommand(SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_PAUSE, sonosroutesession, sciopcbswigbase);
    }

    public void run()
    {
        ZoneGroup zonegroup = session.getZoneGroup();
        if(zonegroup != null)
        {
            if(session.getNumItemsInQueue() > 0)
            {
                final SCIOp pauseOp = zonegroup.nowPlaying.getTransport().createPauseOp();
                pauseOp._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int i)
                    {
                        SLog.d("SonosRouteSession", (new StringBuilder()).append("pause completed, res=").append(i).toString());
                        removeOp(pauseOp);
                        completeSessionCommand(l, i);
                    }

                    final PauseSessionCommand this$0;
                    final SCIOp val$pauseOp;

            
            {
                this$0 = PauseSessionCommand.this;
                pauseOp = sciop;
                super();
            }
                }
);
                addOp(pauseOp);
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
        session.setSonosStatePlaying(false);
    }
}
