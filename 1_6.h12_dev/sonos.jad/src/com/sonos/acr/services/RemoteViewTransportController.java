// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services;

import android.content.Intent;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.services:
//            SonosService

public class RemoteViewTransportController
{

    public RemoteViewTransportController(SonosService sonosservice)
    {
        service = sonosservice;
    }

    private void executeTransportOperation(final ZoneGroup zoneGroup, final SCINowPlayingTransport transport, SCIOp sciop)
    {
        if(sciop != null)
        {
            currentOp = sciop;
            currentOp._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    SLog.e(RemoteViewTransportController.LOG_TAG, (new StringBuilder()).append("Transport Operation Completed: ").append(i).toString());
                    currentOp = null;
                    if(i == 800)
                    {
                        String as[] = new String[1];
                        transport.getErrorString(SCNPTransportErrorID.SC_NP_ERR_SKIP_LIMIT.swigValue(), as);
                        if(service != null)
                            service.onZoneGroupMessage(zoneGroup, as[0], as[0], 5000L);
                    }
                }

                final RemoteViewTransportController this$0;
                final SCINowPlayingTransport val$transport;
                final ZoneGroup val$zoneGroup;

            
            {
                this$0 = RemoteViewTransportController.this;
                transport = scinowplayingtransport;
                zoneGroup = zonegroup;
                super();
            }
            }
);
        }
    }

    public void handleIntent(Intent intent)
    {
        if(intent != null)
        {
            String s = intent.getAction();
            if("com.sonos.intent.action.TRANSPORT_NEXT".equals(s) || "com.sonos.intent.action.TRANSPORT_PLAYPAUSE".equals(s) || "com.sonos.intent.action.TRANSPORT_PREV".equals(s) || "com.sonos.intent.action.VOTE".equals(s))
                handleNowPlayingOperation(s, intent);
        }
    }

    public void handleNowPlayingOperation(String s, Intent intent)
    {
        String s1;
        com.sonos.sclib.SCIAppReporting.SCViewID scviewid;
        s1 = intent.getStringExtra("com.sonos.intent.extra.ZGID");
        scviewid = (com.sonos.sclib.SCIAppReporting.SCViewID)intent.getSerializableExtra("com.sonos.intent.extra.VIEW_ID");
        if(!StringUtils.isNotEmptyOrNull(s1) || currentOp != null) goto _L2; else goto _L1
_L1:
        final ZoneGroup zoneGroup;
        SLog.d(LOG_TAG, (new StringBuilder()).append("handleNowPlayingOperation: ").append(s).append(" zgid: ").append(s1).toString());
        zoneGroup = LibraryUtils.getHousehold().lookupZoneGroup(s1);
        if(zoneGroup == null) goto _L2; else goto _L3
_L3:
        final SCINowPlayingTransport transport = zoneGroup.nowPlaying.getTransport();
        if(!"com.sonos.intent.action.TRANSPORT_NEXT".equals(s)) goto _L5; else goto _L4
_L4:
        if(transport.isNextTrackEnabled())
        {
            executeTransportOperation(zoneGroup, transport, transport.createNextTrackOp());
            if(scviewid != null)
                sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NEXT);
        }
_L2:
        return;
_L5:
        if("com.sonos.intent.action.TRANSPORT_PREV".equals(s))
        {
            if(transport.isPreviousTrackEnabled())
            {
                final SCIOpGetTrackPositionInfo gtpiop = transport.createGetTrackPositionInfoOp();
                if(gtpiop != null)
                {
                    currentOp = gtpiop;
                    gtpiop._start(new SCIOpCBSwigBase() {

                        public void _operationComplete(long l, int k)
                        {
                            currentOp = null;
                            if(k == 0)
                                executeTransportOperation(zoneGroup, transport, transport.createRewindToStartOrPrevTrackOp(gtpiop.getTrackPositionInSecs()));
                            else
                                executeTransportOperation(zoneGroup, transport, transport.createPrevTrackOp());
                        }

                        final RemoteViewTransportController this$0;
                        final SCIOpGetTrackPositionInfo val$gtpiop;
                        final SCINowPlayingTransport val$transport;
                        final ZoneGroup val$zoneGroup;

            
            {
                this$0 = RemoteViewTransportController.this;
                zoneGroup = zonegroup;
                transport = scinowplayingtransport;
                gtpiop = sciopgettrackpositioninfo;
                super();
            }
                    }
);
                } else
                {
                    executeTransportOperation(zoneGroup, transport, transport.createPrevTrackOp());
                }
                if(scviewid != null)
                    sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_PREV);
            }
        } else
        if("com.sonos.intent.action.TRANSPORT_PLAYPAUSE".equals(s))
        {
            executeTransportOperation(zoneGroup, transport, transport.createTogglePlayPauseOp());
            if(scviewid != null)
                sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_PLAYBACK);
        } else
        if("com.sonos.intent.action.VOTE".equals(s))
            if(zoneGroup.nowPlaying.getSourceType() == SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            {
                int j = intent.getIntExtra("com.sonos.intent.extra.VOTE_ID", -1);
                if(j == 0)
                {
                    NowPlaying nowplaying1 = zoneGroup.nowPlaying;
                    boolean flag1;
                    if(!zoneGroup.nowPlaying.getNightMode())
                        flag1 = true;
                    else
                        flag1 = false;
                    nowplaying1.setNightMode(flag1);
                    sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NIGHT_MODE);
                } else
                if(j == 1)
                {
                    NowPlaying nowplaying = zoneGroup.nowPlaying;
                    boolean flag;
                    if(!zoneGroup.nowPlaying.getTVDialogEnhancement())
                        flag = true;
                    else
                        flag = false;
                    nowplaying.setTVDialogEnhancement(flag);
                    sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_TV_DIALOG_ENH);
                }
            } else
            {
                int i = intent.getIntExtra("com.sonos.intent.extra.VOTE_ID", -1);
                SCINowPlayingRatings scinowplayingratings = zoneGroup.nowPlaying.getRatings();
                if(scinowplayingratings != null && i != -1)
                {
                    if(scinowplayingratings.numberOfRatings() == 1 && i == 1)
                        i = 0;
                    SCIActionContext sciactioncontext = scinowplayingratings.getActionForRating(i);
                    if(sciactioncontext != null)
                    {
                        sciactioncontext.getPropertyBag().setStrProp("ZONEGROUP_ID", zoneGroup.getID());
                        sciactioncontext.perform();
                        sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, scviewid, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_RATINGS);
                    }
                }
            }
        if(true) goto _L2; else goto _L6
_L6:
    }

    public static final String ACTION_TRANSPORT_NEXT = "com.sonos.intent.action.TRANSPORT_NEXT";
    public static final String ACTION_TRANSPORT_PLAYPAUSE = "com.sonos.intent.action.TRANSPORT_PLAYPAUSE";
    public static final String ACTION_TRANSPORT_PREV = "com.sonos.intent.action.TRANSPORT_PREV";
    public static final String ACTION_VOTE = "com.sonos.intent.action.VOTE";
    public static final String EXTRA_VIEWID = "com.sonos.intent.extra.VIEW_ID";
    public static final String EXTRA_VOTE_ID = "com.sonos.intent.extra.VOTE_ID";
    public static final String EXTRA_ZONEDEVICE_ID = "com.sonos.intent.extra.ZDID";
    public static final String EXTRA_ZONEGROUP_ID = "com.sonos.intent.extra.ZGID";
    public static final String LOG_TAG = com/sonos/acr/services/RemoteViewTransportController.getSimpleName();
    private SCIOp currentOp;
    SonosService service;



/*
    static SCIOp access$002(RemoteViewTransportController remoteviewtransportcontroller, SCIOp sciop)
    {
        remoteviewtransportcontroller.currentOp = sciop;
        return sciop;
    }

*/

}
