// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.app.ActivityManager;
import android.content.*;
import android.content.res.Resources;
import android.os.*;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.Display;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.support.v7.media:
//            MediaRouteSelector, MediaRouteProvider, SystemMediaRouteProvider, MediaRouteProviderDescriptor, 
//            MediaRouteDescriptor, RegisteredMediaRouteProviderWatcher, MediaRouteDiscoveryRequest, RemoteControlClientCompat

public final class MediaRouter
{
    private static final class GlobalMediaRouter
        implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback
    {
        private final class CallbackHandler extends Handler
        {

            private void invokeCallback(CallbackRecord callbackrecord, int i, Object obj)
            {
                MediaRouter mediarouter;
                Callback callback;
                mediarouter = callbackrecord.mRouter;
                callback = callbackrecord.mCallback;
                0xff00 & i;
                JVM INSTR lookupswitch 2: default 44
            //                           256: 45
            //                           512: 191;
                   goto _L1 _L2 _L3
_L5:
                return;
_L2:
                RouteInfo routeinfo = (RouteInfo)obj;
                if(callbackrecord.filterRouteEvent(routeinfo))
                    switch(i)
                    {
                    case 257: 
                        callback.onRouteAdded(mediarouter, routeinfo);
                        break;

                    case 258: 
                        callback.onRouteRemoved(mediarouter, routeinfo);
                        break;

                    case 259: 
                        callback.onRouteChanged(mediarouter, routeinfo);
                        break;

                    case 260: 
                        callback.onRouteVolumeChanged(mediarouter, routeinfo);
                        break;

                    case 261: 
                        callback.onRoutePresentationDisplayChanged(mediarouter, routeinfo);
                        break;

                    case 262: 
                        callback.onRouteSelected(mediarouter, routeinfo);
                        break;

                    case 263: 
                        callback.onRouteUnselected(mediarouter, routeinfo);
                        break;
                    }
_L1:
                if(true) goto _L5; else goto _L4
_L4:
_L3:
                ProviderInfo providerinfo = (ProviderInfo)obj;
                switch(i)
                {
                case 513: 
                    callback.onProviderAdded(mediarouter, providerinfo);
                    break;

                case 514: 
                    callback.onProviderRemoved(mediarouter, providerinfo);
                    break;

                case 515: 
                    callback.onProviderChanged(mediarouter, providerinfo);
                    break;
                }
                if(true) goto _L5; else goto _L6
_L6:
            }

            private void syncWithSystemProvider(int i, Object obj)
            {
                i;
                JVM INSTR tableswitch 257 262: default 40
            //                           257 41
            //                           258 58
            //                           259 75
            //                           260 40
            //                           261 40
            //                           262 92;
                   goto _L1 _L2 _L3 _L4 _L1 _L1 _L5
_L1:
                return;
_L2:
                mSystemProvider.onSyncRouteAdded((RouteInfo)obj);
                continue; /* Loop/switch isn't completed */
_L3:
                mSystemProvider.onSyncRouteRemoved((RouteInfo)obj);
                continue; /* Loop/switch isn't completed */
_L4:
                mSystemProvider.onSyncRouteChanged((RouteInfo)obj);
                continue; /* Loop/switch isn't completed */
_L5:
                mSystemProvider.onSyncRouteSelected((RouteInfo)obj);
                if(true) goto _L1; else goto _L6
_L6:
            }

            public void handleMessage(Message message)
            {
                int i;
                Object obj;
                i = message.what;
                obj = message.obj;
                syncWithSystemProvider(i, obj);
                int j = mRouters.size();
_L1:
                MediaRouter mediarouter;
                do
                {
                    if(--j < 0)
                        break MISSING_BLOCK_LABEL_108;
                    mediarouter = (MediaRouter)((WeakReference)mRouters.get(j)).get();
                    if(mediarouter != null)
                        break MISSING_BLOCK_LABEL_92;
                    mRouters.remove(j);
                } while(true);
                Exception exception;
                exception;
                mTempCallbackRecords.clear();
                throw exception;
                mTempCallbackRecords.addAll(mediarouter.mCallbackRecords);
                  goto _L1
                int k;
                int l;
                k = mTempCallbackRecords.size();
                l = 0;
_L2:
                if(l >= k)
                    break MISSING_BLOCK_LABEL_151;
                invokeCallback((CallbackRecord)mTempCallbackRecords.get(l), i, obj);
                l++;
                  goto _L2
                mTempCallbackRecords.clear();
                return;
            }

            public void post(int i, Object obj)
            {
                obtainMessage(i, obj).sendToTarget();
            }

            public static final int MSG_PROVIDER_ADDED = 513;
            public static final int MSG_PROVIDER_CHANGED = 515;
            public static final int MSG_PROVIDER_REMOVED = 514;
            public static final int MSG_ROUTE_ADDED = 257;
            public static final int MSG_ROUTE_CHANGED = 259;
            public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
            public static final int MSG_ROUTE_REMOVED = 258;
            public static final int MSG_ROUTE_SELECTED = 262;
            public static final int MSG_ROUTE_UNSELECTED = 263;
            public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
            private static final int MSG_TYPE_MASK = 65280;
            private static final int MSG_TYPE_PROVIDER = 512;
            private static final int MSG_TYPE_ROUTE = 256;
            private final ArrayList mTempCallbackRecords;
            final GlobalMediaRouter this$0;

            private CallbackHandler()
            {
                this$0 = GlobalMediaRouter.this;
                super();
                mTempCallbackRecords = new ArrayList();
            }

        }

        private final class RemoteControlClientRecord
            implements RemoteControlClientCompat.VolumeCallback
        {

            public void disconnect()
            {
                mDisconnected = true;
                mRccCompat.setVolumeCallback(null);
            }

            public Object getRemoteControlClient()
            {
                return mRccCompat.getRemoteControlClient();
            }

            public void onVolumeSetRequest(int i)
            {
                if(!mDisconnected && mSelectedRoute != null)
                    mSelectedRoute.requestSetVolume(i);
            }

            public void onVolumeUpdateRequest(int i)
            {
                if(!mDisconnected && mSelectedRoute != null)
                    mSelectedRoute.requestUpdateVolume(i);
            }

            public void updatePlaybackInfo()
            {
                mRccCompat.setPlaybackInfo(mPlaybackInfo);
            }

            private boolean mDisconnected;
            private final RemoteControlClientCompat mRccCompat;
            final GlobalMediaRouter this$0;

            public RemoteControlClientRecord(Object obj)
            {
                this$0 = GlobalMediaRouter.this;
                super();
                mRccCompat = RemoteControlClientCompat.obtain(mApplicationContext, obj);
                mRccCompat.setVolumeCallback(this);
                updatePlaybackInfo();
            }
        }

        private final class MediaSessionRecord
        {

            public void clearVolumeHandling()
            {
                mMsCompat.setPlaybackToLocal(mPlaybackInfo.playbackStream);
                mVpCompat = null;
            }

            public void configureVolume(int i, int j, int k)
            {
                if(mVpCompat != null && i == mControlType && j == mMaxVolume)
                {
                    mVpCompat.setCurrentVolume(k);
                } else
                {
                    mVpCompat = new VolumeProviderCompat(i, j, k) {

                        public void onAdjustVolume(int l)
                        {
                            if(mSelectedRoute != null)
                                mSelectedRoute.requestUpdateVolume(l);
                        }

                        public void onSetVolumeTo(int l)
                        {
                            if(mSelectedRoute != null)
                                mSelectedRoute.requestSetVolume(l);
                        }

                        final MediaSessionRecord this$1;

                    
                    {
                        this$1 = MediaSessionRecord.this;
                        super(i, j, k);
                    }
                    }
;
                    mMsCompat.setPlaybackToRemote(mVpCompat);
                }
            }

            private int mControlType;
            private int mMaxVolume;
            private final MediaSessionCompat mMsCompat;
            private VolumeProviderCompat mVpCompat;
            final GlobalMediaRouter this$0;

            public MediaSessionRecord(Object obj)
            {
                this$0 = GlobalMediaRouter.this;
                super();
                mMsCompat = MediaSessionCompat.obtain(obj);
            }
        }

        private final class ProviderCallback extends MediaRouteProvider.Callback
        {

            public void onDescriptorChanged(MediaRouteProvider mediarouteprovider, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
            {
                updateProviderDescriptor(mediarouteprovider, mediarouteproviderdescriptor);
            }

            final GlobalMediaRouter this$0;

            private ProviderCallback()
            {
                this$0 = GlobalMediaRouter.this;
                super();
            }

        }


        private String assignRouteUniqueId(ProviderInfo providerinfo, String s)
        {
            String s1 = (new StringBuilder()).append(providerinfo.getComponentName().flattenToShortString()).append(":").append(s).toString();
            if(findRouteByUniqueId(s1) >= 0) goto _L2; else goto _L1
_L1:
            return s1;
_L2:
            int i = 2;
            do
            {
label0:
                {
                    Locale locale = Locale.US;
                    Object aobj[] = new Object[2];
                    aobj[0] = s1;
                    aobj[1] = Integer.valueOf(i);
                    String s2 = String.format(locale, "%s_%d", aobj);
                    if(findRouteByUniqueId(s2) >= 0)
                        break label0;
                    s1 = s2;
                }
                if(true)
                    continue;
                i++;
            } while(true);
            if(true) goto _L1; else goto _L3
_L3:
        }

        private RouteInfo chooseFallbackRoute()
        {
            Iterator iterator = mRoutes.iterator();
_L4:
            if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            RouteInfo routeinfo = (RouteInfo)iterator.next();
            if(routeinfo == mDefaultRoute || !isSystemLiveAudioOnlyRoute(routeinfo) || !isRouteSelectable(routeinfo)) goto _L4; else goto _L3
_L3:
            return routeinfo;
_L2:
            routeinfo = mDefaultRoute;
            if(true) goto _L3; else goto _L5
_L5:
        }

        private int findProviderInfo(MediaRouteProvider mediarouteprovider)
        {
            int i;
            int j;
            i = mProviders.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_41;
            if(((ProviderInfo)mProviders.get(j)).mProviderInstance != mediarouteprovider) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        private int findRemoteControlClientRecord(Object obj)
        {
            int i;
            int j;
            i = mRemoteControlClients.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_41;
            if(((RemoteControlClientRecord)mRemoteControlClients.get(j)).getRemoteControlClient() != obj) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        private int findRouteByUniqueId(String s)
        {
            int i;
            int j;
            i = mRoutes.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_44;
            if(!((RouteInfo)mRoutes.get(j)).mUniqueId.equals(s)) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        private boolean isRouteSelectable(RouteInfo routeinfo)
        {
            boolean flag;
            if(routeinfo.mDescriptor != null && routeinfo.mEnabled)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isSystemDefaultRoute(RouteInfo routeinfo)
        {
            boolean flag;
            if(routeinfo.getProviderInstance() == mSystemProvider && routeinfo.mDescriptorId.equals("DEFAULT_ROUTE"))
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isSystemLiveAudioOnlyRoute(RouteInfo routeinfo)
        {
            boolean flag;
            if(routeinfo.getProviderInstance() == mSystemProvider && routeinfo.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !routeinfo.supportsControlCategory("android.media.intent.category.LIVE_VIDEO"))
                flag = true;
            else
                flag = false;
            return flag;
        }

        private void setSelectedRouteInternal(RouteInfo routeinfo)
        {
            if(mSelectedRoute != routeinfo)
            {
                if(mSelectedRoute != null)
                {
                    if(MediaRouter.DEBUG)
                        Log.d("MediaRouter", (new StringBuilder()).append("Route unselected: ").append(mSelectedRoute).toString());
                    mCallbackHandler.post(263, mSelectedRoute);
                    if(mSelectedRouteController != null)
                    {
                        mSelectedRouteController.onUnselect();
                        mSelectedRouteController.onRelease();
                        mSelectedRouteController = null;
                    }
                }
                mSelectedRoute = routeinfo;
                if(mSelectedRoute != null)
                {
                    mSelectedRouteController = routeinfo.getProviderInstance().onCreateRouteController(routeinfo.mDescriptorId);
                    if(mSelectedRouteController != null)
                        mSelectedRouteController.onSelect();
                    if(MediaRouter.DEBUG)
                        Log.d("MediaRouter", (new StringBuilder()).append("Route selected: ").append(mSelectedRoute).toString());
                    mCallbackHandler.post(262, mSelectedRoute);
                }
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        private void updatePlaybackInfoFromSelectedRoute()
        {
            if(mSelectedRoute != null)
            {
                mPlaybackInfo.volume = mSelectedRoute.getVolume();
                mPlaybackInfo.volumeMax = mSelectedRoute.getVolumeMax();
                mPlaybackInfo.volumeHandling = mSelectedRoute.getVolumeHandling();
                mPlaybackInfo.playbackStream = mSelectedRoute.getPlaybackStream();
                mPlaybackInfo.playbackType = mSelectedRoute.getPlaybackType();
                int i = mRemoteControlClients.size();
                for(int j = 0; j < i; j++)
                    ((RemoteControlClientRecord)mRemoteControlClients.get(j)).updatePlaybackInfo();

                if(mMediaSession != null)
                {
                    byte byte0 = 0;
                    if(mPlaybackInfo.volumeHandling == 1)
                        byte0 = 2;
                    mMediaSession.configureVolume(byte0, mPlaybackInfo.volumeMax, mPlaybackInfo.volume);
                }
            }
        }

        private void updateProviderContents(ProviderInfo providerinfo, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
            int i;
            boolean flag;
            if(!providerinfo.updateDescriptor(mediarouteproviderdescriptor))
                break MISSING_BLOCK_LABEL_687;
            i = 0;
            flag = false;
            if(mediarouteproviderdescriptor == null) goto _L2; else goto _L1
_L1:
            if(!mediarouteproviderdescriptor.isValid()) goto _L4; else goto _L3
_L3:
            List list = mediarouteproviderdescriptor.getRoutes();
            int l = list.size();
            int i1 = 0;
            int j1 = 0;
            while(i1 < l) 
            {
                MediaRouteDescriptor mediaroutedescriptor = (MediaRouteDescriptor)list.get(i1);
                String s = mediaroutedescriptor.getId();
                int k1 = providerinfo.findRouteByDescriptorId(s);
                int l1;
                if(k1 < 0)
                {
                    RouteInfo routeinfo2 = new RouteInfo(providerinfo, s, assignRouteUniqueId(providerinfo, s));
                    ArrayList arraylist = providerinfo.mRoutes;
                    l1 = j1 + 1;
                    arraylist.add(j1, routeinfo2);
                    mRoutes.add(routeinfo2);
                    routeinfo2.updateDescriptor(mediaroutedescriptor);
                    if(MediaRouter.DEBUG)
                        Log.d("MediaRouter", (new StringBuilder()).append("Route added: ").append(routeinfo2).toString());
                    mCallbackHandler.post(257, routeinfo2);
                } else
                if(k1 < j1)
                {
                    Log.w("MediaRouter", (new StringBuilder()).append("Ignoring route descriptor with duplicate id: ").append(mediaroutedescriptor).toString());
                    l1 = j1;
                } else
                {
                    RouteInfo routeinfo3 = (RouteInfo)providerinfo.mRoutes.get(k1);
                    ArrayList arraylist1 = providerinfo.mRoutes;
                    l1 = j1 + 1;
                    Collections.swap(arraylist1, k1, j1);
                    int i2 = routeinfo3.updateDescriptor(mediaroutedescriptor);
                    if(i2 != 0)
                    {
                        if((i2 & 1) != 0)
                        {
                            if(MediaRouter.DEBUG)
                                Log.d("MediaRouter", (new StringBuilder()).append("Route changed: ").append(routeinfo3).toString());
                            mCallbackHandler.post(259, routeinfo3);
                        }
                        if((i2 & 2) != 0)
                        {
                            if(MediaRouter.DEBUG)
                                Log.d("MediaRouter", (new StringBuilder()).append("Route volume changed: ").append(routeinfo3).toString());
                            mCallbackHandler.post(260, routeinfo3);
                        }
                        if((i2 & 4) != 0)
                        {
                            if(MediaRouter.DEBUG)
                                Log.d("MediaRouter", (new StringBuilder()).append("Route presentation display changed: ").append(routeinfo3).toString());
                            mCallbackHandler.post(261, routeinfo3);
                        }
                        if(routeinfo3 == mSelectedRoute)
                            flag = true;
                    }
                }
                i1++;
                j1 = l1;
            }
            i = j1;
_L2:
            for(int j = -1 + providerinfo.mRoutes.size(); j >= i; j--)
            {
                RouteInfo routeinfo1 = (RouteInfo)providerinfo.mRoutes.get(j);
                routeinfo1.updateDescriptor(null);
                mRoutes.remove(routeinfo1);
            }

            break; /* Loop/switch isn't completed */
_L4:
            Log.w("MediaRouter", (new StringBuilder()).append("Ignoring invalid provider descriptor: ").append(mediarouteproviderdescriptor).toString());
            if(true) goto _L2; else goto _L5
_L5:
            updateSelectedRouteIfNeeded(flag);
            for(int k = -1 + providerinfo.mRoutes.size(); k >= i; k--)
            {
                RouteInfo routeinfo = (RouteInfo)providerinfo.mRoutes.remove(k);
                if(MediaRouter.DEBUG)
                    Log.d("MediaRouter", (new StringBuilder()).append("Route removed: ").append(routeinfo).toString());
                mCallbackHandler.post(258, routeinfo);
            }

            if(MediaRouter.DEBUG)
                Log.d("MediaRouter", (new StringBuilder()).append("Provider changed: ").append(providerinfo).toString());
            mCallbackHandler.post(515, providerinfo);
        }

        private void updateProviderDescriptor(MediaRouteProvider mediarouteprovider, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
            int i = findProviderInfo(mediarouteprovider);
            if(i >= 0)
                updateProviderContents((ProviderInfo)mProviders.get(i), mediarouteproviderdescriptor);
        }

        private void updateSelectedRouteIfNeeded(boolean flag)
        {
label0:
            {
                if(mDefaultRoute != null && !isRouteSelectable(mDefaultRoute))
                {
                    Log.i("MediaRouter", (new StringBuilder()).append("Clearing the default route because it is no longer selectable: ").append(mDefaultRoute).toString());
                    mDefaultRoute = null;
                }
                if(mDefaultRoute != null || mRoutes.isEmpty())
                    break label0;
                Iterator iterator = mRoutes.iterator();
                RouteInfo routeinfo;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    routeinfo = (RouteInfo)iterator.next();
                } while(!isSystemDefaultRoute(routeinfo) || !isRouteSelectable(routeinfo));
                mDefaultRoute = routeinfo;
                Log.i("MediaRouter", (new StringBuilder()).append("Found default route: ").append(mDefaultRoute).toString());
            }
            if(mSelectedRoute != null && !isRouteSelectable(mSelectedRoute))
            {
                Log.i("MediaRouter", (new StringBuilder()).append("Unselecting the current route because it is no longer selectable: ").append(mSelectedRoute).toString());
                setSelectedRouteInternal(null);
            }
            if(mSelectedRoute != null) goto _L2; else goto _L1
_L1:
            setSelectedRouteInternal(chooseFallbackRoute());
_L4:
            return;
_L2:
            if(flag)
                updatePlaybackInfoFromSelectedRoute();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void addProvider(MediaRouteProvider mediarouteprovider)
        {
            if(findProviderInfo(mediarouteprovider) < 0)
            {
                ProviderInfo providerinfo = new ProviderInfo(mediarouteprovider);
                mProviders.add(providerinfo);
                if(MediaRouter.DEBUG)
                    Log.d("MediaRouter", (new StringBuilder()).append("Provider added: ").append(providerinfo).toString());
                mCallbackHandler.post(513, providerinfo);
                updateProviderContents(providerinfo, mediarouteprovider.getDescriptor());
                mediarouteprovider.setCallback(mProviderCallback);
                mediarouteprovider.setDiscoveryRequest(mDiscoveryRequest);
            }
        }

        public void addRemoteControlClient(Object obj)
        {
            if(findRemoteControlClientRecord(obj) < 0)
            {
                RemoteControlClientRecord remotecontrolclientrecord = new RemoteControlClientRecord(obj);
                mRemoteControlClients.add(remotecontrolclientrecord);
            }
        }

        public ContentResolver getContentResolver()
        {
            return mApplicationContext.getContentResolver();
        }

        public RouteInfo getDefaultRoute()
        {
            if(mDefaultRoute == null)
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            else
                return mDefaultRoute;
        }

        public Display getDisplay(int i)
        {
            return mDisplayManager.getDisplay(i);
        }

        public Context getProviderContext(String s)
        {
            if(!s.equals("android")) goto _L2; else goto _L1
_L1:
            Context context = mApplicationContext;
_L4:
            return context;
_L2:
            Context context1 = mApplicationContext.createPackageContext(s, 4);
            context = context1;
            continue; /* Loop/switch isn't completed */
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            context = null;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public List getProviders()
        {
            return mProviders;
        }

        public MediaRouter getRouter(Context context)
        {
            if(mediarouter2.mContext != context) goto _L2; else goto _L1
_L1:
            mediarouter1 = mediarouter2;
_L4:
            return mediarouter1;
_L2:
            MediaRouter mediarouter1;
            MediaRouter mediarouter2;
            for(int i = mRouters.size(); --i >= 0;)
            {
                mediarouter2 = (MediaRouter)((WeakReference)mRouters.get(i)).get();
                if(mediarouter2 != null)
                    continue; /* Loop/switch isn't completed */
                mRouters.remove(i);
            }

            MediaRouter mediarouter = new MediaRouter(context);
            mRouters.add(new WeakReference(mediarouter));
            mediarouter1 = mediarouter;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public List getRoutes()
        {
            return mRoutes;
        }

        public RouteInfo getSelectedRoute()
        {
            if(mSelectedRoute == null)
                throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
            else
                return mSelectedRoute;
        }

        public RouteInfo getSystemRouteByDescriptorId(String s)
        {
            int i = findProviderInfo(mSystemProvider);
            if(i < 0) goto _L2; else goto _L1
_L1:
            ProviderInfo providerinfo;
            int j;
            providerinfo = (ProviderInfo)mProviders.get(i);
            j = providerinfo.findRouteByDescriptorId(s);
            if(j < 0) goto _L2; else goto _L3
_L3:
            RouteInfo routeinfo = (RouteInfo)providerinfo.mRoutes.get(j);
_L5:
            return routeinfo;
_L2:
            routeinfo = null;
            if(true) goto _L5; else goto _L4
_L4:
        }

        public boolean isRouteAvailable(MediaRouteSelector mediarouteselector, int i)
        {
            boolean flag = false;
            if(!mediarouteselector.isEmpty()) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            int j;
            int k;
            if((i & 2) == 0 && mLowRam)
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            j = mRoutes.size();
            k = 0;
_L4:
            RouteInfo routeinfo;
            if(k >= j)
                continue; /* Loop/switch isn't completed */
            routeinfo = (RouteInfo)mRoutes.get(k);
              goto _L3
_L6:
            k++;
            if(true) goto _L4; else goto _L3
_L3:
            if((i & 1) != 0 && routeinfo.isDefault() || !routeinfo.matchesSelector(mediarouteselector)) goto _L6; else goto _L5
_L5:
            flag = true;
            if(true) goto _L1; else goto _L7
_L7:
        }

        public void removeProvider(MediaRouteProvider mediarouteprovider)
        {
            int i = findProviderInfo(mediarouteprovider);
            if(i >= 0)
            {
                mediarouteprovider.setCallback(null);
                mediarouteprovider.setDiscoveryRequest(null);
                ProviderInfo providerinfo = (ProviderInfo)mProviders.get(i);
                updateProviderContents(providerinfo, null);
                if(MediaRouter.DEBUG)
                    Log.d("MediaRouter", (new StringBuilder()).append("Provider removed: ").append(providerinfo).toString());
                mCallbackHandler.post(514, providerinfo);
                mProviders.remove(i);
            }
        }

        public void removeRemoteControlClient(Object obj)
        {
            int i = findRemoteControlClientRecord(obj);
            if(i >= 0)
                ((RemoteControlClientRecord)mRemoteControlClients.remove(i)).disconnect();
        }

        public void requestSetVolume(RouteInfo routeinfo, int i)
        {
            if(routeinfo == mSelectedRoute && mSelectedRouteController != null)
                mSelectedRouteController.onSetVolume(i);
        }

        public void requestUpdateVolume(RouteInfo routeinfo, int i)
        {
            if(routeinfo == mSelectedRoute && mSelectedRouteController != null)
                mSelectedRouteController.onUpdateVolume(i);
        }

        public void selectRoute(RouteInfo routeinfo)
        {
            if(!mRoutes.contains(routeinfo))
                Log.w("MediaRouter", (new StringBuilder()).append("Ignoring attempt to select removed route: ").append(routeinfo).toString());
            else
            if(!routeinfo.mEnabled)
                Log.w("MediaRouter", (new StringBuilder()).append("Ignoring attempt to select disabled route: ").append(routeinfo).toString());
            else
                setSelectedRouteInternal(routeinfo);
        }

        public void sendControlRequest(RouteInfo routeinfo, Intent intent, ControlRequestCallback controlrequestcallback)
        {
            if((routeinfo != mSelectedRoute || mSelectedRouteController == null || !mSelectedRouteController.onControlRequest(intent, controlrequestcallback)) && controlrequestcallback != null)
                controlrequestcallback.onError(null, null);
        }

        public void setMediaSession(Object obj)
        {
            if(mMediaSession != null)
                mMediaSession.clearVolumeHandling();
            if(obj == null)
            {
                mMediaSession = null;
            } else
            {
                mMediaSession = new MediaSessionRecord(obj);
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        public void start()
        {
            mRegisteredProviderWatcher = new RegisteredMediaRouteProviderWatcher(mApplicationContext, this);
            mRegisteredProviderWatcher.start();
        }

        public void updateDiscoveryRequest()
        {
            boolean flag;
            boolean flag1;
            MediaRouteSelector mediarouteselector;
            flag = false;
            flag1 = false;
            MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
            for(int i = mRouters.size(); --i >= 0;)
            {
                MediaRouter mediarouter = (MediaRouter)((WeakReference)mRouters.get(i)).get();
                if(mediarouter == null)
                {
                    mRouters.remove(i);
                } else
                {
                    int l = mediarouter.mCallbackRecords.size();
                    int i1 = 0;
                    while(i1 < l) 
                    {
                        CallbackRecord callbackrecord = (CallbackRecord)mediarouter.mCallbackRecords.get(i1);
                        builder.addSelector(callbackrecord.mSelector);
                        if((1 & callbackrecord.mFlags) != 0)
                        {
                            flag1 = true;
                            flag = true;
                        }
                        if((4 & callbackrecord.mFlags) != 0 && !mLowRam)
                            flag = true;
                        if((8 & callbackrecord.mFlags) != 0)
                            flag = true;
                        i1++;
                    }
                }
            }

            if(flag)
                mediarouteselector = builder.build();
            else
                mediarouteselector = MediaRouteSelector.EMPTY;
            if(mDiscoveryRequest == null || !mDiscoveryRequest.getSelector().equals(mediarouteselector) || mDiscoveryRequest.isActiveScan() != flag1) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(!mediarouteselector.isEmpty() || flag1)
                break; /* Loop/switch isn't completed */
            if(mDiscoveryRequest == null)
                continue; /* Loop/switch isn't completed */
            mDiscoveryRequest = null;
_L4:
            if(MediaRouter.DEBUG)
                Log.d("MediaRouter", (new StringBuilder()).append("Updated discovery request: ").append(mDiscoveryRequest).toString());
            if(flag && !flag1 && mLowRam)
                Log.i("MediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
            int j = mProviders.size();
            int k = 0;
            while(k < j) 
            {
                ((ProviderInfo)mProviders.get(k)).mProviderInstance.setDiscoveryRequest(mDiscoveryRequest);
                k++;
            }
            if(true) goto _L1; else goto _L3
_L3:
            mDiscoveryRequest = new MediaRouteDiscoveryRequest(mediarouteselector, flag1);
              goto _L4
            if(true) goto _L1; else goto _L5
_L5:
        }

        private final Context mApplicationContext;
        private final CallbackHandler mCallbackHandler = new CallbackHandler();
        private RouteInfo mDefaultRoute;
        private MediaRouteDiscoveryRequest mDiscoveryRequest;
        private final DisplayManagerCompat mDisplayManager;
        private final boolean mLowRam;
        private MediaSessionRecord mMediaSession;
        private final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
        private final ProviderCallback mProviderCallback = new ProviderCallback();
        private final ArrayList mProviders = new ArrayList();
        private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
        private final ArrayList mRemoteControlClients = new ArrayList();
        private final ArrayList mRouters = new ArrayList();
        private final ArrayList mRoutes = new ArrayList();
        private RouteInfo mSelectedRoute;
        private MediaRouteProvider.RouteController mSelectedRouteController;
        private final SystemMediaRouteProvider mSystemProvider;







        GlobalMediaRouter(Context context)
        {
            mApplicationContext = context;
            mDisplayManager = DisplayManagerCompat.getInstance(context);
            mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager)context.getSystemService("activity"));
            mSystemProvider = SystemMediaRouteProvider.obtain(context, this);
            addProvider(mSystemProvider);
        }
    }

    private static final class CallbackRecord
    {

        public boolean filterRouteEvent(RouteInfo routeinfo)
        {
            boolean flag;
            if((2 & mFlags) != 0 || routeinfo.matchesSelector(mSelector))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector;

        public CallbackRecord(MediaRouter mediarouter, Callback callback)
        {
            mRouter = mediarouter;
            mCallback = callback;
            mSelector = MediaRouteSelector.EMPTY;
        }
    }

    public static abstract class ControlRequestCallback
    {

        public void onError(String s, Bundle bundle)
        {
        }

        public void onResult(Bundle bundle)
        {
        }

        public ControlRequestCallback()
        {
        }
    }

    public static abstract class Callback
    {

        public void onProviderAdded(MediaRouter mediarouter, ProviderInfo providerinfo)
        {
        }

        public void onProviderChanged(MediaRouter mediarouter, ProviderInfo providerinfo)
        {
        }

        public void onProviderRemoved(MediaRouter mediarouter, ProviderInfo providerinfo)
        {
        }

        public void onRouteAdded(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRoutePresentationDisplayChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteRemoved(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteSelected(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteUnselected(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public void onRouteVolumeChanged(MediaRouter mediarouter, RouteInfo routeinfo)
        {
        }

        public Callback()
        {
        }
    }

    public static final class ProviderInfo
    {

        int findRouteByDescriptorId(String s)
        {
            int i;
            int j;
            i = mRoutes.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_44;
            if(!((RouteInfo)mRoutes.get(j)).mDescriptorId.equals(s)) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        public ComponentName getComponentName()
        {
            return mMetadata.getComponentName();
        }

        public String getPackageName()
        {
            return mMetadata.getPackageName();
        }

        public MediaRouteProvider getProviderInstance()
        {
            MediaRouter.checkCallingThread();
            return mProviderInstance;
        }

        Resources getResources()
        {
            if(mResources == null && !mResourcesNotAvailable)
            {
                String s = getPackageName();
                Context context = MediaRouter.sGlobal.getProviderContext(s);
                if(context != null)
                {
                    mResources = context.getResources();
                } else
                {
                    Log.w("MediaRouter", (new StringBuilder()).append("Unable to obtain resources for route provider package: ").append(s).toString());
                    mResourcesNotAvailable = true;
                }
            }
            return mResources;
        }

        public List getRoutes()
        {
            MediaRouter.checkCallingThread();
            return mRoutes;
        }

        public String toString()
        {
            return (new StringBuilder()).append("MediaRouter.RouteProviderInfo{ packageName=").append(getPackageName()).append(" }").toString();
        }

        boolean updateDescriptor(MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
            boolean flag;
            if(mDescriptor != mediarouteproviderdescriptor)
            {
                mDescriptor = mediarouteproviderdescriptor;
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        private final MediaRouteProvider mProviderInstance;
        private Resources mResources;
        private boolean mResourcesNotAvailable;
        private final ArrayList mRoutes = new ArrayList();



        ProviderInfo(MediaRouteProvider mediarouteprovider)
        {
            mProviderInstance = mediarouteprovider;
            mMetadata = mediarouteprovider.getMetadata();
        }
    }

    public static final class RouteInfo
    {
        private static interface PlaybackVolume
            extends Annotation
        {
        }

        private static interface PlaybackType
            extends Annotation
        {
        }


        public List getControlFilters()
        {
            return mControlFilters;
        }

        public String getDescription()
        {
            return mDescription;
        }

        String getDescriptorId()
        {
            return mDescriptorId;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public String getId()
        {
            return mUniqueId;
        }

        public String getName()
        {
            return mName;
        }

        public int getPlaybackStream()
        {
            return mPlaybackStream;
        }

        public int getPlaybackType()
        {
            return mPlaybackType;
        }

        public Display getPresentationDisplay()
        {
            MediaRouter.checkCallingThread();
            if(mPresentationDisplayId >= 0 && mPresentationDisplay == null)
                mPresentationDisplay = MediaRouter.sGlobal.getDisplay(mPresentationDisplayId);
            return mPresentationDisplay;
        }

        public ProviderInfo getProvider()
        {
            return mProvider;
        }

        MediaRouteProvider getProviderInstance()
        {
            return mProvider.getProviderInstance();
        }

        public int getVolume()
        {
            return mVolume;
        }

        public int getVolumeHandling()
        {
            return mVolumeHandling;
        }

        public int getVolumeMax()
        {
            return mVolumeMax;
        }

        public boolean isConnecting()
        {
            return mConnecting;
        }

        public boolean isDefault()
        {
            MediaRouter.checkCallingThread();
            boolean flag;
            if(MediaRouter.sGlobal.getDefaultRoute() == this)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isEnabled()
        {
            return mEnabled;
        }

        public boolean isSelected()
        {
            MediaRouter.checkCallingThread();
            boolean flag;
            if(MediaRouter.sGlobal.getSelectedRoute() == this)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean matchesSelector(MediaRouteSelector mediarouteselector)
        {
            if(mediarouteselector == null)
            {
                throw new IllegalArgumentException("selector must not be null");
            } else
            {
                MediaRouter.checkCallingThread();
                return mediarouteselector.matchesControlFilters(mControlFilters);
            }
        }

        public void requestSetVolume(int i)
        {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.requestSetVolume(this, Math.min(mVolumeMax, Math.max(0, i)));
        }

        public void requestUpdateVolume(int i)
        {
            MediaRouter.checkCallingThread();
            if(i != 0)
                MediaRouter.sGlobal.requestUpdateVolume(this, i);
        }

        public void select()
        {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.selectRoute(this);
        }

        public void sendControlRequest(Intent intent, ControlRequestCallback controlrequestcallback)
        {
            if(intent == null)
            {
                throw new IllegalArgumentException("intent must not be null");
            } else
            {
                MediaRouter.checkCallingThread();
                MediaRouter.sGlobal.sendControlRequest(this, intent, controlrequestcallback);
                return;
            }
        }

        public boolean supportsControlAction(String s, String s1)
        {
            int i;
            int j;
            if(s == null)
                throw new IllegalArgumentException("category must not be null");
            if(s1 == null)
                throw new IllegalArgumentException("action must not be null");
            MediaRouter.checkCallingThread();
            i = mControlFilters.size();
            j = 0;
_L3:
            IntentFilter intentfilter;
            if(j >= i)
                break MISSING_BLOCK_LABEL_92;
            intentfilter = (IntentFilter)mControlFilters.get(j);
            if(!intentfilter.hasCategory(s) || !intentfilter.hasAction(s1)) goto _L2; else goto _L1
_L1:
            boolean flag = true;
_L4:
            return flag;
_L2:
            j++;
              goto _L3
            flag = false;
              goto _L4
        }

        public boolean supportsControlCategory(String s)
        {
            int i;
            int j;
            if(s == null)
                throw new IllegalArgumentException("category must not be null");
            MediaRouter.checkCallingThread();
            i = mControlFilters.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_62;
            if(!((IntentFilter)mControlFilters.get(j)).hasCategory(s)) goto _L2; else goto _L1
_L1:
            boolean flag = true;
_L4:
            return flag;
_L2:
            j++;
              goto _L3
            flag = false;
              goto _L4
        }

        public boolean supportsControlRequest(Intent intent)
        {
            ContentResolver contentresolver;
            int i;
            int j;
            if(intent == null)
                throw new IllegalArgumentException("intent must not be null");
            MediaRouter.checkCallingThread();
            contentresolver = MediaRouter.sGlobal.getContentResolver();
            i = mControlFilters.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_76;
            if(((IntentFilter)mControlFilters.get(j)).match(contentresolver, intent, true, "MediaRouter") < 0) goto _L2; else goto _L1
_L1:
            boolean flag = true;
_L4:
            return flag;
_L2:
            j++;
              goto _L3
            flag = false;
              goto _L4
        }

        public String toString()
        {
            return (new StringBuilder()).append("MediaRouter.RouteInfo{ uniqueId=").append(mUniqueId).append(", name=").append(mName).append(", description=").append(mDescription).append(", enabled=").append(mEnabled).append(", connecting=").append(mConnecting).append(", playbackType=").append(mPlaybackType).append(", playbackStream=").append(mPlaybackStream).append(", volumeHandling=").append(mVolumeHandling).append(", volume=").append(mVolume).append(", volumeMax=").append(mVolumeMax).append(", presentationDisplayId=").append(mPresentationDisplayId).append(", extras=").append(mExtras).append(", providerPackageName=").append(mProvider.getPackageName()).append(" }").toString();
        }

        int updateDescriptor(MediaRouteDescriptor mediaroutedescriptor)
        {
            int i = 0;
            if(mDescriptor != mediaroutedescriptor)
            {
                mDescriptor = mediaroutedescriptor;
                if(mediaroutedescriptor != null)
                {
                    if(!MediaRouter.equal(mName, mediaroutedescriptor.getName()))
                    {
                        mName = mediaroutedescriptor.getName();
                        i = false | true;
                    }
                    if(!MediaRouter.equal(mDescription, mediaroutedescriptor.getDescription()))
                    {
                        mDescription = mediaroutedescriptor.getDescription();
                        i |= 1;
                    }
                    if(mEnabled != mediaroutedescriptor.isEnabled())
                    {
                        mEnabled = mediaroutedescriptor.isEnabled();
                        i |= 1;
                    }
                    if(mConnecting != mediaroutedescriptor.isConnecting())
                    {
                        mConnecting = mediaroutedescriptor.isConnecting();
                        i |= 1;
                    }
                    if(!mControlFilters.equals(mediaroutedescriptor.getControlFilters()))
                    {
                        mControlFilters.clear();
                        mControlFilters.addAll(mediaroutedescriptor.getControlFilters());
                        i |= 1;
                    }
                    if(mPlaybackType != mediaroutedescriptor.getPlaybackType())
                    {
                        mPlaybackType = mediaroutedescriptor.getPlaybackType();
                        i |= 1;
                    }
                    if(mPlaybackStream != mediaroutedescriptor.getPlaybackStream())
                    {
                        mPlaybackStream = mediaroutedescriptor.getPlaybackStream();
                        i |= 1;
                    }
                    if(mVolumeHandling != mediaroutedescriptor.getVolumeHandling())
                    {
                        mVolumeHandling = mediaroutedescriptor.getVolumeHandling();
                        i |= 3;
                    }
                    if(mVolume != mediaroutedescriptor.getVolume())
                    {
                        mVolume = mediaroutedescriptor.getVolume();
                        i |= 3;
                    }
                    if(mVolumeMax != mediaroutedescriptor.getVolumeMax())
                    {
                        mVolumeMax = mediaroutedescriptor.getVolumeMax();
                        i |= 3;
                    }
                    if(mPresentationDisplayId != mediaroutedescriptor.getPresentationDisplayId())
                    {
                        mPresentationDisplayId = mediaroutedescriptor.getPresentationDisplayId();
                        mPresentationDisplay = null;
                        i |= 5;
                    }
                    if(!MediaRouter.equal(mExtras, mediaroutedescriptor.getExtras()))
                    {
                        mExtras = mediaroutedescriptor.getExtras();
                        i |= 1;
                    }
                }
            }
            return i;
        }

        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        private boolean mConnecting;
        private final ArrayList mControlFilters = new ArrayList();
        private String mDescription;
        private MediaRouteDescriptor mDescriptor;
        private final String mDescriptorId;
        private boolean mEnabled;
        private Bundle mExtras;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private int mPresentationDisplayId;
        private final ProviderInfo mProvider;
        private final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;





        RouteInfo(ProviderInfo providerinfo, String s, String s1)
        {
            mPresentationDisplayId = -1;
            mProvider = providerinfo;
            mDescriptorId = s;
            mUniqueId = s1;
        }
    }

    private static interface CallbackFlags
        extends Annotation
    {
    }


    MediaRouter(Context context)
    {
        mContext = context;
    }

    static void checkCallingThread()
    {
        if(Looper.myLooper() != Looper.getMainLooper())
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        else
            return;
    }

    static boolean equal(Object obj, Object obj1)
    {
        boolean flag;
        if(obj == obj1 || obj != null && obj1 != null && obj.equals(obj1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int findCallbackRecord(Callback callback)
    {
        int i;
        int j;
        i = mCallbackRecords.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_41;
        if(((CallbackRecord)mCallbackRecords.get(j)).mCallback != callback) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        j++;
          goto _L3
        j = -1;
          goto _L1
    }

    public static MediaRouter getInstance(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        checkCallingThread();
        if(sGlobal == null)
        {
            sGlobal = new GlobalMediaRouter(context.getApplicationContext());
            sGlobal.start();
        }
        return sGlobal.getRouter(context);
    }

    public void addCallback(MediaRouteSelector mediarouteselector, Callback callback)
    {
        addCallback(mediarouteselector, callback, 0);
    }

    public void addCallback(MediaRouteSelector mediarouteselector, Callback callback, int i)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("addCallback: selector=").append(mediarouteselector).append(", callback=").append(callback).append(", flags=").append(Integer.toHexString(i)).toString());
        int j = findCallbackRecord(callback);
        CallbackRecord callbackrecord;
        boolean flag;
        if(j < 0)
        {
            callbackrecord = new CallbackRecord(this, callback);
            mCallbackRecords.add(callbackrecord);
        } else
        {
            callbackrecord = (CallbackRecord)mCallbackRecords.get(j);
        }
        flag = false;
        if((i & (-1 ^ callbackrecord.mFlags)) != 0)
        {
            callbackrecord.mFlags = i | callbackrecord.mFlags;
            flag = true;
        }
        if(!callbackrecord.mSelector.contains(mediarouteselector))
        {
            callbackrecord.mSelector = (new MediaRouteSelector.Builder(callbackrecord.mSelector)).addSelector(mediarouteselector).build();
            flag = true;
        }
        if(flag)
            sGlobal.updateDiscoveryRequest();
    }

    public void addProvider(MediaRouteProvider mediarouteprovider)
    {
        if(mediarouteprovider == null)
            throw new IllegalArgumentException("providerInstance must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("addProvider: ").append(mediarouteprovider).toString());
        sGlobal.addProvider(mediarouteprovider);
    }

    public void addRemoteControlClient(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("remoteControlClient must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("addRemoteControlClient: ").append(obj).toString());
        sGlobal.addRemoteControlClient(obj);
    }

    public RouteInfo getDefaultRoute()
    {
        checkCallingThread();
        return sGlobal.getDefaultRoute();
    }

    public List getProviders()
    {
        checkCallingThread();
        return sGlobal.getProviders();
    }

    public List getRoutes()
    {
        checkCallingThread();
        return sGlobal.getRoutes();
    }

    public RouteInfo getSelectedRoute()
    {
        checkCallingThread();
        return sGlobal.getSelectedRoute();
    }

    public boolean isRouteAvailable(MediaRouteSelector mediarouteselector, int i)
    {
        if(mediarouteselector == null)
        {
            throw new IllegalArgumentException("selector must not be null");
        } else
        {
            checkCallingThread();
            return sGlobal.isRouteAvailable(mediarouteselector, i);
        }
    }

    public void removeCallback(Callback callback)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("removeCallback: callback=").append(callback).toString());
        int i = findCallbackRecord(callback);
        if(i >= 0)
        {
            mCallbackRecords.remove(i);
            sGlobal.updateDiscoveryRequest();
        }
    }

    public void removeProvider(MediaRouteProvider mediarouteprovider)
    {
        if(mediarouteprovider == null)
            throw new IllegalArgumentException("providerInstance must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("removeProvider: ").append(mediarouteprovider).toString());
        sGlobal.removeProvider(mediarouteprovider);
    }

    public void removeRemoteControlClient(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("remoteControlClient must not be null");
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("removeRemoteControlClient: ").append(obj).toString());
        sGlobal.removeRemoteControlClient(obj);
    }

    public void selectRoute(RouteInfo routeinfo)
    {
        if(routeinfo == null)
            throw new IllegalArgumentException("route must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("selectRoute: ").append(routeinfo).toString());
        sGlobal.selectRoute(routeinfo);
    }

    public void setMediaSession(Object obj)
    {
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("addMediaSession: ").append(obj).toString());
        sGlobal.setMediaSession(obj);
    }

    public RouteInfo updateSelectedRoute(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        checkCallingThread();
        if(DEBUG)
            Log.d("MediaRouter", (new StringBuilder()).append("updateSelectedRoute: ").append(mediarouteselector).toString());
        RouteInfo routeinfo = sGlobal.getSelectedRoute();
        if(!routeinfo.isDefault() && !routeinfo.matchesSelector(mediarouteselector))
        {
            routeinfo = sGlobal.getDefaultRoute();
            sGlobal.selectRoute(routeinfo);
        }
        return routeinfo;
    }

    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    private static final boolean DEBUG = Log.isLoggable("MediaRouter", 3);
    private static final String TAG = "MediaRouter";
    static GlobalMediaRouter sGlobal;
    final ArrayList mCallbackRecords = new ArrayList();
    final Context mContext;


}
