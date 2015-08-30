// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.media.SonosMediaItem;
import com.sonos.acr.media.SonosRouteTransport;
import com.sonos.sclib.SCIOpCBSwigBase;

// Referenced classes of package com.sonos.acr.media.session:
//            SonosSessionCommand, SonosRouteSession

public class SeekSessionCommand extends SonosSessionCommand
{

    public SeekSessionCommand(long l, SonosMediaItem sonosmediaitem, SonosRouteSession sonosroutesession, SCIOpCBSwigBase sciopcbswigbase)
    {
        super(SonosSessionCommand.SessionCommandType.SCMD_SEEK, sonosroutesession, sciopcbswigbase);
        contentPositionInMillis = l;
        if(sonosmediaitem != null)
            registerAsTrackPositionSourceForItem(sonosmediaitem);
    }

    public void run()
    {
        session.transport.seekTo(contentPositionInMillis, new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                completeSessionCommand(l, i);
            }

            final SeekSessionCommand this$0;

            
            {
                this$0 = SeekSessionCommand.this;
                super();
            }
        }
, getOps());
    }
}
