// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.app.PendingIntent;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

// Referenced classes of package android.support.v7.media:
//            MediaSessionStatus, MediaItemStatus

public class RemotePlaybackClient
{
    public static abstract class SessionActionCallback extends ActionCallback
    {

        public void onResult(Bundle bundle, String s, MediaSessionStatus mediasessionstatus)
        {
        }

        public SessionActionCallback()
        {
        }
    }

    public static abstract class ItemActionCallback extends ActionCallback
    {

        public void onResult(Bundle bundle, String s, MediaSessionStatus mediasessionstatus, String s1, MediaItemStatus mediaitemstatus)
        {
        }

        public ItemActionCallback()
        {
        }
    }

    public static abstract class ActionCallback
    {

        public void onError(String s, int i, Bundle bundle)
        {
        }

        public ActionCallback()
        {
        }
    }

    public static abstract class StatusCallback
    {

        public void onItemStatusChanged(Bundle bundle, String s, MediaSessionStatus mediasessionstatus, String s1, MediaItemStatus mediaitemstatus)
        {
        }

        public void onSessionChanged(String s)
        {
        }

        public void onSessionStatusChanged(Bundle bundle, String s, MediaSessionStatus mediasessionstatus)
        {
        }

        public StatusCallback()
        {
        }
    }

    private final class StatusReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
            if(s != null && s.equals(mSessionId)) goto _L2; else goto _L1
_L1:
            Log.w("RemotePlaybackClient", (new StringBuilder()).append("Discarding spurious status callback with missing or invalid session id: sessionId=").append(s).toString());
_L4:
            return;
_L2:
            MediaSessionStatus mediasessionstatus = MediaSessionStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.SESSION_STATUS"));
            String s1 = intent.getAction();
            if(s1.equals("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED"))
            {
                String s2 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
                if(s2 == null)
                {
                    Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
                } else
                {
                    MediaItemStatus mediaitemstatus = MediaItemStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.ITEM_STATUS"));
                    if(mediaitemstatus == null)
                    {
                        Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
                    } else
                    {
                        if(RemotePlaybackClient.DEBUG)
                            Log.d("RemotePlaybackClient", (new StringBuilder()).append("Received item status callback: sessionId=").append(s).append(", sessionStatus=").append(mediasessionstatus).append(", itemId=").append(s2).append(", itemStatus=").append(mediaitemstatus).toString());
                        if(mStatusCallback != null)
                            mStatusCallback.onItemStatusChanged(intent.getExtras(), s, mediasessionstatus, s2, mediaitemstatus);
                    }
                }
            } else
            if(s1.equals("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED"))
                if(mediasessionstatus == null)
                {
                    Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
                } else
                {
                    if(RemotePlaybackClient.DEBUG)
                        Log.d("RemotePlaybackClient", (new StringBuilder()).append("Received session status callback: sessionId=").append(s).append(", sessionStatus=").append(mediasessionstatus).toString());
                    if(mStatusCallback != null)
                        mStatusCallback.onSessionStatusChanged(intent.getExtras(), s, mediasessionstatus);
                }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final String ACTION_ITEM_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED";
        public static final String ACTION_SESSION_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED";
        final RemotePlaybackClient this$0;

        private StatusReceiver()
        {
            this$0 = RemotePlaybackClient.this;
            super();
        }

    }


    public RemotePlaybackClient(Context context, MediaRouter.RouteInfo routeinfo)
    {
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(routeinfo == null)
        {
            throw new IllegalArgumentException("route must not be null");
        } else
        {
            mContext = context;
            mRoute = routeinfo;
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
            intentfilter.addAction("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
            mStatusReceiver = new StatusReceiver();
            context.registerReceiver(mStatusReceiver, intentfilter);
            Intent intent = new Intent("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
            intent.setPackage(context.getPackageName());
            mItemStatusPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            Intent intent1 = new Intent("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
            intent1.setPackage(context.getPackageName());
            mSessionStatusPendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
            detectFeatures();
            return;
        }
    }

    private void adoptSession(String s)
    {
        if(s != null)
            setSessionId(s);
    }

    private static String bundleToString(Bundle bundle)
    {
        String s;
        if(bundle != null)
        {
            bundle.size();
            s = bundle.toString();
        } else
        {
            s = "null";
        }
        return s;
    }

    private void detectFeatures()
    {
        boolean flag = true;
        boolean flag1;
        boolean flag2;
        if(routeSupportsAction("android.media.intent.action.PLAY") && routeSupportsAction("android.media.intent.action.SEEK") && routeSupportsAction("android.media.intent.action.GET_STATUS") && routeSupportsAction("android.media.intent.action.PAUSE") && routeSupportsAction("android.media.intent.action.RESUME") && routeSupportsAction("android.media.intent.action.STOP"))
            flag1 = flag;
        else
            flag1 = false;
        mRouteSupportsRemotePlayback = flag1;
        if(mRouteSupportsRemotePlayback && routeSupportsAction("android.media.intent.action.ENQUEUE") && routeSupportsAction("android.media.intent.action.REMOVE"))
            flag2 = flag;
        else
            flag2 = false;
        mRouteSupportsQueuing = flag2;
        if(!mRouteSupportsRemotePlayback || !routeSupportsAction("android.media.intent.action.START_SESSION") || !routeSupportsAction("android.media.intent.action.GET_SESSION_STATUS") || !routeSupportsAction("android.media.intent.action.END_SESSION"))
            flag = false;
        mRouteSupportsSessionManagement = flag;
    }

    private void handleError(Intent intent, ActionCallback actioncallback, String s, Bundle bundle)
    {
        int i;
        if(bundle != null)
            i = bundle.getInt("android.media.intent.extra.ERROR_CODE", 0);
        else
            i = 0;
        if(DEBUG)
            Log.w("RemotePlaybackClient", (new StringBuilder()).append("Received error from ").append(intent.getAction()).append(": error=").append(s).append(", code=").append(i).append(", data=").append(bundleToString(bundle)).toString());
        actioncallback.onError(s, i, bundle);
    }

    private void handleInvalidResult(Intent intent, ActionCallback actioncallback, Bundle bundle)
    {
        Log.w("RemotePlaybackClient", (new StringBuilder()).append("Received invalid result data from ").append(intent.getAction()).append(": data=").append(bundleToString(bundle)).toString());
        actioncallback.onError(null, 0, bundle);
    }

    private static String inferMissingResult(String s, String s1)
    {
        if(s1 != null)
            if(s == null || s.equals(s1))
                s = s1;
            else
                s = null;
        return s;
    }

    private static void logRequest(Intent intent)
    {
        if(DEBUG)
            Log.d("RemotePlaybackClient", (new StringBuilder()).append("Sending request: ").append(intent).toString());
    }

    private void performItemAction(final Intent intent, final String sessionId, final String itemId, Bundle bundle, final ItemActionCallback callback)
    {
        intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        if(sessionId != null)
            intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId);
        if(itemId != null)
            intent.putExtra("android.media.intent.extra.ITEM_ID", itemId);
        if(bundle != null)
            intent.putExtras(bundle);
        logRequest(intent);
        mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {

            public void onError(String s, Bundle bundle1)
            {
                handleError(intent, callback, s, bundle1);
            }

            public void onResult(Bundle bundle1)
            {
                if(bundle1 == null) goto _L2; else goto _L1
_L1:
                String s;
                MediaSessionStatus mediasessionstatus;
                String s1;
                MediaItemStatus mediaitemstatus;
                s = RemotePlaybackClient.inferMissingResult(sessionId, bundle1.getString("android.media.intent.extra.SESSION_ID"));
                mediasessionstatus = MediaSessionStatus.fromBundle(bundle1.getBundle("android.media.intent.extra.SESSION_STATUS"));
                s1 = RemotePlaybackClient.inferMissingResult(itemId, bundle1.getString("android.media.intent.extra.ITEM_ID"));
                mediaitemstatus = MediaItemStatus.fromBundle(bundle1.getBundle("android.media.intent.extra.ITEM_STATUS"));
                adoptSession(s);
                if(s == null || s1 == null || mediaitemstatus == null) goto _L2; else goto _L3
_L3:
                if(RemotePlaybackClient.DEBUG)
                    Log.d("RemotePlaybackClient", (new StringBuilder()).append("Received result from ").append(intent.getAction()).append(": data=").append(RemotePlaybackClient.bundleToString(bundle1)).append(", sessionId=").append(s).append(", sessionStatus=").append(mediasessionstatus).append(", itemId=").append(s1).append(", itemStatus=").append(mediaitemstatus).toString());
                callback.onResult(bundle1, s, mediasessionstatus, s1, mediaitemstatus);
_L5:
                return;
_L2:
                handleInvalidResult(intent, callback, bundle1);
                if(true) goto _L5; else goto _L4
_L4:
            }

            final RemotePlaybackClient this$0;
            final ItemActionCallback val$callback;
            final Intent val$intent;
            final String val$itemId;
            final String val$sessionId;

            
            {
                this$0 = RemotePlaybackClient.this;
                sessionId = s;
                itemId = s1;
                intent = intent1;
                callback = itemactioncallback;
                super();
            }
        }
);
    }

    private void performSessionAction(final Intent intent, final String sessionId, Bundle bundle, final SessionActionCallback callback)
    {
        intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
        if(sessionId != null)
            intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId);
        if(bundle != null)
            intent.putExtras(bundle);
        logRequest(intent);
        mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {

            public void onError(String s, Bundle bundle1)
            {
                handleError(intent, callback, s, bundle1);
            }

            public void onResult(Bundle bundle1)
            {
                if(bundle1 == null) goto _L2; else goto _L1
_L1:
                String s;
                MediaSessionStatus mediasessionstatus;
                s = RemotePlaybackClient.inferMissingResult(sessionId, bundle1.getString("android.media.intent.extra.SESSION_ID"));
                mediasessionstatus = MediaSessionStatus.fromBundle(bundle1.getBundle("android.media.intent.extra.SESSION_STATUS"));
                adoptSession(s);
                if(s == null) goto _L2; else goto _L3
_L3:
                if(RemotePlaybackClient.DEBUG)
                    Log.d("RemotePlaybackClient", (new StringBuilder()).append("Received result from ").append(intent.getAction()).append(": data=").append(RemotePlaybackClient.bundleToString(bundle1)).append(", sessionId=").append(s).append(", sessionStatus=").append(mediasessionstatus).toString());
                callback.onResult(bundle1, s, mediasessionstatus);
                if(intent.getAction().equals("android.media.intent.action.END_SESSION") && s.equals(mSessionId))
                    setSessionId(null);
_L5:
                return;
                Exception exception;
                exception;
                if(intent.getAction().equals("android.media.intent.action.END_SESSION") && s.equals(mSessionId))
                    setSessionId(null);
                throw exception;
_L2:
                handleInvalidResult(intent, callback, bundle1);
                if(true) goto _L5; else goto _L4
_L4:
            }

            final RemotePlaybackClient this$0;
            final SessionActionCallback val$callback;
            final Intent val$intent;
            final String val$sessionId;

            
            {
                this$0 = RemotePlaybackClient.this;
                sessionId = s;
                intent = intent1;
                callback = sessionactioncallback;
                super();
            }
        }
);
    }

    private void playOrEnqueue(Uri uri, String s, Bundle bundle, long l, Bundle bundle1, ItemActionCallback itemactioncallback, 
            String s1)
    {
        if(uri == null)
            throw new IllegalArgumentException("contentUri must not be null");
        throwIfRemotePlaybackNotSupported();
        if(s1.equals("android.media.intent.action.ENQUEUE"))
            throwIfQueuingNotSupported();
        Intent intent = new Intent(s1);
        intent.setDataAndType(uri, s);
        intent.putExtra("android.media.intent.extra.ITEM_STATUS_UPDATE_RECEIVER", mItemStatusPendingIntent);
        if(bundle != null)
            intent.putExtra("android.media.intent.extra.ITEM_METADATA", bundle);
        if(l != 0L)
            intent.putExtra("android.media.intent.extra.ITEM_POSITION", l);
        performItemAction(intent, mSessionId, null, bundle1, itemactioncallback);
    }

    private boolean routeSupportsAction(String s)
    {
        return mRoute.supportsControlAction("android.media.intent.category.REMOTE_PLAYBACK", s);
    }

    private void throwIfNoCurrentSession()
    {
        if(mSessionId == null)
            throw new IllegalStateException("There is no current session.");
        else
            return;
    }

    private void throwIfQueuingNotSupported()
    {
        if(!mRouteSupportsQueuing)
            throw new UnsupportedOperationException("The route does not support queuing.");
        else
            return;
    }

    private void throwIfRemotePlaybackNotSupported()
    {
        if(!mRouteSupportsRemotePlayback)
            throw new UnsupportedOperationException("The route does not support remote playback.");
        else
            return;
    }

    private void throwIfSessionManagementNotSupported()
    {
        if(!mRouteSupportsSessionManagement)
            throw new UnsupportedOperationException("The route does not support session management.");
        else
            return;
    }

    public void endSession(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent("android.media.intent.action.END_SESSION"), mSessionId, bundle, sessionactioncallback);
    }

    public void enqueue(Uri uri, String s, Bundle bundle, long l, Bundle bundle1, ItemActionCallback itemactioncallback)
    {
        playOrEnqueue(uri, s, bundle, l, bundle1, itemactioncallback, "android.media.intent.action.ENQUEUE");
    }

    public String getSessionId()
    {
        return mSessionId;
    }

    public void getSessionStatus(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent("android.media.intent.action.GET_SESSION_STATUS"), mSessionId, bundle, sessionactioncallback);
    }

    public void getStatus(String s, Bundle bundle, ItemActionCallback itemactioncallback)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("itemId must not be null");
        } else
        {
            throwIfNoCurrentSession();
            performItemAction(new Intent("android.media.intent.action.GET_STATUS"), mSessionId, s, bundle, itemactioncallback);
            return;
        }
    }

    public boolean hasSession()
    {
        boolean flag;
        if(mSessionId != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isQueuingSupported()
    {
        return mRouteSupportsQueuing;
    }

    public boolean isRemotePlaybackSupported()
    {
        return mRouteSupportsRemotePlayback;
    }

    public boolean isSessionManagementSupported()
    {
        return mRouteSupportsSessionManagement;
    }

    public void pause(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfNoCurrentSession();
        performSessionAction(new Intent("android.media.intent.action.PAUSE"), mSessionId, bundle, sessionactioncallback);
    }

    public void play(Uri uri, String s, Bundle bundle, long l, Bundle bundle1, ItemActionCallback itemactioncallback)
    {
        playOrEnqueue(uri, s, bundle, l, bundle1, itemactioncallback, "android.media.intent.action.PLAY");
    }

    public void release()
    {
        mContext.unregisterReceiver(mStatusReceiver);
    }

    public void remove(String s, Bundle bundle, ItemActionCallback itemactioncallback)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("itemId must not be null");
        } else
        {
            throwIfQueuingNotSupported();
            throwIfNoCurrentSession();
            performItemAction(new Intent("android.media.intent.action.REMOVE"), mSessionId, s, bundle, itemactioncallback);
            return;
        }
    }

    public void resume(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfNoCurrentSession();
        performSessionAction(new Intent("android.media.intent.action.RESUME"), mSessionId, bundle, sessionactioncallback);
    }

    public void seek(String s, long l, Bundle bundle, ItemActionCallback itemactioncallback)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("itemId must not be null");
        } else
        {
            throwIfNoCurrentSession();
            Intent intent = new Intent("android.media.intent.action.SEEK");
            intent.putExtra("android.media.intent.extra.ITEM_POSITION", l);
            performItemAction(intent, mSessionId, s, bundle, itemactioncallback);
            return;
        }
    }

    public void setSessionId(String s)
    {
        if(mSessionId != s && (mSessionId == null || !mSessionId.equals(s)))
        {
            if(DEBUG)
                Log.d("RemotePlaybackClient", (new StringBuilder()).append("Session id is now: ").append(s).toString());
            mSessionId = s;
            if(mStatusCallback != null)
                mStatusCallback.onSessionChanged(s);
        }
    }

    public void setStatusCallback(StatusCallback statuscallback)
    {
        mStatusCallback = statuscallback;
    }

    public void startSession(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfSessionManagementNotSupported();
        Intent intent = new Intent("android.media.intent.action.START_SESSION");
        intent.putExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER", mSessionStatusPendingIntent);
        performSessionAction(intent, null, bundle, sessionactioncallback);
    }

    public void stop(Bundle bundle, SessionActionCallback sessionactioncallback)
    {
        throwIfNoCurrentSession();
        performSessionAction(new Intent("android.media.intent.action.STOP"), mSessionId, bundle, sessionactioncallback);
    }

    private static final boolean DEBUG = Log.isLoggable("RemotePlaybackClient", 3);
    private static final String TAG = "RemotePlaybackClient";
    private final Context mContext;
    private final PendingIntent mItemStatusPendingIntent;
    private final MediaRouter.RouteInfo mRoute;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    private String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    private StatusCallback mStatusCallback;
    private final StatusReceiver mStatusReceiver;









}
