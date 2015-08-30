// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.*;
import android.content.res.Resources;
import android.media.AudioManager;
import android.view.Display;
import java.util.*;

// Referenced classes of package android.support.v7.media:
//            MediaRouteProvider, MediaRouterJellybeanMr2, MediaRouterJellybean, MediaRouterJellybeanMr1, 
//            MediaRouteDescriptor, MediaRouteDiscoveryRequest, MediaRouteSelector

abstract class SystemMediaRouteProvider extends MediaRouteProvider
{
    private static class JellybeanMr2Impl extends JellybeanMr1Impl
    {

        protected Object getDefaultRoute()
        {
            return MediaRouterJellybeanMr2.getDefaultRoute(mRouterObj);
        }

        protected boolean isConnecting(JellybeanImpl.SystemRouteRecord systemrouterecord)
        {
            return MediaRouterJellybeanMr2.RouteInfo.isConnecting(systemrouterecord.mRouteObj);
        }

        protected void onBuildSystemRouteDescriptor(JellybeanImpl.SystemRouteRecord systemrouterecord, MediaRouteDescriptor.Builder builder)
        {
            super.onBuildSystemRouteDescriptor(systemrouterecord, builder);
            CharSequence charsequence = MediaRouterJellybeanMr2.RouteInfo.getDescription(systemrouterecord.mRouteObj);
            if(charsequence != null)
                builder.setDescription(charsequence.toString());
        }

        protected void selectRoute(Object obj)
        {
            MediaRouterJellybean.selectRoute(mRouterObj, 0x800003, obj);
        }

        protected void updateCallback()
        {
            boolean flag = true;
            if(mCallbackRegistered)
                MediaRouterJellybean.removeCallback(mRouterObj, mCallbackObj);
            mCallbackRegistered = flag;
            Object obj = mRouterObj;
            int i = mRouteTypes;
            Object obj1 = mCallbackObj;
            if(!mActiveScan)
                flag = false;
            MediaRouterJellybeanMr2.addCallback(obj, i, obj1, flag | 2);
        }

        protected void updateUserRouteProperties(JellybeanImpl.UserRouteRecord userrouterecord)
        {
            super.updateUserRouteProperties(userrouterecord);
            MediaRouterJellybeanMr2.UserRouteInfo.setDescription(userrouterecord.mRouteObj, userrouterecord.mRoute.getDescription());
        }

        public JellybeanMr2Impl(Context context, SyncCallback synccallback)
        {
            super(context, synccallback);
        }
    }

    private static class JellybeanMr1Impl extends JellybeanImpl
        implements MediaRouterJellybeanMr1.Callback
    {

        protected Object createCallbackObj()
        {
            return MediaRouterJellybeanMr1.createCallback(this);
        }

        protected boolean isConnecting(JellybeanImpl.SystemRouteRecord systemrouterecord)
        {
            if(mIsConnectingWorkaround == null)
                mIsConnectingWorkaround = new MediaRouterJellybeanMr1.IsConnectingWorkaround();
            return mIsConnectingWorkaround.isConnecting(systemrouterecord.mRouteObj);
        }

        protected void onBuildSystemRouteDescriptor(JellybeanImpl.SystemRouteRecord systemrouterecord, MediaRouteDescriptor.Builder builder)
        {
            super.onBuildSystemRouteDescriptor(systemrouterecord, builder);
            if(!MediaRouterJellybeanMr1.RouteInfo.isEnabled(systemrouterecord.mRouteObj))
                builder.setEnabled(false);
            if(isConnecting(systemrouterecord))
                builder.setConnecting(true);
            Display display = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(systemrouterecord.mRouteObj);
            if(display != null)
                builder.setPresentationDisplayId(display.getDisplayId());
        }

        public void onRoutePresentationDisplayChanged(Object obj)
        {
            int i = findSystemRouteRecord(obj);
            if(i >= 0)
            {
                JellybeanImpl.SystemRouteRecord systemrouterecord = (JellybeanImpl.SystemRouteRecord)mSystemRouteRecords.get(i);
                Display display = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(obj);
                int j;
                if(display != null)
                    j = display.getDisplayId();
                else
                    j = -1;
                if(j != systemrouterecord.mRouteDescriptor.getPresentationDisplayId())
                {
                    systemrouterecord.mRouteDescriptor = (new MediaRouteDescriptor.Builder(systemrouterecord.mRouteDescriptor)).setPresentationDisplayId(j).build();
                    publishRoutes();
                }
            }
        }

        protected void updateCallback()
        {
            super.updateCallback();
            if(mActiveScanWorkaround == null)
                mActiveScanWorkaround = new MediaRouterJellybeanMr1.ActiveScanWorkaround(getContext(), getHandler());
            MediaRouterJellybeanMr1.ActiveScanWorkaround activescanworkaround = mActiveScanWorkaround;
            int i;
            if(mActiveScan)
                i = mRouteTypes;
            else
                i = 0;
            activescanworkaround.setActiveScanRouteTypes(i);
        }

        private MediaRouterJellybeanMr1.ActiveScanWorkaround mActiveScanWorkaround;
        private MediaRouterJellybeanMr1.IsConnectingWorkaround mIsConnectingWorkaround;

        public JellybeanMr1Impl(Context context, SyncCallback synccallback)
        {
            super(context, synccallback);
        }
    }

    static class JellybeanImpl extends SystemMediaRouteProvider
        implements MediaRouterJellybean.Callback, MediaRouterJellybean.VolumeCallback
    {
        protected final class SystemRouteController extends MediaRouteProvider.RouteController
        {

            public void onSetVolume(int i)
            {
                MediaRouterJellybean.RouteInfo.requestSetVolume(mRouteObj, i);
            }

            public void onUpdateVolume(int i)
            {
                MediaRouterJellybean.RouteInfo.requestUpdateVolume(mRouteObj, i);
            }

            private final Object mRouteObj;
            final JellybeanImpl this$0;

            public SystemRouteController(Object obj)
            {
                this$0 = JellybeanImpl.this;
                super();
                mRouteObj = obj;
            }
        }

        protected static final class UserRouteRecord
        {

            public final MediaRouter.RouteInfo mRoute;
            public final Object mRouteObj;

            public UserRouteRecord(MediaRouter.RouteInfo routeinfo, Object obj)
            {
                mRoute = routeinfo;
                mRouteObj = obj;
            }
        }

        protected static final class SystemRouteRecord
        {

            public MediaRouteDescriptor mRouteDescriptor;
            public final String mRouteDescriptorId;
            public final Object mRouteObj;

            public SystemRouteRecord(Object obj, String s)
            {
                mRouteObj = obj;
                mRouteDescriptorId = s;
            }
        }


        private boolean addSystemRouteNoPublish(Object obj)
        {
            boolean flag;
            if(getUserRouteRecord(obj) == null && findSystemRouteRecord(obj) < 0)
            {
                SystemRouteRecord systemrouterecord = new SystemRouteRecord(obj, assignRouteId(obj));
                updateSystemRouteDescriptor(systemrouterecord);
                mSystemRouteRecords.add(systemrouterecord);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private String assignRouteId(Object obj)
        {
            String s;
            boolean flag;
            if(getDefaultRoute() == obj)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = "DEFAULT_ROUTE";
            } else
            {
                Locale locale = Locale.US;
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(getRouteName(obj).hashCode());
                s = String.format(locale, "ROUTE_%08x", aobj);
            }
            if(findSystemRouteRecordByDescriptorId(s) >= 0) goto _L2; else goto _L1
_L1:
            return s;
_L2:
            int i = 2;
            do
            {
label0:
                {
                    Locale locale1 = Locale.US;
                    Object aobj1[] = new Object[2];
                    aobj1[0] = s;
                    aobj1[1] = Integer.valueOf(i);
                    String s1 = String.format(locale1, "%s_%d", aobj1);
                    if(findSystemRouteRecordByDescriptorId(s1) >= 0)
                        break label0;
                    s = s1;
                }
                if(true)
                    continue;
                i++;
            } while(true);
            if(true) goto _L1; else goto _L3
_L3:
        }

        private void updateSystemRoutes()
        {
            boolean flag = false;
            for(Iterator iterator = MediaRouterJellybean.getRoutes(mRouterObj).iterator(); iterator.hasNext();)
                flag |= addSystemRouteNoPublish(iterator.next());

            if(flag)
                publishRoutes();
        }

        protected Object createCallbackObj()
        {
            return MediaRouterJellybean.createCallback(this);
        }

        protected Object createVolumeCallbackObj()
        {
            return MediaRouterJellybean.createVolumeCallback(this);
        }

        protected int findSystemRouteRecord(Object obj)
        {
            int i;
            int j;
            i = mSystemRouteRecords.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_41;
            if(((SystemRouteRecord)mSystemRouteRecords.get(j)).mRouteObj != obj) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        protected int findSystemRouteRecordByDescriptorId(String s)
        {
            int i;
            int j;
            i = mSystemRouteRecords.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_44;
            if(!((SystemRouteRecord)mSystemRouteRecords.get(j)).mRouteDescriptorId.equals(s)) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        protected int findUserRouteRecord(MediaRouter.RouteInfo routeinfo)
        {
            int i;
            int j;
            i = mUserRouteRecords.size();
            j = 0;
_L3:
            if(j >= i)
                break MISSING_BLOCK_LABEL_41;
            if(((UserRouteRecord)mUserRouteRecords.get(j)).mRoute != routeinfo) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            j++;
              goto _L3
            j = -1;
              goto _L1
        }

        protected Object getDefaultRoute()
        {
            if(mGetDefaultRouteWorkaround == null)
                mGetDefaultRouteWorkaround = new MediaRouterJellybean.GetDefaultRouteWorkaround();
            return mGetDefaultRouteWorkaround.getDefaultRoute(mRouterObj);
        }

        protected String getRouteName(Object obj)
        {
            CharSequence charsequence = MediaRouterJellybean.RouteInfo.getName(obj, getContext());
            String s;
            if(charsequence != null)
                s = charsequence.toString();
            else
                s = "";
            return s;
        }

        protected UserRouteRecord getUserRouteRecord(Object obj)
        {
            Object obj1 = MediaRouterJellybean.RouteInfo.getTag(obj);
            UserRouteRecord userrouterecord;
            if(obj1 instanceof UserRouteRecord)
                userrouterecord = (UserRouteRecord)obj1;
            else
                userrouterecord = null;
            return userrouterecord;
        }

        protected void onBuildSystemRouteDescriptor(SystemRouteRecord systemrouterecord, MediaRouteDescriptor.Builder builder)
        {
            int i = MediaRouterJellybean.RouteInfo.getSupportedTypes(systemrouterecord.mRouteObj);
            if((i & 1) != 0)
                builder.addControlFilters(LIVE_AUDIO_CONTROL_FILTERS);
            if((i & 2) != 0)
                builder.addControlFilters(LIVE_VIDEO_CONTROL_FILTERS);
            builder.setPlaybackType(MediaRouterJellybean.RouteInfo.getPlaybackType(systemrouterecord.mRouteObj));
            builder.setPlaybackStream(MediaRouterJellybean.RouteInfo.getPlaybackStream(systemrouterecord.mRouteObj));
            builder.setVolume(MediaRouterJellybean.RouteInfo.getVolume(systemrouterecord.mRouteObj));
            builder.setVolumeMax(MediaRouterJellybean.RouteInfo.getVolumeMax(systemrouterecord.mRouteObj));
            builder.setVolumeHandling(MediaRouterJellybean.RouteInfo.getVolumeHandling(systemrouterecord.mRouteObj));
        }

        public MediaRouteProvider.RouteController onCreateRouteController(String s)
        {
            int i = findSystemRouteRecordByDescriptorId(s);
            SystemRouteController systemroutecontroller;
            if(i >= 0)
                systemroutecontroller = new SystemRouteController(((SystemRouteRecord)mSystemRouteRecords.get(i)).mRouteObj);
            else
                systemroutecontroller = null;
            return systemroutecontroller;
        }

        public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
        {
            int i = 0;
            boolean flag = false;
            if(mediaroutediscoveryrequest != null)
            {
                List list = mediaroutediscoveryrequest.getSelector().getControlCategories();
                int j = list.size();
                int k = 0;
                while(k < j) 
                {
                    String s = (String)list.get(k);
                    if(s.equals("android.media.intent.category.LIVE_AUDIO"))
                        i |= 1;
                    else
                    if(s.equals("android.media.intent.category.LIVE_VIDEO"))
                        i |= 2;
                    else
                        i |= 0x800000;
                    k++;
                }
                flag = mediaroutediscoveryrequest.isActiveScan();
            }
            if(mRouteTypes != i || mActiveScan != flag)
            {
                mRouteTypes = i;
                mActiveScan = flag;
                updateCallback();
                updateSystemRoutes();
            }
        }

        public void onRouteAdded(Object obj)
        {
            if(addSystemRouteNoPublish(obj))
                publishRoutes();
        }

        public void onRouteChanged(Object obj)
        {
            if(getUserRouteRecord(obj) == null)
            {
                int i = findSystemRouteRecord(obj);
                if(i >= 0)
                {
                    updateSystemRouteDescriptor((SystemRouteRecord)mSystemRouteRecords.get(i));
                    publishRoutes();
                }
            }
        }

        public void onRouteGrouped(Object obj, Object obj1, int i)
        {
        }

        public void onRouteRemoved(Object obj)
        {
            if(getUserRouteRecord(obj) == null)
            {
                int i = findSystemRouteRecord(obj);
                if(i >= 0)
                {
                    mSystemRouteRecords.remove(i);
                    publishRoutes();
                }
            }
        }

        public void onRouteSelected(int i, Object obj)
        {
            if(obj == MediaRouterJellybean.getSelectedRoute(mRouterObj, 0x800003)) goto _L2; else goto _L1
_L1:
            return;
_L2:
            UserRouteRecord userrouterecord = getUserRouteRecord(obj);
            if(userrouterecord != null)
            {
                userrouterecord.mRoute.select();
            } else
            {
                int j = findSystemRouteRecord(obj);
                if(j >= 0)
                {
                    SystemRouteRecord systemrouterecord = (SystemRouteRecord)mSystemRouteRecords.get(j);
                    MediaRouter.RouteInfo routeinfo = mSyncCallback.getSystemRouteByDescriptorId(systemrouterecord.mRouteDescriptorId);
                    if(routeinfo != null)
                        routeinfo.select();
                }
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void onRouteUngrouped(Object obj, Object obj1)
        {
        }

        public void onRouteUnselected(int i, Object obj)
        {
        }

        public void onRouteVolumeChanged(Object obj)
        {
            if(getUserRouteRecord(obj) == null)
            {
                int i = findSystemRouteRecord(obj);
                if(i >= 0)
                {
                    SystemRouteRecord systemrouterecord = (SystemRouteRecord)mSystemRouteRecords.get(i);
                    int j = MediaRouterJellybean.RouteInfo.getVolume(obj);
                    if(j != systemrouterecord.mRouteDescriptor.getVolume())
                    {
                        systemrouterecord.mRouteDescriptor = (new MediaRouteDescriptor.Builder(systemrouterecord.mRouteDescriptor)).setVolume(j).build();
                        publishRoutes();
                    }
                }
            }
        }

        public void onSyncRouteAdded(MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo.getProviderInstance() == this) goto _L2; else goto _L1
_L1:
            Object obj = MediaRouterJellybean.createUserRoute(mRouterObj, mUserRouteCategoryObj);
            UserRouteRecord userrouterecord = new UserRouteRecord(routeinfo, obj);
            MediaRouterJellybean.RouteInfo.setTag(obj, userrouterecord);
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(obj, mVolumeCallbackObj);
            updateUserRouteProperties(userrouterecord);
            mUserRouteRecords.add(userrouterecord);
            MediaRouterJellybean.addUserRoute(mRouterObj, obj);
_L4:
            return;
_L2:
            int i = findSystemRouteRecord(MediaRouterJellybean.getSelectedRoute(mRouterObj, 0x800003));
            if(i >= 0 && ((SystemRouteRecord)mSystemRouteRecords.get(i)).mRouteDescriptorId.equals(routeinfo.getDescriptorId()))
                routeinfo.select();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onSyncRouteChanged(MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo.getProviderInstance() != this)
            {
                int i = findUserRouteRecord(routeinfo);
                if(i >= 0)
                    updateUserRouteProperties((UserRouteRecord)mUserRouteRecords.get(i));
            }
        }

        public void onSyncRouteRemoved(MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo.getProviderInstance() != this)
            {
                int i = findUserRouteRecord(routeinfo);
                if(i >= 0)
                {
                    UserRouteRecord userrouterecord = (UserRouteRecord)mUserRouteRecords.remove(i);
                    MediaRouterJellybean.RouteInfo.setTag(userrouterecord.mRouteObj, null);
                    MediaRouterJellybean.UserRouteInfo.setVolumeCallback(userrouterecord.mRouteObj, null);
                    MediaRouterJellybean.removeUserRoute(mRouterObj, userrouterecord.mRouteObj);
                }
            }
        }

        public void onSyncRouteSelected(MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo.isSelected()) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(routeinfo.getProviderInstance() != this)
            {
                int j = findUserRouteRecord(routeinfo);
                if(j >= 0)
                    selectRoute(((UserRouteRecord)mUserRouteRecords.get(j)).mRouteObj);
            } else
            {
                int i = findSystemRouteRecordByDescriptorId(routeinfo.getDescriptorId());
                if(i >= 0)
                    selectRoute(((SystemRouteRecord)mSystemRouteRecords.get(i)).mRouteObj);
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void onVolumeSetRequest(Object obj, int i)
        {
            UserRouteRecord userrouterecord = getUserRouteRecord(obj);
            if(userrouterecord != null)
                userrouterecord.mRoute.requestSetVolume(i);
        }

        public void onVolumeUpdateRequest(Object obj, int i)
        {
            UserRouteRecord userrouterecord = getUserRouteRecord(obj);
            if(userrouterecord != null)
                userrouterecord.mRoute.requestUpdateVolume(i);
        }

        protected void publishRoutes()
        {
            MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder();
            int i = mSystemRouteRecords.size();
            for(int j = 0; j < i; j++)
                builder.addRoute(((SystemRouteRecord)mSystemRouteRecords.get(j)).mRouteDescriptor);

            setDescriptor(builder.build());
        }

        protected void selectRoute(Object obj)
        {
            if(mSelectRouteWorkaround == null)
                mSelectRouteWorkaround = new MediaRouterJellybean.SelectRouteWorkaround();
            mSelectRouteWorkaround.selectRoute(mRouterObj, 0x800003, obj);
        }

        protected void updateCallback()
        {
            if(mCallbackRegistered)
            {
                mCallbackRegistered = false;
                MediaRouterJellybean.removeCallback(mRouterObj, mCallbackObj);
            }
            if(mRouteTypes != 0)
            {
                mCallbackRegistered = true;
                MediaRouterJellybean.addCallback(mRouterObj, mRouteTypes, mCallbackObj);
            }
        }

        protected void updateSystemRouteDescriptor(SystemRouteRecord systemrouterecord)
        {
            MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(systemrouterecord.mRouteDescriptorId, getRouteName(systemrouterecord.mRouteObj));
            onBuildSystemRouteDescriptor(systemrouterecord, builder);
            systemrouterecord.mRouteDescriptor = builder.build();
        }

        protected void updateUserRouteProperties(UserRouteRecord userrouterecord)
        {
            MediaRouterJellybean.UserRouteInfo.setName(userrouterecord.mRouteObj, userrouterecord.mRoute.getName());
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(userrouterecord.mRouteObj, userrouterecord.mRoute.getPlaybackType());
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(userrouterecord.mRouteObj, userrouterecord.mRoute.getPlaybackStream());
            MediaRouterJellybean.UserRouteInfo.setVolume(userrouterecord.mRouteObj, userrouterecord.mRoute.getVolume());
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(userrouterecord.mRouteObj, userrouterecord.mRoute.getVolumeMax());
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(userrouterecord.mRouteObj, userrouterecord.mRoute.getVolumeHandling());
        }

        private static final ArrayList LIVE_AUDIO_CONTROL_FILTERS;
        private static final ArrayList LIVE_VIDEO_CONTROL_FILTERS;
        protected boolean mActiveScan;
        protected final Object mCallbackObj = createCallbackObj();
        protected boolean mCallbackRegistered;
        private MediaRouterJellybean.GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;
        protected int mRouteTypes;
        protected final Object mRouterObj;
        private MediaRouterJellybean.SelectRouteWorkaround mSelectRouteWorkaround;
        private final SyncCallback mSyncCallback;
        protected final ArrayList mSystemRouteRecords = new ArrayList();
        protected final Object mUserRouteCategoryObj;
        protected final ArrayList mUserRouteRecords = new ArrayList();
        protected final Object mVolumeCallbackObj = createVolumeCallbackObj();

        static 
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            LIVE_AUDIO_CONTROL_FILTERS = new ArrayList();
            LIVE_AUDIO_CONTROL_FILTERS.add(intentfilter);
            IntentFilter intentfilter1 = new IntentFilter();
            intentfilter1.addCategory("android.media.intent.category.LIVE_VIDEO");
            LIVE_VIDEO_CONTROL_FILTERS = new ArrayList();
            LIVE_VIDEO_CONTROL_FILTERS.add(intentfilter1);
        }

        public JellybeanImpl(Context context, SyncCallback synccallback)
        {
            super(context);
            mSyncCallback = synccallback;
            mRouterObj = MediaRouterJellybean.getMediaRouter(context);
            Resources resources = context.getResources();
            mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(mRouterObj, resources.getString(android.support.v7.mediarouter.R.string.mr_user_route_category_name), false);
            updateSystemRoutes();
        }
    }

    static class LegacyImpl extends SystemMediaRouteProvider
    {
        final class VolumeChangeReceiver extends BroadcastReceiver
        {

            public void onReceive(Context context, Intent intent)
            {
                if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3)
                {
                    int i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
                    if(i >= 0 && i != mLastReportedVolume)
                        publishRoutes();
                }
            }

            public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
            public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
            public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
            final LegacyImpl this$0;

            VolumeChangeReceiver()
            {
                this$0 = LegacyImpl.this;
                super();
            }
        }

        final class DefaultRouteController extends MediaRouteProvider.RouteController
        {

            public void onSetVolume(int i)
            {
                mAudioManager.setStreamVolume(3, i, 0);
                publishRoutes();
            }

            public void onUpdateVolume(int i)
            {
                int j = mAudioManager.getStreamVolume(3);
                if(Math.min(mAudioManager.getStreamMaxVolume(3), Math.max(0, j + i)) != j)
                    mAudioManager.setStreamVolume(3, j, 0);
                publishRoutes();
            }

            final LegacyImpl this$0;

            DefaultRouteController()
            {
                this$0 = LegacyImpl.this;
                super();
            }
        }


        private void publishRoutes()
        {
            Resources resources = getContext().getResources();
            int i = mAudioManager.getStreamMaxVolume(3);
            mLastReportedVolume = mAudioManager.getStreamVolume(3);
            MediaRouteDescriptor mediaroutedescriptor = (new MediaRouteDescriptor.Builder("DEFAULT_ROUTE", resources.getString(android.support.v7.mediarouter.R.string.mr_system_route_name))).addControlFilters(CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(i).setVolume(mLastReportedVolume).build();
            setDescriptor((new MediaRouteProviderDescriptor.Builder()).addRoute(mediaroutedescriptor).build());
        }

        public MediaRouteProvider.RouteController onCreateRouteController(String s)
        {
            DefaultRouteController defaultroutecontroller;
            if(s.equals("DEFAULT_ROUTE"))
                defaultroutecontroller = new DefaultRouteController();
            else
                defaultroutecontroller = null;
            return defaultroutecontroller;
        }

        private static final ArrayList CONTROL_FILTERS;
        private static final int PLAYBACK_STREAM = 3;
        private final AudioManager mAudioManager;
        private int mLastReportedVolume;
        private final VolumeChangeReceiver mVolumeChangeReceiver = new VolumeChangeReceiver();

        static 
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            intentfilter.addCategory("android.media.intent.category.LIVE_VIDEO");
            CONTROL_FILTERS = new ArrayList();
            CONTROL_FILTERS.add(intentfilter);
        }




        public LegacyImpl(Context context)
        {
            super(context);
            mLastReportedVolume = -1;
            mAudioManager = (AudioManager)context.getSystemService("audio");
            context.registerReceiver(mVolumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            publishRoutes();
        }
    }

    public static interface SyncCallback
    {

        public abstract MediaRouter.RouteInfo getSystemRouteByDescriptorId(String s);
    }


    protected SystemMediaRouteProvider(Context context)
    {
        super(context, new MediaRouteProvider.ProviderMetadata(new ComponentName("android", android/support/v7/media/SystemMediaRouteProvider.getName())));
    }

    public static SystemMediaRouteProvider obtain(Context context, SyncCallback synccallback)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 18)
            obj = new JellybeanMr2Impl(context, synccallback);
        else
        if(android.os.Build.VERSION.SDK_INT >= 17)
            obj = new JellybeanMr1Impl(context, synccallback);
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            obj = new JellybeanImpl(context, synccallback);
        else
            obj = new LegacyImpl(context);
        return ((SystemMediaRouteProvider) (obj));
    }

    public void onSyncRouteAdded(MediaRouter.RouteInfo routeinfo)
    {
    }

    public void onSyncRouteChanged(MediaRouter.RouteInfo routeinfo)
    {
    }

    public void onSyncRouteRemoved(MediaRouter.RouteInfo routeinfo)
    {
    }

    public void onSyncRouteSelected(MediaRouter.RouteInfo routeinfo)
    {
    }

    public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";
    public static final String PACKAGE_NAME = "android";
    private static final String TAG = "SystemMediaRouteProvider";
}
