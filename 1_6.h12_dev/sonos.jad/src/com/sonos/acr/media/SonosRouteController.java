// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.support.v7.media.*;
import com.sonos.acr.media.session.SonosRouteSession;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.acr.volume.ContinuousVolumeHelper;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.media:
//            SonosRouteEventListener, SonosRouteTransport, SonosRouteTrackBoundaryAlarm, SonosRouteMetrics, 
//            SonosRouteError, SonosMediaItem, MRPLog, SonosRouteProvider, 
//            SonosRoutePlayerInfo, SonosMetadata, SonosRouteEventSink, SonosZoneGroupStateParser, 
//            SonosHouseholdZoneGroupState

public class SonosRouteController extends android.support.v7.media.MediaRouteProvider.RouteController
    implements SonosRouteEventListener
{

    public SonosRouteController(SonosRouteProvider sonosrouteprovider, Context context1, String s)
    {
        lostControl = false;
        getZoneGroupStateOp = null;
        session = null;
        controllerStarted = false;
        attachedToSonosGroup = true;
        publishThisRouteWhenDetached = true;
        currentTrackIndexInAVT = -1;
        routeMetrics = null;
        groupName = "";
        wasErrorReported = false;
        errorReportTimestamp = 0L;
        routeSelectedCount = 0;
        invalidatedGroupOnHouseholdEventsWithOrphanedGroups = false;
        detachedTimeoutRunnable = new Runnable() {

            public void run()
            {
                SLog.d("SonosRouteController", "detached from group, timer expired, invalidate and unpublish");
                discardCurrentSession();
                publishThisRouteWhenDetached = false;
                routeProvider.publishSonosRoutes();
            }

            final SonosRouteController this$0;

            
            {
                this$0 = SonosRouteController.this;
                super();
            }
        }
;
        routeProvider = sonosrouteprovider;
        context = context1;
        routeId = s;
        sonosTransport = new SonosRouteTransport(context1, s, this);
        mHandler = new Handler(context1.getMainLooper());
        trackBoundaryAlarm = new SonosRouteTrackBoundaryAlarm(context1);
        routeMetrics = new SonosRouteMetrics(s);
        continuousVolumeHelper = new ContinuousVolumeHelper(s);
        continuousVolumeHelper.setContinousVolumeCallback(new com.sonos.acr.volume.ContinuousVolumeHelper.ContinousVolumeCallback() {

            public void onVolumeChanged(int i, boolean flag)
            {
                updateSonosRouteVolume(i, flag);
            }

            final SonosRouteController this$0;

            
            {
                this$0 = SonosRouteController.this;
                super();
            }
        }
);
        SLog.d("SonosRouteController", (new StringBuilder()).append(routeId).append(": Controller created").toString());
    }

    private void addSessionStatusToBundle(Bundle bundle)
    {
        if(!$assertionsDisabled && session == null)
            throw new AssertionError();
        android.support.v7.media.MediaSessionStatus.Builder builder = new android.support.v7.media.MediaSessionStatus.Builder(session.getSessionState());
        boolean flag;
        if(!session.isAppStatePlaying())
            flag = true;
        else
            flag = false;
        builder.setQueuePaused(flag);
        bundle.putBundle("android.media.intent.extra.SESSION_STATUS", builder.build().asBundle());
    }

    private static int adjustVolumeDeltaForUsabilityReasons(int i, int j)
    {
        int k = Math.abs(i);
        int l = k;
        if(j > 20)
        {
            if(k < 4)
                l = 4;
        } else
        if(j > 10)
            l = 2;
        else
            l = 1;
        if(i <= 0)
            l = -l;
        return l;
    }

    private void attachToQueue()
    {
        if(session != null)
            session.attachQueue(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i != 0)
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Error ").append(i).append(" attaching private queue!").toString());
                        onLossOfControl("Failed to attach to private queue.");
                    }
                }

                final SonosRouteController this$0;

            
            {
                this$0 = SonosRouteController.this;
                super();
            }
            }
);
    }

    private void checkZoneGroupStateForDroppedCoordinator(Runnable runnable, String s, String s1)
    {
        checkZoneGroupStateForDroppedCoordinator(runnable, s, s1, 0);
    }

    private void checkZoneGroupStateForDroppedCoordinator(final Runnable onCoordinatorNotDropped, final String expectedCoordinatorId, final String groupId, final int tries)
    {
        if(getZoneGroupStateOp != null)
        {
            SLog.d("SonosRouteController", "canceling outstanding getZoneGroupState event");
            getZoneGroupStateOp._cancel();
            getZoneGroupStateOp = null;
        }
        SLog.d("SonosRouteController", (new StringBuilder()).append("expect group '").append(groupId).append("' to have coordinator '").append(expectedCoordinatorId).append("'").toString());
        final SCIOpZoneGroupTopologyGetZoneGroupState op = LibraryUtils.getHousehold().createGetZoneGroupStateOp(expectedCoordinatorId);
        if(op != null)
        {
            getZoneGroupStateOp = op;
            op._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i != 0) goto _L2; else goto _L1
_L1:
                    String s = op.getZoneGroupState();
                    String s1 = (new SonosZoneGroupStateParser()).parse(s).masterIdForGroup(groupId);
                    SLog.d("SonosRouteController", (new StringBuilder()).append("group '").append(groupId).append("' has coordinator '").append(s1).append("'").toString());
                    boolean flag;
                    StringBuilder stringbuilder;
                    String s2;
                    if(!expectedCoordinatorId.equals(s1))
                        flag = true;
                    else
                        flag = false;
                    stringbuilder = (new StringBuilder()).append("coordinator was ");
                    if(flag)
                        s2 = "";
                    else
                        s2 = "not ";
                    SLog.d("SonosRouteController", stringbuilder.append(s2).append("dropped").toString());
                    if(!flag && onCoordinatorNotDropped != null)
                        onCoordinatorNotDropped.run();
_L4:
                    getZoneGroupStateOp = null;
                    return;
_L2:
                    SLog.d("SonosRouteController", (new StringBuilder()).append("GetZoneGroupState res=").append(i).toString());
                    if(SonosRouteController.ERROR_CODES_TO_RETRY.contains(Integer.valueOf(i)) && tries < 3)
                    {
                        SLog.d("SonosRouteController", (new StringBuilder()).append("retrying GetZoneGroupState, attempt ").append(1 + tries).append("...").toString());
                        mHandler.postDelayed(new Runnable() {

                            public void run()
                            {
                                checkZoneGroupStateForDroppedCoordinator(onCoordinatorNotDropped, expectedCoordinatorId, groupId, 1 + tries);
                            }

                            final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                        }
, 50 * (1 + tries));
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final SonosRouteController this$0;
                final String val$expectedCoordinatorId;
                final String val$groupId;
                final Runnable val$onCoordinatorNotDropped;
                final SCIOpZoneGroupTopologyGetZoneGroupState val$op;
                final int val$tries;

            
            {
                this$0 = SonosRouteController.this;
                op = sciopzonegrouptopologygetzonegroupstate;
                groupId = s;
                expectedCoordinatorId = s1;
                onCoordinatorNotDropped = runnable;
                tries = i;
                super();
            }
            }
);
        }
    }

    private void cleanup()
    {
        if(controllerStarted)
        {
            controllerStarted = false;
            if(session != null)
                discardCurrentSession();
            else
                sonosTransport.cancelAllOps();
            if(!lostControl)
                sonosTransport.clearTransport();
            unsubscribe();
            continuousVolumeHelper.cleanup();
            getHandler().removeCallbacks(detachedTimeoutRunnable);
            trackBoundaryAlarm.disable();
        }
    }

    private void createNewSession(String s, PendingIntent pendingintent)
    {
        discardCurrentSession();
        session = new SonosRouteSession(context, routeId, sonosTransport, s, pendingintent);
        routeMetrics.reportNewSession(s);
    }

    private void discardCurrentSession()
    {
        if(session != null)
        {
            session.discard();
            session = null;
        }
    }

    private void executeCreateSessionAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        PendingIntent pendingintent = (PendingIntent)intent.getParcelableExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER");
        if(pendingintent != null) goto _L2; else goto _L1
_L1:
        controlrequestcallback.onError(SonosRouteError.MISSING_PENDING_INTENT.getLocString(context, new String[0]), null);
        SLog.w("SonosRouteController", "No PendingIntent provided to receive session status updates, cannot create session");
_L4:
        return;
_L2:
        String s = getClientIdFromPendingIntent(pendingintent);
        if(!isSupportedClientId(s))
        {
            SLog.e("SonosRouteController", "Client application was rejected.");
            controlrequestcallback.onError(SonosRouteError.CLIENT_APP_REJECTED.getLocString(context, new String[0]), null);
        } else
        {
            createNewSession(s, pendingintent);
            session.createSonosSessionIfNecessary(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(i != 0)
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Error ").append(i).append(" creating private queue!").toString());
                        session.updateAndReport(2, (new StringBuilder()).append("Error ").append(i).append(" creating private queue").toString());
                    } else
                    {
                        session.updateAndReport(0, "Session successfully created");
                    }
                }

                final SonosRouteController this$0;

            
            {
                this$0 = SonosRouteController.this;
                super();
            }
            }
);
            if(controlrequestcallback != null)
            {
                Bundle bundle = new Bundle();
                bundle.putString("android.media.intent.extra.SESSION_ID", session.getSessionId());
                addSessionStatusToBundle(bundle);
                controlrequestcallback.onResult(bundle);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void executeEndSessionAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        Bundle bundle = null;
        if(controlrequestcallback != null)
        {
            bundle = new Bundle();
            bundle.putString("android.media.intent.extra.SESSION_ID", session.getSessionId());
            addSessionStatusToBundle(bundle);
        }
        discardCurrentSession();
        if(controlrequestcallback != null)
            controlrequestcallback.onResult(bundle);
    }

    private void executeEnqueueAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        executeEnqueueAndPlayAction(zonegroup, intent, false, controlrequestcallback);
    }

    private void executeEnqueueAndPlayAction(ZoneGroup zonegroup, Intent intent, boolean flag, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        PendingIntent pendingintent;
        Bundle bundle;
        Bundle bundle1;
        String s;
        pendingintent = (PendingIntent)intent.getParcelableExtra("android.media.intent.extra.ITEM_STATUS_UPDATE_RECEIVER");
        bundle = intent.getBundleExtra("android.media.intent.extra.ITEM_METADATA");
        bundle1 = intent.getBundleExtra("android.media.intent.extra.HTTP_HEADERS");
        s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        if(s == null || session != null && s.equals(session.getSessionId())) goto _L2; else goto _L1
_L1:
        if(controlrequestcallback != null)
        {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("android.media.intent.extra.ERROR_CODE", 2);
            controlrequestcallback.onError(SonosRouteError.INVALID_SESSION_ID.getLocString(context, new String[0]), bundle3);
        }
        if(session != null)
            SLog.e("SonosRouteController", (new StringBuilder()).append("Invalid sessionID=").append(s).append(" received, discarding control request.").toString());
        else
            SLog.e("SonosRouteController", (new StringBuilder()).append("Non-null sessionID=").append(s).append(" received, but there is no current session.").toString());
_L8:
        return;
_L2:
        if(s != null) goto _L4; else goto _L3
_L3:
        SLog.d("SonosRouteController", "sessionId=null received, creating new session");
        if(pendingintent == null)
        {
            controlrequestcallback.onError(SonosRouteError.MISSING_PENDING_INTENT.getLocString(context, new String[0]), null);
            SLog.w("SonosRouteController", "No PendingIntent provided to receive status updates, cannot create session");
            continue; /* Loop/switch isn't completed */
        }
        String s5 = getClientIdFromPendingIntent(pendingintent);
        if(!isSupportedClientId(s5))
        {
            SLog.e("SonosRouteController", "Client application was rejected.");
            controlrequestcallback.onError(SonosRouteError.CLIENT_APP_REJECTED.getLocString(context, new String[0]), null);
            continue; /* Loop/switch isn't completed */
        }
        createNewSession(s5, null);
_L6:
        if(!$assertionsDisabled && session == null)
            throw new AssertionError();
        break; /* Loop/switch isn't completed */
_L4:
        if(flag && session != null)
            session.clearQueueIfNecessary();
        if(true) goto _L6; else goto _L5
_L5:
        String s1 = intent.getType();
        final long contentPositionMillis = intent.getLongExtra("android.media.intent.extra.ITEM_POSITION", 0L);
        final SonosMediaItem newItem = new SonosMediaItem(intent.getData(), bundle, s1, bundle1, pendingintent, context, session.getSessionId(), contentPositionMillis, sonosTransport);
        String s2 = "";
        if(bundle1 != null)
        {
            for(Iterator iterator = bundle1.keySet().iterator(); iterator.hasNext();)
            {
                String s4 = (String)iterator.next();
                if(s2.length() > 0)
                    s2 = (new StringBuilder()).append(s2).append(", ").toString();
                s2 = (new StringBuilder()).append(s2).append(s4).toString();
            }

        }
        StringBuilder stringbuilder = (new StringBuilder()).append(routeId).append(": Received play/enqueue request, sessionID=").append(s).append(", itemID=").append(newItem.getId()).append(", uri=").append(newItem.getUri().toString()).append(", mimeType=").append(s1).append(", contentPositionMillis=").append(contentPositionMillis).append(", metadata=").append(bundle).append(", statusUpdate=");
        String s3;
        ZoneDevice zonedevice;
        SonosRouteSession sonosroutesession;
        SCIOpCBSwigBase sciopcbswigbase;
        if(pendingintent != null)
            s3 = "set";
        else
            s3 = "not set";
        MRPLog.i("SonosRouteController", stringbuilder.append(s3).append(", httpHeaderKeys=").append(s2).toString().replace("android.media.metadata.", ""));
        zonedevice = zonegroup.getMasterDevice();
        if(zonedevice != null)
        {
            SonosRoutePlayerInfo sonosrouteplayerinfo = routeProvider.getRoutePlayerInfo(zonedevice.getId());
            if(sonosrouteplayerinfo != null)
            {
                ArrayList arraylist = sonosrouteplayerinfo.getMimeTypesForHTTP();
                if(arraylist != null && !arraylist.contains(s1))
                    SLog.e("SonosRouteController", (new StringBuilder()).append("MIME type ").append(s1).append(" is not supported!").toString());
            }
        }
        session.createSonosSessionIfNecessary(new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                if(i != 0)
                {
                    SLog.e("SonosRouteController", (new StringBuilder()).append("Error ").append(i).append(" creating private queue!").toString());
                    newItem.updateAndReport(7, "Error creating Sonos session");
                    session.updateAndReport(2, (new StringBuilder()).append("Error ").append(i).append(" creating private queue").toString());
                } else
                {
                    session.updateAndReport(0, "Session successfully created");
                }
            }

            final SonosRouteController this$0;
            final SonosMediaItem val$newItem;

            
            {
                this$0 = SonosRouteController.this;
                newItem = sonosmediaitem;
                super();
            }
        }
);
        sonosroutesession = session;
        sciopcbswigbase = new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                SLog.d("SonosRouteController", (new StringBuilder()).append("appendItemToQueueAndPlay completed, res=").append(i).append(", position=").append(contentPositionMillis).toString());
                if(i == 0)
                    newItem.updateAndReport(newItem.getPlaybackState(), null);
                else
                if(!session.isResyncPending())
                    newItem.updateAndReport(7, (new StringBuilder()).append("Play to Sonos failed with code ").append(i).toString());
                else
                    newItem.updateAndReport(0, (new StringBuilder()).append("Received recoverable error ").append(i).append(", attempting to resync").toString());
            }

            final SonosRouteController this$0;
            final long val$contentPositionMillis;
            final SonosMediaItem val$newItem;

            
            {
                this$0 = SonosRouteController.this;
                contentPositionMillis = l;
                newItem = sonosmediaitem;
                super();
            }
        }
;
        sonosroutesession.appendItemToQueueAndPlay(newItem, flag, contentPositionMillis, sciopcbswigbase);
        if(controlrequestcallback != null)
        {
            android.support.v7.media.MediaItemStatus.Builder builder = new android.support.v7.media.MediaItemStatus.Builder(0);
            builder.setContentPosition(contentPositionMillis);
            MediaItemStatus mediaitemstatus = builder.build();
            Bundle bundle2 = new Bundle();
            SLog.d("SonosRouteController", (new StringBuilder()).append("Sending sessionID = ").append(session.getSessionId()).toString());
            bundle2.putString("android.media.intent.extra.SESSION_ID", session.getSessionId());
            bundle2.putString("android.media.intent.extra.ITEM_ID", newItem.getId());
            bundle2.putBundle("android.media.intent.extra.ITEM_STATUS", mediaitemstatus.asBundle());
            addSessionStatusToBundle(bundle2);
            controlrequestcallback.onResult(bundle2);
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    private void executeGetSessionStatusAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        if(controlrequestcallback != null)
        {
            Bundle bundle = new Bundle();
            bundle.putString("android.media.intent.extra.SESSION_ID", session.getSessionId());
            addSessionStatusToBundle(bundle);
            controlrequestcallback.onResult(bundle);
        }
    }

    private void executeGetStatusAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        String s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        String s1 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
        SonosMediaItem sonosmediaitem = getCurrentItem();
        boolean flag;
        SonosMediaItem sonosmediaitem1;
        int i;
        android.support.v7.media.MediaItemStatus.Builder builder;
        long l;
        long l1;
        MediaItemStatus mediaitemstatus;
        Bundle bundle;
        if(sonosmediaitem != null && s1 != null && sonosmediaitem.getId().equals(s1))
            flag = true;
        else
            flag = false;
        if(flag)
            sonosmediaitem1 = sonosmediaitem;
        else
            sonosmediaitem1 = session.getItemWithId(s1);
        i = 2;
        if(sonosmediaitem1 != null && sonosmediaitem1.getLastStatusUpdate() != null)
            i = sonosmediaitem1.getLastStatusUpdate().getPlaybackState();
        builder = new android.support.v7.media.MediaItemStatus.Builder(i);
        if(sonosmediaitem1 == null)
            l = -1L;
        else
            l = sonosmediaitem1.getMetadata().getDurationInSecs();
        if(l != -1L)
            builder.setContentDuration(l * 1000L);
        if(flag)
            l1 = sonosmediaitem.getTrackPositionInMillisecs();
        else
            l1 = 0L;
        builder.setContentPosition(l1);
        mediaitemstatus = builder.build();
        bundle = new Bundle();
        bundle.putString("android.media.intent.extra.SESSION_ID", s);
        bundle.putString("android.media.intent.extra.ITEM_ID", s1);
        bundle.putBundle("android.media.intent.extra.ITEM_STATUS", mediaitemstatus.asBundle());
        addSessionStatusToBundle(bundle);
        controlrequestcallback.onResult(bundle);
    }

    private void executePlayAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        executeEnqueueAndPlayAction(zonegroup, intent, true, controlrequestcallback);
    }

    private void executeRemoveAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        boolean flag;
        String s;
        String s1;
        flag = false;
        s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        s1 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
        if(s1 != null) goto _L2; else goto _L1
_L1:
        SLog.e("SonosRouteController", "No item specified in ACTION_REMOVE!");
        controlrequestcallback.onError(SonosRouteError.NO_ITEM_SPECIFIED_IN_REMOVE_ACTION.getLocString(context, new String[0]), null);
_L4:
        return;
_L2:
        SonosMediaItem sonosmediaitem = getCurrentItem();
        if(sonosmediaitem != null && sonosmediaitem.getId().equals(s1))
            flag = true;
        StringBuilder stringbuilder = (new StringBuilder()).append(routeId).append(": Received remove status request, sessionID=").append(s).append(", itemID=").append(s1);
        String s2;
        if(flag)
            s2 = " (current)";
        else
            s2 = "";
        SLog.d("SonosRouteController", stringbuilder.append(s2).toString());
        session.removeItem(s1);
        if(controlrequestcallback != null)
        {
            int i;
            android.support.v7.media.MediaItemStatus.Builder builder;
            long l;
            MediaItemStatus mediaitemstatus;
            Bundle bundle;
            if(flag)
                i = getLastMediaItemPlaybackState();
            else
                i = 5;
            builder = new android.support.v7.media.MediaItemStatus.Builder(i);
            if(flag)
                l = sonosmediaitem.getTrackPositionInMillisecs();
            else
                l = 0L;
            builder.setContentPosition(l);
            mediaitemstatus = builder.build();
            bundle = new Bundle();
            bundle.putString("android.media.intent.extra.SESSION_ID", s);
            bundle.putString("android.media.intent.extra.ITEM_ID", s1);
            bundle.putBundle("android.media.intent.extra.ITEM_STATUS", mediaitemstatus.asBundle());
            addSessionStatusToBundle(bundle);
            controlrequestcallback.onResult(bundle);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void executeSeekAction(ZoneGroup zonegroup, Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        String s;
        String s1;
        long l;
        s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        s1 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
        l = intent.getLongExtra("android.media.intent.extra.ITEM_POSITION", 0L);
        SonosMediaItem sonosmediaitem = getCurrentItem();
        boolean flag;
        if(sonosmediaitem != null && s1.equals(sonosmediaitem.getId()))
            flag = true;
        else
            flag = false;
        if(flag) goto _L2; else goto _L1
_L1:
        SLog.e("SonosRouteController", "Executing seek action on non-current item!");
        if(controlrequestcallback != null)
            controlrequestcallback.onError(SonosRouteError.CANNOT_SEEK_IN_ITEM.getLocString(context, new String[0]), null);
_L4:
        return;
_L2:
        session.seekTo(l);
        if(controlrequestcallback != null)
        {
            android.support.v7.media.MediaItemStatus.Builder builder = new android.support.v7.media.MediaItemStatus.Builder(3);
            builder.setContentPosition(l);
            MediaItemStatus mediaitemstatus = builder.build();
            Bundle bundle = new Bundle();
            bundle.putString("android.media.intent.extra.SESSION_ID", s);
            bundle.putString("android.media.intent.extra.ITEM_ID", s1);
            bundle.putBundle("android.media.intent.extra.ITEM_STATUS", mediaitemstatus.asBundle());
            addSessionStatusToBundle(bundle);
            controlrequestcallback.onResult(bundle);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String formatIntentAsString(Intent intent)
    {
        String s = intent.getAction();
        String s1 = s;
        if(s.startsWith("android.media.intent."))
            s1 = s.substring("android.media.intent.".length());
        Object aobj[] = new Object[3];
        aobj[0] = s1;
        aobj[1] = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        aobj[2] = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
        String s2 = String.format("%s: sessionID=%s, itemID=%s", aobj);
        if(s.equals("android.media.intent.action.PLAY") || s.equals("android.media.intent.action.ENQUEUE"))
        {
            Bundle bundle = intent.getBundleExtra("android.media.intent.extra.ITEM_METADATA");
            if(bundle != null)
            {
                StringBuilder stringbuilder1 = (new StringBuilder()).append(s2);
                Object aobj2[] = new Object[1];
                aobj2[0] = bundle.getString("android.media.metadata.TITLE");
                s2 = stringbuilder1.append(String.format(", title=%s", aobj2)).toString();
            }
            StringBuilder stringbuilder = (new StringBuilder()).append(s2);
            Object aobj1[] = new Object[2];
            aobj1[0] = intent.getType();
            aobj1[1] = intent.getData();
            s2 = stringbuilder.append(String.format(", type=%s, data=%s", aobj1)).toString();
        }
        if(s.equals("android.media.intent.action.SEEK"))
        {
            long l = intent.getLongExtra("android.media.intent.extra.ITEM_POSITION", 0L);
            s2 = (new StringBuilder()).append(s2).append(", trackPosMs=").append(l).toString();
        }
        return s2;
    }

    private String getClientIdFromPendingIntent(PendingIntent pendingintent)
    {
        if(!$assertionsDisabled && pendingintent == null)
            throw new AssertionError();
        String s1 = pendingintent.getCreatorPackage();
        String s = s1;
_L2:
        return s;
        NoSuchMethodError nosuchmethoderror;
        nosuchmethoderror;
        s = pendingintent.getTargetPackage();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Bundle getDefaultResultBundle()
    {
        return getDefaultResultBundle(getLastMediaItemPlaybackState());
    }

    private Bundle getDefaultResultBundle(int i)
    {
        android.support.v7.media.MediaItemStatus.Builder builder = new android.support.v7.media.MediaItemStatus.Builder(i);
        SonosMediaItem sonosmediaitem = getCurrentItem();
        long l;
        MediaItemStatus mediaitemstatus;
        Bundle bundle;
        if(sonosmediaitem != null)
            l = sonosmediaitem.getTrackPositionInMillisecs();
        else
            l = 0L;
        builder.setContentPosition(l);
        mediaitemstatus = builder.build();
        bundle = new Bundle();
        if(session != null)
        {
            SLog.d("SonosRouteController", (new StringBuilder()).append("Sending sessionID = ").append(session.getSessionId()).toString());
            bundle.putString("android.media.intent.extra.SESSION_ID", session.getSessionId());
            if(sonosmediaitem != null)
                bundle.putString("android.media.intent.extra.ITEM_ID", sonosmediaitem.getId());
        }
        bundle.putBundle("android.media.intent.extra.ITEM_STATUS", mediaitemstatus.asBundle());
        return bundle;
    }

    private Bundle getHttpResponseHeaders(SCIPropertyBag scipropertybag)
    {
        Bundle bundle = new Bundle();
        if(scipropertybag.getKeys().size() > 0L)
        {
            SCIStringArray scistringarray = scipropertybag.getKeys();
            for(int i = 0; (long)i < scistringarray.size(); i++)
            {
                String s = scistringarray.getAt(i);
                bundle.putString(s, scipropertybag.getStrProp(s));
            }

        }
        return bundle;
    }

    private int getItemPlaybackStateForSCPlaybackState(SCNPPlaybackState scnpplaybackstate)
    {
        int i = 0;
        class _cls10
        {

            static final int $SwitchMap$com$sonos$sclib$SCNPPlaybackState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState = new int[SCNPPlaybackState.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_UNKNOWN.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls10..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scnpplaybackstate.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 38
    //                   2 43
    //                   3 48;
           goto _L1 _L2 _L3 _L4
_L1:
        return i;
_L2:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 2;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private int getLastMediaItemPlaybackState()
    {
        SonosMediaItem sonosmediaitem;
        int i;
        if(session != null)
            sonosmediaitem = session.getCurrentItem();
        else
            sonosmediaitem = null;
        if(sonosmediaitem != null && sonosmediaitem.getLastStatusUpdate() != null)
            i = sonosmediaitem.getLastStatusUpdate().getPlaybackState();
        else
        if(sonosTransport.getPlaybackState() == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
            i = 1;
        else
            i = 0;
        return i;
    }

    private long getQueueId()
    {
        long l;
        if(session == null)
            l = -1L;
        else
            l = session.getQueueId();
        return l;
    }

    private boolean isSupportedClientId(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        flag = s.equals("com.google.android.music");
        if(!flag)
        {
            String s1 = context.getResources().getString(0x7f0c0036);
            if(s1.equals("ALPHA") || s1.equals("BETA"))
                flag = s.equals("com.sonos.samples.simpleplayer");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void onLossOfControl(String s)
    {
        if(!lostControl)
        {
            lostControl = true;
            SLog.e("SonosRouteController", (new StringBuilder()).append("lost control of route ").append(routeId).append(": ").append(s).toString());
            discardCurrentSession();
            routeProvider.publishSonosRoutes();
        }
    }

    private void startup()
    {
        if(!controllerStarted)
        {
            controllerStarted = true;
            lostControl = false;
            sonosTransport.clearTransport();
            sonosTransport.flushAllExpectedUris();
            subscribe();
        }
    }

    private void subscribe()
    {
        ZoneGroup zonegroup = getZoneGroup();
        if(zonegroup != null)
        {
            if(eventSink == null)
                eventSink = new SonosRouteEventSink(zonegroup);
            eventSink.addListener(this);
            onNowPlayingEvent(zonegroup.nowPlaying, SonosRouteEventListener.SonosRouteEvent.OnMusicChanged);
            onGroupVolumeChanged(zonegroup.getGroupVolume(), SonosRouteEventListener.SonosRouteEvent.OnGroupVolumeChanged);
        }
    }

    private void unsubscribe()
    {
        if(eventSink != null)
            eventSink.removeListener(this);
        eventSink = null;
    }

    private void updateSonosRouteVolume(int i, boolean flag)
    {
        routeProvider.onSonosRouteVolumeChanged(routeId, i, flag);
    }

    public void enterSimulatedPausedState()
    {
        sonosTransport.setPlaybackState(SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED);
        if(session != null)
        {
            SonosMediaItem sonosmediaitem = session.getCurrentItem();
            if(sonosmediaitem != null)
                sonosmediaitem.updateAndReport(2, "Entering simulated PAUSED state", 0, null, sonosmediaitem.getTrackPositionInMillisecs());
        }
    }

    public MediaRouteDescriptor getCachedRouteDescriptor()
    {
        return cachedRouteDescriptor;
    }

    protected SonosMediaItem getCurrentItem()
    {
        SonosMediaItem sonosmediaitem;
        if(session != null)
            sonosmediaitem = session.getCurrentItem();
        else
            sonosmediaitem = null;
        return sonosmediaitem;
    }

    public long getCurrentQueueId()
    {
        long l;
        if(session != null)
            l = session.getQueueId();
        else
            l = -1L;
        return l;
    }

    public String getCurrentQueueUri()
    {
        String s;
        if(session != null)
            s = session.getQueueUri();
        else
            s = null;
        return s;
    }

    public Handler getHandler()
    {
        return mHandler;
    }

    public boolean getPublishThisRouteWhenDetached()
    {
        return publishThisRouteWhenDetached;
    }

    public ZoneGroup getZoneGroup()
    {
        return sonosTransport.getZoneGroup();
    }

    public boolean onControlRequest(Intent intent, android.support.v7.media.MediaRouter.ControlRequestCallback controlrequestcallback)
    {
        boolean flag;
        flag = false;
        MRPLog.i("SonosRouteController", (new StringBuilder()).append(routeId).append(": Received control request ").append(formatIntentAsString(intent)).toString());
        if(intent.hasCategory("android.media.intent.category.REMOTE_PLAYBACK")) goto _L2; else goto _L1
_L1:
        SLog.e("SonosRouteController", "Sonos-MRP only handles CATEGORY_REMOTE_PLAYBACK intents");
        if(controlrequestcallback != null)
            controlrequestcallback.onError(SonosRouteError.UNSUPPORTED_INTENT_CATEGORY.getLocString(context, new String[0]), null);
_L4:
        return flag;
_L2:
        if(!attachedToSonosGroup)
        {
            SLog.e("SonosRouteController", (new StringBuilder()).append(routeId).append(": received an action for a detached route; invalidating route").toString());
            if(controlrequestcallback != null)
            {
                SonosRouteError sonosrouteerror1 = SonosRouteError.SONOS_GROUP_NOT_FOUND_FOR_ROUTE;
                Context context2 = context;
                String as1[] = new String[1];
                as1[flag] = groupName;
                controlrequestcallback.onError(sonosrouteerror1.getLocString(context2, as1), null);
            }
            discardCurrentSession();
            publishThisRouteWhenDetached = false;
            routeProvider.publishSonosRoutes();
            flag = true;
        } else
        {
            ZoneGroup zonegroup = getZoneGroup();
            if(zonegroup == null)
            {
                SLog.e("SonosRouteController", (new StringBuilder()).append("No Sonos group found for route ").append(routeId).toString());
                if(controlrequestcallback != null)
                {
                    SonosRouteError sonosrouteerror = SonosRouteError.SONOS_GROUP_NOT_FOUND_FOR_ROUTE;
                    Context context1 = context;
                    String as[] = new String[1];
                    as[flag] = groupName;
                    controlrequestcallback.onError(sonosrouteerror.getLocString(context1, as), null);
                }
                flag = true;
            } else
            if(intent.getAction().equals("android.media.intent.action.START_SESSION"))
            {
                executeCreateSessionAction(zonegroup, intent, controlrequestcallback);
                flag = true;
            } else
            if(intent.getAction().equals("android.media.intent.action.PLAY") && intent.getData() != null)
            {
                executePlayAction(zonegroup, intent, controlrequestcallback);
                flag = true;
            } else
            if(intent.getAction().equals("android.media.intent.action.ENQUEUE") && intent.getData() != null)
            {
                executeEnqueueAction(zonegroup, intent, controlrequestcallback);
                flag = true;
            } else
            {
                String s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
                if(session == null || s == null || !s.equals(session.getSessionId()))
                {
                    SLog.e("SonosRouteController", (new StringBuilder()).append("Invalid sessionID '").append(s).append("' received for action ").append(intent.getAction()).toString());
                    if(controlrequestcallback != null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putInt("android.media.intent.extra.ERROR_CODE", 2);
                        controlrequestcallback.onError(SonosRouteError.INVALID_SESSION_ID.getLocString(context, new String[0]), bundle);
                    }
                } else
                if(intent.getAction().equals("android.media.intent.action.END_SESSION"))
                {
                    executeEndSessionAction(zonegroup, intent, controlrequestcallback);
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.GET_SESSION_STATUS"))
                {
                    executeGetSessionStatusAction(zonegroup, intent, controlrequestcallback);
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.REMOVE"))
                {
                    executeRemoveAction(zonegroup, intent, controlrequestcallback);
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.PAUSE"))
                {
                    session.pause();
                    if(controlrequestcallback != null)
                        controlrequestcallback.onResult(getDefaultResultBundle());
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.RESUME"))
                {
                    session.play();
                    if(controlrequestcallback != null)
                        controlrequestcallback.onResult(getDefaultResultBundle());
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.STOP"))
                {
                    session.clearQueueIfNecessary();
                    if(controlrequestcallback != null)
                        controlrequestcallback.onResult(getDefaultResultBundle());
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.GET_STATUS"))
                {
                    executeGetStatusAction(zonegroup, intent, controlrequestcallback);
                    flag = true;
                } else
                if(intent.getAction().equals("android.media.intent.action.SEEK"))
                {
                    executeSeekAction(zonegroup, intent, controlrequestcallback);
                    flag = true;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onGroupVolumeChanged(GroupVolume groupvolume, SonosRouteEventListener.SonosRouteEvent sonosrouteevent)
    {
        if(sonosrouteevent.equals(SonosRouteEventListener.SonosRouteEvent.OnGroupVolumeChanged) && groupvolume != null && groupvolume.getGroupVolume() != null)
        {
            continuousVolumeHelper.setLastEventedVolume(groupvolume.getGroupVolume().getVolume());
            continuousVolumeHelper.setLastEventedGroupMutedState(groupvolume.getGroupVolume().isMuted());
            if(!continuousVolumeHelper.isVolumeTimerActive())
            {
                SLog.d("SonosRouteController", (new StringBuilder()).append(routeId).append(": onGroupVolumeChanged: ").append(continuousVolumeHelper.getLastEventedVolume()).toString());
                routeProvider.onSonosRouteVolumeChanged(routeId, continuousVolumeHelper.getLastEventedVolume(), continuousVolumeHelper.getLastEventedGroupMutedState());
            }
        }
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, SonosRouteEventListener.SonosRouteEvent sonosrouteevent)
    {
        if(!lostControl) goto _L2; else goto _L1
_L1:
        SLog.w("SonosRouteController", "received OnMusicChanged event after losing control, ignoring it.");
_L4:
        return;
_L2:
        if(!sonosrouteevent.equals(SonosRouteEventListener.SonosRouteEvent.OnMusicChanged))
            continue; /* Loop/switch isn't completed */
        boolean flag = false;
        String s = "";
        ZoneDevice zonedevice = nowplaying.getZoneGroup().getMasterDevice();
        String s1;
        String s2;
        String as[];
        if(zonedevice != null)
            s1 = zonedevice.getId();
        else
            s1 = "";
        s2 = nowplaying.getZoneGroup().getID();
        as = new String[1];
        as[0] = "";
        if(nowplaying.getTransport().getAvtURI(as) == SCRet.SC_RET_OK)
        {
            final String eventedUri = as[0];
            if(!sonosTransport.wasUriExpectedAndUpdateExpectedURIs(eventedUri))
            {
                SLog.d("SonosRouteController", (new StringBuilder()).append("AVT URI (").append(eventedUri).append(") was unexpected, calling GetZoneGroupState to check for dropped coordinator").toString());
                checkZoneGroupStateForDroppedCoordinator(new Runnable() {

                    public void run()
                    {
                        SLog.d("SonosRouteController", "group coordinator was not dropped, we lost control");
                        onLossOfControl((new StringBuilder()).append("Transport URI has changed on ").append(routeId).append(" to unexpected URI ").append(eventedUri).toString());
                    }

                    final SonosRouteController this$0;
                    final String val$eventedUri;

            
            {
                this$0 = SonosRouteController.this;
                eventedUri = s;
                super();
            }
                }
, s1, s2);
                continue; /* Loop/switch isn't completed */
            }
        }
        boolean flag1 = sonosTransport.isExpectingStopped();
        SCNPPlaybackState scnpplaybackstate = sonosTransport.getPlaybackState();
        SCNPPlaybackState scnpplaybackstate1 = nowplaying.getTransport().getPlaybackState();
        if(scnpplaybackstate1 != scnpplaybackstate)
        {
            flag = true;
            s = (new StringBuilder()).append("Sonos Playback state changed from ").append(scnpplaybackstate).append(" to ").append(scnpplaybackstate1).append("; ").toString();
        }
        if(session != null && session.notifyCurrentCommandOfNowPlayingEvent(nowplaying) && scnpplaybackstate1 == SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED)
            flag1 = true;
        SonosMediaItem sonosmediaitem = getCurrentItem();
        long l;
        int i;
        boolean flag2;
        List list;
        String as1[];
        SonosMediaItem sonosmediaitem1;
        boolean flag3;
        String s3;
        int j;
        String s4;
        Bundle bundle;
        SonosMediaItem sonosmediaitem2;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        if(sonosmediaitem != null)
            l = sonosmediaitem.getTrackPositionInMillisecs();
        else
            l = -1L;
        sonosTransport.setPlaybackStateAndUpdateTransportTrackPosition(scnpplaybackstate1);
        i = getItemPlaybackStateForSCPlaybackState(scnpplaybackstate1);
        if(scnpplaybackstate1 == SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED && (scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING || scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED))
            flag2 = true;
        else
            flag2 = false;
        if(sonosmediaitem != null && flag2 && !flag1)
        {
            wasErrorReported = false;
            errorReportTimestamp = 0L;
            checkZoneGroupStateForDroppedCoordinator(new Runnable() {

                public void run()
                {
                    boolean flag8;
                    if(wasErrorReported && SystemClock.elapsedRealtime() - errorReportTimestamp < 3000L)
                        flag8 = true;
                    else
                        flag8 = false;
                    if(flag8)
                    {
                        SLog.d("SonosRouteController", "Queue has stopped on error");
                    } else
                    {
                        SLog.d("SonosRouteController", "Queue has finished");
                        session.markQueueAsFinished();
                    }
                }

                final SonosRouteController this$0;

            
            {
                this$0 = SonosRouteController.this;
                super();
            }
            }
, s1, s2);
            flag = false;
            i = 0;
        }
        list = null;
        as1 = new String[1];
        as1[0] = "";
        if(nowplaying.sciNowPlayingSrc.getProperty(SCNPMetadataType.SC_NP_META_CURRENT_TRACK.swigValue(), as1) == SCRet.SC_RET_OK)
        {
            int k = Integer.parseInt(as1[0]);
            if(k != currentTrackIndexInAVT)
            {
                currentTrackIndexInAVT = k;
                if(session != null)
                    list = session.getFinishedItemsOnCurrentIndexChange(k - 1);
            }
        }
        sonosmediaitem1 = getCurrentItem();
        if(sonosmediaitem1 != null && sonosmediaitem != null && !sonosmediaitem1.getId().equals(sonosmediaitem))
            flag3 = true;
        else
            flag3 = false;
        if(flag3)
        {
            l = -1L;
            flag = true;
        }
        s3 = nowplaying.getAsynchronousErrorString();
        j = nowplaying.getTransport().getTransportErrorHttpCode();
        s4 = nowplaying.getTransport().getTransportErrorURI();
        bundle = getHttpResponseHeaders(nowplaying.getTransport().getTransportErrorHttpHeaders());
        if(!StringUtils.isEmptyOrNull(s3))
            MRPLog.i("SonosRouteController", (new StringBuilder()).append("getAsynchronousErrorString() returned: '").append(s3).append("'").toString());
        if(session != null && !StringUtils.isEmptyOrNull(s4))
            sonosmediaitem2 = session.getItemWithUri(s4);
        else
            sonosmediaitem2 = null;
        flag4 = false;
        if(sonosmediaitem2 != null)
            if(sonosmediaitem1 != null && sonosmediaitem2.getId().equals(sonosmediaitem1.getId()))
                flag4 = true;
            else
                flag4 = false;
        if(j >= 400)
            flag5 = true;
        else
            flag5 = false;
        if(j == 0 && !StringUtils.isEmptyOrNull(s3))
            flag6 = true;
        else
            flag6 = false;
        if(flag5 || flag6)
        {
            if(sonosmediaitem2 != null)
            {
                if(flag4)
                {
                    wasErrorReported = true;
                    errorReportTimestamp = SystemClock.elapsedRealtime();
                    if(flag5)
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Sonos player reported HTTP error ").append(j).append(" on the current item with itemID=").append(sonosmediaitem2.getId()).toString());
                        s = (new StringBuilder()).append(s).append("HTTP error ").append(j).append("; ").toString();
                    } else
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Sonos player reported transport error on current item with itemID=").append(sonosmediaitem2.getId()).toString());
                        s = (new StringBuilder()).append(s).append("Transport error: ").append(s3).append("; ").toString();
                    }
                    i = 7;
                    l = sonosmediaitem1.getCachedTrackPositionInMillisecs();
                    flag = true;
                    if(list != null && list.contains(sonosmediaitem2))
                        list.remove(sonosmediaitem2);
                } else
                {
                    long l3;
                    if(flag5)
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Sonos player reported HTTP error ").append(j).append(" on itemID=").append(sonosmediaitem2.getId()).toString());
                        s = (new StringBuilder()).append(s).append("HTTP error ").append(j).append("; ").toString();
                    } else
                    {
                        SLog.e("SonosRouteController", (new StringBuilder()).append("Sonos player reported transport error on itemID=").append(sonosmediaitem2.getId()).toString());
                        s = (new StringBuilder()).append(s).append("Transport error: ").append(s3).append("; ").toString();
                    }
                    if(sonosmediaitem2.getCachedTrackPositionInMillisecs() >= 0L)
                        l3 = sonosmediaitem2.getCachedTrackPositionInMillisecs();
                    else
                        l3 = 0L;
                    sonosmediaitem2.updateAndReport(7, s, j, bundle, l3);
                    j = 0;
                    bundle = null;
                }
            } else
            if(StringUtils.isEmptyOrNull(s4))
                SLog.e("SonosRouteController", "Cannot report HTTP or transport error: no transport error URI found!");
            else
                SLog.e("SonosRouteController", (new StringBuilder()).append("Cannot report HTTP or transport error: no media item found in session for uri=").append(s4).toString());
        } else
        if(j > 0)
            SLog.e("SonosRouteController", (new StringBuilder()).append("HTTP error ").append(j).append(" not reported").toString());
        if(list != null && list.size() > 0)
        {
            if(!$assertionsDisabled && list.contains(sonosmediaitem1))
                throw new AssertionError();
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                SonosMediaItem sonosmediaitem3 = (SonosMediaItem)iterator.next();
                long l2;
                if(sonosmediaitem3.getCachedTrackPositionInMillisecs() >= 0L)
                    l2 = sonosmediaitem3.getCachedTrackPositionInMillisecs();
                else
                    l2 = 0L;
                sonosmediaitem3.updateAndReport(4, s, 0, null, l2);
            }
        }
        if(flag)
        {
            if(sonosmediaitem1 != null)
            {
                long l1;
                SonosRouteSession sonosroutesession;
                boolean flag7;
                if(l >= 0L)
                    l1 = l;
                else
                    l1 = sonosmediaitem1.getTrackPositionInMillisecs();
                sonosmediaitem1.updateAndReport(i, s, j, bundle, l1);
                sonosroutesession = session;
                if(i != 2)
                    flag7 = true;
                else
                    flag7 = false;
                sonosroutesession.setAppStatePlaying(flag7);
                session.updateAndReport(session.getSessionState(), s);
            }
            if(routeMetrics != null)
                routeMetrics.updateRoutePlayState(i);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onRelease()
    {
        MRPLog.i("SonosRouteController", (new StringBuilder()).append(routeId).append(": Controller released").toString());
        if(routeProvider.routeReleased(routeId))
            cleanup();
    }

    public void onSelect()
    {
        MRPLog.i("SonosRouteController", (new StringBuilder()).append(routeId).append(": Selected").toString());
        int i = routeSelectedCount;
        routeSelectedCount = i + 1;
        if(i == 0)
        {
            startup();
            routeMetrics.reportRouteSelected();
        }
    }

    public void onSetVolume(int i)
    {
        ZoneGroup zonegroup = getZoneGroup();
        continuousVolumeHelper.onSetVolume(i, zonegroup);
    }

    protected void onTransportTrackPositionUpdated(long l, long l1, long l2, String s)
    {
        SonosMediaItem sonosmediaitem;
        int i;
        int j;
        int k;
        sonosmediaitem = getCurrentItem();
        boolean flag = false;
        if(sonosmediaitem != null)
        {
            int i1 = session.getIndexOfItemInQueue(sonosmediaitem.getId());
            String s1 = sonosmediaitem.getUri().toString();
            if(l2 > 0L && (long)i1 == l2 - 1L && s1.equals(s))
                flag = true;
            else
                flag = false;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        sonosmediaitem.onTransportTrackPositionUpdated(l, l1, getLastMediaItemPlaybackState());
        i = (int)(l1 / 1000L);
        j = sonosmediaitem.getMetadata().getDurationInSecs();
        k = j - i;
        getLastMediaItemPlaybackState();
        JVM INSTR tableswitch 0 3: default 144
    //                   0 151
    //                   1 151
    //                   2 144
    //                   3 151;
           goto _L3 _L4 _L4 _L3 _L4
_L3:
        return;
_L4:
        if(j > 0 && i >= 0 && k >= 0)
            trackBoundaryAlarm.setNextTrackBoundary(k);
        continue; /* Loop/switch isn't completed */
_L2:
        SLog.d("SonosRouteController", (new StringBuilder()).append("Track position update at ").append(l1).append("ms ignored: not for current item.").toString());
        if(sonosmediaitem != null && sonosmediaitem.getHasReceivedTransportTrackPositionUpdates() && l1 == 0L && l > 0L)
            sonosmediaitem.cacheTrackPosition(l);
        if(true) goto _L3; else goto _L5
_L5:
    }

    public void onUnselect()
    {
        MRPLog.i("SonosRouteController", (new StringBuilder()).append(routeId).append(": Unselected").toString());
        int i = -1 + routeSelectedCount;
        routeSelectedCount = i;
        if(i == 0)
        {
            routeMetrics.reportRouteDeselected();
            cleanup();
        }
        if(routeSelectedCount < 0)
        {
            SLog.e("SonosRouteController", "Unbalanced select/unselect count!");
            routeSelectedCount = 0;
        }
    }

    public void onUpdateVolume(int i)
    {
        SLog.d("SonosRouteController", (new StringBuilder()).append(routeId).append(": Update volume by ").append(i).toString());
        ZoneGroup zonegroup = getZoneGroup();
        if(zonegroup != null && zonegroup.getGroupVolume() != null)
        {
            DeviceVolume devicevolume = zonegroup.getGroupVolume().getGroupVolume();
            if(devicevolume != null && devicevolume.isVolumeAdjustable())
            {
                int j = adjustVolumeDeltaForUsabilityReasons(i, devicevolume.getVolume());
                devicevolume.setRelativeVolume(j);
                if(devicevolume.isMuted() && j + devicevolume.getVolume() > 0)
                {
                    SLog.v("SonosRouteController", (new StringBuilder()).append(routeId).append(": unmuting group (2)").toString());
                    devicevolume.setMute(false);
                }
            }
        }
    }

    public void onZoneGroupsChanged()
    {
        ZoneGroup zonegroup;
        zonegroup = sonosTransport.getZoneGroup();
        boolean flag;
        if(zonegroup == null)
            flag = true;
        else
            flag = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(LibraryUtils.householdHasTransientOrphanedGroups())
            invalidatedGroupOnHouseholdEventsWithOrphanedGroups = true;
        else
            onLossOfControl((new StringBuilder()).append("Sonos group ").append(routeId).append(" no longer exists.").toString());
_L4:
        return;
_L2:
        groupName = zonegroup.createSimpleZoneGroupTitle();
        if(invalidatedGroupOnHouseholdEventsWithOrphanedGroups)
        {
            invalidatedGroupOnHouseholdEventsWithOrphanedGroups = false;
            SLog.d("SonosRouteController", "Re-subscribing due to a previous household event with orphaned group");
            unsubscribe();
            sonosTransport.addExpectedUri("");
            subscribe();
            attachToQueue();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAttachedToSonosGroup(boolean flag)
    {
        if(!attachedToSonosGroup || flag) goto _L2; else goto _L1
_L1:
        getHandler().removeCallbacks(detachedTimeoutRunnable);
        long l = 0x927c0L;
        if(session != null && session.isSonosStatePlaying() && session.getNumItemsInQueue() > 0)
            l = session.getRemainingPlayTimeInMillis();
        SLog.d("SonosRouteController", (new StringBuilder()).append("detached from group; timer set to ").append(l).append(" millis").toString());
        getHandler().postDelayed(detachedTimeoutRunnable, l);
_L4:
        attachedToSonosGroup = flag;
        return;
_L2:
        if(flag && !attachedToSonosGroup)
        {
            SLog.d("SonosRouteController", "attached to group, canceling timer if applicable");
            getHandler().removeCallbacks(detachedTimeoutRunnable);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setCachedRouteDescriptor(MediaRouteDescriptor mediaroutedescriptor)
    {
        cachedRouteDescriptor = mediaroutedescriptor;
    }

    static final boolean $assertionsDisabled = false;
    private static final long DEFAULT_DETACHED_TIMEOUT_IN_MILLIS = 0x927c0L;
    public static final List ERROR_CODES_TO_RETRY;
    private static final String GOOGLE_PLAY_MUSIC_CLIENT_ID = "com.google.android.music";
    private static final String LOG_TAG = "SonosRouteController";
    public static final int SCIOP_MAX_RETRY_COUNT = 3;
    public static final int SCIOP_RETRY_BACKOFF_MILLIS = 50;
    private static final String SONOS_SIMPLEPLAYER_CLIENT_ID = "com.sonos.samples.simpleplayer";
    private boolean attachedToSonosGroup;
    private MediaRouteDescriptor cachedRouteDescriptor;
    private Context context;
    private ContinuousVolumeHelper continuousVolumeHelper;
    private boolean controllerStarted;
    private int currentTrackIndexInAVT;
    private Runnable detachedTimeoutRunnable;
    private long errorReportTimestamp;
    private SonosRouteEventSink eventSink;
    private SCIOpZoneGroupTopologyGetZoneGroupState getZoneGroupStateOp;
    private String groupName;
    private boolean invalidatedGroupOnHouseholdEventsWithOrphanedGroups;
    private boolean lostControl;
    private Handler mHandler;
    private boolean publishThisRouteWhenDetached;
    private final String routeId;
    private SonosRouteMetrics routeMetrics;
    private SonosRouteProvider routeProvider;
    private int routeSelectedCount;
    private SonosRouteSession session;
    private SonosRouteTransport sonosTransport;
    private SonosRouteTrackBoundaryAlarm trackBoundaryAlarm;
    private boolean wasErrorReported;

    static 
    {
        boolean flag;
        Integer ainteger[];
        if(!com/sonos/acr/media/SonosRouteController.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
        ainteger = new Integer[2];
        ainteger[0] = Integer.valueOf(1001);
        ainteger[1] = Integer.valueOf(1002);
        ERROR_CODES_TO_RETRY = Arrays.asList(ainteger);
    }




/*
    static boolean access$1002(SonosRouteController sonosroutecontroller, boolean flag)
    {
        sonosroutecontroller.publishThisRouteWhenDetached = flag;
        return flag;
    }

*/









/*
    static SCIOpZoneGroupTopologyGetZoneGroupState access$802(SonosRouteController sonosroutecontroller, SCIOpZoneGroupTopologyGetZoneGroupState sciopzonegrouptopologygetzonegroupstate)
    {
        sonosroutecontroller.getZoneGroupStateOp = sciopzonegrouptopologygetzonegroupstate;
        return sciopzonegrouptopologygetzonegroupstate;
    }

*/

}
