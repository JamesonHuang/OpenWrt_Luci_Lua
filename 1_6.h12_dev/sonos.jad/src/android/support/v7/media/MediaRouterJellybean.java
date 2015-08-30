// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.media.RemoteControlClient;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final class MediaRouterJellybean
{
    static class VolumeCallbackProxy extends android.media.MediaRouter.VolumeCallback
    {

        public void onVolumeSetRequest(android.media.MediaRouter.RouteInfo routeinfo, int i)
        {
            mCallback.onVolumeSetRequest(routeinfo, i);
        }

        public void onVolumeUpdateRequest(android.media.MediaRouter.RouteInfo routeinfo, int i)
        {
            mCallback.onVolumeUpdateRequest(routeinfo, i);
        }

        protected final VolumeCallback mCallback;

        public VolumeCallbackProxy(VolumeCallback volumecallback)
        {
            mCallback = volumecallback;
        }
    }

    static class CallbackProxy extends android.media.MediaRouter.Callback
    {

        public void onRouteAdded(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteAdded(routeinfo);
        }

        public void onRouteChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteChanged(routeinfo);
        }

        public void onRouteGrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup, int i)
        {
            mCallback.onRouteGrouped(routeinfo, routegroup, i);
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteRemoved(routeinfo);
        }

        public void onRouteSelected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteSelected(i, routeinfo);
        }

        public void onRouteUngrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup)
        {
            mCallback.onRouteUngrouped(routeinfo, routegroup);
        }

        public void onRouteUnselected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteUnselected(i, routeinfo);
        }

        public void onRouteVolumeChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            mCallback.onRouteVolumeChanged(routeinfo);
        }

        protected final Callback mCallback;

        public CallbackProxy(Callback callback)
        {
            mCallback = callback;
        }
    }

    public static final class GetDefaultRouteWorkaround
    {

        public Object getDefaultRoute(Object obj)
        {
            MediaRouter mediarouter = (MediaRouter)obj;
            if(mGetSystemAudioRouteMethod == null) goto _L2; else goto _L1
_L1:
            Object obj2 = mGetSystemAudioRouteMethod.invoke(mediarouter, new Object[0]);
            Object obj1 = obj2;
_L4:
            return obj1;
            InvocationTargetException invocationtargetexception;
            invocationtargetexception;
_L2:
            obj1 = mediarouter.getRouteAt(0);
            if(true) goto _L4; else goto _L3
_L3:
            IllegalAccessException illegalaccessexception;
            illegalaccessexception;
              goto _L2
        }

        private Method mGetSystemAudioRouteMethod;

        public GetDefaultRouteWorkaround()
        {
            if(android.os.Build.VERSION.SDK_INT < 16 || android.os.Build.VERSION.SDK_INT > 17)
                throw new UnsupportedOperationException();
            mGetSystemAudioRouteMethod = android/media/MediaRouter.getMethod("getSystemAudioRoute", new Class[0]);
_L2:
            return;
            NoSuchMethodException nosuchmethodexception;
            nosuchmethodexception;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    public static final class SelectRouteWorkaround
    {

        public void selectRoute(Object obj, int i, Object obj1)
        {
            MediaRouter mediarouter;
            android.media.MediaRouter.RouteInfo routeinfo;
            mediarouter = (MediaRouter)obj;
            routeinfo = (android.media.MediaRouter.RouteInfo)obj1;
            if((0x800000 & routeinfo.getSupportedTypes()) != 0)
                break MISSING_BLOCK_LABEL_79;
            if(mSelectRouteIntMethod == null)
                break MISSING_BLOCK_LABEL_105;
            Method method = mSelectRouteIntMethod;
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = routeinfo;
            method.invoke(mediarouter, aobj);
_L1:
            return;
            IllegalAccessException illegalaccessexception;
            illegalaccessexception;
            Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", illegalaccessexception);
_L2:
            mediarouter.selectRoute(i, routeinfo);
              goto _L1
            InvocationTargetException invocationtargetexception;
            invocationtargetexception;
            Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", invocationtargetexception);
              goto _L2
            Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route because the platform is missing the selectRouteInt() method.  Media routing may not work.");
              goto _L2
        }

        private Method mSelectRouteIntMethod;

        public SelectRouteWorkaround()
        {
            if(android.os.Build.VERSION.SDK_INT < 16 || android.os.Build.VERSION.SDK_INT > 17)
                throw new UnsupportedOperationException();
            Class aclass[] = new Class[2];
            aclass[0] = Integer.TYPE;
            aclass[1] = android/media/MediaRouter$RouteInfo;
            mSelectRouteIntMethod = android/media/MediaRouter.getMethod("selectRouteInt", aclass);
_L2:
            return;
            NoSuchMethodException nosuchmethodexception;
            nosuchmethodexception;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    public static interface VolumeCallback
    {

        public abstract void onVolumeSetRequest(Object obj, int i);

        public abstract void onVolumeUpdateRequest(Object obj, int i);
    }

    public static interface Callback
    {

        public abstract void onRouteAdded(Object obj);

        public abstract void onRouteChanged(Object obj);

        public abstract void onRouteGrouped(Object obj, Object obj1, int i);

        public abstract void onRouteRemoved(Object obj);

        public abstract void onRouteSelected(int i, Object obj);

        public abstract void onRouteUngrouped(Object obj, Object obj1);

        public abstract void onRouteUnselected(int i, Object obj);

        public abstract void onRouteVolumeChanged(Object obj);
    }

    public static final class RouteCategory
    {

        public static CharSequence getName(Object obj, Context context)
        {
            return ((android.media.MediaRouter.RouteCategory)obj).getName(context);
        }

        public static List getRoutes(Object obj)
        {
            ArrayList arraylist = new ArrayList();
            ((android.media.MediaRouter.RouteCategory)obj).getRoutes(arraylist);
            return arraylist;
        }

        public static int getSupportedTypes(Object obj)
        {
            return ((android.media.MediaRouter.RouteCategory)obj).getSupportedTypes();
        }

        public static boolean isGroupable(Object obj)
        {
            return ((android.media.MediaRouter.RouteCategory)obj).isGroupable();
        }

        public RouteCategory()
        {
        }
    }

    public static final class UserRouteInfo
    {

        public static void setIconDrawable(Object obj, Drawable drawable)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setIconDrawable(drawable);
        }

        public static void setName(Object obj, CharSequence charsequence)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setName(charsequence);
        }

        public static void setPlaybackStream(Object obj, int i)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setPlaybackStream(i);
        }

        public static void setPlaybackType(Object obj, int i)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setPlaybackType(i);
        }

        public static void setRemoteControlClient(Object obj, Object obj1)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setRemoteControlClient((RemoteControlClient)obj1);
        }

        public static void setStatus(Object obj, CharSequence charsequence)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setStatus(charsequence);
        }

        public static void setVolume(Object obj, int i)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setVolume(i);
        }

        public static void setVolumeCallback(Object obj, Object obj1)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setVolumeCallback((android.media.MediaRouter.VolumeCallback)obj1);
        }

        public static void setVolumeHandling(Object obj, int i)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setVolumeHandling(i);
        }

        public static void setVolumeMax(Object obj, int i)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setVolumeMax(i);
        }

        public UserRouteInfo()
        {
        }
    }

    public static final class RouteGroup
    {

        public static List getGroupedRoutes(Object obj)
        {
            android.media.MediaRouter.RouteGroup routegroup = (android.media.MediaRouter.RouteGroup)obj;
            int i = routegroup.getRouteCount();
            ArrayList arraylist = new ArrayList(i);
            for(int j = 0; j < i; j++)
                arraylist.add(routegroup.getRouteAt(j));

            return arraylist;
        }

        public RouteGroup()
        {
        }
    }

    public static final class RouteInfo
    {

        public static Object getCategory(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getCategory();
        }

        public static Object getGroup(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getGroup();
        }

        public static Drawable getIconDrawable(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getIconDrawable();
        }

        public static CharSequence getName(Object obj, Context context)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getName(context);
        }

        public static int getPlaybackStream(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getPlaybackStream();
        }

        public static int getPlaybackType(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getPlaybackType();
        }

        public static CharSequence getStatus(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getStatus();
        }

        public static int getSupportedTypes(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getSupportedTypes();
        }

        public static Object getTag(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getTag();
        }

        public static int getVolume(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getVolume();
        }

        public static int getVolumeHandling(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getVolumeHandling();
        }

        public static int getVolumeMax(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getVolumeMax();
        }

        public static boolean isGroup(Object obj)
        {
            return obj instanceof android.media.MediaRouter.RouteGroup;
        }

        public static void requestSetVolume(Object obj, int i)
        {
            ((android.media.MediaRouter.RouteInfo)obj).requestSetVolume(i);
        }

        public static void requestUpdateVolume(Object obj, int i)
        {
            ((android.media.MediaRouter.RouteInfo)obj).requestUpdateVolume(i);
        }

        public static void setTag(Object obj, Object obj1)
        {
            ((android.media.MediaRouter.RouteInfo)obj).setTag(obj1);
        }

        public RouteInfo()
        {
        }
    }


    MediaRouterJellybean()
    {
    }

    public static void addCallback(Object obj, int i, Object obj1)
    {
        ((MediaRouter)obj).addCallback(i, (android.media.MediaRouter.Callback)obj1);
    }

    public static void addUserRoute(Object obj, Object obj1)
    {
        ((MediaRouter)obj).addUserRoute((android.media.MediaRouter.UserRouteInfo)obj1);
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static Object createRouteCategory(Object obj, String s, boolean flag)
    {
        return ((MediaRouter)obj).createRouteCategory(s, flag);
    }

    public static Object createUserRoute(Object obj, Object obj1)
    {
        return ((MediaRouter)obj).createUserRoute((android.media.MediaRouter.RouteCategory)obj1);
    }

    public static Object createVolumeCallback(VolumeCallback volumecallback)
    {
        return new VolumeCallbackProxy(volumecallback);
    }

    public static List getCategories(Object obj)
    {
        MediaRouter mediarouter = (MediaRouter)obj;
        int i = mediarouter.getCategoryCount();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(mediarouter.getCategoryAt(j));

        return arraylist;
    }

    public static Object getMediaRouter(Context context)
    {
        return context.getSystemService("media_router");
    }

    public static List getRoutes(Object obj)
    {
        MediaRouter mediarouter = (MediaRouter)obj;
        int i = mediarouter.getRouteCount();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(mediarouter.getRouteAt(j));

        return arraylist;
    }

    public static Object getSelectedRoute(Object obj, int i)
    {
        return ((MediaRouter)obj).getSelectedRoute(i);
    }

    public static void removeCallback(Object obj, Object obj1)
    {
        ((MediaRouter)obj).removeCallback((android.media.MediaRouter.Callback)obj1);
    }

    public static void removeUserRoute(Object obj, Object obj1)
    {
        ((MediaRouter)obj).removeUserRoute((android.media.MediaRouter.UserRouteInfo)obj1);
    }

    public static void selectRoute(Object obj, int i, Object obj1)
    {
        ((MediaRouter)obj).selectRoute(i, (android.media.MediaRouter.RouteInfo)obj1);
    }

    public static final int ALL_ROUTE_TYPES = 0x800003;
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
    public static final int ROUTE_TYPE_USER = 0x800000;
    private static final String TAG = "MediaRouterJellybean";
}
